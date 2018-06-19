<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/gift.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csGift.csgId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csgId,csgHost,csgMember,csgSysPack,csgUserPack,csgGoods,csgOutlets,csgModel,csgType,csgScript,csgUntilTime,csgUpdateTime,csgAddTime,csgStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csgId,csgHost,csgName,csgMember,csgSysPack,csgUserPack,csgGoods,csgOutlets,csgModel,csgType,csgCount,csgRemain,csgPrice,csgRebate,csgScript,csgUntilTime,csgUpdateTime,csgAddTime,csgStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csgId,csgHost,csgMember,csgSysPack,csgGoods,csgModel,csgType,csgUntilTime,csgStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csgId,csgHost,csgName,csgMember,csgSysPack,csgGoods,csgModel,csgType,csgRemain,csgPrice,csgRebate,csgUntilTime,csgStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>优惠内容管理</title>
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
	<span class="caption">${empty title?"优惠内容管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsGift" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="gift.query.jsp"%>

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
			<dl ref="com.ccclubs.model.CsGift" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsGift" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.csgUntilTime || #request.defines['csgUntilTime']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('until')">有效期至</a>
					</s:if>
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table
				</dd>
			</dl>
			</s:if>
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="gift_table" ref="CsGift" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgId || #request.defines['csgId']!=null">
			 <td <s:if test="#request.defines['csgId']>0">colspan="${defines["csgId"]}" ${all?"width":"iwidth"}="${defines["csgId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csgId"   title="编号">
			 	<a class="${desc=="csg_id" ? "desc" : ""}${asc=="csg_id" ? "asc" : ""}" href="?${desc=="csg_id" ? "asc=csg_id" : ""}${(asc=="csg_id" || desc!="csg_id" )? "desc=csg_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csgId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgHost || #request.defines['csgHost']!=null">
			 <td <s:if test="#request.defines['csgHost']>0">colspan="${defines["csgHost"]}" ${all?"width":"iwidth"}="${defines["csgHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csgHost"   title="城市">
			 	<a class="${desc=="csg_host" ? "desc" : ""}${asc=="csg_host" ? "asc" : ""}" href="?${desc=="csg_host" ? "asc=csg_host" : ""}${(asc=="csg_host" || desc!="csg_host" )? "desc=csg_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csgHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgName || #request.defines['csgName']!=null">
			 <td <s:if test="#request.defines['csgName']>0">colspan="${defines["csgName"]}" ${all?"width":"iwidth"}="${defines["csgName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="csgName" flagKey  title="优惠名称">
			 	<a class="${desc=="csg_name" ? "desc" : ""}${asc=="csg_name" ? "asc" : ""}" href="?${desc=="csg_name" ? "asc=csg_name" : ""}${(asc=="csg_name" || desc!="csg_name" )? "desc=csg_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优惠名称
			 	</a>
			 	${lz:set("checkeds[]","csgName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgMember || #request.defines['csgMember']!=null">
			 <td <s:if test="#request.defines['csgMember']>0">colspan="${defines["csgMember"]}" ${all?"width":"iwidth"}="${defines["csgMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csgMember"   title="所属会员">
			 	<a class="${desc=="csg_member" ? "desc" : ""}${asc=="csg_member" ? "asc" : ""}" href="?${desc=="csg_member" ? "asc=csg_member" : ""}${(asc=="csg_member" || desc!="csg_member" )? "desc=csg_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属会员
			 	</a>
			 	${lz:set("checkeds[]","csgMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgSysPack || #request.defines['csgSysPack']!=null">
			 <td <s:if test="#request.defines['csgSysPack']>0">colspan="${defines["csgSysPack"]}" ${all?"width":"iwidth"}="${defines["csgSysPack"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csgSysPack"   title="系统套餐">
			 	<a class="${desc=="csg_sys_pack" ? "desc" : ""}${asc=="csg_sys_pack" ? "asc" : ""}" href="?${desc=="csg_sys_pack" ? "asc=csg_sys_pack" : ""}${(asc=="csg_sys_pack" || desc!="csg_sys_pack" )? "desc=csg_sys_pack" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	系统套餐
			 	</a>
			 	${lz:set("checkeds[]","csgSysPack")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgUserPack || #request.defines['csgUserPack']!=null">
			 <td <s:if test="#request.defines['csgUserPack']>0">colspan="${defines["csgUserPack"]}" ${all?"width":"iwidth"}="${defines["csgUserPack"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csgUserPack"   title="会员套餐">
			 	<a class="${desc=="csg_user_pack" ? "desc" : ""}${asc=="csg_user_pack" ? "asc" : ""}" href="?${desc=="csg_user_pack" ? "asc=csg_user_pack" : ""}${(asc=="csg_user_pack" || desc!="csg_user_pack" )? "desc=csg_user_pack" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	会员套餐
			 	</a>
			 	${lz:set("checkeds[]","csgUserPack")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgGoods || #request.defines['csgGoods']!=null">
			 <td <s:if test="#request.defines['csgGoods']>0">colspan="${defines["csgGoods"]}" ${all?"width":"iwidth"}="${defines["csgGoods"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csgGoods"   title="所属商品">
			 	<a class="${desc=="csg_goods" ? "desc" : ""}${asc=="csg_goods" ? "asc" : ""}" href="?${desc=="csg_goods" ? "asc=csg_goods" : ""}${(asc=="csg_goods" || desc!="csg_goods" )? "desc=csg_goods" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属商品
			 	</a>
			 	${lz:set("checkeds[]","csgGoods")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgOutlets || #request.defines['csgOutlets']!=null">
			 <td <s:if test="#request.defines['csgOutlets']>0">colspan="${defines["csgOutlets"]}" ${all?"width":"iwidth"}="${defines["csgOutlets"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csgOutlets"   title="所属网点">
			 	<a class="${desc=="csg_outlets" ? "desc" : ""}${asc=="csg_outlets" ? "asc" : ""}" href="?${desc=="csg_outlets" ? "asc=csg_outlets" : ""}${(asc=="csg_outlets" || desc!="csg_outlets" )? "desc=csg_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属网点
			 	</a>
			 	${lz:set("checkeds[]","csgOutlets")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgModel || #request.defines['csgModel']!=null">
			 <td <s:if test="#request.defines['csgModel']>0">colspan="${defines["csgModel"]}" ${all?"width":"iwidth"}="${defines["csgModel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="csgModel"   title="所属车型">
			 	<a class="${desc=="csg_model" ? "desc" : ""}${asc=="csg_model" ? "asc" : ""}" href="?${desc=="csg_model" ? "asc=csg_model" : ""}${(asc=="csg_model" || desc!="csg_model" )? "desc=csg_model" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属车型
			 	</a>
			 	${lz:set("checkeds[]","csgModel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgType || #request.defines['csgType']!=null">
			 <td <s:if test="#request.defines['csgType']>0">colspan="${defines["csgType"]}" ${all?"width":"iwidth"}="${defines["csgType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csgType"   title="优惠类型">
			 	<a class="${desc=="csg_type" ? "desc" : ""}${asc=="csg_type" ? "asc" : ""}" href="?${desc=="csg_type" ? "asc=csg_type" : ""}${(asc=="csg_type" || desc!="csg_type" )? "desc=csg_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优惠类型
			 	</a>
			 	${lz:set("checkeds[]","csgType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgCount || #request.defines['csgCount']!=null">
			 <td <s:if test="#request.defines['csgCount']>0">colspan="${defines["csgCount"]}" ${all?"width":"iwidth"}="${defines["csgCount"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="csgCount"   title="原始数量">
			 	<a class="${desc=="csg_count" ? "desc" : ""}${asc=="csg_count" ? "asc" : ""}" href="?${desc=="csg_count" ? "asc=csg_count" : ""}${(asc=="csg_count" || desc!="csg_count" )? "desc=csg_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	原始数量
			 	</a>
			 	${lz:set("checkeds[]","csgCount")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgRemain || #request.defines['csgRemain']!=null">
			 <td <s:if test="#request.defines['csgRemain']>0">colspan="${defines["csgRemain"]}" ${all?"width":"iwidth"}="${defines["csgRemain"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="csgRemain"   title="可用数量">
			 	<a class="${desc=="csg_remain" ? "desc" : ""}${asc=="csg_remain" ? "asc" : ""}" href="?${desc=="csg_remain" ? "asc=csg_remain" : ""}${(asc=="csg_remain" || desc!="csg_remain" )? "desc=csg_remain" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	可用数量
			 	</a>
			 	${lz:set("checkeds[]","csgRemain")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgPrice || #request.defines['csgPrice']!=null">
			 <td <s:if test="#request.defines['csgPrice']>0">colspan="${defines["csgPrice"]}" ${all?"width":"iwidth"}="${defines["csgPrice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="csgPrice"   title="优惠价格">
			 	<a class="${desc=="csg_price" ? "desc" : ""}${asc=="csg_price" ? "asc" : ""}" href="?${desc=="csg_price" ? "asc=csg_price" : ""}${(asc=="csg_price" || desc!="csg_price" )? "desc=csg_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优惠价格
			 	</a>
			 	${lz:set("checkeds[]","csgPrice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgRebate || #request.defines['csgRebate']!=null">
			 <td <s:if test="#request.defines['csgRebate']>0">colspan="${defines["csgRebate"]}" ${all?"width":"iwidth"}="${defines["csgRebate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="csgRebate"   title="优惠折扣">
			 	<a class="${desc=="csg_rebate" ? "desc" : ""}${asc=="csg_rebate" ? "asc" : ""}" href="?${desc=="csg_rebate" ? "asc=csg_rebate" : ""}${(asc=="csg_rebate" || desc!="csg_rebate" )? "desc=csg_rebate" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优惠折扣
			 	</a>
			 	${lz:set("checkeds[]","csgRebate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgScript || #request.defines['csgScript']!=null">
			 <td <s:if test="#request.defines['csgScript']>0">colspan="${defines["csgScript"]}" ${all?"width":"iwidth"}="${defines["csgScript"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="15" ref="csgScript"   title="函数脚本">
			 	<a class="${desc=="csg_script" ? "desc" : ""}${asc=="csg_script" ? "asc" : ""}" href="?${desc=="csg_script" ? "asc=csg_script" : ""}${(asc=="csg_script" || desc!="csg_script" )? "desc=csg_script" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	函数脚本
			 	</a>
			 	${lz:set("checkeds[]","csgScript")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgUntilTime || #request.defines['csgUntilTime']!=null">
			 <td <s:if test="#request.defines['csgUntilTime']>0">colspan="${defines["csgUntilTime"]}" ${all?"width":"iwidth"}="${defines["csgUntilTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="16" ref="csgUntilTime"   title="有效期至">
			 	<a class="${desc=="csg_until_time" ? "desc" : ""}${asc=="csg_until_time" ? "asc" : ""}" href="?${desc=="csg_until_time" ? "asc=csg_until_time" : ""}${(asc=="csg_until_time" || desc!="csg_until_time" )? "desc=csg_until_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	有效期至
			 	</a>
			 	${lz:set("checkeds[]","csgUntilTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgUpdateTime || #request.defines['csgUpdateTime']!=null">
			 <td <s:if test="#request.defines['csgUpdateTime']>0">colspan="${defines["csgUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csgUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="17" ref="csgUpdateTime"   title="修改时间">
			 	<a class="${desc=="csg_update_time" ? "desc" : ""}${asc=="csg_update_time" ? "asc" : ""}" href="?${desc=="csg_update_time" ? "asc=csg_update_time" : ""}${(asc=="csg_update_time" || desc!="csg_update_time" )? "desc=csg_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csgUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgAddTime || #request.defines['csgAddTime']!=null">
			 <td <s:if test="#request.defines['csgAddTime']>0">colspan="${defines["csgAddTime"]}" ${all?"width":"iwidth"}="${defines["csgAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="18" ref="csgAddTime"   title="添加时间">
			 	<a class="${desc=="csg_add_time" ? "desc" : ""}${asc=="csg_add_time" ? "asc" : ""}" href="?${desc=="csg_add_time" ? "asc=csg_add_time" : ""}${(asc=="csg_add_time" || desc!="csg_add_time" )? "desc=csg_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csgAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csgStatus || #request.defines['csgStatus']!=null">
			 <td <s:if test="#request.defines['csgStatus']>0">colspan="${defines["csgStatus"]}" ${all?"width":"iwidth"}="${defines["csgStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="19" ref="csgStatus"   title="状态">
			 	<a class="${desc=="csg_status" ? "desc" : ""}${asc=="csg_status" ? "asc" : ""}" href="?${desc=="csg_status" ? "asc=csg_status" : ""}${(asc=="csg_status" || desc!="csg_status" )? "desc=csg_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csgStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="20" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="21" ref="operation">
					&nbsp;
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
		  <tr id="${item.csgId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csgId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgId || #request.defines['csgId']!=null">
				<s:if test="#request.defines['csgId']>0">
					${lz:set("注释","****csgId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgId",lz:indexOf(fieldName,"csgId")>-1)}
				  		<s:if test="#request.atCsgId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgId字段的字串格式化输出****")}
					<td ref="csgId" class="td ">
						 
						 	${lz:or(item$csgId[i.count-1],lz:left(item.csgId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgHost || #request.defines['csgHost']!=null">
				<s:if test="#request.defines['csgHost']>0">
					${lz:set("注释","****csgHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgHost",lz:indexOf(fieldName,"csgHost")>-1)}
				  		<s:if test="#request.atCsgHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgHost字段的字串格式化输出****")}
					<td ref="csgHost" class="td " relate="${item.csgHost}">
						 
						 	<a <s:if test="#item.csgHost!=null && #item.csgHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csgHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csgHost[i.count-1],lz:left(item.csgHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgName || #request.defines['csgName']!=null">
				<s:if test="#request.defines['csgName']>0">
					${lz:set("注释","****csgName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgName",lz:indexOf(fieldName,"csgName")>-1)}
				  		<s:if test="#request.atCsgName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgName字段的字串格式化输出****")}
					<td ref="csgName" class="td  node">
						 
						 	${lz:or(item$csgName[i.count-1],lz:left(item.csgName$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgMember || #request.defines['csgMember']!=null">
				<s:if test="#request.defines['csgMember']>0">
					${lz:set("注释","****csgMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgMember",lz:indexOf(fieldName,"csgMember")>-1)}
				  		<s:if test="#request.atCsgMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgMember字段的字串格式化输出****")}
					<td ref="csgMember" class="td " relate="${item.csgMember}">
						 
						 	<a <s:if test="#item.csgMember!=null && #item.csgMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csgMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csgMember[i.count-1],lz:left(item.csgMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgSysPack || #request.defines['csgSysPack']!=null">
				<s:if test="#request.defines['csgSysPack']>0">
					${lz:set("注释","****csgSysPack关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgSysPack))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgSysPack",lz:indexOf(fieldName,"csgSysPack")>-1)}
				  		<s:if test="#request.atCsgSysPack==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgSysPack字段的字串格式化输出****")}
					<td ref="csgSysPack" class="td " relate="${item.csgSysPack}">
						 
						 	<a <s:if test="#item.csgSysPack!=null && #item.csgSysPack!=''"> onclick="window.href('${basePath}${proname}/fee/pack_details.do?key=${item.csgSysPack}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csgSysPack[i.count-1],lz:left(item.csgSysPack$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgUserPack || #request.defines['csgUserPack']!=null">
				<s:if test="#request.defines['csgUserPack']>0">
					${lz:set("注释","****csgUserPack关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgUserPack))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgUserPack",lz:indexOf(fieldName,"csgUserPack")>-1)}
				  		<s:if test="#request.atCsgUserPack==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgUserPack字段的字串格式化输出****")}
					<td ref="csgUserPack" class="td " relate="${item.csgUserPack}">
						 
						 	<a <s:if test="#item.csgUserPack!=null && #item.csgUserPack!=''"> onclick="window.href('${basePath}${proname}/user/pack_details.do?key=${item.csgUserPack}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csgUserPack[i.count-1],lz:left(item.csgUserPack$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgGoods || #request.defines['csgGoods']!=null">
				<s:if test="#request.defines['csgGoods']>0">
					${lz:set("注释","****csgGoods关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgGoods))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgGoods",lz:indexOf(fieldName,"csgGoods")>-1)}
				  		<s:if test="#request.atCsgGoods==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgGoods字段的字串格式化输出****")}
					<td ref="csgGoods" class="td " relate="${item.csgGoods}">
						 
						 	<a <s:if test="#item.csgGoods!=null && #item.csgGoods!=''"> onclick="window.href('${basePath}${proname}/fee/goods_details.do?key=${item.csgGoods}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csgGoods[i.count-1],lz:left(item.csgGoods$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgOutlets || #request.defines['csgOutlets']!=null">
				<s:if test="#request.defines['csgOutlets']>0">
					${lz:set("注释","****csgOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgOutlets))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgOutlets",lz:indexOf(fieldName,"csgOutlets")>-1)}
				  		<s:if test="#request.atCsgOutlets==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgOutlets字段的字串格式化输出****")}
					<td ref="csgOutlets" class="td " relate="${item.csgOutlets}">
						 
						 	<a <s:if test="#item.csgOutlets!=null && #item.csgOutlets!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.csgOutlets}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csgOutlets[i.count-1],lz:left(item.csgOutlets$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgModel || #request.defines['csgModel']!=null">
				<s:if test="#request.defines['csgModel']>0">
					${lz:set("注释","****csgModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgModel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgModel",lz:indexOf(fieldName,"csgModel")>-1)}
				  		<s:if test="#request.atCsgModel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgModel字段的字串格式化输出****")}
					<td ref="csgModel" class="td " relate="${item.csgModel}">
						 
						 	${lz:or(item$csgModel[i.count-1],lz:left(item.csgModel$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgType || #request.defines['csgType']!=null">
				<s:if test="#request.defines['csgType']>0">
					${lz:set("注释","****csgType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgType",lz:indexOf(fieldName,"csgType")>-1)}
				  		<s:if test="#request.atCsgType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgType字段的字串格式化输出****")}
					<td ref="csgType" class="td ">
						 
						 	${lz:or(item$csgType[i.count-1],lz:left(item.csgType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgCount || #request.defines['csgCount']!=null">
				<s:if test="#request.defines['csgCount']>0">
					${lz:set("注释","****csgCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgCount))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgCount",lz:indexOf(fieldName,"csgCount")>-1)}
				  		<s:if test="#request.atCsgCount==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgCount字段的字串格式化输出****")}
					<td ref="csgCount" class="td ">
						 
						 	${lz:or(item$csgCount[i.count-1],lz:left(item.csgCount$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgRemain || #request.defines['csgRemain']!=null">
				<s:if test="#request.defines['csgRemain']>0">
					${lz:set("注释","****csgRemain关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgRemain))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgRemain",lz:indexOf(fieldName,"csgRemain")>-1)}
				  		<s:if test="#request.atCsgRemain==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgRemain字段的字串格式化输出****")}
					<td ref="csgRemain" class="td ">
						 
						 	${lz:or(item$csgRemain[i.count-1],lz:left(item.csgRemain$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgPrice || #request.defines['csgPrice']!=null">
				<s:if test="#request.defines['csgPrice']>0">
					${lz:set("注释","****csgPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgPrice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgPrice",lz:indexOf(fieldName,"csgPrice")>-1)}
				  		<s:if test="#request.atCsgPrice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgPrice字段的字串格式化输出****")}
					<td ref="csgPrice" class="td ">
						 
						 	${lz:or(item$csgPrice[i.count-1],lz:left(item.csgPrice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgRebate || #request.defines['csgRebate']!=null">
				<s:if test="#request.defines['csgRebate']>0">
					${lz:set("注释","****csgRebate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgRebate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgRebate",lz:indexOf(fieldName,"csgRebate")>-1)}
				  		<s:if test="#request.atCsgRebate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgRebate字段的字串格式化输出****")}
					<td ref="csgRebate" class="td ">
						 
						 	${lz:or(item$csgRebate[i.count-1],lz:left(item.csgRebate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgScript || #request.defines['csgScript']!=null">
				<s:if test="#request.defines['csgScript']>0">
					${lz:set("注释","****csgScript关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgScript))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgScript",lz:indexOf(fieldName,"csgScript")>-1)}
				  		<s:if test="#request.atCsgScript==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgScript字段的字串格式化输出****")}
					<td ref="csgScript" class="td ">
						 
						 	${lz:or(item$csgScript[i.count-1],lz:left(item.csgScript$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgUntilTime || #request.defines['csgUntilTime']!=null">
				<s:if test="#request.defines['csgUntilTime']>0">
					${lz:set("注释","****csgUntilTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgUntilTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgUntilTime",lz:indexOf(fieldName,"csgUntilTime")>-1)}
				  		<s:if test="#request.atCsgUntilTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgUntilTime字段的字串格式化输出****")}
					<td ref="csgUntilTime" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csgId}','until')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csgUntilTime[i.count-1],lz:left(item.csgUntilTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgUpdateTime || #request.defines['csgUpdateTime']!=null">
				<s:if test="#request.defines['csgUpdateTime']>0">
					${lz:set("注释","****csgUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgUpdateTime",lz:indexOf(fieldName,"csgUpdateTime")>-1)}
				  		<s:if test="#request.atCsgUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgUpdateTime字段的字串格式化输出****")}
					<td ref="csgUpdateTime" class="td ">
						 
						 	${lz:or(item$csgUpdateTime[i.count-1],lz:left(item.csgUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgAddTime || #request.defines['csgAddTime']!=null">
				<s:if test="#request.defines['csgAddTime']>0">
					${lz:set("注释","****csgAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgAddTime",lz:indexOf(fieldName,"csgAddTime")>-1)}
				  		<s:if test="#request.atCsgAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgAddTime字段的字串格式化输出****")}
					<td ref="csgAddTime" class="td ">
						 
						 	${lz:or(item$csgAddTime[i.count-1],lz:left(item.csgAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csgStatus || #request.defines['csgStatus']!=null">
				<s:if test="#request.defines['csgStatus']>0">
					${lz:set("注释","****csgStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csgStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsgStatus",lz:indexOf(fieldName,"csgStatus")>-1)}
				  		<s:if test="#request.atCsgStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csgStatus字段的字串格式化输出****")}
					<td ref="csgStatus" class="td ">
						 
						 	${lz:or(item$csgStatus[i.count-1],lz:left(item.csgStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.csgId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectGift('${item.csgId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditGift('${item.csgId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelGift('${item.csgId}','${item.csgName}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsGift('${item.csgId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csgId || #request.defines['csgId']!=null">
				<s:if test="#request.defines['csgId']>0">
					${lz:set("注释","****csgId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgId",lz:indexOf(fieldName,"csgId")>-1)}				  		
				  		<s:if test="#request.atCsgId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgHost || #request.defines['csgHost']!=null">
				<s:if test="#request.defines['csgHost']>0">
					${lz:set("注释","****csgHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgHost",lz:indexOf(fieldName,"csgHost")>-1)}				  		
				  		<s:if test="#request.atCsgHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgName || #request.defines['csgName']!=null">
				<s:if test="#request.defines['csgName']>0">
					${lz:set("注释","****csgName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgName",lz:indexOf(fieldName,"csgName")>-1)}				  		
				  		<s:if test="#request.atCsgName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgMember || #request.defines['csgMember']!=null">
				<s:if test="#request.defines['csgMember']>0">
					${lz:set("注释","****csgMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgMember",lz:indexOf(fieldName,"csgMember")>-1)}				  		
				  		<s:if test="#request.atCsgMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgSysPack || #request.defines['csgSysPack']!=null">
				<s:if test="#request.defines['csgSysPack']>0">
					${lz:set("注释","****csgSysPack关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgSysPack",lz:indexOf(fieldName,"csgSysPack")>-1)}				  		
				  		<s:if test="#request.atCsgSysPack==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgSysPack[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgUserPack || #request.defines['csgUserPack']!=null">
				<s:if test="#request.defines['csgUserPack']>0">
					${lz:set("注释","****csgUserPack关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgUserPack",lz:indexOf(fieldName,"csgUserPack")>-1)}				  		
				  		<s:if test="#request.atCsgUserPack==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgUserPack[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgGoods || #request.defines['csgGoods']!=null">
				<s:if test="#request.defines['csgGoods']>0">
					${lz:set("注释","****csgGoods关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgGoods",lz:indexOf(fieldName,"csgGoods")>-1)}				  		
				  		<s:if test="#request.atCsgGoods==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgGoods[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgOutlets || #request.defines['csgOutlets']!=null">
				<s:if test="#request.defines['csgOutlets']>0">
					${lz:set("注释","****csgOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgOutlets",lz:indexOf(fieldName,"csgOutlets")>-1)}				  		
				  		<s:if test="#request.atCsgOutlets==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgOutlets[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgModel || #request.defines['csgModel']!=null">
				<s:if test="#request.defines['csgModel']>0">
					${lz:set("注释","****csgModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgModel",lz:indexOf(fieldName,"csgModel")>-1)}				  		
				  		<s:if test="#request.atCsgModel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgModel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgType || #request.defines['csgType']!=null">
				<s:if test="#request.defines['csgType']>0">
					${lz:set("注释","****csgType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgType",lz:indexOf(fieldName,"csgType")>-1)}				  		
				  		<s:if test="#request.atCsgType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgCount || #request.defines['csgCount']!=null">
				<s:if test="#request.defines['csgCount']>0">
					${lz:set("注释","****csgCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgCount",lz:indexOf(fieldName,"csgCount")>-1)}				  		
				  		<s:if test="#request.atCsgCount==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgCount[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgRemain || #request.defines['csgRemain']!=null">
				<s:if test="#request.defines['csgRemain']>0">
					${lz:set("注释","****csgRemain关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgRemain",lz:indexOf(fieldName,"csgRemain")>-1)}				  		
				  		<s:if test="#request.atCsgRemain==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgRemain[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgPrice || #request.defines['csgPrice']!=null">
				<s:if test="#request.defines['csgPrice']>0">
					${lz:set("注释","****csgPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgPrice",lz:indexOf(fieldName,"csgPrice")>-1)}				  		
				  		<s:if test="#request.atCsgPrice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgPrice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgRebate || #request.defines['csgRebate']!=null">
				<s:if test="#request.defines['csgRebate']>0">
					${lz:set("注释","****csgRebate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgRebate",lz:indexOf(fieldName,"csgRebate")>-1)}				  		
				  		<s:if test="#request.atCsgRebate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgRebate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgScript || #request.defines['csgScript']!=null">
				<s:if test="#request.defines['csgScript']>0">
					${lz:set("注释","****csgScript关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgScript",lz:indexOf(fieldName,"csgScript")>-1)}				  		
				  		<s:if test="#request.atCsgScript==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgScript[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgUntilTime || #request.defines['csgUntilTime']!=null">
				<s:if test="#request.defines['csgUntilTime']>0">
					${lz:set("注释","****csgUntilTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgUntilTime",lz:indexOf(fieldName,"csgUntilTime")>-1)}				  		
				  		<s:if test="#request.atCsgUntilTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgUntilTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgUpdateTime || #request.defines['csgUpdateTime']!=null">
				<s:if test="#request.defines['csgUpdateTime']>0">
					${lz:set("注释","****csgUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgUpdateTime",lz:indexOf(fieldName,"csgUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsgUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgAddTime || #request.defines['csgAddTime']!=null">
				<s:if test="#request.defines['csgAddTime']>0">
					${lz:set("注释","****csgAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgAddTime",lz:indexOf(fieldName,"csgAddTime")>-1)}				  		
				  		<s:if test="#request.atCsgAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csgStatus || #request.defines['csgStatus']!=null">
				<s:if test="#request.defines['csgStatus']>0">
					${lz:set("注释","****csgStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsgStatus",lz:indexOf(fieldName,"csgStatus")>-1)}				  		
				  		<s:if test="#request.atCsgStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csgStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
<s:if test="#request.alias=='select'">
	/**
	* 选择优惠内容
	**/
	function SelectGift(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改优惠内容
	**/
	function EditGift(id){
		var url = "${basePath}${namespace}gift_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改优惠内容'}":lz:json(ctrl)}};
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
		EditGift(checker.val());
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
				var url = "${basePath}${namespace}gift_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新优惠内容",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}gift_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新优惠内容",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改优惠内容
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}gift_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新优惠内容",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["until"]={};
	fieldNames["until"]["csgUntilTime"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改优惠内容任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}gift_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新优惠内容",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}gift_edit.do";
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
			var url = "${basePath}${namespace}gift_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除优惠内容
	**/
	function DelGift(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除优惠内容["+flag+"]吗？</span>",
				title:"删除优惠内容",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}gift_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选优惠内容吗？</span>",
				title:"批量删除优惠内容",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}gift_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示优惠内容详情
	**/
	function DetailsGift(id)
	{
		var url = "${basePath}${namespace}gift_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"优惠内容详情",editable:false,visible:true}};
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
		DetailsGift(checker.val());
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