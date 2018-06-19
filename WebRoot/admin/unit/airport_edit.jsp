<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/airport.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/airport.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitAirport.csuaId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csuaId,csuaHost,csuaUnitInfo,csuaType,csuaFlight,csuaTime,csuaContact,csuaMobile,csuaCount,csuaStatement,csuaRemark,csuaStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuaId,csuaHost,csuaUnitInfo,csuaType,csuaFlight,csuaTime,csuaContact,csuaMobile,csuaCount,csuaStatement,csuaRemark,csuaStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csuaId,csuaHost,csuaUnitInfo,csuaType,csuaFlight,csuaTime,csuaContact,csuaMobile,csuaCount,csuaStatement,csuaRemark,csuaStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuaId,csuaHost,csuaUnitInfo,csuaType,csuaFlight,csuaTime,csuaContact,csuaMobile,csuaCount,csuaStatement,csuaRemark,csuaStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"机场服务编辑":CTRL.title}</title>
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
	$("#airportForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csuaId==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaHost==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaUnitInfo==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaUnitInfo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaType==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaFlight==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaFlight":function(el){
			if(jQuery.trim(el.value)=="")
				return "航班号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "航班号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaContact==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaContact":function(el){
			if(jQuery.trim(el.value)=="")
				return "联系人不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系人最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaMobile==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaMobile":function(el){
			if(jQuery.trim(el.value)=="")
				return "联系电话不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaCount==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaCount":function(el){
			if(jQuery.trim(el.value)=="")
				return "人数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaStatement==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaStatement":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaAddTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaRemark==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuaStatus==true">
		${lz:set("haveEditable",true)}
		,"csUnitAirport.csuaStatus":function(el){
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
	<form class="form editform" ref="CsUnitAirport" id="airportForm" name="airportForm" action="${empty CTRL.action?"airport_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写机场服务相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csUnitAirportToken}" name="csUnitAirportToken" id="csUnitAirportToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitAirport.csuaId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csuaId和after$csuaId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaId==true">
	${before$csuaId}
	<dl class="csuaId ${CTRL.e.csuaId?"hidden":""}" major  ref="csuaId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csuaId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUnitAirport$csuaId!=null">${csUnitAirport$csuaId}</s:if><s:else>
			<input type="hidden" value="${csUnitAirport.csuaId}" name="csUnitAirport.csuaId" id="csuaId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaId">${csUnitAirport.csuaId}</textarea>
		 		<span>
		 	
			 ${csUnitAirport.csuaId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csuaHost和after$csuaHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaHost==true">
	${before$csuaHost}
	<dl class="csuaHost " major  ref="csuaHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csuaHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitAirport$csuaHost!=null">${csUnitAirport$csuaHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csuaHost" name="csUnitAirport.csuaHost">
		 				<option selected value="${csUnitAirport.csuaHost}">
		 					${get:SrvHost(csUnitAirport.csuaHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuaHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csuaHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaHost">${csUnitAirport.csuaHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csUnitAirport.csuaHost}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitAirport.csuaHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaHost}
	</s:if>
	
	${lz:set("注释","*****************所属单位字段的输入框代码*****************")}
	${lz:set("注释","before$csuaUnitInfo和after$csuaUnitInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaUnitInfo==true">
	${before$csuaUnitInfo}
	<dl class="csuaUnitInfo " major  ref="csuaUnitInfo" >
		<dt>所属单位:</dt>
		<s:if test="#request.CTRL.e.csuaUnitInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitAirport$csuaUnitInfo!=null">${csUnitAirport$csuaUnitInfo}</s:if><s:else>
		 			<input title="请输入企业信息名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csuaHost}" type="text" id="csuaUnitInfo" name="csUnitAirport.csuaUnitInfo" text="${get:CsUnitInfo(csUnitAirport.csuaUnitInfo).csuiName}" value="${csUnitAirport.csuaUnitInfo}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuaUnitInfo').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?key='+$('#csuaUnitInfo').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属单位字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaUnitInfo">${csUnitAirport.csuaUnitInfo}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${csUnitAirport.csuaUnitInfo}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitAirport.csuaUnitInfo$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaUnitInfo}
	</s:if>
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$csuaType和after$csuaType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaType==true">
	${before$csuaType}
	<dl class="csuaType " major  ref="csuaType" >
		<dt>类　　型:</dt>
		<s:if test="#request.CTRL.e.csuaType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitAirport$csuaType!=null">${csUnitAirport$csuaType}</s:if><s:else>
		 	<select class="narrow" id="csuaType" name="csUnitAirport.csuaType">
		 		<option value="">请选择</option>
				<option value="0" ${csUnitAirport.csuaType==0?"selected":""}>接机</option>
				<option value="1" ${csUnitAirport.csuaType==1?"selected":""}>送机</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择机场服务的类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaType">${csUnitAirport.csuaType}</textarea>
		 		<span>
		 	
			 ${csUnitAirport.csuaType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaType}
	</s:if>
	
	${lz:set("注释","*****************航班号字段的输入框代码*****************")}
	${lz:set("注释","before$csuaFlight和after$csuaFlight变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaFlight==true">
	${before$csuaFlight}
	<dl class="csuaFlight " major  ref="csuaFlight" >
		<dt>航&nbsp;&nbsp;班&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.csuaFlight==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitAirport$csuaFlight!=null">${csUnitAirport$csuaFlight}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitAirport.csuaFlight" id="csuaFlight"  value="${csUnitAirport.csuaFlight}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入机场服务的航班号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****航班号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaFlight">${csUnitAirport.csuaFlight}</textarea>
		 		<span>
		 	
			 ${csUnitAirport.csuaFlight$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaFlight}
	</s:if>
	
	${lz:set("注释","*****************时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuaTime和after$csuaTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaTime==true">
	${before$csuaTime}
	<dl class="csuaTime " major  ref="csuaTime" >
		<dt>时　　间:</dt>
		<s:if test="#request.CTRL.e.csuaTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitAirport$csuaTime!=null">${csUnitAirport$csuaTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitAirport.csuaTime" id="csuaTime"  value="<s:date name="csUnitAirport.csuaTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择机场服务的时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaTime">${csUnitAirport.csuaTime}</textarea>
		 		<span>
		 	
			 ${csUnitAirport.csuaTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaTime}
	</s:if>
	
	${lz:set("注释","*****************联系人字段的输入框代码*****************")}
	${lz:set("注释","before$csuaContact和after$csuaContact变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaContact==true">
	${before$csuaContact}
	<dl class="csuaContact " major  ref="csuaContact" >
		<dt>联&nbsp;&nbsp;系&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csuaContact==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitAirport$csuaContact!=null">${csUnitAirport$csuaContact}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitAirport.csuaContact" id="csuaContact"  value="${csUnitAirport.csuaContact}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入机场服务的联系人</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaContact">${csUnitAirport.csuaContact}</textarea>
		 		<span>
		 	
			 ${csUnitAirport.csuaContact$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaContact}
	</s:if>
	
	${lz:set("注释","*****************联系电话字段的输入框代码*****************")}
	${lz:set("注释","before$csuaMobile和after$csuaMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaMobile==true">
	${before$csuaMobile}
	<dl class="csuaMobile " major  ref="csuaMobile" >
		<dt>联系电话:</dt>
		<s:if test="#request.CTRL.e.csuaMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitAirport$csuaMobile!=null">${csUnitAirport$csuaMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitAirport.csuaMobile" id="csuaMobile"  value="${csUnitAirport.csuaMobile}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入机场服务的联系电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaMobile">${csUnitAirport.csuaMobile}</textarea>
		 		<span>
		 	
			 ${csUnitAirport.csuaMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaMobile}
	</s:if>
	
	${lz:set("注释","*****************人数字段的输入框代码*****************")}
	${lz:set("注释","before$csuaCount和after$csuaCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaCount==true">
	${before$csuaCount}
	<dl class="csuaCount " major  ref="csuaCount" >
		<dt>人　　数:</dt>
		<s:if test="#request.CTRL.e.csuaCount==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csUnitAirport$csuaCount!=null">${csUnitAirport$csuaCount}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitAirport.csuaCount" id="csuaCount"  value="${csUnitAirport.csuaCount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入机场服务的人数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaCount">${csUnitAirport.csuaCount}</textarea>
		 		<span>
		 	
			 ${csUnitAirport.csuaCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaCount}
	</s:if>
	
	${lz:set("注释","*****************结账单字段的输入框代码*****************")}
	${lz:set("注释","before$csuaStatement和after$csuaStatement变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaStatement==true">
	${before$csuaStatement}
	<dl class="csuaStatement " major  ref="csuaStatement" >
		<dt>结&nbsp;&nbsp;账&nbsp;&nbsp;单:</dt>
		<s:if test="#request.CTRL.e.csuaStatement==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitAirport$csuaStatement!=null">${csUnitAirport$csuaStatement}</s:if><s:else>
		 			<input title="请输入企业对账帐单月份进行查询" class="combox narrow" action="${basePath}${proname}/unit/bill_query.do?value={param}&csubHost={csuaHost}" type="text" id="csuaStatement" name="csUnitAirport.csuaStatement" text="${get:CsUnitBill(csUnitAirport.csuaStatement).csubMonth}" value="${csUnitAirport.csuaStatement}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuaStatement').val())==''){return;};window.href('${basePath}${proname}/unit/bill_details.do?key='+$('#csuaStatement').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结账单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaStatement">${csUnitAirport.csuaStatement}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/bill_details.do?key=${csUnitAirport.csuaStatement}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitAirport.csuaStatement$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaStatement}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuaUpdateTime和after$csuaUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaUpdateTime==true">
	${before$csuaUpdateTime}
	<dl class="csuaUpdateTime " major  ref="csuaUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csuaUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitAirport$csuaUpdateTime!=null">${csUnitAirport$csuaUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitAirport.csuaUpdateTime" id="csuaUpdateTime"  value="<s:date name="csUnitAirport.csuaUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择机场服务的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaUpdateTime">${csUnitAirport.csuaUpdateTime}</textarea>
		 		<span>
		 	
			 ${csUnitAirport.csuaUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuaAddTime和after$csuaAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaAddTime==true">
	${before$csuaAddTime}
	<dl class="csuaAddTime " major  ref="csuaAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csuaAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitAirport$csuaAddTime!=null">${csUnitAirport$csuaAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitAirport.csuaAddTime" id="csuaAddTime"  value="<s:date name="csUnitAirport.csuaAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择机场服务的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaAddTime">${csUnitAirport.csuaAddTime}</textarea>
		 		<span>
		 	
			 ${csUnitAirport.csuaAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csuaRemark和after$csuaRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaRemark==true">
	${before$csuaRemark}
	<dl class="csuaRemark " minor  ref="csuaRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csuaRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitAirport$csuaRemark!=null">${csUnitAirport$csuaRemark}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csUnitAirport.csuaRemark" id="csuaRemark"  value="${csUnitAirport.csuaRemark}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入机场服务的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csuaRemark">${csUnitAirport.csuaRemark}</textarea>
		 		<span>
		 	
			 ${csUnitAirport.csuaRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csuaStatus和after$csuaStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuaStatus==true">
	${before$csuaStatus}
	<dl class="csuaStatus " major  ref="csuaStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csuaStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitAirport$csuaStatus!=null">${csUnitAirport$csuaStatus}</s:if><s:else>
		 	<select class="narrow" id="csuaStatus" name="csUnitAirport.csuaStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csUnitAirport.csuaStatus==0?"selected":""}>未处理</option>
				<option value="1" ${csUnitAirport.csuaStatus==1?"selected":""}>已处理</option>
				<option value="2" ${csUnitAirport.csuaStatus==2?"selected":""}>审核失败</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择机场服务的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuaStatus">${csUnitAirport.csuaStatus}</textarea>
		 		<span>
		 	
			 ${csUnitAirport.csuaStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuaStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUnitAirport.csuaId}" name="csUnitAirport.csuaId" id="csuaId" />
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