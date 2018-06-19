<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/gift.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/gift.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csGift.csgId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csgId,csgHost,csgName,csgMember,csgSysPack,csgUserPack,csgGoods,csgOutlets,csgModel,csgType,csgCount,csgRemain,csgPrice,csgRebate,records,csgUntilTime,csgStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csgId,csgHost,csgName,csgMember,csgSysPack,csgUserPack,csgGoods,csgOutlets,csgModel,csgType,csgCount,csgRemain,csgPrice,csgRebate,csgScript,records,csgUntilTime,csgStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csgId,csgHost,csgName,csgModel,csgCount,csgRemain,csgPrice,csgRebate,records,csgUntilTime,csgStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csgId,csgHost,csgName,csgMember,csgSysPack,csgUserPack,csgGoods,csgOutlets,csgModel,csgType,csgCount,csgRemain,csgPrice,csgRebate,csgScript,records,csgUntilTime,csgUpdateTime,csgAddTime,csgStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"优惠内容编辑":CTRL.title}</title>
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
	$("#giftForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csgId==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgHost==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgName==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgName":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "优惠名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgMember==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgSysPack==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgSysPack":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgUserPack==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgUserPack":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgGoods==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgGoods":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgOutlets==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgOutlets":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgModel==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgModel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgType==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择优惠类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgCount==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgCount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "原始数量输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgRemain==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgRemain":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "可用数量输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgPrice==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "优惠价格输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgRebate==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgRebate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "优惠折扣输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgScript==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgScript":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "函数脚本最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgUntilTime==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgUntilTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "有效期至不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgAddTime==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgStatus==true">
		${lz:set("haveEditable",true)}
		,"csGift.csgStatus":function(el){
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
	<form class="form editform" ref="CsGift" id="giftForm" name="giftForm" action="${empty CTRL.action?"gift_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写优惠内容相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csGiftToken}" name="csGiftToken" id="csGiftToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csGift.csgId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csgId和after$csgId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgId==true">
	${before$csgId}
	<dl class="csgId ${CTRL.e.csgId?"hidden":""}" major  ref="csgId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csgId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csGift$csgId!=null">${csGift$csgId}</s:if><s:else>
			<input type="hidden" value="${csGift.csgId}" name="csGift.csgId" id="csgId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgId">${csGift.csgId}</textarea>
		 		<span>
		 	
			 ${csGift.csgId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csgHost和after$csgHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgHost==true">
	${before$csgHost}
	<dl class="csgHost " major  ref="csgHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csgHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csGift$csgHost!=null">${csGift$csgHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csgHost" name="csGift.csgHost">
		 				<option selected value="${csGift.csgHost}">
		 					${get:SrvHost(csGift.csgHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csgHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgHost">${csGift.csgHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csGift.csgHost}',{ctrl:{editable:false,visible:true}})">
			 ${csGift.csgHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgHost}
	</s:if>
	
	${lz:set("注释","*****************优惠名称字段的输入框代码*****************")}
	${lz:set("注释","before$csgName和after$csgName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgName==true">
	${before$csgName}
	<dl class="csgName " major  ref="csgName" >
		<dt>优惠名称:</dt>
		<s:if test="#request.CTRL.e.csgName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csGift$csgName!=null">${csGift$csgName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csGift.csgName" id="csgName"  value="${csGift.csgName}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入优惠内容的优惠名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优惠名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgName">${csGift.csgName}</textarea>
		 		<span>
		 	
			 ${csGift.csgName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgName}
	</s:if>
	
	${lz:set("注释","*****************所属会员字段的输入框代码*****************")}
	${lz:set("注释","before$csgMember和after$csgMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgMember==true">
	${before$csgMember}
	<dl class="csgMember " major  ref="csgMember" >
		<dt>所属会员:</dt>
		<s:if test="#request.CTRL.e.csgMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csGift$csgMember!=null">${csGift$csgMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csgHost}" type="text" id="csgMember" name="csGift.csgMember" text="${get:CsMember(csGift.csgMember).csmName}" value="${csGift.csgMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csgMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgMember">${csGift.csgMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csGift.csgMember}',{ctrl:{editable:false,visible:true}})">
			 ${csGift.csgMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgMember}
	</s:if>
	
	${lz:set("注释","*****************系统套餐字段的输入框代码*****************")}
	${lz:set("注释","before$csgSysPack和after$csgSysPack变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgSysPack==true">
	${before$csgSysPack}
	<dl class="csgSysPack " major  ref="csgSysPack" >
		<dt>系统套餐:</dt>
		<s:if test="#request.CTRL.e.csgSysPack==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csGift$csgSysPack!=null">${csGift$csgSysPack}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/fee/pack_query.do?size=-1" id="csgSysPack" name="csGift.csgSysPack">
		 				<option selected value="${csGift.csgSysPack}">
		 					${get:CsPack(csGift.csgSysPack).cspName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgSysPack').val())==''){return;};window.href('${basePath}${proname}/fee/pack_details.do?key='+$('#csgSysPack').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>所属于哪个系统套餐</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****系统套餐字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgSysPack">${csGift.csgSysPack}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/pack_details.do?key=${csGift.csgSysPack}',{ctrl:{editable:false,visible:true}})">
			 ${csGift.csgSysPack$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgSysPack}
	</s:if>
	
	${lz:set("注释","*****************会员套餐字段的输入框代码*****************")}
	${lz:set("注释","before$csgUserPack和after$csgUserPack变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgUserPack==true">
	${before$csgUserPack}
	<dl class="csgUserPack " major  ref="csgUserPack" >
		<dt>会员套餐:</dt>
		<s:if test="#request.CTRL.e.csgUserPack==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csGift$csgUserPack!=null">${csGift$csgUserPack}</s:if><s:else>
		 			<input title="请输入用户套餐编号进行查询" class="combox narrow" action="${basePath}${proname}/user/pack_query.do?value={param}&csupHost={csgHost}" type="text" id="csgUserPack" name="csGift.csgUserPack" text="${get:CsUserPack(csGift.csgUserPack).csupId}" value="${csGift.csgUserPack}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgUserPack').val())==''){return;};window.href('${basePath}${proname}/user/pack_details.do?key='+$('#csgUserPack').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员套餐字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgUserPack">${csGift.csgUserPack}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/pack_details.do?key=${csGift.csgUserPack}',{ctrl:{editable:false,visible:true}})">
			 ${csGift.csgUserPack$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgUserPack}
	</s:if>
	
	${lz:set("注释","*****************所属商品字段的输入框代码*****************")}
	${lz:set("注释","before$csgGoods和after$csgGoods变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgGoods==true">
	${before$csgGoods}
	<dl class="csgGoods " major  ref="csgGoods" >
		<dt>所属商品:</dt>
		<s:if test="#request.CTRL.e.csgGoods==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csGift$csgGoods!=null">${csGift$csgGoods}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/fee/goods_query.do?size=-1" id="csgGoods" name="csGift.csgGoods">
		 				<option selected value="${csGift.csgGoods}">
		 					${get:CsGoods(csGift.csgGoods).csgName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgGoods').val())==''){return;};window.href('${basePath}${proname}/fee/goods_details.do?key='+$('#csgGoods').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属商品字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgGoods">${csGift.csgGoods}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/goods_details.do?key=${csGift.csgGoods}',{ctrl:{editable:false,visible:true}})">
			 ${csGift.csgGoods$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgGoods}
	</s:if>
	
	${lz:set("注释","*****************所属网点字段的输入框代码*****************")}
	${lz:set("注释","before$csgOutlets和after$csgOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgOutlets==true">
	${before$csgOutlets}
	<dl class="csgOutlets " minor  ref="csgOutlets" >
		<dt>所属网点:</dt>
		<s:if test="#request.CTRL.e.csgOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csGift$csgOutlets!=null">${csGift$csgOutlets}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/outlets_query.do?size=-1&csoHost={csgHost}" id="csgOutlets" name="csGift.csgOutlets">
		 				<option selected value="${csGift.csgOutlets}">
		 					${get:CsOutlets(csGift.csgOutlets).csoName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgOutlets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#csgOutlets').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgOutlets">${csGift.csgOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csGift.csgOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csGift.csgOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgOutlets}
	</s:if>
	
	${lz:set("注释","*****************所属车型字段的输入框代码*****************")}
	${lz:set("注释","before$csgModel和after$csgModel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgModel==true">
	${before$csgModel}
	<dl class="csgModel " major  ref="csgModel" style="width:98%;">
		<dt>所属车型:</dt>
		<s:if test="#request.CTRL.e.csgModel==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csGift$csgModel!=null">${csGift$csgModel}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csGift.csgModel" id="csgModel"/>
 					${lz:set("items", lz:query("ccclubs_system","select * from cs_car_model where 1=1 ") )}
					<s:iterator value="#request.items" id="item" status="i">
			<label><input type="checkbox" ${lz:idin(csGift.csgModel,item.cscm_id)?"checked='checked'":""} value="#${item.cscm_id}#" name="csGift.csgModel" id="csgModel"/>
					${item.cscm_name}&nbsp;&nbsp;&nbsp;</label>
					</s:iterator>
					${lz:set("items",null)}
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择优惠内容的所属车型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属车型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csgModel">${csGift.csgModel}</textarea>
		 		<span>
		 	
			 ${csGift.csgModel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgModel}
	</s:if>
	
	${lz:set("注释","*****************优惠类型字段的输入框代码*****************")}
	${lz:set("注释","before$csgType和after$csgType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgType==true">
	${before$csgType}
	<dl class="csgType " major  ref="csgType" >
		<dt>优惠类型:</dt>
		<s:if test="#request.CTRL.e.csgType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csGift$csgType!=null">${csGift$csgType}</s:if><s:else>
		 	<select class="narrow" id="csgType" name="csGift.csgType">
		 		<option value="">请选择</option>
				<option value="0" ${csGift.csgType==0?"selected":""}>数量</option>
				<option value="1" ${csGift.csgType==1?"selected":""}>价格</option>
				<option value="2" ${csGift.csgType==2?"selected":""}>折扣</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择优惠内容的优惠类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优惠类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgType">${csGift.csgType}</textarea>
		 		<span>
		 	
			 ${csGift.csgType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgType}
	</s:if>
	
	${lz:set("注释","*****************原始数量字段的输入框代码*****************")}
	${lz:set("注释","before$csgCount和after$csgCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgCount==true">
	${before$csgCount}
	<dl class="csgCount " minor  ref="csgCount" >
		<dt>原始数量:</dt>
		<s:if test="#request.CTRL.e.csgCount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csGift$csgCount!=null">${csGift$csgCount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csGift.csgCount" id="csgCount"  value="${csGift.csgCount}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入优惠内容的原始数量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****原始数量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgCount">${csGift.csgCount}</textarea>
		 		<span>
		 	
			 ${csGift.csgCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgCount}
	</s:if>
	
	${lz:set("注释","*****************可用数量字段的输入框代码*****************")}
	${lz:set("注释","before$csgRemain和after$csgRemain变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgRemain==true">
	${before$csgRemain}
	<dl class="csgRemain " major  ref="csgRemain" >
		<dt>可用数量:</dt>
		<s:if test="#request.CTRL.e.csgRemain==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csGift$csgRemain!=null">${csGift$csgRemain}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csGift.csgRemain" id="csgRemain"  value="${csGift.csgRemain}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入优惠内容的可用数量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****可用数量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgRemain">${csGift.csgRemain}</textarea>
		 		<span>
		 	
			 ${csGift.csgRemain$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgRemain}
	</s:if>
	
	${lz:set("注释","*****************优惠价格字段的输入框代码*****************")}
	${lz:set("注释","before$csgPrice和after$csgPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgPrice==true">
	${before$csgPrice}
	<dl class="csgPrice " major  ref="csgPrice" >
		<dt>优惠价格:</dt>
		<s:if test="#request.CTRL.e.csgPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csGift$csgPrice!=null">${csGift$csgPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csGift.csgPrice" id="csgPrice"  value="${csGift.csgPrice}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入优惠内容的优惠价格</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优惠价格字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgPrice">${csGift.csgPrice}</textarea>
		 		<span>
		 	
			 ${csGift.csgPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgPrice}
	</s:if>
	
	${lz:set("注释","*****************优惠折扣字段的输入框代码*****************")}
	${lz:set("注释","before$csgRebate和after$csgRebate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgRebate==true">
	${before$csgRebate}
	<dl class="csgRebate " major  ref="csgRebate" >
		<dt>优惠折扣:</dt>
		<s:if test="#request.CTRL.e.csgRebate==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csGift$csgRebate!=null">${csGift$csgRebate}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csGift.csgRebate" id="csgRebate"  value="${csGift.csgRebate}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入优惠内容的优惠折扣</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优惠折扣字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgRebate">${csGift.csgRebate}</textarea>
		 		<span>
		 	
			 ${csGift.csgRebate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgRebate}
	</s:if>
	
	${lz:set("注释","*****************函数脚本字段的输入框代码*****************")}
	${lz:set("注释","before$csgScript和after$csgScript变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgScript==true">
	${before$csgScript}
	<dl class="csgScript " minor  ref="csgScript" style="width:98%;">
		<dt>函数脚本:</dt>
		<s:if test="#request.CTRL.e.csgScript==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csGift$csgScript!=null">${csGift$csgScript}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csGift.csgScript" id="csgScript"  value="${csGift.csgScript}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>优惠项脚本，二次干预优惠项，返回false表示不能使用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****函数脚本字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csgScript">${csGift.csgScript}</textarea>
		 		<span>
		 	
			 ${csGift.csgScript$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgScript}
	</s:if>
	
	${lz:set("注释","*****************使用记录字段的输入框代码*****************")}
	${lz:set("注释","before$records和after$records变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.records==true">
	${before$records}
	<dl class="records " minor  ref="records" style="width:98%;">
		<dt>使用记录:</dt>
		<s:if test="#request.CTRL.e.records==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csGift$records!=null">${csGift$records}</s:if><s:else>
		  		<s:if test="csGift.csgId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="records" name="records" frameborder='0' src="" url="${basePath}${proname}/user/userecord.do?inFrame=true&canQuery=false&fields=csurOrder,csurCount,csurAddTime&csurGift=${csGift.csgId}&ctrl={queryable:false,fields:{csurGift:{visible:true,editable:false,defaultValue:${csGift.csgId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#records').attr('src',$('#records').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑使用记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csGift.csgId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="records" name="records" frameborder='0' src="" url="${basePath}${proname}/user/userecord.do?inFrame=true&canQuery=false&fields=csurOrder,csurCount,csurAddTime&csurGift=${csGift.csgId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
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
	
	${lz:set("注释","*****************有效期至字段的输入框代码*****************")}
	${lz:set("注释","before$csgUntilTime和after$csgUntilTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgUntilTime==true">
	${before$csgUntilTime}
	<dl class="csgUntilTime " major  ref="csgUntilTime" >
		<dt>有效期至:</dt>
		<s:if test="#request.CTRL.e.csgUntilTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csGift$csgUntilTime!=null">${csGift$csgUntilTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csGift.csgUntilTime" id="csgUntilTime"  value="<s:date name="csGift.csgUntilTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择优惠内容的有效期至</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****有效期至字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgUntilTime">${csGift.csgUntilTime}</textarea>
		 		<span>
		 	
			 ${csGift.csgUntilTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgUntilTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csgUpdateTime和after$csgUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgUpdateTime==true">
	${before$csgUpdateTime}
	<dl class="csgUpdateTime " major  ref="csgUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csgUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csGift$csgUpdateTime!=null">${csGift$csgUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csGift.csgUpdateTime" id="csgUpdateTime"  value="<s:date name="csGift.csgUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择优惠内容的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgUpdateTime">${csGift.csgUpdateTime}</textarea>
		 		<span>
		 	
			 ${csGift.csgUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csgAddTime和after$csgAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgAddTime==true">
	${before$csgAddTime}
	<dl class="csgAddTime " major  ref="csgAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csgAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csGift$csgAddTime!=null">${csGift$csgAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csGift.csgAddTime" id="csgAddTime"  value="<s:date name="csGift.csgAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择优惠内容的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgAddTime">${csGift.csgAddTime}</textarea>
		 		<span>
		 	
			 ${csGift.csgAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csgStatus和after$csgStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgStatus==true">
	${before$csgStatus}
	<dl class="csgStatus " major  ref="csgStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csgStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csGift$csgStatus!=null">${csGift$csgStatus}</s:if><s:else>
		 	<select class="narrow" id="csgStatus" name="csGift.csgStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csGift.csgStatus==1?"selected":""}>有效</option>
				<option value="0" ${csGift.csgStatus==0?"selected":""}>无效</option>
				<option value="2" ${csGift.csgStatus==2?"selected":""}>过期</option>
				<option value="3" ${csGift.csgStatus==3?"selected":""}>用完</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择优惠内容的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgStatus">${csGift.csgStatus}</textarea>
		 		<span>
		 	
			 ${csGift.csgStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csGift.csgId}" name="csGift.csgId" id="csgId" />
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