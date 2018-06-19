<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/repair.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/service/repair.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csRepair.csrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csrId,csrHost,csrProfile,csrCar,csrAdr,csrOrder,csrSender,csrInDate,csrOutDate,csrDescript,csrRepairNo,csrMoney,csrIsSettle,csrSettleTime,csrMemo,csrStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrId,csrHost,csrProfile,csrCar,csrAdr,csrOrder,csrSender,csrInDate,csrOutDate,csrDescript,csrRepairNo,csrMoney,csrIsSettle,csrSettleTime,csrMemo,csrStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csrId,csrHost,csrProfile,csrCar,csrAdr,csrOrder,csrSender,csrInDate,csrOutDate,csrDescript,csrRepairNo,csrMoney,csrIsSettle,csrSettleTime,csrMemo,csrStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrId,csrHost,csrProfile,csrCar,csrAdr,csrOrder,csrSender,csrInDate,csrOutDate,csrDescript,csrRepairNo,csrMoney,csrIsSettle,csrSettleTime,csrMemo,csrStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车辆维修编辑":CTRL.title}</title>
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
	$("#repairForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csrId==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrHost==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrProfile==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrProfile":function(el){
			if(jQuery.trim(el.value)=="")
				return "报修描述不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "报修描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrCar==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrAdr==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrAdr":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrOrder==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrSender==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrSender":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrInDate==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrInDate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrOutDate==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrOutDate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrDescript==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrDescript":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "维修描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrRepairNo==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrRepairNo":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "维修单号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrMoney==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrMoney":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "维修费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrIsSettle==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrIsSettle":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrSettleTime==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrSettleTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrEditor==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrAddTime==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrMemo==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrMemo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrStatus==true">
		${lz:set("haveEditable",true)}
		,"csRepair.csrStatus":function(el){
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
	<form class="form editform" ref="CsRepair" id="repairForm" name="repairForm" action="${empty CTRL.action?"repair_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车辆维修相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csRepairToken}" name="csRepairToken" id="csRepairToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csRepair.csrId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csrId和after$csrId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrId==true">
	${before$csrId}
	<dl class="csrId ${CTRL.e.csrId?"hidden":""}" major  ref="csrId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csrId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csRepair$csrId!=null">${csRepair$csrId}</s:if><s:else>
			<input type="hidden" value="${csRepair.csrId}" name="csRepair.csrId" id="csrId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrId">${csRepair.csrId}</textarea>
		 		<span>
		 	
			 ${csRepair.csrId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csrHost和after$csrHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrHost==true">
	${before$csrHost}
	<dl class="csrHost " major  ref="csrHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csrHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csRepair$csrHost!=null">${csRepair$csrHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csrHost" name="csRepair.csrHost">
		 				<option selected value="${csRepair.csrHost}">
		 					${get:SrvHost(csRepair.csrHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csrHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrHost">${csRepair.csrHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csRepair.csrHost}',{ctrl:{editable:false,visible:true}})">
			 ${csRepair.csrHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrHost}
	</s:if>
	
	${lz:set("注释","*****************报修描述字段的输入框代码*****************")}
	${lz:set("注释","before$csrProfile和after$csrProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrProfile==true">
	${before$csrProfile}
	<dl class="csrProfile " major  ref="csrProfile" style="width:98%;">
		<dt>报修描述:</dt>
		<s:if test="#request.CTRL.e.csrProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csRepair$csrProfile!=null">${csRepair$csrProfile}</s:if><s:else>
		 	<textarea class="input wide"  id="csrProfile" name="csRepair.csrProfile" rows="5">${csRepair.csrProfile}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车辆维修的报修描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****报修描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csRepair.csrProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrProfile}
	</s:if>
	
	${lz:set("注释","*****************维修车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csrCar和after$csrCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrCar==true">
	${before$csrCar}
	<dl class="csrCar " major  ref="csrCar" >
		<dt>维修车辆:</dt>
		<s:if test="#request.CTRL.e.csrCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csRepair$csrCar!=null">${csRepair$csrCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csrHost}" type="text" id="csrCar" name="csRepair.csrCar" text="${get:CsCar(csRepair.csrCar).cscNumber}" value="${csRepair.csrCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#csrCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****维修车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrCar">${csRepair.csrCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csRepair.csrCar}',{ctrl:{editable:false,visible:true}})">
			 ${csRepair.csrCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrCar}
	</s:if>
	
	${lz:set("注释","*****************送修地点字段的输入框代码*****************")}
	${lz:set("注释","before$csrAdr和after$csrAdr变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrAdr==true">
	${before$csrAdr}
	<dl class="csrAdr " major  ref="csrAdr" >
		<dt>送修地点:</dt>
		<s:if test="#request.CTRL.e.csrAdr==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csRepair$csrAdr!=null">${csRepair$csrAdr}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/service/repairadr_query.do?size=-1&csraHost={csrHost}" id="csrAdr" name="csRepair.csrAdr">
		 				<option selected value="${csRepair.csrAdr}">
		 					${get:CsRepairAdr(csRepair.csrAdr).csraName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrAdr').val())==''){return;};window.href('${basePath}${proname}/service/repairadr_details.do?key='+$('#csrAdr').val(),{ctrl:{editable:false}})"></a>
		 			<a class="reladd" config="{
		 				action:'${basePath}${proname}/service/repairadr_edit.do?rd=1'
		 				,callback:function(object){
		 					var a = $('<a>'+object.csraName+'</a>');
		 					a.attr('href','javascript:void(0);');
		 					a.attr('value',object.csraId);
		 					a.attr('style','text-indent: 5px;');
		 					var input = $('#csrAdr');
		 					input.val(object.csraId);
		 					input.prev().html(object.csraName);
		 					var combox = input.parents('.combox');
		 					a.appendTo(combox.find('.list'));
		 				}
		 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****送修地点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrAdr">${csRepair.csrAdr}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/repairadr_details.do?key=${csRepair.csrAdr}',{ctrl:{editable:false,visible:true}})">
			 ${csRepair.csrAdr$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrAdr}
	</s:if>
	
	${lz:set("注释","*****************事故订单字段的输入框代码*****************")}
	${lz:set("注释","before$csrOrder和after$csrOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrOrder==true">
	${before$csrOrder}
	<dl class="csrOrder " major  ref="csrOrder" >
		<dt>事故订单:</dt>
		<s:if test="#request.CTRL.e.csrOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csRepair$csrOrder!=null">${csRepair$csrOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csrHost}" type="text" id="csrOrder" name="csRepair.csrOrder" text="${get:CsOrder(csRepair.csrOrder).csoId}" value="${csRepair.csrOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csrOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事故订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrOrder">${csRepair.csrOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csRepair.csrOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csRepair.csrOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrOrder}
	</s:if>
	
	${lz:set("注释","*****************送修人字段的输入框代码*****************")}
	${lz:set("注释","before$csrSender和after$csrSender变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrSender==true">
	${before$csrSender}
	<dl class="csrSender " major  ref="csrSender" >
		<dt>送&nbsp;&nbsp;修&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csrSender==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csRepair$csrSender!=null">${csRepair$csrSender}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csrSender" name="csRepair.csrSender">
		 				<option selected value="${csRepair.csrSender}">
		 					${get:SrvUser(csRepair.csrSender).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrSender').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csrSender').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****送修人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrSender">${csRepair.csrSender}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csRepair.csrSender}',{ctrl:{editable:false,visible:true}})">
			 ${csRepair.csrSender$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrSender}
	</s:if>
	
	${lz:set("注释","*****************进厂日期字段的输入框代码*****************")}
	${lz:set("注释","before$csrInDate和after$csrInDate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrInDate==true">
	${before$csrInDate}
	<dl class="csrInDate " minor  ref="csrInDate" >
		<dt>进厂日期:</dt>
		<s:if test="#request.CTRL.e.csrInDate==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csRepair$csrInDate!=null">${csRepair$csrInDate}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csRepair.csrInDate" id="csrInDate"  value="<s:date name="csRepair.csrInDate" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆维修的进厂日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****进厂日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrInDate">${csRepair.csrInDate}</textarea>
		 		<span>
		 	
			 ${csRepair.csrInDate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrInDate}
	</s:if>
	
	${lz:set("注释","*****************出厂日期字段的输入框代码*****************")}
	${lz:set("注释","before$csrOutDate和after$csrOutDate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrOutDate==true">
	${before$csrOutDate}
	<dl class="csrOutDate " minor  ref="csrOutDate" >
		<dt>出厂日期:</dt>
		<s:if test="#request.CTRL.e.csrOutDate==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csRepair$csrOutDate!=null">${csRepair$csrOutDate}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csRepair.csrOutDate" id="csrOutDate"  value="<s:date name="csRepair.csrOutDate" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆维修的出厂日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****出厂日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrOutDate">${csRepair.csrOutDate}</textarea>
		 		<span>
		 	
			 ${csRepair.csrOutDate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrOutDate}
	</s:if>
	
	${lz:set("注释","*****************维修描述字段的输入框代码*****************")}
	${lz:set("注释","before$csrDescript和after$csrDescript变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrDescript==true">
	${before$csrDescript}
	<dl class="csrDescript " major  ref="csrDescript" style="width:98%;">
		<dt>维修描述:</dt>
		<s:if test="#request.CTRL.e.csrDescript==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRepair$csrDescript!=null">${csRepair$csrDescript}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csRepair.csrDescript" id="csrDescript"  value="${csRepair.csrDescript}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆维修的维修描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****维修描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csrDescript">${csRepair.csrDescript}</textarea>
		 		<span>
		 	
			 ${csRepair.csrDescript$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrDescript}
	</s:if>
	
	${lz:set("注释","*****************维修单号字段的输入框代码*****************")}
	${lz:set("注释","before$csrRepairNo和after$csrRepairNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrRepairNo==true">
	${before$csrRepairNo}
	<dl class="csrRepairNo " minor  ref="csrRepairNo" >
		<dt>维修单号:</dt>
		<s:if test="#request.CTRL.e.csrRepairNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRepair$csrRepairNo!=null">${csRepair$csrRepairNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csRepair.csrRepairNo" id="csrRepairNo"  value="${csRepair.csrRepairNo}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆维修的维修单号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****维修单号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrRepairNo">${csRepair.csrRepairNo}</textarea>
		 		<span>
		 	
			 ${csRepair.csrRepairNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrRepairNo}
	</s:if>
	
	${lz:set("注释","*****************维修费用字段的输入框代码*****************")}
	${lz:set("注释","before$csrMoney和after$csrMoney变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrMoney==true">
	${before$csrMoney}
	<dl class="csrMoney " major  ref="csrMoney" >
		<dt>维修费用:</dt>
		<s:if test="#request.CTRL.e.csrMoney==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csRepair$csrMoney!=null">${csRepair$csrMoney}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csRepair.csrMoney" id="csrMoney"  value="${csRepair.csrMoney}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆维修的维修费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****维修费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrMoney">${csRepair.csrMoney}</textarea>
		 		<span>
		 	
			 ${csRepair.csrMoney$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrMoney}
	</s:if>
	
	${lz:set("注释","*****************已结算？字段的输入框代码*****************")}
	${lz:set("注释","before$csrIsSettle和after$csrIsSettle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrIsSettle==true">
	${before$csrIsSettle}
	<dl class="csrIsSettle " major  ref="csrIsSettle" >
		<dt>已结算？:</dt>
		<s:if test="#request.CTRL.e.csrIsSettle==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRepair$csrIsSettle!=null">${csRepair$csrIsSettle}</s:if><s:else>
		 	<select class="narrow" id="csrIsSettle" name="csRepair.csrIsSettle">
		 		<option value="">请选择</option>
				<option value="true" ${csRepair.csrIsSettle==true?"selected":""}>是</option>
				<option value="false" ${csRepair.csrIsSettle==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆维修的已结算？</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****已结算？字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrIsSettle">${csRepair.csrIsSettle}</textarea>
		 		<span>
		 	
			 ${csRepair.csrIsSettle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrIsSettle}
	</s:if>
	
	${lz:set("注释","*****************结算日期字段的输入框代码*****************")}
	${lz:set("注释","before$csrSettleTime和after$csrSettleTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrSettleTime==true">
	${before$csrSettleTime}
	<dl class="csrSettleTime " major  ref="csrSettleTime" >
		<dt>结算日期:</dt>
		<s:if test="#request.CTRL.e.csrSettleTime==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csRepair$csrSettleTime!=null">${csRepair$csrSettleTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csRepair.csrSettleTime" id="csrSettleTime"  value="<s:date name="csRepair.csrSettleTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆维修的结算日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结算日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrSettleTime">${csRepair.csrSettleTime}</textarea>
		 		<span>
		 	
			 ${csRepair.csrSettleTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrSettleTime}
	</s:if>
	
	${lz:set("注释","*****************操作人字段的输入框代码*****************")}
	${lz:set("注释","before$csrEditor和after$csrEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrEditor==true">
	${before$csrEditor}
	<dl class="csrEditor " major  ref="csrEditor" >
		<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csrEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csRepair$csrEditor!=null">${csRepair$csrEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csrEditor" name="csRepair.csrEditor">
		 				<option selected value="${csRepair.csrEditor}">
		 					${get:SrvUser(csRepair.csrEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csrEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrEditor">${csRepair.csrEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csRepair.csrEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csRepair.csrEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrEditor}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csrAddTime和after$csrAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrAddTime==true">
	${before$csrAddTime}
	<dl class="csrAddTime " major  ref="csrAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csrAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csRepair$csrAddTime!=null">${csRepair$csrAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csRepair.csrAddTime" id="csrAddTime"  value="<s:date name="csRepair.csrAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆维修的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrAddTime">${csRepair.csrAddTime}</textarea>
		 		<span>
		 	
			 ${csRepair.csrAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csrMemo和after$csrMemo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrMemo==true">
	${before$csrMemo}
	<dl class="csrMemo " major  ref="csrMemo" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csrMemo==true">
		${lz:set("haveEditable",true)}
		<dd input="grid">
		<s:if test="#request.csRepair$csrMemo!=null">${csRepair$csrMemo}</s:if><s:else>
		    <textarea class="input wide grid" grid="[
		    	['备注内容',null,'textarea','content',null]
		    	,['签名',100,'text','sign',null]
		    ]" id="csrMemo" name="csRepair.csrMemo">${csRepair.csrMemo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csrMemo">${csRepair.csrMemo}</textarea>
		 		<span>
		 	
			 ${csRepair.csrMemo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrMemo}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csrStatus和after$csrStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrStatus==true">
	${before$csrStatus}
	<dl class="csrStatus " major  ref="csrStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csrStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRepair$csrStatus!=null">${csRepair$csrStatus}</s:if><s:else>
		 	<select class="narrow" id="csrStatus" name="csRepair.csrStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csRepair.csrStatus==0?"selected":""}>正在维修</option>
				<option value="1" ${csRepair.csrStatus==1?"selected":""}>维修完成</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆维修的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrStatus">${csRepair.csrStatus}</textarea>
		 		<span>
		 	
			 ${csRepair.csrStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csRepair.csrId}" name="csRepair.csrId" id="csrId" />
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