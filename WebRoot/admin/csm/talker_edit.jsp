<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/csm/talker.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/csm/talker.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csChatTalker.csctId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csctId,csctHost,csctName,csctHeader,csctSign,csctMember,csctFlag,csctPlatform,csctCount,csctLastIp,csctServicer,csctLastTime,csctRemark,csctState,csctStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csctId,csctHost,csctName,csctHeader,csctSign,csctMember,csctFlag,csctPlatform,csctCount,csctLastIp,csctServicer,csctLastTime,csctRemark,csctState,csctStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csctId,csctHost,csctName,csctHeader,csctSign,csctMember,csctFlag,csctPlatform,csctCount,csctLastIp,csctServicer,csctLastTime,csctRemark,csctState,csctStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csctId,csctHost,csctName,csctHeader,csctSign,csctMember,csctFlag,csctPlatform,csctCount,csctLastIp,csctServicer,csctLastTime,csctRemark,csctState,csctStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"在线访客编辑":CTRL.title}</title>
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
	$("#talkerForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csctId==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctHost==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctName==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctName":function(el){
			if(jQuery.trim(el.value)=="")
				return "名称姓名不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "名称姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctHeader==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctHeader":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctSign==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctSign":function(el){
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "个性签名最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctMember==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctFlag==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "会话标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctPlatform==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctPlatform":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择来源平台";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctCount==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctCount":function(el){
			if(jQuery.trim(el.value)=="")
				return "上线次数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctLastIp==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctLastIp":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "IP地址最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctServicer==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctServicer":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctLastTime==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctLastTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctAddTime==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctRemark==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctState==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctState":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择客服状态";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csctStatus==true">
		${lz:set("haveEditable",true)}
		,"csChatTalker.csctStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择当前状态";
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
	<form class="form editform" ref="CsChatTalker" id="talkerForm" name="talkerForm" action="${empty CTRL.action?"talker_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写在线访客相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csChatTalkerToken}" name="csChatTalkerToken" id="csChatTalkerToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csChatTalker.csctId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csctId和after$csctId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctId==true">
	${before$csctId}
	<dl class="csctId ${CTRL.e.csctId?"hidden":""}" major  ref="csctId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csctId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csChatTalker$csctId!=null">${csChatTalker$csctId}</s:if><s:else>
			<input type="hidden" value="${csChatTalker.csctId}" name="csChatTalker.csctId" id="csctId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctId">${csChatTalker.csctId}</textarea>
		 		<span>
		 	
			 ${csChatTalker.csctId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csctHost和after$csctHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctHost==true">
	${before$csctHost}
	<dl class="csctHost " major  ref="csctHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csctHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csChatTalker$csctHost!=null">${csChatTalker$csctHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csctHost" name="csChatTalker.csctHost">
		 				<option selected value="${csChatTalker.csctHost}">
		 					${get:SrvHost(csChatTalker.csctHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csctHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csctHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctHost">${csChatTalker.csctHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csChatTalker.csctHost}',{ctrl:{editable:false,visible:true}})">
			 ${csChatTalker.csctHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctHost}
	</s:if>
	
	${lz:set("注释","*****************名称姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csctName和after$csctName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctName==true">
	${before$csctName}
	<dl class="csctName " major  ref="csctName" >
		<dt>名称姓名:</dt>
		<s:if test="#request.CTRL.e.csctName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChatTalker$csctName!=null">${csChatTalker$csctName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csChatTalker.csctName" id="csctName"  value="${csChatTalker.csctName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>客户姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****名称姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctName">${csChatTalker.csctName}</textarea>
		 		<span>
		 	
			 ${csChatTalker.csctName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctName}
	</s:if>
	
	${lz:set("注释","*****************使用头像字段的输入框代码*****************")}
	${lz:set("注释","before$csctHeader和after$csctHeader变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctHeader==true">
	${before$csctHeader}
	<dl class="csctHeader " major  ref="csctHeader" style="width:98%;">
		<dt>使用头像:</dt>
		<s:if test="#request.CTRL.e.csctHeader==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csChatTalker$csctHeader!=null">${csChatTalker$csctHeader}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csChatTalker.csctHeader" id="csctHeader"  value="${csChatTalker.csctHeader}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csctHeader').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#csctHeader').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#csctHeader').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传在线访客的使用头像</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用头像字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csctHeader">${csChatTalker.csctHeader}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csChatTalker.csctHeader))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csChatTalker.csctHeader}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctHeader}
	</s:if>
	
	${lz:set("注释","*****************个性签名字段的输入框代码*****************")}
	${lz:set("注释","before$csctSign和after$csctSign变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctSign==true">
	${before$csctSign}
	<dl class="csctSign " major  ref="csctSign" style="width:98%;">
		<dt>个性签名:</dt>
		<s:if test="#request.CTRL.e.csctSign==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChatTalker$csctSign!=null">${csChatTalker$csctSign}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csChatTalker.csctSign" id="csctSign"  value="${csChatTalker.csctSign}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入在线访客的个性签名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****个性签名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csctSign">${csChatTalker.csctSign}</textarea>
		 		<span>
		 	
			 ${csChatTalker.csctSign$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctSign}
	</s:if>
	
	${lz:set("注释","*****************关联会员字段的输入框代码*****************")}
	${lz:set("注释","before$csctMember和after$csctMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctMember==true">
	${before$csctMember}
	<dl class="csctMember " major  ref="csctMember" >
		<dt>关联会员:</dt>
		<s:if test="#request.CTRL.e.csctMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csChatTalker$csctMember!=null">${csChatTalker$csctMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csctHost}" type="text" id="csctMember" name="csChatTalker.csctMember" text="${get:CsMember(csChatTalker.csctMember).csmName}" value="${csChatTalker.csctMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csctMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csctMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>此项有值为会员</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctMember">${csChatTalker.csctMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csChatTalker.csctMember}',{ctrl:{editable:false,visible:true}})">
			 ${csChatTalker.csctMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctMember}
	</s:if>
	
	${lz:set("注释","*****************会话标识字段的输入框代码*****************")}
	${lz:set("注释","before$csctFlag和after$csctFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctFlag==true">
	${before$csctFlag}
	<dl class="csctFlag " minor  ref="csctFlag" >
		<dt>会话标识:</dt>
		<s:if test="#request.CTRL.e.csctFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChatTalker$csctFlag!=null">${csChatTalker$csctFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csChatTalker.csctFlag" id="csctFlag"  value="${csChatTalker.csctFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>clientid或openid</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会话标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctFlag">${csChatTalker.csctFlag}</textarea>
		 		<span>
		 	
			 ${csChatTalker.csctFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctFlag}
	</s:if>
	
	${lz:set("注释","*****************来源平台字段的输入框代码*****************")}
	${lz:set("注释","before$csctPlatform和after$csctPlatform变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctPlatform==true">
	${before$csctPlatform}
	<dl class="csctPlatform " major  ref="csctPlatform" >
		<dt>来源平台:</dt>
		<s:if test="#request.CTRL.e.csctPlatform==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChatTalker$csctPlatform!=null">${csChatTalker$csctPlatform}</s:if><s:else>
		 	<select class="narrow" id="csctPlatform" name="csChatTalker.csctPlatform">
		 		<option value="">请选择</option>
				<option value="0" ${csChatTalker.csctPlatform==0?"selected":""}>后台</option>
				<option value="1" ${csChatTalker.csctPlatform==1?"selected":""}>网站</option>
				<option value="2" ${csChatTalker.csctPlatform==2?"selected":""}>微站</option>
				<option value="3" ${csChatTalker.csctPlatform==3?"selected":""}>微信</option>
				<option value="4" ${csChatTalker.csctPlatform==4?"selected":""}>支付宝</option>
				<option value="5" ${csChatTalker.csctPlatform==5?"selected":""}>短信</option>
				<option value="6" ${csChatTalker.csctPlatform==6?"selected":""}>QQ</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择在线访客的来源平台</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****来源平台字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctPlatform">${csChatTalker.csctPlatform}</textarea>
		 		<span>
		 	
			 ${csChatTalker.csctPlatform$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctPlatform}
	</s:if>
	
	${lz:set("注释","*****************上线次数字段的输入框代码*****************")}
	${lz:set("注释","before$csctCount和after$csctCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctCount==true">
	${before$csctCount}
	<dl class="csctCount " major  ref="csctCount" >
		<dt>上线次数:</dt>
		<s:if test="#request.CTRL.e.csctCount==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csChatTalker$csctCount!=null">${csChatTalker$csctCount}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csChatTalker.csctCount" id="csctCount"  value="${csChatTalker.csctCount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入在线访客的上线次数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上线次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctCount">${csChatTalker.csctCount}</textarea>
		 		<span>
		 	
			 ${csChatTalker.csctCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctCount}
	</s:if>
	
	${lz:set("注释","*****************IP地址字段的输入框代码*****************")}
	${lz:set("注释","before$csctLastIp和after$csctLastIp变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctLastIp==true">
	${before$csctLastIp}
	<dl class="csctLastIp " major  ref="csctLastIp" >
		<dt>IP地址:</dt>
		<s:if test="#request.CTRL.e.csctLastIp==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChatTalker$csctLastIp!=null">${csChatTalker$csctLastIp}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csChatTalker.csctLastIp" id="csctLastIp"  value="${csChatTalker.csctLastIp}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入在线访客的IP地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****IP地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctLastIp">${csChatTalker.csctLastIp}</textarea>
		 		<span>
		 	
			 ${csChatTalker.csctLastIp$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctLastIp}
	</s:if>
	
	${lz:set("注释","*****************当前客服字段的输入框代码*****************")}
	${lz:set("注释","before$csctServicer和after$csctServicer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctServicer==true">
	${before$csctServicer}
	<dl class="csctServicer " major  ref="csctServicer" >
		<dt>当前客服:</dt>
		<s:if test="#request.CTRL.e.csctServicer==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csChatTalker$csctServicer!=null">${csChatTalker$csctServicer}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csctServicer" name="csChatTalker.csctServicer">
		 				<option selected value="${csChatTalker.csctServicer}">
		 					${get:SrvUser(csChatTalker.csctServicer).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csctServicer').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csctServicer').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****当前客服字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctServicer">${csChatTalker.csctServicer}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csChatTalker.csctServicer}',{ctrl:{editable:false,visible:true}})">
			 ${csChatTalker.csctServicer$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctServicer}
	</s:if>
	
	${lz:set("注释","*****************最近时间字段的输入框代码*****************")}
	${lz:set("注释","before$csctLastTime和after$csctLastTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctLastTime==true">
	${before$csctLastTime}
	<dl class="csctLastTime " major  ref="csctLastTime" >
		<dt>最近时间:</dt>
		<s:if test="#request.CTRL.e.csctLastTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChatTalker$csctLastTime!=null">${csChatTalker$csctLastTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChatTalker.csctLastTime" id="csctLastTime"  value="<s:date name="csChatTalker.csctLastTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择在线访客的最近时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****最近时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctLastTime">${csChatTalker.csctLastTime}</textarea>
		 		<span>
		 	
			 ${csChatTalker.csctLastTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctLastTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csctAddTime和after$csctAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctAddTime==true">
	${before$csctAddTime}
	<dl class="csctAddTime " major  ref="csctAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csctAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChatTalker$csctAddTime!=null">${csChatTalker$csctAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChatTalker.csctAddTime" id="csctAddTime"  value="<s:date name="csChatTalker.csctAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择在线访客的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctAddTime">${csChatTalker.csctAddTime}</textarea>
		 		<span>
		 	
			 ${csChatTalker.csctAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csctRemark和after$csctRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctRemark==true">
	${before$csctRemark}
	<dl class="csctRemark " minor  ref="csctRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csctRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csChatTalker$csctRemark!=null">${csChatTalker$csctRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csctRemark" name="csChatTalker.csctRemark" rows="5">${csChatTalker.csctRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入在线访客的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csChatTalker.csctRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctRemark}
	</s:if>
	
	${lz:set("注释","*****************客服状态字段的输入框代码*****************")}
	${lz:set("注释","before$csctState和after$csctState变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctState==true">
	${before$csctState}
	<dl class="csctState " major  ref="csctState" >
		<dt>客服状态:</dt>
		<s:if test="#request.CTRL.e.csctState==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChatTalker$csctState!=null">${csChatTalker$csctState}</s:if><s:else>
		 	<select class="narrow" id="csctState" name="csChatTalker.csctState">
		 		<option value="">请选择</option>
				<option value="0" ${csChatTalker.csctState==0?"selected":""}>排队中</option>
				<option value="1" ${csChatTalker.csctState==1?"selected":""}>沟通中</option>
				<option value="2" ${csChatTalker.csctState==2?"selected":""}>已结束</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择在线访客的客服状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****客服状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctState">${csChatTalker.csctState}</textarea>
		 		<span>
		 	
			 ${csChatTalker.csctState$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctState}
	</s:if>
	
	${lz:set("注释","*****************当前状态字段的输入框代码*****************")}
	${lz:set("注释","before$csctStatus和after$csctStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csctStatus==true">
	${before$csctStatus}
	<dl class="csctStatus " major  ref="csctStatus" >
		<dt>当前状态:</dt>
		<s:if test="#request.CTRL.e.csctStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChatTalker$csctStatus!=null">${csChatTalker$csctStatus}</s:if><s:else>
		 	<select class="narrow" id="csctStatus" name="csChatTalker.csctStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csChatTalker.csctStatus==1?"selected":""}>在线</option>
				<option value="0" ${csChatTalker.csctStatus==0?"selected":""}>不在线</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择在线访客的当前状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****当前状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csctStatus">${csChatTalker.csctStatus}</textarea>
		 		<span>
		 	
			 ${csChatTalker.csctStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csctStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csChatTalker.csctId}" name="csChatTalker.csctId" id="csctId" />
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