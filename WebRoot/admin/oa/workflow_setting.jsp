<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="wf" uri="/workflow-tags" %>

<script type="text/javascript">
<!--
$(function(){	
	window["workflowForm"]=$("#workflowForm").remove();
});
//-->
</script>


<!-- 工作流属性表单 -->
<div class="form" id="workflowForm" style="width:660px;">
	<input type="hidden" value="${criteria}" id="criteria" name="criteria" />	
	<input type="hidden" value="${srvWorkflow.swId}" name="srvWorkflow.swId" id="swId" />
	<dl>
		<dt>名称:</dt>
		<dd>
		 	<input type="text" class="input" size="20"  maxlength="32" name="srvWorkflow.swName" id="swName"  value="${srvWorkflow.swName}"/>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
	</dl>
	
	<dl >
		<dt>Action:</dt>
		<dd>
		 	<input type="text" class="input" size="20"  maxlength="32" name="srvWorkflow.swAction" id="swAction"  value="${srvWorkflow.swAction}"/>
	 	 
	 	 <b>*</b>
	 	 <em>全小写字母单词，无空格</em>
		</dd>
	</dl>
	
	<dl>
		<dt>功能路径:</dt>
		<dd>
		 	<input type="text" class="input" size="20"  maxlength="64" name="srvWorkflow.swPath" id="swPath"  value="${srvWorkflow.swPath}"/>
	 	 
	 	 
	 	 <em>多个路径以英文逗号分隔</em>
		</dd>
	</dl>
	
	<dl>
		<dt>表模块:</dt>
		<dd>
		 	<select class="narrow" id="swModel" name="srvWorkflow.swModel" style="width:150px;">
		 		<option value="">请选择</option>
		 		${lz:set("models",wf:models())}
		 		<s:iterator value="#request.models" id="model">
		 		<option ${srvWorkflow.swModel==model.name?"selected":""} value="${model.name}">${model.caption}</option>
		 		</s:iterator>
		 	</select>
	 	 	<em></em>
		</dd>
	</dl>
	
	<dl style="width:98%;">
		<dt>图标:</dt>
		<dd>
		    <input type="text" class="input" maxlength="128" size="32" name="srvWorkflow.swIcon" id="swIcon"  value="${srvWorkflow.swIcon}"/>
			<button type="button" onclick="$.upload({type:'img',width:64,height:64,callback:function(url){if(url)$('#srvWorkflow\\.swIcon').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#srvWorkflow\\.swIcon').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#srvWorkflow\\.swIcon').val()})" class="button">查看图片</button>
	 	 
	 	 
	 	 <em></em>
		</dd>
	</dl>	
	<dl style="width:98%;">
		<dt>工作流描述:</dt>
		<dd>
		 	<textarea class="input wide"  id="swProfile" name="srvWorkflow.swProfile" rows="5">${srvWorkflow.swProfile}</textarea>
	 	 	<em></em>
		</dd>
	</dl>
	<dl >
		<dt>状态:</dt>
		<dd>
		 	<select id="swStatus" name="srvWorkflow.swStatus">
				<option value="1" ${srvWorkflow.swStatus==1?"selected":""}>正常</option>
				<option value="0" ${srvWorkflow.swStatus==0?"selected":""}>无效</option>
		 		<option value="">不选择</option>
		 	</select>
	 	 	<b>*</b>
	 	 <em></em>
		</dd>
	</dl>
</div>
