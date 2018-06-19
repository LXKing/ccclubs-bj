<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 

	<s:if test="(csTrouble.cstStatus>0 && csTrouble.cstStatus<5) || (csTrouble.cstType==0 && csTrouble.cstStatus<5)">	
		<lz:set name="jsonString">
			{
				title:"事故协商",
				editable:false,
				visible:false,
				action:"trouble_consult.do",
				fields:{
					csoId:{editable:false,visible:true}
					,cstMember:{editable:false,visible:true}
					,cstCar:{editable:false,visible:true}
					,cstOrder:{editable:false,visible:true}
					,cstType:{editable:false,visible:true}
					,cstDamage:{editable:false,visible:true}
					,cstInsureFee:{editable:false,visible:true}
					,cstMoney:{editable:true,visible:true}
					,cstDecipt:{editable:false,visible:true}
					,cstRemark:{editable:true,visible:true}
					,cstRepair:{editable:false,visible:true}
				}
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		${lz:set("message","只有状态为已定损并且未扣款的事故才能协商")}
		<lz:set name="jsonString">
			{
				title:"事故协商",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
