<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$tbpcId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpcId" : (lz:join(relateObject,"$tbpcId")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpcId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpcId || #request.defines['tbpcId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpcId":relateObject}" id="form-dl-tbpcId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpcIdYesNot=="not"?"checked-not":""}${data.tbpcIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpcIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.tbpcId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$tbpcId}
			 	
			 ${before$tbpcHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpcHost" : (lz:join(relateObject,"$tbpcHost")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpcHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpcHost || #request.defines['tbpcHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpcHost":relateObject}" id="form-dl-tbpcHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpcHostYesNot=="not"?"checked-not":""}${data.tbpcHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpcHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbpcHost}">
				 					${get:SrvHost(data.tbpcHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$tbpcHost}
			 	
			 ${before$tbpcTitle}
			 
			 ${lz:set("注释","*****************签到描述字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpcTitle" : (lz:join(relateObject,"$tbpcTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpcTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpcTitle || #request.defines['tbpcTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpcTitle":relateObject}" id="form-dl-tbpcTitle">
					<dt>签到描述：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpcTitleYesNot=="not"?"checked-not":""}${data.tbpcTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpcTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbpcTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************签到描述字段的查询代码结束*****************")}
			
			
			${after$tbpcTitle}
			 	
			 ${before$tbpcEmployee}
			 
			 ${lz:set("注释","*****************员工字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpcEmployee" : (lz:join(relateObject,"$tbpcEmployee")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpcEmployee"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpcEmployee || #request.defines['tbpcEmployee']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpcEmployee":relateObject}" id="form-dl-tbpcEmployee">
					<dt>员　　工：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpcEmployeeYesNot=="not"?"checked-not":""}${data.tbpcEmployeeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpcEmployeeYesNot}"/>
			 			<input title="请输入公司员工姓名进行查询" class="combox" action="${basePath}${proname}/permissions/user_tree.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:TbEmployee(data.tbpcEmployee).tbeName}" value="${data.tbpcEmployee}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/oa/hr/employee_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************员工字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbpcEmployee']>0">
				${lz:set("注释","****当员工字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbpcEmployee")}
			  	<input type="hidden" name="tbpcEmployee$on" id="TbEmployee$on" value="true"/>
			  	<jsp:include page="/admin/oa/hr/employee.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbpcEmployee}
			 	
			 ${before$tbpcOutTime}
			 
			 ${lz:set("注释","*****************签出时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpcOutTime" : (lz:join(relateObject,"$tbpcOutTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpcOutTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpcOutTime || #request.defines['tbpcOutTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpcOutTime":relateObject}" id="form-dl-tbpcOutTime">
					<dt>签出时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpcOutTimeYesNot=="not"?"checked-not":""}${data.tbpcOutTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpcOutTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbpcOutTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbpcOutTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbpcOutTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbpcOutTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>签出时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbpcOutTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************签出时间字段的查询代码结束*****************")}
			
			
			${after$tbpcOutTime}
			 	
			 ${before$tbpcRemark}
			 
			 ${lz:set("注释","*****************备注信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpcRemark" : (lz:join(relateObject,"$tbpcRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpcRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpcRemark || #request.defines['tbpcRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpcRemark":relateObject}" id="form-dl-tbpcRemark">
					<dt>备注信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpcRemarkYesNot=="not"?"checked-not":""}${data.tbpcRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpcRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.tbpcRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注信息字段的查询代码结束*****************")}
			
			
			${after$tbpcRemark}
			 	
			 ${before$tbpcStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbpcStatus" : (lz:join(relateObject,"$tbpcStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".tbpcStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbpcStatus || #request.defines['tbpcStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbpcStatus":relateObject}" id="form-dl-tbpcStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbpcStatusYesNot=="not"?"checked-not":""}${data.tbpcStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbpcStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.tbpcStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.tbpcStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$tbpcStatus}