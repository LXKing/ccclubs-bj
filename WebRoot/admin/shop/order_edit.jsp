<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/shop/order.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/shop/order.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csShopOrder.cssoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cssoId,cssoMember,cssoMoney,cssoRealPay,cssoMemo,cssoStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssoId,cssoMember,cssoMoney,cssoRealPay,cssoMemo,cssoStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cssoId,cssoMember,cssoMoney,cssoRealPay,cssoMemo,cssoStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssoId,cssoMember,cssoMoney,cssoRealPay,cssoEditor,cssoMemo,cssoStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"商城订单编辑":CTRL.title}</title>
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
	$("#orderForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cssoId==true">
		${lz:set("haveEditable",true)}
		,"csShopOrder.cssoId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoMember==true">
		${lz:set("haveEditable",true)}
		,"csShopOrder.cssoMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoMoney==true">
		${lz:set("haveEditable",true)}
		,"csShopOrder.cssoMoney":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "订单金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoRealPay==true">
		${lz:set("haveEditable",true)}
		,"csShopOrder.cssoRealPay":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "实际金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoEditor==true">
		${lz:set("haveEditable",true)}
		,"csShopOrder.cssoEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoMemo==true">
		${lz:set("haveEditable",true)}
		,"csShopOrder.cssoMemo":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "备忘信息最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoData==true">
		${lz:set("haveEditable",true)}
		,"csShopOrder.cssoData":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "程序数据最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csShopOrder.cssoUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoAddTime==true">
		${lz:set("haveEditable",true)}
		,"csShopOrder.cssoAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoStatus==true">
		${lz:set("haveEditable",true)}
		,"csShopOrder.cssoStatus":function(el){
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
	<form class="form editform" ref="CsShopOrder" id="orderForm" name="orderForm" action="${empty CTRL.action?"order_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写商城订单相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csShopOrderToken}" name="csShopOrderToken" id="csShopOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csShopOrder.cssoId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cssoId和after$cssoId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoId==true">
	${before$cssoId}
	<dl class="cssoId ${CTRL.e.cssoId?"hidden":""}" major  ref="cssoId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cssoId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csShopOrder$cssoId!=null">${csShopOrder$cssoId}</s:if><s:else>
			<input type="hidden" value="${csShopOrder.cssoId}" name="csShopOrder.cssoId" id="cssoId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoId">${csShopOrder.cssoId}</textarea>
		 		<span>
		 	
			 ${csShopOrder.cssoId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoId}
	</s:if>
	
	${lz:set("注释","*****************所属会员字段的输入框代码*****************")}
	${lz:set("注释","before$cssoMember和after$cssoMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoMember==true">
	${before$cssoMember}
	<dl class="cssoMember " major  ref="cssoMember" >
		<dt>所属会员:</dt>
		<s:if test="#request.CTRL.e.cssoMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csShopOrder$cssoMember!=null">${csShopOrder$cssoMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}" type="text" id="cssoMember" name="csShopOrder.cssoMember" text="${get:CsMember(csShopOrder.cssoMember).csmName}" value="${csShopOrder.cssoMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cssoMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoMember">${csShopOrder.cssoMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csShopOrder.cssoMember}',{ctrl:{editable:false,visible:true}})">
			 ${csShopOrder.cssoMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoMember}
	</s:if>
	
	${lz:set("注释","*****************订单金额字段的输入框代码*****************")}
	${lz:set("注释","before$cssoMoney和after$cssoMoney变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoMoney==true">
	${before$cssoMoney}
	<dl class="cssoMoney " major  ref="cssoMoney" >
		<dt>订单金额:</dt>
		<s:if test="#request.CTRL.e.cssoMoney==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csShopOrder$cssoMoney!=null">${csShopOrder$cssoMoney}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csShopOrder.cssoMoney" id="cssoMoney"  value="${csShopOrder.cssoMoney}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入商城订单的订单金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoMoney">${csShopOrder.cssoMoney}</textarea>
		 		<span>
		 	
			 ${csShopOrder.cssoMoney$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoMoney}
	</s:if>
	
	${lz:set("注释","*****************实际金额字段的输入框代码*****************")}
	${lz:set("注释","before$cssoRealPay和after$cssoRealPay变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoRealPay==true">
	${before$cssoRealPay}
	<dl class="cssoRealPay " major  ref="cssoRealPay" >
		<dt>实际金额:</dt>
		<s:if test="#request.CTRL.e.cssoRealPay==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csShopOrder$cssoRealPay!=null">${csShopOrder$cssoRealPay}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csShopOrder.cssoRealPay" id="cssoRealPay"  value="${csShopOrder.cssoRealPay}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入商城订单的实际金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoRealPay">${csShopOrder.cssoRealPay}</textarea>
		 		<span>
		 	
			 ${csShopOrder.cssoRealPay$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoRealPay}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$cssoEditor和after$cssoEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoEditor==true">
	${before$cssoEditor}
	<dl class="cssoEditor " major  ref="cssoEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cssoEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csShopOrder$cssoEditor!=null">${csShopOrder$cssoEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cssoEditor" name="csShopOrder.cssoEditor">
		 				<option selected value="${csShopOrder.cssoEditor}">
		 					${get:SrvUser(csShopOrder.cssoEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cssoEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoEditor">${csShopOrder.cssoEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csShopOrder.cssoEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csShopOrder.cssoEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoEditor}
	</s:if>
	
	${lz:set("注释","*****************备忘信息字段的输入框代码*****************")}
	${lz:set("注释","before$cssoMemo和after$cssoMemo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoMemo==true">
	${before$cssoMemo}
	<dl class="cssoMemo " minor  ref="cssoMemo" style="width:98%;">
		<dt>备忘信息:</dt>
		<s:if test="#request.CTRL.e.cssoMemo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csShopOrder$cssoMemo!=null">${csShopOrder$cssoMemo}</s:if><s:else>
		 	<textarea class="input wide"  id="cssoMemo" name="csShopOrder.cssoMemo" rows="5">${csShopOrder.cssoMemo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入商城订单的备忘信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csShopOrder.cssoMemo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoMemo}
	</s:if>
	
	${lz:set("注释","*****************程序数据字段的输入框代码*****************")}
	${lz:set("注释","before$cssoData和after$cssoData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoData==true">
	${before$cssoData}
	<dl class="cssoData " minor  ref="cssoData" style="width:98%;">
		<dt>程序数据:</dt>
		<s:if test="#request.CTRL.e.cssoData==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csShopOrder$cssoData!=null">${csShopOrder$cssoData}</s:if><s:else>
		 	<textarea class="input wide"  id="cssoData" name="csShopOrder.cssoData" rows="5">${csShopOrder.cssoData}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入商城订单的程序数据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csShopOrder.cssoData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoData}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssoUpdateTime和after$cssoUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoUpdateTime==true">
	${before$cssoUpdateTime}
	<dl class="cssoUpdateTime " major  ref="cssoUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cssoUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csShopOrder$cssoUpdateTime!=null">${csShopOrder$cssoUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csShopOrder.cssoUpdateTime" id="cssoUpdateTime"  value="<s:date name="csShopOrder.cssoUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择商城订单的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoUpdateTime">${csShopOrder.cssoUpdateTime}</textarea>
		 		<span>
		 	
			 ${csShopOrder.cssoUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssoAddTime和after$cssoAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoAddTime==true">
	${before$cssoAddTime}
	<dl class="cssoAddTime " major  ref="cssoAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cssoAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csShopOrder$cssoAddTime!=null">${csShopOrder$cssoAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csShopOrder.cssoAddTime" id="cssoAddTime"  value="<s:date name="csShopOrder.cssoAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择商城订单的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoAddTime">${csShopOrder.cssoAddTime}</textarea>
		 		<span>
		 	
			 ${csShopOrder.cssoAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cssoStatus和after$cssoStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoStatus==true">
	${before$cssoStatus}
	<dl class="cssoStatus " major  ref="cssoStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cssoStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csShopOrder$cssoStatus!=null">${csShopOrder$cssoStatus}</s:if><s:else>
		 	<select class="narrow" id="cssoStatus" name="csShopOrder.cssoStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csShopOrder.cssoStatus==0?"selected":""}>待付款</option>
				<option value="1" ${csShopOrder.cssoStatus==1?"selected":""}>待发货</option>
				<option value="2" ${csShopOrder.cssoStatus==2?"selected":""}>已完成</option>
				<option value="3" ${csShopOrder.cssoStatus==3?"selected":""}>已撤销</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择商城订单的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoStatus">${csShopOrder.cssoStatus}</textarea>
		 		<span>
		 	
			 ${csShopOrder.cssoStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csShopOrder.cssoId}" name="csShopOrder.cssoId" id="cssoId" />
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