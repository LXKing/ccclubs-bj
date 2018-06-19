<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/userecord.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/userecord.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUseRecord.csurId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csurId,csurHost,csurProfile,csurMember,csurCoin,csurGift,csurOrder,csurCount",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csurId,csurHost,csurProfile,csurMember,csurCoin,csurGift,csurOrder,csurCount",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csurId,csurHost,csurProfile,csurMember,csurCoin,csurGift,csurOrder,csurCount",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csurId,csurHost,csurProfile,csurMember,csurCoin,csurGift,csurOrder,csurCount",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"惠免记录编辑":CTRL.title}</title>
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
	$("#userecordForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csurId==true">
		${lz:set("haveEditable",true)}
		,"csUseRecord.csurId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csurHost==true">
		${lz:set("haveEditable",true)}
		,"csUseRecord.csurHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csurProfile==true">
		${lz:set("haveEditable",true)}
		,"csUseRecord.csurProfile":function(el){
			if(jQuery.trim(el.value)=="")
				return "摘要不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "摘要最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csurMember==true">
		${lz:set("haveEditable",true)}
		,"csUseRecord.csurMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csurFreeHour==true">
		${lz:set("haveEditable",true)}
		,"csUseRecord.csurFreeHour":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csurCoin==true">
		${lz:set("haveEditable",true)}
		,"csUseRecord.csurCoin":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csurGift==true">
		${lz:set("haveEditable",true)}
		,"csUseRecord.csurGift":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csurOrder==true">
		${lz:set("haveEditable",true)}
		,"csUseRecord.csurOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csurCount==true">
		${lz:set("haveEditable",true)}
		,"csUseRecord.csurCount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "使用数量输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csurAddTime==true">
		${lz:set("haveEditable",true)}
		,"csUseRecord.csurAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "使用时间不能为空";
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
	<form class="form editform" ref="CsUseRecord" id="userecordForm" name="userecordForm" action="${empty CTRL.action?"userecord_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写惠免记录相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csUseRecordToken}" name="csUseRecordToken" id="csUseRecordToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUseRecord.csurId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csurId和after$csurId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csurId==true">
	${before$csurId}
	<dl class="csurId ${CTRL.e.csurId?"hidden":""}" major  ref="csurId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csurId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUseRecord$csurId!=null">${csUseRecord$csurId}</s:if><s:else>
			<input type="hidden" value="${csUseRecord.csurId}" name="csUseRecord.csurId" id="csurId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csurId">${csUseRecord.csurId}</textarea>
		 		<span>
		 	
			 ${csUseRecord.csurId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csurId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csurHost和after$csurHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csurHost==true">
	${before$csurHost}
	<dl class="csurHost " major  ref="csurHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csurHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUseRecord$csurHost!=null">${csUseRecord$csurHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csurHost" name="csUseRecord.csurHost">
		 				<option selected value="${csUseRecord.csurHost}">
		 					${get:SrvHost(csUseRecord.csurHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csurHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csurHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csurHost">${csUseRecord.csurHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csUseRecord.csurHost}',{ctrl:{editable:false,visible:true}})">
			 ${csUseRecord.csurHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csurHost}
	</s:if>
	
	${lz:set("注释","*****************摘要字段的输入框代码*****************")}
	${lz:set("注释","before$csurProfile和after$csurProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csurProfile==true">
	${before$csurProfile}
	<dl class="csurProfile " major  ref="csurProfile" style="width:98%;">
		<dt>摘　　要:</dt>
		<s:if test="#request.CTRL.e.csurProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUseRecord$csurProfile!=null">${csUseRecord$csurProfile}</s:if><s:else>
		 	<textarea class="input wide"  id="csurProfile" name="csUseRecord.csurProfile" rows="5">${csUseRecord.csurProfile}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入惠免记录的摘要</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****摘要字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUseRecord.csurProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csurProfile}
	</s:if>
	
	${lz:set("注释","*****************所属会员字段的输入框代码*****************")}
	${lz:set("注释","before$csurMember和after$csurMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csurMember==true">
	${before$csurMember}
	<dl class="csurMember " major  ref="csurMember" >
		<dt>所属会员:</dt>
		<s:if test="#request.CTRL.e.csurMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUseRecord$csurMember!=null">${csUseRecord$csurMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csurHost}" type="text" id="csurMember" name="csUseRecord.csurMember" text="${get:CsMember(csUseRecord.csurMember).csmName}" value="${csUseRecord.csurMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csurMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csurMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csurMember">${csUseRecord.csurMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csUseRecord.csurMember}',{ctrl:{editable:false,visible:true}})">
			 ${csUseRecord.csurMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csurMember}
	</s:if>
	
	${lz:set("注释","*****************所属免费小时字段的输入框代码*****************")}
	${lz:set("注释","before$csurFreeHour和after$csurFreeHour变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csurFreeHour==false">
	${before$csurFreeHour}
	<dl class="csurFreeHour " major  ref="csurFreeHour" >
		<dt>所属免费小时:</dt>
		<s:if test="#request.CTRL.e.csurFreeHour==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUseRecord$csurFreeHour!=null">${csUseRecord$csurFreeHour}</s:if><s:else>
		 			<input title="请输入免费小时编号进行查询" class="combox narrow" action="${basePath}${proname}/user/freehour_query.do?value={param}&csfhHost={csurHost}" type="text" id="csurFreeHour" name="csUseRecord.csurFreeHour" text="${get:CsFreeHour(csUseRecord.csurFreeHour).csfhId}" value="${csUseRecord.csurFreeHour}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csurFreeHour').val())==''){return;};window.href('${basePath}${proname}/user/freehour_details.do?key='+$('#csurFreeHour').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属免费小时字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csurFreeHour">${csUseRecord.csurFreeHour}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/freehour_details.do?key=${csUseRecord.csurFreeHour}',{ctrl:{editable:false,visible:true}})">
			 ${csUseRecord.csurFreeHour$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csurFreeHour}
	</s:if>
	
	${lz:set("注释","*****************所属红包字段的输入框代码*****************")}
	${lz:set("注释","before$csurCoin和after$csurCoin变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csurCoin==true">
	${before$csurCoin}
	<dl class="csurCoin " major  ref="csurCoin" >
		<dt>红包编号:</dt>
		<s:if test="#request.CTRL.e.csurCoin==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUseRecord$csurCoin!=null">${csUseRecord$csurCoin}</s:if><s:else>
		 			<input title="请输入红包编号进行查询" class="combox narrow" action="${basePath}${proname}/user/coin_query.do?value={param}&cscHost={csurHost}" type="text" id="csurCoin" name="csUseRecord.csurCoin" text="${get:CsCoin(csUseRecord.csurCoin).cscId}" value="${csUseRecord.csurCoin}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csurCoin').val())==''){return;};window.href('${basePath}${proname}/user/coin_details.do?key='+$('#csurCoin').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属红包字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csurCoin">${csUseRecord.csurCoin}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/coin_details.do?key=${csUseRecord.csurCoin}',{ctrl:{editable:false,visible:true}})">
			 ${csUseRecord.csurCoin$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csurCoin}
	</s:if>
	
	${lz:set("注释","*****************所属优惠项字段的输入框代码*****************")}
	${lz:set("注释","before$csurGift和after$csurGift变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csurGift==true">
	${before$csurGift}
	<dl class="csurGift " major  ref="csurGift" >
		<dt>所属优惠项:</dt>
		<s:if test="#request.CTRL.e.csurGift==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUseRecord$csurGift!=null">${csUseRecord$csurGift}</s:if><s:else>
		 			<input title="请输入优惠内容优惠名称进行查询" class="combox narrow" action="${basePath}${proname}/user/gift_query.do?value={param}&csgHost={csurHost}" type="text" id="csurGift" name="csUseRecord.csurGift" text="${get:CsGift(csUseRecord.csurGift).csgName}" value="${csUseRecord.csurGift}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csurGift').val())==''){return;};window.href('${basePath}${proname}/user/gift_details.do?key='+$('#csurGift').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属优惠项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csurGift">${csUseRecord.csurGift}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/gift_details.do?key=${csUseRecord.csurGift}',{ctrl:{editable:false,visible:true}})">
			 ${csUseRecord.csurGift$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csurGift}
	</s:if>
	
	${lz:set("注释","*****************所属订单字段的输入框代码*****************")}
	${lz:set("注释","before$csurOrder和after$csurOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csurOrder==true">
	${before$csurOrder}
	<dl class="csurOrder " major  ref="csurOrder" >
		<dt>所属订单:</dt>
		<s:if test="#request.CTRL.e.csurOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUseRecord$csurOrder!=null">${csUseRecord$csurOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csurHost}" type="text" id="csurOrder" name="csUseRecord.csurOrder" text="${get:CsOrder(csUseRecord.csurOrder).csoId}" value="${csUseRecord.csurOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csurOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csurOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csurOrder">${csUseRecord.csurOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csUseRecord.csurOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csUseRecord.csurOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csurOrder}
	</s:if>
	
	${lz:set("注释","*****************使用数量字段的输入框代码*****************")}
	${lz:set("注释","before$csurCount和after$csurCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csurCount==true">
	${before$csurCount}
	<dl class="csurCount " major  ref="csurCount" >
		<dt>消费金额:</dt>
		<s:if test="#request.CTRL.e.csurCount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUseRecord$csurCount!=null">${csUseRecord$csurCount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUseRecord.csurCount" id="csurCount"  value="${csUseRecord.csurCount}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入惠免记录的使用数量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用数量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csurCount">${csUseRecord.csurCount}</textarea>
		 		<span>
		 	
			 ${csUseRecord.csurCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csurCount}
	</s:if>
	
	${lz:set("注释","*****************使用时间字段的输入框代码*****************")}
	${lz:set("注释","before$csurAddTime和after$csurAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csurAddTime==true">
	${before$csurAddTime}
	<dl class="csurAddTime " major  ref="csurAddTime" >
		<dt>使用时间:</dt>
		<s:if test="#request.CTRL.e.csurAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUseRecord$csurAddTime!=null">${csUseRecord$csurAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUseRecord.csurAddTime" id="csurAddTime"  value="<s:date name="csUseRecord.csurAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择惠免记录的使用时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csurAddTime">${csUseRecord.csurAddTime}</textarea>
		 		<span>
		 	
			 ${csUseRecord.csurAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csurAddTime}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUseRecord.csurId}" name="csUseRecord.csurId" id="csurId" />
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