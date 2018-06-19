<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csehId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehId" : (lz:join(relateObject,"$csehId")))}
			 ${lz:set("cname",lz:join(relateObject,".csehId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehId || #request.defines['csehId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehId":relateObject}" id="form-dl-csehId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehIdYesNot=="not"?"checked-not":""}${data.csehIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csehId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csehId}
			 	
			 ${before$csehEventType}
			 
			 ${lz:set("注释","*****************事件类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehEventType" : (lz:join(relateObject,"$csehEventType")))}
			 ${lz:set("cname",lz:join(relateObject,".csehEventType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehEventType || #request.defines['csehEventType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehEventType":relateObject}" id="form-dl-csehEventType">
					<dt>事件类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehEventTypeYesNot=="not"?"checked-not":""}${data.csehEventTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehEventTypeYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/systematic/eventtype_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csehEventType}">
				 					${get:CsEventType(data.csehEventType).csetName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/systematic/eventtype_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************事件类型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csehEventType']>0">
				${lz:set("注释","****当事件类型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csehEventType")}
			  	<input type="hidden" name="csehEventType$on" id="CsEventType$on" value="true"/>
			  	<jsp:include page="/admin/systematic/eventtype.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csehEventType}
			 	
			 ${before$csehHeedUser}
			 
			 ${lz:set("注释","*****************关注人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehHeedUser" : (lz:join(relateObject,"$csehHeedUser")))}
			 ${lz:set("cname",lz:join(relateObject,".csehHeedUser"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehHeedUser || #request.defines['csehHeedUser']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehHeedUser":relateObject}" id="form-dl-csehHeedUser">
					<dt>关&nbsp;&nbsp;注&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehHeedUserYesNot=="not"?"checked-not":""}${data.csehHeedUserYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehHeedUserYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csehHeedUser}">
				 					${get:SrvUser(data.csehHeedUser).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关注人字段的查询代码结束*****************")}
			
			
			${after$csehHeedUser}
			 	
			 ${before$csehHeedType}
			 
			 ${lz:set("注释","*****************处理方式字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehHeedType" : (lz:join(relateObject,"$csehHeedType")))}
			 ${lz:set("cname",lz:join(relateObject,".csehHeedType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehHeedType || #request.defines['csehHeedType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehHeedType":relateObject}" id="form-dl-csehHeedType">
					<dt>处理方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehHeedTypeYesNot=="not"?"checked-not":""}${data.csehHeedTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehHeedTypeYesNot}"/>
				 		<select id="${NAME}" name="${NAME}" >
			 				<option value="">全部</option>
							<option value="#0#" ${data.csehHeedType=='#0#'?"selected":""}>消息提示</option>
							<option value="#1#" ${data.csehHeedType=='#1#'?"selected":""}>系统通知</option>
							<option value="#3#" ${data.csehHeedType=='#3#'?"selected":""}>邮件通知</option>
							<option value="#2#" ${data.csehHeedType=='#2#'?"selected":""}>短信通知</option>
							<option value="#4#" ${data.csehHeedType=='#4#'?"selected":""}>微信通知</option>
						</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************处理方式字段的查询代码结束*****************")}
			
			
			${after$csehHeedType}
			 	
			 ${before$csehUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehUpdateTime" : (lz:join(relateObject,"$csehUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csehUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehUpdateTime || #request.defines['csehUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehUpdateTime":relateObject}" id="form-dl-csehUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehUpdateTimeYesNot=="not"?"checked-not":""}${data.csehUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csehUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csehUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csehUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csehUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csehUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csehUpdateTime}
			 	
			 ${before$csehAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehAddTime" : (lz:join(relateObject,"$csehAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csehAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehAddTime || #request.defines['csehAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehAddTime":relateObject}" id="form-dl-csehAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehAddTimeYesNot=="not"?"checked-not":""}${data.csehAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csehAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csehAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csehAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csehAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csehAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csehAddTime}
			 	
			 ${before$csehStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehStatus" : (lz:join(relateObject,"$csehStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csehStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehStatus || #request.defines['csehStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehStatus":relateObject}" id="form-dl-csehStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehStatusYesNot=="not"?"checked-not":""}${data.csehStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csehStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csehStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csehStatus}