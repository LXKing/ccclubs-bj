<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/repair.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csRepair.csrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csrId,csrHost,csrProfile,csrCar,csrAdr,csrOrder,csrSender,csrRepairNo,csrIsSettle,csrSettleTime,csrEditor,csrAddTime,csrMemo,csrStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csrId,csrHost,csrProfile,csrCar,csrAdr,csrOrder,csrSender,csrInDate,csrOutDate,csrDescript,csrRepairNo,csrMoney,csrIsSettle,csrSettleTime,csrEditor,csrAddTime,csrMemo,csrStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csrId,csrHost,csrProfile,csrCar,csrAdr,csrOrder,csrSender,csrIsSettle,csrSettleTime,csrMemo,csrStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csrId,csrHost,csrProfile,csrCar,csrAdr,csrOrder,csrSender,csrDescript,csrMoney,csrIsSettle,csrSettleTime,csrMemo,csrStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>车辆维修管理</title>
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


<!--LAZY3Q_JSP_BODY-->
<script>
$(function(){
	$(".table tbody td[ref='csrStatus']:contains('正在维修')").css("color","#ff0000");
	$(".table tbody td[ref='csrStatus']:contains('维修完成')").css("color","#00ff00");
});
</script>
<!--LAZY3Q_JSP_BODY-->


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
	<span class="caption">${empty title?"车辆维修管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsRepair" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="repair.query.jsp"%>

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
			<button type="button" class="add" onclick="AddRepair();">
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
			<dl ref="com.ccclubs.model.CsRepair" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsRepair" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.csrIsSettle || #request.defines['csrIsSettle']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('settle')">已结算？</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csrSettleTime || #request.defines['csrSettleTime']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('settle')">结算日期</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csrMemo || #request.defines['csrMemo']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('remark')">备注信息</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csrStatus || #request.defines['csrStatus']!=null">
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
		<table id="repair_table" ref="CsRepair" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrId || #request.defines['csrId']!=null">
			 <td <s:if test="#request.defines['csrId']>0">colspan="${defines["csrId"]}" ${all?"width":"iwidth"}="${defines["csrId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csrId"   title="编号">
			 	<a class="${desc=="csr_id" ? "desc" : ""}${asc=="csr_id" ? "asc" : ""}" href="?${desc=="csr_id" ? "asc=csr_id" : ""}${(asc=="csr_id" || desc!="csr_id" )? "desc=csr_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csrId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrHost || #request.defines['csrHost']!=null">
			 <td <s:if test="#request.defines['csrHost']>0">colspan="${defines["csrHost"]}" ${all?"width":"iwidth"}="${defines["csrHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csrHost"   title="城市">
			 	<a class="${desc=="csr_host" ? "desc" : ""}${asc=="csr_host" ? "asc" : ""}" href="?${desc=="csr_host" ? "asc=csr_host" : ""}${(asc=="csr_host" || desc!="csr_host" )? "desc=csr_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csrHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrProfile || #request.defines['csrProfile']!=null">
			 <td <s:if test="#request.defines['csrProfile']>0">colspan="${defines["csrProfile"]}" ${all?"width":"iwidth"}="${defines["csrProfile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="csrProfile" flagKey  title="报修描述">
			 	<a class="${desc=="csr_profile" ? "desc" : ""}${asc=="csr_profile" ? "asc" : ""}" href="?${desc=="csr_profile" ? "asc=csr_profile" : ""}${(asc=="csr_profile" || desc!="csr_profile" )? "desc=csr_profile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	报修描述
			 	</a>
			 	${lz:set("checkeds[]","csrProfile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrCar || #request.defines['csrCar']!=null">
			 <td <s:if test="#request.defines['csrCar']>0">colspan="${defines["csrCar"]}" ${all?"width":"iwidth"}="${defines["csrCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csrCar"   title="维修车辆">
			 	<a class="${desc=="csr_car" ? "desc" : ""}${asc=="csr_car" ? "asc" : ""}" href="?${desc=="csr_car" ? "asc=csr_car" : ""}${(asc=="csr_car" || desc!="csr_car" )? "desc=csr_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	维修车辆
			 	</a>
			 	${lz:set("checkeds[]","csrCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrAdr || #request.defines['csrAdr']!=null">
			 <td <s:if test="#request.defines['csrAdr']>0">colspan="${defines["csrAdr"]}" ${all?"width":"iwidth"}="${defines["csrAdr"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csrAdr"   title="送修地点">
			 	<a class="${desc=="csr_adr" ? "desc" : ""}${asc=="csr_adr" ? "asc" : ""}" href="?${desc=="csr_adr" ? "asc=csr_adr" : ""}${(asc=="csr_adr" || desc!="csr_adr" )? "desc=csr_adr" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	送修地点
			 	</a>
			 	${lz:set("checkeds[]","csrAdr")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrOrder || #request.defines['csrOrder']!=null">
			 <td <s:if test="#request.defines['csrOrder']>0">colspan="${defines["csrOrder"]}" ${all?"width":"iwidth"}="${defines["csrOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csrOrder"   title="事故订单">
			 	<a class="${desc=="csr_order" ? "desc" : ""}${asc=="csr_order" ? "asc" : ""}" href="?${desc=="csr_order" ? "asc=csr_order" : ""}${(asc=="csr_order" || desc!="csr_order" )? "desc=csr_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	事故订单
			 	</a>
			 	${lz:set("checkeds[]","csrOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrSender || #request.defines['csrSender']!=null">
			 <td <s:if test="#request.defines['csrSender']>0">colspan="${defines["csrSender"]}" ${all?"width":"iwidth"}="${defines["csrSender"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csrSender"   title="送修人">
			 	<a class="${desc=="csr_sender" ? "desc" : ""}${asc=="csr_sender" ? "asc" : ""}" href="?${desc=="csr_sender" ? "asc=csr_sender" : ""}${(asc=="csr_sender" || desc!="csr_sender" )? "desc=csr_sender" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	送修人
			 	</a>
			 	${lz:set("checkeds[]","csrSender")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrInDate || #request.defines['csrInDate']!=null">
			 <td <s:if test="#request.defines['csrInDate']>0">colspan="${defines["csrInDate"]}" ${all?"width":"iwidth"}="${defines["csrInDate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="8" ref="csrInDate"   title="进厂日期">
			 	<a class="${desc=="csr_in_date" ? "desc" : ""}${asc=="csr_in_date" ? "asc" : ""}" href="?${desc=="csr_in_date" ? "asc=csr_in_date" : ""}${(asc=="csr_in_date" || desc!="csr_in_date" )? "desc=csr_in_date" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	进厂日期
			 	</a>
			 	${lz:set("checkeds[]","csrInDate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrOutDate || #request.defines['csrOutDate']!=null">
			 <td <s:if test="#request.defines['csrOutDate']>0">colspan="${defines["csrOutDate"]}" ${all?"width":"iwidth"}="${defines["csrOutDate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="9" ref="csrOutDate"   title="出厂日期">
			 	<a class="${desc=="csr_out_date" ? "desc" : ""}${asc=="csr_out_date" ? "asc" : ""}" href="?${desc=="csr_out_date" ? "asc=csr_out_date" : ""}${(asc=="csr_out_date" || desc!="csr_out_date" )? "desc=csr_out_date" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	出厂日期
			 	</a>
			 	${lz:set("checkeds[]","csrOutDate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrDescript || #request.defines['csrDescript']!=null">
			 <td <s:if test="#request.defines['csrDescript']>0">colspan="${defines["csrDescript"]}" ${all?"width":"iwidth"}="${defines["csrDescript"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="10" ref="csrDescript"   title="维修描述">
			 	<a class="${desc=="csr_descript" ? "desc" : ""}${asc=="csr_descript" ? "asc" : ""}" href="?${desc=="csr_descript" ? "asc=csr_descript" : ""}${(asc=="csr_descript" || desc!="csr_descript" )? "desc=csr_descript" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	维修描述
			 	</a>
			 	${lz:set("checkeds[]","csrDescript")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrRepairNo || #request.defines['csrRepairNo']!=null">
			 <td <s:if test="#request.defines['csrRepairNo']>0">colspan="${defines["csrRepairNo"]}" ${all?"width":"iwidth"}="${defines["csrRepairNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="csrRepairNo"   title="维修单号">
			 	<a class="${desc=="csr_repair_no" ? "desc" : ""}${asc=="csr_repair_no" ? "asc" : ""}" href="?${desc=="csr_repair_no" ? "asc=csr_repair_no" : ""}${(asc=="csr_repair_no" || desc!="csr_repair_no" )? "desc=csr_repair_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	维修单号
			 	</a>
			 	${lz:set("checkeds[]","csrRepairNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrMoney || #request.defines['csrMoney']!=null">
			 <td <s:if test="#request.defines['csrMoney']>0">colspan="${defines["csrMoney"]}" ${all?"width":"iwidth"}="${defines["csrMoney"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="csrMoney"   title="维修费用">
			 	<a class="${desc=="csr_money" ? "desc" : ""}${asc=="csr_money" ? "asc" : ""}" href="?${desc=="csr_money" ? "asc=csr_money" : ""}${(asc=="csr_money" || desc!="csr_money" )? "desc=csr_money" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	维修费用
			 	</a>
			 	${lz:set("checkeds[]","csrMoney")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrIsSettle || #request.defines['csrIsSettle']!=null">
			 <td <s:if test="#request.defines['csrIsSettle']>0">colspan="${defines["csrIsSettle"]}" ${all?"width":"iwidth"}="${defines["csrIsSettle"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="csrIsSettle"   title="已结算？">
			 	<a class="${desc=="csr_is_settle" ? "desc" : ""}${asc=="csr_is_settle" ? "asc" : ""}" href="?${desc=="csr_is_settle" ? "asc=csr_is_settle" : ""}${(asc=="csr_is_settle" || desc!="csr_is_settle" )? "desc=csr_is_settle" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	已结算？
			 	</a>
			 	${lz:set("checkeds[]","csrIsSettle")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrSettleTime || #request.defines['csrSettleTime']!=null">
			 <td <s:if test="#request.defines['csrSettleTime']>0">colspan="${defines["csrSettleTime"]}" ${all?"width":"iwidth"}="${defines["csrSettleTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="14" ref="csrSettleTime"   title="结算日期">
			 	<a class="${desc=="csr_settle_time" ? "desc" : ""}${asc=="csr_settle_time" ? "asc" : ""}" href="?${desc=="csr_settle_time" ? "asc=csr_settle_time" : ""}${(asc=="csr_settle_time" || desc!="csr_settle_time" )? "desc=csr_settle_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结算日期
			 	</a>
			 	${lz:set("checkeds[]","csrSettleTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrEditor || #request.defines['csrEditor']!=null">
			 <td <s:if test="#request.defines['csrEditor']>0">colspan="${defines["csrEditor"]}" ${all?"width":"iwidth"}="${defines["csrEditor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="csrEditor"   title="操作人">
			 	<a class="${desc=="csr_editor" ? "desc" : ""}${asc=="csr_editor" ? "asc" : ""}" href="?${desc=="csr_editor" ? "asc=csr_editor" : ""}${(asc=="csr_editor" || desc!="csr_editor" )? "desc=csr_editor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	操作人
			 	</a>
			 	${lz:set("checkeds[]","csrEditor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrAddTime || #request.defines['csrAddTime']!=null">
			 <td <s:if test="#request.defines['csrAddTime']>0">colspan="${defines["csrAddTime"]}" ${all?"width":"iwidth"}="${defines["csrAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="16" ref="csrAddTime"   title="添加时间">
			 	<a class="${desc=="csr_add_time" ? "desc" : ""}${asc=="csr_add_time" ? "asc" : ""}" href="?${desc=="csr_add_time" ? "asc=csr_add_time" : ""}${(asc=="csr_add_time" || desc!="csr_add_time" )? "desc=csr_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csrAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrMemo || #request.defines['csrMemo']!=null">
			 <td <s:if test="#request.defines['csrMemo']>0">colspan="${defines["csrMemo"]}" ${all?"width":"iwidth"}="${defines["csrMemo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="17" ref="csrMemo"   title="备注信息">
			 	<a class="${desc=="csr_memo" ? "desc" : ""}${asc=="csr_memo" ? "asc" : ""}" href="?${desc=="csr_memo" ? "asc=csr_memo" : ""}${(asc=="csr_memo" || desc!="csr_memo" )? "desc=csr_memo" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注信息
			 	</a>
			 	${lz:set("checkeds[]","csrMemo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrStatus || #request.defines['csrStatus']!=null">
			 <td <s:if test="#request.defines['csrStatus']>0">colspan="${defines["csrStatus"]}" ${all?"width":"iwidth"}="${defines["csrStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="18" ref="csrStatus"   title="状态">
			 	<a class="${desc=="csr_status" ? "desc" : ""}${asc=="csr_status" ? "asc" : ""}" href="?${desc=="csr_status" ? "asc=csr_status" : ""}${(asc=="csr_status" || desc!="csr_status" )? "desc=csr_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csrStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="19" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="20" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddRepair()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.csrId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csrId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrId || #request.defines['csrId']!=null">
				<s:if test="#request.defines['csrId']>0">
					${lz:set("注释","****csrId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrId",lz:indexOf(fieldName,"csrId")>-1)}
				  		<s:if test="#request.atCsrId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrId字段的字串格式化输出****")}
					<td ref="csrId" class="td ">
						 
						 	${lz:or(item$csrId[i.count-1],lz:left(item.csrId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrHost || #request.defines['csrHost']!=null">
				<s:if test="#request.defines['csrHost']>0">
					${lz:set("注释","****csrHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrHost",lz:indexOf(fieldName,"csrHost")>-1)}
				  		<s:if test="#request.atCsrHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrHost字段的字串格式化输出****")}
					<td ref="csrHost" class="td " relate="${item.csrHost}">
						 
						 	<a <s:if test="#item.csrHost!=null && #item.csrHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csrHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csrHost[i.count-1],lz:left(item.csrHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrProfile || #request.defines['csrProfile']!=null">
				<s:if test="#request.defines['csrProfile']>0">
					${lz:set("注释","****csrProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrProfile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrProfile",lz:indexOf(fieldName,"csrProfile")>-1)}
				  		<s:if test="#request.atCsrProfile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrProfile字段的字串格式化输出****")}
					<td ref="csrProfile" class="td  node">
						 
						 	${lz:or(item$csrProfile[i.count-1],lz:left(item.csrProfile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrCar || #request.defines['csrCar']!=null">
				<s:if test="#request.defines['csrCar']>0">
					${lz:set("注释","****csrCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrCar",lz:indexOf(fieldName,"csrCar")>-1)}
				  		<s:if test="#request.atCsrCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrCar字段的字串格式化输出****")}
					<td ref="csrCar" class="td " relate="${item.csrCar}">
						 
						 	<a <s:if test="#item.csrCar!=null && #item.csrCar!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.csrCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csrCar[i.count-1],lz:left(item.csrCar$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrAdr || #request.defines['csrAdr']!=null">
				<s:if test="#request.defines['csrAdr']>0">
					${lz:set("注释","****csrAdr关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrAdr))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrAdr",lz:indexOf(fieldName,"csrAdr")>-1)}
				  		<s:if test="#request.atCsrAdr==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrAdr字段的字串格式化输出****")}
					<td ref="csrAdr" class="td " relate="${item.csrAdr}">
						 
						 	<a <s:if test="#item.csrAdr!=null && #item.csrAdr!=''"> onclick="window.href('${basePath}${proname}/service/repairadr_details.do?key=${item.csrAdr}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csrAdr[i.count-1],lz:left(item.csrAdr$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrOrder || #request.defines['csrOrder']!=null">
				<s:if test="#request.defines['csrOrder']>0">
					${lz:set("注释","****csrOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrOrder",lz:indexOf(fieldName,"csrOrder")>-1)}
				  		<s:if test="#request.atCsrOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrOrder字段的字串格式化输出****")}
					<td ref="csrOrder" class="td " relate="${item.csrOrder}">
						 
						 	<a <s:if test="#item.csrOrder!=null && #item.csrOrder!=''"> onclick="window.href('${basePath}${proname}/service/order_details.do?key=${item.csrOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csrOrder[i.count-1],lz:left(item.csrOrder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrSender || #request.defines['csrSender']!=null">
				<s:if test="#request.defines['csrSender']>0">
					${lz:set("注释","****csrSender关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrSender))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrSender",lz:indexOf(fieldName,"csrSender")>-1)}
				  		<s:if test="#request.atCsrSender==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrSender字段的字串格式化输出****")}
					<td ref="csrSender" class="td " relate="${item.csrSender}">
						 
						 	<a <s:if test="#item.csrSender!=null && #item.csrSender!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csrSender}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csrSender[i.count-1],lz:left(item.csrSender$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrInDate || #request.defines['csrInDate']!=null">
				<s:if test="#request.defines['csrInDate']>0">
					${lz:set("注释","****csrInDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrInDate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrInDate",lz:indexOf(fieldName,"csrInDate")>-1)}
				  		<s:if test="#request.atCsrInDate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrInDate字段的字串格式化输出****")}
					<td ref="csrInDate" class="td ">
						 
						 	${lz:or(item$csrInDate[i.count-1],lz:left(item.csrInDate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrOutDate || #request.defines['csrOutDate']!=null">
				<s:if test="#request.defines['csrOutDate']>0">
					${lz:set("注释","****csrOutDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrOutDate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrOutDate",lz:indexOf(fieldName,"csrOutDate")>-1)}
				  		<s:if test="#request.atCsrOutDate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrOutDate字段的字串格式化输出****")}
					<td ref="csrOutDate" class="td ">
						 
						 	${lz:or(item$csrOutDate[i.count-1],lz:left(item.csrOutDate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrDescript || #request.defines['csrDescript']!=null">
				<s:if test="#request.defines['csrDescript']>0">
					${lz:set("注释","****csrDescript关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrDescript))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrDescript",lz:indexOf(fieldName,"csrDescript")>-1)}
				  		<s:if test="#request.atCsrDescript==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrDescript字段的字串格式化输出****")}
					<td ref="csrDescript" class="td ">
						 
						 	${lz:or(item$csrDescript[i.count-1],lz:left(item.csrDescript$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrRepairNo || #request.defines['csrRepairNo']!=null">
				<s:if test="#request.defines['csrRepairNo']>0">
					${lz:set("注释","****csrRepairNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrRepairNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrRepairNo",lz:indexOf(fieldName,"csrRepairNo")>-1)}
				  		<s:if test="#request.atCsrRepairNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrRepairNo字段的字串格式化输出****")}
					<td ref="csrRepairNo" class="td ">
						 
						 	${lz:or(item$csrRepairNo[i.count-1],lz:left(item.csrRepairNo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrMoney || #request.defines['csrMoney']!=null">
				<s:if test="#request.defines['csrMoney']>0">
					${lz:set("注释","****csrMoney关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrMoney))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrMoney",lz:indexOf(fieldName,"csrMoney")>-1)}
				  		<s:if test="#request.atCsrMoney==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrMoney字段的字串格式化输出****")}
					<td ref="csrMoney" class="td ">
						 
						 	${lz:or(item$csrMoney[i.count-1],lz:left(item.csrMoney$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrIsSettle || #request.defines['csrIsSettle']!=null">
				<s:if test="#request.defines['csrIsSettle']>0">
					${lz:set("注释","****csrIsSettle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrIsSettle))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrIsSettle",lz:indexOf(fieldName,"csrIsSettle")>-1)}
				  		<s:if test="#request.atCsrIsSettle==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrIsSettle字段的字串格式化输出****")}
					<td ref="csrIsSettle" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csrId}','settle')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csrIsSettle[i.count-1],lz:left(item.csrIsSettle$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrSettleTime || #request.defines['csrSettleTime']!=null">
				<s:if test="#request.defines['csrSettleTime']>0">
					${lz:set("注释","****csrSettleTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrSettleTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrSettleTime",lz:indexOf(fieldName,"csrSettleTime")>-1)}
				  		<s:if test="#request.atCsrSettleTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrSettleTime字段的字串格式化输出****")}
					<td ref="csrSettleTime" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csrId}','settle')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csrSettleTime[i.count-1],lz:left(item.csrSettleTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrEditor || #request.defines['csrEditor']!=null">
				<s:if test="#request.defines['csrEditor']>0">
					${lz:set("注释","****csrEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrEditor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrEditor",lz:indexOf(fieldName,"csrEditor")>-1)}
				  		<s:if test="#request.atCsrEditor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrEditor字段的字串格式化输出****")}
					<td ref="csrEditor" class="td " relate="${item.csrEditor}">
						 
						 	<a <s:if test="#item.csrEditor!=null && #item.csrEditor!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csrEditor}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csrEditor[i.count-1],lz:left(item.csrEditor$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrAddTime || #request.defines['csrAddTime']!=null">
				<s:if test="#request.defines['csrAddTime']>0">
					${lz:set("注释","****csrAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrAddTime",lz:indexOf(fieldName,"csrAddTime")>-1)}
				  		<s:if test="#request.atCsrAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrAddTime字段的字串格式化输出****")}
					<td ref="csrAddTime" class="td ">
						 
						 	${lz:or(item$csrAddTime[i.count-1],lz:left(item.csrAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrMemo || #request.defines['csrMemo']!=null">
				<s:if test="#request.defines['csrMemo']>0">
					${lz:set("注释","****csrMemo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrMemo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrMemo",lz:indexOf(fieldName,"csrMemo")>-1)}
				  		<s:if test="#request.atCsrMemo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrMemo字段的字串格式化输出****")}
					<td ref="csrMemo" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csrId}','remark')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csrMemo[i.count-1],lz:left(item.csrMemo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrStatus || #request.defines['csrStatus']!=null">
				<s:if test="#request.defines['csrStatus']>0">
					${lz:set("注释","****csrStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrStatus",lz:indexOf(fieldName,"csrStatus")>-1)}
				  		<s:if test="#request.atCsrStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrStatus字段的字串格式化输出****")}
					<td ref="csrStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csrId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csrStatus[i.count-1],lz:left(item.csrStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.csrId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectRepair('${item.csrId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditRepair('${item.csrId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelRepair('${item.csrId}','${item.csrProfile}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsRepair('${item.csrId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csrId || #request.defines['csrId']!=null">
				<s:if test="#request.defines['csrId']>0">
					${lz:set("注释","****csrId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrId",lz:indexOf(fieldName,"csrId")>-1)}				  		
				  		<s:if test="#request.atCsrId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrHost || #request.defines['csrHost']!=null">
				<s:if test="#request.defines['csrHost']>0">
					${lz:set("注释","****csrHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrHost",lz:indexOf(fieldName,"csrHost")>-1)}				  		
				  		<s:if test="#request.atCsrHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrProfile || #request.defines['csrProfile']!=null">
				<s:if test="#request.defines['csrProfile']>0">
					${lz:set("注释","****csrProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrProfile",lz:indexOf(fieldName,"csrProfile")>-1)}				  		
				  		<s:if test="#request.atCsrProfile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrProfile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrCar || #request.defines['csrCar']!=null">
				<s:if test="#request.defines['csrCar']>0">
					${lz:set("注释","****csrCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrCar",lz:indexOf(fieldName,"csrCar")>-1)}				  		
				  		<s:if test="#request.atCsrCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrAdr || #request.defines['csrAdr']!=null">
				<s:if test="#request.defines['csrAdr']>0">
					${lz:set("注释","****csrAdr关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrAdr",lz:indexOf(fieldName,"csrAdr")>-1)}				  		
				  		<s:if test="#request.atCsrAdr==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrAdr[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrOrder || #request.defines['csrOrder']!=null">
				<s:if test="#request.defines['csrOrder']>0">
					${lz:set("注释","****csrOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrOrder",lz:indexOf(fieldName,"csrOrder")>-1)}				  		
				  		<s:if test="#request.atCsrOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrSender || #request.defines['csrSender']!=null">
				<s:if test="#request.defines['csrSender']>0">
					${lz:set("注释","****csrSender关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrSender",lz:indexOf(fieldName,"csrSender")>-1)}				  		
				  		<s:if test="#request.atCsrSender==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrSender[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrInDate || #request.defines['csrInDate']!=null">
				<s:if test="#request.defines['csrInDate']>0">
					${lz:set("注释","****csrInDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrInDate",lz:indexOf(fieldName,"csrInDate")>-1)}				  		
				  		<s:if test="#request.atCsrInDate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrInDate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrOutDate || #request.defines['csrOutDate']!=null">
				<s:if test="#request.defines['csrOutDate']>0">
					${lz:set("注释","****csrOutDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrOutDate",lz:indexOf(fieldName,"csrOutDate")>-1)}				  		
				  		<s:if test="#request.atCsrOutDate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrOutDate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrDescript || #request.defines['csrDescript']!=null">
				<s:if test="#request.defines['csrDescript']>0">
					${lz:set("注释","****csrDescript关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrDescript",lz:indexOf(fieldName,"csrDescript")>-1)}				  		
				  		<s:if test="#request.atCsrDescript==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrDescript[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrRepairNo || #request.defines['csrRepairNo']!=null">
				<s:if test="#request.defines['csrRepairNo']>0">
					${lz:set("注释","****csrRepairNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrRepairNo",lz:indexOf(fieldName,"csrRepairNo")>-1)}				  		
				  		<s:if test="#request.atCsrRepairNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrRepairNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrMoney || #request.defines['csrMoney']!=null">
				<s:if test="#request.defines['csrMoney']>0">
					${lz:set("注释","****csrMoney关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrMoney",lz:indexOf(fieldName,"csrMoney")>-1)}				  		
				  		<s:if test="#request.atCsrMoney==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrMoney[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrIsSettle || #request.defines['csrIsSettle']!=null">
				<s:if test="#request.defines['csrIsSettle']>0">
					${lz:set("注释","****csrIsSettle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrIsSettle",lz:indexOf(fieldName,"csrIsSettle")>-1)}				  		
				  		<s:if test="#request.atCsrIsSettle==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrIsSettle[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrSettleTime || #request.defines['csrSettleTime']!=null">
				<s:if test="#request.defines['csrSettleTime']>0">
					${lz:set("注释","****csrSettleTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrSettleTime",lz:indexOf(fieldName,"csrSettleTime")>-1)}				  		
				  		<s:if test="#request.atCsrSettleTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrSettleTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrEditor || #request.defines['csrEditor']!=null">
				<s:if test="#request.defines['csrEditor']>0">
					${lz:set("注释","****csrEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrEditor",lz:indexOf(fieldName,"csrEditor")>-1)}				  		
				  		<s:if test="#request.atCsrEditor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrEditor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrAddTime || #request.defines['csrAddTime']!=null">
				<s:if test="#request.defines['csrAddTime']>0">
					${lz:set("注释","****csrAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrAddTime",lz:indexOf(fieldName,"csrAddTime")>-1)}				  		
				  		<s:if test="#request.atCsrAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrMemo || #request.defines['csrMemo']!=null">
				<s:if test="#request.defines['csrMemo']>0">
					${lz:set("注释","****csrMemo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrMemo",lz:indexOf(fieldName,"csrMemo")>-1)}				  		
				  		<s:if test="#request.atCsrMemo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrMemo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrStatus || #request.defines['csrStatus']!=null">
				<s:if test="#request.defines['csrStatus']>0">
					${lz:set("注释","****csrStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrStatus",lz:indexOf(fieldName,"csrStatus")>-1)}				  		
				  		<s:if test="#request.atCsrStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加车辆维修
	**/
	function AddRepair(parent){		
		var url = "${basePath}${namespace}repair_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加车辆维修'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择车辆维修
	**/
	function SelectRepair(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改车辆维修
	**/
	function EditRepair(id){
		var url = "${basePath}${namespace}repair_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改车辆维修'}":lz:json(ctrl)}};
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
		EditRepair(checker.val());
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
				var url = "${basePath}${namespace}repair_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新车辆维修",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}repair_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新车辆维修",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改车辆维修
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}repair_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新车辆维修",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["settle"]={};
	fieldNames["settle"]={};
	fieldNames["remark"]={};
	fieldNames["status"]={};
	fieldNames["settle"]["csrIsSettle"]=true;
	fieldNames["settle"]["csrSettleTime"]=true;
	fieldNames["remark"]["csrMemo"]=true;
	fieldNames["status"]["csrStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改车辆维修任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}repair_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新车辆维修",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}repair_edit.do";
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
			var url = "${basePath}${namespace}repair_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除车辆维修
	**/
	function DelRepair(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除车辆维修["+flag+"]吗？</span>",
				title:"删除车辆维修",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}repair_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选车辆维修吗？</span>",
				title:"批量删除车辆维修",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}repair_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示车辆维修详情
	**/
	function DetailsRepair(id)
	{
		var url = "${basePath}${namespace}repair_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"车辆维修详情",editable:false,visible:true}};
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
		DetailsRepair(checker.val());
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