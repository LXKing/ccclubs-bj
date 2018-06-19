<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/sale/custom.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/sale/custom.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbSaleCustom.tbscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"tbscId,tbscHost,tbscName,tbscBattle,tbscEvent,tbscGroup,tbscMobile,tbscNumber,tbscEmail,tbscQq,tbscOthrer,tbscProfile,tbscLevel,tbscFormal,tbscServer,tbscMemo,tbscStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbscId,tbscHost,tbscName,tbscBattle,tbscEvent,tbscGroup,tbscMobile,tbscNumber,tbscEmail,tbscQq,tbscOthrer,tbscProfile,tbscLevel,tbscFormal,tbscServer,tbscMemo,tbscStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"tbscId,tbscHost,tbscName,tbscBattle,tbscEvent,tbscGroup,tbscMobile,tbscNumber,tbscEmail,tbscQq,tbscOthrer,tbscProfile,tbscLevel,tbscFormal,tbscServer,tbscMemo,tbscStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbscId,tbscHost,tbscName,tbscBattle,tbscEvent,tbscGroup,tbscMobile,tbscNumber,tbscEmail,tbscQq,tbscOthrer,tbscProfile,tbscLevel,tbscFormal,tbscServer,tbscAdder,tbscEditor,tbscMemo,tbscStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"客户资料编辑":CTRL.title}</title>
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
	$("#customForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.tbscId==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscHost==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscName==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscName":function(el){
			if(jQuery.trim(el.value)=="")
				return "客户名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "客户名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscBattle==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscBattle":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscEvent==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscEvent":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscGroup==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscGroup":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscMobile==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscMobile":function(el){
			if(jQuery.trim(el.value)=="")
				return "联系手机不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系手机最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscNumber==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscNumber":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscEmail==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscEmail":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系邮箱最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscQq==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscQq":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系QQ最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscOthrer==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscOthrer":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "其它联系方式最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscProfile==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscProfile":function(el){
			if(jQuery.trim(el.value)=="")
				return "业务方向不能为空";
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "业务方向最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscLevel==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscLevel":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择会员级别";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscFormal==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscFormal":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择意向阶段";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscServer==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscServer":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择服务阶段";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscOnwer==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscOnwer":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscAdder==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscEditor==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscRemark==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscMemo==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscMemo":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "备忘信息最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscData==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscData":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "程序数据最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscAddTime==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscStatus==true">
		${lz:set("haveEditable",true)}
		,"tbSaleCustom.tbscStatus":function(el){
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
	<form class="form editform" ref="TbSaleCustom" id="customForm" name="customForm" action="${empty CTRL.action?"custom_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写客户资料相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${tbSaleCustomToken}" name="tbSaleCustomToken" id="tbSaleCustomToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty tbSaleCustom.tbscId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$tbscId和after$tbscId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscId==true">
	${before$tbscId}
	<dl class="tbscId ${CTRL.e.tbscId?"hidden":""}" major  ref="tbscId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.tbscId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.tbSaleCustom$tbscId!=null">${tbSaleCustom$tbscId}</s:if><s:else>
			<input type="hidden" value="${tbSaleCustom.tbscId}" name="tbSaleCustom.tbscId" id="tbscId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscId">${tbSaleCustom.tbscId}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$tbscHost和after$tbscHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscHost==true">
	${before$tbscHost}
	<dl class="tbscHost " major  ref="tbscHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.tbscHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleCustom$tbscHost!=null">${tbSaleCustom$tbscHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="tbscHost" name="tbSaleCustom.tbscHost">
		 				<option selected value="${tbSaleCustom.tbscHost}">
		 					${get:SrvHost(tbSaleCustom.tbscHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#tbscHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscHost">${tbSaleCustom.tbscHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${tbSaleCustom.tbscHost}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleCustom.tbscHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscHost}
	</s:if>
	
	${lz:set("注释","*****************客户名称字段的输入框代码*****************")}
	${lz:set("注释","before$tbscName和after$tbscName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscName==true">
	${before$tbscName}
	<dl class="tbscName " major  ref="tbscName" >
		<dt>客户名称:</dt>
		<s:if test="#request.CTRL.e.tbscName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbSaleCustom$tbscName!=null">${tbSaleCustom$tbscName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbSaleCustom.tbscName" id="tbscName"  value="${tbSaleCustom.tbscName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入客户资料的客户名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****客户名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscName">${tbSaleCustom.tbscName}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscName}
	</s:if>
	
	${lz:set("注释","*****************归属战役字段的输入框代码*****************")}
	${lz:set("注释","before$tbscBattle和after$tbscBattle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscBattle==true">
	${before$tbscBattle}
	<dl class="tbscBattle " major  ref="tbscBattle" >
		<dt>归属战役:</dt>
		<s:if test="#request.CTRL.e.tbscBattle==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbSaleCustom$tbscBattle!=null">${tbSaleCustom$tbscBattle}</s:if><s:else>
		 			<input title="请输入销售战役战役标题进行查询" class="combox narrow" action="${basePath}${proname}/sale/battle_query.do?value={param}&tbsbHost={tbscHost}" type="text" id="tbscBattle" name="tbSaleCustom.tbscBattle" text="${get:TbSaleBattle(tbSaleCustom.tbscBattle).tbsbTitle}" value="${tbSaleCustom.tbscBattle}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscBattle').val())==''){return;};window.href('${basePath}${proname}/sale/battle_details.do?key='+$('#tbscBattle').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****归属战役字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscBattle">${tbSaleCustom.tbscBattle}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/battle_details.do?key=${tbSaleCustom.tbscBattle}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleCustom.tbscBattle$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscBattle}
	</s:if>
	
	${lz:set("注释","*****************归属活动字段的输入框代码*****************")}
	${lz:set("注释","before$tbscEvent和after$tbscEvent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscEvent==true">
	${before$tbscEvent}
	<dl class="tbscEvent " major  ref="tbscEvent" >
		<dt>归属活动:</dt>
		<s:if test="#request.CTRL.e.tbscEvent==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbSaleCustom$tbscEvent!=null">${tbSaleCustom$tbscEvent}</s:if><s:else>
		 			<input title="请输入销售活动活动主题进行查询" class="combox narrow" action="${basePath}${proname}/sale/event_query.do?value={param}&csseHost={tbscHost}" type="text" id="tbscEvent" name="tbSaleCustom.tbscEvent" text="${get:CsSaleEvent(tbSaleCustom.tbscEvent).csseSubject}" value="${tbSaleCustom.tbscEvent}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscEvent').val())==''){return;};window.href('${basePath}${proname}/sale/event_details.do?key='+$('#tbscEvent').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****归属活动字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscEvent">${tbSaleCustom.tbscEvent}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/event_details.do?key=${tbSaleCustom.tbscEvent}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleCustom.tbscEvent$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscEvent}
	</s:if>
	
	${lz:set("注释","*****************所属组织字段的输入框代码*****************")}
	${lz:set("注释","before$tbscGroup和after$tbscGroup变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscGroup==true">
	${before$tbscGroup}
	<dl class="tbscGroup " major  ref="tbscGroup" >
		<dt>所属组织:</dt>
		<s:if test="#request.CTRL.e.tbscGroup==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbSaleCustom$tbscGroup!=null">${tbSaleCustom$tbscGroup}</s:if><s:else>
		 			<input title="请输入客户组织组织名称进行查询" class="combox narrow" action="${basePath}${proname}/sale/customgroup_query.do?value={param}&tbcgHost={tbscHost}" type="text" id="tbscGroup" name="tbSaleCustom.tbscGroup" text="${get:TbCustomGroup(tbSaleCustom.tbscGroup).tbcgName}" value="${tbSaleCustom.tbscGroup}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscGroup').val())==''){return;};window.href('${basePath}${proname}/sale/customgroup_details.do?key='+$('#tbscGroup').val(),{ctrl:{editable:false}})"></a>
		 			<a class="reladd" config="{
		 				action:'${basePath}${proname}/sale/customgroup_edit.do?rd=1'
		 				,callback:function(object){			 					
		 					var input = $('#tbscGroup');
		 					input.val(object.tbcgId);
		 					input.prev().html(object.tbcgName);
		 				}
		 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属组织字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscGroup">${tbSaleCustom.tbscGroup}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/customgroup_details.do?key=${tbSaleCustom.tbscGroup}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleCustom.tbscGroup$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscGroup}
	</s:if>
	
	${lz:set("注释","*****************联系手机字段的输入框代码*****************")}
	${lz:set("注释","before$tbscMobile和after$tbscMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscMobile==true">
	${before$tbscMobile}
	<dl class="tbscMobile " major  ref="tbscMobile" >
		<dt>联系手机:</dt>
		<s:if test="#request.CTRL.e.tbscMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbSaleCustom$tbscMobile!=null">${tbSaleCustom$tbscMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbSaleCustom.tbscMobile" id="tbscMobile"  value="${tbSaleCustom.tbscMobile}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入客户资料的联系手机</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系手机字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscMobile">${tbSaleCustom.tbscMobile}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscMobile}
	</s:if>
	
	${lz:set("注释","*****************联系电话字段的输入框代码*****************")}
	${lz:set("注释","before$tbscNumber和after$tbscNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscNumber==true">
	${before$tbscNumber}
	<dl class="tbscNumber " minor  ref="tbscNumber" >
		<dt>联系电话:</dt>
		<s:if test="#request.CTRL.e.tbscNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbSaleCustom$tbscNumber!=null">${tbSaleCustom$tbscNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbSaleCustom.tbscNumber" id="tbscNumber"  value="${tbSaleCustom.tbscNumber}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户资料的联系电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscNumber">${tbSaleCustom.tbscNumber}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscNumber}
	</s:if>
	
	${lz:set("注释","*****************联系邮箱字段的输入框代码*****************")}
	${lz:set("注释","before$tbscEmail和after$tbscEmail变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscEmail==true">
	${before$tbscEmail}
	<dl class="tbscEmail " minor  ref="tbscEmail" >
		<dt>联系邮箱:</dt>
		<s:if test="#request.CTRL.e.tbscEmail==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbSaleCustom$tbscEmail!=null">${tbSaleCustom$tbscEmail}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbSaleCustom.tbscEmail" id="tbscEmail"  value="${tbSaleCustom.tbscEmail}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户资料的联系邮箱</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系邮箱字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscEmail">${tbSaleCustom.tbscEmail}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscEmail$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscEmail}
	</s:if>
	
	${lz:set("注释","*****************联系QQ字段的输入框代码*****************")}
	${lz:set("注释","before$tbscQq和after$tbscQq变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscQq==true">
	${before$tbscQq}
	<dl class="tbscQq " minor  ref="tbscQq" >
		<dt>联系QQ:</dt>
		<s:if test="#request.CTRL.e.tbscQq==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbSaleCustom$tbscQq!=null">${tbSaleCustom$tbscQq}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbSaleCustom.tbscQq" id="tbscQq"  value="${tbSaleCustom.tbscQq}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户资料的联系QQ</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系QQ字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscQq">${tbSaleCustom.tbscQq}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscQq$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscQq}
	</s:if>
	
	${lz:set("注释","*****************其它联系方式字段的输入框代码*****************")}
	${lz:set("注释","before$tbscOthrer和after$tbscOthrer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscOthrer==true">
	${before$tbscOthrer}
	<dl class="tbscOthrer " minor  ref="tbscOthrer" >
		<dt>其它联系方式:</dt>
		<s:if test="#request.CTRL.e.tbscOthrer==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbSaleCustom$tbscOthrer!=null">${tbSaleCustom$tbscOthrer}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbSaleCustom.tbscOthrer" id="tbscOthrer"  value="${tbSaleCustom.tbscOthrer}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户资料的其它联系方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****其它联系方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscOthrer">${tbSaleCustom.tbscOthrer}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscOthrer$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscOthrer}
	</s:if>
	
	${lz:set("注释","*****************业务方向字段的输入框代码*****************")}
	${lz:set("注释","before$tbscProfile和after$tbscProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscProfile==true">
	${before$tbscProfile}
	<dl class="tbscProfile " major  ref="tbscProfile" style="width:98%;">
		<dt>业务方向:</dt>
		<s:if test="#request.CTRL.e.tbscProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbSaleCustom$tbscProfile!=null">${tbSaleCustom$tbscProfile}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="tbSaleCustom.tbscProfile" id="tbscProfile"  value="${tbSaleCustom.tbscProfile}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>描述将会和此客户将来会有什么业务方面的合作</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****业务方向字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="tbscProfile">${tbSaleCustom.tbscProfile}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscProfile}
	</s:if>
	
	${lz:set("注释","*****************会员级别字段的输入框代码*****************")}
	${lz:set("注释","before$tbscLevel和after$tbscLevel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscLevel==true">
	${before$tbscLevel}
	<dl class="tbscLevel " major  ref="tbscLevel" >
		<dt>会员级别:</dt>
		<s:if test="#request.CTRL.e.tbscLevel==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbSaleCustom$tbscLevel!=null">${tbSaleCustom$tbscLevel}</s:if><s:else>
		 	<select class="narrow" id="tbscLevel" name="tbSaleCustom.tbscLevel">
		 		<option value="">请选择</option>
				<option value="0" ${tbSaleCustom.tbscLevel==0?"selected":""}>普通客户</option>
				<option value="1" ${tbSaleCustom.tbscLevel==1?"selected":""}>重要客户</option>
				<option value="2" ${tbSaleCustom.tbscLevel==2?"selected":""}>集团大客户</option>
				<option value="3" ${tbSaleCustom.tbscLevel==3?"selected":""}>政府大客户</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户资料的会员级别</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员级别字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscLevel">${tbSaleCustom.tbscLevel}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscLevel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscLevel}
	</s:if>
	
	${lz:set("注释","*****************意向阶段字段的输入框代码*****************")}
	${lz:set("注释","before$tbscFormal和after$tbscFormal变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscFormal==true">
	${before$tbscFormal}
	<dl class="tbscFormal " major  ref="tbscFormal" >
		<dt>意向阶段:</dt>
		<s:if test="#request.CTRL.e.tbscFormal==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbSaleCustom$tbscFormal!=null">${tbSaleCustom$tbscFormal}</s:if><s:else>
		 	<select class="narrow" id="tbscFormal" name="tbSaleCustom.tbscFormal">
		 		<option value="">请选择</option>
				<option value="0" ${tbSaleCustom.tbscFormal==0?"selected":""}>暂无意向</option>
				<option value="1" ${tbSaleCustom.tbscFormal==1?"selected":""}>意向一般</option>
				<option value="2" ${tbSaleCustom.tbscFormal==2?"selected":""}>意向强烈</option>
				<option value="3" ${tbSaleCustom.tbscFormal==3?"selected":""}>售前跟踪</option>
				<option value="4" ${tbSaleCustom.tbscFormal==4?"selected":""}>合同执行</option>
				<option value="5" ${tbSaleCustom.tbscFormal==5?"selected":""}>售后服务</option>
				<option value="6" ${tbSaleCustom.tbscFormal==6?"selected":""}>合同期满</option>
				<option value="7" ${tbSaleCustom.tbscFormal==7?"selected":""}>失败</option>
				<option value="8" ${tbSaleCustom.tbscFormal==8?"selected":""}>搁置</option>
				<option value="9" ${tbSaleCustom.tbscFormal==9?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户资料的意向阶段</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****意向阶段字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscFormal">${tbSaleCustom.tbscFormal}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscFormal$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscFormal}
	</s:if>
	
	${lz:set("注释","*****************服务阶段字段的输入框代码*****************")}
	${lz:set("注释","before$tbscServer和after$tbscServer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscServer==true">
	${before$tbscServer}
	<dl class="tbscServer " major  ref="tbscServer" >
		<dt>服务阶段:</dt>
		<s:if test="#request.CTRL.e.tbscServer==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbSaleCustom$tbscServer!=null">${tbSaleCustom$tbscServer}</s:if><s:else>
		 	<select class="narrow" id="tbscServer" name="tbSaleCustom.tbscServer">
		 		<option value="">请选择</option>
		 		 <option value="">无法自动构造</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户资料的服务阶段</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****服务阶段字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscServer">${tbSaleCustom.tbscServer}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscServer$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscServer}
	</s:if>
	
	${lz:set("注释","*****************所有人字段的输入框代码*****************")}
	${lz:set("注释","before$tbscOnwer和after$tbscOnwer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscOnwer==true">
	${before$tbscOnwer}
	<dl class="tbscOnwer " major  ref="tbscOnwer" >
		<dt>所&nbsp;&nbsp;有&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbscOnwer==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleCustom$tbscOnwer!=null">${tbSaleCustom$tbscOnwer}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbscOnwer" name="tbSaleCustom.tbscOnwer">
		 				<option selected value="${tbSaleCustom.tbscOnwer}">
		 					${get:SrvUser(tbSaleCustom.tbscOnwer).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscOnwer').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbscOnwer').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所有人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscOnwer">${tbSaleCustom.tbscOnwer}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscOnwer$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscOnwer}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$tbscAdder和after$tbscAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscAdder==true">
	${before$tbscAdder}
	<dl class="tbscAdder " major  ref="tbscAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbscAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleCustom$tbscAdder!=null">${tbSaleCustom$tbscAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbscAdder" name="tbSaleCustom.tbscAdder">
		 				<option selected value="${tbSaleCustom.tbscAdder}">
		 					${get:SrvUser(tbSaleCustom.tbscAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbscAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscAdder">${tbSaleCustom.tbscAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbSaleCustom.tbscAdder}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleCustom.tbscAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscAdder}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$tbscEditor和after$tbscEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscEditor==true">
	${before$tbscEditor}
	<dl class="tbscEditor " major  ref="tbscEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbscEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleCustom$tbscEditor!=null">${tbSaleCustom$tbscEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbscEditor" name="tbSaleCustom.tbscEditor">
		 				<option selected value="${tbSaleCustom.tbscEditor}">
		 					${get:SrvUser(tbSaleCustom.tbscEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbscEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscEditor">${tbSaleCustom.tbscEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbSaleCustom.tbscEditor}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleCustom.tbscEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscEditor}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$tbscRemark和after$tbscRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscRemark==true">
	${before$tbscRemark}
	<dl class="tbscRemark " major  ref="tbscRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.tbscRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbSaleCustom$tbscRemark!=null">${tbSaleCustom$tbscRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="tbscRemark" name="tbSaleCustom.tbscRemark" rows="5">${tbSaleCustom.tbscRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户资料的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbSaleCustom.tbscRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscRemark}
	</s:if>
	
	${lz:set("注释","*****************备忘信息字段的输入框代码*****************")}
	${lz:set("注释","before$tbscMemo和after$tbscMemo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscMemo==true">
	${before$tbscMemo}
	<dl class="tbscMemo " minor  ref="tbscMemo" style="width:98%;">
		<dt>备忘信息:</dt>
		<s:if test="#request.CTRL.e.tbscMemo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbSaleCustom$tbscMemo!=null">${tbSaleCustom$tbscMemo}</s:if><s:else>
		 	<textarea class="input wide"  id="tbscMemo" name="tbSaleCustom.tbscMemo" rows="5">${tbSaleCustom.tbscMemo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户资料的备忘信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbSaleCustom.tbscMemo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscMemo}
	</s:if>
	
	${lz:set("注释","*****************程序数据字段的输入框代码*****************")}
	${lz:set("注释","before$tbscData和after$tbscData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscData==true">
	${before$tbscData}
	<dl class="tbscData " minor  ref="tbscData" style="width:98%;">
		<dt>程序数据:</dt>
		<s:if test="#request.CTRL.e.tbscData==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbSaleCustom$tbscData!=null">${tbSaleCustom$tbscData}</s:if><s:else>
		 	<textarea class="input wide"  id="tbscData" name="tbSaleCustom.tbscData" rows="5">${tbSaleCustom.tbscData}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户资料的程序数据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbSaleCustom.tbscData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscData}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbscUpdateTime和after$tbscUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscUpdateTime==true">
	${before$tbscUpdateTime}
	<dl class="tbscUpdateTime " major  ref="tbscUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.tbscUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbSaleCustom$tbscUpdateTime!=null">${tbSaleCustom$tbscUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbSaleCustom.tbscUpdateTime" id="tbscUpdateTime"  value="<s:date name="tbSaleCustom.tbscUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户资料的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscUpdateTime">${tbSaleCustom.tbscUpdateTime}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbscAddTime和after$tbscAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscAddTime==true">
	${before$tbscAddTime}
	<dl class="tbscAddTime " major  ref="tbscAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.tbscAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbSaleCustom$tbscAddTime!=null">${tbSaleCustom$tbscAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbSaleCustom.tbscAddTime" id="tbscAddTime"  value="<s:date name="tbSaleCustom.tbscAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户资料的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscAddTime">${tbSaleCustom.tbscAddTime}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$tbscStatus和after$tbscStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscStatus==true">
	${before$tbscStatus}
	<dl class="tbscStatus " major  ref="tbscStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.tbscStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbSaleCustom$tbscStatus!=null">${tbSaleCustom$tbscStatus}</s:if><s:else>
		 	<select class="narrow" id="tbscStatus" name="tbSaleCustom.tbscStatus">
		 		<option value="">请选择</option>
				<option value="1" ${tbSaleCustom.tbscStatus==1?"selected":""}>正常</option>
				<option value="0" ${tbSaleCustom.tbscStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户资料的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscStatus">${tbSaleCustom.tbscStatus}</textarea>
		 		<span>
		 	
			 ${tbSaleCustom.tbscStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${tbSaleCustom.tbscId}" name="tbSaleCustom.tbscId" id="tbscId" />
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