<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>

${lz:set("haveData",false)}

<s:iterator value="#request.members" id="item" status="i">
	${get:srvlimit("admin/user/member.do")}
	<s:if test="#request.canView==true">
	${lz:set("haveData",true)}
	<div class="item">
		<dl class="opts">
			<dt>操作 ▼<dt><dd class="optionMenu">
			<s:if test="#request.canExp[0]==true">
			<a href="javascript:HandleSel('预订','${item.csmId}')">预订</a>
			</s:if>
			<s:if test="#request.canExp[1]==true">
			<a href="javascript:HandleSel('发短信','${item.csmId}')">发短信</a>
			</s:if>
			<s:if test="#request.canExp[2]==true">
			<a href="javascript:HandleSel('充值','${item.csmId}')">充值</a>
			</s:if>
			<s:if test="#request.canExp[3]==true">
			<a href="javascript:HandleSel('扣款','${item.csmId}')">扣款</a>
			</s:if>
			<s:if test="#request.canExp[4]==true">
			<a href="javascript:HandleSel('充小时','${item.csmId}')">充小时</a>
			</s:if>
			<s:if test="#request.canExp[5]==true">
			<a href="javascript:HandleSel('重置','${item.csmId}')">重置</a>
			</s:if>
			<s:if test="#request.canExp[6]==true">
			<a href="javascript:HandleSel('审核','${item.csmId}','${basePath}admin/user/member_edit.do')">审核</a>
			</s:if>
			</dd>
		</dl>
		<img class="icon" align="absmiddle" src="${basePath}admin/images/icons/20088256427828778017.png"/>
		<a class="detail" href="javascript:void(0);" onclick="$.showModalDialog({url:'${basePath}admin/user/member_details.do?id=${item.csmId}'},{ctrl:{editable:false,editable:false,visible:true}})">
		会员：${lz:keyword(item.csmName,keyword,3)}(${lz:keyword(item.csmMobile,keyword,11)})</a>
	</div>
	${lz:set("lastOrders",lz:execute("getMemberLastOrders",item.csmId))}
	<s:iterator value="#request.lastOrders" id="order">
		<%@include file="search.order.jsp"%>
	</s:iterator>
	</s:if>
</s:iterator>


<s:iterator value="#request.cars" id="item" status="i">
	${get:srvlimit("admin/object/car.do")}
	<s:if test="#request.canView==true">
	${lz:set("haveData",true)}
	<div class="item">
		<dl class="opts">
			<dt>操作 ▼<dt><dd class="optionMenu">
			<s:if test="#request.canExp[0]==true">
			<a href="javascript:HandleSel('开门','${item.cscId}')">开门</a>
			</s:if>
			<s:if test="#request.canExp[1]==true">
			<a href="javascript:HandleSel('关门','${item.cscId}')">关门</a>
			</s:if>
			<s:if test="#request.canExp[2]==true">
			<a href="javascript:HandleSel('可启动','${item.cscId}')">可启动</a>
			</s:if>
			<s:if test="#request.canExp[3]==true">
			<a href="javascript:HandleSel('断油电','${item.cscId}')">断油电</a>
			</s:if>
			<s:if test="#request.canExp[4]==true">
			<a href="javascript:HandleSel('初始化','${item.cscId}')">初始化</a>
			</s:if>
			<s:if test="#request.canExp[5]==true">
			<a href="javascript:HandleSel('同步','${item.cscId}')">同步</a>
			</s:if>
			</dd>
		</dl>
		<img class="icon" align="absmiddle" src="${basePath}admin/images/icons/200882564229234778010.png"/>
		<a class="detail" href="javascript:void(0);" onclick="$.showModalDialog({url:'${basePath}admin/object/car_details.do?id=${item.cscId}'},{ctrl:{editable:false,editable:false,visible:true}})">
		车辆：${lz:keyword(item.cscNumber,keyword,11)}(${lz:keyword(item.cscModel$,keyword,11)})</a>
	</div>
	${lz:set("lastOrders",lz:execute("getCarLastOrders",item.cscId))}
	<s:iterator value="#request.lastOrders" id="order">
		<%@include file="search.order.jsp"%>
	</s:iterator>
	</s:if>
</s:iterator>

<s:if test="#request.haveData==true">
<div class="line"></div>
</s:if>

<s:iterator value="#request.orders" id="order" status="i">
<%@include file="search.order.jsp"%>
</s:iterator>



<s:if test="#request.haveData!=true">
<center>未找到搜索结果...</center>
</s:if>
