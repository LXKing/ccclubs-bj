<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csviId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csviId" : (lz:join(relateObject,"$csviId")))}
			 ${lz:set("cname",lz:join(relateObject,".csviId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csviId || #request.defines['csviId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csviId":relateObject}" id="form-dl-csviId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csviIdYesNot=="not"?"checked-not":""}${data.csviIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csviIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csviId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csviId}
			 	
			 ${before$csviHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csviHost" : (lz:join(relateObject,"$csviHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csviHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csviHost || #request.defines['csviHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csviHost":relateObject}" id="form-dl-csviHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csviHostYesNot=="not"?"checked-not":""}${data.csviHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csviHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csviHost}">
				 					${get:SrvHost(data.csviHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csviHost}
			 	
			 ${before$csviAddress}
			 
			 ${lz:set("注释","*****************IP地址字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csviAddress" : (lz:join(relateObject,"$csviAddress")))}
			 ${lz:set("cname",lz:join(relateObject,".csviAddress"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csviAddress || #request.defines['csviAddress']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csviAddress":relateObject}" id="form-dl-csviAddress">
					<dt>IP地址：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csviAddressYesNot=="not"?"checked-not":""}${data.csviAddressYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csviAddressYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csviAddress}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************IP地址字段的查询代码结束*****************")}
			
			
			${after$csviAddress}
			 	
			 ${before$csviChannel}
			 
			 ${lz:set("注释","*****************所属渠道字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csviChannel" : (lz:join(relateObject,"$csviChannel")))}
			 ${lz:set("cname",lz:join(relateObject,".csviChannel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csviChannel || #request.defines['csviChannel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csviChannel":relateObject}" id="form-dl-csviChannel">
					<dt>所属渠道：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csviChannelYesNot=="not"?"checked-not":""}${data.csviChannelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csviChannelYesNot}"/>
			 			<input title="请输入访问渠道渠道名称进行查询" class="combox" action="${basePath}${proname}/extension/channel_query.do?value={param}&cscHost={csviHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsChannel(data.csviChannel).cscName}" value="${data.csviChannel}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/extension/channel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属渠道字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csviChannel']>0">
				${lz:set("注释","****当所属渠道字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csviChannel")}
			  	<input type="hidden" name="csviChannel$on" id="CsChannel$on" value="true"/>
			  	<jsp:include page="/admin/extension/channel.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csviChannel}
			 	
			 ${before$csviPlan}
			 
			 ${lz:set("注释","*****************所属策划字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csviPlan" : (lz:join(relateObject,"$csviPlan")))}
			 ${lz:set("cname",lz:join(relateObject,".csviPlan"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csviPlan || #request.defines['csviPlan']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csviPlan":relateObject}" id="form-dl-csviPlan">
					<dt>所属策划：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csviPlanYesNot=="not"?"checked-not":""}${data.csviPlanYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csviPlanYesNot}"/>
			 			<input title="请输入营销方案方案主题进行查询" class="combox" action="${basePath}${proname}/operate/marketplan_query.do?value={param}&csmpHost={csviHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMarketPlan(data.csviPlan).csmpName}" value="${data.csviPlan}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/operate/marketplan_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属策划字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csviPlan']>0">
				${lz:set("注释","****当所属策划字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csviPlan")}
			  	<input type="hidden" name="csviPlan$on" id="CsMarketPlan$on" value="true"/>
			  	<jsp:include page="/admin/operate/marketplan.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csviPlan}
			 	
			 ${before$csviActivity}
			 
			 ${lz:set("注释","*****************所属活动字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csviActivity" : (lz:join(relateObject,"$csviActivity")))}
			 ${lz:set("cname",lz:join(relateObject,".csviActivity"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csviActivity || #request.defines['csviActivity']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csviActivity":relateObject}" id="form-dl-csviActivity">
					<dt>所属活动：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csviActivityYesNot=="not"?"checked-not":""}${data.csviActivityYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csviActivityYesNot}"/>
			 			<input title="请输入营销活动活动主题进行查询" class="combox" action="${basePath}${proname}/operate/activitys/activity_query.do?value={param}&csaHost={csviHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsActivity(data.csviActivity).csaTitle}" value="${data.csviActivity}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/operate/activitys/activity_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属活动字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csviActivity']>0">
				${lz:set("注释","****当所属活动字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csviActivity")}
			  	<input type="hidden" name="csviActivity$on" id="CsActivity$on" value="true"/>
			  	<jsp:include page="/admin/operate/activitys/activity.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csviActivity}
			 	
			 ${before$csviAddTime}
			 
			 ${lz:set("注释","*****************访问时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csviAddTime" : (lz:join(relateObject,"$csviAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csviAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csviAddTime || #request.defines['csviAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csviAddTime":relateObject}" id="form-dl-csviAddTime">
					<dt>访问时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csviAddTimeYesNot=="not"?"checked-not":""}${data.csviAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csviAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csviAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csviAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csviAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csviAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>访问时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csviAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************访问时间字段的查询代码结束*****************")}
			
			
			${after$csviAddTime}