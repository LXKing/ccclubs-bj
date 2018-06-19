<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/trouble.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csTrouble.cstId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"cstId,cstHost,cstOrder,cstMember,cstCar,cstRepairAdr,cstRepair,cstRepayDate,cstRepayType,cstCompDate,cstHappenTime,cstNotice,cstDealFlag,cstAccepter,cstCaser,cstFinishTime,cstUpdateTime,cstAddTime,cstStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"cstId,cstHost,cstOrder,cstMember,cstCar,cstType,cstDamage,cstRepairAdr,cstRepairPart,cstRepair,cstInvoice,cstInsureFee,cstMoney,cstThirdLoss,cstTrailFee,cstRepayDate,cstRepayType,cstThirdDesc,cstCompensate,cstCompDate,cstRepairPay,cstAgio,cstDecipt,cstHappenTime,cstNotice,cstDealFlag,cstAccepter,cstCaser,cstFinishTime,cstRemark,cstUpdateTime,cstAddTime,cstStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"cstId,cstHost,cstOrder,cstMember,cstCar,cstNotice,cstDealFlag,cstAccepter,cstAddTime,cstStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"cstId,cstHost,cstOrder,cstMember,cstCar,cstType,cstInsureFee,cstMoney,cstThirdLoss,cstAgio,cstNotice,cstDealFlag,cstAccepter,cstAddTime,cstStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>车辆事故管理</title>
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


<!-- LAZY3Q_JSP_BODY -->
<script>
$(function(){
	$(".table tbody td[ref='cstStatus']:contains('已录入待定损')").css("color","#ff0000");
	$(".table tbody td[ref='cstStatus']:contains('已定损待协商')").css("color","#00ff00");
	$(".table tbody td[ref='cstStatus']:contains('已协商待维修')").css("color","#0000ff");
	$(".table tbody td[ref='cstStatus']:contains('正在维修')").css("color","#ffbb00");
	$(".table tbody td[ref='cstStatus']:contains('已维修待扣款')").css("color","#ff00ff");
	$(".table tbody td[ref='cstStatus']:contains('已扣款待结案')").css("color","#00ffff");
	$(".table tbody td[ref='cstStatus']:contains('已结案')").css("color","#000000");
	$(".table tbody td[ref='cstNotice']:contains('未通知')").css("color","#FF0000");
});
function getHandle(operate,id){
	if(operate=="备录")
		return record;
}
function record(operate,id){
	var url = "${basePath}admin/service/troubledetail_edit.do";
	var params = {
		entrypoint:"${entrypoint}",
		ctrl:{
			title:"添加事故明细备录",
			fields:{
				cstdId:{
					visible:false
				},
				cstdTrouble:{
					defaultValue:id,
					editable:false
				},
				cstdContent:{
					editable:true
				},
				cstdMoney:{
					editable:true
				}			
			}
		}
	};
	href(url,params);
}
</script>
<!-- LAZY3Q_JSP_BODY -->


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
	<s:if test="#request.alias==null">
		<s:if test="#request.CTRL.canStats==true">
	<a href="trouble_stats.do?${lz:querys("UTF-8","limitsid")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Statistics.png"/>
		数据统计
	</a>
		</s:if>
	</s:if>
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"车辆事故管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsTrouble" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="trouble.query.jsp"%>

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
			<button type="button" class="add" onclick="AddTrouble();">
				添加
			</button>							
			</s:if>
		  </s:if>
		  <s:if test="#request.alias==null">	
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
			<dl ref="com.ccclubs.model.CsTrouble" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsTrouble" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.cstAgio || #request.defines['cstAgio']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('agio')">合计差价</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.cstNotice || #request.defines['cstNotice']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('notice')">客服通知</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.cstDealFlag || #request.defines['cstDealFlag']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('deal')">受理标记</a>
					</s:if>
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table
				</dd>
			</dl>
			</s:if>
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="trouble_table" ref="CsTrouble" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstId || #request.defines['cstId']!=null">
			 <td <s:if test="#request.defines['cstId']>0">colspan="${defines["cstId"]}" ${all?"width":"iwidth"}="${defines["cstId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="cstId" flagKey  title="编号">
			 	<a class="${desc=="cst_id" ? "desc" : ""}${asc=="cst_id" ? "asc" : ""}" href="?${desc=="cst_id" ? "asc=cst_id" : ""}${(asc=="cst_id" || desc!="cst_id" )? "desc=cst_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","cstId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstHost || #request.defines['cstHost']!=null">
			 <td <s:if test="#request.defines['cstHost']>0">colspan="${defines["cstHost"]}" ${all?"width":"iwidth"}="${defines["cstHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="cstHost"   title="城市">
			 	<a class="${desc=="cst_host" ? "desc" : ""}${asc=="cst_host" ? "asc" : ""}" href="?${desc=="cst_host" ? "asc=cst_host" : ""}${(asc=="cst_host" || desc!="cst_host" )? "desc=cst_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","cstHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstOrder || #request.defines['cstOrder']!=null">
			 <td <s:if test="#request.defines['cstOrder']>0">colspan="${defines["cstOrder"]}" ${all?"width":"iwidth"}="${defines["cstOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="cstOrder"   title="事故订单">
			 	<a class="${desc=="cst_order" ? "desc" : ""}${asc=="cst_order" ? "asc" : ""}" href="?${desc=="cst_order" ? "asc=cst_order" : ""}${(asc=="cst_order" || desc!="cst_order" )? "desc=cst_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	事故订单
			 	</a>
			 	${lz:set("checkeds[]","cstOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstMember || #request.defines['cstMember']!=null">
			 <td <s:if test="#request.defines['cstMember']>0">colspan="${defines["cstMember"]}" ${all?"width":"iwidth"}="${defines["cstMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="cstMember"   title="事故会员">
			 	<a class="${desc=="cst_member" ? "desc" : ""}${asc=="cst_member" ? "asc" : ""}" href="?${desc=="cst_member" ? "asc=cst_member" : ""}${(asc=="cst_member" || desc!="cst_member" )? "desc=cst_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	事故会员
			 	</a>
			 	${lz:set("checkeds[]","cstMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstCar || #request.defines['cstCar']!=null">
			 <td <s:if test="#request.defines['cstCar']>0">colspan="${defines["cstCar"]}" ${all?"width":"iwidth"}="${defines["cstCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="cstCar"   title="事故车辆">
			 	<a class="${desc=="cst_car" ? "desc" : ""}${asc=="cst_car" ? "asc" : ""}" href="?${desc=="cst_car" ? "asc=cst_car" : ""}${(asc=="cst_car" || desc!="cst_car" )? "desc=cst_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	事故车辆
			 	</a>
			 	${lz:set("checkeds[]","cstCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstType || #request.defines['cstType']!=null">
			 <td <s:if test="#request.defines['cstType']>0">colspan="${defines["cstType"]}" ${all?"width":"iwidth"}="${defines["cstType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="cstType"   title="事故类型">
			 	<a class="${desc=="cst_type" ? "desc" : ""}${asc=="cst_type" ? "asc" : ""}" href="?${desc=="cst_type" ? "asc=cst_type" : ""}${(asc=="cst_type" || desc!="cst_type" )? "desc=cst_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	事故类型
			 	</a>
			 	${lz:set("checkeds[]","cstType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstDamage || #request.defines['cstDamage']!=null">
			 <td <s:if test="#request.defines['cstDamage']>0">colspan="${defines["cstDamage"]}" ${all?"width":"iwidth"}="${defines["cstDamage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="cstDamage"   title="车损情况">
			 	<a class="${desc=="cst_damage" ? "desc" : ""}${asc=="cst_damage" ? "asc" : ""}" href="?${desc=="cst_damage" ? "asc=cst_damage" : ""}${(asc=="cst_damage" || desc!="cst_damage" )? "desc=cst_damage" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车损情况
			 	</a>
			 	${lz:set("checkeds[]","cstDamage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstRepairAdr || #request.defines['cstRepairAdr']!=null">
			 <td <s:if test="#request.defines['cstRepairAdr']>0">colspan="${defines["cstRepairAdr"]}" ${all?"width":"iwidth"}="${defines["cstRepairAdr"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="cstRepairAdr"   title="送修地点">
			 	<a class="${desc=="cst_repair_adr" ? "desc" : ""}${asc=="cst_repair_adr" ? "asc" : ""}" href="?${desc=="cst_repair_adr" ? "asc=cst_repair_adr" : ""}${(asc=="cst_repair_adr" || desc!="cst_repair_adr" )? "desc=cst_repair_adr" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	送修地点
			 	</a>
			 	${lz:set("checkeds[]","cstRepairAdr")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstRepairPart || #request.defines['cstRepairPart']!=null">
			 <td <s:if test="#request.defines['cstRepairPart']>0">colspan="${defines["cstRepairPart"]}" ${all?"width":"iwidth"}="${defines["cstRepairPart"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="cstRepairPart"   title="维修部位">
			 	<a class="${desc=="cst_repair_part" ? "desc" : ""}${asc=="cst_repair_part" ? "asc" : ""}" href="?${desc=="cst_repair_part" ? "asc=cst_repair_part" : ""}${(asc=="cst_repair_part" || desc!="cst_repair_part" )? "desc=cst_repair_part" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	维修部位
			 	</a>
			 	${lz:set("checkeds[]","cstRepairPart")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstRepair || #request.defines['cstRepair']!=null">
			 <td <s:if test="#request.defines['cstRepair']>0">colspan="${defines["cstRepair"]}" ${all?"width":"iwidth"}="${defines["cstRepair"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="cstRepair"   title="维修详细信息">
			 	<a class="${desc=="cst_repair" ? "desc" : ""}${asc=="cst_repair" ? "asc" : ""}" href="?${desc=="cst_repair" ? "asc=cst_repair" : ""}${(asc=="cst_repair" || desc!="cst_repair" )? "desc=cst_repair" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	维修详细信息
			 	</a>
			 	${lz:set("checkeds[]","cstRepair")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstInvoice || #request.defines['cstInvoice']!=null">
			 <td <s:if test="#request.defines['cstInvoice']>0">colspan="${defines["cstInvoice"]}" ${all?"width":"iwidth"}="${defines["cstInvoice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="cstInvoice"   title="开票金额(元)">
			 	<a class="${desc=="cst_invoice" ? "desc" : ""}${asc=="cst_invoice" ? "asc" : ""}" href="?${desc=="cst_invoice" ? "asc=cst_invoice" : ""}${(asc=="cst_invoice" || desc!="cst_invoice" )? "desc=cst_invoice" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	开票金额(元)
			 	</a>
			 	${lz:set("checkeds[]","cstInvoice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstInsureFee || #request.defines['cstInsureFee']!=null">
			 <td <s:if test="#request.defines['cstInsureFee']>0">colspan="${defines["cstInsureFee"]}" ${all?"width":"iwidth"}="${defines["cstInsureFee"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="cstInsureFee"   title="保险定损(元)">
			 	<a class="${desc=="cst_insure_fee" ? "desc" : ""}${asc=="cst_insure_fee" ? "asc" : ""}" href="?${desc=="cst_insure_fee" ? "asc=cst_insure_fee" : ""}${(asc=="cst_insure_fee" || desc!="cst_insure_fee" )? "desc=cst_insure_fee" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	保险定损(元)
			 	</a>
			 	${lz:set("checkeds[]","cstInsureFee")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstMoney || #request.defines['cstMoney']!=null">
			 <td <s:if test="#request.defines['cstMoney']>0">colspan="${defines["cstMoney"]}" ${all?"width":"iwidth"}="${defines["cstMoney"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="cstMoney"   title="协商扣款(元)">
			 	<a class="${desc=="cst_money" ? "desc" : ""}${asc=="cst_money" ? "asc" : ""}" href="?${desc=="cst_money" ? "asc=cst_money" : ""}${(asc=="cst_money" || desc!="cst_money" )? "desc=cst_money" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	协商扣款(元)
			 	</a>
			 	${lz:set("checkeds[]","cstMoney")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstThirdLoss || #request.defines['cstThirdLoss']!=null">
			 <td <s:if test="#request.defines['cstThirdLoss']>0">colspan="${defines["cstThirdLoss"]}" ${all?"width":"iwidth"}="${defines["cstThirdLoss"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="cstThirdLoss"   title="三者损失(元)">
			 	<a class="${desc=="cst_third_loss" ? "desc" : ""}${asc=="cst_third_loss" ? "asc" : ""}" href="?${desc=="cst_third_loss" ? "asc=cst_third_loss" : ""}${(asc=="cst_third_loss" || desc!="cst_third_loss" )? "desc=cst_third_loss" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	三者损失(元)
			 	</a>
			 	${lz:set("checkeds[]","cstThirdLoss")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstTrailFee || #request.defines['cstTrailFee']!=null">
			 <td <s:if test="#request.defines['cstTrailFee']>0">colspan="${defines["cstTrailFee"]}" ${all?"width":"iwidth"}="${defines["cstTrailFee"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="cstTrailFee"   title="拖车费用">
			 	<a class="${desc=="cst_trail_fee" ? "desc" : ""}${asc=="cst_trail_fee" ? "asc" : ""}" href="?${desc=="cst_trail_fee" ? "asc=cst_trail_fee" : ""}${(asc=="cst_trail_fee" || desc!="cst_trail_fee" )? "desc=cst_trail_fee" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	拖车费用
			 	</a>
			 	${lz:set("checkeds[]","cstTrailFee")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstRepayDate || #request.defines['cstRepayDate']!=null">
			 <td <s:if test="#request.defines['cstRepayDate']>0">colspan="${defines["cstRepayDate"]}" ${all?"width":"iwidth"}="${defines["cstRepayDate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="16" ref="cstRepayDate"   title="返还垫付时间">
			 	<a class="${desc=="cst_repay_date" ? "desc" : ""}${asc=="cst_repay_date" ? "asc" : ""}" href="?${desc=="cst_repay_date" ? "asc=cst_repay_date" : ""}${(asc=="cst_repay_date" || desc!="cst_repay_date" )? "desc=cst_repay_date" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	返还垫付时间
			 	</a>
			 	${lz:set("checkeds[]","cstRepayDate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstRepayType || #request.defines['cstRepayType']!=null">
			 <td <s:if test="#request.defines['cstRepayType']>0">colspan="${defines["cstRepayType"]}" ${all?"width":"iwidth"}="${defines["cstRepayType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="17" ref="cstRepayType"   title="返还垫付方式">
			 	<a class="${desc=="cst_repay_type" ? "desc" : ""}${asc=="cst_repay_type" ? "asc" : ""}" href="?${desc=="cst_repay_type" ? "asc=cst_repay_type" : ""}${(asc=="cst_repay_type" || desc!="cst_repay_type" )? "desc=cst_repay_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	返还垫付方式
			 	</a>
			 	${lz:set("checkeds[]","cstRepayType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstThirdDesc || #request.defines['cstThirdDesc']!=null">
			 <td <s:if test="#request.defines['cstThirdDesc']>0">colspan="${defines["cstThirdDesc"]}" ${all?"width":"iwidth"}="${defines["cstThirdDesc"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="18" ref="cstThirdDesc"   title="三者垫付备注">
			 	<a class="${desc=="cst_third_desc" ? "desc" : ""}${asc=="cst_third_desc" ? "asc" : ""}" href="?${desc=="cst_third_desc" ? "asc=cst_third_desc" : ""}${(asc=="cst_third_desc" || desc!="cst_third_desc" )? "desc=cst_third_desc" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	三者垫付备注
			 	</a>
			 	${lz:set("checkeds[]","cstThirdDesc")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstCompensate || #request.defines['cstCompensate']!=null">
			 <td <s:if test="#request.defines['cstCompensate']>0">colspan="${defines["cstCompensate"]}" ${all?"width":"iwidth"}="${defines["cstCompensate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="19" ref="cstCompensate"   title="赔付到账(元)">
			 	<a class="${desc=="cst_compensate" ? "desc" : ""}${asc=="cst_compensate" ? "asc" : ""}" href="?${desc=="cst_compensate" ? "asc=cst_compensate" : ""}${(asc=="cst_compensate" || desc!="cst_compensate" )? "desc=cst_compensate" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	赔付到账(元)
			 	</a>
			 	${lz:set("checkeds[]","cstCompensate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstCompDate || #request.defines['cstCompDate']!=null">
			 <td <s:if test="#request.defines['cstCompDate']>0">colspan="${defines["cstCompDate"]}" ${all?"width":"iwidth"}="${defines["cstCompDate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="20" ref="cstCompDate"   title="赔付到账时间">
			 	<a class="${desc=="cst_comp_date" ? "desc" : ""}${asc=="cst_comp_date" ? "asc" : ""}" href="?${desc=="cst_comp_date" ? "asc=cst_comp_date" : ""}${(asc=="cst_comp_date" || desc!="cst_comp_date" )? "desc=cst_comp_date" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	赔付到账时间
			 	</a>
			 	${lz:set("checkeds[]","cstCompDate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstRepairPay || #request.defines['cstRepairPay']!=null">
			 <td <s:if test="#request.defines['cstRepairPay']>0">colspan="${defines["cstRepairPay"]}" ${all?"width":"iwidth"}="${defines["cstRepairPay"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="21" ref="cstRepairPay"   title="维修费用(元)">
			 	<a class="${desc=="cst_repair_pay" ? "desc" : ""}${asc=="cst_repair_pay" ? "asc" : ""}" href="?${desc=="cst_repair_pay" ? "asc=cst_repair_pay" : ""}${(asc=="cst_repair_pay" || desc!="cst_repair_pay" )? "desc=cst_repair_pay" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	维修费用(元)
			 	</a>
			 	${lz:set("checkeds[]","cstRepairPay")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstAgio || #request.defines['cstAgio']!=null">
			 <td <s:if test="#request.defines['cstAgio']>0">colspan="${defines["cstAgio"]}" ${all?"width":"iwidth"}="${defines["cstAgio"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="22" ref="cstAgio"   title="合计差价">
			 	<a class="${desc=="cst_agio" ? "desc" : ""}${asc=="cst_agio" ? "asc" : ""}" href="?${desc=="cst_agio" ? "asc=cst_agio" : ""}${(asc=="cst_agio" || desc!="cst_agio" )? "desc=cst_agio" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	合计差价
			 	</a>
			 	${lz:set("checkeds[]","cstAgio")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstDecipt || #request.defines['cstDecipt']!=null">
			 <td <s:if test="#request.defines['cstDecipt']>0">colspan="${defines["cstDecipt"]}" ${all?"width":"iwidth"}="${defines["cstDecipt"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="23" ref="cstDecipt"   title="事故描述">
			 	<a class="${desc=="cst_decipt" ? "desc" : ""}${asc=="cst_decipt" ? "asc" : ""}" href="?${desc=="cst_decipt" ? "asc=cst_decipt" : ""}${(asc=="cst_decipt" || desc!="cst_decipt" )? "desc=cst_decipt" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	事故描述
			 	</a>
			 	${lz:set("checkeds[]","cstDecipt")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstHappenTime || #request.defines['cstHappenTime']!=null">
			 <td <s:if test="#request.defines['cstHappenTime']>0">colspan="${defines["cstHappenTime"]}" ${all?"width":"iwidth"}="${defines["cstHappenTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="24" ref="cstHappenTime"   title="发生时间">
			 	<a class="${desc=="cst_happen_time" ? "desc" : ""}${asc=="cst_happen_time" ? "asc" : ""}" href="?${desc=="cst_happen_time" ? "asc=cst_happen_time" : ""}${(asc=="cst_happen_time" || desc!="cst_happen_time" )? "desc=cst_happen_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	发生时间
			 	</a>
			 	${lz:set("checkeds[]","cstHappenTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstNotice || #request.defines['cstNotice']!=null">
			 <td <s:if test="#request.defines['cstNotice']>0">colspan="${defines["cstNotice"]}" ${all?"width":"iwidth"}="${defines["cstNotice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="25" ref="cstNotice"   title="客服通知">
			 	<a class="${desc=="cst_notice" ? "desc" : ""}${asc=="cst_notice" ? "asc" : ""}" href="?${desc=="cst_notice" ? "asc=cst_notice" : ""}${(asc=="cst_notice" || desc!="cst_notice" )? "desc=cst_notice" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	客服通知
			 	</a>
			 	${lz:set("checkeds[]","cstNotice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstDealFlag || #request.defines['cstDealFlag']!=null">
			 <td <s:if test="#request.defines['cstDealFlag']>0">colspan="${defines["cstDealFlag"]}" ${all?"width":"iwidth"}="${defines["cstDealFlag"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="26" ref="cstDealFlag"   title="受理标记">
			 	<a class="${desc=="cst_deal_flag" ? "desc" : ""}${asc=="cst_deal_flag" ? "asc" : ""}" href="?${desc=="cst_deal_flag" ? "asc=cst_deal_flag" : ""}${(asc=="cst_deal_flag" || desc!="cst_deal_flag" )? "desc=cst_deal_flag" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	受理标记
			 	</a>
			 	${lz:set("checkeds[]","cstDealFlag")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstAccepter || #request.defines['cstAccepter']!=null">
			 <td <s:if test="#request.defines['cstAccepter']>0">colspan="${defines["cstAccepter"]}" ${all?"width":"iwidth"}="${defines["cstAccepter"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="27" ref="cstAccepter"   title="受理人员">
			 	<a class="${desc=="cst_accepter" ? "desc" : ""}${asc=="cst_accepter" ? "asc" : ""}" href="?${desc=="cst_accepter" ? "asc=cst_accepter" : ""}${(asc=="cst_accepter" || desc!="cst_accepter" )? "desc=cst_accepter" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	受理人员
			 	</a>
			 	${lz:set("checkeds[]","cstAccepter")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstCaser || #request.defines['cstCaser']!=null">
			 <td <s:if test="#request.defines['cstCaser']>0">colspan="${defines["cstCaser"]}" ${all?"width":"iwidth"}="${defines["cstCaser"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="28" ref="cstCaser"   title="结案人员">
			 	<a class="${desc=="cst_caser" ? "desc" : ""}${asc=="cst_caser" ? "asc" : ""}" href="?${desc=="cst_caser" ? "asc=cst_caser" : ""}${(asc=="cst_caser" || desc!="cst_caser" )? "desc=cst_caser" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结案人员
			 	</a>
			 	${lz:set("checkeds[]","cstCaser")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstFinishTime || #request.defines['cstFinishTime']!=null">
			 <td <s:if test="#request.defines['cstFinishTime']>0">colspan="${defines["cstFinishTime"]}" ${all?"width":"iwidth"}="${defines["cstFinishTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="29" ref="cstFinishTime"   title="结案时间">
			 	<a class="${desc=="cst_finish_time" ? "desc" : ""}${asc=="cst_finish_time" ? "asc" : ""}" href="?${desc=="cst_finish_time" ? "asc=cst_finish_time" : ""}${(asc=="cst_finish_time" || desc!="cst_finish_time" )? "desc=cst_finish_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结案时间
			 	</a>
			 	${lz:set("checkeds[]","cstFinishTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstRemark || #request.defines['cstRemark']!=null">
			 <td <s:if test="#request.defines['cstRemark']>0">colspan="${defines["cstRemark"]}" ${all?"width":"iwidth"}="${defines["cstRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="30" ref="cstRemark"   title="备注信息">
			 	<a class="${desc=="cst_remark" ? "desc" : ""}${asc=="cst_remark" ? "asc" : ""}" href="?${desc=="cst_remark" ? "asc=cst_remark" : ""}${(asc=="cst_remark" || desc!="cst_remark" )? "desc=cst_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注信息
			 	</a>
			 	${lz:set("checkeds[]","cstRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstUpdateTime || #request.defines['cstUpdateTime']!=null">
			 <td <s:if test="#request.defines['cstUpdateTime']>0">colspan="${defines["cstUpdateTime"]}" ${all?"width":"iwidth"}="${defines["cstUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="31" ref="cstUpdateTime"   title="更新时间">
			 	<a class="${desc=="cst_update_time" ? "desc" : ""}${asc=="cst_update_time" ? "asc" : ""}" href="?${desc=="cst_update_time" ? "asc=cst_update_time" : ""}${(asc=="cst_update_time" || desc!="cst_update_time" )? "desc=cst_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	更新时间
			 	</a>
			 	${lz:set("checkeds[]","cstUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstAddTime || #request.defines['cstAddTime']!=null">
			 <td <s:if test="#request.defines['cstAddTime']>0">colspan="${defines["cstAddTime"]}" ${all?"width":"iwidth"}="${defines["cstAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="32" ref="cstAddTime"   title="添加时间">
			 	<a class="${desc=="cst_add_time" ? "desc" : ""}${asc=="cst_add_time" ? "asc" : ""}" href="?${desc=="cst_add_time" ? "asc=cst_add_time" : ""}${(asc=="cst_add_time" || desc!="cst_add_time" )? "desc=cst_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","cstAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cstStatus || #request.defines['cstStatus']!=null">
			 <td <s:if test="#request.defines['cstStatus']>0">colspan="${defines["cstStatus"]}" ${all?"width":"iwidth"}="${defines["cstStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="33" ref="cstStatus"   title="处理状态">
			 	<a class="${desc=="cst_status" ? "desc" : ""}${asc=="cst_status" ? "asc" : ""}" href="?${desc=="cst_status" ? "asc=cst_status" : ""}${(asc=="cst_status" || desc!="cst_status" )? "desc=cst_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	处理状态
			 	</a>
			 	${lz:set("checkeds[]","cstStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="34" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="35" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddTrouble()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.cstId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.cstId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstId || #request.defines['cstId']!=null">
				<s:if test="#request.defines['cstId']>0">
					${lz:set("注释","****cstId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstId",lz:indexOf(fieldName,"cstId")>-1)}
				  		<s:if test="#request.atCstId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstId字段的字串格式化输出****")}
					<td ref="cstId" class="td  node">
						 
						 	${lz:or(item$cstId[i.count-1],lz:left(item.cstId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstHost || #request.defines['cstHost']!=null">
				<s:if test="#request.defines['cstHost']>0">
					${lz:set("注释","****cstHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstHost",lz:indexOf(fieldName,"cstHost")>-1)}
				  		<s:if test="#request.atCstHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstHost字段的字串格式化输出****")}
					<td ref="cstHost" class="td " relate="${item.cstHost}">
						 
						 	<a <s:if test="#item.cstHost!=null && #item.cstHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.cstHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cstHost[i.count-1],lz:left(item.cstHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstOrder || #request.defines['cstOrder']!=null">
				<s:if test="#request.defines['cstOrder']>0">
					${lz:set("注释","****cstOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstOrder",lz:indexOf(fieldName,"cstOrder")>-1)}
				  		<s:if test="#request.atCstOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstOrder字段的字串格式化输出****")}
					<td ref="cstOrder" class="td " relate="${item.cstOrder}">
						 
						 	<a <s:if test="#item.cstOrder!=null && #item.cstOrder!=''"> onclick="window.href('${basePath}${proname}/service/order_details.do?key=${item.cstOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cstOrder[i.count-1],lz:left(item.cstOrder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstMember || #request.defines['cstMember']!=null">
				<s:if test="#request.defines['cstMember']>0">
					${lz:set("注释","****cstMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstMember",lz:indexOf(fieldName,"cstMember")>-1)}
				  		<s:if test="#request.atCstMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstMember字段的字串格式化输出****")}
					<td ref="cstMember" class="td " relate="${item.cstMember}">
						 
						 	<a <s:if test="#item.cstMember!=null && #item.cstMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.cstMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cstMember[i.count-1],lz:left(item.cstMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstCar || #request.defines['cstCar']!=null">
				<s:if test="#request.defines['cstCar']>0">
					${lz:set("注释","****cstCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstCar",lz:indexOf(fieldName,"cstCar")>-1)}
				  		<s:if test="#request.atCstCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstCar字段的字串格式化输出****")}
					<td ref="cstCar" class="td " relate="${item.cstCar}">
						 
						 	<a <s:if test="#item.cstCar!=null && #item.cstCar!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.cstCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cstCar[i.count-1],lz:left(item.cstCar$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstType || #request.defines['cstType']!=null">
				<s:if test="#request.defines['cstType']>0">
					${lz:set("注释","****cstType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstType",lz:indexOf(fieldName,"cstType")>-1)}
				  		<s:if test="#request.atCstType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstType字段的字串格式化输出****")}
					<td ref="cstType" class="td ">
						 
						 	${lz:or(item$cstType[i.count-1],lz:left(item.cstType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstDamage || #request.defines['cstDamage']!=null">
				<s:if test="#request.defines['cstDamage']>0">
					${lz:set("注释","****cstDamage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstDamage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstDamage",lz:indexOf(fieldName,"cstDamage")>-1)}
				  		<s:if test="#request.atCstDamage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstDamage字段的字串格式化输出****")}
					<td ref="cstDamage" class="td ">
						 
						 	${lz:or(item$cstDamage[i.count-1],lz:left(item.cstDamage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstRepairAdr || #request.defines['cstRepairAdr']!=null">
				<s:if test="#request.defines['cstRepairAdr']>0">
					${lz:set("注释","****cstRepairAdr关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstRepairAdr))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstRepairAdr",lz:indexOf(fieldName,"cstRepairAdr")>-1)}
				  		<s:if test="#request.atCstRepairAdr==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstRepairAdr字段的字串格式化输出****")}
					<td ref="cstRepairAdr" class="td " relate="${item.cstRepairAdr}">
						 
						 	<a <s:if test="#item.cstRepairAdr!=null && #item.cstRepairAdr!=''"> onclick="window.href('${basePath}${proname}/service/repairadr_details.do?key=${item.cstRepairAdr}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cstRepairAdr[i.count-1],lz:left(item.cstRepairAdr$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstRepairPart || #request.defines['cstRepairPart']!=null">
				<s:if test="#request.defines['cstRepairPart']>0">
					${lz:set("注释","****cstRepairPart关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstRepairPart))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstRepairPart",lz:indexOf(fieldName,"cstRepairPart")>-1)}
				  		<s:if test="#request.atCstRepairPart==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstRepairPart字段的字串格式化输出****")}
					<td ref="cstRepairPart" class="td ">
						 
						 	${lz:or(item$cstRepairPart[i.count-1],lz:left(item.cstRepairPart$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstRepair || #request.defines['cstRepair']!=null">
				<s:if test="#request.defines['cstRepair']>0">
					${lz:set("注释","****cstRepair关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstRepair))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstRepair",lz:indexOf(fieldName,"cstRepair")>-1)}
				  		<s:if test="#request.atCstRepair==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstRepair字段的字串格式化输出****")}
					<td ref="cstRepair" class="td " relate="${item.cstRepair}">
						 
						 	<a <s:if test="#item.cstRepair!=null && #item.cstRepair!=''"> onclick="window.href('${basePath}${proname}/service/repair_details.do?key=${item.cstRepair}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cstRepair[i.count-1],lz:left(item.cstRepair$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstInvoice || #request.defines['cstInvoice']!=null">
				<s:if test="#request.defines['cstInvoice']>0">
					${lz:set("注释","****cstInvoice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstInvoice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstInvoice",lz:indexOf(fieldName,"cstInvoice")>-1)}
				  		<s:if test="#request.atCstInvoice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstInvoice字段的字串格式化输出****")}
					<td ref="cstInvoice" class="td ">
						 
						 	${lz:or(item$cstInvoice[i.count-1],lz:left(item.cstInvoice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstInsureFee || #request.defines['cstInsureFee']!=null">
				<s:if test="#request.defines['cstInsureFee']>0">
					${lz:set("注释","****cstInsureFee关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstInsureFee))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstInsureFee",lz:indexOf(fieldName,"cstInsureFee")>-1)}
				  		<s:if test="#request.atCstInsureFee==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstInsureFee字段的字串格式化输出****")}
					<td ref="cstInsureFee" class="td ">
						 
						 	${lz:or(item$cstInsureFee[i.count-1],lz:left(item.cstInsureFee$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstMoney || #request.defines['cstMoney']!=null">
				<s:if test="#request.defines['cstMoney']>0">
					${lz:set("注释","****cstMoney关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstMoney))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstMoney",lz:indexOf(fieldName,"cstMoney")>-1)}
				  		<s:if test="#request.atCstMoney==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstMoney字段的字串格式化输出****")}
					<td ref="cstMoney" class="td ">
						 
						 	${lz:or(item$cstMoney[i.count-1],lz:left(item.cstMoney$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstThirdLoss || #request.defines['cstThirdLoss']!=null">
				<s:if test="#request.defines['cstThirdLoss']>0">
					${lz:set("注释","****cstThirdLoss关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstThirdLoss))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstThirdLoss",lz:indexOf(fieldName,"cstThirdLoss")>-1)}
				  		<s:if test="#request.atCstThirdLoss==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstThirdLoss字段的字串格式化输出****")}
					<td ref="cstThirdLoss" class="td ">
						 
						 	${lz:or(item$cstThirdLoss[i.count-1],lz:left(item.cstThirdLoss$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstTrailFee || #request.defines['cstTrailFee']!=null">
				<s:if test="#request.defines['cstTrailFee']>0">
					${lz:set("注释","****cstTrailFee关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstTrailFee))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstTrailFee",lz:indexOf(fieldName,"cstTrailFee")>-1)}
				  		<s:if test="#request.atCstTrailFee==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstTrailFee字段的字串格式化输出****")}
					<td ref="cstTrailFee" class="td ">
						 
						 	${lz:or(item$cstTrailFee[i.count-1],lz:left(item.cstTrailFee$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstRepayDate || #request.defines['cstRepayDate']!=null">
				<s:if test="#request.defines['cstRepayDate']>0">
					${lz:set("注释","****cstRepayDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstRepayDate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstRepayDate",lz:indexOf(fieldName,"cstRepayDate")>-1)}
				  		<s:if test="#request.atCstRepayDate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstRepayDate字段的字串格式化输出****")}
					<td ref="cstRepayDate" class="td ">
						 
						 	${lz:or(item$cstRepayDate[i.count-1],lz:left(item.cstRepayDate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstRepayType || #request.defines['cstRepayType']!=null">
				<s:if test="#request.defines['cstRepayType']>0">
					${lz:set("注释","****cstRepayType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstRepayType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstRepayType",lz:indexOf(fieldName,"cstRepayType")>-1)}
				  		<s:if test="#request.atCstRepayType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstRepayType字段的字串格式化输出****")}
					<td ref="cstRepayType" class="td ">
						 
						 	${lz:or(item$cstRepayType[i.count-1],lz:left(item.cstRepayType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstThirdDesc || #request.defines['cstThirdDesc']!=null">
				<s:if test="#request.defines['cstThirdDesc']>0">
					${lz:set("注释","****cstThirdDesc关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstThirdDesc))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstThirdDesc",lz:indexOf(fieldName,"cstThirdDesc")>-1)}
				  		<s:if test="#request.atCstThirdDesc==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstThirdDesc字段的字串格式化输出****")}
					<td ref="cstThirdDesc" class="td ">
						 
						 	${lz:or(item$cstThirdDesc[i.count-1],lz:left(item.cstThirdDesc$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstCompensate || #request.defines['cstCompensate']!=null">
				<s:if test="#request.defines['cstCompensate']>0">
					${lz:set("注释","****cstCompensate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstCompensate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstCompensate",lz:indexOf(fieldName,"cstCompensate")>-1)}
				  		<s:if test="#request.atCstCompensate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstCompensate字段的字串格式化输出****")}
					<td ref="cstCompensate" class="td ">
						 
						 	${lz:or(item$cstCompensate[i.count-1],lz:left(item.cstCompensate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstCompDate || #request.defines['cstCompDate']!=null">
				<s:if test="#request.defines['cstCompDate']>0">
					${lz:set("注释","****cstCompDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstCompDate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstCompDate",lz:indexOf(fieldName,"cstCompDate")>-1)}
				  		<s:if test="#request.atCstCompDate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstCompDate字段的字串格式化输出****")}
					<td ref="cstCompDate" class="td ">
						 
						 	${lz:or(item$cstCompDate[i.count-1],lz:left(item.cstCompDate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstRepairPay || #request.defines['cstRepairPay']!=null">
				<s:if test="#request.defines['cstRepairPay']>0">
					${lz:set("注释","****cstRepairPay关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstRepairPay))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstRepairPay",lz:indexOf(fieldName,"cstRepairPay")>-1)}
				  		<s:if test="#request.atCstRepairPay==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstRepairPay字段的字串格式化输出****")}
					<td ref="cstRepairPay" class="td ">
						 
						 	${lz:or(item$cstRepairPay[i.count-1],lz:left(item.cstRepairPay$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstAgio || #request.defines['cstAgio']!=null">
				<s:if test="#request.defines['cstAgio']>0">
					${lz:set("注释","****cstAgio关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstAgio))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstAgio",lz:indexOf(fieldName,"cstAgio")>-1)}
				  		<s:if test="#request.atCstAgio==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstAgio字段的字串格式化输出****")}
					<td ref="cstAgio" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cstId}','agio')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cstAgio[i.count-1],lz:left(item.cstAgio$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstDecipt || #request.defines['cstDecipt']!=null">
				<s:if test="#request.defines['cstDecipt']>0">
					${lz:set("注释","****cstDecipt关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstDecipt))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstDecipt",lz:indexOf(fieldName,"cstDecipt")>-1)}
				  		<s:if test="#request.atCstDecipt==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstDecipt字段的字串格式化输出****")}
					<td ref="cstDecipt" class="td ">
						 
						 	${lz:or(item$cstDecipt[i.count-1],lz:left(item.cstDecipt$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstHappenTime || #request.defines['cstHappenTime']!=null">
				<s:if test="#request.defines['cstHappenTime']>0">
					${lz:set("注释","****cstHappenTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstHappenTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstHappenTime",lz:indexOf(fieldName,"cstHappenTime")>-1)}
				  		<s:if test="#request.atCstHappenTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstHappenTime字段的字串格式化输出****")}
					<td ref="cstHappenTime" class="td ">
						 
						 	${lz:or(item$cstHappenTime[i.count-1],lz:left(item.cstHappenTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstNotice || #request.defines['cstNotice']!=null">
				<s:if test="#request.defines['cstNotice']>0">
					${lz:set("注释","****cstNotice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstNotice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstNotice",lz:indexOf(fieldName,"cstNotice")>-1)}
				  		<s:if test="#request.atCstNotice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstNotice字段的字串格式化输出****")}
					<td ref="cstNotice" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cstId}','notice')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cstNotice[i.count-1],lz:left(item.cstNotice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstDealFlag || #request.defines['cstDealFlag']!=null">
				<s:if test="#request.defines['cstDealFlag']>0">
					${lz:set("注释","****cstDealFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstDealFlag))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstDealFlag",lz:indexOf(fieldName,"cstDealFlag")>-1)}
				  		<s:if test="#request.atCstDealFlag==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstDealFlag字段的字串格式化输出****")}
					<td ref="cstDealFlag" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cstId}','deal')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cstDealFlag[i.count-1],lz:left(item.cstDealFlag$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstAccepter || #request.defines['cstAccepter']!=null">
				<s:if test="#request.defines['cstAccepter']>0">
					${lz:set("注释","****cstAccepter关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstAccepter))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstAccepter",lz:indexOf(fieldName,"cstAccepter")>-1)}
				  		<s:if test="#request.atCstAccepter==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstAccepter字段的字串格式化输出****")}
					<td ref="cstAccepter" class="td " relate="${item.cstAccepter}">
						 
						 	<a <s:if test="#item.cstAccepter!=null && #item.cstAccepter!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.cstAccepter}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cstAccepter[i.count-1],lz:left(item.cstAccepter$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstCaser || #request.defines['cstCaser']!=null">
				<s:if test="#request.defines['cstCaser']>0">
					${lz:set("注释","****cstCaser关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstCaser))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstCaser",lz:indexOf(fieldName,"cstCaser")>-1)}
				  		<s:if test="#request.atCstCaser==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstCaser字段的字串格式化输出****")}
					<td ref="cstCaser" class="td " relate="${item.cstCaser}">
						 
						 	<a <s:if test="#item.cstCaser!=null && #item.cstCaser!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.cstCaser}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cstCaser[i.count-1],lz:left(item.cstCaser$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstFinishTime || #request.defines['cstFinishTime']!=null">
				<s:if test="#request.defines['cstFinishTime']>0">
					${lz:set("注释","****cstFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstFinishTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstFinishTime",lz:indexOf(fieldName,"cstFinishTime")>-1)}
				  		<s:if test="#request.atCstFinishTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstFinishTime字段的字串格式化输出****")}
					<td ref="cstFinishTime" class="td ">
						 
						 	${lz:or(item$cstFinishTime[i.count-1],lz:left(item.cstFinishTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstRemark || #request.defines['cstRemark']!=null">
				<s:if test="#request.defines['cstRemark']>0">
					${lz:set("注释","****cstRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstRemark",lz:indexOf(fieldName,"cstRemark")>-1)}
				  		<s:if test="#request.atCstRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstRemark字段的字串格式化输出****")}
					<td ref="cstRemark" class="td ">
						 
						 	${lz:or(item$cstRemark[i.count-1],lz:left(item.cstRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstUpdateTime || #request.defines['cstUpdateTime']!=null">
				<s:if test="#request.defines['cstUpdateTime']>0">
					${lz:set("注释","****cstUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstUpdateTime",lz:indexOf(fieldName,"cstUpdateTime")>-1)}
				  		<s:if test="#request.atCstUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstUpdateTime字段的字串格式化输出****")}
					<td ref="cstUpdateTime" class="td ">
						 
						 	${lz:or(item$cstUpdateTime[i.count-1],lz:left(item.cstUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstAddTime || #request.defines['cstAddTime']!=null">
				<s:if test="#request.defines['cstAddTime']>0">
					${lz:set("注释","****cstAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstAddTime",lz:indexOf(fieldName,"cstAddTime")>-1)}
				  		<s:if test="#request.atCstAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstAddTime字段的字串格式化输出****")}
					<td ref="cstAddTime" class="td ">
						 
						 	${lz:or(item$cstAddTime[i.count-1],lz:left(item.cstAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cstStatus || #request.defines['cstStatus']!=null">
				<s:if test="#request.defines['cstStatus']>0">
					${lz:set("注释","****cstStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cstStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCstStatus",lz:indexOf(fieldName,"cstStatus")>-1)}
				  		<s:if test="#request.atCstStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cstStatus字段的字串格式化输出****")}
					<td ref="cstStatus" class="td ">
						 
						 	${lz:or(item$cstStatus[i.count-1],lz:left(item.cstStatus$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('定损','${item.cstId}')">定损</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[1]==true">
					<a href="javascript:HandleSel('协商','${item.cstId}')">协商</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[2]==true">
					<a href="javascript:HandleSel('维修','${item.cstId}')">维修</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[3]==true">
					<a href="javascript:HandleSel('扣款','${item.cstId}')">扣款</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[4]==true">
					<a href="javascript:HandleSel('结案','${item.cstId}')">结案</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.cstId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectTrouble('${item.cstId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditTrouble('${item.cstId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelTrouble('${item.cstId}','${item.cstId}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsTrouble('${item.cstId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cstId || #request.defines['cstId']!=null">
				<s:if test="#request.defines['cstId']>0">
					${lz:set("注释","****cstId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstId",lz:indexOf(fieldName,"cstId")>-1)}				  		
				  		<s:if test="#request.atCstId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstHost || #request.defines['cstHost']!=null">
				<s:if test="#request.defines['cstHost']>0">
					${lz:set("注释","****cstHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstHost",lz:indexOf(fieldName,"cstHost")>-1)}				  		
				  		<s:if test="#request.atCstHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstOrder || #request.defines['cstOrder']!=null">
				<s:if test="#request.defines['cstOrder']>0">
					${lz:set("注释","****cstOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstOrder",lz:indexOf(fieldName,"cstOrder")>-1)}				  		
				  		<s:if test="#request.atCstOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstMember || #request.defines['cstMember']!=null">
				<s:if test="#request.defines['cstMember']>0">
					${lz:set("注释","****cstMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstMember",lz:indexOf(fieldName,"cstMember")>-1)}				  		
				  		<s:if test="#request.atCstMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstCar || #request.defines['cstCar']!=null">
				<s:if test="#request.defines['cstCar']>0">
					${lz:set("注释","****cstCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstCar",lz:indexOf(fieldName,"cstCar")>-1)}				  		
				  		<s:if test="#request.atCstCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstType || #request.defines['cstType']!=null">
				<s:if test="#request.defines['cstType']>0">
					${lz:set("注释","****cstType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstType",lz:indexOf(fieldName,"cstType")>-1)}				  		
				  		<s:if test="#request.atCstType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstDamage || #request.defines['cstDamage']!=null">
				<s:if test="#request.defines['cstDamage']>0">
					${lz:set("注释","****cstDamage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstDamage",lz:indexOf(fieldName,"cstDamage")>-1)}				  		
				  		<s:if test="#request.atCstDamage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstDamage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstRepairAdr || #request.defines['cstRepairAdr']!=null">
				<s:if test="#request.defines['cstRepairAdr']>0">
					${lz:set("注释","****cstRepairAdr关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstRepairAdr",lz:indexOf(fieldName,"cstRepairAdr")>-1)}				  		
				  		<s:if test="#request.atCstRepairAdr==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstRepairAdr[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstRepairPart || #request.defines['cstRepairPart']!=null">
				<s:if test="#request.defines['cstRepairPart']>0">
					${lz:set("注释","****cstRepairPart关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstRepairPart",lz:indexOf(fieldName,"cstRepairPart")>-1)}				  		
				  		<s:if test="#request.atCstRepairPart==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstRepairPart[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstRepair || #request.defines['cstRepair']!=null">
				<s:if test="#request.defines['cstRepair']>0">
					${lz:set("注释","****cstRepair关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstRepair",lz:indexOf(fieldName,"cstRepair")>-1)}				  		
				  		<s:if test="#request.atCstRepair==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstRepair[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstInvoice || #request.defines['cstInvoice']!=null">
				<s:if test="#request.defines['cstInvoice']>0">
					${lz:set("注释","****cstInvoice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstInvoice",lz:indexOf(fieldName,"cstInvoice")>-1)}				  		
				  		<s:if test="#request.atCstInvoice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstInvoice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstInsureFee || #request.defines['cstInsureFee']!=null">
				<s:if test="#request.defines['cstInsureFee']>0">
					${lz:set("注释","****cstInsureFee关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstInsureFee",lz:indexOf(fieldName,"cstInsureFee")>-1)}				  		
				  		<s:if test="#request.atCstInsureFee==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstInsureFee[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstMoney || #request.defines['cstMoney']!=null">
				<s:if test="#request.defines['cstMoney']>0">
					${lz:set("注释","****cstMoney关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstMoney",lz:indexOf(fieldName,"cstMoney")>-1)}				  		
				  		<s:if test="#request.atCstMoney==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstMoney[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstThirdLoss || #request.defines['cstThirdLoss']!=null">
				<s:if test="#request.defines['cstThirdLoss']>0">
					${lz:set("注释","****cstThirdLoss关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstThirdLoss",lz:indexOf(fieldName,"cstThirdLoss")>-1)}				  		
				  		<s:if test="#request.atCstThirdLoss==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstThirdLoss[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstTrailFee || #request.defines['cstTrailFee']!=null">
				<s:if test="#request.defines['cstTrailFee']>0">
					${lz:set("注释","****cstTrailFee关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstTrailFee",lz:indexOf(fieldName,"cstTrailFee")>-1)}				  		
				  		<s:if test="#request.atCstTrailFee==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstTrailFee[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstRepayDate || #request.defines['cstRepayDate']!=null">
				<s:if test="#request.defines['cstRepayDate']>0">
					${lz:set("注释","****cstRepayDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstRepayDate",lz:indexOf(fieldName,"cstRepayDate")>-1)}				  		
				  		<s:if test="#request.atCstRepayDate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstRepayDate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstRepayType || #request.defines['cstRepayType']!=null">
				<s:if test="#request.defines['cstRepayType']>0">
					${lz:set("注释","****cstRepayType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstRepayType",lz:indexOf(fieldName,"cstRepayType")>-1)}				  		
				  		<s:if test="#request.atCstRepayType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstRepayType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstThirdDesc || #request.defines['cstThirdDesc']!=null">
				<s:if test="#request.defines['cstThirdDesc']>0">
					${lz:set("注释","****cstThirdDesc关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstThirdDesc",lz:indexOf(fieldName,"cstThirdDesc")>-1)}				  		
				  		<s:if test="#request.atCstThirdDesc==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstThirdDesc[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstCompensate || #request.defines['cstCompensate']!=null">
				<s:if test="#request.defines['cstCompensate']>0">
					${lz:set("注释","****cstCompensate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstCompensate",lz:indexOf(fieldName,"cstCompensate")>-1)}				  		
				  		<s:if test="#request.atCstCompensate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstCompensate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstCompDate || #request.defines['cstCompDate']!=null">
				<s:if test="#request.defines['cstCompDate']>0">
					${lz:set("注释","****cstCompDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstCompDate",lz:indexOf(fieldName,"cstCompDate")>-1)}				  		
				  		<s:if test="#request.atCstCompDate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstCompDate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstRepairPay || #request.defines['cstRepairPay']!=null">
				<s:if test="#request.defines['cstRepairPay']>0">
					${lz:set("注释","****cstRepairPay关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstRepairPay",lz:indexOf(fieldName,"cstRepairPay")>-1)}				  		
				  		<s:if test="#request.atCstRepairPay==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstRepairPay[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstAgio || #request.defines['cstAgio']!=null">
				<s:if test="#request.defines['cstAgio']>0">
					${lz:set("注释","****cstAgio关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstAgio",lz:indexOf(fieldName,"cstAgio")>-1)}				  		
				  		<s:if test="#request.atCstAgio==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstAgio[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstDecipt || #request.defines['cstDecipt']!=null">
				<s:if test="#request.defines['cstDecipt']>0">
					${lz:set("注释","****cstDecipt关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstDecipt",lz:indexOf(fieldName,"cstDecipt")>-1)}				  		
				  		<s:if test="#request.atCstDecipt==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstDecipt[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstHappenTime || #request.defines['cstHappenTime']!=null">
				<s:if test="#request.defines['cstHappenTime']>0">
					${lz:set("注释","****cstHappenTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstHappenTime",lz:indexOf(fieldName,"cstHappenTime")>-1)}				  		
				  		<s:if test="#request.atCstHappenTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstHappenTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstNotice || #request.defines['cstNotice']!=null">
				<s:if test="#request.defines['cstNotice']>0">
					${lz:set("注释","****cstNotice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstNotice",lz:indexOf(fieldName,"cstNotice")>-1)}				  		
				  		<s:if test="#request.atCstNotice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstNotice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstDealFlag || #request.defines['cstDealFlag']!=null">
				<s:if test="#request.defines['cstDealFlag']>0">
					${lz:set("注释","****cstDealFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstDealFlag",lz:indexOf(fieldName,"cstDealFlag")>-1)}				  		
				  		<s:if test="#request.atCstDealFlag==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstDealFlag[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstAccepter || #request.defines['cstAccepter']!=null">
				<s:if test="#request.defines['cstAccepter']>0">
					${lz:set("注释","****cstAccepter关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstAccepter",lz:indexOf(fieldName,"cstAccepter")>-1)}				  		
				  		<s:if test="#request.atCstAccepter==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstAccepter[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstCaser || #request.defines['cstCaser']!=null">
				<s:if test="#request.defines['cstCaser']>0">
					${lz:set("注释","****cstCaser关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstCaser",lz:indexOf(fieldName,"cstCaser")>-1)}				  		
				  		<s:if test="#request.atCstCaser==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstCaser[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstFinishTime || #request.defines['cstFinishTime']!=null">
				<s:if test="#request.defines['cstFinishTime']>0">
					${lz:set("注释","****cstFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstFinishTime",lz:indexOf(fieldName,"cstFinishTime")>-1)}				  		
				  		<s:if test="#request.atCstFinishTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstFinishTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstRemark || #request.defines['cstRemark']!=null">
				<s:if test="#request.defines['cstRemark']>0">
					${lz:set("注释","****cstRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstRemark",lz:indexOf(fieldName,"cstRemark")>-1)}				  		
				  		<s:if test="#request.atCstRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstUpdateTime || #request.defines['cstUpdateTime']!=null">
				<s:if test="#request.defines['cstUpdateTime']>0">
					${lz:set("注释","****cstUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstUpdateTime",lz:indexOf(fieldName,"cstUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCstUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstAddTime || #request.defines['cstAddTime']!=null">
				<s:if test="#request.defines['cstAddTime']>0">
					${lz:set("注释","****cstAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstAddTime",lz:indexOf(fieldName,"cstAddTime")>-1)}				  		
				  		<s:if test="#request.atCstAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cstStatus || #request.defines['cstStatus']!=null">
				<s:if test="#request.defines['cstStatus']>0">
					${lz:set("注释","****cstStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCstStatus",lz:indexOf(fieldName,"cstStatus")>-1)}				  		
				  		<s:if test="#request.atCstStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cstStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加车辆事故
	**/
	function AddTrouble(parent){		
		var url = "${basePath}${namespace}trouble_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加车辆事故'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择车辆事故
	**/
	function SelectTrouble(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改车辆事故
	**/
	function EditTrouble(id){
		var url = "${basePath}${namespace}trouble_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改车辆事故'}":lz:json(ctrl)}};
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
		EditTrouble(checker.val());
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
				var url = "${basePath}${namespace}trouble_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新车辆事故",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}trouble_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新车辆事故",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改车辆事故
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}trouble_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新车辆事故",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["agio"]={};
	fieldNames["notice"]={};
	fieldNames["deal"]={};
	fieldNames["agio"]["cstAgio"]=true;
	fieldNames["notice"]["cstNotice"]=true;
	fieldNames["deal"]["cstDealFlag"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改车辆事故任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}trouble_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新车辆事故",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}trouble_edit.do";
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
			var url = "${basePath}${namespace}trouble_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除车辆事故
	**/
	function DelTrouble(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除车辆事故["+flag+"]吗？</span>",
				title:"删除车辆事故",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}trouble_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选车辆事故吗？</span>",
				title:"批量删除车辆事故",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}trouble_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示车辆事故详情
	**/
	function DetailsTrouble(id)
	{
		var url = "${basePath}${namespace}trouble_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"车辆事故详情",editable:false,visible:true}};
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
		DetailsTrouble(checker.val());
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