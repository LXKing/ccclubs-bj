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
	display:block;text-align:left;margin:5px;border:0px double #dcdcdc;background:#ededed;
	font-size:14px;cursor:pointer;text-decoration:none;padding:12px 10px;text-indent:20px;
}
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
    		
	    		<a href="order.do" class="bigbutton">
	    		<img src="${basePath}official/images/System_03.png" width="20" height="20" border="0" align="absmiddle" />
	    		订单管理</a>

	    		<a href="car.do" class="bigbutton">
	    		<img src="${basePath}official/images/System_08.png" width="20" height="20" border="0" align="absmiddle" />
	    		车辆管理</a>
	    	
	    		
	    		<a href="map.do" class="bigbutton">
	    		<img src="${basePath}official/images/System_10.png" width="20" height="20" border="0" align="absmiddle" />
	    		车辆监控</a>
	    		
	    	<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==true">
		    	${lz:set("limit",lz:idin(unit_login.csuuLimit,1))}
		    	<s:if test="#request.limit==true">
		    		<a href="fee.do" class="bigbutton">
		    		<img src="${basePath}admin/images/icons/20088256422923477806.png" width="20" height="20" border="0" align="absmiddle" />
		    		价格配置</a>
		    	</s:if>
		    	${lz:set("limit",lz:idin(unit_login.csuuLimit,7))}
		    	<s:if test="#request.limit==true">
		    		<a href="airport.do" class="bigbutton">
		    		<img src="${basePath}admin/images/icons/13667784979046.png" width="20" height="20" border="0" align="absmiddle" />
		    		机场服务</a>
		    	</s:if>
	    	</s:if>
    	</div>
    	<div class="item state-active">系统配置</div>
    	<div class="panel" style="display:block;">
    		${lz:set("limit",lz:idin(unit_login.csuuLimit,1))}
	    	<s:if test="#request.limit==true && #session.unit_login.csuuIsRoot==true">
	    		<a href="dept.do" class="bigbutton">
		    		<img src="${basePath}official/images/System_12.png" width="20" height="20" border="0" align="absmiddle" />
		    		部门层级管理</a>
	    	</s:if>
	    	${lz:set("limit",lz:idin(unit_login.csuuLimit,2))}
	    	<s:if test="#request.limit==true">
	    		<a href="clerk.do" class="bigbutton">
	    		<img src="${basePath}official/images/System_14.png" width="20" height="20" border="0" align="absmiddle" />
	    		人员管理</a>
	    	</s:if>
	    </div>
	    <div class="item">财务对账</div>
    	<div class="panel">
<%-- 	    	${lz:set("limit",lz:idin(unit_login.csuuLimit,4))}
	    	<s:if test="#request.limit==true">
	    		<a href="pack.do" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/20088256422681277805.png" width="20" height="20" border="0" align="absmiddle" />
	    		套餐管理</a>
	    	</s:if>
	    	${lz:set("limit",lz:idin(unit_login.csuuLimit,5))}
	    	<s:if test="#request.limit==true">
	    		<a href="gift.do" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/20088256427828778012.png" width="20" height="20" border="0" align="absmiddle" />
	    		套餐详细</a>
	    	</s:if>
	    	${lz:set("limit",lz:idin(unit_login.csuuLimit,6))}
	    	<s:if test="#request.limit==true">
	    		<a href="record.do" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/Billing.png" width="20" height="20" border="0" align="absmiddle" />
	    		套餐使用</a>
	    	</s:if>
	    	${lz:set("limit",lz:idin(unit_login.csuuLimit,8))} --%>
	    	<s:if test="#request.limit==true">
	    		<a href="financial.do" class="bigbutton">
	    		<img src="${basePath}official/images/System_16.png" width="20" height="20" border="0" align="absmiddle" />
	    		财务对账单</a>
	    	</s:if>
    	</div>
	</td>
  </tr>
</table>


  

<script>

var onResize=function(){
	var hItem=33;
	var iCount=$("#accordion .item").size();
	$("#accordion .panel").css("height",$(window).height()-hItem*iCount-33);
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

<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${lz:config("project_name")}</title> 
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

<link rel="stylesheet" href="css/lanrenzhijia.css" type="text/css">
<script>document.documentElement.className = "js";</script>
<script src="js/jquery-1.9.1.js"></script>
<script src="js/json2.js"></script>
<script src="js/jquery.collapse.js"></script>
<script src="js/jquery.collapse_storage.js"></script>
<script src="js/jquery.collapse_cookie_storage.js"></script>
</head>

<body>
<div class="col c2">
  <!-- END Custom open and close -->
  <!-- BEGIN Showing and hiding with CSS -->
  <h2>菜  单  导  航</h2>
  <div id="css3-animated-example">
    <h3>业务系统</h3>
    <div>
      <div class="content">
      <p> <a href="order.do" >
	    		<img src="${basePath}admin/images/icons/20088256422923477809.png" width="20" height="20" border="0" align="absmiddle" />
	    订单管理</a></p>
	    <p> <a href="order.do" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/20088256422923477809.png" width="20" height="20" border="0" align="absmiddle" />
	    订单管理</a></p>  
	    <p> <a href="order.do" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/20088256422923477809.png" width="20" height="20" border="0" align="absmiddle" />
	    订单管理</a></p>  
	    <p> <a href="order.do" class="bigbutton">
	    		<img src="${basePath}admin/images/icons/20088256422923477809.png" width="20" height="20" border="0" align="absmiddle" />
	    订单管理</a></p>      
      </div>
    </div>
    <h3>Friend</h3>
    <div>
      <div class="content">
        <p>This example simply sets a class attribute to the details and let's an
          external stylesheet toggle the collapsed state.</p>
        <p>Hello Sir.</p>
      </div>
    </div>
    <h3>Foe</h3>
    <div>
      <div class="content">
        <p>This example simply sets a class attribute to the details and let's an
          external stylesheet toggle the collapsed state.</p>
      </div>
    </div>
  </div>
  <script>
        $("#css3-animated-example").collapse({
          accordion: true,
          open: function() {
            this.addClass("open");
            this.css({ height: this.children().outerHeight() });
          },
          close: function() {
            this.css({ height: "0px" });
            this.removeClass("open");
          }
        });
      </script>
</div>
</body>
</html> --%>