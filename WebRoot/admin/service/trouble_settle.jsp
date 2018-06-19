<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 


	<s:if test="csTrouble.cstStatus!=5">
		${lz:set("message","只有状态为'已扣款待结案'的事故才能结案")}
		<lz:set name="jsonString">
			{
				title:"结案处理",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>		
		<lz:set name="jsonString">
			{
				title:"结案处理",
				editable:false,
				visible:false,
				action:"trouble_settle.do",
				fields:{
					csoId:{editable:false,visible:true}
					,cstMember:{editable:false,visible:true}
					,cstCar:{editable:false,visible:true}
					,cstOrder:{editable:false,visible:true}
					,cstType:{editable:false,visible:true}
					,cstAgio:{editable:true,visible:true}
					,cstDecipt:{editable:false,visible:true}
					,cstRemark:{editable:true,visible:true}
					,cstInvoice:{editable:false,visible:true}
					,cstInsureFee:{editable:false,visible:true}
					,cstMoney:{editable:false,visible:true}
					,cstThirdLoss:{editable:false,visible:true}
					,cstThirdDesc:{editable:false,visible:true}
					,cstCompensate:{editable:false,visible:true}
					,cstOperatLoss:{editable:false,visible:true}
					,cstRepairPay:{editable:false,visible:true}
					,cstRepair:{editable:false,visible:true}
				},
				submits:[
					{name:"确定结案",value="确定结案"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
		<script>
		$(function(){
			//$("#cstAgio").attr("onkeyup","alert('操');var reg=/^(\-)?(([1-9]\d{0,9})|0)(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''");
		});
		</script>
	</s:else>
