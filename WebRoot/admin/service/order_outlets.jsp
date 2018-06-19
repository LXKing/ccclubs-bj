<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/>
<s:if test="(csOrder.csoStatus==0||csOrder.csoStatus==1) && csOrder.csoOutlets != csOrder.csoOutletsRet ">
	<lz:set name="jsonString">
		{
			title:"更新网点",
			editable:false,
			visible:false,
			action:"order_changeOutlets.do",
			fields:{
				csoId:{editable:false,visible:true}
				,csoHost:{editable:false,visible:true}
				,csoCar:{editable:false,visible:true}
				,csoOutlets:{editable:false,visible:true}
				,csoOutletsRet:{editable:true,visible:true}
				,csoStartTime:{editable:false,visible:true}
				,csoFinishTime:{editable:false,visible:true}
			},
			submits:[{name:"更改网点",value="更改网点"}]
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
</s:if>
