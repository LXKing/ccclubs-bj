<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>数据导出</title>
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

<style>
.page{position:fixed;width:100%;height:100%;margin:0px;overflow:hidden;}
#navigator{background:#ffffff;width:220px;position:fixed;height:100%;top:0px;left:0px;overflow:scroll;}
.content{margin:0px;margin-left:220px;height:800px;width:640px;}
.exportbtn{margin:10px 8px;height:40px;width:100px;}
</style>

<body srcoll="no">
	<form name="exportForm" id="exportForm" method="post" target="_blank">
	<div id="navigator">
		<table id="classTree" width="202" class="table" border="0" cellspacing="1" cellpadding="0">
			<thead class="state-header">
				<tr>
					<td>
						<a id="clean" style="font-weight:normal;float:right;margin-right:20px;" href="javascript:void(0);" onclick="uncheckbox();">X</a>
						导出结构							
					</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.fields" id="item" status="i">
				<tr id="${item.name}" tree="${empty item.parent ? "0" : item.parent}">
			  		<td class="node">
			  			<input name="keys" ${(lz:indexOf(checkeds,item.name)>-1) ? "checked='checked'" : "" } type="checkbox" value="${item.name}" />
			  			${item.caption}
			  		</td>
			  	</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<div class="content">
		<div class="plate" style="margin-top:10px;border-top:1px solid #dddddd;">
			<center style="line-height:25px;font-weight:bold;">
				下载需要排队，如果下载过程中出现卡的现象，请耐心等待。 
			</center>
		</div>
		<br/>
		<center>
			<button type="button" onclick="exportData(5000)" class="exportbtn">导出文件</button>
	 	</center>
	</div>
	</form>
</body>
<script>
var onSize = function(){
	$("#navigator").height($(window).height());
}
$(function(){	
	$("#classTree").treegrid({expand:true});
	onSize();
	$(window).resize(onSize);
	$("#clean").say("这儿可以清空勾选哦");
});
function exportData(size){
	var exporturl="${exporturl}&className=${className }&size="+size;
	$("#exportForm").attr("action",exporturl.replace("{page}",0));
	$("#exportForm").submit();
}
function uncheckbox(){
	$("input[name='keys']").each(function(){
	   $(this).attr("checked",false);
	}); 
}
</script>
</html>
