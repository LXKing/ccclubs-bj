<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cslodId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslodId" : (lz:join(relateObject,"$cslodId")))}
			 ${lz:set("cname",lz:join(relateObject,".cslodId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslodId || #request.defines['cslodId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslodId":relateObject}" id="form-dl-cslodId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslodIdYesNot=="not"?"checked-not":""}${data.cslodIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslodIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cslodId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cslodId}
			 	
			 ${before$cslodHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslodHost" : (lz:join(relateObject,"$cslodHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cslodHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslodHost || #request.defines['cslodHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslodHost":relateObject}" id="form-dl-cslodHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslodHostYesNot=="not"?"checked-not":""}${data.cslodHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslodHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cslodHost}">
				 					${get:SrvHost(data.cslodHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cslodHost}
			 	
			 ${before$cslodLongOrder}
			 
			 ${lz:set("注释","*****************所属订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslodLongOrder" : (lz:join(relateObject,"$cslodLongOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".cslodLongOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslodLongOrder || #request.defines['cslodLongOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslodLongOrder":relateObject}" id="form-dl-cslodLongOrder">
					<dt>所属订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslodLongOrderYesNot=="not"?"checked-not":""}${data.cslodLongOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslodLongOrderYesNot}"/>
			 			<input title="请输入长租订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/longorder_query.do?value={param}&csloHost={cslodHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsLongOrder(data.cslodLongOrder).csloId}" value="${data.cslodLongOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/longorder_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cslodLongOrder']>0">
				${lz:set("注释","****当所属订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cslodLongOrder")}
			  	<input type="hidden" name="cslodLongOrder$on" id="CsLongOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/longorder.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cslodLongOrder}
			 	
			 ${before$cslodCar}
			 
			 ${lz:set("注释","*****************预订车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslodCar" : (lz:join(relateObject,"$cslodCar")))}
			 ${lz:set("cname",lz:join(relateObject,".cslodCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslodCar || #request.defines['cslodCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslodCar":relateObject}" id="form-dl-cslodCar">
					<dt>预订车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslodCarYesNot=="not"?"checked-not":""}${data.cslodCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslodCarYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cslodHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.cslodCar).cscNumber}" value="${data.cslodCar}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************预订车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cslodCar']>0">
				${lz:set("注释","****当预订车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cslodCar")}
			  	<input type="hidden" name="cslodCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cslodCar}
			 	
			 ${before$cslodSetTime}
			 
			 ${lz:set("注释","*****************车辆交付时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslodSetTime" : (lz:join(relateObject,"$cslodSetTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cslodSetTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslodSetTime || #request.defines['cslodSetTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslodSetTime":relateObject}" id="form-dl-cslodSetTime">
					<dt>车辆交付时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslodSetTimeYesNot=="not"?"checked-not":""}${data.cslodSetTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslodSetTimeYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cslodSetTimeStart,"yyyy-MM-dd")}" end="${lz:date(data.cslodSetTimeEnd,"yyyy-MM-dd")}" exp="${data.cslodSetTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cslodSetTimeStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>车辆交付时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cslodSetTimeEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车辆交付时间字段的查询代码结束*****************")}
			
			
			${after$cslodSetTime}
			 	
			 ${before$cslodRetTime}
			 
			 ${lz:set("注释","*****************车辆归还时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslodRetTime" : (lz:join(relateObject,"$cslodRetTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cslodRetTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslodRetTime || #request.defines['cslodRetTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslodRetTime":relateObject}" id="form-dl-cslodRetTime">
					<dt>车辆归还时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslodRetTimeYesNot=="not"?"checked-not":""}${data.cslodRetTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslodRetTimeYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cslodRetTimeStart,"yyyy-MM-dd")}" end="${lz:date(data.cslodRetTimeEnd,"yyyy-MM-dd")}" exp="${data.cslodRetTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cslodRetTimeStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>车辆归还时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cslodRetTimeEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车辆归还时间字段的查询代码结束*****************")}
			
			
			${after$cslodRetTime}
			 	
			 ${before$cslodUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslodUpdateTime" : (lz:join(relateObject,"$cslodUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cslodUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslodUpdateTime || #request.defines['cslodUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslodUpdateTime":relateObject}" id="form-dl-cslodUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslodUpdateTimeYesNot=="not"?"checked-not":""}${data.cslodUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslodUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cslodUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cslodUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cslodUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cslodUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cslodUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cslodUpdateTime}
			 	
			 ${before$cslodAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslodAddTime" : (lz:join(relateObject,"$cslodAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cslodAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslodAddTime || #request.defines['cslodAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslodAddTime":relateObject}" id="form-dl-cslodAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslodAddTimeYesNot=="not"?"checked-not":""}${data.cslodAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslodAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cslodAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cslodAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cslodAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cslodAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cslodAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cslodAddTime}
			 	
			 ${before$cslodEditor}
			 
			 ${lz:set("注释","*****************操作人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslodEditor" : (lz:join(relateObject,"$cslodEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".cslodEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslodEditor || #request.defines['cslodEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslodEditor":relateObject}" id="form-dl-cslodEditor">
					<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslodEditorYesNot=="not"?"checked-not":""}${data.cslodEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslodEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cslodEditor}">
				 					${get:SrvUser(data.cslodEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作人字段的查询代码结束*****************")}
			
			
			${after$cslodEditor}
			 	
			 ${before$cslodRemark}
			 
			 ${lz:set("注释","*****************备注信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslodRemark" : (lz:join(relateObject,"$cslodRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".cslodRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslodRemark || #request.defines['cslodRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslodRemark":relateObject}" id="form-dl-cslodRemark">
					<dt>备注信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslodRemarkYesNot=="not"?"checked-not":""}${data.cslodRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslodRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.cslodRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注信息字段的查询代码结束*****************")}
			
			
			${after$cslodRemark}
			 	
			 ${before$cslodStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslodStatus" : (lz:join(relateObject,"$cslodStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cslodStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslodStatus || #request.defines['cslodStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslodStatus":relateObject}" id="form-dl-cslodStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslodStatusYesNot=="not"?"checked-not":""}${data.cslodStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslodStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cslodStatus==0?"selected":""}>待交付</option>
							<option value="1" ${data.cslodStatus==1?"selected":""}>使用中</option>
							<option value="2" ${data.cslodStatus==2?"selected":""}>已结束</option>
							<option value="3" ${data.cslodStatus==3?"selected":""}>已作废</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cslodStatus}