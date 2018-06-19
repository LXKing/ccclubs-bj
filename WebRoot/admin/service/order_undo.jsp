<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 

	<s:if test="csOrder.csoStatus!=5">
		${lz:set("message","只有待处理的订单才能作废")}
		<lz:set name="jsonString">
			{
				title:"不能作废",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		${lz:set("message","作废后订单将不收取任何费用<br/>作废操作后,请结合监控系统对车进行初始化操作!!!")}		
		<lz:set name="jsonString">
			{
				title:"作废",
				editable:false,
				visible:false,
				action:"order_undo.do",
				fields:{
					csoId:{editable:false,visible:true}
					,csoPayMember:{editable:false,visible:true}
					,csoUseMember:{editable:false,visible:true}
					,csoStartTime:{editable:false,visible:true}
					,csoFinishTime:{editable:false,visible:true}
					,csoPayNeed:{editable:false,visible:true}
					,csoPayReal:{editable:false,visible:true}
					,csoRemark:{editable:true,visible:true}
				},
				submits:[{name:"作废",value="作废"}]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>