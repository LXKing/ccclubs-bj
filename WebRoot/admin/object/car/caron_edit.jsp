<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/car/caron.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/object/car/caron.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCarOn.cscoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscoId,cscoHost,cscoCar,cscoOnUser,cscoOffUser,cscoLong,cscoRemark,cscoStatus,cscoOnRemark,cscoProperty",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscoId,cscoHost,cscoCar,cscoOnUser,cscoOffUser,cscoLong,cscoRemark,cscoStatus,cscoOnRemark,cscoProperty",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscoId,cscoHost,cscoCar,cscoOnUser,cscoOffUser,cscoLong,cscoRemark,cscoStatus,cscoOnRemark,cscoProperty",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscoId,cscoHost,cscoCar,cscoOnUser,cscoOffUser,cscoLong,cscoRemark,cscoStatus,cscoOnRemark,cscoProperty",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车辆上线记录编辑":CTRL.title}</title>
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
	$("#caronForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscoId==true">
		${lz:set("haveEditable",true)}
		,"csCarOn.cscoId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscoHost==true">
		${lz:set("haveEditable",true)}
		,"csCarOn.cscoHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscoCar==true">
		${lz:set("haveEditable",true)}
		,"csCarOn.cscoCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscoOnUser==true">
		${lz:set("haveEditable",true)}
		,"csCarOn.cscoOnUser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscoOffUser==true">
		${lz:set("haveEditable",true)}
		,"csCarOn.cscoOffUser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscoOnTime==true">
		${lz:set("haveEditable",true)}
		,"csCarOn.cscoOnTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscoOffTime==true">
		${lz:set("haveEditable",true)}
		,"csCarOn.cscoOffTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscoLong==true">
		${lz:set("haveEditable",true)}
		,"csCarOn.cscoLong":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscoRemark==true">
		${lz:set("haveEditable",true)}
		,"csCarOn.cscoRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "下线备注最大长度为256个字符";
		}
		</s:if>
		
		<s:if test="#request.CTRL.e.cscoOnRemark==true">
		${lz:set("haveEditable",true)}
		,"csCarOn.cscoOnRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "上线备注最大长度为256个字符";
		}
		</s:if>
		
		<s:if test="#request.CTRL.e.cscoStatus==true">
		${lz:set("haveEditable",true)}
		,"csCarOn.cscoStatus":function(el){
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
	<form class="form editform" ref="CsCarOn" id="caronForm" name="caronForm" action="${empty CTRL.action?"caron_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车辆上线记录相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCarOnToken}" name="csCarOnToken" id="csCarOnToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCarOn.cscoId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscoId和after$cscoId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscoId==true">
	${before$cscoId}
	<dl class="cscoId ${CTRL.e.cscoId?"hidden":""}" major  ref="cscoId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscoId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCarOn$cscoId!=null">${csCarOn$cscoId}</s:if><s:else>
			<input type="hidden" value="${csCarOn.cscoId}" name="csCarOn.cscoId" id="cscoId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscoId">${csCarOn.cscoId}</textarea>
		 		<span>
		 	
			 ${csCarOn.cscoId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscoId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscoHost和after$cscoHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscoHost==true">
	${before$cscoHost}
	<dl class="cscoHost " major  ref="cscoHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscoHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCarOn$cscoHost!=null">${csCarOn$cscoHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscoHost" name="csCarOn.cscoHost">
		 				<option selected value="${csCarOn.cscoHost}">
		 					${get:SrvHost(csCarOn.cscoHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscoHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscoHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscoHost">${csCarOn.cscoHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCarOn.cscoHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCarOn.cscoHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscoHost}
	</s:if>
	
	${lz:set("注释","*****************所属车辆字段的输入框代码*****************")}
	${lz:set("注释","before$cscoCar和after$cscoCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscoCar==true">
	${before$cscoCar}
	<dl class="cscoCar " major  ref="cscoCar" >
		<dt>所属车辆:</dt>
		<s:if test="#request.CTRL.e.cscoCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCarOn$cscoCar!=null">${csCarOn$cscoCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cscoHost}" type="text" id="cscoCar" name="csCarOn.cscoCar" text="${get:CsCar(csCarOn.cscoCar).cscNumber}" value="${csCarOn.cscoCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscoCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#cscoCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscoCar">${csCarOn.cscoCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csCarOn.cscoCar}',{ctrl:{editable:false,visible:true}})">
			 ${csCarOn.cscoCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscoCar}
	</s:if>
	
	${lz:set("注释","*****************上线操作人字段的输入框代码*****************")}
	${lz:set("注释","before$cscoOnUser和after$cscoOnUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscoOnUser==true">
	${before$cscoOnUser}
	<dl class="cscoOnUser " major  ref="cscoOnUser" >
		<dt>上线操作人:</dt>
		<s:if test="#request.CTRL.e.cscoOnUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCarOn$cscoOnUser!=null">${csCarOn$cscoOnUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cscoOnUser" name="csCarOn.cscoOnUser">
		 				<option selected value="${csCarOn.cscoOnUser}">
		 					${get:SrvUser(csCarOn.cscoOnUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscoOnUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cscoOnUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上线操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscoOnUser">${csCarOn.cscoOnUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csCarOn.cscoOnUser}',{ctrl:{editable:false,visible:true}})">
			 ${csCarOn.cscoOnUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscoOnUser}
	</s:if>
	
	${lz:set("注释","*****************下线操作人字段的输入框代码*****************")}
	${lz:set("注释","before$cscoOffUser和after$cscoOffUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscoOffUser==true">
	${before$cscoOffUser}
	<dl class="cscoOffUser " major  ref="cscoOffUser" >
		<dt>下线操作人:</dt>
		<s:if test="#request.CTRL.e.cscoOffUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCarOn$cscoOffUser!=null">${csCarOn$cscoOffUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cscoOffUser" name="csCarOn.cscoOffUser">
		 				<option selected value="${csCarOn.cscoOffUser}">
		 					${get:SrvUser(csCarOn.cscoOffUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscoOffUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cscoOffUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****下线操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscoOffUser">${csCarOn.cscoOffUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csCarOn.cscoOffUser}',{ctrl:{editable:false,visible:true}})">
			 ${csCarOn.cscoOffUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscoOffUser}
	</s:if>
	
	${lz:set("注释","*****************上线时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscoOnTime和after$cscoOnTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscoOnTime==true">
	${before$cscoOnTime}
	<dl class="cscoOnTime " major  ref="cscoOnTime" >
		<dt>上线时间:</dt>
		<s:if test="#request.CTRL.e.cscoOnTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCarOn$cscoOnTime!=null">${csCarOn$cscoOnTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCarOn.cscoOnTime" id="cscoOnTime"  value="<s:date name="csCarOn.cscoOnTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆上线记录的上线时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上线时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscoOnTime">${csCarOn.cscoOnTime}</textarea>
		 		<span>
		 	
			 ${csCarOn.cscoOnTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscoOnTime}
	</s:if>
	
	${lz:set("注释","*****************下线时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscoOffTime和after$cscoOffTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscoOffTime==true">
	${before$cscoOffTime}
	<dl class="cscoOffTime " major  ref="cscoOffTime" >
		<dt>下线时间:</dt>
		<s:if test="#request.CTRL.e.cscoOffTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCarOn$cscoOffTime!=null">${csCarOn$cscoOffTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCarOn.cscoOffTime" id="cscoOffTime"  value="<s:date name="csCarOn.cscoOffTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆上线记录的下线时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****下线时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscoOffTime">${csCarOn.cscoOffTime}</textarea>
		 		<span>
		 	
			 ${csCarOn.cscoOffTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscoOffTime}
	</s:if>
	
	${lz:set("注释","*****************运营时长字段的输入框代码*****************")}
	${lz:set("注释","before$cscoLong和after$cscoLong变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscoLong==true">
	${before$cscoLong}
	<dl class="cscoLong " major  ref="cscoLong" >
		<dt>运营时长:</dt>
		<s:if test="#request.CTRL.e.cscoLong==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csCarOn$cscoLong!=null">${csCarOn$cscoLong}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCarOn.cscoLong" id="cscoLong"  value="${csCarOn.cscoLong}"/>
	 	 </s:else>
	 	 分钟
	 	 
	 	 <em>请输入车辆上线记录的运营时长</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****运营时长字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscoLong">${csCarOn.cscoLong}</textarea>
		 		<span>
		 	
			 ${csCarOn.cscoLong$}
	 	 分钟 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscoLong}
	</s:if>
	
	
	${lz:set("注释","*****************上线备注字段的输入框代码*****************")}
	${lz:set("注释","before$cscoOnRemark和after$cscoOnRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscoOnRemark==true">
	${before$cscoOnRemark}
	<dl class="cscoOnRemark " major  ref="cscoOnRemark" style="width:98%;">
		<dt>上线备注:</dt>
		<s:if test="#request.CTRL.e.cscoOnRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCarOn$cscoOnRemark!=null">${csCarOn$cscoOnRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cscoOnRemark" name="csCarOn.cscoOnRemark" rows="5">${csCarOn.cscoOnRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆上线记录的上线备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****下线备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCarOn.cscoOnRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscoOnRemark}
	</s:if>
	
	
	
	${lz:set("注释","*****************下线备注字段的输入框代码*****************")}
	${lz:set("注释","before$cscoRemark和after$cscoRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscoRemark==true">
	${before$cscoRemark}
	<dl class="cscoRemark " major  ref="cscoRemark" style="width:98%;">
		<dt>下线备注:</dt>
		<s:if test="#request.CTRL.e.cscoRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCarOn$cscoRemark!=null">${csCarOn$cscoRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cscoRemark" name="csCarOn.cscoRemark" rows="5">${csCarOn.cscoRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆下线记录的下线备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****下线备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCarOn.cscoRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscoRemark}
	</s:if>
	
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscoOffFlag和after$cscoOffFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscoProperty==true">
	${before$cscoProperty}
	<dl class="cscoProperty " major  ref="cscoProperty" >
		<dt>下线标签:</dt>
		<s:if test="#request.CTRL.e.cscoProperty==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCarOn$cscoProperty!=null">${csCarOn$cscoProperty}</s:if><s:else>
		 	<select class="narrow" id="cscoProperty" name="csCarOn.cscoProperty">
		 		<option value="">请选择</option>
 					${lz:set("items", lz:query("ccclubs_system","SELECT * from srv_property p where EXISTS (SELECT sp_flag from srv_property where p.sp_parent = sp_id and sp_flag = 'CAR_OFF_LINE_LABEL' and sp_parent = 0 )") )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csCarOn.cscoProperty==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscoProperty').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#cscoProperty').val(),{ctrl:{editable:false}})"></a>
	 	 	
	 	 </s:else>
	 	 
	 	 <b></b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscoProperty">${csCarOn.cscoProperty}</textarea>
		 		<span>
		 	
			 ${csCarOn.cscoProperty$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscoProperty}
	</s:if>
	
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscoStatus和after$cscoStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscoStatus==true">
	${before$cscoStatus}
	<dl class="cscoStatus " major  ref="cscoStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscoStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCarOn$cscoStatus!=null">${csCarOn$cscoStatus}</s:if><s:else>
		 	<select class="narrow" id="cscoStatus" name="csCarOn.cscoStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csCarOn.cscoStatus==0?"selected":""}>上线中</option>
				<option value="1" ${csCarOn.cscoStatus==1?"selected":""}>已完成</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆上线记录的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscoStatus">${csCarOn.cscoStatus}</textarea>
		 		<span>
		 	
			 ${csCarOn.cscoStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscoStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCarOn.cscoId}" name="csCarOn.cscoId" id="cscoId" />
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