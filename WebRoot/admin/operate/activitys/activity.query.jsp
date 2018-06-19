<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csaId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaId" : (lz:join(relateObject,"$csaId")))}
			 ${lz:set("cname",lz:join(relateObject,".csaId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaId || #request.defines['csaId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaId":relateObject}" id="form-dl-csaId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaIdYesNot=="not"?"checked-not":""}${data.csaIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csaId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csaId}
			 	
			 ${before$csaHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaHost" : (lz:join(relateObject,"$csaHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csaHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaHost || #request.defines['csaHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaHost":relateObject}" id="form-dl-csaHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaHostYesNot=="not"?"checked-not":""}${data.csaHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csaHost}">
				 					${get:SrvHost(data.csaHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csaHost}
			 	
			 ${before$csaTitle}
			 
			 ${lz:set("注释","*****************活动主题字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaTitle" : (lz:join(relateObject,"$csaTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".csaTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaTitle || #request.defines['csaTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaTitle":relateObject}" id="form-dl-csaTitle">
					<dt>活动主题：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaTitleYesNot=="not"?"checked-not":""}${data.csaTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csaTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************活动主题字段的查询代码结束*****************")}
			
			
			${after$csaTitle}
			 	
			 ${before$csaFlag}
			 
			 ${lz:set("注释","*****************活动标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaFlag" : (lz:join(relateObject,"$csaFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".csaFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaFlag || #request.defines['csaFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaFlag":relateObject}" id="form-dl-csaFlag">
					<dt>活动标识：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaFlagYesNot=="not"?"checked-not":""}${data.csaFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaFlagYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csaFlag}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************活动标识字段的查询代码结束*****************")}
			
			
			${after$csaFlag}
			 	
			 ${before$csaType}
			 
			 ${lz:set("注释","*****************营销分类字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaType" : (lz:join(relateObject,"$csaType")))}
			 ${lz:set("cname",lz:join(relateObject,".csaType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaType || #request.defines['csaType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaType":relateObject}" id="form-dl-csaType">
					<dt>营销分类：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaTypeYesNot=="not"?"checked-not":""}${data.csaTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", get:propertys("营销类型","market_type"))}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.spId}" ${data.csaType==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************营销分类字段的查询代码结束*****************")}
			
			
			${after$csaType}
			 	
			 ${before$csaPlan}
			 
			 ${lz:set("注释","*****************所属策划字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaPlan" : (lz:join(relateObject,"$csaPlan")))}
			 ${lz:set("cname",lz:join(relateObject,".csaPlan"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaPlan || #request.defines['csaPlan']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaPlan":relateObject}" id="form-dl-csaPlan">
					<dt>所属策划：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaPlanYesNot=="not"?"checked-not":""}${data.csaPlanYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaPlanYesNot}"/>
			 			<input title="请输入营销方案方案主题进行查询" class="combox" action="${basePath}${proname}/operate/marketplan_query.do?value={param}&csmpHost={csaHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMarketPlan(data.csaPlan).csmpName}" value="${data.csaPlan}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/operate/marketplan_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属策划字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csaPlan']>0">
				${lz:set("注释","****当所属策划字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csaPlan")}
			  	<input type="hidden" name="csaPlan$on" id="CsMarketPlan$on" value="true"/>
			  	<jsp:include page="/admin/operate/marketplan.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csaPlan}
			 	
			 ${before$csaStart}
			 
			 ${lz:set("注释","*****************开始时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaStart" : (lz:join(relateObject,"$csaStart")))}
			 ${lz:set("cname",lz:join(relateObject,".csaStart"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaStart || #request.defines['csaStart']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaStart":relateObject}" id="form-dl-csaStart">
					<dt>开始时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaStartYesNot=="not"?"checked-not":""}${data.csaStartYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaStartYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csaStartStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csaStartEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csaStartExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csaStartStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>开始时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csaStartEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************开始时间字段的查询代码结束*****************")}
			
			
			${after$csaStart}
			 	
			 ${before$csaFinish}
			 
			 ${lz:set("注释","*****************结束时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaFinish" : (lz:join(relateObject,"$csaFinish")))}
			 ${lz:set("cname",lz:join(relateObject,".csaFinish"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaFinish || #request.defines['csaFinish']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaFinish":relateObject}" id="form-dl-csaFinish">
					<dt>结束时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaFinishYesNot=="not"?"checked-not":""}${data.csaFinishYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaFinishYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csaFinishStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csaFinishEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csaFinishExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csaFinishStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>结束时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csaFinishEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结束时间字段的查询代码结束*****************")}
			
			
			${after$csaFinish}
			 	
			 ${before$csaCreater}
			 
			 ${lz:set("注释","*****************创建人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaCreater" : (lz:join(relateObject,"$csaCreater")))}
			 ${lz:set("cname",lz:join(relateObject,".csaCreater"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaCreater || #request.defines['csaCreater']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaCreater":relateObject}" id="form-dl-csaCreater">
					<dt>创&nbsp;&nbsp;建&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaCreaterYesNot=="not"?"checked-not":""}${data.csaCreaterYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaCreaterYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csaCreater}">
				 					${get:SrvUser(data.csaCreater).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************创建人字段的查询代码结束*****************")}
			
			
			${after$csaCreater}
			 	
			 ${before$csaUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaUpdateTime" : (lz:join(relateObject,"$csaUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csaUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaUpdateTime || #request.defines['csaUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaUpdateTime":relateObject}" id="form-dl-csaUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaUpdateTimeYesNot=="not"?"checked-not":""}${data.csaUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csaUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csaUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csaUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csaUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csaUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csaUpdateTime}
			 	
			 ${before$csaAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaAddTime" : (lz:join(relateObject,"$csaAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csaAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaAddTime || #request.defines['csaAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaAddTime":relateObject}" id="form-dl-csaAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaAddTimeYesNot=="not"?"checked-not":""}${data.csaAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csaAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csaAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csaAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csaAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csaAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csaAddTime}
			 	
			 ${before$csaStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaStatus" : (lz:join(relateObject,"$csaStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csaStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaStatus || #request.defines['csaStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaStatus":relateObject}" id="form-dl-csaStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaStatusYesNot=="not"?"checked-not":""}${data.csaStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csaStatus==0?"selected":""}>策划中</option>
							<option value="1" ${data.csaStatus==1?"selected":""}>进行中</option>
							<option value="2" ${data.csaStatus==2?"selected":""}>已结束</option>
							<option value="3" ${data.csaStatus==3?"selected":""}>已放弃</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csaStatus}