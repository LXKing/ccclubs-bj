<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/control/remote.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/control/remote.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csRemote.csrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csrHost,csrCar,csrWay,csrType,csrRemark",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrHost,csrCar,csrWay,csrType,csrRemark",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csrHost,csrWay",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrHost,csrWay",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"远程控制编辑":CTRL.title}</title>
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
<lz:set name="csrType1">
<select class="narrow" id="csrType" name="csRemote.csrType">
	<option value="">请选择</option>
	<option value="0" ${csRemote.csrType==0?"selected":""}>远程开门</option>
	<option value="1" ${csRemote.csrType==1?"selected":""}>远程关门</option>
	<option value="2" ${csRemote.csrType==2?"selected":""}>允许启动</option>
</select>
</lz:set>
<script type="text/javascript">
<!--
$(function(){
	var types={};
	var dd = $("#csrType").parent();
	types["0"]=$("#csrType");
	types["1"] = $("${lz:js(csrType1)}");
	$("#csrWay").change(function(){
		types["0"].remove();
		types["1"].remove();
		types[""+$(this).val()].prependTo(dd);
	});
	
	$(".form").submit(function(e){
		if($("#csrWay").val()=="1" && $("#csrCar").val()!=""){
			var car = $.api("com.ccclubs.model.CsCar","get")($("#csrCar").val());
			if(car.cscDeviceVesion!=1){
				$("#csrWay").say("对不起，当前车载设备不支持远程短信控制");
				return false;
			}
			if($.trim(car.cscMobile)==""){
				$("#csrWay").say("对不起，当前车载设备未配置手机号码");
				return false;
			}
			var orderCount = $.api("com.ccclubs.model.CsOrder","count")({
				csoCar:$("#csrCar").val(),
				csoStartTimeEnd:$.formatDate("yyyy-MM-dd HH:mm:ss",new Date()),
				csoFinishTimeStart:$.formatDate("yyyy-MM-dd HH:mm:ss",new Date())
			});
			if(orderCount==0){
				$("#csrWay").say("对不起，当前车辆没有订单，不能以短信方式远程控制");
				return false;
			}
		}
		if($("#csrType").val()=="4"){
			var orderCount = $.api("com.ccclubs.model.CsOrder","count")({
				csoCar:$("#csrCar").val(),
				csoStartTimeEnd:$.formatDate("yyyy-MM-dd HH:mm:ss",new Date()),
				csoFinishTimeStart:$.formatDate("yyyy-MM-dd HH:mm:ss",new Date())
			});
			if(orderCount>0){
				if(!confirm("当前车辆有可能正在使用，确定要初始化吗?")){
					return false;
				}
			}
		}
	});
});
//-->
</script>
<lz:set name="csRemote$csrType">
	<select class="narrow" id="csrType" name="csRemote.csrType">
		<option value="">请选择</option>
		<option value="0" ${csRemote.csrType==0?"selected":""}>远程开门</option>
		<option value="1" ${csRemote.csrType==1?"selected":""}>远程关门</option>
		<option value="2" ${csRemote.csrType==2?"selected":""}>允许启动</option>
		<option value="3" ${csRemote.csrType==3?"selected":""}>断油电</option>
		<option value="4" ${csRemote.csrType==4?"selected":""}>初始化</option>
		<option value="5" ${csRemote.csrType==5?"selected":""}>同步时钟</option>
		<option value="6" ${csRemote.csrType==6?"selected":""}>远程取车</option>
		<option value="7" ${csRemote.csrType==7?"selected":""}>远程还车</option>
		<s:if test="#request.canExp[0]==true && #request.CTRL.canExp[0]==true">
		<option value="8" ${csRemote.csrType==8?"selected":""}>远程升级</option>
		</s:if>
		<option value="9" ${csRemote.csrType==9?"selected":""}>远程重启</option>
		<option value="10" ${csRemote.csrType==10?"selected":""}>GPS远程开</option>
		<option value="11" ${csRemote.csrType==11?"selected":""}>GPS远程关</option>
		<option value="12" ${csRemote.csrType==12?"selected":""}>读取RFID序列号</option>
		<option value="13" ${csRemote.csrType==13?"selected":""}>设置GPS坐标</option>
		<option value="14" ${csRemote.csrType==14?"selected":""}>查询手机号</option>
		<option value="15" ${csRemote.csrType==15?"selected":""}>刷卡器开</option>
		<option value="16" ${csRemote.csrType==16?"selected":""}>刷卡器关</option>
		<option value="17" ${csRemote.csrType==17?"selected":""}>远程鸣笛</option>
	</select>
</lz:set>
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
	$("#remoteForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csrId==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrHost==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrNumber==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrNumber":function(el){
			if(jQuery.trim(el.value)=="")
				return "车牌号码不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车牌号码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrCar==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrWay==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrWay":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择数据方式";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrType==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择控制类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrCode==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrCode":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "控制串码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrEditor==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrRemark==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrRemark":function(el){
			if(jQuery.trim(el.value)=="")
				return "备注不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrUpdateTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrAddTime==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrState==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrState":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择数据发送状态";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrStatus==true">
		${lz:set("haveEditable",true)}
		,"csRemote.csrStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择操作结果";
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
	<form class="form editform" ref="CsRemote" id="remoteForm" name="remoteForm" action="${empty CTRL.action?"remote_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写远程控制相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csRemoteToken}" name="csRemoteToken" id="csRemoteToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csRemote.csrId))}		
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
		<s:if test="#request.csRemote$csrId!=null">${csRemote$csrId}</s:if><s:else>
			<input type="hidden" value="${csRemote.csrId}" name="csRemote.csrId" id="csrId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrId">${csRemote.csrId}</textarea>
		 		<span>
		 	
			 ${csRemote.csrId$}
	 	  
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
		<s:if test="#request.csRemote$csrHost!=null">${csRemote$csrHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csrHost" name="csRemote.csrHost">
		 				<option selected value="${csRemote.csrHost}">
		 					${get:SrvHost(csRemote.csrHost).shName}
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
		 		<textarea class="" style="display:none;" id="csrHost">${csRemote.csrHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csRemote.csrHost}',{ctrl:{editable:false,visible:true}})">
			 ${csRemote.csrHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrHost}
	</s:if>
	
	${lz:set("注释","*****************车牌号码字段的输入框代码*****************")}
	${lz:set("注释","before$csrNumber和after$csrNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrNumber==true">
	${before$csrNumber}
	<dl class="csrNumber " major  ref="csrNumber" >
		<dt>车牌号码:</dt>
		<s:if test="#request.CTRL.e.csrNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRemote$csrNumber!=null">${csRemote$csrNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csRemote.csrNumber" id="csrNumber"  value="${csRemote.csrNumber}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入远程控制的车牌号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrNumber">${csRemote.csrNumber}</textarea>
		 		<span>
		 	
			 ${csRemote.csrNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrNumber}
	</s:if>
	
	${lz:set("注释","*****************控制车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csrCar和after$csrCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrCar==true">
	${before$csrCar}
	<dl class="csrCar " major  ref="csrCar" >
		<dt>控制车辆:</dt>
		<s:if test="#request.CTRL.e.csrCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csRemote$csrCar!=null">${csRemote$csrCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csrHost}" type="text" id="csrCar" name="csRemote.csrCar" text="${get:CsCar(csRemote.csrCar).cscNumber}" value="${csRemote.csrCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#csrCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****控制车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrCar">${csRemote.csrCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csRemote.csrCar}',{ctrl:{editable:false,visible:true}})">
			 ${csRemote.csrCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrCar}
	</s:if>
	
	${lz:set("注释","*****************数据方式字段的输入框代码*****************")}
	${lz:set("注释","before$csrWay和after$csrWay变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrWay==true">
	${before$csrWay}
	<dl class="csrWay " major  ref="csrWay" >
		<dt>数据方式:</dt>
		<s:if test="#request.CTRL.e.csrWay==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRemote$csrWay!=null">${csRemote$csrWay}</s:if><s:else>
		 	<select class="narrow" id="csrWay" name="csRemote.csrWay">
		 		<option value="">请选择</option>
				<option value="0" ${csRemote.csrWay==0?"selected":""}>网关</option>
				<option value="1" ${csRemote.csrWay==1?"selected":""}>短信</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>断线的车辆建议用短信方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****数据方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrWay">${csRemote.csrWay}</textarea>
		 		<span>
		 	
			 ${csRemote.csrWay$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrWay}
	</s:if>
	
	${lz:set("注释","*****************控制类型字段的输入框代码*****************")}
	${lz:set("注释","before$csrType和after$csrType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrType==true">
	${before$csrType}
	<dl class="csrType " major  ref="csrType" >
		<dt>控制类型:</dt>
		<s:if test="#request.CTRL.e.csrType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRemote$csrType!=null">${csRemote$csrType}</s:if><s:else>
		 	<select class="narrow" id="csrType" name="csRemote.csrType">
		 		<option value="">请选择</option>
				<option value="0" ${csRemote.csrType==0?"selected":""}>远程开门</option>
				<option value="1" ${csRemote.csrType==1?"selected":""}>远程关门</option>
				<option value="2" ${csRemote.csrType==2?"selected":""}>允许启动</option>
				<option value="3" ${csRemote.csrType==3?"selected":""}>断油电</option>
				<option value="4" ${csRemote.csrType==4?"selected":""}>初始化</option>
				<option value="5" ${csRemote.csrType==5?"selected":""}>同步时钟</option>
				<option value="6" ${csRemote.csrType==6?"selected":""}>远程取车</option>
				<option value="7" ${csRemote.csrType==7?"selected":""}>远程还车</option>
				<option value="8" ${csRemote.csrType==8?"selected":""}>远程升级</option>
				<option value="9" ${csRemote.csrType==9?"selected":""}>远程重启</option>
				<option value="10" ${csRemote.csrType==10?"selected":""}>GPS远程开</option>
				<option value="11" ${csRemote.csrType==11?"selected":""}>GPS远程关</option>
				<option value="12" ${csRemote.csrType==12?"selected":""}>读取RFID序列号</option>
				<option value="13" ${csRemote.csrType==13?"selected":""}>设置GPS坐标</option>
				<option value="14" ${csRemote.csrType==14?"selected":""}>查询手机号</option>
				<option value="15" ${csRemote.csrType==15?"selected":""}>刷卡器开</option>
				<option value="16" ${csRemote.csrType==16?"selected":""}>刷卡器关</option>
				<option value="17" ${csRemote.csrType==17?"selected":""}>远程鸣笛</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择远程控制的控制类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****控制类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrType">${csRemote.csrType}</textarea>
		 		<span>
		 	
			 ${csRemote.csrType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrType}
	</s:if>
	
	${lz:set("注释","*****************控制串码字段的输入框代码*****************")}
	${lz:set("注释","before$csrCode和after$csrCode变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrCode==true">
	${before$csrCode}
	<dl class="csrCode " minor  ref="csrCode" >
		<dt>控制串码:</dt>
		<s:if test="#request.CTRL.e.csrCode==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRemote$csrCode!=null">${csRemote$csrCode}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csRemote.csrCode" id="csrCode"  value="${csRemote.csrCode}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入远程控制的控制串码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****控制串码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrCode">${csRemote.csrCode}</textarea>
		 		<span>
		 	
			 ${csRemote.csrCode$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrCode}
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
		<s:if test="#request.csRemote$csrEditor!=null">${csRemote$csrEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csrEditor" name="csRemote.csrEditor">
		 				<option selected value="${csRemote.csrEditor}">
		 					${get:SrvUser(csRemote.csrEditor).suRealName}
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
		 		<textarea class="" style="display:none;" id="csrEditor">${csRemote.csrEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csRemote.csrEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csRemote.csrEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrEditor}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csrRemark和after$csrRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrRemark==true">
	${before$csrRemark}
	<dl class="csrRemark " major  ref="csrRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csrRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csRemote$csrRemark!=null">${csRemote$csrRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csrRemark" name="csRemote.csrRemark" rows="5">${csRemote.csrRemark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入远程控制的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csRemote.csrRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrRemark}
	</s:if>
	
	${lz:set("注释","*****************操作成功时间字段的输入框代码*****************")}
	${lz:set("注释","before$csrUpdateTime和after$csrUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrUpdateTime==true">
	${before$csrUpdateTime}
	<dl class="csrUpdateTime " major  ref="csrUpdateTime" >
		<dt>操作成功时间:</dt>
		<s:if test="#request.CTRL.e.csrUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csRemote$csrUpdateTime!=null">${csRemote$csrUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csRemote.csrUpdateTime" id="csrUpdateTime"  value="<s:date name="csRemote.csrUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择远程控制的操作成功时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作成功时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrUpdateTime">${csRemote.csrUpdateTime}</textarea>
		 		<span>
		 	
			 ${csRemote.csrUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrUpdateTime}
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
		<s:if test="#request.csRemote$csrAddTime!=null">${csRemote$csrAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csRemote.csrAddTime" id="csrAddTime"  value="<s:date name="csRemote.csrAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择远程控制的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrAddTime">${csRemote.csrAddTime}</textarea>
		 		<span>
		 	
			 ${csRemote.csrAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrAddTime}
	</s:if>
	
	${lz:set("注释","*****************数据发送状态字段的输入框代码*****************")}
	${lz:set("注释","before$csrState和after$csrState变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrState==true">
	${before$csrState}
	<dl class="csrState " major  ref="csrState" >
		<dt>数据发送状态:</dt>
		<s:if test="#request.CTRL.e.csrState==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRemote$csrState!=null">${csRemote$csrState}</s:if><s:else>
		 	<select class="narrow" id="csrState" name="csRemote.csrState">
		 		<option value="">请选择</option>
				<option value="0" ${csRemote.csrState==0?"selected":""}>未发送</option>
				<option value="1" ${csRemote.csrState==1?"selected":""}>已发送</option>
				<option value="2" ${csRemote.csrState==2?"selected":""}>组装中</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择远程控制的数据发送状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****数据发送状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrState">${csRemote.csrState}</textarea>
		 		<span>
		 	
			 ${csRemote.csrState$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrState}
	</s:if>
	
	${lz:set("注释","*****************操作结果字段的输入框代码*****************")}
	${lz:set("注释","before$csrStatus和after$csrStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrStatus==true">
	${before$csrStatus}
	<dl class="csrStatus " major  ref="csrStatus" >
		<dt>操作结果:</dt>
		<s:if test="#request.CTRL.e.csrStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRemote$csrStatus!=null">${csRemote$csrStatus}</s:if><s:else>
		 	<select class="narrow" id="csrStatus" name="csRemote.csrStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csRemote.csrStatus==0?"selected":""}>未知</option>
				<option value="1" ${csRemote.csrStatus==1?"selected":""}>操作成功</option>
				<option value="2" ${csRemote.csrStatus==2?"selected":""}>操作失败</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择远程控制的操作结果</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作结果字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrStatus">${csRemote.csrStatus}</textarea>
		 		<span>
		 	
			 ${csRemote.csrStatus$}
	 	  
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
					<input type="hidden" value="${csRemote.csrId}" name="csRemote.csrId" id="csrId" />
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