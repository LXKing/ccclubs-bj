<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 
	
	<s:if test="csRefund.csrStatus!=0">
		${lz:set("message","只有状态为'等待审核'的退款才能进行审核操作")}
		<lz:set name="jsonString">
			{
				title:"退款审核",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>		
		<lz:set name="jsonString">
			{
				title:"退款审核",
				editable:false,
				visible:false,
				action:"refund_check.do",
				fields:{
					csrMember:{editable:false,visible:true}
					,csrCheckTime:{editable:true,visible:true}
					,csrBackTime:{editable:true,visible:true}
					,csrEndTime:{editable:true,visible:true}
					,csrRemark:{editable:true,visible:true}
				},
				submits:[
					{name:"审核通过",value="审核通过"},
					{name:"继续等待",value="继续等待"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
		${lz:set("frameid",lz:uuid())}
		<lz:set name="troubleViolatHtml">
		<div>
			<dl style="width:98%;">
				<dt>事故列表:</dt>
				<dd>		
					<iframe class="state-input" onload="$(this).autoHeight()" id="iframe_${frameid}_trouble" name="iframe_${frameid}_trouble" frameborder='0' 
					src="${basePath}admin/service/trouble.do?cstMember=${csRefund.csrMember}&all=true&ctrl={mutual:'dialog',canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" width='620' height='0'></iframe>
				</dd>
			</dl>
			<dl style="width:98%;">
				<dt>违章列表:</dt>
				<dd>		
					<iframe class="state-input" onload="$(this).autoHeight()" id="iframe_${frameid}_violat" name="iframe_${frameid}_violat" frameborder='0' 
					src="${basePath}admin/service/violat.do?csvMember=${csRefund.csrMember}&all=true&ctrl={mutual:'dialog',canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" width='620' height='0'></iframe>
				</dd>
			</dl>
		</div>
		</lz:set>
		<script>
		$(function(){
			$("${lz:js(troubleViolatHtml)}").insertBefore("dl[ref=csrRemark]");		
		});
		</script>
	</s:else>