<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cscpId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscpId" : (lz:join(relateObject,"$cscpId")))}
			 ${lz:set("cname",lz:join(relateObject,".cscpId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscpId || #request.defines['cscpId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscpId":relateObject}" id="form-dl-cscpId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscpIdYesNot=="not"?"checked-not":""}${data.cscpIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscpIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cscpId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cscpId}
			 	
			 ${before$cscpHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscpHost" : (lz:join(relateObject,"$cscpHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cscpHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscpHost || #request.defines['cscpHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscpHost":relateObject}" id="form-dl-cscpHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscpHostYesNot=="not"?"checked-not":""}${data.cscpHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscpHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscpHost}">
				 					${get:SrvHost(data.cscpHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cscpHost}
			 	
			 ${before$cscpCar}
			 
			 ${lz:set("注释","*****************所属车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscpCar" : (lz:join(relateObject,"$cscpCar")))}
			 ${lz:set("cname",lz:join(relateObject,".cscpCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscpCar || #request.defines['cscpCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscpCar":relateObject}" id="form-dl-cscpCar">
					<dt>所属车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscpCarYesNot=="not"?"checked-not":""}${data.cscpCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscpCarYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cscpHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.cscpCar).cscNumber}" value="${data.cscpCar}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscpCar']>0">
				${lz:set("注释","****当所属车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscpCar")}
			  	<input type="hidden" name="cscpCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscpCar}
			 	
			 ${before$cscpDate}
			 
			 ${lz:set("注释","*****************照片日期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscpDate" : (lz:join(relateObject,"$cscpDate")))}
			 ${lz:set("cname",lz:join(relateObject,".cscpDate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscpDate || #request.defines['cscpDate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscpDate":relateObject}" id="form-dl-cscpDate">
					<dt>照片日期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscpDateYesNot=="not"?"checked-not":""}${data.cscpDateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscpDateYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscpDateStart,"yyyy-MM-dd")}" end="${lz:date(data.cscpDateEnd,"yyyy-MM-dd")}" exp="${data.cscpDateExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscpDateStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>照片日期：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscpDateEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************照片日期字段的查询代码结束*****************")}
			
			
			${after$cscpDate}
			 	
			 ${before$cscpEditor}
			 
			 ${lz:set("注释","*****************上传人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscpEditor" : (lz:join(relateObject,"$cscpEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".cscpEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscpEditor || #request.defines['cscpEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscpEditor":relateObject}" id="form-dl-cscpEditor">
					<dt>上传人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscpEditorYesNot=="not"?"checked-not":""}${data.cscpEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscpEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscpEditor}">
				 					${get:SrvUser(data.cscpEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************上传人员字段的查询代码结束*****************")}
			
			
			${after$cscpEditor}
			 	
			 ${before$cscpRemark}
			 
			 ${lz:set("注释","*****************备注信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscpRemark" : (lz:join(relateObject,"$cscpRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".cscpRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscpRemark || #request.defines['cscpRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscpRemark":relateObject}" id="form-dl-cscpRemark">
					<dt>备注信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscpRemarkYesNot=="not"?"checked-not":""}${data.cscpRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscpRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.cscpRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注信息字段的查询代码结束*****************")}
			
			
			${after$cscpRemark}
			 	
			 ${before$cscpAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscpAddTime" : (lz:join(relateObject,"$cscpAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscpAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscpAddTime || #request.defines['cscpAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscpAddTime":relateObject}" id="form-dl-cscpAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscpAddTimeYesNot=="not"?"checked-not":""}${data.cscpAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscpAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscpAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscpAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscpAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscpAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscpAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cscpAddTime}