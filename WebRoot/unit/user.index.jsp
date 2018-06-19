<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>企业管理员管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
		
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
</head>  
<body>

${lz:set("canAdd",true)}${lz:set("canEdit",true)}${lz:set("canDel",true)}${lz:set("canView",true)}

<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td>


<div class="title">企业管理员管理</div>

	
</td></tr><tr><td>
  <%@include file="/admin/module/page.jsp"%>
  	
  			
	<div class="content">
	    <div class="toolbar">
			${lz:set("permission",lz:permission(controller,'canDel'))}
			<s:if test="#request.canDel==true && #request.permission==true">
			<button type="button" class="del" onclick="DelSel();">
				删除
			</button>
			</s:if>
			${lz:set("permission",lz:permission(controller,'canView'))}
			<s:if test="#request.canView==true && #request.permission==true">
			<!--<button type="button" class="detail" onclick="DetailsSel();">
				详情
			</button>-->
			</s:if>
			${lz:set("permission",lz:permission(controller,'canEdit'))}
			<s:if test="#request.canEdit==true && #request.permission==true">
						
			<button type="button" class="edit" onclick="UpdateSel('limit')">权限
			</button>
						
						
			<button type="button" class="edit" onclick="UpdateSel('status')">状态
			</button>
						
			<!--<button type="button" class="edit" onclick="EditSel();">
				修改
			</button>-->
			</s:if>
		    ${lz:set("permission",lz:permission(controller,'canAdd'))}
			<s:if test="#request.canAdd==true && #request.permission==true">
			<button type="button" class="add" onclick="AddUser();">
				添加
			</button>							
			</s:if>
			<button type="button" class="all" onclick="$('.check').attr('checked',true)">
				全选
			</button>
			<button type="button" class="invert" onclick="$('.check').click();">
				反选
			</button>
			<a href="?${all==true?"":"all=true&"}${lz:querys("UTF-8","all")}"  class="more">
				${all==true?"主要内容":"全部内容"}
			</a>		
		</div>
		
		<table id="user_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td width="40" align="center">
			 	选择
			 </td>
			 
			 ${lz:set("listable",lz:listable(controller,'csuuId'))}
			 <s:if test="#request.listable!=false">
			 <td width=72 ${all?"width":"refer"}="120" title="编号">
			 	<a class="${desc=="csuu_id" ? "desc" : ""}${asc=="csuu_id" ? "asc" : ""}" href="?${desc=="csuu_id" ? "asc=csuu_id" : ""}${(asc=="csuu_id" || desc!="csuu_id" )? "desc=csuu_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csuuUsername'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="200" title="登录帐号">
			 	<a class="${desc=="csuu_username" ? "desc" : ""}${asc=="csuu_username" ? "asc" : ""}" href="?${desc=="csuu_username" ? "asc=csuu_username" : ""}${(asc=="csuu_username" || desc!="csuu_username" )? "desc=csuu_username" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	登录帐号
			 	</a>
			 </td>
			 </s:if>
			 			 
			 
			 ${lz:set("listable",lz:listable(controller,'csuuIsRoot'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="系统管理员">
			 	<a class="${desc=="csuu_is_root" ? "desc" : ""}${asc=="csuu_is_root" ? "asc" : ""}" href="?${desc=="csuu_is_root" ? "asc=csuu_is_root" : ""}${(asc=="csuu_is_root" || desc!="csuu_is_root" )? "desc=csuu_is_root" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	系统管理员
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csuuLimit'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="200" title="权限">
			 	<a class="${desc=="csuu_limit" ? "desc" : ""}${asc=="csuu_limit" ? "asc" : ""}" href="?${desc=="csuu_limit" ? "asc=csuu_limit" : ""}${(asc=="csuu_limit" || desc!="csuu_limit" )? "desc=csuu_limit" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	权限
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csuuRealname'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="200" title="真实姓名">
			 	<a class="${desc=="csuu_realname" ? "desc" : ""}${asc=="csuu_realname" ? "asc" : ""}" href="?${desc=="csuu_realname" ? "asc=csuu_realname" : ""}${(asc=="csuu_realname" || desc!="csuu_realname" )? "desc=csuu_realname" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	真实姓名
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csuuMobile'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="200" title="联系电话">
			 	<a class="${desc=="csuu_mobile" ? "desc" : ""}${asc=="csuu_mobile" ? "asc" : ""}" href="?${desc=="csuu_mobile" ? "asc=csuu_mobile" : ""}${(asc=="csuu_mobile" || desc!="csuu_mobile" )? "desc=csuu_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系电话
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csuuAddTime'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="140" title="添加时间">
			 	<a class="${desc=="csuu_add_time" ? "desc" : ""}${asc=="csuu_add_time" ? "asc" : ""}" href="?${desc=="csuu_add_time" ? "asc=csuu_add_time" : ""}${(asc=="csuu_add_time" || desc!="csuu_add_time" )? "desc=csuu_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 </td>
			 </s:if>
			 
			 <s:if test="#request.all==true">
			 ${lz:set("listable",lz:listable(controller,'csuuLastIp'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="200" title="最后登录IP">
			 	<a class="${desc=="csuu_last_ip" ? "desc" : ""}${asc=="csuu_last_ip" ? "asc" : ""}" href="?${desc=="csuu_last_ip" ? "asc=csuu_last_ip" : ""}${(asc=="csuu_last_ip" || desc!="csuu_last_ip" )? "desc=csuu_last_ip" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	最后登录IP
			 	</a>
			 </td>
			 </s:if>
			 </s:if>
			 <s:if test="#request.all==true">
			 ${lz:set("listable",lz:listable(controller,'csuuLastTime'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="200" title="最后登录时间">
			 	<a class="${desc=="csuu_last_time" ? "desc" : ""}${asc=="csuu_last_time" ? "asc" : ""}" href="?${desc=="csuu_last_time" ? "asc=csuu_last_time" : ""}${(asc=="csuu_last_time" || desc!="csuu_last_time" )? "desc=csuu_last_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	最后登录时间
			 	</a>
			 </td>
			 </s:if>
			 </s:if>
			 
			 ${lz:set("listable",lz:listable(controller,'csuuStatus'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="状态">
			 	<a class="${desc=="csuu_status" ? "desc" : ""}${asc=="csuu_status" ? "asc" : ""}" href="?${desc=="csuu_status" ? "asc=csuu_status" : ""}${(asc=="csuu_status" || desc!="csuu_status" )? "desc=csuu_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 </td>
			 </s:if>
			 
			<td width="130">&nbsp;
				${lz:set("permission",lz:permission(controller,'canAdd'))}
				<s:if test="#request.canAdd==true && #request.permission==true">
					<a href="javascript:AddUser()" style="text-decoration:underline">添加</a>
				</s:if>
			</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csuuId}" >
		  	<td align="center">
				<input class="check" type="checkbox" value="${item.csuuId}"/>
			</td>
			
			${lz:set("listable",lz:listable(controller,'csuuId'))}
			<s:if test="#request.listable!=false">
			<td ref="csuuId" class="">
					 ${lz:zerofill(item.csuuId,6)}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csuuUsername'))}
			<s:if test="#request.listable!=false">
			<td ref="csuuUsername" class=" node">
					 ${item.csuuUsername}
			</td>
			</s:if>
			
						
			
			${lz:set("listable",lz:listable(controller,'csuuIsRoot'))}
			<s:if test="#request.listable!=false">
			<td ref="csuuIsRoot" class="">
				 			${item.csuuIsRoot==true?"是":"否"}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csuuLimit'))}
			<s:if test="#request.listable!=false">
			<td ref="csuuLimit" class="">
				 	${lz:set("permission",lz:permission(controller,'canEdit'))}
				 <s:if test="#request.canEdit==true && #request.permission==true">
				 	<a class="modify" href="javascript:Update('${item.csuuId}','limit')"></a>
				 </s:if>
							${item.csuuLimit$}&nbsp;&nbsp;&nbsp;							
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csuuRealname'))}
			<s:if test="#request.listable!=false">
			<td ref="csuuRealname" class="">
					 ${item.csuuRealname}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csuuMobile'))}
			<s:if test="#request.listable!=false">
			<td ref="csuuMobile" class="">
					 ${item.csuuMobile}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csuuAddTime'))}
			<s:if test="#request.listable!=false">
			<td ref="csuuAddTime" class="">
				 	 <s:date name="#item.csuuAddTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
			
			<s:if test="#request.all==true">
			${lz:set("listable",lz:listable(controller,'csuuLastIp'))}
			<s:if test="#request.listable!=false">
			<td ref="csuuLastIp" class="">
					 ${item.csuuLastIp}
			</td>
			</s:if>
			</s:if>
			<s:if test="#request.all==true">
			${lz:set("listable",lz:listable(controller,'csuuLastTime'))}
			<s:if test="#request.listable!=false">
			<td ref="csuuLastTime" class="">
					 ${item.csuuLastTime}
			</td>
			</s:if>
			</s:if>
			
			${lz:set("listable",lz:listable(controller,'csuuStatus'))}
			<s:if test="#request.listable!=false">
			<td ref="csuuStatus" class="">
				 	${lz:set("permission",lz:permission(controller,'canEdit'))}
				 <s:if test="#request.canEdit==true && #request.permission==true">
				 	<a class="modify" href="javascript:Update('${item.csuuId}','status')"></a>
				 </s:if>
					 ${item.csuuStatus==1?"正常":""}
					 ${item.csuuStatus==0?"无效":""}
			</td>
			</s:if>
			
			<td>
				${lz:set("permission",lz:permission(controller,'canEdit'))}
				<s:if test="#request.canEdit==true && #request.permission==true">
				<a href="javascript:EditUser('${item.csuuId}')">修改</a></s:if>
				${lz:set("permission",lz:permission(controller,'canDel'))}
				
				<s:if test="#session.unit_login.csuuId!=item.csuuId">
					<s:if test="#request.canDel==true && #request.permission==true">
					<a href="javascript:DelUser('${item.csuuId}','${item.csuuUsername}')">删除</a></s:if>
				</s:if>
				
				${lz:set("permission",lz:permission(controller,'canView'))}
				<s:if test="#request.canView==true && #request.permission==true">
				<a href="javascript:DetailsUser('${item.csuuId}')">详情</a></s:if>
			</td>
		  </tr>
		 </s:iterator>
		 </tbody>			 
	  </table>
	  
  </div>
  <%@include file="/admin/module/page.jsp"%>
  
 </td></tr></table>

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
${lz:set("permission",lz:permission(controller,'canAdd'))}
<s:if test="#request.canAdd==true && #request.permission==true">
	/**
	* 添加企业管理员
	**/
	function AddUser(parent){		
		var url = "${basePath}unit/user.edit.do";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),controller:${controller==null?"{title:'添加企业管理员'}":lz:json(controller)}};
		href(url,params);
	}
</s:if>
${lz:set("permission",lz:permission(controller,'canEdit'))}
<s:if test="#request.canEdit==true && #request.permission==true">
	/**
	* 修改企业管理员
	**/
	function EditUser(id){
		var url = "${basePath}unit/user.edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:${controller==null?"{title:'修改企业管理员'}":lz:json(controller)}};
		href(url,params);
	}
	/*
	* 修改所选
	*/
	function EditSel(){
		var checker=$(".check:checked");
		if(checker.length!=1)
		{
			$.tips("请选择一个需要修改的记录.");
			return;
		}
		EditUser(checker.val());
	}
	
	/**
	* 批量修改
	**/
	function UpdateSel(flag)
	{
		var checker=$(".check:checked");
		if(checker.length==0){
			$.tips("您未选中任何记录，请至少选择一项.");
			return;
		}
		var ids = "";
		checker.each(function(i){
			if(ids!="")
				ids+=",";
			ids+=$(this).val();
		});
		var url = "${basePath}unit/user.edit.do";
		var params = {entrypoint:"${entrypoint}",ids:ids,controller:{title:"批量更新企业管理员",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.controller["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	/**
	* 修改企业管理员
	**/
	function Update(id,flag)
	{
		var url = "${basePath}unit/user.edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"更新企业管理员",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.controller["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["limit"]={};
	fieldNames["status"]={};
	fieldNames["limit"]["csuuLimit"]=true;
	fieldNames["status"]["csuuStatus"]=true;

</s:if>
${lz:set("permission",lz:permission(controller,'canDel'))}
<s:if test="#request.canDel==true && #request.permission==true">
	/**
	* 删除企业管理员
	**/
	function DelUser(id,flag)
	{
		var dialog=$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除企业管理员["+flag+"]吗？</span>",
				title:"删除企业管理员",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}unit/user.del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);
				}
		 });	
	}
	/*
	* 删除所选记录详情
	*/
	function DelSel(){
		var checker=$(".check:checked");
		if(checker.length==0)
		{
			$.tips("您未选中任何记录，请至少选择一项.");
			return;
		}
		var dialog=$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除所选企业管理员吗？</span>",
				title:"批量删除企业管理员",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}unit/user.del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示企业管理员详情
	**/
	function DetailsUser(id)
	{
		var url = "${basePath}unit/user.details.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"企业管理员详情",editable:false,visible:true}};
		href(url,params);
	}
	/*
	* 显示所选记录详情
	*/
	function DetailsSel(){
		var checker=$(".check:checked");
		if(checker.length!=1)
		{
			$.tips("请选择一个需要显示详情的记录.");
			return;
		}
		DetailsUser(checker.val());
	}
</script>    

</body>
</html>