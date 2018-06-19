<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/info.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/info.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitInfo.csuiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csuiId,csuiHost,csuiName,csuiPerson,csuiType,users,csuiAddress,csuiContact,csuiOutlets,csuiFeeType,csuiSettleType,feeset,csuiRemark,csuiStatus,csuiArea",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuiId,csuiHost,csuiName,csuiPerson,csuiType,users,csuiAddress,csuiContact,csuiOutlets,csuiFeeType,csuiSettleType,feeset,csuiRemark,csuiStatus,csuiArea",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csuiId,csuiHost,csuiName,csuiPerson,csuiType,users,csuiAddress,csuiContact,csuiMember,csuiOutlets,csuiFeeType,feeset,csuiRemark,csuiStatus,csuiArea",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuiId,csuiHost,csuiName,csuiPerson,csuiType,users,csuiAddress,csuiContact,csuiMember,csuiOutlets,csuiFeeType,csuiSettleType,feeset,csuiRemark,csuiStatus,csuiArea",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"企业信息编辑":CTRL.title}</title>
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
<s:if test="#request.edittype=='save'">
<lz:set name="after$csuiContact">
<dl>
	<dt>自动创建</dt>
	<dd>
		<div class="input narrow">
			&nbsp;
			<label><input type="checkbox" value="1" name="autoCreate"/>支付帐号</label>
			&nbsp;
			<label><input type="checkbox" value="2" name="autoCreate"/>管理帐号</label>
		</div>
	</dd>
</dl>
</lz:set>
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
	$("#infoForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csuiId==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiHost==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiName==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiName":function(el){
			if(jQuery.trim(el.value)=="")
				return "名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiPerson==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiPerson":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系人最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiType==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiAddress==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiAddress":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "详细地址最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiContact==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiContact":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiMember==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiMember":function(el){
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("info_query.do",{exists:true,csuiMember:el.value});
				if(exists && exists.length>0){
					if(${csUnitInfo.csuiId==null} || exists[0].value!=$("#csuiId").val())
						return "支付帐号已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiOutlets==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiOutlets":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiFeeType==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiFeeType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiSettleType==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiSettleType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择结算方式";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiAddTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiRemark==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuiStatus==true">
		${lz:set("haveEditable",true)}
		,"csUnitInfo.csuiStatus":function(el){
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
	<form class="form editform" ref="CsUnitInfo" id="infoForm" name="infoForm" action="${empty CTRL.action?"info_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写企业信息相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csUnitInfoToken}" name="csUnitInfoToken" id="csUnitInfoToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitInfo.csuiId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csuiId和after$csuiId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiId==true">
	${before$csuiId}
	<dl class="csuiId ${CTRL.e.csuiId?"hidden":""}" major  ref="csuiId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csuiId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUnitInfo$csuiId!=null">${csUnitInfo$csuiId}</s:if><s:else>
			<input type="hidden" value="${csUnitInfo.csuiId}" name="csUnitInfo.csuiId" id="csuiId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiId">${csUnitInfo.csuiId}</textarea>
		 		<span>
		 	
			 ${csUnitInfo.csuiId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csuiHost和after$csuiHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiHost==true">
	${before$csuiHost}
	<dl class="csuiHost " major  ref="csuiHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csuiHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitInfo$csuiHost!=null">${csUnitInfo$csuiHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csuiHost" name="csUnitInfo.csuiHost">
		 				<option selected value="${csUnitInfo.csuiHost}">
		 					${get:SrvHost(csUnitInfo.csuiHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuiHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csuiHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiHost">${csUnitInfo.csuiHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csUnitInfo.csuiHost}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitInfo.csuiHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiHost}
	</s:if>
	
	${lz:set("注释","*****************所在区域字段的输入框代码*****************")}
	${lz:set("注释","before$csuiArea和after$csuiArea变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiArea==true">
	${before$csuiArea}
	<dl class="csuiArea " major  ref="csuiArea" >
		<dt>所在区域:</dt>
		<s:if test="#request.CTRL.e.csuiArea==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOutlets$csuiArea!=null">${csUnitInfo$csuiArea}</s:if><s:else>
 			<select class="combox narrow" action="${basePath}${proname}/configurator/area_query.do?size=-1&csaHost={csuiHost}" id="csuiArea" name="csUnitInfo.csuiArea">
 				<option selected value="${csUnitInfo.csuiArea}">
 					${get:CsArea(csOutlets.csuiArea).csaName}
 				</option>
 			</select>
 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuiArea').val())==''){return;};window.href('${basePath}${proname}/configurator/area_details.do?key='+$('#csuiArea').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择区域</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所在区域字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiArea">${csUnitInfo.csuiArea}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/area_details.do?key=${csUnitInfo.csuiArea}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitInfo.csuiArea$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiArea}
	</s:if>
	
	${lz:set("注释","*****************名称字段的输入框代码*****************")}
	${lz:set("注释","before$csuiName和after$csuiName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiName==true">
	${before$csuiName}
	<dl class="csuiName " major  ref="csuiName" >
		<dt>名　　称:</dt>
		<s:if test="#request.CTRL.e.csuiName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitInfo$csuiName!=null">${csUnitInfo$csuiName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitInfo.csuiName" id="csuiName"  value="${csUnitInfo.csuiName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入企业信息的名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiName">${csUnitInfo.csuiName}</textarea>
		 		<span>
		 	
			 ${csUnitInfo.csuiName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiName}
	</s:if>
	
	${lz:set("注释","*****************联系人字段的输入框代码*****************")}
	${lz:set("注释","before$csuiPerson和after$csuiPerson变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiPerson==true">
	${before$csuiPerson}
	<dl class="csuiPerson " major  ref="csuiPerson" >
		<dt>联&nbsp;&nbsp;系&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csuiPerson==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitInfo$csuiPerson!=null">${csUnitInfo$csuiPerson}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitInfo.csuiPerson" id="csuiPerson"  value="${csUnitInfo.csuiPerson}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业信息的联系人</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiPerson">${csUnitInfo.csuiPerson}</textarea>
		 		<span>
		 	
			 ${csUnitInfo.csuiPerson$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiPerson}
	</s:if>
	
	${lz:set("注释","*****************企业类型字段的输入框代码*****************")}
	${lz:set("注释","before$csuiType和after$csuiType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiType==true">
	${before$csuiType}
	<dl class="csuiType " major  ref="csuiType" >
		<dt>企业类型:</dt>
		<s:if test="#request.CTRL.e.csuiType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitInfo$csuiType!=null">${csUnitInfo$csuiType}</s:if><s:else>
		 	<select class="narrow" id="csuiType" name="csUnitInfo.csuiType">
		 		<option value="">请选择</option>
				<option value="0" ${csUnitInfo.csuiType==0?"selected":""}>企业用车</option>
				<option value="1" ${csUnitInfo.csuiType==1?"selected":""}>企业代理</option>
				<option value="2" ${csUnitInfo.csuiType==2?"selected":""}>公务用车</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择企业信息的企业类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****企业类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiType">${csUnitInfo.csuiType}</textarea>
		 		<span>
		 	
			 ${csUnitInfo.csuiType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiType}
	</s:if>
	
	${lz:set("注释","*****************企业管理员字段的输入框代码*****************")}
	${lz:set("注释","before$users和after$users变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.users==true">
	${before$users}
	<dl class="users " minor  ref="users" style="width:98%;">
		<dt>企业管理员:</dt>
		<s:if test="#request.CTRL.e.users==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csUnitInfo$users!=null">${csUnitInfo$users}</s:if><s:else>
		  		<s:if test="csUnitInfo.csuiId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="users" name="users" frameborder='0' src="" url="${basePath}${proname}/unit/user.do?inFrame=true&canQuery=false&fields=csuuUsername,csuuIsRoot,csuuRealname,csuuStatus&csuuUnitInfo=${csUnitInfo.csuiId}&ctrl={queryable:false,fields:{csuuUnitInfo:{visible:true,editable:false,defaultValue:${csUnitInfo.csuiId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#users').attr('src',$('#users').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑企业管理员
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****企业管理员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csUnitInfo.csuiId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="users" name="users" frameborder='0' src="" url="${basePath}${proname}/unit/user.do?inFrame=true&canQuery=false&fields=csuuUsername,csuuIsRoot,csuuRealname,csuuStatus&csuuUnitInfo=${csUnitInfo.csuiId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#users').attr('src',$('#users').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$users}
	</s:if>
	
	${lz:set("注释","*****************详细地址字段的输入框代码*****************")}
	${lz:set("注释","before$csuiAddress和after$csuiAddress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiAddress==true">
	${before$csuiAddress}
	<dl class="csuiAddress " minor  ref="csuiAddress" style="width:98%;">
		<dt>详细地址:</dt>
		<s:if test="#request.CTRL.e.csuiAddress==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitInfo$csuiAddress!=null">${csUnitInfo$csuiAddress}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csUnitInfo.csuiAddress" id="csuiAddress"  value="${csUnitInfo.csuiAddress}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业信息的详细地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****详细地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csuiAddress">${csUnitInfo.csuiAddress}</textarea>
		 		<span>
		 	
			 ${csUnitInfo.csuiAddress$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiAddress}
	</s:if>
	
	${lz:set("注释","*****************联系电话字段的输入框代码*****************")}
	${lz:set("注释","before$csuiContact和after$csuiContact变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiContact==true">
	${before$csuiContact}
	<dl class="csuiContact " major  ref="csuiContact" >
		<dt>联系电话:</dt>
		<s:if test="#request.CTRL.e.csuiContact==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitInfo$csuiContact!=null">${csUnitInfo$csuiContact}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitInfo.csuiContact" id="csuiContact"  value="${csUnitInfo.csuiContact}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业信息的联系电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiContact">${csUnitInfo.csuiContact}</textarea>
		 		<span>
		 	
			 ${csUnitInfo.csuiContact$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiContact}
	</s:if>
	
	${lz:set("注释","*****************支付帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csuiMember和after$csuiMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiMember==true">
	${before$csuiMember}
	<dl class="csuiMember " major  ref="csuiMember" style="width:98%;">
		<dt>支付帐号:</dt>
		<s:if test="#request.CTRL.e.csuiMember==true">
		${lz:set("haveEditable",true)}
		<dd input="list">
		<s:if test="#request.csUnitInfo$csuiMember!=null">${csUnitInfo$csuiMember}</s:if><s:else>
		 	
			    <div name="csUnitInfo.csuiMember" id="csuiMember" class="wide input listinput">
					<input type="hidden" name="csUnitInfo.csuiMember" value="0"/>
				<s:generator val="csUnitInfo.csuiMember" separator=",">
					<s:iterator  id="item">
					<s:if test="item!=0">
						<div>
							${get:CsMember(item).csmName}							
						<input type="hidden" name="csUnitInfo.csuiMember" value="${item}"/><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
					</s:iterator>
				</s:generator>
				</div>
				<button type="button" style="float:left;" onclick="$.getInput({type:'combox',title:'会员帐号真实姓名',action:$(this).attr('action'),callback:function(id,text){$('#csuiMember').append('<div>'+text+'<input type=&quot;hidden&quot; name=&quot;csUnitInfo.csuiMember&quot; value=&quot;'+id+'&quot;/><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}});" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csuiHost}" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csuiMember">${csUnitInfo.csuiMember}</textarea>
		 		<span>
		 	
			 ${csUnitInfo.csuiMember$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiMember}
	</s:if>
	
	${lz:set("注释","*****************指定网点字段的输入框代码*****************")}
	${lz:set("注释","before$csuiOutlets和after$csuiOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiOutlets==true">
	${before$csuiOutlets}
	<dl class="csuiOutlets " major  ref="csuiOutlets" style="width:98%;">
		<dt>指定网点:</dt>
		<s:if test="#request.CTRL.e.csuiOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="list">
		
		<s:if test="#request.csUnitInfo$csuiOutlets!=null">${csUnitInfo$csuiOutlets}</s:if><s:else>
			<div name="csUnitInfo.csuiOutlets" id="csuiOutlets" class="wide input listinput">
				<input type="hidden" name="csUnitInfo.csuiOutlets" value="0"/>
				<s:generator val="csUnitInfo.csuiOutlets" separator=",">
					<s:iterator  id="item">
					<s:if test="item!=0">
						<div>
							${get:CsOutlets(item).csoName}							
						<input type="hidden" name="csUnitInfo.csuiOutlets" value="${item}"/><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
					</s:iterator>
				</s:generator>
			</div>
			<button type="button" style="float:left;" onclick="$.getInput({type:'combox',title:'选择网点',action:$(this).attr('action'),callback:function(id,text){$('#csuiOutlets').append('<div>'+text+'<input type=&quot;hidden&quot; name=&quot;csUnitInfo.csuiOutlets&quot; value=&quot;'+parseInt(id)+'&quot;/><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}});" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={csoHost}&csoHost={csuiHost}" class="button">添加</button>
		 </s:else>
	 	 <em></em>
		</dd>
		
		</s:if>
		<s:else>
		${lz:set("注释","****指定网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiOutlets">${csUnitInfo.csuiOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csUnitInfo.csuiOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitInfo.csuiOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiOutlets}
	</s:if>
	
	${lz:set("注释","*****************计费策略字段的输入框代码*****************")}
	${lz:set("注释","before$csuiFeeType和after$csuiFeeType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiFeeType==true">
	${before$csuiFeeType}
	<dl class="csuiFeeType " major  ref="csuiFeeType" >
		<dt>计费策略:</dt>
		<s:if test="#request.CTRL.e.csuiFeeType==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitInfo$csuiFeeType!=null">${csUnitInfo$csuiFeeType}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/user/type_query.do?size=-1" id="csuiFeeType" name="csUnitInfo.csuiFeeType">
		 				<option selected value="${csUnitInfo.csuiFeeType}">
		 					${get:CsUserType(csUnitInfo.csuiFeeType).csutName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuiFeeType').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?key='+$('#csuiFeeType').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****计费策略字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiFeeType">${csUnitInfo.csuiFeeType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/type_details.do?key=${csUnitInfo.csuiFeeType}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitInfo.csuiFeeType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiFeeType}
	</s:if>
	
	${lz:set("注释","*****************结算方式字段的输入框代码*****************")}
	${lz:set("注释","before$csuiSettleType和after$csuiSettleType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiSettleType==true">
	${before$csuiSettleType}
	<dl class="csuiSettleType " major  ref="csuiSettleType" >
		<dt>结算方式:</dt>
		<s:if test="#request.CTRL.e.csuiSettleType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitInfo$csuiSettleType!=null">${csUnitInfo$csuiSettleType}</s:if><s:else>
		 	<select class="narrow" id="csuiSettleType" name="csUnitInfo.csuiSettleType">
		 		<option value="">请选择</option>
				<option value="0" ${csUnitInfo.csuiSettleType==0?"selected":""}>后结算模式</option>
				<option value="1" ${csUnitInfo.csuiSettleType==1?"selected":""}>预付款模式</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业信息的结算方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结算方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiSettleType">${csUnitInfo.csuiSettleType}</textarea>
		 		<span>
		 	
			 ${csUnitInfo.csuiSettleType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiSettleType}
	</s:if>
	
	${lz:set("注释","*****************价格配置字段的输入框代码*****************")}
	${lz:set("注释","before$feeset和after$feeset变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.feeset==true">
	${before$feeset}
	<dl class="feeset " minor  ref="feeset" style="width:98%;">
		<dt>价格配置:</dt>
		<s:if test="#request.CTRL.e.feeset==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csUnitInfo$feeset!=null">${csUnitInfo$feeset}</s:if><s:else>
		  		<s:if test="csUnitInfo.csuiId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="feeset" name="feeset" frameborder='0' src="" url="${basePath}${proname}/unit/fee.do?inFrame=true&canQuery=false&all=true&csufUnitInfo=${csUnitInfo.csuiId}&ctrl={queryable:false,fields:{csufUnitInfo:{visible:true,editable:false,defaultValue:${csUnitInfo.csuiId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#feeset').attr('src',$('#feeset').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑价格配置
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****价格配置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csUnitInfo.csuiId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="feeset" name="feeset" frameborder='0' src="" url="${basePath}${proname}/unit/fee.do?inFrame=true&canQuery=false&all=true&csufUnitInfo=${csUnitInfo.csuiId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#feeset').attr('src',$('#feeset').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$feeset}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuiUpdateTime和after$csuiUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiUpdateTime==true">
	${before$csuiUpdateTime}
	<dl class="csuiUpdateTime " major  ref="csuiUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csuiUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitInfo$csuiUpdateTime!=null">${csUnitInfo$csuiUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitInfo.csuiUpdateTime" id="csuiUpdateTime"  value="<s:date name="csUnitInfo.csuiUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业信息的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiUpdateTime">${csUnitInfo.csuiUpdateTime}</textarea>
		 		<span>
		 	
			 ${csUnitInfo.csuiUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuiAddTime和after$csuiAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiAddTime==true">
	${before$csuiAddTime}
	<dl class="csuiAddTime " major  ref="csuiAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csuiAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitInfo$csuiAddTime!=null">${csUnitInfo$csuiAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitInfo.csuiAddTime" id="csuiAddTime"  value="<s:date name="csUnitInfo.csuiAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业信息的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiAddTime">${csUnitInfo.csuiAddTime}</textarea>
		 		<span>
		 	
			 ${csUnitInfo.csuiAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csuiRemark和after$csuiRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiRemark==true">
	${before$csuiRemark}
	<dl class="csuiRemark " minor  ref="csuiRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csuiRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUnitInfo$csuiRemark!=null">${csUnitInfo$csuiRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csuiRemark" name="csUnitInfo.csuiRemark" rows="5">${csUnitInfo.csuiRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业信息的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUnitInfo.csuiRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csuiStatus和after$csuiStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuiStatus==true">
	${before$csuiStatus}
	<dl class="csuiStatus " major  ref="csuiStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csuiStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitInfo$csuiStatus!=null">${csUnitInfo$csuiStatus}</s:if><s:else>
		 	<select class="narrow" id="csuiStatus" name="csUnitInfo.csuiStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csUnitInfo.csuiStatus==1?"selected":""}>正常</option>
				<option value="0" ${csUnitInfo.csuiStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业信息的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuiStatus">${csUnitInfo.csuiStatus}</textarea>
		 		<span>
		 	
			 ${csUnitInfo.csuiStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuiStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUnitInfo.csuiId}" name="csUnitInfo.csuiId" id="csuiId" />
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