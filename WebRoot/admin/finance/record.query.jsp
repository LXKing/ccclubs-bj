<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csrId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrId" : (lz:join(relateObject,"$csrId")))}
			 ${lz:set("cname",lz:join(relateObject,".csrId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrId || #request.defines['csrId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrId":relateObject}" id="form-dl-csrId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrIdYesNot=="not"?"checked-not":""}${data.csrIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csrId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csrId}
			 	
			 ${before$csrHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrHost" : (lz:join(relateObject,"$csrHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csrHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrHost || #request.defines['csrHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrHost":relateObject}" id="form-dl-csrHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrHostYesNot=="not"?"checked-not":""}${data.csrHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrHost}">
				 					${get:SrvHost(data.csrHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csrHost}
			 	
			 ${before$csrRemark}
			 
			 ${lz:set("注释","*****************摘要字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrRemark" : (lz:join(relateObject,"$csrRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".csrRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrRemark || #request.defines['csrRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrRemark":relateObject}" id="form-dl-csrRemark">
					<dt>摘　　要：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrRemarkYesNot=="not"?"checked-not":""}${data.csrRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csrRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************摘要字段的查询代码结束*****************")}
			
			
			${after$csrRemark}
			 	
			 ${before$csrMember}
			 
			 ${lz:set("注释","*****************消费会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrMember" : (lz:join(relateObject,"$csrMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csrMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrMember || #request.defines['csrMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrMember":relateObject}" id="form-dl-csrMember">
					<dt>消费会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrMemberYesNot=="not"?"checked-not":""}${data.csrMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csrHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csrMember).csmName}" value="${data.csrMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************消费会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrMember']>0">
				${lz:set("注释","****当消费会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrMember")}
			  	<input type="hidden" name="csrMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrMember}
			 	
			 ${before$csrMoneyType}
			 
			 ${lz:set("注释","*****************金额类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrMoneyType" : (lz:join(relateObject,"$csrMoneyType")))}
			 ${lz:set("cname",lz:join(relateObject,".csrMoneyType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrMoneyType || #request.defines['csrMoneyType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrMoneyType":relateObject}" id="form-dl-csrMoneyType">
					<dt>金额类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrMoneyTypeYesNot=="not"?"checked-not":""}${data.csrMoneyTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrMoneyTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csrMoneyType==0?"selected":""}>保证金</option>
							<option value="1" ${data.csrMoneyType==1?"selected":""}>现金券</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************金额类型字段的查询代码结束*****************")}
			
			
			${after$csrMoneyType}
			 	
			 ${before$csrRecordSubject}
			 
			 ${lz:set("注释","*****************科目类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrRecordSubject" : (lz:join(relateObject,"$csrRecordSubject")))}
			 ${lz:set("cname",lz:join(relateObject,".csrRecordSubject"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrRecordSubject || #request.defines['csrRecordSubject']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrRecordSubject":relateObject}" id="form-dl-csrRecordSubject">
					<dt>科目类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrRecordSubjectYesNot=="not"?"checked-not":""}${data.csrRecordSubjectYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrRecordSubjectYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/configurator/recordsubject_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrRecordSubject}">
				 					${get:CsRecordSubject(data.csrRecordSubject).csrsName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/recordsubject_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************科目类型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrRecordSubject']>0">
				${lz:set("注释","****当科目类型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrRecordSubject")}
			  	<input type="hidden" name="csrRecordSubject$on" id="CsRecordSubject$on" value="true"/>
			  	<jsp:include page="/admin/configurator/recordsubject.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrRecordSubject}
			 	
			 ${before$csrRecordType}
			 
			 ${lz:set("注释","*****************消费类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrRecordType" : (lz:join(relateObject,"$csrRecordType")))}
			 ${lz:set("cname",lz:join(relateObject,".csrRecordType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrRecordType || #request.defines['csrRecordType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrRecordType":relateObject}" id="form-dl-csrRecordType">
					<dt>消费类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrRecordTypeYesNot=="not"?"checked-not":""}${data.csrRecordTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrRecordTypeYesNot}"/>
			 			<input title="请输入消费类型类型名称进行查询" class="combox" action="${basePath}${proname}/configurator/recordtype_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsRecordType(data.csrRecordType).csrtTypeName}" value="${data.csrRecordType}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/recordtype_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************消费类型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrRecordType']>0">
				${lz:set("注释","****当消费类型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrRecordType")}
			  	<input type="hidden" name="csrRecordType$on" id="CsRecordType$on" value="true"/>
			  	<jsp:include page="/admin/configurator/recordtype.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrRecordType}
			 	
			 ${before$csrAmount}
			 
			 ${lz:set("注释","*****************数额字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrAmount" : (lz:join(relateObject,"$csrAmount")))}
			 ${lz:set("cname",lz:join(relateObject,".csrAmount"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrAmount || #request.defines['csrAmount']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrAmount":relateObject}" id="form-dl-csrAmount">
					<dt>数　　额：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrAmountYesNot=="not"?"checked-not":""}${data.csrAmountYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrAmountYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csrAmountMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csrAmountMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************数额字段的查询代码结束*****************")}
			
			
			${after$csrAmount}
			 	
			 ${before$csrAddTime}
			 
			 ${lz:set("注释","*****************时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrAddTime" : (lz:join(relateObject,"$csrAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csrAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrAddTime || #request.defines['csrAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrAddTime":relateObject}" id="form-dl-csrAddTime">
					<dt>时　　间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrAddTimeYesNot=="not"?"checked-not":""}${data.csrAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csrAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************时间字段的查询代码结束*****************")}
			
			
			${after$csrAddTime}
			 	
			 ${before$csrOrder}
			 
			 ${lz:set("注释","*****************关联订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrOrder" : (lz:join(relateObject,"$csrOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csrOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrOrder || #request.defines['csrOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrOrder":relateObject}" id="form-dl-csrOrder">
					<dt>关联订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrOrderYesNot=="not"?"checked-not":""}${data.csrOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csrHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.csrOrder).csoId}" value="${data.csrOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrOrder']>0">
				${lz:set("注释","****当关联订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrOrder")}
			  	<input type="hidden" name="csrOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrOrder}
			 	
			 ${before$csrObject}
			 
			 ${lz:set("注释","*****************其它关联字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrObject" : (lz:join(relateObject,"$csrObject")))}
			 ${lz:set("cname",lz:join(relateObject,".csrObject"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrObject || #request.defines['csrObject']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrObject":relateObject}" id="form-dl-csrObject">
					<dt>其它关联：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrObjectYesNot=="not"?"checked-not":""}${data.csrObjectYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrObjectYesNot}"/>
			 		<input title="请输入动态查询" class="combox" generic="csrObjectGeneric" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:generic(data.csrObject)}" value="${data.csrObject}"/>
			 		${lz:set("models",get:models(""))}
				 	<select id="csrObjectGeneric" style="display:none;">
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
			
			
			${after$csrObject}
			 	
			 ${before$csrEditor}
			 
			 ${lz:set("注释","*****************操作人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrEditor" : (lz:join(relateObject,"$csrEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".csrEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrEditor || #request.defines['csrEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrEditor":relateObject}" id="form-dl-csrEditor">
					<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrEditorYesNot=="not"?"checked-not":""}${data.csrEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrEditor}">
				 					${get:SrvUser(data.csrEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作人字段的查询代码结束*****************")}
			
			
			${after$csrEditor}