<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty controller.title?"价格配置编辑":controller.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
		
	<!-- 感谢kindeditor出品的在线HTML编辑控件 -->
	<script type="text/javascript" charset="utf-8" src="${basePath}admin/js/kindeditor/kindeditor.js"></script>
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>

</head>  
<body ${(controller.mutual=="page"||(controller.mutual!="dialog" && false))?"class='pagetype'":""}>

<script>

$(function(){	
	showTips("${tips.value}");
});


$(function(){
		 
	${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitFee.csufId))}
	${lz:set("haveEditable",false)}
	$("#feeForm").form({
		"":function(){}
		,"csUnitFee.csufName":function(el){
			if(jQuery.trim(el.value)=="")
				return "名称不能为空";
			if(el.value.length>32)
				return "名称最大长度为32个字符";
		}
		,"csUnitFee.csufModel":function(el){
			if(jQuery.trim(el.value)!=""){
				var json = $.getObject("fee.query.do",{csufModel:el.value});
				if(json.exists==true){
					if(${csUnitFee.csufId==null} || json.id!=$("#csufId").val())
						return "车型已存在";						
				}
			}
		}
		,"csUnitFee.csufDayPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "天租价格输入格式错误";
		}
		,"csUnitFee.csufHourPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "小时价格输入格式错误";
		}
		,"csUnitFee.csufMileage":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "里程费输入格式错误";
		}
	});		
});
</script>

<form class="form editform" id="feeForm" name="feeForm" action="fee.save.do" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">	
	
		<div class="prompt">
			温馨提示：请仔细填写价格配置相关信息，<span class="extrude">"*" 为必填选项。</span>
		</div>
		
		<dl ref="csufName" >
			<dt>名称:</dt>
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitFee.csufName" id="csufName"  value="${csUnitFee.csufName}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入价格配置的名称</em>
			</dd>			
		</dl>
		
		<dl ref="csufModel" >
			<dt>车型:</dt>
			<dd>
			 	<select class="combox narrow"  action="${basePath}unit/object/carmodel_query.do?size=-1" id="csufModel" name="csUnitFee.csufModel">
		 				<option selected value="${csUnitFee.csufModel}">
		 					${lz:get('ccclubs_system','cs_car_model',csUnitFee.csufModel).cscm_name}
		 				</option>
		 		</select>	 	
		 	 	<b>*</b>
		 	 <em>请选择价格配置的车型</em>
			</dd>			
		</dl>		
		
		<dl ref="csufDayPrice" >
			<dt>天租价格:</dt>			
			<dd>
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitFee.csufDayPrice" id="csufDayPrice"  value="${csUnitFee.csufDayPrice}"/>
		 	 	<b>*</b>
		 	 	<em>请输入价格配置的天租价格</em>
			</dd>			
		</dl>
		
		<dl ref="csufHourPrice" >
			<dt>小时价格:</dt>			
			<dd>
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitFee.csufHourPrice" id="csufHourPrice"  value="${csUnitFee.csufHourPrice}"/>
		 	 	<b>*</b>
		 	 	<em>请输入价格配置的小时价格</em>
			</dd>
		</dl>	
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csufMileage" >
			<dt>里程费:</dt>
			<dd>
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitFee.csufMileage" id="csufMileage"  value="${csUnitFee.csufMileage}"/>
		 	 	<b>*</b>
		 	 	<em>设置为0表示不启用该价格</em>
			</dd>
		</dl>
		
		<div class="line"></div>
		<center>
			<input type="hidden" value="${csUnitFeeToken}" name="csUnitFeeToken" id="csUnitFeeToken" />
			<input type="hidden" value="${csUnitFee.csufId}" name="csUnitFee.csufId" id="csufId" />
			<button class="button" type="submit" name="submiter" value="完成">&nbsp;&nbsp;完成&nbsp;&nbsp;</button>
			<button class="button" type="reset">&nbsp;&nbsp;重置&nbsp;&nbsp;</button>
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
		</center>
		
	</div>	
</div>
<div class="foot"></div>
	
</form>

<script>
	function cancel(){
		$.closeModalDialog();
	}
	
	function href(url,params){
		if(${controller.mutual=="dialog"})
			top.$.showModalDialog({url:url},params,window);
		else if(${controller.mutual=="page"})
			$.submit(url,params,"_self");
		else if(true)
			top.$.showModalDialog({url:url},params,window);
		else
			$.submit(url,params,"_self");	
	}
</script>

</body>
</html>