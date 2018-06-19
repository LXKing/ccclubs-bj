<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>工作流管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}admin/js/define.js"></script>
	
	<!-- 模版解释器 -->
	<script type="text/javascript" src="${basePath}admin/js/lazy3q.template.js"></script>
	
	<!-- JSON引擎 -->
	<script type="text/javascript" src="${basePath}admin/js/json2.js"></script>
			
	<!-- 感谢kindeditor出品的在线HTML编辑控件 -->
	<script type="text/javascript" charset="utf-8" src="${basePath}admin/js/kindeditor/kindeditor.js"></script>
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
</head> 
<style id="tab-css">
.tab{overflow:hidden;}
.tab .btns{width:100%;padding:0px;margin:0px;border-bottom:2px solid #18a0e0;}
.tab .btns li{float:left;margin-left:10px;list-style:none;padding:0px 8px 0px 8px; width:auto;cursor:pointer;text-align:center;font-weight:bold; white-space:nowrap;}
</style>

<script>

var configs=${empty srvWorkflow.swConfig ? "{}" : srvWorkflow.swConfig};

/**
* flash获取流程图信息
**/
function getPaints(){
	return ${empty srvWorkflow.swPaint ? "{}" : srvWorkflow.swPaint};
}
//初始化配置信息列表,为每个图元配置一个空的对象（如果该元没有配置）
for(var o in getPaints())
	if(!configs[o])configs[o]={};
	
//获取流程的输出箭头
function getOutputArrowsByPaintId(id){
	var paints=flash.getPaints();
	var arrows=[];
	for(var o in paints){
		if(paints[o].type!="Arrow")continue;
		if(paints[o].start.paint==id)
			arrows[arrows.length]=paints[o];
	}
	return arrows;
}
//获取流程的输入箭头
function getInputArrowsByPaintId(id){
	var paints=flash.getPaints();
	var arrows=[];
	for(var o in paints){
		if(paints[o].type!="Arrow")continue;
		if(paints[o].finish.paint==id)
			arrows[arrows.length]=paints[o];
	}
	return arrows;
}

function getWorkFlowToolbars(){
	return [
		{name:"保存",action:"saveWorkFlow"},
		{name:"设置",action:"setting"},
		{name:"刷新",action:"reloadWindow"},
		{name:"完成",action:"windowBackford"}
	];
}
function saveWorkFlow(){
	try{
		//JSON.stringify
		var paints=flash.getPaints();
		
		//过滤多余的配置信息
		var temp = {};
		for(var o in paints){		
			for(var p in configs){
				if(o==p)
					temp[o]=configs[p];
			}
		}
		configs=temp;
					
		//验证工作流设置表单
		if($.trim(workflowForm.find("#swName").val())==""){
			setting();
			$.tips("工作流名称不能为空");
			return;
		}
		if(workflowForm.find("#swStatus").val()==""){
			setting();
			$.tips("请选择工作流状态");
			return;
		}
		var data = {
				"srvWorkflow.swId":workflowForm.find("#swId").val(),
				"srvWorkflow.swName":workflowForm.find("#swName").val(),
				"srvWorkflow.swAction":workflowForm.find("#swAction").val(),			
				"srvWorkflow.swPath":workflowForm.find("#swPath").val(),
				"srvWorkflow.swModel":workflowForm.find("#swModel").val(),
				"srvWorkflow.swIcon":workflowForm.find("#swIcon").val(),
				"srvWorkflow.swProfile":workflowForm.find("#swProfile").val(),
				"srvWorkflow.swStatus":workflowForm.find("#swStatus").val(),
				"srvWorkflow.swPaint":JSON.stringify(paints),
				"srvWorkflow.swConfig":JSON.stringify(configs)
			};
			
		$.ajax({
			type:"POST",
			async:false,
			cache:false,
			url:"workflow_save.do",
			data:data,
			dataType:"json",
			success:function(data){
				var strTips = "";
				if(data.message)
					strTips+=data.message+"\n";
				if(data.id)
					workflowForm.find("#swId").val(data.id);
				if(data.warns && data.warns.length>0){
					strTips+="警告信息：\n";
					for(var o in data.warns)
						strTips+="\t"+data.warns[o]+"\n";
				}
				if(strTips!="")
					alert(strTips);
			},
			error:function(){
				alert("保存数据时出错");	
			}
		});
	}catch(ex){
		alert(ex.message);
	}
	
}
function setting(callback){
	top.$(workflowForm).dialog({
		title:"工作流设置",
		modal:true,
		onOk:function(){
			if(callback)
				callback();
		}
	});
}
function windowBackford(){
	window.location='workflow.do?${querys}';
}
function reloadWindow(){
	window.location.reload();
}
function onPaintClick(name,id,type){
	if(workflowForm.find("#swModel").val()==""){
		setting(function(){
			onPaintClick(name,id,type);
		});
		top.$.tips("请选择表模块");
		return;
	}
	if(type=="Process"||type=="Start")
		setProcessConfig(id,type);
	if(type=="Arrow")
		setActionConfig(id,type);	
}
$(function(){
	window["flash"] = (document.embeds["lazy3qWorkFlow"]||document["lazy3qWorkFlow"]||window["lazy3qWorkFlow"]);
});
</script>

<!-- LAZY3Q_LOCK_FLAG -->
<style>body,html{overflow:hidden;}</style>
<body scroll="no" style="overflow:hidden;">

<!-- 流程设计 -->
<object id="lazy3qWorkFlow" name="lazy3qWorkFlow" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="100%" height="100%">
	<param name="movie" value="${basePath}admin/flash/Lazy3qWorkFlow.swf" />
	<param name="quality" value="high"/>
	<param wmode="opaque"/>
	<param name="allowScriptAccess" value="always" />
	<embed id="lazy3qWorkFlow" wmode="opaque" name="lazy3qWorkFlow" src="${basePath}admin/flash/Lazy3qWorkFlow.swf" quality="high" allowScriptAccess="always" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="100%" height="100%"></embed>
</object>

<!-- 工作流设置 -->
<%@ include file="workflow_setting.jsp" %>

<!-- 流程配置 -->
<%@ include file="workflow_process_config.jsp" %>

<!-- 动作设置 -->
<%@ include file="workflow_action_config.jsp" %>

</body>
</html>
