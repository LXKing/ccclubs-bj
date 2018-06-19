<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csgId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgId" : (lz:join(relateObject,"$csgId")))}
			 ${lz:set("cname",lz:join(relateObject,".csgId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgId || #request.defines['csgId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgId":relateObject}" id="form-dl-csgId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgIdYesNot=="not"?"checked-not":""}${data.csgIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csgId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csgId}
			 	
			 ${before$csgProduct}
			 
			 ${lz:set("注释","*****************所属产品字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgProduct" : (lz:join(relateObject,"$csgProduct")))}
			 ${lz:set("cname",lz:join(relateObject,".csgProduct"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgProduct || #request.defines['csgProduct']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgProduct":relateObject}" id="form-dl-csgProduct">
					<dt>所属产品：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgProductYesNot=="not"?"checked-not":""}${data.csgProductYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgProductYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/fee/product_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csgProduct}">
				 					${get:CsProduct(data.csgProduct).cspName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/product_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属产品字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csgProduct']>0">
				${lz:set("注释","****当所属产品字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csgProduct")}
			  	<input type="hidden" name="csgProduct$on" id="CsProduct$on" value="true"/>
			  	<jsp:include page="/admin/fee/product.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csgProduct}
			 	
			 ${before$csgUserType}
			 
			 ${lz:set("注释","*****************计费类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgUserType" : (lz:join(relateObject,"$csgUserType")))}
			 ${lz:set("cname",lz:join(relateObject,".csgUserType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgUserType || #request.defines['csgUserType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgUserType":relateObject}" id="form-dl-csgUserType">
					<dt>计费类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgUserTypeYesNot=="not"?"checked-not":""}${data.csgUserTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgUserTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", lz:query("ccclubs_system","select * from cs_user_type where 1=1 ") )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.csutId}" ${data.csgUserType==item.csutId?"selected":""}>${item.csutName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************计费类型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csgUserType']>0">
				${lz:set("注释","****当计费类型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csgUserType")}
			  	<input type="hidden" name="csgUserType$on" id="CsUserType$on" value="true"/>
			  	<jsp:include page="/admin/user/type.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csgUserType}
			 	
			 ${before$csgRule}
			 
			 ${lz:set("注释","*****************单位规则字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgRule" : (lz:join(relateObject,"$csgRule")))}
			 ${lz:set("cname",lz:join(relateObject,".csgRule"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgRule || #request.defines['csgRule']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgRule":relateObject}" id="form-dl-csgRule">
					<dt>单位规则：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgRuleYesNot=="not"?"checked-not":""}${data.csgRuleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgRuleYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/fee/rule_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csgRule}">
				 					${get:CsRule(data.csgRule).csrName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/rule_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************单位规则字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csgRule']>0">
				${lz:set("注释","****当单位规则字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csgRule")}
			  	<input type="hidden" name="csgRule$on" id="CsRule$on" value="true"/>
			  	<jsp:include page="/admin/fee/rule.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csgRule}
			 	
			 ${before$csgAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgAddTime" : (lz:join(relateObject,"$csgAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csgAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgAddTime || #request.defines['csgAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgAddTime":relateObject}" id="form-dl-csgAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgAddTimeYesNot=="not"?"checked-not":""}${data.csgAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csgAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csgAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csgAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csgAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csgAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csgAddTime}
			 	
			 ${before$csgStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgStatus" : (lz:join(relateObject,"$csgStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csgStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgStatus || #request.defines['csgStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgStatus":relateObject}" id="form-dl-csgStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgStatusYesNot=="not"?"checked-not":""}${data.csgStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csgStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csgStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csgStatus}