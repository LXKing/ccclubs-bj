<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$tbpId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpId" : (lz:join(relateObject,"$tbpId")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpId || #request.defines['tbpId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpId":relateObject}" id="form-dl-tbpId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpIdYesNot=="not"?"checked-not":""}${data.tbpIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.tbpId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$tbpId}
			 	
			 ${before$tbpHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpHost" : (lz:join(relateObject,"$tbpHost")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpHost || #request.defines['tbpHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpHost":relateObject}" id="form-dl-tbpHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpHostYesNot=="not"?"checked-not":""}${data.tbpHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbpHost}">
				 					${get:SrvHost(data.tbpHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$tbpHost}
			 	
			 ${before$tbpProfile}
			 
			 ${lz:set("注释","*****************问题描述字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpProfile" : (lz:join(relateObject,"$tbpProfile")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpProfile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpProfile || #request.defines['tbpProfile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpProfile":relateObject}" id="form-dl-tbpProfile">
					<dt>问题描述：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpProfileYesNot=="not"?"checked-not":""}${data.tbpProfileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpProfileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="512" name="${NAME}" id="${NAME}"  value="${data.tbpProfile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************问题描述字段的查询代码结束*****************")}
			
			
			${after$tbpProfile}
			 	
			 ${before$tbpType}
			 
			 ${lz:set("注释","*****************问题类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpType" : (lz:join(relateObject,"$tbpType")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpType || #request.defines['tbpType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpType":relateObject}" id="form-dl-tbpType">
					<dt>问题类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpTypeYesNot=="not"?"checked-not":""}${data.tbpTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.tbpType==0?"selected":""}>服务</option>
							<option value="1" ${data.tbpType==1?"selected":""}>产品</option>
							<option value="2" ${data.tbpType==2?"selected":""}>系统</option>
							<option value="3" ${data.tbpType==3?"selected":""}>设备</option>
							<option value="4" ${data.tbpType==4?"selected":""}>车辆</option>
							<option value="5" ${data.tbpType==5?"selected":""}>网点</option>
							<option value="6" ${data.tbpType==6?"selected":""}>电桩</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************问题类型字段的查询代码结束*****************")}
			
			
			${after$tbpType}
			 	
			 ${before$tbpMember}
			 
			 ${lz:set("注释","*****************反馈会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpMember" : (lz:join(relateObject,"$tbpMember")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpMember || #request.defines['tbpMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpMember":relateObject}" id="form-dl-tbpMember">
					<dt>反馈会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpMemberYesNot=="not"?"checked-not":""}${data.tbpMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={tbpHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.tbpMember).csmName}" value="${data.tbpMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************反馈会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbpMember']>0">
				${lz:set("注释","****当反馈会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbpMember")}
			  	<input type="hidden" name="tbpMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbpMember}
			 	
			 ${before$tbpOrder}
			 
			 ${lz:set("注释","*****************所属订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpOrder" : (lz:join(relateObject,"$tbpOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpOrder || #request.defines['tbpOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpOrder":relateObject}" id="form-dl-tbpOrder">
					<dt>所属订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpOrderYesNot=="not"?"checked-not":""}${data.tbpOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={tbpHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.tbpOrder).csoId}" value="${data.tbpOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbpOrder']>0">
				${lz:set("注释","****当所属订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbpOrder")}
			  	<input type="hidden" name="tbpOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbpOrder}
			 	
			 ${before$tbpRelate}
			 
			 ${lz:set("注释","*****************关联对象字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpRelate" : (lz:join(relateObject,"$tbpRelate")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpRelate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpRelate || #request.defines['tbpRelate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpRelate":relateObject}" id="form-dl-tbpRelate">
					<dt>关联对象：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpRelateYesNot=="not"?"checked-not":""}${data.tbpRelateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpRelateYesNot}"/>
			 		<input title="请输入动态查询" class="combox" generic="tbpRelateGeneric" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:generic(data.tbpRelate)}" value="${data.tbpRelate}"/>
			 		${lz:set("models",get:models("CsCar,CsOutlets,CsPowerPile"))}
				 	<select id="tbpRelateGeneric" style="display:none;">
				 		<option value="">--选择模块--</option>
				 		<s:iterator value="#request.models" id="item" status="i">
				 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
				 		</s:iterator>
				 	</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联对象字段的查询代码结束*****************")}
			
			
			${after$tbpRelate}
			 	
			 ${before$tbpAdder}
			 
			 ${lz:set("注释","*****************添加人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpAdder" : (lz:join(relateObject,"$tbpAdder")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpAdder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpAdder || #request.defines['tbpAdder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpAdder":relateObject}" id="form-dl-tbpAdder">
					<dt>添加人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpAdderYesNot=="not"?"checked-not":""}${data.tbpAdderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpAdderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbpAdder}">
				 					${get:SrvUser(data.tbpAdder).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人员字段的查询代码结束*****************")}
			
			
			${after$tbpAdder}
			 	
			 ${before$tbpEditor}
			 
			 ${lz:set("注释","*****************修改人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpEditor" : (lz:join(relateObject,"$tbpEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpEditor || #request.defines['tbpEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpEditor":relateObject}" id="form-dl-tbpEditor">
					<dt>修改人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpEditorYesNot=="not"?"checked-not":""}${data.tbpEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbpEditor}">
				 					${get:SrvUser(data.tbpEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改人员字段的查询代码结束*****************")}
			
			
			${after$tbpEditor}
			 	
			 ${before$tbpDealer}
			 
			 ${lz:set("注释","*****************任务指派字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpDealer" : (lz:join(relateObject,"$tbpDealer")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpDealer"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpDealer || #request.defines['tbpDealer']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpDealer":relateObject}" id="form-dl-tbpDealer">
					<dt>任务指派：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpDealerYesNot=="not"?"checked-not":""}${data.tbpDealerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpDealerYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbpDealer}">
				 					${get:SrvUser(data.tbpDealer).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************任务指派字段的查询代码结束*****************")}
			
			
			${after$tbpDealer}
			 	
			 ${before$tbpResult}
			 
			 ${lz:set("注释","*****************结案描述字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpResult" : (lz:join(relateObject,"$tbpResult")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpResult"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpResult || #request.defines['tbpResult']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpResult":relateObject}" id="form-dl-tbpResult">
					<dt>结案描述：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpResultYesNot=="not"?"checked-not":""}${data.tbpResultYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpResultYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.tbpResult}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结案描述字段的查询代码结束*****************")}
			
			
			${after$tbpResult}
			 	
			 ${before$tbpMemo}
			 
			 ${lz:set("注释","*****************备忘信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpMemo" : (lz:join(relateObject,"$tbpMemo")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpMemo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpMemo || #request.defines['tbpMemo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpMemo":relateObject}" id="form-dl-tbpMemo">
					<dt>备忘信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpMemoYesNot=="not"?"checked-not":""}${data.tbpMemoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpMemoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.tbpMemo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备忘信息字段的查询代码结束*****************")}
			
			
			${after$tbpMemo}
			 	
			 ${before$tbpData}
			 
			 ${lz:set("注释","*****************程序数据字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpData" : (lz:join(relateObject,"$tbpData")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpData"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpData || #request.defines['tbpData']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpData":relateObject}" id="form-dl-tbpData">
					<dt>程序数据：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpDataYesNot=="not"?"checked-not":""}${data.tbpDataYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpDataYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.tbpData}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************程序数据字段的查询代码结束*****************")}
			
			
			${after$tbpData}
			 	
			 ${before$tbpUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpUpdateTime" : (lz:join(relateObject,"$tbpUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpUpdateTime || #request.defines['tbpUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpUpdateTime":relateObject}" id="form-dl-tbpUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpUpdateTimeYesNot=="not"?"checked-not":""}${data.tbpUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbpUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbpUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbpUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbpUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbpUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$tbpUpdateTime}
			 	
			 ${before$tbpAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpAddTime" : (lz:join(relateObject,"$tbpAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpAddTime || #request.defines['tbpAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpAddTime":relateObject}" id="form-dl-tbpAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpAddTimeYesNot=="not"?"checked-not":""}${data.tbpAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbpAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbpAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbpAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbpAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbpAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$tbpAddTime}
			 	
			 ${before$tbpStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpStatus" : (lz:join(relateObject,"$tbpStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpStatus || #request.defines['tbpStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpStatus":relateObject}" id="form-dl-tbpStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpStatusYesNot=="not"?"checked-not":""}${data.tbpStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.tbpStatus==0?"selected":""}>待处理</option>
							<option value="1" ${data.tbpStatus==1?"selected":""}>正在处理</option>
							<option value="2" ${data.tbpStatus==2?"selected":""}>已处理</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$tbpStatus}