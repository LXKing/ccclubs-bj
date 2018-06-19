<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 

	<div id="compute" style="clear:both;font-size:16px;margin:0px auto;width:550px;display:none;">
		请仔细填写企业订单相关信息，"*" 为必填选项。
	</div>
	<script>
	<!--
	$(function(){
		$("#compute").insertBefore("#orderForm .line");
	});
	//-->
	</script>

	<s:if test="csUnitOrder.csuoState!=1">
		${lz:set("message","只有状态为待审核的订单才能进行审核操作")}
		<lz:set name="jsonString">
			{
				title:"结算订单",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("controller",lz:eval(jsonString))}
	</s:if>
	<s:else>
		<lz:set name="jsonString">
			{
				title:"结算订单",
				editable:false,
				visible:false,
				action:"order.check.do",
				fields:{
					csuoId:{editable:false,visible:false}
					,csuoUnitChild:{editable:false,visible:true}
					,csuoCar:{editable:false,visible:true}
					,csuoUsername:{editable:false,visible:true}
					,csuoMobile:{editable:false,visible:true}
					,csuoAlias:{editable:false,visible:true}
					,csuoIdcard:{editable:false,visible:true}
					,csuoStartTime:{editable:false,visible:true}
					,csuoFinishTime:{editable:false,visible:true}
					,csuoRemark:{editable:true,visible:true}
				},
				submits:[{name:"审核通过",value="审核通过"},{name:"审核不通过",value="审核不通过"}]
			}
		</lz:set>
		${lz:set("controller",lz:eval(jsonString))}
	</s:else>
	
	<script>
	$(function(){
		$("<b>*</b>").insertAfter("#csuoRemark");
	});
	</script>