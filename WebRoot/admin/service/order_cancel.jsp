<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 


	<s:if test="csOrder.csoStatus!=0">
		${lz:set("message","只有已预订的订单才能取消")}
		<lz:set name="jsonString">
			{
				title:"取消订单",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		${lz:set("difference",csOrder.csoFinishTime.time-csOrder.csoStartTime.time)}
		<s:if test="#request.difference>(24*60*60*1000)">
			${lz:set("message","6小时外取消收取订单0%费用,6小时内取消收取10%费用<br/>取消操作后,请结合监控系统对车进行初始化操作!!!")}
		</s:if>
		<s:else>
			${lz:set("message","1小时外取消收取订单0%费用,1小时内取消收取10%费用<br/>取消操作后,请结合监控系统对车进行初始化操作!!!")}
		</s:else>
		
		<lz:set name="jsonString">
			{
				title:"取消订单",
				editable:false,
				visible:false,
				action:"order_cancel.do",
				fields:{
					csoId:{editable:false,visible:true}
					,csoPayMember:{editable:false,visible:true}
					,csoUseMember:{editable:false,visible:true}
					,csoCar:{editable:false,visible:true}
					,csoFeeType:{editable:false,visible:true}
					,csoStartTime:{editable:false,visible:true}
					,csoFinishTime:{editable:false,visible:true}					
					,csoPayNeed:{editable:false,visible:true}
					,csoPayReal:{editable:false,visible:true}
					,csoRemark:{editable:true,visible:true}
				},
				submits:[{name:"取消订单",value="取消订单"}]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>