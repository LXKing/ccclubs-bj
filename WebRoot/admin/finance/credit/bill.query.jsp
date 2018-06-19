<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cscbId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbId" : (lz:join(relateObject,"$cscbId")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbId || #request.defines['cscbId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbId":relateObject}" id="form-dl-cscbId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbIdYesNot=="not"?"checked-not":""}${data.cscbIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cscbId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cscbId}
			 	
			 ${before$cscbHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbHost" : (lz:join(relateObject,"$cscbHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbHost || #request.defines['cscbHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbHost":relateObject}" id="form-dl-cscbHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbHostYesNot=="not"?"checked-not":""}${data.cscbHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscbHost}">
				 					${get:SrvHost(data.cscbHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cscbHost}
			 	
			 ${before$cscbTitle}
			 
			 ${lz:set("注释","*****************账单描述字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbTitle" : (lz:join(relateObject,"$cscbTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbTitle || #request.defines['cscbTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbTitle":relateObject}" id="form-dl-cscbTitle">
					<dt>账单描述：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbTitleYesNot=="not"?"checked-not":""}${data.cscbTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.cscbTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************账单描述字段的查询代码结束*****************")}
			
			
			${after$cscbTitle}
			 	
			 ${before$cscbMember}
			 
			 ${lz:set("注释","*****************所属会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbMember" : (lz:join(relateObject,"$cscbMember")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbMember || #request.defines['cscbMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbMember":relateObject}" id="form-dl-cscbMember">
					<dt>所属会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbMemberYesNot=="not"?"checked-not":""}${data.cscbMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={cscbHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.cscbMember).csmName}" value="${data.cscbMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscbMember']>0">
				${lz:set("注释","****当所属会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscbMember")}
			  	<input type="hidden" name="cscbMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscbMember}
			 	
			 ${before$cscbCreditCard}
			 
			 ${lz:set("注释","*****************所属信用卡字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbCreditCard" : (lz:join(relateObject,"$cscbCreditCard")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbCreditCard"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbCreditCard || #request.defines['cscbCreditCard']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbCreditCard":relateObject}" id="form-dl-cscbCreditCard">
					<dt>所属信用卡：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbCreditCardYesNot=="not"?"checked-not":""}${data.cscbCreditCardYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbCreditCardYesNot}"/>
			 			<input title="请输入信用卡信用卡号进行查询" class="combox" action="${basePath}${proname}/user/credit/card_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCreditCard(data.cscbCreditCard).csccNo}" value="${data.cscbCreditCard}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/credit/card_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属信用卡字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscbCreditCard']>0">
				${lz:set("注释","****当所属信用卡字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscbCreditCard")}
			  	<input type="hidden" name="cscbCreditCard$on" id="CsCreditCard$on" value="true"/>
			  	<jsp:include page="/admin/user/credit/card.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscbCreditCard}
			 	
			 ${before$cscbOrder}
			 
			 ${lz:set("注释","*****************关联订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbOrder" : (lz:join(relateObject,"$cscbOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbOrder || #request.defines['cscbOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbOrder":relateObject}" id="form-dl-cscbOrder">
					<dt>关联订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbOrderYesNot=="not"?"checked-not":""}${data.cscbOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={cscbHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.cscbOrder).csoId}" value="${data.cscbOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscbOrder']>0">
				${lz:set("注释","****当关联订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscbOrder")}
			  	<input type="hidden" name="cscbOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscbOrder}
			 	
			 ${before$cscbViolat}
			 
			 ${lz:set("注释","*****************关联违章字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbViolat" : (lz:join(relateObject,"$cscbViolat")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbViolat"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbViolat || #request.defines['cscbViolat']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbViolat":relateObject}" id="form-dl-cscbViolat">
					<dt>关联违章：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbViolatYesNot=="not"?"checked-not":""}${data.cscbViolatYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbViolatYesNot}"/>
			 			<input title="请输入车辆违章编号进行查询" class="combox" action="${basePath}${proname}/service/violat_query.do?value={param}&csvHost={cscbHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsViolat(data.cscbViolat).csvId}" value="${data.cscbViolat}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/violat_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联违章字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscbViolat']>0">
				${lz:set("注释","****当关联违章字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscbViolat")}
			  	<input type="hidden" name="cscbViolat$on" id="CsViolat$on" value="true"/>
			  	<jsp:include page="/admin/service/violat.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscbViolat}
			 	
			 ${before$cscbTrouble}
			 
			 ${lz:set("注释","*****************关联事故字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbTrouble" : (lz:join(relateObject,"$cscbTrouble")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbTrouble"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbTrouble || #request.defines['cscbTrouble']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbTrouble":relateObject}" id="form-dl-cscbTrouble">
					<dt>关联事故：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbTroubleYesNot=="not"?"checked-not":""}${data.cscbTroubleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbTroubleYesNot}"/>
			 			<input title="请输入车辆事故编号进行查询" class="combox" action="${basePath}${proname}/service/trouble_query.do?value={param}&cstHost={cscbHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsTrouble(data.cscbTrouble).cstId}" value="${data.cscbTrouble}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/trouble_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联事故字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscbTrouble']>0">
				${lz:set("注释","****当关联事故字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscbTrouble")}
			  	<input type="hidden" name="cscbTrouble$on" id="CsTrouble$on" value="true"/>
			  	<jsp:include page="/admin/service/trouble.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscbTrouble}
			 	
			 ${before$cscbRepayTime}
			 
			 ${lz:set("注释","*****************还款时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbRepayTime" : (lz:join(relateObject,"$cscbRepayTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbRepayTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbRepayTime || #request.defines['cscbRepayTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbRepayTime":relateObject}" id="form-dl-cscbRepayTime">
					<dt>还款时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbRepayTimeYesNot=="not"?"checked-not":""}${data.cscbRepayTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbRepayTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscbRepayTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscbRepayTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscbRepayTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscbRepayTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>还款时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscbRepayTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************还款时间字段的查询代码结束*****************")}
			
			
			${after$cscbRepayTime}
			 	
			 ${before$cscbUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbUpdateTime" : (lz:join(relateObject,"$cscbUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbUpdateTime || #request.defines['cscbUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbUpdateTime":relateObject}" id="form-dl-cscbUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbUpdateTimeYesNot=="not"?"checked-not":""}${data.cscbUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscbUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscbUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscbUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscbUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscbUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cscbUpdateTime}
			 	
			 ${before$cscbAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbAddTime" : (lz:join(relateObject,"$cscbAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbAddTime || #request.defines['cscbAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbAddTime":relateObject}" id="form-dl-cscbAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbAddTimeYesNot=="not"?"checked-not":""}${data.cscbAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscbAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscbAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscbAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscbAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscbAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cscbAddTime}
			 	
			 ${before$cscbEditor}
			 
			 ${lz:set("注释","*****************操作人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbEditor" : (lz:join(relateObject,"$cscbEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbEditor || #request.defines['cscbEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbEditor":relateObject}" id="form-dl-cscbEditor">
					<dt>操作人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbEditorYesNot=="not"?"checked-not":""}${data.cscbEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscbEditor}">
				 					${get:SrvUser(data.cscbEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作人员字段的查询代码结束*****************")}
			
			
			${after$cscbEditor}
			 	
			 ${before$cscbProcess}
			 
			 ${lz:set("注释","*****************跟踪信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbProcess" : (lz:join(relateObject,"$cscbProcess")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbProcess"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbProcess || #request.defines['cscbProcess']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbProcess":relateObject}" id="form-dl-cscbProcess">
					<dt>跟踪信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbProcessYesNot=="not"?"checked-not":""}${data.cscbProcessYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbProcessYesNot}"/>
				 		<select id="${NAME}" name="${NAME}" >
			 				<option value="">全部</option>
							<option value="#1#" ${data.cscbProcess=='#1#'?"selected":""}>催缴短信已发</option>
							<option value="#2#" ${data.cscbProcess=='#2#'?"selected":""}>催缴电话已打</option>
						</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************跟踪信息字段的查询代码结束*****************")}
			
			
			${after$cscbProcess}
			 	
			 ${before$cscbState}
			 
			 ${lz:set("注释","*****************同步状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbState" : (lz:join(relateObject,"$cscbState")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbState"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbState || #request.defines['cscbState']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbState":relateObject}" id="form-dl-cscbState">
					<dt>同步状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbStateYesNot=="not"?"checked-not":""}${data.cscbStateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbStateYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cscbState==0?"selected":""}>未同步</option>
							<option value="1" ${data.cscbState==1?"selected":""}>已同步</option>
							<option value="2" ${data.cscbState==2?"selected":""}>不同步</option>
							<option value="3" ${data.cscbState==3?"selected":""}>待撤单</option>
							<option value="4" ${data.cscbState==4?"selected":""}>已撤单</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************同步状态字段的查询代码结束*****************")}
			
			
			${after$cscbState}
			 	
			 ${before$cscbStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscbStatus" : (lz:join(relateObject,"$cscbStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cscbStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscbStatus || #request.defines['cscbStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscbStatus":relateObject}" id="form-dl-cscbStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscbStatusYesNot=="not"?"checked-not":""}${data.cscbStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscbStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cscbStatus==0?"selected":""}>待还款</option>
							<option value="1" ${data.cscbStatus==1?"selected":""}>已还款</option>
							<option value="2" ${data.cscbStatus==2?"selected":""}>已关闭</option>
							<option value="3" ${data.cscbStatus==3?"selected":""}>已代付</option>
							<option value="4" ${data.cscbStatus==4?"selected":""}>已坏帐</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cscbStatus}