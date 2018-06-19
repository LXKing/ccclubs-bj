<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$tbeId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbeId" : (lz:join(relateObject,"$tbeId")))}
			 ${lz:set("cname",lz:join(relateObject,".tbeId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbeId || #request.defines['tbeId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbeId":relateObject}" id="form-dl-tbeId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbeIdYesNot=="not"?"checked-not":""}${data.tbeIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbeIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.tbeId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$tbeId}
			 	
			 ${before$tbeName}
			 
			 ${lz:set("注释","*****************姓名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbeName" : (lz:join(relateObject,"$tbeName")))}
			 ${lz:set("cname",lz:join(relateObject,".tbeName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbeName || #request.defines['tbeName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbeName":relateObject}" id="form-dl-tbeName">
					<dt>姓　　名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbeNameYesNot=="not"?"checked-not":""}${data.tbeNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbeNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbeName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************姓名字段的查询代码结束*****************")}
			
			
			${after$tbeName}
			 	
			 ${before$tbeDepart}
			 
			 ${lz:set("注释","*****************部门字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbeDepart" : (lz:join(relateObject,"$tbeDepart")))}
			 ${lz:set("cname",lz:join(relateObject,".tbeDepart"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbeDepart || #request.defines['tbeDepart']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbeDepart":relateObject}" id="form-dl-tbeDepart">
					<dt>部　　门：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbeDepartYesNot=="not"?"checked-not":""}${data.tbeDepartYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbeDepartYesNot}"/>
			 			<input title="请输入公司部门部门名称进行查询" class="combox" action="${basePath}${proname}/oa/hr/department_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:TbDepartment(data.tbeDepart).tbdName}" value="${data.tbeDepart}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/oa/hr/department_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************部门字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbeDepart']>0">
				${lz:set("注释","****当部门字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbeDepart")}
			  	<input type="hidden" name="tbeDepart$on" id="TbDepartment$on" value="true"/>
			  	<jsp:include page="/admin/oa/hr/department.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbeDepart}
			 	
			 ${before$tbeEditor}
			 
			 ${lz:set("注释","*****************修改人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbeEditor" : (lz:join(relateObject,"$tbeEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".tbeEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbeEditor || #request.defines['tbeEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbeEditor":relateObject}" id="form-dl-tbeEditor">
					<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbeEditorYesNot=="not"?"checked-not":""}${data.tbeEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbeEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbeEditor}">
				 					${get:SrvUser(data.tbeEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改人字段的查询代码结束*****************")}
			
			
			${after$tbeEditor}
			 	
			 ${before$tbeRemark}
			 
			 ${lz:set("注释","*****************备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbeRemark" : (lz:join(relateObject,"$tbeRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".tbeRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbeRemark || #request.defines['tbeRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbeRemark":relateObject}" id="form-dl-tbeRemark">
					<dt>备　　注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbeRemarkYesNot=="not"?"checked-not":""}${data.tbeRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbeRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.tbeRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注字段的查询代码结束*****************")}
			
			
			${after$tbeRemark}
			 	
			 ${before$tbeMemo}
			 
			 ${lz:set("注释","*****************备忘信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbeMemo" : (lz:join(relateObject,"$tbeMemo")))}
			 ${lz:set("cname",lz:join(relateObject,".tbeMemo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbeMemo || #request.defines['tbeMemo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbeMemo":relateObject}" id="form-dl-tbeMemo">
					<dt>备忘信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbeMemoYesNot=="not"?"checked-not":""}${data.tbeMemoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbeMemoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.tbeMemo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备忘信息字段的查询代码结束*****************")}
			
			
			${after$tbeMemo}
			 	
			 ${before$tbeData}
			 
			 ${lz:set("注释","*****************程序数据字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbeData" : (lz:join(relateObject,"$tbeData")))}
			 ${lz:set("cname",lz:join(relateObject,".tbeData"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbeData || #request.defines['tbeData']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbeData":relateObject}" id="form-dl-tbeData">
					<dt>程序数据：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbeDataYesNot=="not"?"checked-not":""}${data.tbeDataYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbeDataYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.tbeData}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************程序数据字段的查询代码结束*****************")}
			
			
			${after$tbeData}
			 	
			 ${before$tbeUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbeUpdateTime" : (lz:join(relateObject,"$tbeUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbeUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbeUpdateTime || #request.defines['tbeUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbeUpdateTime":relateObject}" id="form-dl-tbeUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbeUpdateTimeYesNot=="not"?"checked-not":""}${data.tbeUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbeUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbeUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbeUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbeUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbeUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbeUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$tbeUpdateTime}
			 	
			 ${before$tbeAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbeAddTime" : (lz:join(relateObject,"$tbeAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbeAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbeAddTime || #request.defines['tbeAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbeAddTime":relateObject}" id="form-dl-tbeAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbeAddTimeYesNot=="not"?"checked-not":""}${data.tbeAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbeAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbeAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbeAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbeAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbeAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbeAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$tbeAddTime}
			 	
			 ${before$tbeStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbeStatus" : (lz:join(relateObject,"$tbeStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".tbeStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbeStatus || #request.defines['tbeStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbeStatus":relateObject}" id="form-dl-tbeStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbeStatusYesNot=="not"?"checked-not":""}${data.tbeStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbeStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.tbeStatus==0?"selected":""}>待审核</option>
							<option value="1" ${data.tbeStatus==1?"selected":""}>正常</option>
							<option value="2" ${data.tbeStatus==2?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$tbeStatus}