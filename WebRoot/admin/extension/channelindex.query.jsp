<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csciId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csciId" : (lz:join(relateObject,"$csciId")))}
			 ${lz:set("cname",lz:join(relateObject,".csciId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csciId || #request.defines['csciId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csciId":relateObject}" id="form-dl-csciId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csciIdYesNot=="not"?"checked-not":""}${data.csciIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csciIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csciId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csciId}
			 	
			 ${before$csciHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csciHost" : (lz:join(relateObject,"$csciHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csciHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csciHost || #request.defines['csciHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csciHost":relateObject}" id="form-dl-csciHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csciHostYesNot=="not"?"checked-not":""}${data.csciHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csciHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csciHost}">
				 					${get:SrvHost(data.csciHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csciHost}
			 	
			 ${before$csciName}
			 
			 ${lz:set("注释","*****************指标名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csciName" : (lz:join(relateObject,"$csciName")))}
			 ${lz:set("cname",lz:join(relateObject,".csciName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csciName || #request.defines['csciName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csciName":relateObject}" id="form-dl-csciName">
					<dt>指标名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csciNameYesNot=="not"?"checked-not":""}${data.csciNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csciNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csciName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************指标名称字段的查询代码结束*****************")}
			
			
			${after$csciName}
			 	
			 ${before$csciPlan}
			 
			 ${lz:set("注释","*****************所属渠道字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csciPlan" : (lz:join(relateObject,"$csciPlan")))}
			 ${lz:set("cname",lz:join(relateObject,".csciPlan"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csciPlan || #request.defines['csciPlan']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csciPlan":relateObject}" id="form-dl-csciPlan">
					<dt>所属渠道：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csciPlanYesNot=="not"?"checked-not":""}${data.csciPlanYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csciPlanYesNot}"/>
			 			<input title="请输入访问渠道渠道名称进行查询" class="combox" action="${basePath}${proname}/extension/channel_query.do?value={param}&cscHost={csciHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsChannel(data.csciPlan).cscName}" value="${data.csciPlan}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/extension/channel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属渠道字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csciPlan']>0">
				${lz:set("注释","****当所属渠道字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csciPlan")}
			  	<input type="hidden" name="csciPlan$on" id="CsChannel$on" value="true"/>
			  	<jsp:include page="/admin/extension/channel.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csciPlan}
			 	
			 ${before$csciType}
			 
			 ${lz:set("注释","*****************指标类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csciType" : (lz:join(relateObject,"$csciType")))}
			 ${lz:set("cname",lz:join(relateObject,".csciType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csciType || #request.defines['csciType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csciType":relateObject}" id="form-dl-csciType">
					<dt>指标类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csciTypeYesNot=="not"?"checked-not":""}${data.csciTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csciTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csciType==0?"selected":""}>其它</option>
							<option value="1" ${data.csciType==1?"selected":""}>PV</option>
							<option value="2" ${data.csciType==2?"selected":""}>UV</option>
							<option value="3" ${data.csciType==3?"selected":""}>IP</option>
							<option value="4" ${data.csciType==4?"selected":""}>注册</option>
							<option value="5" ${data.csciType==5?"selected":""}>充值</option>
							<option value="6" ${data.csciType==6?"selected":""}>订单</option>
							<option value="7" ${data.csciType==7?"selected":""}>维护</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************指标类型字段的查询代码结束*****************")}
			
			
			${after$csciType}
			 	
			 ${before$csciTarget}
			 
			 ${lz:set("注释","*****************目标设定字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csciTarget" : (lz:join(relateObject,"$csciTarget")))}
			 ${lz:set("cname",lz:join(relateObject,".csciTarget"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csciTarget || #request.defines['csciTarget']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csciTarget":relateObject}" id="form-dl-csciTarget">
					<dt>目标设定：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csciTargetYesNot=="not"?"checked-not":""}${data.csciTargetYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csciTargetYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csciTargetMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csciTargetMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************目标设定字段的查询代码结束*****************")}
			
			
			${after$csciTarget}
			 	
			 ${before$csciResult}
			 
			 ${lz:set("注释","*****************实际完成字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csciResult" : (lz:join(relateObject,"$csciResult")))}
			 ${lz:set("cname",lz:join(relateObject,".csciResult"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csciResult || #request.defines['csciResult']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csciResult":relateObject}" id="form-dl-csciResult">
					<dt>实际完成：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csciResultYesNot=="not"?"checked-not":""}${data.csciResultYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csciResultYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csciResultMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csciResultMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************实际完成字段的查询代码结束*****************")}
			
			
			${after$csciResult}
			 	
			 ${before$csciRate}
			 
			 ${lz:set("注释","*****************完成率字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csciRate" : (lz:join(relateObject,"$csciRate")))}
			 ${lz:set("cname",lz:join(relateObject,".csciRate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csciRate || #request.defines['csciRate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csciRate":relateObject}" id="form-dl-csciRate">
					<dt>完&nbsp;&nbsp;成&nbsp;&nbsp;率：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csciRateYesNot=="not"?"checked-not":""}${data.csciRateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csciRateYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csciRateMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csciRateMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************完成率字段的查询代码结束*****************")}
			
			
			${after$csciRate}
			 	
			 ${before$csciStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csciStatus" : (lz:join(relateObject,"$csciStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csciStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csciStatus || #request.defines['csciStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csciStatus":relateObject}" id="form-dl-csciStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csciStatusYesNot=="not"?"checked-not":""}${data.csciStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csciStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csciStatus==0?"selected":""}>执行中</option>
							<option value="1" ${data.csciStatus==1?"selected":""}>已完成</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csciStatus}