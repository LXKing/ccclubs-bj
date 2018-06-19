<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csaId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaId" : (lz:join(relateObject,"$csaId")))}
			 ${lz:set("cname",lz:join(relateObject,".csaId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaId || #request.defines['csaId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaId":relateObject}" id="form-dl-csaId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaIdYesNot=="not"?"checked-not":""}${data.csaIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csaId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csaId}
			 	
			 ${before$csaHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaHost" : (lz:join(relateObject,"$csaHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csaHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaHost || #request.defines['csaHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaHost":relateObject}" id="form-dl-csaHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaHostYesNot=="not"?"checked-not":""}${data.csaHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csaHost}">
				 					${get:SrvHost(data.csaHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csaHost}
			 	
			 ${before$csaNumber}
			 
			 ${lz:set("注释","*****************车牌号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaNumber" : (lz:join(relateObject,"$csaNumber")))}
			 ${lz:set("cname",lz:join(relateObject,".csaNumber"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaNumber || #request.defines['csaNumber']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaNumber":relateObject}" id="form-dl-csaNumber">
					<dt>车&nbsp;&nbsp;牌&nbsp;&nbsp;号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaNumberYesNot=="not"?"checked-not":""}${data.csaNumberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaNumberYesNot}"/>
			 			<input title="请输入查询" class="combox" action="${basePath}${proname}/object/car_numbers.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${data.csaNumber}" value="${data.csaNumber}"/>	 		
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车牌号字段的查询代码结束*****************")}
			
			
			${after$csaNumber}
			 	
			 ${before$csaAddTime}
			 
			 ${lz:set("注释","*****************接收时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaAddTime" : (lz:join(relateObject,"$csaAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csaAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaAddTime || #request.defines['csaAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaAddTime":relateObject}" id="form-dl-csaAddTime">
					<dt>接收时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaAddTimeYesNot=="not"?"checked-not":""}${data.csaAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csaAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csaAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csaAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csaAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>接收时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csaAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接收时间字段的查询代码结束*****************")}
			
			
			${after$csaAddTime}
			 	
			 ${before$csaInfo}
			 
			 ${lz:set("注释","*****************报警信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaInfo" : (lz:join(relateObject,"$csaInfo")))}
			 ${lz:set("cname",lz:join(relateObject,".csaInfo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaInfo || #request.defines['csaInfo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaInfo":relateObject}" id="form-dl-csaInfo">
					<dt>报警信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaInfoYesNot=="not"?"checked-not":""}${data.csaInfoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaInfoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csaInfo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************报警信息字段的查询代码结束*****************")}
			
			
			${after$csaInfo}
			 	
			 ${before$csaOrder}
			 
			 ${lz:set("注释","*****************当前订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaOrder" : (lz:join(relateObject,"$csaOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csaOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaOrder || #request.defines['csaOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaOrder":relateObject}" id="form-dl-csaOrder">
					<dt>当前订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaOrderYesNot=="not"?"checked-not":""}${data.csaOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csaHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.csaOrder).csoId}" value="${data.csaOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************当前订单字段的查询代码结束*****************")}
			
			
			${after$csaOrder}