<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csurId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csurId" : (lz:join(relateObject,"$csurId")))}
			 ${lz:set("cname",lz:join(relateObject,".csurId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csurId || #request.defines['csurId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csurId":relateObject}" id="form-dl-csurId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csurIdYesNot=="not"?"checked-not":""}${data.csurIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csurIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csurId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csurId}
			 	
			 ${before$csurHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csurHost" : (lz:join(relateObject,"$csurHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csurHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csurHost || #request.defines['csurHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csurHost":relateObject}" id="form-dl-csurHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csurHostYesNot=="not"?"checked-not":""}${data.csurHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csurHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csurHost}">
				 					${get:SrvHost(data.csurHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csurHost}
			 	
			 ${before$csurProfile}
			 
			 ${lz:set("注释","*****************摘要字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csurProfile" : (lz:join(relateObject,"$csurProfile")))}
			 ${lz:set("cname",lz:join(relateObject,".csurProfile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csurProfile || #request.defines['csurProfile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csurProfile":relateObject}" id="form-dl-csurProfile">
					<dt>摘　　要：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csurProfileYesNot=="not"?"checked-not":""}${data.csurProfileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csurProfileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csurProfile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************摘要字段的查询代码结束*****************")}
			
			
			${after$csurProfile}
			 	
			 ${before$csurMember}
			 
			 ${lz:set("注释","*****************所属会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csurMember" : (lz:join(relateObject,"$csurMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csurMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csurMember || #request.defines['csurMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csurMember":relateObject}" id="form-dl-csurMember">
					<dt>所属会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csurMemberYesNot=="not"?"checked-not":""}${data.csurMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csurMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csurHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csurMember).csmName}" value="${data.csurMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csurMember']>0">
				${lz:set("注释","****当所属会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csurMember")}
			  	<input type="hidden" name="csurMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csurMember}
			 	
			 ${before$csurFreeHour}
			 
			 ${lz:set("注释","*****************所属免费小时字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csurFreeHour" : (lz:join(relateObject,"$csurFreeHour")))}
			 ${lz:set("cname",lz:join(relateObject,".csurFreeHour"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csurFreeHour || #request.defines['csurFreeHour']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csurFreeHour":relateObject}" id="form-dl-csurFreeHour">
					<dt>所属免费小时：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csurFreeHourYesNot=="not"?"checked-not":""}${data.csurFreeHourYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csurFreeHourYesNot}"/>
			 			<input title="请输入免费小时编号进行查询" class="combox" action="${basePath}${proname}/user/freehour_query.do?value={param}&csfhHost={csurHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsFreeHour(data.csurFreeHour).csfhId}" value="${data.csurFreeHour}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/freehour_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属免费小时字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csurFreeHour']>0">
				${lz:set("注释","****当所属免费小时字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csurFreeHour")}
			  	<input type="hidden" name="csurFreeHour$on" id="CsFreeHour$on" value="true"/>
			  	<jsp:include page="/admin/user/freehour.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csurFreeHour}
			 	
			 ${before$csurCoin}
			 
			 ${lz:set("注释","*****************所属红包字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csurCoin" : (lz:join(relateObject,"$csurCoin")))}
			 ${lz:set("cname",lz:join(relateObject,".csurCoin"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csurCoin || #request.defines['csurCoin']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csurCoin":relateObject}" id="form-dl-csurCoin">
					<dt>所属红包：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csurCoinYesNot=="not"?"checked-not":""}${data.csurCoinYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csurCoinYesNot}"/>
			 			<input title="请输入红包编号进行查询" class="combox" action="${basePath}${proname}/user/coin_query.do?value={param}&cscHost={csurHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCoin(data.csurCoin).cscId}" value="${data.csurCoin}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/coin_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属红包字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csurCoin']>0">
				${lz:set("注释","****当所属红包字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csurCoin")}
			  	<input type="hidden" name="csurCoin$on" id="CsCoin$on" value="true"/>
			  	<jsp:include page="/admin/user/coin.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csurCoin}
			 	
			 ${before$csurGift}
			 
			 ${lz:set("注释","*****************所属优惠项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csurGift" : (lz:join(relateObject,"$csurGift")))}
			 ${lz:set("cname",lz:join(relateObject,".csurGift"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csurGift || #request.defines['csurGift']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csurGift":relateObject}" id="form-dl-csurGift">
					<dt>所属优惠项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csurGiftYesNot=="not"?"checked-not":""}${data.csurGiftYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csurGiftYesNot}"/>
			 			<input title="请输入优惠内容优惠名称进行查询" class="combox" action="${basePath}${proname}/user/gift_query.do?value={param}&csgHost={csurHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsGift(data.csurGift).csgName}" value="${data.csurGift}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/gift_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属优惠项字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csurGift']>0">
				${lz:set("注释","****当所属优惠项字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csurGift")}
			  	<input type="hidden" name="csurGift$on" id="CsGift$on" value="true"/>
			  	<jsp:include page="/admin/user/gift.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csurGift}
			 	
			 ${before$csurOrder}
			 
			 ${lz:set("注释","*****************所属订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csurOrder" : (lz:join(relateObject,"$csurOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csurOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csurOrder || #request.defines['csurOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csurOrder":relateObject}" id="form-dl-csurOrder">
					<dt>所属订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csurOrderYesNot=="not"?"checked-not":""}${data.csurOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csurOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csurHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.csurOrder).csoId}" value="${data.csurOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csurOrder']>0">
				${lz:set("注释","****当所属订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csurOrder")}
			  	<input type="hidden" name="csurOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csurOrder}
			 	
			 ${before$csurAddTime}
			 
			 ${lz:set("注释","*****************使用时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csurAddTime" : (lz:join(relateObject,"$csurAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csurAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csurAddTime || #request.defines['csurAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csurAddTime":relateObject}" id="form-dl-csurAddTime">
					<dt>使用时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csurAddTimeYesNot=="not"?"checked-not":""}${data.csurAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csurAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csurAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csurAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csurAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csurAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>使用时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csurAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************使用时间字段的查询代码结束*****************")}
			
			
			${after$csurAddTime}