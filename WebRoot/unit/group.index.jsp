<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>分组信息管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		window.basePath="${basePath}";
		window.contextPath="${basePath}${proname}/";
		window.apiUrl="${basePath}${proname}/api.do";
		window.handles={};
	</script>
		
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
</head>  
<body>

<table border="0" cellspacing="0" cellpadding="0"><tr><td>

<div class="title">
	<span class="caption">${empty title?"分组信息管理":title}</span>
</div>

</td></tr><tr><td>
	<s:if test="#request.CTRL.canQuery==true">
  <%@include file="/admin/module/page.jsp"%>
  	</s:if>
  			
	<div class="content">
		<table id="group_table" ref="CsUnitGroup" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 
			 <td ref="csugId" width="60"  title="编号">
			 	<a class="${desc=="csug_id" ? "desc" : ""}${asc=="csug_id" ? "asc" : ""}" href="?${desc=="csug_id" ? "asc=csug_id" : ""}${(asc=="csug_id" || desc!="csug_id" )? "desc=csug_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 </td>
				
			 <td tdid="4" ref="csugName" flagKey  title="组名称">
			 	<a class="${desc=="csug_name" ? "desc" : ""}${asc=="csug_name" ? "asc" : ""}" href="?${desc=="csug_name" ? "asc=csug_name" : ""}${(asc=="csug_name" || desc!="csug_name" )? "desc=csug_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	组名称
			 	</a>
			 </td>
			 <s:if test="#session.unit_login.$csuuUnitInfo.csuiType==2">
			 <td  tdid="5" ref="csugPerson"   title="审核人员">
			 	<a class="${desc=="csug_person" ? "desc" : ""}${asc=="csug_person" ? "asc" : ""}" href="?${desc=="csug_person" ? "asc=csug_person" : ""}${(asc=="csug_person" || desc!="csug_person" )? "desc=csug_person" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	审核人员
			 	</a>
			 </td>
			 </s:if>
			 <td width="80" ref="csugStatus"   title="状态">
			 	<a class="${desc=="csug_status" ? "desc" : ""}${asc=="csug_status" ? "asc" : ""}" href="?${desc=="csug_status" ? "asc=csug_status" : ""}${(asc=="csug_status" || desc!="csug_status" )? "desc=csug_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 </td>
			
			<td width="60" tdid="12" class="options" ref="options">操作</td>
			<td width="105" class="operation" tdid="13" ref="operation">
				<a href="javascript:AddGroup()" style="text-decoration:underline">添加</a>
			</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csugId}" >
				<td class="td">
					${lz:left(item.csugId$,100)}
				</td>
				<td class="td">
					${lz:left(item.csugName$,100)}
				</td>
				<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==2">
				<td class="td">
					${lz:left(item.csugPerson$,100)}
				</td>
				</s:if>
				<td class="td">
					${lz:left(item.csugStatus$,100)}
				</td>
				<td class="td">
					&nbsp;
				</td>
				<td class="operation">
					<a href="javascript:EditGroup('${item.csugId}')">修改</a>
					<a href="javascript:DelGroup('${item.csugId}','${item.csugName}')">删除</a>
					<a href="javascript:DetailsGroup('${item.csugId}')">详情</a>
				</td>
		  </tr>
		 </s:iterator>
		 </tbody>			 
	  </table>
	  
	  ${lz:set("注释","*****************数据列表结束*****************")}
	  
  </div>
  <%@include file="/admin/module/page.jsp"%>
  
 </td></tr></table>

<script>

	$(function(){	
		showTips("${tips.value}");
	});

	function href(url,params){
		top.$.showModalDialog({url:url},params,window);
	} 
	/**
	* 添加分组信息
	**/
	function AddGroup(parent){		
		var url = "${basePath}${namespace}group.edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加分组信息'}":lz:json(ctrl)}};
		href(url,params);
	}
	/**
	* 修改分组信息
	**/
	function EditGroup(id){
		var url = "${basePath}${namespace}group.edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改分组信息'}":lz:json(ctrl)}};
		href(url,params);
	}
	
	/*
	* 自定义操作
	*/
	function HandleSel(operate,id){
		if(!id){
			var checker=$(".check:checked");
			if(checker.length!=1){
				$.tips("请选择一个需要操作的记录.");
				return;
			}
			id = checker.val();
		}
		if(window.getHandle && window.getHandle(operate,id)!=null)//本窗口的操作函数获取句柄
			window.getHandle(operate)(operate,id);
		else if(window.handles && window.handles[operate])//本窗口的操作函数句柄
			window.handles[operate](operate,id);
		else if(typeof(caller)!="undefined" && caller.handles && caller.handles[operate])//调用窗口的操作函数句柄
			caller.handles[operate](operate,id);
		else if(parent && parent.handles && parent.handles[operate])//父窗口的操作函数句柄
			parent.handles[operate](operate,id);
		else{
			var url = "${basePath}${namespace}group.edit.do";
			var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:operate}};
			href(url,params);
		}
	}
	
	/**
	* 删除分组信息
	**/
	function DelGroup(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除分组信息["+flag+"]吗？</span>",
				title:"删除分组信息",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}group.del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);
				}
		 });	
	}
	
	/**
	* 显示分组信息详情
	**/
	function DetailsGroup(id)
	{
		var url = "${basePath}${namespace}group.details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"分组信息详情",editable:false,visible:true}};
		href(url,params);
	}
</script>

</body>
</html>