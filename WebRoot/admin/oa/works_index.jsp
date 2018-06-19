<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="wf" uri="/workflow-tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>工作任务管理</title>
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

<table border="0" cellspacing="0" cellpadding="0"><tr><td>

<div class="title">${srvWorkflow.swName}</div>

<div class="line"></div>
<div class="content">
	<form class="query" id="queryForm" action="" method="post">
		 		 		 
			<dl>
				<dt>处理时间:</dt>
				<dd>
	 			<input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="swUpdateTimeStart" id="swUpdateTimeStart"  value="<s:date name="#request.swUpdateTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
				起</dd></dl><dl class="query-item"><dt>处理时间:</dt><dd>
				<input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="swUpdateTimeEnd" id="swUpdateTimeEnd"  value="<s:date name="#request.swUpdateTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
				止
				</dd>
			</dl>
		
		 
			<dl>
				<dt>添加时间:</dt>
				<dd>
	 			<input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="swAddTimeStart" id="swAddTimeStart"  value="<s:date name="#request.swAddTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
				起</dd></dl><dl class="query-item"><dt>添加时间:</dt><dd>
				<input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="swAddTimeEnd" id="swAddTimeEnd"  value="<s:date name="#request.swAddTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
				止
				</dd>
			</dl>
		
		 
			<dl>
				<dt>状态:</dt>
				<dd>
		 		<select id="swStatus" name="swStatus" style="width:128px;">
		 			<option value="">全部</option>
						<option value="0" ${swStatus==0?"selected":""}>待处理</option>
						<option value="1" ${swStatus==1?"selected":""}>已处理</option>
		 		</select>
				</dd>
			</dl>
		
			<div class="line"></div>
			<center>
				<input type="hidden" name="all" value="${all}" />
				${lz:set("start",wf:canStart(srvWorkflow.swId))}
				<s:if test="#request.start!=null">
				<button class="button" onclick="javascript:href('${basePath}${start.CTRL.action}','${start.id}',null)" type="button">${start.name}</button>
				</s:if>
				<button class="button" type="submit">查询</button>		
				<button class="button" onclick="clearForm()" type="button">重置</button>
			</center>
		</form>
	</div>
   
	
</td></tr><tr><td>
		
	<div class="content">
		
		<table id="workitem_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td width="40" align="center">
			 	选择
			 </td>
			 
			 <td width=72 ${all?"width":"refer"}="100" title="编号">
			 	<a class="${desc=="sw_id" ? "desc" : ""}${asc=="sw_id" ? "asc" : ""}" href="?${desc=="sw_id" ? "asc=sw_id" : ""}${(asc=="sw_id" || desc!="sw_id" )? "desc=sw_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 </td>
			 
			 
			 <td  ${all?"width":"refer"}="100" title="所属工作流">
			 	<a class="${desc=="sw_workflow" ? "desc" : ""}${asc=="sw_workflow" ? "asc" : ""}" href="?${desc=="sw_workflow" ? "asc=sw_workflow" : ""}${(asc=="sw_workflow" || desc!="sw_workflow" )? "desc=sw_workflow" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属工作流
			 	</a>
			 </td>
			 
			 
			 <td  ${all?"width":"refer"}="200" title="当前节点">
			 	<a class="${desc=="sw_node" ? "desc" : ""}${asc=="sw_node" ? "asc" : ""}" href="?${desc=="sw_node" ? "asc=sw_node" : ""}${(asc=="sw_node" || desc!="sw_node" )? "desc=sw_node" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	当前节点
			 	</a>
			 </td>
			 
			 
			 <td  ${all?"width":"refer"}="100" title="发起人">
			 	<a class="${desc=="sw_source" ? "desc" : ""}${asc=="sw_source" ? "asc" : ""}" href="?${desc=="sw_source" ? "asc=sw_source" : ""}${(asc=="sw_source" || desc!="sw_source" )? "desc=sw_source" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	发起人
			 	</a>
			 </td>
			 
			 <s:if test="#request.all==true">
			 <td  ${all?"width":"refer"}="200" title="历史处理记录">
			 	<a class="${desc=="sw_history" ? "desc" : ""}${asc=="sw_history" ? "asc" : ""}" href="?${desc=="sw_history" ? "asc=sw_history" : ""}${(asc=="sw_history" || desc!="sw_history" )? "desc=sw_history" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	历史处理记录
			 	</a>
			 </td>
			 </s:if>
			 
			 <td  ${all?"width":"refer"}="140" title="处理时间">
			 	<a class="${desc=="sw_update_time" ? "desc" : ""}${asc=="sw_update_time" ? "asc" : ""}" href="?${desc=="sw_update_time" ? "asc=sw_update_time" : ""}${(asc=="sw_update_time" || desc!="sw_update_time" )? "desc=sw_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	处理时间
			 	</a>
			 </td>
			 
			 
			 <td  ${all?"width":"refer"}="140" title="添加时间">
			 	<a class="${desc=="sw_add_time" ? "desc" : ""}${asc=="sw_add_time" ? "asc" : ""}" href="?${desc=="sw_add_time" ? "asc=sw_add_time" : ""}${(asc=="sw_add_time" || desc!="sw_add_time" )? "desc=sw_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 </td>
			 
			 
			 <td  ${all?"width":"refer"}="100" title="状态">
			 	<a class="${desc=="sw_status" ? "desc" : ""}${asc=="sw_status" ? "asc" : ""}" href="?${desc=="sw_status" ? "asc=sw_status" : ""}${(asc=="sw_status" || desc!="sw_status" )? "desc=sw_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 </td>
			 
			<td width="120">&nbsp;
				${lz:set("start",wf:canStart(srvWorkflow.swId))}
				<s:if test="#request.start!=null">
				<a href="javascript:href('${basePath}${start.CTRL.action}','${start.id}',null)" style="text-decoration:underline">${start.name}</a>
				</s:if>
			</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.swId}" >
		  	<td align="center">
				<input class="check" type="checkbox" value="${item.swId}"/>
			</td>
			
			<td class=" node">
					 ${lz:zerofill(item.swId,6)}
			</td>
			
			
			<td class="" title="${item.swWorkflow}">
					<input type='hidden' class='query-flag' name='swWorkflow' value='${lz:zerofill(item.swWorkflow,6)}'/>
				 	 ${lz:get('ccclubs_system','srv_workflow',item.swWorkflow).sw_name}
			</td>
			
			<td class="">
					 ${wf:nodal(srvWorkflow.swId,item.swNode).name}
			</td>
			
			<td class="" title="${item.swSource}">
					<input type='hidden' class='query-flag' name='swSource' value='${lz:zerofill(item.swSource,6)}'/>
				 	 ${lz:get('ccclubs_system','srv_user',item.swSource).su_username}
			</td>
			
			<s:if test="#request.all==true">
			<td class="">
				 	${lz:left(item.swHistory,32)}
			</td>
			</s:if>
			
			<td class="">
					 		<s:date name="#item.swUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			
			
			<td class="">
					 		<s:date name="#item.swAddTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			
			
			<td class="">
				 <s:if test="#request.canEdit==true && #request.CTRL.canEdit==true">
				 	<a href="javascript:Update('${item.swId}','swStatus')">
				 </s:if>
					 ${item.swStatus==0?"待处理":""}
					 ${item.swStatus==1?"已处理":""}
			 	 <s:if test="#request.canEdit==true && #request.CTRL.canEdit==true">
				 	</a>
				  </s:if>
			</td>
			
			<td>&nbsp;
				
			</td>
		  </tr>
		 </s:iterator>
		 </tbody>
	  </table>
	  
  </div>
  <%@include file="/admin/module/page.jsp"%>
  
 </td></tr></table>

<script>

<s:if test="#session.tips.notNull==true">
	$.tips("${tips.value}");//错误提示
</s:if>

	/**
	*	处理流程:地址，工作流ID，流程ID，数据ID
	**/
	function href(url,process,workitem){
		
		var params = {
			entrypoint:'${entrypoint}',			
			ctrl:{
				workflow:${srvWorkflow.swId},
				process:process,
				workitem:(workitem?workitem:""),
				before:[{className:'com.ccclubs.plugin.WorkFlowPlugin',methodName:'before'}],
				after:[{className:'com.ccclubs.plugin.WorkFlowPlugin',methodName:'after'}]
			}
		};
	
		if(${CTRL.mutual=="dialog"})
			top.$.showModalDialog({url:url},params,window);
		else if(${CTRL.mutual=="page"})
			$.submit(url,params,"_self");
		else if(true)
			top.$.showModalDialog({url:url},params,window);
		else
			$.submit(url,params,"_self");	
	}

</script>    
</body>
</html>
