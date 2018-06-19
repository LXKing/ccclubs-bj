<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/sale/customgroup.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/sale/customgroup.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbCustomGroup.tbcgId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"tbcgId,tbcgHost,tbcgBattle,tbcgEvent,tbcgName,tbcgFullName,tbcgType,tbcgTrade,tbcgService,tbcgScale,tbcgAddress,tbcgConcact,tbcgNumber,tbcgDetail,tbcgAdder,tbcgMemo,tbcgStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbcgId,tbcgHost,tbcgBattle,tbcgEvent,tbcgName,tbcgFullName,tbcgType,tbcgTrade,tbcgService,tbcgScale,tbcgAddress,tbcgConcact,tbcgNumber,tbcgDetail,tbcgAdder,tbcgMemo,tbcgStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"tbcgId,tbcgHost,tbcgBattle,tbcgEvent,tbcgName,tbcgFullName,tbcgType,tbcgTrade,tbcgService,tbcgScale,tbcgAddress,tbcgConcact,tbcgNumber,tbcgDetail,tbcgAdder,tbcgMemo,tbcgStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbcgId,tbcgHost,tbcgBattle,tbcgEvent,tbcgName,tbcgFullName,tbcgType,tbcgTrade,tbcgService,tbcgScale,tbcgAddress,tbcgConcact,tbcgNumber,tbcgDetail,tbcgAdder,tbcgEditor,tbcgMemo,tbcgStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"客户组织编辑":CTRL.title}</title>
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
		if($("#tbcgDetail").size()>0){
			 //构造编辑页tbCustomGroup.tbcgDetail为html编辑控件
			 KE.init({
				id : 'tbcgDetail',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=customgroup_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("tbcgDetail");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#customgroupForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.tbcgId==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgHost==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgBattle==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgBattle":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgEvent==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgEvent":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgName==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgName":function(el){
			if(jQuery.trim(el.value)=="")
				return "组织名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "组织名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgFullName==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgFullName":function(el){
			if(jQuery.trim(el.value)=="")
				return "组织全称不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "组织全称最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgType==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择组织类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgTrade==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgTrade":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择行业";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgService==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgService":function(el){
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "主营业务最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgScale==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgScale":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgAddress==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgAddress":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "详细地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgConcact==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgConcact":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系人最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgNumber==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgNumber":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgDetail==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgDetail":function(el){
			if($("#tbcgDetail").get(0))
			 	KE.sync("tbcgDetail");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgOnwer==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgOnwer":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgAdder==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgEditor==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgRemark==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgMemo==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgMemo":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "备忘信息最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgData==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgData":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "程序数据最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgAddTime==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbcgStatus==true">
		${lz:set("haveEditable",true)}
		,"tbCustomGroup.tbcgStatus":function(el){
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
	<form class="form editform" ref="TbCustomGroup" id="customgroupForm" name="customgroupForm" action="${empty CTRL.action?"customgroup_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写客户组织相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${tbCustomGroupToken}" name="tbCustomGroupToken" id="tbCustomGroupToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty tbCustomGroup.tbcgId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgId和after$tbcgId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgId==true">
	${before$tbcgId}
	<dl class="tbcgId ${CTRL.e.tbcgId?"hidden":""}" major  ref="tbcgId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.tbcgId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.tbCustomGroup$tbcgId!=null">${tbCustomGroup$tbcgId}</s:if><s:else>
			<input type="hidden" value="${tbCustomGroup.tbcgId}" name="tbCustomGroup.tbcgId" id="tbcgId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgId">${tbCustomGroup.tbcgId}</textarea>
		 		<span>
		 	
			 ${tbCustomGroup.tbcgId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgHost和after$tbcgHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgHost==true">
	${before$tbcgHost}
	<dl class="tbcgHost " major  ref="tbcgHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.tbcgHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbCustomGroup$tbcgHost!=null">${tbCustomGroup$tbcgHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="tbcgHost" name="tbCustomGroup.tbcgHost">
		 				<option selected value="${tbCustomGroup.tbcgHost}">
		 					${get:SrvHost(tbCustomGroup.tbcgHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbcgHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#tbcgHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgHost">${tbCustomGroup.tbcgHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${tbCustomGroup.tbcgHost}',{ctrl:{editable:false,visible:true}})">
			 ${tbCustomGroup.tbcgHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgHost}
	</s:if>
	
	${lz:set("注释","*****************归属战役字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgBattle和after$tbcgBattle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgBattle==true">
	${before$tbcgBattle}
	<dl class="tbcgBattle " major  ref="tbcgBattle" >
		<dt>归属战役:</dt>
		<s:if test="#request.CTRL.e.tbcgBattle==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbCustomGroup$tbcgBattle!=null">${tbCustomGroup$tbcgBattle}</s:if><s:else>
		 			<input title="请输入销售战役战役标题进行查询" class="combox narrow" action="${basePath}${proname}/sale/battle_query.do?value={param}&tbsbHost={tbcgHost}" type="text" id="tbcgBattle" name="tbCustomGroup.tbcgBattle" text="${get:TbSaleBattle(tbCustomGroup.tbcgBattle).tbsbTitle}" value="${tbCustomGroup.tbcgBattle}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbcgBattle').val())==''){return;};window.href('${basePath}${proname}/sale/battle_details.do?key='+$('#tbcgBattle').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****归属战役字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgBattle">${tbCustomGroup.tbcgBattle}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/battle_details.do?key=${tbCustomGroup.tbcgBattle}',{ctrl:{editable:false,visible:true}})">
			 ${tbCustomGroup.tbcgBattle$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgBattle}
	</s:if>
	
	${lz:set("注释","*****************归属活动字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgEvent和after$tbcgEvent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgEvent==true">
	${before$tbcgEvent}
	<dl class="tbcgEvent " major  ref="tbcgEvent" >
		<dt>归属活动:</dt>
		<s:if test="#request.CTRL.e.tbcgEvent==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbCustomGroup$tbcgEvent!=null">${tbCustomGroup$tbcgEvent}</s:if><s:else>
		 			<input title="请输入销售活动活动主题进行查询" class="combox narrow" action="${basePath}${proname}/sale/event_query.do?value={param}&csseHost={tbcgHost}" type="text" id="tbcgEvent" name="tbCustomGroup.tbcgEvent" text="${get:CsSaleEvent(tbCustomGroup.tbcgEvent).csseSubject}" value="${tbCustomGroup.tbcgEvent}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbcgEvent').val())==''){return;};window.href('${basePath}${proname}/sale/event_details.do?key='+$('#tbcgEvent').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****归属活动字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgEvent">${tbCustomGroup.tbcgEvent}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/event_details.do?key=${tbCustomGroup.tbcgEvent}',{ctrl:{editable:false,visible:true}})">
			 ${tbCustomGroup.tbcgEvent$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgEvent}
	</s:if>
	
	${lz:set("注释","*****************组织名称字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgName和after$tbcgName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgName==true">
	${before$tbcgName}
	<dl class="tbcgName " major  ref="tbcgName" >
		<dt>组织名称:</dt>
		<s:if test="#request.CTRL.e.tbcgName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbCustomGroup$tbcgName!=null">${tbCustomGroup$tbcgName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbCustomGroup.tbcgName" id="tbcgName"  value="${tbCustomGroup.tbcgName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入客户组织的组织名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****组织名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgName">${tbCustomGroup.tbcgName}</textarea>
		 		<span>
		 	
			 ${tbCustomGroup.tbcgName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgName}
	</s:if>
	
	${lz:set("注释","*****************组织全称字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgFullName和after$tbcgFullName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgFullName==true">
	${before$tbcgFullName}
	<dl class="tbcgFullName " major  ref="tbcgFullName" style="width:98%;">
		<dt>组织全称:</dt>
		<s:if test="#request.CTRL.e.tbcgFullName==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbCustomGroup$tbcgFullName!=null">${tbCustomGroup$tbcgFullName}</s:if><s:else>
		 	<textarea class="input wide"  id="tbcgFullName" name="tbCustomGroup.tbcgFullName" rows="5">${tbCustomGroup.tbcgFullName}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入客户组织的组织全称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****组织全称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbCustomGroup.tbcgFullName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgFullName}
	</s:if>
	
	${lz:set("注释","*****************组织类型字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgType和after$tbcgType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgType==true">
	${before$tbcgType}
	<dl class="tbcgType " major  ref="tbcgType" >
		<dt>组织类型:</dt>
		<s:if test="#request.CTRL.e.tbcgType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbCustomGroup$tbcgType!=null">${tbCustomGroup$tbcgType}</s:if><s:else>
		 	<select class="narrow" id="tbcgType" name="tbCustomGroup.tbcgType">
		 		<option value="">请选择</option>
				<option value="1" ${tbCustomGroup.tbcgType==1?"selected":""}>公司企业</option>
				<option value="2" ${tbCustomGroup.tbcgType==2?"selected":""}>事业单位</option>
				<option value="3" ${tbCustomGroup.tbcgType==3?"selected":""}>政府机构</option>
				<option value="4" ${tbCustomGroup.tbcgType==4?"selected":""}>社会团体</option>
				<option value="0" ${tbCustomGroup.tbcgType==0?"selected":""}>其它</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户组织的组织类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****组织类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgType">${tbCustomGroup.tbcgType}</textarea>
		 		<span>
		 	
			 ${tbCustomGroup.tbcgType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgType}
	</s:if>
	
	${lz:set("注释","*****************行业字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgTrade和after$tbcgTrade变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgTrade==true">
	${before$tbcgTrade}
	<dl class="tbcgTrade " major  ref="tbcgTrade" >
		<dt>行　　业:</dt>
		<s:if test="#request.CTRL.e.tbcgTrade==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbCustomGroup$tbcgTrade!=null">${tbCustomGroup$tbcgTrade}</s:if><s:else>
		 	<select class="narrow" id="tbcgTrade" name="tbCustomGroup.tbcgTrade">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("客户组织行业","customgroup_trade"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${tbCustomGroup.tbcgTrade==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbcgTrade').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#tbcgTrade').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("customgroup_trade").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#tbcgTrade');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户组织的行业</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****行业字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgTrade">${tbCustomGroup.tbcgTrade}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${tbCustomGroup.tbcgTrade}',{ctrl:{editable:false,visible:true}})">
			 ${tbCustomGroup.tbcgTrade$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgTrade}
	</s:if>
	
	${lz:set("注释","*****************主营业务字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgService和after$tbcgService变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgService==true">
	${before$tbcgService}
	<dl class="tbcgService " minor  ref="tbcgService" style="width:98%;">
		<dt>主营业务:</dt>
		<s:if test="#request.CTRL.e.tbcgService==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbCustomGroup$tbcgService!=null">${tbCustomGroup$tbcgService}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="tbCustomGroup.tbcgService" id="tbcgService"  value="${tbCustomGroup.tbcgService}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户组织的主营业务</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****主营业务字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="tbcgService">${tbCustomGroup.tbcgService}</textarea>
		 		<span>
		 	
			 ${tbCustomGroup.tbcgService$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgService}
	</s:if>
	
	${lz:set("注释","*****************人员规模字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgScale和after$tbcgScale变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgScale==true">
	${before$tbcgScale}
	<dl class="tbcgScale " minor  ref="tbcgScale" >
		<dt>人员规模:</dt>
		<s:if test="#request.CTRL.e.tbcgScale==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbCustomGroup$tbcgScale!=null">${tbCustomGroup$tbcgScale}</s:if><s:else>
		 	<select class="narrow" id="tbcgScale" name="tbCustomGroup.tbcgScale">
		 		<option value="">请选择</option>
				<option value="0" ${tbCustomGroup.tbcgScale==0?"selected":""}>20人以内</option>
				<option value="1" ${tbCustomGroup.tbcgScale==1?"selected":""}>20到50人</option>
				<option value="2" ${tbCustomGroup.tbcgScale==2?"selected":""}>50到100人</option>
				<option value="3" ${tbCustomGroup.tbcgScale==3?"selected":""}>100到200人</option>
				<option value="4" ${tbCustomGroup.tbcgScale==4?"selected":""}>200人以上</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择客户组织的人员规模</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人员规模字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgScale">${tbCustomGroup.tbcgScale}</textarea>
		 		<span>
		 	
			 ${tbCustomGroup.tbcgScale$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgScale}
	</s:if>
	
	${lz:set("注释","*****************详细地址字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgAddress和after$tbcgAddress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgAddress==true">
	${before$tbcgAddress}
	<dl class="tbcgAddress " minor  ref="tbcgAddress" style="width:98%;">
		<dt>详细地址:</dt>
		<s:if test="#request.CTRL.e.tbcgAddress==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbCustomGroup$tbcgAddress!=null">${tbCustomGroup$tbcgAddress}</s:if><s:else>
		 	<textarea class="input wide"  id="tbcgAddress" name="tbCustomGroup.tbcgAddress" rows="5">${tbCustomGroup.tbcgAddress}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户组织的详细地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****详细地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbCustomGroup.tbcgAddress$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgAddress}
	</s:if>
	
	${lz:set("注释","*****************联系人字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgConcact和after$tbcgConcact变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgConcact==true">
	${before$tbcgConcact}
	<dl class="tbcgConcact " major  ref="tbcgConcact" >
		<dt>联&nbsp;&nbsp;系&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbcgConcact==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbCustomGroup$tbcgConcact!=null">${tbCustomGroup$tbcgConcact}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbCustomGroup.tbcgConcact" id="tbcgConcact"  value="${tbCustomGroup.tbcgConcact}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户组织的联系人</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgConcact">${tbCustomGroup.tbcgConcact}</textarea>
		 		<span>
		 	
			 ${tbCustomGroup.tbcgConcact$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgConcact}
	</s:if>
	
	${lz:set("注释","*****************联系电话字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgNumber和after$tbcgNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgNumber==true">
	${before$tbcgNumber}
	<dl class="tbcgNumber " major  ref="tbcgNumber" >
		<dt>联系电话:</dt>
		<s:if test="#request.CTRL.e.tbcgNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbCustomGroup$tbcgNumber!=null">${tbCustomGroup$tbcgNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbCustomGroup.tbcgNumber" id="tbcgNumber"  value="${tbCustomGroup.tbcgNumber}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户组织的联系电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgNumber">${tbCustomGroup.tbcgNumber}</textarea>
		 		<span>
		 	
			 ${tbCustomGroup.tbcgNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgNumber}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgDetail和after$tbcgDetail变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgDetail==true">
	${before$tbcgDetail}
	<dl class="tbcgDetail " minor  ref="tbcgDetail" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.tbcgDetail==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.tbCustomGroup$tbcgDetail!=null">${tbCustomGroup$tbcgDetail}</s:if><s:else>
		    <textarea class="input wide"  id="tbcgDetail" name="tbCustomGroup.tbcgDetail" style="height:300px;visibility:hidden;">${tbCustomGroup.tbcgDetail}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户组织的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbCustomGroup.tbcgDetail$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgDetail}
	</s:if>
	
	${lz:set("注释","*****************所有人字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgOnwer和after$tbcgOnwer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgOnwer==true">
	${before$tbcgOnwer}
	<dl class="tbcgOnwer " major  ref="tbcgOnwer" >
		<dt>所&nbsp;&nbsp;有&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbcgOnwer==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbCustomGroup$tbcgOnwer!=null">${tbCustomGroup$tbcgOnwer}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbcgOnwer" name="tbCustomGroup.tbcgOnwer">
		 				<option selected value="${tbCustomGroup.tbcgOnwer}">
		 					${get:SrvUser(tbCustomGroup.tbcgOnwer).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbcgOnwer').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbcgOnwer').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所有人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgOnwer">${tbCustomGroup.tbcgOnwer}</textarea>
		 		<span>
		 	
			 ${tbCustomGroup.tbcgOnwer$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgOnwer}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgAdder和after$tbcgAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgAdder==true">
	${before$tbcgAdder}
	<dl class="tbcgAdder " major  ref="tbcgAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbcgAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbCustomGroup$tbcgAdder!=null">${tbCustomGroup$tbcgAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbcgAdder" name="tbCustomGroup.tbcgAdder">
		 				<option selected value="${tbCustomGroup.tbcgAdder}">
		 					${get:SrvUser(tbCustomGroup.tbcgAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbcgAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbcgAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgAdder">${tbCustomGroup.tbcgAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbCustomGroup.tbcgAdder}',{ctrl:{editable:false,visible:true}})">
			 ${tbCustomGroup.tbcgAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgAdder}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgEditor和after$tbcgEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgEditor==true">
	${before$tbcgEditor}
	<dl class="tbcgEditor " major  ref="tbcgEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbcgEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbCustomGroup$tbcgEditor!=null">${tbCustomGroup$tbcgEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbcgEditor" name="tbCustomGroup.tbcgEditor">
		 				<option selected value="${tbCustomGroup.tbcgEditor}">
		 					${get:SrvUser(tbCustomGroup.tbcgEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbcgEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbcgEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgEditor">${tbCustomGroup.tbcgEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbCustomGroup.tbcgEditor}',{ctrl:{editable:false,visible:true}})">
			 ${tbCustomGroup.tbcgEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgEditor}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgRemark和after$tbcgRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgRemark==true">
	${before$tbcgRemark}
	<dl class="tbcgRemark " major  ref="tbcgRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.tbcgRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbCustomGroup$tbcgRemark!=null">${tbCustomGroup$tbcgRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="tbcgRemark" name="tbCustomGroup.tbcgRemark" rows="5">${tbCustomGroup.tbcgRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户组织的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbCustomGroup.tbcgRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgRemark}
	</s:if>
	
	${lz:set("注释","*****************备忘信息字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgMemo和after$tbcgMemo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgMemo==true">
	${before$tbcgMemo}
	<dl class="tbcgMemo " minor  ref="tbcgMemo" style="width:98%;">
		<dt>备忘信息:</dt>
		<s:if test="#request.CTRL.e.tbcgMemo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbCustomGroup$tbcgMemo!=null">${tbCustomGroup$tbcgMemo}</s:if><s:else>
		 	<textarea class="input wide"  id="tbcgMemo" name="tbCustomGroup.tbcgMemo" rows="5">${tbCustomGroup.tbcgMemo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户组织的备忘信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbCustomGroup.tbcgMemo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgMemo}
	</s:if>
	
	${lz:set("注释","*****************程序数据字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgData和after$tbcgData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgData==true">
	${before$tbcgData}
	<dl class="tbcgData " minor  ref="tbcgData" style="width:98%;">
		<dt>程序数据:</dt>
		<s:if test="#request.CTRL.e.tbcgData==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbCustomGroup$tbcgData!=null">${tbCustomGroup$tbcgData}</s:if><s:else>
		 	<textarea class="input wide"  id="tbcgData" name="tbCustomGroup.tbcgData" rows="5">${tbCustomGroup.tbcgData}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户组织的程序数据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbCustomGroup.tbcgData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgData}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgUpdateTime和after$tbcgUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgUpdateTime==true">
	${before$tbcgUpdateTime}
	<dl class="tbcgUpdateTime " major  ref="tbcgUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.tbcgUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbCustomGroup$tbcgUpdateTime!=null">${tbCustomGroup$tbcgUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbCustomGroup.tbcgUpdateTime" id="tbcgUpdateTime"  value="<s:date name="tbCustomGroup.tbcgUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户组织的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgUpdateTime">${tbCustomGroup.tbcgUpdateTime}</textarea>
		 		<span>
		 	
			 ${tbCustomGroup.tbcgUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgAddTime和after$tbcgAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgAddTime==true">
	${before$tbcgAddTime}
	<dl class="tbcgAddTime " major  ref="tbcgAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.tbcgAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbCustomGroup$tbcgAddTime!=null">${tbCustomGroup$tbcgAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbCustomGroup.tbcgAddTime" id="tbcgAddTime"  value="<s:date name="tbCustomGroup.tbcgAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户组织的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgAddTime">${tbCustomGroup.tbcgAddTime}</textarea>
		 		<span>
		 	
			 ${tbCustomGroup.tbcgAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$tbcgStatus和after$tbcgStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbcgStatus==true">
	${before$tbcgStatus}
	<dl class="tbcgStatus " major  ref="tbcgStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.tbcgStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbCustomGroup$tbcgStatus!=null">${tbCustomGroup$tbcgStatus}</s:if><s:else>
		 	<select class="narrow" id="tbcgStatus" name="tbCustomGroup.tbcgStatus">
		 		<option value="">请选择</option>
				<option value="1" ${tbCustomGroup.tbcgStatus==1?"selected":""}>正常</option>
				<option value="0" ${tbCustomGroup.tbcgStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户组织的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbcgStatus">${tbCustomGroup.tbcgStatus}</textarea>
		 		<span>
		 	
			 ${tbCustomGroup.tbcgStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbcgStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${tbCustomGroup.tbcgId}" name="tbCustomGroup.tbcgId" id="tbcgId" />
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