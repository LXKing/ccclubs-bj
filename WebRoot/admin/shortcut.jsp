<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>添加快捷方式</title>
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

<style>
	#shortcut .item{ text-decoration:none; float:left; width:72px; text-align:center; display:inline-block; margin:10px; padding:0px; cursor:pointer;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;}
	#shortcut .item img{ width:48px; height:48px;}
	#shortcut .item div{ width:100%; text-align:center; padding-top:10px; padding-bottom:10px;}		
	a img{ border:0px;}
</style>

<%request.setAttribute("now",new java.util.Date());%>

<script>
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";

function SetMenu(){
	var strLink=$("#sysMenus").val();
	if(strLink==""){
		$(".custom").show();
		$("#name").val("链接名称");
		$("#link").val("http://");
		$("#target").val("_blank");
		return;
	}
	else
		$(".custom").hide();
	
	var strName=$("#sysMenus").find("option[value='"+strLink+"']").html();	
	$("#name").val(strName);	
	$("#link").val("javascript:parent.href('"+strLink+"','"+strName+"')");
	$("#target").val("_self");
	SetIcon($("#sysMenus").find("option[value='"+strLink+"']").attr("icon"));
}
/*设置图标*/
function SetIcon(url){
	$('#icon').val(url);
	$('#iconContainer').html("<img src='"+url+"' width=48 height=48/>");
	$("#iconContainer").pngFix();
}

$(function(){	
	//权限密钥数组，对应每个登录用户可操作的项目
	var limits=[];
	<s:iterator  value="#request.limits" id="limit" status="i">
	${lz:set("tempProject",lz:get("ccclubs_system","srv_project",limit.slProjectId))}
	limits["${limit.slProjectId}"]="${lz:limitid(tempProject.sp_url,limit.slLimit)}";
	${lz:set("tempProject",null)}
	</s:iterator>	
	//创建用户可操作的项目菜单
	var projects=[];
	<s:iterator  value="#request.projects" id="project" status="i">
		<s:if test="#project.spParentId!=0 && #project.spStatus==1">
			projects[projects.length]={text:"${project.spName}",icon:"${empty project.spLogo?"images/icons/2008825642250778016.png":project.spLogo}",url:"${project.spUrl=="#"?"":basePath}${project.spUrl}?limitsid="+(limits[${project.spId}]||"")};
		</s:if>
	</s:iterator>
	var selector=$("#sysMenus").get(0);
	for(var o in projects){
		var p=projects[o];
		var option = new Option(p.text,p.url);
		selector.options[selector.options.length]=option;
		$(option).attr("icon",p.icon);		
	}
})

function submitForm(){
	if($("#icon").val()==""){
		return $.tips("请设置图标");
	}
	if($("#name").val()==""){
		return $.tips("请设置链接名称");
	}
	if($("#link").val()==""){
		return $.tips("请设置链接地址");
	}
	$.ajax({
	   type: "get",
	   dataType:"html",
	   url:"shortcut_saveIcon.do",
	   data:$("#shortcutForm").params(),
	   cache:false,
	   async:true,
	   success:function(data){
	   	 caller.location.href=caller.location.href;
	   	 $.closeModalDialog();
	   }
	});	
}

</script>

<body scroll="no" style="overflow:hidden;">
		
	<div id="iconEditor">
		<div style="width:600px;">
			<form class="form editform" id="shortcutForm" name="shortcutForm" action="shortcut_saveIcon.do" method="post"  style="margin:0px; padding:10px 0px 10px 0px;">
			<input type="hidden" name="uuid" id="uuid" value="${lz:uuid()}"/>
			<dl style="width:95%;">
				<dt>链接图标:</dt>
				<dd>
					<div id="iconContainer" style="width:48px; height:48px; float:left; margin-right:10px;"></div>
					<button class="button" type="button" onclick="top.$.sysicon({jsp:'${basePath}admin/icons.jsp',callback:SetIcon});">系统图标</button>
					&nbsp;&nbsp;
					<button class="button" type="button" onclick="$.upload({type:'img',callback:SetIcon})">上传图标</button>
					&nbsp;&nbsp;
					<button class="button" type="button" onclick="$('#icon').val('');$('#iconContainer').html('&nbsp;')">删除图标</button>
					<br />
					<input type="hidden" name="icon" id="icon" value=""/>
					建议log为透明png图片，48*48像素
					<b>*</b>
				</dd>
			</dl>
			<dl style="width:95%;">
				<dt>链接类型:</dt>
				<dd>				
					<select onchange="SetMenu()" id="sysMenus">
						<option value="">--自定义链接--</option>
					</select>
				</dd>
			</dl>
			<dl style="width:95%;">
				<dt>链接名称:</dt>
				<dd>
					<input type="text" class="input" maxlength="6" size="24"  maxlength="12" name="name" id="name"  value=""/>
					<b>*</b>
				</dd>
			</dl>
			<dl style="width:95%;">
				<dt>链接地址:</dt>
				<dd>
					<input type="text" class="input" size="40"  maxlength="256" maxlength="128" name="link" id="link"  value="http://"/>
					<b>*</b>
				</dd>
			</dl>
			<dl class="custom" style="width:95%;">
				<dt>目标窗口:</dt>
				<dd>
					<select id="target" name="target">
						<option value="_blank">新窗口</option>
						<option value="_parent">父窗口</option>
						<option value="_self">当前窗口</option>
					</select>
				</dd>
			</dl>
			
			<div class="line"></div>
			<center>
				<button class="button" onclick="submitForm()" type="button" name="submiter" value="完成">&nbsp;&nbsp;完成&nbsp;&nbsp;</button>					
				<button class="button" type="reset">&nbsp;&nbsp;重置&nbsp;&nbsp;</button>
				<button class="button" onclick="$.closeModalDialog();" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</center>
			
			
			</form>
		</div>	
	</div>
	
</body>
</html>
