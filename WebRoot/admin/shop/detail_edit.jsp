<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/shop/detail.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/shop/detail.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csShopOrderItem.cssoiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cssoiId,cssoiOrder,cssoiItem,cssoiMember,cssoiTitle,cssoiPrice,cssoiRebate,cssoiCount,cssoiMoney,cssoiRealpay,cssoiStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssoiId,cssoiOrder,cssoiItem,cssoiMember,cssoiTitle,cssoiPrice,cssoiRebate,cssoiCount,cssoiMoney,cssoiRealpay,cssoiStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cssoiId,cssoiOrder,cssoiItem,cssoiMember,cssoiTitle,cssoiPrice,cssoiRebate,cssoiCount,cssoiMoney,cssoiRealpay,cssoiStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssoiId,cssoiOrder,cssoiItem,cssoiMember,cssoiTitle,cssoiPrice,cssoiRebate,cssoiCount,cssoiMoney,cssoiRealpay,cssoiEditor,cssoiStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"订单明细编辑":CTRL.title}</title>
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
	$("#detailForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cssoiId==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiOrder==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiItem==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiItem":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiMember==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiTitle==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "商品名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "商品名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiPrice==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "商品价格输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiRebate==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiRebate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "优惠折扣输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiCount==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiCount":function(el){
			if(jQuery.trim(el.value)=="")
				return "购买数量不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiMoney==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiMoney":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "金额小计输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiRealpay==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiRealpay":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "实际金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiEditor==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiRemark==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiAddTime==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoiStatus==true">
		${lz:set("haveEditable",true)}
		,"csShopOrderItem.cssoiStatus":function(el){
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
	<form class="form editform" ref="CsShopOrderItem" id="detailForm" name="detailForm" action="${empty CTRL.action?"detail_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写订单明细相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csShopOrderItemToken}" name="csShopOrderItemToken" id="csShopOrderItemToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csShopOrderItem.cssoiId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiId和after$cssoiId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiId==true">
	${before$cssoiId}
	<dl class="cssoiId ${CTRL.e.cssoiId?"hidden":""}" major  ref="cssoiId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cssoiId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csShopOrderItem$cssoiId!=null">${csShopOrderItem$cssoiId}</s:if><s:else>
			<input type="hidden" value="${csShopOrderItem.cssoiId}" name="csShopOrderItem.cssoiId" id="cssoiId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiId">${csShopOrderItem.cssoiId}</textarea>
		 		<span>
		 	
			 ${csShopOrderItem.cssoiId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiId}
	</s:if>
	
	${lz:set("注释","*****************所属订单字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiOrder和after$cssoiOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiOrder==true">
	${before$cssoiOrder}
	<dl class="cssoiOrder " major  ref="cssoiOrder" >
		<dt>所属订单:</dt>
		<s:if test="#request.CTRL.e.cssoiOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csShopOrderItem$cssoiOrder!=null">${csShopOrderItem$cssoiOrder}</s:if><s:else>
		 			<input title="请输入商城订单编号进行查询" class="combox narrow" action="${basePath}${proname}/shop/order_query.do?value={param}" type="text" id="cssoiOrder" name="csShopOrderItem.cssoiOrder" text="${get:CsShopOrder(csShopOrderItem.cssoiOrder).cssoId}" value="${csShopOrderItem.cssoiOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoiOrder').val())==''){return;};window.href('${basePath}${proname}/shop/order_details.do?key='+$('#cssoiOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiOrder">${csShopOrderItem.cssoiOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/shop/order_details.do?key=${csShopOrderItem.cssoiOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csShopOrderItem.cssoiOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiOrder}
	</s:if>
	
	${lz:set("注释","*****************商品信息字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiItem和after$cssoiItem变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiItem==true">
	${before$cssoiItem}
	<dl class="cssoiItem " major  ref="cssoiItem" >
		<dt>商品信息:</dt>
		<s:if test="#request.CTRL.e.cssoiItem==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csShopOrderItem$cssoiItem!=null">${csShopOrderItem$cssoiItem}</s:if><s:else>
		 			<input title="请输入商品信息商品名称进行查询" class="combox narrow" action="${basePath}${proname}/fee/item_query.do?value={param}" type="text" id="cssoiItem" name="csShopOrderItem.cssoiItem" text="${get:CsItem(csShopOrderItem.cssoiItem).csiTitle}" value="${csShopOrderItem.cssoiItem}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoiItem').val())==''){return;};window.href('${basePath}${proname}/fee/item_details.do?key='+$('#cssoiItem').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商品信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiItem">${csShopOrderItem.cssoiItem}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/item_details.do?key=${csShopOrderItem.cssoiItem}',{ctrl:{editable:false,visible:true}})">
			 ${csShopOrderItem.cssoiItem$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiItem}
	</s:if>
	
	${lz:set("注释","*****************会员帐号字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiMember和after$cssoiMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiMember==true">
	${before$cssoiMember}
	<dl class="cssoiMember " major  ref="cssoiMember" >
		<dt>会员帐号:</dt>
		<s:if test="#request.CTRL.e.cssoiMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csShopOrderItem$cssoiMember!=null">${csShopOrderItem$cssoiMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}" type="text" id="cssoiMember" name="csShopOrderItem.cssoiMember" text="${get:CsMember(csShopOrderItem.cssoiMember).csmName}" value="${csShopOrderItem.cssoiMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoiMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cssoiMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiMember">${csShopOrderItem.cssoiMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csShopOrderItem.cssoiMember}',{ctrl:{editable:false,visible:true}})">
			 ${csShopOrderItem.cssoiMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiMember}
	</s:if>
	
	${lz:set("注释","*****************商品名称字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiTitle和after$cssoiTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiTitle==true">
	${before$cssoiTitle}
	<dl class="cssoiTitle " major  ref="cssoiTitle" >
		<dt>商品名称:</dt>
		<s:if test="#request.CTRL.e.cssoiTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csShopOrderItem$cssoiTitle!=null">${csShopOrderItem$cssoiTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csShopOrderItem.cssoiTitle" id="cssoiTitle"  value="${csShopOrderItem.cssoiTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单明细的商品名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商品名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiTitle">${csShopOrderItem.cssoiTitle}</textarea>
		 		<span>
		 	
			 ${csShopOrderItem.cssoiTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiTitle}
	</s:if>
	
	${lz:set("注释","*****************商品价格字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiPrice和after$cssoiPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiPrice==true">
	${before$cssoiPrice}
	<dl class="cssoiPrice " major  ref="cssoiPrice" >
		<dt>商品价格:</dt>
		<s:if test="#request.CTRL.e.cssoiPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csShopOrderItem$cssoiPrice!=null">${csShopOrderItem$cssoiPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csShopOrderItem.cssoiPrice" id="cssoiPrice"  value="${csShopOrderItem.cssoiPrice}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单明细的商品价格</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商品价格字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiPrice">${csShopOrderItem.cssoiPrice}</textarea>
		 		<span>
		 	
			 ${csShopOrderItem.cssoiPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiPrice}
	</s:if>
	
	${lz:set("注释","*****************优惠折扣字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiRebate和after$cssoiRebate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiRebate==true">
	${before$cssoiRebate}
	<dl class="cssoiRebate " major  ref="cssoiRebate" >
		<dt>优惠折扣:</dt>
		<s:if test="#request.CTRL.e.cssoiRebate==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csShopOrderItem$cssoiRebate!=null">${csShopOrderItem$cssoiRebate}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csShopOrderItem.cssoiRebate" id="cssoiRebate"  value="${csShopOrderItem.cssoiRebate}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单明细的优惠折扣</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优惠折扣字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiRebate">${csShopOrderItem.cssoiRebate}</textarea>
		 		<span>
		 	
			 ${csShopOrderItem.cssoiRebate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiRebate}
	</s:if>
	
	${lz:set("注释","*****************购买数量字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiCount和after$cssoiCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiCount==true">
	${before$cssoiCount}
	<dl class="cssoiCount " major  ref="cssoiCount" >
		<dt>购买数量:</dt>
		<s:if test="#request.CTRL.e.cssoiCount==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csShopOrderItem$cssoiCount!=null">${csShopOrderItem$cssoiCount}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csShopOrderItem.cssoiCount" id="cssoiCount"  value="${csShopOrderItem.cssoiCount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单明细的购买数量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****购买数量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiCount">${csShopOrderItem.cssoiCount}</textarea>
		 		<span>
		 	
			 ${csShopOrderItem.cssoiCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiCount}
	</s:if>
	
	${lz:set("注释","*****************金额小计字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiMoney和after$cssoiMoney变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiMoney==true">
	${before$cssoiMoney}
	<dl class="cssoiMoney " major  ref="cssoiMoney" >
		<dt>金额小计:</dt>
		<s:if test="#request.CTRL.e.cssoiMoney==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csShopOrderItem$cssoiMoney!=null">${csShopOrderItem$cssoiMoney}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csShopOrderItem.cssoiMoney" id="cssoiMoney"  value="${csShopOrderItem.cssoiMoney}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单明细的金额小计</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****金额小计字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiMoney">${csShopOrderItem.cssoiMoney}</textarea>
		 		<span>
		 	
			 ${csShopOrderItem.cssoiMoney$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiMoney}
	</s:if>
	
	${lz:set("注释","*****************实际金额字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiRealpay和after$cssoiRealpay变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiRealpay==true">
	${before$cssoiRealpay}
	<dl class="cssoiRealpay " major  ref="cssoiRealpay" >
		<dt>实际金额:</dt>
		<s:if test="#request.CTRL.e.cssoiRealpay==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csShopOrderItem$cssoiRealpay!=null">${csShopOrderItem$cssoiRealpay}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csShopOrderItem.cssoiRealpay" id="cssoiRealpay"  value="${csShopOrderItem.cssoiRealpay}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单明细的实际金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiRealpay">${csShopOrderItem.cssoiRealpay}</textarea>
		 		<span>
		 	
			 ${csShopOrderItem.cssoiRealpay$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiRealpay}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiEditor和after$cssoiEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiEditor==true">
	${before$cssoiEditor}
	<dl class="cssoiEditor " major  ref="cssoiEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cssoiEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csShopOrderItem$cssoiEditor!=null">${csShopOrderItem$cssoiEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cssoiEditor" name="csShopOrderItem.cssoiEditor">
		 				<option selected value="${csShopOrderItem.cssoiEditor}">
		 					${get:SrvUser(csShopOrderItem.cssoiEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoiEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cssoiEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiEditor">${csShopOrderItem.cssoiEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csShopOrderItem.cssoiEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csShopOrderItem.cssoiEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiEditor}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiRemark和after$cssoiRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiRemark==true">
	${before$cssoiRemark}
	<dl class="cssoiRemark " minor  ref="cssoiRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.cssoiRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csShopOrderItem$cssoiRemark!=null">${csShopOrderItem$cssoiRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cssoiRemark" name="csShopOrderItem.cssoiRemark" rows="5">${csShopOrderItem.cssoiRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入订单明细的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csShopOrderItem.cssoiRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiRemark}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiUpdateTime和after$cssoiUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiUpdateTime==true">
	${before$cssoiUpdateTime}
	<dl class="cssoiUpdateTime " major  ref="cssoiUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cssoiUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csShopOrderItem$cssoiUpdateTime!=null">${csShopOrderItem$cssoiUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csShopOrderItem.cssoiUpdateTime" id="cssoiUpdateTime"  value="<s:date name="csShopOrderItem.cssoiUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择订单明细的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiUpdateTime">${csShopOrderItem.cssoiUpdateTime}</textarea>
		 		<span>
		 	
			 ${csShopOrderItem.cssoiUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiAddTime和after$cssoiAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiAddTime==true">
	${before$cssoiAddTime}
	<dl class="cssoiAddTime " major  ref="cssoiAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cssoiAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csShopOrderItem$cssoiAddTime!=null">${csShopOrderItem$cssoiAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csShopOrderItem.cssoiAddTime" id="cssoiAddTime"  value="<s:date name="csShopOrderItem.cssoiAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择订单明细的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiAddTime">${csShopOrderItem.cssoiAddTime}</textarea>
		 		<span>
		 	
			 ${csShopOrderItem.cssoiAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cssoiStatus和after$cssoiStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoiStatus==true">
	${before$cssoiStatus}
	<dl class="cssoiStatus " major  ref="cssoiStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cssoiStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csShopOrderItem$cssoiStatus!=null">${csShopOrderItem$cssoiStatus}</s:if><s:else>
		 	<select class="narrow" id="cssoiStatus" name="csShopOrderItem.cssoiStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csShopOrderItem.cssoiStatus==1?"selected":""}>正常</option>
				<option value="0" ${csShopOrderItem.cssoiStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择订单明细的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoiStatus">${csShopOrderItem.cssoiStatus}</textarea>
		 		<span>
		 	
			 ${csShopOrderItem.cssoiStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoiStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csShopOrderItem.cssoiId}" name="csShopOrderItem.cssoiId" id="cssoiId" />
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