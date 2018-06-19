 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
<body scroll="no" style="overflow:hidden;">

<table id="right-panel" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr height="32">
		<td class="state-title">
			<div id="tabframe">
				<table id="tabs" border="0" cellspacing="3" cellpadding="0">
				  <tr id="tabstr"></tr>
				</table>
			</div>		
			<div id="tabmore">
				<button class="button" type="button" id="toleft"></button>
				<button class="button" type="button" id="toright"></button>
			</div>	
		</td>
	</tr>
	<tr height="*">
		<td id="iframes" align="center" valign="middle">
			<!--<iframe id="welcome" class="iframe" frameborder="0" src="example.html"></iframe> -->
		</td>
	</tr>
</table>

<script>
//初始化
var speed=20;
var timer=0;
var hrefs=[];
var current;
var toLeft=function(){
	$("#tabframe").scrollLeft($("#tabframe").scrollLeft()-speed);
}
var toRight=function(){
	$("#tabframe").scrollLeft($("#tabframe").scrollLeft()+speed);
}
var stopScroll=function(){
	clearInterval(timer);
	timer=0;
}
$(function(){
	$("#toleft").mousedown(function(){
		stopScroll();
		timer=setInterval(toLeft,50);	
	});
	$("#toright").mousedown(function(){	
		stopScroll();
		timer=setInterval(toRight,50);
	});
	$("#toleft,#toright").mouseup(stopScroll);
	$("#toleft,#toright").mouseleave(stopScroll);
});
var href=function(url,title){
	if(url.indexOf("#")==0)
		return;
	var flag=url;
	if(url.indexOf("?")!=-1)
	   flag=url.substring(0,url.indexOf("?"));			
	if(hrefs[flag]){
		hrefs[flag].active();
		return;
	}
	var frameHandle = "handle_"+$.UUID();
	var newHref=hrefs[flag]=new Object();
	newHref.flag=flag;
	newHref.url=url;
	newHref.title=title;
	newHref.td=$("<td class='item state-focus'></td>");
	newHref.td.get(0).onwer=newHref;
	newHref.td.appendTo($("#tabstr"));			
	newHref.table=$("<table border='0' cellspacing='0' cellpadding='0'><tr><td class='btn'>"+title+"</td><td class='cls'>x</td></tr></table>");
	newHref.button=newHref.table.find(".btn");
	newHref.icon=newHref.table.find(".cls");			
	newHref.table.appendTo(newHref.td);
	newHref.iframe=$("<iframe id='"+frameHandle+"' name='"+frameHandle+"' src='' scrolling='yes' width='100%' height='100%' frameborder='0' style='width:100%;height:100%; display:none;'></iframe>");
	newHref.iframe.appendTo($("#iframes"));	
	
	newHref.button.menu({
		"关闭":function(){
			newHref.close();
		},
		"关闭其它":function(){
			for(var o in hrefs)
			{
				if(hrefs[o]!=newHref)
					hrefs[o].close();
			}
		},
		"关闭所有":function(){
			for(var o in hrefs){
				hrefs[o].close();
			}
		}
	});
	
	newHref.active=function(){
		if(current){
			current.td.attr("class","item state-header");
			current.iframe.hide();
		}
		current=newHref;
		current.td.attr("class","item state-focus");
		current.iframe.show();
	}
	newHref.close=function(){		
		delete hrefs[newHref.flag];
		
		if(current==newHref){
			if(newHref.td.prev().length>0)
				newHref.td.prev().get(0).onwer.active();
			else if(newHref.td.next().length>0)
				newHref.td.next().get(0).onwer.active();
		}
		
		newHref.td.remove();
		$(newHref.iframe.get(0).contentWindow).unload();
		newHref.iframe.remove();
		$.remove(newHref.td);
		$.remove(newHref.iframe);
	}
	newHref.open=function(){
		var tUrl=current.url;
		tUrl=tUrl+(tUrl.indexOf("?")>-1 ? "&" : "?") +"mm="+ Math.random();//防止缓存，在网址后面加随数		
		current.iframe.get(0).src=tUrl;
	}
	
	newHref.button.click(newHref.active);
	newHref.icon.click(newHref.close);
	
	newHref.active();
	newHref.open();	
};

var onResize=function(){
	$("#tabframe").css("width",$(window).width()-$("#tabmore").width()-10);
}
$(function(){
	onResize();	
	$(window).resize(onResize);
	$(document).resize(onResize);	
	//parent.href("welcome.do","<img src='${basePath}admin/images/icons/home.png' width='20' height='20' border='0' align='absmiddle' />系统首页");	
});
</script>

</body>
</html>