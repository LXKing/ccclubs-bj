<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 


	<lz:set name="jsonString">
		{
			title:"订单详情",
			editable:false,
			visible:true,
			fields:{
				csuoId:{editable:false,visible:false}
				,csuoUnitInfo:{visible:false}
				,csuoUnitMember:{visible:false}
			}
		}
	</lz:set>
	${lz:set("controller",lz:eval(jsonString))}
	
	<script>
	$(function(){
		$(".form dl dd a").each(function(){
			$(this).parent().html($(this).html());
		});
	});
	</script>