<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${lz:config("project_name")}</title> 
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
<!-- 用户自定义js(懒加载) -->
<script type="text/javascript" src="${basePath}admin/js/define.js"></script>
<style>
.bigbutton{
	display:block;text-align:left;margin:8px;border:0px double #dcdcdc;background:#ededed;
	font-size:14px;cursor:pointer;text-decoration:none;padding:12px 10px;text-indent:20px;
}
#accordion .item{height:36px;line-height:36px;font-size:14px;}
#accordion .panel{background:#f2f2f2;}
.bigbutton img{width:25px;height:25px;border:0px;margin:0px 10px;margin-left:-20px;} 
</style>
<body scroll="no" style="overflow:hidden;">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr height="32">
    <td>
		<table id="navigate" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="120" class="state-focus" align="center" style="height:32px;">菜单导航</td>
			<td width="32" class="c">&nbsp;</td>
			<td width="*" class="state-title" style="height:32px;">&nbsp;</td>
		  </tr>
	    </table>
	</td>
  </tr> 
  <tr height="*">
    <td id="accordion" valign="top">    	
	    <div class="item">系统业务</div>
    	<div class="panel">
    		${lz:set("limit",lz:idin(unit_login.csuuLimit,0))}
	    	<s:if test="#request.limit==true">
	    		<a href="order.do" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/20088256422923477809.png" width="20" height="20" border="0" align="absmiddle" />
	    		订单管理</a>
				<a href="#" onclick="top.$.open('${basePath}unit/order.edit.do',{controller:{title:'公务用车'}});" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/Badge-plus.png" width="20" height="20" border="0" align="absmiddle" />
	    		${unit_login.$csuuUnitInfo.csuiType<2?"内部用车":"公务用车"}</a>
	    	</s:if>
    		${lz:set("limit",lz:idin(unit_login.csuuLimit,1))}
	    	<s:if test="#request.limit==true">
	    		<a href="car.do" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/200882564229234778010.png" width="20" height="20" border="0" align="absmiddle" />
	    		车辆管理</a>
	    	</s:if>
    		${lz:set("limit",lz:idin(unit_login.csuuLimit,2))}
	    	<s:if test="#request.limit==true">
	    		<a href="map.do" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/GPS.png" width="20" height="20" border="0" align="absmiddle" />
	    		车辆监控</a>
	    	</s:if>
    	</div>
    	<div class="item state-active">系统配置</div>
    	<div class="panel" style="display:block;">
    		${lz:set("limit",lz:idin(unit_login.csuuLimit,3))}
	    	<s:if test="#request.limit==true && #session.unit_login.csuuIsRoot==true">
	    		<a href="user.do" class="bigbutton">
		    		<img src="${basePath}admin/images/icons/User.png" width="20" height="20" border="0" align="absmiddle" />
		    		权限管理</a>
	    	</s:if>
	    	<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==1">
		    	${lz:set("limit",lz:idin(unit_login.csuuLimit,4))}
		    	<s:if test="#request.limit==true">
		    		<a href="fee.do" class="bigbutton">
		    		<img src="${basePath}admin/images/icons/20088256422923477806.png" width="20" height="20" border="0" align="absmiddle" />
		    		价格配置</a>
		    	</s:if>
	    	</s:if>
	    	${lz:set("limit",lz:idin(unit_login.csuuLimit,5))}
	    	<s:if test="#request.limit==true">
	    		<a href="group.do" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/20088256422923477804.png" width="20" height="20" border="0" align="absmiddle" />
	    		部门分组</a>
	    	</s:if>
	    	${lz:set("limit",lz:idin(unit_login.csuuLimit,6))}
	    	<s:if test="#request.limit==true">
	    		<a href="person.do" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/20088256422923477804.png" width="20" height="20" border="0" align="absmiddle" />
	    		用车帐号</a>
	    	</s:if>
	    </div>
	    <div class="item">财务对账</div>
	    <div class="panel">
	    	${lz:set("limit",lz:idin(unit_login.csuuLimit,7))}
	    	<s:if test="#request.limit==true">
	    		<a href="bill.do" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/Billing.png" width="20" height="20" border="0" align="absmiddle" />
	    		对账单</a>
	    	</s:if>
    	</div>
	    
	</td>
  </tr>
</table>


  

<script>

var onResize=function(){
	var hItem=37;
	var iCount=$("#accordion .item").size();
	$("#accordion .panel").css("height",$(window).height()-hItem*iCount-hItem);
}

/*实现抽屉盒功能*/
$(function(){
	
	$("#accordion a").click(function(){
		parent.href($(this).attr("href"),$(this).html());
		return false;
	});

	$("#accordion .item").click(function(){
		$("#accordion .panel").hide();
		$(this).next().show();
		$("#accordion .item").attr("class","item state-header");
		$(this).attr("class","item state-active");  
	});	
	
	$("#accordion .panel").each(function(){
		if($.trim($(this).text())=="")
			$(this).add($(this).prev()).remove(); 
	});
	
	onResize();	
	$(window).resize(onResize);
	$(document).resize(onResize);
	$("#accordion .item:eq(0)").click();			
});

function link(flag,params){
	var link=$("#accordion a[href*='"+flag+"']").attr("href");
	eval(link);
}

</script>

</body>
</html>