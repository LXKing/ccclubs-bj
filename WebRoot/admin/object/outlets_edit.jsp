<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/outlets.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/object/outlets.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOutlets.csoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csoId,csoHost,csoName,csoArea,csoType,csoLongitude,csoLatitude,csoCars,csoAddress,csoSpace,csoDetails,csoDoorTime,csoCost,csoPerson,csoContact,csoUntil,csoExpire,csoPileTime,csoCarS,csoPositionS,csoImage,csoCanA,csoCanB,csoCanReg,csoCanOrder,csoPriority,csoVoteS,csoProfile,csoOnlineTime,csoStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csoId,csoHost,csoName,csoArea,csoType,csoLongitude,csoLatitude,csoCars,csoAddress,csoSpace,csoDetails,csoDoorTime,csoCost,csoPerson,csoContact,csoUntil,csoExpire,csoPileTime,csoCarS,csoPositionS,csoImage,csoCanA,csoCanB,csoCanReg,csoCanOrder,csoPriority,csoVoteS,csoProfile,csoOnlineTime,csoStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csoId,csoHost,csoName,csoArea,csoType,csoLongitude,csoLatitude,csoCars,csoAddress,csoSpace,csoDetails,csoDoorTime,csoCost,csoPerson,csoContact,csoUntil,csoExpire,csoPileTime,csoCarS,csoPositionS,csoImage,csoCanA,csoCanB,csoCanReg,csoCanOrder,csoPriority,csoVoteS,csoProfile,csoOnlineTime,csoStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csoId,csoHost,csoName,csoArea,csoType,csoLongitude,csoLatitude,csoCars,csoAddress,csoSpace,csoDetails,csoDoorTime,csoCost,csoPerson,csoContact,csoUntil,csoExpire,csoPileTime,csoCarS,csoPositionS,csoImage,csoCanA,csoCanB,csoCanReg,csoCanOrder,csoPriority,csoVoteS,csoProfile,csoOnlineTime,csoAddTime,csoStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"网点编辑":CTRL.title}</title>
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
<%@include file="/admin/map.point.qq.jsp"%>
<script>
$(function(){
	if(!window["reginit"]){
		window["reginit"] = true;
		$("#csoLongitude,#csoLatitude").click(function(){	
			$.getMapPoint(function(Latitude,Longitude){
				$("#csoLongitude").val(Longitude);
				$("#csoLatitude").val(Latitude);
			},{
				Latitude:$("#csoLatitude").val(),
				Longitude:$("#csoLongitude").val(),
				radius:$("#range").val()
			});
		});
	}
});
</script>

<lz:set name="after$csoCars">
<s:if test="csOutlets.csoId!=null">
<dl class="" minor  ref="csoCars" style="width:98%;">
	<dt>异借列表:</dt>
	<dd>
		<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="affirms" name="affirms" frameborder='0' src="" url="${basePath}${proname}/oa/outside/affirm.do?inFrame=true&canQuery=false&fields=tbaaCar,tbaaOrder,tbaaOut,tbaaTime,tbaaStatus&tbaaRet=${csOutlets.csoId}&tbaaStatus=2&tbaaStatusNot=yes&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
		<div class="iframe-list">
			<a class="button" style="float:right;" onclick="$('#csoCars').attr('src',$('#csoCars').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
			<div class="line" style="margin:0px;clear:none;width:450px;"></div>
		</div>		
	</dd>
</dl>
</s:if>
</lz:set>
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
	$("#outletsForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csoId==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoHost==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoName==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoName":function(el){
			if(jQuery.trim(el.value)=="")
				return "名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "名称最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("outlets_query.do",{exists:true,csoName:el.value});
				if(exists && exists.length>0){
					if(${csOutlets.csoId==null} || exists[0].value!=$("#csoId").val())
						return "名称已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoArea==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoArea":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoType==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择网点类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoLongitude==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoLongitude":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "经度输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoLatitude==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoLatitude":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "纬度输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoAddress==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoAddress":function(el){
			if(jQuery.trim(el.value)=="")
				return "地址不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoSpace==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoSpace":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "停车地点最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoDetails==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoDetails":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "网点介绍最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoDoorTime==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoDoorTime":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "门禁时间最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoCost==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoCost":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "停车费输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPerson==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoPerson":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系人最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoContact==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoContact":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoUntil==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoUntil":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoExpire==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoExpire":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPileTime==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoPileTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoCarS==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoCarS":function(el){
			if(jQuery.trim(el.value)=="")
				return "已有车辆数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPositionS==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoPositionS":function(el){
			if(jQuery.trim(el.value)=="")
				return "车位数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoImage==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoImage":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoCanA==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoCanA":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择AB借出";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoCanB==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoCanB":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择AB还入";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoCanReg==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoCanReg":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择能注册";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoCanOrder==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoCanOrder":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择能预订";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPriority==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoPriority":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoVoteS==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoVoteS":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoProfile==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoProfile":function(el){
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "描述最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoOnlineTime==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoOnlineTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoUpdateTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoAddTime==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoStatus==true">
		${lz:set("haveEditable",true)}
		,"csOutlets.csoStatus":function(el){
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
	<form class="form editform" ref="CsOutlets" id="outletsForm" name="outletsForm" action="${empty CTRL.action?"outlets_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写网点相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csOutletsToken}" name="csOutletsToken" id="csOutletsToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csOutlets.csoId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csoId和after$csoId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoId==true">
	${before$csoId}
	<dl class="csoId ${CTRL.e.csoId?"hidden":""}" major  ref="csoId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csoId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csOutlets$csoId!=null">${csOutlets$csoId}</s:if><s:else>
			<input type="hidden" value="${csOutlets.csoId}" name="csOutlets.csoId" id="csoId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoId">${csOutlets.csoId}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csoHost和after$csoHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoHost==true">
	${before$csoHost}
	<dl class="csoHost " major  ref="csoHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csoHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOutlets$csoHost!=null">${csOutlets$csoHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csoHost" name="csOutlets.csoHost">
		 				<option selected value="${csOutlets.csoHost}">
		 					${get:SrvHost(csOutlets.csoHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csoHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoHost">${csOutlets.csoHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csOutlets.csoHost}',{ctrl:{editable:false,visible:true}})">
			 ${csOutlets.csoHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoHost}
	</s:if>
	
	${lz:set("注释","*****************名称字段的输入框代码*****************")}
	${lz:set("注释","before$csoName和after$csoName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoName==true">
	${before$csoName}
	<dl class="csoName " major  ref="csoName" >
		<dt>名　　称:</dt>
		<s:if test="#request.CTRL.e.csoName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutlets$csoName!=null">${csOutlets$csoName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutlets.csoName" id="csoName"  value="${csOutlets.csoName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入网点的名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoName">${csOutlets.csoName}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoName}
	</s:if>
	
	${lz:set("注释","*****************所在区域字段的输入框代码*****************")}
	${lz:set("注释","before$csoArea和after$csoArea变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoArea==true">
	${before$csoArea}
	<dl class="csoArea " major  ref="csoArea" >
		<dt>所在区域:</dt>
		<s:if test="#request.CTRL.e.csoArea==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOutlets$csoArea!=null">${csOutlets$csoArea}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/configurator/area_query.do?size=-1&csaHost={csoHost}" id="csoArea" name="csOutlets.csoArea">
		 				<option selected value="${csOutlets.csoArea}">
		 					${get:CsArea(csOutlets.csoArea).csaName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoArea').val())==''){return;};window.href('${basePath}${proname}/configurator/area_details.do?key='+$('#csoArea').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所在区域字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoArea">${csOutlets.csoArea}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/area_details.do?key=${csOutlets.csoArea}',{ctrl:{editable:false,visible:true}})">
			 ${csOutlets.csoArea$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoArea}
	</s:if>
	
	${lz:set("注释","*****************网点类型字段的输入框代码*****************")}
	${lz:set("注释","before$csoType和after$csoType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoType==true">
	${before$csoType}
	<dl class="csoType " major  ref="csoType" >
		<dt>网点类型:</dt>
		<s:if test="#request.CTRL.e.csoType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOutlets$csoType!=null">${csOutlets$csoType}</s:if><s:else>
		 	<select class="narrow" id="csoType" name="csOutlets.csoType">
		 		<option value="">请选择</option>
				<option value="0" ${csOutlets.csoType==0?"selected":""}>汽油车网点</option>
				<option value="1" ${csOutlets.csoType==1?"selected":""}>电动车网点</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择网点的网点类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****网点类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoType">${csOutlets.csoType}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoType}
	</s:if>
	
	${lz:set("注释","*****************经度字段的输入框代码*****************")}
	${lz:set("注释","before$csoLongitude和after$csoLongitude变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoLongitude==true">
	${before$csoLongitude}
	<dl class="csoLongitude " major  ref="csoLongitude" >
		<dt>经　　度:</dt>
		<s:if test="#request.CTRL.e.csoLongitude==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOutlets$csoLongitude!=null">${csOutlets$csoLongitude}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="9" name="csOutlets.csoLongitude" id="csoLongitude"  value="${csOutlets.csoLongitude}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入网点的经度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****经度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoLongitude">${csOutlets.csoLongitude}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoLongitude$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoLongitude}
	</s:if>
	
	${lz:set("注释","*****************纬度字段的输入框代码*****************")}
	${lz:set("注释","before$csoLatitude和after$csoLatitude变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoLatitude==true">
	${before$csoLatitude}
	<dl class="csoLatitude " major  ref="csoLatitude" >
		<dt>纬　　度:</dt>
		<s:if test="#request.CTRL.e.csoLatitude==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOutlets$csoLatitude!=null">${csOutlets$csoLatitude}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="9" name="csOutlets.csoLatitude" id="csoLatitude"  value="${csOutlets.csoLatitude}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入网点的纬度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****纬度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoLatitude">${csOutlets.csoLatitude}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoLatitude$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoLatitude}
	</s:if>
	
	${lz:set("注释","*****************描述字段的输入框代码*****************")}
	${lz:set("注释","before$csoProfile和after$csoProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoProfile==true">
	${before$csoProfile}
	<dl class="csoProfile " major  ref="csoProfile" style="width:98%;">
		<dt>还车半径:</dt>
		<s:if test="#request.CTRL.e.csoProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csOutlets$csoProfile!=null">${csOutlets$csoProfile}</s:if><s:else>
		 	<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="range" id="range"  value="${range}"/>米
	 	 </s:else>
	 	 
	 	 
	 	 <em>以网点坐标按还车半径设置网点还车圆形范围，超出范围用户则不能还车。如填0或未填写，则用户还车没有位置限制。</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
			 ${range}
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoProfile}
	</s:if>
	
	
	${lz:set("注释","*****************车辆列表字段的输入框代码*****************")}
	${lz:set("注释","before$csoCars和after$csoCars变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCars==true">
	${before$csoCars}
	<dl class="csoCars " minor  ref="csoCars" style="width:98%;">
		<dt>车辆列表:</dt>
		<s:if test="#request.CTRL.e.csoCars==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csOutlets$csoCars!=null">${csOutlets$csoCars}</s:if><s:else>
		  		<s:if test="csOutlets.csoId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csoCars" name="csoCars" frameborder='0' src="" url="${basePath}${proname}/object/car.do?inFrame=true&canQuery=false&fields=cscNumber,cscModel,cscProvid,cscStatus&cscOutlets=${csOutlets.csoId}&ctrl={queryable:false,fields:{cscOutlets:{visible:true,editable:false,defaultValue:${csOutlets.csoId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csoCars').attr('src',$('#csoCars').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑车辆列表
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆列表字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csOutlets.csoId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csoCars" name="csoCars" frameborder='0' src="" url="${basePath}${proname}/object/car.do?inFrame=true&canQuery=false&fields=cscNumber,cscModel,cscProvid,cscStatus&cscOutlets=${csOutlets.csoId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csoCars').attr('src',$('#csoCars').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csoCars}
	</s:if>
	
	${lz:set("注释","*****************地址字段的输入框代码*****************")}
	${lz:set("注释","before$csoAddress和after$csoAddress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoAddress==true">
	${before$csoAddress}
	<dl class="csoAddress " major  ref="csoAddress" style="width:98%;">
		<dt>地　　址:</dt>
		<s:if test="#request.CTRL.e.csoAddress==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutlets$csoAddress!=null">${csOutlets$csoAddress}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csOutlets.csoAddress" id="csoAddress"  value="${csOutlets.csoAddress}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入网点的地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csoAddress">${csOutlets.csoAddress}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoAddress$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoAddress}
	</s:if>
	
	${lz:set("注释","*****************停车地点字段的输入框代码*****************")}
	${lz:set("注释","before$csoSpace和after$csoSpace变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoSpace==true">
	${before$csoSpace}
	<dl class="csoSpace " minor  ref="csoSpace" style="width:98%;">
		<dt>停车地点:</dt>
		<s:if test="#request.CTRL.e.csoSpace==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutlets$csoSpace!=null">${csOutlets$csoSpace}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csOutlets.csoSpace" id="csoSpace"  value="${csOutlets.csoSpace}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入网点的停车地点</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****停车地点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csoSpace">${csOutlets.csoSpace}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoSpace$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoSpace}
	</s:if>
	
	${lz:set("注释","*****************网点介绍字段的输入框代码*****************")}
	${lz:set("注释","before$csoDetails和after$csoDetails变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoDetails==true">
	${before$csoDetails}
	<dl class="csoDetails " minor  ref="csoDetails" style="width:98%;">
		<dt>网点介绍:</dt>
		<s:if test="#request.CTRL.e.csoDetails==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutlets$csoDetails!=null">${csOutlets$csoDetails}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csOutlets.csoDetails" id="csoDetails"  value="${csOutlets.csoDetails}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入网点的网点介绍</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****网点介绍字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csoDetails">${csOutlets.csoDetails}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoDetails$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoDetails}
	</s:if>
	
	${lz:set("注释","*****************门禁时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoDoorTime和after$csoDoorTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoDoorTime==true">
	${before$csoDoorTime}
	<dl class="csoDoorTime " minor  ref="csoDoorTime" >
		<dt>门禁时间:</dt>
		<s:if test="#request.CTRL.e.csoDoorTime==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutlets$csoDoorTime!=null">${csOutlets$csoDoorTime}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutlets.csoDoorTime" id="csoDoorTime"  value="${csOutlets.csoDoorTime}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入网点的门禁时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****门禁时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoDoorTime">${csOutlets.csoDoorTime}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoDoorTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoDoorTime}
	</s:if>
	
	${lz:set("注释","*****************停车费字段的输入框代码*****************")}
	${lz:set("注释","before$csoCost和after$csoCost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCost==true">
	${before$csoCost}
	<dl class="csoCost " minor  ref="csoCost" >
		<dt>停&nbsp;&nbsp;车&nbsp;&nbsp;费:</dt>
		<s:if test="#request.CTRL.e.csoCost==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOutlets$csoCost!=null">${csOutlets$csoCost}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOutlets.csoCost" id="csoCost"  value="${csOutlets.csoCost}"/>
	 	 </s:else>
	 	 元每月
	 	 
	 	 <em>请输入网点的停车费</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****停车费字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoCost">${csOutlets.csoCost}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoCost$}
	 	 元每月 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoCost}
	</s:if>
	
	${lz:set("注释","*****************联系人字段的输入框代码*****************")}
	${lz:set("注释","before$csoPerson和after$csoPerson变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPerson==true">
	${before$csoPerson}
	<dl class="csoPerson " minor  ref="csoPerson" >
		<dt>联&nbsp;&nbsp;系&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csoPerson==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutlets$csoPerson!=null">${csOutlets$csoPerson}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutlets.csoPerson" id="csoPerson"  value="${csOutlets.csoPerson}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入网点的联系人</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPerson">${csOutlets.csoPerson}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoPerson$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPerson}
	</s:if>
	
	${lz:set("注释","*****************联系电话字段的输入框代码*****************")}
	${lz:set("注释","before$csoContact和after$csoContact变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoContact==true">
	${before$csoContact}
	<dl class="csoContact " minor  ref="csoContact" >
		<dt>联系电话:</dt>
		<s:if test="#request.CTRL.e.csoContact==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutlets$csoContact!=null">${csOutlets$csoContact}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutlets.csoContact" id="csoContact"  value="${csOutlets.csoContact}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入网点的联系电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoContact">${csOutlets.csoContact}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoContact$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoContact}
	</s:if>
	
	${lz:set("注释","*****************缴费到期字段的输入框代码*****************")}
	${lz:set("注释","before$csoUntil和after$csoUntil变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoUntil==true">
	${before$csoUntil}
	<dl class="csoUntil " major  ref="csoUntil" >
		<dt>缴费到期:</dt>
		<s:if test="#request.CTRL.e.csoUntil==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csOutlets$csoUntil!=null">${csOutlets$csoUntil}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csOutlets.csoUntil" id="csoUntil"  value="<s:date name="csOutlets.csoUntil" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择网点的缴费到期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缴费到期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoUntil">${csOutlets.csoUntil}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoUntil$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoUntil}
	</s:if>
	
	${lz:set("注释","*****************合约到期字段的输入框代码*****************")}
	${lz:set("注释","before$csoExpire和after$csoExpire变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoExpire==true">
	${before$csoExpire}
	<dl class="csoExpire " major  ref="csoExpire" >
		<dt>合约到期:</dt>
		<s:if test="#request.CTRL.e.csoExpire==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csOutlets$csoExpire!=null">${csOutlets$csoExpire}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csOutlets.csoExpire" id="csoExpire"  value="<s:date name="csOutlets.csoExpire" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择网点的合约到期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合约到期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoExpire">${csOutlets.csoExpire}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoExpire$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoExpire}
	</s:if>
	
	${lz:set("注释","*****************电桩建立时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoPileTime和after$csoPileTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPileTime==true">
	${before$csoPileTime}
	<dl class="csoPileTime " minor  ref="csoPileTime" >
		<dt>电桩建立时间:</dt>
		<s:if test="#request.CTRL.e.csoPileTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOutlets$csoPileTime!=null">${csOutlets$csoPileTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOutlets.csoPileTime" id="csoPileTime"  value="<s:date name="csOutlets.csoPileTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择网点的电桩建立时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****电桩建立时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPileTime">${csOutlets.csoPileTime}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoPileTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPileTime}
	</s:if>
	
	${lz:set("注释","*****************已有车辆数字段的输入框代码*****************")}
	${lz:set("注释","before$csoCarS和after$csoCarS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCarS==true">
	${before$csoCarS}
	<dl class="csoCarS " major  ref="csoCarS" >
		<dt>已有车辆数:</dt>
		<s:if test="#request.CTRL.e.csoCarS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csOutlets$csoCarS!=null">${csOutlets$csoCarS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOutlets.csoCarS" id="csoCarS"  value="${csOutlets.csoCarS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入网点的已有车辆数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****已有车辆数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoCarS">${csOutlets.csoCarS}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoCarS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoCarS}
	</s:if>
	
	${lz:set("注释","*****************车位数字段的输入框代码*****************")}
	${lz:set("注释","before$csoPositionS和after$csoPositionS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPositionS==true">
	${before$csoPositionS}
	<dl class="csoPositionS " major  ref="csoPositionS" >
		<dt>车&nbsp;&nbsp;位&nbsp;&nbsp;数:</dt>
		<s:if test="#request.CTRL.e.csoPositionS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csOutlets$csoPositionS!=null">${csOutlets$csoPositionS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOutlets.csoPositionS" id="csoPositionS"  value="${csOutlets.csoPositionS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入网点的车位数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车位数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPositionS">${csOutlets.csoPositionS}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoPositionS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPositionS}
	</s:if>
	
	${lz:set("注释","*****************网点图片字段的输入框代码*****************")}
	${lz:set("注释","before$csoImage和after$csoImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoImage==true">
	${before$csoImage}
	<dl class="csoImage " minor  ref="csoImage" style="width:98%;">
		<dt>网点图片:</dt>
		<s:if test="#request.CTRL.e.csoImage==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csOutlets$csoImage!=null">${csOutlets$csoImage}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csOutlets.csoImage" id="csoImage"  value="${csOutlets.csoImage}"/>
			<button type="button" onclick="$.upload({type:'img',width:400,height:300,callback:function(url){if(url)$('#csoImage').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#csoImage').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#csoImage').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传网点的网点图片</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****网点图片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csoImage">${csOutlets.csoImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csOutlets.csoImage))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csOutlets.csoImage}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoImage}
	</s:if>
	
	${lz:set("注释","*****************AB借出字段的输入框代码*****************")}
	${lz:set("注释","before$csoCanA和after$csoCanA变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCanA==true">
	${before$csoCanA}
	<dl class="csoCanA " major  ref="csoCanA" >
		<dt>AB借出:</dt>
		<s:if test="#request.CTRL.e.csoCanA==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOutlets$csoCanA!=null">${csOutlets$csoCanA}</s:if><s:else>
		 	<select class="narrow" id="csoCanA" name="csOutlets.csoCanA">
		 		<option value="">请选择</option>
				<option value="true" ${csOutlets.csoCanA==true?"selected":""}>是</option>
				<option value="false" ${csOutlets.csoCanA==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>是否支持A借B还中的借出</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****AB借出字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoCanA">${csOutlets.csoCanA}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoCanA$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoCanA}
	</s:if>
	
	${lz:set("注释","*****************AB还入字段的输入框代码*****************")}
	${lz:set("注释","before$csoCanB和after$csoCanB变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCanB==true">
	${before$csoCanB}
	<dl class="csoCanB " major  ref="csoCanB" >
		<dt>AB还入:</dt>
		<s:if test="#request.CTRL.e.csoCanB==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOutlets$csoCanB!=null">${csOutlets$csoCanB}</s:if><s:else>
		 	<select class="narrow" id="csoCanB" name="csOutlets.csoCanB">
		 		<option value="">请选择</option>
				<option value="true" ${csOutlets.csoCanB==true?"selected":""}>是</option>
				<option value="false" ${csOutlets.csoCanB==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>是否支持A借B还中的还入</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****AB还入字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoCanB">${csOutlets.csoCanB}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoCanB$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoCanB}
	</s:if>
	
	${lz:set("注释","*****************能注册字段的输入框代码*****************")}
	${lz:set("注释","before$csoCanReg和after$csoCanReg变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCanReg==true">
	${before$csoCanReg}
	<dl class="csoCanReg " major  ref="csoCanReg" >
		<dt>能&nbsp;&nbsp;注&nbsp;&nbsp;册:</dt>
		<s:if test="#request.CTRL.e.csoCanReg==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOutlets$csoCanReg!=null">${csOutlets$csoCanReg}</s:if><s:else>
		 	<select class="narrow" id="csoCanReg" name="csOutlets.csoCanReg">
		 		<option value="">请选择</option>
				<option value="true" ${csOutlets.csoCanReg==true?"selected":""}>是</option>
				<option value="false" ${csOutlets.csoCanReg==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>可作为会员默认网点的选取项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****能注册字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoCanReg">${csOutlets.csoCanReg}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoCanReg$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoCanReg}
	</s:if>
	
	${lz:set("注释","*****************能预订字段的输入框代码*****************")}
	${lz:set("注释","before$csoCanOrder和after$csoCanOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCanOrder==true">
	${before$csoCanOrder}
	<dl class="csoCanOrder " major  ref="csoCanOrder" >
		<dt>能&nbsp;&nbsp;预&nbsp;&nbsp;订:</dt>
		<s:if test="#request.CTRL.e.csoCanOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOutlets$csoCanOrder!=null">${csOutlets$csoCanOrder}</s:if><s:else>
		 	<select class="narrow" id="csoCanOrder" name="csOutlets.csoCanOrder">
		 		<option value="">请选择</option>
				<option value="true" ${csOutlets.csoCanOrder==true?"selected":""}>是</option>
				<option value="false" ${csOutlets.csoCanOrder==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>描述该网点是否可以下单</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****能预订字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoCanOrder">${csOutlets.csoCanOrder}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoCanOrder$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoCanOrder}
	</s:if>
	
	${lz:set("注释","*****************优先级字段的输入框代码*****************")}
	${lz:set("注释","before$csoPriority和after$csoPriority变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPriority==true">
	${before$csoPriority}
	<dl class="csoPriority " minor  ref="csoPriority" >
		<dt>优&nbsp;&nbsp;先&nbsp;&nbsp;级:</dt>
		<s:if test="#request.CTRL.e.csoPriority==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csOutlets$csoPriority!=null">${csOutlets$csoPriority}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOutlets.csoPriority" id="csoPriority"  value="${csOutlets.csoPriority}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入网点的优先级</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优先级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPriority">${csOutlets.csoPriority}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoPriority$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPriority}
	</s:if>
	
	${lz:set("注释","*****************人气字段的输入框代码*****************")}
	${lz:set("注释","before$csoVoteS和after$csoVoteS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoVoteS==true">
	${before$csoVoteS}
	<dl class="csoVoteS " minor  ref="csoVoteS" >
		<dt>人　　气:</dt>
		<s:if test="#request.CTRL.e.csoVoteS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csOutlets$csoVoteS!=null">${csOutlets$csoVoteS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOutlets.csoVoteS" id="csoVoteS"  value="${csOutlets.csoVoteS}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入网点的人气</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人气字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoVoteS">${csOutlets.csoVoteS}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoVoteS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoVoteS}
	</s:if>
	
	${lz:set("注释","*****************描述字段的输入框代码*****************")}
	${lz:set("注释","before$csoProfile和after$csoProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoProfile==true">
	${before$csoProfile}
	<dl class="csoProfile " major  ref="csoProfile" style="width:98%;">
		<dt>描　　述:</dt>
		<s:if test="#request.CTRL.e.csoProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csOutlets$csoProfile!=null">${csOutlets$csoProfile}</s:if><s:else>
		 	<textarea class="input wide"  id="csoProfile" name="descript" rows="5">${descript}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入网点的描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			${descript}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoProfile}
	</s:if>
	
	
	
	${lz:set("注释","*****************上线时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoOnlineTime和after$csoOnlineTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoOnlineTime==true">
	${before$csoOnlineTime}
	<dl class="csoOnlineTime " major  ref="csoOnlineTime" >
		<dt>上线时间:</dt>
		<s:if test="#request.CTRL.e.csoOnlineTime==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csOutlets$csoOnlineTime!=null">${csOutlets$csoOnlineTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csOutlets.csoOnlineTime" id="csoOnlineTime"  value="<s:date name="csOutlets.csoOnlineTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择网点的上线时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上线时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoOnlineTime">${csOutlets.csoOnlineTime}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoOnlineTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoOnlineTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoUpdateTime和after$csoUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoUpdateTime==true">
	${before$csoUpdateTime}
	<dl class="csoUpdateTime " minor  ref="csoUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csoUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOutlets$csoUpdateTime!=null">${csOutlets$csoUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOutlets.csoUpdateTime" id="csoUpdateTime"  value="<s:date name="csOutlets.csoUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择网点的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoUpdateTime">${csOutlets.csoUpdateTime}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoAddTime和after$csoAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoAddTime==true">
	${before$csoAddTime}
	<dl class="csoAddTime " major  ref="csoAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csoAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOutlets$csoAddTime!=null">${csOutlets$csoAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOutlets.csoAddTime" id="csoAddTime"  value="<s:date name="csOutlets.csoAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择网点的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoAddTime">${csOutlets.csoAddTime}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csoStatus和after$csoStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoStatus==true">
	${before$csoStatus}
	<dl class="csoStatus " major  ref="csoStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csoStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOutlets$csoStatus!=null">${csOutlets$csoStatus}</s:if><s:else>
		 	<select class="narrow" id="csoStatus" name="csOutlets.csoStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csOutlets.csoStatus==1?"selected":""}>正常</option>
				<option value="0" ${csOutlets.csoStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>描述该网点是否让会员看得到</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoStatus">${csOutlets.csoStatus}</textarea>
		 		<span>
		 	
			 ${csOutlets.csoStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csOutlets.csoId}" name="csOutlets.csoId" id="csoId" />
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