<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csugId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csugId" : (lz:join(relateObject,"$csugId")))}
			 ${lz:set("cname",lz:join(relateObject,".csugId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csugId || #request.defines['csugId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csugId":relateObject}" id="form-dl-csugId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csugIdYesNot=="not"?"checked-not":""}${data.csugIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csugIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csugId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csugId}
			 	
			 ${before$csugHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csugHost" : (lz:join(relateObject,"$csugHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csugHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csugHost || #request.defines['csugHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csugHost":relateObject}" id="form-dl-csugHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csugHostYesNot=="not"?"checked-not":""}${data.csugHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csugHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csugHost}">
				 					${get:SrvHost(data.csugHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csugHost}
			 	
			 ${before$csugInfo}
			 
			 ${lz:set("注释","*****************所属单位字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csugInfo" : (lz:join(relateObject,"$csugInfo")))}
			 ${lz:set("cname",lz:join(relateObject,".csugInfo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csugInfo || #request.defines['csugInfo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csugInfo":relateObject}" id="form-dl-csugInfo">
					<dt>所属单位：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csugInfoYesNot=="not"?"checked-not":""}${data.csugInfoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csugInfoYesNot}"/>
			 			<input title="请输入企业信息名称进行查询" class="combox" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csugHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsUnitInfo(data.csugInfo).csuiName}" value="${data.csugInfo}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属单位字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csugInfo']>0">
				${lz:set("注释","****当所属单位字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csugInfo")}
			  	<input type="hidden" name="csugInfo$on" id="CsUnitInfo$on" value="true"/>
			  	<jsp:include page="/admin/unit/info.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csugInfo}
			 	
			 ${before$csugName}
			 
			 ${lz:set("注释","*****************组名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csugName" : (lz:join(relateObject,"$csugName")))}
			 ${lz:set("cname",lz:join(relateObject,".csugName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csugName || #request.defines['csugName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csugName":relateObject}" id="form-dl-csugName">
					<dt>组&nbsp;&nbsp;名&nbsp;&nbsp;称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csugNameYesNot=="not"?"checked-not":""}${data.csugNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csugNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csugName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************组名称字段的查询代码结束*****************")}
			
			
			${after$csugName}
			 	
			 ${before$csugPerson}
			 
			 ${lz:set("注释","*****************审核人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csugPerson" : (lz:join(relateObject,"$csugPerson")))}
			 ${lz:set("cname",lz:join(relateObject,".csugPerson"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csugPerson || #request.defines['csugPerson']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csugPerson":relateObject}" id="form-dl-csugPerson">
					<dt>审核人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csugPersonYesNot=="not"?"checked-not":""}${data.csugPersonYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csugPersonYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/unit/person_query.do?size=-1&csupGroup={csugId}&csupHost={csugHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csugPerson}">
				 					${get:CsUnitPerson(data.csugPerson).csupName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/person_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************审核人员字段的查询代码结束*****************")}
			
			
			${after$csugPerson}
			 	
			 ${before$csugRemark}
			 
			 ${lz:set("注释","*****************备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csugRemark" : (lz:join(relateObject,"$csugRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".csugRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csugRemark || #request.defines['csugRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csugRemark":relateObject}" id="form-dl-csugRemark">
					<dt>备　　注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csugRemarkYesNot=="not"?"checked-not":""}${data.csugRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csugRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csugRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注字段的查询代码结束*****************")}
			
			
			${after$csugRemark}
			 	
			 ${before$csugMemo}
			 
			 ${lz:set("注释","*****************备忘信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csugMemo" : (lz:join(relateObject,"$csugMemo")))}
			 ${lz:set("cname",lz:join(relateObject,".csugMemo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csugMemo || #request.defines['csugMemo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csugMemo":relateObject}" id="form-dl-csugMemo">
					<dt>备忘信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csugMemoYesNot=="not"?"checked-not":""}${data.csugMemoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csugMemoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.csugMemo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备忘信息字段的查询代码结束*****************")}
			
			
			${after$csugMemo}
			 	
			 ${before$csugData}
			 
			 ${lz:set("注释","*****************程序数据字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csugData" : (lz:join(relateObject,"$csugData")))}
			 ${lz:set("cname",lz:join(relateObject,".csugData"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csugData || #request.defines['csugData']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csugData":relateObject}" id="form-dl-csugData">
					<dt>程序数据：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csugDataYesNot=="not"?"checked-not":""}${data.csugDataYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csugDataYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.csugData}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************程序数据字段的查询代码结束*****************")}
			
			
			${after$csugData}
			 	
			 ${before$csugUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csugUpdateTime" : (lz:join(relateObject,"$csugUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csugUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csugUpdateTime || #request.defines['csugUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csugUpdateTime":relateObject}" id="form-dl-csugUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csugUpdateTimeYesNot=="not"?"checked-not":""}${data.csugUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csugUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csugUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csugUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csugUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csugUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csugUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csugUpdateTime}
			 	
			 ${before$csugAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csugAddTime" : (lz:join(relateObject,"$csugAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csugAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csugAddTime || #request.defines['csugAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csugAddTime":relateObject}" id="form-dl-csugAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csugAddTimeYesNot=="not"?"checked-not":""}${data.csugAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csugAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csugAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csugAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csugAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csugAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csugAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csugAddTime}
			 	
			 ${before$csugStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csugStatus" : (lz:join(relateObject,"$csugStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csugStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csugStatus || #request.defines['csugStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csugStatus":relateObject}" id="form-dl-csugStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csugStatusYesNot=="not"?"checked-not":""}${data.csugStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csugStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csugStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csugStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csugStatus}