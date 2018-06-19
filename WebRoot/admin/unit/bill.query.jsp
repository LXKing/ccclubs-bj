<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csubId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csubId" : (lz:join(relateObject,"$csubId")))}
			 ${lz:set("cname",lz:join(relateObject,".csubId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csubId || #request.defines['csubId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csubId":relateObject}" id="form-dl-csubId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csubIdYesNot=="not"?"checked-not":""}${data.csubIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csubIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csubId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csubId}
			 	
			 ${before$csubHost}
			 
			 ${lz:set("注释","*****************运营地区字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csubHost" : (lz:join(relateObject,"$csubHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csubHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csubHost || #request.defines['csubHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csubHost":relateObject}" id="form-dl-csubHost">
					<dt>运营地区：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csubHostYesNot=="not"?"checked-not":""}${data.csubHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csubHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csubHost}">
				 					${get:SrvHost(data.csubHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************运营地区字段的查询代码结束*****************")}
			
			
			${after$csubHost}
			 	
			 ${before$csubMonth}
			 
			 ${lz:set("注释","*****************帐单月份字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csubMonth" : (lz:join(relateObject,"$csubMonth")))}
			 ${lz:set("cname",lz:join(relateObject,".csubMonth"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csubMonth || #request.defines['csubMonth']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csubMonth":relateObject}" id="form-dl-csubMonth">
					<dt>帐单月份：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csubMonthYesNot=="not"?"checked-not":""}${data.csubMonthYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csubMonthYesNot}"/>
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM'})" type="text" class="input" size="14" maxlength="19" name="${NAME}" id="${NAME}"  value="${data.csubMonth}"/>
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csubMonthStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>帐单月份：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csubMonthEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************帐单月份字段的查询代码结束*****************")}
			
			
			${after$csubMonth}
			 	
			 ${before$csubUnit}
			 
			 ${lz:set("注释","*****************所属单位字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csubUnit" : (lz:join(relateObject,"$csubUnit")))}
			 ${lz:set("cname",lz:join(relateObject,".csubUnit"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csubUnit || #request.defines['csubUnit']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csubUnit":relateObject}" id="form-dl-csubUnit">
					<dt>所属单位：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csubUnitYesNot=="not"?"checked-not":""}${data.csubUnitYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csubUnitYesNot}"/>
			 			<input title="请输入企业信息名称进行查询" class="combox" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csubHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsUnitInfo(data.csubUnit).csuiName}" value="${data.csubUnit}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属单位字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csubUnit']>0">
				${lz:set("注释","****当所属单位字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csubUnit")}
			  	<input type="hidden" name="csubUnit$on" id="CsUnitInfo$on" value="true"/>
			  	<jsp:include page="/admin/unit/info.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csubUnit}
			 	
			 ${before$csubEditor}
			 
			 ${lz:set("注释","*****************修改人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csubEditor" : (lz:join(relateObject,"$csubEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".csubEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csubEditor || #request.defines['csubEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csubEditor":relateObject}" id="form-dl-csubEditor">
					<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csubEditorYesNot=="not"?"checked-not":""}${data.csubEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csubEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csubEditor}">
				 					${get:SrvUser(data.csubEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改人字段的查询代码结束*****************")}
			
			
			${after$csubEditor}
			 	
			 ${before$csubUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csubUpdateTime" : (lz:join(relateObject,"$csubUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csubUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csubUpdateTime || #request.defines['csubUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csubUpdateTime":relateObject}" id="form-dl-csubUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csubUpdateTimeYesNot=="not"?"checked-not":""}${data.csubUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csubUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csubUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csubUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csubUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csubUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csubUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csubUpdateTime}
			 	
			 ${before$csubAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csubAddTime" : (lz:join(relateObject,"$csubAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csubAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csubAddTime || #request.defines['csubAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csubAddTime":relateObject}" id="form-dl-csubAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csubAddTimeYesNot=="not"?"checked-not":""}${data.csubAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csubAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csubAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csubAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csubAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csubAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csubAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csubAddTime}
			 	
			 ${before$csubStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csubStatus" : (lz:join(relateObject,"$csubStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csubStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csubStatus || #request.defines['csubStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csubStatus":relateObject}" id="form-dl-csubStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csubStatusYesNot=="not"?"checked-not":""}${data.csubStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csubStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csubStatus==0?"selected":""}>未审核</option>
							<option value="1" ${data.csubStatus==1?"selected":""}>待结算</option>
							<option value="2" ${data.csubStatus==2?"selected":""}>已结算</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csubStatus}