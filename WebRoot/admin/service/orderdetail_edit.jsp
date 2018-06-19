<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/orderdetail.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/service/orderdetail.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrderDetail.csodId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csodId,csodHost,csodOrder,csodProduct,csodGoods,csodPricer,csodOutlets,csodModel,csodCar,csodProvid,csodRule,csodCount,csodCountReal,csodPrice,csodPriceReal,csodTotal,csodRebate,csodTotalReal,csodRemark",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csodId,csodHost,csodOrder,csodProduct,csodGoods,csodPricer,csodOutlets,csodModel,csodCar,csodProvid,csodRule,csodCount,csodCountReal,csodPrice,csodPriceReal,csodTotal,csodRebate,csodTotalReal,csodRemark",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csodId,csodHost,csodOrder,csodProduct,csodGoods,csodPricer,csodOutlets,csodModel,csodCar,csodProvid,csodRule,csodCount,csodCountReal,csodPrice,csodPriceReal,csodTotal,csodRebate,csodTotalReal,csodRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csodId,csodHost,csodOrder,csodProduct,csodGoods,csodPricer,csodOutlets,csodModel,csodCar,csodProvid,csodStart,csodEnd,csodRule,csodCount,csodCountReal,csodPrice,csodPriceReal,csodTotal,csodRebate,csodTotalReal,csodRemark,csodAddTime",
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
	$("#orderdetailForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csodId==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodHost==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodOrder==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodProduct==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodProduct":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodGoods==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodGoods":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodPricer==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodPricer":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodOutlets==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodOutlets":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodModel==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodModel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodCar==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodProvid==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodProvid":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodStart==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodStart":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodEnd==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodEnd":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodRule==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodRule":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodCount==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodCount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "理论数量输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodCountReal==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodCountReal":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "实际数量输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodPrice==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "理论单价输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodPriceReal==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodPriceReal":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "实际单价输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodTotal==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodTotal":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "理论金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodRebate==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodRebate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "折扣输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodTotalReal==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodTotalReal":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "实际金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodRemark==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csodAddTime==true">
		${lz:set("haveEditable",true)}
		,"csOrderDetail.csodAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
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
	<form class="form editform" ref="CsOrderDetail" id="orderdetailForm" name="orderdetailForm" action="${empty CTRL.action?"orderdetail_save.do":CTRL.action}" method="post">
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
				<input type="hidden" value="${csOrderDetailToken}" name="csOrderDetailToken" id="csOrderDetailToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrderDetail.csodId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csodId和after$csodId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodId==true">
	${before$csodId}
	<dl class="csodId ${CTRL.e.csodId?"hidden":""}" major  ref="csodId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csodId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csOrderDetail$csodId!=null">${csOrderDetail$csodId}</s:if><s:else>
			<input type="hidden" value="${csOrderDetail.csodId}" name="csOrderDetail.csodId" id="csodId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodId">${csOrderDetail.csodId}</textarea>
		 		<span>
		 	
			 ${csOrderDetail.csodId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csodHost和after$csodHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodHost==true">
	${before$csodHost}
	<dl class="csodHost " major  ref="csodHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csodHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrderDetail$csodHost!=null">${csOrderDetail$csodHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csodHost" name="csOrderDetail.csodHost">
		 				<option selected value="${csOrderDetail.csodHost}">
		 					${get:SrvHost(csOrderDetail.csodHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csodHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csodHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodHost">${csOrderDetail.csodHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csOrderDetail.csodHost}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderDetail.csodHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodHost}
	</s:if>
	
	${lz:set("注释","*****************所属定单字段的输入框代码*****************")}
	${lz:set("注释","before$csodOrder和after$csodOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodOrder==true">
	${before$csodOrder}
	<dl class="csodOrder " major  ref="csodOrder" >
		<dt>所属定单:</dt>
		<s:if test="#request.CTRL.e.csodOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrderDetail$csodOrder!=null">${csOrderDetail$csodOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csodHost}" type="text" id="csodOrder" name="csOrderDetail.csodOrder" text="${get:CsOrder(csOrderDetail.csodOrder).csoId}" value="${csOrderDetail.csodOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csodOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csodOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属定单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodOrder">${csOrderDetail.csodOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csOrderDetail.csodOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderDetail.csodOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodOrder}
	</s:if>
	
	${lz:set("注释","*****************所属产品字段的输入框代码*****************")}
	${lz:set("注释","before$csodProduct和after$csodProduct变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodProduct==true">
	${before$csodProduct}
	<dl class="csodProduct " major  ref="csodProduct" >
		<dt>所属产品:</dt>
		<s:if test="#request.CTRL.e.csodProduct==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrderDetail$csodProduct!=null">${csOrderDetail$csodProduct}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/fee/product_query.do?size=-1" id="csodProduct" name="csOrderDetail.csodProduct">
		 				<option selected value="${csOrderDetail.csodProduct}">
		 					${get:CsProduct(csOrderDetail.csodProduct).cspName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csodProduct').val())==''){return;};window.href('${basePath}${proname}/fee/product_details.do?key='+$('#csodProduct').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属产品字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodProduct">${csOrderDetail.csodProduct}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/product_details.do?key=${csOrderDetail.csodProduct}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderDetail.csodProduct$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodProduct}
	</s:if>
	
	${lz:set("注释","*****************所属商品字段的输入框代码*****************")}
	${lz:set("注释","before$csodGoods和after$csodGoods变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodGoods==true">
	${before$csodGoods}
	<dl class="csodGoods " major  ref="csodGoods" >
		<dt>所属商品:</dt>
		<s:if test="#request.CTRL.e.csodGoods==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrderDetail$csodGoods!=null">${csOrderDetail$csodGoods}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/fee/goods_query.do?size=-1" id="csodGoods" name="csOrderDetail.csodGoods">
		 				<option selected value="${csOrderDetail.csodGoods}">
		 					${get:CsGoods(csOrderDetail.csodGoods).csgName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csodGoods').val())==''){return;};window.href('${basePath}${proname}/fee/goods_details.do?key='+$('#csodGoods').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属商品字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodGoods">${csOrderDetail.csodGoods}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/goods_details.do?key=${csOrderDetail.csodGoods}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderDetail.csodGoods$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodGoods}
	</s:if>
	
	${lz:set("注释","*****************所属价格字段的输入框代码*****************")}
	${lz:set("注释","before$csodPricer和after$csodPricer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodPricer==true">
	${before$csodPricer}
	<dl class="csodPricer " major  ref="csodPricer" >
		<dt>所属价格:</dt>
		<s:if test="#request.CTRL.e.csodPricer==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrderDetail$csodPricer!=null">${csOrderDetail$csodPricer}</s:if><s:else>
		 			<input title="请输入价格编号进行查询" class="combox narrow" action="${basePath}${proname}/fee/price_query.do?value={param}" type="text" id="csodPricer" name="csOrderDetail.csodPricer" text="${get:CsPrice(csOrderDetail.csodPricer).cspId}" value="${csOrderDetail.csodPricer}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csodPricer').val())==''){return;};window.href('${basePath}${proname}/fee/price_details.do?key='+$('#csodPricer').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属价格字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodPricer">${csOrderDetail.csodPricer}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/price_details.do?key=${csOrderDetail.csodPricer}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderDetail.csodPricer$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodPricer}
	</s:if>
	
	${lz:set("注释","*****************所属网点字段的输入框代码*****************")}
	${lz:set("注释","before$csodOutlets和after$csodOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodOutlets==true">
	${before$csodOutlets}
	<dl class="csodOutlets " major  ref="csodOutlets" >
		<dt>所属网点:</dt>
		<s:if test="#request.CTRL.e.csodOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrderDetail$csodOutlets!=null">${csOrderDetail$csodOutlets}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&csoHost={csodHost}" id="csodOutlets" name="csOrderDetail.csodOutlets">
		 				<option selected value="${csOrderDetail.csodOutlets}">
		 					${get:CsOutlets(csOrderDetail.csodOutlets).csoName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csodOutlets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#csodOutlets').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodOutlets">${csOrderDetail.csodOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csOrderDetail.csodOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderDetail.csodOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodOutlets}
	</s:if>
	
	${lz:set("注释","*****************所属车型字段的输入框代码*****************")}
	${lz:set("注释","before$csodModel和after$csodModel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodModel==true">
	${before$csodModel}
	<dl class="csodModel " major  ref="csodModel" >
		<dt>所属车型:</dt>
		<s:if test="#request.CTRL.e.csodModel==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrderDetail$csodModel!=null">${csOrderDetail$csodModel}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/carmodel_query.do?size=-1" id="csodModel" name="csOrderDetail.csodModel">
		 				<option selected value="${csOrderDetail.csodModel}">
		 					${get:CsCarModel(csOrderDetail.csodModel).cscmName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csodModel').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?key='+$('#csodModel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属车型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodModel">${csOrderDetail.csodModel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${csOrderDetail.csodModel}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderDetail.csodModel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodModel}
	</s:if>
	
	${lz:set("注释","*****************所属车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csodCar和after$csodCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodCar==true">
	${before$csodCar}
	<dl class="csodCar " major  ref="csodCar" >
		<dt>所属车辆:</dt>
		<s:if test="#request.CTRL.e.csodCar==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrderDetail$csodCar!=null">${csOrderDetail$csodCar}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/car_query.do?size=-1&cscHost={csodHost}" id="csodCar" name="csOrderDetail.csodCar">
		 				<option selected value="${csOrderDetail.csodCar}">
		 					${get:CsCar(csOrderDetail.csodCar).cscNumber}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csodCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#csodCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodCar">${csOrderDetail.csodCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csOrderDetail.csodCar}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderDetail.csodCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodCar}
	</s:if>
	
	${lz:set("注释","*****************所属商家字段的输入框代码*****************")}
	${lz:set("注释","before$csodProvid和after$csodProvid变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodProvid==true">
	${before$csodProvid}
	<dl class="csodProvid " major  ref="csodProvid" >
		<dt>所属商家:</dt>
		<s:if test="#request.CTRL.e.csodProvid==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrderDetail$csodProvid!=null">${csOrderDetail$csodProvid}</s:if><s:else>
		 			<input title="请输入供应商名称进行查询" class="combox narrow" action="${basePath}${proname}/configurator/provid_query.do?value={param}" type="text" id="csodProvid" name="csOrderDetail.csodProvid" text="${get:CsProvid(csOrderDetail.csodProvid).cspName}" value="${csOrderDetail.csodProvid}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csodProvid').val())==''){return;};window.href('${basePath}${proname}/configurator/provid_details.do?key='+$('#csodProvid').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属商家字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodProvid">${csOrderDetail.csodProvid}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/provid_details.do?key=${csOrderDetail.csodProvid}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderDetail.csodProvid$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodProvid}
	</s:if>
	
	${lz:set("注释","*****************开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csodStart和after$csodStart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodStart==true">
	${before$csodStart}
	<dl class="csodStart " major  ref="csodStart" >
		<dt>开始时间:</dt>
		<s:if test="#request.CTRL.e.csodStart==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrderDetail$csodStart!=null">${csOrderDetail$csodStart}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrderDetail.csodStart" id="csodStart"  value="<s:date name="csOrderDetail.csodStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择订单明细的开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodStart">${csOrderDetail.csodStart}</textarea>
		 		<span>
		 	
			 ${csOrderDetail.csodStart$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodStart}
	</s:if>
	
	${lz:set("注释","*****************结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csodEnd和after$csodEnd变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodEnd==true">
	${before$csodEnd}
	<dl class="csodEnd " major  ref="csodEnd" >
		<dt>结束时间:</dt>
		<s:if test="#request.CTRL.e.csodEnd==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrderDetail$csodEnd!=null">${csOrderDetail$csodEnd}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrderDetail.csodEnd" id="csodEnd"  value="<s:date name="csOrderDetail.csodEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择订单明细的结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodEnd">${csOrderDetail.csodEnd}</textarea>
		 		<span>
		 	
			 ${csOrderDetail.csodEnd$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodEnd}
	</s:if>
	
	${lz:set("注释","*****************单位字段的输入框代码*****************")}
	${lz:set("注释","before$csodRule和after$csodRule变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodRule==true">
	${before$csodRule}
	<dl class="csodRule " major  ref="csodRule" >
		<dt>单　　位:</dt>
		<s:if test="#request.CTRL.e.csodRule==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrderDetail$csodRule!=null">${csOrderDetail$csodRule}</s:if><s:else>
		 			<input title="请输入规则规则名称进行查询" class="combox narrow" action="${basePath}${proname}/fee/rule_query.do?value={param}" type="text" id="csodRule" name="csOrderDetail.csodRule" text="${get:CsRule(csOrderDetail.csodRule).csrName}" value="${csOrderDetail.csodRule}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csodRule').val())==''){return;};window.href('${basePath}${proname}/fee/rule_details.do?key='+$('#csodRule').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****单位字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodRule">${csOrderDetail.csodRule}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/rule_details.do?key=${csOrderDetail.csodRule}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderDetail.csodRule$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodRule}
	</s:if>
	
	${lz:set("注释","*****************理论数量字段的输入框代码*****************")}
	${lz:set("注释","before$csodCount和after$csodCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodCount==true">
	${before$csodCount}
	<dl class="csodCount " major  ref="csodCount" >
		<dt>理论数量:</dt>
		<s:if test="#request.CTRL.e.csodCount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrderDetail$csodCount!=null">${csOrderDetail$csodCount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrderDetail.csodCount" id="csodCount"  value="${csOrderDetail.csodCount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单明细的理论数量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****理论数量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodCount">${csOrderDetail.csodCount}</textarea>
		 		<span>
		 	
			 ${csOrderDetail.csodCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodCount}
	</s:if>
	
	${lz:set("注释","*****************实际数量字段的输入框代码*****************")}
	${lz:set("注释","before$csodCountReal和after$csodCountReal变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodCountReal==true">
	${before$csodCountReal}
	<dl class="csodCountReal " major  ref="csodCountReal" >
		<dt>实际数量:</dt>
		<s:if test="#request.CTRL.e.csodCountReal==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrderDetail$csodCountReal!=null">${csOrderDetail$csodCountReal}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrderDetail.csodCountReal" id="csodCountReal"  value="${csOrderDetail.csodCountReal}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单明细的实际数量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际数量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodCountReal">${csOrderDetail.csodCountReal}</textarea>
		 		<span>
		 	
			 ${csOrderDetail.csodCountReal$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodCountReal}
	</s:if>
	
	${lz:set("注释","*****************理论单价字段的输入框代码*****************")}
	${lz:set("注释","before$csodPrice和after$csodPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodPrice==true">
	${before$csodPrice}
	<dl class="csodPrice " major  ref="csodPrice" >
		<dt>理论单价:</dt>
		<s:if test="#request.CTRL.e.csodPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrderDetail$csodPrice!=null">${csOrderDetail$csodPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrderDetail.csodPrice" id="csodPrice"  value="${csOrderDetail.csodPrice}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单明细的理论单价</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****理论单价字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodPrice">${csOrderDetail.csodPrice}</textarea>
		 		<span>
		 	
			 ${csOrderDetail.csodPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodPrice}
	</s:if>
	
	${lz:set("注释","*****************实际单价字段的输入框代码*****************")}
	${lz:set("注释","before$csodPriceReal和after$csodPriceReal变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodPriceReal==true">
	${before$csodPriceReal}
	<dl class="csodPriceReal " major  ref="csodPriceReal" >
		<dt>实际单价:</dt>
		<s:if test="#request.CTRL.e.csodPriceReal==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrderDetail$csodPriceReal!=null">${csOrderDetail$csodPriceReal}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrderDetail.csodPriceReal" id="csodPriceReal"  value="${csOrderDetail.csodPriceReal}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单明细的实际单价</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际单价字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodPriceReal">${csOrderDetail.csodPriceReal}</textarea>
		 		<span>
		 	
			 ${csOrderDetail.csodPriceReal$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodPriceReal}
	</s:if>
	
	${lz:set("注释","*****************理论金额字段的输入框代码*****************")}
	${lz:set("注释","before$csodTotal和after$csodTotal变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodTotal==true">
	${before$csodTotal}
	<dl class="csodTotal " major  ref="csodTotal" >
		<dt>理论金额:</dt>
		<s:if test="#request.CTRL.e.csodTotal==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrderDetail$csodTotal!=null">${csOrderDetail$csodTotal}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrderDetail.csodTotal" id="csodTotal"  value="${csOrderDetail.csodTotal}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单明细的理论金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****理论金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodTotal">${csOrderDetail.csodTotal}</textarea>
		 		<span>
		 	
			 ${csOrderDetail.csodTotal$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodTotal}
	</s:if>
	
	${lz:set("注释","*****************折扣字段的输入框代码*****************")}
	${lz:set("注释","before$csodRebate和after$csodRebate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodRebate==true">
	${before$csodRebate}
	<dl class="csodRebate " major  ref="csodRebate" >
		<dt>折　　扣:</dt>
		<s:if test="#request.CTRL.e.csodRebate==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrderDetail$csodRebate!=null">${csOrderDetail$csodRebate}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrderDetail.csodRebate" id="csodRebate"  value="${csOrderDetail.csodRebate}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>0到1,比如0.75表示七五折</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****折扣字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodRebate">${csOrderDetail.csodRebate}</textarea>
		 		<span>
		 	
			 ${csOrderDetail.csodRebate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodRebate}
	</s:if>
	
	${lz:set("注释","*****************实际金额字段的输入框代码*****************")}
	${lz:set("注释","before$csodTotalReal和after$csodTotalReal变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodTotalReal==true">
	${before$csodTotalReal}
	<dl class="csodTotalReal " major  ref="csodTotalReal" >
		<dt>实际金额:</dt>
		<s:if test="#request.CTRL.e.csodTotalReal==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrderDetail$csodTotalReal!=null">${csOrderDetail$csodTotalReal}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrderDetail.csodTotalReal" id="csodTotalReal"  value="${csOrderDetail.csodTotalReal}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>实付金额=实际单价*实际数量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodTotalReal">${csOrderDetail.csodTotalReal}</textarea>
		 		<span>
		 	
			 ${csOrderDetail.csodTotalReal$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodTotalReal}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csodRemark和after$csodRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodRemark==true">
	${before$csodRemark}
	<dl class="csodRemark " major  ref="csodRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csodRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csOrderDetail$csodRemark!=null">${csOrderDetail$csodRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csodRemark" name="csOrderDetail.csodRemark" rows="5">${csOrderDetail.csodRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入订单明细的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csOrderDetail.csodRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodRemark}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csodAddTime和after$csodAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csodAddTime==true">
	${before$csodAddTime}
	<dl class="csodAddTime " major  ref="csodAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csodAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrderDetail$csodAddTime!=null">${csOrderDetail$csodAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrderDetail.csodAddTime" id="csodAddTime"  value="<s:date name="csOrderDetail.csodAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择订单明细的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csodAddTime">${csOrderDetail.csodAddTime}</textarea>
		 		<span>
		 	
			 ${csOrderDetail.csodAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csodAddTime}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csOrderDetail.csodId}" name="csOrderDetail.csodId" id="csodId" />
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