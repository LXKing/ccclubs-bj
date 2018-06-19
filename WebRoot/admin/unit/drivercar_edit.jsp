<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/drivercar.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/drivercar.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csDriverCar.csdcId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csdcId,csdcHost,csdcOutlets,csdcDriver,csdcCar,csdcState,csdcStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csdcId,csdcHost,csdcOutlets,csdcDriver,csdcCar,csdcState,csdcStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csdcId,csdcHost,csdcOutlets,csdcDriver,csdcCar,csdcState,csdcStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csdcId,csdcHost,csdcOutlets,csdcDriver,csdcCar,csdcState,csdcStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"司机专车编辑":CTRL.title}</title>
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
	$("#drivercarForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csdcId==true">
		${lz:set("haveEditable",true)}
		,"csDriverCar.csdcId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csdcHost==true">
		${lz:set("haveEditable",true)}
		,"csDriverCar.csdcHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csdcOutlets==true">
		${lz:set("haveEditable",true)}
		,"csDriverCar.csdcOutlets":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择网点";
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
	<form class="form editform" ref="csDriverCar" id="drivercarForm" name="drivercarForm" action="${empty CTRL.action?"drivercar_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写司机人员相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csDriverCarToken}" name="csDriverCarToken" id="csDriverCarToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csDriverCar.csdcId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csdcId和after$csdcId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdcId==true">
	${before$csdcId}
	<dl class="csdcId ${CTRL.e.csdcId?"hidden":""}" major  ref="csdcId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csdcId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csDriverCar$csdcId!=null">${csDriverCar$csdcId}</s:if><s:else>
			<input type="hidden" value="${csDriverCar.csdcId}" name="csDriverCar.csdcId" id="csdcId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdcId">${csDriverCar.csdcId}</textarea>
		 		<span>
		 	
			 ${csDriverCar.csdcId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdcId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csdcHost和after$csdcHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdcHost==true">
	${before$csdcHost}
	<dl class="csdcHost " major  ref="csdcHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csdcHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csDriverCar$csdcHost!=null">${csDriverCar$csdcHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csdcHost" name="csDriverCar.csdcHost">
		 				<option selected value="${csDriverCar.csdcHost}">
		 					${get:SrvHost(csDriverCar.csdcHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csdcHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csdcHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择城市</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdcHost">${csDriverCar.csdcHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csDriverCar.csdcHost}',{ctrl:{editable:false,visible:true}})">
			 ${csDriverCar.csdcHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdcHost}
	</s:if>
	
	${lz:set("注释","*****************所在网点字段的输入框代码*****************")}
	${lz:set("注释","before$csdcOutlets和after$csdcOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdcOutlets==true">
	${before$csdcOutlets}
	<dl class="csdcOutlets " major  ref="csdcOutlets" >
		<dt>所在网点:</dt>
		<s:if test="#request.CTRL.e.csdcOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="tree">
		<s:if test="#request.csDriverCar$csdcOutlets!=null">${csDriverCar$csdcOutlets}</s:if><s:else>
  			<select class="combox narrow"  id="csdcOutlets" name="csDriverCar.csdcOutlets" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&csoHost={csdcHost}">
				<option value="${csDriverCar.csdcOutlets}">
						${get:CsOutlets(csDriverCar.csdcOutlets).csoName}
				</option>
			</select>		 			
			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csdcOutlets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#csdcOutlets').val(),{ctrl:{editable:false}})"></a>
								  		
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择所在网点</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所在网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdcOutlets">${csDriverCar.csdcOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csDriverCar.csdcOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csDriverCar.csdcOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdcOutlets}
	</s:if>
	
	
	${lz:set("注释","*****************司机字段的输入框代码*****************")}
	${lz:set("注释","before$csdcDriver和after$csdcDriver变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdcDriver==true">
	${before$csdcDriver}
	<dl class="csdcDriver " major  ref="csdcDriver" >
		<dt>司机信息:</dt>
		<s:if test="#request.CTRL.e.csdcDriver==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csDriverCar$csdcDriver!=null">${csDriverCar$csdcDriver}</s:if><s:else>
	 			<input title="请输入司机手机号进行查询" class="combox narrow" action="${basePath}${proname}/unit/driver_query_driver.do?value={param}&csdOutlets={csdcOutlets}" type="text" id="csdcDriver" name="csDriverCar.csdcDriver" text="${csDriverCar.$csdcDriver.csdName }" value="${csDriverCar.csdcDriver}" />
				<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csdcDriver').val())==''){return;};window.href('${basePath}${proname}/unit/driver_details.do?key='+$('#csdcDriver').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>选择司机信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****司机字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdcDriver">${csDriverCar.csdcDriver}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/driver_details.do?key=${csDriverCar.csdcDriver}',{ctrl:{editable:false,visible:true}})">
			 ${csDriverCar.$csdcDriver.csdName}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdcDriver}
	</s:if>
	
	
	${lz:set("注释","*****************车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csdcCar和after$csdcCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdcCar==true">
	${before$csdcDriver}
	<dl class="csdcCar " major  ref="csdcCar" >
		<dt>专车信息:</dt>
		<s:if test="#request.CTRL.e.csdcCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csDriverCar$csdcCar!=null">${csDriverCar$csdcCar}</s:if><s:else>
 			<input title="请输入车牌号进行查询" class="combox narrow" action="${basePath}${proname}/unit/carres_query_carres.do?value={param}&cscrOutlets={csdcOutlets}" type="text" id="csdcCar" name="csDriverCar.csdcCar" text="${csDriverCar.$csdcCar.cscrCarNo }" value="${csDriverCar.csdcCar}" />
 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csdcCar').val())==''){return;};window.href('${basePath}${proname}/unit/carres_details.do?key='+$('#csdcCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>选择专车信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属单位字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdcDriver">${csDriverCar.csdcCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/carres_details.do?key=${csDriverCar.csdcCar}',{ctrl:{editable:false,visible:true}})">
			 ${csDriverCar.$csdcCar.cscrCarNo}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdcDriver}
	</s:if>
	
	
	
	
	${lz:set("注释","*****************业务状态的输入框代码*****************")}
	${lz:set("注释","before$csdcState和after$csdcState变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdcState==true">
	${before$csdcState}
	<dl class="csdcState " major  ref="csdcState" >
		<dt>业务状态:</dt>
		<s:if test="#request.CTRL.e.csdcState==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csdcState$csdcState!=null">${csDriverCar$csdcState}</s:if><s:else>
	 	 	<select  class="narrow"  id="csdcState" name="csDriverCar.csdcState" >
					<option value="">请选择</option>
					<option value="2" ${csDriverCar.csdcState==2?"selected":""}>离线</option>
					<option value="1" ${csDriverCar.csdcState==1?"selected":""}>已接单</option>
					<option value="0" ${csDriverCar.csdcState==0?"selected":""}>出车</option>
	 		</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>业务状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****业务状态非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdcState"><s:if test="#csDriverCar.csdcState==1">出车</s:if><s:else>空闲</s:else></textarea>
		 		<span>
		 	
			<s:if test="csDriverCar.csdcState==1">已接单</s:if>
			<s:if test="csDriverCar.csdcState==2">离线</s:if>
			<s:if test="csDriverCar.csdcState==0">出车</s:if>
	 	  	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdcState}
	</s:if>
	
	${lz:set("注释","****************数据状态的输入框代码*****************")}
	${lz:set("注释","before$csdcStatus和after$csdcStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdcStatus==true">
	${before$csdcStatus}
	<dl class="csdcStatus " major  ref="csdcStatus" >
		<dt>数据状态:</dt>
		<s:if test="#request.CTRL.e.csdcStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csdcStatus$csdcStatus!=null">${csDriverCar$csdcStatus}</s:if><s:else>
	 	 	<select class="narrow" id="csdcStatus" name="csDriverCar.csdcStatus" >
	 	 			<option value="">请选择</option>
					<option value="1" ${csDriverCar.csdcStatus==1?"selected":""}>正常</option>
					<option value="0" ${csDriverCar.csdcStatus==0?"selected":""}>禁用</option>
	 		</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>数据状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****数据状态非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdcStatus"><s:if test="#csDriverCar.csdcStatus==1">正常</s:if><s:else>禁用</s:else></textarea>
		 		<span>
			<s:if test="csDriverCar.csdcStatus==1">正常</s:if><s:else>禁用</s:else>
	 	  	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdcStatus}
	</s:if>
	
	
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csdcUpdateTime和after$csdcUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdcUpdateTime==true">
	${before$csdcUpdateTime}
	<dl class="csdcUpdateTime " major  ref="csdcUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csdcUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csDriverCar$csdcUpdateTime!=null">${csDriverCar$csdcUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csDriverCar.csdcUpdateTime" id="csdcUpdateTime"  value="<s:date name="csDriverCar.csdcUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>司机人员的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdcUpdateTime">${csDriverCar.csdcUpdateTime}</textarea>
		 		<span>
		 	
			 ${csDriverCar.csdcUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdcUpdateTime}
	</s:if>
	
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csdcAddTime和after$csdcAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdcAddTime==true">
	${before$csdcAddTime}
	<dl class="csdcAddTime " major  ref="csdcAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csdcAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csDriverCar$csdcAddTime!=null">${csDriverCar$csdcAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csDriverCar.csdcAddTime" id="csdcAddTime"  value="<s:date name="csDriverCar.csdcAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>司机人员的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdcAddTime">${csDriverCar.csdcAddTime}</textarea>
		 		<span>
		 	
			 ${csDriverCar.csdcAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdcAddTime}
	</s:if>
	
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csDriverCar.csdcId}" name="csDriverCar.csdcId" id="csdcId" />
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