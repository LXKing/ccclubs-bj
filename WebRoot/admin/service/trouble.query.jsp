<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cstId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstId" : (lz:join(relateObject,"$cstId")))}
			 ${lz:set("cname",lz:join(relateObject,".cstId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstId || #request.defines['cstId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstId":relateObject}" id="form-dl-cstId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstIdYesNot=="not"?"checked-not":""}${data.cstIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cstId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cstId}
			 	
			 ${before$cstHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstHost" : (lz:join(relateObject,"$cstHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cstHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstHost || #request.defines['cstHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstHost":relateObject}" id="form-dl-cstHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstHostYesNot=="not"?"checked-not":""}${data.cstHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cstHost}">
				 					${get:SrvHost(data.cstHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cstHost}
			 	
			 ${before$cstOrder}
			 
			 ${lz:set("注释","*****************事故订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstOrder" : (lz:join(relateObject,"$cstOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".cstOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstOrder || #request.defines['cstOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstOrder":relateObject}" id="form-dl-cstOrder">
					<dt>事故订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstOrderYesNot=="not"?"checked-not":""}${data.cstOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={cstHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.cstOrder).csoId}" value="${data.cstOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************事故订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cstOrder']>0">
				${lz:set("注释","****当事故订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cstOrder")}
			  	<input type="hidden" name="cstOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cstOrder}
			 	
			 ${before$cstMember}
			 
			 ${lz:set("注释","*****************事故会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstMember" : (lz:join(relateObject,"$cstMember")))}
			 ${lz:set("cname",lz:join(relateObject,".cstMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstMember || #request.defines['cstMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstMember":relateObject}" id="form-dl-cstMember">
					<dt>事故会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstMemberYesNot=="not"?"checked-not":""}${data.cstMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={cstHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.cstMember).csmName}" value="${data.cstMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************事故会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cstMember']>0">
				${lz:set("注释","****当事故会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cstMember")}
			  	<input type="hidden" name="cstMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cstMember}
			 	
			 ${before$cstCar}
			 
			 ${lz:set("注释","*****************事故车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstCar" : (lz:join(relateObject,"$cstCar")))}
			 ${lz:set("cname",lz:join(relateObject,".cstCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstCar || #request.defines['cstCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstCar":relateObject}" id="form-dl-cstCar">
					<dt>事故车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstCarYesNot=="not"?"checked-not":""}${data.cstCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstCarYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cstHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.cstCar).cscNumber}" value="${data.cstCar}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************事故车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cstCar']>0">
				${lz:set("注释","****当事故车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cstCar")}
			  	<input type="hidden" name="cstCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cstCar}
			 	
			 ${before$cstRepairAdr}
			 
			 ${lz:set("注释","*****************送修地点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstRepairAdr" : (lz:join(relateObject,"$cstRepairAdr")))}
			 ${lz:set("cname",lz:join(relateObject,".cstRepairAdr"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstRepairAdr || #request.defines['cstRepairAdr']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstRepairAdr":relateObject}" id="form-dl-cstRepairAdr">
					<dt>送修地点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstRepairAdrYesNot=="not"?"checked-not":""}${data.cstRepairAdrYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstRepairAdrYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", lz:query$$("ccclubs_system","select * from cs_repair_adr where 1=1  and (csra_host in (?) or ? is null) ",ccclubs_login.suHost,ccclubs_login.suHost) )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.csraId}" ${data.cstRepairAdr==item.csraId?"selected":""}>${item.csraName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/repairadr_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************送修地点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cstRepairAdr']>0">
				${lz:set("注释","****当送修地点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cstRepairAdr")}
			  	<input type="hidden" name="cstRepairAdr$on" id="CsRepairAdr$on" value="true"/>
			  	<jsp:include page="/admin/service/repairadr.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cstRepairAdr}
			 	
			 ${before$cstRepair}
			 
			 ${lz:set("注释","*****************维修详细信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstRepair" : (lz:join(relateObject,"$cstRepair")))}
			 ${lz:set("cname",lz:join(relateObject,".cstRepair"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstRepair || #request.defines['cstRepair']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstRepair":relateObject}" id="form-dl-cstRepair">
					<dt>维修详细信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstRepairYesNot=="not"?"checked-not":""}${data.cstRepairYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstRepairYesNot}"/>
			 			<input title="请输入车辆维修报修描述进行查询" class="combox" action="${basePath}${proname}/service/repair_query.do?value={param}&csrHost={cstHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsRepair(data.cstRepair).csrProfile}" value="${data.cstRepair}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/repair_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************维修详细信息字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cstRepair']>0">
				${lz:set("注释","****当维修详细信息字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cstRepair")}
			  	<input type="hidden" name="cstRepair$on" id="CsRepair$on" value="true"/>
			  	<jsp:include page="/admin/service/repair.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cstRepair}
			 	
			 ${before$cstRepayDate}
			 
			 ${lz:set("注释","*****************返还垫付时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstRepayDate" : (lz:join(relateObject,"$cstRepayDate")))}
			 ${lz:set("cname",lz:join(relateObject,".cstRepayDate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstRepayDate || #request.defines['cstRepayDate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstRepayDate":relateObject}" id="form-dl-cstRepayDate">
					<dt>返还垫付时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstRepayDateYesNot=="not"?"checked-not":""}${data.cstRepayDateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstRepayDateYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cstRepayDateStart,"yyyy-MM-dd")}" end="${lz:date(data.cstRepayDateEnd,"yyyy-MM-dd")}" exp="${data.cstRepayDateExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cstRepayDateStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>返还垫付时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cstRepayDateEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************返还垫付时间字段的查询代码结束*****************")}
			
			
			${after$cstRepayDate}
			 	
			 ${before$cstRepayType}
			 
			 ${lz:set("注释","*****************返还垫付方式字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstRepayType" : (lz:join(relateObject,"$cstRepayType")))}
			 ${lz:set("cname",lz:join(relateObject,".cstRepayType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstRepayType || #request.defines['cstRepayType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstRepayType":relateObject}" id="form-dl-cstRepayType">
					<dt>返还垫付方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstRepayTypeYesNot=="not"?"checked-not":""}${data.cstRepayTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstRepayTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cstRepayType==0?"selected":""}>银行转帐</option>
							<option value="1" ${data.cstRepayType==1?"selected":""}>返还保证金</option>
							<option value="2" ${data.cstRepayType==2?"selected":""}>返还现金</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************返还垫付方式字段的查询代码结束*****************")}
			
			
			${after$cstRepayType}
			 	
			 ${before$cstCompDate}
			 
			 ${lz:set("注释","*****************赔付到账时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstCompDate" : (lz:join(relateObject,"$cstCompDate")))}
			 ${lz:set("cname",lz:join(relateObject,".cstCompDate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstCompDate || #request.defines['cstCompDate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstCompDate":relateObject}" id="form-dl-cstCompDate">
					<dt>赔付到账时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstCompDateYesNot=="not"?"checked-not":""}${data.cstCompDateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstCompDateYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cstCompDateStart,"yyyy-MM-dd")}" end="${lz:date(data.cstCompDateEnd,"yyyy-MM-dd")}" exp="${data.cstCompDateExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cstCompDateStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>赔付到账时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cstCompDateEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************赔付到账时间字段的查询代码结束*****************")}
			
			
			${after$cstCompDate}
			 	
			 ${before$cstHappenTime}
			 
			 ${lz:set("注释","*****************发生时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstHappenTime" : (lz:join(relateObject,"$cstHappenTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cstHappenTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstHappenTime || #request.defines['cstHappenTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstHappenTime":relateObject}" id="form-dl-cstHappenTime">
					<dt>发生时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstHappenTimeYesNot=="not"?"checked-not":""}${data.cstHappenTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstHappenTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cstHappenTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cstHappenTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cstHappenTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cstHappenTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>发生时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cstHappenTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发生时间字段的查询代码结束*****************")}
			
			
			${after$cstHappenTime}
			 	
			 ${before$cstNotice}
			 
			 ${lz:set("注释","*****************客服通知字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstNotice" : (lz:join(relateObject,"$cstNotice")))}
			 ${lz:set("cname",lz:join(relateObject,".cstNotice"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstNotice || #request.defines['cstNotice']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstNotice":relateObject}" id="form-dl-cstNotice">
					<dt>客服通知：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstNoticeYesNot=="not"?"checked-not":""}${data.cstNoticeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstNoticeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cstNotice==0?"selected":""}>未通知</option>
							<option value="1" ${data.cstNotice==1?"selected":""}>已通知</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************客服通知字段的查询代码结束*****************")}
			
			
			${after$cstNotice}
			 	
			 ${before$cstDealFlag}
			 
			 ${lz:set("注释","*****************受理标记字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstDealFlag" : (lz:join(relateObject,"$cstDealFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".cstDealFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstDealFlag || #request.defines['cstDealFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstDealFlag":relateObject}" id="form-dl-cstDealFlag">
					<dt>受理标记：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstDealFlagYesNot=="not"?"checked-not":""}${data.cstDealFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstDealFlagYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cstDealFlag==0?"selected":""}>默认</option>
							<option value="1" ${data.cstDealFlag==1?"selected":""}>配合</option>
							<option value="2" ${data.cstDealFlag==2?"selected":""}>不配合</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************受理标记字段的查询代码结束*****************")}
			
			
			${after$cstDealFlag}
			 	
			 ${before$cstAccepter}
			 
			 ${lz:set("注释","*****************受理人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstAccepter" : (lz:join(relateObject,"$cstAccepter")))}
			 ${lz:set("cname",lz:join(relateObject,".cstAccepter"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstAccepter || #request.defines['cstAccepter']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstAccepter":relateObject}" id="form-dl-cstAccepter">
					<dt>受理人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstAccepterYesNot=="not"?"checked-not":""}${data.cstAccepterYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstAccepterYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cstAccepter}">
				 					${get:SrvUser(data.cstAccepter).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************受理人员字段的查询代码结束*****************")}
			
			
			${after$cstAccepter}
			 	
			 ${before$cstCaser}
			 
			 ${lz:set("注释","*****************结案人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstCaser" : (lz:join(relateObject,"$cstCaser")))}
			 ${lz:set("cname",lz:join(relateObject,".cstCaser"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstCaser || #request.defines['cstCaser']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstCaser":relateObject}" id="form-dl-cstCaser">
					<dt>结案人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstCaserYesNot=="not"?"checked-not":""}${data.cstCaserYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstCaserYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cstCaser}">
				 					${get:SrvUser(data.cstCaser).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结案人员字段的查询代码结束*****************")}
			
			
			${after$cstCaser}
			 	
			 ${before$cstFinishTime}
			 
			 ${lz:set("注释","*****************结案时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstFinishTime" : (lz:join(relateObject,"$cstFinishTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cstFinishTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstFinishTime || #request.defines['cstFinishTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstFinishTime":relateObject}" id="form-dl-cstFinishTime">
					<dt>结案时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstFinishTimeYesNot=="not"?"checked-not":""}${data.cstFinishTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstFinishTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cstFinishTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cstFinishTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cstFinishTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cstFinishTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>结案时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cstFinishTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结案时间字段的查询代码结束*****************")}
			
			
			${after$cstFinishTime}
			 	
			 ${before$cstUpdateTime}
			 
			 ${lz:set("注释","*****************更新时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstUpdateTime" : (lz:join(relateObject,"$cstUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cstUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstUpdateTime || #request.defines['cstUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstUpdateTime":relateObject}" id="form-dl-cstUpdateTime">
					<dt>更新时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstUpdateTimeYesNot=="not"?"checked-not":""}${data.cstUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cstUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cstUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cstUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cstUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>更新时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cstUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************更新时间字段的查询代码结束*****************")}
			
			
			${after$cstUpdateTime}
			 	
			 ${before$cstAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstAddTime" : (lz:join(relateObject,"$cstAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cstAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstAddTime || #request.defines['cstAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstAddTime":relateObject}" id="form-dl-cstAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstAddTimeYesNot=="not"?"checked-not":""}${data.cstAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cstAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cstAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cstAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cstAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cstAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cstAddTime}
			 	
			 ${before$cstStatus}
			 
			 ${lz:set("注释","*****************处理状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstStatus" : (lz:join(relateObject,"$cstStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cstStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstStatus || #request.defines['cstStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstStatus":relateObject}" id="form-dl-cstStatus">
					<dt>处理状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstStatusYesNot=="not"?"checked-not":""}${data.cstStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cstStatus==0?"selected":""}>已录入待定损</option>
							<option value="1" ${data.cstStatus==1?"selected":""}>已定损待协商</option>
							<option value="2" ${data.cstStatus==2?"selected":""}>已协商待维修</option>
							<option value="3" ${data.cstStatus==3?"selected":""}>正在维修</option>
							<option value="4" ${data.cstStatus==4?"selected":""}>已维修待扣款</option>
							<option value="5" ${data.cstStatus==5?"selected":""}>已扣款待结案</option>
							<option value="6" ${data.cstStatus==6?"selected":""}>已结案</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************处理状态字段的查询代码结束*****************")}
			
			
			${after$cstStatus}