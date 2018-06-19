<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>${srvUser.suId!=null?"编辑":"添加"}用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
</head>  
<body>

${get:srvlimit("admin/permissions/user.do")}
<s:if test="#request.canView!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>对不起，您没有权限查看当前页面</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>

<script>

$(function(){
	$("#userForm").form({
		"srvUser.suUsername":function(el){
			if(el.value.length<4)
				return "用户姓名最小长度为4个字符";
			if(el.value.length>12)
				return "用户姓名最大长度为12个字符";
			var pattern = /^([a-zA-Z0-9_]){4,12}$/;
			if(!pattern.test(el.value))
				return "请输入正确格式的用户姓名,4-12个字符,只能用英文字母、数字或下划线。";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("user_query.do",{exists:true,suUsername:el.value});
				if(exists && exists.length>0){
					if(${srvUser.suId==null} || exists[0].value!=$("#suId").val())
						return "该用户名已存在";						
				}
			}
		}
	});
});

</script>

<form class="form editform" id="userForm" name="userForm" action="user_${srvUser.suId!=null?"update":"save"}.do" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">
	
		<input type="hidden" value="${srvUser.suId}" name="srvUser.suId" id="suId" />
		<input type="hidden" value="${srvUserExp.sueId}" name="srvUserExp.sueId" id="sueId" />
		<div class="prompt">
			温馨提示：请仔细填写用户相关资料，<span class="extrude">"*" 为必填选项。</span>
		</div>
	  	
		
		
		
			
		
		  <s:if test="srvUser==null || srvUser.suId==null">
		  <dl>
		    <dt>用户名：</dt>
		    <dd>
				<input class="input" type="text"  maxlength="12" name="srvUser.suUsername" id="suUsername"  value="${srvUser.suUsername}"/>
				<b>*</b>	
			</dd>
		  </dl>
		  </s:if>
		  <s:else>
		  <dl>
		    <dt>用户名：</dt>
		    <dd>
				${srvUser.suUsername}	
			</dd>
		  </dl>
		  </s:else>
		  <dl>
		    <dt>真实姓名：</dt>
		    <dd>
				<input class="input" type="text"  maxlength="64" name="srvUser.suRealName" id="suRealName"  value="${srvUser.suRealName}"/>
				<b>*</b>
			</dd>
		  </dl>
		  <dl>
		    <dt>手机号码：</dt>
		    <dd>
				<input class="input" type="text"  maxlength="32" name="srvUser.suMobile" id="suMobile"  value="${srvUser.suMobile}"/>
				<b>*</b>
			</dd>
		  </dl>
		  <dl>
		    <dt>邮箱地址：</dt>
		    <dd>
				<input class="input" type="text"  maxlength="32" name="srvUser.suEmail" id="suEmail"  value="${srvUser.suEmail}"/>
			</dd>
		  </dl>
		  <s:if test="#session.ccclubs_login.suId==0">
		  <dl>
		    <dt>所属区域：</dt>
		    <dd>
				${lz:set("items",lz:query('ccclubs_system','select * from srv_host where sh_status=1'))}
				<s:iterator value="#request.items" id="item" status="i">
				<input type="checkbox" value="${item.sh_id}" name="srvUser.suHost" id="suHost" ${lz:idin(srvUser.suHost,item.sh_id)?"checked":""}/>${item.sh_name}
				</s:iterator>
			</dd>
		  </dl>
		  </s:if>
		  
		  <dl>
		    <dt>用户状态：</dt>
		    <dd>
				&nbsp;&nbsp;&nbsp;&nbsp;
				正常<input type="radio" value="1" name="srvUser.suStatus" id="suStatus" ${srvUser==null || srvUser.suStatus==1?"checked":""}/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				禁用<input type="radio" value="0" name="srvUser.suStatus" id="suStatus" ${srvUser.suStatus==0?"checked":""}/>
				<em>当用户状态为禁用时，将删除级连子用户所有用户或组的权限</em>
			</dd>
		  </dl>
		  
		  <dl>
		    <dt>所属组：</dt>
		    <dd>
				<select name="srvUser.suGroupId" id="suGroupId">
					<option value="">请选择</option>
					${lz:set("items",lz:query$('ccclubs_system','select * from srv_group where sg_parent_id=? order by sg_id desc',lz:or(srvUser.suParentId,ccclubs_login.suId)))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.sg_id}" ${parent==item.sg_id?"selected":""}>${item.sg_name}</option>
					</s:iterator>		
				</select>
				<b>*</b>
			</dd>
		  </dl>
		  
		  <dl  class="sueOutlets " major  ref="sueOutlets" style="width:98%;">
			<dt>外勤所在网点:</dt>
			<dd input="list">
		 	 	<div name="srvUserExp.sueOutlets" id="sueOutlets" class="wide input listinput">
				<input type="hidden" name="srvUserExp.sueOutlets" value="0"/>
				<s:generator val="srvUserExp.sueOutlets" separator=",">
					<s:iterator  id="item">
					<s:if test="item!=0">
						<div>
							${get:CsOutlets(item).csoName}							
						<input type="hidden" name="srvUserExp.sueOutlets" value="${item}"/><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
					</s:iterator>
				</s:generator>
				</div>
				<button type="button" style="float:left;" onclick="$.getInput({type:'combox',title:'选择网点',action:$(this).attr('action'),callback:function(id,text){$('#sueOutlets').append('<div>'+text+'<input type=&quot;hidden&quot; name=&quot;srvUserExp.sueOutlets&quot; value=&quot;'+parseInt(id)+'&quot;/><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}});" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={suHost}&csoHost={suHost}&csoStatus=1" class="button">添加</button>
		 	 <b></b>
		 	 <em>请为外勤人员选择网点，便于发送相关提醒短信</em>
			</dd>
		</dl>
		   
		  
	
	
		<div class="line"></div>
		
		<center>
			<button class="button" type="submit" value="完成">完成</button>		
			<button class="button" type="reset">重置</button>
			<button class="button" onclick="$.closeModalDialog();" type="button">取消</button>
		</center>
	</div>	
</div>
<div class="foot"></div>
</form>

</body>
</html>
