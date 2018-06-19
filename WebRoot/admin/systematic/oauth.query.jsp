<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$soId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "soId" : (lz:join(relateObject,"$soId")))}
			 ${lz:set("cname",lz:join(relateObject,".soId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.soId || #request.defines['soId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"soId":relateObject}" id="form-dl-soId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.soIdYesNot=="not"?"checked-not":""}${data.soIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.soIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.soId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$soId}
			 	
			 ${before$soToken}
			 
			 ${lz:set("注释","*****************Token字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "soToken" : (lz:join(relateObject,"$soToken")))}
			 ${lz:set("cname",lz:join(relateObject,".soToken"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.soToken || #request.defines['soToken']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"soToken":relateObject}" id="form-dl-soToken">
					<dt>Token：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.soTokenYesNot=="not"?"checked-not":""}${data.soTokenYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.soTokenYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.soToken}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************Token字段的查询代码结束*****************")}
			
			
			${after$soToken}
			 	
			 ${before$soUserId}
			 
			 ${lz:set("注释","*****************帐号ID字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "soUserId" : (lz:join(relateObject,"$soUserId")))}
			 ${lz:set("cname",lz:join(relateObject,".soUserId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.soUserId || #request.defines['soUserId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"soUserId":relateObject}" id="form-dl-soUserId">
					<dt>帐号ID：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.soUserIdYesNot=="not"?"checked-not":""}${data.soUserIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.soUserIdYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.soUserIdMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.soUserIdMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************帐号ID字段的查询代码结束*****************")}
			
			
			${after$soUserId}
			 	
			 ${before$soApiClient}
			 
			 ${lz:set("注释","*****************API客户端字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "soApiClient" : (lz:join(relateObject,"$soApiClient")))}
			 ${lz:set("cname",lz:join(relateObject,".soApiClient"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.soApiClient || #request.defines['soApiClient']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"soApiClient":relateObject}" id="form-dl-soApiClient">
					<dt>API客户端：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.soApiClientYesNot=="not"?"checked-not":""}${data.soApiClientYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.soApiClientYesNot}"/>
			 			<input title="请输入API客户端客户端ID进行查询" class="combox" action="${basePath}${proname}/configurator/apiclient_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:SrvApiClient(data.soApiClient).sacClient}" value="${data.soApiClient}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/apiclient_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************API客户端字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['soApiClient']>0">
				${lz:set("注释","****当API客户端字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","soApiClient")}
			  	<input type="hidden" name="soApiClient$on" id="SrvApiClient$on" value="true"/>
			  	<jsp:include page="/admin/configurator/apiclient.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$soApiClient}
			 	
			 ${before$soTicket}
			 
			 ${lz:set("注释","*****************三方标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "soTicket" : (lz:join(relateObject,"$soTicket")))}
			 ${lz:set("cname",lz:join(relateObject,".soTicket"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.soTicket || #request.defines['soTicket']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"soTicket":relateObject}" id="form-dl-soTicket">
					<dt>三方标识：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.soTicketYesNot=="not"?"checked-not":""}${data.soTicketYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.soTicketYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.soTicket}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************三方标识字段的查询代码结束*****************")}
			
			
			${after$soTicket}
			 	
			 ${before$soUpdateTime}
			 
			 ${lz:set("注释","*****************登录时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "soUpdateTime" : (lz:join(relateObject,"$soUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".soUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.soUpdateTime || #request.defines['soUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"soUpdateTime":relateObject}" id="form-dl-soUpdateTime">
					<dt>登录时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.soUpdateTimeYesNot=="not"?"checked-not":""}${data.soUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.soUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.soUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.soUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.soUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.soUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>登录时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.soUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************登录时间字段的查询代码结束*****************")}
			
			
			${after$soUpdateTime}