<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csrId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrId" : (lz:join(relateObject,"$csrId")))}
			 ${lz:set("cname",lz:join(relateObject,".csrId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrId || #request.defines['csrId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrId":relateObject}" id="form-dl-csrId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrIdYesNot=="not"?"checked-not":""}${data.csrIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csrId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csrId}
			 	
			 ${before$csrHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrHost" : (lz:join(relateObject,"$csrHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csrHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrHost || #request.defines['csrHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrHost":relateObject}" id="form-dl-csrHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrHostYesNot=="not"?"checked-not":""}${data.csrHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrHost}">
				 					${get:SrvHost(data.csrHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csrHost}
			 	
			 ${before$csrProfile}
			 
			 ${lz:set("注释","*****************报修描述字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrProfile" : (lz:join(relateObject,"$csrProfile")))}
			 ${lz:set("cname",lz:join(relateObject,".csrProfile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrProfile || #request.defines['csrProfile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrProfile":relateObject}" id="form-dl-csrProfile">
					<dt>报修描述：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrProfileYesNot=="not"?"checked-not":""}${data.csrProfileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrProfileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csrProfile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************报修描述字段的查询代码结束*****************")}
			
			
			${after$csrProfile}
			 	
			 ${before$csrCar}
			 
			 ${lz:set("注释","*****************维修车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrCar" : (lz:join(relateObject,"$csrCar")))}
			 ${lz:set("cname",lz:join(relateObject,".csrCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrCar || #request.defines['csrCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrCar":relateObject}" id="form-dl-csrCar">
					<dt>维修车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrCarYesNot=="not"?"checked-not":""}${data.csrCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrCarYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csrHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.csrCar).cscNumber}" value="${data.csrCar}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************维修车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrCar']>0">
				${lz:set("注释","****当维修车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrCar")}
			  	<input type="hidden" name="csrCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrCar}
			 	
			 ${before$csrAdr}
			 
			 ${lz:set("注释","*****************送修地点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrAdr" : (lz:join(relateObject,"$csrAdr")))}
			 ${lz:set("cname",lz:join(relateObject,".csrAdr"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrAdr || #request.defines['csrAdr']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrAdr":relateObject}" id="form-dl-csrAdr">
					<dt>送修地点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrAdrYesNot=="not"?"checked-not":""}${data.csrAdrYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrAdrYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/service/repairadr_query.do?size=-1&csraHost={csrHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrAdr}">
				 					${get:CsRepairAdr(data.csrAdr).csraName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/repairadr_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************送修地点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrAdr']>0">
				${lz:set("注释","****当送修地点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrAdr")}
			  	<input type="hidden" name="csrAdr$on" id="CsRepairAdr$on" value="true"/>
			  	<jsp:include page="/admin/service/repairadr.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrAdr}
			 	
			 ${before$csrOrder}
			 
			 ${lz:set("注释","*****************事故订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrOrder" : (lz:join(relateObject,"$csrOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csrOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrOrder || #request.defines['csrOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrOrder":relateObject}" id="form-dl-csrOrder">
					<dt>事故订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrOrderYesNot=="not"?"checked-not":""}${data.csrOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csrHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.csrOrder).csoId}" value="${data.csrOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************事故订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrOrder']>0">
				${lz:set("注释","****当事故订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrOrder")}
			  	<input type="hidden" name="csrOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrOrder}
			 	
			 ${before$csrSender}
			 
			 ${lz:set("注释","*****************送修人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrSender" : (lz:join(relateObject,"$csrSender")))}
			 ${lz:set("cname",lz:join(relateObject,".csrSender"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrSender || #request.defines['csrSender']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrSender":relateObject}" id="form-dl-csrSender">
					<dt>送&nbsp;&nbsp;修&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrSenderYesNot=="not"?"checked-not":""}${data.csrSenderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrSenderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrSender}">
				 					${get:SrvUser(data.csrSender).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************送修人字段的查询代码结束*****************")}
			
			
			${after$csrSender}
			 	
			 ${before$csrRepairNo}
			 
			 ${lz:set("注释","*****************维修单号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrRepairNo" : (lz:join(relateObject,"$csrRepairNo")))}
			 ${lz:set("cname",lz:join(relateObject,".csrRepairNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrRepairNo || #request.defines['csrRepairNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrRepairNo":relateObject}" id="form-dl-csrRepairNo">
					<dt>维修单号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrRepairNoYesNot=="not"?"checked-not":""}${data.csrRepairNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrRepairNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csrRepairNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************维修单号字段的查询代码结束*****************")}
			
			
			${after$csrRepairNo}
			 	
			 ${before$csrIsSettle}
			 
			 ${lz:set("注释","*****************已结算？字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrIsSettle" : (lz:join(relateObject,"$csrIsSettle")))}
			 ${lz:set("cname",lz:join(relateObject,".csrIsSettle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrIsSettle || #request.defines['csrIsSettle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrIsSettle":relateObject}" id="form-dl-csrIsSettle">
					<dt>已结算？：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrIsSettleYesNot=="not"?"checked-not":""}${data.csrIsSettleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrIsSettleYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csrIsSettle=='true'?"selected":""}>是</option>
							<option value="false" ${data.csrIsSettle=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************已结算？字段的查询代码结束*****************")}
			
			
			${after$csrIsSettle}
			 	
			 ${before$csrSettleTime}
			 
			 ${lz:set("注释","*****************结算日期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrSettleTime" : (lz:join(relateObject,"$csrSettleTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csrSettleTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrSettleTime || #request.defines['csrSettleTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrSettleTime":relateObject}" id="form-dl-csrSettleTime">
					<dt>结算日期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrSettleTimeYesNot=="not"?"checked-not":""}${data.csrSettleTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrSettleTimeYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csrSettleTimeStart,"yyyy-MM-dd")}" end="${lz:date(data.csrSettleTimeEnd,"yyyy-MM-dd")}" exp="${data.csrSettleTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csrSettleTimeStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>结算日期：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csrSettleTimeEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结算日期字段的查询代码结束*****************")}
			
			
			${after$csrSettleTime}
			 	
			 ${before$csrEditor}
			 
			 ${lz:set("注释","*****************操作人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrEditor" : (lz:join(relateObject,"$csrEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".csrEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrEditor || #request.defines['csrEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrEditor":relateObject}" id="form-dl-csrEditor">
					<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrEditorYesNot=="not"?"checked-not":""}${data.csrEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrEditor}">
				 					${get:SrvUser(data.csrEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作人字段的查询代码结束*****************")}
			
			
			${after$csrEditor}
			 	
			 ${before$csrAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrAddTime" : (lz:join(relateObject,"$csrAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csrAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrAddTime || #request.defines['csrAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrAddTime":relateObject}" id="form-dl-csrAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrAddTimeYesNot=="not"?"checked-not":""}${data.csrAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csrAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csrAddTime}
			 	
			 ${before$csrMemo}
			 
			 ${lz:set("注释","*****************备注信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrMemo" : (lz:join(relateObject,"$csrMemo")))}
			 ${lz:set("cname",lz:join(relateObject,".csrMemo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrMemo || #request.defines['csrMemo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrMemo":relateObject}" id="form-dl-csrMemo">
					<dt>备注信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrMemoYesNot=="not"?"checked-not":""}${data.csrMemoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrMemoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.csrMemo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注信息字段的查询代码结束*****************")}
			
			
			${after$csrMemo}
			 	
			 ${before$csrStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrStatus" : (lz:join(relateObject,"$csrStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csrStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrStatus || #request.defines['csrStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrStatus":relateObject}" id="form-dl-csrStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrStatusYesNot=="not"?"checked-not":""}${data.csrStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csrStatus==0?"selected":""}>正在维修</option>
							<option value="1" ${data.csrStatus==1?"selected":""}>维修完成</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csrStatus}