<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/fee/item.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csItem.csiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csiId,csiTitle,csiType,csiSort,csiFlag,csiRemark,csiUpdateTime,csiAddTime,csiStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csiId,csiTitle,csiType,csiSort,csiPrice,csiRebate,csiFlag,csiDataString,csiDataNumber,csiDataDecimal,csiThumb,csiImages,csiDepict,csiSale,csiLevel,csiRemark,csiUpdateTime,csiAddTime,csiStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csiId,csiTitle,csiType,csiSort,csiStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csiId,csiTitle,csiType,csiSort,csiPrice,csiRebate,csiSale,csiLevel,csiStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>商品信息管理</title>
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
	<span class="caption">${empty title?"商品信息管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsItem" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="item.query.jsp"%>

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
			<button type="button" class="add" onclick="AddItem();">
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
			<dl ref="com.ccclubs.model.CsItem" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsItem" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.csiPrice || #request.defines['csiPrice']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('price')">商品价格</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csiRebate || #request.defines['csiRebate']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('price')">折扣</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csiLevel || #request.defines['csiLevel']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('level')">优先级</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csiStatus || #request.defines['csiStatus']!=null">
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
		<table id="item_table" ref="CsItem" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiId || #request.defines['csiId']!=null">
			 <td <s:if test="#request.defines['csiId']>0">colspan="${defines["csiId"]}" ${all?"width":"iwidth"}="${defines["csiId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csiId"   title="编号">
			 	<a class="${desc=="csi_id" ? "desc" : ""}${asc=="csi_id" ? "asc" : ""}" href="?${desc=="csi_id" ? "asc=csi_id" : ""}${(asc=="csi_id" || desc!="csi_id" )? "desc=csi_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csiId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiTitle || #request.defines['csiTitle']!=null">
			 <td <s:if test="#request.defines['csiTitle']>0">colspan="${defines["csiTitle"]}" ${all?"width":"iwidth"}="${defines["csiTitle"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="2" ref="csiTitle" flagKey  title="商品名称">
			 	<a class="${desc=="csi_title" ? "desc" : ""}${asc=="csi_title" ? "asc" : ""}" href="?${desc=="csi_title" ? "asc=csi_title" : ""}${(asc=="csi_title" || desc!="csi_title" )? "desc=csi_title" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	商品名称
			 	</a>
			 	${lz:set("checkeds[]","csiTitle")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiType || #request.defines['csiType']!=null">
			 <td <s:if test="#request.defines['csiType']>0">colspan="${defines["csiType"]}" ${all?"width":"iwidth"}="${defines["csiType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="csiType"   title="商品类型">
			 	<a class="${desc=="csi_type" ? "desc" : ""}${asc=="csi_type" ? "asc" : ""}" href="?${desc=="csi_type" ? "asc=csi_type" : ""}${(asc=="csi_type" || desc!="csi_type" )? "desc=csi_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	商品类型
			 	</a>
			 	${lz:set("checkeds[]","csiType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiSort || #request.defines['csiSort']!=null">
			 <td <s:if test="#request.defines['csiSort']>0">colspan="${defines["csiSort"]}" ${all?"width":"iwidth"}="${defines["csiSort"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csiSort"   title="商品分类">
			 	<a class="${desc=="csi_sort" ? "desc" : ""}${asc=="csi_sort" ? "asc" : ""}" href="?${desc=="csi_sort" ? "asc=csi_sort" : ""}${(asc=="csi_sort" || desc!="csi_sort" )? "desc=csi_sort" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	商品分类
			 	</a>
			 	${lz:set("checkeds[]","csiSort")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiPrice || #request.defines['csiPrice']!=null">
			 <td <s:if test="#request.defines['csiPrice']>0">colspan="${defines["csiPrice"]}" ${all?"width":"iwidth"}="${defines["csiPrice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csiPrice"   title="商品价格(元)">
			 	<a class="${desc=="csi_price" ? "desc" : ""}${asc=="csi_price" ? "asc" : ""}" href="?${desc=="csi_price" ? "asc=csi_price" : ""}${(asc=="csi_price" || desc!="csi_price" )? "desc=csi_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	商品价格(元)
			 	</a>
			 	${lz:set("checkeds[]","csiPrice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiRebate || #request.defines['csiRebate']!=null">
			 <td <s:if test="#request.defines['csiRebate']>0">colspan="${defines["csiRebate"]}" ${all?"width":"iwidth"}="${defines["csiRebate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csiRebate"   title="折扣">
			 	<a class="${desc=="csi_rebate" ? "desc" : ""}${asc=="csi_rebate" ? "asc" : ""}" href="?${desc=="csi_rebate" ? "asc=csi_rebate" : ""}${(asc=="csi_rebate" || desc!="csi_rebate" )? "desc=csi_rebate" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	折扣
			 	</a>
			 	${lz:set("checkeds[]","csiRebate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiFlag || #request.defines['csiFlag']!=null">
			 <td <s:if test="#request.defines['csiFlag']>0">colspan="${defines["csiFlag"]}" ${all?"width":"iwidth"}="${defines["csiFlag"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="csiFlag"   title="标识">
			 	<a class="${desc=="csi_flag" ? "desc" : ""}${asc=="csi_flag" ? "asc" : ""}" href="?${desc=="csi_flag" ? "asc=csi_flag" : ""}${(asc=="csi_flag" || desc!="csi_flag" )? "desc=csi_flag" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	标识
			 	</a>
			 	${lz:set("checkeds[]","csiFlag")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiDataString || #request.defines['csiDataString']!=null">
			 <td <s:if test="#request.defines['csiDataString']>0">colspan="${defines["csiDataString"]}" ${all?"width":"iwidth"}="${defines["csiDataString"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="8" ref="csiDataString"   title="附加字串">
			 	<a class="${desc=="csi_data_string" ? "desc" : ""}${asc=="csi_data_string" ? "asc" : ""}" href="?${desc=="csi_data_string" ? "asc=csi_data_string" : ""}${(asc=="csi_data_string" || desc!="csi_data_string" )? "desc=csi_data_string" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	附加字串
			 	</a>
			 	${lz:set("checkeds[]","csiDataString")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiDataNumber || #request.defines['csiDataNumber']!=null">
			 <td <s:if test="#request.defines['csiDataNumber']>0">colspan="${defines["csiDataNumber"]}" ${all?"width":"iwidth"}="${defines["csiDataNumber"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="csiDataNumber"   title="附加数字">
			 	<a class="${desc=="csi_data_number" ? "desc" : ""}${asc=="csi_data_number" ? "asc" : ""}" href="?${desc=="csi_data_number" ? "asc=csi_data_number" : ""}${(asc=="csi_data_number" || desc!="csi_data_number" )? "desc=csi_data_number" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	附加数字
			 	</a>
			 	${lz:set("checkeds[]","csiDataNumber")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiDataDecimal || #request.defines['csiDataDecimal']!=null">
			 <td <s:if test="#request.defines['csiDataDecimal']>0">colspan="${defines["csiDataDecimal"]}" ${all?"width":"iwidth"}="${defines["csiDataDecimal"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csiDataDecimal"   title="附加小数">
			 	<a class="${desc=="csi_data_decimal" ? "desc" : ""}${asc=="csi_data_decimal" ? "asc" : ""}" href="?${desc=="csi_data_decimal" ? "asc=csi_data_decimal" : ""}${(asc=="csi_data_decimal" || desc!="csi_data_decimal" )? "desc=csi_data_decimal" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	附加小数
			 	</a>
			 	${lz:set("checkeds[]","csiDataDecimal")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiThumb || #request.defines['csiThumb']!=null">
			 <td <s:if test="#request.defines['csiThumb']>0">colspan="${defines["csiThumb"]}" ${all?"width":"iwidth"}="${defines["csiThumb"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="csiThumb"   title="缩略图">
			 	<a class="${desc=="csi_thumb" ? "desc" : ""}${asc=="csi_thumb" ? "asc" : ""}" href="?${desc=="csi_thumb" ? "asc=csi_thumb" : ""}${(asc=="csi_thumb" || desc!="csi_thumb" )? "desc=csi_thumb" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	缩略图
			 	</a>
			 	${lz:set("checkeds[]","csiThumb")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiImages || #request.defines['csiImages']!=null">
			 <td <s:if test="#request.defines['csiImages']>0">colspan="${defines["csiImages"]}" ${all?"width":"iwidth"}="${defines["csiImages"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="csiImages"   title="图片列表">
			 	<a class="${desc=="csi_images" ? "desc" : ""}${asc=="csi_images" ? "asc" : ""}" href="?${desc=="csi_images" ? "asc=csi_images" : ""}${(asc=="csi_images" || desc!="csi_images" )? "desc=csi_images" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	图片列表
			 	</a>
			 	${lz:set("checkeds[]","csiImages")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiDepict || #request.defines['csiDepict']!=null">
			 <td <s:if test="#request.defines['csiDepict']>0">colspan="${defines["csiDepict"]}" ${all?"width":"iwidth"}="${defines["csiDepict"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="csiDepict"   title="描述">
			 	<a class="${desc=="csi_depict" ? "desc" : ""}${asc=="csi_depict" ? "asc" : ""}" href="?${desc=="csi_depict" ? "asc=csi_depict" : ""}${(asc=="csi_depict" || desc!="csi_depict" )? "desc=csi_depict" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	描述
			 	</a>
			 	${lz:set("checkeds[]","csiDepict")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiSale || #request.defines['csiSale']!=null">
			 <td <s:if test="#request.defines['csiSale']>0">colspan="${defines["csiSale"]}" ${all?"width":"iwidth"}="${defines["csiSale"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="csiSale"   title="已售数">
			 	<a class="${desc=="csi_sale" ? "desc" : ""}${asc=="csi_sale" ? "asc" : ""}" href="?${desc=="csi_sale" ? "asc=csi_sale" : ""}${(asc=="csi_sale" || desc!="csi_sale" )? "desc=csi_sale" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	已售数
			 	</a>
			 	${lz:set("checkeds[]","csiSale")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiLevel || #request.defines['csiLevel']!=null">
			 <td <s:if test="#request.defines['csiLevel']>0">colspan="${defines["csiLevel"]}" ${all?"width":"iwidth"}="${defines["csiLevel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="csiLevel"   title="优先级">
			 	<a class="${desc=="csi_level" ? "desc" : ""}${asc=="csi_level" ? "asc" : ""}" href="?${desc=="csi_level" ? "asc=csi_level" : ""}${(asc=="csi_level" || desc!="csi_level" )? "desc=csi_level" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优先级
			 	</a>
			 	${lz:set("checkeds[]","csiLevel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiRemark || #request.defines['csiRemark']!=null">
			 <td <s:if test="#request.defines['csiRemark']>0">colspan="${defines["csiRemark"]}" ${all?"width":"iwidth"}="${defines["csiRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="16" ref="csiRemark"   title="备注">
			 	<a class="${desc=="csi_remark" ? "desc" : ""}${asc=="csi_remark" ? "asc" : ""}" href="?${desc=="csi_remark" ? "asc=csi_remark" : ""}${(asc=="csi_remark" || desc!="csi_remark" )? "desc=csi_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 	${lz:set("checkeds[]","csiRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiUpdateTime || #request.defines['csiUpdateTime']!=null">
			 <td <s:if test="#request.defines['csiUpdateTime']>0">colspan="${defines["csiUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csiUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="17" ref="csiUpdateTime"   title="修改时间">
			 	<a class="${desc=="csi_update_time" ? "desc" : ""}${asc=="csi_update_time" ? "asc" : ""}" href="?${desc=="csi_update_time" ? "asc=csi_update_time" : ""}${(asc=="csi_update_time" || desc!="csi_update_time" )? "desc=csi_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csiUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiAddTime || #request.defines['csiAddTime']!=null">
			 <td <s:if test="#request.defines['csiAddTime']>0">colspan="${defines["csiAddTime"]}" ${all?"width":"iwidth"}="${defines["csiAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="18" ref="csiAddTime"   title="添加时间">
			 	<a class="${desc=="csi_add_time" ? "desc" : ""}${asc=="csi_add_time" ? "asc" : ""}" href="?${desc=="csi_add_time" ? "asc=csi_add_time" : ""}${(asc=="csi_add_time" || desc!="csi_add_time" )? "desc=csi_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csiAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiStatus || #request.defines['csiStatus']!=null">
			 <td <s:if test="#request.defines['csiStatus']>0">colspan="${defines["csiStatus"]}" ${all?"width":"iwidth"}="${defines["csiStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="19" ref="csiStatus"   title="状态">
			 	<a class="${desc=="csi_status" ? "desc" : ""}${asc=="csi_status" ? "asc" : ""}" href="?${desc=="csi_status" ? "asc=csi_status" : ""}${(asc=="csi_status" || desc!="csi_status" )? "desc=csi_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csiStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="20" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="21" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddItem()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.csiId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csiId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiId || #request.defines['csiId']!=null">
				<s:if test="#request.defines['csiId']>0">
					${lz:set("注释","****csiId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiId",lz:indexOf(fieldName,"csiId")>-1)}
				  		<s:if test="#request.atCsiId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiId字段的字串格式化输出****")}
					<td ref="csiId" class="td ">
						 
						 	${lz:or(item$csiId[i.count-1],lz:left(item.csiId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiTitle || #request.defines['csiTitle']!=null">
				<s:if test="#request.defines['csiTitle']>0">
					${lz:set("注释","****csiTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiTitle))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiTitle",lz:indexOf(fieldName,"csiTitle")>-1)}
				  		<s:if test="#request.atCsiTitle==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiTitle字段的字串格式化输出****")}
					<td ref="csiTitle" class="td  node">
						 
						 	${lz:or(item$csiTitle[i.count-1],lz:left(item.csiTitle$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiType || #request.defines['csiType']!=null">
				<s:if test="#request.defines['csiType']>0">
					${lz:set("注释","****csiType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiType",lz:indexOf(fieldName,"csiType")>-1)}
				  		<s:if test="#request.atCsiType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiType字段的字串格式化输出****")}
					<td ref="csiType" class="td ">
						 
						 	${lz:or(item$csiType[i.count-1],lz:left(item.csiType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiSort || #request.defines['csiSort']!=null">
				<s:if test="#request.defines['csiSort']>0">
					${lz:set("注释","****csiSort关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiSort))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiSort",lz:indexOf(fieldName,"csiSort")>-1)}
				  		<s:if test="#request.atCsiSort==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiSort字段的字串格式化输出****")}
					<td ref="csiSort" class="td " relate="${item.csiSort}">
						 
						 	<a <s:if test="#item.csiSort!=null && #item.csiSort!=''"> onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${item.csiSort}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csiSort[i.count-1],lz:left(item.csiSort$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiPrice || #request.defines['csiPrice']!=null">
				<s:if test="#request.defines['csiPrice']>0">
					${lz:set("注释","****csiPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiPrice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiPrice",lz:indexOf(fieldName,"csiPrice")>-1)}
				  		<s:if test="#request.atCsiPrice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiPrice字段的字串格式化输出****")}
					<td ref="csiPrice" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csiId}','price')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csiPrice[i.count-1],lz:left(item.csiPrice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiRebate || #request.defines['csiRebate']!=null">
				<s:if test="#request.defines['csiRebate']>0">
					${lz:set("注释","****csiRebate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiRebate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiRebate",lz:indexOf(fieldName,"csiRebate")>-1)}
				  		<s:if test="#request.atCsiRebate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiRebate字段的字串格式化输出****")}
					<td ref="csiRebate" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csiId}','price')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csiRebate[i.count-1],lz:left(item.csiRebate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiFlag || #request.defines['csiFlag']!=null">
				<s:if test="#request.defines['csiFlag']>0">
					${lz:set("注释","****csiFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiFlag))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiFlag",lz:indexOf(fieldName,"csiFlag")>-1)}
				  		<s:if test="#request.atCsiFlag==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiFlag字段的字串格式化输出****")}
					<td ref="csiFlag" class="td ">
						 
						 	${lz:or(item$csiFlag[i.count-1],lz:left(item.csiFlag$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiDataString || #request.defines['csiDataString']!=null">
				<s:if test="#request.defines['csiDataString']>0">
					${lz:set("注释","****csiDataString关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiDataString))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiDataString",lz:indexOf(fieldName,"csiDataString")>-1)}
				  		<s:if test="#request.atCsiDataString==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiDataString字段的字串格式化输出****")}
					<td ref="csiDataString" class="td ">
						 
						 	${lz:or(item$csiDataString[i.count-1],lz:left(item.csiDataString$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiDataNumber || #request.defines['csiDataNumber']!=null">
				<s:if test="#request.defines['csiDataNumber']>0">
					${lz:set("注释","****csiDataNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiDataNumber))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiDataNumber",lz:indexOf(fieldName,"csiDataNumber")>-1)}
				  		<s:if test="#request.atCsiDataNumber==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiDataNumber字段的字串格式化输出****")}
					<td ref="csiDataNumber" class="td ">
						 
						 	${lz:or(item$csiDataNumber[i.count-1],lz:left(item.csiDataNumber$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiDataDecimal || #request.defines['csiDataDecimal']!=null">
				<s:if test="#request.defines['csiDataDecimal']>0">
					${lz:set("注释","****csiDataDecimal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiDataDecimal))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiDataDecimal",lz:indexOf(fieldName,"csiDataDecimal")>-1)}
				  		<s:if test="#request.atCsiDataDecimal==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiDataDecimal字段的字串格式化输出****")}
					<td ref="csiDataDecimal" class="td ">
						 
						 	${lz:or(item$csiDataDecimal[i.count-1],lz:left(item.csiDataDecimal$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiThumb || #request.defines['csiThumb']!=null">
				<s:if test="#request.defines['csiThumb']>0">
					${lz:set("注释","****csiThumb关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiThumb))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiThumb",lz:indexOf(fieldName,"csiThumb")>-1)}
				  		<s:if test="#request.atCsiThumb==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiThumb字段的字串格式化输出****")}
					<td ref="csiThumb" class="td ">
						 
						 	${lz:or(item$csiThumb[i.count-1],lz:left(item.csiThumb$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiImages || #request.defines['csiImages']!=null">
				<s:if test="#request.defines['csiImages']>0">
					${lz:set("注释","****csiImages关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiImages))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiImages",lz:indexOf(fieldName,"csiImages")>-1)}
				  		<s:if test="#request.atCsiImages==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiImages字段的字串格式化输出****")}
					<td ref="csiImages" class="td ">
						 
						 	${lz:or(item$csiImages[i.count-1],lz:left(item.csiImages$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiDepict || #request.defines['csiDepict']!=null">
				<s:if test="#request.defines['csiDepict']>0">
					${lz:set("注释","****csiDepict关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiDepict))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiDepict",lz:indexOf(fieldName,"csiDepict")>-1)}
				  		<s:if test="#request.atCsiDepict==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiDepict字段的字串格式化输出****")}
					<td ref="csiDepict" class="td ">
						 
						 	${lz:or(item$csiDepict[i.count-1],lz:left(item.csiDepict$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiSale || #request.defines['csiSale']!=null">
				<s:if test="#request.defines['csiSale']>0">
					${lz:set("注释","****csiSale关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiSale))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiSale",lz:indexOf(fieldName,"csiSale")>-1)}
				  		<s:if test="#request.atCsiSale==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiSale字段的字串格式化输出****")}
					<td ref="csiSale" class="td ">
						 
						 	${lz:or(item$csiSale[i.count-1],lz:left(item.csiSale$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiLevel || #request.defines['csiLevel']!=null">
				<s:if test="#request.defines['csiLevel']>0">
					${lz:set("注释","****csiLevel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiLevel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiLevel",lz:indexOf(fieldName,"csiLevel")>-1)}
				  		<s:if test="#request.atCsiLevel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiLevel字段的字串格式化输出****")}
					<td ref="csiLevel" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csiId}','level')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csiLevel[i.count-1],lz:left(item.csiLevel$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiRemark || #request.defines['csiRemark']!=null">
				<s:if test="#request.defines['csiRemark']>0">
					${lz:set("注释","****csiRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiRemark",lz:indexOf(fieldName,"csiRemark")>-1)}
				  		<s:if test="#request.atCsiRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiRemark字段的字串格式化输出****")}
					<td ref="csiRemark" class="td ">
						 
						 	${lz:or(item$csiRemark[i.count-1],lz:left(item.csiRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiUpdateTime || #request.defines['csiUpdateTime']!=null">
				<s:if test="#request.defines['csiUpdateTime']>0">
					${lz:set("注释","****csiUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiUpdateTime",lz:indexOf(fieldName,"csiUpdateTime")>-1)}
				  		<s:if test="#request.atCsiUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiUpdateTime字段的字串格式化输出****")}
					<td ref="csiUpdateTime" class="td ">
						 
						 	${lz:or(item$csiUpdateTime[i.count-1],lz:left(item.csiUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiAddTime || #request.defines['csiAddTime']!=null">
				<s:if test="#request.defines['csiAddTime']>0">
					${lz:set("注释","****csiAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiAddTime",lz:indexOf(fieldName,"csiAddTime")>-1)}
				  		<s:if test="#request.atCsiAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiAddTime字段的字串格式化输出****")}
					<td ref="csiAddTime" class="td ">
						 
						 	${lz:or(item$csiAddTime[i.count-1],lz:left(item.csiAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiStatus || #request.defines['csiStatus']!=null">
				<s:if test="#request.defines['csiStatus']>0">
					${lz:set("注释","****csiStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiStatus",lz:indexOf(fieldName,"csiStatus")>-1)}
				  		<s:if test="#request.atCsiStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiStatus字段的字串格式化输出****")}
					<td ref="csiStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csiId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csiStatus[i.count-1],lz:left(item.csiStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.csiId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectItem('${item.csiId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditItem('${item.csiId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelItem('${item.csiId}','${item.csiTitle}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsItem('${item.csiId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csiId || #request.defines['csiId']!=null">
				<s:if test="#request.defines['csiId']>0">
					${lz:set("注释","****csiId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiId",lz:indexOf(fieldName,"csiId")>-1)}				  		
				  		<s:if test="#request.atCsiId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiTitle || #request.defines['csiTitle']!=null">
				<s:if test="#request.defines['csiTitle']>0">
					${lz:set("注释","****csiTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiTitle",lz:indexOf(fieldName,"csiTitle")>-1)}				  		
				  		<s:if test="#request.atCsiTitle==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiTitle[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiType || #request.defines['csiType']!=null">
				<s:if test="#request.defines['csiType']>0">
					${lz:set("注释","****csiType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiType",lz:indexOf(fieldName,"csiType")>-1)}				  		
				  		<s:if test="#request.atCsiType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiSort || #request.defines['csiSort']!=null">
				<s:if test="#request.defines['csiSort']>0">
					${lz:set("注释","****csiSort关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiSort",lz:indexOf(fieldName,"csiSort")>-1)}				  		
				  		<s:if test="#request.atCsiSort==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiSort[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiPrice || #request.defines['csiPrice']!=null">
				<s:if test="#request.defines['csiPrice']>0">
					${lz:set("注释","****csiPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiPrice",lz:indexOf(fieldName,"csiPrice")>-1)}				  		
				  		<s:if test="#request.atCsiPrice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiPrice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiRebate || #request.defines['csiRebate']!=null">
				<s:if test="#request.defines['csiRebate']>0">
					${lz:set("注释","****csiRebate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiRebate",lz:indexOf(fieldName,"csiRebate")>-1)}				  		
				  		<s:if test="#request.atCsiRebate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiRebate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiFlag || #request.defines['csiFlag']!=null">
				<s:if test="#request.defines['csiFlag']>0">
					${lz:set("注释","****csiFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiFlag",lz:indexOf(fieldName,"csiFlag")>-1)}				  		
				  		<s:if test="#request.atCsiFlag==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiFlag[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiDataString || #request.defines['csiDataString']!=null">
				<s:if test="#request.defines['csiDataString']>0">
					${lz:set("注释","****csiDataString关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiDataString",lz:indexOf(fieldName,"csiDataString")>-1)}				  		
				  		<s:if test="#request.atCsiDataString==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiDataString[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiDataNumber || #request.defines['csiDataNumber']!=null">
				<s:if test="#request.defines['csiDataNumber']>0">
					${lz:set("注释","****csiDataNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiDataNumber",lz:indexOf(fieldName,"csiDataNumber")>-1)}				  		
				  		<s:if test="#request.atCsiDataNumber==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiDataNumber[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiDataDecimal || #request.defines['csiDataDecimal']!=null">
				<s:if test="#request.defines['csiDataDecimal']>0">
					${lz:set("注释","****csiDataDecimal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiDataDecimal",lz:indexOf(fieldName,"csiDataDecimal")>-1)}				  		
				  		<s:if test="#request.atCsiDataDecimal==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiDataDecimal[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiThumb || #request.defines['csiThumb']!=null">
				<s:if test="#request.defines['csiThumb']>0">
					${lz:set("注释","****csiThumb关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiThumb",lz:indexOf(fieldName,"csiThumb")>-1)}				  		
				  		<s:if test="#request.atCsiThumb==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiThumb[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiImages || #request.defines['csiImages']!=null">
				<s:if test="#request.defines['csiImages']>0">
					${lz:set("注释","****csiImages关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiImages",lz:indexOf(fieldName,"csiImages")>-1)}				  		
				  		<s:if test="#request.atCsiImages==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiImages[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiDepict || #request.defines['csiDepict']!=null">
				<s:if test="#request.defines['csiDepict']>0">
					${lz:set("注释","****csiDepict关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiDepict",lz:indexOf(fieldName,"csiDepict")>-1)}				  		
				  		<s:if test="#request.atCsiDepict==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiDepict[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiSale || #request.defines['csiSale']!=null">
				<s:if test="#request.defines['csiSale']>0">
					${lz:set("注释","****csiSale关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiSale",lz:indexOf(fieldName,"csiSale")>-1)}				  		
				  		<s:if test="#request.atCsiSale==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiSale[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiLevel || #request.defines['csiLevel']!=null">
				<s:if test="#request.defines['csiLevel']>0">
					${lz:set("注释","****csiLevel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiLevel",lz:indexOf(fieldName,"csiLevel")>-1)}				  		
				  		<s:if test="#request.atCsiLevel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiLevel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiRemark || #request.defines['csiRemark']!=null">
				<s:if test="#request.defines['csiRemark']>0">
					${lz:set("注释","****csiRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiRemark",lz:indexOf(fieldName,"csiRemark")>-1)}				  		
				  		<s:if test="#request.atCsiRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiUpdateTime || #request.defines['csiUpdateTime']!=null">
				<s:if test="#request.defines['csiUpdateTime']>0">
					${lz:set("注释","****csiUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiUpdateTime",lz:indexOf(fieldName,"csiUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsiUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiAddTime || #request.defines['csiAddTime']!=null">
				<s:if test="#request.defines['csiAddTime']>0">
					${lz:set("注释","****csiAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiAddTime",lz:indexOf(fieldName,"csiAddTime")>-1)}				  		
				  		<s:if test="#request.atCsiAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiStatus || #request.defines['csiStatus']!=null">
				<s:if test="#request.defines['csiStatus']>0">
					${lz:set("注释","****csiStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiStatus",lz:indexOf(fieldName,"csiStatus")>-1)}				  		
				  		<s:if test="#request.atCsiStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加商品信息
	**/
	function AddItem(parent){		
		var url = "${basePath}${namespace}item_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加商品信息'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择商品信息
	**/
	function SelectItem(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改商品信息
	**/
	function EditItem(id){
		var url = "${basePath}${namespace}item_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改商品信息'}":lz:json(ctrl)}};
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
		EditItem(checker.val());
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
				var url = "${basePath}${namespace}item_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新商品信息",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}item_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新商品信息",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改商品信息
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}item_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新商品信息",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["price"]={};
	fieldNames["price"]={};
	fieldNames["level"]={};
	fieldNames["status"]={};
	fieldNames["price"]["csiPrice"]=true;
	fieldNames["price"]["csiRebate"]=true;
	fieldNames["level"]["csiLevel"]=true;
	fieldNames["status"]["csiStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改商品信息任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}item_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新商品信息",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}item_edit.do";
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
			var url = "${basePath}${namespace}item_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除商品信息
	**/
	function DelItem(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除商品信息["+flag+"]吗？</span>",
				title:"删除商品信息",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}item_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选商品信息吗？</span>",
				title:"批量删除商品信息",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}item_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示商品信息详情
	**/
	function DetailsItem(id)
	{
		var url = "${basePath}${namespace}item_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"商品信息详情",editable:false,visible:true}};
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
		DetailsItem(checker.val());
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