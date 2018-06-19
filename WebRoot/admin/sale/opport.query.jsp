<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cssoId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoId" : (lz:join(relateObject,"$cssoId")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoId || #request.defines['cssoId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoId":relateObject}" id="form-dl-cssoId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoIdYesNot=="not"?"checked-not":""}${data.cssoIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cssoId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cssoId}
			 	
			 ${before$cssoHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoHost" : (lz:join(relateObject,"$cssoHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoHost || #request.defines['cssoHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoHost":relateObject}" id="form-dl-cssoHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoHostYesNot=="not"?"checked-not":""}${data.cssoHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cssoHost}">
				 					${get:SrvHost(data.cssoHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cssoHost}
			 	
			 ${before$cssoTitle}
			 
			 ${lz:set("注释","*****************机会标题字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoTitle" : (lz:join(relateObject,"$cssoTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoTitle || #request.defines['cssoTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoTitle":relateObject}" id="form-dl-cssoTitle">
					<dt>机会标题：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoTitleYesNot=="not"?"checked-not":""}${data.cssoTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cssoTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************机会标题字段的查询代码结束*****************")}
			
			
			${after$cssoTitle}
			 	
			 ${before$cssoBattle}
			 
			 ${lz:set("注释","*****************归属战役字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoBattle" : (lz:join(relateObject,"$cssoBattle")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoBattle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoBattle || #request.defines['cssoBattle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoBattle":relateObject}" id="form-dl-cssoBattle">
					<dt>归属战役：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoBattleYesNot=="not"?"checked-not":""}${data.cssoBattleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoBattleYesNot}"/>
			 			<input title="请输入销售战役战役标题进行查询" class="combox" action="${basePath}${proname}/sale/battle_query.do?value={param}&tbsbHost={cssoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:TbSaleBattle(data.cssoBattle).tbsbTitle}" value="${data.cssoBattle}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/battle_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************归属战役字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cssoBattle']>0">
				${lz:set("注释","****当归属战役字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cssoBattle")}
			  	<input type="hidden" name="cssoBattle$on" id="TbSaleBattle$on" value="true"/>
			  	<jsp:include page="/admin/sale/battle.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cssoBattle}
			 	
			 ${before$cssoEvent}
			 
			 ${lz:set("注释","*****************归属活动字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoEvent" : (lz:join(relateObject,"$cssoEvent")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoEvent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoEvent || #request.defines['cssoEvent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoEvent":relateObject}" id="form-dl-cssoEvent">
					<dt>归属活动：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoEventYesNot=="not"?"checked-not":""}${data.cssoEventYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoEventYesNot}"/>
			 			<input title="请输入销售活动活动主题进行查询" class="combox" action="${basePath}${proname}/sale/event_query.do?value={param}&csseHost={cssoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsSaleEvent(data.cssoEvent).csseSubject}" value="${data.cssoEvent}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/event_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************归属活动字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cssoEvent']>0">
				${lz:set("注释","****当归属活动字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cssoEvent")}
			  	<input type="hidden" name="cssoEvent$on" id="CsSaleEvent$on" value="true"/>
			  	<jsp:include page="/admin/sale/event.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cssoEvent}
			 	
			 ${before$cssoFrom}
			 
			 ${lz:set("注释","*****************机会来源字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoFrom" : (lz:join(relateObject,"$cssoFrom")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoFrom"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoFrom || #request.defines['cssoFrom']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoFrom":relateObject}" id="form-dl-cssoFrom">
					<dt>机会来源：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoFromYesNot=="not"?"checked-not":""}${data.cssoFromYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoFromYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", get:propertys("销售机会来源","opport_from"))}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.spId}" ${data.cssoFrom==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************机会来源字段的查询代码结束*****************")}
			
			
			${after$cssoFrom}
			 	
			 ${before$cssoCustom}
			 
			 ${lz:set("注释","*****************客户资料字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoCustom" : (lz:join(relateObject,"$cssoCustom")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoCustom"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoCustom || #request.defines['cssoCustom']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoCustom":relateObject}" id="form-dl-cssoCustom">
					<dt>客户资料：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoCustomYesNot=="not"?"checked-not":""}${data.cssoCustomYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoCustomYesNot}"/>
			 			<input title="请输入客户资料客户名称进行查询" class="combox" action="${basePath}${proname}/sale/custom_query.do?value={param}&tbscHost={cssoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:TbSaleCustom(data.cssoCustom).tbscName}" value="${data.cssoCustom}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/custom_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************客户资料字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cssoCustom']>0">
				${lz:set("注释","****当客户资料字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cssoCustom")}
			  	<input type="hidden" name="cssoCustom$on" id="TbSaleCustom$on" value="true"/>
			  	<jsp:include page="/admin/sale/custom.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cssoCustom}
			 	
			 ${before$cssoRate}
			 
			 ${lz:set("注释","*****************成功机率字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoRate" : (lz:join(relateObject,"$cssoRate")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoRate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoRate || #request.defines['cssoRate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoRate":relateObject}" id="form-dl-cssoRate">
					<dt>成功机率：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoRateYesNot=="not"?"checked-not":""}${data.cssoRateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoRateYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cssoRate==0?"selected":""}>1%</option>
							<option value="1" ${data.cssoRate==1?"selected":""}>25%</option>
							<option value="2" ${data.cssoRate==2?"selected":""}>50%</option>
							<option value="3" ${data.cssoRate==3?"selected":""}>75%</option>
							<option value="4" ${data.cssoRate==4?"selected":""}>99%</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************成功机率字段的查询代码结束*****************")}
			
			
			${after$cssoRate}
			 	
			 ${before$cssoOnwer}
			 
			 ${lz:set("注释","*****************所有人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoOnwer" : (lz:join(relateObject,"$cssoOnwer")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoOnwer"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoOnwer || #request.defines['cssoOnwer']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoOnwer":relateObject}" id="form-dl-cssoOnwer">
					<dt>所&nbsp;&nbsp;有&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoOnwerYesNot=="not"?"checked-not":""}${data.cssoOnwerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoOnwerYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cssoOnwer}">
				 					${get:SrvUser(data.cssoOnwer).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所有人字段的查询代码结束*****************")}
			
			
			${after$cssoOnwer}
			 	
			 ${before$cssoUser}
			 
			 ${lz:set("注释","*****************添加人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoUser" : (lz:join(relateObject,"$cssoUser")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoUser"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoUser || #request.defines['cssoUser']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoUser":relateObject}" id="form-dl-cssoUser">
					<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoUserYesNot=="not"?"checked-not":""}${data.cssoUserYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoUserYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cssoUser}">
				 					${get:SrvUser(data.cssoUser).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人字段的查询代码结束*****************")}
			
			
			${after$cssoUser}
			 	
			 ${before$cssoProfile}
			 
			 ${lz:set("注释","*****************机会描述字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoProfile" : (lz:join(relateObject,"$cssoProfile")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoProfile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoProfile || #request.defines['cssoProfile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoProfile":relateObject}" id="form-dl-cssoProfile">
					<dt>机会描述：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoProfileYesNot=="not"?"checked-not":""}${data.cssoProfileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoProfileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.cssoProfile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************机会描述字段的查询代码结束*****************")}
			
			
			${after$cssoProfile}
			 	
			 ${before$cssoUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoUpdateTime" : (lz:join(relateObject,"$cssoUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoUpdateTime || #request.defines['cssoUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoUpdateTime":relateObject}" id="form-dl-cssoUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoUpdateTimeYesNot=="not"?"checked-not":""}${data.cssoUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cssoUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cssoUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cssoUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cssoUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cssoUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cssoUpdateTime}
			 	
			 ${before$cssoAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoAddTime" : (lz:join(relateObject,"$cssoAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoAddTime || #request.defines['cssoAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoAddTime":relateObject}" id="form-dl-cssoAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoAddTimeYesNot=="not"?"checked-not":""}${data.cssoAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cssoAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cssoAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cssoAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cssoAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cssoAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cssoAddTime}
			 	
			 ${before$cssoStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoStatus" : (lz:join(relateObject,"$cssoStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoStatus || #request.defines['cssoStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoStatus":relateObject}" id="form-dl-cssoStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoStatusYesNot=="not"?"checked-not":""}${data.cssoStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cssoStatus==0?"selected":""}>跟踪</option>
							<option value="1" ${data.cssoStatus==1?"selected":""}>成功</option>
							<option value="2" ${data.cssoStatus==2?"selected":""}>搁置</option>
							<option value="3" ${data.cssoStatus==3?"selected":""}>失败</option>
							<option value="4" ${data.cssoStatus==4?"selected":""}>失效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cssoStatus}