<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csorId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorId" : (lz:join(relateObject,"$csorId")))}
			 ${lz:set("cname",lz:join(relateObject,".csorId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorId || #request.defines['csorId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorId":relateObject}" id="form-dl-csorId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorIdYesNot=="not"?"checked-not":""}${data.csorIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csorId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csorId}
			 	
			 ${before$csorHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorHost" : (lz:join(relateObject,"$csorHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csorHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorHost || #request.defines['csorHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorHost":relateObject}" id="form-dl-csorHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorHostYesNot=="not"?"checked-not":""}${data.csorHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csorHost}">
				 					${get:SrvHost(data.csorHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csorHost}
			 	
			 ${before$csorTitle}
			 
			 ${lz:set("注释","*****************摘要字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorTitle" : (lz:join(relateObject,"$csorTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".csorTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorTitle || #request.defines['csorTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorTitle":relateObject}" id="form-dl-csorTitle">
					<dt>摘　　要：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorTitleYesNot=="not"?"checked-not":""}${data.csorTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csorTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************摘要字段的查询代码结束*****************")}
			
			
			${after$csorTitle}
			 	
			 ${before$csorMoneyType}
			 
			 ${lz:set("注释","*****************金额类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorMoneyType" : (lz:join(relateObject,"$csorMoneyType")))}
			 ${lz:set("cname",lz:join(relateObject,".csorMoneyType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorMoneyType || #request.defines['csorMoneyType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorMoneyType":relateObject}" id="form-dl-csorMoneyType">
					<dt>金额类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorMoneyTypeYesNot=="not"?"checked-not":""}${data.csorMoneyTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorMoneyTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csorMoneyType==0?"selected":""}>保证金</option>
							<option value="1" ${data.csorMoneyType==1?"selected":""}>优惠券</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************金额类型字段的查询代码结束*****************")}
			
			
			${after$csorMoneyType}
			 	
			 ${before$csorRecordType}
			 
			 ${lz:set("注释","*****************科目类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorRecordType" : (lz:join(relateObject,"$csorRecordType")))}
			 ${lz:set("cname",lz:join(relateObject,".csorRecordType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorRecordType || #request.defines['csorRecordType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorRecordType":relateObject}" id="form-dl-csorRecordType">
					<dt>科目类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorRecordTypeYesNot=="not"?"checked-not":""}${data.csorRecordTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorRecordTypeYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/configurator/recordsubject_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csorRecordType}">
				 					${get:CsRecordSubject(data.csorRecordType).csrsName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/recordsubject_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************科目类型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csorRecordType']>0">
				${lz:set("注释","****当科目类型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csorRecordType")}
			  	<input type="hidden" name="csorRecordType$on" id="CsRecordSubject$on" value="true"/>
			  	<jsp:include page="/admin/configurator/recordsubject.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csorRecordType}
			 	
			 ${before$csorIn}
			 
			 ${lz:set("注释","*****************收入字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorIn" : (lz:join(relateObject,"$csorIn")))}
			 ${lz:set("cname",lz:join(relateObject,".csorIn"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorIn || #request.defines['csorIn']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorIn":relateObject}" id="form-dl-csorIn">
					<dt>收　　入：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorInYesNot=="not"?"checked-not":""}${data.csorInYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorInYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csorInMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csorInMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************收入字段的查询代码结束*****************")}
			
			
			${after$csorIn}
			 	
			 ${before$csorOut}
			 
			 ${lz:set("注释","*****************支出字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorOut" : (lz:join(relateObject,"$csorOut")))}
			 ${lz:set("cname",lz:join(relateObject,".csorOut"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorOut || #request.defines['csorOut']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorOut":relateObject}" id="form-dl-csorOut">
					<dt>支　　出：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorOutYesNot=="not"?"checked-not":""}${data.csorOutYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorOutYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csorOutMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csorOutMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************支出字段的查询代码结束*****************")}
			
			
			${after$csorOut}
			 	
			 ${before$csorRecord}
			 
			 ${lz:set("注释","*****************关联消费记录字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorRecord" : (lz:join(relateObject,"$csorRecord")))}
			 ${lz:set("cname",lz:join(relateObject,".csorRecord"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorRecord || #request.defines['csorRecord']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorRecord":relateObject}" id="form-dl-csorRecord">
					<dt>关联消费记录：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorRecordYesNot=="not"?"checked-not":""}${data.csorRecordYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorRecordYesNot}"/>
			 			<input title="请输入消费记录编号进行查询" class="combox" action="${basePath}${proname}/finance/record_query.do?value={param}&csrHost={csorHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsRecord(data.csorRecord).csrId}" value="${data.csorRecord}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/finance/record_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联消费记录字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csorRecord']>0">
				${lz:set("注释","****当关联消费记录字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csorRecord")}
			  	<input type="hidden" name="csorRecord$on" id="CsRecord$on" value="true"/>
			  	<jsp:include page="/admin/finance/record.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csorRecord}
			 	
			 ${before$csorMember}
			 
			 ${lz:set("注释","*****************关联会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorMember" : (lz:join(relateObject,"$csorMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csorMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorMember || #request.defines['csorMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorMember":relateObject}" id="form-dl-csorMember">
					<dt>关联会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorMemberYesNot=="not"?"checked-not":""}${data.csorMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csorHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csorMember).csmName}" value="${data.csorMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csorMember']>0">
				${lz:set("注释","****当关联会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csorMember")}
			  	<input type="hidden" name="csorMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csorMember}
			 	
			 ${before$csorOrder}
			 
			 ${lz:set("注释","*****************关联订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorOrder" : (lz:join(relateObject,"$csorOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csorOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorOrder || #request.defines['csorOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorOrder":relateObject}" id="form-dl-csorOrder">
					<dt>关联订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorOrderYesNot=="not"?"checked-not":""}${data.csorOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csorHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.csorOrder).csoId}" value="${data.csorOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csorOrder']>0">
				${lz:set("注释","****当关联订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csorOrder")}
			  	<input type="hidden" name="csorOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csorOrder}
			 	
			 ${before$csorObject}
			 
			 ${lz:set("注释","*****************其它关联字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorObject" : (lz:join(relateObject,"$csorObject")))}
			 ${lz:set("cname",lz:join(relateObject,".csorObject"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorObject || #request.defines['csorObject']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorObject":relateObject}" id="form-dl-csorObject">
					<dt>其它关联：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorObjectYesNot=="not"?"checked-not":""}${data.csorObjectYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorObjectYesNot}"/>
			 		<input title="请输入动态查询" class="combox" generic="csorObjectGeneric" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:generic(data.csorObject)}" value="${data.csorObject}"/>
			 		${lz:set("models",get:models("CsTrouble,CsViolat,CsRepair,CsMaintain"))}
				 	<select id="csorObjectGeneric" style="display:none;">
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
			
			
			${after$csorObject}
			 	
			 ${before$csorDateTime}
			 
			 ${lz:set("注释","*****************发生时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorDateTime" : (lz:join(relateObject,"$csorDateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csorDateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorDateTime || #request.defines['csorDateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorDateTime":relateObject}" id="form-dl-csorDateTime">
					<dt>发生时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorDateTimeYesNot=="not"?"checked-not":""}${data.csorDateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorDateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csorDateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csorDateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csorDateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csorDateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>发生时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csorDateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发生时间字段的查询代码结束*****************")}
			
			
			${after$csorDateTime}
			 	
			 ${before$csorCashNo}
			 
			 ${lz:set("注释","*****************结账流水字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorCashNo" : (lz:join(relateObject,"$csorCashNo")))}
			 ${lz:set("cname",lz:join(relateObject,".csorCashNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorCashNo || #request.defines['csorCashNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorCashNo":relateObject}" id="form-dl-csorCashNo">
					<dt>结账流水：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorCashNoYesNot=="not"?"checked-not":""}${data.csorCashNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorCashNoYesNot}"/>
			 			<input title="请输入提现记录流水号进行查询" class="combox" action="${basePath}${proname}/finance/cashrecord_query.do?value={param}&cscrHost={csorHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCashRecord(data.csorCashNo).cscrTradeNo}" value="${data.csorCashNo}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/finance/cashrecord_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结账流水字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csorCashNo']>0">
				${lz:set("注释","****当结账流水字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csorCashNo")}
			  	<input type="hidden" name="csorCashNo$on" id="CsCashRecord$on" value="true"/>
			  	<jsp:include page="/admin/finance/cashrecord.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csorCashNo}
			 	
			 ${before$csorAdder}
			 
			 ${lz:set("注释","*****************添加人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorAdder" : (lz:join(relateObject,"$csorAdder")))}
			 ${lz:set("cname",lz:join(relateObject,".csorAdder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorAdder || #request.defines['csorAdder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorAdder":relateObject}" id="form-dl-csorAdder">
					<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorAdderYesNot=="not"?"checked-not":""}${data.csorAdderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorAdderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csorAdder}">
				 					${get:SrvUser(data.csorAdder).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人字段的查询代码结束*****************")}
			
			
			${after$csorAdder}
			 	
			 ${before$csorStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csorStatus" : (lz:join(relateObject,"$csorStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csorStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csorStatus || #request.defines['csorStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csorStatus":relateObject}" id="form-dl-csorStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csorStatusYesNot=="not"?"checked-not":""}${data.csorStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csorStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csorStatus==1?"selected":""}>正常</option>
							<option value="2" ${data.csorStatus==2?"selected":""}>争议</option>
							<option value="0" ${data.csorStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csorStatus}