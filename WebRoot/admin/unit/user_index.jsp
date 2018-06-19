<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/user.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitUser.csuuId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csuuId,csuuHost,csuuUsername,csuuUnitInfo,csuuIsRoot,csuuRealname,csuuMobile,csuuAddTime,csuuStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csuuId,csuuHost,csuuUsername,csuuUnitInfo,csuuIsRoot,csuuLimit,csuuRealname,csuuMobile,csuuAddTime,csuuLastIp,csuuLastTime,csuuStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csuuId,csuuHost,csuuUsername,csuuUnitInfo,csuuIsRoot,csuuRealname,csuuMobile,csuuAddTime,csuuStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csuuId,csuuHost,csuuUsername,csuuUnitInfo,csuuIsRoot,csuuLimit,csuuRealname,csuuMobile,csuuAddTime,csuuStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>企业车管管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	<meta name="hashcode" content="${window.hashcode}">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		window.basePath="${basePath}";
		window.contextPath="${basePath}${proname}/";
		window.hashcode = "${window.hashcode}";
		window.apiUrl="${basePath}${proname}/api.do";
		window.handles={};
	</script>
		
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
	<!------------------------LAZY3Q_JSP_HEAD------------------------>
	<!------------------------LAZY3Q_JSP_HEAD------------------------>	
	
</head>  
<body>

${lz:set("注释","****非法权限时的友情提醒****")}
<s:if test="#request.canView!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>${lz:config("nolimit.message")}</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>


<!------------------------LAZY3Q_JSP_BODY------------------------>
<!------------------------LAZY3Q_JSP_BODY------------------------>


${lz:set("querys",get:querys(servletPath))}
${lz:set("haveQuerys",lz:size(querys)>0)}

<div class="shortcuts state-content" style="display:${inFrame==null && (window.shortcuts!="hide" && (shortcuts!=null || haveQuerys))?"block":"none"};">
	<div class="shadow"></div>
	<div class="holder">
		<a class="item ${(T+Q+0)==0?"focus":""}" href="${location}">		
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256421631277806.png"/>
			默认数据列表
		</a>
		
		<s:iterator value="#request.querys" id="item" status="i">
		<a class="item ${Q==item.sqId?"focus":""}" onclick="$.submit('${item.sqPath}?Q=${item.sqId}',eval('('+$(this).find('textarea').val()+')'))" href="javascript:void(0);">
			<span ref="${item.sqId}">X</span>
			<img align="absmiddle" src="${item.sqIcon}"/>${item.sqName}
			<textarea style="display:none;">${item.sqParams}</textarea>
		</a>
		</s:iterator>
		
		
		${shortcuts}
	</div>
</div>

<table class="${inFrame==null && (window.shortcuts!="hide" && (shortcuts!=null || haveQuerys))?"margin-by-shortcut":""}" border="0" cellspacing="0" cellpadding="0"><tr><td>

${before$title}
	<s:if test="#request.CTRL.canQuery==true">
<div class="title">
	<a href="javascript:void(0);" onclick="parent.current.close();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Badge-multiply.png"/>
		关闭页面
	</a>
	<a href="javascript:void(0);" class="linker imize ${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize"))?"minimize":"maximize"}">
		<img align="absmiddle" class="up" src="${basePath}admin/images/icons/Arrow up.png"/>
		<img align="absmiddle" class="down" src="${basePath}admin/images/icons/Arrow down.png"/>
		展开收起
	</a>
	<a href="javascript:void(0);" class="linker setting">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256421529677801.png"/>
		页面设置
	</a>
	<a href="?${lz:querys("UTF-8","xxxxx")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/2008825642250778028.png"/>
		重新加载
	</a>
	<s:if test="#request.CTRL.canExport==true">
	<a href="javascript:void(0);" onclick="exportXls();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256427828778018.png"/>
		数据导出
	</a>
	</s:if>
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"企业车管管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsUnitUser" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="user.query.jsp"%>

		<div class="line"></div>
		<div class="option">			
			<table border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td>
					<input type="hidden" name="all" value="${all}" />
					<input type="hidden" name="fields" value="${fields}" />
					<button class="button" type="submit">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Zoom.png"/>
						查询&nbsp;&nbsp;</button>		
					<button class="button" onclick="clearForm()" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778045.png"/>
						重置&nbsp;&nbsp;</button>
					<button class="button" onclick="saveForm()" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
						保存&nbsp;&nbsp;</button>
					<s:if test="#request.alias=='join' || #request.alias=='select'">
					<button class="button onok" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						确定&nbsp;&nbsp;</button>
					<button class="button oncancel" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						放弃&nbsp;&nbsp;</button>
					</s:if>
				</td>
			  </tr>
			</table>
			<div class="clear"></div>
		</div>
	</form>
</div>
</s:if>

<s:if test="#request.haveQuery==false">
<script>
$(function(){
	$(".query").parent().prevAll().remove();
	$(".query").parent().remove();
	$(".pager:eq(0)").remove();
})
</script>
</s:if>
    
${after$form}
	
</td></tr><tr><td>
	<s:if test="#request.CTRL.canQuery==true">
  <%@include file="/admin/module/page.jsp"%>
  	</s:if>
  			
	<div class="content">
	    <div class="toolbar">
	    
	      ${before$toolbar}
	    	
	      <s:if test="#request.alias==null">
			<s:if test="#request.CTRL.canDel==true">
			<button type="button" class="del" onclick="DelSel();">
				删除
			</button>
			</s:if>
			<s:if test="#request.CTRL.canAdd==true">
			<button type="button" class="add" onclick="AddUser();">
				添加
			</button>							
			</s:if>
		  </s:if>
	    	
	      ${lz:set("items",lz:split(handles,","))}
		  <s:iterator value="#request.items" id="handle" status="i">
			${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
			<s:if test="#request.isMenu==true">
			<button type="button" class="edit" onclick="HandleMenu('${handle}');">
				${handle}
			</button>
			</s:if>
		  </s:iterator>
	    
		  
			<button type="button" class="all" onclick="$('.check').attr('checked',true)">
				全选
			</button>
			<button type="button" class="invert" onclick="$('.check').click();">
				反选
			</button>
			<dl ref="com.ccclubs.model.CsUnitUser" class="display dropdowan" style="margin-left:30px;">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/display.png"/>
					${((all=="false" || all==null) && defines==null ) ? "主要内容":""}
					${(all=="true" && defines==null) ? "全部内容":""}
					${(defines!=null) ? "自定义显示":""}
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
						<a all="false" href="${basePath}${lz:replace(servletPath,"^/","")}?all=false&${lz:queryss("UTF-8","all","fields")}">主要内容</a>
						<a all="true" href="${basePath}${lz:replace(servletPath,"^/","")}?all=true&${lz:queryss("UTF-8","all","fields")}">全部内容</a>
						<a all="" href="${basePath}${lz:replace(servletPath,"^/","")}?${lz:queryss("UTF-8","all","fields")}">自定义显示</a>
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table>
				</dd>
			</dl>
			<s:if test="#request.CTRL.canEdit==true">
			<dl ref="com.ccclubs.model.CsUnitUser" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.csuuLimit || #request.defines['csuuLimit']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('limit')">权限信息</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csuuStatus || #request.defines['csuuStatus']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('status')">状态</a>
					</s:if>
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table
				</dd>
			</dl>
			</s:if>
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="user_table" ref="CsUnitUser" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuuId || #request.defines['csuuId']!=null">
			 <td <s:if test="#request.defines['csuuId']>0">colspan="${defines["csuuId"]}" ${all?"width":"iwidth"}="${defines["csuuId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csuuId"   title="编号">
			 	<a class="${desc=="csuu_id" ? "desc" : ""}${asc=="csuu_id" ? "asc" : ""}" href="?${desc=="csuu_id" ? "asc=csuu_id" : ""}${(asc=="csuu_id" || desc!="csuu_id" )? "desc=csuu_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csuuId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuuHost || #request.defines['csuuHost']!=null">
			 <td <s:if test="#request.defines['csuuHost']>0">colspan="${defines["csuuHost"]}" ${all?"width":"iwidth"}="${defines["csuuHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csuuHost"   title="城市">
			 	<a class="${desc=="csuu_host" ? "desc" : ""}${asc=="csuu_host" ? "asc" : ""}" href="?${desc=="csuu_host" ? "asc=csuu_host" : ""}${(asc=="csuu_host" || desc!="csuu_host" )? "desc=csuu_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csuuHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuuUsername || #request.defines['csuuUsername']!=null">
			 <td <s:if test="#request.defines['csuuUsername']>0">colspan="${defines["csuuUsername"]}" ${all?"width":"iwidth"}="${defines["csuuUsername"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="csuuUsername" flagKey  title="登录帐号">
			 	<a class="${desc=="csuu_username" ? "desc" : ""}${asc=="csuu_username" ? "asc" : ""}" href="?${desc=="csuu_username" ? "asc=csuu_username" : ""}${(asc=="csuu_username" || desc!="csuu_username" )? "desc=csuu_username" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	登录帐号
			 	</a>
			 	${lz:set("checkeds[]","csuuUsername")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuuUnitInfo || #request.defines['csuuUnitInfo']!=null">
			 <td <s:if test="#request.defines['csuuUnitInfo']>0">colspan="${defines["csuuUnitInfo"]}" ${all?"width":"iwidth"}="${defines["csuuUnitInfo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csuuUnitInfo"   title="所属单位">
			 	<a class="${desc=="csuu_unit_info" ? "desc" : ""}${asc=="csuu_unit_info" ? "asc" : ""}" href="?${desc=="csuu_unit_info" ? "asc=csuu_unit_info" : ""}${(asc=="csuu_unit_info" || desc!="csuu_unit_info" )? "desc=csuu_unit_info" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属单位
			 	</a>
			 	${lz:set("checkeds[]","csuuUnitInfo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuuIsRoot || #request.defines['csuuIsRoot']!=null">
			 <td <s:if test="#request.defines['csuuIsRoot']>0">colspan="${defines["csuuIsRoot"]}" ${all?"width":"iwidth"}="${defines["csuuIsRoot"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csuuIsRoot"   title="系统管理员">
			 	<a class="${desc=="csuu_is_root" ? "desc" : ""}${asc=="csuu_is_root" ? "asc" : ""}" href="?${desc=="csuu_is_root" ? "asc=csuu_is_root" : ""}${(asc=="csuu_is_root" || desc!="csuu_is_root" )? "desc=csuu_is_root" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	系统管理员
			 	</a>
			 	${lz:set("checkeds[]","csuuIsRoot")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuuLimit || #request.defines['csuuLimit']!=null">
			 <td <s:if test="#request.defines['csuuLimit']>0">colspan="${defines["csuuLimit"]}" ${all?"width":"iwidth"}="${defines["csuuLimit"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="6" ref="csuuLimit"   title="权限信息">
			 	<a class="${desc=="csuu_limit" ? "desc" : ""}${asc=="csuu_limit" ? "asc" : ""}" href="?${desc=="csuu_limit" ? "asc=csuu_limit" : ""}${(asc=="csuu_limit" || desc!="csuu_limit" )? "desc=csuu_limit" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	权限信息
			 	</a>
			 	${lz:set("checkeds[]","csuuLimit")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuuRealname || #request.defines['csuuRealname']!=null">
			 <td <s:if test="#request.defines['csuuRealname']>0">colspan="${defines["csuuRealname"]}" ${all?"width":"iwidth"}="${defines["csuuRealname"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="csuuRealname"   title="真实姓名">
			 	<a class="${desc=="csuu_realname" ? "desc" : ""}${asc=="csuu_realname" ? "asc" : ""}" href="?${desc=="csuu_realname" ? "asc=csuu_realname" : ""}${(asc=="csuu_realname" || desc!="csuu_realname" )? "desc=csuu_realname" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	真实姓名
			 	</a>
			 	${lz:set("checkeds[]","csuuRealname")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuuMobile || #request.defines['csuuMobile']!=null">
			 <td <s:if test="#request.defines['csuuMobile']>0">colspan="${defines["csuuMobile"]}" ${all?"width":"iwidth"}="${defines["csuuMobile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="8" ref="csuuMobile"   title="联系电话">
			 	<a class="${desc=="csuu_mobile" ? "desc" : ""}${asc=="csuu_mobile" ? "asc" : ""}" href="?${desc=="csuu_mobile" ? "asc=csuu_mobile" : ""}${(asc=="csuu_mobile" || desc!="csuu_mobile" )? "desc=csuu_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系电话
			 	</a>
			 	${lz:set("checkeds[]","csuuMobile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuuAddTime || #request.defines['csuuAddTime']!=null">
			 <td <s:if test="#request.defines['csuuAddTime']>0">colspan="${defines["csuuAddTime"]}" ${all?"width":"iwidth"}="${defines["csuuAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="9" ref="csuuAddTime"   title="添加时间">
			 	<a class="${desc=="csuu_add_time" ? "desc" : ""}${asc=="csuu_add_time" ? "asc" : ""}" href="?${desc=="csuu_add_time" ? "asc=csuu_add_time" : ""}${(asc=="csuu_add_time" || desc!="csuu_add_time" )? "desc=csuu_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csuuAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuuLastIp || #request.defines['csuuLastIp']!=null">
			 <td <s:if test="#request.defines['csuuLastIp']>0">colspan="${defines["csuuLastIp"]}" ${all?"width":"iwidth"}="${defines["csuuLastIp"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="10" ref="csuuLastIp"   title="最后登录IP">
			 	<a class="${desc=="csuu_last_ip" ? "desc" : ""}${asc=="csuu_last_ip" ? "asc" : ""}" href="?${desc=="csuu_last_ip" ? "asc=csuu_last_ip" : ""}${(asc=="csuu_last_ip" || desc!="csuu_last_ip" )? "desc=csuu_last_ip" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	最后登录IP
			 	</a>
			 	${lz:set("checkeds[]","csuuLastIp")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuuLastTime || #request.defines['csuuLastTime']!=null">
			 <td <s:if test="#request.defines['csuuLastTime']>0">colspan="${defines["csuuLastTime"]}" ${all?"width":"iwidth"}="${defines["csuuLastTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="csuuLastTime"   title="最后登录时间">
			 	<a class="${desc=="csuu_last_time" ? "desc" : ""}${asc=="csuu_last_time" ? "asc" : ""}" href="?${desc=="csuu_last_time" ? "asc=csuu_last_time" : ""}${(asc=="csuu_last_time" || desc!="csuu_last_time" )? "desc=csuu_last_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	最后登录时间
			 	</a>
			 	${lz:set("checkeds[]","csuuLastTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuuStatus || #request.defines['csuuStatus']!=null">
			 <td <s:if test="#request.defines['csuuStatus']>0">colspan="${defines["csuuStatus"]}" ${all?"width":"iwidth"}="${defines["csuuStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="csuuStatus"   title="状态">
			 	<a class="${desc=="csuu_status" ? "desc" : ""}${asc=="csuu_status" ? "asc" : ""}" href="?${desc=="csuu_status" ? "asc=csuu_status" : ""}${(asc=="csuu_status" || desc!="csuu_status" )? "desc=csuu_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csuuStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="13" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="14" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddUser()" style="text-decoration:underline">添加</a>
				</s:if>
				<s:else>&nbsp;</s:else>
			</td>
		  </tr>
		  <s:if test="#request.childes!=null">
		  ${lz:set("注释","****级联字段:当某字段关联另外一个表时，并且用户勾选了要显示另外的字段****")}
		  <tr>
		  	<s:iterator value="#request.childes" id="fieldName" status="i">
		 	<td tdid="${fieldName}" ref="${fieldName}">
		 		${lz:set("field",lz:field(modelClass,fieldName))}
		 		<!-- <a class="${desc==field.notes.column.value ? "desc" : ""}${asc==field.notes.column.value ? "asc" : ""}" href="?${desc==field.notes.column.value ? (lz:join("asc=",field.notes.column.value)) : ""}${(asc==field.notes.column.value || desc!=field.notes.column.value )? (lz:join("desc=",field.notes.column.value)) : ""}&${lz:queryss("UTF-8","desc","asc")}"> -->
		 		${field.notes.caption.value}
		 		<!-- </a> -->
		 	</td>
		 	</s:iterator>
		 	${lz:set("checkeds[]",fieldName)}
		  </tr>
		  </s:if>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csuuId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csuuId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuuId || #request.defines['csuuId']!=null">
				<s:if test="#request.defines['csuuId']>0">
					${lz:set("注释","****csuuId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuuId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuuId",lz:indexOf(fieldName,"csuuId")>-1)}
				  		<s:if test="#request.atCsuuId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuuId字段的字串格式化输出****")}
					<td ref="csuuId" class="td ">
						 
						 	${lz:or(item$csuuId[i.count-1],lz:left(item.csuuId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuuHost || #request.defines['csuuHost']!=null">
				<s:if test="#request.defines['csuuHost']>0">
					${lz:set("注释","****csuuHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuuHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuuHost",lz:indexOf(fieldName,"csuuHost")>-1)}
				  		<s:if test="#request.atCsuuHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuuHost字段的字串格式化输出****")}
					<td ref="csuuHost" class="td " relate="${item.csuuHost}">
						 
						 	<a <s:if test="#item.csuuHost!=null && #item.csuuHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csuuHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csuuHost[i.count-1],lz:left(item.csuuHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuuUsername || #request.defines['csuuUsername']!=null">
				<s:if test="#request.defines['csuuUsername']>0">
					${lz:set("注释","****csuuUsername关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuuUsername))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuuUsername",lz:indexOf(fieldName,"csuuUsername")>-1)}
				  		<s:if test="#request.atCsuuUsername==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuuUsername字段的字串格式化输出****")}
					<td ref="csuuUsername" class="td  node">
						 
						 	${lz:or(item$csuuUsername[i.count-1],lz:left(item.csuuUsername$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuuUnitInfo || #request.defines['csuuUnitInfo']!=null">
				<s:if test="#request.defines['csuuUnitInfo']>0">
					${lz:set("注释","****csuuUnitInfo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuuUnitInfo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuuUnitInfo",lz:indexOf(fieldName,"csuuUnitInfo")>-1)}
				  		<s:if test="#request.atCsuuUnitInfo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuuUnitInfo字段的字串格式化输出****")}
					<td ref="csuuUnitInfo" class="td " relate="${item.csuuUnitInfo}">
						 
						 	<a <s:if test="#item.csuuUnitInfo!=null && #item.csuuUnitInfo!=''"> onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${item.csuuUnitInfo}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csuuUnitInfo[i.count-1],lz:left(item.csuuUnitInfo$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuuIsRoot || #request.defines['csuuIsRoot']!=null">
				<s:if test="#request.defines['csuuIsRoot']>0">
					${lz:set("注释","****csuuIsRoot关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuuIsRoot))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuuIsRoot",lz:indexOf(fieldName,"csuuIsRoot")>-1)}
				  		<s:if test="#request.atCsuuIsRoot==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuuIsRoot字段的字串格式化输出****")}
					<td ref="csuuIsRoot" class="td ">
						 
						 	${lz:or(item$csuuIsRoot[i.count-1],lz:left(item.csuuIsRoot$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuuLimit || #request.defines['csuuLimit']!=null">
				<s:if test="#request.defines['csuuLimit']>0">
					${lz:set("注释","****csuuLimit关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuuLimit))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuuLimit",lz:indexOf(fieldName,"csuuLimit")>-1)}
				  		<s:if test="#request.atCsuuLimit==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuuLimit字段的字串格式化输出****")}
					<td ref="csuuLimit" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csuuId}','limit')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csuuLimit[i.count-1],lz:left(item.csuuLimit$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuuRealname || #request.defines['csuuRealname']!=null">
				<s:if test="#request.defines['csuuRealname']>0">
					${lz:set("注释","****csuuRealname关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuuRealname))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuuRealname",lz:indexOf(fieldName,"csuuRealname")>-1)}
				  		<s:if test="#request.atCsuuRealname==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuuRealname字段的字串格式化输出****")}
					<td ref="csuuRealname" class="td ">
						 
						 	${lz:or(item$csuuRealname[i.count-1],lz:left(item.csuuRealname$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuuMobile || #request.defines['csuuMobile']!=null">
				<s:if test="#request.defines['csuuMobile']>0">
					${lz:set("注释","****csuuMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuuMobile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuuMobile",lz:indexOf(fieldName,"csuuMobile")>-1)}
				  		<s:if test="#request.atCsuuMobile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuuMobile字段的字串格式化输出****")}
					<td ref="csuuMobile" class="td ">
						 
						 	${lz:or(item$csuuMobile[i.count-1],lz:left(item.csuuMobile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuuAddTime || #request.defines['csuuAddTime']!=null">
				<s:if test="#request.defines['csuuAddTime']>0">
					${lz:set("注释","****csuuAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuuAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuuAddTime",lz:indexOf(fieldName,"csuuAddTime")>-1)}
				  		<s:if test="#request.atCsuuAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuuAddTime字段的字串格式化输出****")}
					<td ref="csuuAddTime" class="td ">
						 
						 	${lz:or(item$csuuAddTime[i.count-1],lz:left(item.csuuAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuuLastIp || #request.defines['csuuLastIp']!=null">
				<s:if test="#request.defines['csuuLastIp']>0">
					${lz:set("注释","****csuuLastIp关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuuLastIp))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuuLastIp",lz:indexOf(fieldName,"csuuLastIp")>-1)}
				  		<s:if test="#request.atCsuuLastIp==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuuLastIp字段的字串格式化输出****")}
					<td ref="csuuLastIp" class="td ">
						 
						 	${lz:or(item$csuuLastIp[i.count-1],lz:left(item.csuuLastIp$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuuLastTime || #request.defines['csuuLastTime']!=null">
				<s:if test="#request.defines['csuuLastTime']>0">
					${lz:set("注释","****csuuLastTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuuLastTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuuLastTime",lz:indexOf(fieldName,"csuuLastTime")>-1)}
				  		<s:if test="#request.atCsuuLastTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuuLastTime字段的字串格式化输出****")}
					<td ref="csuuLastTime" class="td ">
						 
						 	${lz:or(item$csuuLastTime[i.count-1],lz:left(item.csuuLastTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuuStatus || #request.defines['csuuStatus']!=null">
				<s:if test="#request.defines['csuuStatus']>0">
					${lz:set("注释","****csuuStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuuStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuuStatus",lz:indexOf(fieldName,"csuuStatus")>-1)}
				  		<s:if test="#request.atCsuuStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuuStatus字段的字串格式化输出****")}
					<td ref="csuuStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csuuId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csuuStatus[i.count-1],lz:left(item.csuuStatus$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.csuuId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectUser('${item.csuuId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditUser('${item.csuuId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelUser('${item.csuuId}','${item.csuuUsername}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsUser('${item.csuuId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuuId || #request.defines['csuuId']!=null">
				<s:if test="#request.defines['csuuId']>0">
					${lz:set("注释","****csuuId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuuId",lz:indexOf(fieldName,"csuuId")>-1)}				  		
				  		<s:if test="#request.atCsuuId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuuId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuuHost || #request.defines['csuuHost']!=null">
				<s:if test="#request.defines['csuuHost']>0">
					${lz:set("注释","****csuuHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuuHost",lz:indexOf(fieldName,"csuuHost")>-1)}				  		
				  		<s:if test="#request.atCsuuHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuuHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuuUsername || #request.defines['csuuUsername']!=null">
				<s:if test="#request.defines['csuuUsername']>0">
					${lz:set("注释","****csuuUsername关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuuUsername",lz:indexOf(fieldName,"csuuUsername")>-1)}				  		
				  		<s:if test="#request.atCsuuUsername==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuuUsername[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuuUnitInfo || #request.defines['csuuUnitInfo']!=null">
				<s:if test="#request.defines['csuuUnitInfo']>0">
					${lz:set("注释","****csuuUnitInfo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuuUnitInfo",lz:indexOf(fieldName,"csuuUnitInfo")>-1)}				  		
				  		<s:if test="#request.atCsuuUnitInfo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuuUnitInfo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuuIsRoot || #request.defines['csuuIsRoot']!=null">
				<s:if test="#request.defines['csuuIsRoot']>0">
					${lz:set("注释","****csuuIsRoot关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuuIsRoot",lz:indexOf(fieldName,"csuuIsRoot")>-1)}				  		
				  		<s:if test="#request.atCsuuIsRoot==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuuIsRoot[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuuLimit || #request.defines['csuuLimit']!=null">
				<s:if test="#request.defines['csuuLimit']>0">
					${lz:set("注释","****csuuLimit关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuuLimit",lz:indexOf(fieldName,"csuuLimit")>-1)}				  		
				  		<s:if test="#request.atCsuuLimit==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuuLimit[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuuRealname || #request.defines['csuuRealname']!=null">
				<s:if test="#request.defines['csuuRealname']>0">
					${lz:set("注释","****csuuRealname关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuuRealname",lz:indexOf(fieldName,"csuuRealname")>-1)}				  		
				  		<s:if test="#request.atCsuuRealname==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuuRealname[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuuMobile || #request.defines['csuuMobile']!=null">
				<s:if test="#request.defines['csuuMobile']>0">
					${lz:set("注释","****csuuMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuuMobile",lz:indexOf(fieldName,"csuuMobile")>-1)}				  		
				  		<s:if test="#request.atCsuuMobile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuuMobile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuuAddTime || #request.defines['csuuAddTime']!=null">
				<s:if test="#request.defines['csuuAddTime']>0">
					${lz:set("注释","****csuuAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuuAddTime",lz:indexOf(fieldName,"csuuAddTime")>-1)}				  		
				  		<s:if test="#request.atCsuuAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuuAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuuLastIp || #request.defines['csuuLastIp']!=null">
				<s:if test="#request.defines['csuuLastIp']>0">
					${lz:set("注释","****csuuLastIp关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuuLastIp",lz:indexOf(fieldName,"csuuLastIp")>-1)}				  		
				  		<s:if test="#request.atCsuuLastIp==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuuLastIp[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuuLastTime || #request.defines['csuuLastTime']!=null">
				<s:if test="#request.defines['csuuLastTime']>0">
					${lz:set("注释","****csuuLastTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuuLastTime",lz:indexOf(fieldName,"csuuLastTime")>-1)}				  		
				  		<s:if test="#request.atCsuuLastTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuuLastTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuuStatus || #request.defines['csuuStatus']!=null">
				<s:if test="#request.defines['csuuStatus']>0">
					${lz:set("注释","****csuuStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuuStatus",lz:indexOf(fieldName,"csuuStatus")>-1)}				  		
				  		<s:if test="#request.atCsuuStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuuStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
		  	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
		 </tr>
			 </s:iterator>
	 	 </s:bean>
		 </s:if>
		  
		  
		 </s:iterator>
		 </tbody>			 
	  </table>
	  
	  ${lz:set("注释","*****************数据列表结束*****************")}
	  
  </div>
  <%@include file="/admin/module/page.jsp"%>
  
 </td></tr></table>

<script>

	<s:if test="#request.alias=='join'">
	window.objects = ${objects==null ? null : (lz:json(objects))};
	</s:if>	
	window.entrypoint="${entrypoint}";
	
	showTips("${tips.value}");
	
	<s:if test="#request.onload!=null">
	$(function(){
		try{
			if("${lz:js(onload)}".indexOf("(")>-1){
				eval("${lz:js(onload)}");
			}else{
				window["${onload}"]();
			}
		}catch(e){}
	});
	</s:if>
	

	function href(url,params){
		top.$.showModalDialog({url:url},params,window);
	} 
	
	function saveForm(){
		var params = $("#queryForm").params();
		var paramCount = 0;
		for(var o in params){
			if($.trim(params[o])=="")
				delete params[o];
			else if($("#queryForm *[name='"+o+"']").attr("type")=="hidden"){
			
			}else
				paramCount++;
		}
		if(paramCount==0){
			top.Alert("没有任何查询条件，不能保存");
			return;
		}
		var dialog = top.$.open("${basePath}admin/configurator/query_edit.do",{
			"srvQuery.sqPath":"${servletPath}",
			"srvQuery.sqParams":JSON.stringify(params)
		},function(object){
			if(typeof(object)!="object"){
				try{
					dialog.find("iframe").get(0).contentWindow.$("#sqPath").parents("dl").hide();
					dialog.find("iframe").get(0).contentWindow.$("#sqParams").parents("dl").hide();
				}catch(e){}
			}else if(object && object.sqId){
				top.Alert("快速查询链接保存成功");
				$.cache("shortcuts","show");
				$.submit("${location}?Q="+object.sqId,params);
			}
		});		
	}
	$(function(){
		$(".shortcuts .item span").click(function(){
			var id = $(this).attr("ref");
			var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除该快速查询链接吗？</span>",
				title:"删除快速查询链接",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}admin/configurator/query_del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);					
				}
			});
			return false;	
		});
	});
<s:if test="#request.CTRL.canAdd==true">
	/**
	* 添加企业车管
	**/
	function AddUser(parent){		
		var url = "${basePath}${namespace}user_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加企业车管'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择企业车管
	**/
	function SelectUser(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改企业车管
	**/
	function EditUser(id){
		var url = "${basePath}${namespace}user_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改企业车管'}":lz:json(ctrl)}};
		href(url,params);
	}
	/*
	* 修改所选
	*/
	function EditSel(){
		var checker=$(".check:checked");
		if(checker.length!=1)
		{
			$.tips("请选择一个需要修改的记录.");
			return;
		}
		EditUser(checker.val());
	}
	
	/**
	* 批量修改
	**/
	function UpdateSel(flag)
	{
		var checker=$(".check:checked");
		if(checker.length==0){
			<s:if test="#session.ccclubs_login.suId==0">
			$.alert("确定要对当前查询的${objects.count}条记录进行批量修改吗?",function(){
				var url = "${basePath}${namespace}user_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新企业车管",visible:false,editable:false,fields:{}}};
				for(var o in fieldNames[flag])
					params.ctrl["fields"][o]={visible:true,editable:true};
				href(url,params);
			});
			</s:if>
			<s:else>
			top.$.alert("您未选择要修改的记录");
			</s:else>
		}else{
			var ids = "";
			checker.each(function(i){
				if(ids!="")
					ids+=",";
				ids+=$(this).val();
			});
			var url = "${basePath}${namespace}user_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新企业车管",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改企业车管
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}user_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新企业车管",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["limit"]={};
	fieldNames["status"]={};
	fieldNames["limit"]["csuuLimit"]=true;
	fieldNames["status"]["csuuStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改企业车管任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}user_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新企业车管",visible:false,editable:false,fields:{}}};
			params.ctrl["fields"][$(this).attr("ref")]={visible:true,editable:true};
			href(url,params);
		});
	});
	</s:if>

	/*
	* 自定义操作
	*/
	function HandleSel(operate,id){
		if(!id){
			var checker=$(".check:checked");
			if(checker.length!=1){
				$.tips("请选择一个需要操作的记录.");
				return;
			}
			id = checker.val();
		}
		
		if(window.getHandle && window.getHandle(operate,id)!=null)//本窗口的操作函数获取句柄
			window.getHandle(operate)(operate,id);
		else if(window.handles && window.handles[operate])//本窗口的操作函数句柄
			window.handles[operate](operate,id);
		else if(typeof(caller)!="undefined" && caller.handles && caller.handles[operate])//调用窗口的操作函数句柄
			caller.handles[operate](operate,id);
		else if(parent && parent.handles && parent.handles[operate])//父窗口的操作函数句柄
			parent.handles[operate](operate,id);
		else{
			var url = "${basePath}${namespace}user_edit.do";
			var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:operate}};
			href(url,params);
		}
	}
	/*
	* 自定义菜单
	*/
	function HandleMenu(operate){
		if(window.getHandle && window.getHandle(operate)!=null)//本窗口的操作函数获取句柄
			window.getHandle(operate)(operate);
		else if(window.handles && window.handles[operate])//本窗口的操作函数句柄
			window.handles[operate](operate);
		else if(typeof(caller)!="undefined" && caller.handles && caller.handles[operate])//调用窗口的操作函数句柄
			caller.handles[operate](operate);
		else if(parent && parent.handles && parent.handles[operate])//父窗口的操作函数句柄
			parent.handles[operate](operate);
		else{
			var url = "${basePath}${namespace}user_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除企业车管
	**/
	function DelUser(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除企业车管["+flag+"]吗？</span>",
				title:"删除企业车管",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}user_del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);
				}
		 });	
	}
	/*
	* 删除所选记录详情
	*/
	function DelSel(){
		var checker=$(".check:checked");
		if(checker.length==0)
		{
			$.tips("您未选中任何记录，请至少选择一项.");
			return;
		}
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除所选企业车管吗？</span>",
				title:"批量删除企业车管",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}user_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示企业车管详情
	**/
	function DetailsUser(id)
	{
		var url = "${basePath}${namespace}user_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"企业车管详情",editable:false,visible:true}};
		href(url,params);
	}
	/*
	* 显示所选记录详情
	*/
	function DetailsSel(){
		var checker=$(".check:checked");
		if(checker.length!=1)
		{
			$.tips("请选择一个需要显示详情的记录.");
			return;
		}
		DetailsUser(checker.val());
	}
	/*
	* 数据导出
	*/
	function exportXls(){
		if(${page.count>=0 ? page.count : 0}==0){
			showTips("当前没有可导出数据");
			return;
		}
		top.$.showModalDialog({
			url:"${basePath}admin/export.do"
		},{
			className:"${page.result[0].class.name}",
			count:${page.count+0},
			exporturl:"${basePath}${servletPath}?export=true&page={page}&${lz:querys("UTF-8","page")}",
			checkeds:"${checkeds}"
		},window);	
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>