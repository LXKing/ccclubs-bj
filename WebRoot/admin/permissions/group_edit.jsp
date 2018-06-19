<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>${srvGroup.sgId!=null?"编辑":"添加"}组</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
</head>  
<body>

${get:srvlimit("admin/permissions/group.do")}
<s:if test="#request.canView!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>对不起，您没有权限查看当前页面</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>

<script>
$(function(){
	$("#groupForm").form({
		"sgName":function(el){
			if(el.value.length<2)
				return "组名称最小长度为2个字符";
			if(el.value.length>16)
				return "组名称最大长度为16个字符";
		}
	});
});
</script>


<form class="form editform" id="groupForm" name="groupForm" action="group_${srvGroup.sgId!=null?"update":"save"}.do" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">
	
		  <div class="prompt">
			  温馨提示：请仔细填写相关信息，<span class="extrude">"*" 为必填选项。</span>
		  </div>
	
		  <input type="hidden" value="${srvGroup.sgId}" name="srvGroup.sgId" id="sgId" />
	
		  <dl>
		    <dt>组名称：</dt>
		    <dd>
				<input class="input" type="text"  maxlength="16" name="srvGroup.sgName" id="sgName"  value="${srvGroup.sgName}"/>
				<b>*</b>
			</dd>
		  </dl>
		  <dl>
		    <dt>组状态：</dt>
		    <dd>				
				正常<input type="radio" value="1" name="srvGroup.sgStatus" id="sgStatus" ${srvGroup==null || srvGroup.sgStatus==1?"checked":""}/>
				&nbsp;&nbsp;
				禁用<input type="radio" value="0" name="srvGroup.sgStatus" id="sgStatus" ${srvGroup.sgStatus==0?"checked":""}/>
				<em>当组状态为禁用时，将删除级连子组所有用户或组的权限</em>
            </dd>
		  </dl>		 
  
  		  <div class="line"></div>
		
		  <center>
			  <button class="button" type="submit" value="完成">完成</button>		
			  <button class="button" type="reset">重置</button>
			  <button class="button" onclick="$.closeModalDialog();" type="button">取消</button>
		  </center>
	</div>	
</div>
<div class="foot"></div>
  
</form>

</body>
</html>
