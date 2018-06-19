<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 


	
	<s:if test="csTrouble.cstStatus==4 || (csTrouble.cstType==0 && csTrouble.cstStatus>1 && csTrouble.cstStatus<5)">
		<lz:set name="message">确定扣款后，将直接从会员帐户上扣除${csTrouble.cstMoney}元</lz:set>
		<lz:set name="jsonString">
			{
				title:"处理扣款",
				editable:false,
				visible:false,
				action:"trouble_deduct.do",
				fields:{
					csoId:{editable:true,visible:true}
					,cstMember:{editable:false,visible:true}
					,cstCar:{editable:false,visible:true}
					,cstOrder:{editable:false,visible:true}
					,cstType:{editable:false,visible:true}
					,cstDamage:{editable:false,visible:true}
					,cstInsureFee:{editable:false,visible:true}
					,cstMoney:{editable:false,visible:true}
					,cstDecipt:{editable:false,visible:true}
					,cstRemark:{editable:true,visible:true}
				},
				submits:[
					{name:"确定扣款",value="确定扣款"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		${lz:set("message","只有状态为'已维修待扣款'的事故才能处理扣款")}
		<lz:set name="jsonString">
			{
				title:"处理扣款",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}		
	</s:else>