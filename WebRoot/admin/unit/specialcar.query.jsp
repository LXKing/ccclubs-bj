<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csscId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscId" : (lz:join(relateObject,"$csscId")))}
			 ${lz:set("cname",lz:join(relateObject,".csscId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscId || #request.defines['csscId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscId":relateObject}" id="form-dl-csscId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscIdYesNot=="not"?"checked-not":""}${data.csscIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csscId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csscId}
			 	
			 ${before$csscHost}
			 
			 ${lz:set("注释","*****************运营地区字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscHost" : (lz:join(relateObject,"$csscHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csscHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscHost || #request.defines['csscHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscHost":relateObject}" id="form-dl-csscHost">
					<dt>运营地区：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscHostYesNot=="not"?"checked-not":""}${data.csscHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csscHost}">
				 					${get:SrvHost(data.csscHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************运营地区字段的查询代码结束*****************")}
			
			
			${after$csscHost}
			 	
			 ${before$csscInfo}
			 
			 ${lz:set("注释","*****************企业信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscInfo" : (lz:join(relateObject,"$csscInfo")))}
			 ${lz:set("cname",lz:join(relateObject,".csscInfo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscInfo || #request.defines['csscInfo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscInfo":relateObject}" id="form-dl-csscInfo">
					<dt>企业信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscInfoYesNot=="not"?"checked-not":""}${data.csscInfoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscInfoYesNot}"/>
			 			<input title="请输入企业信息名称进行查询" class="combox" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csscHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsUnitInfo(data.csscInfo).csuiName}" value="${data.csscInfo}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************企业信息字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csscInfo']>0">
				${lz:set("注释","****当企业信息字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csscInfo")}
			  	<input type="hidden" name="csscInfo$on" id="CsUnitInfo$on" value="true"/>
			  	<jsp:include page="/admin/unit/info.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csscInfo}
			 	
			 ${before$csscPerson}
			 
			 ${lz:set("注释","*****************用车人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscPerson" : (lz:join(relateObject,"$csscPerson")))}
			 ${lz:set("cname",lz:join(relateObject,".csscPerson"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscPerson || #request.defines['csscPerson']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscPerson":relateObject}" id="form-dl-csscPerson">
					<dt>用车人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscPersonYesNot=="not"?"checked-not":""}${data.csscPersonYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscPersonYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/unit/person_query.do?size=-1&csupHost={csscHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csscPerson}">
				 					${get:CsUnitPerson(data.csscPerson).csupName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/person_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************用车人员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csscPerson']>0">
				${lz:set("注释","****当用车人员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csscPerson")}
			  	<input type="hidden" name="csscPerson$on" id="CsUnitPerson$on" value="true"/>
			  	<jsp:include page="/admin/unit/person.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csscPerson}
			 	
			 ${before$csscUnitOrder}
			 
			 ${lz:set("注释","*****************企业订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscUnitOrder" : (lz:join(relateObject,"$csscUnitOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csscUnitOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscUnitOrder || #request.defines['csscUnitOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscUnitOrder":relateObject}" id="form-dl-csscUnitOrder">
					<dt>企业订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscUnitOrderYesNot=="not"?"checked-not":""}${data.csscUnitOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscUnitOrderYesNot}"/>
			 			<input title="请输入企业订单系统订单进行查询" class="combox" action="${basePath}${proname}/unit/order_query.do?value={param}&csuoHost={csscHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsUnitOrder(data.csscUnitOrder).csuoOrder}" value="${data.csscUnitOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************企业订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csscUnitOrder']>0">
				${lz:set("注释","****当企业订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csscUnitOrder")}
			  	<input type="hidden" name="csscUnitOrder$on" id="CsUnitOrder$on" value="true"/>
			  	<jsp:include page="/admin/unit/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csscUnitOrder}
			 	
			 ${before$csscName}
			 
			 ${lz:set("注释","*****************乘客姓名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscName" : (lz:join(relateObject,"$csscName")))}
			 ${lz:set("cname",lz:join(relateObject,".csscName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscName || #request.defines['csscName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscName":relateObject}" id="form-dl-csscName">
					<dt>乘客姓名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscNameYesNot=="not"?"checked-not":""}${data.csscNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csscName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************乘客姓名字段的查询代码结束*****************")}
			
			
			${after$csscName}
			 	
			 ${before$csscCount}
			 
			 ${lz:set("注释","*****************乘车人数字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscCount" : (lz:join(relateObject,"$csscCount")))}
			 ${lz:set("cname",lz:join(relateObject,".csscCount"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscCount || #request.defines['csscCount']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscCount":relateObject}" id="form-dl-csscCount">
					<dt>乘车人数：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscCountYesNot=="not"?"checked-not":""}${data.csscCountYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscCountYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csscCountMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csscCountMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************乘车人数字段的查询代码结束*****************")}
			
			
			${after$csscCount}
			 	
			 ${before$csscCarModel}
			 
			 ${lz:set("注释","*****************预定车型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscCarModel" : (lz:join(relateObject,"$csscCarModel")))}
			 ${lz:set("cname",lz:join(relateObject,".csscCarModel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscCarModel || #request.defines['csscCarModel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscCarModel":relateObject}" id="form-dl-csscCarModel">
					<dt>预定车型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscCarModelYesNot=="not"?"checked-not":""}${data.csscCarModelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscCarModelYesNot}"/>
			 			<input title="请输入专车车型车型名称进行查询" class="combox" action="${basePath}${proname}/unit/specialmodel_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsSpecialModel(data.csscCarModel).cssmName}" value="${data.csscCarModel}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/specialmodel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************预定车型字段的查询代码结束*****************")}
			
			
			${after$csscCarModel}
			 	
			 ${before$csscCarNumber}
			 
			 ${lz:set("注释","*****************车牌号码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscCarNumber" : (lz:join(relateObject,"$csscCarNumber")))}
			 ${lz:set("cname",lz:join(relateObject,".csscCarNumber"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscCarNumber || #request.defines['csscCarNumber']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscCarNumber":relateObject}" id="form-dl-csscCarNumber">
					<dt>车牌号码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscCarNumberYesNot=="not"?"checked-not":""}${data.csscCarNumberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscCarNumberYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csscCarNumber}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车牌号码字段的查询代码结束*****************")}
			
			
			${after$csscCarNumber}
			 	
			 ${before$csscDriverName}
			 
			 ${lz:set("注释","*****************司机姓名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscDriverName" : (lz:join(relateObject,"$csscDriverName")))}
			 ${lz:set("cname",lz:join(relateObject,".csscDriverName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscDriverName || #request.defines['csscDriverName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscDriverName":relateObject}" id="form-dl-csscDriverName">
					<dt>司机姓名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscDriverNameYesNot=="not"?"checked-not":""}${data.csscDriverNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscDriverNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csscDriverName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************司机姓名字段的查询代码结束*****************")}
			
			
			${after$csscDriverName}
			 	
			 ${before$csscMsg}
			 
			 ${lz:set("注释","*****************客户留言字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscMsg" : (lz:join(relateObject,"$csscMsg")))}
			 ${lz:set("cname",lz:join(relateObject,".csscMsg"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscMsg || #request.defines['csscMsg']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscMsg":relateObject}" id="form-dl-csscMsg">
					<dt>客户留言：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscMsgYesNot=="not"?"checked-not":""}${data.csscMsgYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscMsgYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csscMsg}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************客户留言字段的查询代码结束*****************")}
			
			
			${after$csscMsg}
			 	
			 ${before$csscStartAddr}
			 
			 ${lz:set("注释","*****************起点地址字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscStartAddr" : (lz:join(relateObject,"$csscStartAddr")))}
			 ${lz:set("cname",lz:join(relateObject,".csscStartAddr"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscStartAddr || #request.defines['csscStartAddr']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscStartAddr":relateObject}" id="form-dl-csscStartAddr">
					<dt>起点地址：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscStartAddrYesNot=="not"?"checked-not":""}${data.csscStartAddrYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscStartAddrYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csscStartAddr}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************起点地址字段的查询代码结束*****************")}
			
			
			${after$csscStartAddr}
			 	
			 ${before$csscStartTime}
			 
			 ${lz:set("注释","*****************开始时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscStartTime" : (lz:join(relateObject,"$csscStartTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csscStartTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscStartTime || #request.defines['csscStartTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscStartTime":relateObject}" id="form-dl-csscStartTime">
					<dt>开始时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscStartTimeYesNot=="not"?"checked-not":""}${data.csscStartTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscStartTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csscStartTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csscStartTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csscStartTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csscStartTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>开始时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csscStartTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************开始时间字段的查询代码结束*****************")}
			
			
			${after$csscStartTime}
			 	
			 ${before$csscFinishAddr}
			 
			 ${lz:set("注释","*****************终点地址字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscFinishAddr" : (lz:join(relateObject,"$csscFinishAddr")))}
			 ${lz:set("cname",lz:join(relateObject,".csscFinishAddr"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscFinishAddr || #request.defines['csscFinishAddr']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscFinishAddr":relateObject}" id="form-dl-csscFinishAddr">
					<dt>终点地址：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscFinishAddrYesNot=="not"?"checked-not":""}${data.csscFinishAddrYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscFinishAddrYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csscFinishAddr}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************终点地址字段的查询代码结束*****************")}
			
			
			${after$csscFinishAddr}
			 	
			 ${before$csscFinishTime}
			 
			 ${lz:set("注释","*****************结束时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscFinishTime" : (lz:join(relateObject,"$csscFinishTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csscFinishTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscFinishTime || #request.defines['csscFinishTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscFinishTime":relateObject}" id="form-dl-csscFinishTime">
					<dt>结束时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscFinishTimeYesNot=="not"?"checked-not":""}${data.csscFinishTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscFinishTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csscFinishTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csscFinishTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csscFinishTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csscFinishTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>结束时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csscFinishTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结束时间字段的查询代码结束*****************")}
			
			
			${after$csscFinishTime}
			 	
			 ${before$csscScope}
			 
			 ${lz:set("注释","*****************用车评分字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscScope" : (lz:join(relateObject,"$csscScope")))}
			 ${lz:set("cname",lz:join(relateObject,".csscScope"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscScope || #request.defines['csscScope']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscScope":relateObject}" id="form-dl-csscScope">
					<dt>用车评分：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscScopeYesNot=="not"?"checked-not":""}${data.csscScopeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscScopeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csscScope==0?"selected":""}>未评分</option>
							<option value="1" ${data.csscScope==1?"selected":""}>一星</option>
							<option value="2" ${data.csscScope==2?"selected":""}>二星</option>
							<option value="3" ${data.csscScope==3?"selected":""}>三星</option>
							<option value="4" ${data.csscScope==4?"selected":""}>四星</option>
							<option value="5" ${data.csscScope==5?"selected":""}>五星</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************用车评分字段的查询代码结束*****************")}
			
			
			${after$csscScope}
			 	
			 ${before$csscAssess}
			 
			 ${lz:set("注释","*****************用车评价字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscAssess" : (lz:join(relateObject,"$csscAssess")))}
			 ${lz:set("cname",lz:join(relateObject,".csscAssess"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscAssess || #request.defines['csscAssess']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscAssess":relateObject}" id="form-dl-csscAssess">
					<dt>用车评价：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscAssessYesNot=="not"?"checked-not":""}${data.csscAssessYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscAssessYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csscAssess}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************用车评价字段的查询代码结束*****************")}
			
			
			${after$csscAssess}
			 	
			 ${before$csscThird}
			 
			 ${lz:set("注释","*****************三方名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscThird" : (lz:join(relateObject,"$csscThird")))}
			 ${lz:set("cname",lz:join(relateObject,".csscThird"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscThird || #request.defines['csscThird']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscThird":relateObject}" id="form-dl-csscThird">
					<dt>三方名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscThirdYesNot=="not"?"checked-not":""}${data.csscThirdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscThirdYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csscThird==1?"selected":""}>滴滴专车</option>
							<option value="2" ${data.csscThird==2?"selected":""}>易道专车</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************三方名称字段的查询代码结束*****************")}
			
			
			${after$csscThird}
			 	
			 ${before$csscThirdNo}
			 
			 ${lz:set("注释","*****************三方订单号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscThirdNo" : (lz:join(relateObject,"$csscThirdNo")))}
			 ${lz:set("cname",lz:join(relateObject,".csscThirdNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscThirdNo || #request.defines['csscThirdNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscThirdNo":relateObject}" id="form-dl-csscThirdNo">
					<dt>三方订单号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscThirdNoYesNot=="not"?"checked-not":""}${data.csscThirdNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscThirdNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="64" name="${NAME}" id="${NAME}"  value="${data.csscThirdNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************三方订单号字段的查询代码结束*****************")}
			
			
			${after$csscThirdNo}
			 	
			 ${before$csscUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscUpdateTime" : (lz:join(relateObject,"$csscUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csscUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscUpdateTime || #request.defines['csscUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscUpdateTime":relateObject}" id="form-dl-csscUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscUpdateTimeYesNot=="not"?"checked-not":""}${data.csscUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csscUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csscUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csscUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csscUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csscUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csscUpdateTime}
			 	
			 ${before$csscAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscAddTime" : (lz:join(relateObject,"$csscAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csscAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscAddTime || #request.defines['csscAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscAddTime":relateObject}" id="form-dl-csscAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscAddTimeYesNot=="not"?"checked-not":""}${data.csscAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csscAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csscAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csscAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csscAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csscAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csscAddTime}
			 	
			 ${before$csscResponse}
			 
			 ${lz:set("注释","*****************响应状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscResponse" : (lz:join(relateObject,"$csscResponse")))}
			 ${lz:set("cname",lz:join(relateObject,".csscResponse"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscResponse || #request.defines['csscResponse']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscResponse":relateObject}" id="form-dl-csscResponse">
					<dt>响应状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscResponseYesNot=="not"?"checked-not":""}${data.csscResponseYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscResponseYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csscResponse}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************响应状态字段的查询代码结束*****************")}
			
			
			${after$csscResponse}
			 	
			 ${before$csscStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscStatus" : (lz:join(relateObject,"$csscStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csscStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscStatus || #request.defines['csscStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscStatus":relateObject}" id="form-dl-csscStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscStatusYesNot=="not"?"checked-not":""}${data.csscStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csscStatus==0?"selected":""}>待审核</option>
							<option value="1" ${data.csscStatus==1?"selected":""}>已审核</option>
							<option value="2" ${data.csscStatus==2?"selected":""}>已下单</option>
							<option value="3" ${data.csscStatus==3?"selected":""}>已接单</option>
							<option value="4" ${data.csscStatus==4?"selected":""}>已完成</option>
							<option value="5" ${data.csscStatus==5?"selected":""}>已取消</option>
							<option value="6" ${data.csscStatus==6?"selected":""}>已作废</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csscStatus}
			
			
			${before$csscIsCarpool}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csscIsCarpool" : (lz:join(relateObject,"$csscIsCarpool")))}
			 ${lz:set("cname",lz:join(relateObject,".csscIsCarpool"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csscIsCarpool || #request.defines['csscIsCarpool']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csscIsCarpool":relateObject}" id="form-dl-csscStatus">
					<dt>是否拼车：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csscIsCarpoolYesNot=="not"?"checked-not":""}${data.csscIsCarpoolYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csscIsCarpoolYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csscIsCarpool==0?"selected":""}>不拼车</option>
							<option value="1" ${data.csscIsCarpool==1?"selected":""}>拼车</option>
						
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csscIsCarpool}