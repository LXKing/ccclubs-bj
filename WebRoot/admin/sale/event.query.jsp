<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csseId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csseId" : (lz:join(relateObject,"$csseId")))}
			 ${lz:set("cname",lz:join(relateObject,".csseId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csseId || #request.defines['csseId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csseId":relateObject}" id="form-dl-csseId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csseIdYesNot=="not"?"checked-not":""}${data.csseIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csseIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csseId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csseId}
			 	
			 ${before$csseHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csseHost" : (lz:join(relateObject,"$csseHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csseHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csseHost || #request.defines['csseHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csseHost":relateObject}" id="form-dl-csseHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csseHostYesNot=="not"?"checked-not":""}${data.csseHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csseHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csseHost}">
				 					${get:SrvHost(data.csseHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csseHost}
			 	
			 ${before$csseSubject}
			 
			 ${lz:set("注释","*****************活动主题字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csseSubject" : (lz:join(relateObject,"$csseSubject")))}
			 ${lz:set("cname",lz:join(relateObject,".csseSubject"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csseSubject || #request.defines['csseSubject']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csseSubject":relateObject}" id="form-dl-csseSubject">
					<dt>活动主题：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csseSubjectYesNot=="not"?"checked-not":""}${data.csseSubjectYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csseSubjectYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csseSubject}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************活动主题字段的查询代码结束*****************")}
			
			
			${after$csseSubject}
			 	
			 ${before$csseBattle}
			 
			 ${lz:set("注释","*****************归属战役字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csseBattle" : (lz:join(relateObject,"$csseBattle")))}
			 ${lz:set("cname",lz:join(relateObject,".csseBattle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csseBattle || #request.defines['csseBattle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csseBattle":relateObject}" id="form-dl-csseBattle">
					<dt>归属战役：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csseBattleYesNot=="not"?"checked-not":""}${data.csseBattleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csseBattleYesNot}"/>
			 			<input title="请输入销售战役战役标题进行查询" class="combox" action="${basePath}${proname}/sale/battle_query.do?value={param}&tbsbHost={csseHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:TbSaleBattle(data.csseBattle).tbsbTitle}" value="${data.csseBattle}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/battle_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************归属战役字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csseBattle']>0">
				${lz:set("注释","****当归属战役字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csseBattle")}
			  	<input type="hidden" name="csseBattle$on" id="TbSaleBattle$on" value="true"/>
			  	<jsp:include page="/admin/sale/battle.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csseBattle}
			 	
			 ${before$csseType}
			 
			 ${lz:set("注释","*****************活动类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csseType" : (lz:join(relateObject,"$csseType")))}
			 ${lz:set("cname",lz:join(relateObject,".csseType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csseType || #request.defines['csseType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csseType":relateObject}" id="form-dl-csseType">
					<dt>活动类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csseTypeYesNot=="not"?"checked-not":""}${data.csseTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csseTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", get:propertys("销售活动类型","sale_event_type"))}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.spId}" ${data.csseType==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************活动类型字段的查询代码结束*****************")}
			
			
			${after$csseType}
			 	
			 ${before$cssePerson}
			 
			 ${lz:set("注释","*****************负责人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssePerson" : (lz:join(relateObject,"$cssePerson")))}
			 ${lz:set("cname",lz:join(relateObject,".cssePerson"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssePerson || #request.defines['cssePerson']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssePerson":relateObject}" id="form-dl-cssePerson">
					<dt>负&nbsp;&nbsp;责&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssePersonYesNot=="not"?"checked-not":""}${data.cssePersonYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssePersonYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cssePerson}">
				 					${get:SrvUser(data.cssePerson).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************负责人字段的查询代码结束*****************")}
			
			
			${after$cssePerson}
			 	
			 ${before$csseUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csseUpdateTime" : (lz:join(relateObject,"$csseUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csseUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csseUpdateTime || #request.defines['csseUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csseUpdateTime":relateObject}" id="form-dl-csseUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csseUpdateTimeYesNot=="not"?"checked-not":""}${data.csseUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csseUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csseUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csseUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csseUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csseUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csseUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csseUpdateTime}
			 	
			 ${before$csseAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csseAddTime" : (lz:join(relateObject,"$csseAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csseAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csseAddTime || #request.defines['csseAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csseAddTime":relateObject}" id="form-dl-csseAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csseAddTimeYesNot=="not"?"checked-not":""}${data.csseAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csseAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csseAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csseAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csseAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csseAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csseAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csseAddTime}
			 	
			 ${before$csseStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csseStatus" : (lz:join(relateObject,"$csseStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csseStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csseStatus || #request.defines['csseStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csseStatus":relateObject}" id="form-dl-csseStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csseStatusYesNot=="not"?"checked-not":""}${data.csseStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csseStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csseStatus==0?"selected":""}>筹备中</option>
							<option value="1" ${data.csseStatus==1?"selected":""}>进行中</option>
							<option value="2" ${data.csseStatus==2?"selected":""}>已完成</option>
							<option value="3" ${data.csseStatus==3?"selected":""}>已取消</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csseStatus}