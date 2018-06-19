<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/specialcar.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csSpecialCar.csscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csscId,csscHost,csscInfo,csscPerson,csscUnitOrder,csscName,csscCount,csscCarModel,csscCarNumber,csscDriverName,csscMsg,csscStartAddr,csscStartTime,csscFinishAddr,csscFinishTime,csscScope,csscAssess,csscThird,csscThirdNo,csscUpdateTime,csscAddTime,csscResponse,csscStatus,csscIsCarpool",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csscId,csscHost,csscInfo,csscPerson,csscUnitOrder,csscName,csscMobile,csscCount,csscIsCarpool,csscCarModel,csscCarNumber,csscDriverName,csscDriverPhone,csscDriverImage,csscDriverColor,csscDriverCarInfo,csscMsg,csscStartPoint,csscStartAddr,csscStartTime,csscFinishPoint,csscFinishAddr,csscFinishTime,csscDuration,csscRealDur,csscKilo,csscScope,csscAssess,csscThird,csscThirdNo,csscThirdFee,csscThirdOtherFee,csscThirdOtherFeeDes,csscUpdateTime,csscAddTime,csscResponse,csscStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csscId,csscHost,csscInfo,csscPerson,csscUnitOrder,csscName,csscStartTime,csscThird,csscThirdNo,csscResponse,csscStatus,csscIsCarpool,csscDriverName,csscDriverPhone",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csscId,csscHost,csscInfo,csscPerson,csscUnitOrder,csscName,csscMobile,csscIsCarpool,csscStartTime,csscStatus,csscDriverName,csscDriverPhone",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>专车订单管理</title>
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

<script>
	window.handles={
		"派单":function(opt,id){
			var url = "${basePath}${namespace}specialcar_appoint.do";
			var params = {
				entrypoint:"${entrypoint}",
				id:id,
				ctrl:{
					fields:{
						csrMember:{
							defaultValue:id,
							editable:false
						},
						csrStatus:{
							defaultValue:1
						}	
					}
				}
			};
			href(url,params);
		},
		"结算":function(opt,id){
			var url = "${basePath}${namespace}specialcar_settle.do";
			var params = {
				entrypoint:"${entrypoint}",
				id:id,
				isAddType:true,
				ctrl:{
					fields:{
						csrMember:{
							defaultValue:id,
							editable:false
						},
						csrStatus:{
							defaultValue:1
						}	
					}
				}
			};
			href(url,params);
		},
		"改派":function(opt,id){
			var url = "${basePath}${namespace}specialcar_cAppoint.do";
			var params = {
				entrypoint:"${entrypoint}",
				appoint:"change",
				id:id,
				ctrl:{
					title:"订单改派",
					fields:{
						csrMember:{
							defaultValue:id,
							editable:false
						},
						csrStatus:{
							defaultValue:1
						}	
					}
				}
			};
			href(url,params);
		}
	};
</script>	

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
	<span class="caption">${empty title?"专车订单管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsSpecialCar" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="specialcar.query.jsp"%>

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
			<button type="button" class="add" onclick="AddSpecialcar();">
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
			<dl ref="com.ccclubs.model.CsSpecialCar" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsSpecialCar" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.csscStatus || #request.defines['csscStatus']!=null">
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
		<table id="specialcar_table" ref="CsSpecialCar" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscId || #request.defines['csscId']!=null">
			 <td <s:if test="#request.defines['csscId']>0">colspan="${defines["csscId"]}" ${all?"width":"iwidth"}="${defines["csscId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csscId" flagKey  title="编号">
			 	<a class="${desc=="cssc_id" ? "desc" : ""}${asc=="cssc_id" ? "asc" : ""}" href="?${desc=="cssc_id" ? "asc=cssc_id" : ""}${(asc=="cssc_id" || desc!="cssc_id" )? "desc=cssc_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csscId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscHost || #request.defines['csscHost']!=null">
			 <td <s:if test="#request.defines['csscHost']>0">colspan="${defines["csscHost"]}" ${all?"width":"iwidth"}="${defines["csscHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csscHost"   title="运营地区">
			 	<a class="${desc=="cssc_host" ? "desc" : ""}${asc=="cssc_host" ? "asc" : ""}" href="?${desc=="cssc_host" ? "asc=cssc_host" : ""}${(asc=="cssc_host" || desc!="cssc_host" )? "desc=cssc_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	运营地区
			 	</a>
			 	${lz:set("checkeds[]","csscHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscInfo || #request.defines['csscInfo']!=null">
			 <td <s:if test="#request.defines['csscInfo']>0">colspan="${defines["csscInfo"]}" ${all?"width":"iwidth"}="${defines["csscInfo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="csscInfo"   title="企业信息">
			 	<a class="${desc=="cssc_info" ? "desc" : ""}${asc=="cssc_info" ? "asc" : ""}" href="?${desc=="cssc_info" ? "asc=cssc_info" : ""}${(asc=="cssc_info" || desc!="cssc_info" )? "desc=cssc_info" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	企业信息
			 	</a>
			 	${lz:set("checkeds[]","csscInfo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscPerson || #request.defines['csscPerson']!=null">
			 <td <s:if test="#request.defines['csscPerson']>0">colspan="${defines["csscPerson"]}" ${all?"width":"iwidth"}="${defines["csscPerson"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csscPerson"   title="用车人员">
			 	<a class="${desc=="cssc_person" ? "desc" : ""}${asc=="cssc_person" ? "asc" : ""}" href="?${desc=="cssc_person" ? "asc=cssc_person" : ""}${(asc=="cssc_person" || desc!="cssc_person" )? "desc=cssc_person" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	用车人员
			 	</a>
			 	${lz:set("checkeds[]","csscPerson")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscUnitOrder || #request.defines['csscUnitOrder']!=null">
			 <td <s:if test="#request.defines['csscUnitOrder']>0">colspan="${defines["csscUnitOrder"]}" ${all?"width":"iwidth"}="${defines["csscUnitOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csscUnitOrder"   title="企业订单">
			 	<a class="${desc=="cssc_unit_order" ? "desc" : ""}${asc=="cssc_unit_order" ? "asc" : ""}" href="?${desc=="cssc_unit_order" ? "asc=cssc_unit_order" : ""}${(asc=="cssc_unit_order" || desc!="cssc_unit_order" )? "desc=cssc_unit_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	企业订单
			 	</a>
			 	${lz:set("checkeds[]","csscUnitOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscName || #request.defines['csscName']!=null">
			 <td <s:if test="#request.defines['csscName']>0">colspan="${defines["csscName"]}" ${all?"width":"iwidth"}="${defines["csscName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="6" ref="csscName"   title="乘客姓名">
			 	<a class="${desc=="cssc_name" ? "desc" : ""}${asc=="cssc_name" ? "asc" : ""}" href="?${desc=="cssc_name" ? "asc=cssc_name" : ""}${(asc=="cssc_name" || desc!="cssc_name" )? "desc=cssc_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	乘客姓名
			 	</a>
			 	${lz:set("checkeds[]","csscName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscMobile || #request.defines['csscMobile']!=null">
			 <td <s:if test="#request.defines['csscMobile']>0">colspan="${defines["csscMobile"]}" ${all?"width":"iwidth"}="${defines["csscMobile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="csscMobile"   title="乘客电话">
			 	<a class="${desc=="cssc_mobile" ? "desc" : ""}${asc=="cssc_mobile" ? "asc" : ""}" href="?${desc=="cssc_mobile" ? "asc=cssc_mobile" : ""}${(asc=="cssc_mobile" || desc!="cssc_mobile" )? "desc=cssc_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	乘客电话
			 	</a>
			 	${lz:set("checkeds[]","csscMobile")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csscIsCarpool || #request.defines['csscIsCarpool']!=null">
			 <td <s:if test="#request.defines['csscIsCarpool']>0">colspan="${defines["csscIsCarpool"]}" ${all?"width":"iwidth"}="${defines["csscMobile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="csscIsCarpool"   title="是否拼车">
			 	<a class="${desc=="cssc_is_carpool" ? "desc" : ""}${asc=="cssc_is_carpool" ? "asc" : ""}" href="?${desc=="cssc_is_carpool" ? "asc=cssc_is_carpool" : ""}${(asc=="cssc_is_carpool" || desc!="cssc_is_carpool" )? "desc=cssc_is_carpool" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	拼车
			 	</a>
			 	${lz:set("checkeds[]","csscIsCarpool")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscCount || #request.defines['csscCount']!=null">
			 <td <s:if test="#request.defines['csscCount']>0">colspan="${defines["csscCount"]}" ${all?"width":"iwidth"}="${defines["csscCount"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csscCount"   title="乘车人数">
			 	<a class="${desc=="cssc_count" ? "desc" : ""}${asc=="cssc_count" ? "asc" : ""}" href="?${desc=="cssc_count" ? "asc=cssc_count" : ""}${(asc=="cssc_count" || desc!="cssc_count" )? "desc=cssc_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	乘车人数
			 	</a>
			 	${lz:set("checkeds[]","csscCount")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscCarModel || #request.defines['csscCarModel']!=null">
			 <td <s:if test="#request.defines['csscCarModel']>0">colspan="${defines["csscCarModel"]}" ${all?"width":"iwidth"}="${defines["csscCarModel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="csscCarModel"   title="预定车型">
			 	<a class="${desc=="cssc_car_model" ? "desc" : ""}${asc=="cssc_car_model" ? "asc" : ""}" href="?${desc=="cssc_car_model" ? "asc=cssc_car_model" : ""}${(asc=="cssc_car_model" || desc!="cssc_car_model" )? "desc=cssc_car_model" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预定车型
			 	</a>
			 	${lz:set("checkeds[]","csscCarModel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscCarNumber || #request.defines['csscCarNumber']!=null">
			 <td <s:if test="#request.defines['csscCarNumber']>0">colspan="${defines["csscCarNumber"]}" ${all?"width":"iwidth"}="${defines["csscCarNumber"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="10" ref="csscCarNumber"   title="车牌号码">
			 	<a class="${desc=="cssc_car_number" ? "desc" : ""}${asc=="cssc_car_number" ? "asc" : ""}" href="?${desc=="cssc_car_number" ? "asc=cssc_car_number" : ""}${(asc=="cssc_car_number" || desc!="cssc_car_number" )? "desc=cssc_car_number" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车牌号码
			 	</a>
			 	${lz:set("checkeds[]","csscCarNumber")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscDriverName || #request.defines['csscDriverName']!=null">
			 <td <s:if test="#request.defines['csscDriverName']>0">colspan="${defines["csscDriverName"]}" ${all?"width":"iwidth"}="${defines["csscDriverName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="csscDriverName"   title="司机姓名">
			 	<a class="${desc=="cssc_driver_name" ? "desc" : ""}${asc=="cssc_driver_name" ? "asc" : ""}" href="?${desc=="cssc_driver_name" ? "asc=cssc_driver_name" : ""}${(asc=="cssc_driver_name" || desc!="cssc_driver_name" )? "desc=cssc_driver_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	司机姓名
			 	</a>
			 	${lz:set("checkeds[]","csscDriverName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscDriverPhone || #request.defines['csscDriverPhone']!=null">
			 <td <s:if test="#request.defines['csscDriverPhone']>0">colspan="${defines["csscDriverPhone"]}" ${all?"width":"iwidth"}="${defines["csscDriverPhone"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="csscDriverPhone"   title="司机电话">
			 	<a class="${desc=="cssc_driver_phone" ? "desc" : ""}${asc=="cssc_driver_phone" ? "asc" : ""}" href="?${desc=="cssc_driver_phone" ? "asc=cssc_driver_phone" : ""}${(asc=="cssc_driver_phone" || desc!="cssc_driver_phone" )? "desc=cssc_driver_phone" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	司机电话
			 	</a>
			 	${lz:set("checkeds[]","csscDriverPhone")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscDriverImage || #request.defines['csscDriverImage']!=null">
			 <td <s:if test="#request.defines['csscDriverImage']>0">colspan="${defines["csscDriverImage"]}" ${all?"width":"iwidth"}="${defines["csscDriverImage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="csscDriverImage"   title="司机照片">
			 	<a class="${desc=="cssc_driver_image" ? "desc" : ""}${asc=="cssc_driver_image" ? "asc" : ""}" href="?${desc=="cssc_driver_image" ? "asc=cssc_driver_image" : ""}${(asc=="cssc_driver_image" || desc!="cssc_driver_image" )? "desc=cssc_driver_image" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	司机照片
			 	</a>
			 	${lz:set("checkeds[]","csscDriverImage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscDriverColor || #request.defines['csscDriverColor']!=null">
			 <td <s:if test="#request.defines['csscDriverColor']>0">colspan="${defines["csscDriverColor"]}" ${all?"width":"iwidth"}="${defines["csscDriverColor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="csscDriverColor"   title="车辆颜色">
			 	<a class="${desc=="cssc_driver_color" ? "desc" : ""}${asc=="cssc_driver_color" ? "asc" : ""}" href="?${desc=="cssc_driver_color" ? "asc=cssc_driver_color" : ""}${(asc=="cssc_driver_color" || desc!="cssc_driver_color" )? "desc=cssc_driver_color" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车辆颜色
			 	</a>
			 	${lz:set("checkeds[]","csscDriverColor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscDriverCarInfo || #request.defines['csscDriverCarInfo']!=null">
			 <td <s:if test="#request.defines['csscDriverCarInfo']>0">colspan="${defines["csscDriverCarInfo"]}" ${all?"width":"iwidth"}="${defines["csscDriverCarInfo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="15" ref="csscDriverCarInfo"   title="车型信息">
			 	<a class="${desc=="cssc_driver_car_info" ? "desc" : ""}${asc=="cssc_driver_car_info" ? "asc" : ""}" href="?${desc=="cssc_driver_car_info" ? "asc=cssc_driver_car_info" : ""}${(asc=="cssc_driver_car_info" || desc!="cssc_driver_car_info" )? "desc=cssc_driver_car_info" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车型信息
			 	</a>
			 	${lz:set("checkeds[]","csscDriverCarInfo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscMsg || #request.defines['csscMsg']!=null">
			 <td <s:if test="#request.defines['csscMsg']>0">colspan="${defines["csscMsg"]}" ${all?"width":"iwidth"}="${defines["csscMsg"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="16" ref="csscMsg"   title="客户留言">
			 	<a class="${desc=="cssc_msg" ? "desc" : ""}${asc=="cssc_msg" ? "asc" : ""}" href="?${desc=="cssc_msg" ? "asc=cssc_msg" : ""}${(asc=="cssc_msg" || desc!="cssc_msg" )? "desc=cssc_msg" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	客户留言
			 	</a>
			 	${lz:set("checkeds[]","csscMsg")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscStartPoint || #request.defines['csscStartPoint']!=null">
			 <td <s:if test="#request.defines['csscStartPoint']>0">colspan="${defines["csscStartPoint"]}" ${all?"width":"iwidth"}="${defines["csscStartPoint"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="17" ref="csscStartPoint"   title="起点位置">
			 	<a class="${desc=="cssc_start_point" ? "desc" : ""}${asc=="cssc_start_point" ? "asc" : ""}" href="?${desc=="cssc_start_point" ? "asc=cssc_start_point" : ""}${(asc=="cssc_start_point" || desc!="cssc_start_point" )? "desc=cssc_start_point" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	起点位置
			 	</a>
			 	${lz:set("checkeds[]","csscStartPoint")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscStartAddr || #request.defines['csscStartAddr']!=null">
			 <td <s:if test="#request.defines['csscStartAddr']>0">colspan="${defines["csscStartAddr"]}" ${all?"width":"iwidth"}="${defines["csscStartAddr"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="18" ref="csscStartAddr"   title="起点地址">
			 	<a class="${desc=="cssc_start_addr" ? "desc" : ""}${asc=="cssc_start_addr" ? "asc" : ""}" href="?${desc=="cssc_start_addr" ? "asc=cssc_start_addr" : ""}${(asc=="cssc_start_addr" || desc!="cssc_start_addr" )? "desc=cssc_start_addr" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	起点地址
			 	</a>
			 	${lz:set("checkeds[]","csscStartAddr")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscStartTime || #request.defines['csscStartTime']!=null">
			 <td <s:if test="#request.defines['csscStartTime']>0">colspan="${defines["csscStartTime"]}" ${all?"width":"iwidth"}="${defines["csscStartTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="19" ref="csscStartTime"   title="开始时间">
			 	<a class="${desc=="cssc_start_time" ? "desc" : ""}${asc=="cssc_start_time" ? "asc" : ""}" href="?${desc=="cssc_start_time" ? "asc=cssc_start_time" : ""}${(asc=="cssc_start_time" || desc!="cssc_start_time" )? "desc=cssc_start_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	开始时间
			 	</a>
			 	${lz:set("checkeds[]","csscStartTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscFinishPoint || #request.defines['csscFinishPoint']!=null">
			 <td <s:if test="#request.defines['csscFinishPoint']>0">colspan="${defines["csscFinishPoint"]}" ${all?"width":"iwidth"}="${defines["csscFinishPoint"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="20" ref="csscFinishPoint"   title="终点位置">
			 	<a class="${desc=="cssc_finish_point" ? "desc" : ""}${asc=="cssc_finish_point" ? "asc" : ""}" href="?${desc=="cssc_finish_point" ? "asc=cssc_finish_point" : ""}${(asc=="cssc_finish_point" || desc!="cssc_finish_point" )? "desc=cssc_finish_point" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	终点位置
			 	</a>
			 	${lz:set("checkeds[]","csscFinishPoint")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscFinishAddr || #request.defines['csscFinishAddr']!=null">
			 <td <s:if test="#request.defines['csscFinishAddr']>0">colspan="${defines["csscFinishAddr"]}" ${all?"width":"iwidth"}="${defines["csscFinishAddr"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="21" ref="csscFinishAddr"   title="终点地址">
			 	<a class="${desc=="cssc_finish_addr" ? "desc" : ""}${asc=="cssc_finish_addr" ? "asc" : ""}" href="?${desc=="cssc_finish_addr" ? "asc=cssc_finish_addr" : ""}${(asc=="cssc_finish_addr" || desc!="cssc_finish_addr" )? "desc=cssc_finish_addr" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	终点地址
			 	</a>
			 	${lz:set("checkeds[]","csscFinishAddr")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscFinishTime || #request.defines['csscFinishTime']!=null">
			 <td <s:if test="#request.defines['csscFinishTime']>0">colspan="${defines["csscFinishTime"]}" ${all?"width":"iwidth"}="${defines["csscFinishTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="22" ref="csscFinishTime"   title="结束时间">
			 	<a class="${desc=="cssc_finish_time" ? "desc" : ""}${asc=="cssc_finish_time" ? "asc" : ""}" href="?${desc=="cssc_finish_time" ? "asc=cssc_finish_time" : ""}${(asc=="cssc_finish_time" || desc!="cssc_finish_time" )? "desc=cssc_finish_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结束时间
			 	</a>
			 	${lz:set("checkeds[]","csscFinishTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscDuration || #request.defines['csscDuration']!=null">
			 <td <s:if test="#request.defines['csscDuration']>0">colspan="${defines["csscDuration"]}" ${all?"width":"iwidth"}="${defines["csscDuration"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="23" ref="csscDuration"   title="订单时长(分钟)">
			 	<a class="${desc=="cssc_duration" ? "desc" : ""}${asc=="cssc_duration" ? "asc" : ""}" href="?${desc=="cssc_duration" ? "asc=cssc_duration" : ""}${(asc=="cssc_duration" || desc!="cssc_duration" )? "desc=cssc_duration" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单时长(分钟)
			 	</a>
			 	${lz:set("checkeds[]","csscDuration")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscRealDur || #request.defines['csscRealDur']!=null">
			 <td <s:if test="#request.defines['csscRealDur']>0">colspan="${defines["csscRealDur"]}" ${all?"width":"iwidth"}="${defines["csscRealDur"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="24" ref="csscRealDur"   title="实际时长(分钟)">
			 	<a class="${desc=="cssc_real_dur" ? "desc" : ""}${asc=="cssc_real_dur" ? "asc" : ""}" href="?${desc=="cssc_real_dur" ? "asc=cssc_real_dur" : ""}${(asc=="cssc_real_dur" || desc!="cssc_real_dur" )? "desc=cssc_real_dur" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际时长(分钟)
			 	</a>
			 	${lz:set("checkeds[]","csscRealDur")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscKilo || #request.defines['csscKilo']!=null">
			 <td <s:if test="#request.defines['csscKilo']>0">colspan="${defines["csscKilo"]}" ${all?"width":"iwidth"}="${defines["csscKilo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="25" ref="csscKilo"   title="公里数">
			 	<a class="${desc=="cssc_kilo" ? "desc" : ""}${asc=="cssc_kilo" ? "asc" : ""}" href="?${desc=="cssc_kilo" ? "asc=cssc_kilo" : ""}${(asc=="cssc_kilo" || desc!="cssc_kilo" )? "desc=cssc_kilo" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	公里数
			 	</a>
			 	${lz:set("checkeds[]","csscKilo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscScope || #request.defines['csscScope']!=null">
			 <td <s:if test="#request.defines['csscScope']>0">colspan="${defines["csscScope"]}" ${all?"width":"iwidth"}="${defines["csscScope"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="26" ref="csscScope"   title="用车评分">
			 	<a class="${desc=="cssc_scope" ? "desc" : ""}${asc=="cssc_scope" ? "asc" : ""}" href="?${desc=="cssc_scope" ? "asc=cssc_scope" : ""}${(asc=="cssc_scope" || desc!="cssc_scope" )? "desc=cssc_scope" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	用车评分
			 	</a>
			 	${lz:set("checkeds[]","csscScope")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscAssess || #request.defines['csscAssess']!=null">
			 <td <s:if test="#request.defines['csscAssess']>0">colspan="${defines["csscAssess"]}" ${all?"width":"iwidth"}="${defines["csscAssess"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="27" ref="csscAssess"   title="用车评价">
			 	<a class="${desc=="cssc_assess" ? "desc" : ""}${asc=="cssc_assess" ? "asc" : ""}" href="?${desc=="cssc_assess" ? "asc=cssc_assess" : ""}${(asc=="cssc_assess" || desc!="cssc_assess" )? "desc=cssc_assess" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	用车评价
			 	</a>
			 	${lz:set("checkeds[]","csscAssess")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscThird || #request.defines['csscThird']!=null">
			 <td <s:if test="#request.defines['csscThird']>0">colspan="${defines["csscThird"]}" ${all?"width":"iwidth"}="${defines["csscThird"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="28" ref="csscThird"   title="三方名称">
			 	<a class="${desc=="cssc_third" ? "desc" : ""}${asc=="cssc_third" ? "asc" : ""}" href="?${desc=="cssc_third" ? "asc=cssc_third" : ""}${(asc=="cssc_third" || desc!="cssc_third" )? "desc=cssc_third" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	三方名称
			 	</a>
			 	${lz:set("checkeds[]","csscThird")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscThirdNo || #request.defines['csscThirdNo']!=null">
			 <td <s:if test="#request.defines['csscThirdNo']>0">colspan="${defines["csscThirdNo"]}" ${all?"width":"iwidth"}="${defines["csscThirdNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="29" ref="csscThirdNo"   title="三方订单号">
			 	<a class="${desc=="cssc_third_no" ? "desc" : ""}${asc=="cssc_third_no" ? "asc" : ""}" href="?${desc=="cssc_third_no" ? "asc=cssc_third_no" : ""}${(asc=="cssc_third_no" || desc!="cssc_third_no" )? "desc=cssc_third_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	三方订单号
			 	</a>
			 	${lz:set("checkeds[]","csscThirdNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscThirdFee || #request.defines['csscThirdFee']!=null">
			 <td <s:if test="#request.defines['csscThirdFee']>0">colspan="${defines["csscThirdFee"]}" ${all?"width":"iwidth"}="${defines["csscThirdFee"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="30" ref="csscThirdFee"   title="三方费用">
			 	<a class="${desc=="cssc_third_fee" ? "desc" : ""}${asc=="cssc_third_fee" ? "asc" : ""}" href="?${desc=="cssc_third_fee" ? "asc=cssc_third_fee" : ""}${(asc=="cssc_third_fee" || desc!="cssc_third_fee" )? "desc=cssc_third_fee" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	三方费用
			 	</a>
			 	${lz:set("checkeds[]","csscThirdFee")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscThirdOtherFee || #request.defines['csscThirdOtherFee']!=null">
			 <td <s:if test="#request.defines['csscThirdOtherFee']>0">colspan="${defines["csscThirdOtherFee"]}" ${all?"width":"iwidth"}="${defines["csscThirdOtherFee"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="31" ref="csscThirdOtherFee"   title="三方其它费用">
			 	<a class="${desc=="cssc_third_other_fee" ? "desc" : ""}${asc=="cssc_third_other_fee" ? "asc" : ""}" href="?${desc=="cssc_third_other_fee" ? "asc=cssc_third_other_fee" : ""}${(asc=="cssc_third_other_fee" || desc!="cssc_third_other_fee" )? "desc=cssc_third_other_fee" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	三方其它费用
			 	</a>
			 	${lz:set("checkeds[]","csscThirdOtherFee")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscThirdOtherFeeDes || #request.defines['csscThirdOtherFeeDes']!=null">
			 <td <s:if test="#request.defines['csscThirdOtherFeeDes']>0">colspan="${defines["csscThirdOtherFeeDes"]}" ${all?"width":"iwidth"}="${defines["csscThirdOtherFeeDes"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="32" ref="csscThirdOtherFeeDes"   title="三方其它费用描述">
			 	<a class="${desc=="cssc_third_other_fee_des" ? "desc" : ""}${asc=="cssc_third_other_fee_des" ? "asc" : ""}" href="?${desc=="cssc_third_other_fee_des" ? "asc=cssc_third_other_fee_des" : ""}${(asc=="cssc_third_other_fee_des" || desc!="cssc_third_other_fee_des" )? "desc=cssc_third_other_fee_des" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	三方其它费用描述
			 	</a>
			 	${lz:set("checkeds[]","csscThirdOtherFeeDes")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscUpdateTime || #request.defines['csscUpdateTime']!=null">
			 <td <s:if test="#request.defines['csscUpdateTime']>0">colspan="${defines["csscUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csscUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="33" ref="csscUpdateTime"   title="修改时间">
			 	<a class="${desc=="cssc_update_time" ? "desc" : ""}${asc=="cssc_update_time" ? "asc" : ""}" href="?${desc=="cssc_update_time" ? "asc=cssc_update_time" : ""}${(asc=="cssc_update_time" || desc!="cssc_update_time" )? "desc=cssc_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csscUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscAddTime || #request.defines['csscAddTime']!=null">
			 <td <s:if test="#request.defines['csscAddTime']>0">colspan="${defines["csscAddTime"]}" ${all?"width":"iwidth"}="${defines["csscAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="34" ref="csscAddTime"   title="添加时间">
			 	<a class="${desc=="cssc_add_time" ? "desc" : ""}${asc=="cssc_add_time" ? "asc" : ""}" href="?${desc=="cssc_add_time" ? "asc=cssc_add_time" : ""}${(asc=="cssc_add_time" || desc!="cssc_add_time" )? "desc=cssc_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csscAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscResponse || #request.defines['csscResponse']!=null">
			 <td <s:if test="#request.defines['csscResponse']>0">colspan="${defines["csscResponse"]}" ${all?"width":"iwidth"}="${defines["csscResponse"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="35" ref="csscResponse"   title="响应状态">
			 	<a class="${desc=="cssc_response" ? "desc" : ""}${asc=="cssc_response" ? "asc" : ""}" href="?${desc=="cssc_response" ? "asc=cssc_response" : ""}${(asc=="cssc_response" || desc!="cssc_response" )? "desc=cssc_response" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	响应状态
			 	</a>
			 	${lz:set("checkeds[]","csscResponse")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csscStatus || #request.defines['csscStatus']!=null">
			 <td <s:if test="#request.defines['csscStatus']>0">colspan="${defines["csscStatus"]}" ${all?"width":"iwidth"}="${defines["csscStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="36" ref="csscStatus"   title="状态">
			 	<a class="${desc=="cssc_status" ? "desc" : ""}${asc=="cssc_status" ? "asc" : ""}" href="?${desc=="cssc_status" ? "asc=cssc_status" : ""}${(asc=="cssc_status" || desc!="cssc_status" )? "desc=cssc_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csscStatus")}
			 </td>
			 </s:if>
			 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="37" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="38" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddSpecialcar()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.csscId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csscId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscId || #request.defines['csscId']!=null">
				<s:if test="#request.defines['csscId']>0">
					${lz:set("注释","****csscId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscId",lz:indexOf(fieldName,"csscId")>-1)}
				  		<s:if test="#request.atCsscId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscId字段的字串格式化输出****")}
					<td ref="csscId" class="td  node">
						 
						 	${lz:or(item$csscId[i.count-1],(lz:left(item.csscId$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscHost || #request.defines['csscHost']!=null">
				<s:if test="#request.defines['csscHost']>0">
					${lz:set("注释","****csscHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscHost",lz:indexOf(fieldName,"csscHost")>-1)}
				  		<s:if test="#request.atCsscHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscHost字段的字串格式化输出****")}
					<td ref="csscHost" class="td " relate="${item.csscHost}">
						 
						 	<a <s:if test="#item.csscHost!=null && #item.csscHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csscHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csscHost[i.count-1],(lz:left(item.csscHost$,100)))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscInfo || #request.defines['csscInfo']!=null">
				<s:if test="#request.defines['csscInfo']>0">
					${lz:set("注释","****csscInfo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscInfo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscInfo",lz:indexOf(fieldName,"csscInfo")>-1)}
				  		<s:if test="#request.atCsscInfo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscInfo字段的字串格式化输出****")}
					<td ref="csscInfo" class="td " relate="${item.csscInfo}">
						 
						 	<a <s:if test="#item.csscInfo!=null && #item.csscInfo!=''"> onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${item.csscInfo}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csscInfo[i.count-1],(lz:left(item.csscInfo$,100)))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscPerson || #request.defines['csscPerson']!=null">
				<s:if test="#request.defines['csscPerson']>0">
					${lz:set("注释","****csscPerson关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscPerson))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscPerson",lz:indexOf(fieldName,"csscPerson")>-1)}
				  		<s:if test="#request.atCsscPerson==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscPerson字段的字串格式化输出****")}
					<td ref="csscPerson" class="td " relate="${item.csscPerson}">
						 
						 	<a <s:if test="#item.csscPerson!=null && #item.csscPerson!=''"> onclick="window.href('${basePath}${proname}/unit/person_details.do?key=${item.csscPerson}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csscPerson[i.count-1],(lz:left(item.csscPerson$,100)))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscUnitOrder || #request.defines['csscUnitOrder']!=null">
				<s:if test="#request.defines['csscUnitOrder']>0">
					${lz:set("注释","****csscUnitOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscUnitOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscUnitOrder",lz:indexOf(fieldName,"csscUnitOrder")>-1)}
				  		<s:if test="#request.atCsscUnitOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscUnitOrder字段的字串格式化输出****")}
					<td ref="csscUnitOrder" class="td " relate="${item.csscUnitOrder}">
						 
						 	<a <s:if test="#item.csscUnitOrder!=null && #item.csscUnitOrder!=''"> onclick="window.href('${basePath}${proname}/unit/order_details.do?key=${item.csscUnitOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csscUnitOrder[i.count-1],(lz:left(item.csscUnitOrder$,100)))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscName || #request.defines['csscName']!=null">
				<s:if test="#request.defines['csscName']>0">
					${lz:set("注释","****csscName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscName",lz:indexOf(fieldName,"csscName")>-1)}
				  		<s:if test="#request.atCsscName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscName字段的字串格式化输出****")}
					<td ref="csscName" class="td ">
						 
						 	${lz:or(item$csscName[i.count-1],(lz:left(item.csscName$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscMobile || #request.defines['csscMobile']!=null">
				<s:if test="#request.defines['csscMobile']>0">
					${lz:set("注释","****csscMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscMobile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscMobile",lz:indexOf(fieldName,"csscMobile")>-1)}
				  		<s:if test="#request.atCsscMobile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscMobile字段的字串格式化输出****")}
					<td ref="csscMobile" class="td ">
						 
						 	${lz:or(item$csscMobile[i.count-1],(lz:left(item.csscMobile$,100)))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csscIsCarpool || #request.defines['csscIsCarpool']!=null">
				<s:if test="#request.defines['csscIsCarpool']>0">
					${lz:set("注释","****csscIsCarpool关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscIsCarpool))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscIsCarpool",lz:indexOf(fieldName,"csscIsCarpool")>-1)}
				  		<s:if test="#request.atCsscIsCarpool==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscIsCarpool字段的字串格式化输出****")}
					<td ref="csscIsCarpool" class="td ">
						 	${lz:or(item$csscIsCarpool[i.count-1],(lz:left(item.csscIsCarpool$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscCount || #request.defines['csscCount']!=null">
				<s:if test="#request.defines['csscCount']>0">
					${lz:set("注释","****csscCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscCount))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscCount",lz:indexOf(fieldName,"csscCount")>-1)}
				  		<s:if test="#request.atCsscCount==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscCount字段的字串格式化输出****")}
					<td ref="csscCount" class="td ">
						 
						 	${lz:or(item$csscCount[i.count-1],(lz:left(item.csscCount$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscCarModel || #request.defines['csscCarModel']!=null">
				<s:if test="#request.defines['csscCarModel']>0">
					${lz:set("注释","****csscCarModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscCarModel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscCarModel",lz:indexOf(fieldName,"csscCarModel")>-1)}
				  		<s:if test="#request.atCsscCarModel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscCarModel字段的字串格式化输出****")}
					<td ref="csscCarModel" class="td " relate="${item.csscCarModel}">
						 
						 	<a <s:if test="#item.csscCarModel!=null && #item.csscCarModel!=''"> onclick="window.href('${basePath}${proname}/unit/specialmodel_details.do?key=${item.csscCarModel}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csscCarModel[i.count-1],(lz:left(item.csscCarModel$,100)))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscCarNumber || #request.defines['csscCarNumber']!=null">
				<s:if test="#request.defines['csscCarNumber']>0">
					${lz:set("注释","****csscCarNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscCarNumber))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscCarNumber",lz:indexOf(fieldName,"csscCarNumber")>-1)}
				  		<s:if test="#request.atCsscCarNumber==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscCarNumber字段的字串格式化输出****")}
					<td ref="csscCarNumber" class="td ">
						 
						 	${lz:or(item$csscCarNumber[i.count-1],(lz:left(item.csscCarNumber$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscDriverName || #request.defines['csscDriverName']!=null">
				<s:if test="#request.defines['csscDriverName']>0">
					${lz:set("注释","****csscDriverName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscDriverName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscDriverName",lz:indexOf(fieldName,"csscDriverName")>-1)}
				  		<s:if test="#request.atCsscDriverName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscDriverName字段的字串格式化输出****")}
					<td ref="csscDriverName" class="td ">
						 
						 	${lz:or(item$csscDriverName[i.count-1],(lz:left(item.csscDriverName$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscDriverPhone || #request.defines['csscDriverPhone']!=null">
				<s:if test="#request.defines['csscDriverPhone']>0">
					${lz:set("注释","****csscDriverPhone关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscDriverPhone))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscDriverPhone",lz:indexOf(fieldName,"csscDriverPhone")>-1)}
				  		<s:if test="#request.atCsscDriverPhone==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscDriverPhone字段的字串格式化输出****")}
					<td ref="csscDriverPhone" class="td ">
						 
						 	${lz:or(item$csscDriverPhone[i.count-1],(lz:left(item.csscDriverPhone$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscDriverImage || #request.defines['csscDriverImage']!=null">
				<s:if test="#request.defines['csscDriverImage']>0">
					${lz:set("注释","****csscDriverImage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscDriverImage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscDriverImage",lz:indexOf(fieldName,"csscDriverImage")>-1)}
				  		<s:if test="#request.atCsscDriverImage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscDriverImage字段的字串格式化输出****")}
					<td ref="csscDriverImage" class="td ">
						 
						 	${lz:or(item$csscDriverImage[i.count-1],(lz:left(item.csscDriverImage$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscDriverColor || #request.defines['csscDriverColor']!=null">
				<s:if test="#request.defines['csscDriverColor']>0">
					${lz:set("注释","****csscDriverColor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscDriverColor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscDriverColor",lz:indexOf(fieldName,"csscDriverColor")>-1)}
				  		<s:if test="#request.atCsscDriverColor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscDriverColor字段的字串格式化输出****")}
					<td ref="csscDriverColor" class="td ">
						 
						 	${lz:or(item$csscDriverColor[i.count-1],(lz:left(item.csscDriverColor$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscDriverCarInfo || #request.defines['csscDriverCarInfo']!=null">
				<s:if test="#request.defines['csscDriverCarInfo']>0">
					${lz:set("注释","****csscDriverCarInfo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscDriverCarInfo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscDriverCarInfo",lz:indexOf(fieldName,"csscDriverCarInfo")>-1)}
				  		<s:if test="#request.atCsscDriverCarInfo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscDriverCarInfo字段的字串格式化输出****")}
					<td ref="csscDriverCarInfo" class="td ">
						 
						 	${lz:or(item$csscDriverCarInfo[i.count-1],(lz:left(item.csscDriverCarInfo$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscMsg || #request.defines['csscMsg']!=null">
				<s:if test="#request.defines['csscMsg']>0">
					${lz:set("注释","****csscMsg关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscMsg))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscMsg",lz:indexOf(fieldName,"csscMsg")>-1)}
				  		<s:if test="#request.atCsscMsg==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscMsg字段的字串格式化输出****")}
					<td ref="csscMsg" class="td ">
						 
						 	${lz:or(item$csscMsg[i.count-1],(lz:left(item.csscMsg$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscStartPoint || #request.defines['csscStartPoint']!=null">
				<s:if test="#request.defines['csscStartPoint']>0">
					${lz:set("注释","****csscStartPoint关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscStartPoint))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscStartPoint",lz:indexOf(fieldName,"csscStartPoint")>-1)}
				  		<s:if test="#request.atCsscStartPoint==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscStartPoint字段的字串格式化输出****")}
					<td ref="csscStartPoint" class="td ">
						 
						 	${lz:or(item$csscStartPoint[i.count-1],(lz:left(item.csscStartPoint$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscStartAddr || #request.defines['csscStartAddr']!=null">
				<s:if test="#request.defines['csscStartAddr']>0">
					${lz:set("注释","****csscStartAddr关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscStartAddr))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscStartAddr",lz:indexOf(fieldName,"csscStartAddr")>-1)}
				  		<s:if test="#request.atCsscStartAddr==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscStartAddr字段的字串格式化输出****")}
					<td ref="csscStartAddr" class="td ">
						 
						 	${lz:or(item$csscStartAddr[i.count-1],(lz:left(item.csscStartAddr$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscStartTime || #request.defines['csscStartTime']!=null">
				<s:if test="#request.defines['csscStartTime']>0">
					${lz:set("注释","****csscStartTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscStartTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscStartTime",lz:indexOf(fieldName,"csscStartTime")>-1)}
				  		<s:if test="#request.atCsscStartTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscStartTime字段的字串格式化输出****")}
					<td ref="csscStartTime" class="td ">
						 
						 	${lz:or(item$csscStartTime[i.count-1],(lz:left(item.csscStartTime$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscFinishPoint || #request.defines['csscFinishPoint']!=null">
				<s:if test="#request.defines['csscFinishPoint']>0">
					${lz:set("注释","****csscFinishPoint关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscFinishPoint))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscFinishPoint",lz:indexOf(fieldName,"csscFinishPoint")>-1)}
				  		<s:if test="#request.atCsscFinishPoint==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscFinishPoint字段的字串格式化输出****")}
					<td ref="csscFinishPoint" class="td ">
						 
						 	${lz:or(item$csscFinishPoint[i.count-1],(lz:left(item.csscFinishPoint$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscFinishAddr || #request.defines['csscFinishAddr']!=null">
				<s:if test="#request.defines['csscFinishAddr']>0">
					${lz:set("注释","****csscFinishAddr关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscFinishAddr))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscFinishAddr",lz:indexOf(fieldName,"csscFinishAddr")>-1)}
				  		<s:if test="#request.atCsscFinishAddr==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscFinishAddr字段的字串格式化输出****")}
					<td ref="csscFinishAddr" class="td ">
						 
						 	${lz:or(item$csscFinishAddr[i.count-1],(lz:left(item.csscFinishAddr$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscFinishTime || #request.defines['csscFinishTime']!=null">
				<s:if test="#request.defines['csscFinishTime']>0">
					${lz:set("注释","****csscFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscFinishTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscFinishTime",lz:indexOf(fieldName,"csscFinishTime")>-1)}
				  		<s:if test="#request.atCsscFinishTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscFinishTime字段的字串格式化输出****")}
					<td ref="csscFinishTime" class="td ">
						 
						 	${lz:or(item$csscFinishTime[i.count-1],(lz:left(item.csscFinishTime$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscDuration || #request.defines['csscDuration']!=null">
				<s:if test="#request.defines['csscDuration']>0">
					${lz:set("注释","****csscDuration关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscDuration))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscDuration",lz:indexOf(fieldName,"csscDuration")>-1)}
				  		<s:if test="#request.atCsscDuration==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscDuration字段的字串格式化输出****")}
					<td ref="csscDuration" class="td ">
						 
						 	${lz:or(item$csscDuration[i.count-1],(lz:left(item.csscDuration$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscRealDur || #request.defines['csscRealDur']!=null">
				<s:if test="#request.defines['csscRealDur']>0">
					${lz:set("注释","****csscRealDur关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscRealDur))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscRealDur",lz:indexOf(fieldName,"csscRealDur")>-1)}
				  		<s:if test="#request.atCsscRealDur==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscRealDur字段的字串格式化输出****")}
					<td ref="csscRealDur" class="td ">
						 
						 	${lz:or(item$csscRealDur[i.count-1],(lz:left(item.csscRealDur$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscKilo || #request.defines['csscKilo']!=null">
				<s:if test="#request.defines['csscKilo']>0">
					${lz:set("注释","****csscKilo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscKilo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscKilo",lz:indexOf(fieldName,"csscKilo")>-1)}
				  		<s:if test="#request.atCsscKilo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscKilo字段的字串格式化输出****")}
					<td ref="csscKilo" class="td ">
						 
						 	${lz:or(item$csscKilo[i.count-1],(lz:left(item.csscKilo$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscScope || #request.defines['csscScope']!=null">
				<s:if test="#request.defines['csscScope']>0">
					${lz:set("注释","****csscScope关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscScope))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscScope",lz:indexOf(fieldName,"csscScope")>-1)}
				  		<s:if test="#request.atCsscScope==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscScope字段的字串格式化输出****")}
					<td ref="csscScope" class="td ">
						 
						 	${lz:or(item$csscScope[i.count-1],(lz:left(item.csscScope$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscAssess || #request.defines['csscAssess']!=null">
				<s:if test="#request.defines['csscAssess']>0">
					${lz:set("注释","****csscAssess关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscAssess))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscAssess",lz:indexOf(fieldName,"csscAssess")>-1)}
				  		<s:if test="#request.atCsscAssess==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscAssess字段的字串格式化输出****")}
					<td ref="csscAssess" class="td ">
						 
						 	${lz:or(item$csscAssess[i.count-1],(lz:left(item.csscAssess$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscThird || #request.defines['csscThird']!=null">
				<s:if test="#request.defines['csscThird']>0">
					${lz:set("注释","****csscThird关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscThird))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscThird",lz:indexOf(fieldName,"csscThird")>-1)}
				  		<s:if test="#request.atCsscThird==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscThird字段的字串格式化输出****")}
					<td ref="csscThird" class="td ">
						 
						 	${lz:or(item$csscThird[i.count-1],(lz:left(item.csscThird$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscThirdNo || #request.defines['csscThirdNo']!=null">
				<s:if test="#request.defines['csscThirdNo']>0">
					${lz:set("注释","****csscThirdNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscThirdNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscThirdNo",lz:indexOf(fieldName,"csscThirdNo")>-1)}
				  		<s:if test="#request.atCsscThirdNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscThirdNo字段的字串格式化输出****")}
					<td ref="csscThirdNo" class="td ">
						 
						 	${lz:or(item$csscThirdNo[i.count-1],(lz:left(item.csscThirdNo$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscThirdFee || #request.defines['csscThirdFee']!=null">
				<s:if test="#request.defines['csscThirdFee']>0">
					${lz:set("注释","****csscThirdFee关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscThirdFee))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscThirdFee",lz:indexOf(fieldName,"csscThirdFee")>-1)}
				  		<s:if test="#request.atCsscThirdFee==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscThirdFee字段的字串格式化输出****")}
					<td ref="csscThirdFee" class="td ">
						 
						 	${lz:or(item$csscThirdFee[i.count-1],(lz:left(item.csscThirdFee$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscThirdOtherFee || #request.defines['csscThirdOtherFee']!=null">
				<s:if test="#request.defines['csscThirdOtherFee']>0">
					${lz:set("注释","****csscThirdOtherFee关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscThirdOtherFee))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscThirdOtherFee",lz:indexOf(fieldName,"csscThirdOtherFee")>-1)}
				  		<s:if test="#request.atCsscThirdOtherFee==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscThirdOtherFee字段的字串格式化输出****")}
					<td ref="csscThirdOtherFee" class="td ">
						 
						 	${lz:or(item$csscThirdOtherFee[i.count-1],(lz:left(item.csscThirdOtherFee$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscThirdOtherFeeDes || #request.defines['csscThirdOtherFeeDes']!=null">
				<s:if test="#request.defines['csscThirdOtherFeeDes']>0">
					${lz:set("注释","****csscThirdOtherFeeDes关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscThirdOtherFeeDes))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscThirdOtherFeeDes",lz:indexOf(fieldName,"csscThirdOtherFeeDes")>-1)}
				  		<s:if test="#request.atCsscThirdOtherFeeDes==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscThirdOtherFeeDes字段的字串格式化输出****")}
					<td ref="csscThirdOtherFeeDes" class="td ">
						 
						 	${lz:or(item$csscThirdOtherFeeDes[i.count-1],(lz:left(item.csscThirdOtherFeeDes$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscUpdateTime || #request.defines['csscUpdateTime']!=null">
				<s:if test="#request.defines['csscUpdateTime']>0">
					${lz:set("注释","****csscUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscUpdateTime",lz:indexOf(fieldName,"csscUpdateTime")>-1)}
				  		<s:if test="#request.atCsscUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscUpdateTime字段的字串格式化输出****")}
					<td ref="csscUpdateTime" class="td ">
						 
						 	${lz:or(item$csscUpdateTime[i.count-1],(lz:left(item.csscUpdateTime$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscAddTime || #request.defines['csscAddTime']!=null">
				<s:if test="#request.defines['csscAddTime']>0">
					${lz:set("注释","****csscAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscAddTime",lz:indexOf(fieldName,"csscAddTime")>-1)}
				  		<s:if test="#request.atCsscAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscAddTime字段的字串格式化输出****")}
					<td ref="csscAddTime" class="td ">
						 
						 	${lz:or(item$csscAddTime[i.count-1],(lz:left(item.csscAddTime$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscResponse || #request.defines['csscResponse']!=null">
				<s:if test="#request.defines['csscResponse']>0">
					${lz:set("注释","****csscResponse关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscResponse))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscResponse",lz:indexOf(fieldName,"csscResponse")>-1)}
				  		<s:if test="#request.atCsscResponse==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscResponse字段的字串格式化输出****")}
					<td ref="csscResponse" class="td ">
						 
						 	${lz:or(item$csscResponse[i.count-1],(lz:left(item.csscResponse$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csscStatus || #request.defines['csscStatus']!=null">
				<s:if test="#request.defines['csscStatus']>0">
					${lz:set("注释","****csscStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csscStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsscStatus",lz:indexOf(fieldName,"csscStatus")>-1)}
				  		<s:if test="#request.atCsscStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csscStatus字段的字串格式化输出****")}
					<td ref="csscStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csscId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csscStatus[i.count-1],(lz:left(item.csscStatus$,100)))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
<!-- 		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")} -->
<!-- 			<td class="options"> -->
<!-- 				${lz:set("items",lz:split(handles,","))} -->
<!-- 				<s:iterator value="#request.items" id="handle" status="i"> -->
<!-- 					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))} -->
<!-- 					<s:if test="#request.isMenu!=true"> -->
<!-- 					<a href="javascript:HandleSel('${handle}','${item.csscId}')">${handle}</a> -->
<!-- 					</s:if> -->
<!-- 				</s:iterator> -->
<!-- 			</td> -->
			
			 ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">
					<s:if test="#request.CTRL.canExp[0]==true && #item.$csscUnitOrder.csuoSettleState ==0 && #item.csscStatus<3">
					<a href="javascript:HandleSel('派单','${item.csscId}')">派单</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[1]==true && #item.$csscUnitOrder.csuoSettleState == 0 && (#item.csscStatus==3 || #item.csscStatus==7 || #item.csscStatus==8 || #item.csscStatus==9 )">
					<a href="javascript:HandleSel('结算','${item.csscId}')">结算</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[2]==true && #item.csscStatus==3 ">
					<a href="javascript:HandleSel('改派','${item.csscId}')">改派</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.csscId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectSpecialcar('${item.csscId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditSpecialcar('${item.csscId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelSpecialcar('${item.csscId}','${item.csscId}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsSpecialcar('${item.csscId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csscId || #request.defines['csscId']!=null">
				<s:if test="#request.defines['csscId']>0">
					${lz:set("注释","****csscId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscId",lz:indexOf(fieldName,"csscId")>-1)}				  		
				  		<s:if test="#request.atCsscId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscHost || #request.defines['csscHost']!=null">
				<s:if test="#request.defines['csscHost']>0">
					${lz:set("注释","****csscHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscHost",lz:indexOf(fieldName,"csscHost")>-1)}				  		
				  		<s:if test="#request.atCsscHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscInfo || #request.defines['csscInfo']!=null">
				<s:if test="#request.defines['csscInfo']>0">
					${lz:set("注释","****csscInfo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscInfo",lz:indexOf(fieldName,"csscInfo")>-1)}				  		
				  		<s:if test="#request.atCsscInfo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscInfo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscPerson || #request.defines['csscPerson']!=null">
				<s:if test="#request.defines['csscPerson']>0">
					${lz:set("注释","****csscPerson关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscPerson",lz:indexOf(fieldName,"csscPerson")>-1)}				  		
				  		<s:if test="#request.atCsscPerson==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscPerson[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscUnitOrder || #request.defines['csscUnitOrder']!=null">
				<s:if test="#request.defines['csscUnitOrder']>0">
					${lz:set("注释","****csscUnitOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscUnitOrder",lz:indexOf(fieldName,"csscUnitOrder")>-1)}				  		
				  		<s:if test="#request.atCsscUnitOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscUnitOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscName || #request.defines['csscName']!=null">
				<s:if test="#request.defines['csscName']>0">
					${lz:set("注释","****csscName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscName",lz:indexOf(fieldName,"csscName")>-1)}				  		
				  		<s:if test="#request.atCsscName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscMobile || #request.defines['csscMobile']!=null">
				<s:if test="#request.defines['csscMobile']>0">
					${lz:set("注释","****csscMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscMobile",lz:indexOf(fieldName,"csscMobile")>-1)}				  		
				  		<s:if test="#request.atCsscMobile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscMobile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscCount || #request.defines['csscCount']!=null">
				<s:if test="#request.defines['csscCount']>0">
					${lz:set("注释","****csscCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscCount",lz:indexOf(fieldName,"csscCount")>-1)}				  		
				  		<s:if test="#request.atCsscCount==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscCount[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscCarModel || #request.defines['csscCarModel']!=null">
				<s:if test="#request.defines['csscCarModel']>0">
					${lz:set("注释","****csscCarModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscCarModel",lz:indexOf(fieldName,"csscCarModel")>-1)}				  		
				  		<s:if test="#request.atCsscCarModel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscCarModel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscCarNumber || #request.defines['csscCarNumber']!=null">
				<s:if test="#request.defines['csscCarNumber']>0">
					${lz:set("注释","****csscCarNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscCarNumber",lz:indexOf(fieldName,"csscCarNumber")>-1)}				  		
				  		<s:if test="#request.atCsscCarNumber==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscCarNumber[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscDriverName || #request.defines['csscDriverName']!=null">
				<s:if test="#request.defines['csscDriverName']>0">
					${lz:set("注释","****csscDriverName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscDriverName",lz:indexOf(fieldName,"csscDriverName")>-1)}				  		
				  		<s:if test="#request.atCsscDriverName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscDriverName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscDriverPhone || #request.defines['csscDriverPhone']!=null">
				<s:if test="#request.defines['csscDriverPhone']>0">
					${lz:set("注释","****csscDriverPhone关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscDriverPhone",lz:indexOf(fieldName,"csscDriverPhone")>-1)}				  		
				  		<s:if test="#request.atCsscDriverPhone==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscDriverPhone[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscDriverImage || #request.defines['csscDriverImage']!=null">
				<s:if test="#request.defines['csscDriverImage']>0">
					${lz:set("注释","****csscDriverImage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscDriverImage",lz:indexOf(fieldName,"csscDriverImage")>-1)}				  		
				  		<s:if test="#request.atCsscDriverImage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscDriverImage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscDriverColor || #request.defines['csscDriverColor']!=null">
				<s:if test="#request.defines['csscDriverColor']>0">
					${lz:set("注释","****csscDriverColor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscDriverColor",lz:indexOf(fieldName,"csscDriverColor")>-1)}				  		
				  		<s:if test="#request.atCsscDriverColor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscDriverColor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscDriverCarInfo || #request.defines['csscDriverCarInfo']!=null">
				<s:if test="#request.defines['csscDriverCarInfo']>0">
					${lz:set("注释","****csscDriverCarInfo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscDriverCarInfo",lz:indexOf(fieldName,"csscDriverCarInfo")>-1)}				  		
				  		<s:if test="#request.atCsscDriverCarInfo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscDriverCarInfo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscMsg || #request.defines['csscMsg']!=null">
				<s:if test="#request.defines['csscMsg']>0">
					${lz:set("注释","****csscMsg关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscMsg",lz:indexOf(fieldName,"csscMsg")>-1)}				  		
				  		<s:if test="#request.atCsscMsg==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscMsg[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscStartPoint || #request.defines['csscStartPoint']!=null">
				<s:if test="#request.defines['csscStartPoint']>0">
					${lz:set("注释","****csscStartPoint关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscStartPoint",lz:indexOf(fieldName,"csscStartPoint")>-1)}				  		
				  		<s:if test="#request.atCsscStartPoint==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscStartPoint[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscStartAddr || #request.defines['csscStartAddr']!=null">
				<s:if test="#request.defines['csscStartAddr']>0">
					${lz:set("注释","****csscStartAddr关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscStartAddr",lz:indexOf(fieldName,"csscStartAddr")>-1)}				  		
				  		<s:if test="#request.atCsscStartAddr==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscStartAddr[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscStartTime || #request.defines['csscStartTime']!=null">
				<s:if test="#request.defines['csscStartTime']>0">
					${lz:set("注释","****csscStartTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscStartTime",lz:indexOf(fieldName,"csscStartTime")>-1)}				  		
				  		<s:if test="#request.atCsscStartTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscStartTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscFinishPoint || #request.defines['csscFinishPoint']!=null">
				<s:if test="#request.defines['csscFinishPoint']>0">
					${lz:set("注释","****csscFinishPoint关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscFinishPoint",lz:indexOf(fieldName,"csscFinishPoint")>-1)}				  		
				  		<s:if test="#request.atCsscFinishPoint==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscFinishPoint[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscFinishAddr || #request.defines['csscFinishAddr']!=null">
				<s:if test="#request.defines['csscFinishAddr']>0">
					${lz:set("注释","****csscFinishAddr关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscFinishAddr",lz:indexOf(fieldName,"csscFinishAddr")>-1)}				  		
				  		<s:if test="#request.atCsscFinishAddr==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscFinishAddr[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscFinishTime || #request.defines['csscFinishTime']!=null">
				<s:if test="#request.defines['csscFinishTime']>0">
					${lz:set("注释","****csscFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscFinishTime",lz:indexOf(fieldName,"csscFinishTime")>-1)}				  		
				  		<s:if test="#request.atCsscFinishTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscFinishTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscDuration || #request.defines['csscDuration']!=null">
				<s:if test="#request.defines['csscDuration']>0">
					${lz:set("注释","****csscDuration关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscDuration",lz:indexOf(fieldName,"csscDuration")>-1)}				  		
				  		<s:if test="#request.atCsscDuration==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscDuration[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscRealDur || #request.defines['csscRealDur']!=null">
				<s:if test="#request.defines['csscRealDur']>0">
					${lz:set("注释","****csscRealDur关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscRealDur",lz:indexOf(fieldName,"csscRealDur")>-1)}				  		
				  		<s:if test="#request.atCsscRealDur==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscRealDur[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscKilo || #request.defines['csscKilo']!=null">
				<s:if test="#request.defines['csscKilo']>0">
					${lz:set("注释","****csscKilo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscKilo",lz:indexOf(fieldName,"csscKilo")>-1)}				  		
				  		<s:if test="#request.atCsscKilo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscKilo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscScope || #request.defines['csscScope']!=null">
				<s:if test="#request.defines['csscScope']>0">
					${lz:set("注释","****csscScope关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscScope",lz:indexOf(fieldName,"csscScope")>-1)}				  		
				  		<s:if test="#request.atCsscScope==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscScope[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscAssess || #request.defines['csscAssess']!=null">
				<s:if test="#request.defines['csscAssess']>0">
					${lz:set("注释","****csscAssess关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscAssess",lz:indexOf(fieldName,"csscAssess")>-1)}				  		
				  		<s:if test="#request.atCsscAssess==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscAssess[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscThird || #request.defines['csscThird']!=null">
				<s:if test="#request.defines['csscThird']>0">
					${lz:set("注释","****csscThird关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscThird",lz:indexOf(fieldName,"csscThird")>-1)}				  		
				  		<s:if test="#request.atCsscThird==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscThird[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscThirdNo || #request.defines['csscThirdNo']!=null">
				<s:if test="#request.defines['csscThirdNo']>0">
					${lz:set("注释","****csscThirdNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscThirdNo",lz:indexOf(fieldName,"csscThirdNo")>-1)}				  		
				  		<s:if test="#request.atCsscThirdNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscThirdNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscThirdFee || #request.defines['csscThirdFee']!=null">
				<s:if test="#request.defines['csscThirdFee']>0">
					${lz:set("注释","****csscThirdFee关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscThirdFee",lz:indexOf(fieldName,"csscThirdFee")>-1)}				  		
				  		<s:if test="#request.atCsscThirdFee==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscThirdFee[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscThirdOtherFee || #request.defines['csscThirdOtherFee']!=null">
				<s:if test="#request.defines['csscThirdOtherFee']>0">
					${lz:set("注释","****csscThirdOtherFee关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscThirdOtherFee",lz:indexOf(fieldName,"csscThirdOtherFee")>-1)}				  		
				  		<s:if test="#request.atCsscThirdOtherFee==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscThirdOtherFee[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscThirdOtherFeeDes || #request.defines['csscThirdOtherFeeDes']!=null">
				<s:if test="#request.defines['csscThirdOtherFeeDes']>0">
					${lz:set("注释","****csscThirdOtherFeeDes关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscThirdOtherFeeDes",lz:indexOf(fieldName,"csscThirdOtherFeeDes")>-1)}				  		
				  		<s:if test="#request.atCsscThirdOtherFeeDes==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscThirdOtherFeeDes[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscUpdateTime || #request.defines['csscUpdateTime']!=null">
				<s:if test="#request.defines['csscUpdateTime']>0">
					${lz:set("注释","****csscUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscUpdateTime",lz:indexOf(fieldName,"csscUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsscUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscAddTime || #request.defines['csscAddTime']!=null">
				<s:if test="#request.defines['csscAddTime']>0">
					${lz:set("注释","****csscAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscAddTime",lz:indexOf(fieldName,"csscAddTime")>-1)}				  		
				  		<s:if test="#request.atCsscAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscResponse || #request.defines['csscResponse']!=null">
				<s:if test="#request.defines['csscResponse']>0">
					${lz:set("注释","****csscResponse关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscResponse",lz:indexOf(fieldName,"csscResponse")>-1)}				  		
				  		<s:if test="#request.atCsscResponse==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscResponse[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csscStatus || #request.defines['csscStatus']!=null">
				<s:if test="#request.defines['csscStatus']>0">
					${lz:set("注释","****csscStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsscStatus",lz:indexOf(fieldName,"csscStatus")>-1)}				  		
				  		<s:if test="#request.atCsscStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csscStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加专车订单
	**/
	function AddSpecialcar(parent){		
		var url = "${basePath}${namespace}specialcar_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加专车订单'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择专车订单
	**/
	function SelectSpecialcar(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改专车订单
	**/
	function EditSpecialcar(id){
		var url = "${basePath}${namespace}specialcar_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改专车订单'}":lz:json(ctrl)}};
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
		EditSpecialcar(checker.val());
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
				var url = "${basePath}${namespace}specialcar_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新专车订单",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}specialcar_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新专车订单",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改专车订单
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}specialcar_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新专车订单",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["csscStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改专车订单任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}specialcar_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新专车订单",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}specialcar_edit.do";
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
			var url = "${basePath}${namespace}specialcar_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除专车订单
	**/
	function DelSpecialcar(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除专车订单["+flag+"]吗？</span>",
				title:"删除专车订单",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}specialcar_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选专车订单吗？</span>",
				title:"批量删除专车订单",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}specialcar_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示专车订单详情
	**/
	function DetailsSpecialcar(id)
	{
		var url = "${basePath}${namespace}specialcar_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"专车订单详情",editable:false,visible:true}};
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
		DetailsSpecialcar(checker.val());
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