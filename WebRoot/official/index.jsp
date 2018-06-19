<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<title>企业用车服务平台</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
<!-- 用户自定义js(懒加载) -->
<script type="text/javascript" src="${basePath}admin/js/define.js"></script>

<body scroll="no" style="overflow:hidden;">
	<iframe id="indexframe" name="indexframe" frameborder="0" width="100%" height="100%" src="index.frame.do"></iframe>
</body>
<script>
/**
* 风格主题选择
**/
function themes()
{		
	//(同步方式)ajax取风格主题选择页面的html代码
	//什么叫同步方式？是指当ajax数据取回来后，代码才执行下一步
	var html = $.ajax({
	  	url: "themes.jsp?type=list", //网址
	  	cache: false,//是否缓存数据
	  	async: false//是否异步
	}).responseText;
	//把取回来的html显示到一个对话框上面
	var dialog=$(html).dialog({
			title:"风格主题选择",//对话框标题
			modal: true	,//是否模式对话框
			onOk:function() {
				parent.window.location='index.style.do?type=select&theme='+$("#themes_select").val()+'&css='+$("#styles_select").val();
				dialog.close();
			},
			onCancel: function() {
				dialog.close();
			}	
	});
}
</script>
</html>