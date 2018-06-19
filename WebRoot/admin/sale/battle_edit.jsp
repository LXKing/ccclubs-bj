<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/sale/battle.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/sale/battle.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbSaleBattle.tbsbId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"tbsbId,tbsbHost,tbsbTitle,tbsbDetail,tbsbHeader,tbsbStart,tbsbFinish,tbsbStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbsbId,tbsbHost,tbsbTitle,tbsbDetail,tbsbHeader,tbsbStart,tbsbFinish,tbsbStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"tbsbId,tbsbHost,tbsbTitle,tbsbDetail,tbsbHeader,tbsbStart,tbsbFinish,tbsbStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbsbId,tbsbHost,tbsbTitle,tbsbDetail,tbsbHeader,tbsbStart,tbsbFinish,tbsbAdder,tbsbEditor,tbsbStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"销售战役编辑":CTRL.title}</title>
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
		if($("#tbsbDetail").size()>0){
			 //构造编辑页tbSaleBattle.tbsbDetail为html编辑控件
			 KE.init({
				id : 'tbsbDetail',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=battle_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("tbsbDetail");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#battleForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.tbsbId==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsbHost==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsbTitle==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "战役标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "战役标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsbDetail==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbDetail":function(el){
			if($("#tbsbDetail").get(0))
			 	KE.sync("tbsbDetail");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "战役描述最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsbHeader==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbHeader":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsbStart==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbStart":function(el){
			if(jQuery.trim(el.value)=="")
				return "开始时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsbFinish==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbFinish":function(el){
			if(jQuery.trim(el.value)=="")
				return "结束时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsbAdder==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsbEditor==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsbRemark==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsbUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsbAddTime==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsbStatus==true">
		${lz:set("haveEditable",true)}
		,"tbSaleBattle.tbsbStatus":function(el){
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
	<form class="form editform" ref="TbSaleBattle" id="battleForm" name="battleForm" action="${empty CTRL.action?"battle_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写销售战役相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${tbSaleBattleToken}" name="tbSaleBattleToken" id="tbSaleBattleToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty tbSaleBattle.tbsbId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbId和after$tbsbId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbId==true">
	${before$tbsbId}
	<dl class="tbsbId ${CTRL.e.tbsbId?"hidden":""}" major  ref="tbsbId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.tbsbId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.tbSaleBattle$tbsbId!=null">${tbSaleBattle$tbsbId}</s:if><s:else>
			<input type="hidden" value="${tbSaleBattle.tbsbId}" name="tbSaleBattle.tbsbId" id="tbsbId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsbId">${tbSaleBattle.tbsbId}</textarea>
		 		<span>
		 	
			 ${tbSaleBattle.tbsbId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbHost和after$tbsbHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbHost==true">
	${before$tbsbHost}
	<dl class="tbsbHost " major  ref="tbsbHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.tbsbHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleBattle$tbsbHost!=null">${tbSaleBattle$tbsbHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="tbsbHost" name="tbSaleBattle.tbsbHost">
		 				<option selected value="${tbSaleBattle.tbsbHost}">
		 					${get:SrvHost(tbSaleBattle.tbsbHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbsbHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#tbsbHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsbHost">${tbSaleBattle.tbsbHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${tbSaleBattle.tbsbHost}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleBattle.tbsbHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbHost}
	</s:if>
	
	${lz:set("注释","*****************战役标题字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbTitle和after$tbsbTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbTitle==true">
	${before$tbsbTitle}
	<dl class="tbsbTitle " major  ref="tbsbTitle" >
		<dt>战役标题:</dt>
		<s:if test="#request.CTRL.e.tbsbTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbSaleBattle$tbsbTitle!=null">${tbSaleBattle$tbsbTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbSaleBattle.tbsbTitle" id="tbsbTitle"  value="${tbSaleBattle.tbsbTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入销售战役的战役标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****战役标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsbTitle">${tbSaleBattle.tbsbTitle}</textarea>
		 		<span>
		 	
			 ${tbSaleBattle.tbsbTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbTitle}
	</s:if>
	
	${lz:set("注释","*****************战役描述字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbDetail和after$tbsbDetail变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbDetail==true">
	${before$tbsbDetail}
	<dl class="tbsbDetail " minor  ref="tbsbDetail" style="width:98%;">
		<dt>战役描述:</dt>
		<s:if test="#request.CTRL.e.tbsbDetail==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.tbSaleBattle$tbsbDetail!=null">${tbSaleBattle$tbsbDetail}</s:if><s:else>
		    <textarea class="input wide"  id="tbsbDetail" name="tbSaleBattle.tbsbDetail" style="height:300px;visibility:hidden;">${tbSaleBattle.tbsbDetail}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入销售战役的战役描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****战役描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbSaleBattle.tbsbDetail$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbDetail}
	</s:if>
	
	${lz:set("注释","*****************战役负责人字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbHeader和after$tbsbHeader变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbHeader==true">
	${before$tbsbHeader}
	<dl class="tbsbHeader " major  ref="tbsbHeader" >
		<dt>战役负责人:</dt>
		<s:if test="#request.CTRL.e.tbsbHeader==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleBattle$tbsbHeader!=null">${tbSaleBattle$tbsbHeader}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbsbHeader" name="tbSaleBattle.tbsbHeader">
		 				<option selected value="${tbSaleBattle.tbsbHeader}">
		 					${get:SrvUser(tbSaleBattle.tbsbHeader).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbsbHeader').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbsbHeader').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****战役负责人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsbHeader">${tbSaleBattle.tbsbHeader}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbSaleBattle.tbsbHeader}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleBattle.tbsbHeader$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbHeader}
	</s:if>
	
	${lz:set("注释","*****************开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbStart和after$tbsbStart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbStart==true">
	${before$tbsbStart}
	<dl class="tbsbStart " major  ref="tbsbStart" >
		<dt>开始时间:</dt>
		<s:if test="#request.CTRL.e.tbsbStart==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.tbSaleBattle$tbsbStart!=null">${tbSaleBattle$tbsbStart}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="tbSaleBattle.tbsbStart" id="tbsbStart"  value="<s:date name="tbSaleBattle.tbsbStart" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售战役的开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsbStart">${tbSaleBattle.tbsbStart}</textarea>
		 		<span>
		 	
			 ${tbSaleBattle.tbsbStart$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbStart}
	</s:if>
	
	${lz:set("注释","*****************结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbFinish和after$tbsbFinish变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbFinish==true">
	${before$tbsbFinish}
	<dl class="tbsbFinish " major  ref="tbsbFinish" >
		<dt>结束时间:</dt>
		<s:if test="#request.CTRL.e.tbsbFinish==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.tbSaleBattle$tbsbFinish!=null">${tbSaleBattle$tbsbFinish}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="tbSaleBattle.tbsbFinish" id="tbsbFinish"  value="<s:date name="tbSaleBattle.tbsbFinish" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售战役的结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsbFinish">${tbSaleBattle.tbsbFinish}</textarea>
		 		<span>
		 	
			 ${tbSaleBattle.tbsbFinish$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbFinish}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbAdder和after$tbsbAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbAdder==true">
	${before$tbsbAdder}
	<dl class="tbsbAdder " major  ref="tbsbAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbsbAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleBattle$tbsbAdder!=null">${tbSaleBattle$tbsbAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbsbAdder" name="tbSaleBattle.tbsbAdder">
		 				<option selected value="${tbSaleBattle.tbsbAdder}">
		 					${get:SrvUser(tbSaleBattle.tbsbAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbsbAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbsbAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsbAdder">${tbSaleBattle.tbsbAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbSaleBattle.tbsbAdder}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleBattle.tbsbAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbAdder}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbEditor和after$tbsbEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbEditor==true">
	${before$tbsbEditor}
	<dl class="tbsbEditor " major  ref="tbsbEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbsbEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleBattle$tbsbEditor!=null">${tbSaleBattle$tbsbEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbsbEditor" name="tbSaleBattle.tbsbEditor">
		 				<option selected value="${tbSaleBattle.tbsbEditor}">
		 					${get:SrvUser(tbSaleBattle.tbsbEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbsbEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbsbEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsbEditor">${tbSaleBattle.tbsbEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbSaleBattle.tbsbEditor}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleBattle.tbsbEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbEditor}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbRemark和after$tbsbRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbRemark==true">
	${before$tbsbRemark}
	<dl class="tbsbRemark " major  ref="tbsbRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.tbsbRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbSaleBattle$tbsbRemark!=null">${tbSaleBattle$tbsbRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="tbsbRemark" name="tbSaleBattle.tbsbRemark" rows="5">${tbSaleBattle.tbsbRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入销售战役的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbSaleBattle.tbsbRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbRemark}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbUpdateTime和after$tbsbUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbUpdateTime==true">
	${before$tbsbUpdateTime}
	<dl class="tbsbUpdateTime " major  ref="tbsbUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.tbsbUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbSaleBattle$tbsbUpdateTime!=null">${tbSaleBattle$tbsbUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbSaleBattle.tbsbUpdateTime" id="tbsbUpdateTime"  value="<s:date name="tbSaleBattle.tbsbUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售战役的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsbUpdateTime">${tbSaleBattle.tbsbUpdateTime}</textarea>
		 		<span>
		 	
			 ${tbSaleBattle.tbsbUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbAddTime和after$tbsbAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbAddTime==true">
	${before$tbsbAddTime}
	<dl class="tbsbAddTime " major  ref="tbsbAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.tbsbAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbSaleBattle$tbsbAddTime!=null">${tbSaleBattle$tbsbAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbSaleBattle.tbsbAddTime" id="tbsbAddTime"  value="<s:date name="tbSaleBattle.tbsbAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售战役的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsbAddTime">${tbSaleBattle.tbsbAddTime}</textarea>
		 		<span>
		 	
			 ${tbSaleBattle.tbsbAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$tbsbStatus和after$tbsbStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsbStatus==true">
	${before$tbsbStatus}
	<dl class="tbsbStatus " major  ref="tbsbStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.tbsbStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbSaleBattle$tbsbStatus!=null">${tbSaleBattle$tbsbStatus}</s:if><s:else>
		 	<select class="narrow" id="tbsbStatus" name="tbSaleBattle.tbsbStatus">
		 		<option value="">请选择</option>
				<option value="0" ${tbSaleBattle.tbsbStatus==0?"selected":""}>策划中</option>
				<option value="1" ${tbSaleBattle.tbsbStatus==1?"selected":""}>执行中</option>
				<option value="2" ${tbSaleBattle.tbsbStatus==2?"selected":""}>已完成</option>
				<option value="3" ${tbSaleBattle.tbsbStatus==3?"selected":""}>已放弃</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售战役的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsbStatus">${tbSaleBattle.tbsbStatus}</textarea>
		 		<span>
		 	
			 ${tbSaleBattle.tbsbStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsbStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${tbSaleBattle.tbsbId}" name="tbSaleBattle.tbsbId" id="tbsbId" />
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