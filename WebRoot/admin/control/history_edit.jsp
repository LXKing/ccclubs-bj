<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/control/history.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/control/history.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csHistoryState.cshsId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cshsHost,cshsMoData",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cshsHost,cshsMoData",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cshsHost,cshsMoData",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cshsHost,cshsMoData",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车辆历史状态编辑":CTRL.title}</title>
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
	$("#historyForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cshsId==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsHost==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsNumber==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsNumber":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsAddTime==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsCurrentTime==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsCurrentTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsRented==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsRented":function(el){
			if(el.value.length>8 && el.value.indexOf("[*]")==-1)
				return "租赁状态最大长度为8个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsWarn==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsWarn":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "有报警最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsRfid==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsRfid":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "RFID卡号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsRfidDte==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsRfidDte":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "用户RFID最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsObdMile==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsObdMile":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "OBD里程最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsEngineT==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsEngineT":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "发动机温度最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsMileage==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsMileage":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "总里程最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsSpeed==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsSpeed":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车速最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsMotor==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsMotor":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "转速最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsOil==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsOil":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "燃油量最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsPower==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsPower":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "蓄电量最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsEvBattery==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsEvBattery":function(el){
			if(el.value.length>16 && el.value.indexOf("[*]")==-1)
				return "动力电池电量最大长度为16个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsCharging==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsCharging":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "在充电最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsFuelMileage==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsFuelMileage":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "油里程最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsElectricMileage==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsElectricMileage":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "电量程最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsEndurance==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsEndurance":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "续航里程最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsTemperature==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsTemperature":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "温度最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsCsq==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsCsq":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "信号强度最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsPowerConsumption==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsPowerConsumption":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "功耗最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsLongitude==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsLongitude":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "经度最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsLatitude==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsLatitude":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "纬度最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsOrder==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsOrder":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^([0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的编号";				
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshsMoData==true">
		${lz:set("haveEditable",true)}
		,"csHistoryState.cshsMoData":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "接口数据最大长度为65535个字符";
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
	<form class="form editform" ref="CsHistoryState" id="historyForm" name="historyForm" action="${empty CTRL.action?"history_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车辆历史状态相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csHistoryStateToken}" name="csHistoryStateToken" id="csHistoryStateToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csHistoryState.cshsId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cshsId和after$cshsId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsId==true">
	${before$cshsId}
	<dl class="cshsId ${CTRL.e.cshsId?"hidden":""}" major  ref="cshsId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cshsId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csHistoryState$cshsId!=null">${csHistoryState$cshsId}</s:if><s:else>
			<input type="hidden" value="${csHistoryState.cshsId}" name="csHistoryState.cshsId" id="cshsId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsId">${csHistoryState.cshsId}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cshsHost和after$cshsHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsHost==true">
	${before$cshsHost}
	<dl class="cshsHost " major  ref="cshsHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cshsHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csHistoryState$cshsHost!=null">${csHistoryState$cshsHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cshsHost" name="csHistoryState.cshsHost">
		 				<option selected value="${csHistoryState.cshsHost}">
		 					${get:SrvHost(csHistoryState.cshsHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cshsHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cshsHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsHost">${csHistoryState.cshsHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csHistoryState.cshsHost}',{ctrl:{editable:false,visible:true}})">
			 ${csHistoryState.cshsHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsHost}
	</s:if>
	
	${lz:set("注释","*****************车牌号字段的输入框代码*****************")}
	${lz:set("注释","before$cshsNumber和after$cshsNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsNumber==true">
	${before$cshsNumber}
	<dl class="cshsNumber " major  ref="cshsNumber" >
		<dt>车&nbsp;&nbsp;牌&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.cshsNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csHistoryState$cshsNumber!=null">${csHistoryState$cshsNumber}</s:if><s:else>
	 				<input title="请输入查询" class="combox" action="${basePath}${proname}/object/car_numbers.do?value={param}" size="16" type="text" id="cshsNumber" name="csHistoryState.cshsNumber" text="${csHistoryState.cshsNumber}" value="${csHistoryState.cshsNumber}" />	 		
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsNumber">${csHistoryState.cshsNumber}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsNumber}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cshsAddTime和after$cshsAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsAddTime==true">
	${before$cshsAddTime}
	<dl class="cshsAddTime " major  ref="cshsAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cshsAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csHistoryState$cshsAddTime!=null">${csHistoryState$cshsAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csHistoryState.cshsAddTime" id="cshsAddTime"  value="<s:date name="csHistoryState.cshsAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆历史状态的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsAddTime">${csHistoryState.cshsAddTime}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsAddTime}
	</s:if>
	
	${lz:set("注释","*****************下位机时间字段的输入框代码*****************")}
	${lz:set("注释","before$cshsCurrentTime和after$cshsCurrentTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsCurrentTime==true">
	${before$cshsCurrentTime}
	<dl class="cshsCurrentTime " minor  ref="cshsCurrentTime" >
		<dt>下位机时间:</dt>
		<s:if test="#request.CTRL.e.cshsCurrentTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csHistoryState$cshsCurrentTime!=null">${csHistoryState$cshsCurrentTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csHistoryState.cshsCurrentTime" id="cshsCurrentTime"  value="<s:date name="csHistoryState.cshsCurrentTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆历史状态的下位机时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****下位机时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsCurrentTime">${csHistoryState.cshsCurrentTime}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsCurrentTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsCurrentTime}
	</s:if>
	
	${lz:set("注释","*****************租赁状态字段的输入框代码*****************")}
	${lz:set("注释","before$cshsRented和after$cshsRented变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsRented==true">
	${before$cshsRented}
	<dl class="cshsRented " major  ref="cshsRented" >
		<dt>租赁状态:</dt>
		<s:if test="#request.CTRL.e.cshsRented==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsRented!=null">${csHistoryState$cshsRented}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="8" name="csHistoryState.cshsRented" id="cshsRented"  value="${csHistoryState.cshsRented}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的租赁状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****租赁状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsRented">${csHistoryState.cshsRented}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsRented$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsRented}
	</s:if>
	
	${lz:set("注释","*****************有报警字段的输入框代码*****************")}
	${lz:set("注释","before$cshsWarn和after$cshsWarn变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsWarn==true">
	${before$cshsWarn}
	<dl class="cshsWarn " minor  ref="cshsWarn" >
		<dt>有&nbsp;&nbsp;报&nbsp;&nbsp;警:</dt>
		<s:if test="#request.CTRL.e.cshsWarn==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsWarn!=null">${csHistoryState$cshsWarn}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsWarn" id="cshsWarn"  value="${csHistoryState.cshsWarn}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的有报警</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****有报警字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsWarn">${csHistoryState.cshsWarn}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsWarn$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsWarn}
	</s:if>
	
	${lz:set("注释","*****************RFID卡号字段的输入框代码*****************")}
	${lz:set("注释","before$cshsRfid和after$cshsRfid变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsRfid==true">
	${before$cshsRfid}
	<dl class="cshsRfid " major  ref="cshsRfid" >
		<dt>RFID卡号:</dt>
		<s:if test="#request.CTRL.e.cshsRfid==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsRfid!=null">${csHistoryState$cshsRfid}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsRfid" id="cshsRfid"  value="${csHistoryState.cshsRfid}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的RFID卡号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****RFID卡号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsRfid">${csHistoryState.cshsRfid}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsRfid$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsRfid}
	</s:if>
	
	${lz:set("注释","*****************用户RFID字段的输入框代码*****************")}
	${lz:set("注释","before$cshsRfidDte和after$cshsRfidDte变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsRfidDte==true">
	${before$cshsRfidDte}
	<dl class="cshsRfidDte " minor  ref="cshsRfidDte" >
		<dt>用户RFID:</dt>
		<s:if test="#request.CTRL.e.cshsRfidDte==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsRfidDte!=null">${csHistoryState$cshsRfidDte}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsRfidDte" id="cshsRfidDte"  value="${csHistoryState.cshsRfidDte}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的用户RFID</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****用户RFID字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsRfidDte">${csHistoryState.cshsRfidDte}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsRfidDte$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsRfidDte}
	</s:if>
	
	${lz:set("注释","*****************OBD里程字段的输入框代码*****************")}
	${lz:set("注释","before$cshsObdMile和after$cshsObdMile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsObdMile==true">
	${before$cshsObdMile}
	<dl class="cshsObdMile " minor  ref="cshsObdMile" >
		<dt>OBD里程:</dt>
		<s:if test="#request.CTRL.e.cshsObdMile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsObdMile!=null">${csHistoryState$cshsObdMile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsObdMile" id="cshsObdMile"  value="${csHistoryState.cshsObdMile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的OBD里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****OBD里程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsObdMile">${csHistoryState.cshsObdMile}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsObdMile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsObdMile}
	</s:if>
	
	${lz:set("注释","*****************发动机温度字段的输入框代码*****************")}
	${lz:set("注释","before$cshsEngineT和after$cshsEngineT变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsEngineT==true">
	${before$cshsEngineT}
	<dl class="cshsEngineT " minor  ref="cshsEngineT" >
		<dt>发动机温度:</dt>
		<s:if test="#request.CTRL.e.cshsEngineT==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsEngineT!=null">${csHistoryState$cshsEngineT}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsEngineT" id="cshsEngineT"  value="${csHistoryState.cshsEngineT}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的发动机温度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发动机温度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsEngineT">${csHistoryState.cshsEngineT}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsEngineT$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsEngineT}
	</s:if>
	
	${lz:set("注释","*****************总里程字段的输入框代码*****************")}
	${lz:set("注释","before$cshsMileage和after$cshsMileage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsMileage==true">
	${before$cshsMileage}
	<dl class="cshsMileage " major  ref="cshsMileage" >
		<dt>总&nbsp;&nbsp;里&nbsp;&nbsp;程:</dt>
		<s:if test="#request.CTRL.e.cshsMileage==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsMileage!=null">${csHistoryState$cshsMileage}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsMileage" id="cshsMileage"  value="${csHistoryState.cshsMileage}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的总里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****总里程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsMileage">${csHistoryState.cshsMileage}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsMileage$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsMileage}
	</s:if>
	
	${lz:set("注释","*****************车速字段的输入框代码*****************")}
	${lz:set("注释","before$cshsSpeed和after$cshsSpeed变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsSpeed==true">
	${before$cshsSpeed}
	<dl class="cshsSpeed " major  ref="cshsSpeed" >
		<dt>车　　速:</dt>
		<s:if test="#request.CTRL.e.cshsSpeed==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsSpeed!=null">${csHistoryState$cshsSpeed}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsSpeed" id="cshsSpeed"  value="${csHistoryState.cshsSpeed}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的车速</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车速字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsSpeed">${csHistoryState.cshsSpeed}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsSpeed$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsSpeed}
	</s:if>
	
	${lz:set("注释","*****************转速字段的输入框代码*****************")}
	${lz:set("注释","before$cshsMotor和after$cshsMotor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsMotor==true">
	${before$cshsMotor}
	<dl class="cshsMotor " major  ref="cshsMotor" >
		<dt>转　　速:</dt>
		<s:if test="#request.CTRL.e.cshsMotor==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsMotor!=null">${csHistoryState$cshsMotor}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsMotor" id="cshsMotor"  value="${csHistoryState.cshsMotor}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的转速</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****转速字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsMotor">${csHistoryState.cshsMotor}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsMotor$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsMotor}
	</s:if>
	
	${lz:set("注释","*****************燃油量字段的输入框代码*****************")}
	${lz:set("注释","before$cshsOil和after$cshsOil变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsOil==true">
	${before$cshsOil}
	<dl class="cshsOil " major  ref="cshsOil" >
		<dt>燃&nbsp;&nbsp;油&nbsp;&nbsp;量:</dt>
		<s:if test="#request.CTRL.e.cshsOil==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsOil!=null">${csHistoryState$cshsOil}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsOil" id="cshsOil"  value="${csHistoryState.cshsOil}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的燃油量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****燃油量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsOil">${csHistoryState.cshsOil}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsOil$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsOil}
	</s:if>
	
	${lz:set("注释","*****************蓄电量字段的输入框代码*****************")}
	${lz:set("注释","before$cshsPower和after$cshsPower变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsPower==true">
	${before$cshsPower}
	<dl class="cshsPower " major  ref="cshsPower" >
		<dt>蓄&nbsp;&nbsp;电&nbsp;&nbsp;量:</dt>
		<s:if test="#request.CTRL.e.cshsPower==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsPower!=null">${csHistoryState$cshsPower}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsPower" id="cshsPower"  value="${csHistoryState.cshsPower}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的蓄电量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****蓄电量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsPower">${csHistoryState.cshsPower}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsPower$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsPower}
	</s:if>
	
	${lz:set("注释","*****************动力电池电量字段的输入框代码*****************")}
	${lz:set("注释","before$cshsEvBattery和after$cshsEvBattery变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsEvBattery==true">
	${before$cshsEvBattery}
	<dl class="cshsEvBattery " minor  ref="cshsEvBattery" >
		<dt>动力电池电量:</dt>
		<s:if test="#request.CTRL.e.cshsEvBattery==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsEvBattery!=null">${csHistoryState$cshsEvBattery}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="16" name="csHistoryState.cshsEvBattery" id="cshsEvBattery"  value="${csHistoryState.cshsEvBattery}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的动力电池电量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****动力电池电量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsEvBattery">${csHistoryState.cshsEvBattery}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsEvBattery$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsEvBattery}
	</s:if>
	
	${lz:set("注释","*****************在充电字段的输入框代码*****************")}
	${lz:set("注释","before$cshsCharging和after$cshsCharging变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsCharging==true">
	${before$cshsCharging}
	<dl class="cshsCharging " minor  ref="cshsCharging" >
		<dt>在&nbsp;&nbsp;充&nbsp;&nbsp;电:</dt>
		<s:if test="#request.CTRL.e.cshsCharging==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsCharging!=null">${csHistoryState$cshsCharging}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsCharging" id="cshsCharging"  value="${csHistoryState.cshsCharging}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的在充电</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****在充电字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsCharging">${csHistoryState.cshsCharging}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsCharging$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsCharging}
	</s:if>
	
	${lz:set("注释","*****************油里程字段的输入框代码*****************")}
	${lz:set("注释","before$cshsFuelMileage和after$cshsFuelMileage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsFuelMileage==true">
	${before$cshsFuelMileage}
	<dl class="cshsFuelMileage " minor  ref="cshsFuelMileage" >
		<dt>油&nbsp;&nbsp;里&nbsp;&nbsp;程:</dt>
		<s:if test="#request.CTRL.e.cshsFuelMileage==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsFuelMileage!=null">${csHistoryState$cshsFuelMileage}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsFuelMileage" id="cshsFuelMileage"  value="${csHistoryState.cshsFuelMileage}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的油里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****油里程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsFuelMileage">${csHistoryState.cshsFuelMileage}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsFuelMileage$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsFuelMileage}
	</s:if>
	
	${lz:set("注释","*****************电量程字段的输入框代码*****************")}
	${lz:set("注释","before$cshsElectricMileage和after$cshsElectricMileage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsElectricMileage==true">
	${before$cshsElectricMileage}
	<dl class="cshsElectricMileage " minor  ref="cshsElectricMileage" >
		<dt>电&nbsp;&nbsp;量&nbsp;&nbsp;程:</dt>
		<s:if test="#request.CTRL.e.cshsElectricMileage==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsElectricMileage!=null">${csHistoryState$cshsElectricMileage}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsElectricMileage" id="cshsElectricMileage"  value="${csHistoryState.cshsElectricMileage}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的电量程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****电量程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsElectricMileage">${csHistoryState.cshsElectricMileage}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsElectricMileage$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsElectricMileage}
	</s:if>
	
	${lz:set("注释","*****************续航里程字段的输入框代码*****************")}
	${lz:set("注释","before$cshsEndurance和after$cshsEndurance变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsEndurance==true">
	${before$cshsEndurance}
	<dl class="cshsEndurance " minor  ref="cshsEndurance" >
		<dt>续航里程:</dt>
		<s:if test="#request.CTRL.e.cshsEndurance==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsEndurance!=null">${csHistoryState$cshsEndurance}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsEndurance" id="cshsEndurance"  value="${csHistoryState.cshsEndurance}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的续航里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****续航里程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsEndurance">${csHistoryState.cshsEndurance}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsEndurance$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsEndurance}
	</s:if>
	
	${lz:set("注释","*****************温度字段的输入框代码*****************")}
	${lz:set("注释","before$cshsTemperature和after$cshsTemperature变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsTemperature==true">
	${before$cshsTemperature}
	<dl class="cshsTemperature " minor  ref="cshsTemperature" >
		<dt>温　　度:</dt>
		<s:if test="#request.CTRL.e.cshsTemperature==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsTemperature!=null">${csHistoryState$cshsTemperature}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsTemperature" id="cshsTemperature"  value="${csHistoryState.cshsTemperature}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的温度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****温度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsTemperature">${csHistoryState.cshsTemperature}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsTemperature$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsTemperature}
	</s:if>
	
	${lz:set("注释","*****************信号强度字段的输入框代码*****************")}
	${lz:set("注释","before$cshsCsq和after$cshsCsq变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsCsq==true">
	${before$cshsCsq}
	<dl class="cshsCsq " minor  ref="cshsCsq" >
		<dt>信号强度:</dt>
		<s:if test="#request.CTRL.e.cshsCsq==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsCsq!=null">${csHistoryState$cshsCsq}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsCsq" id="cshsCsq"  value="${csHistoryState.cshsCsq}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的信号强度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****信号强度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsCsq">${csHistoryState.cshsCsq}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsCsq$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsCsq}
	</s:if>
	
	${lz:set("注释","*****************功耗字段的输入框代码*****************")}
	${lz:set("注释","before$cshsPowerConsumption和after$cshsPowerConsumption变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsPowerConsumption==true">
	${before$cshsPowerConsumption}
	<dl class="cshsPowerConsumption " minor  ref="cshsPowerConsumption" >
		<dt>功　　耗:</dt>
		<s:if test="#request.CTRL.e.cshsPowerConsumption==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsPowerConsumption!=null">${csHistoryState$cshsPowerConsumption}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsPowerConsumption" id="cshsPowerConsumption"  value="${csHistoryState.cshsPowerConsumption}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的功耗</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****功耗字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsPowerConsumption">${csHistoryState.cshsPowerConsumption}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsPowerConsumption$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsPowerConsumption}
	</s:if>
	
	${lz:set("注释","*****************经度字段的输入框代码*****************")}
	${lz:set("注释","before$cshsLongitude和after$cshsLongitude变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsLongitude==true">
	${before$cshsLongitude}
	<dl class="cshsLongitude " major  ref="cshsLongitude" >
		<dt>经　　度:</dt>
		<s:if test="#request.CTRL.e.cshsLongitude==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsLongitude!=null">${csHistoryState$cshsLongitude}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsLongitude" id="cshsLongitude"  value="${csHistoryState.cshsLongitude}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的经度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****经度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsLongitude">${csHistoryState.cshsLongitude}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsLongitude$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsLongitude}
	</s:if>
	
	${lz:set("注释","*****************纬度字段的输入框代码*****************")}
	${lz:set("注释","before$cshsLatitude和after$cshsLatitude变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsLatitude==true">
	${before$cshsLatitude}
	<dl class="cshsLatitude " major  ref="cshsLatitude" >
		<dt>纬　　度:</dt>
		<s:if test="#request.CTRL.e.cshsLatitude==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHistoryState$cshsLatitude!=null">${csHistoryState$cshsLatitude}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHistoryState.cshsLatitude" id="cshsLatitude"  value="${csHistoryState.cshsLatitude}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的纬度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****纬度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsLatitude">${csHistoryState.cshsLatitude}</textarea>
		 		<span>
		 	
			 ${csHistoryState.cshsLatitude$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsLatitude}
	</s:if>
	
	${lz:set("注释","*****************当前订单字段的输入框代码*****************")}
	${lz:set("注释","before$cshsOrder和after$cshsOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsOrder==true">
	${before$cshsOrder}
	<dl class="cshsOrder " major  ref="cshsOrder" >
		<dt>当前订单:</dt>
		<s:if test="#request.CTRL.e.cshsOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csHistoryState$cshsOrder!=null">${csHistoryState$cshsOrder}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csHistoryState.cshsOrder" id="cshsOrder"  value="${csHistoryState.cshsOrder}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的当前订单</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****当前订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshsOrder">${csHistoryState.cshsOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csHistoryState.cshsOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csHistoryState.cshsOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsOrder}
	</s:if>
	
	${lz:set("注释","*****************接口数据字段的输入框代码*****************")}
	${lz:set("注释","before$cshsMoData和after$cshsMoData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshsMoData==true">
	${before$cshsMoData}
	<dl class="cshsMoData " minor  ref="cshsMoData" style="width:98%;">
		<dt>接口数据:</dt>
		<s:if test="#request.CTRL.e.cshsMoData==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csHistoryState$cshsMoData!=null">${csHistoryState$cshsMoData}</s:if><s:else>
		 	<textarea class="input wide"  id="cshsMoData" name="csHistoryState.cshsMoData" rows="5">${csHistoryState.cshsMoData}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆历史状态的接口数据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接口数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csHistoryState.cshsMoData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshsMoData}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csHistoryState.cshsId}" name="csHistoryState.cshsId" id="cshsId" />
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