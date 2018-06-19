<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>套餐使用记录管理</title>
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


<div class="title">套餐使用记录管理</div>

<div class="plate">
	<form class="query" id="queryForm" action="record.do" method="post">
	${lz:set("haveQuery",false)}
		 
		 			 
		 	${lz:set("queryable",lz:queryable(controller,'csurGift'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>所属套餐项:</dt>
				<dd>
					<input class="Not" title="不包含" ${csurGiftNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csurGiftNot" value="csurGift"/>
		 			<input title="请输入优惠内容编号进行查询" class="combox" action="${basePath}admin/user/gift_query.do?value={param}" size="16" type="text" id="csurGift" name="csurGift" text="${get:CsGift(csurGift).csgId}" value="${csurGift}" />
				</dd>
			</dl>
			</s:if>
		
		 
		 	${lz:set("queryable",lz:queryable(controller,'csurOrder'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>所属订单:</dt>
				<dd>
					<input class="Not" title="不包含" ${csurOrderNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csurOrderNot" value="csurOrder"/>
		 			<input title="请输入订单订单编号进行查询" class="combox" action="${basePath}admin/service/order_query.do?value={param}" size="16" type="text" id="csurOrder" name="csurOrder" text="${get:CsOrder(csurOrder).csoId}" value="${csurOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csurOrder').val())==''){return;};window.href('${basePath}admin/service/order_details.do?id='+$('#csurOrder').val(),{controller:{editable:false,editable:false,visible:true}})"></a>
				</dd>
			</dl>
			</s:if>
		
		 
		 	${lz:set("queryable",lz:queryable(controller,'csurAddTime'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>添加时间:</dt>
				<dd>
					<input class="Not" title="不包含" ${csurAddTimeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csurAddTimeNot" value="csurAddTime"/>
		 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csurAddTimeStart" id="csurAddTimeStart"  value="<s:date name="#request.csurAddTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
					起</dd></dl><dl class="query-item"><dt>添加时间:</dt><dd>
					<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csurAddTimeEnd" id="csurAddTimeEnd"  value="<s:date name="#request.csurAddTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
					止
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
	   
		<table id="userecord_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	
			 ${lz:set("listable",lz:listable(controller,'csurId'))}
			 <s:if test="#request.listable!=false">
			 <td width=72 ${all?"width":"refer"}="120" title="编号">
			 	<a class="${desc=="csur_id" ? "desc" : ""}${asc=="csur_id" ? "asc" : ""}" href="?${desc=="csur_id" ? "asc=csur_id" : ""}${(asc=="csur_id" || desc!="csur_id" )? "desc=csur_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csurMember'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="使用帐号">
			 	使用帐号
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csurGift'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="所属优惠项">
			 	<a class="${desc=="csur_gift" ? "desc" : ""}${asc=="csur_gift" ? "asc" : ""}" href="?${desc=="csur_gift" ? "asc=csur_gift" : ""}${(asc=="csur_gift" || desc!="csur_gift" )? "desc=csur_gift" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属优惠项
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csurOrder'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="所属订单">
			 	<a class="${desc=="csur_order" ? "desc" : ""}${asc=="csur_order" ? "asc" : ""}" href="?${desc=="csur_order" ? "asc=csur_order" : ""}${(asc=="csur_order" || desc!="csur_order" )? "desc=csur_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属订单
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csurCount'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="使用数量">
			 	<a class="${desc=="csur_count" ? "desc" : ""}${asc=="csur_count" ? "asc" : ""}" href="?${desc=="csur_count" ? "asc=csur_count" : ""}${(asc=="csur_count" || desc!="csur_count" )? "desc=csur_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用数量
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csurAddTime'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="140" title="添加时间">
			 	<a class="${desc=="csur_add_time" ? "desc" : ""}${asc=="csur_add_time" ? "asc" : ""}" href="?${desc=="csur_add_time" ? "asc=csur_add_time" : ""}${(asc=="csur_add_time" || desc!="csur_add_time" )? "desc=csur_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 </td>
			 </s:if>
			 
			<td width="130">&nbsp;
				${lz:set("permission",lz:permission(controller,'canAdd'))}
				<s:if test="#request.canAdd==true && #request.permission==true">
					<a href="javascript:AddUserecord()" style="text-decoration:underline">添加</a>
				</s:if>
			</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csurId}" >
			
			${lz:set("listable",lz:listable(controller,'csurId'))}
			<s:if test="#request.listable!=false">
			<td ref="csurId" class=" node">
					 ${lz:zerofill(item.csurId,6)}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csurMember'))}
			<s:if test="#request.listable!=false">
			<td ref="csurMember" class="" relate="${item.csurMember}">
				 ${get:CsMember(item.csurMember).csmName}
			</td>
			</s:if>
			
						
			${lz:set("listable",lz:listable(controller,'csurGift'))}
			<s:if test="#request.listable!=false">
			<td ref="csurGift" class="" relate="${item.csurGift}">
				 ${get:CsGift(item.csurGift).csgName}	
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csurOrder'))}
			<s:if test="#request.listable!=false">
			<td ref="csurOrder" class="" relate="${item.csurOrder}">
				 	 <a href="javascript:void(0);" onclick="window.href('${basePath}unit/order.details.do?id=${item.csurOrder}',{controller:{editable:false,visible:true}})">
				 	 ${get:CsOrder(item.csurOrder).csoId}			 	 
				 	 </a>
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csurCount'))}
			<s:if test="#request.listable!=false">
			<td ref="csurCount" class="">
			 				${item.csurCount}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csurAddTime'))}
			<s:if test="#request.listable!=false">
			<td ref="csurAddTime" class="">
				 	 <s:date name="#item.csurAddTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
			
			<td>
				${lz:set("permission",lz:permission(controller,'canEdit'))}
				<s:if test="#request.canEdit==true && #request.permission==true">
				<a href="javascript:EditUserecord('${item.csurId}')">修改</a></s:if>
				${lz:set("permission",lz:permission(controller,'canDel'))}
				<s:if test="#request.canDel==true && #request.permission==true">
				<a href="javascript:DelUserecord('${item.csurId}','${item.csurId}')">删除</a></s:if>
				${lz:set("permission",lz:permission(controller,'canView'))}
				<s:if test="#request.canView==true && #request.permission==true">
				<a href="javascript:DetailsUserecord('${item.csurId}')">详情</a></s:if>
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
	* 添加免费优惠记录
	**/
	function AddUserecord(parent){		
		var url = "${basePath}admin/user/userecord_edit.do";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),controller:${controller==null?"{title:'添加免费优惠记录'}":lz:json(controller)}};
		href(url,params);
	}
</s:if>
${lz:set("permission",lz:permission(controller,'canEdit'))}
<s:if test="#request.canEdit==true && #request.permission==true">
	/**
	* 修改免费优惠记录
	**/
	function EditUserecord(id){
		var url = "${basePath}admin/user/userecord_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:${controller==null?"{title:'修改免费优惠记录'}":lz:json(controller)}};
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
		EditUserecord(checker.val());
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
		var url = "${basePath}admin/user/userecord_edit.do";
		var params = {entrypoint:"${entrypoint}",ids:ids,controller:{title:"批量更新免费优惠记录",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.controller["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	/**
	* 修改免费优惠记录
	**/
	function Update(id,flag)
	{
		var url = "${basePath}admin/user/userecord_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"更新免费优惠记录",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.controller["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};

</s:if>
${lz:set("permission",lz:permission(controller,'canDel'))}
<s:if test="#request.canDel==true && #request.permission==true">
	/**
	* 删除免费优惠记录
	**/
	function DelUserecord(id,flag)
	{
		var dialog=$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除免费优惠记录["+flag+"]吗？</span>",
				title:"删除免费优惠记录",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}admin/user/userecord_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选免费优惠记录吗？</span>",
				title:"批量删除免费优惠记录",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}admin/user/userecord_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示免费优惠记录详情
	**/
	function DetailsUserecord(id)
	{
		var url = "${basePath}admin/user/userecord_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"免费优惠记录详情",editable:false,visible:true}};
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
		DetailsUserecord(checker.val());
	}
</script>    

</body>
</html>