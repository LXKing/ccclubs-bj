<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 


	<s:if test="csUnitOrder.$csuoOrder.csoStatus>0 && csUnitOrder.$csuoOrder.csoStatus<7">
		${lz:set("message","只有在系统订单状态为已预订或已撤销的订单才能取消")}
		<lz:set name="jsonString">
			{
				title:"取消订单",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("controller",lz:eval(jsonString))}
	</s:if>
	<s:else>
		<lz:set name="jsonString">
			{
				title:"取消订单",
				editable:false,
				visible:false,
				action:"order.cancel.do",
				fields:{
					csuoId:{editable:false,visible:false}
					,csuoUnitChild:{editable:false,visible:true}
					,csuoCar:{editable:false,visible:true}
					,csuoUsername:{editable:false,visible:true}
					,csuoMobile:{editable:false,visible:true}
					,csuoDayPrice:{editable:false,visible:true}
					,csuoHourPrice:{editable:false,visible:true}					
					,csuoStartTime:{editable:false,visible:true}
					,csuoFinishTime:{editable:false,visible:true}
					,csuoDay:{editable:false,visible:true}
					,csuoHour:{editable:false,visible:true}
					,csuoPayNeed:{editable:false,visible:true}
					,csuoFreeHour:{editable:false,visible:true}
					,csuoRemark:{editable:true,visible:true}
				},
				submits:[{name:"取消订单",value="取消订单"}]
			}
		</lz:set>
		${lz:set("controller",lz:eval(jsonString))}
	</s:else>