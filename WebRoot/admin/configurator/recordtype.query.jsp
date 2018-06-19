<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csrtId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrtId" : (lz:join(relateObject,"$csrtId")))}
			 ${lz:set("cname",lz:join(relateObject,".csrtId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrtId || #request.defines['csrtId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrtId":relateObject}" id="form-dl-csrtId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrtIdYesNot=="not"?"checked-not":""}${data.csrtIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrtIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csrtId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csrtId}
			 	
			 ${before$csrtTypeName}
			 
			 ${lz:set("注释","*****************类型名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrtTypeName" : (lz:join(relateObject,"$csrtTypeName")))}
			 ${lz:set("cname",lz:join(relateObject,".csrtTypeName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrtTypeName || #request.defines['csrtTypeName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrtTypeName":relateObject}" id="form-dl-csrtTypeName">
					<dt>类型名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrtTypeNameYesNot=="not"?"checked-not":""}${data.csrtTypeNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrtTypeNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csrtTypeName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************类型名称字段的查询代码结束*****************")}
			
			
			${after$csrtTypeName}
			 	
			 ${before$csrtMoneyType}
			 
			 ${lz:set("注释","*****************券额类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrtMoneyType" : (lz:join(relateObject,"$csrtMoneyType")))}
			 ${lz:set("cname",lz:join(relateObject,".csrtMoneyType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrtMoneyType || #request.defines['csrtMoneyType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrtMoneyType":relateObject}" id="form-dl-csrtMoneyType">
					<dt>券额类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrtMoneyTypeYesNot=="not"?"checked-not":""}${data.csrtMoneyTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrtMoneyTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csrtMoneyType==0?"selected":""}>保证金</option>
							<option value="1" ${data.csrtMoneyType==1?"selected":""}>现金券</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************券额类型字段的查询代码结束*****************")}
			
			
			${after$csrtMoneyType}
			 	
			 ${before$csrtIsIncome}
			 
			 ${lz:set("注释","*****************是否收入项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrtIsIncome" : (lz:join(relateObject,"$csrtIsIncome")))}
			 ${lz:set("cname",lz:join(relateObject,".csrtIsIncome"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrtIsIncome || #request.defines['csrtIsIncome']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrtIsIncome":relateObject}" id="form-dl-csrtIsIncome">
					<dt>是否收入项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrtIsIncomeYesNot=="not"?"checked-not":""}${data.csrtIsIncomeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrtIsIncomeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csrtIsIncome=='true'?"selected":""}>是</option>
							<option value="false" ${data.csrtIsIncome=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************是否收入项字段的查询代码结束*****************")}
			
			
			${after$csrtIsIncome}
			 	
			 ${before$csrtCanInvoice}
			 
			 ${lz:set("注释","*****************是否开票项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrtCanInvoice" : (lz:join(relateObject,"$csrtCanInvoice")))}
			 ${lz:set("cname",lz:join(relateObject,".csrtCanInvoice"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrtCanInvoice || #request.defines['csrtCanInvoice']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrtCanInvoice":relateObject}" id="form-dl-csrtCanInvoice">
					<dt>是否开票项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrtCanInvoiceYesNot=="not"?"checked-not":""}${data.csrtCanInvoiceYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrtCanInvoiceYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csrtCanInvoice=='true'?"selected":""}>是</option>
							<option value="false" ${data.csrtCanInvoice=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************是否开票项字段的查询代码结束*****************")}
			
			
			${after$csrtCanInvoice}
			 	
			 ${before$csrtSubject}
			 
			 ${lz:set("注释","*****************对应科目字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrtSubject" : (lz:join(relateObject,"$csrtSubject")))}
			 ${lz:set("cname",lz:join(relateObject,".csrtSubject"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrtSubject || #request.defines['csrtSubject']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrtSubject":relateObject}" id="form-dl-csrtSubject">
					<dt>对应科目：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrtSubjectYesNot=="not"?"checked-not":""}${data.csrtSubjectYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrtSubjectYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/configurator/recordsubject_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrtSubject}">
				 					${get:CsRecordSubject(data.csrtSubject).csrsName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/recordsubject_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************对应科目字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrtSubject']>0">
				${lz:set("注释","****当对应科目字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrtSubject")}
			  	<input type="hidden" name="csrtSubject$on" id="CsRecordSubject$on" value="true"/>
			  	<jsp:include page="/admin/configurator/recordsubject.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrtSubject}
			 	
			 ${before$csrtStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrtStatus" : (lz:join(relateObject,"$csrtStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csrtStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrtStatus || #request.defines['csrtStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrtStatus":relateObject}" id="form-dl-csrtStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrtStatusYesNot=="not"?"checked-not":""}${data.csrtStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrtStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csrtStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csrtStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csrtStatus}