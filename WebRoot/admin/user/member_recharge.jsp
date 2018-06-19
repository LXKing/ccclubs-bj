<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>会员${way}</title>
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
	
	<style>
		.form .head,.form .body,.form .foot{width:500px;}
		.form dt{min-height:28px;height:28px;}
		.form .no-em em{display:none;}
	</style>
		
</head>  
<body>



<script>

$(function(){	
<s:if test="#session.tips.notNull==true">
	$.tips("${tips.value}");//错误提示
</s:if>
});

$(function(){
	$("#memberForm").form({
		"":function(){}	
	});		
});
</script>

<form class="form editform" id="memberForm" name="memberForm" action="member_recharge.do" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">
		
		<input type="hidden" value="${editorpoint}" id="editorpoint" name="editorpoint" />	
		<input type="hidden" value="${entrypoint}" id="entrypoint" name="entrypoint" />
		<input type="hidden" value="${id}" id="id" name="id" />
		<input type="hidden" value="${way}" id="way" name="way" />
		
		
		
		<table class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
			<thead class="state-header">
				<tr>
					<td width="25%">会员帐号</td>
					<td width="75%">会员积分</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.members" id="item" status="i">
				<tr>
					<td width="25%">
						<input type="hidden" value="${item.csmId}" name="ids">
				 		${item.csmName}[${item.csmUsername}](${item.csmMobile})
					</td>
					<td width="25%">
						${item.csmIntegral}
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		
		<div class="line" style="margin:0px 0px;margin-top:20px;"></div>		
			
		<div class="line" style="margin:0px 0px;"></div>		
		<dl class="no-em" style="width:98%;">
			<dt>${way}数量:</dt>			
			<dd>
			 	<input onchange="onkeyup()" onkeyup="var reg=/^(([1-9]\d{0,9})|0)(\.)?(\d{1,2})?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="amount" id="amount"  value=""/>
				<b>*</b>
			</dd>			
		</dl>	
		
		<div class="line" style="margin:0px 0px;"></div>		
		<dl class="no-em" style="width:98%;">
			<dt>${way}备注:</dt>			
			<dd>
			 	<input type="text" class="input narrow"  id="remark" name="remark" rows="5"/>
			 	<b>*</b>
			</dd>			
		</dl>			
		
		
		<div class="line" style="margin:0px 0px;"></div>
		<center style="margin-top:20px;">
			<button class="button" type="submit">&nbsp;&nbsp;提交&nbsp;&nbsp;</button>
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
		top.$.showModalDialog({url:url},params,window);
	}
</script>




</body>
</html>
