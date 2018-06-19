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


<div class="title">部门层级管理</div>

	
</td></tr><tr><td>
  <%@include file="/admin/module/page.jsp"%>
  	
  			
	<div class="content">
	    <div class="toolbar">

			<button type="button" class="del" onclick="DelSel();">
				删除
			</button>

			
			<button type="button" class="add" onclick="AddDept();">
				添加
			</button>							
			<button type="button" class="all" onclick="$('.check').attr('checked',true)">
				全选
			</button>
			<button type="button" class="invert" onclick="$('.check').click();">
				反选
			</button>		
		</div>
		
		<table id="dept_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td width="40" align="center">
			 	选择
			 </td>
			 
			
			 <td width=72 ${all?"width":"refer"}="120" title="编号">
			 	<a class="${desc=="csug_id" ? "desc" : ""}${asc=="csug_id" ? "asc" : ""}" href="?${desc=="csug_id" ? "asc=csug_id" : ""}${(asc=="csug_id" || desc!="csug_id" )? "desc=csug_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 </td>
						 			 
		
			 <td  ${all?"width":"refer"}="200" title="部门名称">
			 	<a class="${desc=="csug_name" ? "desc" : ""}${asc=="csug_name" ? "asc" : ""}" href="?${desc=="csug_name" ? "asc=csug_name" : ""}${(asc=="csug_name" || desc!="csug_name" )? "desc=csug_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	部门名称
			 	</a>
			 </td>
			 			 			 			 			
			
			 <td  ${all?"width":"refer"}="120" title="所属单位">
			 	<a class="${desc=="csug_info" ? "desc" : ""}${asc=="csug_info" ? "asc" : ""}" href="?${desc=="csug_info" ? "asc=csug_info" : ""}${(asc=="csuu_is_root" || desc!="csug_info" )? "desc=csug_info" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属单位
			 	</a>
			 </td>
			 			 
			 
			 <td  ${all?"width":"refer"}="200" title="城市">
			 	<a class="${desc=="csug_host" ? "desc" : ""}${asc=="csug_host" ? "asc" : ""}" href="?${desc=="csug_host" ? "asc=csug_host" : ""}${(asc=="csug_host" || desc!="csug_host" )? "desc=csug_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 </td>
						 			 
			
			 <td  ${all?"width":"refer"}="200" title="审核人员">
			 	<a class="${desc=="csug_person" ? "desc" : ""}${asc=="csug_person" ? "asc" : ""}" href="?${desc=="csug_person" ? "asc=csug_person" : ""}${(asc=="csug_person" || desc!="csug_person" )? "desc=csug_person" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	审核人员
			 	</a>
			 </td>
					
			
			 <td  ${all?"width":"refer"}="200" title="添加时间">
			 	<a class="${desc=="csug_add_time" ? "desc" : ""}${asc=="csug_add_time" ? "asc" : ""}" href="?${desc=="csug_add_time" ? "asc=csug_add_time" : ""}${(asc=="csug_add_time" || desc!="csug_add_time" )? "desc=csug_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 </td>

			 
			 <td  ${all?"width":"refer"}="140" title="状态">
			 	<a class="${desc=="csug_status" ? "desc" : ""}${asc=="csug_status" ? "asc" : ""}" href="?${desc=="csug_status" ? "asc=csug_status" : ""}${(asc=="csug_status" || desc!="csug_status" )? "desc=csug_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 </td>
	
			 
			<td width="130">&nbsp;
				<a href="javascript:AddDept()" style="text-decoration:underline">添加</a>
			</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csugId}" >
		  	<td align="center">
				<input class="check" type="checkbox" value="${item.csugId}"/>
			</td>

			<td ref="csugId" class="">
					 ${lz:zerofill(item.csugId,6)}
			</td>		

			<td ref="csugName" class=" node">
					 ${item.csugName}
			</td>
			
			<td ref="csugInfo" class="">
				 	${item.$csugInfo.csuiName$}
			</td>
		
			<td ref="csugHost" class="">
					 ${item.csugHost$}
			</td>

			<td ref="csugPerson" class="">
				${item.$csugPerson.$csupMember.csmName}-${item.$csugPerson.$csupMember.csmMobile}
			</td>
			
			<td ref="csugAddTime" class="">
				<s:date name="#item.csugAddTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			
			
			<td ref="csugStatus" class="">				
				<a class="modify" href="javascript:Update('${item.csugId}','status')"></a>
				${item.csugStatus==1?"正常":""}
				${item.csugStatus==0?"无效":""}
			</td>

			
			<td>
				<a href="javascript:EditDept('${item.csugId}')">修改</a>				
				<a href="javascript:DelDept('${item.csugId}','${item.csugName}')">删除</a>
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
	/**
	* 添加企业部门
	**/
	function AddDept(parent){		
		var url = "${basePath}official/dept.edit.do";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),controller:${controller==null?"{title:'添加企业部门'}":lz:json(controller)}};
		href(url,params);
	}
	/**
	* 修改企业部门
	**/
	function EditDept(id){
		var url = "${basePath}official/dept.edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:${controller==null?"{title:'修改企业部门'}":lz:json(controller)}};
		href(url,params);
	}
	
		/**
	* 删除企业管理员
	**/
	function DelDept(id,flag)
	{
		var dialog=$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除企业部门信息["+flag+"]吗？</span>",
				title:"删除企业部门",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}official/dept.del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选企业部门吗？</span>",
				title:"批量删除企业部门",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}official/dept.del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}

	
	/**
	* 修改企业管理员
	**/
	function Update(id,flag)
	{
		var url = "${basePath}official/dept.edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"更新企业部门信息",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.controller["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	/**
	* 显示企业管理员详情
	**/
	function DetailsDept(id)
	{
		var url = "${basePath}official/dept.details.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"企业部门详情",editable:false,visible:true}};
		href(url,params);
	}

</script>    

</body>
</html>