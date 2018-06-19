<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cslofId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslofId" : (lz:join(relateObject,"$cslofId")))}
			 ${lz:set("cname",lz:join(relateObject,".cslofId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslofId || #request.defines['cslofId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslofId":relateObject}" id="form-dl-cslofId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslofIdYesNot=="not"?"checked-not":""}${data.cslofIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslofIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cslofId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cslofId}
			 	
			 ${before$cslofHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslofHost" : (lz:join(relateObject,"$cslofHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cslofHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslofHost || #request.defines['cslofHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslofHost":relateObject}" id="form-dl-cslofHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslofHostYesNot=="not"?"checked-not":""}${data.cslofHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslofHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cslofHost}">
				 					${get:SrvHost(data.cslofHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cslofHost}
			 	
			 ${before$cslofLongorder}
			 
			 ${lz:set("注释","*****************所属长租字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslofLongorder" : (lz:join(relateObject,"$cslofLongorder")))}
			 ${lz:set("cname",lz:join(relateObject,".cslofLongorder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslofLongorder || #request.defines['cslofLongorder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslofLongorder":relateObject}" id="form-dl-cslofLongorder">
					<dt>所属长租：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslofLongorderYesNot=="not"?"checked-not":""}${data.cslofLongorderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslofLongorderYesNot}"/>
			 			<input title="请输入长租订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/longorder_query.do?value={param}&csloHost={cslofHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsLongOrder(data.cslofLongorder).csloId}" value="${data.cslofLongorder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/longorder_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属长租字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cslofLongorder']>0">
				${lz:set("注释","****当所属长租字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cslofLongorder")}
			  	<input type="hidden" name="cslofLongorder$on" id="CsLongOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/longorder.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cslofLongorder}
			 	
			 ${before$cslofName}
			 
			 ${lz:set("注释","*****************项目名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslofName" : (lz:join(relateObject,"$cslofName")))}
			 ${lz:set("cname",lz:join(relateObject,".cslofName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslofName || #request.defines['cslofName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslofName":relateObject}" id="form-dl-cslofName">
					<dt>项目名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslofNameYesNot=="not"?"checked-not":""}${data.cslofNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslofNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cslofName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************项目名称字段的查询代码结束*****************")}
			
			
			${after$cslofName}
			 	
			 ${before$cslofType}
			 
			 ${lz:set("注释","*****************费用类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslofType" : (lz:join(relateObject,"$cslofType")))}
			 ${lz:set("cname",lz:join(relateObject,".cslofType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslofType || #request.defines['cslofType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslofType":relateObject}" id="form-dl-cslofType">
					<dt>费用类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslofTypeYesNot=="not"?"checked-not":""}${data.cslofTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslofTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cslofType==0?"selected":""}>押金</option>
							<option value="1" ${data.cslofType==1?"selected":""}>租金</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************费用类型字段的查询代码结束*****************")}
			
			
			${after$cslofType}
			 	
			 ${before$cslofRemark}
			 
			 ${lz:set("注释","*****************备注信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslofRemark" : (lz:join(relateObject,"$cslofRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".cslofRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslofRemark || #request.defines['cslofRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslofRemark":relateObject}" id="form-dl-cslofRemark">
					<dt>备注信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslofRemarkYesNot=="not"?"checked-not":""}${data.cslofRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslofRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.cslofRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注信息字段的查询代码结束*****************")}
			
			
			${after$cslofRemark}
			 	
			 ${before$cslofUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslofUpdateTime" : (lz:join(relateObject,"$cslofUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cslofUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslofUpdateTime || #request.defines['cslofUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslofUpdateTime":relateObject}" id="form-dl-cslofUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslofUpdateTimeYesNot=="not"?"checked-not":""}${data.cslofUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslofUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cslofUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cslofUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cslofUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cslofUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cslofUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cslofUpdateTime}
			 	
			 ${before$cslofAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslofAddTime" : (lz:join(relateObject,"$cslofAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cslofAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslofAddTime || #request.defines['cslofAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslofAddTime":relateObject}" id="form-dl-cslofAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslofAddTimeYesNot=="not"?"checked-not":""}${data.cslofAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslofAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cslofAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cslofAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cslofAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cslofAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cslofAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cslofAddTime}
			 	
			 ${before$cslofStateInfo}
			 
			 ${lz:set("注释","*****************入帐信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslofStateInfo" : (lz:join(relateObject,"$cslofStateInfo")))}
			 ${lz:set("cname",lz:join(relateObject,".cslofStateInfo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslofStateInfo || #request.defines['cslofStateInfo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslofStateInfo":relateObject}" id="form-dl-cslofStateInfo">
					<dt>入帐信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslofStateInfoYesNot=="not"?"checked-not":""}${data.cslofStateInfoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslofStateInfoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.cslofStateInfo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************入帐信息字段的查询代码结束*****************")}
			
			
			${after$cslofStateInfo}
			 	
			 ${before$cslofState}
			 
			 ${lz:set("注释","*****************财务确认字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslofState" : (lz:join(relateObject,"$cslofState")))}
			 ${lz:set("cname",lz:join(relateObject,".cslofState"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslofState || #request.defines['cslofState']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslofState":relateObject}" id="form-dl-cslofState">
					<dt>财务确认：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslofStateYesNot=="not"?"checked-not":""}${data.cslofStateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslofStateYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cslofState==0?"selected":""}>待确认</option>
							<option value="1" ${data.cslofState==1?"selected":""}>已确认</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************财务确认字段的查询代码结束*****************")}
			
			
			${after$cslofState}
			 	
			 ${before$cslofStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslofStatus" : (lz:join(relateObject,"$cslofStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cslofStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslofStatus || #request.defines['cslofStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslofStatus":relateObject}" id="form-dl-cslofStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslofStatusYesNot=="not"?"checked-not":""}${data.cslofStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslofStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cslofStatus==0?"selected":""}>已交款</option>
							<option value="1" ${data.cslofStatus==1?"selected":""}>已退款</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cslofStatus}