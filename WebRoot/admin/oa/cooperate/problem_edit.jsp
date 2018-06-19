<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/cooperate/problem.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/oa/cooperate/problem.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbProblem.tbpId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"tbpId,tbpHost,tbpProfile,tbpType,tbpMember,tbpOrder,tbpRelate,tbpDealer,tbpMemo",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbpId,tbpHost,tbpProfile,tbpType,tbpMember,tbpOrder,tbpRelate,tbpDealer,tbpMemo",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"tbpId,tbpHost,tbpProfile,tbpType,tbpMember,tbpOrder,tbpRelate,tbpDealer,tbpResult,tbpMemo,tbpStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbpId,tbpHost,tbpProfile,tbpType,tbpMember,tbpOrder,tbpRelate,tbpAdder,tbpEditor,tbpDealer,records,tbpResult,tbpMemo,tbpStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"问题故障报修编辑":CTRL.title}</title>
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
	$("#problemForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.tbpId==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpHost==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpProfile==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpProfile":function(el){
			if(jQuery.trim(el.value)=="")
				return "问题描述不能为空";
			if(el.value.length>512 && el.value.indexOf("[*]")==-1)
				return "问题描述最大长度为512个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpType==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择问题类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpMember==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpOrder==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpRelate==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpRelate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpAdder==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpEditor==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpDealer==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpDealer":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpResult==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpResult":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "结案描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpRemark==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpMemo==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpMemo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpData==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpData":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "程序数据最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpAddTime==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpStatus==true">
		${lz:set("haveEditable",true)}
		,"tbProblem.tbpStatus":function(el){
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
	<form class="form editform" ref="TbProblem" id="problemForm" name="problemForm" action="${empty CTRL.action?"problem_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写问题故障报修相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${tbProblemToken}" name="tbProblemToken" id="tbProblemToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty tbProblem.tbpId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$tbpId和after$tbpId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpId==true">
	${before$tbpId}
	<dl class="tbpId ${CTRL.e.tbpId?"hidden":""}" major  ref="tbpId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.tbpId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.tbProblem$tbpId!=null">${tbProblem$tbpId}</s:if><s:else>
			<input type="hidden" value="${tbProblem.tbpId}" name="tbProblem.tbpId" id="tbpId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpId">${tbProblem.tbpId}</textarea>
		 		<span>
		 	
			 ${tbProblem.tbpId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$tbpHost和after$tbpHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpHost==true">
	${before$tbpHost}
	<dl class="tbpHost " major  ref="tbpHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.tbpHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbProblem$tbpHost!=null">${tbProblem$tbpHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="tbpHost" name="tbProblem.tbpHost">
		 				<option selected value="${tbProblem.tbpHost}">
		 					${get:SrvHost(tbProblem.tbpHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbpHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#tbpHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpHost">${tbProblem.tbpHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${tbProblem.tbpHost}',{ctrl:{editable:false,visible:true}})">
			 ${tbProblem.tbpHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpHost}
	</s:if>
	
	${lz:set("注释","*****************问题描述字段的输入框代码*****************")}
	${lz:set("注释","before$tbpProfile和after$tbpProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpProfile==true">
	${before$tbpProfile}
	<dl class="tbpProfile " major  ref="tbpProfile" style="width:98%;">
		<dt>问题描述:</dt>
		<s:if test="#request.CTRL.e.tbpProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbProblem$tbpProfile!=null">${tbProblem$tbpProfile}</s:if><s:else>
		 	<textarea class="input wide"  id="tbpProfile" name="tbProblem.tbpProfile" rows="5">${tbProblem.tbpProfile}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入问题故障报修的问题描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****问题描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbProblem.tbpProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpProfile}
	</s:if>
	
	${lz:set("注释","*****************问题类型字段的输入框代码*****************")}
	${lz:set("注释","before$tbpType和after$tbpType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpType==true">
	${before$tbpType}
	<dl class="tbpType " major  ref="tbpType" >
		<dt>问题类型:</dt>
		<s:if test="#request.CTRL.e.tbpType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbProblem$tbpType!=null">${tbProblem$tbpType}</s:if><s:else>
		 	<select class="narrow" id="tbpType" name="tbProblem.tbpType">
		 		<option value="">请选择</option>
				<option value="0" ${tbProblem.tbpType==0?"selected":""}>服务</option>
				<option value="1" ${tbProblem.tbpType==1?"selected":""}>产品</option>
				<option value="2" ${tbProblem.tbpType==2?"selected":""}>系统</option>
				<option value="3" ${tbProblem.tbpType==3?"selected":""}>设备</option>
				<option value="4" ${tbProblem.tbpType==4?"selected":""}>车辆</option>
				<option value="5" ${tbProblem.tbpType==5?"selected":""}>网点</option>
				<option value="6" ${tbProblem.tbpType==6?"selected":""}>电桩</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择问题故障报修的问题类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****问题类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpType">${tbProblem.tbpType}</textarea>
		 		<span>
		 	
			 ${tbProblem.tbpType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpType}
	</s:if>
	
	${lz:set("注释","*****************反馈会员字段的输入框代码*****************")}
	${lz:set("注释","before$tbpMember和after$tbpMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpMember==true">
	${before$tbpMember}
	<dl class="tbpMember " major  ref="tbpMember" >
		<dt>反馈会员:</dt>
		<s:if test="#request.CTRL.e.tbpMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbProblem$tbpMember!=null">${tbProblem$tbpMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={tbpHost}" type="text" id="tbpMember" name="tbProblem.tbpMember" text="${get:CsMember(tbProblem.tbpMember).csmName}" value="${tbProblem.tbpMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbpMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#tbpMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****反馈会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpMember">${tbProblem.tbpMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${tbProblem.tbpMember}',{ctrl:{editable:false,visible:true}})">
			 ${tbProblem.tbpMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpMember}
	</s:if>
	
	${lz:set("注释","*****************所属订单字段的输入框代码*****************")}
	${lz:set("注释","before$tbpOrder和after$tbpOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpOrder==true">
	${before$tbpOrder}
	<dl class="tbpOrder " major  ref="tbpOrder" >
		<dt>所属订单:</dt>
		<s:if test="#request.CTRL.e.tbpOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbProblem$tbpOrder!=null">${tbProblem$tbpOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={tbpHost}" type="text" id="tbpOrder" name="tbProblem.tbpOrder" text="${get:CsOrder(tbProblem.tbpOrder).csoId}" value="${tbProblem.tbpOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbpOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#tbpOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpOrder">${tbProblem.tbpOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${tbProblem.tbpOrder}',{ctrl:{editable:false,visible:true}})">
			 ${tbProblem.tbpOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpOrder}
	</s:if>
	
	${lz:set("注释","*****************关联对象字段的输入框代码*****************")}
	${lz:set("注释","before$tbpRelate和after$tbpRelate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpRelate==true">
	${before$tbpRelate}
	<dl class="tbpRelate " major  ref="tbpRelate" >
		<dt>关联对象:</dt>
		<s:if test="#request.CTRL.e.tbpRelate==true">
		${lz:set("haveEditable",true)}
		<dd input="generic">
		<s:if test="#request.tbProblem$tbpRelate!=null">${tbProblem$tbpRelate}</s:if><s:else>
		 	<input title="请输入动态查询" class="combox narrow" generic="tbpRelateGeneric" size="16" type="text" id="tbpRelate" name="tbProblem.tbpRelate" text="${tbProblem.tbpRelate$}" value="${tbProblem.tbpRelate}"/>
		 	${lz:set("models",get:models("CsCar,CsOutlets,CsPowerPile"))}
		 	<select id="tbpRelateGeneric" style="display:none;">
		 		<option value="">--选择模块--</option>
		 		<s:iterator value="#request.models" id="item" status="i">
		 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
		 		</s:iterator>
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联对象字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpRelate">${tbProblem.tbpRelate}</textarea>
		 		<span>
		 	
			 ${tbProblem.tbpRelate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpRelate}
	</s:if>
	
	${lz:set("注释","*****************添加人员字段的输入框代码*****************")}
	${lz:set("注释","before$tbpAdder和after$tbpAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpAdder==true">
	${before$tbpAdder}
	<dl class="tbpAdder " major  ref="tbpAdder" >
		<dt>添加人员:</dt>
		<s:if test="#request.CTRL.e.tbpAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbProblem$tbpAdder!=null">${tbProblem$tbpAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbpAdder" name="tbProblem.tbpAdder">
		 				<option selected value="${tbProblem.tbpAdder}">
		 					${get:SrvUser(tbProblem.tbpAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbpAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbpAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpAdder">${tbProblem.tbpAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbProblem.tbpAdder}',{ctrl:{editable:false,visible:true}})">
			 ${tbProblem.tbpAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpAdder}
	</s:if>
	
	${lz:set("注释","*****************修改人员字段的输入框代码*****************")}
	${lz:set("注释","before$tbpEditor和after$tbpEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpEditor==true">
	${before$tbpEditor}
	<dl class="tbpEditor " major  ref="tbpEditor" >
		<dt>修改人员:</dt>
		<s:if test="#request.CTRL.e.tbpEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbProblem$tbpEditor!=null">${tbProblem$tbpEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbpEditor" name="tbProblem.tbpEditor">
		 				<option selected value="${tbProblem.tbpEditor}">
		 					${get:SrvUser(tbProblem.tbpEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbpEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbpEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpEditor">${tbProblem.tbpEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbProblem.tbpEditor}',{ctrl:{editable:false,visible:true}})">
			 ${tbProblem.tbpEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpEditor}
	</s:if>
	
	${lz:set("注释","*****************任务指派字段的输入框代码*****************")}
	${lz:set("注释","before$tbpDealer和after$tbpDealer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpDealer==true">
	${before$tbpDealer}
	<dl class="tbpDealer " major  ref="tbpDealer" >
		<dt>任务指派:</dt>
		<s:if test="#request.CTRL.e.tbpDealer==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbProblem$tbpDealer!=null">${tbProblem$tbpDealer}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbpDealer" name="tbProblem.tbpDealer">
		 				<option selected value="${tbProblem.tbpDealer}">
		 					${get:SrvUser(tbProblem.tbpDealer).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbpDealer').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbpDealer').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****任务指派字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpDealer">${tbProblem.tbpDealer}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbProblem.tbpDealer}',{ctrl:{editable:false,visible:true}})">
			 ${tbProblem.tbpDealer$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpDealer}
	</s:if>
	
	${lz:set("注释","*****************处理过程字段的输入框代码*****************")}
	${lz:set("注释","before$records和after$records变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.records==true">
	${before$records}
	<dl class="records " minor  ref="records" style="width:98%;">
		<dt>处理过程:</dt>
		<s:if test="#request.CTRL.e.records==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.tbProblem$records!=null">${tbProblem$records}</s:if><s:else>
		  		<s:if test="tbProblem.tbpId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="records" name="records" frameborder='0' src="" url="${basePath}${proname}/oa/cooperate/solve.do?inFrame=true&canQuery=false&fields=tbsId,tbsDesc,tbsProplem,tbsUser,tbsRedirect,tbsStatus&tbsProplem=${tbProblem.tbpId}&ctrl={queryable:false,fields:{tbsProplem:{visible:true,editable:false,defaultValue:${tbProblem.tbpId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#records').attr('src',$('#records').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑处理过程
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理过程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="tbProblem.tbpId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="records" name="records" frameborder='0' src="" url="${basePath}${proname}/oa/cooperate/solve.do?inFrame=true&canQuery=false&fields=tbsId,tbsDesc,tbsProplem,tbsUser,tbsRedirect,tbsStatus&tbsProplem=${tbProblem.tbpId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#records').attr('src',$('#records').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$records}
	</s:if>
	
	${lz:set("注释","*****************结案描述字段的输入框代码*****************")}
	${lz:set("注释","before$tbpResult和after$tbpResult变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpResult==true">
	${before$tbpResult}
	<dl class="tbpResult " major  ref="tbpResult" style="width:98%;">
		<dt>结案描述:</dt>
		<s:if test="#request.CTRL.e.tbpResult==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbProblem$tbpResult!=null">${tbProblem$tbpResult}</s:if><s:else>
		 	<textarea class="input wide"  id="tbpResult" name="tbProblem.tbpResult" rows="5">${tbProblem.tbpResult}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入问题故障报修的结案描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结案描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbProblem.tbpResult$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpResult}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$tbpRemark和after$tbpRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpRemark==true">
	${before$tbpRemark}
	<dl class="tbpRemark " minor  ref="tbpRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.tbpRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbProblem$tbpRemark!=null">${tbProblem$tbpRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="tbpRemark" name="tbProblem.tbpRemark" rows="5">${tbProblem.tbpRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入问题故障报修的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbProblem.tbpRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpRemark}
	</s:if>
	
	${lz:set("注释","*****************备忘信息字段的输入框代码*****************")}
	${lz:set("注释","before$tbpMemo和after$tbpMemo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpMemo==true">
	${before$tbpMemo}
	<dl class="tbpMemo " minor  ref="tbpMemo" style="width:98%;">
		<dt>备忘信息:</dt>
		<s:if test="#request.CTRL.e.tbpMemo==true">
		${lz:set("haveEditable",true)}
		<dd input="grid">
		<s:if test="#request.tbProblem$tbpMemo!=null">${tbProblem$tbpMemo}</s:if><s:else>
		    <textarea class="input wide grid" grid="[
		    	['备忘内容',null,'textarea','content',null]
		    	,['签名',100,'text','sign',null]
		    ]" id="tbpMemo" name="tbProblem.tbpMemo">${tbProblem.tbpMemo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="tbpMemo">${tbProblem.tbpMemo}</textarea>
		 		<span>
		 	
			 ${tbProblem.tbpMemo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpMemo}
	</s:if>
	
	${lz:set("注释","*****************程序数据字段的输入框代码*****************")}
	${lz:set("注释","before$tbpData和after$tbpData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpData==true">
	${before$tbpData}
	<dl class="tbpData " minor  ref="tbpData" style="width:98%;">
		<dt>程序数据:</dt>
		<s:if test="#request.CTRL.e.tbpData==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbProblem$tbpData!=null">${tbProblem$tbpData}</s:if><s:else>
		 	<textarea class="input wide"  id="tbpData" name="tbProblem.tbpData" rows="5">${tbProblem.tbpData}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入问题故障报修的程序数据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbProblem.tbpData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpData}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbpUpdateTime和after$tbpUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpUpdateTime==true">
	${before$tbpUpdateTime}
	<dl class="tbpUpdateTime " major  ref="tbpUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.tbpUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbProblem$tbpUpdateTime!=null">${tbProblem$tbpUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbProblem.tbpUpdateTime" id="tbpUpdateTime"  value="<s:date name="tbProblem.tbpUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择问题故障报修的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpUpdateTime">${tbProblem.tbpUpdateTime}</textarea>
		 		<span>
		 	
			 ${tbProblem.tbpUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbpAddTime和after$tbpAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpAddTime==true">
	${before$tbpAddTime}
	<dl class="tbpAddTime " major  ref="tbpAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.tbpAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbProblem$tbpAddTime!=null">${tbProblem$tbpAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbProblem.tbpAddTime" id="tbpAddTime"  value="<s:date name="tbProblem.tbpAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择问题故障报修的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpAddTime">${tbProblem.tbpAddTime}</textarea>
		 		<span>
		 	
			 ${tbProblem.tbpAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$tbpStatus和after$tbpStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpStatus==true">
	${before$tbpStatus}
	<dl class="tbpStatus " major  ref="tbpStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.tbpStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbProblem$tbpStatus!=null">${tbProblem$tbpStatus}</s:if><s:else>
		 	<select class="narrow" id="tbpStatus" name="tbProblem.tbpStatus">
		 		<option value="">请选择</option>
				<option value="0" ${tbProblem.tbpStatus==0?"selected":""}>待处理</option>
				<option value="1" ${tbProblem.tbpStatus==1?"selected":""}>正在处理</option>
				<option value="2" ${tbProblem.tbpStatus==2?"selected":""}>已处理</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择问题故障报修的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpStatus">${tbProblem.tbpStatus}</textarea>
		 		<span>
		 	
			 ${tbProblem.tbpStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${tbProblem.tbpId}" name="tbProblem.tbpId" id="tbpId" />
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