<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csetId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csetId" : (lz:join(relateObject,"$csetId")))}
			 ${lz:set("cname",lz:join(relateObject,".csetId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csetId || #request.defines['csetId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csetId":relateObject}" id="form-dl-csetId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csetIdYesNot=="not"?"checked-not":""}${data.csetIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csetIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csetId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csetId}
			 	
			 ${before$csetName}
			 
			 ${lz:set("注释","*****************事件类型名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csetName" : (lz:join(relateObject,"$csetName")))}
			 ${lz:set("cname",lz:join(relateObject,".csetName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csetName || #request.defines['csetName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csetName":relateObject}" id="form-dl-csetName">
					<dt>事件类型名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csetNameYesNot=="not"?"checked-not":""}${data.csetNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csetNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csetName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************事件类型名称字段的查询代码结束*****************")}
			
			
			${after$csetName}
			 	
			 ${before$csetProject}
			 
			 ${lz:set("注释","*****************关联项目字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csetProject" : (lz:join(relateObject,"$csetProject")))}
			 ${lz:set("cname",lz:join(relateObject,".csetProject"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csetProject || #request.defines['csetProject']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csetProject":relateObject}" id="form-dl-csetProject">
					<dt>关联项目：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csetProjectYesNot=="not"?"checked-not":""}${data.csetProjectYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csetProjectYesNot}"/>
				  		<select class="combox" id="${NAME}" name="${NAME}" action="${basePath}${proname}/permissions/project_tree.do?parent={param}">
							<option value="${data.csetProject}">
									${get:SrvProject(data.csetProject).spName}
							</option>
						</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联项目字段的查询代码结束*****************")}
			
			
			${after$csetProject}
			 	
			 ${before$csetInterval}
			 
			 ${lz:set("注释","*****************时间间隔字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csetInterval" : (lz:join(relateObject,"$csetInterval")))}
			 ${lz:set("cname",lz:join(relateObject,".csetInterval"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csetInterval || #request.defines['csetInterval']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csetInterval":relateObject}" id="form-dl-csetInterval">
					<dt>时间间隔：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csetIntervalYesNot=="not"?"checked-not":""}${data.csetIntervalYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csetIntervalYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csetInterval==0?"selected":""}>不限制</option>
							<option value="60" ${data.csetInterval==60?"selected":""}>一分钟</option>
							<option value="600" ${data.csetInterval==600?"selected":""}>十分钟</option>
							<option value="1800" ${data.csetInterval==1800?"selected":""}>半小时</option>
							<option value="3600" ${data.csetInterval==3600?"selected":""}>一小时</option>
							<option value="7200" ${data.csetInterval==7200?"selected":""}>两小时</option>
							<option value="43200" ${data.csetInterval==43200?"selected":""}>半天</option>
							<option value="86400" ${data.csetInterval==86400?"selected":""}>一天</option>
							<option value="2592000" ${data.csetInterval==2592000?"selected":""}>一个月</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************时间间隔字段的查询代码结束*****************")}
			
			
			${after$csetInterval}
			 	
			 ${before$csetUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csetUpdateTime" : (lz:join(relateObject,"$csetUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csetUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csetUpdateTime || #request.defines['csetUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csetUpdateTime":relateObject}" id="form-dl-csetUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csetUpdateTimeYesNot=="not"?"checked-not":""}${data.csetUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csetUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csetUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csetUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csetUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csetUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csetUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csetUpdateTime}
			 	
			 ${before$csetAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csetAddTime" : (lz:join(relateObject,"$csetAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csetAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csetAddTime || #request.defines['csetAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csetAddTime":relateObject}" id="form-dl-csetAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csetAddTimeYesNot=="not"?"checked-not":""}${data.csetAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csetAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csetAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csetAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csetAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csetAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csetAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csetAddTime}
			 	
			 ${before$csetStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csetStatus" : (lz:join(relateObject,"$csetStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csetStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csetStatus || #request.defines['csetStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csetStatus":relateObject}" id="form-dl-csetStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csetStatusYesNot=="not"?"checked-not":""}${data.csetStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csetStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csetStatus==1?"selected":""}>启用</option>
							<option value="0" ${data.csetStatus==0?"selected":""}>禁用</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csetStatus}