<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/carres.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCarRes.cscrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"cscrId,cscrHost,cscrOutlets,cscrCar,cscrCarmodel,cscrCarNo,cscrColor,cscrLocation,cscrSource,cscrStatus,cscrAddTime,cscrUpdateTime",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"cscrId,cscrHost,cscrOutlets,cscrCar,cscrCarmodel,cscrCarNo,cscrColor,cscrLocation,cscrSource,cscrStatus,cscrAddTime,cscrUpdateTime",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"cscrId,cscrHost,cscrOutlets,cscrCar,cscrCarmodel,cscrCarNo,cscrColor,cscrLocation,cscrSource,cscrStatus,cscrAddTime,cscrUpdateTime",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"cscrId,cscrHost,cscrOutlets,cscrCar,cscrCarmodel,cscrCarNo,cscrColor,cscrLocation,cscrSource,cscrStatus,cscrAddTime,cscrUpdateTime",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>专车管理</title>
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
		<span class="caption">${empty title?"专车管理":title}</span>
	</div>
</s:if>

${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="Cscarres" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="carres.query.jsp"%>

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
			<button type="button" class="add" onclick="Addcarres();">
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
			<dl ref="com.ccclubs.model.CsCarRes" class="display dropdowan" style="margin-left:30px;">
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
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表表头开始*****************")}
		<table id="carres_table" ref="CsCarRes" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrId || #request.defines['cscrId']!=null">
			 <td <s:if test="#request.defines['cscrId']>0">colspan="${defines["cscrId"]}" ${all?"width":"iwidth"}="${defines["cscrId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=72":""} </s:else> tdid="1" ref="cscrId"   title="编号">
			 	<a class="${desc=="cscr_id" ? "desc" : ""}${asc=="cscr_id" ? "asc" : ""}" href="?${desc=="cscr_id" ? "asc=cscr_id" : ""}${(asc=="cscr_id" || desc!="cscr_id" )? "desc=cscr_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","cscrId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrHost || #request.defines['cscrHost']!=null">
			 <td <s:if test="#request.defines['cscrHost']>0">colspan="${defines["cscrHost"]}" ${all?"width":"iwidth"}="${defines["cscrHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=100":""} </s:else> tdid="2" ref="cscrHost"   title="城市">
			 	<a class="${desc=="cscr_host" ? "desc" : ""}${asc=="cscr_host" ? "asc" : ""}" href="?${desc=="cscr_host" ? "asc=cscr_host" : ""}${(asc=="cscr_host" || desc!="cscr_host" )? "desc=cscr_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","cscrHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrOutlets || #request.defines['cscrOutlets']!=null">
			 <td <s:if test="#request.defines['cscrOutlets']>0">colspan="${defines["cscrOutlets"]}" ${all?"width":"iwidth"}="${defines["cscrOutlets"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=100":""} </s:else> tdid="9" ref="cscrOutlets"   title="指定网点">
			 	<a class="${desc=="cscr_outlets" ? "desc" : ""}${asc=="cscr_outlets" ? "asc" : ""}" href="?${desc=="cscr_outlets" ? "asc=cscr_outlets" : ""}${(asc=="cscr_outlets" || desc!="cscr_outlets" )? "desc=cscr_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	指定网点
			 	</a>
			 	${lz:set("checkeds[]","cscrOutlets")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrCar || #request.defines['cscrCar']!=null">
			 <td <s:if test="#request.defines['cscrCar']>0">colspan="${defines["cscrCar"]}" ${all?"width":"iwidth"}="${defines["cscrCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="cscrCar" flagKey  title="姓名">
			 	<a class="${desc=="cscr_car" ? "desc" : ""}${asc=="cscr_car" ? "asc" : ""}" href="?${desc=="cscr_car" ? "asc=cscr_car" : ""}${(asc=="cscr_car" || desc!="cscr_car" )? "desc=cscr_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	外部车辆ID
			 	</a>
			 	${lz:set("checkeds[]","cscrCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrCarmodel || #request.defines['cscrCarmodel']!=null">
			 <td <s:if test="#request.defines['cscrCarmodel']>0">colspan="${defines["cscrCarmodel"]}" ${all?"width":"iwidth"}="${defines["cscrCarmodel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=60":""} </s:else> tdid="5" ref="cscrCarmodel"   title="性别">
			 	<a class="${desc=="cscr_carmodel" ? "desc" : ""}${asc=="cscr_carmodel" ? "asc" : ""}" href="?${desc=="cscr_carmodel" ? "asc=cscr_carmodel" : ""}${(asc=="cscr_carmodel" || desc!="cscr_carmodel" )? "desc=cscr_carmodel" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	外部车型ID
			 	</a>
			 	${lz:set("checkeds[]","cscrCarmodel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrCarNo || #request.defines['cscrCarNo']!=null">
			 <td <s:if test="#request.defines['cscrCarNo']>0">colspan="${defines["cscrCarNo"]}" ${all?"width":"iwidth"}="${defines["cscrCarNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="cscrCarNo"   title="手机号码">
			 	<a class="${desc=="cscr_car_no" ? "desc" : ""}${asc=="cscr_car_no" ? "asc" : ""}" href="?${desc=="cscr_car_no" ? "asc=cscr_car_no" : ""}${(asc=="cscr_car_no" || desc!="cscr_car_no" )? "desc=cscr_car_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车牌名称
			 	</a>
			 	${lz:set("checkeds[]","cscrCarNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrColor || #request.defines['cscrColor']!=null">
			 <td <s:if test="#request.defines['cscrColor']>0">colspan="${defines["cscrColor"]}" ${all?"width":"iwidth"}="${defines["cscrColor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="cscrColor"   title="司机照片">
			 	<a class="${desc=="cscr_color" ? "desc" : ""}${asc=="cscr_color" ? "asc" : ""}" href="?${desc=="cscr_color" ? "asc=cscr_color" : ""}${(asc=="cscr_color" || desc!="cscr_color" )? "desc=cscr_color" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车辆颜色
			 	</a>
			 	${lz:set("checkeds[]","cscrColor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrLocation || #request.defines['cscrLocation']!=null">
			 <td <s:if test="#request.defines['cscrLocation']>0">colspan="${defines["cscrLocation"]}" ${all?"width":"iwidth"}="${defines["cscrLocation"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="8" ref="cscrLocation"   title="身份证号码">
			 	<a class="${desc=="cscr_location" ? "desc" : ""}${asc=="cscr_location" ? "asc" : ""}" href="?${desc=="cscr_location" ? "asc=cscr_location" : ""}${(asc=="cscr_location" || desc!="cscr_location" )? "desc=cscr_location" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	停放位置
			 	</a>
			 	${lz:set("checkeds[]","cscrLocation")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrSource || #request.defines['cscrSource']!=null">
			 <td <s:if test="#request.defines['cscrSource']>0">colspan="${defines["cscrSource"]}" ${all?"width":"iwidth"}="${defines["cscrSource"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="9" ref="cscrSource"   title="驾驶证号码">
			 	<a class="${desc=="cscr_source" ? "desc" : ""}${asc=="cscr_source" ? "asc" : ""}" href="?${desc=="cscr_source" ? "asc=cscr_source" : ""}${(asc=="cscr_source" || desc!="cscr_source" )? "desc=cscr_source" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	数据来源
			 	</a>
			 	${lz:set("checkeds[]","cscrSource")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrStatus || #request.defines['cscrStatus']!=null">
			 <td <s:if test="#request.defines['cscrStatus']>0">colspan="${defines["cscrStatus"]}" ${all?"width":"iwidth"}="${defines["cscrStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=80":""} </s:else> tdid="10" ref="cscrStatus"   title="实名认证状态">
			 	<a class="${desc=="cscr_status" ? "desc" : ""}${asc=="cscr_status" ? "asc" : ""}" href="?${desc=="cscr_status" ? "asc=cscr_status" : ""}${(asc=="cscr_status" || desc!="cscr_status" )? "desc=cscr_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	数据状态
			 	</a>
			 	${lz:set("checkeds[]","cscrStatus")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrAddTime || #request.defines['cscrAddTime']!=null">
			 <td <s:if test="#request.defines['cscrAddTime']>0">colspan="${defines["cscrAddTime"]}" ${all?"width":"iwidth"}="${defines["cscrAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=80":""} </s:else> tdid="10" ref="cscrAddTime"   title="数据状态">
			 	<a class="${desc=="cscr_add_time" ? "desc" : ""}${asc=="cscr_add_time" ? "asc" : ""}" href="?${desc=="cscr_add_time" ? "asc=cscr_add_time" : ""}${(asc=="cscr_add_time" || desc!="cscr_add_time" )? "desc=cscr_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	创建时间
			 	</a>
			 	${lz:set("checkeds[]","cscrAddTime")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrUpdateTime || #request.defines['cscrUpdateTime']!=null">
			 <td <s:if test="#request.defines['cscrUpdateTime']>0">colspan="${defines["cscrUpdateTime"]}" ${all?"width":"iwidth"}="${defines["cscrUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="cscrUpdateTime"   title="创建时间">
			 	<a class="${desc=="cscr_update_time" ? "desc" : ""}${asc=="cscr_update_time" ? "asc" : ""}" href="?${desc=="cscr_update_time" ? "asc=cscr_update_time" : ""}${(asc=="cscr_update_time" || desc!="cscr_update_time" )? "desc=cscr_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","cscrUpdateTime")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="11" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="12" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:Addcarres()" style="text-decoration:underline">添加</a>
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
		${lz:set("注释","*****************数据列表表头结束*****************")}
		${lz:set("注释","*****************数据列表内容开始*****************")}
		 
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.cscrId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.cscrId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscrId || #request.defines['cscrId']!=null">
				<s:if test="#request.defines['cscrId']>0">
					${lz:set("注释","****cscrId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscrId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcscrId",lz:indexOf(fieldName,"cscrId")>-1)}
				  		<s:if test="#request.atcscrId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscrId字段的字串格式化输出****")}
					<td ref="cscrId" class="td ">
						 
						 	${lz:or(item$cscrId[i.count-1],lz:left(item.cscrId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscrHost || #request.defines['cscrHost']!=null">
				<s:if test="#request.defines['cscrHost']>0">
					${lz:set("注释","****cscrHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscrHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcscrHost",lz:indexOf(fieldName,"cscrHost")>-1)}
				  		<s:if test="#request.atcscrHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscrHost字段的字串格式化输出****")}
					<td ref="cscrHost" class="td " relate="${item.cscrHost}">
						 
						 	<a <s:if test="#item.cscrHost!=null && #item.cscrHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.cscrHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscrHost[i.count-1],lz:left(item.cscrHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscrOutlets || #request.defines['cscrOutlets']!=null">
				<s:if test="#request.defines['cscrOutlets']>0">
					${lz:set("注释","****cscrOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscrOutlets))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcscrOutlets",lz:indexOf(fieldName,"cscrOutlets")>-1)}
				  		<s:if test="#request.atcscrOutlets==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscrOutlets字段的字串格式化输出****")}
					<td ref="cscrOutlets" class="td " relate="${item.cscrOutlets}">
						 
						 	<a <s:if test="#item.cscrOutlets!=null && #item.cscrOutlets!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.cscrOutlets}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${item.cscrOutlets$}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscrCar || #request.defines['cscrCar']!=null">
				<s:if test="#request.defines['cscrCar']>0">
					${lz:set("注释","****cscrCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscrCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcscrCar",lz:indexOf(fieldName,"cscrCar")>-1)}
				  		<s:if test="#request.atcscrCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscrCar字段的字串格式化输出****")}
					<td ref="cscrCar" class="td  node">
						 	<a <s:if test="#item.cscrCarmodel!=null && #item.cscrCarmodel!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.cscrCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 
						 	${lz:or(item$cscrCar[i.count-1],lz:left(item.cscrCar$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscrCarmodel || #request.defines['cscrCarmodel']!=null">
				<s:if test="#request.defines['cscrCarmodel']>0">
					${lz:set("注释","****cscrCarmodel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscrCarmodel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcscrCarmodel",lz:indexOf(fieldName,"cscrCarmodel")>-1)}
				  		<s:if test="#request.atcscrCarmodel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscrCarmodel字段的字串格式化输出****")}
					<td ref="cscrCarmodel" class="td " relate="${item.cscrCarmodel}">
						 
						 	<a <s:if test="#item.cscrCarmodel!=null && #item.cscrCarmodel!=''"> onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${item.cscrCarmodel}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscrCarmodel[i.count-1],lz:left(item.cscrCarmodel$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscrCarNo || #request.defines['cscrCarNo']!=null">
				<s:if test="#request.defines['cscrCarNo']>0">
					${lz:set("注释","****cscrCarNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscrCarNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcscrCarNo",lz:indexOf(fieldName,"cscrCarNo")>-1)}
				  		<s:if test="#request.atcscrCarNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscrCarNo字段的字串格式化输出****")}
					<td ref="cscrCarNo" class="td " relate="${item.cscrCarNo}">
						 
						 	
						 	${lz:or(item$cscrCarNo[i.count-1],lz:left(item.cscrCarNo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscrColor || #request.defines['cscrColor']!=null">
				<s:if test="#request.defines['cscrColor']>0">
					${lz:set("注释","****cscrColor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscrColor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcscrColor",lz:indexOf(fieldName,"cscrColor")>-1)}
				  		<s:if test="#request.atcscrColor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscrColor字段的字串格式化输出****")}
					<td ref="cscrColor" class="td ">
						 
						 	${lz:or(item$cscrColor[i.count-1],lz:left(item.cscrColor$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscrLocation || #request.defines['cscrLocation']!=null">
				<s:if test="#request.defines['cscrLocation']>0">
					${lz:set("注释","****cscrLocation关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscrLocation))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcscrLocation",lz:indexOf(fieldName,"cscrLocation")>-1)}
				  		<s:if test="#request.atcscrLocation==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscrLocation字段的字串格式化输出****")}
					<td ref="cscrLocation" class="td ">
						 
						 	${lz:or(item$cscrLocation[i.count-1],lz:left(item.cscrLocation$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscrSource || #request.defines['cscrSource']!=null">
				<s:if test="#request.defines['cscrSource']>0">
					${lz:set("注释","****cscrSource关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscrSource))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcscrSource",lz:indexOf(fieldName,"cscrSource")>-1)}
				  		<s:if test="#request.atcscrSource==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscrSource字段的字串格式化输出****")}
					<td ref="cscrSource" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscrId}','source')"></a>
							 </s:if>
					     </s:if>
						 	${lz:or(item$cscrSource[i.count-1],lz:left(item.cscrSource$,100))==1?"公务出行":"外部采集"}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscrStatus || #request.defines['cscrStatus']!=null">
				<s:if test="#request.defines['cscrStatus']>0">
					${lz:set("注释","****cscrStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscrStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcscrStatus",lz:indexOf(fieldName,"cscrStatus")>-1)}
				  		<s:if test="#request.atcscrStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscrStatus字段的字串格式化输出****")}
					<td ref="cscrStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscrId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cscrStatus[i.count-1],lz:left(item.cscrStatus$,100))}
					</td>
				</s:else>
			</s:if>
			
			
			<s:if test="#request.defines==null && #request.CTRL.l.cscrAddTime || #request.defines['cscrAddTime']!=null">
				<s:if test="#request.defines['cscrAddTime']>0">
					${lz:set("注释","****cscrAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscrAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcscrAddTime",lz:indexOf(fieldName,"cscrAddTime")>-1)}
				  		<s:if test="#request.atcscrAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscrAddTime字段的字串格式化输出****")}
					<td ref="cscrAddTime" class="td ">
						 	${lz:or(item$cscrAddTime[i.count-1],lz:left(item.cscrAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.cscrUpdateTime || #request.defines['cscrUpdateTime']!=null">
				<s:if test="#request.defines['cscrUpdateTime']>0">
					${lz:set("注释","****cscrAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscrUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcscrAddTime",lz:indexOf(fieldName,"cscrUpdateTime")>-1)}
				  		<s:if test="#request.atcscrUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscrUpdateTime字段的字串格式化输出****")}
					<td ref="cscrUpdateTime" class="td ">
						 	${lz:or(item$cscrUpdateTime[i.count-1],lz:left(item.cscrUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.cscrId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:Selectcarres('${item.cscrId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:Editcarres('${item.cscrId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:Delcarres('${item.cscrId}','${item.cscrCarNo}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:Detailscarres('${item.cscrId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscrId || #request.defines['cscrId']!=null">
				<s:if test="#request.defines['cscrId']>0">
					${lz:set("注释","****cscrId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcscrId",lz:indexOf(fieldName,"cscrId")>-1)}				  		
				  		<s:if test="#request.atcscrId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscrId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscrHost || #request.defines['cscrHost']!=null">
				<s:if test="#request.defines['cscrHost']>0">
					${lz:set("注释","****cscrHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcscrHost",lz:indexOf(fieldName,"cscrHost")>-1)}				  		
				  		<s:if test="#request.atcscrHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscrHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrOutlets || #request.defines['cscrOutlets']!=null">
				<s:if test="#request.defines['cscrOutlets']>0">
					${lz:set("注释","****cscrOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcscrOutlets",lz:indexOf(fieldName,"cscrOutlets")>-1)}				  		
				  		<s:if test="#request.atcscrOutlets==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscrOutlets[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			 
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscrCar || #request.defines['cscrCar']!=null">
				<s:if test="#request.defines['cscrCar']>0">
					${lz:set("注释","****cscrCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcscrCar",lz:indexOf(fieldName,"cscrCar")>-1)}				  		
				  		<s:if test="#request.atcscrCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscrCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscrCarmodel || #request.defines['cscrCarmodel']!=null">
				<s:if test="#request.defines['cscrCarmodel']>0">
					${lz:set("注释","****cscrCarmodel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcscrCarmodel",lz:indexOf(fieldName,"cscrCarmodel")>-1)}				  		
				  		<s:if test="#request.atcscrCarmodel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscrCarmodel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscrCarNo || #request.defines['cscrCarNo']!=null">
				<s:if test="#request.defines['cscrCarNo']>0">
					${lz:set("注释","****cscrCarNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcscrCarNo",lz:indexOf(fieldName,"cscrCarNo")>-1)}				  		
				  		<s:if test="#request.atcscrCarNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscrCarNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscrColor || #request.defines['cscrColor']!=null">
				<s:if test="#request.defines['cscrColor']>0">
					${lz:set("注释","****cscrColor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcscrColor",lz:indexOf(fieldName,"cscrColor")>-1)}				  		
				  		<s:if test="#request.atcscrColor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscrColor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscrLocation || #request.defines['cscrLocation']!=null">
				<s:if test="#request.defines['cscrLocation']>0">
					${lz:set("注释","****cscrLocation关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcscrLocation",lz:indexOf(fieldName,"cscrLocation")>-1)}				  		
				  		<s:if test="#request.atcscrLocation==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscrLocation[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscrSource || #request.defines['cscrSource']!=null">
				<s:if test="#request.defines['cscrSource']>0">
					${lz:set("注释","****cscrSource关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcscrSource",lz:indexOf(fieldName,"cscrSource")>-1)}				  		
				  		<s:if test="#request.atcscrSource==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscrSource[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscrStatus || #request.defines['cscrStatus']!=null">
				<s:if test="#request.defines['cscrStatus']>0">
					${lz:set("注释","****cscrStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcscrStatus",lz:indexOf(fieldName,"cscrStatus")>-1)}				  		
				  		<s:if test="#request.atcscrStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscrStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			 
			 
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrAddTime || #request.defines['cscrAddTime']!=null">
				<s:if test="#request.defines['cscrAddTime']>0">
					${lz:set("注释","****cscrAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcscrAddTime",lz:indexOf(fieldName,"cscrAddTime")>-1)}				  		
				  		<s:if test="#request.atcscrAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscrAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.cscrUpdateTime || #request.defines['cscrUpdateTime']!=null">
				<s:if test="#request.defines['cscrUpdateTime']>0">
					${lz:set("注释","****cscrUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcscrUpdateTime",lz:indexOf(fieldName,"cscrUpdateTime")>-1)}				  		
				  		<s:if test="#request.atcscrUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscrUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加专车信息
	**/
	function Addcarres(parent){		
		var url = "${basePath}${namespace}carres_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加专车信息'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择专车
	**/
	function Selectcarres(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改专车
	**/
	function Editcarres(id){
		var url = "${basePath}${namespace}carres_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改专车信息'}":lz:json(ctrl)}};
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
		Editcarres(checker.val());
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
				alert(fieldNames);
				var url = "${basePath}${namespace}carres_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新专车信息",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}carres_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新更新专车信息",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改专车信息
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}carres_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新用车人员",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["cscrStatus"]=true;
	fieldNames["source"]={};
	fieldNames["source"]["cscrSource"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改专车任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}carres_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新专车信息",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}carres_edit.do";
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
			var url = "${basePath}${namespace}carres_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除专车信息
	**/
	function Delcarres(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除专车信息["+flag+"]吗？</span>",
				title:"删除专车",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}carres_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选用车人员吗？</span>",
				title:"批量删除用车人员",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}carres_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示专车信息详情
	**/
	function Detailscarres(id)
	{
		var url = "${basePath}${namespace}carres_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"专车人员详情",editable:false,visible:true}};
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
		Detailscarres(checker.val());
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

