<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csuoId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoId" : (lz:join(relateObject,"$csuoId")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoId || #request.defines['csuoId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoId":relateObject}" id="form-dl-csuoId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoIdYesNot=="not"?"checked-not":""}${data.csuoIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csuoId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csuoId}
			 	
			 ${before$csuoHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoHost" : (lz:join(relateObject,"$csuoHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoHost || #request.defines['csuoHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoHost":relateObject}" id="form-dl-csuoHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoHostYesNot=="not"?"checked-not":""}${data.csuoHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csuoHost}">
				 					${get:SrvHost(data.csuoHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csuoHost}
			 	
			 ${before$csuoOrder}
			 
			 ${lz:set("注释","*****************系统订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoOrder" : (lz:join(relateObject,"$csuoOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoOrder || #request.defines['csuoOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoOrder":relateObject}" id="form-dl-csuoOrder">
					<dt>系统订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoOrderYesNot=="not"?"checked-not":""}${data.csuoOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csuoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.csuoOrder).csoId}" value="${data.csuoOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************系统订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuoOrder']>0">
				${lz:set("注释","****当系统订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuoOrder")}
			  	<input type="hidden" name="csuoOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuoOrder}
			 	
			 ${before$csuoSpecial}
			 
			 ${lz:set("注释","*****************专车订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoSpecial" : (lz:join(relateObject,"$csuoSpecial")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoSpecial"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoSpecial || #request.defines['csuoSpecial']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoSpecial":relateObject}" id="form-dl-csuoSpecial">
					<dt>专车订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoSpecialYesNot=="not"?"checked-not":""}${data.csuoSpecialYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoSpecialYesNot}"/>
			 			<input title="请输入专车订单编号进行查询" class="combox" action="${basePath}${proname}/unit/specialcar_query.do?value={param}&csscHost={csuoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsSpecialCar(data.csuoSpecial).csscId}" value="${data.csuoSpecial}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/specialcar_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************专车订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuoSpecial']>0">
				${lz:set("注释","****当专车订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuoSpecial")}
			  	<input type="hidden" name="csuoSpecial$on" id="CsSpecialCar$on" value="true"/>
			  	<jsp:include page="/admin/unit/specialcar.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuoSpecial}
			 	
			 ${before$csuoUnitInfo}
			 
			 ${lz:set("注释","*****************所属单位字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoUnitInfo" : (lz:join(relateObject,"$csuoUnitInfo")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoUnitInfo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoUnitInfo || #request.defines['csuoUnitInfo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoUnitInfo":relateObject}" id="form-dl-csuoUnitInfo">
					<dt>所属单位：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoUnitInfoYesNot=="not"?"checked-not":""}${data.csuoUnitInfoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoUnitInfoYesNot}"/>
			 			<input title="请输入企业信息名称进行查询" class="combox" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csuoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsUnitInfo(data.csuoUnitInfo).csuiName}" value="${data.csuoUnitInfo}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属单位字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuoUnitInfo']>0">
				${lz:set("注释","****当所属单位字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuoUnitInfo")}
			  	<input type="hidden" name="csuoUnitInfo$on" id="CsUnitInfo$on" value="true"/>
			  	<jsp:include page="/admin/unit/info.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuoUnitInfo}
			 	
			 ${before$csuoUnitGroup}
			 
			 ${lz:set("注释","*****************所属分组字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoUnitGroup" : (lz:join(relateObject,"$csuoUnitGroup")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoUnitGroup"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoUnitGroup || #request.defines['csuoUnitGroup']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoUnitGroup":relateObject}" id="form-dl-csuoUnitGroup">
					<dt>所属分组：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoUnitGroupYesNot=="not"?"checked-not":""}${data.csuoUnitGroupYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoUnitGroupYesNot}"/>
			 			<input title="请输入分组信息组名称进行查询" class="combox" action="${basePath}${proname}/unit/group_query.do?value={param}&csugHost={csuoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsUnitGroup(data.csuoUnitGroup).csugName}" value="${data.csuoUnitGroup}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/group_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属分组字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuoUnitGroup']>0">
				${lz:set("注释","****当所属分组字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuoUnitGroup")}
			  	<input type="hidden" name="csuoUnitGroup$on" id="CsUnitGroup$on" value="true"/>
			  	<jsp:include page="/admin/unit/group.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuoUnitGroup}
			 	
			 ${before$csuoUnitMember}
			 
			 ${lz:set("注释","*****************支付帐号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoUnitMember" : (lz:join(relateObject,"$csuoUnitMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoUnitMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoUnitMember || #request.defines['csuoUnitMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoUnitMember":relateObject}" id="form-dl-csuoUnitMember">
					<dt>支付帐号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoUnitMemberYesNot=="not"?"checked-not":""}${data.csuoUnitMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoUnitMemberYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/user/member_query.do?size=-1&csmHost={csuoHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csuoUnitMember}">
				 					${get:CsMember(data.csuoUnitMember).csmName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************支付帐号字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuoUnitMember']>0">
				${lz:set("注释","****当支付帐号字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuoUnitMember")}
			  	<input type="hidden" name="csuoUnitMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuoUnitMember}
			 	
			 ${before$csuoUnitChild}
			 
			 ${lz:set("注释","*****************使用帐号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoUnitChild" : (lz:join(relateObject,"$csuoUnitChild")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoUnitChild"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoUnitChild || #request.defines['csuoUnitChild']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoUnitChild":relateObject}" id="form-dl-csuoUnitChild">
					<dt>使用帐号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoUnitChildYesNot=="not"?"checked-not":""}${data.csuoUnitChildYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoUnitChildYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/unit/info_room.do?value={param}&unit={csuoUnitInfo}&csmHost={csuoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csuoUnitChild).csmName}" value="${data.csuoUnitChild}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************使用帐号字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuoUnitChild']>0">
				${lz:set("注释","****当使用帐号字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuoUnitChild")}
			  	<input type="hidden" name="csuoUnitChild$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuoUnitChild}
			 	
			 ${before$csuoCar}
			 
			 ${lz:set("注释","*****************预订车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoCar" : (lz:join(relateObject,"$csuoCar")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoCar || #request.defines['csuoCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoCar":relateObject}" id="form-dl-csuoCar">
					<dt>预订车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoCarYesNot=="not"?"checked-not":""}${data.csuoCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoCarYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csuoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.csuoCar).cscNumber}" value="${data.csuoCar}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************预订车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuoCar']>0">
				${lz:set("注释","****当预订车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuoCar")}
			  	<input type="hidden" name="csuoCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuoCar}
			 	
			 ${before$csuoNumber}
			 
			 ${lz:set("注释","*****************车牌号码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoNumber" : (lz:join(relateObject,"$csuoNumber")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoNumber"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoNumber || #request.defines['csuoNumber']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoNumber":relateObject}" id="form-dl-csuoNumber">
					<dt>车牌号码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoNumberYesNot=="not"?"checked-not":""}${data.csuoNumberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoNumberYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csuoNumber}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车牌号码字段的查询代码结束*****************")}
			
			
			${after$csuoNumber}
			 	
			 ${before$csuoType}
			 
			 ${lz:set("注释","*****************订单类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoType" : (lz:join(relateObject,"$csuoType")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoType || #request.defines['csuoType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoType":relateObject}" id="form-dl-csuoType">
					<dt>订单类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoTypeYesNot=="not"?"checked-not":""}${data.csuoTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csuoType==0?"selected":""}>代理订单</option>
							<option value="1" ${data.csuoType==1?"selected":""}>公务用车</option>
							<option value="2" ${data.csuoType==2?"selected":""}>紧急公务</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单类型字段的查询代码结束*****************")}
			
			
			${after$csuoType}
			 	
			 ${before$csuoPerson}
			 
			 ${lz:set("注释","*****************用车人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoPerson" : (lz:join(relateObject,"$csuoPerson")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoPerson"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoPerson || #request.defines['csuoPerson']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoPerson":relateObject}" id="form-dl-csuoPerson">
					<dt>用车人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoPersonYesNot=="not"?"checked-not":""}${data.csuoPersonYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoPersonYesNot}"/>
			 			<input title="请输入用车人员姓名进行查询" class="combox" action="${basePath}${proname}/unit/person_query.do?value={param}&csupHost={csuoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsUnitPerson(data.csuoPerson).csupName}" value="${data.csuoPerson}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/person_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************用车人员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuoPerson']>0">
				${lz:set("注释","****当用车人员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuoPerson")}
			  	<input type="hidden" name="csuoPerson$on" id="CsUnitPerson$on" value="true"/>
			  	<jsp:include page="/admin/unit/person.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuoPerson}
			 	
			 ${before$csuoUsername}
			 
			 ${lz:set("注释","*****************使用人姓名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoUsername" : (lz:join(relateObject,"$csuoUsername")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoUsername"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoUsername || #request.defines['csuoUsername']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoUsername":relateObject}" id="form-dl-csuoUsername">
					<dt>使用人姓名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoUsernameYesNot=="not"?"checked-not":""}${data.csuoUsernameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoUsernameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csuoUsername}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************使用人姓名字段的查询代码结束*****************")}
			
			
			${after$csuoUsername}
			 	
			 ${before$csuoMobile}
			 
			 ${lz:set("注释","*****************使用人手机字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoMobile" : (lz:join(relateObject,"$csuoMobile")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoMobile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoMobile || #request.defines['csuoMobile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoMobile":relateObject}" id="form-dl-csuoMobile">
					<dt>使用人手机：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoMobileYesNot=="not"?"checked-not":""}${data.csuoMobileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoMobileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csuoMobile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************使用人手机字段的查询代码结束*****************")}
			
			
			${after$csuoMobile}
			 	
			 ${before$csuoProfile}
			 
			 ${lz:set("注释","*****************订单摘要字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoProfile" : (lz:join(relateObject,"$csuoProfile")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoProfile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoProfile || #request.defines['csuoProfile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoProfile":relateObject}" id="form-dl-csuoProfile">
					<dt>订单摘要：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoProfileYesNot=="not"?"checked-not":""}${data.csuoProfileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoProfileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csuoProfile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单摘要字段的查询代码结束*****************")}
			
			
			${after$csuoProfile}
			 	
			 ${before$csuoStartTime}
			 
			 ${lz:set("注释","*****************订单开始时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoStartTime" : (lz:join(relateObject,"$csuoStartTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoStartTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoStartTime || #request.defines['csuoStartTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoStartTime":relateObject}" id="form-dl-csuoStartTime">
					<dt>订单开始时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoStartTimeYesNot=="not"?"checked-not":""}${data.csuoStartTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoStartTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuoStartTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuoStartTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuoStartTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuoStartTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>订单开始时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuoStartTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单开始时间字段的查询代码结束*****************")}
			
			
			${after$csuoStartTime}
			 	
			 ${before$csuoFinishTime}
			 
			 ${lz:set("注释","*****************订单结束时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoFinishTime" : (lz:join(relateObject,"$csuoFinishTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoFinishTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoFinishTime || #request.defines['csuoFinishTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoFinishTime":relateObject}" id="form-dl-csuoFinishTime">
					<dt>订单结束时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoFinishTimeYesNot=="not"?"checked-not":""}${data.csuoFinishTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoFinishTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuoFinishTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuoFinishTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuoFinishTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuoFinishTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>订单结束时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuoFinishTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单结束时间字段的查询代码结束*****************")}
			
			
			${after$csuoFinishTime}
			 	
			 ${before$csuoRetTime}
			 
			 ${lz:set("注释","*****************实际还车时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoRetTime" : (lz:join(relateObject,"$csuoRetTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoRetTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoRetTime || #request.defines['csuoRetTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoRetTime":relateObject}" id="form-dl-csuoRetTime">
					<dt>实际还车时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoRetTimeYesNot=="not"?"checked-not":""}${data.csuoRetTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoRetTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuoRetTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuoRetTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuoRetTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuoRetTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>实际还车时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuoRetTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************实际还车时间字段的查询代码结束*****************")}
			
			
			${after$csuoRetTime}
			 	
			 ${before$csuoRemark}
			 
			 ${lz:set("注释","*****************订单备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoRemark" : (lz:join(relateObject,"$csuoRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoRemark || #request.defines['csuoRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoRemark":relateObject}" id="form-dl-csuoRemark">
					<dt>订单备注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoRemarkYesNot=="not"?"checked-not":""}${data.csuoRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csuoRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单备注字段的查询代码结束*****************")}
			
			
			${after$csuoRemark}
			 	
			 ${before$csuoAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoAddTime" : (lz:join(relateObject,"$csuoAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoAddTime || #request.defines['csuoAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoAddTime":relateObject}" id="form-dl-csuoAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoAddTimeYesNot=="not"?"checked-not":""}${data.csuoAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuoAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuoAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuoAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuoAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuoAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csuoAddTime}
			 	
			 ${before$csuoUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoUpdateTime" : (lz:join(relateObject,"$csuoUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoUpdateTime || #request.defines['csuoUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoUpdateTime":relateObject}" id="form-dl-csuoUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoUpdateTimeYesNot=="not"?"checked-not":""}${data.csuoUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuoUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuoUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuoUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuoUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuoUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csuoUpdateTime}
			 	
			 ${before$csuoSettleState}
			 
			 ${lz:set("注释","*****************结算状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoSettleState" : (lz:join(relateObject,"$csuoSettleState")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoSettleState"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoSettleState || #request.defines['csuoSettleState']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoSettleState":relateObject}" id="form-dl-csuoSettleState">
					<dt>结算状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoSettleStateYesNot=="not"?"checked-not":""}${data.csuoSettleStateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoSettleStateYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csuoSettleState==0?"selected":""}>未结算</option>
							<option value="1" ${data.csuoSettleState==1?"selected":""}>已结算</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结算状态字段的查询代码结束*****************")}
			
			
			${after$csuoSettleState}
			 	
			 ${before$csuoSettleTime}
			 
			 ${lz:set("注释","*****************结算时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoSettleTime" : (lz:join(relateObject,"$csuoSettleTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoSettleTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoSettleTime || #request.defines['csuoSettleTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoSettleTime":relateObject}" id="form-dl-csuoSettleTime">
					<dt>结算时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoSettleTimeYesNot=="not"?"checked-not":""}${data.csuoSettleTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoSettleTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuoSettleTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuoSettleTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuoSettleTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuoSettleTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>结算时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuoSettleTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结算时间字段的查询代码结束*****************")}
			
			
			${after$csuoSettleTime}
			 	
			 ${before$csuoStatement}
			 
			 ${lz:set("注释","*****************结账单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoStatement" : (lz:join(relateObject,"$csuoStatement")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoStatement"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoStatement || #request.defines['csuoStatement']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoStatement":relateObject}" id="form-dl-csuoStatement">
					<dt>结&nbsp;&nbsp;账&nbsp;&nbsp;单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoStatementYesNot=="not"?"checked-not":""}${data.csuoStatementYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoStatementYesNot}"/>
			 			<input title="请输入企业对账帐单月份进行查询" class="combox" action="${basePath}${proname}/unit/bill_query.do?value={param}&csubHost={csuoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsUnitBill(data.csuoStatement).csubMonth}" value="${data.csuoStatement}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/bill_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结账单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuoStatement']>0">
				${lz:set("注释","****当结账单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuoStatement")}
			  	<input type="hidden" name="csuoStatement$on" id="CsUnitBill$on" value="true"/>
			  	<jsp:include page="/admin/unit/bill.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuoStatement}
			 	
			 ${before$csuoFrom}
			 
			 ${lz:set("注释","*****************订单来源字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoFrom" : (lz:join(relateObject,"$csuoFrom")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoFrom"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoFrom || #request.defines['csuoFrom']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoFrom":relateObject}" id="form-dl-csuoFrom">
					<dt>订单来源：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoFromYesNot=="not"?"checked-not":""}${data.csuoFromYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoFromYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csuoFrom}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单来源字段的查询代码结束*****************")}
			
			
			${after$csuoFrom}
			 	
			 ${before$csuoCheckTime}
			 
			 ${lz:set("注释","*****************审核时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoCheckTime" : (lz:join(relateObject,"$csuoCheckTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoCheckTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoCheckTime || #request.defines['csuoCheckTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoCheckTime":relateObject}" id="form-dl-csuoCheckTime">
					<dt>审核时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoCheckTimeYesNot=="not"?"checked-not":""}${data.csuoCheckTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoCheckTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuoCheckTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuoCheckTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuoCheckTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuoCheckTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>审核时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuoCheckTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************审核时间字段的查询代码结束*****************")}
			
			
			${after$csuoCheckTime}
			 	
			 ${before$csuoChecker}
			 
			 ${lz:set("注释","*****************审核人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoChecker" : (lz:join(relateObject,"$csuoChecker")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoChecker"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoChecker || #request.defines['csuoChecker']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoChecker":relateObject}" id="form-dl-csuoChecker">
					<dt>审&nbsp;&nbsp;核&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoCheckerYesNot=="not"?"checked-not":""}${data.csuoCheckerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoCheckerYesNot}"/>
			 		<input title="请输入动态查询" class="combox" generic="csuoCheckerGeneric" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:generic(data.csuoChecker)}" value="${data.csuoChecker}"/>
			 		${lz:set("models",get:models("SrvUser,CsUnitUser,CsUnitPerson"))}
				 	<select id="csuoCheckerGeneric" style="display:none;">
				 		<option value="">--选择模块--</option>
				 		<s:iterator value="#request.models" id="item" status="i">
				 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
				 		</s:iterator>
				 	</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************审核人字段的查询代码结束*****************")}
			
			
			${after$csuoChecker}
			 	
			 ${before$csuoState}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuoState" : (lz:join(relateObject,"$csuoState")))}
			 ${lz:set("cname",lz:join(relateObject,".csuoState"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuoState || #request.defines['csuoState']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuoState":relateObject}" id="form-dl-csuoState">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuoStateYesNot=="not"?"checked-not":""}${data.csuoStateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuoStateYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csuoState==1?"selected":""}>等待审核</option>
							<option value="2" ${data.csuoState==2?"selected":""}>审核通过</option>
							<option value="3" ${data.csuoState==3?"selected":""}>审核未过</option>
							<option value="4" ${data.csuoState==4?"selected":""}>订单结束</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csuoState}