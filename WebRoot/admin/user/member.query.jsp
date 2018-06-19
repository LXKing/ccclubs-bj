<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csmId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmId" : (lz:join(relateObject,"$csmId")))}
			 ${lz:set("cname",lz:join(relateObject,".csmId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmId || #request.defines['csmId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmId":relateObject}" id="form-dl-csmId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmIdYesNot=="not"?"checked-not":""}${data.csmIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csmId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csmId}
			 	
			 ${before$csmHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmHost" : (lz:join(relateObject,"$csmHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csmHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmHost || #request.defines['csmHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmHost":relateObject}" id="form-dl-csmHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmHostYesNot=="not"?"checked-not":""}${data.csmHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmHost}">
				 					${get:SrvHost(data.csmHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csmHost}
			 	
			 ${before$csmUsername}
			 
			 ${lz:set("注释","*****************用户名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmUsername" : (lz:join(relateObject,"$csmUsername")))}
			 ${lz:set("cname",lz:join(relateObject,".csmUsername"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmUsername || #request.defines['csmUsername']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmUsername":relateObject}" id="form-dl-csmUsername">
					<dt>用&nbsp;&nbsp;户&nbsp;&nbsp;名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmUsernameYesNot=="not"?"checked-not":""}${data.csmUsernameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmUsernameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csmUsername}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************用户名字段的查询代码结束*****************")}
			
			
			${after$csmUsername}
			 	
			 ${before$csmGroup}
			 
			 ${lz:set("注释","*****************所属组织字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmGroup" : (lz:join(relateObject,"$csmGroup")))}
			 ${lz:set("cname",lz:join(relateObject,".csmGroup"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmGroup || #request.defines['csmGroup']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmGroup":relateObject}" id="form-dl-csmGroup">
					<dt>所属组织：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmGroupYesNot=="not"?"checked-not":""}${data.csmGroupYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmGroupYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/user/membergroup_query.do?size=-1&csmgHost={csmHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmGroup}">
				 					${get:CsMemberGroup(data.csmGroup).csmgName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/membergroup_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属组织字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmGroup']>0">
				${lz:set("注释","****当所属组织字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmGroup")}
			  	<input type="hidden" name="csmGroup$on" id="CsMemberGroup$on" value="true"/>
			  	<jsp:include page="/admin/user/membergroup.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmGroup}
			 	
			 ${before$csmMoney}
			 
			 ${lz:set("注释","*****************余额字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmMoney" : (lz:join(relateObject,"$csmMoney")))}
			 ${lz:set("cname",lz:join(relateObject,".csmMoney"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmMoney || #request.defines['csmMoney']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmMoney":relateObject}" id="form-dl-csmMoney">
					<dt>余　　额：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmMoneyYesNot=="not"?"checked-not":""}${data.csmMoneyYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmMoneyYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csmMoneyMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csmMoneyMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************余额字段的查询代码结束*****************")}
			
			
			${after$csmMoney}
			 	
			 ${before$csmNotRevenue}
			 
			 ${lz:set("注释","*****************计算营收字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmNotRevenue" : (lz:join(relateObject,"$csmNotRevenue")))}
			 ${lz:set("cname",lz:join(relateObject,".csmNotRevenue"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmNotRevenue || #request.defines['csmNotRevenue']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmNotRevenue":relateObject}" id="form-dl-csmNotRevenue">
					<dt>计算营收：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmNotRevenueYesNot=="not"?"checked-not":""}${data.csmNotRevenueYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmNotRevenueYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmNotRevenue==0?"selected":""}>计入营收</option>
							<option value="1" ${data.csmNotRevenue==1?"selected":""}>不计营收</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************计算营收字段的查询代码结束*****************")}
			
			
			${after$csmNotRevenue}
			 	
			 ${before$csmWeixinFlag}
			 
			 ${lz:set("注释","*****************微信帐号标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmWeixinFlag" : (lz:join(relateObject,"$csmWeixinFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".csmWeixinFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmWeixinFlag || #request.defines['csmWeixinFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmWeixinFlag":relateObject}" id="form-dl-csmWeixinFlag">
					<dt>微信帐号标识：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmWeixinFlagYesNot=="not"?"checked-not":""}${data.csmWeixinFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmWeixinFlagYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="64" name="${NAME}" id="${NAME}"  value="${data.csmWeixinFlag}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************微信帐号标识字段的查询代码结束*****************")}
			
			
			${after$csmWeixinFlag}
			 	
			 ${before$csmAlipayFlag}
			 
			 ${lz:set("注释","*****************支付宝帐号标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmAlipayFlag" : (lz:join(relateObject,"$csmAlipayFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".csmAlipayFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmAlipayFlag || #request.defines['csmAlipayFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmAlipayFlag":relateObject}" id="form-dl-csmAlipayFlag">
					<dt>支付宝帐号标识：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmAlipayFlagYesNot=="not"?"checked-not":""}${data.csmAlipayFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmAlipayFlagYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="64" name="${NAME}" id="${NAME}"  value="${data.csmAlipayFlag}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************支付宝帐号标识字段的查询代码结束*****************")}
			
			
			${after$csmAlipayFlag}
			 	
			 ${before$csmEmail}
			 
			 ${lz:set("注释","*****************邮箱字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmEmail" : (lz:join(relateObject,"$csmEmail")))}
			 ${lz:set("cname",lz:join(relateObject,".csmEmail"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmEmail || #request.defines['csmEmail']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmEmail":relateObject}" id="form-dl-csmEmail">
					<dt>邮　　箱：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmEmailYesNot=="not"?"checked-not":""}${data.csmEmailYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmEmailYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csmEmail}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************邮箱字段的查询代码结束*****************")}
			
			
			${after$csmEmail}
			 	
			 ${before$csmMobile}
			 
			 ${lz:set("注释","*****************手机字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmMobile" : (lz:join(relateObject,"$csmMobile")))}
			 ${lz:set("cname",lz:join(relateObject,".csmMobile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmMobile || #request.defines['csmMobile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmMobile":relateObject}" id="form-dl-csmMobile">
					<dt>手　　机：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmMobileYesNot=="not"?"checked-not":""}${data.csmMobileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmMobileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csmMobile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************手机字段的查询代码结束*****************")}
			
			
			${after$csmMobile}
			 	
			 ${before$csmEvcard}
			 
			 ${lz:set("注释","*****************EV卡字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmEvcard" : (lz:join(relateObject,"$csmEvcard")))}
			 ${lz:set("cname",lz:join(relateObject,".csmEvcard"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmEvcard || #request.defines['csmEvcard']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmEvcard":relateObject}" id="form-dl-csmEvcard">
					<dt>E&nbsp;&nbsp;V&nbsp;&nbsp;卡：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmEvcardYesNot=="not"?"checked-not":""}${data.csmEvcardYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmEvcardYesNot}"/>
			 			<input title="请输入会员卡卡号进行查询" class="combox" action="${basePath}${proname}/object/evcard_query.do?value={param}&csecHost={csmHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsEvCard(data.csmEvcard).csecNumber}" value="${data.csmEvcard}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/evcard_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************EV卡字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmEvcard']>0">
				${lz:set("注释","****当EV卡字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmEvcard")}
			  	<input type="hidden" name="csmEvcard$on" id="CsEvCard$on" value="true"/>
			  	<jsp:include page="/admin/object/evcard.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmEvcard}
			 	
			 ${before$csmExpress}
			 
			 ${lz:set("注释","*****************快递单号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmExpress" : (lz:join(relateObject,"$csmExpress")))}
			 ${lz:set("cname",lz:join(relateObject,".csmExpress"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmExpress || #request.defines['csmExpress']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmExpress":relateObject}" id="form-dl-csmExpress">
					<dt>快递单号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmExpressYesNot=="not"?"checked-not":""}${data.csmExpressYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmExpressYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csmExpress}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************快递单号字段的查询代码结束*****************")}
			
			
			${after$csmExpress}
			 	
			 ${before$csmTemp}
			 
			 ${lz:set("注释","*****************临时标记字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmTemp" : (lz:join(relateObject,"$csmTemp")))}
			 ${lz:set("cname",lz:join(relateObject,".csmTemp"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmTemp || #request.defines['csmTemp']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmTemp":relateObject}" id="form-dl-csmTemp">
					<dt>临时标记：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmTempYesNot=="not"?"checked-not":""}${data.csmTempYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmTempYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csmTemp}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************临时标记字段的查询代码结束*****************")}
			
			
			${after$csmTemp}
			 	
			 ${before$csmName}
			 
			 ${lz:set("注释","*****************真实姓名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmName" : (lz:join(relateObject,"$csmName")))}
			 ${lz:set("cname",lz:join(relateObject,".csmName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmName || #request.defines['csmName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmName":relateObject}" id="form-dl-csmName">
					<dt>真实姓名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmNameYesNot=="not"?"checked-not":""}${data.csmNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csmName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************真实姓名字段的查询代码结束*****************")}
			
			
			${after$csmName}
			 	
			 ${before$csmInfo}
			 
			 ${lz:set("注释","*****************详细信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmInfo" : (lz:join(relateObject,"$csmInfo")))}
			 ${lz:set("cname",lz:join(relateObject,".csmInfo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmInfo || #request.defines['csmInfo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmInfo":relateObject}" id="form-dl-csmInfo">
					<dt>详细信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmInfoYesNot=="not"?"checked-not":""}${data.csmInfoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmInfoYesNot}"/>
			 			<input title="请输入会员信息真实姓名进行查询" class="combox" action="${basePath}${proname}/user/memberinfo_query.do?value={param}&csmiHost={csmHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMemberInfo(data.csmInfo).csmiName}" value="${data.csmInfo}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/memberinfo_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************详细信息字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmInfo']>0">
				${lz:set("注释","****当详细信息字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmInfo")}
			  	<input type="hidden" name="csmInfo$on" id="CsMemberInfo$on" value="true"/>
			  	<jsp:include page="/admin/user/memberinfo.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmInfo}
			 	
			 ${before$csmOutlets}
			 
			 ${lz:set("注释","*****************默认网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmOutlets" : (lz:join(relateObject,"$csmOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".csmOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmOutlets || #request.defines['csmOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmOutlets":relateObject}" id="form-dl-csmOutlets">
					<dt>默认网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmOutletsYesNot=="not"?"checked-not":""}${data.csmOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmOutletsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_tree.do?parent={param}&csoHost={csmHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmOutlets}">
				 					${get:CsOutlets(data.csmOutlets).csoName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************默认网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmOutlets']>0">
				${lz:set("注释","****当默认网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmOutlets")}
			  	<input type="hidden" name="csmOutlets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmOutlets}
			 	
			 ${before$csmUpdateTime}
			 
			 ${lz:set("注释","*****************更新时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmUpdateTime" : (lz:join(relateObject,"$csmUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmUpdateTime || #request.defines['csmUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmUpdateTime":relateObject}" id="form-dl-csmUpdateTime">
					<dt>更新时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmUpdateTimeYesNot=="not"?"checked-not":""}${data.csmUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>更新时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************更新时间字段的查询代码结束*****************")}
			
			
			${after$csmUpdateTime}
			 	
			 ${before$csmAddTime}
			 
			 ${lz:set("注释","*****************注册时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmAddTime" : (lz:join(relateObject,"$csmAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmAddTime || #request.defines['csmAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmAddTime":relateObject}" id="form-dl-csmAddTime">
					<dt>注册时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmAddTimeYesNot=="not"?"checked-not":""}${data.csmAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>注册时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************注册时间字段的查询代码结束*****************")}
			
			
			${after$csmAddTime}
			 	
			 ${before$csmLastTime}
			 
			 ${lz:set("注释","*****************最后登录字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmLastTime" : (lz:join(relateObject,"$csmLastTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmLastTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmLastTime || #request.defines['csmLastTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmLastTime":relateObject}" id="form-dl-csmLastTime">
					<dt>最后登录：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmLastTimeYesNot=="not"?"checked-not":""}${data.csmLastTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmLastTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmLastTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmLastTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmLastTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmLastTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>最后登录：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmLastTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************最后登录字段的查询代码结束*****************")}
			
			
			${after$csmLastTime}
			 	
			 ${before$csmFirstUse}
			 
			 ${lz:set("注释","*****************首次用车字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmFirstUse" : (lz:join(relateObject,"$csmFirstUse")))}
			 ${lz:set("cname",lz:join(relateObject,".csmFirstUse"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmFirstUse || #request.defines['csmFirstUse']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmFirstUse":relateObject}" id="form-dl-csmFirstUse">
					<dt>首次用车：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmFirstUseYesNot=="not"?"checked-not":""}${data.csmFirstUseYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmFirstUseYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmFirstUseStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmFirstUseEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmFirstUseExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmFirstUseStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>首次用车：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmFirstUseEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************首次用车字段的查询代码结束*****************")}
			
			
			${after$csmFirstUse}
			 	
			 ${before$csmLastUse}
			 
			 ${lz:set("注释","*****************最后使用字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmLastUse" : (lz:join(relateObject,"$csmLastUse")))}
			 ${lz:set("cname",lz:join(relateObject,".csmLastUse"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmLastUse || #request.defines['csmLastUse']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmLastUse":relateObject}" id="form-dl-csmLastUse">
					<dt>最后使用：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmLastUseYesNot=="not"?"checked-not":""}${data.csmLastUseYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmLastUseYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmLastUseStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmLastUseEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmLastUseExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmLastUseStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>最后使用：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmLastUseEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************最后使用字段的查询代码结束*****************")}
			
			
			${after$csmLastUse}
			 	
			 ${before$csmAllRecharge}
			 
			 ${lz:set("注释","*****************累计充值字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmAllRecharge" : (lz:join(relateObject,"$csmAllRecharge")))}
			 ${lz:set("cname",lz:join(relateObject,".csmAllRecharge"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmAllRecharge || #request.defines['csmAllRecharge']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmAllRecharge":relateObject}" id="form-dl-csmAllRecharge">
					<dt>累计充值：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmAllRechargeYesNot=="not"?"checked-not":""}${data.csmAllRechargeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmAllRechargeYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csmAllRechargeMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csmAllRechargeMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************累计充值字段的查询代码结束*****************")}
			
			
			${after$csmAllRecharge}
			 	
			 ${before$csmAllOrderS}
			 
			 ${lz:set("注释","*****************累计订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmAllOrderS" : (lz:join(relateObject,"$csmAllOrderS")))}
			 ${lz:set("cname",lz:join(relateObject,".csmAllOrderS"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmAllOrderS || #request.defines['csmAllOrderS']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmAllOrderS":relateObject}" id="form-dl-csmAllOrderS">
					<dt>累计订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmAllOrderSYesNot=="not"?"checked-not":""}${data.csmAllOrderSYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmAllOrderSYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csmAllOrderSMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csmAllOrderSMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************累计订单字段的查询代码结束*****************")}
			
			
			${after$csmAllOrderS}
			 	
			 ${before$csmAllUseTime}
			 
			 ${lz:set("注释","*****************累计用车小时字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmAllUseTime" : (lz:join(relateObject,"$csmAllUseTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmAllUseTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmAllUseTime || #request.defines['csmAllUseTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmAllUseTime":relateObject}" id="form-dl-csmAllUseTime">
					<dt>累计用车小时：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmAllUseTimeYesNot=="not"?"checked-not":""}${data.csmAllUseTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmAllUseTimeYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csmAllUseTimeMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csmAllUseTimeMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************累计用车小时字段的查询代码结束*****************")}
			
			
			${after$csmAllUseTime}
			 	
			 ${before$csmAllViolatS}
			 
			 ${lz:set("注释","*****************违章次数字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmAllViolatS" : (lz:join(relateObject,"$csmAllViolatS")))}
			 ${lz:set("cname",lz:join(relateObject,".csmAllViolatS"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmAllViolatS || #request.defines['csmAllViolatS']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmAllViolatS":relateObject}" id="form-dl-csmAllViolatS">
					<dt>违章次数：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmAllViolatSYesNot=="not"?"checked-not":""}${data.csmAllViolatSYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmAllViolatSYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csmAllViolatSMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csmAllViolatSMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************违章次数字段的查询代码结束*****************")}
			
			
			${after$csmAllViolatS}
			 	
			 ${before$csmUnViolatS}
			 
			 ${lz:set("注释","*****************未处理违章字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmUnViolatS" : (lz:join(relateObject,"$csmUnViolatS")))}
			 ${lz:set("cname",lz:join(relateObject,".csmUnViolatS"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmUnViolatS || #request.defines['csmUnViolatS']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmUnViolatS":relateObject}" id="form-dl-csmUnViolatS">
					<dt>未处理违章：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmUnViolatSYesNot=="not"?"checked-not":""}${data.csmUnViolatSYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmUnViolatSYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csmUnViolatSMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csmUnViolatSMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************未处理违章字段的查询代码结束*****************")}
			
			
			${after$csmUnViolatS}
			 	
			 ${before$csmAllTroubleS}
			 
			 ${lz:set("注释","*****************事故次数字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmAllTroubleS" : (lz:join(relateObject,"$csmAllTroubleS")))}
			 ${lz:set("cname",lz:join(relateObject,".csmAllTroubleS"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmAllTroubleS || #request.defines['csmAllTroubleS']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmAllTroubleS":relateObject}" id="form-dl-csmAllTroubleS">
					<dt>事故次数：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmAllTroubleSYesNot=="not"?"checked-not":""}${data.csmAllTroubleSYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmAllTroubleSYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csmAllTroubleSMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csmAllTroubleSMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************事故次数字段的查询代码结束*****************")}
			
			
			${after$csmAllTroubleS}
			 	
			 ${before$csmFirstRecharge}
			 
			 ${lz:set("注释","*****************首次充值字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmFirstRecharge" : (lz:join(relateObject,"$csmFirstRecharge")))}
			 ${lz:set("cname",lz:join(relateObject,".csmFirstRecharge"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmFirstRecharge || #request.defines['csmFirstRecharge']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmFirstRecharge":relateObject}" id="form-dl-csmFirstRecharge">
					<dt>首次充值：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmFirstRechargeYesNot=="not"?"checked-not":""}${data.csmFirstRechargeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmFirstRechargeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmFirstRechargeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmFirstRechargeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmFirstRechargeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmFirstRechargeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>首次充值：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmFirstRechargeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************首次充值字段的查询代码结束*****************")}
			
			
			${after$csmFirstRecharge}
			 	
			 ${before$csmRefundTime}
			 
			 ${lz:set("注释","*****************最后退款字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmRefundTime" : (lz:join(relateObject,"$csmRefundTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmRefundTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmRefundTime || #request.defines['csmRefundTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmRefundTime":relateObject}" id="form-dl-csmRefundTime">
					<dt>最后退款：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmRefundTimeYesNot=="not"?"checked-not":""}${data.csmRefundTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmRefundTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmRefundTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmRefundTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmRefundTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmRefundTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>最后退款：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmRefundTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************最后退款字段的查询代码结束*****************")}
			
			
			${after$csmRefundTime}
			 	
			 ${before$csmAdder}
			 
			 ${lz:set("注释","*****************添加人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmAdder" : (lz:join(relateObject,"$csmAdder")))}
			 ${lz:set("cname",lz:join(relateObject,".csmAdder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmAdder || #request.defines['csmAdder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmAdder":relateObject}" id="form-dl-csmAdder">
					<dt>添加人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmAdderYesNot=="not"?"checked-not":""}${data.csmAdderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmAdderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmAdder}">
				 					${get:SrvUser(data.csmAdder).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人员字段的查询代码结束*****************")}
			
			
			${after$csmAdder}
			 	
			 ${before$csmTracker}
			 
			 ${lz:set("注释","*****************跟踪人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmTracker" : (lz:join(relateObject,"$csmTracker")))}
			 ${lz:set("cname",lz:join(relateObject,".csmTracker"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmTracker || #request.defines['csmTracker']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmTracker":relateObject}" id="form-dl-csmTracker">
					<dt>跟踪人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmTrackerYesNot=="not"?"checked-not":""}${data.csmTrackerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmTrackerYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmTracker}">
				 					${get:SrvUser(data.csmTracker).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************跟踪人员字段的查询代码结束*****************")}
			
			
			${after$csmTracker}
			 	
			 ${before$csmRefer}
			 
			 ${lz:set("注释","*****************推荐会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmRefer" : (lz:join(relateObject,"$csmRefer")))}
			 ${lz:set("cname",lz:join(relateObject,".csmRefer"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmRefer || #request.defines['csmRefer']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmRefer":relateObject}" id="form-dl-csmRefer">
					<dt>推荐会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmReferYesNot=="not"?"checked-not":""}${data.csmReferYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmReferYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csmHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csmRefer).csmName}" value="${data.csmRefer}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************推荐会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmRefer']>0">
				${lz:set("注释","****当推荐会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmRefer")}
			  	<input type="hidden" name="csmRefer$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmRefer}
			 	
			 ${before$csmReferType}
			 
			 ${lz:set("注释","*****************推荐方式字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmReferType" : (lz:join(relateObject,"$csmReferType")))}
			 ${lz:set("cname",lz:join(relateObject,".csmReferType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmReferType || #request.defines['csmReferType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmReferType":relateObject}" id="form-dl-csmReferType">
					<dt>推荐方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmReferTypeYesNot=="not"?"checked-not":""}${data.csmReferTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmReferTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmReferType==0?"selected":""}>无</option>
							<option value="1" ${data.csmReferType==1?"selected":""}>地址转发</option>
							<option value="2" ${data.csmReferType==2?"selected":""}>微博分享</option>
							<option value="3" ${data.csmReferType==3?"selected":""}>广告媒体</option>
							<option value="4" ${data.csmReferType==4?"selected":""}>网络搜索</option>
							<option value="5" ${data.csmReferType==5?"selected":""}>朋友推荐</option>
							<option value="6" ${data.csmReferType==6?"selected":""}>活动推广</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************推荐方式字段的查询代码结束*****************")}
			
			
			${after$csmReferType}
			 	
			 ${before$csmFrom}
			 
			 ${lz:set("注释","*****************注册平台字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmFrom" : (lz:join(relateObject,"$csmFrom")))}
			 ${lz:set("cname",lz:join(relateObject,".csmFrom"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmFrom || #request.defines['csmFrom']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmFrom":relateObject}" id="form-dl-csmFrom">
					<dt>注册平台：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmFromYesNot=="not"?"checked-not":""}${data.csmFromYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmFromYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmFrom==0?"selected":""}>后台</option>
							<option value="1" ${data.csmFrom==1?"selected":""}>网站</option>
							<option value="2" ${data.csmFrom==2?"selected":""}>微信</option>
							<option value="3" ${data.csmFrom==3?"selected":""}>APP</option>
							<option value="4" ${data.csmFrom==4?"selected":""}>支付宝微生活</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************注册平台字段的查询代码结束*****************")}
			
			
			${after$csmFrom}
			 	
			 ${before$csmSrc}
			 
			 ${lz:set("注释","*****************注册源信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmSrc" : (lz:join(relateObject,"$csmSrc")))}
			 ${lz:set("cname",lz:join(relateObject,".csmSrc"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmSrc || #request.defines['csmSrc']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmSrc":relateObject}" id="form-dl-csmSrc">
					<dt>注册源信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmSrcYesNot=="not"?"checked-not":""}${data.csmSrcYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmSrcYesNot}"/>
			 		<input title="请输入动态查询" class="combox" generic="csmSrcGeneric" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:generic(data.csmSrc)}" value="${data.csmSrc}"/>
			 		${lz:set("models",get:models(""))}
				 	<select id="csmSrcGeneric" style="display:none;">
				 		<option value="">--选择模块--</option>
				 		<s:iterator value="#request.models" id="item" status="i">
				 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
				 		</s:iterator>
				 	</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************注册源信息字段的查询代码结束*****************")}
			
			
			${after$csmSrc}
			 	
			 ${before$csmChannel}
			 
			 ${lz:set("注释","*****************来源渠道字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmChannel" : (lz:join(relateObject,"$csmChannel")))}
			 ${lz:set("cname",lz:join(relateObject,".csmChannel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmChannel || #request.defines['csmChannel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmChannel":relateObject}" id="form-dl-csmChannel">
					<dt>来源渠道：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmChannelYesNot=="not"?"checked-not":""}${data.csmChannelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmChannelYesNot}"/>
			 			<input title="请输入访问渠道渠道名称进行查询" class="combox" action="${basePath}${proname}/extension/channel_query.do?value={param}&cscHost={csmHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsChannel(data.csmChannel).cscName}" value="${data.csmChannel}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/extension/channel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************来源渠道字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmChannel']>0">
				${lz:set("注释","****当来源渠道字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmChannel")}
			  	<input type="hidden" name="csmChannel$on" id="CsChannel$on" value="true"/>
			  	<jsp:include page="/admin/extension/channel.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmChannel}
			 	
			 ${before$csmMarketPlan}
			 
			 ${lz:set("注释","*****************营销方案字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmMarketPlan" : (lz:join(relateObject,"$csmMarketPlan")))}
			 ${lz:set("cname",lz:join(relateObject,".csmMarketPlan"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmMarketPlan || #request.defines['csmMarketPlan']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmMarketPlan":relateObject}" id="form-dl-csmMarketPlan">
					<dt>营销方案：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmMarketPlanYesNot=="not"?"checked-not":""}${data.csmMarketPlanYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmMarketPlanYesNot}"/>
			 			<input title="请输入营销方案方案主题进行查询" class="combox" action="${basePath}${proname}/operate/marketplan_query.do?value={param}&csmpHost={csmHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMarketPlan(data.csmMarketPlan).csmpName}" value="${data.csmMarketPlan}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/operate/marketplan_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************营销方案字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmMarketPlan']>0">
				${lz:set("注释","****当营销方案字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmMarketPlan")}
			  	<input type="hidden" name="csmMarketPlan$on" id="CsMarketPlan$on" value="true"/>
			  	<jsp:include page="/admin/operate/marketplan.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmMarketPlan}
			 	
			 ${before$csmActivity}
			 
			 ${lz:set("注释","*****************来源活动字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmActivity" : (lz:join(relateObject,"$csmActivity")))}
			 ${lz:set("cname",lz:join(relateObject,".csmActivity"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmActivity || #request.defines['csmActivity']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmActivity":relateObject}" id="form-dl-csmActivity">
					<dt>来源活动：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmActivityYesNot=="not"?"checked-not":""}${data.csmActivityYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmActivityYesNot}"/>
			 			<input title="请输入营销活动活动主题进行查询" class="combox" action="${basePath}${proname}/operate/activitys/activity_query.do?value={param}&csaHost={csmHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsActivity(data.csmActivity).csaTitle}" value="${data.csmActivity}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/operate/activitys/activity_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************来源活动字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmActivity']>0">
				${lz:set("注释","****当来源活动字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmActivity")}
			  	<input type="hidden" name="csmActivity$on" id="CsActivity$on" value="true"/>
			  	<jsp:include page="/admin/operate/activitys/activity.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmActivity}
			 	
			 ${before$csmSaler}
			 
			 ${lz:set("注释","*****************销售人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmSaler" : (lz:join(relateObject,"$csmSaler")))}
			 ${lz:set("cname",lz:join(relateObject,".csmSaler"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmSaler || #request.defines['csmSaler']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmSaler":relateObject}" id="form-dl-csmSaler">
					<dt>销售人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmSalerYesNot=="not"?"checked-not":""}${data.csmSalerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmSalerYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmSaler}">
				 					${get:SrvUser(data.csmSaler).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************销售人员字段的查询代码结束*****************")}
			
			
			${after$csmSaler}
			 	
			 ${before$csmMarket}
			 
			 ${lz:set("注释","*****************营销标记字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmMarket" : (lz:join(relateObject,"$csmMarket")))}
			 ${lz:set("cname",lz:join(relateObject,".csmMarket"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmMarket || #request.defines['csmMarket']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmMarket":relateObject}" id="form-dl-csmMarket">
					<dt>营销标记：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmMarketYesNot=="not"?"checked-not":""}${data.csmMarketYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmMarketYesNot}"/>
		 			<select id="${NAME}" name="${NAME}" >
		 				<option value="">全部</option>
					    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'MEMBER_MARKET') )}
						<s:iterator value="#request.items" id="item" status="i">
						<option value="#${item.sp_id}#" ${lz:idin(data.csmMarket,item.sp_id)?"selected":""}>${item.sp_name}</option>
						</s:iterator>
						${lz:set("items",null)}
					</select>
					<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************营销标记字段的查询代码结束*****************")}
			
			
			${after$csmMarket}
			 	
			 ${before$csmTag}
			 
			 ${lz:set("注释","*****************会员标签字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmTag" : (lz:join(relateObject,"$csmTag")))}
			 ${lz:set("cname",lz:join(relateObject,".csmTag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmTag || #request.defines['csmTag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmTag":relateObject}" id="form-dl-csmTag">
					<dt>会员标签：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmTagYesNot=="not"?"checked-not":""}${data.csmTagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmTagYesNot}"/>
		 			<select id="${NAME}" name="${NAME}" >
		 				<option value="">全部</option>
					    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'MEMBER_TAG') )}
						<s:iterator value="#request.items" id="item" status="i">
						<option value="#${item.sp_id}#" ${lz:idin(data.csmTag,item.sp_id)?"selected":""}>${item.sp_name}</option>
						</s:iterator>
						${lz:set("items",null)}
					</select>
					<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************会员标签字段的查询代码结束*****************")}
			
			
			${after$csmTag}
			 	
			 ${before$csmMark}
			 
			 ${lz:set("注释","*****************备忘标记字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmMark" : (lz:join(relateObject,"$csmMark")))}
			 ${lz:set("cname",lz:join(relateObject,".csmMark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmMark || #request.defines['csmMark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmMark":relateObject}" id="form-dl-csmMark">
					<dt>备忘标记：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmMarkYesNot=="not"?"checked-not":""}${data.csmMarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmMarkYesNot}"/>
		 			<select id="${NAME}" name="${NAME}" >
		 				<option value="">全部</option>
					    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'MEMBER_MARK') )}
						<s:iterator value="#request.items" id="item" status="i">
						<option value="#${item.sp_id}#" ${lz:idin(data.csmMark,item.sp_id)?"selected":""}>${item.sp_name}</option>
						</s:iterator>
						${lz:set("items",null)}
					</select>
					<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备忘标记字段的查询代码结束*****************")}
			
			
			${after$csmMark}
			 	
			 ${before$csmVisitFlag}
			 
			 ${lz:set("注释","*****************回访标记字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmVisitFlag" : (lz:join(relateObject,"$csmVisitFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".csmVisitFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmVisitFlag || #request.defines['csmVisitFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmVisitFlag":relateObject}" id="form-dl-csmVisitFlag">
					<dt>回访标记：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmVisitFlagYesNot=="not"?"checked-not":""}${data.csmVisitFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmVisitFlagYesNot}"/>
		 			<select id="${NAME}" name="${NAME}" >
		 				<option value="">全部</option>
					    ${lz:set("items", lz:query("ccclubs_system","select * from cs_quest_template where 1=1") )}
						<s:iterator value="#request.items" id="item" status="i">
						<option value="#${item.csqt_id}#" ${lz:idin(data.csmVisitFlag,item.csqt_id)?"selected":""}>${item.csqt_name}</option>
						</s:iterator>
						${lz:set("items",null)}
					</select>
					<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/csm/revisit/quest_details.do?key='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************回访标记字段的查询代码结束*****************")}
			
			
			${after$csmVisitFlag}
			 	
			 ${before$csmMask}
			 
			 ${lz:set("注释","*****************程序掩码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmMask" : (lz:join(relateObject,"$csmMask")))}
			 ${lz:set("cname",lz:join(relateObject,".csmMask"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmMask || #request.defines['csmMask']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmMask":relateObject}" id="form-dl-csmMask">
					<dt>程序掩码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmMaskYesNot=="not"?"checked-not":""}${data.csmMaskYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmMaskYesNot}"/>
						<!-- 数值型的checkbox，暂时以select方式查询，后面改成多值查询 -->				
						<select id="${NAME}" name="${NAME}" >
			 				<option value="">全部</option>
							<option value="1" ${lz:bitin(data.csmMask,1)?"selected":""}>已回流支付宝</option>
							<option value="2" ${lz:bitin(data.csmMask,2)?"selected":""}>保留2</option>
							<option value="4" ${lz:bitin(data.csmMask,4)?"selected":""}>保留4</option>
							<option value="8" ${lz:bitin(data.csmMask,8)?"selected":""}>保留8</option>
						</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************程序掩码字段的查询代码结束*****************")}
			
			
			${after$csmMask}
			 	
			 ${before$csmRemark}
			 
			 ${lz:set("注释","*****************备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmRemark" : (lz:join(relateObject,"$csmRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".csmRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmRemark || #request.defines['csmRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmRemark":relateObject}" id="form-dl-csmRemark">
					<dt>备　　注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmRemarkYesNot=="not"?"checked-not":""}${data.csmRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csmRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注字段的查询代码结束*****************")}
			
			
			${after$csmRemark}
			 	
			 ${before$csmVMobile}
			 
			 ${lz:set("注释","*****************手机认证字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmVMobile" : (lz:join(relateObject,"$csmVMobile")))}
			 ${lz:set("cname",lz:join(relateObject,".csmVMobile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmVMobile || #request.defines['csmVMobile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmVMobile":relateObject}" id="form-dl-csmVMobile">
					<dt>手机认证：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmVMobileYesNot=="not"?"checked-not":""}${data.csmVMobileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmVMobileYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmVMobile==0?"selected":""}>未认证</option>
							<option value="1" ${data.csmVMobile==1?"selected":""}>已认证</option>
							<option value="2" ${data.csmVMobile==2?"selected":""}>等待认证</option>
							<option value="3" ${data.csmVMobile==3?"selected":""}>认证失败</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************手机认证字段的查询代码结束*****************")}
			
			
			${after$csmVMobile}
			 	
			 ${before$csmVEmail}
			 
			 ${lz:set("注释","*****************邮箱认证字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmVEmail" : (lz:join(relateObject,"$csmVEmail")))}
			 ${lz:set("cname",lz:join(relateObject,".csmVEmail"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmVEmail || #request.defines['csmVEmail']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmVEmail":relateObject}" id="form-dl-csmVEmail">
					<dt>邮箱认证：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmVEmailYesNot=="not"?"checked-not":""}${data.csmVEmailYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmVEmailYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmVEmail==0?"selected":""}>未认证</option>
							<option value="1" ${data.csmVEmail==1?"selected":""}>已认证</option>
							<option value="2" ${data.csmVEmail==2?"selected":""}>等待认证</option>
							<option value="3" ${data.csmVEmail==3?"selected":""}>认证失败</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************邮箱认证字段的查询代码结束*****************")}
			
			
			${after$csmVEmail}
			 	
			 ${before$csmVReal}
			 
			 ${lz:set("注释","*****************实名认证字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmVReal" : (lz:join(relateObject,"$csmVReal")))}
			 ${lz:set("cname",lz:join(relateObject,".csmVReal"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmVReal || #request.defines['csmVReal']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmVReal":relateObject}" id="form-dl-csmVReal">
					<dt>实名认证：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmVRealYesNot=="not"?"checked-not":""}${data.csmVRealYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmVRealYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmVReal==0?"selected":""}>未认证</option>
							<option value="1" ${data.csmVReal==1?"selected":""}>已认证</option>
							<option value="2" ${data.csmVReal==2?"selected":""}>等待认证</option>
							<option value="3" ${data.csmVReal==3?"selected":""}>认证失败</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************实名认证字段的查询代码结束*****************")}
			
			
			${after$csmVReal}
			 	
			 ${before$csmVDrive}
			 
			 ${lz:set("注释","*****************驾驶认证字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmVDrive" : (lz:join(relateObject,"$csmVDrive")))}
			 ${lz:set("cname",lz:join(relateObject,".csmVDrive"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmVDrive || #request.defines['csmVDrive']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmVDrive":relateObject}" id="form-dl-csmVDrive">
					<dt>驾驶认证：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmVDriveYesNot=="not"?"checked-not":""}${data.csmVDriveYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmVDriveYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmVDrive==0?"selected":""}>未认证</option>
							<option value="1" ${data.csmVDrive==1?"selected":""}>已认证</option>
							<option value="2" ${data.csmVDrive==2?"selected":""}>等待认证</option>
							<option value="3" ${data.csmVDrive==3?"selected":""}>认证失败</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************驾驶认证字段的查询代码结束*****************")}
			
			
			${after$csmVDrive}
			 	
			 ${before$csmStatus}
			 
			 ${lz:set("注释","*****************可用状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmStatus" : (lz:join(relateObject,"$csmStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csmStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmStatus || #request.defines['csmStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmStatus":relateObject}" id="form-dl-csmStatus">
					<dt>可用状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmStatusYesNot=="not"?"checked-not":""}${data.csmStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csmStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csmStatus==0?"selected":""}>禁用</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************可用状态字段的查询代码结束*****************")}
			
			
			${after$csmStatus}