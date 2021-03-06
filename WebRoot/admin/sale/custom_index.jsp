<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/sale/custom.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbSaleCustom.tbscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"tbscId,tbscHost,tbscName,tbscBattle,tbscEvent,tbscGroup,tbscMobile,tbscNumber,tbscEmail,tbscQq,tbscProfile,tbscLevel,tbscFormal,tbscServer,tbscOnwer,tbscAdder,tbscRemark,tbscMemo,tbscData,tbscUpdateTime,tbscAddTime,tbscStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"tbscId,tbscHost,tbscName,tbscBattle,tbscEvent,tbscGroup,tbscMobile,tbscNumber,tbscEmail,tbscQq,tbscOthrer,tbscProfile,tbscLevel,tbscFormal,tbscServer,tbscOnwer,tbscAdder,tbscRemark,tbscMemo,tbscData,tbscUpdateTime,tbscAddTime,tbscStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"tbscId,tbscHost,tbscName,tbscBattle,tbscEvent,tbscGroup,tbscMobile,tbscProfile,tbscOnwer,tbscRemark,tbscUpdateTime,tbscAddTime,tbscStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"tbscId,tbscHost,tbscName,tbscBattle,tbscEvent,tbscGroup,tbscMobile,tbscProfile,tbscOnwer,tbscRemark,tbscUpdateTime,tbscAddTime,tbscStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>客户资料管理</title>
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
	<span class="caption">${empty title?"客户资料管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="TbSaleCustom" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="custom.query.jsp"%>

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
			<button type="button" class="add" onclick="AddCustom();">
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
			<dl ref="com.ccclubs.model.TbSaleCustom" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.TbSaleCustom" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.tbscStatus || #request.defines['tbscStatus']!=null">
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
		<table id="custom_table" ref="TbSaleCustom" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscId || #request.defines['tbscId']!=null">
			 <td <s:if test="#request.defines['tbscId']>0">colspan="${defines["tbscId"]}" ${all?"width":"iwidth"}="${defines["tbscId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="tbscId"   title="编号">
			 	<a class="${desc=="tbsc_id" ? "desc" : ""}${asc=="tbsc_id" ? "asc" : ""}" href="?${desc=="tbsc_id" ? "asc=tbsc_id" : ""}${(asc=="tbsc_id" || desc!="tbsc_id" )? "desc=tbsc_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","tbscId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscHost || #request.defines['tbscHost']!=null">
			 <td <s:if test="#request.defines['tbscHost']>0">colspan="${defines["tbscHost"]}" ${all?"width":"iwidth"}="${defines["tbscHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="tbscHost"   title="城市">
			 	<a class="${desc=="tbsc_host" ? "desc" : ""}${asc=="tbsc_host" ? "asc" : ""}" href="?${desc=="tbsc_host" ? "asc=tbsc_host" : ""}${(asc=="tbsc_host" || desc!="tbsc_host" )? "desc=tbsc_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","tbscHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscName || #request.defines['tbscName']!=null">
			 <td <s:if test="#request.defines['tbscName']>0">colspan="${defines["tbscName"]}" ${all?"width":"iwidth"}="${defines["tbscName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="tbscName" flagKey  title="客户名称">
			 	<a class="${desc=="tbsc_name" ? "desc" : ""}${asc=="tbsc_name" ? "asc" : ""}" href="?${desc=="tbsc_name" ? "asc=tbsc_name" : ""}${(asc=="tbsc_name" || desc!="tbsc_name" )? "desc=tbsc_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	客户名称
			 	</a>
			 	${lz:set("checkeds[]","tbscName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscBattle || #request.defines['tbscBattle']!=null">
			 <td <s:if test="#request.defines['tbscBattle']>0">colspan="${defines["tbscBattle"]}" ${all?"width":"iwidth"}="${defines["tbscBattle"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="tbscBattle"   title="归属战役">
			 	<a class="${desc=="tbsc_battle" ? "desc" : ""}${asc=="tbsc_battle" ? "asc" : ""}" href="?${desc=="tbsc_battle" ? "asc=tbsc_battle" : ""}${(asc=="tbsc_battle" || desc!="tbsc_battle" )? "desc=tbsc_battle" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	归属战役
			 	</a>
			 	${lz:set("checkeds[]","tbscBattle")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscEvent || #request.defines['tbscEvent']!=null">
			 <td <s:if test="#request.defines['tbscEvent']>0">colspan="${defines["tbscEvent"]}" ${all?"width":"iwidth"}="${defines["tbscEvent"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="tbscEvent"   title="归属活动">
			 	<a class="${desc=="tbsc_event" ? "desc" : ""}${asc=="tbsc_event" ? "asc" : ""}" href="?${desc=="tbsc_event" ? "asc=tbsc_event" : ""}${(asc=="tbsc_event" || desc!="tbsc_event" )? "desc=tbsc_event" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	归属活动
			 	</a>
			 	${lz:set("checkeds[]","tbscEvent")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscGroup || #request.defines['tbscGroup']!=null">
			 <td <s:if test="#request.defines['tbscGroup']>0">colspan="${defines["tbscGroup"]}" ${all?"width":"iwidth"}="${defines["tbscGroup"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="tbscGroup"   title="所属组织">
			 	<a class="${desc=="tbsc_group" ? "desc" : ""}${asc=="tbsc_group" ? "asc" : ""}" href="?${desc=="tbsc_group" ? "asc=tbsc_group" : ""}${(asc=="tbsc_group" || desc!="tbsc_group" )? "desc=tbsc_group" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属组织
			 	</a>
			 	${lz:set("checkeds[]","tbscGroup")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscMobile || #request.defines['tbscMobile']!=null">
			 <td <s:if test="#request.defines['tbscMobile']>0">colspan="${defines["tbscMobile"]}" ${all?"width":"iwidth"}="${defines["tbscMobile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="tbscMobile"   title="联系手机">
			 	<a class="${desc=="tbsc_mobile" ? "desc" : ""}${asc=="tbsc_mobile" ? "asc" : ""}" href="?${desc=="tbsc_mobile" ? "asc=tbsc_mobile" : ""}${(asc=="tbsc_mobile" || desc!="tbsc_mobile" )? "desc=tbsc_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系手机
			 	</a>
			 	${lz:set("checkeds[]","tbscMobile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscNumber || #request.defines['tbscNumber']!=null">
			 <td <s:if test="#request.defines['tbscNumber']>0">colspan="${defines["tbscNumber"]}" ${all?"width":"iwidth"}="${defines["tbscNumber"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="8" ref="tbscNumber"   title="联系电话">
			 	<a class="${desc=="tbsc_number" ? "desc" : ""}${asc=="tbsc_number" ? "asc" : ""}" href="?${desc=="tbsc_number" ? "asc=tbsc_number" : ""}${(asc=="tbsc_number" || desc!="tbsc_number" )? "desc=tbsc_number" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系电话
			 	</a>
			 	${lz:set("checkeds[]","tbscNumber")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscEmail || #request.defines['tbscEmail']!=null">
			 <td <s:if test="#request.defines['tbscEmail']>0">colspan="${defines["tbscEmail"]}" ${all?"width":"iwidth"}="${defines["tbscEmail"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="tbscEmail"   title="联系邮箱">
			 	<a class="${desc=="tbsc_email" ? "desc" : ""}${asc=="tbsc_email" ? "asc" : ""}" href="?${desc=="tbsc_email" ? "asc=tbsc_email" : ""}${(asc=="tbsc_email" || desc!="tbsc_email" )? "desc=tbsc_email" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系邮箱
			 	</a>
			 	${lz:set("checkeds[]","tbscEmail")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscQq || #request.defines['tbscQq']!=null">
			 <td <s:if test="#request.defines['tbscQq']>0">colspan="${defines["tbscQq"]}" ${all?"width":"iwidth"}="${defines["tbscQq"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="10" ref="tbscQq"   title="联系QQ">
			 	<a class="${desc=="tbsc_qq" ? "desc" : ""}${asc=="tbsc_qq" ? "asc" : ""}" href="?${desc=="tbsc_qq" ? "asc=tbsc_qq" : ""}${(asc=="tbsc_qq" || desc!="tbsc_qq" )? "desc=tbsc_qq" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系QQ
			 	</a>
			 	${lz:set("checkeds[]","tbscQq")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscOthrer || #request.defines['tbscOthrer']!=null">
			 <td <s:if test="#request.defines['tbscOthrer']>0">colspan="${defines["tbscOthrer"]}" ${all?"width":"iwidth"}="${defines["tbscOthrer"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="tbscOthrer"   title="其它联系方式">
			 	<a class="${desc=="tbsc_othrer" ? "desc" : ""}${asc=="tbsc_othrer" ? "asc" : ""}" href="?${desc=="tbsc_othrer" ? "asc=tbsc_othrer" : ""}${(asc=="tbsc_othrer" || desc!="tbsc_othrer" )? "desc=tbsc_othrer" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	其它联系方式
			 	</a>
			 	${lz:set("checkeds[]","tbscOthrer")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscProfile || #request.defines['tbscProfile']!=null">
			 <td <s:if test="#request.defines['tbscProfile']>0">colspan="${defines["tbscProfile"]}" ${all?"width":"iwidth"}="${defines["tbscProfile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="tbscProfile"   title="业务方向">
			 	<a class="${desc=="tbsc_profile" ? "desc" : ""}${asc=="tbsc_profile" ? "asc" : ""}" href="?${desc=="tbsc_profile" ? "asc=tbsc_profile" : ""}${(asc=="tbsc_profile" || desc!="tbsc_profile" )? "desc=tbsc_profile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	业务方向
			 	</a>
			 	${lz:set("checkeds[]","tbscProfile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscLevel || #request.defines['tbscLevel']!=null">
			 <td <s:if test="#request.defines['tbscLevel']>0">colspan="${defines["tbscLevel"]}" ${all?"width":"iwidth"}="${defines["tbscLevel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="tbscLevel"   title="会员级别">
			 	<a class="${desc=="tbsc_level" ? "desc" : ""}${asc=="tbsc_level" ? "asc" : ""}" href="?${desc=="tbsc_level" ? "asc=tbsc_level" : ""}${(asc=="tbsc_level" || desc!="tbsc_level" )? "desc=tbsc_level" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	会员级别
			 	</a>
			 	${lz:set("checkeds[]","tbscLevel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscFormal || #request.defines['tbscFormal']!=null">
			 <td <s:if test="#request.defines['tbscFormal']>0">colspan="${defines["tbscFormal"]}" ${all?"width":"iwidth"}="${defines["tbscFormal"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="tbscFormal"   title="意向阶段">
			 	<a class="${desc=="tbsc_formal" ? "desc" : ""}${asc=="tbsc_formal" ? "asc" : ""}" href="?${desc=="tbsc_formal" ? "asc=tbsc_formal" : ""}${(asc=="tbsc_formal" || desc!="tbsc_formal" )? "desc=tbsc_formal" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	意向阶段
			 	</a>
			 	${lz:set("checkeds[]","tbscFormal")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscServer || #request.defines['tbscServer']!=null">
			 <td <s:if test="#request.defines['tbscServer']>0">colspan="${defines["tbscServer"]}" ${all?"width":"iwidth"}="${defines["tbscServer"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="tbscServer"   title="服务阶段">
			 	<a class="${desc=="tbsc_server" ? "desc" : ""}${asc=="tbsc_server" ? "asc" : ""}" href="?${desc=="tbsc_server" ? "asc=tbsc_server" : ""}${(asc=="tbsc_server" || desc!="tbsc_server" )? "desc=tbsc_server" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	服务阶段
			 	</a>
			 	${lz:set("checkeds[]","tbscServer")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscOnwer || #request.defines['tbscOnwer']!=null">
			 <td <s:if test="#request.defines['tbscOnwer']>0">colspan="${defines["tbscOnwer"]}" ${all?"width":"iwidth"}="${defines["tbscOnwer"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="16" ref="tbscOnwer"   title="所有人">
			 	<a class="${desc=="tbsc_onwer" ? "desc" : ""}${asc=="tbsc_onwer" ? "asc" : ""}" href="?${desc=="tbsc_onwer" ? "asc=tbsc_onwer" : ""}${(asc=="tbsc_onwer" || desc!="tbsc_onwer" )? "desc=tbsc_onwer" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所有人
			 	</a>
			 	${lz:set("checkeds[]","tbscOnwer")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscAdder || #request.defines['tbscAdder']!=null">
			 <td <s:if test="#request.defines['tbscAdder']>0">colspan="${defines["tbscAdder"]}" ${all?"width":"iwidth"}="${defines["tbscAdder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="17" ref="tbscAdder"   title="添加人">
			 	<a class="${desc=="tbsc_adder" ? "desc" : ""}${asc=="tbsc_adder" ? "asc" : ""}" href="?${desc=="tbsc_adder" ? "asc=tbsc_adder" : ""}${(asc=="tbsc_adder" || desc!="tbsc_adder" )? "desc=tbsc_adder" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加人
			 	</a>
			 	${lz:set("checkeds[]","tbscAdder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscRemark || #request.defines['tbscRemark']!=null">
			 <td <s:if test="#request.defines['tbscRemark']>0">colspan="${defines["tbscRemark"]}" ${all?"width":"iwidth"}="${defines["tbscRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="18" ref="tbscRemark"   title="备注">
			 	<a class="${desc=="tbsc_remark" ? "desc" : ""}${asc=="tbsc_remark" ? "asc" : ""}" href="?${desc=="tbsc_remark" ? "asc=tbsc_remark" : ""}${(asc=="tbsc_remark" || desc!="tbsc_remark" )? "desc=tbsc_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 	${lz:set("checkeds[]","tbscRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscMemo || #request.defines['tbscMemo']!=null">
			 <td <s:if test="#request.defines['tbscMemo']>0">colspan="${defines["tbscMemo"]}" ${all?"width":"iwidth"}="${defines["tbscMemo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="19" ref="tbscMemo"   title="备忘信息">
			 	<a class="${desc=="tbsc_memo" ? "desc" : ""}${asc=="tbsc_memo" ? "asc" : ""}" href="?${desc=="tbsc_memo" ? "asc=tbsc_memo" : ""}${(asc=="tbsc_memo" || desc!="tbsc_memo" )? "desc=tbsc_memo" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备忘信息
			 	</a>
			 	${lz:set("checkeds[]","tbscMemo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscData || #request.defines['tbscData']!=null">
			 <td <s:if test="#request.defines['tbscData']>0">colspan="${defines["tbscData"]}" ${all?"width":"iwidth"}="${defines["tbscData"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="20" ref="tbscData"   title="程序数据">
			 	<a class="${desc=="tbsc_data" ? "desc" : ""}${asc=="tbsc_data" ? "asc" : ""}" href="?${desc=="tbsc_data" ? "asc=tbsc_data" : ""}${(asc=="tbsc_data" || desc!="tbsc_data" )? "desc=tbsc_data" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	程序数据
			 	</a>
			 	${lz:set("checkeds[]","tbscData")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscUpdateTime || #request.defines['tbscUpdateTime']!=null">
			 <td <s:if test="#request.defines['tbscUpdateTime']>0">colspan="${defines["tbscUpdateTime"]}" ${all?"width":"iwidth"}="${defines["tbscUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="21" ref="tbscUpdateTime"   title="修改时间">
			 	<a class="${desc=="tbsc_update_time" ? "desc" : ""}${asc=="tbsc_update_time" ? "asc" : ""}" href="?${desc=="tbsc_update_time" ? "asc=tbsc_update_time" : ""}${(asc=="tbsc_update_time" || desc!="tbsc_update_time" )? "desc=tbsc_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","tbscUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscAddTime || #request.defines['tbscAddTime']!=null">
			 <td <s:if test="#request.defines['tbscAddTime']>0">colspan="${defines["tbscAddTime"]}" ${all?"width":"iwidth"}="${defines["tbscAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="22" ref="tbscAddTime"   title="添加时间">
			 	<a class="${desc=="tbsc_add_time" ? "desc" : ""}${asc=="tbsc_add_time" ? "asc" : ""}" href="?${desc=="tbsc_add_time" ? "asc=tbsc_add_time" : ""}${(asc=="tbsc_add_time" || desc!="tbsc_add_time" )? "desc=tbsc_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","tbscAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbscStatus || #request.defines['tbscStatus']!=null">
			 <td <s:if test="#request.defines['tbscStatus']>0">colspan="${defines["tbscStatus"]}" ${all?"width":"iwidth"}="${defines["tbscStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="23" ref="tbscStatus"   title="状态">
			 	<a class="${desc=="tbsc_status" ? "desc" : ""}${asc=="tbsc_status" ? "asc" : ""}" href="?${desc=="tbsc_status" ? "asc=tbsc_status" : ""}${(asc=="tbsc_status" || desc!="tbsc_status" )? "desc=tbsc_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","tbscStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="24" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="25" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddCustom()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.tbscId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.tbscId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscId || #request.defines['tbscId']!=null">
				<s:if test="#request.defines['tbscId']>0">
					${lz:set("注释","****tbscId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscId",lz:indexOf(fieldName,"tbscId")>-1)}
				  		<s:if test="#request.atTbscId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscId字段的字串格式化输出****")}
					<td ref="tbscId" class="td ">
						 
						 	${lz:or(item$tbscId[i.count-1],lz:left(item.tbscId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscHost || #request.defines['tbscHost']!=null">
				<s:if test="#request.defines['tbscHost']>0">
					${lz:set("注释","****tbscHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscHost",lz:indexOf(fieldName,"tbscHost")>-1)}
				  		<s:if test="#request.atTbscHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscHost字段的字串格式化输出****")}
					<td ref="tbscHost" class="td " relate="${item.tbscHost}">
						 
						 	<a <s:if test="#item.tbscHost!=null && #item.tbscHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.tbscHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbscHost[i.count-1],lz:left(item.tbscHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscName || #request.defines['tbscName']!=null">
				<s:if test="#request.defines['tbscName']>0">
					${lz:set("注释","****tbscName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscName",lz:indexOf(fieldName,"tbscName")>-1)}
				  		<s:if test="#request.atTbscName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscName字段的字串格式化输出****")}
					<td ref="tbscName" class="td  node">
						 
						 	${lz:or(item$tbscName[i.count-1],lz:left(item.tbscName$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscBattle || #request.defines['tbscBattle']!=null">
				<s:if test="#request.defines['tbscBattle']>0">
					${lz:set("注释","****tbscBattle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscBattle))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscBattle",lz:indexOf(fieldName,"tbscBattle")>-1)}
				  		<s:if test="#request.atTbscBattle==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscBattle字段的字串格式化输出****")}
					<td ref="tbscBattle" class="td " relate="${item.tbscBattle}">
						 
						 	<a <s:if test="#item.tbscBattle!=null && #item.tbscBattle!=''"> onclick="window.href('${basePath}${proname}/sale/battle_details.do?key=${item.tbscBattle}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbscBattle[i.count-1],lz:left(item.tbscBattle$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscEvent || #request.defines['tbscEvent']!=null">
				<s:if test="#request.defines['tbscEvent']>0">
					${lz:set("注释","****tbscEvent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscEvent))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscEvent",lz:indexOf(fieldName,"tbscEvent")>-1)}
				  		<s:if test="#request.atTbscEvent==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscEvent字段的字串格式化输出****")}
					<td ref="tbscEvent" class="td " relate="${item.tbscEvent}">
						 
						 	<a <s:if test="#item.tbscEvent!=null && #item.tbscEvent!=''"> onclick="window.href('${basePath}${proname}/sale/event_details.do?key=${item.tbscEvent}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbscEvent[i.count-1],lz:left(item.tbscEvent$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscGroup || #request.defines['tbscGroup']!=null">
				<s:if test="#request.defines['tbscGroup']>0">
					${lz:set("注释","****tbscGroup关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscGroup))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscGroup",lz:indexOf(fieldName,"tbscGroup")>-1)}
				  		<s:if test="#request.atTbscGroup==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscGroup字段的字串格式化输出****")}
					<td ref="tbscGroup" class="td " relate="${item.tbscGroup}">
						 
						 	<a <s:if test="#item.tbscGroup!=null && #item.tbscGroup!=''"> onclick="window.href('${basePath}${proname}/sale/customgroup_details.do?key=${item.tbscGroup}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbscGroup[i.count-1],lz:left(item.tbscGroup$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscMobile || #request.defines['tbscMobile']!=null">
				<s:if test="#request.defines['tbscMobile']>0">
					${lz:set("注释","****tbscMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscMobile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscMobile",lz:indexOf(fieldName,"tbscMobile")>-1)}
				  		<s:if test="#request.atTbscMobile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscMobile字段的字串格式化输出****")}
					<td ref="tbscMobile" class="td ">
						 
						 	${lz:or(item$tbscMobile[i.count-1],lz:left(item.tbscMobile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscNumber || #request.defines['tbscNumber']!=null">
				<s:if test="#request.defines['tbscNumber']>0">
					${lz:set("注释","****tbscNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscNumber))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscNumber",lz:indexOf(fieldName,"tbscNumber")>-1)}
				  		<s:if test="#request.atTbscNumber==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscNumber字段的字串格式化输出****")}
					<td ref="tbscNumber" class="td ">
						 
						 	${lz:or(item$tbscNumber[i.count-1],lz:left(item.tbscNumber$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscEmail || #request.defines['tbscEmail']!=null">
				<s:if test="#request.defines['tbscEmail']>0">
					${lz:set("注释","****tbscEmail关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscEmail))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscEmail",lz:indexOf(fieldName,"tbscEmail")>-1)}
				  		<s:if test="#request.atTbscEmail==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscEmail字段的字串格式化输出****")}
					<td ref="tbscEmail" class="td ">
						 
						 	${lz:or(item$tbscEmail[i.count-1],lz:left(item.tbscEmail$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscQq || #request.defines['tbscQq']!=null">
				<s:if test="#request.defines['tbscQq']>0">
					${lz:set("注释","****tbscQq关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscQq))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscQq",lz:indexOf(fieldName,"tbscQq")>-1)}
				  		<s:if test="#request.atTbscQq==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscQq字段的字串格式化输出****")}
					<td ref="tbscQq" class="td ">
						 
						 	${lz:or(item$tbscQq[i.count-1],lz:left(item.tbscQq$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscOthrer || #request.defines['tbscOthrer']!=null">
				<s:if test="#request.defines['tbscOthrer']>0">
					${lz:set("注释","****tbscOthrer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscOthrer))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscOthrer",lz:indexOf(fieldName,"tbscOthrer")>-1)}
				  		<s:if test="#request.atTbscOthrer==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscOthrer字段的字串格式化输出****")}
					<td ref="tbscOthrer" class="td ">
						 
						 	${lz:or(item$tbscOthrer[i.count-1],lz:left(item.tbscOthrer$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscProfile || #request.defines['tbscProfile']!=null">
				<s:if test="#request.defines['tbscProfile']>0">
					${lz:set("注释","****tbscProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscProfile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscProfile",lz:indexOf(fieldName,"tbscProfile")>-1)}
				  		<s:if test="#request.atTbscProfile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscProfile字段的字串格式化输出****")}
					<td ref="tbscProfile" class="td ">
						 
						 	${lz:or(item$tbscProfile[i.count-1],lz:left(item.tbscProfile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscLevel || #request.defines['tbscLevel']!=null">
				<s:if test="#request.defines['tbscLevel']>0">
					${lz:set("注释","****tbscLevel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscLevel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscLevel",lz:indexOf(fieldName,"tbscLevel")>-1)}
				  		<s:if test="#request.atTbscLevel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscLevel字段的字串格式化输出****")}
					<td ref="tbscLevel" class="td ">
						 
						 	${lz:or(item$tbscLevel[i.count-1],lz:left(item.tbscLevel$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscFormal || #request.defines['tbscFormal']!=null">
				<s:if test="#request.defines['tbscFormal']>0">
					${lz:set("注释","****tbscFormal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscFormal))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscFormal",lz:indexOf(fieldName,"tbscFormal")>-1)}
				  		<s:if test="#request.atTbscFormal==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscFormal字段的字串格式化输出****")}
					<td ref="tbscFormal" class="td ">
						 
						 	${lz:or(item$tbscFormal[i.count-1],lz:left(item.tbscFormal$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscServer || #request.defines['tbscServer']!=null">
				<s:if test="#request.defines['tbscServer']>0">
					${lz:set("注释","****tbscServer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscServer))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscServer",lz:indexOf(fieldName,"tbscServer")>-1)}
				  		<s:if test="#request.atTbscServer==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscServer字段的字串格式化输出****")}
					<td ref="tbscServer" class="td ">
						 
						 	${lz:or(item$tbscServer[i.count-1],lz:left(item.tbscServer$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscOnwer || #request.defines['tbscOnwer']!=null">
				<s:if test="#request.defines['tbscOnwer']>0">
					${lz:set("注释","****tbscOnwer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscOnwer))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscOnwer",lz:indexOf(fieldName,"tbscOnwer")>-1)}
				  		<s:if test="#request.atTbscOnwer==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscOnwer字段的字串格式化输出****")}
					<td ref="tbscOnwer" class="td " relate="${item.tbscOnwer}">
						 
						 	${lz:or(item$tbscOnwer[i.count-1],lz:left(item.tbscOnwer$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscAdder || #request.defines['tbscAdder']!=null">
				<s:if test="#request.defines['tbscAdder']>0">
					${lz:set("注释","****tbscAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscAdder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscAdder",lz:indexOf(fieldName,"tbscAdder")>-1)}
				  		<s:if test="#request.atTbscAdder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscAdder字段的字串格式化输出****")}
					<td ref="tbscAdder" class="td " relate="${item.tbscAdder}">
						 
						 	<a <s:if test="#item.tbscAdder!=null && #item.tbscAdder!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.tbscAdder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbscAdder[i.count-1],lz:left(item.tbscAdder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscRemark || #request.defines['tbscRemark']!=null">
				<s:if test="#request.defines['tbscRemark']>0">
					${lz:set("注释","****tbscRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscRemark",lz:indexOf(fieldName,"tbscRemark")>-1)}
				  		<s:if test="#request.atTbscRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscRemark字段的字串格式化输出****")}
					<td ref="tbscRemark" class="td ">
						 
						 	${lz:or(item$tbscRemark[i.count-1],lz:left(item.tbscRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscMemo || #request.defines['tbscMemo']!=null">
				<s:if test="#request.defines['tbscMemo']>0">
					${lz:set("注释","****tbscMemo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscMemo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscMemo",lz:indexOf(fieldName,"tbscMemo")>-1)}
				  		<s:if test="#request.atTbscMemo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscMemo字段的字串格式化输出****")}
					<td ref="tbscMemo" class="td ">
						 
						 	${lz:or(item$tbscMemo[i.count-1],lz:left(item.tbscMemo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscData || #request.defines['tbscData']!=null">
				<s:if test="#request.defines['tbscData']>0">
					${lz:set("注释","****tbscData关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscData))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscData",lz:indexOf(fieldName,"tbscData")>-1)}
				  		<s:if test="#request.atTbscData==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscData字段的字串格式化输出****")}
					<td ref="tbscData" class="td ">
						 
						 	${lz:or(item$tbscData[i.count-1],lz:left(item.tbscData$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscUpdateTime || #request.defines['tbscUpdateTime']!=null">
				<s:if test="#request.defines['tbscUpdateTime']>0">
					${lz:set("注释","****tbscUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscUpdateTime",lz:indexOf(fieldName,"tbscUpdateTime")>-1)}
				  		<s:if test="#request.atTbscUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscUpdateTime字段的字串格式化输出****")}
					<td ref="tbscUpdateTime" class="td ">
						 
						 	${lz:or(item$tbscUpdateTime[i.count-1],lz:left(item.tbscUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscAddTime || #request.defines['tbscAddTime']!=null">
				<s:if test="#request.defines['tbscAddTime']>0">
					${lz:set("注释","****tbscAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscAddTime",lz:indexOf(fieldName,"tbscAddTime")>-1)}
				  		<s:if test="#request.atTbscAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscAddTime字段的字串格式化输出****")}
					<td ref="tbscAddTime" class="td ">
						 
						 	${lz:or(item$tbscAddTime[i.count-1],lz:left(item.tbscAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbscStatus || #request.defines['tbscStatus']!=null">
				<s:if test="#request.defines['tbscStatus']>0">
					${lz:set("注释","****tbscStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbscStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbscStatus",lz:indexOf(fieldName,"tbscStatus")>-1)}
				  		<s:if test="#request.atTbscStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbscStatus字段的字串格式化输出****")}
					<td ref="tbscStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.tbscId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$tbscStatus[i.count-1],lz:left(item.tbscStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.tbscId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectCustom('${item.tbscId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditCustom('${item.tbscId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelCustom('${item.tbscId}','${item.tbscName}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsCustom('${item.tbscId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.tbscId || #request.defines['tbscId']!=null">
				<s:if test="#request.defines['tbscId']>0">
					${lz:set("注释","****tbscId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscId",lz:indexOf(fieldName,"tbscId")>-1)}				  		
				  		<s:if test="#request.atTbscId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscHost || #request.defines['tbscHost']!=null">
				<s:if test="#request.defines['tbscHost']>0">
					${lz:set("注释","****tbscHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscHost",lz:indexOf(fieldName,"tbscHost")>-1)}				  		
				  		<s:if test="#request.atTbscHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscName || #request.defines['tbscName']!=null">
				<s:if test="#request.defines['tbscName']>0">
					${lz:set("注释","****tbscName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscName",lz:indexOf(fieldName,"tbscName")>-1)}				  		
				  		<s:if test="#request.atTbscName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscBattle || #request.defines['tbscBattle']!=null">
				<s:if test="#request.defines['tbscBattle']>0">
					${lz:set("注释","****tbscBattle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscBattle",lz:indexOf(fieldName,"tbscBattle")>-1)}				  		
				  		<s:if test="#request.atTbscBattle==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscBattle[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscEvent || #request.defines['tbscEvent']!=null">
				<s:if test="#request.defines['tbscEvent']>0">
					${lz:set("注释","****tbscEvent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscEvent",lz:indexOf(fieldName,"tbscEvent")>-1)}				  		
				  		<s:if test="#request.atTbscEvent==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscEvent[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscGroup || #request.defines['tbscGroup']!=null">
				<s:if test="#request.defines['tbscGroup']>0">
					${lz:set("注释","****tbscGroup关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscGroup",lz:indexOf(fieldName,"tbscGroup")>-1)}				  		
				  		<s:if test="#request.atTbscGroup==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscGroup[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscMobile || #request.defines['tbscMobile']!=null">
				<s:if test="#request.defines['tbscMobile']>0">
					${lz:set("注释","****tbscMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscMobile",lz:indexOf(fieldName,"tbscMobile")>-1)}				  		
				  		<s:if test="#request.atTbscMobile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscMobile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscNumber || #request.defines['tbscNumber']!=null">
				<s:if test="#request.defines['tbscNumber']>0">
					${lz:set("注释","****tbscNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscNumber",lz:indexOf(fieldName,"tbscNumber")>-1)}				  		
				  		<s:if test="#request.atTbscNumber==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscNumber[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscEmail || #request.defines['tbscEmail']!=null">
				<s:if test="#request.defines['tbscEmail']>0">
					${lz:set("注释","****tbscEmail关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscEmail",lz:indexOf(fieldName,"tbscEmail")>-1)}				  		
				  		<s:if test="#request.atTbscEmail==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscEmail[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscQq || #request.defines['tbscQq']!=null">
				<s:if test="#request.defines['tbscQq']>0">
					${lz:set("注释","****tbscQq关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscQq",lz:indexOf(fieldName,"tbscQq")>-1)}				  		
				  		<s:if test="#request.atTbscQq==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscQq[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscOthrer || #request.defines['tbscOthrer']!=null">
				<s:if test="#request.defines['tbscOthrer']>0">
					${lz:set("注释","****tbscOthrer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscOthrer",lz:indexOf(fieldName,"tbscOthrer")>-1)}				  		
				  		<s:if test="#request.atTbscOthrer==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscOthrer[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscProfile || #request.defines['tbscProfile']!=null">
				<s:if test="#request.defines['tbscProfile']>0">
					${lz:set("注释","****tbscProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscProfile",lz:indexOf(fieldName,"tbscProfile")>-1)}				  		
				  		<s:if test="#request.atTbscProfile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscProfile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscLevel || #request.defines['tbscLevel']!=null">
				<s:if test="#request.defines['tbscLevel']>0">
					${lz:set("注释","****tbscLevel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscLevel",lz:indexOf(fieldName,"tbscLevel")>-1)}				  		
				  		<s:if test="#request.atTbscLevel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscLevel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscFormal || #request.defines['tbscFormal']!=null">
				<s:if test="#request.defines['tbscFormal']>0">
					${lz:set("注释","****tbscFormal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscFormal",lz:indexOf(fieldName,"tbscFormal")>-1)}				  		
				  		<s:if test="#request.atTbscFormal==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscFormal[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscServer || #request.defines['tbscServer']!=null">
				<s:if test="#request.defines['tbscServer']>0">
					${lz:set("注释","****tbscServer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscServer",lz:indexOf(fieldName,"tbscServer")>-1)}				  		
				  		<s:if test="#request.atTbscServer==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscServer[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscOnwer || #request.defines['tbscOnwer']!=null">
				<s:if test="#request.defines['tbscOnwer']>0">
					${lz:set("注释","****tbscOnwer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscOnwer",lz:indexOf(fieldName,"tbscOnwer")>-1)}				  		
				  		<s:if test="#request.atTbscOnwer==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscOnwer[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscAdder || #request.defines['tbscAdder']!=null">
				<s:if test="#request.defines['tbscAdder']>0">
					${lz:set("注释","****tbscAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscAdder",lz:indexOf(fieldName,"tbscAdder")>-1)}				  		
				  		<s:if test="#request.atTbscAdder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscAdder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscRemark || #request.defines['tbscRemark']!=null">
				<s:if test="#request.defines['tbscRemark']>0">
					${lz:set("注释","****tbscRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscRemark",lz:indexOf(fieldName,"tbscRemark")>-1)}				  		
				  		<s:if test="#request.atTbscRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscMemo || #request.defines['tbscMemo']!=null">
				<s:if test="#request.defines['tbscMemo']>0">
					${lz:set("注释","****tbscMemo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscMemo",lz:indexOf(fieldName,"tbscMemo")>-1)}				  		
				  		<s:if test="#request.atTbscMemo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscMemo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscData || #request.defines['tbscData']!=null">
				<s:if test="#request.defines['tbscData']>0">
					${lz:set("注释","****tbscData关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscData",lz:indexOf(fieldName,"tbscData")>-1)}				  		
				  		<s:if test="#request.atTbscData==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscData[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscUpdateTime || #request.defines['tbscUpdateTime']!=null">
				<s:if test="#request.defines['tbscUpdateTime']>0">
					${lz:set("注释","****tbscUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscUpdateTime",lz:indexOf(fieldName,"tbscUpdateTime")>-1)}				  		
				  		<s:if test="#request.atTbscUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscAddTime || #request.defines['tbscAddTime']!=null">
				<s:if test="#request.defines['tbscAddTime']>0">
					${lz:set("注释","****tbscAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscAddTime",lz:indexOf(fieldName,"tbscAddTime")>-1)}				  		
				  		<s:if test="#request.atTbscAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbscStatus || #request.defines['tbscStatus']!=null">
				<s:if test="#request.defines['tbscStatus']>0">
					${lz:set("注释","****tbscStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbscStatus",lz:indexOf(fieldName,"tbscStatus")>-1)}				  		
				  		<s:if test="#request.atTbscStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbscStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加客户资料
	**/
	function AddCustom(parent){		
		var url = "${basePath}${namespace}custom_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加客户资料'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择客户资料
	**/
	function SelectCustom(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改客户资料
	**/
	function EditCustom(id){
		var url = "${basePath}${namespace}custom_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改客户资料'}":lz:json(ctrl)}};
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
		EditCustom(checker.val());
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
				var url = "${basePath}${namespace}custom_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新客户资料",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}custom_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新客户资料",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改客户资料
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}custom_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新客户资料",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["tbscStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改客户资料任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}custom_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新客户资料",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}custom_edit.do";
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
			var url = "${basePath}${namespace}custom_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除客户资料
	**/
	function DelCustom(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除客户资料["+flag+"]吗？</span>",
				title:"删除客户资料",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}custom_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选客户资料吗？</span>",
				title:"批量删除客户资料",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}custom_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示客户资料详情
	**/
	function DetailsCustom(id)
	{
		var url = "${basePath}${namespace}custom_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"客户资料详情",editable:false,visible:true}};
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
		DetailsCustom(checker.val());
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