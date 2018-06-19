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
<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
<body scroll="no" style="overflow:hidden;">
<script>
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";
</script>
<script>
function link(flag,params){
	var link=$("#navigater .menucontent a[href*='"+flag+"']").attr("href");
	eval(link);
}
</script>
<div id="desktop">
	<div id="syslinks" > 
		<div class="item">
   			<a class="link" onclick="parent.href('welcome.do','欢迎页面');" href='javascript:void(0);'>
   				<img class="icon" src="${basePath}admin/images/icons/home.png"/>
   				<span class="name">欢迎页面</span>
   				<span class="mask"></span>
   			</a>
   		</div>
   		<div class="item">
   			<a class="link" onclick="top.themes();" href='javascript:void(0);'>
   				<img class="icon" src="${basePath}admin/images/icons/20088256423873477801.png"/>
   				<span class="name">风格主题</span>
   				<span class="mask"></span>
   			</a>
   		</div>
		<div class="item">
   			<a class="link" onclick="top.$.showModalDialog({url:'shortcut.do'},{},window);" href='javascript:void(0);'>
   				<img class="icon" src="${basePath}admin/images/icons/2008825642250778031.png"/>
   				<span class="name">添加图标</span>
   				<span class="mask"></span>
   			</a>
   		 </div>
   		 <div class="item">
   			<a class="link" onclick="top.$.showModalDialog({url:'permissions/password.do'},{},null);" href="#">
   				<img class="icon" src="${basePath}admin/images/icons/20081122201219142778044.png"/>
   				<span class="name">修改密码</span>
   				<span class="mask"></span>
   			</a>
   		 </div>
   		 <div class="item">
   			<a class="link" onclick="return confirm('确定要退出登录吗？')" href="login_logout.do">
   				<img class="icon" src="${basePath}/admin/images/icons/Badge-cancel.png"/>
   				<span class="name">退出登录</span>
   				<span class="mask"></span>
   			</a>
   		 </div>
   	</div>
   	<div id="shortcuts">
		<s:iterator value="#request.shortcuts" id="item" status="i">
		<div class="item shortcut">
			<s:if test="#item.target=='_parent'">
				<a uuid="${item.uuid}" class="link" href="javascript:parent.href('${item.link}','${item.name}')">
			</s:if>
			<s:else>
				<a uuid="${item.uuid}" class="link" href="${item.link}" target="${item.target}">
			</s:else>
				<img class="icon" src="${item.icon}" />
				<span class="name">${item.name}</span>
				<span class="mask"></span>
			</a>
		</div>
		</s:iterator>
	</div>
   	
   	${lz:set("background",empty background ? "images/background.jpg" : background)}
   	<div id="background">
   		<div class="mask"></div>
   		<div class="image" style="${empty background?"":"background:url("}${background}${empty background?"":") center;"}">
   			${empty background?"":"<img src='"}${background}'${empty background?"":"></img>"}
   		</div>
   	</div>	
   	
</div>

<div id="taskbar">
	<div class="bg"></div>
	<div class="cnt">
		<a id="showdesk" href="javascript:void(0);"></a>
		<dl id="navigater">
			<dt class="btn">导航菜单</dt>
			<dd class="mask"></dd>
			<dd class="menucontent">				
				<s:if test="#session.ccclubs_login.suId==0">
			  		<dl id="nav_super">
			  			<dt>
				  			<a class="link" href="#">
				  				<img align="absmiddle" class="icon" src="${basePath}/admin/images/icons/Folder.png"/>
				  				<span class="name">超级权限</span>
				  			</a>
			  			</<dt>
			  		</dl>
				</s:if>
				<s:iterator  value="#request.projects" id="project1" status="i">
				  <s:if test="#project1.spParentId==0">
				  	<dl id="nav_${project1.spId}">
				  		<dt>
				  			<a class="link" id="nav_${project1.spId}" href="#">
				  				<img align="absmiddle" class="icon" src="${empty project1.spLogo?basePath:""}${empty project1.spLogo?"/admin/images/icons/Folder.png":project1.spLogo}"/>
				  				<span class="name">${project1.spName}</span>
				  			</a>
				  		</dt>
				  	</dl>	
				  </s:if>
				</s:iterator>
				<dl>
		  			<dt>
			  			<a class="link" href="login_logout.do">
			  				<img align="absmiddle" class="icon" src="${basePath}/admin/images/icons/Badge-cancel.png"/>
			  				<span class="name">退出登录</span>
			  			</a>
		  			</<dt>
		  		</dl>
		  		<div class='mnmask'></div>
			</dd>
		</dl>
		<div class="info">
			欢迎您, ${ccclubs_login.suRealName}(${ccclubs_login.suUsername})
		</div>
		<div id="windowtab">
			<div id="tabmore">
				<button class="button" type="button" id="toleft"></button>
				<button class="button" type="button" id="toright"></button>
			</div>
			<div id="tabframe">
				<table id="tabs" border="0" cellspacing="3" cellpadding="0">
				  <tr id="tabstr"></tr>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
<!--
//初始化
var speed=20;
var timer=0;
var hrefs=[];
var current;
var laster;
var topness=$.zindex()*3;
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
	$("#showdesk").click(function(){
		if(current){
			laster = current;
			current.blur();
		}else if(laster){
			laster.active();
		}
	});
	$("#desktop #background").mousedown(function(){	
		for(var o in hrefs){
			hrefs[o].blur();
		}
	});
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
	newHref.td=$("<td class='item state-header'></td>");
	newHref.td.get(0).onwer=newHref;
	newHref.td.appendTo($("#tabstr"));			
	newHref.table=$("<table border='0' cellspacing='0' cellpadding='0'><tr><td class='btn'>"+title+"</td><td class='cls'>x</td></tr></table>");
	newHref.button=newHref.table.find(".btn");
	newHref.icon=newHref.table.find(".cls");			
	newHref.table.appendTo(newHref.td);
	var tUrl=url;
	tUrl=tUrl+(tUrl.indexOf("?")>-1 ? "&" : "?") +"mm="+ Math.random();//防止缓存，在网址后面加随数	
	
	newHref.button.menu({
		"关闭":function(){
			newHref.close();
		},
		"关闭其它":function(){
			for(var o in hrefs){
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
		if(current!=null && current!=newHref)
			current.blur();
		current=newHref;
		current.td.attr("class","item state-focus");
		newHref.lastindex=newHref.iframe.css("z-index");
		newHref.iframe.css("z-index",topness);
		newHref.iframe.show();
	}
	newHref.blur=function(){
		newHref.td.attr("class","item state-header");
		if(newHref.lastindex)
			newHref.iframe.css("z-index",newHref.lastindex);
		newHref.iframe.hide();
		current = null;
	}
	newHref.close=function(){
		newHref.blur();	
		delete hrefs[newHref.flag];
		newHref.td.remove();
		newHref.iframe.close(true);
	}
	
	
	newHref.iframe=$.showWindowDialog({
		url:tUrl,
		flag:frameHandle,
		onClose:newHref.close,
		onMinimize:newHref.blur
	});
	newHref.button.click(function(){
		if(newHref.td.hasClass("state-focus")){
			newHref.blur();
			newHref.iframe.hide();
		}else
			newHref.active();
	});
	newHref.iframe.click(newHref.active);
	newHref.icon.click(newHref.close);
	
	newHref.active();
};
/*************************自适应桌布尺寸*************************/
function autoDesktopPosition(){
	var iconSize = 90;//图标尺寸
	var vCount = Math.floor(($("#desktop").height()-40)/iconSize);//垂直可摆放的图标个数
	$("#shortcuts .item").each(function(){
		var index = $(this).index();
		var x = Math.floor(index/vCount);//所在列
		var y = index%vCount;//所在行
		$(this).css({left:x*iconSize,top:y*iconSize});
	}).show();
	$("#syslinks .item").each(function(){
		var index = $(this).index();
		var x = Math.floor(index/vCount);//所在列
		var y = index%vCount;//所在行
		$(this).css({right:x*iconSize,top:y*iconSize});
	}).show();
}
$(autoDesktopPosition);
$(window).resize(autoDesktopPosition);
/*************************自适应桌布尺寸*************************/

$(function(){
	$("#desktop .shortcut").menu({
		"删除快捷方式":function(ui){
			var uuid=$(ui).find(".link").attr("uuid");	
			top.$.dialog({
				title:"删除快捷方式",
				html:"<div style='white-space:nowrap;'>确定要删除快捷方式["+$(ui).find("span").html()+"]吗?</div>",
				onOk:function(){
					$.ajax({
					   type: "POST",
					   dataType:"html",
					   url:"shortcut_delIcon.do",
					   data:{uuid:uuid},
					   cache:false,
					   async:true,
					   success:function(data){
					   	 window.location.href=window.location.href;
					   }
					});										
				},
				modal:true
			});		
		}
	});
});
-->
</script>


<script type="text/javascript">
<!--
	$.fn.extend({
		ntree:function(value){
			var branches=$("<dl><dt><a href=\""+value.href+"\"><img align='absmiddle' class='icon' src='"+value.icon+"'/><span class='name'>"+value.text+"</span></a></dt></dl>");
			if($(this).find("dd").size()==0)
				$("<dd class='dd'></dd>").appendTo($(this));
			if($(this).children("dd").children(".mnmask").size()==0)
				$("<div class='mnmask'></div>").appendTo($(this).children("dd"));
			branches.appendTo($(this).children("dd"));
			return branches;
	    }
	});

	<s:if test="#session.ccclubs_login.suId==0">
	//如果是超级用户（登录用户的ID为0），则添加几个超级权限导航菜单，当数据库清空时，也能登录到系统
	$("#nav_super").ntree({text:"项目管理",href:"javascript:parent.href('${basePath}admin/permissions/project.do','项目管理')",expand:false,icon:"images/icons/20088256422681277805.png"});
	$("#nav_super").ntree({text:"用户管理",href:"javascript:parent.href('${basePath}admin/permissions/user.do','用户管理')",expand:false,icon:"images/icons/20088256427828778017.png"});
	$("#nav_super").ntree({text:"分组管理",href:"javascript:parent.href('${basePath}admin/permissions/group.do','分组管理')",expand:false,icon:"images/icons/Folder user.png"});
	$("#nav_super").ntree({text:"权限管理",href:"javascript:parent.href('${basePath}admin/permissions/limit.do','权限管理')",expand:false,icon:"images/icons/2008825642250778046.png"});
	</s:if>
	
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
			projects[${project.spId}]=(projects[${project.spParentId}]||$("#nav_${project.spParentId}")).ntree({text:"${project.spName}",href:"javascript:parent.href('${project.spUrl=="#"?"":basePath}${project.spUrl}${lz:indexOf(project.spUrl,'?') > -1 ? "&":"?"}limitsid="+(limits[${project.spId}]||"")+"','${project.spName}')",expand:true,url:"admin/${project.spUrl}",icon:"${empty project.spLogo?"images/icons/2008825642250778016.png":project.spLogo}"});
		</s:if>
	</s:iterator>
	
	$("#navigater .btn").click(function(){
		$("#navigater .menucontent").show();
		$("#navigater .mask").show();
		return false;
	});
	$("#navigater a[href='#']").click(function(){
		return false;
	});
	$("#navigater .mask").click(function(){
		$("#navigater .menucontent").hide();
		$("#navigater .mask").hide();
	});
	$("#navigater a[href!='#']").click(function(){
		$("#navigater .menucontent").hide();
		$("#navigater .mask").hide();
	}).menu({
		"创建快捷方式":function(ui){
			$.ajax({
			   type: "POST",
			   dataType:"html",
			   url:"shortcut_saveIcon.do",
			   data:{
			   	  name:ui.text(),
			   	  link:ui.attr("href"),
			   	  icon:ui.find("img").attr("src")
			   },
			   cache:false,
			   async:true,
			   success:function(data){
			   	 window.location.href=window.location.href;
			   }
			});	
		}
	});
	$("#navigater dl").mouseenter(function(){
		var dd = $(this).children("dd");
		if(dd.size()==0)
			return;	
		dd.show();
		dd.height(dd.height());
		if(dd.offset().top<0)
			dd.css({top:0});
		if((dd.offset().top+dd.height())>($(window).height()-40))
			dd.offset({top:($(window).height()-40)-dd.height()});
	});
	$("#navigater dl").mouseleave(function(){
		$(this).children("dd").hide();
	});
//-->
</script>

</body>
</html>