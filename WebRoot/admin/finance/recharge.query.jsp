<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csarId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarId" : (lz:join(relateObject,"$csarId")))}
			 ${lz:set("cname",lz:join(relateObject,".csarId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarId || #request.defines['csarId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarId":relateObject}" id="form-dl-csarId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarIdYesNot=="not"?"checked-not":""}${data.csarIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csarId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csarId}
			 	
			 ${before$csarHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarHost" : (lz:join(relateObject,"$csarHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csarHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarHost || #request.defines['csarHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarHost":relateObject}" id="form-dl-csarHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarHostYesNot=="not"?"checked-not":""}${data.csarHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csarHost}">
				 					${get:SrvHost(data.csarHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csarHost}
			 	
			 ${before$csarTitle}
			 
			 ${lz:set("注释","*****************摘要字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarTitle" : (lz:join(relateObject,"$csarTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".csarTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarTitle || #request.defines['csarTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarTitle":relateObject}" id="form-dl-csarTitle">
					<dt>摘　　要：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarTitleYesNot=="not"?"checked-not":""}${data.csarTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csarTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************摘要字段的查询代码结束*****************")}
			
			
			${after$csarTitle}
			 	
			 ${before$csarMember}
			 
			 ${lz:set("注释","*****************会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarMember" : (lz:join(relateObject,"$csarMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csarMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarMember || #request.defines['csarMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarMember":relateObject}" id="form-dl-csarMember">
					<dt>会　　员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarMemberYesNot=="not"?"checked-not":""}${data.csarMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csarHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csarMember).csmName}" value="${data.csarMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csarMember']>0">
				${lz:set("注释","****当会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csarMember")}
			  	<input type="hidden" name="csarMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csarMember}
			 	
			 ${before$csarMoneyType}
			 
			 ${lz:set("注释","*****************金额类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarMoneyType" : (lz:join(relateObject,"$csarMoneyType")))}
			 ${lz:set("cname",lz:join(relateObject,".csarMoneyType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarMoneyType || #request.defines['csarMoneyType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarMoneyType":relateObject}" id="form-dl-csarMoneyType">
					<dt>金额类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarMoneyTypeYesNot=="not"?"checked-not":""}${data.csarMoneyTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarMoneyTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csarMoneyType==0?"selected":""}>保证金</option>
							<option value="1" ${data.csarMoneyType==1?"selected":""}>现金券</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************金额类型字段的查询代码结束*****************")}
			
			
			${after$csarMoneyType}
			 	
			 ${before$csarRecordSubject}
			 
			 ${lz:set("注释","*****************类型科目字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarRecordSubject" : (lz:join(relateObject,"$csarRecordSubject")))}
			 ${lz:set("cname",lz:join(relateObject,".csarRecordSubject"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarRecordSubject || #request.defines['csarRecordSubject']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarRecordSubject":relateObject}" id="form-dl-csarRecordSubject">
					<dt>类型科目：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarRecordSubjectYesNot=="not"?"checked-not":""}${data.csarRecordSubjectYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarRecordSubjectYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/configurator/recordsubject_query.do?size=-1&csrsIsArtif=true" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csarRecordSubject}">
				 					${get:CsRecordSubject(data.csarRecordSubject).csrsName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/recordsubject_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************类型科目字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csarRecordSubject']>0">
				${lz:set("注释","****当类型科目字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csarRecordSubject")}
			  	<input type="hidden" name="csarRecordSubject$on" id="CsRecordSubject$on" value="true"/>
			  	<jsp:include page="/admin/configurator/recordsubject.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csarRecordSubject}
			 	
			 ${before$csarOrder}
			 
			 ${lz:set("注释","*****************关联订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarOrder" : (lz:join(relateObject,"$csarOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csarOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarOrder || #request.defines['csarOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarOrder":relateObject}" id="form-dl-csarOrder">
					<dt>关联订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarOrderYesNot=="not"?"checked-not":""}${data.csarOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csarHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.csarOrder).csoId}" value="${data.csarOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csarOrder']>0">
				${lz:set("注释","****当关联订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csarOrder")}
			  	<input type="hidden" name="csarOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csarOrder}
			 	
			 ${before$csarRelate}
			 
			 ${lz:set("注释","*****************其它关联字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarRelate" : (lz:join(relateObject,"$csarRelate")))}
			 ${lz:set("cname",lz:join(relateObject,".csarRelate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarRelate || #request.defines['csarRelate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarRelate":relateObject}" id="form-dl-csarRelate">
					<dt>其它关联：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarRelateYesNot=="not"?"checked-not":""}${data.csarRelateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarRelateYesNot}"/>
			 		<input title="请输入动态查询" class="combox" generic="csarRelateGeneric" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:generic(data.csarRelate)}" value="${data.csarRelate}"/>
			 		${lz:set("models",get:models("CsRefund,CsAlipayRecord,CsCreditBill,CsTrouble,CsViolat"))}
				 	<select id="csarRelateGeneric" style="display:none;">
				 		<option value="">--选择模块--</option>
				 		<s:iterator value="#request.models" id="item" status="i">
				 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
				 		</s:iterator>
				 	</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************其它关联字段的查询代码结束*****************")}
			
			
			${after$csarRelate}
			 	
			 ${before$csarReceived}
			 
			 ${lz:set("注释","*****************现金已收讫字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarReceived" : (lz:join(relateObject,"$csarReceived")))}
			 ${lz:set("cname",lz:join(relateObject,".csarReceived"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarReceived || #request.defines['csarReceived']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarReceived":relateObject}" id="form-dl-csarReceived">
					<dt>现金已收讫：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarReceivedYesNot=="not"?"checked-not":""}${data.csarReceivedYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarReceivedYesNot}"/>
			 		<input type="radio" value="" name="${NAME}" id="${NAME}" ${data.csarReceived==null?"checked":""}/>
					全部&nbsp;&nbsp;
			 	     <input type="radio" value="1" name="${NAME}" id="${NAME}" ${data.csarReceived=='true'?"checked":""}/>
					是&nbsp;&nbsp;
					<input type="radio" value="0" name="${NAME}" id="${NAME}" ${data.csarReceived=='false'?"checked":""}/>否
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************现金已收讫字段的查询代码结束*****************")}
			
			
			${after$csarReceived}
			 	
			 ${before$csarVoucher}
			 
			 ${lz:set("注释","*****************现金入帐凭据字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarVoucher" : (lz:join(relateObject,"$csarVoucher")))}
			 ${lz:set("cname",lz:join(relateObject,".csarVoucher"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarVoucher || #request.defines['csarVoucher']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarVoucher":relateObject}" id="form-dl-csarVoucher">
					<dt>现金入帐凭据：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarVoucherYesNot=="not"?"checked-not":""}${data.csarVoucherYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarVoucherYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csarVoucher}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************现金入帐凭据字段的查询代码结束*****************")}
			
			
			${after$csarVoucher}
			 	
			 ${before$csarAdder}
			 
			 ${lz:set("注释","*****************添加人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarAdder" : (lz:join(relateObject,"$csarAdder")))}
			 ${lz:set("cname",lz:join(relateObject,".csarAdder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarAdder || #request.defines['csarAdder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarAdder":relateObject}" id="form-dl-csarAdder">
					<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarAdderYesNot=="not"?"checked-not":""}${data.csarAdderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarAdderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csarAdder}">
				 					${get:SrvUser(data.csarAdder).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人字段的查询代码结束*****************")}
			
			
			${after$csarAdder}
			 	
			 ${before$csarEditor}
			 
			 ${lz:set("注释","*****************修改人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarEditor" : (lz:join(relateObject,"$csarEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".csarEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarEditor || #request.defines['csarEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarEditor":relateObject}" id="form-dl-csarEditor">
					<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarEditorYesNot=="not"?"checked-not":""}${data.csarEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csarEditor}">
				 					${get:SrvUser(data.csarEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改人字段的查询代码结束*****************")}
			
			
			${after$csarEditor}
			 	
			 ${before$csarConfirmer}
			 
			 ${lz:set("注释","*****************财务审核人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarConfirmer" : (lz:join(relateObject,"$csarConfirmer")))}
			 ${lz:set("cname",lz:join(relateObject,".csarConfirmer"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarConfirmer || #request.defines['csarConfirmer']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarConfirmer":relateObject}" id="form-dl-csarConfirmer">
					<dt>财务审核人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarConfirmerYesNot=="not"?"checked-not":""}${data.csarConfirmerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarConfirmerYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csarConfirmerMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csarConfirmerMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************财务审核人字段的查询代码结束*****************")}
			
			
			${after$csarConfirmer}
			 	
			 ${before$csarUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarUpdateTime" : (lz:join(relateObject,"$csarUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csarUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarUpdateTime || #request.defines['csarUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarUpdateTime":relateObject}" id="form-dl-csarUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarUpdateTimeYesNot=="not"?"checked-not":""}${data.csarUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csarUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csarUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csarUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csarUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csarUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csarUpdateTime}
			 	
			 ${before$csarAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarAddTime" : (lz:join(relateObject,"$csarAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csarAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarAddTime || #request.defines['csarAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarAddTime":relateObject}" id="form-dl-csarAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarAddTimeYesNot=="not"?"checked-not":""}${data.csarAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csarAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csarAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csarAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csarAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csarAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csarAddTime}
			 	
			 ${before$csarConfirmTime}
			 
			 ${lz:set("注释","*****************审核时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarConfirmTime" : (lz:join(relateObject,"$csarConfirmTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csarConfirmTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarConfirmTime || #request.defines['csarConfirmTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarConfirmTime":relateObject}" id="form-dl-csarConfirmTime">
					<dt>审核时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarConfirmTimeYesNot=="not"?"checked-not":""}${data.csarConfirmTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarConfirmTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csarConfirmTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csarConfirmTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csarConfirmTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csarConfirmTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>审核时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csarConfirmTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************审核时间字段的查询代码结束*****************")}
			
			
			${after$csarConfirmTime}
			 	
			 ${before$csarRemark}
			 
			 ${lz:set("注释","*****************备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarRemark" : (lz:join(relateObject,"$csarRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".csarRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarRemark || #request.defines['csarRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarRemark":relateObject}" id="form-dl-csarRemark">
					<dt>备　　注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarRemarkYesNot=="not"?"checked-not":""}${data.csarRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csarRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注字段的查询代码结束*****************")}
			
			
			${after$csarRemark}
			 	
			 ${before$csarStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarStatus" : (lz:join(relateObject,"$csarStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csarStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarStatus || #request.defines['csarStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarStatus":relateObject}" id="form-dl-csarStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarStatusYesNot=="not"?"checked-not":""}${data.csarStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csarStatus==0?"selected":""}>待审核</option>
							<option value="1" ${data.csarStatus==1?"selected":""}>已审核</option>
							<option value="2" ${data.csarStatus==2?"selected":""}>审核未通过</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csarStatus}