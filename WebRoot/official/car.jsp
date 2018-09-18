<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${lz:config("project_name")}</title> 
</head>

	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>

<body>

<style>
.timeline{height:40px;overflow:hidden;position:relative;margin:2px;}
.timeline .lines{position:relative;background:none;background:#ffffff;border:1px solid;position:relative;height:10px;overflow:hidden;}
.timeline .order,.timeline .restr{position:absolute;top:0px;height:10px;}
.timeline .order{background:red;z-index:1;cursor:pointer;}
.timeline .restr{background:blue;z-index:2;}
.timeline .days{position:relative;position:relative;}
.timeline .day{position:absolute;top:2px;height:15px;text-indent:0px;pading:0px;line-height:15px;text-align:center;border-right:1px solid #000000;}
</style>




<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td>


<div class="title">
	<a href="javascript:void(0);" class="setting imize"></a>
	<a ref=".plate" href="javascript:void(0);" class="maximize imize"></a>
	<a ref=".plate" href="javascript:void(0);" class="minimize imize"></a>
	车辆管理	
</div>
<div class="plate">
	<form class="query" id="queryForm" action="${basePath}official/car.do" method="post">
		<table align="center" width="90%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td>车牌号:</td>
		    <td><input type="text" class="input" size="16"  maxlength="32" name="cscNumber" id="cscNumber"  value="${cscNumber}"/></td>
		    <td>车型:</td>
		    <td><select class="combox"  action="${basePath}official/object/carmodel_query.do?size=-1" id="cscModel" name="cscModel">
	 				<option selected value="${cscModel}">
	 					${lz:get('ccclubs_system','cs_car_model',cscModel).cscm_name}
	 				</option>
	 		</select>
	 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscModel').val())==''){return;};window.href('${basePath}admin/object/carmodel_details.do?id='+$('#cscModel').val(),{controller:{editable:false}})"></a></td>
		    <td>网点:</td>
		    <td><select class="combox"  action="${basePath}official/object/outlets_tree.do?parent={param}" id="cscOutlets" name="cscOutlets">
	 				<option selected value="${cscOutlets}">
	 					${lz:get('ccclubs_system','cs_outlets',cscOutlets).cso_name}
	 				</option>
	 		</select>
	 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscOutlets').val())==''){return;};window.href('${basePath}admin/object/outlets_details.do?id='+$('#cscOutlets').val(),{controller:{editable:false}})"></a></td>
		    <td>
		    	日期范围:
		    	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="begin" id="begin"  value="<s:date name="#request.begin" format="yyyy-MM-dd"/>"/>
		    	至
		    	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="end" id="end"  value="<s:date name="#request.end" format="yyyy-MM-dd"/>"/>
		    </td>
		    <td><button class='button' type='submit'>&nbsp;查&nbsp;&nbsp;询&nbsp;</button></td>
		  </tr>
		</table>
		<input type="hidden" name="size" value="8"/>
		<s:if test="#request.select!=false"></s:if>
		<s:else>
			<input type="hidden" name="select" value="${select}"/>
		</s:else>
	</form>
</div>
<div class="line" style="width:1000px;margin:10px auto;"></div>
<div class="content">
	<table class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  	<tr>
		  	 	<td align="center">车牌号</td>
		  	 	<td align="center">车型</td>
		  	 	<td align="center">所在网点</td>
		  	 	<td align="center">车辆状态</td>
		  	 	<td align="center">操作</td>
		  	</tr>
		</thead>
		<tbody>
		<s:iterator value="#request.cars" id="item" status="i">
			<tr>
		  	 	<td align="center">${item.cscNumber}</td>
		  	 	<td align="center">${item.cscModel$}</td>
		  	 	<td align="center">${item.cscOutlets$}</td>
		  	 	<td align="center">
		  	 		${item.cscStatus$}
				</td>
		  	 	<td align="center">
		  	 		<s:if test="#request.select=='true'">
						<a onclick="caller.onSelectCar(${item.cscId},'${item.cscNumber}');$.closeModalDialog();" href="javascript:void(0)">下单</a>
					</s:if>
					<s:else>
						<a href="javascript:addUnitOrder(${item.cscId})">
			  	 			<img width="16" align="absmiddle" src="${basePath}admin/images/icons/200882564229234778010.png">
			  	 			公务用车
			  	 		</a>
		  	 			<lz:set name=""></lz:set>
		  	 				<%-- <a href="javascript:remote(${item.cscId},0)">远程开门</a>
		  	 				<a href="javascript:remote(${item.cscId},1)">远程关门</a>	 --%>
		  	 					
		  	 		</s:else>
		  	 	</td>
		  	</tr>
		  	<tr>
		  	 	<td align="center" colspan="5">
		  	 		<div>
						<div class="timeline" >
				  			<div class="lines">
								<s:iterator value="#item.values.orders" id="order" status="i">
									${lz:set("left",lz:digit((order.csoStartTime.time-begin.time)*100/howlong,2))}
									${lz:set("right",lz:digit((order.csoFinishTime.time-begin.time)*100/howlong,2))}
									<div style="left:${left}%;width:${lz:digit(right-left,2)}%;" title="${lz:date(order.csoStartTime,"MM-dd HH:mm")} 至 ${lz:date(order.csoFinishTime,"MM-dd HH:mm")} 有单,点击查看订单" class="order">
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
					</div>
				</td>
		  	</tr>
		</s:iterator>
		</tbody>
	</table>	
</div>

<%@include file="/admin/module/page.jsp"%>
  
</td></tr></table>

<div style="width:1000px;margin:10px auto;"></div>

<script>
	
	top.showTips("${tips.value}");

	function href(url,params){
		if(${controller.mutual=="dialog"})
			top.$.showModalDialog({url:url},params,window);
		else if(${controller.mutual=="page"})
			$.submit(url,params,"_self");
		else if(true)
			top.$.showModalDialog({url:url},params,window);
		else
			$.submit(url,params,"_self");	
	}
	
	<lz:set name=""></lz:set>
	//远程控制，type,0：开门,1:关门
	function remote(id,type){
		$.tips($.getObject("car.remote.do",{id:id,type:type}).message);
	}
	
	
	
	function addProxyOrder(id){
		var url = "${basePath}unit/order.edit.do";
		var params = {entrypoint:"${entrypoint}","csUnitOrder.csuoCar":id,controller:{title:"代理订单"}};
		href(url,params);	
	}
	function addUnitOrder(id){
		var url = "${basePath}official/order.add.do";
		var params = {entrypoint:"${entrypoint}","csUnitOrder.csuoCar":id,controller:{title:"公务用车"}};
		href(url,params);
	}
	
	
	<lz:set name="statusHtmlTemplage">
		<select id="status" name="status">
			<option value="3">&nbsp;可租用&nbsp;&nbsp;&nbsp;&nbsp;</option>
			<option value="2">&nbsp;不可租&nbsp;&nbsp;&nbsp;&nbsp;</option>
		</select>
	</lz:set>
	<lz:set name="statusJsTemplage">
	function updateCarStatus(id,number){
		var select=top.$("${lz:js(statusHtmlTemplage)}");
		select.dialog({
			title:"修改车辆["+number+"]状态",
			width:250,
			modal:true,
			onOk:function(){
				window.location="car.status.do?id="+id+"&status="+select.val();
			}
		});
	}
	</lz:set>
</script>



</body>
</html>