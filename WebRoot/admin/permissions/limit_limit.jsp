<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>权限设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
</head> 
 
<body>

${get:srvlimit("admin/permissions/limit.do")}
<s:if test="#request.canView!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>对不起，您没有权限查看当前页面</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>

<div id="limit" style="left:0px; top:0px; width:100%;"></div>

<div style="left:0px; height:50px; width:100%;">
	<div style="margin:3px;">
		<form name="limitForm" id="limitForm" action="limit_update.do" method="post" style="margin:0px;">	
		<input type="hidden" name="actortype" id="actortype" value="${actortype}" />
		<input type="hidden" name="actorid" id="actorid" value="${actorid}" />
		<input type="hidden" name="limit_list" id="limit_list" value="" />
		<button type="button" class="button ui-state-default" onclick="SubmitLimit()" style="margin:10px; float:right;">保存修改</button>
		</form>
		<button class="button" onclick="ImportLimit()" style="margin:10px; float:right;">导入权限</button>
		<button class="button" onclick="ClearLimit()" style="margin:10px; float:right;">清空权限</button>
		<button class="button" onclick="SelectAll()"  style="margin:10px; float:right;">全&nbsp;&nbsp;选</button>
		<div style="margin:10px; float:left;">
			<s:if test="#request.actortype==1">
				<strong>用户：</strong>
				${target.suUsername}
			</s:if>
			<s:else>
				<strong>组：</strong>
				${target.sgName}
			</s:else>			
		</div>
	</div>
</div>


<script>
var bytes=[64,128,256,512,1024,2048,4096,8192,16384,32768,65536];

var limitGrid=$("#limit").grid({
	column:[["项目名称",150],["查看",40],["增加",40],["修改",40],["删除",40],["导出",40],["分析",40],["&nbsp;&nbsp;",500]],
	height:$(window).height()-50
});
var projects=[];
<s:iterator value="#request.projects" id="item" status="i">
projects[${item.spId}]=(projects[${item.spParentId}]?projects[${item.spParentId}]:limitGrid).add({data:["${item.spName}","","","","",""],expand:true});
projects[${item.spId}].limitExp="${item.spLimitExp}";
projects[${item.spId}].exps=[];
</s:iterator>

/*****************根据设置者的权限创建checkbox******************/
var limits=[];
<s:iterator  value="#request.limits" id="item" status="i">
limits["${item.slProjectId}"]=${item.slLimit};
</s:iterator>
for(var o in projects)
{
	var bDisabled;
	
	bDisabled=( (limits[o] && ((limits[o] & 1)==1)) || ${EXTEND_BY_SUPER==true?"true":"false"});
	var can_view=projects[o].can_view=$("<input onclick='OnCheck(this)' type='checkbox' id='view_"+o+"' name='view_"+o+"' value='1' "+(bDisabled?"":"disabled")+"/>").get(0);
	can_view.project=projects[o];
	projects[o].tds[1].get(0).appendChild(can_view);
	
	bDisabled=( (limits[o] && ((limits[o] & 2)==2)) || ${EXTEND_BY_SUPER==true?"true":"false"});	
	var can_add=projects[o].can_add=$("<input onclick='OnCheck(this)' type='checkbox' id='add_"+o+"' name='add_"+o+"' value='2' "+(bDisabled?"":"disabled")+"/>").get(0);
	can_add.project=projects[o];
	projects[o].tds[2].get(0).appendChild(can_add);
	
	bDisabled=( (limits[o] && ((limits[o] & 4)==4)) || ${EXTEND_BY_SUPER==true?"true":"false"});	
	var can_edit=projects[o].can_edit=$("<input onclick='OnCheck(this)' type='checkbox' id='edit_"+o+"' name='edit_"+o+"' value='4' "+(bDisabled?"":"disabled")+"/>").get(0);
	can_edit.project=projects[o];
	projects[o].tds[3].get(0).appendChild(can_edit);
	
	bDisabled=( (limits[o] && ((limits[o] & 8)==8)) || ${EXTEND_BY_SUPER==true?"true":"false"});
	var can_del=projects[o].can_del=$("<input onclick='OnCheck(this)' type='checkbox' id='del_"+o+"' name='del_"+o+"' value='8' "+(bDisabled?"":"disabled")+"/>").get(0);
	can_del.project=projects[o];
	projects[o].tds[4].get(0).appendChild(can_del);
	
	
	bDisabled=( (limits[o] && ((limits[o] & 16)==16)) || ${EXTEND_BY_SUPER==true?"true":"false"});
	var can_export=projects[o].can_export=$("<input onclick='OnCheck(this)' type='checkbox' id='export_"+o+"' name='export_"+o+"' value='16' "+(bDisabled?"":"disabled")+"/>").get(0);
	can_export.project=projects[o];
	projects[o].tds[5].get(0).appendChild(can_export);
	
	
	bDisabled=( (limits[o] && ((limits[o] & 32)==32)) || ${EXTEND_BY_SUPER==true?"true":"false"});
	var can_stats=projects[o].can_stats=$("<input onclick='OnCheck(this)' type='checkbox' id='stats_"+o+"' name='stats_"+o+"' value='32' "+(bDisabled?"":"disabled")+"/>").get(0);
	can_stats.project=projects[o];
	projects[o].tds[6].get(0).appendChild(can_stats);
	
	
	if(projects[o].limitExp!=""){
		projects[o].exps=[];
		var array = projects[o].limitExp.split(",");
		for(var i=0;i<array.length;i++){
			bDisabled=( (limits[o] && ((limits[o] & bytes[i])==bytes[i])) || ${EXTEND_BY_SUPER==true?"true":"false"});
			var can_exp=projects[o].exps[projects[o].exps.length]=$("<input onclick='OnCheck(this)' type='checkbox' id='exp_"+o+"_"+i+"' name='exp_"+o+"_"+i+"' value='"+bytes[i]+"' "+(bDisabled?"":"disabled")+"/>").get(0);
			can_exp.project=projects[o];
			if(i==7)projects[o].tds[7].get(0).appendChild($("<br/>").get(0));
			projects[o].tds[7].get(0).appendChild(can_exp);
			projects[o].tds[7].get(0).appendChild($("<span>"+array[i]+"</span>").get(0));
		}
	}
}

/*****************根据被设置者的权限加载权限******************/
var limiteds=new Array();
var project;
<s:iterator  value="#request.limiteds" id="limited" status="i">
project=projects[${limited.slProjectId}];
if(project)
{
	project.can_view.checked=(${limited.slLimit} & 1) ==1;
	project.can_add.checked=(${limited.slLimit} & 2) ==2;
	project.can_edit.checked=(${limited.slLimit} & 4) ==4;
	project.can_del.checked=(${limited.slLimit} & 8) ==8;
	project.can_export.checked=(${limited.slLimit} & 16) ==16;
	project.can_stats.checked=(${limited.slLimit} & 32) ==32;
	for(var i=0;i<bytes.length;i++){
		if(project.exps[i])
			project.exps[i].checked=(${limited.slLimit} & bytes[i]) ==bytes[i];
	}
	project.limitId=${limited.slId};
}
</s:iterator>


function OnCheck(check)
{		
	if(check.checked)//如果当前被选中，则查看权限也应该被选中，级连所有父节点的查看权限也被选中
	{
		/*var project=check.project;
		while(project){
			project.can_view.checked=true;
			project=project.onwer;
		}*/			
	}
	else if(check.value==1)//否则查看权限未被选中时，级连所有子节点的所有权限都不被选中
	{
		NotCheckAll(check.project);			
	}
}
//级连所有子节点的所有权限都不被选中
function NotCheckAll(project)
{
	if(!project)
		return;
	project.can_view.checked=false;
	project.can_add.checked=false;	
	project.can_edit.checked=false;
	project.can_del.checked=false;
	project.can_export.checked=false;
	project.can_stats.checked=false;
	for(var i=0;i<bytes.length;i++){
		if(project.exps[i])
			project.exps[i].checked=false;
	}
	for(var o in project.childs)
		NotCheckAll(project.childs[o]);
}


<lz:set name="importDialog">
<table id="importLimit" width="*" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="60" align="right">请选择：</td>
    <td width="*">
		<select class="combox"  action="${basePath}admin/permissions/user_tree.do?group=true" id="actor" name="actor"><option value=""></option></select>
	</td>
  </tr>
</table>
</lz:set>
function ImportLimit(){
	var dialog = top.$("${lz:js(importDialog)}").dialog({
		modal:true,
		title:"请选择组或用户",
		onOk:function(){
			var actorid = top.$("#actor").val();
			$.getObject("${basePath}admin/permissions/limit_ajax.do",{
				actorid:actorid
			},function(data){
				if(!data.success){
					top.Alert(data.message);
				}else{
					ClearLimit();
					var limits = data.limits;
					for(var o in limits){
						var limit = limits[o];
						var project=projects[limit.slProjectId];
						if(project){
							project.can_view.checked=(limit.slLimit & 1) ==1;
							project.can_add.checked=(limit.slLimit & 2) ==2;
							project.can_edit.checked=(limit.slLimit & 4) ==4;
							project.can_del.checked=(limit.slLimit & 8) ==8;
							project.can_export.checked=(limit.slLimit & 16) ==16;
							project.can_stats.checked=(limit.slLimit & 32) ==32;
							for(var i=0;i<bytes.length;i++){
								if(project.exps[i])
									project.exps[i].checked=(limit.slLimit & bytes[i]) ==bytes[i];
							}
						}
					}
				}
			});
		}
	});
	dialog.find(".combox").combox();
	dialog.find("#importLimit").parent().css("overflow","visible");
}


//清空
function ClearLimit()
{
	for(var o in projects)
	{
		var project=projects[o];
		project.can_view.checked=false;
		project.can_add.checked=false;
		project.can_del.checked=false;
		project.can_edit.checked=false;
		project.can_export.checked=false;
		project.can_stats.checked=false;
		for(var i=0;i<bytes.length;i++){
			if(project.exps[i])
				project.exps[i].checked=false;
		}
	}	
}

//全选
function SelectAll()
{
	for(var o in projects)
	{
		var project=projects[o];
		if(!project.can_view.disabled)
			project.can_view.checked=true;	
		if(!project.can_add.disabled)
			project.can_add.checked=true;
		if(!project.can_del.disabled)
			project.can_del.checked=true;
		if(!project.can_edit.disabled)
			project.can_edit.checked=true;
		if(!project.can_export.disabled)
			project.can_export.checked=true;
		if(!project.can_stats.disabled)
			project.can_stats.checked=true;
		for(var i=0;i<bytes.length;i++){
			if(project.exps[i] && !project.exps[i].disabled)
				project.exps[i].checked=true;
		}
	}
}

//提交权限
function SubmitLimit()
{
	var strLimitList="";
	for(var o in projects)
	{
		if(strLimitList!="")
			strLimitList+=",";
			
		var project=projects[o];		

		if(project.limitId)
			strLimitList+=project.limitId;
		else
			strLimitList+="0";
		strLimitList+="|";
		strLimitList+=o;
		strLimitList+="|";
		
		var limitValue=0;
		if(project.can_view.checked)
			limitValue|=1;
		if(project.can_add.checked)
			limitValue|=2;
		if(project.can_edit.checked)
			limitValue|=4;
		if(project.can_del.checked)
			limitValue|=8;
		if(project.can_export.checked)
			limitValue|=16;
		if(project.can_stats.checked)
			limitValue|=32;
			
		for(var i=0;i<bytes.length;i++){
			if(project.exps[i] && project.exps[i].checked)
				limitValue|=bytes[i];
		}
		
		strLimitList+=limitValue;
	}
		
	$("#limit_list").val(strLimitList);	
	$("#limitForm").submit();
}

<s:if test="#request.USER_GROUP_LIMIT">
	$.tips("当前用户使用的是所属组的权限。");
</s:if>

<s:if test="#session.tips.notNull==true">
//错误提示
showTips("${tips.value}");	
</s:if>
</script>    
</body>
</html>
