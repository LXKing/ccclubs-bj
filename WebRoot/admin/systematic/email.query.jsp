<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cseId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cseId" : (lz:join(relateObject,"$cseId")))}
			 ${lz:set("cname",lz:join(relateObject,".cseId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cseId || #request.defines['cseId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cseId":relateObject}" id="form-dl-cseId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cseIdYesNot=="not"?"checked-not":""}${data.cseIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cseIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cseId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cseId}
			 	
			 ${before$cseTo}
			 
			 ${lz:set("注释","*****************接收人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cseTo" : (lz:join(relateObject,"$cseTo")))}
			 ${lz:set("cname",lz:join(relateObject,".cseTo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cseTo || #request.defines['cseTo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cseTo":relateObject}" id="form-dl-cseTo">
					<dt>接&nbsp;&nbsp;收&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cseToYesNot=="not"?"checked-not":""}${data.cseToYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cseToYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.cseTo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接收人字段的查询代码结束*****************")}
			
			
			${after$cseTo}
			 	
			 ${before$cseContent}
			 
			 ${lz:set("注释","*****************邮件内容字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cseContent" : (lz:join(relateObject,"$cseContent")))}
			 ${lz:set("cname",lz:join(relateObject,".cseContent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cseContent || #request.defines['cseContent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cseContent":relateObject}" id="form-dl-cseContent">
					<dt>邮件内容：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cseContentYesNot=="not"?"checked-not":""}${data.cseContentYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cseContentYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.cseContent}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************邮件内容字段的查询代码结束*****************")}
			
			
			${after$cseContent}
			 	
			 ${before$cseFrom}
			 
			 ${lz:set("注释","*****************发送人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cseFrom" : (lz:join(relateObject,"$cseFrom")))}
			 ${lz:set("cname",lz:join(relateObject,".cseFrom"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cseFrom || #request.defines['cseFrom']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cseFrom":relateObject}" id="form-dl-cseFrom">
					<dt>发&nbsp;&nbsp;送&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cseFromYesNot=="not"?"checked-not":""}${data.cseFromYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cseFromYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.cseFrom}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发送人字段的查询代码结束*****************")}
			
			
			${after$cseFrom}
			 	
			 ${before$cseSendTime}
			 
			 ${lz:set("注释","*****************发送时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cseSendTime" : (lz:join(relateObject,"$cseSendTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cseSendTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cseSendTime || #request.defines['cseSendTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cseSendTime":relateObject}" id="form-dl-cseSendTime">
					<dt>发送时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cseSendTimeYesNot=="not"?"checked-not":""}${data.cseSendTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cseSendTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cseSendTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cseSendTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cseSendTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cseSendTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>发送时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cseSendTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发送时间字段的查询代码结束*****************")}
			
			
			${after$cseSendTime}
			 	
			 ${before$cseAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cseAddTime" : (lz:join(relateObject,"$cseAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cseAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cseAddTime || #request.defines['cseAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cseAddTime":relateObject}" id="form-dl-cseAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cseAddTimeYesNot=="not"?"checked-not":""}${data.cseAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cseAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cseAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cseAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cseAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cseAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cseAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cseAddTime}
			 	
			 ${before$cseStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cseStatus" : (lz:join(relateObject,"$cseStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cseStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cseStatus || #request.defines['cseStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cseStatus":relateObject}" id="form-dl-cseStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cseStatusYesNot=="not"?"checked-not":""}${data.cseStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cseStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cseStatus==0?"selected":""}>待发送</option>
							<option value="1" ${data.cseStatus==1?"selected":""}>发送成功</option>
							<option value="2" ${data.cseStatus==2?"selected":""}>发送失败</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cseStatus}