<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>优惠内容管理</title>
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


<div class="title">优惠内容管理</div>

<div class="plate">
	<form class="query" id="queryForm" action="gift.do" method="post">
	${lz:set("haveQuery",false)}
		 
		 			 
		 	${lz:set("queryable",lz:queryable(controller,'csgUserPack'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>所属套餐:</dt>
				<dd>
					<input class="Not" title="不包含" ${csgUserPackNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csgUserPackNot" value="csgUserPack"/>
		 			<input title="请输入用户套餐编号进行查询" class="combox" action="${basePath}unit/pack.query.do?value={param}&csupMember=${unit_login.$csuuUnitInfo.csuiMember}" size="16" type="text" id="csgUserPack" name="csgUserPack" text="${get:CsUserPack(csgUserPack).csupId}" value="${csgUserPack}" />
				</dd>
			</dl>
			</s:if>
			
		 		
		 
		 	${lz:set("queryable",lz:queryable(controller,'csgUntilTime'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>有效期至:</dt>
				<dd>
					<input class="Not" title="不包含" ${csgUntilTimeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csgUntilTimeNot" value="csgUntilTime"/>
		 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csgUntilTimeStart" id="csgUntilTimeStart"  value="<s:date name="#request.csgUntilTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
					起</dd></dl><dl class="query-item"><dt>有效期至:</dt><dd>
					<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csgUntilTimeEnd" id="csgUntilTimeEnd"  value="<s:date name="#request.csgUntilTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
					止
				</dd>
			</dl>
			</s:if>
				 
		 	${lz:set("queryable",lz:queryable(controller,'csgStatus'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>状态:</dt>
				<dd>
					<input class="Not" title="不包含" ${csgStatusNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csgStatusNot" value="csgStatus"/>
		 		<select id="csgStatus" name="csgStatus" style="width:100px;">
		 			<option value="">全部</option>
						<option value="1" ${csgStatus==1?"selected":""}>有效</option>
						<option value="0" ${csgStatus==0?"selected":""}>无效</option>
						<option value="2" ${csgStatus==2?"selected":""}>过期</option>
						<option value="3" ${csgStatus==3?"selected":""}>用完</option>
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
	    		
		<table id="gift_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 
			 ${lz:set("listable",lz:listable(controller,'csgId'))}
			 <s:if test="#request.listable!=false">
			 <td width=72 ${all?"width":"refer"}="120" title="编号">
			 	<a class="${desc=="csg_id" ? "desc" : ""}${asc=="csg_id" ? "asc" : ""}" href="?${desc=="csg_id" ? "asc=csg_id" : ""}${(asc=="csg_id" || desc!="csg_id" )? "desc=csg_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csgName'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="200" title="优惠名称">
			 	<a class="${desc=="csg_name" ? "desc" : ""}${asc=="csg_name" ? "asc" : ""}" href="?${desc=="csg_name" ? "asc=csg_name" : ""}${(asc=="csg_name" || desc!="csg_name" )? "desc=csg_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优惠名称
			 	</a>
			 </td>
			 </s:if>
			 
			 			 
			
			 ${lz:set("listable",lz:listable(controller,'csgUserPack'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="会员套餐">
			 	<a class="${desc=="csg_user_pack" ? "desc" : ""}${asc=="csg_user_pack" ? "asc" : ""}" href="?${desc=="csg_user_pack" ? "asc=csg_user_pack" : ""}${(asc=="csg_user_pack" || desc!="csg_user_pack" )? "desc=csg_user_pack" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	会员套餐
			 	</a>
			 </td>
			 </s:if>
			 
			 ${lz:set("listable",lz:listable(controller,'csgGoods'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="所属商品">
			 	<a class="${desc=="csg_goods" ? "desc" : ""}${asc=="csg_goods" ? "asc" : ""}" href="?${desc=="csg_goods" ? "asc=csg_goods" : ""}${(asc=="csg_goods" || desc!="csg_goods" )? "desc=csg_goods" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属商品
			 	</a>
			 </td>
			 </s:if>
			 
			 ${lz:set("listable",lz:listable(controller,'csgOutlets'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="所属网点">
			 	<a class="${desc=="csg_outlets" ? "desc" : ""}${asc=="csg_outlets" ? "asc" : ""}" href="?${desc=="csg_outlets" ? "asc=csg_outlets" : ""}${(asc=="csg_outlets" || desc!="csg_outlets" )? "desc=csg_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属网点
			 	</a>
			 </td>
			 </s:if>
			 
			 ${lz:set("listable",lz:listable(controller,'csgModel'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="所属车型">
			 	<a class="${desc=="csg_model" ? "desc" : ""}${asc=="csg_model" ? "asc" : ""}" href="?${desc=="csg_model" ? "asc=csg_model" : ""}${(asc=="csg_model" || desc!="csg_model" )? "desc=csg_model" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属车型
			 	</a>
			 </td>
			 </s:if>
			 
			 ${lz:set("listable",lz:listable(controller,'csgType'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="优惠类型">
			 	<a class="${desc=="csg_type" ? "desc" : ""}${asc=="csg_type" ? "asc" : ""}" href="?${desc=="csg_type" ? "asc=csg_type" : ""}${(asc=="csg_type" || desc!="csg_type" )? "desc=csg_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优惠类型
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csgCount'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="原始数量">
			 	<a class="${desc=="csg_count" ? "desc" : ""}${asc=="csg_count" ? "asc" : ""}" href="?${desc=="csg_count" ? "asc=csg_count" : ""}${(asc=="csg_count" || desc!="csg_count" )? "desc=csg_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	原始数量
			 	</a>
			 </td>
			 </s:if>
			 
			 ${lz:set("listable",lz:listable(controller,'csgRemain'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="可用数量">
			 	<a class="${desc=="csg_remain" ? "desc" : ""}${asc=="csg_remain" ? "asc" : ""}" href="?${desc=="csg_remain" ? "asc=csg_remain" : ""}${(asc=="csg_remain" || desc!="csg_remain" )? "desc=csg_remain" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	可用数量
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csgPrice'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="优惠价格">
			 	<a class="${desc=="csg_price" ? "desc" : ""}${asc=="csg_price" ? "asc" : ""}" href="?${desc=="csg_price" ? "asc=csg_price" : ""}${(asc=="csg_price" || desc!="csg_price" )? "desc=csg_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优惠价格
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csgRebate'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="优惠折扣">
			 	<a class="${desc=="csg_rebate" ? "desc" : ""}${asc=="csg_rebate" ? "asc" : ""}" href="?${desc=="csg_rebate" ? "asc=csg_rebate" : ""}${(asc=="csg_rebate" || desc!="csg_rebate" )? "desc=csg_rebate" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优惠折扣
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csgUntilTime'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="140" title="有效期至">
			 	<a class="${desc=="csg_until_time" ? "desc" : ""}${asc=="csg_until_time" ? "asc" : ""}" href="?${desc=="csg_until_time" ? "asc=csg_until_time" : ""}${(asc=="csg_until_time" || desc!="csg_until_time" )? "desc=csg_until_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	有效期至
			 	</a>
			 </td>
			 </s:if>
			 
			 <s:if test="#request.all==true">
			 ${lz:set("listable",lz:listable(controller,'csgUpdateTime'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="140" title="修改时间">
			 	<a class="${desc=="csg_update_time" ? "desc" : ""}${asc=="csg_update_time" ? "asc" : ""}" href="?${desc=="csg_update_time" ? "asc=csg_update_time" : ""}${(asc=="csg_update_time" || desc!="csg_update_time" )? "desc=csg_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 </td>
			 </s:if>
			 </s:if>
			 <s:if test="#request.all==true">
			 ${lz:set("listable",lz:listable(controller,'csgAddTime'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="140" title="添加时间">
			 	<a class="${desc=="csg_add_time" ? "desc" : ""}${asc=="csg_add_time" ? "asc" : ""}" href="?${desc=="csg_add_time" ? "asc=csg_add_time" : ""}${(asc=="csg_add_time" || desc!="csg_add_time" )? "desc=csg_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 </td>
			 </s:if>
			 </s:if>
			 
			 ${lz:set("listable",lz:listable(controller,'csgStatus'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="状态">
			 	<a class="${desc=="csg_status" ? "desc" : ""}${asc=="csg_status" ? "asc" : ""}" href="?${desc=="csg_status" ? "asc=csg_status" : ""}${(asc=="csg_status" || desc!="csg_status" )? "desc=csg_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 </td>
			 </s:if>
			 
			<td width="130">&nbsp;
			</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csgId}" >
		  				
			${lz:set("listable",lz:listable(controller,'csgId'))}
			<s:if test="#request.listable!=false">
			<td ref="csgId" class=" node">
					 ${lz:zerofill(item.csgId,6)}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csgName'))}
			<s:if test="#request.listable!=false">
			<td ref="csgName" class=" node">
					 ${item.csgName}
			</td>
			</s:if>
			
						
			${lz:set("listable",lz:listable(controller,'csgUserPack'))}
			<s:if test="#request.listable!=false">
			<td ref="csgUserPack" class="" relate="${item.csgUserPack}">
				 ${get:CsUserPack(item.csgUserPack).csupId}	
			</td>
			</s:if>
			
			${lz:set("listable",lz:listable(controller,'csgGoods'))}
			<s:if test="#request.listable!=false">
			<td ref="csgGoods" class="" relate="${item.csgGoods}">
				 ${get:CsGoods(item.csgGoods).csgName}	
			</td>
			</s:if>
			
			${lz:set("listable",lz:listable(controller,'csgOutlets'))}
			<s:if test="#request.listable!=false">
			<td ref="csgOutlets" class="" relate="${item.csgOutlets}">
				 ${get:CsOutlets(item.csgOutlets).csoName}		
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csgModel'))}
			<s:if test="#request.listable!=false">
			<td ref="csgModel" class="" relate="${item.csgModel}">
				 ${get:CsCarModel(item.csgModel).cscmName}	
			</td>
			</s:if>
			
			${lz:set("listable",lz:listable(controller,'csgType'))}
			<s:if test="#request.listable!=false">
			<td ref="csgType" class="">
					 ${item.csgType==0?"数量":""}
					 ${item.csgType==1?"价格":""}
					 ${item.csgType==2?"折扣":""}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csgCount'))}
			<s:if test="#request.listable!=false">
			<td ref="csgCount" class="">
			 				${item.csgCount}
			</td>
			</s:if>
			
			${lz:set("listable",lz:listable(controller,'csgRemain'))}
			<s:if test="#request.listable!=false">
			<td ref="csgRemain" class="">
			 				${item.csgRemain}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csgPrice'))}
			<s:if test="#request.listable!=false">
			<td ref="csgPrice" class="">
			 				${item.csgPrice}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csgRebate'))}
			<s:if test="#request.listable!=false">
			<td ref="csgRebate" class="">
			 				${item.csgRebate}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csgUntilTime'))}
			<s:if test="#request.listable!=false">
			<td ref="csgUntilTime" class="">
				 	 <s:date name="#item.csgUntilTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
			
			<s:if test="#request.all==true">
			${lz:set("listable",lz:listable(controller,'csgUpdateTime'))}
			<s:if test="#request.listable!=false">
			<td ref="csgUpdateTime" class="">
				 	 <s:date name="#item.csgUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
			</s:if>
			<s:if test="#request.all==true">
			${lz:set("listable",lz:listable(controller,'csgAddTime'))}
			<s:if test="#request.listable!=false">
			<td ref="csgAddTime" class="">
				 	 <s:date name="#item.csgAddTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
			</s:if>
			
			${lz:set("listable",lz:listable(controller,'csgStatus'))}
			<s:if test="#request.listable!=false">
			<td ref="csgStatus" class="">
					 ${item.csgStatus==1?"有效":""}
					 ${item.csgStatus==0?"无效":""}
					 ${item.csgStatus==2?"过期":""}
					 ${item.csgStatus==3?"用完":""}
			</td>
			</s:if>
			
			<td>
				${lz:set("permission",lz:permission(controller,'canEdit'))}
				<s:if test="#request.canEdit==true && #request.permission==true">
				<a href="javascript:EditGift('${item.csgId}')">修改</a></s:if>
				${lz:set("permission",lz:permission(controller,'canDel'))}
				<s:if test="#request.canDel==true && #request.permission==true">
				<a href="javascript:DelGift('${item.csgId}','${item.csgId}')">删除</a></s:if>
				${lz:set("permission",lz:permission(controller,'canView'))}
				<s:if test="#request.canView==true && #request.permission==true">
				<a href="javascript:DetailsGift('${item.csgId}')">详情</a></s:if>
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
${lz:set("permission",lz:permission(controller,'canEdit'))}
<s:if test="#request.canEdit==true && #request.permission==true">
	/**
	* 修改优惠内容
	**/
	function EditGift(id){
		var url = "${basePath}admin/user/gift_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:${controller==null?"{title:'修改优惠内容'}":lz:json(controller)}};
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
		EditGift(checker.val());
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
		var url = "${basePath}admin/user/gift_edit.do";
		var params = {entrypoint:"${entrypoint}",ids:ids,controller:{title:"批量更新优惠内容",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.controller["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	/**
	* 修改优惠内容
	**/
	function Update(id,flag)
	{
		var url = "${basePath}admin/user/gift_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"更新优惠内容",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.controller["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};

</s:if>
${lz:set("permission",lz:permission(controller,'canDel'))}
<s:if test="#request.canDel==true && #request.permission==true">
	/**
	* 删除优惠内容
	**/
	function DelGift(id,flag)
	{
		var dialog=$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除优惠内容["+flag+"]吗？</span>",
				title:"删除优惠内容",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}admin/user/gift_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选优惠内容吗？</span>",
				title:"批量删除优惠内容",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}admin/user/gift_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示优惠内容详情
	**/
	function DetailsGift(id)
	{
		var url = "${basePath}admin/user/gift_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"优惠内容详情",editable:false,visible:true}};
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
		DetailsGift(checker.val());
	}
</script>    

</body>
</html>