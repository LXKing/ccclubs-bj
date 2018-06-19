<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 


	<s:if test="csTrouble.cstStatus!=2 && csTrouble.cstStatus!=3">
		${lz:set("message","只有状态为'已定损待维修'或'正在维修'的事故才能处理维修操作")}
		<lz:set name="jsonString">
			{
				title:"维修处理",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>		
		<lz:set name="jsonString">
			{
				title:"维修处理",
				editable:false,
				visible:false,
				action:"trouble_repair.do",
				fields:{
					csoId:{editable:false,visible:true}
					,cstMember:{editable:false,visible:true}
					,cstCar:{editable:false,visible:true}
					,cstOrder:{editable:false,visible:true}
					,cstType:{editable:false,visible:true}
					,cstDamage:{editable:false,visible:true}
					,cstRepairAdr:{editable:true,visible:true}
					,cstRepairPart:{editable:true,visible:true}
					,cstDecipt:{editable:false,visible:true}
					,cstRemark:{editable:true,visible:true}
					,cstRepair:{editable:false,visible:true}
				},
				submits:[
					{name:"送入维修",value="送入维修"},
					{name:"维修完成",value="维修完成"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}		
	</s:else>
	
