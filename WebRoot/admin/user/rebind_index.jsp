<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>绑定会员卡</title>
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
</script>

<form class="form editform" id="rebindForm" name="rebindForm" action="rebind_submit.do" method="post">

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
				
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csmMobile" >
			<dt>手机:</dt>
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmMobile" id="csmMobile"  value="${csMember.csmMobile}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入会员帐号的手机</em>
			</dd>
		</dl>
			
		<dl>
			<dt>卡号:</dt>
			${lz:set("haveEditable",true)}
			<dd>
			 	 <input type="text" class="input narrow"  maxlength="32" name="cardno" id="cardno"  value="${cardno}"/>
		 		 <em>请输入卡号</em>
			</dd>
		</dl>
		
		
		<dl>
			<dt>卡识别码:</dt>
			${lz:set("haveEditable",true)}
			<dd>
		 		<input type="password" class="input narrow"  maxlength="32" name="rfid" id="rfid"  value="${rfid}"/>
		 	 	<em></em>
			</dd>
		</dl>
				
		<div class="line"></div>
		<center>
			<input type="hidden" value="${csMemberToken}" name="csMemberToken" id="csMemberToken" />
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csMember.csmId}" name="csMember.csmId" id="csmId" />
			</s:if>			
			<s:if test="#request.haveEditable==true">
						<button class="button" type="button" onclick="$('#rebindForm').submit();" name="submiter" value="完成">&nbsp;&nbsp;完成&nbsp;&nbsp;</button>
			<button class="button" type="button" onclick="$('#rebindForm').submit();" name="submiter" value="保存">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
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
		$("#rebindForm").form({
			"csMember.csmMobile":function(el){
				if(el.value.length>32)
					return "手机最大长度为32个字符";
				if(jQuery.trim(el.value)!=""){
					var exists = $.getObject("member_query.do",{object:true,csmMobile:el.value});
					if(exists && exists.length==0){
						return "该会员不存在";			
					}
				}
				var mobile = $("#csmMobile").val();
				var pattern = /^[1][3,4,5,8]\d{9}$/;	
				if(!pattern.test(mobile))
					return "手机号码格式输入错误，请输入正确的手机号码。";
			}
		});
	});
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
