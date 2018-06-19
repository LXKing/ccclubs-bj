<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 


	<s:if test="csTrouble.cstStatus!=0">
		${lz:set("message","只有状态为'已录入待定损'的事故才能定损")}
		<lz:set name="jsonString">
			{
				title:"事故定损",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>		
		<lz:set name="jsonString">
			{
				title:"事故定损",
				editable:false,
				visible:false,
				action:"trouble_pricing.do",
				fields:{
					csoId:{editable:false,visible:true}
					,cstMember:{editable:false,visible:true}
					,cstCar:{editable:false,visible:true}
					,cstOrder:{editable:false,visible:true}
					,cstType:{editable:false,visible:true}
					,cstDamage:{editable:false,visible:true}
					,cstInsureFee:{editable:true,visible:true}
					,cstDecipt:{editable:false,visible:true}
					,cstRemark:{editable:true,visible:true}
				}
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
