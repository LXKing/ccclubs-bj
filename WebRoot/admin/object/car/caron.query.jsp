<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cscoId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscoId" : (lz:join(relateObject,"$cscoId")))}
			 ${lz:set("cname",lz:join(relateObject,".cscoId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscoId || #request.defines['cscoId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscoId":relateObject}" id="form-dl-cscoId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscoIdYesNot=="not"?"checked-not":""}${data.cscoIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscoIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cscoId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cscoId}
			 	
			 ${before$cscoHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscoHost" : (lz:join(relateObject,"$cscoHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cscoHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscoHost || #request.defines['cscoHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscoHost":relateObject}" id="form-dl-cscoHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscoHostYesNot=="not"?"checked-not":""}${data.cscoHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscoHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscoHost}">
				 					${get:SrvHost(data.cscoHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cscoHost}
			 	
			 ${before$cscoCar}
			 
			 ${lz:set("注释","*****************所属车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscoCar" : (lz:join(relateObject,"$cscoCar")))}
			 ${lz:set("cname",lz:join(relateObject,".cscoCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscoCar || #request.defines['cscoCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscoCar":relateObject}" id="form-dl-cscoCar">
					<dt>所属车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscoCarYesNot=="not"?"checked-not":""}${data.cscoCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscoCarYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cscoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.cscoCar).cscNumber}" value="${data.cscoCar}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscoCar']>0">
				${lz:set("注释","****当所属车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscoCar")}
			  	<input type="hidden" name="cscoCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscoCar}
			 	
			 ${before$cscoOnUser}
			 
			 ${lz:set("注释","*****************上线操作人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscoOnUser" : (lz:join(relateObject,"$cscoOnUser")))}
			 ${lz:set("cname",lz:join(relateObject,".cscoOnUser"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscoOnUser || #request.defines['cscoOnUser']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscoOnUser":relateObject}" id="form-dl-cscoOnUser">
					<dt>上线操作人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscoOnUserYesNot=="not"?"checked-not":""}${data.cscoOnUserYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscoOnUserYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscoOnUser}">
				 					${get:SrvUser(data.cscoOnUser).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************上线操作人字段的查询代码结束*****************")}
			
			
			${after$cscoOnUser}
			 	
			 ${before$cscoOffUser}
			 
			 ${lz:set("注释","*****************下线操作人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscoOffUser" : (lz:join(relateObject,"$cscoOffUser")))}
			 ${lz:set("cname",lz:join(relateObject,".cscoOffUser"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscoOffUser || #request.defines['cscoOffUser']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscoOffUser":relateObject}" id="form-dl-cscoOffUser">
					<dt>下线操作人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscoOffUserYesNot=="not"?"checked-not":""}${data.cscoOffUserYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscoOffUserYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscoOffUser}">
				 					${get:SrvUser(data.cscoOffUser).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************下线操作人字段的查询代码结束*****************")}
			
			
			${after$cscoOffUser}
			 	
			 ${before$cscoOnTime}
			 
			 ${lz:set("注释","*****************上线时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscoOnTime" : (lz:join(relateObject,"$cscoOnTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscoOnTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscoOnTime || #request.defines['cscoOnTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscoOnTime":relateObject}" id="form-dl-cscoOnTime">
					<dt>上线时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscoOnTimeYesNot=="not"?"checked-not":""}${data.cscoOnTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscoOnTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscoOnTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscoOnTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscoOnTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscoOnTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>上线时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscoOnTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************上线时间字段的查询代码结束*****************")}
			
			
			${after$cscoOnTime}
			 	
			 ${before$cscoOffTime}
			 
			 ${lz:set("注释","*****************下线时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscoOffTime" : (lz:join(relateObject,"$cscoOffTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscoOffTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscoOffTime || #request.defines['cscoOffTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscoOffTime":relateObject}" id="form-dl-cscoOffTime">
					<dt>下线时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscoOffTimeYesNot=="not"?"checked-not":""}${data.cscoOffTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscoOffTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscoOffTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscoOffTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscoOffTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscoOffTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>下线时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscoOffTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************下线时间字段的查询代码结束*****************")}
			
			
			${after$cscoOffTime}
			 	
			 ${before$cscoLong}
			 
			 ${lz:set("注释","*****************运营时长字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscoLong" : (lz:join(relateObject,"$cscoLong")))}
			 ${lz:set("cname",lz:join(relateObject,".cscoLong"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscoLong || #request.defines['cscoLong']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscoLong":relateObject}" id="form-dl-cscoLong">
					<dt>运营时长：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscoLongYesNot=="not"?"checked-not":""}${data.cscoLongYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscoLongYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.cscoLongMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.cscoLongMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************运营时长字段的查询代码结束*****************")}
			
			
			${after$cscoLong}
			
			
			${before$cscoOnRemark}
			 
			 ${lz:set("注释","*****************上线备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscoOnRemark" : (lz:join(relateObject,"$cscoOnRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".cscoOnRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscoOnRemark || #request.defines['cscoOnRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscoOnRemark":relateObject}" id="form-dl-cscoOnRemark">
					<dt>上线备注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscoOnRemarkYesNot=="not"?"checked-not":""}${data.cscoOnRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscoOnRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.cscoOnRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************上线备注字段的查询代码结束*****************")}
			
			
			${after$cscoOnRemark}
			
			 	
			 ${before$cscoRemark}
			 
			 ${lz:set("注释","*****************下线备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscoRemark" : (lz:join(relateObject,"$cscoRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".cscoRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscoRemark || #request.defines['cscoRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscoRemark":relateObject}" id="form-dl-cscoRemark">
					<dt>下线备注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscoRemarkYesNot=="not"?"checked-not":""}${data.cscoRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscoRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.cscoRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************下线备注字段的查询代码结束*****************")}
			
			
			${after$cscoRemark}
			 	
			 ${before$cscoStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscoStatus" : (lz:join(relateObject,"$cscoStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cscoStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscoStatus || #request.defines['cscoStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscoStatus":relateObject}" id="form-dl-cscoStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscoStatusYesNot=="not"?"checked-not":""}${data.cscoStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscoStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cscoStatus==0?"selected":""}>上线中</option>
							<option value="1" ${data.cscoStatus==1?"selected":""}>已完成</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cscoStatus}
			
			
			 
			 ${before$cscoProperty}
			 
			 ${lz:set("注释","*****************网点区域字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscoProperty" : (lz:join(relateObject,"$cscoProperty")))}
			 ${lz:set("cname",lz:join(relateObject,".cscoProperty"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscoProperty || #request.defines['cscoProperty']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscoProperty":relateObject}" id="form-dl-cscoProperty">
					<dt>下线标签：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscoPropertyYesNot=="not"?"checked-not":""}${data.cscoPropertyYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscoPropertyYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", lz:query("ccclubs_system","SELECT * from srv_property p where EXISTS (SELECT sp_flag from srv_property where p.sp_parent = sp_id and sp_flag = 'CAR_OFF_LINE_LABEL' and sp_parent = 0 ) ") )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.spId}" ${data.cscoProperty==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************网点区域字段的查询代码结束*****************")}
			
			${after$cscoProperty}