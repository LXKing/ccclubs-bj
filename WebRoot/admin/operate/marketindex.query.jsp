<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csmiId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiId" : (lz:join(relateObject,"$csmiId")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiId || #request.defines['csmiId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiId":relateObject}" id="form-dl-csmiId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiIdYesNot=="not"?"checked-not":""}${data.csmiIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csmiId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csmiId}
			 	
			 ${before$csmiHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiHost" : (lz:join(relateObject,"$csmiHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiHost || #request.defines['csmiHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiHost":relateObject}" id="form-dl-csmiHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiHostYesNot=="not"?"checked-not":""}${data.csmiHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmiHost}">
				 					${get:SrvHost(data.csmiHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csmiHost}
			 	
			 ${before$csmiName}
			 
			 ${lz:set("注释","*****************指标名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiName" : (lz:join(relateObject,"$csmiName")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiName || #request.defines['csmiName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiName":relateObject}" id="form-dl-csmiName">
					<dt>指标名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiNameYesNot=="not"?"checked-not":""}${data.csmiNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csmiName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************指标名称字段的查询代码结束*****************")}
			
			
			${after$csmiName}
			 	
			 ${before$csmiPlan}
			 
			 ${lz:set("注释","*****************所属方案字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiPlan" : (lz:join(relateObject,"$csmiPlan")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiPlan"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiPlan || #request.defines['csmiPlan']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiPlan":relateObject}" id="form-dl-csmiPlan">
					<dt>所属方案：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiPlanYesNot=="not"?"checked-not":""}${data.csmiPlanYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiPlanYesNot}"/>
			 			<input title="请输入营销方案方案主题进行查询" class="combox" action="${basePath}${proname}/operate/marketplan_query.do?value={param}&csmpHost={csmiHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMarketPlan(data.csmiPlan).csmpName}" value="${data.csmiPlan}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/operate/marketplan_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属方案字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmiPlan']>0">
				${lz:set("注释","****当所属方案字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmiPlan")}
			  	<input type="hidden" name="csmiPlan$on" id="CsMarketPlan$on" value="true"/>
			  	<jsp:include page="/admin/operate/marketplan.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmiPlan}
			 	
			 ${before$csmiTarget}
			 
			 ${lz:set("注释","*****************目标设定字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiTarget" : (lz:join(relateObject,"$csmiTarget")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiTarget"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiTarget || #request.defines['csmiTarget']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiTarget":relateObject}" id="form-dl-csmiTarget">
					<dt>目标设定：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiTargetYesNot=="not"?"checked-not":""}${data.csmiTargetYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiTargetYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csmiTargetMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csmiTargetMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************目标设定字段的查询代码结束*****************")}
			
			
			${after$csmiTarget}
			 	
			 ${before$csmiResult}
			 
			 ${lz:set("注释","*****************实际完成字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiResult" : (lz:join(relateObject,"$csmiResult")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiResult"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiResult || #request.defines['csmiResult']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiResult":relateObject}" id="form-dl-csmiResult">
					<dt>实际完成：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiResultYesNot=="not"?"checked-not":""}${data.csmiResultYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiResultYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csmiResultMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csmiResultMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************实际完成字段的查询代码结束*****************")}
			
			
			${after$csmiResult}
			 	
			 ${before$csmiRate}
			 
			 ${lz:set("注释","*****************完成率字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiRate" : (lz:join(relateObject,"$csmiRate")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiRate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiRate || #request.defines['csmiRate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiRate":relateObject}" id="form-dl-csmiRate">
					<dt>完&nbsp;&nbsp;成&nbsp;&nbsp;率：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiRateYesNot=="not"?"checked-not":""}${data.csmiRateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiRateYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csmiRateMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csmiRateMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************完成率字段的查询代码结束*****************")}
			
			
			${after$csmiRate}
			 	
			 ${before$csmiStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiStatus" : (lz:join(relateObject,"$csmiStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiStatus || #request.defines['csmiStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiStatus":relateObject}" id="form-dl-csmiStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiStatusYesNot=="not"?"checked-not":""}${data.csmiStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmiStatus==0?"selected":""}>执行中</option>
							<option value="1" ${data.csmiStatus==1?"selected":""}>已完成</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csmiStatus}