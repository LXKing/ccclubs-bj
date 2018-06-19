<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cshsId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cshsId" : (lz:join(relateObject,"$cshsId")))}
			 ${lz:set("cname",lz:join(relateObject,".cshsId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cshsId || #request.defines['cshsId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cshsId":relateObject}" id="form-dl-cshsId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cshsIdYesNot=="not"?"checked-not":""}${data.cshsIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cshsIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cshsId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cshsId}
			 	
			 ${before$cshsHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cshsHost" : (lz:join(relateObject,"$cshsHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cshsHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cshsHost || #request.defines['cshsHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cshsHost":relateObject}" id="form-dl-cshsHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cshsHostYesNot=="not"?"checked-not":""}${data.cshsHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cshsHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cshsHost}">
				 					${get:SrvHost(data.cshsHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cshsHost}
			 	
			 ${before$cshsNumber}
			 
			 ${lz:set("注释","*****************车牌号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cshsNumber" : (lz:join(relateObject,"$cshsNumber")))}
			 ${lz:set("cname",lz:join(relateObject,".cshsNumber"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cshsNumber || #request.defines['cshsNumber']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cshsNumber":relateObject}" id="form-dl-cshsNumber">
					<dt>车&nbsp;&nbsp;牌&nbsp;&nbsp;号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cshsNumberYesNot=="not"?"checked-not":""}${data.cshsNumberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cshsNumberYesNot}"/>
			 			<input title="请输入查询" class="combox" action="${basePath}${proname}/object/car_numbers.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${data.cshsNumber}" value="${data.cshsNumber}"/>	 		
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车牌号字段的查询代码结束*****************")}
			
			
			${after$cshsNumber}
			 	
			 ${before$cshsAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cshsAddTime" : (lz:join(relateObject,"$cshsAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cshsAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cshsAddTime || #request.defines['cshsAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cshsAddTime":relateObject}" id="form-dl-cshsAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cshsAddTimeYesNot=="not"?"checked-not":""}${data.cshsAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cshsAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cshsAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cshsAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cshsAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cshsAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cshsAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cshsAddTime}
			 	
			 ${before$cshsMileage}
			 
			 ${lz:set("注释","*****************总里程字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cshsMileage" : (lz:join(relateObject,"$cshsMileage")))}
			 ${lz:set("cname",lz:join(relateObject,".cshsMileage"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cshsMileage || #request.defines['cshsMileage']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cshsMileage":relateObject}" id="form-dl-cshsMileage">
					<dt>总&nbsp;&nbsp;里&nbsp;&nbsp;程：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cshsMileageYesNot=="not"?"checked-not":""}${data.cshsMileageYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cshsMileageYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cshsMileage}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************总里程字段的查询代码结束*****************")}
			
			
			${after$cshsMileage}
			 	
			 ${before$cshsOrder}
			 
			 ${lz:set("注释","*****************当前订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cshsOrder" : (lz:join(relateObject,"$cshsOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".cshsOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cshsOrder || #request.defines['cshsOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cshsOrder":relateObject}" id="form-dl-cshsOrder">
					<dt>当前订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cshsOrderYesNot=="not"?"checked-not":""}${data.cshsOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cshsOrderYesNot}"/>
			 			<input class="input" size="16" type="text" id="${NAME}" name="${NAME}" value="${data.cshsOrder}" />
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************当前订单字段的查询代码结束*****************")}
			
			
			${after$cshsOrder}
			 	
			 ${before$cshsMoData}
			 
			 ${lz:set("注释","*****************接口数据字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cshsMoData" : (lz:join(relateObject,"$cshsMoData")))}
			 ${lz:set("cname",lz:join(relateObject,".cshsMoData"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cshsMoData || #request.defines['cshsMoData']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cshsMoData":relateObject}" id="form-dl-cshsMoData">
					<dt>接口数据：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cshsMoDataYesNot=="not"?"checked-not":""}${data.cshsMoDataYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cshsMoDataYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.cshsMoData}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接口数据字段的查询代码结束*****************")}
			
			
			${after$cshsMoData}