<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>${srvProject.spId!=null?"编辑":"添加"}项目</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
</head>  
<body>

${get:srvlimit("admin/permissions/project.do")}
<s:if test="#request.canView!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>对不起，您没有权限查看当前页面</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>

<script>
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";
$(function(){
	$("#projectForm").form({
		"srvProject.spName":function(el){
			if(el.value.length<2)
				return "项目名称最小长度为2个字符";
			if(el.value.length>16)
				return "项目名称最大长度为16个字符";
		},
		"srvProject.spUrl":function(el){
			if(el.value.length>128)
				return "项目地址最大长度为128个字符";
		},
		"srvProject.spDescript":function(el){
			//var paints = mind.getPaints();
			//el.value = JSON.stringify(paints);
		}
	});
});

function setIcon(url){
	$("#spLogoImage").attr("src",url);
	$("#spLogo").val(url);
}

</script>

<form class="form editform" id="projectForm" name="projectForm" action="project_${srvProject.spId!=null?"update":"save"}.do" method="post"  enctype="multipart/form-data">

<div class="head"></div>
<div class="body">
	<div class="content">
	
		<input type="hidden" value="${srvProject.spId}" name="srvProject.spId" id="spId" />
		<input type="hidden" value="${srvProject.spFamily}" name="srvProject.spFamily" id="spFamily" />
	
		  <dl>
		    <dt>项目名称：</dt>
		    <dd>
				<input class="input" type="text"  maxlength="16" name="srvProject.spName" id="spName"  value="${srvProject.spName}"/>
				<b>*</b>	
			</dd>
		  </dl>
		  <dl>
		    <dt>父级目录：</dt>
		    <dd>
		    	<select id="spParentId" name="srvProject.spParentId">
		    		<option value="0">顶级目录</option>
		    		<s:iterator value="#request.list" id="item">
		    		<option ${item.spId==parent.spId?"selected":""} value="${item.spId}">${item.spName}</option>
		    		</s:iterator>
		    	</select>
			</dd>
		  </dl>
		  <dl style="width:95%;">
		    <dt>页面地址：</dt>
		    <dd>
				<input class="input" type="text"  style="width:80%;"  maxlength="64" name="srvProject.spUrl" id="spUrl"  value="${srvProject.spUrl}"/>
				<b>*</b>
			</dd>
		  </dl>		  
		  <dl style="width:95%;">
				<dt>链接图标:</dt>
				<dd>
					<div id="iconContainer" style="width:48px; height:48px; float:left; margin-right:10px;">
						<img id="spLogoImage" src='${srvProject.spLogo}' width=48 height=48/>
					</div>
					<button class="button" type="button" onclick="top.$.sysicon({jsp:'${basePath}admin/icons.jsp',callback:setIcon});">系统图标</button>
					&nbsp;&nbsp;
					<button class="button" type="button" onclick="$.upload({type:'img',callback:setIcon})">上传图标</button>
					&nbsp;&nbsp;
					<button class="button" type="button" onclick="$('#icon').val('');$('#iconContainer').html('&nbsp;')">删除图标</button>
					<br />
					<input type="hidden" name="srvProject.spLogo" id="spLogo" value="${srvProject.spLogo}"/>
					建议log为透明png图片，48*48像素
					<b>*</b>
				</dd>
			</dl>
		  <dl style="width:95%;">
		    <dt>项目描述：</dt>
		    <dd>
				<textarea class="input" name="srvProject.spDescript" id="spDescript" style="width:100%;height:60px;">${srvProject.spDescript}</textarea> 
			</dd>
		  </dl>
		  <dl>
		    <dt>优先级：</dt>
		    <dd>
				<input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="input" type="text"  maxlength="32" name="srvProject.spLevel" id="spLevel"  value="${srvProject.spLevel}"/>	
			</dd>
		  </dl>
		  <dl>
		    <dt>扩展权限：</dt>
		    <dd>
				<input class="input" type="text"  maxlength="128" name="srvProject.spLimitExp" id="spLimitExp"  value="${srvProject.spLimitExp}"/>	
			</dd>
		  </dl>
		  <dl>
		    <dt>菜单分组：</dt>
		    <dd>
				<input class="input" type="text"  maxlength="32" name="srvProject.spGroup" id="spGroup"  value="${srvProject.spGroup}"/>	
			</dd>
		  </dl>
		  <dl>
		    <dt>项目状态：</dt>
		    <dd>
				&nbsp;&nbsp;&nbsp;&nbsp;
				正常<input type="radio" value="1" name="srvProject.spStatus" id="spStatus" ${srvProject==null || srvProject.spStatus==1?"checked":""}/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				禁用<input type="radio" value="0" name="srvProject.spStatus" id="spStatus" ${srvProject.spStatus==0?"checked":""}/>
				<em>当项目状态为禁用时，将删除级连子项目所有用户或组的权限</em>
			</dd>
		  </dl>
		
		<div class="line"></div>
		
		<center>
			<button class="button" type="submit" value="完成">完成</button>		
			<button class="button" type="reset">重置</button>
			<button class="button" onclick="mind.setFullScreen(true);" type="button">全屏</button>
			<button class="button" onclick="$.closeModalDialog();" type="button">取消</button>
		</center>
	</div>	
</div>
<div class="foot"></div>
	
</form>




</body>
</html>
