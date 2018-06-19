<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${lz:config("project_name")}</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<meta name="basePath" content="${basePath}">
	<script type="text/javascript" src="${basePath}admin/messages/box/box.js"></script>
</head>
<body>
<table id='boxDialog' class='shadow dialog mini' border='0' cellspacing='0' cellpadding='0'>
	<tr>  <td class='tl'></td>  <td class='t'></td>  <td class='tr'></td></tr>
	<tr>  <td class='l'></td>  <td class='c'>
		<table id="c" class='state-content' border='0' cellspacing='0' cellpadding='0'>
			<tr><td class='body'>
					<div class="box-header state-panel">
						<div class="mini" style="height:5px"></div>
						<div class="tit">
							<span class="icon maxi" id="maxi">↓</span>
							<span class="icon mini" id="mini">↑</span>
							<a alt="系统消息" title="系统消息" ref="#notices" href="javascript:void(0);">
								<img align="absmiddle" src="${basePath}admin/images/icons/notice.png"/>
								<span id="noticeCount"></span>
							</a>
							<a alt="待办事项" title="待办事项" ref="#todos" href="javascript:void(0);">
								<img align="absmiddle" src="${basePath}admin/images/icons/todo.png"/>
								<span id="todoCount"></span>
							</a>
							<a alt="提示信息" title="提示信息" ref="#messages" href="javascript:void(0);">
								<img align="absmiddle" src="${basePath}admin/images/icons/tip.png"/>
								<span id="messageCount"></span>
							</a>
							<a href="javascript:void(heed())" style="color:#ffffff;margin:0px 5px;">+关注</a>
							<div class="clear"></div>
						</div>
						<div class="info mini">
							<span class="msg">&nbsp;&nbsp;&nbsp;&nbsp;欢迎您：${ccclubs_login.suRealName} ${ccclubs_login.suUsername}</span>
							<input id="suId" value="${ccclubs_login.suId}" type="hidden" />
							&nbsp;&nbsp;&nbsp;
							<a onclick="$.open('${basePath}admin/permissions/user_info.do');" href="javascript:void(0);">[修改资料]</a>
							<div class="clear"></div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="box-search state-content">
						<!-- 搜索框 -->
						<table class="state-content" width="100%" border="0" cellspacing="0" cellpadding="5">
						  <tr style="line-height:normal;">
						  	<td width="90">
						  		<%request.setAttribute("user_host",com.ccclubs.config.SYSTEM.getDefaultHost());%>
						  		${lz:set("hosts",get:hosts())}
								<select style="width:90px;" class="combox" name="likeHost" id="likeHost"><!--如果是非域用户，则在窗口最上面漂浮一个切换地域的选择框-->
									<s:iterator value="#request.hosts" id="item" status="i">
									<option value="${item.shId}" ${user_host==item.shId?"selected":""}>-- ${item.shName} --</option>
									</s:iterator>
								</select>
							</td>
						    <td>
						    	<input type="text" name="search" id="search" value="搜索：会员、订单、车辆"/>
							</td>
						  </tr>
						</table>
						<table class="state-content" width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr class="results">
						  	<td class="state-content">
						  		<div id="results" class="state-content">
							  		<a class="close" href="javascript:void(0);">X</a>
							    	<div class="list state-content"></div>
						    	</div>
							</td>
						  </tr>
						</table>
						<!-- 搜索框 -->
					</div>
					<div class="box-tabs tabs mini" focus="state-focus">
						<button class="button state-focus" ref="#notices" type="button">系统通知</button>
						<button class="button" ref="#todos" type="button">待办事项</button>
						<button class="button" ref="#messages" type="button">提示信息</button>
						<div class="clear"></div>
					</div>	
					<div class="box-cnts mini">
						<div id="notices" class="state-content box-content">
							<div class="opts state-header">
								<a href="javascript:void(reloadNotice())">
									<img align="absmiddle" src="${basePath}admin/images/icons/2008825642250778028.png"/>
									刷新</a>
								<a href="javascript:void(heed())">
									<img align="absmiddle" src="${basePath}admin/images/icons/Badge-plus.png"/>
									关注</a>
							</div>
							<div class="list"></div>
						</div>
						<div id="todos" class="state-content box-content hidden">
							<div class="opts state-header">
								<a href="javascript:void(reloadTodo())">
									<img align="absmiddle" src="${basePath}admin/images/icons/2008825642250778028.png"/>
									刷新</a>
								<a href="#" onclick="$.open('${basePath}admin/systematic/todo_edit.do?edittype=save')">
									<img align="absmiddle" src="${basePath}admin/images/icons/Badge-plus.png"/>
									添加</a>
							</div>
							<div class="list"></div>
						</div>
						<div id="messages" class="state-content box-content hidden">
							<div class="opts state-header">
								<a href="javascript:void($('#messages .list').empty())">
									<img align="absmiddle" src="${basePath}admin/images/icons/Recicle bin.png"/>
									清空</a>
								<a href="javascript:void(heed())">
									<img align="absmiddle" src="${basePath}admin/images/icons/Badge-plus.png"/>
									关注</a>
							</div>
							<div class="list"></div>
						</div>
					</div>
			</td></tr>
		</table>
	</td>  <td class='r'></td></tr><tr>  <td class='bl'></td>  <td class='b'></td>  <td class='br'></td></tr>
</table>


</body>
</html>