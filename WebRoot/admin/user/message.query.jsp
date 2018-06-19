<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csmId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmId" : (lz:join(relateObject,"$csmId")))}
			 ${lz:set("cname",lz:join(relateObject,".csmId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmId || #request.defines['csmId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmId":relateObject}" id="form-dl-csmId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmIdYesNot=="not"?"checked-not":""}${data.csmIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csmId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csmId}
			 	
			 ${before$csmTitle}
			 
			 ${lz:set("注释","*****************标题字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmTitle" : (lz:join(relateObject,"$csmTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".csmTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmTitle || #request.defines['csmTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmTitle":relateObject}" id="form-dl-csmTitle">
					<dt>标　　题：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmTitleYesNot=="not"?"checked-not":""}${data.csmTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csmTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************标题字段的查询代码结束*****************")}
			
			
			${after$csmTitle}
			 	
			 ${before$csmReceiver}
			 
			 ${lz:set("注释","*****************接收人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmReceiver" : (lz:join(relateObject,"$csmReceiver")))}
			 ${lz:set("cname",lz:join(relateObject,".csmReceiver"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmReceiver || #request.defines['csmReceiver']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmReceiver":relateObject}" id="form-dl-csmReceiver">
					<dt>接&nbsp;&nbsp;收&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmReceiverYesNot=="not"?"checked-not":""}${data.csmReceiverYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmReceiverYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}admin/user/member_inquire.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csmReceiver).csmName}" value="${data.csmReceiver}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接收人字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmReceiver']>0">
				${lz:set("注释","****当接收人字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmReceiver")}
			  	<input type="hidden" name="csmReceiver$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmReceiver}
			 	
			 ${before$csmSender}
			 
			 ${lz:set("注释","*****************发送人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmSender" : (lz:join(relateObject,"$csmSender")))}
			 ${lz:set("cname",lz:join(relateObject,".csmSender"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmSender || #request.defines['csmSender']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmSender":relateObject}" id="form-dl-csmSender">
					<dt>发&nbsp;&nbsp;送&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmSenderYesNot=="not"?"checked-not":""}${data.csmSenderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmSenderYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}admin/user/member_inquire.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csmSender).csmName}" value="${data.csmSender}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发送人字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmSender']>0">
				${lz:set("注释","****当发送人字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmSender")}
			  	<input type="hidden" name="csmSender$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmSender}
			 	
			 ${before$csmRcvTime}
			 
			 ${lz:set("注释","*****************接收时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmRcvTime" : (lz:join(relateObject,"$csmRcvTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmRcvTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmRcvTime || #request.defines['csmRcvTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmRcvTime":relateObject}" id="form-dl-csmRcvTime">
					<dt>接收时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmRcvTimeYesNot=="not"?"checked-not":""}${data.csmRcvTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmRcvTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmRcvTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmRcvTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmRcvTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmRcvTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>接收时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmRcvTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接收时间字段的查询代码结束*****************")}
			
			
			${after$csmRcvTime}
			 	
			 ${before$csmSndTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmSndTime" : (lz:join(relateObject,"$csmSndTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmSndTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmSndTime || #request.defines['csmSndTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmSndTime":relateObject}" id="form-dl-csmSndTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmSndTimeYesNot=="not"?"checked-not":""}${data.csmSndTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmSndTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmSndTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmSndTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmSndTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmSndTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmSndTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csmSndTime}
			 	
			 ${before$csmRcvStatus}
			 
			 ${lz:set("注释","*****************接收状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmRcvStatus" : (lz:join(relateObject,"$csmRcvStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csmRcvStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmRcvStatus || #request.defines['csmRcvStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmRcvStatus":relateObject}" id="form-dl-csmRcvStatus">
					<dt>接收状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmRcvStatusYesNot=="not"?"checked-not":""}${data.csmRcvStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmRcvStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmRcvStatus==0?"selected":""}>未读</option>
							<option value="1" ${data.csmRcvStatus==1?"selected":""}>已读</option>
							<option value="2" ${data.csmRcvStatus==2?"selected":""}>已删</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接收状态字段的查询代码结束*****************")}
			
			
			${after$csmRcvStatus}
			 	
			 ${before$csmSndStatus}
			 
			 ${lz:set("注释","*****************发送状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmSndStatus" : (lz:join(relateObject,"$csmSndStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csmSndStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmSndStatus || #request.defines['csmSndStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmSndStatus":relateObject}" id="form-dl-csmSndStatus">
					<dt>发送状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmSndStatusYesNot=="not"?"checked-not":""}${data.csmSndStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmSndStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
					 		 <option value="">无法自动构造</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发送状态字段的查询代码结束*****************")}
			
			
			${after$csmSndStatus}