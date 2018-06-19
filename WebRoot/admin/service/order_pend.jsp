<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 

	<s:if test="csOrder.csoStatus>2">
		${lz:set("message","只有已预订、正在执行、已还车的订单才能进行待处理操作")}
		<lz:set name="jsonString">
			{
				title:"待处理",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>		
		${lz:set("message","待处理操作后,请结合监控系统对车进行初始化操作!!!")}
		<lz:set name="jsonString">
			{
				title:"待处理",
				editable:false,
				visible:false,
				action:"order_pend.do",
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
				submits:[{name:"待处理",value="待处理"}]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>