<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/evcard.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/object/evcard.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csEvCard.csecId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csecId,csecHost,csecNumber,csecRfid,csecRemark,csecStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csecId,csecHost,csecNumber,csecRfid,csecRemark,csecStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csecId,csecHost,csecNumber,csecRfid,csecRemark,csecStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csecId,csecHost,csecNumber,csecRfid,csecRemark,csecAddTime,csecStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"会员卡编辑":CTRL.title}</title>
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



<!--LAZY3Q_JSP_BODY-->
<script>
$(function(){	
	$("#evcardForm").form({
		"csEvCard.csecRfid":function(el){
			if(el.value.indexOf("[*]")==-1){
				$(el).val($(el).val().toUpperCase().replace(/[^A-Z0-9]/g,""));
				if(el.value.length!=8)
					return "机器号必须是8位";
			}
		}
	});
	$("#csecStatus").val(1);
});
</script>
<!--LAZY3Q_JSP_BODY-->



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#evcardForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csecId==true">
		${lz:set("haveEditable",true)}
		,"csEvCard.csecId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csecHost==true">
		${lz:set("haveEditable",true)}
		,"csEvCard.csecHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csecNumber==true">
		${lz:set("haveEditable",true)}
		,"csEvCard.csecNumber":function(el){
			if(jQuery.trim(el.value)=="")
				return "卡号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "卡号最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("evcard_query.do",{exists:true,csecNumber:el.value});
				if(exists && exists.length>0){
					if(${csEvCard.csecId==null} || exists[0].value!=$("#csecId").val())
						return "卡号已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csecRfid==true">
		${lz:set("haveEditable",true)}
		,"csEvCard.csecRfid":function(el){
			if(jQuery.trim(el.value)=="")
				return "机器号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "机器号最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("evcard_query.do",{exists:true,csecRfid:el.value});
				if(exists && exists.length>0){
					if(${csEvCard.csecId==null} || exists[0].value!=$("#csecId").val())
						return "机器号已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csecRemark==true">
		${lz:set("haveEditable",true)}
		,"csEvCard.csecRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csecAddTime==true">
		${lz:set("haveEditable",true)}
		,"csEvCard.csecAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csecFlag==true">
		${lz:set("haveEditable",true)}
		,"csEvCard.csecFlag":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择已绑定？";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csecStatus==true">
		${lz:set("haveEditable",true)}
		,"csEvCard.csecStatus":function(el){
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
	<form class="form editform" ref="CsEvCard" id="evcardForm" name="evcardForm" action="${empty CTRL.action?"evcard_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写会员卡相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csEvCardToken}" name="csEvCardToken" id="csEvCardToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csEvCard.csecId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csecId和after$csecId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csecId==true">
	${before$csecId}
	<dl class="csecId ${CTRL.e.csecId?"hidden":""}" major  ref="csecId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csecId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csEvCard$csecId!=null">${csEvCard$csecId}</s:if><s:else>
			<input type="hidden" value="${csEvCard.csecId}" name="csEvCard.csecId" id="csecId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csecId">${csEvCard.csecId}</textarea>
		 		<span>
		 	
			 ${csEvCard.csecId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csecId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csecHost和after$csecHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csecHost==true">
	${before$csecHost}
	<dl class="csecHost " major  ref="csecHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csecHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csEvCard$csecHost!=null">${csEvCard$csecHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csecHost" name="csEvCard.csecHost">
		 				<option selected value="${csEvCard.csecHost}">
		 					${get:SrvHost(csEvCard.csecHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csecHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csecHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csecHost">${csEvCard.csecHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csEvCard.csecHost}',{ctrl:{editable:false,visible:true}})">
			 ${csEvCard.csecHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csecHost}
	</s:if>
	
	${lz:set("注释","*****************卡号字段的输入框代码*****************")}
	${lz:set("注释","before$csecNumber和after$csecNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csecNumber==true">
	${before$csecNumber}
	<dl class="csecNumber " major  ref="csecNumber" >
		<dt>卡　　号:</dt>
		<s:if test="#request.CTRL.e.csecNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csEvCard$csecNumber!=null">${csEvCard$csecNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"   name="csEvCard.csecNumber" id="csecNumber"  value="${csEvCard.csecNumber}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员卡的卡号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****卡号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csecNumber">${csEvCard.csecNumber}</textarea>
		 		<span>
		 	
			 ${csEvCard.csecNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csecNumber}
	</s:if>
	
	${lz:set("注释","*****************机器号字段的输入框代码*****************")}
	${lz:set("注释","before$csecRfid和after$csecRfid变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csecRfid==true">
	${before$csecRfid}
	<dl class="csecRfid " major  ref="csecRfid" >
		<dt>机&nbsp;&nbsp;器&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.csecRfid==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csEvCard$csecRfid!=null">${csEvCard$csecRfid}</s:if><s:else>
		 	<input type="text" class="input narrow"  name="csEvCard.csecRfid" id="csecRfid"  value="${csEvCard.csecRfid}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员卡的机器号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****机器号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csecRfid">${csEvCard.csecRfid}</textarea>
		 		<span>
		 	
			 ${csEvCard.csecRfid$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csecRfid}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csecRemark和after$csecRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csecRemark==true">
	${before$csecRemark}
	<dl class="csecRemark " minor  ref="csecRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csecRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csEvCard$csecRemark!=null">${csEvCard$csecRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csecRemark" name="csEvCard.csecRemark" rows="5">${csEvCard.csecRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员卡的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csEvCard.csecRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csecRemark}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csecAddTime和after$csecAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csecAddTime==true">
	${before$csecAddTime}
	<dl class="csecAddTime " major  ref="csecAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csecAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csEvCard$csecAddTime!=null">${csEvCard$csecAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csEvCard.csecAddTime" id="csecAddTime"  value="<s:date name="csEvCard.csecAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员卡的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csecAddTime">${csEvCard.csecAddTime}</textarea>
		 		<span>
		 	
			 ${csEvCard.csecAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csecAddTime}
	</s:if>
	
	${lz:set("注释","*****************已绑定？字段的输入框代码*****************")}
	${lz:set("注释","before$csecFlag和after$csecFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csecFlag==true">
	${before$csecFlag}
	<dl class="csecFlag " major  ref="csecFlag" >
		<dt>已绑定？:</dt>
		<s:if test="#request.CTRL.e.csecFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csEvCard$csecFlag!=null">${csEvCard$csecFlag}</s:if><s:else>
		 	<select class="narrow" id="csecFlag" name="csEvCard.csecFlag">
		 		<option value="">请选择</option>
				<option value="0" ${csEvCard.csecFlag==0?"selected":""}>未绑定</option>
				<option value="1" ${csEvCard.csecFlag==1?"selected":""}>已绑定</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员卡的已绑定？</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****已绑定？字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csecFlag">${csEvCard.csecFlag}</textarea>
		 		<span>
		 	
			 ${csEvCard.csecFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csecFlag}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csecStatus和after$csecStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csecStatus==true">
	${before$csecStatus}
	<dl class="csecStatus " major  ref="csecStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csecStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csEvCard$csecStatus!=null">${csEvCard$csecStatus}</s:if><s:else>
		 	<select class="narrow" id="csecStatus" name="csEvCard.csecStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csEvCard.csecStatus==1?"selected":""}>正常</option>
				<option value="2" ${csEvCard.csecStatus==2?"selected":""}>挂失</option>
				<option value="0" ${csEvCard.csecStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员卡的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csecStatus">${csEvCard.csecStatus}</textarea>
		 		<span>
		 	
			 ${csEvCard.csecStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csecStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csEvCard.csecId}" name="csEvCard.csecId" id="csecId" />
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