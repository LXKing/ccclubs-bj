<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/order.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrder.csoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csoId,csoHost,csoPayMember,csoUseMember,csoFeeType,csoArea,csoOutlets,csoOutletsRet,csoModel,csoCar,csoProvid,csoMobile,csoStartTime,csoFinishTime,csoLongOrder,csoUseType,csoRetTime,csoDuration,csoCreditCard,csoCreditBill,csoInsureType,csoIncome,csoUseIndex,csoFlag,csoFrom,csoSrc,csoUpdateTime,csoAddTime,csoEditor,csoDeviceVesion,csoAlarm,csoState,csoStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csoId,csoHost,csoPayMember,csoUseMember,csoFeeType,csoArea,csoOutlets,csoOutletsRet,csoModel,csoCar,csoProvid,csoCarNumber,csoMobile,csoEvRfid,csoIsAdCar,csoStartTime,csoFinishTime,csoLongOrder,csoLongPrice,csoUseType,csoTakeTime,csoRetTime,csoDuration,csoFuel,csoElectric,csoCreditCard,csoCreditBill,csoInsureType,csoFreehour,csoRebate,csoMarginNeed,csoPredict,csoPayNeed,csoPayReal,csoPayMoney,csoPayCoupon,csoPayCoin,csoPayKilom,csoPayMileage,csoPayRent,csoPayInsure,csoPayTimeout,csoPayDriver,csoIncome,csoUseIndex,csoFlag,csoFrom,csoSrc,csoUpdateTime,csoAddTime,csoEndTime,csoSerial,csoCode,csoRemark,csoEditor,csoMask,csoDeviceVesion,csoAlarm,csoState,csoStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csoId,csoHost,csoUseMember,csoCar,csoStartTime,csoFinishTime,csoUseType,csoCreditBill,csoState,csoStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csoId,csoHost,csoUseMember,csoCar,csoStartTime,csoFinishTime,csoUseType,csoCreditBill,csoCode,csoState,csoStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>系统订单统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	<meta name="hashcode" content="${window.hashcode}">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	<!-- 统计处理js -->
	<script type="text/javascript" src="${basePath}admin/js/lazy3q.stats.js"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	<script type="text/javascript">
		window.basePath="${basePath}";
		window.hashcode = "${window.hashcode}";
		window.contextPath="${basePath}admin/";
		window.apiUrl="${basePath}admin/api.do";
	</script>
		
	<!-- 感谢My97DatePicker时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	<!-- 感谢FusionCharts报表图控件 -->
	<script type="text/javascript" src="${basePath}admin/js/FusionCharts/JSClass/FusionCharts.js"></script>
	
	
	<!------------------------LAZY3Q_JSP_HEAD------------------------>
	<style>
	.fast a{margin:0px 2px;}
	</style>
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
<lz:set name="defineHtml">
	<div class="fast condition" style="width:auto;font-weight:normal;">
		<strong>快速选择：</strong>
		<ul>
			<li>
				<a href="javascript:void(0);" start="" end="" type="button">清空</a>
				<a href="javascript:void(0);" start="${dates.today.start.time}" end="${dates.today.end.time}" type="button">今天</a>
				<a href="javascript:void(0);" start="${dates.today.start.time-1*24*60*60*1000}" end="${dates.today.end.time-1*24*60*60*1000}" type="button">昨天</a>
				<a href="javascript:void(0);" start="${dates.today.start.time-2*24*60*60*1000}" end="${dates.today.end.time-2*24*60*60*1000}" type="button">前天</a>
				<a href="javascript:void(0);" start="${dates.week.start.time}" end="${dates.week.end.time}" type="button">这周</a>
				<a href="javascript:void(0);" start="${dates.week.start.time-7*24*60*60*1000}" end="${dates.week.end.time-7*24*60*60*1000}" type="button">上周</a>
				<a href="javascript:void(0);" start="${dates.month.start.time}" end="${dates.month.end.time}" type="button">本月</a>
				<a href="javascript:void(0);" start="${dates.premonth.start.time}" end="${dates.premonth.end.time}" type="button">上月</a>
				<a href="javascript:void(0);" start="${dates.year.start.time}" end="${dates.year.end.time}" type="button">今年</a>
				<a href="javascript:void(0);" start="${dates.preyear.start.time}" end="${dates.preyear.end.time}" type="button">去年</a>
				&nbsp;&nbsp;
				<select title="全部时间：指查询结果中所有匹配到的结果统计,时间之内：订单的时长与查询的时间交集部分的统计。只有订单开始时间和订单结束时间都输入一个以上的情况下，才动态统计时间之内收入" id="netincome" name="netincome">
					<option ${netincome==true?"selected":""} value="true">时间之内</option>
					<option ${netincome==false?"selected":""} value="false">全部时间</option>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<select id="notrevenue" name="notrevenue" style="width:200px;">
					<option ${notrevenue==true?"selected":""} value="true">排除非营收会员支付的订单</option>
					<option ${notrevenue==false?"selected":""} value="false">不排除非营收会员支付的订单</option>
				</select>
			</li>
			<div class="clear"></div>
		</ul>
	</div>
	<div class="line"></div>
</lz:set>
<script>
$(function(){
	var fast = $("${lz:js(defineHtml)}").insertBefore(".group");
	$("#order_table").hide().show();
	fast.find("a").click(function(){
		if($(this).attr("start")=="" || $(this).attr("end")==""){
			$("#csoStartTimeEnd").val("");
			$("#csoFinishTimeStart").val("");
		}else{
			var start = new Date(Number($(this).attr("start")));
			var end = new Date(Number($(this).attr("end")));
			$("#csoStartTimeEnd").val($.formatDate("yyyy-MM-dd HH:mm:ss",end));
			$("#csoFinishTimeStart").val($.formatDate("yyyy-MM-dd HH:mm:ss",start));
		}
		$.toast("时间设置成功");
	});
	$("#notrevenue").change(function(){
		if($(this).val()=="false"){
			var dd = $("#csoPayMember").parents("dd:eq(0)");
			var radio = dd.find(".join[type='radio']");
			radio.attr("checked",false);
			dd.find(".b").addClass("hidden");
			dd.find(".a").removeClass("hidden");
		}
	});
	/*
	var timelong = $("<li><input ${timeLongSum==true?"checked='checked'":""} type=\"checkbox\" name=\"sum\" value=\"timeLong\"/>订单时长&nbsp;&nbsp;</li>");
	timelong.appendTo(".sum ul");	
	var timeScale = $("<li><input ${timeScaleSum==true?"checked='checked'":""} type=\"checkbox\" name=\"sum\" value=\"timeScale\"/>订单时长&nbsp;&nbsp;</li>");
	timelong.appendTo(".sum ul");
	*/	
});
</script>
<!--LAZY3Q_JSP_BODY-->


${lz:set("querys",get:querys(servletPath))}
${lz:set("haveQuerys",lz:size(querys)>0)}

<div class="shortcuts state-content" style="display:${(window.shortcuts!="hide" && (shortcuts!=null || haveQuerys))?"block":"none"};">
	<div class="shadow"></div>
	<div class="holder">
		<a class="item ${(T+Q+0)==0?"focus":""}" href="${location}">		
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256421631277806.png"/>
			默认数据统计
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


<table class="${(window.shortcuts!="hide" && (shortcuts!=null || haveQuerys))?"margin-by-shortcut":""}" border="0" cellspacing="0" cellpadding="0"><tr><td>

<div class="title">
	
	<a href="javascript:void(0);" onclick="parent.current.close();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Badge-multiply.png"/>
		关闭页面
	</a>
	<s:if test="#request.hideQuery!=true">
	<a href="javascript:void(0);" class="linker imize ${window.imize=="minimize"?"minimize":"maximize"}">
		<img align="absmiddle" class="up" src="${basePath}admin/images/icons/Arrow up.png"/>
		<img align="absmiddle" class="down" src="${basePath}admin/images/icons/Arrow down.png"/>
		展开收起
	</a>
	</s:if>
	<a href="javascript:void(0);" class="linker setting">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256421529677801.png"/>
		页面设置
	</a>
	<a href="?${lz:querys("UTF-8","xxxxx")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/2008825642250778028.png"/>
		重新加载
	</a>
	<a class="linker export" href="order_stats.do?export=true">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256427828778018.png"/>
		数据导出
	</a>
	<a href="order.do?${lz:querys("UTF-8","")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256421631277806.png"/>
		数据管理
	</a>
		
		
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">系统订单统计</span>
	<dl ref="com.ccclubs.model.CsOrder" class="display dropdowan" style="margin-left:30px;">
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
				<a all="" fields="${fields}" href="${basePath}${lz:replace(servletPath,"^/","")}?${lz:queryss("UTF-8","all","fields")}">自定义显示</a>
			</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
			</tbody></table>
		</dd>
	</dl>
</div>
<s:if test="#request.canQuery==true && #request.CTRL.canQuery==true">
<div class="plate" style="${(hideQuery || (lz:or(window.imize,"maximize"))=="minimize") ? "display:none;":""}">
	<form ref="CsOrder" class="query statsForm" id="queryForm" action="order_stats.do" method="post">
		
		${lz:set("haveQuery",false)}
	
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="order.query.jsp"%>
	
		<div class="line"></div>
		
		<div class="group condition">
			<strong>统计分组：</strong>				
			<ul>
				<li>
					<input ${csoFeeTypeGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoFeeType"/>计费类型
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoAreaGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoArea"/>网点区域
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoOutletsGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoOutlets"/>取车网点
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoOutletsRetGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoOutletsRet"/>还车网点
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoModelGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoModel"/>预订车型
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoCarGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoCar"/>预订车辆
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoProvidGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoProvid"/>车辆商家
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoIsAdCarGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoIsAdCar"/>宣传车？
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoLongOrderGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoLongOrder"/>是否长单
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoInsureTypeGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoInsureType"/>保险类型
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoFlagGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoFlag"/>类型标识
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoFromGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoFrom"/>订单来源
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoAddTimeGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoAddTime"/>
				 	<select name="csoAddTimeTimeType">
				 		<option value="%Y-%m,yyyy年MM月">添加时间</option>
				 		
				 		<option ${csoAddTimeTimeType=="%Y,yyyy年"?"selected":""} value="%Y,yyyy年">
					 	每年(添加时间)</option>
					 	<option ${csoAddTimeTimeType=="%Y-%m,yyyy年MM月"?"selected":""} value="%Y-%m,yyyy年MM月">
					 	每月(添加时间)</option>
					 	<option ${csoAddTimeTimeType=="%Y%U,yyyy年第ww周"?"selected":""} value="%Y%U,yyyy年第ww周">
					 	每周(添加时间)</option>
					 	<option ${csoAddTimeTimeType=="%Y-%m-%d,yyyy年MM月dd日"?"selected":""} value="%Y-%m-%d,yyyy年MM月dd日">
					 	每日(添加时间)</option>
					 	<option ${csoAddTimeTimeType=="%Y-%m-%d %H,yyyy年MM月dd日 HH点"?"selected":""} value="%Y-%m-%d %H,yyyy年MM月dd日 HH点">
					 	每时(添加时间)</option>
					 	
					 	<option ${csoAddTimeTimeType=="%m,MM月"?"selected":""} value="%m,MM月">
					 	月份(添加时间)</option>
					 	<option ${csoAddTimeTimeType=="%U,第ww周"?"selected":""} value="%U,第ww周">
					 	周份(添加时间)</option>
					 	<option ${csoAddTimeTimeType=="%w,EEEE"?"selected":""} value="%w,EEEE">
					 	星期(添加时间)</option>
					 	<option ${csoAddTimeTimeType=="%d,dd日"?"selected":""} value="%d,dd日">
					 	天份(添加时间)</option>
					 	<option ${csoAddTimeTimeType=="%H,HH点"?"selected":""} value="%H,HH点">
					 	时份(添加时间)</option>
				 		
				 	</select>
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csoStatusGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csoStatus"/>状态
				 	&nbsp;&nbsp;
				</li>
				<div class="clear"></div>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="line"></div>
		
					
		<div class="sum condition">
			<strong>统计类型：</strong>
			<ul>
				<li>
					<input ${countSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="count"/>
					数量&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoDurationSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoDuration"/>
					订单时长&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoFuelSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoFuel"/>
					燃油里程&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoElectricSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoElectric"/>
					电里程&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoFreehourSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoFreehour"/>
					免费小时&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoPredictSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoPredict"/>
					预计/里程费&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoPayNeedSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoPayNeed"/>
					应付金额&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoPayRealSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoPayReal"/>
					实付金额&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoPayMoneySum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoPayMoney"/>
					保证金支付&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoPayCouponSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoPayCoupon"/>
					现金券支付&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoPayCoinSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoPayCoin"/>
					红包支付&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoPayKilomSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoPayKilom"/>
					电里程费&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoPayMileageSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoPayMileage"/>
					油里程费&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoPayRentSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoPayRent"/>
					租金费用&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoPayInsureSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoPayInsure"/>
					免责费用&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoPayTimeoutSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoPayTimeout"/>
					超时费用&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csoPayDriverSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csoPayDriver"/>
					陪驾费用&nbsp;&nbsp;
				</li>
		
<!------------------------LAZY3Q_SUM_DEFINE------------------------>
<!------------------------LAZY3Q_SUM_DEFINE------------------------>

				<div class="clear"></div>
			</ul>
			<div class="clear"></div>
		</div>
		
		
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
})
</script>
</s:if>
	
</td></tr><tr><td>
	  			
	<div class="content">
	
		<s:if test="#request.groupCount==0 && #request.sumCount==0">
		<div style="padding:10px;line-height:32px;font-size:14px;">
			<img width="25" height="25" align="absmiddle" src="${basePath}admin/images/icons/20088256421529677807.png" />
			&nbsp;&nbsp;<font size="3">[统计说明]：</font><br/>
			1：先根据查询条件筛选出您想要的数据，然后选择下面不同的分组类型和统计类型，查询出N种不同类型的统计数据<br/>
			2：统计分组指的是根据不同的类型把查出的数据分成不的组分别进行统计,统计类型同时最多只能选择三项（当选择三项分组时，系统将使用这三项进行交叉分组统计）<br/>
			3：统计类型指的是要统计哪些内容,当统计分组和统计类型都只选择一项时，数据可以通过表格、饼图、柱状图、曲线图...等多种方式展示数据<br/>
			4：关于时间分组：<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			每年,每月,每周,每日,每时表示每个时间单元不重复，比如两年的每日会分成365*2=730个统计单位<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			月份,周份,星期,天份,时份表未每个时间单元重复分组，比如你查询再久的时间范围，按月份永远只分成12组，按星期的话永远只分成7组.....
		</div>
		</s:if>
		<s:else>
		<div class='viewtypes'>
			
		</div>
		<div id="stats" class="stats"></div>
		<script>
		var stats = {
			groupCount:${groupCount+0},
			sumCount:${sumCount+0},
			groupBy:${lz:or(lz:json(groupBy),"[]")},
			sumBy:[<s:iterator value="#request.sumBy" id="item" status="i">${i.count>1?",":""}"${lz:split(item,":")[0]}"</s:iterator>],
			haveTimeType:${haveTimeType==true},
			timeTypes:${lz:or(lz:json(timeTypes),"[]")},
			viewType:"${lz:or(viewType,"table")}"			
		};
		stats.structs={
		 	  "count":"总数"
			  ,"csoFeeType":"计费类型"
			  ,"csoArea":"网点区域"
			  ,"csoOutlets":"取车网点"
			  ,"csoOutletsRet":"还车网点"
			  ,"csoModel":"预订车型"
			  ,"csoCar":"预订车辆"
			  ,"csoProvid":"车辆商家"
			  ,"csoIsAdCar":"宣传车？"
			  ,"csoLongOrder":"是否长单"
			  ,"csoDuration":"订单时长"
			  ,"csoFuel":"燃油里程"
			  ,"csoElectric":"电里程"
			  ,"csoInsureType":"保险类型"
			  ,"csoFreehour":"免费小时"
			  ,"csoPredict":"预计/里程费"
			  ,"csoPayNeed":"应付金额"
			  ,"csoPayReal":"实付金额"
			  ,"csoPayMoney":"保证金支付"
			  ,"csoPayCoupon":"现金券支付"
			  ,"csoPayCoin":"红包支付"
			  ,"csoPayKilom":"电里程费"
			  ,"csoPayMileage":"油里程费"
			  ,"csoPayRent":"租金费用"
			  ,"csoPayInsure":"免责费用"
			  ,"csoPayTimeout":"超时费用"
			  ,"csoPayDriver":"陪驾费用"
			  ,"csoFlag":"类型标识"
			  ,"csoFrom":"订单来源"
			  ,"csoAddTime":"添加时间"
			  ,"csoStatus":"状态"
			  <s:iterator value="#request.sumBy" id="sumName" status="i">
			  ${lz:set("array",lz:split(sumName,":"))}${lz:set("arrayCount",lz:size(array))}
			  <s:if test="#request.arrayCount==3">,"${array[0]}":"${array[2]}"</s:if>
			  </s:iterator>
		}
		stats.datas=[ 
		 	  <s:iterator value="#request.list" id="item" status="i">${i.count>1?",":""}{"count":"${item.count}"
			  <lz:set name="itemValue">
					 	 ${get:CsUserType(item.csoFeeType).csutName}			 	 
		  	 </lz:set>
		  	 ,"csoFeeType":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
					 	 ${get:CsArea(item.csoArea).csaName}			 	 
		  	 </lz:set>
		  	 ,"csoArea":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
					 	 ${get:CsOutlets(item.csoOutlets).csoName}			 	 
		  	 </lz:set>
		  	 ,"csoOutlets":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
					 	 ${get:CsOutlets(item.csoOutletsRet).csoName}			 	 
		  	 </lz:set>
		  	 ,"csoOutletsRet":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
					 	 ${get:CsCarModel(item.csoModel).cscmName}			 	 
		  	 </lz:set>
		  	 ,"csoModel":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
					 	 ${get:CsCar(item.csoCar).cscNumber}			 	 
		  	 </lz:set>
		  	 ,"csoCar":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
					 	 ${get:CsProvid(item.csoProvid).cspName}			 	 
		  	 </lz:set>
		  	 ,"csoProvid":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
					 	 ${item.csoIsAdCar==true?"是":"否"}						 	
		  	 </lz:set>
		  	 ,"csoIsAdCar":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
					 	 ${item.csoLongOrder==true?"是":"否"}						 	
		  	 </lz:set>
		  	 ,"csoLongOrder":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
					 	 ${get:CsProduct(item.csoInsureType).cspName}			 	 
		  	 </lz:set>
		  	 ,"csoInsureType":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
						 ${item.csoFlag==0?"默认":""}
						 ${item.csoFlag==1?"2014春节订单":""}
						 ${item.csoFlag==2?"2015春节订单":""}
		  	 </lz:set>
		  	 ,"csoFlag":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
						 ${item.csoFrom==0?"网站预订":""}
						 ${item.csoFrom==1?"后台预订":""}
						 ${item.csoFrom==2?"微信预订":""}
						 ${item.csoFrom==3?"App预订":""}
						 ${item.csoFrom==4?"企业预订":""}
						 ${item.csoFrom==5?"支付宝预订":""}
		  	 </lz:set>
		  	 ,"csoFrom":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
					 	 ${lz:date(item.csoAddTime,lz:split(csoAddTimeTimeType,",")[1])}		 	 
		  	 </lz:set>
		  	 ,"csoAddTime":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
						 ${item.csoStatus==0?"已预定":""}
						 ${item.csoStatus==1?"使用中":""}
						 ${item.csoStatus==2?"已还车":""}
						 ${item.csoStatus==3?"已取消":""}
						 ${item.csoStatus==4?"已完成":""}
						 ${item.csoStatus==5?"待处理":""}
						 ${item.csoStatus==6?"已退款":""}
						 ${item.csoStatus==7?"已作废":""}
		  	 </lz:set>
		  	 ,"csoStatus":"${lz:js(lz:trim(itemValue))}"
		  	 ,"csoDuration":"${lz:digit(item.csoDuration,2)}"
		  	 ,"csoFuel":"${lz:digit(item.csoFuel,2)}"
		  	 ,"csoElectric":"${lz:digit(item.csoElectric,2)}"
		  	 ,"csoFreehour":"${lz:digit(item.csoFreehour,2)}"
		  	 ,"csoPredict":"${lz:digit(item.csoPredict,2)}"
		  	 ,"csoPayNeed":"${lz:digit(item.csoPayNeed,2)}"
		  	 ,"csoPayReal":"${lz:digit(item.csoPayReal,2)}"
		  	 ,"csoPayMoney":"${lz:digit(item.csoPayMoney,2)}"
		  	 ,"csoPayCoupon":"${lz:digit(item.csoPayCoupon,2)}"
		  	 ,"csoPayCoin":"${lz:digit(item.csoPayCoin,2)}"
		  	 ,"csoPayKilom":"${lz:digit(item.csoPayKilom,2)}"
		  	 ,"csoPayMileage":"${lz:digit(item.csoPayMileage,2)}"
		  	 ,"csoPayRent":"${lz:digit(item.csoPayRent,2)}"
		  	 ,"csoPayInsure":"${lz:digit(item.csoPayInsure,2)}"
		  	 ,"csoPayTimeout":"${lz:digit(item.csoPayTimeout,2)}"
		  	 ,"csoPayDriver":"${lz:digit(item.csoPayDriver,2)}"
			  <s:iterator value="#request.sumBy" id="sumName" status="i">
			  ${lz:set("array",lz:split(sumName,":"))}${lz:set("arrayCount",lz:size(array))}
			  <s:if test="#request.arrayCount==3">,"${array[0]}":"${item[array[0]]}"</s:if>
			  </s:iterator>
			 }</s:iterator>
		 ]
		 </script>
		 </s:else>
		 
	</div>
  
  
 </td></tr></table>

<script>
	
	showTips("${tips.value}");

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

</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>