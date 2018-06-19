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
	
<%-- 	<script type="text/javascript">
		${lz:set("proname","official")}
		window.basePath="${basePath}";
		window.contextPath="${basePath}${proname}/";
		window.apiUrl="${basePath}${proname}/api.do";
		window.handles={};
	</script> --%>
		
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
</head>  
<body>

<form ref="CsUnitPerson" class="query" id="queryForm" action="${basePath}official/clerk.query.do" method="post">
		${lz:set("haveQuery",false)}
	
	 	
	<div class="title">
	<a href="javascript:void(0);" onclick="parent.current.close();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Badge-multiply.png"/>
		关闭页面
	</a>
	<a href="javascript:void(0);" class="linker imize ${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize"))?"minimize":"maximize"}">
		<img align="absmiddle" class="up" src="${basePath}admin/images/icons/Arrow up.png"/>
		<img align="absmiddle" class="down" src="${basePath}admin/images/icons/Arrow down.png"/>
		展开收起
	</a>
	<a href="javascript:void(0);" class="linker setting">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256421529677801.png"/>
		页面设置
	</a>
	<a href="?${lz:querys("UTF-8","xxxxx")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/2008825642250778028.png"/>
		重新加载
	</a>
	<s:if test="#request.CTRL.canExport==true">
	<a href="javascript:void(0);" onclick="exportXls();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256427828778018.png"/>
		数据导出
	</a>
	</s:if>
	<s:if test="#request.alias==null">
		<s:if test="#request.CTRL.canStats==true">
	<a href="order_stats.do?${lz:querys("UTF-8","limitsid")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Statistics.png"/>
		数据统计
	</a>
		</s:if>
	</s:if>
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"人员管理":title}</span>
	
</div>	
			 <!-- ----------------查询相关字段---------------- -->		
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>公务员编号：</dt>
					<dd>

						<a href="javascript:void(0);" title="不包含" class="checkbox ${csupNameNot=="not"?"checked":""}" for="csupNameNot"></a>
						<input class="Not" title="不包含" ${csupNameNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csupNameNot" value="csupName"/>
			 			<input class="input" size="16" type="text" id="csupName" name="csupName" value="${csupName}"/>
								
					 
					</dd>
				</dl>						

			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
	
<%-- 			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>姓名：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoUnitChildNot=="not"?"checked":""}" for="csuoUnitChildNot"></a>
						<input class="Not" title="不包含" ${csuoUnitChildNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoUnitChildNot" value="csuoUnitChild"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/person/person_inquire.do?value={param}" size="16" type="text" id="csuoUnitChild" name="csuoUnitChild" text="${get:CsMember(csuoUnitChild).csmName}" value="${csuoUnitChild}"/>
					</dd>
				</dl>	 --%>					
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
				
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>所属部门：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoStateNot=="not"?"checked":""}" for="csUnitGroupNot"></a>
						<input class="Not" title="不包含" ${csuoStateNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csUnitGroupNot" value="csUnitGroup"/>
			 		<select id="csUnitGroup" name="csUnitGroup" >
			 			<option value="">全部</option>			 					 					 		
			 			<s:iterator value="#request.csUnitGroups" id="item" status="csUnitGroup">
			 				<option value="${item.csugId}" ${item.csugId==groupId?"selected":""}>${item.csugName}</option>
						</s:iterator>
			 		</select>
															
					 
					</dd>
				</dl>						
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 
				<dl>
					<dt>使用人手机：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csmMobileNot=="not"?"checked":""}" for="csmMobileNot"></a>
						<input class="Not" title="不包含" ${csmMobileNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csmMobileNot" value="csmMobile"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="csmMobile" id="csmMobile"  value="${csmMobile}"/>
				 
					</dd>
				</dl>
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->


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
					<s:if test="#request.alias=='join'">
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

<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td>

	
</td></tr><tr><td>
  <%@include file="/admin/module/page.jsp"%>
  	
  			
	<div class="content">
	    <div class="toolbar">

			<button type="button" class="del" onclick="DelSel();">
				删除
			</button>

			
			<button type="button" class="add" onclick="AddClerk();">
				添加
			</button>							
			<button type="button" class="all" onclick="$('.check').attr('checked',true)">
				全选
			</button>
			<button type="button" class="invert" onclick="$('.check').click();">
				反选
			</button>		
		</div>
		
		<table id="clerk_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td width="40" align="center">
			 	选择
			 </td>
			 
			
			 <td width=72 ${all?"width":"refer"}="120" title="编号">
			 	<a class="${desc=="csup_id" ? "desc" : ""}${asc=="csup_id" ? "asc" : ""}" href="?${desc=="csup_id" ? "asc=csug_id" : ""}${(asc=="csup_id" || desc!="csup_id" )? "desc=csup_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 </td>
			 
			 
			 <td width=72 ${all?"width":"refer"}="120" title="公务员编号">
			 	<a class="${desc=="csup_name" ? "desc" : ""}${asc=="csup_name" ? "asc" : ""}" href="?${desc=="csup_name" ? "asc=csug_id" : ""}${(asc=="csup_name" || desc!="csup_name" )? "desc=csup_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	公务员编号
			 	</a>
			 </td>
						 			 
		
			 <td  ${all?"width":"refer"}="200" title="姓名">			 	
			 	姓名			
			 </td>
			 
			  <td  ${all?"width":"refer"}="200" title="性别">			 	
			 	性别			
			 </td>
			 
			 
			 <td  ${all?"width":"refer"}="120" title="所属部门">
			 	<a class="${desc=="csup_group" ? "desc" : ""}${asc=="csup_group" ? "asc" : ""}" href="?${desc=="csup_group" ? "asc=csup_group" : ""}${(asc=="csup_group" || desc!="csup_group" )? "desc=csup_group" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属部门
			 	</a>
			 </td>			 			 			 			
			
			 <td  ${all?"width":"refer"}="120" title="所属单位">
			 	<a class="${desc=="csup_info" ? "desc" : ""}${asc=="csup_info" ? "asc" : ""}" href="?${desc=="csup_info" ? "asc=csup_info" : ""}${(asc=="csup_info" || desc!="csup_info" )? "desc=csup_info" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属单位
			 	</a>
			 </td>
			 			 
			 
			 <td  ${all?"width":"refer"}="200" title="城市">
			 	<a class="${desc=="csup_host" ? "desc" : ""}${asc=="csup_host" ? "asc" : ""}" href="?${desc=="csup_host" ? "asc=csup_host" : ""}${(asc=="csup_host" || desc!="csup_host" )? "desc=csup_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 </td>
			 
			<td  ${all?"width":"refer"}="200" title="手机号码">			 	
			 	手机号码			 	
			 </td>			 			 
			
<!-- 			 <td  ${all?"width":"refer"}="200" title="证件类型">			 	
			 	证件类型			 	
			 </td>
			 
			 <td  ${all?"width":"refer"}="200" title="证件号码">			 	
			 	证件号码			 	
			 </td>
			 
			 <td  ${all?"width":"refer"}="200" title="证件图片">			 	
			 	证件图片			 	
			 </td> -->
			 
			<!--  <td  ${all?"width":"refer"}="200" title="驾照号码">			 	
			 	驾照号码			 	
			 </td>
			 
			 <td  ${all?"width":"refer"}="200" title="驾照图片">			 	
			 	驾照图片			 	
			 </td>	 -->							
			
			 <td  ${all?"width":"refer"}="200" title="添加时间">
			 	<a class="${desc=="csup_add_time" ? "desc" : ""}${asc=="csup_add_time" ? "asc" : ""}" href="?${desc=="csup_add_time" ? "asc=csup_add_time" : ""}${(asc=="csup_add_time" || desc!="csup_add_time" )? "desc=csup_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 </td>
			 
			 
			 <td  ${all?"width":"refer"}="140" title="叫车权限">
			 	<a class="${desc=="csup_flag" ? "desc" : ""}${asc=="csup_flag" ? "asc" : ""}" href="?${desc=="csup_flag" ? "asc=csup_flag" : ""}${(asc=="csup_flag" || desc!="csup_flag" )? "desc=csup_flag" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	叫车权限
			 	</a>
			 </td>

			<td  ${all?"width":"refer"}="140" title="公务出行审批权限">
			 	<a class="${desc=="csup_flag" ? "desc" : ""}${asc=="csup_flag" ? "asc" : ""}" href="?${desc=="csup_flag" ? "asc=csup_flag" : ""}${(asc=="csup_flag" || desc!="csup_flag" )? "desc=csup_flag" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	公务出行审批权限
			 	</a>
			 </td>
			 
			 <td  ${all?"width":"refer"}="140" title="状态">
			 	<a class="${desc=="csup_status" ? "desc" : ""}${asc=="csup_status" ? "asc" : ""}" href="?${desc=="csup_status" ? "asc=csup_status" : ""}${(asc=="csup_status" || desc!="csup_status" )? "desc=csup_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 </td>
	
			 
			<td width="170">&nbsp;
				<a href="javascript:AddClerk()" style="text-decoration:underline">添加</a>
			</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csupId}" >
		  	<td align="center">
				<input class="check" type="checkbox" value="${item.csupId}"/>
			</td>

			<td ref="csupId" class="">
					 ${lz:zerofill(item.csupId,6)}
			</td>
			
			<td ref="csupName" class="">
					 ${item.csupName}
			</td>
							
			<td ref="csmName" class="">
					 ${item.$csupMember.csmName}
			</td>
			
			<td ref="csmiSex" class="">
					 ${item.$csupMember.$csmInfo.csmiSex$}
			</td>
			
			<td ref="csupGroup" class="">
				 	${item.csupGroup$}
			</td>
		
			<td ref="csupInfo" class="">
					 ${item.csupInfo$}
			</td>
			
			<td ref="csugHost" class="">
					 ${item.csupHost$}
			</td>

			<td ref="csmMobile" class="">
				${item.$csupMember.csmMobile$}
			</td>
			
<%-- 			<td ref="csmiCertifyImage" class="">				
				<!-- <a class="modify" href="javascript:Update('${item.$csupMember.$csmInfo.csmiCertifyType$}','status')"></a>
				${item.$csupMember.$csmInfo.csmiCertifyType$==1?"身份证":""}
				${item.$csupMember.$csmInfo.csmiCertifyType$==5?"港澳通行证":""}
				${item.$csupMember.$csmInfo.csmiCertifyType$==7?"公务员证":""} -->
				${item.$csupMember.$csmInfo.csmiCertifyType$}
			</td>
			
			<td ref="csmiCertifyNum" class="">				
				${item.$csupMember.$csmInfo.csmiCertifyNum$}
			</td>
			
			<td ref="csmiCertifyImage" class="">
				${item.$csupMember.$csmInfo.csmiCertifyImage$}
			</td> --%>
			
<%-- 			<td ref="csmiCertifyImage" class="">
				${item.$csupMember.$csmInfo.csmiDriverNum$}
			</td>
			
			<td ref="csmiCertifyImage" class="">
				${item.$csupMember.$csmInfo.csmiDriverImage$}
			</td> --%>
									
			<td ref="csupAddTime" class="">
				<s:date name="#item.csupAddTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			
			<td ref="csupFlag" class="">				
				<a class="modify" href="javascript:Update('${item.csupId}','csupFlag')"></a>
				<s:generator separator="," val="#item.csupFlag" id="flags">
					<s:iterator value="#flags" id="flag">
						<s:if test="#flag==1">[叫车]</s:if>
						<s:if test="#flag==2">[约租车]</s:if>
					</s:iterator>
				</s:generator>
				
			</td>
			
			<td ref="csupFlag" class="">				
				<a class="modify" href="javascript:Update('${item.csupId}','csupFlag')"></a>
				<s:generator separator="," val="#item.csupFlag" id="flags">
					<s:iterator value="#flags" id="flag">
						<s:if test="#flag==3">[无需审批]</s:if>
						<s:if test="#flag==4">[需要审批]</s:if>
					</s:iterator>
				</s:generator>
				
			</td>
			
			<td ref="csupStatus" class="">				
				<a class="modify" href="javascript:Update('${item.csupId}','status')"></a>
				${item.csupStatus==1?"正常":""}
				${item.csupStatus==0?"无效":""}
			</td>

			
			<td>				
				<a href="javascript:EditClerk('${item.csupId}')">修改</a>				
				<a href="javascript:DelClerk('${item.csupId}','${item.csupName}')">删除</a>				
				<a href="javascript:ResetClerk('${item.csupId}')">重置密码</a>
				<a href="javascript:DetailsClerk('${item.csupId}')">详情</a>
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
	* 添加人员信息
	**/
	function AddClerk(parent){		
		var url = "${basePath}official/clerk.edit.do";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),controller:${controller==null?"{title:'添加人员信息'}":lz:json(controller)}};
		href(url,params);
	}
	/**
	* 修改人员信息
	**/
	function EditClerk(id){
		var url = "${basePath}official/clerk.edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:${controller==null?"{title:'修改人员信息'}":lz:json(controller)}};
		href(url,params);
	}
	
		/**
	* 删除企业管理员
	**/
	function DelClerk(id,flag)
	{
		var dialog=$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除人员信息["+flag+"]吗？</span>",
				title:"删除人员信息",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}official/clerk.del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);
				}
		 });	
	}
	
	/**
	*重置用户密码
	**/
	
	function ResetClerk(id){
		var dialog=$.dialog({
				html:"<span style='white-space:nowrap;'>确定要重置员工密吗？</span>",
				title:"重置密码",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}official/clerk.resetPassword.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选人员信息吗？</span>",
				title:"批量删除人员信息",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}official/clerk.del.do";
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
		var url = "${basePath}official/clerk.edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"更新人员信息",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.controller["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	/**
	* 显示企业管理员详情
	**/
	function DetailsClerk(id)
	{
		var url = "${basePath}official/clerk.details.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"人员信息详情",editable:false,visible:true}};
		href(url,params);
	}

</script>    
</body>
</html>