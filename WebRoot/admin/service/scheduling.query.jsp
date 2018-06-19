<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cssId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssId" : (lz:join(relateObject,"$cssId")))}
			 ${lz:set("cname",lz:join(relateObject,".cssId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssId || #request.defines['cssId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssId":relateObject}" id="form-dl-cssId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssIdYesNot=="not"?"checked-not":""}${data.cssIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cssId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cssId}
			 	
			 ${before$cssHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssHost" : (lz:join(relateObject,"$cssHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cssHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssHost || #request.defines['cssHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssHost":relateObject}" id="form-dl-cssHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssHostYesNot=="not"?"checked-not":""}${data.cssHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cssHost}">
				 					${get:SrvHost(data.cssHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cssHost}
			 	
			 ${before$cssOutlets}
			 
			 ${lz:set("注释","*****************网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssOutlets" : (lz:join(relateObject,"$cssOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".cssOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssOutlets || #request.defines['cssOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssOutlets":relateObject}" id="form-dl-cssOutlets">
					<dt>网　　点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssOutletsYesNot=="not"?"checked-not":""}${data.cssOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssOutletsYesNot}"/>
			 			<input title="请输入网点名称进行查询" class="combox" action="${basePath}${proname}/object/outlets_query.do?value={param}&csoHost={cssHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOutlets(data.cssOutlets).csoName}" value="${data.cssOutlets}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cssOutlets']>0">
				${lz:set("注释","****当网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cssOutlets")}
			  	<input type="hidden" name="cssOutlets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cssOutlets}
			 	
			 ${before$cssCar}
			 
			 ${lz:set("注释","*****************车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssCar" : (lz:join(relateObject,"$cssCar")))}
			 ${lz:set("cname",lz:join(relateObject,".cssCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssCar || #request.defines['cssCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssCar":relateObject}" id="form-dl-cssCar">
					<dt>车　　辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssCarYesNot=="not"?"checked-not":""}${data.cssCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssCarYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cssHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.cssCar).cscNumber}" value="${data.cssCar}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cssCar']>0">
				${lz:set("注释","****当车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cssCar")}
			  	<input type="hidden" name="cssCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cssCar}
			 	
			 ${before$cssTime}
			 
			 ${lz:set("注释","*****************时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssTime" : (lz:join(relateObject,"$cssTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cssTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssTime || #request.defines['cssTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssTime":relateObject}" id="form-dl-cssTime">
					<dt>时　　间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssTimeYesNot=="not"?"checked-not":""}${data.cssTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cssTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cssTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cssTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cssTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cssTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************时间字段的查询代码结束*****************")}
			
			
			${after$cssTime}
			 	
			 ${before$cssDir}
			 
			 ${lz:set("注释","*****************方向字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssDir" : (lz:join(relateObject,"$cssDir")))}
			 ${lz:set("cname",lz:join(relateObject,".cssDir"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssDir || #request.defines['cssDir']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssDir":relateObject}" id="form-dl-cssDir">
					<dt>方　　向：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssDirYesNot=="not"?"checked-not":""}${data.cssDirYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssDirYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cssDir==0?"selected":""}>调入</option>
							<option value="1" ${data.cssDir==1?"selected":""}>调出</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************方向字段的查询代码结束*****************")}
			
			
			${after$cssDir}
			 	
			 ${before$cssExplain}
			 
			 ${lz:set("注释","*****************说明字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssExplain" : (lz:join(relateObject,"$cssExplain")))}
			 ${lz:set("cname",lz:join(relateObject,".cssExplain"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssExplain || #request.defines['cssExplain']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssExplain":relateObject}" id="form-dl-cssExplain">
					<dt>说　　明：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssExplainYesNot=="not"?"checked-not":""}${data.cssExplainYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssExplainYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cssExplain}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************说明字段的查询代码结束*****************")}
			
			
			${after$cssExplain}
			 	
			 ${before$cssOrder}
			 
			 ${lz:set("注释","*****************订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssOrder" : (lz:join(relateObject,"$cssOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".cssOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssOrder || #request.defines['cssOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssOrder":relateObject}" id="form-dl-cssOrder">
					<dt>订　　单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssOrderYesNot=="not"?"checked-not":""}${data.cssOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={cssHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.cssOrder).csoId}" value="${data.cssOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cssOrder']>0">
				${lz:set("注释","****当订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cssOrder")}
			  	<input type="hidden" name="cssOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cssOrder}
			 	
			 ${before$cssOperator}
			 
			 ${lz:set("注释","*****************操作人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssOperator" : (lz:join(relateObject,"$cssOperator")))}
			 ${lz:set("cname",lz:join(relateObject,".cssOperator"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssOperator || #request.defines['cssOperator']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssOperator":relateObject}" id="form-dl-cssOperator">
					<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssOperatorYesNot=="not"?"checked-not":""}${data.cssOperatorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssOperatorYesNot}"/>
			 		<input title="请输入动态查询" class="combox" generic="cssOperatorGeneric" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:generic(data.cssOperator)}" value="${data.cssOperator}"/>
			 		${lz:set("models",get:models("SrvUser,CsMember"))}
				 	<select id="cssOperatorGeneric" style="display:none;">
				 		<option value="">--选择模块--</option>
				 		<s:iterator value="#request.models" id="item" status="i">
				 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
				 		</s:iterator>
				 	</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作人字段的查询代码结束*****************")}
			
			
			${after$cssOperator}
			 	
			 ${before$cssStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssStatus" : (lz:join(relateObject,"$cssStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cssStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssStatus || #request.defines['cssStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssStatus":relateObject}" id="form-dl-cssStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssStatusYesNot=="not"?"checked-not":""}${data.cssStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cssStatus==0?"selected":""}>待调</option>
							<option value="1" ${data.cssStatus==1?"selected":""}>已调</option>
							<option value="2" ${data.cssStatus==2?"selected":""}>取消</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cssStatus}