<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$shId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shId" : (lz:join(relateObject,"$shId")))}
			 ${lz:set("cname",lz:join(relateObject,".shId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shId || #request.defines['shId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shId":relateObject}" id="form-dl-shId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shIdYesNot=="not"?"checked-not":""}${data.shIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.shId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$shId}
			 	
			 ${before$shName}
			 
			 ${lz:set("注释","*****************城市名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shName" : (lz:join(relateObject,"$shName")))}
			 ${lz:set("cname",lz:join(relateObject,".shName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shName || #request.defines['shName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shName":relateObject}" id="form-dl-shName">
					<dt>城市名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shNameYesNot=="not"?"checked-not":""}${data.shNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.shName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市名称字段的查询代码结束*****************")}
			
			
			${after$shName}
			 	
			 ${before$shOper}
			 
			 ${lz:set("注释","*****************运营商字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shOper" : (lz:join(relateObject,"$shOper")))}
			 ${lz:set("cname",lz:join(relateObject,".shOper"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shOper || #request.defines['shOper']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shOper":relateObject}" id="form-dl-shOper">
					<dt>运&nbsp;&nbsp;营&nbsp;&nbsp;商：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shOperYesNot=="not"?"checked-not":""}${data.shOperYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shOperYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/configurator/oper_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.shOper}">
				 					${get:CsOper(data.shOper).csoName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/oper_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************运营商字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['shOper']>0">
				${lz:set("注释","****当运营商字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","shOper")}
			  	<input type="hidden" name="shOper$on" id="CsOper$on" value="true"/>
			  	<jsp:include page="/admin/configurator/oper.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$shOper}
			 	
			 ${before$shFeeType}
			 
			 ${lz:set("注释","*****************默认计费方式字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shFeeType" : (lz:join(relateObject,"$shFeeType")))}
			 ${lz:set("cname",lz:join(relateObject,".shFeeType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shFeeType || #request.defines['shFeeType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shFeeType":relateObject}" id="form-dl-shFeeType">
					<dt>默认计费方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shFeeTypeYesNot=="not"?"checked-not":""}${data.shFeeTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shFeeTypeYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/user/type_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.shFeeType}">
				 					${get:CsUserType(data.shFeeType).csutName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************默认计费方式字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['shFeeType']>0">
				${lz:set("注释","****当默认计费方式字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","shFeeType")}
			  	<input type="hidden" name="shFeeType$on" id="CsUserType$on" value="true"/>
			  	<jsp:include page="/admin/user/type.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$shFeeType}
			 	
			 ${before$shState}
			 
			 ${lz:set("注释","*****************运营状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shState" : (lz:join(relateObject,"$shState")))}
			 ${lz:set("cname",lz:join(relateObject,".shState"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shState || #request.defines['shState']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shState":relateObject}" id="form-dl-shState">
					<dt>运营状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shStateYesNot=="not"?"checked-not":""}${data.shStateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shStateYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.shState==0?"selected":""}>下线</option>
							<option value="1" ${data.shState==1?"selected":""}>上线</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************运营状态字段的查询代码结束*****************")}
			
			
			${after$shState}
			 	
			 ${before$shStatus}
			 
			 ${lz:set("注释","*****************有效状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shStatus" : (lz:join(relateObject,"$shStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".shStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shStatus || #request.defines['shStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shStatus":relateObject}" id="form-dl-shStatus">
					<dt>有效状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shStatusYesNot=="not"?"checked-not":""}${data.shStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.shStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.shStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************有效状态字段的查询代码结束*****************")}
			
			
			${after$shStatus}