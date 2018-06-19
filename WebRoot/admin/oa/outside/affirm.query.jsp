<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$tbaaId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaId" : (lz:join(relateObject,"$tbaaId")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaId || #request.defines['tbaaId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaId":relateObject}" id="form-dl-tbaaId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaIdYesNot=="not"?"checked-not":""}${data.tbaaIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.tbaaId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$tbaaId}
			 	
			 ${before$tbaaHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaHost" : (lz:join(relateObject,"$tbaaHost")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaHost || #request.defines['tbaaHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaHost":relateObject}" id="form-dl-tbaaHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaHostYesNot=="not"?"checked-not":""}${data.tbaaHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbaaHost}">
				 					${get:SrvHost(data.tbaaHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$tbaaHost}
			 	
			 ${before$tbaaCar}
			 
			 ${lz:set("注释","*****************所属车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaCar" : (lz:join(relateObject,"$tbaaCar")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaCar || #request.defines['tbaaCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaCar":relateObject}" id="form-dl-tbaaCar">
					<dt>所属车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaCarYesNot=="not"?"checked-not":""}${data.tbaaCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaCarYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={tbaaHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.tbaaCar).cscNumber}" value="${data.tbaaCar}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbaaCar']>0">
				${lz:set("注释","****当所属车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbaaCar")}
			  	<input type="hidden" name="tbaaCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbaaCar}
			 	
			 ${before$tbaaOrder}
			 
			 ${lz:set("注释","*****************所属订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaOrder" : (lz:join(relateObject,"$tbaaOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaOrder || #request.defines['tbaaOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaOrder":relateObject}" id="form-dl-tbaaOrder">
					<dt>所属订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaOrderYesNot=="not"?"checked-not":""}${data.tbaaOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={tbaaHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.tbaaOrder).csoId}" value="${data.tbaaOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbaaOrder']>0">
				${lz:set("注释","****当所属订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbaaOrder")}
			  	<input type="hidden" name="tbaaOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbaaOrder}
			 	
			 ${before$tbaaOut}
			 
			 ${lz:set("注释","*****************借出网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaOut" : (lz:join(relateObject,"$tbaaOut")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaOut"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaOut || #request.defines['tbaaOut']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaOut":relateObject}" id="form-dl-tbaaOut">
					<dt>借出网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaOutYesNot=="not"?"checked-not":""}${data.tbaaOutYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaOutYesNot}"/>
			 			<input title="请输入网点名称进行查询" class="combox" action="${basePath}${proname}/object/outlets_query.do?value={param}&csoHost={tbaaHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOutlets(data.tbaaOut).csoName}" value="${data.tbaaOut}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************借出网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbaaOut']>0">
				${lz:set("注释","****当借出网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbaaOut")}
			  	<input type="hidden" name="tbaaOut$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbaaOut}
			 	
			 ${before$tbaaRet}
			 
			 ${lz:set("注释","*****************预计还入网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaRet" : (lz:join(relateObject,"$tbaaRet")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaRet"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaRet || #request.defines['tbaaRet']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaRet":relateObject}" id="form-dl-tbaaRet">
					<dt>预计还入网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaRetYesNot=="not"?"checked-not":""}${data.tbaaRetYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaRetYesNot}"/>
			 			<input title="请输入网点名称进行查询" class="combox" action="${basePath}${proname}/object/outlets_query.do?value={param}&csoHost={tbaaHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOutlets(data.tbaaRet).csoName}" value="${data.tbaaRet}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************预计还入网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbaaRet']>0">
				${lz:set("注释","****当预计还入网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbaaRet")}
			  	<input type="hidden" name="tbaaRet$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbaaRet}
			 	
			 ${before$tbaaRealRet}
			 
			 ${lz:set("注释","*****************实际还入网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaRealRet" : (lz:join(relateObject,"$tbaaRealRet")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaRealRet"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaRealRet || #request.defines['tbaaRealRet']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaRealRet":relateObject}" id="form-dl-tbaaRealRet">
					<dt>实际还入网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaRealRetYesNot=="not"?"checked-not":""}${data.tbaaRealRetYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaRealRetYesNot}"/>
			 			<input title="请输入网点名称进行查询" class="combox" action="${basePath}${proname}/object/outlets_query.do?value={param}&csoHost={tbaaHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOutlets(data.tbaaRealRet).csoName}" value="${data.tbaaRealRet}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************实际还入网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbaaRealRet']>0">
				${lz:set("注释","****当实际还入网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbaaRealRet")}
			  	<input type="hidden" name="tbaaRealRet$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbaaRealRet}
			 	
			 ${before$tbaaTime}
			 
			 ${lz:set("注释","*****************预计还入时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaTime" : (lz:join(relateObject,"$tbaaTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaTime || #request.defines['tbaaTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaTime":relateObject}" id="form-dl-tbaaTime">
					<dt>预计还入时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaTimeYesNot=="not"?"checked-not":""}${data.tbaaTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbaaTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbaaTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbaaTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbaaTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>预计还入时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbaaTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************预计还入时间字段的查询代码结束*****************")}
			
			
			${after$tbaaTime}
			 	
			 ${before$tbaaRealTime}
			 
			 ${lz:set("注释","*****************实际还入时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaRealTime" : (lz:join(relateObject,"$tbaaRealTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaRealTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaRealTime || #request.defines['tbaaRealTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaRealTime":relateObject}" id="form-dl-tbaaRealTime">
					<dt>实际还入时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaRealTimeYesNot=="not"?"checked-not":""}${data.tbaaRealTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaRealTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbaaRealTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbaaRealTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbaaRealTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbaaRealTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>实际还入时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbaaRealTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************实际还入时间字段的查询代码结束*****************")}
			
			
			${after$tbaaRealTime}
			 	
			 ${before$tbaaConfirm}
			 
			 ${lz:set("注释","*****************确认人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaConfirm" : (lz:join(relateObject,"$tbaaConfirm")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaConfirm"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaConfirm || #request.defines['tbaaConfirm']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaConfirm":relateObject}" id="form-dl-tbaaConfirm">
					<dt>确&nbsp;&nbsp;认&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaConfirmYesNot=="not"?"checked-not":""}${data.tbaaConfirmYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaConfirmYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbaaConfirm}">
				 					${get:SrvUser(data.tbaaConfirm).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************确认人字段的查询代码结束*****************")}
			
			
			${after$tbaaConfirm}
			 	
			 ${before$tbaaAdder}
			 
			 ${lz:set("注释","*****************添加人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaAdder" : (lz:join(relateObject,"$tbaaAdder")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaAdder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaAdder || #request.defines['tbaaAdder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaAdder":relateObject}" id="form-dl-tbaaAdder">
					<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaAdderYesNot=="not"?"checked-not":""}${data.tbaaAdderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaAdderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbaaAdder}">
				 					${get:SrvUser(data.tbaaAdder).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人字段的查询代码结束*****************")}
			
			
			${after$tbaaAdder}
			 	
			 ${before$tbaaEditor}
			 
			 ${lz:set("注释","*****************修改人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaEditor" : (lz:join(relateObject,"$tbaaEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaEditor || #request.defines['tbaaEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaEditor":relateObject}" id="form-dl-tbaaEditor">
					<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaEditorYesNot=="not"?"checked-not":""}${data.tbaaEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbaaEditor}">
				 					${get:SrvUser(data.tbaaEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改人字段的查询代码结束*****************")}
			
			
			${after$tbaaEditor}
			 	
			 ${before$tbaaConfirmTime}
			 
			 ${lz:set("注释","*****************确认时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaConfirmTime" : (lz:join(relateObject,"$tbaaConfirmTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaConfirmTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaConfirmTime || #request.defines['tbaaConfirmTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaConfirmTime":relateObject}" id="form-dl-tbaaConfirmTime">
					<dt>确认时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaConfirmTimeYesNot=="not"?"checked-not":""}${data.tbaaConfirmTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaConfirmTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbaaConfirmTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbaaConfirmTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbaaConfirmTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbaaConfirmTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>确认时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbaaConfirmTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************确认时间字段的查询代码结束*****************")}
			
			
			${after$tbaaConfirmTime}
			 	
			 ${before$tbaaUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaUpdateTime" : (lz:join(relateObject,"$tbaaUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaUpdateTime || #request.defines['tbaaUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaUpdateTime":relateObject}" id="form-dl-tbaaUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaUpdateTimeYesNot=="not"?"checked-not":""}${data.tbaaUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbaaUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbaaUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbaaUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbaaUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbaaUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$tbaaUpdateTime}
			 	
			 ${before$tbaaAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaAddTime" : (lz:join(relateObject,"$tbaaAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaAddTime || #request.defines['tbaaAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaAddTime":relateObject}" id="form-dl-tbaaAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaAddTimeYesNot=="not"?"checked-not":""}${data.tbaaAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbaaAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbaaAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbaaAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbaaAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbaaAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$tbaaAddTime}
			 	
			 ${before$tbaaRemark}
			 
			 ${lz:set("注释","*****************备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaRemark" : (lz:join(relateObject,"$tbaaRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaRemark || #request.defines['tbaaRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaRemark":relateObject}" id="form-dl-tbaaRemark">
					<dt>备　　注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaRemarkYesNot=="not"?"checked-not":""}${data.tbaaRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.tbaaRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注字段的查询代码结束*****************")}
			
			
			${after$tbaaRemark}
			 	
			 ${before$tbaaStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbaaStatus" : (lz:join(relateObject,"$tbaaStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".tbaaStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbaaStatus || #request.defines['tbaaStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbaaStatus":relateObject}" id="form-dl-tbaaStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbaaStatusYesNot=="not"?"checked-not":""}${data.tbaaStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbaaStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.tbaaStatus==0?"selected":""}>待确认</option>
							<option value="1" ${data.tbaaStatus==1?"selected":""}>未确认</option>
							<option value="2" ${data.tbaaStatus==2?"selected":""}>已确认</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$tbaaStatus}