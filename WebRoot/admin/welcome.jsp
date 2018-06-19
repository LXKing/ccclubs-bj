<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="get" uri="/get-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${lz:config("project_name")}</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}admin/js/define.js"></script>
	
	<script type="text/javascript" src="${basePath}admin/js/FusionCharts/JSClass/FusionCharts.js"></script>
		
</head>

<style>
#operations tbody td{height:35px;line-height:35px;}
.paneler .matter{height:250px;border:1px solid #ffffff;border-top:0px;}
</style>
<style>
.states{ font-size:12px;background:#ffffff;}
.states td{ background:#fafafa; color:#333333;line-height:22px;}
.states .red{color:red;font-family:Impact;font-size:16px;}
.states .blue{color:black;font-family:Impact;font-size:14px;color:#3ccf0f;}
.states .tite{ font-size:14px; font-weight:bold;color:black;}
.states .tite img{width:32px;height:32px;}
.states .cnte{padding:4px 20px;height:51px;}
.cnte a{font-size:12px; color:black;text-decoration:none;line-height:22px;margin:0px;padding:0px;}
.indexes{background:#ffffff;box-shadow:0px 0px 5px #aaaaaa;}
.indexes .scope{background:#fafafa;}
.indexes .scope a{font-size:32px; font-family:Impact; margin:5px; color:red; text-decoration:none;}
.indexes td{ background:#f2f2f2;}
.indexes strong{ font-size:14px;}
.table tbody .even{background:#fafafa;}
</style>

<body>


	<table style="table-layout:fixed;" width="100%" border="0" cellspacing="10" cellpadding="0">	  
	  <tr>
	    <td valign="top" >
			<div class="paneler" valign="top">
				<div class="titler">运营概况</div>
				<div class="matter fixed">
					<table id="operations" style="box-shadow:none;background:#ededed;" noresize="noresize" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
						<thead class="state-header">
							<tr>
								<td width="20%">类型\时间</td>
								<td width="20%">今天</td>
								<td width="20%">昨天</td>
								<td width="20%">本周</td>
								<td width="20%">本月</td>
							</tr>
						</thead>
						<tbody>
							${lz:set("items",operations.logins)}
							<tr>
								<td>登录数量</td>
								<td>${empty items.today?0:items.today}</td>
								<td>${empty items.yesterday?0:items.yesterday}</td>
								<td>${empty items.week?0:items.week}</td>
								<td>${empty items.month?0:items.month}</td>
							</tr>
							${lz:set("items",operations.regists)}
							<tr>
								<td>注册数量</td>
								<td>${empty items.today?0:items.today}</td>
								<td>${empty items.yesterday?0:items.yesterday}</td>
								<td>${empty items.week?0:items.week}</td>
								<td>${empty items.month?0:items.month}</td>
							</tr>
							${lz:set("items",operations.recharge)}
							<tr>
								<td>充值金额</td>
								<td>${empty items.today?0:items.today}</td>
								<td>${empty items.yesterday?0:items.yesterday}</td>
								<td>${empty items.week?0:items.week}</td>
								<td>${empty items.month?0:items.month}</td>
							</tr>
							${lz:set("items",operations.orders)}
							<tr>
								<td>下单数量</td>
								<td>${empty items.today?0:items.today}</td>
								<td>${empty items.yesterday?0:items.yesterday}</td>
								<td>${empty items.week?0:items.week}</td>
								<td>${empty items.month?0:items.month}</td>
							</tr>
							${lz:set("items",operations.order$)}
							<tr>
								<td>下单金额</td>
								<td>${lz:digit(empty items.today?0:items.today,2)}</td>
								<td>${lz:digit(empty items.yesterday?0:items.yesterday,2)}</td>
								<td>${lz:digit(empty items.week?0:items.week,2)}</td>
								<td>${lz:digit(empty items.month?0:items.month,2)}</td>
							</tr>
							${lz:set("items",operations.ordert)}
							<tr>
								<td>订单小时</td>
								<td>${empty items.today?0:items.today/3600}</td>
								<td>${empty items.yesterday?0:items.yesterday/3600}</td>
								<td>${empty items.week?0:items.week/3600}</td>
								<td>${empty items.month?0:items.month/3600}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</td>
		<td colspan="2">
			<div class="paneler" valign="top">
				<div class="titler">
					数据统计 (${lz:date(statistics.start,"yyyy年MM月")} 至 ${lz:date(statistics.end,"yyyy年MM月")})
				</div>
				<div class="matter fixed">
					<div id="chartdiv"></div>
				</div>
			</div>
		</td>		
	  </tr>
	  <!-- 10分钟更新一次 -->
	  <tr>
	  	<td colspan="2">
			<div class="paneler" valign="top">
				<div class="titler">数据概况</div>
				<div class="matter" style="height:300px; padding:10px;">
				<table class="states" width="100%" border="0" cellspacing="1" cellpadding="5">
					  <tr>
						<td style="border-top:0px;" class="tite" width="160" align="left" valign="middle"><img src="images/icons/20088256427828778017.png" width="48" height="48" hspace="20" align="absmiddle" />会员状态</td>
						<td style="border-top:0px; valign="top" class="cnte">
							当前共有<a class="blue" href="javascript:void(parent.href('${basePath}admin/user/member.do','会员管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_member where csm_host=? and csm_status!=-1",(get:host()))[0]["count"]}
							</a>个会员，
							审核通过<a class="blue" href="javascript:void(parent.href('${basePath}admin/user/member.do?csmHost=${get:host()}&csmVReal=1&csmVDrive=1','会员管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_member where csm_host=? and csm_v_real=1 and csm_v_drive=1 and csm_status!=-1",(get:host()))[0]["count"]}
							</a>个，
							待审核<a class="red" href="javascript:void(parent.href('${basePath}admin/user/member.do?csmHost=${get:host()}&csmVReal=2&csmVDrive=2','会员管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_member where csm_host=? and (csm_v_real=2 and csm_v_drive=2) and csm_status!=-1",(get:host()))[0]["count"]}
							</a>个，
							最近7天新注册会员<a class="blue" href="javascript:void(parent.href('${basePath}admin/user/member.do?csmHost=${get:host()}&csmAddTimeStart=${lz:date((lz:datexp("D{7}").start),"yyyy-MM-dd HH:mm:ss")}','会员管理'));">
								${get:query$$("ccclubs_system","select count(1) as count from cs_member where csm_host=? and csm_add_time>? and csm_status!=-1",(get:host()),(lz:datexp("D{7}").start))[0]["count"]}
							</a>个，
							最近7天新转化会员<a class="blue" href="javascript:void(parent.href('${basePath}admin/user/member.do?csmHost=${get:host()}&csmFirstRechargeStart=${lz:date((lz:datexp("D{7}").start),"yyyy-MM-dd HH:mm:ss")}','会员管理'));">
								${get:query$$("ccclubs_system","select count(1) as count from cs_member where csm_host=? and csm_first_recharge>? and csm_status!=-1",(get:host()),(lz:datexp("D{7}").start))[0]["count"]}
							</a>个，
							最近7天退款会员<a class="blue" href="javascript:void(parent.href('${basePath}admin/user/member.do?csmHost=${get:host()}&csmRefundTimeStart=${lz:date((lz:datexp("D{7}").start),"yyyy-MM-dd HH:mm:ss")}','会员管理'));">
								${get:query$$("ccclubs_system","select count(1) as count from cs_member where csm_host=? and csm_refund_time>? and csm_status!=-1",(get:host()),(lz:datexp("D{7}").start))[0]["count"]}
							</a>个
						</td>
					  </tr>
					  <tr>
						<td class="tite" align="left" valign="middle"><img src="images/icons/GPS.png" width="48" height="48" hspace="20" align="absmiddle" />网点状态</td>
						<td valign="top" class="cnte">
							当前共有<a class="blue" href="javascript:void(parent.href('${basePath}admin/object/outlets.do?csoHost=${get:host()}','网点管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_outlets where cso_host=? and cso_status!=-1",(get:host()))[0]["count"]}
							</a>个网点，
							预设网点<a class="blue" href="javascript:void(parent.href('${basePath}admin/object/outlets.do?csoHost=${get:host()}&csoStatus=0','网点管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_outlets where cso_host=? and cso_status=0",(get:host()))[0]["count"]}
							</a>个，
							其中可预定<a class="blue" href="javascript:void(parent.href('${basePath}admin/object/outlets.do?csoHost=${get:host()}&csoCanOrder=1','网点管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_outlets where cso_host=? and cso_can_order=1 and cso_status!=-1",(get:host()))[0]["count"]}
							</a>个，
							可注册<a class="blue" href="javascript:void(parent.href('${basePath}admin/object/outlets.do?csoHost=${get:host()}&csoCanReg=1','网点管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_outlets where cso_host=? and cso_can_reg=1 and cso_status!=-1",(get:host()))[0]["count"]}
							</a>个，
							7天内交费到期<a class="red" href="javascript:void(parent.href('${basePath}admin/object/outlets.do?csoHost=${get:host()}&csoUntilEnd=${lz:date((lz:datexp("D{-7}").start),"yyyy-MM-dd HH:mm:ss")}','网点管理'));">
								${get:query$$("ccclubs_system","select count(1) as count from cs_outlets where cso_host=? and cso_until<? and cso_status!=-1",(get:host()),(lz:datexp("D{-7}").start))[0]["count"]}
							</a>个，
							7天内合约到期<a class="red" href="javascript:void(parent.href('${basePath}admin/object/outlets.do?csoHost=${get:host()}&csoExpireEnd=${lz:date((lz:datexp("D{-7}").start),"yyyy-MM-dd HH:mm:ss")}','网点管理'));">
								${get:query$$("ccclubs_system","select count(1) as count from cs_outlets where cso_host=? and cso_expire<? and cso_status!=-1",(get:host()),(lz:datexp("D{-7}").start))[0]["count"]}
							</a>个
						</td>
					  </tr>
					  <tr>
						<td class="tite" align="left" valign="middle"><img src="images/icons/200882564229234778010.png" width="48" height="48" hspace="20" align="absmiddle" />车辆状态</td>
						<td valign="top" class="cnte">
							当前共有<a class="blue" href="javascript:void(parent.href('${basePath}admin/object/car.do?cscHost=${get:host()}','车辆管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_car where csc_host=? and 1=1 and csc_status!=-1",(get:host()))[0]["count"]}
							</a>辆车，
							其中在线运营<a class="blue" href="javascript:void(parent.href('${basePath}admin/object/car.do?cscHost=${get:host()}&cscStatus=1','车辆管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_car where csc_host=? and csc_status=1",(get:host()))[0]["count"]}
							</a>辆，
							在保养<a class="blue" href="javascript:void(parent.href('${basePath}admin/service/maintain.do?csmHost=${get:host()}&csmStatus=2','保养管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_maintain where csm_host=? and csm_status=2",(get:host()))[0]["count"]}
							</a>辆，
							在维修<a class="blue" href="javascript:void(parent.href('${basePath}admin/service/repair.do?csrHost=${get:host()}&csrStatus=0','维修管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_repair where csr_host=? and csr_status=0",(get:host()))[0]["count"]}
							</a>辆，
							交强险7天内到期<a class="red" href="javascript:void(parent.href('${basePath}admin/object/car.do?cscHost=${get:host()}&cscTiUnitEnd=${lz:date((lz:datexp("D{-7}").start),"yyyy-MM-dd HH:mm:ss")}','车辆管理'));">
								${get:query$$("ccclubs_system","select count(1) as count from cs_car where csc_host=? and csc_ti_unit<? and csc_status!=-1",(get:host()),(lz:datexp("D{-7}").start))[0]["count"]}
							</a>辆，
							商业险7天内到期<a class="red" href="javascript:void(parent.href('${basePath}admin/object/car.do?cscHost=${get:host()}&cscBiUnitEnd=${lz:date((lz:datexp("D{-7}").start),"yyyy-MM-dd HH:mm:ss")}','车辆管理'));">
								${get:query$$("ccclubs_system","select count(1) as count from cs_car where csc_host=? and csc_bi_unit<? and csc_status!=-1",(get:host()),(lz:datexp("D{-7}").start))[0]["count"]}
							</a>辆，
						</td>
					  </tr>
					  <tr>
						<td class="tite" align="left" valign="middle"><img src="images/icons/200882564216312778012.png" width="48" height="48" hspace="20" align="absmiddle" />订单状态</td>
						<td valign="top" class="cnte">
							当前订单概述：
							预定中<a class="blue" href="javascript:void(parent.href('${basePath}admin/service/order.do?csoHost=${get:host()}&csoStatus=0','订单管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_order where cso_host=? and cso_status=0",(get:host()))[0]["count"]}
							</a>个，
							使用中<a class="blue" href="javascript:void(parent.href('${basePath}admin/service/order.do?csoHost=${get:host()}&csoStatus=1','订单管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_order where cso_host=? and cso_status=1",(get:host()))[0]["count"]}
							</a>个，
							待处理<a class="red" href="javascript:void(parent.href('${basePath}admin/service/order.do?csoHost=${get:host()}&csoStatus=5','订单管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_order where cso_host=? and cso_status=5",(get:host()))[0]["count"]}
							</a>个，
							未付款<a class="red" href="javascript:void(parent.href('${basePath}admin/service/order.do?csoHost=${get:host()}&csoCreditBill$on=true&csoCreditBill$cscbStatus=0','订单管理'));">
								${get:query$("ccclubs_system","select count(*) as count from cs_credit_bill left JOIN cs_order on cso_id = cscb_order where cscb_status=0 and cso_host=?",(get:host()))[0]["count"]}
							</a>个
						</td>
					  </tr>
					  <tr>
					    <td class="tite" style="border-bottom:0px;" class="tite" align="left" valign="middle"><img src="images/icons/20088256421529677801.png" width="48" height="48" hspace="20" align="absmiddle" />运维状态</td>
					    <td style="border-bottom:0px;" valign="top" class="cnte">
							当前共有<a class="red" href="javascript:void(parent.href('${basePath}admin/service/trouble.do?cstHost=${get:host()}&cstStatus=6&cstStatusYesNot=not','事故管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_trouble where cst_host=? and cst_status<6 and cst_status!=-1",(get:host()))[0]["count"]}
							</a>个车辆事故，
							<a class="red" href="javascript:void(parent.href('${basePath}admin/service/violat.do?csvHost=${get:host()}&csvStatus=0','违章管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_violat where csv_host=? and csv_status=0 and csv_status!=-1",(get:host()))[0]["count"]}
							</a>个车辆违章，
							三天内截止处理的违章<a class="red" href="javascript:void(parent.href('${basePath}admin/service/violat.do?csvHost=${get:host()}&csvStatus=0&csvCutoffTimeEnd=${lz:date((lz:datexp("D{-3}").start),"yyyy-MM-dd HH:mm:ss")}','违章管理'));">
								${get:query$$("ccclubs_system","select count(1) as count from cs_violat where csv_host=? and csv_status=0 and csv_cutoff_time<?",(get:host()),(lz:datexp("D{-3}").start))[0]["count"]}
							</a>个，
							当前<a class="red" href="javascript:void(parent.href('${basePath}admin/service/maintain.do?csmHost=${get:host()}&csmStatus=1','保养管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_maintain where csm_host=? and csm_status=1",(get:host()))[0]["count"]}
							</a>辆车保养报警，
							近两天退款待审核<a class="red" href="javascript:void(parent.href('${basePath}admin/finance/refund.do?csrHost=${get:host()}&csrStatus=0&csrBackTimeEnd=${lz:date((lz:datexp("D{-2}").start),"yyyy-MM-dd HH:mm:ss")}','退款管理'));">
								${get:query$$("ccclubs_system","select count(1) as count from cs_refund where csr_host=? and csr_back_time<? and csr_status=0",(get:host()),(lz:datexp("D{-2}").start))[0]["count"]}
							</a>个，
							当前等待退款<a class="red" href="javascript:void(parent.href('${basePath}admin/finance/refund.do?csrHost=${get:host()}&csrStatus=1','退款管理'));">
								${get:query$("ccclubs_system","select count(1) as count from cs_refund where csr_host=? and csr_status=1",(get:host()))[0]["count"]}
							</a>个
						</td>
				      </tr>
		  		</table>
				</div>
			</div>
		</td>		
	    <td valign="top">
			<div class="paneler" valign="top">
				<div class="titler">最近三个月各平台注册概况</div>
				<div class="matter" style="height:320px; padding:0px;">
					<div style="height:32px;">&nbsp;</div>
					<div style="height:250px;" id="registFrom">
						<textarea id='registFromXml'>
						<chart showValues='0' decimals='0' formatNumberScale='0' bgColor='f2f2f2'>
						${lz:set("froms",(get:query$$("ccclubs_system","select count(1) as count,csm_from from cs_member where csm_host=? and csm_add_time>? group by csm_from",get:host(),(lz:datexp("M{6}").start))))}
						<s:iterator value="#request.froms" id="item" status="i">
						 	<lz:set name="itemValue">
							 ${item.csmFrom==0?"后台":""}
							 ${item.csmFrom==1?"网站":""}
							 ${item.csmFrom==2?"微信":""}
							 ${item.csmFrom==3?"APP":""}
							 ${item.csmFrom==4?"微生活":""}
					  	 	</lz:set>
					  	 	<s:if test="#item.csmFrom>0 && #item.csmFrom<5">
							<set label='${lz:js(lz:trim(itemValue))}(${item.count})' value='${item.count}' />
							</s:if>
					    </s:iterator>
					    </chart>
					    </textarea>
					</div>					
				</div>
			</div>
		</td>
	  </tr> 
	  <tr>
			<td>
				${lz:set("count",(get:query$("ccclubs_system","select count(1) as count from cs_channel where csc_host=? and csc_status=1",(get:host()))[0]["count"]))}
				<table class="indexes" width="100%" border="0" cellspacing="1" cellpadding="10">
				  <tr>
					<td class="scope" width="60" height="71" align="center"><a title="计算公式：n/√(n^2+100)" href="#">${lz:parseInt(lz:triangle(10,count)*100)}</a>分</td>
					<td valign="top"><strong>品牌推广</strong><br />
					  当前共${count}个推广渠道在合作</td>
				  </tr>
				</table>
			</td>
			<td>
				${lz:set("count",(get:query$("ccclubs_system","select count(1) as count from cs_market_plan where csmp_host=? and csmp_status=1",(get:host()))[0]["count"]))}
				<table class="indexes" width="100%" border="0" cellspacing="1" cellpadding="10">
				  <tr>
					<td class="scope" width="60" height="71" align="center"><a title="计算公式：n/√(n^2+100)" href="#">${lz:parseInt(lz:triangle(10,count)*100)}</a>分</td>
					<td valign="top"><strong>运营营销</strong><br />
				       当前共${count}个运营方案在执行</td>
				  </tr>
				</table>
			</td>
			<td>
				${lz:set("count",(get:query$("ccclubs_system","select count(1) as count from cs_sale_event where csse_host=? and csse_status=1",(get:host()))[0]["count"]))}
				<table class="indexes" width="100%" border="0" cellspacing="1" cellpadding="10">
				  <tr>
					<td class="scope" width="60" height="72" align="center"><a title="计算公式：n/√(n^2+100)" href="#">${lz:parseInt(lz:triangle(10,count)*100)}</a>分</td>
					<td valign="top"><strong>销售活动</strong><br/>
					  当前共${count}个销售活动在执行 
					</td>
				  </tr>
				</table>
			</td>
	  </tr>
	  <tr>
			<td width="33%"></td>
			<td width="34%"></td> 
			<td width="33%"></td>
	  </tr>
	</table>
	
<script>
$(function(){
	var chart = new FusionCharts($.contextPath()+"js/FusionCharts/Charts/Column3D.swf?mm="+Math.random(),"registFrom", $("#registFrom").width(), "280", "0", "0");
   	chart.setTransparent(true);
   	chart.setDataXML($("#registFromXml").val());
   	chart.render("registFrom");
});
</script>

<iframe id="car_frame" name="car_frame" src="${basePath}admin/object/car_select.do?size=8&select=false" frameborder="0" width="100%" height="700" style="height:700px;"></iframe>	
	
<%@include file="monitor_chart.jsp" %>
<script>
$(function(){
	var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/MSColumnLine3D.swf","ChartId", $("#chartdiv").width(), "248", "0", "0");
   	chart.setTransparent(true);
   	chart.setDataXML(chartXML);		   
   	chart.render("chartdiv");	
});
</script>
	

</body>
</html>
