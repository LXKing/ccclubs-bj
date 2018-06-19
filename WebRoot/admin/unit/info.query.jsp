<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csuiId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuiId" : (lz:join(relateObject,"$csuiId")))}
			 ${lz:set("cname",lz:join(relateObject,".csuiId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuiId || #request.defines['csuiId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuiId":relateObject}" id="form-dl-csuiId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuiIdYesNot=="not"?"checked-not":""}${data.csuiIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuiIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csuiId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csuiId}
			 	
			 ${before$csuiHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuiHost" : (lz:join(relateObject,"$csuiHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csuiHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuiHost || #request.defines['csuiHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuiHost":relateObject}" id="form-dl-csuiHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuiHostYesNot=="not"?"checked-not":""}${data.csuiHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuiHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csuiHost}">
				 					${get:SrvHost(data.csuiHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csuiHost}
			
			
			${before$csuiArea}
			 
			 ${lz:set("注释","*****************所在区域字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuiArea" : (lz:join(relateObject,"$csuiArea")))}
			 ${lz:set("cname",lz:join(relateObject,".csuiArea"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuiArea || #request.defines['csuiArea']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuiArea":relateObject}" id="form-dl-csuiArea">
					<dt>所在区域：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuiAreaYesNot=="not"?"checked-not":""}${data.csuiAreaYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuiAreaYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/configurator/area_query.do?size=-1&csaHost={csoHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csuiArea}">
				 					${get:CsArea(data.csuiArea).csaName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/area_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所在区域字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuiArea']>0">
				${lz:set("注释","****当所在区域字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuiArea")}
			  	<input type="hidden" name="csuiArea$on" id="CsArea$on" value="true"/>
			  	<jsp:include page="/admin/configurator/area.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuiArea}
			
			
			
			 	
			 ${before$csuiName}
			 
			 ${lz:set("注释","*****************名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuiName" : (lz:join(relateObject,"$csuiName")))}
			 ${lz:set("cname",lz:join(relateObject,".csuiName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuiName || #request.defines['csuiName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuiName":relateObject}" id="form-dl-csuiName">
					<dt>名　　称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuiNameYesNot=="not"?"checked-not":""}${data.csuiNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuiNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csuiName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************名称字段的查询代码结束*****************")}
			
			
			${after$csuiName}
			 	
			 ${before$csuiType}
			 
			 ${lz:set("注释","*****************企业类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuiType" : (lz:join(relateObject,"$csuiType")))}
			 ${lz:set("cname",lz:join(relateObject,".csuiType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuiType || #request.defines['csuiType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuiType":relateObject}" id="form-dl-csuiType">
					<dt>企业类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuiTypeYesNot=="not"?"checked-not":""}${data.csuiTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuiTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csuiType==0?"selected":""}>企业用车</option>
							<option value="1" ${data.csuiType==1?"selected":""}>企业代理</option>
							<option value="2" ${data.csuiType==2?"selected":""}>公务用车</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************企业类型字段的查询代码结束*****************")}
			
			
			${after$csuiType}
			 	
			 ${before$csuiMember}
			 
			 ${lz:set("注释","*****************支付帐号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuiMember" : (lz:join(relateObject,"$csuiMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csuiMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuiMember || #request.defines['csuiMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuiMember":relateObject}" id="form-dl-csuiMember">
					<dt>支付帐号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuiMemberYesNot=="not"?"checked-not":""}${data.csuiMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuiMemberYesNot}"/>
					<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csuiHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csuiMember).csmName}" value="${data.csuiMember}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************支付帐号字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuiMember']>0">
				${lz:set("注释","****当支付帐号字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuiMember")}
			  	<input type="hidden" name="csuiMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuiMember}
			 	
			 ${before$csuiOutlets}
			 
			 ${lz:set("注释","*****************指定网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuiOutlets" : (lz:join(relateObject,"$csuiOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".csuiOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuiOutlets || #request.defines['csuiOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuiOutlets":relateObject}" id="form-dl-csuiOutlets">
					<dt>指定网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuiOutletsYesNot=="not"?"checked-not":""}${data.csuiOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuiOutletsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={csoHost}&csoHost={csuiHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csuiOutlets}">
				 					${get:CsOutlets(data.csuiOutlets).csoName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************指定网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuiOutlets']>0">
				${lz:set("注释","****当指定网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuiOutlets")}
			  	<input type="hidden" name="csuiOutlets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuiOutlets}
			 	
			 ${before$csuiFeeType}
			 
			 ${lz:set("注释","*****************计费策略字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuiFeeType" : (lz:join(relateObject,"$csuiFeeType")))}
			 ${lz:set("cname",lz:join(relateObject,".csuiFeeType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuiFeeType || #request.defines['csuiFeeType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuiFeeType":relateObject}" id="form-dl-csuiFeeType">
					<dt>计费策略：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuiFeeTypeYesNot=="not"?"checked-not":""}${data.csuiFeeTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuiFeeTypeYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/user/type_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csuiFeeType}">
				 					${get:CsUserType(data.csuiFeeType).csutName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************计费策略字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuiFeeType']>0">
				${lz:set("注释","****当计费策略字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuiFeeType")}
			  	<input type="hidden" name="csuiFeeType$on" id="CsUserType$on" value="true"/>
			  	<jsp:include page="/admin/user/type.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuiFeeType}
			 	
			 ${before$csuiSettleType}
			 
			 ${lz:set("注释","*****************结算方式字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuiSettleType" : (lz:join(relateObject,"$csuiSettleType")))}
			 ${lz:set("cname",lz:join(relateObject,".csuiSettleType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuiSettleType || #request.defines['csuiSettleType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuiSettleType":relateObject}" id="form-dl-csuiSettleType">
					<dt>结算方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuiSettleTypeYesNot=="not"?"checked-not":""}${data.csuiSettleTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuiSettleTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csuiSettleType==0?"selected":""}>后结算模式</option>
							<option value="1" ${data.csuiSettleType==1?"selected":""}>预付款模式</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结算方式字段的查询代码结束*****************")}
			
			
			${after$csuiSettleType}
			 	
			 ${before$csuiUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuiUpdateTime" : (lz:join(relateObject,"$csuiUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuiUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuiUpdateTime || #request.defines['csuiUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuiUpdateTime":relateObject}" id="form-dl-csuiUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuiUpdateTimeYesNot=="not"?"checked-not":""}${data.csuiUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuiUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuiUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuiUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuiUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuiUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuiUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csuiUpdateTime}
			 	
			 ${before$csuiAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuiAddTime" : (lz:join(relateObject,"$csuiAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuiAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuiAddTime || #request.defines['csuiAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuiAddTime":relateObject}" id="form-dl-csuiAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuiAddTimeYesNot=="not"?"checked-not":""}${data.csuiAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuiAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuiAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csuiAddTime}
			 	
			 ${before$csuiStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuiStatus" : (lz:join(relateObject,"$csuiStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csuiStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuiStatus || #request.defines['csuiStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuiStatus":relateObject}" id="form-dl-csuiStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuiStatusYesNot=="not"?"checked-not":""}${data.csuiStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuiStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csuiStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csuiStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csuiStatus}