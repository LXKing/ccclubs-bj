<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty controller.title?"优惠内容编辑":controller.title}</title>
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

window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";

$(function(){
		 
	${lz:set("isAddType",(lz:vacant(ids))&&(empty csGift.csgId))}
	${lz:set("haveEditable",false)}
	$("#giftForm").form({
		"":function(){}
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgId')==null)?canEditable:(lz:editable(controller,'csgId')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgId":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgMember')==null)?canEditable:(lz:editable(controller,'csgMember')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgMember":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgSysPack')==null)?canEditable:(lz:editable(controller,'csgSysPack')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgSysPack":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgUserPack')==null)?canEditable:(lz:editable(controller,'csgUserPack')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgUserPack":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgGoods')==null)?canEditable:(lz:editable(controller,'csgGoods')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgGoods":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgOutlets')==null)?canEditable:(lz:editable(controller,'csgOutlets')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgOutlets":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgModel')==null)?canEditable:(lz:editable(controller,'csgModel')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgModel":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgType')==null)?canEditable:(lz:editable(controller,'csgType')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择优惠类型";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgCount')==null)?canEditable:(lz:editable(controller,'csgCount')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgCount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "原始数量输入格式错误";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgRemain')==null)?canEditable:(lz:editable(controller,'csgRemain')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgRemain":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "可用数量输入格式错误";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgPrice')==null)?canEditable:(lz:editable(controller,'csgPrice')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "优惠价格输入格式错误";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgRebate')==null)?canEditable:(lz:editable(controller,'csgRebate')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgRebate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "优惠折扣输入格式错误";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgUntilTime')==null)?canEditable:(lz:editable(controller,'csgUntilTime')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgUntilTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "有效期至不能为空";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgUpdateTime')==null)?canEditable:(lz:editable(controller,'csgUpdateTime')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgAddTime')==null)?canEditable:(lz:editable(controller,'csgAddTime')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgStatus')==null)?canEditable:(lz:editable(controller,'csgStatus')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csGift.csgStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
	});		
});
</script>

<form class="form editform" id="giftForm" name="giftForm" action="${empty controller.action?"gift_save.do":controller.action}" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">	
	
		<s:if test="#request.haveEditable==true">
		<div class="prompt">
			温馨提示：请仔细填写优惠内容相关信息，<span class="extrude">"*" 为必填选项。</span>
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
		<input type="hidden" value="${ids}" name="ids" id="ids" />
		<input type="hidden" value="${lz:html(lz:json(controller))}" id="controller" name="controller" />
		
		${lz:set("isAddType",(lz:vacant(ids))&&(empty csGift.csgId))}		
		${lz:set("haveEditable",false)}
		${lz:set("havePrimary",false)}
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgId')==null)?canEditable:(lz:editable(controller,'csgId')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgId')==null)?canVisible:(lz:visible(controller,'csgId')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && true)?"class='hidden'":""}  ref="csgId" >
			<dt>编号:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			${lz:set("havePrimary",true)}
			<dd>
				<input type="hidden" value="${csGift.csgId}" name="csGift.csgId" id="csgId" />
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgId}" id="csgId" />
			 		<span>
				${lz:zerofill(csGift.csgId,6)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgMember')==null)?canEditable:(lz:editable(controller,'csgMember')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgMember')==null)?canVisible:(lz:visible(controller,'csgMember')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgMember" >
			<dt>所属会员:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}admin/user/member_inquire.do?value={param}" type="text" id="csgMember" name="csGift.csgMember" text="${get:CsMember(csGift.csgMember).csmName}" value="${csGift.csgMember}" />
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgMember').val())==''){return;};window.href('${basePath}admin/user/member_details.do?id='+$('#csgMember').val(),{controller:{editable:false}})"></a>
		 	 
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgMember}" id="csgMember" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csGift.csgMember}')==''){return;};window.href('${basePath}admin/user/member_details.do?id=${csGift.csgMember}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsMember(csGift.csgMember).csmName}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgSysPack')==null)?canEditable:(lz:editable(controller,'csgSysPack')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgSysPack')==null)?canVisible:(lz:visible(controller,'csgSysPack')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgSysPack" >
			<dt>系统套餐:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<select class="combox narrow" action="${basePath}admin/fee/pack_query.do?size=-1" id="csgSysPack" name="csGift.csgSysPack">
			 				<option selected value="${csGift.csgSysPack}">
			 					${get:CsPack(csGift.csgSysPack).cspName}
			 				</option>
			 			</select>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgSysPack').val())==''){return;};window.href('${basePath}admin/fee/pack_details.do?id='+$('#csgSysPack').val(),{controller:{editable:false}})"></a>
		 	 
		 	 <b>*</b>
		 	 <em>所属于哪个系统套餐</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgSysPack}" id="csgSysPack" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csGift.csgSysPack}')==''){return;};window.href('${basePath}admin/fee/pack_details.do?id=${csGift.csgSysPack}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsPack(csGift.csgSysPack).cspName}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgUserPack')==null)?canEditable:(lz:editable(controller,'csgUserPack')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgUserPack')==null)?canVisible:(lz:visible(controller,'csgUserPack')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgUserPack" >
			<dt>会员套餐:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<input title="请输入用户套餐编号进行查询" class="combox narrow" action="${basePath}admin/user/pack_query.do?value={param}" type="text" id="csgUserPack" name="csGift.csgUserPack" text="${get:CsUserPack(csGift.csgUserPack).csupId}" value="${csGift.csgUserPack}" />
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgUserPack').val())==''){return;};window.href('${basePath}admin/user/pack_details.do?id='+$('#csgUserPack').val(),{controller:{editable:false}})"></a>
		 	 
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgUserPack}" id="csgUserPack" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csGift.csgUserPack}')==''){return;};window.href('${basePath}admin/user/pack_details.do?id=${csGift.csgUserPack}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsUserPack(csGift.csgUserPack).csupId}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgGoods')==null)?canEditable:(lz:editable(controller,'csgGoods')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgGoods')==null)?canVisible:(lz:visible(controller,'csgGoods')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgGoods" >
			<dt>所属商品:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<select class="combox narrow" action="${basePath}admin/fee/goods_query.do?size=-1" id="csgGoods" name="csGift.csgGoods">
			 				<option selected value="${csGift.csgGoods}">
			 					${get:CsGoods(csGift.csgGoods).csgName}
			 				</option>
			 			</select>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgGoods').val())==''){return;};window.href('${basePath}admin/fee/goods_details.do?id='+$('#csgGoods').val(),{controller:{editable:false}})"></a>
		 	 
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgGoods}" id="csgGoods" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csGift.csgGoods}')==''){return;};window.href('${basePath}admin/fee/goods_details.do?id=${csGift.csgGoods}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsGoods(csGift.csgGoods).csgName}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgOutlets')==null)?canEditable:(lz:editable(controller,'csgOutlets')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgOutlets')==null)?canVisible:(lz:visible(controller,'csgOutlets')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgOutlets" >
			<dt>所属网点:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<select class="combox narrow" action="${basePath}admin/object/outlets_query.do?size=-1" id="csgOutlets" name="csGift.csgOutlets">
			 				<option selected value="${csGift.csgOutlets}">
			 					${get:CsOutlets(csGift.csgOutlets).csoName}
			 				</option>
			 			</select>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgOutlets').val())==''){return;};window.href('${basePath}admin/object/outlets_details.do?id='+$('#csgOutlets').val(),{controller:{editable:false}})"></a>
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgOutlets}" id="csgOutlets" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csGift.csgOutlets}')==''){return;};window.href('${basePath}admin/object/outlets_details.do?id=${csGift.csgOutlets}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsOutlets(csGift.csgOutlets).csoName}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgModel')==null)?canEditable:(lz:editable(controller,'csgModel')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgModel')==null)?canVisible:(lz:visible(controller,'csgModel')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgModel" >
			<dt>所属车型:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<select class="combox narrow" action="${basePath}admin/object/carmodel_query.do?size=-1" id="csgModel" name="csGift.csgModel">
			 				<option selected value="${csGift.csgModel}">
			 					${get:CsCarModel(csGift.csgModel).cscmName}
			 				</option>
			 			</select>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgModel').val())==''){return;};window.href('${basePath}admin/object/carmodel_details.do?id='+$('#csgModel').val(),{controller:{editable:false}})"></a>
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgModel}" id="csgModel" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csGift.csgModel}')==''){return;};window.href('${basePath}admin/object/carmodel_details.do?id=${csGift.csgModel}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsCarModel(csGift.csgModel).cscmName}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgType')==null)?canEditable:(lz:editable(controller,'csgType')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgType')==null)?canVisible:(lz:visible(controller,'csgType')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgType" >
			<dt>优惠类型:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<select class="narrow" id="csgType" name="csGift.csgType">
			 		<option value="">请选择</option>
					<option value="0" ${csGift.csgType==0?"selected":""}>数量</option>
					<option value="1" ${csGift.csgType==1?"selected":""}>价格</option>
					<option value="2" ${csGift.csgType==2?"selected":""}>折扣</option>
			 	</select>
		 	 
		 	 <b>*</b>
		 	 <em>请选择优惠内容的优惠类型</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgType}" id="csgType" />
			 		<span>
				 ${csGift.csgType==0?"数量":""}
				 ${csGift.csgType==1?"价格":""}
				 ${csGift.csgType==2?"折扣":""}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgCount')==null)?canEditable:(lz:editable(controller,'csgCount')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgCount')==null)?canVisible:(lz:visible(controller,'csgCount')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgCount" >
			<dt>原始数量:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csGift.csgCount" id="csgCount"  value="${csGift.csgCount}"/>
		 	 
		 	 
		 	 <em>请输入优惠内容的原始数量</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgCount}" id="csgCount" />
			 		<span>
			 				${csGift.csgCount}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgRemain')==null)?canEditable:(lz:editable(controller,'csgRemain')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgRemain')==null)?canVisible:(lz:visible(controller,'csgRemain')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgRemain" >
			<dt>可用数量:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csGift.csgRemain" id="csgRemain"  value="${csGift.csgRemain}"/>
		 	 
		 	 
		 	 <em>请输入优惠内容的可用数量</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgRemain}" id="csgRemain" />
			 		<span>
			 				${csGift.csgRemain}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgPrice')==null)?canEditable:(lz:editable(controller,'csgPrice')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgPrice')==null)?canVisible:(lz:visible(controller,'csgPrice')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgPrice" >
			<dt>优惠价格:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csGift.csgPrice" id="csgPrice"  value="${csGift.csgPrice}"/>
		 	 
		 	 
		 	 <em>请输入优惠内容的优惠价格</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgPrice}" id="csgPrice" />
			 		<span>
			 				${csGift.csgPrice}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgRebate')==null)?canEditable:(lz:editable(controller,'csgRebate')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgRebate')==null)?canVisible:(lz:visible(controller,'csgRebate')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgRebate" >
			<dt>优惠折扣:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csGift.csgRebate" id="csgRebate"  value="${csGift.csgRebate}"/>
		 	 
		 	 
		 	 <em>请输入优惠内容的优惠折扣</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgRebate}" id="csgRebate" />
			 		<span>
			 				${csGift.csgRebate}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'records')==null)?canEditable:(lz:editable(controller,'records')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'records')==null)?canVisible:(lz:visible(controller,'records')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="records" style="width:98%;">
			<dt>使用记录:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			  		<s:if test="csGift.csgId!=null">
			  		${lz:set("uuid",lz:uuid())}
					<iframe class="state-input" onload="$(this).autoHeight()" id="iframe_${uuid}" name="iframe_${uuid}" frameborder='0' src="${basePath}admin/user/userecord.do?all=true&csurGift=${csGift.csgId}&controller={mutual:'dialog',queryable:false,fields:{csurGift:{visible:true,editable:false,defaultValue:${csGift.csgId}}}}&size=8" width='520' height='0'></iframe>
					</s:if>
					<s:else>
					<div class="state-input wide">
					完成或保存表单后即可编辑使用记录
					</div>
					</s:else>
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<s:if test="csGift.csgId!=null">
		  		${lz:set("uuid",lz:uuid())}
				<iframe class="state-input" onload="$(this).autoHeight()" id="iframe_${uuid}" name="iframe_${uuid}" frameborder='0' src="${basePath}admin/user/userecord.do?csurGift=${csGift.csgId}&all=true&controller={mutual:'dialog',canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" width='520' height='0'></iframe>
				</s:if>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgUntilTime')==null)?canEditable:(lz:editable(controller,'csgUntilTime')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgUntilTime')==null)?canVisible:(lz:visible(controller,'csgUntilTime')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgUntilTime" >
			<dt>有效期至:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csGift.csgUntilTime" id="csgUntilTime"  value="<s:date name="csGift.csgUntilTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择优惠内容的有效期至</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgUntilTime}" id="csgUntilTime" />
			 		<span>
			 	 <s:date name="csGift.csgUntilTime" format="yyyy-MM-dd HH:mm:ss"/>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgUpdateTime')==null)?canEditable:(lz:editable(controller,'csgUpdateTime')))}
		
		${lz:set("canVisible",(false && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgUpdateTime')==null)?canVisible:(lz:visible(controller,'csgUpdateTime')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgUpdateTime" >
			<dt>修改时间:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csGift.csgUpdateTime" id="csgUpdateTime"  value="<s:date name="csGift.csgUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择优惠内容的修改时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgUpdateTime}" id="csgUpdateTime" />
			 		<span>
			 	 <s:date name="csGift.csgUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgAddTime')==null)?canEditable:(lz:editable(controller,'csgAddTime')))}
		
		${lz:set("canVisible",(false && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgAddTime')==null)?canVisible:(lz:visible(controller,'csgAddTime')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgAddTime" >
			<dt>添加时间:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csGift.csgAddTime" id="csgAddTime"  value="<s:date name="csGift.csgAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择优惠内容的添加时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgAddTime}" id="csgAddTime" />
			 		<span>
			 	 <s:date name="csGift.csgAddTime" format="yyyy-MM-dd HH:mm:ss"/>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csgStatus')==null)?canEditable:(lz:editable(controller,'csgStatus')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csgStatus')==null)?canVisible:(lz:visible(controller,'csgStatus')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csgStatus" >
			<dt>状态:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<select class="narrow" id="csgStatus" name="csGift.csgStatus">
			 		<option value="">请选择</option>
					<option value="1" ${csGift.csgStatus==1?"selected":""}>有效</option>
					<option value="0" ${csGift.csgStatus==0?"selected":""}>无效</option>
					<option value="2" ${csGift.csgStatus==2?"selected":""}>过期</option>
					<option value="3" ${csGift.csgStatus==3?"selected":""}>用完</option>
			 	</select>
		 	 
		 	 <b>*</b>
		 	 <em>请选择优惠内容的状态</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csGift.csgStatus}" id="csgStatus" />
			 		<span>
				 ${csGift.csgStatus==1?"有效":""}
				 ${csGift.csgStatus==0?"无效":""}
				 ${csGift.csgStatus==2?"过期":""}
				 ${csGift.csgStatus==3?"用完":""}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
	
		
		
		<div class="line"></div>
		<center>
			<input type="hidden" value="${csGiftToken}" name="csGiftToken" id="csGiftToken" />
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csGift.csgId}" name="csGift.csgId" id="csgId" />
			</s:if>			
			<s:if test="#request.haveEditable==true">
				<s:if test="#request.controller.submits!=null">
					<s:iterator value="#request.controller.submits" id="submit" status="i">
			<button class="button" type="submit" name="submiter" value="${submit.value}">&nbsp;&nbsp;${submit.name}&nbsp;&nbsp;</button>
					</s:iterator>
				</s:if>
				<s:else>
			<button class="button" type="submit" name="submiter" value="完成">&nbsp;&nbsp;完成&nbsp;&nbsp;</button>
			<button class="button" type="submit" name="submiter" value="保存">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
				</s:else>
			<button class="button" type="reset">&nbsp;&nbsp;重置&nbsp;&nbsp;</button>
			</s:if>
			<s:else>
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;确定&nbsp;&nbsp;</button>
			</s:else>
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
		</center>
		
		
	</div>	
</div>
<div class="foot"></div>
	
</form>

<script>
	function cancel(){
		if(${controller.mutual=="dialog"})
			$.closeModalDialog();
		else if(${controller.mutual=="page"})
			window.location="${lz:xeh(entrypoint)}";
		else if(true)
			$.closeModalDialog();
		else
			window.location="${lz:xeh(entrypoint)}";
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