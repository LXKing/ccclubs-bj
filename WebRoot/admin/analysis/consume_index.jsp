<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/analysis/consume.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>
${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}

${lz:set("注释","***************************************************")}

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>会员消费记录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="JUST">
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
	<a href="javascript:void(0);" onclick="exportXls();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256427828778018.png"/>
		数据导出
	</a>
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"会员消费记录":title}</span>
</div>
${before$form}

<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="MemberConsuMe" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
			<%@include file="consume.query.jsp"%> 
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


</td>
</tr>
<tr>
	<td>
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
			<button type="button" class="add" onclick="AddCsCarParts();">
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
			<dl ref="com.ccclubs.model.MemberConsuMe" class="display dropdowan" style="margin-left:30px;">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/display.png"/>
				</dt>
			</dl>
			${after$toolbar}
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="carparts_table" ref="MemberConsuMe" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
			 <td <s:if test="#request.defines['csmId']>0">colspan="${defines["csmId"]}" ${all?"width":"iwidth"}="${defines["csmId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csmId"   title="编号">
			 	编号
			 	${lz:set("checkeds[]","memberId")}
			 </td>
			 <td <s:if test="#request.defines['csmName']>0">colspan="${defines["csmName"]}" ${all?"width":"iwidth"}="${defines["csmName"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="2" ref="csmName"   title="姓名">
			 	姓名
			 	${lz:set("checkeds[]","memberName")}
			 </td>
			 <td <s:if test="#request.defines['csuiName']>0">colspan="${defines["csuiName"]}" ${all?"width":"iwidth"}="${defines["csuiName"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="4" ref="csuiName"   title="所属单位">
			 	<a class="${desc=="csui_name" ? "desc" : ""}${asc=="csui_name" ? "asc" : ""}" href="?${desc=="csui_name" ? "asc=csui_name" : ""}${(asc=="csui_name" || desc!="csui_name" )? "desc=csui_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属单位
			 	</a>
			 	${lz:set("checkeds[]","infoName")}
			 </td>
			 <td <s:if test="#request.defines['csugName']>0">colspan="${defines["csugName"]}" ${all?"width":"iwidth"}="${defines["csugName"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="3" ref="csugName"   title="部门名称">
			 	部门名称
			 	${lz:set("checkeds[]","groupName")}
			 </td>
			 <td <s:if test="#request.defines['orderCount']>0">colspan="${defines["orderCount"]}" ${all?"width":"iwidth"}="${defines["orderCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="4" ref="orderCount"   title="订单总数">
			 	订单总数
			 	${lz:set("checkeds[]","orderCount")}
			 </td>
			 <td <s:if test="#request.defines['orderTime']>0">colspan="${defines["orderTime"]}" ${all?"width":"iwidth"}="${defines["orderTime"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="5" ref="orderTime"   title="订单总时长">
			 	订单总时长
			 	${lz:set("checkeds[]","orderTime")}
			 </td>
			 <td <s:if test="#request.defines['rechargeCount']>0">colspan="${defines["rechargeCount"]}" ${all?"width":"iwidth"}="${defines["rechargeCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="rechargeCount"   title="充值总金额">
			 	充值总金额
			 	${lz:set("checkeds[]","rechargeCount")}
			 </td>
			 <td <s:if test="#request.defines['consumeCount']>0">colspan="${defines["consumeCount"]}" ${all?"width":"iwidth"}="${defines["consumeCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="consumeCount"   title="消费总金额">
			 	消费总金额
			 	${lz:set("checkeds[]","consumeCount")}
			 </td>
			 
			 <td <s:if test="#request.defines['orderConsume']>0">colspan="${defines["orderConsume"]}" ${all?"width":"iwidth"}="${defines["orderConsume"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="orderConsume"   title="订单消费总金额">
			 	订单消费总金额
			 	${lz:set("checkeds[]","orderConsume")}
			 </td>
		  
			 <td <s:if test="#request.defines['csmMoney']>0">colspan="${defines["csmMoney"]}" ${all?"width":"iwidth"}="${defines["csmMoney"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="csmMoney"   title="csmMoney">
			 	余额
			 	${lz:set("checkeds[]","money")}
			 </td>
		  
			 <td <s:if test="#request.defines['balanceRechargeCount']>0">colspan="${defines["balanceRechargeCount"]}" ${all?"width":"iwidth"}="${defines["balanceRechargeCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="balanceRechargeCount"   title="余额用户充值总计">
			 	余额充值总计
			 	${lz:set("checkeds[]","balanceRechargeCount")}
			 </td>
		  
			 <td <s:if test="#request.defines['backgroundCount']>0">colspan="${defines["backgroundCount"]}" ${all?"width":"iwidth"}="${defines["backgroundCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="balanceRechargeCount"   title="余额后台充值总计">
			 	余额后台充值总计
			 	${lz:set("checkeds[]","backgroundCount")}
			 </td>
		  
			 <td <s:if test="#request.defines['balanceConsuMeCount']>0">colspan="${defines["balanceConsuMeCount"]}" ${all?"width":"iwidth"}="${defines["balanceConsuMeCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="balanceRechargeCount"   title="余额用户消费总计">
			 	余额消费总计
			 	${lz:set("checkeds[]","balanceConsuMeCount")}
			 </td>
		  
			 <td <s:if test="#request.defines['backConsuMeCount']>0">colspan="${defines["backConsuMeCount"]}" ${all?"width":"iwidth"}="${defines["backConsuMeCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="backConsuMeCount"   title="余额后台扣款总计">
			 	余额扣款总计
			 	${lz:set("checkeds[]","backConsuMeCount")}
			 </td>
			 <td <s:if test="#request.defines['csmCoupon']>0">colspan="${defines["csmCoupon"]}" ${all?"width":"iwidth"}="${defines["csmCoupon"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="csmCoupon"   title="现金券">
			 	现金券
			 	${lz:set("checkeds[]","coupon")}
			 </td>
			 <td <s:if test="#request.defines['couponCount']>0">colspan="${defines["couponCount"]}" ${all?"width":"iwidth"}="${defines["couponCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="couponCount"   title="现金券用户购买统计">
			 	现金券购买统计
			 	${lz:set("checkeds[]","couponCount")}
			 </td>
			 <td <s:if test="#request.defines['backRechargeCoupon']>0">colspan="${defines["backRechargeCoupon"]}" ${all?"width":"iwidth"}="${defines["backRechargeCoupon"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="backRechargeCoupon"   title="现金券后台充值统计">
			 	现金券充值统计
			 	${lz:set("checkeds[]","backRechargeCoupon")}
			 </td>
			 <td <s:if test="#request.defines['couponConsuMeCount']>0">colspan="${defines["couponConsuMeCount"]}" ${all?"width":"iwidth"}="${defines["couponConsuMeCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="couponConsuMeCount"   title="现金券用户消费统计">
			 	现金券消费统计
			 	${lz:set("checkeds[]","couponConsuMeCount")}
			 </td>
			 <td <s:if test="#request.defines['backConsuMeCouponCount']>0">colspan="${defines["backConsuMeCouponCount"]}" ${all?"width":"iwidth"}="${defines["backConsuMeCouponCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="backConsuMeCouponCount"   title="现金券后台扣款统计">
			 	现金券扣款统计
			 	${lz:set("checkeds[]","backConsuMeCouponCount")}
			 </td>
			 <td <s:if test="#request.defines['coinCount']>0">colspan="${defines["coinCount"]}" ${all?"width":"iwidth"}="${defines["coinCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="coinCount"   title="红包">
			 	红包
			 	${lz:set("checkeds[]","coinCount")}
			 </td>
			 <td <s:if test="#request.defines['giveCoinCount']>0">colspan="${defines["giveCoinCount"]}" ${all?"width":"iwidth"}="${defines["giveCoinCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="giveCoinCount"   title="红包赠送总计">
			 	红包赠送总计
			 	${lz:set("checkeds[]","giveCoinCount")}
			 </td>
			 <td <s:if test="#request.defines['coinConsuMeCount']>0">colspan="${defines["coinConsuMeCount"]}" ${all?"width":"iwidth"}="${defines["coinConsuMeCount"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="coinConsuMeCount"   title="红包消费总计">
			 	红包消费总计
			 	${lz:set("checkeds[]","coinConsuMeCount")}
			 </td>
		  
		  
		  
		  ${lz:set("注释","****数据列表列头最后两列****")}
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
		  <tr id="${item.csMember.csmId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csMember.csmId}"/>
			</td>
		  
		  
		   ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			
			<td ref="csmId" class="td ">
					${item.csMember.csmId}
			</td>
			
			<td ref="csmName" class="td ">
					${item.csMember.csmName}
			</td>
			<td ref="csuiName" class="td ">
					${item.csUnitPerson.$csupInfo.csuiName}
			</td>
			<td ref="csugName" class="td ">
					${item.csUnitPerson.$csupGroup.csugName}
			</td><td ref="orderCount" class="td ">
					${item.orderCount}
			</td>
			<td ref="orderTime" class="td ">
					${item.orderTime}
			</td>
			<td ref="rechargeCount" class="td ">
					${item.rechargeCount}
			</td>
			<td ref="consumeCount" class="td ">
					${item.consumeCount}
			</td>
			<td ref="orderConsume" class="td ">
					${item.orderConsume}
			</td>
			<td ref="csmMoney" class="td ">
					${item.csMember.csmMoney}
			</td>
			<td ref="balanceRechargeCount" class="td ">
					${item.balanceRechargeCount}
			</td>
			<td ref="backgroundCount" class="td ">
					${item.backgroundCount}
			</td>
			<td ref="balanceConsuMeCount" class="td ">
					${item.balanceConsuMeCount}
			</td>
			<td ref="backConsuMeCount" class="td ">
					${item.backConsuMeCount}
			</td>
			
			<td ref="csmCoupon" class="td ">
					${item.csMember.csmCoupon}
			</td>
			
			
			<td ref="couponCount" class="td ">
					${item.couponCount}
			</td>
			
			<td ref="backRechargeCoupon" class="td ">
					${item.backRechargeCoupon}
			</td>
			
			<td ref="couponConsuMeCount" class="td ">
					${item.couponConsuMeCount}
			</td>
			
			<td ref="backConsuMeCouponCount" class="td ">
					${item.backConsuMeCouponCount}
			</td>
			
			<td ref="coinCount" class="td ">
					${item.coinCount}
			</td>
			
			<td ref="giveCoinCount" class="td ">
					${item.giveCoinCount}
			</td>
			
			<td ref="coinConsuMeCount" class="td ">
					${item.coinConsuMeCount}
			</td>
			
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		  </tr>
		  
		  
		  </s:iterator>
		 </tbody>			 
	  </table>
	  
	  ${lz:set("注释","*****************数据列表结束*****************")}
	  
  </div>
  <%@include file="/admin/module/page.jsp"%>
  
 </td></tr></table>
<script>
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
		  


