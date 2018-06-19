<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/chjsign.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/chjsign.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csChjSign.cscsId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscsId,cscsHost,cscsName,cscsMobile,cscsMember,cscsTakeTime,cscsRetTime,cscsModel,cscsOutlets,cscsTarget,cscsLocked,cscsLockTime,cscsRemark,cscsYear,cscsVisitChannel,cscsStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscsId,cscsHost,cscsName,cscsMobile,cscsMember,cscsTakeTime,cscsRetTime,cscsModel,cscsOutlets,cscsTarget,cscsLocked,cscsLockTime,cscsRemark,cscsYear,cscsVisitChannel,cscsStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscsId,cscsHost,cscsName,cscsMobile,cscsMember,cscsTakeTime,cscsRetTime,cscsModel,cscsOutlets,cscsTarget,cscsLocked,cscsLockTime,cscsRemark,cscsYear,cscsVisitChannel,cscsStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscsId,cscsHost,cscsName,cscsMobile,cscsMember,cscsTakeTime,cscsRetTime,cscsModel,cscsOutlets,cscsTarget,cscsLocked,cscsLockTime,cscsRemark,cscsYear,cscsVisitChannel,cscsStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"春节套餐报名编辑":CTRL.title}</title>
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
	$("#chjsignForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscsId==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsHost==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsName==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsName":function(el){
			if(jQuery.trim(el.value)=="")
				return "姓名不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsMobile==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsMobile":function(el){
			if(jQuery.trim(el.value)=="")
				return "手机号码不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "手机号码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsMember==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsTakeTime==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsTakeTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsRetTime==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsRetTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsModel==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsModel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsOutlets==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsOutlets":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsTarget==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsTarget":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "目的地最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsLocked==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsLocked":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsLockTime==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsLockTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsRemark==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsYear==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsYear":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsVisitChannel==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsVisitChannel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsAddTime==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsStatus==true">
		${lz:set("haveEditable",true)}
		,"csChjSign.cscsStatus":function(el){
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
	<form class="form editform" ref="CsChjSign" id="chjsignForm" name="chjsignForm" action="${empty CTRL.action?"chjsign_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写春节套餐报名相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csChjSignToken}" name="csChjSignToken" id="csChjSignToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csChjSign.cscsId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscsId和after$cscsId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsId==true">
	${before$cscsId}
	<dl class="cscsId ${CTRL.e.cscsId?"hidden":""}" major  ref="cscsId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscsId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csChjSign$cscsId!=null">${csChjSign$cscsId}</s:if><s:else>
			<input type="hidden" value="${csChjSign.cscsId}" name="csChjSign.cscsId" id="cscsId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsId">${csChjSign.cscsId}</textarea>
		 		<span>
		 	
			 ${csChjSign.cscsId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscsHost和after$cscsHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsHost==true">
	${before$cscsHost}
	<dl class="cscsHost " major  ref="cscsHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscsHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csChjSign$cscsHost!=null">${csChjSign$cscsHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscsHost" name="csChjSign.cscsHost">
		 				<option selected value="${csChjSign.cscsHost}">
		 					${get:SrvHost(csChjSign.cscsHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscsHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscsHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsHost">${csChjSign.cscsHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csChjSign.cscsHost}',{ctrl:{editable:false,visible:true}})">
			 ${csChjSign.cscsHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsHost}
	</s:if>
	
	${lz:set("注释","*****************姓名字段的输入框代码*****************")}
	${lz:set("注释","before$cscsName和after$cscsName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsName==true">
	${before$cscsName}
	<dl class="cscsName " major  ref="cscsName" >
		<dt>姓　　名:</dt>
		<s:if test="#request.CTRL.e.cscsName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChjSign$cscsName!=null">${csChjSign$cscsName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csChjSign.cscsName" id="cscsName"  value="${csChjSign.cscsName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入春节套餐报名的姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsName">${csChjSign.cscsName}</textarea>
		 		<span>
		 	
			 ${csChjSign.cscsName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsName}
	</s:if>
	
	${lz:set("注释","*****************手机号码字段的输入框代码*****************")}
	${lz:set("注释","before$cscsMobile和after$cscsMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsMobile==true">
	${before$cscsMobile}
	<dl class="cscsMobile " major  ref="cscsMobile" >
		<dt>手机号码:</dt>
		<s:if test="#request.CTRL.e.cscsMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChjSign$cscsMobile!=null">${csChjSign$cscsMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csChjSign.cscsMobile" id="cscsMobile"  value="${csChjSign.cscsMobile}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入春节套餐报名的手机号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****手机号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsMobile">${csChjSign.cscsMobile}</textarea>
		 		<span>
		 	
			 ${csChjSign.cscsMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsMobile}
	</s:if>
	
	${lz:set("注释","*****************关联会员字段的输入框代码*****************")}
	${lz:set("注释","before$cscsMember和after$cscsMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsMember==true">
	${before$cscsMember}
	<dl class="cscsMember " major  ref="cscsMember" >
		<dt>关联会员:</dt>
		<s:if test="#request.CTRL.e.cscsMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csChjSign$cscsMember!=null">${csChjSign$cscsMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={cscsHost}" type="text" id="cscsMember" name="csChjSign.cscsMember" text="${get:CsMember(csChjSign.cscsMember).csmName}" value="${csChjSign.cscsMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscsMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cscsMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsMember">${csChjSign.cscsMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csChjSign.cscsMember}',{ctrl:{editable:false,visible:true}})">
			 ${csChjSign.cscsMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsMember}
	</s:if>
	
	${lz:set("注释","*****************取车时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscsTakeTime和after$cscsTakeTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsTakeTime==true">
	${before$cscsTakeTime}
	<dl class="cscsTakeTime " major  ref="cscsTakeTime" >
		<dt>取车时间:</dt>
		<s:if test="#request.CTRL.e.cscsTakeTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChjSign$cscsTakeTime!=null">${csChjSign$cscsTakeTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChjSign.cscsTakeTime" id="cscsTakeTime"  value="<s:date name="csChjSign.cscsTakeTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择春节套餐报名的取车时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****取车时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsTakeTime">${csChjSign.cscsTakeTime}</textarea>
		 		<span>
		 	
			 ${csChjSign.cscsTakeTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsTakeTime}
	</s:if>
	
	${lz:set("注释","*****************还车时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscsRetTime和after$cscsRetTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsRetTime==true">
	${before$cscsRetTime}
	<dl class="cscsRetTime " major  ref="cscsRetTime" >
		<dt>还车时间:</dt>
		<s:if test="#request.CTRL.e.cscsRetTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChjSign$cscsRetTime!=null">${csChjSign$cscsRetTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChjSign.cscsRetTime" id="cscsRetTime"  value="<s:date name="csChjSign.cscsRetTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择春节套餐报名的还车时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****还车时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsRetTime">${csChjSign.cscsRetTime}</textarea>
		 		<span>
		 	
			 ${csChjSign.cscsRetTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsRetTime}
	</s:if>
	
	${lz:set("注释","*****************预定车型字段的输入框代码*****************")}
	${lz:set("注释","before$cscsModel和after$cscsModel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsModel==true">
	${before$cscsModel}
	<dl class="cscsModel " major  ref="cscsModel" >
		<dt>预定车型:</dt>
		<s:if test="#request.CTRL.e.cscsModel==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csChjSign$cscsModel!=null">${csChjSign$cscsModel}</s:if><s:else>
		 			<input title="请输入车型车型名称进行查询" class="combox narrow" action="${basePath}${proname}/object/carmodel_query.do?value={param}" type="text" id="cscsModel" name="csChjSign.cscsModel" text="${get:CsCarModel(csChjSign.cscsModel).cscmName}" value="${csChjSign.cscsModel}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscsModel').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?key='+$('#cscsModel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预定车型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsModel">${csChjSign.cscsModel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${csChjSign.cscsModel}',{ctrl:{editable:false,visible:true}})">
			 ${csChjSign.cscsModel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsModel}
	</s:if>
	
	${lz:set("注释","*****************预定网点字段的输入框代码*****************")}
	${lz:set("注释","before$cscsOutlets和after$cscsOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsOutlets==true">
	${before$cscsOutlets}
	<dl class="cscsOutlets " major  ref="cscsOutlets" >
		<dt>预定网点:</dt>
		<s:if test="#request.CTRL.e.cscsOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csChjSign$cscsOutlets!=null">${csChjSign$cscsOutlets}</s:if><s:else>
		 			<input title="请输入网点名称进行查询" class="combox narrow" action="${basePath}${proname}/object/outlets_query.do?value={param}&csoHost={cscsHost}" type="text" id="cscsOutlets" name="csChjSign.cscsOutlets" text="${get:CsOutlets(csChjSign.cscsOutlets).csoName}" value="${csChjSign.cscsOutlets}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscsOutlets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#cscsOutlets').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预定网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsOutlets">${csChjSign.cscsOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csChjSign.cscsOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csChjSign.cscsOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsOutlets}
	</s:if>
	
	${lz:set("注释","*****************目的地字段的输入框代码*****************")}
	${lz:set("注释","before$cscsTarget和after$cscsTarget变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsTarget==true">
	${before$cscsTarget}
	<dl class="cscsTarget " major  ref="cscsTarget" >
		<dt>目&nbsp;&nbsp;的&nbsp;&nbsp;地:</dt>
		<s:if test="#request.CTRL.e.cscsTarget==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChjSign$cscsTarget!=null">${csChjSign$cscsTarget}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csChjSign.cscsTarget" id="cscsTarget"  value="${csChjSign.cscsTarget}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入春节套餐报名的目的地</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****目的地字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsTarget">${csChjSign.cscsTarget}</textarea>
		 		<span>
		 	
			 ${csChjSign.cscsTarget$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsTarget}
	</s:if>
	
	${lz:set("注释","*****************预定套餐字段的输入框代码*****************")}
	${lz:set("注释","before$cscsLocked和after$cscsLocked变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsLocked==true">
	${before$cscsLocked}
	<dl class="cscsLocked " major  ref="cscsLocked" >
		<dt>预定套餐:</dt>
		<s:if test="#request.CTRL.e.cscsLocked==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csChjSign$cscsLocked!=null">${csChjSign$cscsLocked}</s:if><s:else>
		 			<input title="请输入系统套餐套餐名称进行查询" class="combox narrow" action="${basePath}${proname}/fee/pack_query.do?value={param}" type="text" id="cscsLocked" name="csChjSign.cscsLocked" text="${get:CsPack(csChjSign.cscsLocked).cspName}" value="${csChjSign.cscsLocked}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscsLocked').val())==''){return;};window.href('${basePath}${proname}/fee/pack_details.do?key='+$('#cscsLocked').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预定套餐字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsLocked">${csChjSign.cscsLocked}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/pack_details.do?key=${csChjSign.cscsLocked}',{ctrl:{editable:false,visible:true}})">
			 ${csChjSign.cscsLocked$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsLocked}
	</s:if>
	
	${lz:set("注释","*****************预定时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscsLockTime和after$cscsLockTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsLockTime==true">
	${before$cscsLockTime}
	<dl class="cscsLockTime " major  ref="cscsLockTime" >
		<dt>预定时间:</dt>
		<s:if test="#request.CTRL.e.cscsLockTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChjSign$cscsLockTime!=null">${csChjSign$cscsLockTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChjSign.cscsLockTime" id="cscsLockTime"  value="<s:date name="csChjSign.cscsLockTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择春节套餐报名的预定时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预定时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsLockTime">${csChjSign.cscsLockTime}</textarea>
		 		<span>
		 	
			 ${csChjSign.cscsLockTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsLockTime}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$cscsRemark和after$cscsRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsRemark==true">
	${before$cscsRemark}
	<dl class="cscsRemark " minor  ref="cscsRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.cscsRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csChjSign$cscsRemark!=null">${csChjSign$cscsRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cscsRemark" name="csChjSign.cscsRemark" rows="5">${csChjSign.cscsRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入春节套餐报名的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csChjSign.cscsRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsRemark}
	</s:if>
	
	${lz:set("注释","*****************年份字段的输入框代码*****************")}
	${lz:set("注释","before$cscsYear和after$cscsYear变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsYear==true">
	${before$cscsYear}
	<dl class="cscsYear " major  ref="cscsYear" >
		<dt>年　　份:</dt>
		<s:if test="#request.CTRL.e.cscsYear==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChjSign$cscsYear!=null">${csChjSign$cscsYear}</s:if><s:else>
		 	<select class="narrow" id="cscsYear" name="csChjSign.cscsYear">
		 		<option value="">请选择</option>
				<option value="2014" ${csChjSign.cscsYear==2014?"selected":""}>2014年</option>
				<option value="2015" ${csChjSign.cscsYear==2015?"selected":""}>2015年</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择春节套餐报名的年份</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****年份字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsYear">${csChjSign.cscsYear}</textarea>
		 		<span>
		 	
			 ${csChjSign.cscsYear$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsYear}
	</s:if>
	
	${lz:set("注释","*****************渠道来源字段的输入框代码*****************")}
	${lz:set("注释","before$cscsVisitChannel和after$cscsVisitChannel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsVisitChannel==true">
	${before$cscsVisitChannel}
	<dl class="cscsVisitChannel " major  ref="cscsVisitChannel" >
		<dt>渠道来源:</dt>
		<s:if test="#request.CTRL.e.cscsVisitChannel==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csChjSign$cscsVisitChannel!=null">${csChjSign$cscsVisitChannel}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/extension/channel_query.do?size=-1&cscHost={cscsHost}" id="cscsVisitChannel" name="csChjSign.cscsVisitChannel">
		 				<option selected value="${csChjSign.cscsVisitChannel}">
		 					${get:CsChannel(csChjSign.cscsVisitChannel).cscName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscsVisitChannel').val())==''){return;};window.href('${basePath}${proname}/extension/channel_details.do?key='+$('#cscsVisitChannel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****渠道来源字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsVisitChannel">${csChjSign.cscsVisitChannel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/extension/channel_details.do?key=${csChjSign.cscsVisitChannel}',{ctrl:{editable:false,visible:true}})">
			 ${csChjSign.cscsVisitChannel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsVisitChannel}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscsUpdateTime和after$cscsUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsUpdateTime==true">
	${before$cscsUpdateTime}
	<dl class="cscsUpdateTime " major  ref="cscsUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cscsUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChjSign$cscsUpdateTime!=null">${csChjSign$cscsUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChjSign.cscsUpdateTime" id="cscsUpdateTime"  value="<s:date name="csChjSign.cscsUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择春节套餐报名的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsUpdateTime">${csChjSign.cscsUpdateTime}</textarea>
		 		<span>
		 	
			 ${csChjSign.cscsUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscsAddTime和after$cscsAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsAddTime==true">
	${before$cscsAddTime}
	<dl class="cscsAddTime " major  ref="cscsAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscsAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChjSign$cscsAddTime!=null">${csChjSign$cscsAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChjSign.cscsAddTime" id="cscsAddTime"  value="<s:date name="csChjSign.cscsAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择春节套餐报名的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsAddTime">${csChjSign.cscsAddTime}</textarea>
		 		<span>
		 	
			 ${csChjSign.cscsAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscsStatus和after$cscsStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsStatus==true">
	${before$cscsStatus}
	<dl class="cscsStatus " major  ref="cscsStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscsStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChjSign$cscsStatus!=null">${csChjSign$cscsStatus}</s:if><s:else>
		 	<select class="narrow" id="cscsStatus" name="csChjSign.cscsStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csChjSign.cscsStatus==0?"selected":""}>已报名</option>
				<option value="1" ${csChjSign.cscsStatus==1?"selected":""}>已预定</option>
				<option value="2" ${csChjSign.cscsStatus==2?"selected":""}>已购买</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择春节套餐报名的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsStatus">${csChjSign.cscsStatus}</textarea>
		 		<span>
		 	
			 ${csChjSign.cscsStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csChjSign.cscsId}" name="csChjSign.cscsId" id="cscsId" />
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