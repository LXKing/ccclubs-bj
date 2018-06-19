<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/specialcar.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/specialcar.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csSpecialCar.csscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csscId,csscHost,csscInfo,csscPerson,csscUnitOrder,csscName,csscMobile,csscCount,csscCarModel,csscCarNumber,csscDriverName,csscDriverPhone,csscDriverImage,csscDriverColor,csscDriverCarInfo,csscMsg,csscStartPoint,csscStartAddr,csscStartTime,csscFinishPoint,csscFinishAddr,csscFinishTime,csscDuration,csscRealDur,csscKilo,csscScope,csscAssess,csscEstimateFee,csscPreditMileage,csscDemand,csscIsCarpool,csscDriverArrivaltime,csscStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csscId,csscHost,csscInfo,csscPerson,csscUnitOrder,csscName,csscMobile,csscCount,csscCarModel,csscCarNumber,csscDriverName,csscDriverPhone,csscDriverImage,csscDriverColor,csscDriverCarInfo,csscMsg,csscStartPoint,csscStartAddr,csscStartTime,csscFinishPoint,csscFinishAddr,csscFinishTime,csscDuration,csscRealDur,csscKilo,csscScope,csscAssess,csscEstimateFee,csscPreditMileage,csscDemand,csscIsCarpool,csscDriverArrivaltime,csscStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csscId,csscHost,csscInfo,csscPerson,csscUnitOrder,csscName,csscMobile,csscCount,csscCarModel,csscCarNumber,csscDriverName,csscDriverPhone,csscDriverImage,csscDriverColor,csscDriverCarInfo,csscMsg,csscStartPoint,csscStartAddr,csscStartTime,csscFinishPoint,csscFinishAddr,csscFinishTime,csscDuration,csscRealDur,csscKilo,csscScope,csscAssess,csscEstimateFee,csscPreditMileage,csscDemand,csscIsCarpool,csscDriverArrivaltime,csscStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csscId,csscHost,csscInfo,csscPerson,csscUnitOrder,csscName,csscMobile,csscCount,csscCarModel,csscCarNumber,csscDriverName,csscDriverPhone,csscDriverImage,csscDriverColor,csscDriverCarInfo,csscMsg,csscStartPoint,csscStartAddr,csscStartTime,csscFinishPoint,csscFinishAddr,csscFinishTime,csscDuration,csscRealDur,csscKilo,csscScope,csscAssess,csscEstimateFee,csscPreditMileage,csscDemand,csscIsCarpool,csscDriverArrivaltime,csscStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"专车订单编辑":CTRL.title}</title>
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
		if($("#csscThirdFeeData").size()>0){
			 //构造编辑页csSpecialCar.csscThirdFeeData为html编辑控件
			 KE.init({
				id : 'csscThirdFeeData',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=specialcar_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("csscThirdFeeData");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#specialcarForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csscId==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscHost==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscInfo==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscInfo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscPerson==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscPerson":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscUnitOrder==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscUnitOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscName==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscName":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "乘客姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscMobile==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscMobile":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "乘客电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscCount==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscCount":function(el){
			if(jQuery.trim(el.value)=="")
				return "乘车人数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscCarModel==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscCarModel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscCarNumber==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscCarNumber":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "车牌号码最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscDriverName==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscDriverName":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "司机姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscDriverPhone==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscDriverPhone":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "司机电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscDriverImage==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscDriverImage":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscDriverColor==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscDriverColor":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车辆颜色最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscDriverCarInfo==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscDriverCarInfo":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车型信息最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscMsg==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscMsg":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "客户留言最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscStartPoint==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscStartPoint":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscStartAddr==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscStartAddr":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "起点地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscStartTime==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscStartTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "开始时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscFinishPoint==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscFinishPoint":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscFinishAddr==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscFinishAddr":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "终点地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscFinishTime==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscFinishTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscDuration==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscDuration":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscRealDur==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscRealDur":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscKilo==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscKilo":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscScope==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscScope":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择用车评分";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscAssess==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscAssess":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "用车评价最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscThird==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscThird":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscThirdNo==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscThirdNo":function(el){
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "三方订单号最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscThirdFee==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscThirdFee":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "三方费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscThirdFeeData==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscThirdFeeData":function(el){
			if($("#csscThirdFeeData").get(0))
			 	KE.sync("csscThirdFeeData");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "三方费用信息最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscThirdOtherFee==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscThirdOtherFee":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "三方其它费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscThirdOtherFeeDes==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscThirdOtherFeeDes":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "三方其它费用描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscAddTime==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscResponse==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscResponse":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "响应状态最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscStatus==true">
		${lz:set("haveEditable",true)}
		,"csSpecialCar.csscStatus":function(el){
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
	<form class="form editform" ref="CsSpecialCar" id="specialcarForm" name="specialcarForm" action="${empty CTRL.action?"specialcar_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写专车订单相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csSpecialCarToken}" name="csSpecialCarToken" id="csSpecialCarToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csSpecialCar.csscId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csscId和after$csscId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscId==true">
	${before$csscId}
	<dl class="csscId ${CTRL.e.csscId?"hidden":""}" major  ref="csscId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csscId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csSpecialCar$csscId!=null">${csSpecialCar$csscId}</s:if><s:else>
			<input type="hidden" value="${(csSpecialCar.csscId)}" name="csSpecialCar.csscId" id="csscId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscId">${csSpecialCar.csscId}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscId$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscId}
	</s:if>
	
	${lz:set("注释","*****************运营地区字段的输入框代码*****************")}
	${lz:set("注释","before$csscHost和after$csscHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscHost==true">
	${before$csscHost}
	<dl class="csscHost " major  ref="csscHost" >
		<dt>运营地区:</dt>
		<s:if test="#request.CTRL.e.csscHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csSpecialCar$csscHost!=null">${csSpecialCar$csscHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csscHost" name="csSpecialCar.csscHost">
		 				<option selected value="${(csSpecialCar.csscHost)}">
		 					${get:SrvHost(csSpecialCar.csscHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csscHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csscHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****运营地区字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscHost">${csSpecialCar.csscHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${(csSpecialCar.csscHost)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csSpecialCar.csscHost$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscHost}
	</s:if>
	
	${lz:set("注释","*****************企业信息字段的输入框代码*****************")}
	${lz:set("注释","before$csscInfo和after$csscInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscInfo==true">
	${before$csscInfo}
	<dl class="csscInfo " major  ref="csscInfo" >
		<dt>企业信息:</dt>
		<s:if test="#request.CTRL.e.csscInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csSpecialCar$csscInfo!=null">${csSpecialCar$csscInfo}</s:if><s:else>
		 			<input title="请输入企业信息名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csscHost}" type="text" id="csscInfo" name="csSpecialCar.csscInfo" text="${get:CsUnitInfo(csSpecialCar.csscInfo).csuiName}" value="${(csSpecialCar.csscInfo)}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csscInfo').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?key='+$('#csscInfo').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****企业信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscInfo">${csSpecialCar.csscInfo}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${(csSpecialCar.csscInfo)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csSpecialCar.csscInfo$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscInfo}
	</s:if>
	
	${lz:set("注释","*****************用车人员字段的输入框代码*****************")}
	${lz:set("注释","before$csscPerson和after$csscPerson变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscPerson==true">
	${before$csscPerson}
	<dl class="csscPerson " major  ref="csscPerson" >
		<dt>用车人员:</dt>
		<s:if test="#request.CTRL.e.csscPerson==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csSpecialCar$csscPerson!=null">${csSpecialCar$csscPerson}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/unit/person_query.do?size=-1&csupHost={csscHost}" id="csscPerson" name="csSpecialCar.csscPerson">
		 				<option selected value="${(csSpecialCar.csscPerson)}">
		 					${get:CsUnitPerson(csSpecialCar.csscPerson).csupName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csscPerson').val())==''){return;};window.href('${basePath}${proname}/unit/person_details.do?key='+$('#csscPerson').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****用车人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscPerson">${csSpecialCar.csscPerson}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/person_details.do?key=${(csSpecialCar.csscPerson)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csSpecialCar.csscPerson$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscPerson}
	</s:if>
	
	${lz:set("注释","*****************企业订单字段的输入框代码*****************")}
	${lz:set("注释","before$csscUnitOrder和after$csscUnitOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscUnitOrder==true">
	${before$csscUnitOrder}
	<dl class="csscUnitOrder " major  ref="csscUnitOrder" >
		<dt>企业订单:</dt>
		<s:if test="#request.CTRL.e.csscUnitOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csSpecialCar$csscUnitOrder!=null">${csSpecialCar$csscUnitOrder}</s:if><s:else>
		 			<input title="请输入企业订单系统订单进行查询" class="combox narrow" action="${basePath}${proname}/unit/order_query.do?value={param}&csuoHost={csscHost}" type="text" id="csscUnitOrder" name="csSpecialCar.csscUnitOrder" text="${get:CsUnitOrder(csSpecialCar.csscUnitOrder).csuoOrder}" value="${(csSpecialCar.csscUnitOrder)}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csscUnitOrder').val())==''){return;};window.href('${basePath}${proname}/unit/order_details.do?key='+$('#csscUnitOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****企业订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscUnitOrder">${csSpecialCar.csscUnitOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/order_details.do?key=${(csSpecialCar.csscUnitOrder)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csSpecialCar.csscUnitOrder$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscUnitOrder}
	</s:if>
	
	${lz:set("注释","*****************乘客姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csscName和after$csscName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscName==true">
	${before$csscName}
	<dl class="csscName " major  ref="csscName" >
		<dt>乘客姓名:</dt>
		<s:if test="#request.CTRL.e.csscName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSpecialCar$csscName!=null">${csSpecialCar$csscName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSpecialCar.csscName" id="csscName"  value="${(csSpecialCar.csscName)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的乘客姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****乘客姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscName">${csSpecialCar.csscName}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscName$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscName}
	</s:if>
	
	${lz:set("注释","*****************乘客电话字段的输入框代码*****************")}
	${lz:set("注释","before$csscMobile和after$csscMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscMobile==true">
	${before$csscMobile}
	<dl class="csscMobile " major  ref="csscMobile" >
		<dt>乘客电话:</dt>
		<s:if test="#request.CTRL.e.csscMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSpecialCar$csscMobile!=null">${csSpecialCar$csscMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSpecialCar.csscMobile" id="csscMobile"  value="${(csSpecialCar.csscMobile)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的乘客电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****乘客电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscMobile">${csSpecialCar.csscMobile}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscMobile$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscMobile}
	</s:if>
	
<!-- 	${lz:set("注释","*****************乘车人数字段的输入框代码*****************")} -->
<!-- 	${lz:set("注释","before$csscCount和after$csscCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")} -->
<!-- 	<s:if test="#request.CTRL.v.csscCount==true"> -->
<!-- 	${before$csscCount} -->
<!-- 	<dl class="csscCount " major  ref="csscCount" > -->
<!-- 		<dt>乘车人数:</dt> -->
<!-- 		<s:if test="#request.CTRL.e.csscCount==true"> -->
<!-- 		${lz:set("haveEditable",true)} -->
<!-- 		<dd input="number"> -->
<!-- 		<s:if test="#request.csSpecialCar$csscCount!=null">${csSpecialCar$csscCount}</s:if><s:else> -->
<!-- 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csSpecialCar.csscCount" id="csscCount"  value="${(csSpecialCar.csscCount)}"/> -->
<!-- 	 	 </s:else> -->
	 	 
<!-- 	 	 <b>*</b> -->
<!-- 	 	 <em>请输入专车订单的乘车人数</em> -->
<!-- 		</dd> -->
<!-- 		</s:if> -->
<!-- 		<s:else> -->
<!-- 		${lz:set("注释","****乘车人数字段非编辑模式或只读时的显示****")} -->
<!-- 		<dd> -->
<!-- 		 	<div class="state-input narrow"> -->
<!-- 		 		<textarea class="" style="display:none;" id="csscCount">${csSpecialCar.csscCount}</textarea> -->
<!-- 		 		<span> -->
		 	
<!-- 			 ${lz:html(csSpecialCar.csscCount$)} -->
	 	  
<!-- 	 	 		&nbsp;	 -->
<!-- 	 	 		</span> -->
<!-- 	 	 	</div> -->
<!-- 		</dd> -->
<!-- 		</s:else> -->
<!-- 	</dl> -->
<!-- 	${after$csscCount} -->
<!-- 	</s:if> -->
	
<!-- 	${lz:set("注释","*****************预定车型字段的输入框代码*****************")} -->
<!-- 	${lz:set("注释","before$csscCarModel和after$csscCarModel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")} -->
<!-- 	<s:if test="#request.CTRL.v.csscCarModel==true"> -->
<!-- 	${before$csscCarModel} -->
<!-- 	<dl class="csscCarModel " minor  ref="csscCarModel" > -->
<!-- 		<dt>预定车型:</dt> -->
<!-- 		<s:if test="#request.CTRL.e.csscCarModel==true"> -->
<!-- 		${lz:set("haveEditable",true)} -->
<!-- 		<dd input="query"> -->
<!-- 		<s:if test="#request.csSpecialCar$csscCarModel!=null">${csSpecialCar$csscCarModel}</s:if><s:else> -->
<!-- 		 			<input title="请输入专车车型车型名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/specialmodel_query.do?value={param}" type="text" id="csscCarModel" name="csSpecialCar.csscCarModel" text="${get:CsSpecialModel(csSpecialCar.csscCarModel).cssmName}" value="${(csSpecialCar.csscCarModel)}" /> -->
<!-- 		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csscCarModel').val())==''){return;};window.href('${basePath}${proname}/unit/specialmodel_details.do?key='+$('#csscCarModel').val(),{ctrl:{editable:false}})"></a> -->
<!-- 	 	 </s:else> -->
	 	 
	 	 
<!-- 	 	 <em></em> -->
<!-- 		</dd> -->
<!-- 		</s:if> -->
<!-- 		<s:else> -->
<!-- 		${lz:set("注释","****预定车型字段非编辑模式或只读时的显示****")} -->
<!-- 		<dd> -->
<!-- 		 	<div class="state-input narrow"> -->
<!-- 		 		<textarea class="" style="display:none;" id="csscCarModel">${csSpecialCar.csscCarModel}</textarea> -->
<!-- 		 		<span> -->
		 	
<!-- 			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/specialmodel_details.do?key=${(csSpecialCar.csscCarModel)}',{ctrl:{editable:false,visible:true}})"> -->
<!-- 			 ${lz:html(csSpecialCar.csscCarModel$)}</a> -->
	 	  
<!-- 	 	 		&nbsp;	 -->
<!-- 	 	 		</span> -->
<!-- 	 	 	</div> -->
<!-- 		</dd> -->
<!-- 		</s:else> -->
<!-- 	</dl> -->
<!-- 	${after$csscCarModel} -->
<!-- 	</s:if> -->
	
	${lz:set("注释","*****************车牌号码字段的输入框代码*****************")}
	${lz:set("注释","before$csscCarNumber和after$csscCarNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscCarNumber==true">
	${before$csscCarNumber}
	<dl class="csscCarNumber " minor  ref="csscCarNumber" style="width:98%;">
		<dt>车牌号码:</dt>
		<s:if test="#request.CTRL.e.csscCarNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSpecialCar$csscCarNumber!=null">${csSpecialCar$csscCarNumber}</s:if><s:else>
		 	<textarea class="input wide"  id="csscCarNumber" name="csSpecialCar.csscCarNumber" rows="5">${(csSpecialCar.csscCarNumber)}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的车牌号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscCarNumber$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscCarNumber}
	</s:if>
	
	${lz:set("注释","*****************司机姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csscDriverName和after$csscDriverName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscDriverName==true">
	${before$csscDriverName}
	<dl class="csscDriverName " minor  ref="csscDriverName" >
		<dt>司机姓名:</dt>
		<s:if test="#request.CTRL.e.csscDriverName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSpecialCar$csscDriverName!=null">${csSpecialCar$csscDriverName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSpecialCar.csscDriverName" id="csscDriverName"  value="${(csSpecialCar.csscDriverName)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的司机姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****司机姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscDriverName">${csSpecialCar.csscDriverName}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscDriverName$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscDriverName}
	</s:if>
	
	${lz:set("注释","*****************司机电话字段的输入框代码*****************")}
	${lz:set("注释","before$csscDriverPhone和after$csscDriverPhone变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscDriverPhone==true">
	${before$csscDriverPhone}
	<dl class="csscDriverPhone " minor  ref="csscDriverPhone" >
		<dt>司机电话:</dt>
		<s:if test="#request.CTRL.e.csscDriverPhone==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSpecialCar$csscDriverPhone!=null">${csSpecialCar$csscDriverPhone}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSpecialCar.csscDriverPhone" id="csscDriverPhone"  value="${(csSpecialCar.csscDriverPhone)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的司机电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****司机电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscDriverPhone">${csSpecialCar.csscDriverPhone}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscDriverPhone$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscDriverPhone}
	</s:if>
	
	${lz:set("注释","*****************司机照片字段的输入框代码*****************")}
	${lz:set("注释","before$csscDriverImage和after$csscDriverImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscDriverImage==true">
	${before$csscDriverImage}
	<dl class="csscDriverImage " minor  ref="csscDriverImage" style="width:98%;">
		<dt>司机照片:</dt>
		<s:if test="#request.CTRL.e.csscDriverImage==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csSpecialCar$csscDriverImage!=null">${csSpecialCar$csscDriverImage}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csSpecialCar.csscDriverImage" id="csscDriverImage"  value="${(csSpecialCar.csscDriverImage)}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csscDriverImage').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#csscDriverImage').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#csscDriverImage').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传专车订单的司机照片</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****司机照片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csscDriverImage">${csSpecialCar.csscDriverImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csSpecialCar.csscDriverImage))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${(csSpecialCar.csscDriverImage)}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscDriverImage}
	</s:if>
	
	${lz:set("注释","*****************车辆颜色字段的输入框代码*****************")}
	${lz:set("注释","before$csscDriverColor和after$csscDriverColor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscDriverColor==true">
	${before$csscDriverColor}
	<dl class="csscDriverColor " minor  ref="csscDriverColor" >
		<dt>车辆颜色:</dt>
		<s:if test="#request.CTRL.e.csscDriverColor==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSpecialCar$csscDriverColor!=null">${csSpecialCar$csscDriverColor}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSpecialCar.csscDriverColor" id="csscDriverColor"  value="${(csSpecialCar.csscDriverColor)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的车辆颜色</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆颜色字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscDriverColor">${csSpecialCar.csscDriverColor}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscDriverColor$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscDriverColor}
	</s:if>
	
	${lz:set("注释","*****************车型信息字段的输入框代码*****************")}
	${lz:set("注释","before$csscDriverCarInfo和after$csscDriverCarInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscDriverCarInfo==true">
	${before$csscDriverCarInfo}
	<dl class="csscDriverCarInfo " minor  ref="csscDriverCarInfo" >
		<dt>车型信息:</dt>
		<s:if test="#request.CTRL.e.csscDriverCarInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSpecialCar$csscDriverCarInfo!=null">${csSpecialCar$csscDriverCarInfo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSpecialCar.csscDriverCarInfo" id="csscDriverCarInfo"  value="${(csSpecialCar.csscDriverCarInfo)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的车型信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车型信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscDriverCarInfo">${csSpecialCar.csscDriverCarInfo}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscDriverCarInfo$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscDriverCarInfo}
	</s:if>
	
	${lz:set("注释","*****************客户留言字段的输入框代码*****************")}
	${lz:set("注释","before$csscMsg和after$csscMsg变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscMsg==true">
	${before$csscMsg}
	<dl class="csscMsg " minor  ref="csscMsg" style="width:98%;">
		<dt>客户留言:</dt>
		<s:if test="#request.CTRL.e.csscMsg==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSpecialCar$csscMsg!=null">${csSpecialCar$csscMsg}</s:if><s:else>
		 	<textarea class="input wide"  id="csscMsg" name="csSpecialCar.csscMsg" rows="5">${(csSpecialCar.csscMsg)}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的客户留言</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****客户留言字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscMsg$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscMsg}
	</s:if>
	
	${lz:set("注释","*****************起点位置字段的输入框代码*****************")}
	${lz:set("注释","before$csscStartPoint和after$csscStartPoint变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscStartPoint==true">
	${before$csscStartPoint}
	<dl class="csscStartPoint " major  ref="csscStartPoint" >
		<dt>起点位置:</dt>
		<s:if test="#request.CTRL.e.csscStartPoint==true">
		${lz:set("haveEditable",true)}
		<dd input="position">
		<s:if test="#request.csSpecialCar$csscStartPoint!=null">${csSpecialCar$csscStartPoint}</s:if><s:else>
		 	<input type="text" class="input narrow position" maxlength="64" name="csSpecialCar.csscStartPoint" id="csscStartPoint"  value="${(csSpecialCar.csscStartPoint)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****起点位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscStartPoint">${csSpecialCar.csscStartPoint}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscStartPoint$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscStartPoint}
	</s:if>
	
	${lz:set("注释","*****************起点地址字段的输入框代码*****************")}
	${lz:set("注释","before$csscStartAddr和after$csscStartAddr变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscStartAddr==true">
	${before$csscStartAddr}
	<dl class="csscStartAddr " minor  ref="csscStartAddr" style="width:98%;">
		<dt>起点地址:</dt>
		<s:if test="#request.CTRL.e.csscStartAddr==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSpecialCar$csscStartAddr!=null">${csSpecialCar$csscStartAddr}</s:if><s:else>
		 	<textarea class="input wide"  id="csscStartAddr" name="csSpecialCar.csscStartAddr" rows="5">${(csSpecialCar.csscStartAddr)}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的起点地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****起点地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscStartAddr$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscStartAddr}
	</s:if>
	
	${lz:set("注释","*****************开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csscStartTime和after$csscStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscStartTime==true">
	${before$csscStartTime}
	<dl class="csscStartTime " major  ref="csscStartTime" >
		<dt>开始时间:</dt>
		<s:if test="#request.CTRL.e.csscStartTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSpecialCar$csscStartTime!=null">${csSpecialCar$csscStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSpecialCar.csscStartTime" id="csscStartTime"  value="<s:date name="csSpecialCar.csscStartTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择专车订单的开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscStartTime">${csSpecialCar.csscStartTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscStartTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscStartTime}
	</s:if>
	
	${lz:set("注释","*****************终点位置字段的输入框代码*****************")}
	${lz:set("注释","before$csscFinishPoint和after$csscFinishPoint变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscFinishPoint==true">
	${before$csscFinishPoint}
	<dl class="csscFinishPoint " major  ref="csscFinishPoint" >
		<dt>终点位置:</dt>
		<s:if test="#request.CTRL.e.csscFinishPoint==true">
		${lz:set("haveEditable",true)}
		<dd input="position">
		<s:if test="#request.csSpecialCar$csscFinishPoint!=null">${csSpecialCar$csscFinishPoint}</s:if><s:else>
		 	<input type="text" class="input narrow position" maxlength="64" name="csSpecialCar.csscFinishPoint" id="csscFinishPoint"  value="${(csSpecialCar.csscFinishPoint)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****终点位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscFinishPoint">${csSpecialCar.csscFinishPoint}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscFinishPoint$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscFinishPoint}
	</s:if>
	
	${lz:set("注释","*****************终点地址字段的输入框代码*****************")}
	${lz:set("注释","before$csscFinishAddr和after$csscFinishAddr变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscFinishAddr==true">
	${before$csscFinishAddr}
	<dl class="csscFinishAddr " minor  ref="csscFinishAddr" style="width:98%;">
		<dt>终点地址:</dt>
		<s:if test="#request.CTRL.e.csscFinishAddr==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSpecialCar$csscFinishAddr!=null">${csSpecialCar$csscFinishAddr}</s:if><s:else>
		 	<textarea class="input wide"  id="csscFinishAddr" name="csSpecialCar.csscFinishAddr" rows="5">${(csSpecialCar.csscFinishAddr)}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的终点地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****终点地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscFinishAddr$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscFinishAddr}
	</s:if>
	
	${lz:set("注释","*****************结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csscFinishTime和after$csscFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscFinishTime==true">
	${before$csscFinishTime}
	<dl class="csscFinishTime " minor  ref="csscFinishTime" >
		<dt>结束时间:</dt>
		<s:if test="#request.CTRL.e.csscFinishTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSpecialCar$csscFinishTime!=null">${csSpecialCar$csscFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSpecialCar.csscFinishTime" id="csscFinishTime"  value="<s:date name="csSpecialCar.csscFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择专车订单的结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscFinishTime">${csSpecialCar.csscFinishTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscFinishTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscFinishTime}
	</s:if>
	
	${lz:set("注释","*****************订单时长字段的输入框代码*****************")}
	${lz:set("注释","before$csscDuration和after$csscDuration变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscDuration==true">
	${before$csscDuration}
	<dl class="csscDuration " minor  ref="csscDuration" >
		<dt>订单时长:</dt>
		<s:if test="#request.CTRL.e.csscDuration==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csSpecialCar$csscDuration!=null">${csSpecialCar$csscDuration}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csSpecialCar.csscDuration" id="csscDuration"  value="${(csSpecialCar.csscDuration)}"/>
	 	 </s:else>
	 	 分钟
	 	 
	 	 <em>请输入专车订单的订单时长</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单时长字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscDuration">${csSpecialCar.csscDuration}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscDuration$)}
	 	 分钟 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscDuration}
	</s:if>
	
	${lz:set("注释","*****************预估里程字段的输入框代码*****************")}
	${lz:set("注释","before$csscKilo和after$csscKilo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscPreditMileage==true">
	${before$csscPreditMileage}
	<dl class="csscPreditMileage " minor  ref="csscPreditMileage" >
		<dt>预估里程:</dt>
		<s:if test="#request.CTRL.e.csscPreditMileage==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csSpecialCar$csscPreditMileage!=null">${csSpecialCar$csscPreditMileage}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csSpecialCar.csscPreditMileage" id="csscPreditMileage"  value="${(csSpecialCar.csscPreditMileage)}"/>
	 	 </s:else>
	 	 <em>请输入专车订单的预估里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****公里数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscPreditMileage">${csSpecialCar.csscPreditMileage}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscPreditMileage$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscPreditMileage}
	</s:if>
	
	${lz:set("注释","*****************预估费用字段的输入框代码*****************")}
	${lz:set("注释","before$csscEstimateFee和after$csscEstimateFee变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscEstimateFee==true">
	${before$csscEstimateFee}
	<dl class="csscEstimateFee " minor  ref="csscEstimateFee" >
		<dt>预估费用:</dt>
		<s:if test="#request.CTRL.e.csscEstimateFee==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csSpecialCar$csscEstimateFee!=null">${csSpecialCar$csscEstimateFee}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csSpecialCar.csscEstimateFee" id="csscEstimateFee"  value="${(csSpecialCar.csscEstimateFee)}"/>
	 	 </s:else>
	 	 <em>请输入专车订单的预估费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****公里数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscEstimateFee">${csSpecialCar.csscEstimateFee}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscEstimateFee$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscEstimateFee}
	</s:if>
	
	${lz:set("注释","*****************实际时长字段的输入框代码*****************")}
	${lz:set("注释","before$csscRealDur和after$csscRealDur变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscRealDur==true">
	${before$csscRealDur}
	<dl class="csscRealDur " minor  ref="csscRealDur" >
		<dt>实际时长:</dt>
		<s:if test="#request.CTRL.e.csscRealDur==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csSpecialCar$csscRealDur!=null">${csSpecialCar$csscRealDur}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csSpecialCar.csscRealDur" id="csscRealDur"  value="${(csSpecialCar.csscRealDur)}"/>
	 	 </s:else>
	 	 分钟
	 	 
	 	 <em>请输入专车订单的实际时长</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际时长字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscRealDur">${csSpecialCar.csscRealDur}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscRealDur$)}
	 	 分钟 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscRealDur}
	</s:if>
	
	${lz:set("注释","*****************公里数字段的输入框代码*****************")}
	${lz:set("注释","before$csscKilo和after$csscKilo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscKilo==true">
	${before$csscKilo}
	<dl class="csscKilo " minor  ref="csscKilo" >
		<dt>公&nbsp;&nbsp;里&nbsp;&nbsp;数:</dt>
		<s:if test="#request.CTRL.e.csscKilo==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csSpecialCar$csscKilo!=null">${csSpecialCar$csscKilo}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csSpecialCar.csscKilo" id="csscKilo"  value="${(csSpecialCar.csscKilo)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的公里数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****公里数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscKilo">${csSpecialCar.csscKilo}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscKilo$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscKilo}
	</s:if>
	
	${lz:set("注释","*****************用车评分字段的输入框代码*****************")}
	${lz:set("注释","before$csscScope和after$csscScope变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscScope==true">
	${before$csscScope}
	<dl class="csscScope " major  ref="csscScope" >
		<dt>用车评分:</dt>
		<s:if test="#request.CTRL.e.csscScope==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSpecialCar$csscScope!=null">${csSpecialCar$csscScope}</s:if><s:else>
		 	<select class="narrow" id="csscScope" name="csSpecialCar.csscScope">
		 		<option value="">请选择</option>
				<option value="0" ${csSpecialCar.csscScope==0?"selected":""}>未评分</option>
				<option value="1" ${csSpecialCar.csscScope==1?"selected":""}>一星</option>
				<option value="2" ${csSpecialCar.csscScope==2?"selected":""}>二星</option>
				<option value="3" ${csSpecialCar.csscScope==3?"selected":""}>三星</option>
				<option value="4" ${csSpecialCar.csscScope==4?"selected":""}>四星</option>
				<option value="5" ${csSpecialCar.csscScope==5?"selected":""}>五星</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择专车订单的用车评分</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****用车评分字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscScope">${csSpecialCar.csscScope}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscScope$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscScope}
	</s:if>
	
	${lz:set("注释","*****************乘车习惯字段的输入框代码*****************")}
	${lz:set("注释","before$csscDemand和after$csscDemand变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscDemand==true">
	${before$csscDemand}
	<dl class="csscDemand " minor  ref="csscDemand" style="width:98%;">
		<dt>乘车习惯:</dt>
		<s:if test="#request.CTRL.e.csscDemand==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSpecialCar$csscDemand!=null">${csSpecialCar$csscDemand}</s:if><s:else>
		 	<textarea class="input wide"  id="csscDemand" name="csSpecialCar.csscDemand" rows="5">${(csSpecialCar.csscDemand)}</textarea>
	 	 </s:else>
	 	 
	 	 <em>请输入专车订单的乘车习惯</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****用车评价字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscDemand$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscAssess}
	</s:if>
	
	${lz:set("注释","*****************用车评价字段的输入框代码*****************")}
	${lz:set("注释","before$csscAssess和after$csscAssess变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscAssess==true">
	${before$csscAssess}
	<dl class="csscAssess " minor  ref="csscAssess" style="width:98%;">
		<dt>用车评价:</dt>
		<s:if test="#request.CTRL.e.csscAssess==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSpecialCar$csscAssess!=null">${csSpecialCar$csscAssess}</s:if><s:else>
		 	<textarea class="input wide"  id="csscAssess" name="csSpecialCar.csscAssess" rows="5">${(csSpecialCar.csscAssess)}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的用车评价</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****用车评价字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscAssess$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscAssess}
	</s:if>
	
	${lz:set("注释","*****************司机到达时间字段的输入框代码*****************")}
	${lz:set("注释","before$csscDriverArrivaltime和after$csscDriverArrivaltime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscDriverArrivaltime==true">
	${before$csscDriverArrivaltime}
	<dl class="csscDriverArrivaltime " minor  ref="csscDriverArrivaltime" >
		<dt>司机到达时间:</dt>
		<s:if test="#request.CTRL.e.csscDriverArrivaltime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSpecialCar$csscDriverArrivaltime!=null">${csSpecialCar$csscDriverArrivaltime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSpecialCar.csscDriverArrivaltime" id="csscDriverArrivaltime"  value="<s:date name="csSpecialCar.csscDriverArrivaltime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <em>请选择专车订单的司机到达时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****司机到达时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscDriverArrivaltime">${csSpecialCar.csscDriverArrivaltime}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscDriverArrivaltime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscFinishTime}
	</s:if>
	
	${lz:set("注释","*****************是否拼车字段的输入框代码*****************")}
	${lz:set("注释","before$csscIsCarpool和after$csscIsCarpool变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscIsCarpool==true">
	${before$csscIsCarpool}
	<dl class="csscIsCarpool" major  ref="csscIsCarpool" >
		<dt>是否拼车:</dt>
		<s:if test="#request.CTRL.e.csscIsCarpool==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSpecialCar$csscIsCarpool!=null">${csSpecialCar$csscIsCarpool}</s:if><s:else>
		 	<select class="narrow" id="csscIsCarpool" name="csSpecialCar.csscIsCarpool">
		 		<option value="">请选择</option>
				<option value="0" ${csSpecialCar.csscIsCarpool==0?"selected":""}>不拼车</option>
				<option value="1" ${csSpecialCar.csscIsCarpool==1?"selected":""}>拼车</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择专车订单的是否拼车</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscIsCarpool">${csSpecialCar.csscIsCarpool}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscIsCarpool$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscIsCarpool}
	</s:if>
	
<!-- 	${lz:set("注释","*****************三方名称字段的输入框代码*****************")} -->
<!-- 	${lz:set("注释","before$csscThird和after$csscThird变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")} -->
<!-- 	<s:if test="#request.CTRL.v.csscThird==true"> -->
<!-- 	${before$csscThird} -->
<!-- 	<dl class="csscThird " major  ref="csscThird" > -->
<!-- 		<dt>三方名称:</dt> -->
<!-- 		<s:if test="#request.CTRL.e.csscThird==true"> -->
<!-- 		${lz:set("haveEditable",true)} -->
<!-- 		<dd input="select"> -->
<!-- 		<s:if test="#request.csSpecialCar$csscThird!=null">${csSpecialCar$csscThird}</s:if><s:else> -->
<!-- 		 	<select class="narrow" id="csscThird" name="csSpecialCar.csscThird"> -->
<!-- 		 		<option value="">请选择</option> -->
<!-- 				<option value="1" ${csSpecialCar.csscThird==1?"selected":""}>滴滴专车</option> -->
<!-- 				<option value="2" ${csSpecialCar.csscThird==2?"selected":""}>易道专车</option> -->
<!-- 		 	</select> -->
<!-- 	 	 </s:else> -->
	 	 
	 	 
<!-- 	 	 <em>请选择专车订单的三方名称</em> -->
<!-- 		</dd> -->
<!-- 		</s:if> -->
<!-- 		<s:else> -->
<!-- 		${lz:set("注释","****三方名称字段非编辑模式或只读时的显示****")} -->
<!-- 		<dd> -->
<!-- 		 	<div class="state-input narrow"> -->
<!-- 		 		<textarea class="" style="display:none;" id="csscThird">${csSpecialCar.csscThird}</textarea> -->
<!-- 		 		<span> -->
		 	
<!-- 			 ${lz:html(csSpecialCar.csscThird$)} -->
	 	  
<!-- 	 	 		&nbsp;	 -->
<!-- 	 	 		</span> -->
<!-- 	 	 	</div> -->
<!-- 		</dd> -->
<!-- 		</s:else> -->
<!-- 	</dl> -->
<!-- 	${after$csscThird} -->
<!-- 	</s:if> -->
	
<!-- 	${lz:set("注释","*****************三方订单号字段的输入框代码*****************")} -->
<!-- 	${lz:set("注释","before$csscThirdNo和after$csscThirdNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")} -->
<!-- 	<s:if test="#request.CTRL.v.csscThirdNo==true"> -->
<!-- 	${before$csscThirdNo} -->
<!-- 	<dl class="csscThirdNo " major  ref="csscThirdNo" style="width:98%;"> -->
<!-- 		<dt>三方订单号:</dt> -->
<!-- 		<s:if test="#request.CTRL.e.csscThirdNo==true"> -->
<!-- 		${lz:set("haveEditable",true)} -->
<!-- 		<dd input="text"> -->
<!-- 		<s:if test="#request.csSpecialCar$csscThirdNo!=null">${csSpecialCar$csscThirdNo}</s:if><s:else> -->
<!-- 		 	<input type="text" class="input wide"  maxlength="64" name="csSpecialCar.csscThirdNo" id="csscThirdNo"  value="${(csSpecialCar.csscThirdNo)}"/> -->
<!-- 	 	 </s:else> -->
	 	 
	 	 
<!-- 	 	 <em>请输入专车订单的三方订单号</em> -->
<!-- 		</dd> -->
<!-- 		</s:if> -->
<!-- 		<s:else> -->
<!-- 		${lz:set("注释","****三方订单号字段非编辑模式或只读时的显示****")} -->
<!-- 		<dd> -->
<!-- 		 	<div class="state-input wide"> -->
<!-- 		 		<textarea class="" style="display:none;" id="csscThirdNo">${csSpecialCar.csscThirdNo}</textarea> -->
<!-- 		 		<span> -->
		 	
<!-- 			 ${lz:html(csSpecialCar.csscThirdNo$)} -->
	 	  
<!-- 	 	 		&nbsp;	 -->
<!-- 	 	 		</span> -->
<!-- 	 	 	</div> -->
<!-- 		</dd> -->
<!-- 		</s:else> -->
<!-- 	</dl> -->
<!-- 	${after$csscThirdNo} -->
<!-- 	</s:if> -->
	
<!-- 	${lz:set("注释","*****************三方费用字段的输入框代码*****************")} -->
<!-- 	${lz:set("注释","before$csscThirdFee和after$csscThirdFee变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")} -->
<!-- 	<s:if test="#request.CTRL.v.csscThirdFee==true"> -->
<!-- 	${before$csscThirdFee} -->
<!-- 	<dl class="csscThirdFee " minor  ref="csscThirdFee" > -->
<!-- 		<dt>三方费用:</dt> -->
<!-- 		<s:if test="#request.CTRL.e.csscThirdFee==true"> -->
<!-- 		${lz:set("haveEditable",true)} -->
<!-- 		<dd input="menoy"> -->
<!-- 		<s:if test="#request.csSpecialCar$csscThirdFee!=null">${csSpecialCar$csscThirdFee}</s:if><s:else> -->
<!-- 		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csSpecialCar.csscThirdFee" id="csscThirdFee"  value="${(csSpecialCar.csscThirdFee)}"/> -->
<!-- 	 	 </s:else> -->
	 	 
	 	 
<!-- 	 	 <em>请输入专车订单的三方费用</em> -->
<!-- 		</dd> -->
<!-- 		</s:if> -->
<!-- 		<s:else> -->
<!-- 		${lz:set("注释","****三方费用字段非编辑模式或只读时的显示****")} -->
<!-- 		<dd> -->
<!-- 		 	<div class="state-input narrow"> -->
<!-- 		 		<textarea class="" style="display:none;" id="csscThirdFee">${csSpecialCar.csscThirdFee}</textarea> -->
<!-- 		 		<span> -->
		 	
<!-- 			 ${lz:html(csSpecialCar.csscThirdFee$)} -->
	 	  
<!-- 	 	 		&nbsp;	 -->
<!-- 	 	 		</span> -->
<!-- 	 	 	</div> -->
<!-- 		</dd> -->
<!-- 		</s:else> -->
<!-- 	</dl> -->
<!-- 	${after$csscThirdFee} -->
<!-- 	</s:if> -->
	
<!-- 	${lz:set("注释","*****************三方费用信息字段的输入框代码*****************")} -->
<!-- 	${lz:set("注释","before$csscThirdFeeData和after$csscThirdFeeData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")} -->
<!-- 	<s:if test="#request.CTRL.v.csscThirdFeeData==true"> -->
<!-- 	${before$csscThirdFeeData} -->
<!-- 	<dl class="csscThirdFeeData " minor  ref="csscThirdFeeData" style="width:98%;"> -->
<!-- 		<dt>三方费用信息:</dt> -->
<!-- 		<s:if test="#request.CTRL.e.csscThirdFeeData==true"> -->
<!-- 		${lz:set("haveEditable",true)} -->
<!-- 		<dd input="html"> -->
<!-- 		<s:if test="#request.csSpecialCar$csscThirdFeeData!=null">${csSpecialCar$csscThirdFeeData}</s:if><s:else> -->
<!-- 		    <textarea class="input wide"  id="csscThirdFeeData" name="csSpecialCar.csscThirdFeeData" style="height:300px;visibility:hidden;">${csSpecialCar.csscThirdFeeData}</textarea> -->
<!-- 	 	 </s:else> -->
	 	 
	 	 
<!-- 	 	 <em>请输入专车订单的三方费用信息</em> -->
<!-- 		</dd> -->
<!-- 		</s:if> -->
<!-- 		<s:else> -->
<!-- 		${lz:set("注释","****三方费用信息字段非编辑模式或只读时的显示****")} -->
<!-- 		<dd> -->
<!-- 		 	<div class="state-input wide"> -->
<!-- 		 		<span> -->
		 	
<!-- 			 ${lz:html(csSpecialCar.csscThirdFeeData$)} -->
	 	  
<!-- 	 	 		&nbsp;	 -->
<!-- 	 	 		</span> -->
<!-- 	 	 	</div> -->
<!-- 		</dd> -->
<!-- 		</s:else> -->
<!-- 	</dl> -->
<!-- 	${after$csscThirdFeeData} -->
<!-- 	</s:if> -->
	
<!-- 	${lz:set("注释","*****************三方其它费用字段的输入框代码*****************")} -->
<!-- 	${lz:set("注释","before$csscThirdOtherFee和after$csscThirdOtherFee变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")} -->
<!-- 	<s:if test="#request.CTRL.v.csscThirdOtherFee==true"> -->
<!-- 	${before$csscThirdOtherFee} -->
<!-- 	<dl class="csscThirdOtherFee " major  ref="csscThirdOtherFee" > -->
<!-- 		<dt>三方其它费用:</dt> -->
<!-- 		<s:if test="#request.CTRL.e.csscThirdOtherFee==true"> -->
<!-- 		${lz:set("haveEditable",true)} -->
<!-- 		<dd input="menoy"> -->
<!-- 		<s:if test="#request.csSpecialCar$csscThirdOtherFee!=null">${csSpecialCar$csscThirdOtherFee}</s:if><s:else> -->
<!-- 		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csSpecialCar.csscThirdOtherFee" id="csscThirdOtherFee"  value="${(csSpecialCar.csscThirdOtherFee)}"/> -->
<!-- 	 	 </s:else> -->
	 	 
	 	 
<!-- 	 	 <em>请输入专车订单的三方其它费用</em> -->
<!-- 		</dd> -->
<!-- 		</s:if> -->
<!-- 		<s:else> -->
<!-- 		${lz:set("注释","****三方其它费用字段非编辑模式或只读时的显示****")} -->
<!-- 		<dd> -->
<!-- 		 	<div class="state-input narrow"> -->
<!-- 		 		<textarea class="" style="display:none;" id="csscThirdOtherFee">${csSpecialCar.csscThirdOtherFee}</textarea> -->
<!-- 		 		<span> -->
		 	
<!-- 			 ${lz:html(csSpecialCar.csscThirdOtherFee$)} -->
	 	  
<!-- 	 	 		&nbsp;	 -->
<!-- 	 	 		</span> -->
<!-- 	 	 	</div> -->
<!-- 		</dd> -->
<!-- 		</s:else> -->
<!-- 	</dl> -->
<!-- 	${after$csscThirdOtherFee} -->
<!-- 	</s:if> -->
	
<!-- 	${lz:set("注释","*****************三方其它费用描述字段的输入框代码*****************")} -->
<!-- 	${lz:set("注释","before$csscThirdOtherFeeDes和after$csscThirdOtherFeeDes变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")} -->
<!-- 	<s:if test="#request.CTRL.v.csscThirdOtherFeeDes==true"> -->
<!-- 	${before$csscThirdOtherFeeDes} -->
<!-- 	<dl class="csscThirdOtherFeeDes " major  ref="csscThirdOtherFeeDes" style="width:98%;"> -->
<!-- 		<dt>三方其它费用描述:</dt> -->
<!-- 		<s:if test="#request.CTRL.e.csscThirdOtherFeeDes==true"> -->
<!-- 		${lz:set("haveEditable",true)} -->
<!-- 		<dd input="textarea"> -->
<!-- 		<s:if test="#request.csSpecialCar$csscThirdOtherFeeDes!=null">${csSpecialCar$csscThirdOtherFeeDes}</s:if><s:else> -->
<!-- 		 	<textarea class="input wide"  id="csscThirdOtherFeeDes" name="csSpecialCar.csscThirdOtherFeeDes" rows="5">${(csSpecialCar.csscThirdOtherFeeDes)}</textarea> -->
<!-- 	 	 </s:else> -->
	 	 
	 	 
<!-- 	 	 <em>请输入专车订单的三方其它费用描述</em> -->
<!-- 		</dd> -->
<!-- 		</s:if> -->
<!-- 		<s:else> -->
<!-- 		${lz:set("注释","****三方其它费用描述字段非编辑模式或只读时的显示****")} -->
<!-- 		<dd> -->
<!-- 		 	<div class="state-input wide"> -->
<!-- 		 		<span> -->
		 	
<!-- 			 ${lz:html(csSpecialCar.csscThirdOtherFeeDes$)} -->
	 	  
<!-- 	 	 		&nbsp;	 -->
<!-- 	 	 		</span> -->
<!-- 	 	 	</div> -->
<!-- 		</dd> -->
<!-- 		</s:else> -->
<!-- 	</dl> -->
<!-- 	${after$csscThirdOtherFeeDes} -->
<!-- 	</s:if> -->
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csscUpdateTime和after$csscUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscUpdateTime==true">
	${before$csscUpdateTime}
	<dl class="csscUpdateTime " major  ref="csscUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csscUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSpecialCar$csscUpdateTime!=null">${csSpecialCar$csscUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSpecialCar.csscUpdateTime" id="csscUpdateTime"  value="<s:date name="csSpecialCar.csscUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择专车订单的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscUpdateTime">${csSpecialCar.csscUpdateTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscUpdateTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csscAddTime和after$csscAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscAddTime==true">
	${before$csscAddTime}
	<dl class="csscAddTime " major  ref="csscAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csscAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSpecialCar$csscAddTime!=null">${csSpecialCar$csscAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSpecialCar.csscAddTime" id="csscAddTime"  value="<s:date name="csSpecialCar.csscAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择专车订单的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscAddTime">${csSpecialCar.csscAddTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscAddTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscAddTime}
	</s:if>
	
	${lz:set("注释","*****************响应状态字段的输入框代码*****************")}
	${lz:set("注释","before$csscResponse和after$csscResponse变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscResponse==true">
	${before$csscResponse}
	<dl class="csscResponse " major  ref="csscResponse" style="width:98%;">
		<dt>响应状态:</dt>
		<s:if test="#request.CTRL.e.csscResponse==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSpecialCar$csscResponse!=null">${csSpecialCar$csscResponse}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csSpecialCar.csscResponse" id="csscResponse"  value="${(csSpecialCar.csscResponse)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车订单的响应状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****响应状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csscResponse">${csSpecialCar.csscResponse}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscResponse$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscResponse}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csscStatus和after$csscStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscStatus==true">
	${before$csscStatus}
	<dl class="csscStatus " major  ref="csscStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csscStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSpecialCar$csscStatus!=null">${csSpecialCar$csscStatus}</s:if><s:else>
		 	<select class="narrow" id="csscStatus" name="csSpecialCar.csscStatus">
		 		<option value="">请选择</option>
				<option value="2" ${csSpecialCar.csscStatus==2?"selected":""}>待接单</option>
				<option value="3" ${csSpecialCar.csscStatus==3?"selected":""}>已接单</option>
				<option value="4" ${csSpecialCar.csscStatus==4?"selected":""}>已完成</option>
				<option value="5" ${csSpecialCar.csscStatus==5?"selected":""}>已取消</option>
				<option value="6" ${csSpecialCar.csscStatus==6?"selected":""}>已作废</option>
				<option value="7" ${csSpecialCar.csscStatus==5?"selected":""}>待处理</option>
				<option value="8" ${csSpecialCar.csscStatus==6?"selected":""}>司机到达</option>
				<option value="9" ${csSpecialCar.csscStatus==5?"selected":""}>行程中</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择专车订单的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscStatus">${csSpecialCar.csscStatus}</textarea>
		 		<span>
		 	
			 ${lz:html(csSpecialCar.csscStatus$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csSpecialCar.csscId}" name="csSpecialCar.csscId" id="csscId" />
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