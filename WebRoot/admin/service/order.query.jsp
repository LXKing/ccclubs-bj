<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csoId}
			 
			 ${lz:set("注释","*****************订单编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoId" : (lz:join(relateObject,"$csoId")))}
			 ${lz:set("cname",lz:join(relateObject,".csoId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoId || #request.defines['csoId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoId":relateObject}" id="form-dl-csoId">
					<dt>订单编号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoIdYesNot=="not"?"checked-not":""}${data.csoIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csoId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单编号字段的查询代码结束*****************")}
			
			
			${after$csoId}
			 	
			 ${before$csoHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoHost" : (lz:join(relateObject,"$csoHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csoHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoHost || #request.defines['csoHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoHost":relateObject}" id="form-dl-csoHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoHostYesNot=="not"?"checked-not":""}${data.csoHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csoHost}">
				 					${get:SrvHost(data.csoHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csoHost}
			 	
			 ${before$csoPayMember}
			 
			 ${lz:set("注释","*****************支付会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoPayMember" : (lz:join(relateObject,"$csoPayMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csoPayMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoPayMember || #request.defines['csoPayMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoPayMember":relateObject}" id="form-dl-csoPayMember">
					<dt>支付会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoPayMemberYesNot=="not"?"checked-not":""}${data.csoPayMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoPayMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csoPayMember).csmName}" value="${data.csoPayMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************支付会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoPayMember']>0">
				${lz:set("注释","****当支付会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoPayMember")}
			  	<input type="hidden" name="csoPayMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoPayMember}
			 	
			 ${before$csoUseMember}
			 
			 ${lz:set("注释","*****************使用会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoUseMember" : (lz:join(relateObject,"$csoUseMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csoUseMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoUseMember || #request.defines['csoUseMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoUseMember":relateObject}" id="form-dl-csoUseMember">
					<dt>使用会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoUseMemberYesNot=="not"?"checked-not":""}${data.csoUseMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoUseMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csoUseMember).csmName}" value="${data.csoUseMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************使用会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoUseMember']>0">
				${lz:set("注释","****当使用会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoUseMember")}
			  	<input type="hidden" name="csoUseMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoUseMember}
			 	
			 ${before$csoFeeType}
			 
			 ${lz:set("注释","*****************计费类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoFeeType" : (lz:join(relateObject,"$csoFeeType")))}
			 ${lz:set("cname",lz:join(relateObject,".csoFeeType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoFeeType || #request.defines['csoFeeType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoFeeType":relateObject}" id="form-dl-csoFeeType">
					<dt>计费类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoFeeTypeYesNot=="not"?"checked-not":""}${data.csoFeeTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoFeeTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", lz:query("ccclubs_system","select * from cs_user_type where 1=1 ") )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.csutId}" ${data.csoFeeType==item.csutId?"selected":""}>${item.csutName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************计费类型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoFeeType']>0">
				${lz:set("注释","****当计费类型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoFeeType")}
			  	<input type="hidden" name="csoFeeType$on" id="CsUserType$on" value="true"/>
			  	<jsp:include page="/admin/user/type.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoFeeType}
			 	
			 ${before$csoArea}
			 
			 ${lz:set("注释","*****************网点区域字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoArea" : (lz:join(relateObject,"$csoArea")))}
			 ${lz:set("cname",lz:join(relateObject,".csoArea"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoArea || #request.defines['csoArea']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoArea":relateObject}" id="form-dl-csoArea">
					<dt>网点区域：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoAreaYesNot=="not"?"checked-not":""}${data.csoAreaYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoAreaYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", lz:query$$("ccclubs_system","select * from cs_area where 1=1  and (csa_host in (?) or ? is null) ",ccclubs_login.suHost,ccclubs_login.suHost) )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.csaId}" ${data.csoArea==item.csaId?"selected":""}>${item.csaName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/area_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************网点区域字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoArea']>0">
				${lz:set("注释","****当网点区域字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoArea")}
			  	<input type="hidden" name="csoArea$on" id="CsArea$on" value="true"/>
			  	<jsp:include page="/admin/configurator/area.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoArea}
			 	
			 ${before$csoOutlets}
			 
			 ${lz:set("注释","*****************取车网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoOutlets" : (lz:join(relateObject,"$csoOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".csoOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoOutlets || #request.defines['csoOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoOutlets":relateObject}" id="form-dl-csoOutlets">
					<dt>取车网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoOutletsYesNot=="not"?"checked-not":""}${data.csoOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoOutletsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={csoHost}&csoHost={csoHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csoOutlets}">
				 					${get:CsOutlets(data.csoOutlets).csoName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************取车网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoOutlets']>0">
				${lz:set("注释","****当取车网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoOutlets")}
			  	<input type="hidden" name="csoOutlets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoOutlets}
			 	
			 ${before$csoOutletsRet}
			 
			 ${lz:set("注释","*****************还车网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoOutletsRet" : (lz:join(relateObject,"$csoOutletsRet")))}
			 ${lz:set("cname",lz:join(relateObject,".csoOutletsRet"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoOutletsRet || #request.defines['csoOutletsRet']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoOutletsRet":relateObject}" id="form-dl-csoOutletsRet">
					<dt>还车网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoOutletsRetYesNot=="not"?"checked-not":""}${data.csoOutletsRetYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoOutletsRetYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={csoHost}&csoHost={csoHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csoOutletsRet}">
				 					${get:CsOutlets(data.csoOutletsRet).csoName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************还车网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoOutletsRet']>0">
				${lz:set("注释","****当还车网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoOutletsRet")}
			  	<input type="hidden" name="csoOutletsRet$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoOutletsRet}
			 	
			 ${before$csoModel}
			 
			 ${lz:set("注释","*****************预订车型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoModel" : (lz:join(relateObject,"$csoModel")))}
			 ${lz:set("cname",lz:join(relateObject,".csoModel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoModel || #request.defines['csoModel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoModel":relateObject}" id="form-dl-csoModel">
					<dt>预订车型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoModelYesNot=="not"?"checked-not":""}${data.csoModelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoModelYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/carmodel_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csoModel}">
				 					${get:CsCarModel(data.csoModel).cscmName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************预订车型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoModel']>0">
				${lz:set("注释","****当预订车型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoModel")}
			  	<input type="hidden" name="csoModel$on" id="CsCarModel$on" value="true"/>
			  	<jsp:include page="/admin/object/carmodel.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoModel}
			 	
			 ${before$csoCar}
			 
			 ${lz:set("注释","*****************预订车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoCar" : (lz:join(relateObject,"$csoCar")))}
			 ${lz:set("cname",lz:join(relateObject,".csoCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoCar || #request.defines['csoCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoCar":relateObject}" id="form-dl-csoCar">
					<dt>预订车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoCarYesNot=="not"?"checked-not":""}${data.csoCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoCarYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscOutlets={csoOutlets}&cscModel={csoModel}&cscHost={csoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.csoCar).cscNumber}" value="${data.csoCar}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************预订车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoCar']>0">
				${lz:set("注释","****当预订车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoCar")}
			  	<input type="hidden" name="csoCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoCar}
			 	
			 ${before$csoProvid}
			 
			 ${lz:set("注释","*****************车辆商家字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoProvid" : (lz:join(relateObject,"$csoProvid")))}
			 ${lz:set("cname",lz:join(relateObject,".csoProvid"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoProvid || #request.defines['csoProvid']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoProvid":relateObject}" id="form-dl-csoProvid">
					<dt>车辆商家：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoProvidYesNot=="not"?"checked-not":""}${data.csoProvidYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoProvidYesNot}"/>
			 			<input title="请输入供应商名称进行查询" class="combox" action="${basePath}${proname}/configurator/provid_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsProvid(data.csoProvid).cspName}" value="${data.csoProvid}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/provid_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车辆商家字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoProvid']>0">
				${lz:set("注释","****当车辆商家字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoProvid")}
			  	<input type="hidden" name="csoProvid$on" id="CsProvid$on" value="true"/>
			  	<jsp:include page="/admin/configurator/provid.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoProvid}
			 	
			 ${before$csoMobile}
			 
			 ${lz:set("注释","*****************手机号码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoMobile" : (lz:join(relateObject,"$csoMobile")))}
			 ${lz:set("cname",lz:join(relateObject,".csoMobile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoMobile || #request.defines['csoMobile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoMobile":relateObject}" id="form-dl-csoMobile">
					<dt>手机号码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoMobileYesNot=="not"?"checked-not":""}${data.csoMobileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoMobileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csoMobile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************手机号码字段的查询代码结束*****************")}
			
			
			${after$csoMobile}
			 	
			 ${before$csoStartTime}
			 
			 ${lz:set("注释","*****************订单开始时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoStartTime" : (lz:join(relateObject,"$csoStartTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csoStartTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoStartTime || #request.defines['csoStartTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoStartTime":relateObject}" id="form-dl-csoStartTime">
					<dt>订单开始时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoStartTimeYesNot=="not"?"checked-not":""}${data.csoStartTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoStartTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csoStartTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csoStartTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csoStartTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csoStartTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>订单开始时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csoStartTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单开始时间字段的查询代码结束*****************")}
			
			
			${after$csoStartTime}
			 	
			 ${before$csoFinishTime}
			 
			 ${lz:set("注释","*****************订单结束时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoFinishTime" : (lz:join(relateObject,"$csoFinishTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csoFinishTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoFinishTime || #request.defines['csoFinishTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoFinishTime":relateObject}" id="form-dl-csoFinishTime">
					<dt>订单结束时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoFinishTimeYesNot=="not"?"checked-not":""}${data.csoFinishTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoFinishTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csoFinishTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csoFinishTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csoFinishTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csoFinishTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>订单结束时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csoFinishTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单结束时间字段的查询代码结束*****************")}
			
			
			${after$csoFinishTime}
			 	
			 ${before$csoLongOrder}
			 
			 ${lz:set("注释","*****************是否长单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoLongOrder" : (lz:join(relateObject,"$csoLongOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csoLongOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoLongOrder || #request.defines['csoLongOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoLongOrder":relateObject}" id="form-dl-csoLongOrder">
					<dt>是否长单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoLongOrderYesNot=="not"?"checked-not":""}${data.csoLongOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoLongOrderYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csoLongOrder=='true'?"selected":""}>是</option>
							<option value="false" ${data.csoLongOrder=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************是否长单字段的查询代码结束*****************")}
			
			
			${after$csoLongOrder}
			 	
			 ${before$csoUseType}
			 
			 ${lz:set("注释","*****************用车方式字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoUseType" : (lz:join(relateObject,"$csoUseType")))}
			 ${lz:set("cname",lz:join(relateObject,".csoUseType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoUseType || #request.defines['csoUseType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoUseType":relateObject}" id="form-dl-csoUseType">
					<dt>用车方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoUseTypeYesNot=="not"?"checked-not":""}${data.csoUseTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoUseTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csoUseType==0?"selected":""}>原借原还</option>
							<option value="1" ${data.csoUseType==1?"selected":""}>A借B还</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************用车方式字段的查询代码结束*****************")}
			
			
			${after$csoUseType}
			 	
			 ${before$csoRetTime}
			 
			 ${lz:set("注释","*****************实际还车时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoRetTime" : (lz:join(relateObject,"$csoRetTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csoRetTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoRetTime || #request.defines['csoRetTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoRetTime":relateObject}" id="form-dl-csoRetTime">
					<dt>实际还车时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoRetTimeYesNot=="not"?"checked-not":""}${data.csoRetTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoRetTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csoRetTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csoRetTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csoRetTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csoRetTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>实际还车时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csoRetTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************实际还车时间字段的查询代码结束*****************")}
			
			
			${after$csoRetTime}
			 	
			 ${before$csoDuration}
			 
			 ${lz:set("注释","*****************订单时长字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoDuration" : (lz:join(relateObject,"$csoDuration")))}
			 ${lz:set("cname",lz:join(relateObject,".csoDuration"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoDuration || #request.defines['csoDuration']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoDuration":relateObject}" id="form-dl-csoDuration">
					<dt>订单时长：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoDurationYesNot=="not"?"checked-not":""}${data.csoDurationYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoDurationYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csoDurationMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csoDurationMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单时长字段的查询代码结束*****************")}
			
			
			${after$csoDuration}
			 	
			 ${before$csoCreditCard}
			 
			 ${lz:set("注释","*****************使用信用卡字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoCreditCard" : (lz:join(relateObject,"$csoCreditCard")))}
			 ${lz:set("cname",lz:join(relateObject,".csoCreditCard"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoCreditCard || #request.defines['csoCreditCard']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoCreditCard":relateObject}" id="form-dl-csoCreditCard">
					<dt>使用信用卡：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoCreditCardYesNot=="not"?"checked-not":""}${data.csoCreditCardYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoCreditCardYesNot}"/>
			 			<input title="请输入信用卡信用卡号进行查询" class="combox" action="${basePath}${proname}/user/credit/card_query.do?value={param}&csccMember={csoPayMember}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCreditCard(data.csoCreditCard).csccNo}" value="${data.csoCreditCard}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/credit/card_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************使用信用卡字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoCreditCard']>0">
				${lz:set("注释","****当使用信用卡字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoCreditCard")}
			  	<input type="hidden" name="csoCreditCard$on" id="CsCreditCard$on" value="true"/>
			  	<jsp:include page="/admin/user/credit/card.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoCreditCard}
			 	
			 ${before$csoCreditBill}
			 
			 ${lz:set("注释","*****************信用卡帐单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoCreditBill" : (lz:join(relateObject,"$csoCreditBill")))}
			 ${lz:set("cname",lz:join(relateObject,".csoCreditBill"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoCreditBill || #request.defines['csoCreditBill']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoCreditBill":relateObject}" id="form-dl-csoCreditBill">
					<dt>信用卡帐单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoCreditBillYesNot=="not"?"checked-not":""}${data.csoCreditBillYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoCreditBillYesNot}"/>
			 			<input title="请输入信用账单编号进行查询" class="combox" action="${basePath}${proname}/finance/credit/bill_query.do?value={param}&cscbMember={csoPayMember}&cscbHost={csoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCreditBill(data.csoCreditBill).cscbId}" value="${data.csoCreditBill}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/finance/credit/bill_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************信用卡帐单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoCreditBill']>0">
				${lz:set("注释","****当信用卡帐单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoCreditBill")}
			  	<input type="hidden" name="csoCreditBill$on" id="CsCreditBill$on" value="true"/>
			  	<jsp:include page="/admin/finance/credit/bill.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoCreditBill}
			 	
			 ${before$csoInsureType}
			 
			 ${lz:set("注释","*****************保险类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoInsureType" : (lz:join(relateObject,"$csoInsureType")))}
			 ${lz:set("cname",lz:join(relateObject,".csoInsureType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoInsureType || #request.defines['csoInsureType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoInsureType":relateObject}" id="form-dl-csoInsureType">
					<dt>保险类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoInsureTypeYesNot=="not"?"checked-not":""}${data.csoInsureTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoInsureTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							    ${lz:set("items", lz:query("ccclubs_system","select * from cs_product where csp_flag='INSURE' or csp_flag='SECURE'") )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.cspId}" ${data.csoInsureType==item.cspId?"selected":""}>${item.cspName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/product_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************保险类型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoInsureType']>0">
				${lz:set("注释","****当保险类型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoInsureType")}
			  	<input type="hidden" name="csoInsureType$on" id="CsProduct$on" value="true"/>
			  	<jsp:include page="/admin/fee/product.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoInsureType}
			 	
			 ${before$csoIncome}
			 
			 ${lz:set("注释","*****************计入营收字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoIncome" : (lz:join(relateObject,"$csoIncome")))}
			 ${lz:set("cname",lz:join(relateObject,".csoIncome"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoIncome || #request.defines['csoIncome']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoIncome":relateObject}" id="form-dl-csoIncome">
					<dt>计入营收：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoIncomeYesNot=="not"?"checked-not":""}${data.csoIncomeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoIncomeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csoIncome==0?"selected":""}>不计营收</option>
							<option value="1" ${data.csoIncome==1?"selected":""}>计入营收</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************计入营收字段的查询代码结束*****************")}
			
			
			${after$csoIncome}
			 	
			 ${before$csoUseIndex}
			 
			 ${lz:set("注释","*****************订单次数字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoUseIndex" : (lz:join(relateObject,"$csoUseIndex")))}
			 ${lz:set("cname",lz:join(relateObject,".csoUseIndex"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoUseIndex || #request.defines['csoUseIndex']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoUseIndex":relateObject}" id="form-dl-csoUseIndex">
					<dt>订单次数：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoUseIndexYesNot=="not"?"checked-not":""}${data.csoUseIndexYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoUseIndexYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csoUseIndexMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csoUseIndexMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单次数字段的查询代码结束*****************")}
			
			
			${after$csoUseIndex}
			 	
			 ${before$csoFlag}
			 
			 ${lz:set("注释","*****************类型标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoFlag" : (lz:join(relateObject,"$csoFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".csoFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoFlag || #request.defines['csoFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoFlag":relateObject}" id="form-dl-csoFlag">
					<dt>订单类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoFlagYesNot=="not"?"checked-not":""}${data.csoFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoFlagYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csoFlag==0?"selected":""}>默认</option>
							<option value="1" ${data.csoFlag==1?"selected":""}>2014春节订单</option>
							<option value="2" ${data.csoFlag==2?"selected":""}>2015春节订单</option>
							<option value="3" ${data.csoFlag==3?"selected":""}>定单簇</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************类型标识字段的查询代码结束*****************")}
			
			
			${after$csoFlag}
			 	
			 ${before$csoFrom}
			 
			 ${lz:set("注释","*****************订单来源字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoFrom" : (lz:join(relateObject,"$csoFrom")))}
			 ${lz:set("cname",lz:join(relateObject,".csoFrom"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoFrom || #request.defines['csoFrom']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoFrom":relateObject}" id="form-dl-csoFrom">
					<dt>订单来源：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoFromYesNot=="not"?"checked-not":""}${data.csoFromYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoFromYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csoFrom==0?"selected":""}>网站预订</option>
							<option value="1" ${data.csoFrom==1?"selected":""}>后台预订</option>
							<option value="2" ${data.csoFrom==2?"selected":""}>微信预订</option>
							<option value="3" ${data.csoFrom==3?"selected":""}>App预订</option>
							<option value="4" ${data.csoFrom==4?"selected":""}>企业预订</option>
							<option value="5" ${data.csoFrom==5?"selected":""}>支付宝预订</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单来源字段的查询代码结束*****************")}
			
			
			${after$csoFrom}
			 	
			 ${before$csoSrc}
			 
			 ${lz:set("注释","*****************来源信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoSrc" : (lz:join(relateObject,"$csoSrc")))}
			 ${lz:set("cname",lz:join(relateObject,".csoSrc"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoSrc || #request.defines['csoSrc']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoSrc":relateObject}" id="form-dl-csoSrc">
					<dt>来源信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoSrcYesNot=="not"?"checked-not":""}${data.csoSrcYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoSrcYesNot}"/>
			 		<input title="请输入动态查询" class="combox" generic="csoSrcGeneric" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:generic(data.csoSrc)}" value="${data.csoSrc}"/>
			 		${lz:set("models",get:models(""))}
				 	<select id="csoSrcGeneric" style="display:none;">
				 		<option value="">--选择模块--</option>
				 		<s:iterator value="#request.models" id="item" status="i">
				 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
				 		</s:iterator>
				 	</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************来源信息字段的查询代码结束*****************")}
			
			
			${after$csoSrc}
			 	
			 ${before$csoUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoUpdateTime" : (lz:join(relateObject,"$csoUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csoUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoUpdateTime || #request.defines['csoUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoUpdateTime":relateObject}" id="form-dl-csoUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoUpdateTimeYesNot=="not"?"checked-not":""}${data.csoUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csoUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csoUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csoUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csoUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csoUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csoUpdateTime}
			 	
			 ${before$csoAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoAddTime" : (lz:join(relateObject,"$csoAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csoAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoAddTime || #request.defines['csoAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoAddTime":relateObject}" id="form-dl-csoAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoAddTimeYesNot=="not"?"checked-not":""}${data.csoAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csoAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csoAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csoAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csoAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csoAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csoAddTime}
			 	
			 ${before$csoEditor}
			 
			 ${lz:set("注释","*****************操作人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoEditor" : (lz:join(relateObject,"$csoEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".csoEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoEditor || #request.defines['csoEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoEditor":relateObject}" id="form-dl-csoEditor">
					<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoEditorYesNot=="not"?"checked-not":""}${data.csoEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csoEditor}">
				 					${get:SrvUser(data.csoEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作人字段的查询代码结束*****************")}
			
			
			${after$csoEditor}
			 	
			 ${before$csoDeviceVesion}
			 
			 ${lz:set("注释","*****************下位机版本字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoDeviceVesion" : (lz:join(relateObject,"$csoDeviceVesion")))}
			 ${lz:set("cname",lz:join(relateObject,".csoDeviceVesion"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoDeviceVesion || #request.defines['csoDeviceVesion']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoDeviceVesion":relateObject}" id="form-dl-csoDeviceVesion">
					<dt>下位机版本：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoDeviceVesionYesNot=="not"?"checked-not":""}${data.csoDeviceVesionYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoDeviceVesionYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csoDeviceVesion==0?"selected":""}>默认</option>
							<option value="1" ${data.csoDeviceVesion==1?"selected":""}>测试</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************下位机版本字段的查询代码结束*****************")}
			
			
			${after$csoDeviceVesion}
			 	
			 ${before$csoAlarm}
			 
			 ${lz:set("注释","*****************报警字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoAlarm" : (lz:join(relateObject,"$csoAlarm")))}
			 ${lz:set("cname",lz:join(relateObject,".csoAlarm"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoAlarm || #request.defines['csoAlarm']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoAlarm":relateObject}" id="form-dl-csoAlarm">
					<dt>报　　警：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoAlarmYesNot=="not"?"checked-not":""}${data.csoAlarmYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoAlarmYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csoAlarm==0?"selected":""}>默认</option>
							<option value="1" ${data.csoAlarm==1?"selected":""}>超距</option>
							<option value="2" ${data.csoAlarm==2?"selected":""}>超时</option>
							<option value="3" ${data.csoAlarm==3?"selected":""}>断线</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************报警字段的查询代码结束*****************")}
			
			
			${after$csoAlarm}
			 	
			 ${before$csoState}
			 
			 ${lz:set("注释","*****************数据状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoState" : (lz:join(relateObject,"$csoState")))}
			 ${lz:set("cname",lz:join(relateObject,".csoState"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoState || #request.defines['csoState']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoState":relateObject}" id="form-dl-csoState">
					<dt>数据状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoStateYesNot=="not"?"checked-not":""}${data.csoStateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoStateYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csoState==0?"selected":""}>未发送</option>
							<option value="1" ${data.csoState==1?"selected":""}>已发送</option>
							<option value="2" ${data.csoState==2?"selected":""}>已收到</option>
							<option value="3" ${data.csoState==3?"selected":""}>不发送</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************数据状态字段的查询代码结束*****************")}
			
			
			${after$csoState}
			 	
			 ${before$csoStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoStatus" : (lz:join(relateObject,"$csoStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csoStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoStatus || #request.defines['csoStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoStatus":relateObject}" id="form-dl-csoStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoStatusYesNot=="not"?"checked-not":""}${data.csoStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csoStatus==0?"selected":""}>已预定</option>
							<option value="1" ${data.csoStatus==1?"selected":""}>使用中</option>
							<option value="2" ${data.csoStatus==2?"selected":""}>已还车</option>
							<option value="3" ${data.csoStatus==3?"selected":""}>已取消</option>
							<option value="4" ${data.csoStatus==4?"selected":""}>已完成</option>
							<option value="5" ${data.csoStatus==5?"selected":""}>待处理</option>
							<option value="6" ${data.csoStatus==6?"selected":""}>已退款</option>
							<option value="7" ${data.csoStatus==7?"selected":""}>已作废</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csoStatus}