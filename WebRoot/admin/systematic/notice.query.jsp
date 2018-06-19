<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csnId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csnId" : (lz:join(relateObject,"$csnId")))}
			 ${lz:set("cname",lz:join(relateObject,".csnId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csnId || #request.defines['csnId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csnId":relateObject}" id="form-dl-csnId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csnIdYesNot=="not"?"checked-not":""}${data.csnIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csnIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csnId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csnId}
			 	
			 ${before$csnContent}
			 
			 ${lz:set("注释","*****************通知内容字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csnContent" : (lz:join(relateObject,"$csnContent")))}
			 ${lz:set("cname",lz:join(relateObject,".csnContent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csnContent || #request.defines['csnContent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csnContent":relateObject}" id="form-dl-csnContent">
					<dt>通知内容：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csnContentYesNot=="not"?"checked-not":""}${data.csnContentYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csnContentYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="512" name="${NAME}" id="${NAME}"  value="${data.csnContent}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************通知内容字段的查询代码结束*****************")}
			
			
			${after$csnContent}
			 	
			 ${before$csnAlarmTime}
			 
			 ${lz:set("注释","*****************提醒时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csnAlarmTime" : (lz:join(relateObject,"$csnAlarmTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csnAlarmTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csnAlarmTime || #request.defines['csnAlarmTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csnAlarmTime":relateObject}" id="form-dl-csnAlarmTime">
					<dt>提醒时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csnAlarmTimeYesNot=="not"?"checked-not":""}${data.csnAlarmTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csnAlarmTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csnAlarmTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csnAlarmTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csnAlarmTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csnAlarmTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>提醒时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csnAlarmTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************提醒时间字段的查询代码结束*****************")}
			
			
			${after$csnAlarmTime}
			 	
			 ${before$csnSender}
			 
			 ${lz:set("注释","*****************发送人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csnSender" : (lz:join(relateObject,"$csnSender")))}
			 ${lz:set("cname",lz:join(relateObject,".csnSender"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csnSender || #request.defines['csnSender']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csnSender":relateObject}" id="form-dl-csnSender">
					<dt>发&nbsp;&nbsp;送&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csnSenderYesNot=="not"?"checked-not":""}${data.csnSenderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csnSenderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csnSender}">
				 					${get:SrvUser(data.csnSender).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发送人字段的查询代码结束*****************")}
			
			
			${after$csnSender}
			 	
			 ${before$csnReceiver}
			 
			 ${lz:set("注释","*****************接收人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csnReceiver" : (lz:join(relateObject,"$csnReceiver")))}
			 ${lz:set("cname",lz:join(relateObject,".csnReceiver"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csnReceiver || #request.defines['csnReceiver']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csnReceiver":relateObject}" id="form-dl-csnReceiver">
					<dt>接&nbsp;&nbsp;收&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csnReceiverYesNot=="not"?"checked-not":""}${data.csnReceiverYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csnReceiverYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csnReceiver}">
				 					${get:SrvUser(data.csnReceiver).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接收人字段的查询代码结束*****************")}
			
			
			${after$csnReceiver}
			 	
			 ${before$csnAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csnAddTime" : (lz:join(relateObject,"$csnAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csnAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csnAddTime || #request.defines['csnAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csnAddTime":relateObject}" id="form-dl-csnAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csnAddTimeYesNot=="not"?"checked-not":""}${data.csnAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csnAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csnAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csnAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csnAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csnAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csnAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csnAddTime}
			 	
			 ${before$csnDealTime}
			 
			 ${lz:set("注释","*****************处理时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csnDealTime" : (lz:join(relateObject,"$csnDealTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csnDealTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csnDealTime || #request.defines['csnDealTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csnDealTime":relateObject}" id="form-dl-csnDealTime">
					<dt>处理时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csnDealTimeYesNot=="not"?"checked-not":""}${data.csnDealTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csnDealTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csnDealTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csnDealTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csnDealTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csnDealTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>处理时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csnDealTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************处理时间字段的查询代码结束*****************")}
			
			
			${after$csnDealTime}
			 	
			 ${before$csnStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csnStatus" : (lz:join(relateObject,"$csnStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csnStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csnStatus || #request.defines['csnStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csnStatus":relateObject}" id="form-dl-csnStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csnStatusYesNot=="not"?"checked-not":""}${data.csnStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csnStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csnStatus==0?"selected":""}>待处理</option>
							<option value="1" ${data.csnStatus==1?"selected":""}>提醒报警</option>
							<option value="2" ${data.csnStatus==2?"selected":""}>已处理</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csnStatus}