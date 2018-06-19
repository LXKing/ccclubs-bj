<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>用户套餐管理</title>
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

<script>
	$(function(){
		$(".pager li:contains('导出')").remove();
	});	
</script>

<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td>


<div class="title">用户套餐管理</div>

<div class="plate">
	<form class="query" id="queryForm" action="pack.do" method="post">
	${lz:set("haveQuery",false)}
		 
		 			
		 
		 	${lz:set("queryable",lz:queryable(controller,'csupUntilTime'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>有效期至:</dt>
				<dd>
					<input class="Not" title="不包含" ${csupUntilTimeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csupUntilTimeNot" value="csupUntilTime"/>
		 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csupUntilTimeStart" id="csupUntilTimeStart"  value="<s:date name="#request.csupUntilTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
					起</dd></dl><dl class="query-item"><dt>有效期至:</dt><dd>
					<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csupUntilTimeEnd" id="csupUntilTimeEnd"  value="<s:date name="#request.csupUntilTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
					止
				</dd>
			</dl>
			</s:if>
		
		 
		 	${lz:set("queryable",lz:queryable(controller,'csupStatus'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>套餐状态:</dt>
				<dd>
					<input class="Not" title="不包含" ${csupStatusNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csupStatusNot" value="csupStatus"/>
		 		<select id="csupStatus" name="csupStatus" style="width:100px;">
		 			<option value="">全部</option>
						<option value="1" ${csupStatus==1?"selected":""}>有效</option>
						<option value="0" ${csupStatus==0?"selected":""}>无效</option>
						<option value="2" ${csupStatus==2?"selected":""}>过期</option>
						<option value="3" ${csupStatus==3?"selected":""}>用完</option>
		 		</select>
				</dd>
			</dl>
			</s:if>
		
			<div class="line"></div>
			<div class="option">
				<table border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td>
						<input type="hidden" name="all" value="${all}" />
						<button class="button" type="submit">&nbsp;&nbsp;查询&nbsp;&nbsp;</button>		
						<button class="button" onclick="clearForm()" type="button">&nbsp;&nbsp;重置&nbsp;&nbsp;</button>
					</td>
				  </tr>
				</table>
				<div class="clear"></div>
			</div>
		</form>
	</div>

<s:if test="#request.haveQuery==false">
<script>
$(function(){
	$(".query").parent().prevAll().remove();
	$(".query").parent().remove();
})
</script>
</s:if>
    

	
</td></tr><tr><td>
  <%@include file="/admin/module/page.jsp"%>
  	
  			
	<div class="content">
	   
		<table id="pack_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
			 
			 ${lz:set("listable",lz:listable(controller,'csupId'))}
			 <s:if test="#request.listable!=false">
			 <td width=72 ${all?"width":"refer"}="120" title="编号">
			 	<a class="${desc=="csup_id" ? "desc" : ""}${asc=="csup_id" ? "asc" : ""}" href="?${desc=="csup_id" ? "asc=csup_id" : ""}${(asc=="csup_id" || desc!="csup_id" )? "desc=csup_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csupPack'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="所属系统套餐">
			 	<a class="${desc=="csup_pack" ? "desc" : ""}${asc=="csup_pack" ? "asc" : ""}" href="?${desc=="csup_pack" ? "asc=csup_pack" : ""}${(asc=="csup_pack" || desc!="csup_pack" )? "desc=csup_pack" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属系统套餐
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csupMember'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="所属会员">
			 	<a class="${desc=="csup_member" ? "desc" : ""}${asc=="csup_member" ? "asc" : ""}" href="?${desc=="csup_member" ? "asc=csup_member" : ""}${(asc=="csup_member" || desc!="csup_member" )? "desc=csup_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属会员
			 	</a>
			 </td>
			 </s:if>
			 
			 <s:if test="#request.all==true">
			 ${lz:set("listable",lz:listable(controller,'csupRemark'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="200" title="备注">
			 	<a class="${desc=="csup_remark" ? "desc" : ""}${asc=="csup_remark" ? "asc" : ""}" href="?${desc=="csup_remark" ? "asc=csup_remark" : ""}${(asc=="csup_remark" || desc!="csup_remark" )? "desc=csup_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 </td>
			 </s:if>
			 </s:if>
			 
			 ${lz:set("listable",lz:listable(controller,'csupUntilTime'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="140" title="有效期至">
			 	<a class="${desc=="csup_until_time" ? "desc" : ""}${asc=="csup_until_time" ? "asc" : ""}" href="?${desc=="csup_until_time" ? "asc=csup_until_time" : ""}${(asc=="csup_until_time" || desc!="csup_until_time" )? "desc=csup_until_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	有效期至
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csupUpdateTime'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="140" title="修改时间">
			 	<a class="${desc=="csup_update_time" ? "desc" : ""}${asc=="csup_update_time" ? "asc" : ""}" href="?${desc=="csup_update_time" ? "asc=csup_update_time" : ""}${(asc=="csup_update_time" || desc!="csup_update_time" )? "desc=csup_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csupAddTime'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="140" title="添加时间">
			 	<a class="${desc=="csup_add_time" ? "desc" : ""}${asc=="csup_add_time" ? "asc" : ""}" href="?${desc=="csup_add_time" ? "asc=csup_add_time" : ""}${(asc=="csup_add_time" || desc!="csup_add_time" )? "desc=csup_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csupStatus'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="套餐状态">
			 	<a class="${desc=="csup_status" ? "desc" : ""}${asc=="csup_status" ? "asc" : ""}" href="?${desc=="csup_status" ? "asc=csup_status" : ""}${(asc=="csup_status" || desc!="csup_status" )? "desc=csup_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	套餐状态
			 	</a>
			 </td>
			 </s:if>
			 
			<td width="130">&nbsp;
				${lz:set("permission",lz:permission(controller,'canAdd'))}
				<s:if test="#request.canAdd==true && #request.permission==true">
					<a href="javascript:AddPack()" style="text-decoration:underline">添加</a>
				</s:if>
			</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csupId}" >
		  				
			${lz:set("listable",lz:listable(controller,'csupId'))}
			<s:if test="#request.listable!=false">
			<td ref="csupId" class=" node">
					 ${lz:zerofill(item.csupId,6)}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csupPack'))}
			<s:if test="#request.listable!=false">
			<td ref="csupPack" class="" relate="${item.csupPack}">
				 	 ${get:CsPack(item.csupPack).cspName}	
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csupMember'))}
			<s:if test="#request.listable!=false">
			<td ref="csupMember" class="" relate="${item.csupMember}">
				 	${get:CsMember(item.csupMember).csmName}	
			</td>
			</s:if>
			
			<s:if test="#request.all==true">
			${lz:set("listable",lz:listable(controller,'csupRemark'))}
			<s:if test="#request.listable!=false">
			<td ref="csupRemark" class="">
				 	${lz:left(item.csupRemark,32)}
			</td>
			</s:if>
			</s:if>
			
			${lz:set("listable",lz:listable(controller,'csupUntilTime'))}
			<s:if test="#request.listable!=false">
			<td ref="csupUntilTime" class="">
				 	 <s:date name="#item.csupUntilTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csupUpdateTime'))}
			<s:if test="#request.listable!=false">
			<td ref="csupUpdateTime" class="">
				 	 <s:date name="#item.csupUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csupAddTime'))}
			<s:if test="#request.listable!=false">
			<td ref="csupAddTime" class="">
				 	 <s:date name="#item.csupAddTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csupStatus'))}
			<s:if test="#request.listable!=false">
			<td ref="csupStatus" class="">
				 	${lz:set("permission",lz:permission(controller,'canEdit'))}
				 <s:if test="#request.canEdit==true && #request.permission==true">
				 	<a class="modify" href="javascript:Update('${item.csupId}','status')"></a>
				 </s:if>
					 ${item.csupStatus==1?"有效":""}
					 ${item.csupStatus==0?"无效":""}
					 ${item.csupStatus==2?"过期":""}
					 ${item.csupStatus==3?"用完":""}
			</td>
			</s:if>
			
			<td>
				${lz:set("permission",lz:permission(controller,'canEdit'))}
				<s:if test="#request.canEdit==true && #request.permission==true">
				<a href="javascript:EditPack('${item.csupId}')">修改</a></s:if>
				${lz:set("permission",lz:permission(controller,'canDel'))}
				<s:if test="#request.canDel==true && #request.permission==true">
				<a href="javascript:DelPack('${item.csupId}','${item.csupId}')">删除</a></s:if>
				${lz:set("permission",lz:permission(controller,'canView'))}
				<s:if test="#request.canView==true && #request.permission==true">
				<a href="javascript:DetailsPack('${item.csupId}')">详情</a></s:if>
				
				<a href="javascript:parent.href('gift.do?csgUserPack=${item.csupId}','套餐详细内容');">套餐详细内容</a>
				
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
	* 添加用户套餐
	**/
	function AddPack(parent){		
		var url = "${basePath}admin/user/pack_edit.do";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),controller:${controller==null?"{title:'添加用户套餐'}":lz:json(controller)}};
		href(url,params);
	}
</s:if>
${lz:set("permission",lz:permission(controller,'canEdit'))}
<s:if test="#request.canEdit==true && #request.permission==true">
	/**
	* 修改用户套餐
	**/
	function EditPack(id){
		var url = "${basePath}admin/user/pack_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:${controller==null?"{title:'修改用户套餐'}":lz:json(controller)}};
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
		EditPack(checker.val());
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
		var url = "${basePath}admin/user/pack_edit.do";
		var params = {entrypoint:"${entrypoint}",ids:ids,controller:{title:"批量更新用户套餐",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.controller["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	/**
	* 修改用户套餐
	**/
	function Update(id,flag)
	{
		var url = "${basePath}admin/user/pack_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"更新用户套餐",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.controller["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["state"]={};
	fieldNames["status"]={};
	fieldNames["state"]["csupState"]=true;
	fieldNames["status"]["csupStatus"]=true;

</s:if>
${lz:set("permission",lz:permission(controller,'canDel'))}
<s:if test="#request.canDel==true && #request.permission==true">
	/**
	* 删除用户套餐
	**/
	function DelPack(id,flag)
	{
		var dialog=$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除用户套餐["+flag+"]吗？</span>",
				title:"删除用户套餐",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}admin/user/pack_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选用户套餐吗？</span>",
				title:"批量删除用户套餐",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}admin/user/pack_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示用户套餐详情
	**/
	function DetailsPack(id)
	{
		var url = "${basePath}admin/user/pack_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"用户套餐详情",editable:false,visible:true}};
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
		DetailsPack(checker.val());
	}
</script>    

</body>
</html>