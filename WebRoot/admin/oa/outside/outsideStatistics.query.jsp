<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csosId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosId" : (lz:join(relateObject,"$csosId")))}
			 ${lz:set("cname",lz:join(relateObject,".csosId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosId || #request.defines['csosId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosId":relateObject}" id="form-dl-csosId">
					<dt>编　　号：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosIdYesNot=="not"?"checked-not":""}${data.csosIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csosId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csosId}
			
			
			
			
			 ${before$csosArea}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosArea" : (lz:join(relateObject,"$csosArea")))}
			 ${lz:set("cname",lz:join(relateObject,".csosArea"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosArea || #request.defines['csosArea']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosArea":relateObject}" id="form-dl-csosArea">
					<dt>运营区域：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosAreaYesNot=="not"?"checked-not":""}${data.csosAreaYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosAreaYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/configurator/area_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csosArea}">
				 					${get:CsArea(data.csosArea).csaName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/area_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csosArea}
			
			
			
			
			
			
			
			
			
			 	
			 ${before$csosHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosHost" : (lz:join(relateObject,"$csosHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csosHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosHost || #request.defines['csosHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosHost":relateObject}" id="form-dl-csosHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosHostYesNot=="not"?"checked-not":""}${data.csosHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csosHost}">
				 					${get:SrvHost(data.csosHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csosHost}
			
			
			
			
			 ${before$csosUser}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosUser" : (lz:join(relateObject,"$csosUser")))}
			 ${lz:set("cname",lz:join(relateObject,".csosUser"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosUser || #request.defines['csosUser']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosUser":relateObject}" id="form-dl-csosUser">
					<dt>员工：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosUserYesNot=="not"?"checked-not":""}${data.csosUserYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosUserYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csosUser}">
 				 					${get:SrvUser(data.csosUser).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csosUser}
			
			
			 
			 
			 
			 
			 
			 
			 
		 ${before$csosOutlets}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosOutlets" : (lz:join(relateObject,"$csosOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".csosOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosOutlets || #request.defines['csosOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosOutlets":relateObject}" id="form-dl-csosOutlets">
					<dt>网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosOutletsYesNot=="not"?"checked-not":""}${data.csosOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosOutletsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csosOutlets}">
				 					${get:CsOutlets(data.csosOutlets).csoName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csosOutlets}
			
			
			
			
			
			
			
			
			
			
			 ${before$csosCar}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosCar" : (lz:join(relateObject,"$csosCar")))}
			 ${lz:set("cname",lz:join(relateObject,".csosCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosCar || #request.defines['csosCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosCar":relateObject}" id="form-dl-csosCar">
					<dt>车牌号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosCarYesNot=="not"?"checked-not":""}${data.csosCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosCarYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/car_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csosCar}">
				 					${get:CsCar(data.csosCar).cscNumber}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
		${after$csosCar}






 ${before$csosProperty}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosProperty" : (lz:join(relateObject,"$csosProperty")))}
			 ${lz:set("cname",lz:join(relateObject,".csosProperty"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosProperty || #request.defines['csosProperty']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosProperty":relateObject}" id="form-dl-csosProperty">
					<dt>整备项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosPropertyYesNot=="not"?"checked-not":""}${data.csosPropertyYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosPropertyYesNot}"/>
				 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", lz:query("ccclubs_system","SELECT * FROM srv_property p WHERE EXISTS (SELECT sp_flag FROM srv_property WHERE p.sp_parent = sp_id AND sp_flag = 'REMOTE_CASE' AND sp_parent = 0 ) ") )}
								<s:iterator value="#request.items" id="item" status="i">
									<option value="${item.spId}" ${data.csosProperty==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								<option value="0" ${data.csosProperty==0?"selected":""}>其他</option>
								
								${lz:set("items",null)}
			 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
		${after$csosProperty}




	${before$csosOpenDoorTime}
			 
			 ${lz:set("注释","*****************发生时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosOpenDoorTime" : (lz:join(relateObject,"$csosOpenDoorTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csosOpenDoorTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosOpenDoorTime || #request.defines['csosOpenDoorTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosOpenDoorTime":relateObject}" id="form-dl-csosOpenDoorTime">
					<dt>开门时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosOpenDoorTimeYesNot=="not"?"checked-not":""}${data.csosOpenDoorTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosOpenDoorTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csosOpenDoorTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csosOpenDoorTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csosOpenDoorTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csvHappenTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>发生时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csvHappenTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发生时间字段的查询代码结束*****************")}
			
			
	${after$csosOpenDoorTime}
			 	
			 	
			 	
			 	
			 	
			 	
			 	
	 ${before$csosOpenDoorCount}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosOpenDoorCount" : (lz:join(relateObject,"$csosOpenDoorCount")))}
			 ${lz:set("cname",lz:join(relateObject,".csosOpenDoorCount"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosOpenDoorCount || #request.defines['csosOpenDoorCount']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosOpenDoorCount":relateObject}" id="form-dl-csosOpenDoorCount">
					<dt>开门次数：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosOpenDoorCountYesNot=="not"?"checked-not":""}${data.csosOpenDoorCountYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosOpenDoorCountYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csosOpenDoorCount}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csosOpenDoorCount}
			 	
			 	
		
		
		 ${before$csosCloseDoorTime}
			 
			 ${lz:set("注释","*****************通知时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosCloseDoorTime" : (lz:join(relateObject,"$csosCloseDoorTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csosCloseDoorTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosCloseDoorTime || #request.defines['csosCloseDoorTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosCloseDoorTime":relateObject}" id="form-dl-csosCloseDoorTime">
					<dt>关门时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosCloseDoorTimeYesNot=="not"?"checked-not":""}${data.csosCloseDoorTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosCloseDoorTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csosCloseDoorTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csosCloseDoorTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csosCloseDoorTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csvNoticeTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>通知时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csvNoticeTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************通知时间字段的查询代码结束*****************")}
			
			
			${after$csosCloseDoorTime}
		
			 	
			 	
	 ${before$csosCloseDoorCount}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosCloseDoorCount" : (lz:join(relateObject,"$csosCloseDoorCount")))}
			 ${lz:set("cname",lz:join(relateObject,".csosCloseDoorCount"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosCloseDoorCount || #request.defines['csosCloseDoorCount']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosCloseDoorCount":relateObject}" id="form-dl-csosCloseDoorCount">
					<dt>关门次数：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosCloseDoorCountYesNot=="not"?"checked-not":""}${data.csosCloseDoorCountYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosCloseDoorCountYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csosCloseDoorCount}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csosCloseDoorCount}
			 	 	
			 	 ${before$csosAddTime}
			 
			 ${lz:set("注释","*****************截止时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosAddTime" : (lz:join(relateObject,"$csosAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csosAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosAddTime || #request.defines['csosAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosAddTime":relateObject}" id="form-dl-csosAddTime">
					<dt>开始时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosAddTimeYesNot=="not"?"checked-not":""}${data.csosAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csosAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csosAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csosAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csvCutoffTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>截止时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csvCutoffTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************截止时间字段的查询代码结束*****************")}
			
			
	${after$csosAddTime}
			 	
			 	
			 	
			
			 	
			 ${before$csosFinishTime}
			 
			 ${lz:set("注释","*****************截止时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosFinishTime" : (lz:join(relateObject,"$csosFinishTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csosFinishTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosFinishTime || #request.defines['csosFinishTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosFinishTime":relateObject}" id="form-dl-csosFinishTime">
					<dt>结束时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosFinishTimeYesNot=="not"?"checked-not":""}${data.csosFinishTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosFinishTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csosFinishTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csosFinishTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csosFinishTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csvCutoffTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>截止时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csvCutoffTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************截止时间字段的查询代码结束*****************")}
			
			
			${after$csosFinishTime}
			 	

${before$csosWorkTime}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosWorkTime" : (lz:join(relateObject,"$csosWorkTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csosWorkTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosWorkTime || #request.defines['csosWorkTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosWorkTime":relateObject}" id="form-dl-csosWorkTime">
					<dt>作业时长：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosWorkTimeYesNot=="not"?"checked-not":""}${data.csosWorkTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosWorkTimeYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csosWorkTime}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
	${after$csosWorkTime}
			 	 	


 	
			 ${before$csosStatus}
			 
			 ${lz:set("注释","*****************业务状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csosStatus" : (lz:join(relateObject,"$csosStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csosStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csosStatus || #request.defines['csosStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csosStatus":relateObject}" id="form-dl-csosStatus">
					<dt>业务状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csosStatusYesNot=="not"?"checked-not":""}${data.csosStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csosStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							
							<option value="1" ${data.csosStatus==1?"selected":""}>未完成</option>
							<option value="2" ${data.csosStatus==2?"selected":""}>已完成</option>
							
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************业务状态字段的查询代码结束*****************")}
			
			
			${after$csosStatus}
 

			
 
			 	