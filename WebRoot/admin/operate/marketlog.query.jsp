<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csmlId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmlId" : (lz:join(relateObject,"$csmlId")))}
			 ${lz:set("cname",lz:join(relateObject,".csmlId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmlId || #request.defines['csmlId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmlId":relateObject}" id="form-dl-csmlId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmlIdYesNot=="not"?"checked-not":""}${data.csmlIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmlIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csmlId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csmlId}
			 	
			 ${before$csmlHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmlHost" : (lz:join(relateObject,"$csmlHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csmlHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmlHost || #request.defines['csmlHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmlHost":relateObject}" id="form-dl-csmlHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmlHostYesNot=="not"?"checked-not":""}${data.csmlHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmlHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmlHost}">
				 					${get:SrvHost(data.csmlHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csmlHost}
			 	
			 ${before$csmlTitle}
			 
			 ${lz:set("注释","*****************标题字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmlTitle" : (lz:join(relateObject,"$csmlTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".csmlTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmlTitle || #request.defines['csmlTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmlTitle":relateObject}" id="form-dl-csmlTitle">
					<dt>标　　题：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmlTitleYesNot=="not"?"checked-not":""}${data.csmlTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmlTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csmlTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************标题字段的查询代码结束*****************")}
			
			
			${after$csmlTitle}
			 	
			 ${before$csmlPlan}
			 
			 ${lz:set("注释","*****************所属方案字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmlPlan" : (lz:join(relateObject,"$csmlPlan")))}
			 ${lz:set("cname",lz:join(relateObject,".csmlPlan"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmlPlan || #request.defines['csmlPlan']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmlPlan":relateObject}" id="form-dl-csmlPlan">
					<dt>所属方案：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmlPlanYesNot=="not"?"checked-not":""}${data.csmlPlanYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmlPlanYesNot}"/>
			 			<input title="请输入营销方案方案主题进行查询" class="combox" action="${basePath}${proname}/operate/marketplan_query.do?value={param}&csmpHost={csmlHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMarketPlan(data.csmlPlan).csmpName}" value="${data.csmlPlan}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/operate/marketplan_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属方案字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmlPlan']>0">
				${lz:set("注释","****当所属方案字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmlPlan")}
			  	<input type="hidden" name="csmlPlan$on" id="CsMarketPlan$on" value="true"/>
			  	<jsp:include page="/admin/operate/marketplan.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmlPlan}
			 	
			 ${before$csmlUser}
			 
			 ${lz:set("注释","*****************执行人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmlUser" : (lz:join(relateObject,"$csmlUser")))}
			 ${lz:set("cname",lz:join(relateObject,".csmlUser"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmlUser || #request.defines['csmlUser']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmlUser":relateObject}" id="form-dl-csmlUser">
					<dt>执&nbsp;&nbsp;行&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmlUserYesNot=="not"?"checked-not":""}${data.csmlUserYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmlUserYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmlUser}">
				 					${get:SrvUser(data.csmlUser).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************执行人字段的查询代码结束*****************")}
			
			
			${after$csmlUser}
			 	
			 ${before$csmlUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmlUpdateTime" : (lz:join(relateObject,"$csmlUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmlUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmlUpdateTime || #request.defines['csmlUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmlUpdateTime":relateObject}" id="form-dl-csmlUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmlUpdateTimeYesNot=="not"?"checked-not":""}${data.csmlUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmlUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmlUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmlUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmlUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmlUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmlUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csmlUpdateTime}
			 	
			 ${before$csmlAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmlAddTime" : (lz:join(relateObject,"$csmlAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmlAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmlAddTime || #request.defines['csmlAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmlAddTime":relateObject}" id="form-dl-csmlAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmlAddTimeYesNot=="not"?"checked-not":""}${data.csmlAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmlAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmlAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmlAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmlAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmlAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmlAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csmlAddTime}
			 	
			 ${before$csmlStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmlStatus" : (lz:join(relateObject,"$csmlStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csmlStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmlStatus || #request.defines['csmlStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmlStatus":relateObject}" id="form-dl-csmlStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmlStatusYesNot=="not"?"checked-not":""}${data.csmlStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmlStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmlStatus==0?"selected":""}>默认</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csmlStatus}