<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csuaId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuaId" : (lz:join(relateObject,"$csuaId")))}
			 ${lz:set("cname",lz:join(relateObject,".csuaId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuaId || #request.defines['csuaId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuaId":relateObject}" id="form-dl-csuaId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuaIdYesNot=="not"?"checked-not":""}${data.csuaIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuaIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csuaId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csuaId}
			 	
			 ${before$csuaHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuaHost" : (lz:join(relateObject,"$csuaHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csuaHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuaHost || #request.defines['csuaHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuaHost":relateObject}" id="form-dl-csuaHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuaHostYesNot=="not"?"checked-not":""}${data.csuaHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuaHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csuaHost}">
				 					${get:SrvHost(data.csuaHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csuaHost}
			 	
			 ${before$csuaUnitInfo}
			 
			 ${lz:set("注释","*****************所属单位字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuaUnitInfo" : (lz:join(relateObject,"$csuaUnitInfo")))}
			 ${lz:set("cname",lz:join(relateObject,".csuaUnitInfo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuaUnitInfo || #request.defines['csuaUnitInfo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuaUnitInfo":relateObject}" id="form-dl-csuaUnitInfo">
					<dt>所属单位：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuaUnitInfoYesNot=="not"?"checked-not":""}${data.csuaUnitInfoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuaUnitInfoYesNot}"/>
			 			<input title="请输入企业信息名称进行查询" class="combox" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csuaHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsUnitInfo(data.csuaUnitInfo).csuiName}" value="${data.csuaUnitInfo}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属单位字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuaUnitInfo']>0">
				${lz:set("注释","****当所属单位字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuaUnitInfo")}
			  	<input type="hidden" name="csuaUnitInfo$on" id="CsUnitInfo$on" value="true"/>
			  	<jsp:include page="/admin/unit/info.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuaUnitInfo}
			 	
			 ${before$csuaType}
			 
			 ${lz:set("注释","*****************类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuaType" : (lz:join(relateObject,"$csuaType")))}
			 ${lz:set("cname",lz:join(relateObject,".csuaType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuaType || #request.defines['csuaType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuaType":relateObject}" id="form-dl-csuaType">
					<dt>类　　型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuaTypeYesNot=="not"?"checked-not":""}${data.csuaTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuaTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csuaType==0?"selected":""}>接机</option>
							<option value="1" ${data.csuaType==1?"selected":""}>送机</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************类型字段的查询代码结束*****************")}
			
			
			${after$csuaType}
			 	
			 ${before$csuaFlight}
			 
			 ${lz:set("注释","*****************航班号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuaFlight" : (lz:join(relateObject,"$csuaFlight")))}
			 ${lz:set("cname",lz:join(relateObject,".csuaFlight"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuaFlight || #request.defines['csuaFlight']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuaFlight":relateObject}" id="form-dl-csuaFlight">
					<dt>航&nbsp;&nbsp;班&nbsp;&nbsp;号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuaFlightYesNot=="not"?"checked-not":""}${data.csuaFlightYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuaFlightYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csuaFlight}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************航班号字段的查询代码结束*****************")}
			
			
			${after$csuaFlight}
			 	
			 ${before$csuaTime}
			 
			 ${lz:set("注释","*****************时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuaTime" : (lz:join(relateObject,"$csuaTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuaTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuaTime || #request.defines['csuaTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuaTime":relateObject}" id="form-dl-csuaTime">
					<dt>时　　间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuaTimeYesNot=="not"?"checked-not":""}${data.csuaTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuaTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuaTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuaTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuaTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuaTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuaTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************时间字段的查询代码结束*****************")}
			
			
			${after$csuaTime}
			 	
			 ${before$csuaContact}
			 
			 ${lz:set("注释","*****************联系人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuaContact" : (lz:join(relateObject,"$csuaContact")))}
			 ${lz:set("cname",lz:join(relateObject,".csuaContact"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuaContact || #request.defines['csuaContact']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuaContact":relateObject}" id="form-dl-csuaContact">
					<dt>联&nbsp;&nbsp;系&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuaContactYesNot=="not"?"checked-not":""}${data.csuaContactYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuaContactYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csuaContact}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************联系人字段的查询代码结束*****************")}
			
			
			${after$csuaContact}
			 	
			 ${before$csuaMobile}
			 
			 ${lz:set("注释","*****************联系电话字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuaMobile" : (lz:join(relateObject,"$csuaMobile")))}
			 ${lz:set("cname",lz:join(relateObject,".csuaMobile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuaMobile || #request.defines['csuaMobile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuaMobile":relateObject}" id="form-dl-csuaMobile">
					<dt>联系电话：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuaMobileYesNot=="not"?"checked-not":""}${data.csuaMobileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuaMobileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csuaMobile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************联系电话字段的查询代码结束*****************")}
			
			
			${after$csuaMobile}
			 	
			 ${before$csuaStatement}
			 
			 ${lz:set("注释","*****************结账单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuaStatement" : (lz:join(relateObject,"$csuaStatement")))}
			 ${lz:set("cname",lz:join(relateObject,".csuaStatement"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuaStatement || #request.defines['csuaStatement']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuaStatement":relateObject}" id="form-dl-csuaStatement">
					<dt>结&nbsp;&nbsp;账&nbsp;&nbsp;单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuaStatementYesNot=="not"?"checked-not":""}${data.csuaStatementYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuaStatementYesNot}"/>
			 			<input title="请输入企业对账帐单月份进行查询" class="combox" action="${basePath}${proname}/unit/bill_query.do?value={param}&csubHost={csuaHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsUnitBill(data.csuaStatement).csubMonth}" value="${data.csuaStatement}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/bill_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结账单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuaStatement']>0">
				${lz:set("注释","****当结账单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuaStatement")}
			  	<input type="hidden" name="csuaStatement$on" id="CsUnitBill$on" value="true"/>
			  	<jsp:include page="/admin/unit/bill.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuaStatement}
			 	
			 ${before$csuaUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuaUpdateTime" : (lz:join(relateObject,"$csuaUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuaUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuaUpdateTime || #request.defines['csuaUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuaUpdateTime":relateObject}" id="form-dl-csuaUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuaUpdateTimeYesNot=="not"?"checked-not":""}${data.csuaUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuaUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuaUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuaUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuaUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuaUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuaUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csuaUpdateTime}
			 	
			 ${before$csuaAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuaAddTime" : (lz:join(relateObject,"$csuaAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuaAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuaAddTime || #request.defines['csuaAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuaAddTime":relateObject}" id="form-dl-csuaAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuaAddTimeYesNot=="not"?"checked-not":""}${data.csuaAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuaAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuaAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuaAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuaAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuaAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuaAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csuaAddTime}
			 	
			 ${before$csuaStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuaStatus" : (lz:join(relateObject,"$csuaStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csuaStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuaStatus || #request.defines['csuaStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuaStatus":relateObject}" id="form-dl-csuaStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuaStatusYesNot=="not"?"checked-not":""}${data.csuaStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuaStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csuaStatus==0?"selected":""}>未处理</option>
							<option value="1" ${data.csuaStatus==1?"selected":""}>已处理</option>
							<option value="2" ${data.csuaStatus==2?"selected":""}>审核失败</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csuaStatus}