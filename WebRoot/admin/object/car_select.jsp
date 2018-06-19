<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>车辆选择</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
</head>  

<style>
#car_table{margin-top:10px;border-collapase:collapse;border-spacing:0;}/*box-shadow:0px 0px 8px #dddddd;*/
#car_table td{ height:36px; line-height:36px;color:#000000;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
#car_table tbody td{text-align:center;}
#car_table .data td{font-weight:bold;width:16.66%;}
#car_table .linebar
.timeline{height:40px;overflow:hidden;position:relative;margin:0px 5px;}
.timeline .lines{position:relative;background:#ededed;border-color:#ccc #fff #fff #ccc;border-width:1px 1px 1px 1px;border-style:solid;position:relative;height:10px;}
.timeline .order,.timeline .restr{position:absolute;top:0px;height:10px;border-radius:20px;}
.timeline .order{background:url(${basePath}admin/images/timeline.png) 0px -1px;z-index:1;cursor:pointer;}
.timeline .restr{background:url(${basePath}admin/images/timeline.png) 0px 11px;z-index:2;}
.timeline .days{position:relative;position:relative;}
.timeline .day{position:absolute;top:2px;height:15px;text-indent:0px;pading:0px;line-height:15px;text-align:center;color:#666666;border-right:1px solid #000000;}
.colspan td{}
</style>

<body style="<s:if test="#request.select!=false">min-height:500px;</s:if>">
<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td>
	<!-- <a style="float:right;margin:2px 10px;" onClick="$.closeModalDialog();" href="javascript:void(0);">取消选择</a> -->
	<s:if test="#request.select!=false">
	<div style="width:1000px;height:20px;"></div>
	</s:if>
	
	<form class="query" id="queryForm" action="${basePath}admin/object/car_select.do" method="post">
		<div class="plate" style="margin-top:10px;border-top:1px;">
		<table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td>车牌号:</td>
		    <td><input style="width:80px;" type="text" class="input" size="16"  maxlength="32" name="cscNumber" id="cscNumber"  value="${cscNumber}"/></td>
		  	<td>城市:</td>
		    <td>
				<select style="width:100px;" class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscHost" name="cscHost">
	 				<option selected value="${cscHost}">
	 					${get:SrvHost(cscHost).shName}
	 				</option>
		 		</select>
			</td>
			<td>车型:</td>
		    <td><select style="width:100px;" class="combox"  action="${basePath}admin/object/carmodel_query.do?size=-1&cscmStatus=1" id="cscModel" name="cscModel">
	 				<option selected value="${cscModel}">
	 					${lz:get('ccclubs_system','cs_car_model',cscModel).cscm_name}
	 				</option>
	 		</select>
	 		<a class="lookup" href="javascript:void(0);" onClick="if($.trim($('#cscModel').val())==''){return;};window.href('${basePath}admin/object/carmodel_details.do?id='+$('#cscModel').val(),{ctrl:{editable:false}})"></a></td>
		    <td>网点:</td>
		    <td><select style="width:100px;" class="combox"  action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={cscHost}&csoHost={cscHost}" id="cscOutlets" name="cscOutlets">
	 				<option selected value="${cscOutlets}">
	 					${lz:get('ccclubs_system','cs_outlets',cscOutlets).cso_name}
	 				</option>
	 		</select>
	 		<a class="lookup" href="javascript:void(0);" onClick="if($.trim($('#cscOutlets').val())==''){return;};window.href('${basePath}admin/object/outlets_details.do?id='+$('#cscOutlets').val(),{ctrl:{editable:false}})"></a></td>
		    <td>
		    	日期范围:
		    	<input style="width:80px;" onChange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="begin" id="begin"  value="<s:date name="#request.begin" format="yyyy-MM-dd"/>"/>
		    	至
		    	<input style="width:80px;" onChange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="end" id="end"  value="<s:date name="#request.end" format="yyyy-MM-dd"/>"/>
		    </td>
		    <td><button class='button' type='submit'>&nbsp;查&nbsp;&nbsp;询&nbsp;</button></td>
		  </tr>
		</table>	
		</div>
		<input type="hidden" name="size" value="8"/>
		<s:if test="#request.select!=false"></s:if>
		<s:else>
			<input type="hidden" name="select" value="${select}"/>
		</s:else>
	</form>
	
	<div class="content">
		
		<table id="car_table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<!-- <thead class="state-header">
		  <tr>
			 <td width="140" title="车牌号">
			 	<a class="${desc=="csc_number" ? "desc" : ""}${asc=="csc_number" ? "asc" : ""}" href="?${desc=="csc_number" ? "asc=csc_number" : ""}${(asc=="csc_number" || desc!="csc_number" )? "desc=csc_number" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车牌号
			 	</a>			 	
			 </td> 
			 <td  width="120" title="车型">
			 	<a class="${desc=="csc_model" ? "desc" : ""}${asc=="csc_model" ? "asc" : ""}" href="?${desc=="csc_model" ? "asc=csc_model" : ""}${(asc=="csc_model" || desc!="csc_model" )? "desc=csc_model" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车型
			 	</a>
			 </td> 
			 <td  width="120" title="网点">
			 	<a class="${desc=="csc_outlets" ? "desc" : ""}${asc=="csc_outlets" ? "asc" : ""}" href="?${desc=="csc_outlets" ? "asc=csc_outlets" : ""}${(asc=="csc_outlets" || desc!="csc_outlets" )? "desc=csc_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	网点
			 	</a>
			 </td>
			 <td  width="120" title="供应商">
			 	<a class="${desc=="csc_provid" ? "desc" : ""}${asc=="csc_provid" ? "asc" : ""}" href="?${desc=="csc_provid" ? "asc=csc_provid" : ""}${(asc=="csc_provid" || desc!="csc_provid" )? "desc=csc_provid" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	供应商
			 	</a>
			 </td>
			 <td  width="120" title="状态">
			 	<a class="${desc=="csc_status" ? "desc" : ""}${asc=="csc_status" ? "asc" : ""}" href="?${desc=="csc_status" ? "asc=csc_status" : ""}${(asc=="csc_status" || desc!="csc_status" )? "desc=csc_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 </td>
			<td class='last' width="110">&nbsp;</td>
		  </tr>
		 </thead>	 -->
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr class="colspan"><td colspan="6" style="height:12px;line-height:0px;overflow:hidden;"></td></tr>
		  <tr class="data" id="${item.cscId}">
			<td ref="cscNumber" class=" node" style="font-size:14px; font-weight:bold; color:#ff8800;">
				${item.cscNumber}${item.cscIsAd?"(广告车)":""}
			</td>
			<td ref="cscModel" class="" title="${item.cscModel}">
				 ${item.cscModel$}
			</td>
			<td ref="cscOutlets" class="" title="${item.cscOutlets}">
				${item.cscOutlets$}
			</td>
			<td ref="cscProvid" class="" title="${item.cscProvid}">
				${item.cscProvid$}
			</td>			
			<td ref="cscStatus" class="">				
				 ${item.cscStatus==1?"可租用":""}
				 ${item.cscStatus==2?"不可用":""}
				 ${item.cscStatus==0?"无效":""}
			</td>			
			<td>
				<s:if test="#request.select!=false">
				<a onClick="caller.onSelectCar(${item.cscId},'${item.cscNumber}',${item.cscOutlets},'${item.cscOutlets$}');$.closeModalDialog();" href="javascript:void(0)">选择此车</a>
				</s:if>
			</td>
		  </tr>
		  <tr class="linebar">
		  	<td colspan="6">
		  		<div class="timeline" >
		  			<div class="lines">
						<s:iterator value="#item.timelines[#request.day]" id="timeline" status="i">
							${lz:set("left",lz:digit((timeline.start.time-begin.time)*100/howlong,2))}
							${lz:set("right",lz:digit((timeline.finish.time-begin.time)*100/howlong,2))}
							<div onclick="window.href('${basePath}admin/service/order_details.do?id=${timeline.order}',{ctrl:{editable:false,visible:true}})" style="left:${left}%;width:${lz:digit(right-left,2)}%;" title="${lz:date(timeline.start,"MM-dd HH:mm")} 至 ${lz:date(timeline.finish,"MM-dd HH:mm")} 有单,点击查看订单" class="order">
							</div>
						</s:iterator>
						<s:iterator value="#item.values.restrs" id="restr" status="i">
							${lz:set("left",lz:digit((restr.start.time-begin.time)*100/howlong,2))}
							${lz:set("right",lz:digit((restr.finish.time-begin.time)*100/howlong,2))}
							<div style="left:${left}%;width:${lz:digit(right-left,2)}%;" title="${lz:date(restr.start,"MM-dd HH:mm")} 至 ${lz:date(restr.finish,"MM-dd HH:mm")} 限行" class="restr">
							</div>
						</s:iterator>
					</div>
					<div class="days">
						<s:iterator value="#request.dates" id="date" status="i">
							${lz:set("left",lz:digit((date.start.time-begin.time)*100/howlong,2))}
							${lz:set("right",lz:digit((date.finish.time-begin.time)*100/howlong,2))}
							<div style="left:${left}%;width:${lz:digit(right-left,2)}%;" title="${lz:date(date.start,"MM月dd日 E")}" class="day">
								<s:if test="#request.day>20">${lz:date(date.start,"MM.dd")}</s:if>
								<s:elseif test="#request.day>10">${lz:replace(lz:date(date.start,"MM.dd E"),"星期","")}</s:elseif>
								<s:else>${lz:date(date.start,"MM月dd日 E")}</s:else>
							</div>
						</s:iterator>
					</div>
				</div>
			</td>
		  </tr>
		 </s:iterator>
		 </tbody>			 
	  </table>
	  
  </div>
  <%@include file="/admin/module/page.jsp"%>
 
	<s:if test="#request.select!=false">
	<div style="width:1000px;height:20px;"></div>
	</s:if>
 
</td></tr></table> 

<script>
showTips("${tips.value}");
function href(url,params){
	top.$.showModalDialog({url:url},params,window);
}
<s:if test="#request.select!=false"></s:if>
<s:else>
$(function(){
	var scrollTop = $(parent).scrollTop();
	parent.$("#"+window.name).attr("height",100).css("height",100+"px");
	var height = $(document).height()+30;
	parent.$("#"+window.name).attr("height",height).css("height",height+"px");
	$(parent).scrollTop(scrollTop);
});
</s:else>
</script>


</body>
</html>
