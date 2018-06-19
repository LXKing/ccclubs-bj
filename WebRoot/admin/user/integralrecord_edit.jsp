<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/integralrecord.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/integralrecord.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csIntegralRecord.csrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csrId,csrHost,csrMember,csrType,csrAmount,csrRemain,csrRemark,csrOrder,csrRelate,csrEditor",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrId,csrHost,csrMember,csrType,csrAmount,csrRemain,csrRemark,csrOrder,csrRelate,csrEditor",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csrId,csrHost,csrMember,csrType,csrAmount,csrRemain,csrRemark,csrOrder,csrRelate,csrEditor",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrId,csrHost,csrMember,csrType,csrAmount,csrRemain,csrRemark,csrAddTime,csrOrder,csrRelate,csrEditor",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"积分记录编辑":CTRL.title}</title>
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
	$("#integralrecordForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csrId==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRecord.csrId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrHost==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRecord.csrHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrMember==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRecord.csrMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrType==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRecord.csrType":function(el){
			if(jQuery.trim(el.value)=="")
				return "积分类型不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "积分类型最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrAmount==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRecord.csrAmount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "变化积分输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrRemain==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRecord.csrRemain":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "剩余积分输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrRemark==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRecord.csrRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrAddTime==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRecord.csrAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "发生时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrOrder==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRecord.csrOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrRelate==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRecord.csrRelate":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrSerial==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRecord.csrSerial":function(el){
			if(el.value.length>50 && el.value.indexOf("[*]")==-1)
				return "序列号最大长度为50个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrEditor==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRecord.csrEditor":function(el){
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
	<form class="form editform" ref="CsIntegralRecord" id="integralrecordForm" name="integralrecordForm" action="${empty CTRL.action?"integralrecord_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写积分记录相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csIntegralRecordToken}" name="csIntegralRecordToken" id="csIntegralRecordToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csIntegralRecord.csrId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csrId和after$csrId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrId==true">
	${before$csrId}
	<dl class="csrId ${CTRL.e.csrId?"hidden":""}" major  ref="csrId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csrId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csIntegralRecord$csrId!=null">${csIntegralRecord$csrId}</s:if><s:else>
			<input type="hidden" value="${csIntegralRecord.csrId}" name="csIntegralRecord.csrId" id="csrId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrId">${csIntegralRecord.csrId}</textarea>
		 		<span>
		 	
			 ${csIntegralRecord.csrId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csrHost和after$csrHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrHost==true">
	${before$csrHost}
	<dl class="csrHost " major  ref="csrHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csrHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csIntegralRecord$csrHost!=null">${csIntegralRecord$csrHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csrHost" name="csIntegralRecord.csrHost">
		 				<option selected value="${csIntegralRecord.csrHost}">
		 					${get:SrvHost(csIntegralRecord.csrHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csrHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrHost">${csIntegralRecord.csrHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csIntegralRecord.csrHost}',{ctrl:{editable:false,visible:true}})">
			 ${csIntegralRecord.csrHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrHost}
	</s:if>
	
	${lz:set("注释","*****************消费会员字段的输入框代码*****************")}
	${lz:set("注释","before$csrMember和after$csrMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrMember==true">
	${before$csrMember}
	<dl class="csrMember " major  ref="csrMember" >
		<dt>消费会员:</dt>
		<s:if test="#request.CTRL.e.csrMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csIntegralRecord$csrMember!=null">${csIntegralRecord$csrMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csrHost}" type="text" id="csrMember" name="csIntegralRecord.csrMember" text="${get:CsMember(csIntegralRecord.csrMember).csmName}" value="${csIntegralRecord.csrMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csrMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消费会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrMember">${csIntegralRecord.csrMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csIntegralRecord.csrMember}',{ctrl:{editable:false,visible:true}})">
			 ${csIntegralRecord.csrMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrMember}
	</s:if>
	
	${lz:set("注释","*****************积分类型字段的输入框代码*****************")}
	${lz:set("注释","before$csrType和after$csrType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrType==true">
	${before$csrType}
	<dl class="csrType " major  ref="csrType" >
		<dt>积分类型:</dt>
		<s:if test="#request.CTRL.e.csrType==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csIntegralRecord$csrType!=null">${csIntegralRecord$csrType}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csIntegralRecord.csrType" id="csrType"  value="${csIntegralRecord.csrType}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入积分记录的积分类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****积分类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrType">${csIntegralRecord.csrType}</textarea>
		 		<span>
		 	
			 ${csIntegralRecord.csrType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrType}
	</s:if>
	
	${lz:set("注释","*****************变化积分字段的输入框代码*****************")}
	${lz:set("注释","before$csrAmount和after$csrAmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrAmount==true">
	${before$csrAmount}
	<dl class="csrAmount " major  ref="csrAmount" >
		<dt>变化积分:</dt>
		<s:if test="#request.CTRL.e.csrAmount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csIntegralRecord$csrAmount!=null">${csIntegralRecord$csrAmount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csIntegralRecord.csrAmount" id="csrAmount"  value="${csIntegralRecord.csrAmount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入积分记录的变化积分</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****变化积分字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrAmount">${csIntegralRecord.csrAmount}</textarea>
		 		<span>
		 	
			 ${csIntegralRecord.csrAmount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrAmount}
	</s:if>
	
	${lz:set("注释","*****************剩余积分字段的输入框代码*****************")}
	${lz:set("注释","before$csrRemain和after$csrRemain变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrRemain==true">
	${before$csrRemain}
	<dl class="csrRemain " major  ref="csrRemain" >
		<dt>剩余积分:</dt>
		<s:if test="#request.CTRL.e.csrRemain==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csIntegralRecord$csrRemain!=null">${csIntegralRecord$csrRemain}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csIntegralRecord.csrRemain" id="csrRemain"  value="${csIntegralRecord.csrRemain}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>帐户余额或现金券余额或积分余量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****剩余积分字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrRemain">${csIntegralRecord.csrRemain}</textarea>
		 		<span>
		 	
			 ${csIntegralRecord.csrRemain$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrRemain}
	</s:if>
	
	${lz:set("注释","*****************描述字段的输入框代码*****************")}
	${lz:set("注释","before$csrRemark和after$csrRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrRemark==true">
	${before$csrRemark}
	<dl class="csrRemark " major  ref="csrRemark" style="width:98%;">
		<dt>描　　述:</dt>
		<s:if test="#request.CTRL.e.csrRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csIntegralRecord$csrRemark!=null">${csIntegralRecord$csrRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csrRemark" name="csIntegralRecord.csrRemark" rows="5">${csIntegralRecord.csrRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入积分记录的描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csIntegralRecord.csrRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrRemark}
	</s:if>
	
	${lz:set("注释","*****************发生时间字段的输入框代码*****************")}
	${lz:set("注释","before$csrAddTime和after$csrAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrAddTime==true">
	${before$csrAddTime}
	<dl class="csrAddTime " major  ref="csrAddTime" >
		<dt>发生时间:</dt>
		<s:if test="#request.CTRL.e.csrAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csIntegralRecord$csrAddTime!=null">${csIntegralRecord$csrAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csIntegralRecord.csrAddTime" id="csrAddTime"  value="<s:date name="csIntegralRecord.csrAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择积分记录的发生时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发生时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrAddTime">${csIntegralRecord.csrAddTime}</textarea>
		 		<span>
		 	
			 ${csIntegralRecord.csrAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrAddTime}
	</s:if>
	
	${lz:set("注释","*****************关联订单字段的输入框代码*****************")}
	${lz:set("注释","before$csrOrder和after$csrOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrOrder==true">
	${before$csrOrder}
	<dl class="csrOrder " minor  ref="csrOrder" >
		<dt>关联订单:</dt>
		<s:if test="#request.CTRL.e.csrOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csIntegralRecord$csrOrder!=null">${csIntegralRecord$csrOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csrHost}" type="text" id="csrOrder" name="csIntegralRecord.csrOrder" text="${get:CsOrder(csIntegralRecord.csrOrder).csoId}" value="${csIntegralRecord.csrOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csrOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrOrder">${csIntegralRecord.csrOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csIntegralRecord.csrOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csIntegralRecord.csrOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrOrder}
	</s:if>
	
	${lz:set("注释","*****************关联编号字段的输入框代码*****************")}
	${lz:set("注释","before$csrRelate和after$csrRelate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrRelate==true">
	${before$csrRelate}
	<dl class="csrRelate " minor  ref="csrRelate" >
		<dt>关联编号:</dt>
		<s:if test="#request.CTRL.e.csrRelate==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csIntegralRecord$csrRelate!=null">${csIntegralRecord$csrRelate}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csIntegralRecord.csrRelate" id="csrRelate"  value="${csIntegralRecord.csrRelate}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>支付宝充值？等</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrRelate">${csIntegralRecord.csrRelate}</textarea>
		 		<span>
		 	
			 ${csIntegralRecord.csrRelate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrRelate}
	</s:if>
	
	${lz:set("注释","*****************序列号字段的输入框代码*****************")}
	${lz:set("注释","before$csrSerial和after$csrSerial变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrSerial==true">
	${before$csrSerial}
	<dl class="csrSerial " minor  ref="csrSerial" style="width:98%;">
		<dt>序&nbsp;&nbsp;列&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.csrSerial==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csIntegralRecord$csrSerial!=null">${csIntegralRecord$csrSerial}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="50" name="csIntegralRecord.csrSerial" id="csrSerial"  value="${csIntegralRecord.csrSerial}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入积分记录的序列号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****序列号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csrSerial">${csIntegralRecord.csrSerial}</textarea>
		 		<span>
		 	
			 ${csIntegralRecord.csrSerial$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrSerial}
	</s:if>
	
	${lz:set("注释","*****************操作人字段的输入框代码*****************")}
	${lz:set("注释","before$csrEditor和after$csrEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrEditor==true">
	${before$csrEditor}
	<dl class="csrEditor " major  ref="csrEditor" >
		<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csrEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csIntegralRecord$csrEditor!=null">${csIntegralRecord$csrEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csrEditor" name="csIntegralRecord.csrEditor">
		 				<option selected value="${csIntegralRecord.csrEditor}">
		 					${get:SrvUser(csIntegralRecord.csrEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csrEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrEditor">${csIntegralRecord.csrEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csIntegralRecord.csrEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csIntegralRecord.csrEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrEditor}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csIntegralRecord.csrId}" name="csIntegralRecord.csrId" id="csrId" />
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