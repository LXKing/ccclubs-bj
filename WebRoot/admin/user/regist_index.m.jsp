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
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_mi")}?v=3"></script>
	<link href="${basePath}admin/css/lazy3q.mi.css?v=3" rel="stylesheet" type="text/css" /> 	
	
	<style>
	form .input,form button,form label,form select{display:block;margin:10px auto;width:100%;}	
	</style>
	
</head>
<body>
	
	
	<div id="page" class="page">
	
		<div class="header">
			<a class="right" id="fastBtn" style="margin-right:10px;" onclick="fastRegist();"  href="javascript:void(0);">快速注册</a>
			<a class="right" id="moreBtn" style="margin-right:10px;display:none;" onclick="moreRegist();"  href="javascript:void(0);">详细注册</a>
			<a class="left" style="margin-left:10px;"  href="${basePath}admin/index.do">返&nbsp;&nbsp;回</a>
			<span>会员帐号注册</span>
		</div>		
		<div data-role="content"  data-dom-cache="false">
			
			<form data-ajax="false" id="registForm" name="registForm" enctype="multipart/form-data" action="regist_submit.do" method="post">
			
			<input type="hidden" value="${editorpoint}" id="editorpoint" name="editorpoint" />	
			<input type="hidden" value="${entrypoint}" id="entrypoint" name="entrypoint" />	
		
			<table width="90%" align="center" border="0" cellspacing="0" cellpadding="5">
			  <tr>
			    <td colspan="2">
			    	<label>请输入手机号码</label>
					<input class="input" name="csMember.csmMobile" id="csmMobile"  value="${csMember.csmMobile}" type="text" placeholder="请输入手机号码" />
				</td>				
			  </tr>
			  <tr>
			    <td colspan="2">
			    	<label>请输入会员密码</label>
					<input class="input" name="csMember.csmPassword" id="csmPassword"  value="${csMember.csmPassword}" type="password" placeholder="请输入会员密码" />
				</td>
			  </tr>			  
			  <tr>
			    <td>
			    	<label>请输入真实姓名</label>
					<input class="input" name="csMember.csmName" id="csmName"  value="${csMember.csmName}" type="text" placeholder="请输入真实姓名" />
				</td>
				<td>
					<label>&nbsp;</label>
					<select class="select" id="csmiSex" name="csMemberInfo.csmiSex" data-role="slider" >
					    <option value="1" ${csMemberInfo.csmiSex==1?"selected":""}>男</option>
					    <option value="0" ${csMemberInfo.csmiSex==0?"selected":""}>女</option>
					</select>
				</td>
			  </tr>
			  <tr>
			    <td colspan="2">
			    	<label>请输入驾证号码</label>
					<input class="input" notnull="false" name="csMemberInfo.csmiDriverNum" id="csmiDriverNum"  value="${csMemberInfo.csmiDriverNum}" type="text" placeholder="请输入驾证号码" />
				</td>
			  </tr>
			  <tr>
			    <td>
			    	<label>请上传驾证图片</label>
			    	<input notnull="false" type="text" style="display:none;" name="csMemberInfo.csmiDriverImage" id="csmiDriverImage"  value="${csMemberInfo.csmiDriverImage}"/>
			    	<input class="input" notnull="false" type="file" id="driver" name="driver" placeholder="请上传驾证图片" /></td>
				<td>
					<label>&nbsp;</label>
					<button onclick="getImageFromDevice('setDriver');" type='button' class="button" >设备获取</button>
				</td>
			  </tr>
			  <tr>
			    <td colspan="2">
			    	<label>请读取识别码</label>
			    	<input class="input" placeholder="请读取识别码" type="password" id="rfid" name="rfid" value="${rfid}" />
			    </td>
			  </tr>
			  <tr>
			    <td colspan="2">
			    	<label>请输入卡号</label>
			    	<input class="input" placeholder="请输入卡号" type="text" id="cardno" name="cardno" value="${cardno}" />
			    </td>
			  </tr>
			  <tr>
			    <td width="50%">
			    	<label>请选择区域</label>
					<select class="select" id="area" name="area" data-role="slider" placeholder="请选择区域">
						 <option value="">全部</option>
					     <s:iterator value="#request.areas" id="item" status="i">
			             <option ${id==item.csaId?"selected":""} value="${item.csaId}">${item.csaName}</option>
			             </s:iterator>
					</select>
				</td>
				<td width="50%">
					<label>请选择网点</label>
					<select class="select" id="csmOutlets" name="csMember.csmOutlets" data-role="slider"  placeholder="请选择网点">
						<option value="">请选择</option>
						<s:iterator value="#request.outlets" id="item" status="i">
					    <option area="${item.csoArea}" ${csMember.csmOutlets==item.csoId?"selected":""} value="${item.csoId}">${item.csoName}</option>
					    </s:iterator>
					</select>
				</td>
								
			  </tr>
			  <tr>
			    <td colspan="2">
			    	<label>请选择认证状态</label>
					<select class="select" placeholder="请选择认证状态" notnull="false" id="csmVDrive" name="csMember.csmVDrive" data-native-menu="false"  data-corners="false">
				 		<option value="1">认证通过</option>
				 		<option value="0">未认证</option>
				 	</select>
				</td>
			  </tr>	
			  <tr>
			    <td colspan="2">
			    	<label>请选择推荐人</label>
					<select class="select" placeholder="请选择推荐人" notnull="false" id="csmSaler" name="csMember.csmSaler" data-native-menu="false"  data-corners="false">
				 		<option value="">请选择推荐人</option>
						${lz:set("ids",lz:split(sids,","))}
						<s:iterator value="#request.ids" id="id" status="i">	
							${lz:set("id",lz:trim(id))}						
							<option value="${id}" ${(ccclubs_login_id==id || csMember.csmSaler==id)?"selected":""}>${get:SrvUser(id).suRealName}</option>
						</s:iterator>						
				 	</select>
				</td>
				<td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td colspan="2">
			    	<label>备注信息</label>
					<input class="input" name="csMember.csmRemark" notnull="false" id="csmRemark"  value="${csMember.csmRemark}" type="text" placeholder="备注信息" />
				</td>
			  </tr>
			  <!-- 
			  <tr>
			    <td colspan="2">
			    	<label>赠送现金券</label>
					<input name="coupon" id="coupon"  value="true" type="checkbox"/>
					注册即送100现金券
				</td>
			  </tr>
			   -->
			  <tr>
			    <td colspan="2"><button type="button" onclick="$('#registForm').submit();" data-theme="e" data-corners="false">提交注册</button></td>
			  </tr>
			</table>
			</form>
			
		</div>
	</div>
	
	
	<script>
	$(function(){
		<s:if test="#session.tips.notNull==true">
		//错误提示
		alert("${tips.value}");
		</s:if>
	});
	
	
	function fastRegist(){
		$("[notnull]").each(function(){
			$(this).parents("tr").hide();
		});
		$("#csmVDrive").val("0");
		$("#csmPassword").val("666666").parents("tr").hide();
		$("#fastBtn").hide();
		$("#moreBtn").show();
	}
	
	function moreRegist(){
		$("[notnull]").each(function(){
			$(this).parents("tr").show();
		});
		$("#csmPassword").val("").parents("tr").show();
		$("#moreBtn").hide();
		$("#fastBtn").show();
	}
	
	$(function(){
		window.outlets = $("#csmOutlets option[area]");
	});
	$("#area").change(function(){
		window.outlets.remove();
		if($(this).val()==""){
			window.outlets.appendTo("#csmOutlets");
		}else{
			window.outlets.filter("[area='"+$(this).val()+"']").appendTo("#csmOutlets");
		}
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
	
	$(function(){
		$("#csmiCertifyNum").blur(function(){
			$("#csmiDriverNum").val($(this).val());
		});
	})
	
	//从设备获取图片
	function getImageFromDevice(callbackName){
		if(!window.android){
			alert("非安卓系统客户端，不能以此方式获取图片");
			return false;
		}
		android.upload("${lz:config("upload.url")}?app=memberinfo_edit",0,0,callbackName);
	}
	function setCarder(sUrl){
		$("#carder").hide();
		$("#csmiCertifyImage").val(sUrl).show();
	}
	function setDriver(sUrl){
		$("#driver").hide();
		$("#csmiDriverImage").val(sUrl).show();
	}
	
	
	$("#registForm").submit(function(){
		//非空验证
		{
			var bSuccess = true;
			$("#registForm input,#registForm select").each(function(){
				if($.trim($(this).val())=="" && $(this).attr("notnull")!="false" && $(this).attr("type")!="hidden"){
					alert($(this).attr("placeholder"));
					return bSuccess = false;
				}
			});
			if(!bSuccess)
				return false;
		}
			
		//验证会员名
		/*{
			var username = $("#csmUsername").val();
			var pattern = /^[a-zA-Z0-9\u4e00-\u9fa5_]{4,16}$/;
			var enPattern=/^[a-zA-Z0-9_]{6,32}$/;
			var cnPattern=/[\u4e00-\u9fa5]{2,12}/;
			if(!enPattern.test(username) && !(cnPattern.test(username) && pattern.test(username))){
				alert("会员帐号格式错误。");
				return false;
			}
		}*/
		
		//验证会员名
		/*{
			var username = $("#csmUsername").val();
			var pattern = /^[a-zA-Z0-9\u4e00-\u9fa5_]{4,8}$/;
			var enPattern=/^[a-zA-Z0-9_]{6,16}$/;
			var cnPattern=/[\u4e00-\u9fa5]{4,8}/;
			if(!enPattern.test(username) && !(cnPattern.test(username) && pattern.test(username))){
				alert("会员帐号格式错误,请输入6个字母、下划线、数字或者包含两个汉字的会员名。");
				return false;
			}
		}*/
		
		
		//验证邮箱
		/*{
			var email = $("#csmEmail").val();
			var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;	
			if(!pattern.test(email)){
				alert("邮箱格式输入错误，请输入正确的邮箱。");
				return false;
			}
		}*/
		
		//手机号码
		{
			var mobile = $("#csmMobile").val();
			var pattern = /^[1][3,4,5,7,8,9]\d{9}$/;	
			if(!pattern.test(mobile)){
				alert("手机号码格式输入错误，请输入正确的手机号码。");
				return false;
			}
		}
		
		//真实姓名
		{
			var realname = $("#csmName").val();
			var pattern = /[\u4e00-\u9fa5]{2,12}/;
			if(!pattern.test(realname)){
				alert("真实姓名格式输入错误，必须为2到12位之间的中文字符");
				return false;
			}
		}
		
		
		/*if($("#carder").val()=="" && $("#csmiCertifyImage").val()==""){
			alert("请上传身份证照片");
			return false;
		}*/
		
		/*
		if($("#driver").val()=="" && $("#csmiDriverImage").val()==""){
			alert("请上传驾证照片");
			return false;
		}*/
				
		return true;
	});	
	</script>
</body>
</html>