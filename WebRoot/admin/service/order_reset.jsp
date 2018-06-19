<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 


	<s:if test="csOrder.csoStatus==3||csOrder.csoStatus==4">
		${lz:set("message","已取消或已完成的订单不能重发")}
		<lz:set name="jsonString">
			{
				title:"重发",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		${lz:set("message","如果是已完成或已取消的订单，再次重发时可能会造成重复扣款的问题")}		
		<lz:set name="jsonString">
			{
				title:"重发",
				editable:false,
				visible:false,
				action:"order_reset.do",
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
				submits:[{name:"重发",value="重发"}]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>