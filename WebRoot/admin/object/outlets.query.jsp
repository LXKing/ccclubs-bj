<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csoId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoId" : (lz:join(relateObject,"$csoId")))}
			 ${lz:set("cname",lz:join(relateObject,".csoId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoId || #request.defines['csoId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoId":relateObject}" id="form-dl-csoId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoIdYesNot=="not"?"checked-not":""}${data.csoIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csoId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csoId}
			 	
			 ${before$csoHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoHost" : (lz:join(relateObject,"$csoHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csoHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoHost || #request.defines['csoHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoHost":relateObject}" id="form-dl-csoHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoHostYesNot=="not"?"checked-not":""}${data.csoHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csoHost}">
				 					${get:SrvHost(data.csoHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csoHost}
			 	
			 ${before$csoName}
			 
			 ${lz:set("注释","*****************名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoName" : (lz:join(relateObject,"$csoName")))}
			 ${lz:set("cname",lz:join(relateObject,".csoName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoName || #request.defines['csoName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoName":relateObject}" id="form-dl-csoName">
					<dt>名　　称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoNameYesNot=="not"?"checked-not":""}${data.csoNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csoName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************名称字段的查询代码结束*****************")}
			
			
			${after$csoName}
			 	
			 ${before$csoArea}
			 
			 ${lz:set("注释","*****************所在区域字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoArea" : (lz:join(relateObject,"$csoArea")))}
			 ${lz:set("cname",lz:join(relateObject,".csoArea"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoArea || #request.defines['csoArea']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoArea":relateObject}" id="form-dl-csoArea">
					<dt>所在区域：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoAreaYesNot=="not"?"checked-not":""}${data.csoAreaYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoAreaYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/configurator/area_query.do?size=-1&csaHost={csoHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csoArea}">
				 					${get:CsArea(data.csoArea).csaName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/area_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所在区域字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csoArea']>0">
				${lz:set("注释","****当所在区域字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csoArea")}
			  	<input type="hidden" name="csoArea$on" id="CsArea$on" value="true"/>
			  	<jsp:include page="/admin/configurator/area.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csoArea}
			 	
			 ${before$csoAddress}
			 
			 ${lz:set("注释","*****************地址字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoAddress" : (lz:join(relateObject,"$csoAddress")))}
			 ${lz:set("cname",lz:join(relateObject,".csoAddress"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoAddress || #request.defines['csoAddress']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoAddress":relateObject}" id="form-dl-csoAddress">
					<dt>地　　址：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoAddressYesNot=="not"?"checked-not":""}${data.csoAddressYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoAddressYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csoAddress}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************地址字段的查询代码结束*****************")}
			
			
			${after$csoAddress}
			 	
			 ${before$csoSpace}
			 
			 ${lz:set("注释","*****************停车地点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoSpace" : (lz:join(relateObject,"$csoSpace")))}
			 ${lz:set("cname",lz:join(relateObject,".csoSpace"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoSpace || #request.defines['csoSpace']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoSpace":relateObject}" id="form-dl-csoSpace">
					<dt>停车地点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoSpaceYesNot=="not"?"checked-not":""}${data.csoSpaceYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoSpaceYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csoSpace}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************停车地点字段的查询代码结束*****************")}
			
			
			${after$csoSpace}
			 	
			 ${before$csoUntil}
			 
			 ${lz:set("注释","*****************缴费到期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoUntil" : (lz:join(relateObject,"$csoUntil")))}
			 ${lz:set("cname",lz:join(relateObject,".csoUntil"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoUntil || #request.defines['csoUntil']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoUntil":relateObject}" id="form-dl-csoUntil">
					<dt>缴费到期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoUntilYesNot=="not"?"checked-not":""}${data.csoUntilYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoUntilYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csoUntilStart,"yyyy-MM-dd")}" end="${lz:date(data.csoUntilEnd,"yyyy-MM-dd")}" exp="${data.csoUntilExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csoUntilStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>缴费到期：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csoUntilEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************缴费到期字段的查询代码结束*****************")}
			
			
			${after$csoUntil}
			 	
			 ${before$csoExpire}
			 
			 ${lz:set("注释","*****************合约到期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoExpire" : (lz:join(relateObject,"$csoExpire")))}
			 ${lz:set("cname",lz:join(relateObject,".csoExpire"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoExpire || #request.defines['csoExpire']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoExpire":relateObject}" id="form-dl-csoExpire">
					<dt>合约到期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoExpireYesNot=="not"?"checked-not":""}${data.csoExpireYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoExpireYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csoExpireStart,"yyyy-MM-dd")}" end="${lz:date(data.csoExpireEnd,"yyyy-MM-dd")}" exp="${data.csoExpireExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csoExpireStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>合约到期：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csoExpireEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************合约到期字段的查询代码结束*****************")}
			
			
			${after$csoExpire}
			 	
			 ${before$csoPileTime}
			 
			 ${lz:set("注释","*****************电桩建立时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoPileTime" : (lz:join(relateObject,"$csoPileTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csoPileTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoPileTime || #request.defines['csoPileTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoPileTime":relateObject}" id="form-dl-csoPileTime">
					<dt>电桩建立时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoPileTimeYesNot=="not"?"checked-not":""}${data.csoPileTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoPileTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csoPileTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csoPileTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csoPileTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csoPileTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>电桩建立时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csoPileTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************电桩建立时间字段的查询代码结束*****************")}
			
			
			${after$csoPileTime}
			 	
			 ${before$csoCanA}
			 
			 ${lz:set("注释","*****************AB借出字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoCanA" : (lz:join(relateObject,"$csoCanA")))}
			 ${lz:set("cname",lz:join(relateObject,".csoCanA"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoCanA || #request.defines['csoCanA']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoCanA":relateObject}" id="form-dl-csoCanA">
					<dt>AB借出：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoCanAYesNot=="not"?"checked-not":""}${data.csoCanAYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoCanAYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csoCanA=='true'?"selected":""}>是</option>
							<option value="false" ${data.csoCanA=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************AB借出字段的查询代码结束*****************")}
			
			
			${after$csoCanA}
			 	
			 ${before$csoCanB}
			 
			 ${lz:set("注释","*****************AB还入字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoCanB" : (lz:join(relateObject,"$csoCanB")))}
			 ${lz:set("cname",lz:join(relateObject,".csoCanB"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoCanB || #request.defines['csoCanB']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoCanB":relateObject}" id="form-dl-csoCanB">
					<dt>AB还入：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoCanBYesNot=="not"?"checked-not":""}${data.csoCanBYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoCanBYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csoCanB=='true'?"selected":""}>是</option>
							<option value="false" ${data.csoCanB=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************AB还入字段的查询代码结束*****************")}
			
			
			${after$csoCanB}
			 	
			 ${before$csoCanReg}
			 
			 ${lz:set("注释","*****************能注册字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoCanReg" : (lz:join(relateObject,"$csoCanReg")))}
			 ${lz:set("cname",lz:join(relateObject,".csoCanReg"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoCanReg || #request.defines['csoCanReg']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoCanReg":relateObject}" id="form-dl-csoCanReg">
					<dt>能&nbsp;&nbsp;注&nbsp;&nbsp;册：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoCanRegYesNot=="not"?"checked-not":""}${data.csoCanRegYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoCanRegYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csoCanReg=='true'?"selected":""}>是</option>
							<option value="false" ${data.csoCanReg=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************能注册字段的查询代码结束*****************")}
			
			
			${after$csoCanReg}
			 	
			 ${before$csoCanOrder}
			 
			 ${lz:set("注释","*****************能预订字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoCanOrder" : (lz:join(relateObject,"$csoCanOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csoCanOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoCanOrder || #request.defines['csoCanOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoCanOrder":relateObject}" id="form-dl-csoCanOrder">
					<dt>能&nbsp;&nbsp;预&nbsp;&nbsp;订：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoCanOrderYesNot=="not"?"checked-not":""}${data.csoCanOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoCanOrderYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csoCanOrder=='true'?"selected":""}>是</option>
							<option value="false" ${data.csoCanOrder=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************能预订字段的查询代码结束*****************")}
			
			
			${after$csoCanOrder}
			 	
			 ${before$csoOnlineTime}
			 
			 ${lz:set("注释","*****************上线时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoOnlineTime" : (lz:join(relateObject,"$csoOnlineTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csoOnlineTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoOnlineTime || #request.defines['csoOnlineTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoOnlineTime":relateObject}" id="form-dl-csoOnlineTime">
					<dt>上线时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoOnlineTimeYesNot=="not"?"checked-not":""}${data.csoOnlineTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoOnlineTimeYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csoOnlineTimeStart,"yyyy-MM-dd")}" end="${lz:date(data.csoOnlineTimeEnd,"yyyy-MM-dd")}" exp="${data.csoOnlineTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csoOnlineTimeStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>上线时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csoOnlineTimeEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************上线时间字段的查询代码结束*****************")}
			
			
			${after$csoOnlineTime}
			 	
			 ${before$csoUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoUpdateTime" : (lz:join(relateObject,"$csoUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csoUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoUpdateTime || #request.defines['csoUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoUpdateTime":relateObject}" id="form-dl-csoUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoUpdateTimeYesNot=="not"?"checked-not":""}${data.csoUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csoUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csoUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csoUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csoUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csoUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csoUpdateTime}
			 	
			 ${before$csoAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoAddTime" : (lz:join(relateObject,"$csoAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csoAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoAddTime || #request.defines['csoAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoAddTime":relateObject}" id="form-dl-csoAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoAddTimeYesNot=="not"?"checked-not":""}${data.csoAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csoAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csoAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csoAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csoAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csoAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csoAddTime}
			 	
			 ${before$csoStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csoStatus" : (lz:join(relateObject,"$csoStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csoStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csoStatus || #request.defines['csoStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csoStatus":relateObject}" id="form-dl-csoStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoStatusYesNot=="not"?"checked-not":""}${data.csoStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csoStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csoStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csoStatus}