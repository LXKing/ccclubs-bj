<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csrcId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcId" : (lz:join(relateObject,"$csrcId")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcId || #request.defines['csrcId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcId":relateObject}" id="form-dl-csrcId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcIdYesNot=="not"?"checked-not":""}${data.csrcIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csrcId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csrcId}
			 	
			 ${before$csrcHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcHost" : (lz:join(relateObject,"$csrcHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcHost || #request.defines['csrcHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcHost":relateObject}" id="form-dl-csrcHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcHostYesNot=="not"?"checked-not":""}${data.csrcHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrcHost}">
				 					${get:SrvHost(data.csrcHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csrcHost}
			 	
			 ${before$csrcNumber}
			 
			 ${lz:set("注释","*****************卡号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcNumber" : (lz:join(relateObject,"$csrcNumber")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcNumber"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcNumber || #request.defines['csrcNumber']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcNumber":relateObject}" id="form-dl-csrcNumber">
					<dt>卡　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcNumberYesNot=="not"?"checked-not":""}${data.csrcNumberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcNumberYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csrcNumber}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************卡号字段的查询代码结束*****************")}
			
			
			${after$csrcNumber}
			 	
			 ${before$csrcType}
			 
			 ${lz:set("注释","*****************类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcType" : (lz:join(relateObject,"$csrcType")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcType || #request.defines['csrcType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcType":relateObject}" id="form-dl-csrcType">
					<dt>类　　型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcTypeYesNot=="not"?"checked-not":""}${data.csrcTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csrcType==0?"selected":""}>现金券</option>
							<option value="1" ${data.csrcType==1?"selected":""}>免费小时</option>
							<option value="2" ${data.csrcType==2?"selected":""}>红包</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************类型字段的查询代码结束*****************")}
			
			
			${after$csrcType}
			 	
			 ${before$csrcDenomination}
			 
			 ${lz:set("注释","*****************面额字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcDenomination" : (lz:join(relateObject,"$csrcDenomination")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcDenomination"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcDenomination || #request.defines['csrcDenomination']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcDenomination":relateObject}" id="form-dl-csrcDenomination">
					<dt>面　　额：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcDenominationYesNot=="not"?"checked-not":""}${data.csrcDenominationYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcDenominationYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csrcDenominationMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csrcDenominationMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************面额字段的查询代码结束*****************")}
			
			
			${after$csrcDenomination}
			 	
			 ${before$csrcMethod}
			 
			 ${lz:set("注释","*****************分类字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcMethod" : (lz:join(relateObject,"$csrcMethod")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcMethod"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcMethod || #request.defines['csrcMethod']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcMethod":relateObject}" id="form-dl-csrcMethod">
					<dt>分　　类：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcMethodYesNot=="not"?"checked-not":""}${data.csrcMethodYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcMethodYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", get:propertys("充值卡分类","rcgcard_method"))}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.spId}" ${data.csrcMethod==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************分类字段的查询代码结束*****************")}
			
			
			${after$csrcMethod}
			 	
			 ${before$csrcValidity}
			 
			 ${lz:set("注释","*****************内容有效期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcValidity" : (lz:join(relateObject,"$csrcValidity")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcValidity"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcValidity || #request.defines['csrcValidity']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcValidity":relateObject}" id="form-dl-csrcValidity">
					<dt>内容有效期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcValidityYesNot=="not"?"checked-not":""}${data.csrcValidityYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcValidityYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csrcValidity==1?"selected":""}>1个月</option>
							<option value="2" ${data.csrcValidity==2?"selected":""}>2个月</option>
							<option value="3" ${data.csrcValidity==3?"selected":""}>3个月</option>
							<option value="6" ${data.csrcValidity==6?"selected":""}>半年</option>
							<option value="12" ${data.csrcValidity==12?"selected":""}>1年</option>
							<option value="0" ${data.csrcValidity==0?"selected":""}>自定义</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************内容有效期字段的查询代码结束*****************")}
			
			
			${after$csrcValidity}
			 	
			 ${before$csrcValidityUtil}
			 
			 ${lz:set("注释","*****************内容有效至字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcValidityUtil" : (lz:join(relateObject,"$csrcValidityUtil")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcValidityUtil"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcValidityUtil || #request.defines['csrcValidityUtil']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcValidityUtil":relateObject}" id="form-dl-csrcValidityUtil">
					<dt>内容有效至：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcValidityUtilYesNot=="not"?"checked-not":""}${data.csrcValidityUtilYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcValidityUtilYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csrcValidityUtilStart,"yyyy-MM-dd")}" end="${lz:date(data.csrcValidityUtilEnd,"yyyy-MM-dd")}" exp="${data.csrcValidityUtilExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csrcValidityUtilStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>内容有效至：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csrcValidityUtilEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************内容有效至字段的查询代码结束*****************")}
			
			
			${after$csrcValidityUtil}
			 	
			 ${before$csrcUtil}
			 
			 ${lz:set("注释","*****************充值有效至字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcUtil" : (lz:join(relateObject,"$csrcUtil")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcUtil"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcUtil || #request.defines['csrcUtil']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcUtil":relateObject}" id="form-dl-csrcUtil">
					<dt>充值有效至：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcUtilYesNot=="not"?"checked-not":""}${data.csrcUtilYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcUtilYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csrcUtilStart,"yyyy-MM-dd")}" end="${lz:date(data.csrcUtilEnd,"yyyy-MM-dd")}" exp="${data.csrcUtilExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csrcUtilStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>充值有效至：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csrcUtilEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************充值有效至字段的查询代码结束*****************")}
			
			
			${after$csrcUtil}
			 	
			 ${before$csrcMember}
			 
			 ${lz:set("注释","*****************使用会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcMember" : (lz:join(relateObject,"$csrcMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcMember || #request.defines['csrcMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcMember":relateObject}" id="form-dl-csrcMember">
					<dt>使用会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcMemberYesNot=="not"?"checked-not":""}${data.csrcMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csrcHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csrcMember).csmName}" value="${data.csrcMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************使用会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrcMember']>0">
				${lz:set("注释","****当使用会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrcMember")}
			  	<input type="hidden" name="csrcMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrcMember}
			 	
			 ${before$csrcUseTime}
			 
			 ${lz:set("注释","*****************使用时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcUseTime" : (lz:join(relateObject,"$csrcUseTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcUseTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcUseTime || #request.defines['csrcUseTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcUseTime":relateObject}" id="form-dl-csrcUseTime">
					<dt>使用时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcUseTimeYesNot=="not"?"checked-not":""}${data.csrcUseTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcUseTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csrcUseTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csrcUseTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csrcUseTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csrcUseTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>使用时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csrcUseTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************使用时间字段的查询代码结束*****************")}
			
			
			${after$csrcUseTime}
			 	
			 ${before$csrcAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcAddTime" : (lz:join(relateObject,"$csrcAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcAddTime || #request.defines['csrcAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcAddTime":relateObject}" id="form-dl-csrcAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcAddTimeYesNot=="not"?"checked-not":""}${data.csrcAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csrcAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csrcAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csrcAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csrcAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csrcAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csrcAddTime}
			 	
			 ${before$csrcStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrcStatus" : (lz:join(relateObject,"$csrcStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csrcStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrcStatus || #request.defines['csrcStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrcStatus":relateObject}" id="form-dl-csrcStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrcStatusYesNot=="not"?"checked-not":""}${data.csrcStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrcStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csrcStatus==1?"selected":""}>库存</option>
							<option value="2" ${data.csrcStatus==2?"selected":""}>出库(发出)</option>
							<option value="3" ${data.csrcStatus==3?"selected":""}>已使用</option>
							<option value="0" ${data.csrcStatus==0?"selected":""}>无效卡</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csrcStatus}