<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/outside/outsideStatistics.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/oa/outside/outsideStatistics.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty CsOutsideStatistics.csosId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csosId,csosUser,csosHost,csosOutlets,csosCar,csosOpenDoorTime,csosOpenDoorCount,csosCloseDoorTime,csosCloseDoorCount,csosFinishTime,csosStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csosId,csosUser,csosCar,csosHost,csosOutlets,csosOpenDoorCount,csosCloseDoorCount,csosStartCount,csosWhistleCount,csosOpenDoorTime,csosCloseDoorTime,csosAddTime,csosUpdateTime,csosFinishTime,csosStatus,csosCarLocation,csosUserLocation,csosDistance,csosWorkTime",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csosId,csosUser,csosCar,csosHost,csosOutlets,csosOpenDoorCount,csosCloseDoorCount,csosStartCount,csosWhistleCount,csosOpenDoorTime,csosCloseDoorTime,csosAddTime,csosUpdateTime,csosFinishTime,csosStatus,csosCarLocation,csosUserLocation,csosDistance,csosWorkTime",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csosId,csosUser,csosCar,csosHost,csosOutlets,csosOpenDoorCount,csosCloseDoorCount,csosStartCount,csosWhistleCount,csosOpenDoorTime,csosCloseDoorTime,csosAddTime,csosUpdateTime,csosFinishTime,csosStatus,csosCarLocation,csosUserLocation,csosDistance,csosWorkTime",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"异借确认编辑":CTRL.title}</title>
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

<s:if test="#request.CTRL.title=='确认'">
	<s:if test="outsideStatisticsAction.csosStatus>1">
		${lz:set("message","只有待确认或未确认的记录才能确认")}
		<lz:set name="jsonString">
			{
				title:"确认",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		<lz:set name="jsonString">
			{
				title:"确认",
				editable:false,
				action:"outsideStatistics_confirm.do",
				fields:{
					csosId:{visible:false}
					,csosUser:{editable:true}
					,csosCar:{editable:true}
					,csosHost:{visible:false}
					,csosOutlets:{visible:false}
					,csosProperty:{visible:false}
					,csosOpenDoorCount:{editable:false,visible:true}
					,csosCloseDoorCount:{editable:true,visible:true}
					,csosStartCount:{editable:false,visible:true}
					,csosWhistleCount:{editable:false,visible:true}
					,csosOpenDoorTime:{editable:false,visible:true}
					,csosCloseDoorTime:{editable:false,visible:true}
					,csosAddTime:{editable:false,visible:true}
					,csosUpdateTime:{visible:false}
					,csosFinishTime:{editable:false,visible:true}
					,csosStatus:{editable:false,visible:true}
					,csosCarLocation:{editable:false,visible:true}
					,csosMemberLocation:{editable:false,visible:true}
					,csosDistance:{editable:false,visible:true}
					,csosWorkTime:{editable:false,visible:true}
					
				},
				submits:[
					{name:"确&nbsp;&nbsp;&nbsp;&nbsp;认",value="确认"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
		
		
	</s:else>
</s:if>


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
	$("#outsideStatisticsForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csosId==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosId":function(el){
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosUser==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosUser":function(el){
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosCar==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosCar":function(el){
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosHost==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosHost":function(el){
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosOutlets==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosOutlets":function(el){
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosProperty==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosProperty":function(el){
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosOpenDoorCount==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosOpenDoorCount":function(el){
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosCloseDoorCount==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosCloseDoorCount":function(el){
		}
		</s:if>	
		
		<s:if test="#request.CTRL.e.csosStartCount==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosStartCount":function(el){
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosWhistleCount==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosWhistleCount":function(el){
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosOpenDoorTime==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosOpenDoorTime":function(el){
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosCloseDoorTime==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosCloseDoorTime":function(el){
		}
		</s:if>	
		
		
		
		<s:if test="#request.CTRL.e.csosAddTime==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosAddTime":function(el){
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosFinishTime==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosFinishTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		
		
		
		
		
		<s:if test="#request.CTRL.e.csosStatus==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
		
		<s:if test="#request.CTRL.e.csosCarLocation==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosCarLocation":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosMemberLocation==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosMemberLocation":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
		
		
		
		<s:if test="#request.CTRL.e.csosDistance==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosDistance":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
		
		
		<s:if test="#request.CTRL.e.csosWorkTime==true">
		${lz:set("haveEditable",true)}
		,"csOutsideStatistics.csosWorkTime":function(el){
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
	<form class="form editform" ref="csOutsideStatistics" id="outsideStatisticsForm" name="outsideStatisticsForm" action="${empty CTRL.action?"outsideStatistics_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车辆整备确认相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csOutsideStatisticsToken}" name="csOutsideStatisticsToken" id="csOutsideStatisticsToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csOutsideStatistics.csosId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csosId和after$csosId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosId==true">
	${before$csosId}
	<dl class="csosId ${CTRL.e.csosId?"hidden":""}" major  ref="csosId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csosId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csOutsideStatistics$csosId!=null">${csOutsideStatistics$csosId}</s:if><s:else>
			<input type="hidden" value="${csOutsideStatistics.csosId}" name="csOutsideStatistics.csosId" id="csosId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosId">${csOutsideStatistics.csosId}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csosUser和after$csosUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosUser==true">
	${before$csosUser}
	<dl class="csosUser " major  ref="csosUser" >
		<dt>员　　工:</dt>
		<s:if test="#request.CTRL.e.csosUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOutsideStatistics$csosUser!=null">${csOutsideStatistics$csosUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_query.do?size=-1" id="csosUser" name="csOutsideStatistics.csosUser">
		 				<option selected value="${csOutsideStatistics.csosUser}">
		 					${get:SrvHost(csOutsideStatistics.csosUser).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csosUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csosUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosUser">${csOutsideStatistics.csosUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csOutsideStatistics.csosUser}',{ctrl:{editable:false,visible:true}})">
			 ${csOutsideStatistics.csosUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosUser}
	</s:if>
	
	${lz:set("注释","*****************所属车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csosCar和after$csosCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosCar==true">
	${before$csosCar}
	<dl class="csosCar " major  ref="csosCar" >
		<dt>车　　辆:</dt>
		<s:if test="#request.CTRL.e.csosCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOutsideStatistics$csosCar!=null">${csOutsideStatistics$csosCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csosUser}" type="text" id="csosCar" name="csOutsideStatistics.csosCar" text="${get:CsCar(csOutsideStatistics.csosCar).cscNumber}" value="${csOutsideStatistics.csosCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csosCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#csosCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosCar">${csOutsideStatistics.csosCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csOutsideStatistics.csosCar}',{ctrl:{editable:false,visible:true}})">
			 ${csOutsideStatistics.csosCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosCar}
	</s:if>
	
	${lz:set("注释","*****************所属订单字段的输入框代码*****************")}
	${lz:set("注释","before$csosHost和after$csosHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosHost==true">
	${before$csosHost}
	<dl class="csosHost " major  ref="csosHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csosHost==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOutsideStatistics$csosHost!=null">${csOutsideStatistics$csosHost}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csosUser}" type="text" id="csosHost" name="csOutsideStatistics.csosHost" text="${get:CsOrder(csOutsideStatistics.csosHost).csoId}" value="${csOutsideStatistics.csosHost}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csosHost').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csosHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosHost">${csOutsideStatistics.csosHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csOutsideStatistics.csosHost}',{ctrl:{editable:false,visible:true}})">
			 ${csOutsideStatistics.csosHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosHost}
	</s:if>
	
	${lz:set("注释","*****************借出网点字段的输入框代码*****************")}
	${lz:set("注释","before$csosOutlets和after$csosOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosOutlets==true">
	${before$csosOutlets}
	<dl class="csosOutlets " major  ref="csosOutlets" >
		<dt>网　　点:</dt>
		<s:if test="#request.CTRL.e.csosOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOutsideStatistics$csosOutlets!=null">${csOutsideStatistics$csosOutlets}</s:if><s:else>
		 			<input title="请输入网点名称进行查询" class="combox narrow" action="${basePath}${proname}/object/outlets_query.do?value={param}&csoHost={csosUser}" type="text" id="csosOutlets" name="csOutsideStatistics.csosOutlets" text="${get:CsOutlets(csOutsideStatistics.csosOutlets).csoName}" value="${csOutsideStatistics.csosOutlets}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csosOutlets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#csosOutlets').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****借出网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosOutlets">${csOutsideStatistics.csosOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csOutsideStatistics.csosOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csOutsideStatistics.csosOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosOutlets}
	</s:if>
	
	${lz:set("注释","*****************预计还入网点字段的输入框代码*****************")}
	${lz:set("注释","before$csosProperty和after$csosProperty变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosProperty==true">
	${before$csosProperty}
	<dl class="csosProperty " major  ref="csosProperty" >
		<dt>整备项:</dt>
		<s:if test="#request.CTRL.e.csosProperty==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOutsideStatistics$csosProperty!=null">${csOutsideStatistics$csosProperty}</s:if><s:else>
		 			<input title="请输入网点名称进行查询" class="combox narrow" action="${basePath}${proname}/configurator/property_query.do?value={param}&csoHost={csosUser}" type="text" id="csosProperty" name="csOutsideStatistics.csosProperty" text="${get:CsOutlets(csOutsideStatistics.csosProperty).csoName}" value="${csOutsideStatistics.csosProperty}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csosProperty').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#csosProperty').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预计还入网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosProperty">${csOutsideStatistics.csosProperty}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csOutsideStatistics.csosProperty}',{ctrl:{editable:false,visible:true}})">
			 ${csOutsideStatistics.csosProperty$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosProperty}
	</s:if>
	
	
	
	
		
	${lz:set("注释","*****************人员位置字段的输入框代码*****************")}
	${lz:set("注释","before$csosOpenDoorCount和after$csosOpenDoorCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosOpenDoorCount==true">
	${before$csosOpenDoorCount}
	<dl class="csosOpenDoorCount " major  ref="csosOpenDoorCount" >
		<dt>开门次数:</dt>
		<s:if test="#request.CTRL.e.csosOpenDoorCount==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutsideStatistics$csosOpenDoorCount!=null">${csOutsideStatistics$csosOpenDoorCount}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutsideStatistics.csosOpenDoorCount" id="csosOpenDoorCount"  value="${csOutsideStatistics.csosOpenDoorCount}"/>
	 	 </s:else>
	 	
	 	 <b>*</b>
	 	 <em>请输入巡检作业的人员位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人员位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosOpenDoorCount">${csOutsideStatistics.csosOpenDoorCount}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosOpenDoorCount$}
	 	 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosOpenDoorCount}
	</s:if>
	
	
	
	
	
	
	
	
	
	
	${lz:set("注释","*****************人员位置字段的输入框代码*****************")}
	${lz:set("注释","before$csosCloseDoorCount和after$csosCloseDoorCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosCloseDoorCount==true">
	${before$csosCloseDoorCount}
	<dl class="csosCloseDoorCount " major  ref="csosCloseDoorCount" >
		<dt>关门次数:</dt>
		<s:if test="#request.CTRL.e.csosCloseDoorCount==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutsideStatistics$csosCloseDoorCount!=null">${csOutsideStatistics$csosCloseDoorCount}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutsideStatistics.csosCloseDoorCount" id="csosCloseDoorCount"  value="${csOutsideStatistics.csosCloseDoorCount}"/>
	 	 </s:else>
	 	 <b>*</b>
	 	 <em>请输入巡检作业的人员位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人员位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosCloseDoorCount">${csOutsideStatistics.csosCloseDoorCount}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosCloseDoorCount$}
	 	
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosCloseDoorCount}
	</s:if>
	
	
	
	
	
	
	
	
	
	
	${lz:set("注释","*****************人员位置字段的输入框代码*****************")}
	${lz:set("注释","before$csosStartCount和after$csosStartCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosStartCount==true">
	${before$csosStartCount}
	<dl class="csosStartCount " major  ref="csosStartCount" >
		<dt>启动次数:</dt>
		<s:if test="#request.CTRL.e.csosStartCount==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutsideStatistics$csosStartCount!=null">${csOutsideStatistics$csosStartCount}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutsideStatistics.csosStartCount" id="csosStartCount"  value="${csOutsideStatistics.csosStartCount}"/>
	 	 </s:else>
	 	 <b>*</b>
	 	 <em>请输入巡检作业的人员位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人员位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosStartCount">${csOutsideStatistics.csosStartCount}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosStartCount$}
	 	
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosStartCount}
	</s:if>
	
	
	
	
	
		
	
	
	
	
	${lz:set("注释","*****************人员位置字段的输入框代码*****************")}
	${lz:set("注释","before$csosWhistleCount和after$csosWhistleCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosWhistleCount==true">
	${before$csosWhistleCount}
	<dl class="csosWhistleCount " major  ref="csosWhistleCount" >
		<dt>鸣笛次数:</dt>
		<s:if test="#request.CTRL.e.csosWhistleCount==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutsideStatistics$csosWhistleCount!=null">${csOutsideStatistics$csosWhistleCount}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutsideStatistics.csosWhistleCount" id="csosWhistleCount"  value="${csOutsideStatistics.csosWhistleCount}"/>
	 	 </s:else>
	 	 <b>*</b>
	 	 <em>请输入巡检作业的人员位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人员位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosWhistleCount">${csOutsideStatistics.csosWhistleCount}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosWhistleCount$}
	 	
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosWhistleCount}
	</s:if>
	
	
	
	
	
	
	
	
	
	
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csosOpenDoorTime和after$csosOpenDoorTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosOpenDoorTime==true">
	${before$csosOpenDoorTime}
	<dl class="csosOpenDoorTime " major  ref="csosOpenDoorTime" >
		<dt>开门时间:</dt>
		<s:if test="#request.CTRL.e.csosOpenDoorTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOutsideStatistics$csosOpenDoorTime!=null">${csOutsideStatistics$csosOpenDoorTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOutsideStatistics.csosOpenDoorTime" id="csosOpenDoorTime"  value="<s:date name="csOutsideStatistics.csosOpenDoorTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择异借确认的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosOpenDoorTime">${csOutsideStatistics.csosOpenDoorTime}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosOpenDoorTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosOpenDoorTime}
	</s:if>
	
	
	
	
	
	
	
	
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csosCloseDoorTime和after$csosCloseDoorTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosCloseDoorTime==true">
	${before$csosCloseDoorTime}
	<dl class="csosCloseDoorTime " major  ref="csosCloseDoorTime" >
		<dt>关门时间:</dt>
		<s:if test="#request.CTRL.e.csosCloseDoorTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOutsideStatistics$csosCloseDoorTime!=null">${csOutsideStatistics$csosCloseDoorTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOutsideStatistics.csosCloseDoorTime" id="csosCloseDoorTime"  value="<s:date name="csOutsideStatistics.csosCloseDoorTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择异借确认的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosCloseDoorTime">${csOutsideStatistics.csosCloseDoorTime}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosCloseDoorTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosCloseDoorTime}
	</s:if>
	
	
	
	
	
	
	
	
	
	
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csosAddTime和after$csosAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosAddTime==true">
	${before$csosAddTime}
	<dl class="csosAddTime " major  ref="csosAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csosAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOutsideStatistics$csosAddTime!=null">${csOutsideStatistics$csosAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOutsideStatistics.csosAddTime" id="csosAddTime"  value="<s:date name="csOutsideStatistics.csosAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择异借确认的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosAddTime">${csOutsideStatistics.csosAddTime}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosAddTime}
	</s:if>
	
	
	
	
	
	
	
	
	
	
	
	
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csosUpdateTime和after$csosUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosUpdateTime==true">
	${before$csosUpdateTime}
	<dl class="csosUpdateTime " major  ref="csosUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csosUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOutsideStatistics$csosUpdateTime!=null">${csOutsideStatistics$csosUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOutsideStatistics.csosUpdateTime" id="csosUpdateTime"  value="<s:date name="csOutsideStatistics.csosUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择异借确认的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosUpdateTime">${csOutsideStatistics.csosUpdateTime}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosUpdateTime}
	</s:if>
	
	
	
	
	
	
	
	
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csosFinishTime和after$csosFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosFinishTime==true">
	${before$csosFinishTime}
	<dl class="csosFinishTime " major  ref="csosFinishTime" >
		<dt>完成时间:</dt>
		<s:if test="#request.CTRL.e.csosFinishTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOutsideStatistics$csosFinishTime!=null">${csOutsideStatistics$csosFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOutsideStatistics.csosFinishTime" id="csosFinishTime"  value="<s:date name="csOutsideStatistics.csosFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择异借确认的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosFinishTime">${csOutsideStatistics.csosFinishTime}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosFinishTime}
	</s:if>
	
	
	
	
	
	
	

	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csosStatus和after$csosStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosStatus==true">
	${before$csosStatus}
	<dl class="csosStatus " major  ref="csosStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csosStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOutsideStatistics$csosStatus!=null">${csOutsideStatistics$csosStatus}</s:if><s:else>
		 	<select class="narrow" id="csosStatus" name="csOutsideStatistics.csosStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csOutsideStatistics.csosStatus==1?"selected":""}>未完成</option>
				<option value="2" ${csOutsideStatistics.csosStatus==2?"selected":""}>已完成</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>未确认表示订单已完成未确认</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosStatus">${csOutsideStatistics.csosStatus}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosStatus}
	
	
	
	
	${lz:set("注释","*****************人员位置字段的输入框代码*****************")}
	${lz:set("注释","before$csosCarLocation和after$csosCarLocation变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosCarLocation==true">
	${before$csosCarLocation}
	<dl class="csosCarLocation " major  ref="csosCarLocation" >
		<dt>车辆位置:</dt>
		<s:if test="#request.CTRL.e.csosCarLocation==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutsideStatistics$csosCarLocation!=null">${csOutsideStatistics$csosCarLocation}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutsideStatistics.csosCarLocation" id="csosCarLocation"  value="${csOutsideStatistics.csosCarLocation}"/>
	 	 </s:else>
	 	 <b>*</b>
	 	 <em>请输入巡检作业的人员位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人员位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosCarLocation">${csOutsideStatistics.csosCarLocation}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosCarLocation$}
	 	
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosCarLocation}
	</s:if>
	
	
	
	
	${lz:set("注释","*****************人员位置字段的输入框代码*****************")}
	${lz:set("注释","before$csosMemberLocation和after$csosMemberLocation变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosMemberLocation==true">
	${before$csosMemberLocation}
	<dl class="csosMemberLocation " major  ref="csosMemberLocation" >
		<dt>人员位置:</dt>
		<s:if test="#request.CTRL.e.csosMemberLocation==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutsideStatistics$csosMemberLocation!=null">${csOutsideStatistics$csosMemberLocation}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutsideStatistics.csosMemberLocation" id="csosMemberLocation"  value="${csOutsideStatistics.csosMemberLocation}"/>
	 	 </s:else>
	 	 <b>*</b>
	 	 <em>请输入巡检作业的人员位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人员位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosMemberLocation">${csOutsideStatistics.csosMemberLocation}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosMemberLocation$}
	 	
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosMemberLocation}
	</s:if>
	
	
	
	
	
	
	
	
	
	
	${lz:set("注释","*****************启动次数字段的输入框代码*****************")}
	${lz:set("注释","before$csosDistance和after$csosDistance变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosDistance==true">
	${before$csosDistance}
	<dl class="csosDistance ${CTRL.e.csosDistance?"hidden":""}" major  ref="csosDistance" >
		<dt>人员距离:</dt>
		<s:if test="#request.CTRL.e.csosDistance==true">
		${lz:set("haveEditable",true)}
		<dd input="hidden">
		<s:if test="#request.csOutsideStatistics$csosDistance!=null">${csOutsideStatistics$csosDistance}</s:if><s:else>
			<input type="hidden" value="${csOutsideStatistics.csosDistance}" name="csOutsideStatistics.csosDistance" id="csosDistance" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosMemberLocation">${csOutsideStatistics.csosDistance}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosDistance$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosDistance}
	</s:if>
	
	
	
	${lz:set("注释","*****************人员位置字段的输入框代码*****************")}
	${lz:set("注释","before$csosWorkTime和after$csosWorkTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csosWorkTime==true">
	${before$csosWorkTime}
	<dl class="csosWorkTime " major  ref="csosWorkTime" >
		<dt>作业时长:</dt>
		<s:if test="#request.CTRL.e.csosWorkTime==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutsideStatistics$csosWorkTime!=null">${csOutsideStatistics$csosWorkTime}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutsideStatistics.csosWorkTime" id="csosWorkTime"  value="${csOutsideStatistics.csosWorkTime}"/>
	 	 </s:else>
	 	
	 	 <b>*</b>
	 	 <em>请输入巡检作业的人员位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人员位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csosWorkTime">${csOutsideStatistics.csosWorkTime}</textarea>
		 		<span>
		 	
			 ${csOutsideStatistics.csosWorkTime$}
	 	 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csosWorkTime}
	</s:if>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csOutsideStatistics.csosId}" name="csOutsideStatistics.csosId" id="csosId" />
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















