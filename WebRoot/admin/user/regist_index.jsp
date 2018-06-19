<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>会员注册</title>
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
<body>

<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";
</script>

<form class="form editform" id="registForm" name="registForm" action="regist_submit.do" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">	
	
		<div class="prompt">
			温馨提示：请仔细填写会员帐号相关信息，<span class="extrude">"*" 为必填选项。</span>
		</div>
		
		${lz:set("empty",lz:empty(message))}
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
		<input type="hidden" value="${lz:html(ctrl)}" id="ctrl" name="ctrl" />
		
		
		
		<s:if test="#session.ccclubs_login.$suHost==null">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csmHost" >
			<dt>城市:</dt>
			<dd input="combox">
			 			<select class="combox narrow" action="${basePath}admin/permissions/host_query.do?size=-1" id="csmHost" name="csMember.csmHost">
			 				<option selected value="${csMember.csmHost}">
			 					${get:SrvHost(csMember.csmHost).shName}
			 				</option>
			 			</select>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmHost').val())==''){return;};window.href('${basePath}admin/permissions/host_details.do?id='+$('#csmHost').val(),{ctrl:{editable:false}})"></a>
		 	 
		 	 <b>*</b>
		 	 <em></em>
			</dd>
		</dl>
		</s:if>
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csmMobile" >
			<dt>手机:</dt>
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmMobile" id="csmMobile"  value="${csMember.csmMobile}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入会员帐号的手机</em>
			</dd>
		</dl>
				
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csmPassword" >
			<dt>帐号密码:</dt>
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="password" class="input narrow"  maxlength="32" name="csMember.csmPassword" id="csmPassword"  value="${csMember.csmPassword}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入会员帐号的帐号密码</em>
			</dd>
		</dl>
		
				
		<dl>
			<dt>卡号:</dt>
			${lz:set("haveEditable",true)} 
			<dd>
			 	 <input type="text" class="input narrow" maxlength="32" name="cardno" id="cardno"  value="${cardno}"/>
		 		 <b>*</b>
		 		 <em>请输入卡号</em>
			</dd>
		</dl>
		
		
		<dl>
			<dt>卡识别码:</dt>
			${lz:set("haveEditable",true)}
			<dd>
		 		<input type="password" class="input narrow"  maxlength="32" name="rfid" id="rfid"  value="${rfid}"/>
		 	 	<b>*</b>
		 	 	<em></em>
			</dd>
		</dl>
		
		
				
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csmName" >
			<dt>真实姓名:</dt>
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmName" id="csmName"  value="${csMember.csmName}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入会员帐号的真实姓名</em>
			</dd>
		</dl>
				
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csmiDriverNum" style="width:98%;">
			<dt>驾证号码:</dt>
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input wide"  maxlength="32" name="csMemberInfo.csmiDriverNum" id="csmiDriverNum"  value="${csMemberInfo.csmiDriverNum}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入会员信息的驾证号码</em>
			</dd>
		</dl>
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csmiDriverImage" style="width:98%;">
			<dt>驾证图片:</dt>
			${lz:set("haveEditable",true)}
			<dd>
			    <input type="text" class="input" maxlength="128" size="32" name="csMemberInfo.csmiDriverImage" id="csmiDriverImage"  value="${csMemberInfo.csmiDriverImage}"/>
				<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csmiDriverImage').val(url)}})" class="button">设置图片</button>
				<button type="button" onclick="$('#csmiDriverImage').val('')" class="button">删除图片</button>
				<button type="button" onclick="$.thumb({url:$('#csmiDriverImage').val()})" class="button">查看图片</button>
		 	 
		 	 <b>*</b>
		 	 <em>请上传会员信息的驾证图片</em>
			</dd>
		</dl>
		
		
		<!-- 
		<dl style="width:98%;">
			<dt>赠送现金券:</dt>
			${lz:set("haveEditable",true)}
			<dd>
			 	<input name="coupon" id="coupon"  value="true" type="checkbox"/>
				注册即送100现金券
			</dd>
		</dl>
		 -->
		
		<div class="line"></div>
		<center>
			<input type="hidden" value="${csMemberToken}" name="csMemberToken" id="csMemberToken" />
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csMember.csmId}" name="csMember.csmId" id="csmId" />
			</s:if>			
			<s:if test="#request.haveEditable==true">
						<button class="button" type="button" onclick="$('#registForm').submit();" name="submiter" value="完成">&nbsp;&nbsp;完成&nbsp;&nbsp;</button>
			<button class="button" type="button" onclick="$('#registForm').submit();" name="submiter" value="保存">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
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
	
	$(function(){
		$("#csmiCertifyNum").blur(function(){
			$("#csmiDriverNum").val($(this).val());
		});
		
		
		
		$("#csmMobile").change(function(){
			var result = $.getObject("regist_mobile.do",{mobile:$(this).val()});
			if(result.message)
				alert(result.message);
			if(!result.success){
				$(this).val("");
			}
		});	
		$("#rfid,#cardno").change(function(){
			var result = $.getObject("regist_evcard.do",{rfid:$("#rfid").val(),number:$("#cardno").val()});
			if(result.message)
				alert(result.message);
			if(!result.success){
				$("#rfid").val("");
				$("#cardno").val("");
			}else{
				$("#rfid").val(result.rfid);
				$("#cardno").val(result.number);
			}
		});
		
		
		$("#registForm").form({
			"":function(){}
			,"csMember.csmUsername":function(el){
				if(jQuery.trim(el.value)=="")
					return "用户名不能为空";
				if(el.value.length>32)
					return "用户名最大长度为32个字符";
				if(jQuery.trim(el.value)!=""){
					var exists = $.getObject("member_query.do",{object:true,csmUsername:el.value});
					if(exists && exists.length>0){
						if(${csMember.csmId==null} || exists[0].object.csmId!=$("#csmId").val())
							return "用户名已存在";						
					}
				}
				var username = $("#csmUsername").val();
				var pattern = /^[a-zA-Z0-9\u4e00-\u9fa5_]{4,16}$/;
				var enPattern=/^[a-zA-Z0-9_]{6,32}$/;
				var cnPattern=/[\u4e00-\u9fa5]{2,12}/;
				if(!enPattern.test(username) && !(cnPattern.test(username) && pattern.test(username)))
					return "会员帐号格式错误。";
			}
			,"csMember.csmPassword":function(el){
				if(jQuery.trim(el.value)=="")
					return "帐号密码不能为空";
				if(el.value.length>32)
					return "帐号密码最大长度为32个字符";
			}
			,"cardno":function(el){
				if(jQuery.trim(el.value)!=""){
					var evcards = $.getObject("${basePath}admin/object/evcard_query.do",{object:true,csecNumber:el.value});
					if(evcards && evcards.length>0){						
						var exists = $.getObject("member_query.do",{object:true,csmEvcard:evcards[0].object.csecId});
						if(exists && exists.length>0){
							return "EV卡被其它会员注册";						
						}
					}
				}
			}
			,"rfid":function(el){
				if(jQuery.trim(el.value)!=""){
					var evcards = $.getObject("${basePath}admin/object/evcard_query.do",{object:true,csecRfid:el.value});
					if(evcards && evcards.length>0){
						var exists = $.getObject("member_query.do",{object:true,csmEvcard:evcards[0].object.csecId});
						if(exists && exists.length>0){
							return "RFID关联卡被其它会员注册";						
						}
					}
				}
			}
			,"csMember.csmEmail":function(el){
				if(el.value.length>128)
					return "邮箱最大长度为128个字符";
				if(jQuery.trim(el.value)!=""){
					var exists = $.getObject("member_query.do",{object:true,csmEmail:el.value});
					if(exists && exists.length>0){
						if(${csMember.csmId==null} || exists[0].object.csmId!=$("#csmId").val())
							return "邮箱已存在";						
					}
				}
				var email = $("#csmEmail").val();
				var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;	
				if(!pattern.test(email))
					return "邮箱格式输入错误，请输入正确的邮箱。";
			}
			,"csMember.csmMobile":function(el){
				if(el.value.length>32)
					return "手机最大长度为32个字符";
				if(jQuery.trim(el.value)!=""){
					var exists = $.getObject("member_query.do",{object:true,csmMobile:el.value});
					if(exists && exists.length>0){
						if(${csMember.csmId==null} || exists[0].object.csmId!=$("#csmId").val())
							return "手机已存在";						
					}
				}
				var mobile = $("#csmMobile").val();
				if(mobile.indexOf("9.")>-1)
					return null;
				var pattern = /^[1][3,4,5,8,7,9]\d{9}$/;	
				if(!pattern.test(mobile))
					return "手机号码格式输入错误，请输入正确的手机号码。";
			}
			,"csMember.csmName":function(el){
				if(el.value.length>32)
					return "真实姓名最大长度为32个字符";
				var realname = $("#csmName").val();
				var pattern = /[\u4e00-\u9fa5]{2,12}/;
				if(!pattern.test(realname))
					return "真实姓名格式输入错误，必须为2到12位之间的中文字符";
			}		
			,"csMemberInfo.csmiCertifyType":function(el){
			}			 		
			,"csMemberInfo.csmiCertifyNum":function(el){
				if(el.value.length>32)
					return "证件号码最大长度为32个字符";
			}
			,"csMemberInfo.csmiCertifyImage":function(el){
			}
			,"csMemberInfo.csmiDriverNum":function(el){
				if(el.value.length>32)
					return "驾证号码最大长度为32个字符";
			}
		});	
		
	})	
	
	</script>
	
	
<script>
	function cancel(){
		$.closeModalDialog();
	}
	
	function href(url,params){
		top.$.showModalDialog({url:url},params,window);
	}
</script>
	
</body>
</html>
