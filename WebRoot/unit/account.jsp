<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${lz:config("project_name")}</title> 
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

<script type="text/javascript">
	window.basePath="${basePath}";
	window.contextPath="${basePath}unit/";
	window.apiUrl="${basePath}unit/api.do";
	window.handles={};
</script>

<body>


<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td>


<div class="title">帐号管理</div>
<div class="plate">
	
	<form ref="CsUnitOrder" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">

		<div class="line"></div>
		<div class="option">			
			<table border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td>
					<input type="hidden" name="all" value="${all}" />
					<input type="hidden" name="fields" value="${fields}" />
					<button class="button" type="submit">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Zoom.png"/>
						查询&nbsp;&nbsp;</button>		
					<button class="button" onclick="clearForm()" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778045.png"/>
						重置&nbsp;&nbsp;</button>
					<button class="button" onclick="saveForm()" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
						保存&nbsp;&nbsp;</button>
					<s:if test="#request.alias=='join' || #request.alias=='select'">
					<button class="button onok" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						确定&nbsp;&nbsp;</button>
					<button class="button oncancel" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						放弃&nbsp;&nbsp;</button>
					</s:if>
				</td>
			  </tr>
			</table>
			<div class="clear"></div>
		</div>
	</form>
</div>

</td></tr><tr><td>

 <br/>

<div class="content">
	<div class="toolbar">
 
 	</div>
	<table id="member_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  	<tr>
		  		<td align="center">所属部门组</td>
		  		<td align="center">姓名</td>
		  		<td align="center">手机号码</td>
		  	 	<td align="center">个人帐号</td>
		  	 	<td align="center">EV卡</td>
		  	 	<td align="center">状态</td>
		  	 	<td align="center">使用状态</td>
		  	 	<td align="center" class="operation" width="150">
		  	 		<a href="javascript:addAccount()" style="text-decoration:underline">
		  	 			<img width="16" align="absmiddle" src="${basePath}admin/images/icons/20088256427828778017.png">
		  	 			添加用车帐号</a>
		  	 	</td>
		  	</tr>
		</thead>
		<tbody>
		<s:iterator value="#request.page.result" id="item" status="i">
			<tr>
				<td align="center">所属部门组</td>
				<td align="center">姓名</td>
		  		<td align="center">手机号码</td>
		  	 	<td align="center">${item.csmsTargeter$}</td>
		  	 	<td align="center">${lz:or(item.$csmsTargeter.csmEvcard$,"[未绑定]")}</td>
		  	 	<td align="center">
					${item.$csmsTargeter.csmStatus==1?"正常":""}
					${item.$csmsTargeter.csmStatus==0?"无效":""}
				</td>
		  	 	<td align="center">
					${lz:set("haveOrder", lz:size(lz:query$("ccclubs_system","select * from cs_order where (cso_status<3 or cso_status=5) and cso_use_member=? limit 1",item.csmsTargeter))>0)}
					${haveOrder?"正在使用":"无订单"}
				</td>
		  	 	<td align="center">
		  	 		<a href="javascript:addAllOrder(${item.csmsTargeter})">
		  	 			<img width="16" align="absmiddle" src="${basePath}admin/images/icons/200882564229234778010.png">
		  	 			内部用车
		  	 		</a>
		  	 		<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==true">
		  	 		<a href="javascript:addProxyOrder(${item.csmsTargeter})">
		  	 			<img width="16" align="absmiddle" src="${basePath}admin/images/icons/20088256422923477809.png">
		  	 			代理下单
		  	 		</a>
		  	 		</s:if>
		  	 		<a href="javascript:delAccount(${item.csmsId})">
		  	 			<img width="16" align="absmiddle" src="${basePath}admin/images/icons/Badge-multiply.png">
		  	 			删除
		  	 		</a>
		  	 	</td>
		  	</tr>
		</s:iterator>
		</tbody>
	</table>	
	
</div>

<%@include file="/admin/module/page.jsp"%>
  
</td></tr></table>

<br/>


<script>
	showTips("${tips.value}");

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
	
	function addAllOrder(id){
		var url = "${basePath}unit/order.edit.do";
		var params = {entrypoint:"${entrypoint}","csUnitOrder.csuoUnitChild":id,controller:{title:"内部用车"}};
		href(url,params);
	}
	
	function addProxyOrder(id){
		var url = "${basePath}unit/order.edit.do";
		var params = {entrypoint:"${entrypoint}","csUnitOrder.csuoUnitChild":id,controller:{title:"代理订单"}};
		href(url,params);
	}
	
	
	function addAccount(){
		<lz:set name="editHtml">
		<div class="form">
			<div class="head"></div>
			<div class="body">
				<div class="prompt">
					温馨提示：请仔细填写会员帐号相关信息，<span class="extrude">"*" 为必填选项。</span>
				</div>
						
				${lz:set("empty",lz:vacant(message))}
				<s:if test="#request.empty!=true">
				<br/>
				<center>
					<font color="red"><h4>${message}</h4></font>
				</center>
				<br/>
				</s:if>
				
				<div class="content">
					<dl class="minor" >
						<dt>手机号码:</dt>
						<dd input="query">
						 	<input class="input narrow" type="text" id="mobile" name="mobile" value="" />
					 	 	<b>*</b>
					 	 	<em>请输入在车纷享平台注册的会员手机号码</em>
						</dd>
					</dl>
					<dl class="minor" >
						<dt>真实姓名:</dt>
						<dd input="query">
						 	<input class="input narrow" type="text" id="name" name="name" value="" />
					 	 	<b>*</b>
					 	 	<em>请输入在车纷享平台注册的会员真实姓名</em>
						</dd>
					</dl>
				</div>	
			</div>
			<div class="foot"></div>
		</div>
		</lz:set>
		var dialog=top.$("${lz:js(editHtml)}").dialog({
			title:"添加子级帐号",
			static:true,
			modal:true,
			onOk:function(){
				var mobile = dialog.find("#mobile").val();
				if($.trim(mobile).length<11){
					top.$.toast("请输入11位的手机号码");
					return false;
				}
				var name = dialog.find("#name").val();
				if($.trim(name)==""){
					top.$.toast("请输入真实姓名");
					return false;
				}
				var result = $.api("com.ccclubs.action.unit.AccountAction","add")(mobile,name);
				if(result.message)
					top.$.toast(result.message,function(){
						if(result.success)
							window.location.href=window.location.href;
					});
				if(result.success)
					return true;
				return fasle;
			}
		});
	}
	
	
	function delAccount(id){
		var dialog=top.$.dialog({
			title:"删除确认",
			html:"确认要删除该会员帐号吗？（删除之后不能为该会员用车付款）",
			static:true,
			modal:true,
			onOk:function(){
				var result = $.api("com.ccclubs.action.unit.AccountAction","del")(id);
				if(result.message)
					top.$.toast(result.message,function(){
						if(result.success)
							window.location.href=window.location.href;
					});
				if(result.success)
					return true;
				return fasle;
			}
		});
	}
	
</script>


</body>
</html>