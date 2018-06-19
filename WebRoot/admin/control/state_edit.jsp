<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/control/state.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/control/state.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csState.cssId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cssHost",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssHost",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cssHost",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssHost",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车辆当前状态编辑":CTRL.title}</title>
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
	$("#stateForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cssId==true">
		${lz:set("haveEditable",true)}
		,"csState.cssId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssHost==true">
		${lz:set("haveEditable",true)}
		,"csState.cssHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssNumber==true">
		${lz:set("haveEditable",true)}
		,"csState.cssNumber":function(el){
			if(jQuery.trim(el.value)=="")
				return "车牌号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车牌号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssCar==true">
		${lz:set("haveEditable",true)}
		,"csState.cssCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssAddTime==true">
		${lz:set("haveEditable",true)}
		,"csState.cssAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssCurrentTime==true">
		${lz:set("haveEditable",true)}
		,"csState.cssCurrentTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssRented==true">
		${lz:set("haveEditable",true)}
		,"csState.cssRented":function(el){
			if(el.value.length>8 && el.value.indexOf("[*]")==-1)
				return "租赁状态最大长度为8个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssWarn==true">
		${lz:set("haveEditable",true)}
		,"csState.cssWarn":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "有报警最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssRfid==true">
		${lz:set("haveEditable",true)}
		,"csState.cssRfid":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "RFID卡号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssRfidDte==true">
		${lz:set("haveEditable",true)}
		,"csState.cssRfidDte":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "用户RFID最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssObdMile==true">
		${lz:set("haveEditable",true)}
		,"csState.cssObdMile":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "OBD里程最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssEngineT==true">
		${lz:set("haveEditable",true)}
		,"csState.cssEngineT":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "发动机温度最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssMileage==true">
		${lz:set("haveEditable",true)}
		,"csState.cssMileage":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "总里程最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssSpeed==true">
		${lz:set("haveEditable",true)}
		,"csState.cssSpeed":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车速最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssMotor==true">
		${lz:set("haveEditable",true)}
		,"csState.cssMotor":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "转速最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssOil==true">
		${lz:set("haveEditable",true)}
		,"csState.cssOil":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "燃油量最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssPower==true">
		${lz:set("haveEditable",true)}
		,"csState.cssPower":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "蓄电量最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssEvBattery==true">
		${lz:set("haveEditable",true)}
		,"csState.cssEvBattery":function(el){
			if(el.value.length>16 && el.value.indexOf("[*]")==-1)
				return "动力电池电量最大长度为16个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssCharging==true">
		${lz:set("haveEditable",true)}
		,"csState.cssCharging":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "在充电最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssFuelMileage==true">
		${lz:set("haveEditable",true)}
		,"csState.cssFuelMileage":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "油里程最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssElectricMileage==true">
		${lz:set("haveEditable",true)}
		,"csState.cssElectricMileage":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "电量程最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssEndurance==true">
		${lz:set("haveEditable",true)}
		,"csState.cssEndurance":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "续航里程最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssTemperature==true">
		${lz:set("haveEditable",true)}
		,"csState.cssTemperature":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "温度最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssCsq==true">
		${lz:set("haveEditable",true)}
		,"csState.cssCsq":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "信号强度最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssPowerConsumption==true">
		${lz:set("haveEditable",true)}
		,"csState.cssPowerConsumption":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "功耗最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssLongitude==true">
		${lz:set("haveEditable",true)}
		,"csState.cssLongitude":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "经度最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssLatitude==true">
		${lz:set("haveEditable",true)}
		,"csState.cssLatitude":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "纬度最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssOrder==true">
		${lz:set("haveEditable",true)}
		,"csState.cssOrder":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^([0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的编号";				
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
	<form class="form editform" ref="CsState" id="stateForm" name="stateForm" action="${empty CTRL.action?"state_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车辆当前状态相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csStateToken}" name="csStateToken" id="csStateToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csState.cssId))}		
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
		<s:if test="#request.csState$cssId!=null">${csState$cssId}</s:if><s:else>
			<input type="hidden" value="${csState.cssId}" name="csState.cssId" id="cssId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssId">${csState.cssId}</textarea>
		 		<span>
		 	
			 ${csState.cssId$}
	 	  
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
		<s:if test="#request.csState$cssHost!=null">${csState$cssHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cssHost" name="csState.cssHost">
		 				<option selected value="${csState.cssHost}">
		 					${get:SrvHost(csState.cssHost).shName}
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
		 		<textarea class="" style="display:none;" id="cssHost">${csState.cssHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csState.cssHost}',{ctrl:{editable:false,visible:true}})">
			 ${csState.cssHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssHost}
	</s:if>
	
	${lz:set("注释","*****************车牌号字段的输入框代码*****************")}
	${lz:set("注释","before$cssNumber和after$cssNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssNumber==true">
	${before$cssNumber}
	<dl class="cssNumber " major  ref="cssNumber" >
		<dt>车&nbsp;&nbsp;牌&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.cssNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssNumber!=null">${csState$cssNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssNumber" id="cssNumber"  value="${csState.cssNumber}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车辆当前状态的车牌号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssNumber">${csState.cssNumber}</textarea>
		 		<span>
		 	
			 ${csState.cssNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssNumber}
	</s:if>
	
	${lz:set("注释","*****************关联车辆字段的输入框代码*****************")}
	${lz:set("注释","before$cssCar和after$cssCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssCar==true">
	${before$cssCar}
	<dl class="cssCar " minor  ref="cssCar" >
		<dt>关联车辆:</dt>
		<s:if test="#request.CTRL.e.cssCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csState$cssCar!=null">${csState$cssCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cssHost}" type="text" id="cssCar" name="csState.cssCar" text="${get:CsCar(csState.cssCar).cscNumber}" value="${csState.cssCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#cssCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssCar">${csState.cssCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csState.cssCar}',{ctrl:{editable:false,visible:true}})">
			 ${csState.cssCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssCar}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssAddTime和after$cssAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssAddTime==true">
	${before$cssAddTime}
	<dl class="cssAddTime " major  ref="cssAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cssAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csState$cssAddTime!=null">${csState$cssAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csState.cssAddTime" id="cssAddTime"  value="<s:date name="csState.cssAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆当前状态的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssAddTime">${csState.cssAddTime}</textarea>
		 		<span>
		 	
			 ${csState.cssAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssAddTime}
	</s:if>
	
	${lz:set("注释","*****************下位机时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssCurrentTime和after$cssCurrentTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssCurrentTime==true">
	${before$cssCurrentTime}
	<dl class="cssCurrentTime " minor  ref="cssCurrentTime" >
		<dt>下位机时间:</dt>
		<s:if test="#request.CTRL.e.cssCurrentTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csState$cssCurrentTime!=null">${csState$cssCurrentTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csState.cssCurrentTime" id="cssCurrentTime"  value="<s:date name="csState.cssCurrentTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆当前状态的下位机时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****下位机时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssCurrentTime">${csState.cssCurrentTime}</textarea>
		 		<span>
		 	
			 ${csState.cssCurrentTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssCurrentTime}
	</s:if>
	
	${lz:set("注释","*****************租赁状态字段的输入框代码*****************")}
	${lz:set("注释","before$cssRented和after$cssRented变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssRented==true">
	${before$cssRented}
	<dl class="cssRented " major  ref="cssRented" >
		<dt>租赁状态:</dt>
		<s:if test="#request.CTRL.e.cssRented==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssRented!=null">${csState$cssRented}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="8" name="csState.cssRented" id="cssRented"  value="${csState.cssRented}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的租赁状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****租赁状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssRented">${csState.cssRented}</textarea>
		 		<span>
		 	
			 ${csState.cssRented$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssRented}
	</s:if>
	
	${lz:set("注释","*****************有报警字段的输入框代码*****************")}
	${lz:set("注释","before$cssWarn和after$cssWarn变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssWarn==true">
	${before$cssWarn}
	<dl class="cssWarn " minor  ref="cssWarn" >
		<dt>有&nbsp;&nbsp;报&nbsp;&nbsp;警:</dt>
		<s:if test="#request.CTRL.e.cssWarn==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssWarn!=null">${csState$cssWarn}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssWarn" id="cssWarn"  value="${csState.cssWarn}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的有报警</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****有报警字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssWarn">${csState.cssWarn}</textarea>
		 		<span>
		 	
			 ${csState.cssWarn$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssWarn}
	</s:if>
	
	${lz:set("注释","*****************RFID卡号字段的输入框代码*****************")}
	${lz:set("注释","before$cssRfid和after$cssRfid变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssRfid==true">
	${before$cssRfid}
	<dl class="cssRfid " major  ref="cssRfid" >
		<dt>RFID卡号:</dt>
		<s:if test="#request.CTRL.e.cssRfid==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssRfid!=null">${csState$cssRfid}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssRfid" id="cssRfid"  value="${csState.cssRfid}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的RFID卡号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****RFID卡号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssRfid">${csState.cssRfid}</textarea>
		 		<span>
		 	
			 ${csState.cssRfid$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssRfid}
	</s:if>
	
	${lz:set("注释","*****************用户RFID字段的输入框代码*****************")}
	${lz:set("注释","before$cssRfidDte和after$cssRfidDte变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssRfidDte==true">
	${before$cssRfidDte}
	<dl class="cssRfidDte " minor  ref="cssRfidDte" >
		<dt>用户RFID:</dt>
		<s:if test="#request.CTRL.e.cssRfidDte==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssRfidDte!=null">${csState$cssRfidDte}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssRfidDte" id="cssRfidDte"  value="${csState.cssRfidDte}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的用户RFID</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****用户RFID字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssRfidDte">${csState.cssRfidDte}</textarea>
		 		<span>
		 	
			 ${csState.cssRfidDte$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssRfidDte}
	</s:if>
	
	${lz:set("注释","*****************OBD里程字段的输入框代码*****************")}
	${lz:set("注释","before$cssObdMile和after$cssObdMile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssObdMile==true">
	${before$cssObdMile}
	<dl class="cssObdMile " minor  ref="cssObdMile" >
		<dt>OBD里程:</dt>
		<s:if test="#request.CTRL.e.cssObdMile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssObdMile!=null">${csState$cssObdMile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssObdMile" id="cssObdMile"  value="${csState.cssObdMile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的OBD里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****OBD里程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssObdMile">${csState.cssObdMile}</textarea>
		 		<span>
		 	
			 ${csState.cssObdMile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssObdMile}
	</s:if>
	
	${lz:set("注释","*****************发动机温度字段的输入框代码*****************")}
	${lz:set("注释","before$cssEngineT和after$cssEngineT变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssEngineT==true">
	${before$cssEngineT}
	<dl class="cssEngineT " minor  ref="cssEngineT" >
		<dt>发动机温度:</dt>
		<s:if test="#request.CTRL.e.cssEngineT==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssEngineT!=null">${csState$cssEngineT}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssEngineT" id="cssEngineT"  value="${csState.cssEngineT}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的发动机温度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发动机温度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssEngineT">${csState.cssEngineT}</textarea>
		 		<span>
		 	
			 ${csState.cssEngineT$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssEngineT}
	</s:if>
	
	${lz:set("注释","*****************总里程字段的输入框代码*****************")}
	${lz:set("注释","before$cssMileage和after$cssMileage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssMileage==true">
	${before$cssMileage}
	<dl class="cssMileage " major  ref="cssMileage" >
		<dt>总&nbsp;&nbsp;里&nbsp;&nbsp;程:</dt>
		<s:if test="#request.CTRL.e.cssMileage==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssMileage!=null">${csState$cssMileage}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssMileage" id="cssMileage"  value="${csState.cssMileage}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的总里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****总里程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssMileage">${csState.cssMileage}</textarea>
		 		<span>
		 	
			 ${csState.cssMileage$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssMileage}
	</s:if>
	
	${lz:set("注释","*****************车速字段的输入框代码*****************")}
	${lz:set("注释","before$cssSpeed和after$cssSpeed变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssSpeed==true">
	${before$cssSpeed}
	<dl class="cssSpeed " major  ref="cssSpeed" >
		<dt>车　　速:</dt>
		<s:if test="#request.CTRL.e.cssSpeed==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssSpeed!=null">${csState$cssSpeed}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssSpeed" id="cssSpeed"  value="${csState.cssSpeed}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的车速</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车速字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssSpeed">${csState.cssSpeed}</textarea>
		 		<span>
		 	
			 ${csState.cssSpeed$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssSpeed}
	</s:if>
	
	${lz:set("注释","*****************转速字段的输入框代码*****************")}
	${lz:set("注释","before$cssMotor和after$cssMotor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssMotor==true">
	${before$cssMotor}
	<dl class="cssMotor " major  ref="cssMotor" >
		<dt>转　　速:</dt>
		<s:if test="#request.CTRL.e.cssMotor==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssMotor!=null">${csState$cssMotor}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssMotor" id="cssMotor"  value="${csState.cssMotor}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的转速</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****转速字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssMotor">${csState.cssMotor}</textarea>
		 		<span>
		 	
			 ${csState.cssMotor$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssMotor}
	</s:if>
	
	${lz:set("注释","*****************燃油量字段的输入框代码*****************")}
	${lz:set("注释","before$cssOil和after$cssOil变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssOil==true">
	${before$cssOil}
	<dl class="cssOil " major  ref="cssOil" >
		<dt>燃&nbsp;&nbsp;油&nbsp;&nbsp;量:</dt>
		<s:if test="#request.CTRL.e.cssOil==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssOil!=null">${csState$cssOil}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssOil" id="cssOil"  value="${csState.cssOil}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的燃油量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****燃油量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssOil">${csState.cssOil}</textarea>
		 		<span>
		 	
			 ${csState.cssOil$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssOil}
	</s:if>
	
	${lz:set("注释","*****************蓄电量字段的输入框代码*****************")}
	${lz:set("注释","before$cssPower和after$cssPower变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssPower==true">
	${before$cssPower}
	<dl class="cssPower " major  ref="cssPower" >
		<dt>蓄&nbsp;&nbsp;电&nbsp;&nbsp;量:</dt>
		<s:if test="#request.CTRL.e.cssPower==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssPower!=null">${csState$cssPower}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssPower" id="cssPower"  value="${csState.cssPower}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的蓄电量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****蓄电量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssPower">${csState.cssPower}</textarea>
		 		<span>
		 	
			 ${csState.cssPower$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssPower}
	</s:if>
	
	${lz:set("注释","*****************动力电池电量字段的输入框代码*****************")}
	${lz:set("注释","before$cssEvBattery和after$cssEvBattery变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssEvBattery==true">
	${before$cssEvBattery}
	<dl class="cssEvBattery " minor  ref="cssEvBattery" >
		<dt>动力电池电量:</dt>
		<s:if test="#request.CTRL.e.cssEvBattery==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssEvBattery!=null">${csState$cssEvBattery}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="16" name="csState.cssEvBattery" id="cssEvBattery"  value="${csState.cssEvBattery}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的动力电池电量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****动力电池电量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssEvBattery">${csState.cssEvBattery}</textarea>
		 		<span>
		 	
			 ${csState.cssEvBattery$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssEvBattery}
	</s:if>
	
	${lz:set("注释","*****************在充电字段的输入框代码*****************")}
	${lz:set("注释","before$cssCharging和after$cssCharging变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssCharging==true">
	${before$cssCharging}
	<dl class="cssCharging " minor  ref="cssCharging" >
		<dt>在&nbsp;&nbsp;充&nbsp;&nbsp;电:</dt>
		<s:if test="#request.CTRL.e.cssCharging==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssCharging!=null">${csState$cssCharging}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssCharging" id="cssCharging"  value="${csState.cssCharging}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的在充电</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****在充电字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssCharging">${csState.cssCharging}</textarea>
		 		<span>
		 	
			 ${csState.cssCharging$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssCharging}
	</s:if>
	
	${lz:set("注释","*****************油里程字段的输入框代码*****************")}
	${lz:set("注释","before$cssFuelMileage和after$cssFuelMileage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssFuelMileage==true">
	${before$cssFuelMileage}
	<dl class="cssFuelMileage " minor  ref="cssFuelMileage" >
		<dt>油&nbsp;&nbsp;里&nbsp;&nbsp;程:</dt>
		<s:if test="#request.CTRL.e.cssFuelMileage==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssFuelMileage!=null">${csState$cssFuelMileage}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssFuelMileage" id="cssFuelMileage"  value="${csState.cssFuelMileage}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的油里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****油里程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssFuelMileage">${csState.cssFuelMileage}</textarea>
		 		<span>
		 	
			 ${csState.cssFuelMileage$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssFuelMileage}
	</s:if>
	
	${lz:set("注释","*****************电量程字段的输入框代码*****************")}
	${lz:set("注释","before$cssElectricMileage和after$cssElectricMileage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssElectricMileage==true">
	${before$cssElectricMileage}
	<dl class="cssElectricMileage " minor  ref="cssElectricMileage" >
		<dt>电&nbsp;&nbsp;量&nbsp;&nbsp;程:</dt>
		<s:if test="#request.CTRL.e.cssElectricMileage==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssElectricMileage!=null">${csState$cssElectricMileage}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssElectricMileage" id="cssElectricMileage"  value="${csState.cssElectricMileage}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的电量程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****电量程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssElectricMileage">${csState.cssElectricMileage}</textarea>
		 		<span>
		 	
			 ${csState.cssElectricMileage$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssElectricMileage}
	</s:if>
	
	${lz:set("注释","*****************续航里程字段的输入框代码*****************")}
	${lz:set("注释","before$cssEndurance和after$cssEndurance变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssEndurance==true">
	${before$cssEndurance}
	<dl class="cssEndurance " minor  ref="cssEndurance" >
		<dt>续航里程:</dt>
		<s:if test="#request.CTRL.e.cssEndurance==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssEndurance!=null">${csState$cssEndurance}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssEndurance" id="cssEndurance"  value="${csState.cssEndurance}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的续航里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****续航里程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssEndurance">${csState.cssEndurance}</textarea>
		 		<span>
		 	
			 ${csState.cssEndurance$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssEndurance}
	</s:if>
	
	${lz:set("注释","*****************温度字段的输入框代码*****************")}
	${lz:set("注释","before$cssTemperature和after$cssTemperature变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssTemperature==true">
	${before$cssTemperature}
	<dl class="cssTemperature " minor  ref="cssTemperature" >
		<dt>温　　度:</dt>
		<s:if test="#request.CTRL.e.cssTemperature==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssTemperature!=null">${csState$cssTemperature}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssTemperature" id="cssTemperature"  value="${csState.cssTemperature}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的温度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****温度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssTemperature">${csState.cssTemperature}</textarea>
		 		<span>
		 	
			 ${csState.cssTemperature$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssTemperature}
	</s:if>
	
	${lz:set("注释","*****************信号强度字段的输入框代码*****************")}
	${lz:set("注释","before$cssCsq和after$cssCsq变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssCsq==true">
	${before$cssCsq}
	<dl class="cssCsq " minor  ref="cssCsq" >
		<dt>信号强度:</dt>
		<s:if test="#request.CTRL.e.cssCsq==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssCsq!=null">${csState$cssCsq}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssCsq" id="cssCsq"  value="${csState.cssCsq}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的信号强度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****信号强度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssCsq">${csState.cssCsq}</textarea>
		 		<span>
		 	
			 ${csState.cssCsq$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssCsq}
	</s:if>
	
	${lz:set("注释","*****************功耗字段的输入框代码*****************")}
	${lz:set("注释","before$cssPowerConsumption和after$cssPowerConsumption变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssPowerConsumption==true">
	${before$cssPowerConsumption}
	<dl class="cssPowerConsumption " minor  ref="cssPowerConsumption" >
		<dt>功　　耗:</dt>
		<s:if test="#request.CTRL.e.cssPowerConsumption==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssPowerConsumption!=null">${csState$cssPowerConsumption}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssPowerConsumption" id="cssPowerConsumption"  value="${csState.cssPowerConsumption}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的功耗</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****功耗字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssPowerConsumption">${csState.cssPowerConsumption}</textarea>
		 		<span>
		 	
			 ${csState.cssPowerConsumption$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssPowerConsumption}
	</s:if>
	
	${lz:set("注释","*****************经度字段的输入框代码*****************")}
	${lz:set("注释","before$cssLongitude和after$cssLongitude变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssLongitude==true">
	${before$cssLongitude}
	<dl class="cssLongitude " major  ref="cssLongitude" >
		<dt>经　　度:</dt>
		<s:if test="#request.CTRL.e.cssLongitude==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssLongitude!=null">${csState$cssLongitude}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssLongitude" id="cssLongitude"  value="${csState.cssLongitude}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的经度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****经度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssLongitude">${csState.cssLongitude}</textarea>
		 		<span>
		 	
			 ${csState.cssLongitude$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssLongitude}
	</s:if>
	
	${lz:set("注释","*****************纬度字段的输入框代码*****************")}
	${lz:set("注释","before$cssLatitude和after$cssLatitude变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssLatitude==true">
	${before$cssLatitude}
	<dl class="cssLatitude " major  ref="cssLatitude" >
		<dt>纬　　度:</dt>
		<s:if test="#request.CTRL.e.cssLatitude==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csState$cssLatitude!=null">${csState$cssLatitude}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csState.cssLatitude" id="cssLatitude"  value="${csState.cssLatitude}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的纬度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****纬度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssLatitude">${csState.cssLatitude}</textarea>
		 		<span>
		 	
			 ${csState.cssLatitude$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssLatitude}
	</s:if>
	
	${lz:set("注释","*****************当前订单字段的输入框代码*****************")}
	${lz:set("注释","before$cssOrder和after$cssOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssOrder==true">
	${before$cssOrder}
	<dl class="cssOrder " major  ref="cssOrder" >
		<dt>当前订单:</dt>
		<s:if test="#request.CTRL.e.cssOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csState$cssOrder!=null">${csState$cssOrder}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csState.cssOrder" id="cssOrder"  value="${csState.cssOrder}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆当前状态的当前订单</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****当前订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssOrder">${csState.cssOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csState.cssOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csState.cssOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssOrder}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csState.cssId}" name="csState.cssId" id="cssId" />
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