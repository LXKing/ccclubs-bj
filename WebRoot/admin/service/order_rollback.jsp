<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 

	<s:if test="csOrder.csoStatus!=4">
		${lz:set("message","只有已完成的订单才能回滚")}
		<lz:set name="jsonString">
			{
				title:"不能回滚",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		${lz:set("message","回滚后订单将回到待处理状态<br/>回滚后后,请人工重新结算!!!")}		
		<lz:set name="jsonString">
			{
				title:"回滚",
				editable:false,
				visible:false,
				action:"order_rollback.do",
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
				submits:[{name:"回滚",value="回滚"}]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>