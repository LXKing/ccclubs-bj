<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csmpId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmpId" : (lz:join(relateObject,"$csmpId")))}
			 ${lz:set("cname",lz:join(relateObject,".csmpId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmpId || #request.defines['csmpId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmpId":relateObject}" id="form-dl-csmpId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmpIdYesNot=="not"?"checked-not":""}${data.csmpIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmpIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csmpId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csmpId}
			 	
			 ${before$csmpHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmpHost" : (lz:join(relateObject,"$csmpHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csmpHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmpHost || #request.defines['csmpHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmpHost":relateObject}" id="form-dl-csmpHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmpHostYesNot=="not"?"checked-not":""}${data.csmpHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmpHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmpHost}">
				 					${get:SrvHost(data.csmpHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csmpHost}
			 	
			 ${before$csmpName}
			 
			 ${lz:set("注释","*****************方案主题字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmpName" : (lz:join(relateObject,"$csmpName")))}
			 ${lz:set("cname",lz:join(relateObject,".csmpName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmpName || #request.defines['csmpName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmpName":relateObject}" id="form-dl-csmpName">
					<dt>方案主题：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmpNameYesNot=="not"?"checked-not":""}${data.csmpNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmpNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="64" name="${NAME}" id="${NAME}"  value="${data.csmpName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************方案主题字段的查询代码结束*****************")}
			
			
			${after$csmpName}
			 	
			 ${before$csmpType}
			 
			 ${lz:set("注释","*****************营销类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmpType" : (lz:join(relateObject,"$csmpType")))}
			 ${lz:set("cname",lz:join(relateObject,".csmpType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmpType || #request.defines['csmpType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmpType":relateObject}" id="form-dl-csmpType">
					<dt>营销类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmpTypeYesNot=="not"?"checked-not":""}${data.csmpTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmpTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmpType==0?"selected":""}>会员营销</option>
							<option value="1" ${data.csmpType==1?"selected":""}>产品营销</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************营销类型字段的查询代码结束*****************")}
			
			
			${after$csmpType}
			 	
			 ${before$csmpMethod}
			 
			 ${lz:set("注释","*****************营销手段字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmpMethod" : (lz:join(relateObject,"$csmpMethod")))}
			 ${lz:set("cname",lz:join(relateObject,".csmpMethod"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmpMethod || #request.defines['csmpMethod']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmpMethod":relateObject}" id="form-dl-csmpMethod">
					<dt>营销手段：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmpMethodYesNot=="not"?"checked-not":""}${data.csmpMethodYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmpMethodYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", get:propertys("营销手段","market_method"))}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.spId}" ${data.csmpMethod==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************营销手段字段的查询代码结束*****************")}
			
			
			${after$csmpMethod}
			 	
			 ${before$csmpPerson}
			 
			 ${lz:set("注释","*****************策划人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmpPerson" : (lz:join(relateObject,"$csmpPerson")))}
			 ${lz:set("cname",lz:join(relateObject,".csmpPerson"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmpPerson || #request.defines['csmpPerson']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmpPerson":relateObject}" id="form-dl-csmpPerson">
					<dt>策&nbsp;&nbsp;划&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmpPersonYesNot=="not"?"checked-not":""}${data.csmpPersonYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmpPersonYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmpPerson}">
				 					${get:SrvUser(data.csmpPerson).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************策划人字段的查询代码结束*****************")}
			
			
			${after$csmpPerson}
			 	
			 ${before$csmpVIndex}
			 
			 ${lz:set("注释","*****************启用V指标字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmpVIndex" : (lz:join(relateObject,"$csmpVIndex")))}
			 ${lz:set("cname",lz:join(relateObject,".csmpVIndex"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmpVIndex || #request.defines['csmpVIndex']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmpVIndex":relateObject}" id="form-dl-csmpVIndex">
					<dt>启用V指标：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmpVIndexYesNot=="not"?"checked-not":""}${data.csmpVIndexYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmpVIndexYesNot}"/>
			 		<input type="radio" value="" name="${NAME}" id="${NAME}" ${data.csmpVIndex==null?"checked":""}/>
					全部&nbsp;&nbsp;
			 	     <input type="radio" value="1" name="${NAME}" id="${NAME}" ${data.csmpVIndex=='true'?"checked":""}/>
					是&nbsp;&nbsp;
					<input type="radio" value="0" name="${NAME}" id="${NAME}" ${data.csmpVIndex=='false'?"checked":""}/>否
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************启用V指标字段的查询代码结束*****************")}
			
			
			${after$csmpVIndex}
			 	
			 ${before$csmpStart}
			 
			 ${lz:set("注释","*****************开始时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmpStart" : (lz:join(relateObject,"$csmpStart")))}
			 ${lz:set("cname",lz:join(relateObject,".csmpStart"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmpStart || #request.defines['csmpStart']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmpStart":relateObject}" id="form-dl-csmpStart">
					<dt>开始时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmpStartYesNot=="not"?"checked-not":""}${data.csmpStartYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmpStartYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmpStartStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmpStartEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmpStartExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmpStartStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>开始时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmpStartEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************开始时间字段的查询代码结束*****************")}
			
			
			${after$csmpStart}
			 	
			 ${before$csmpFinish}
			 
			 ${lz:set("注释","*****************结束时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmpFinish" : (lz:join(relateObject,"$csmpFinish")))}
			 ${lz:set("cname",lz:join(relateObject,".csmpFinish"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmpFinish || #request.defines['csmpFinish']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmpFinish":relateObject}" id="form-dl-csmpFinish">
					<dt>结束时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmpFinishYesNot=="not"?"checked-not":""}${data.csmpFinishYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmpFinishYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmpFinishStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmpFinishEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmpFinishExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmpFinishStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>结束时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmpFinishEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结束时间字段的查询代码结束*****************")}
			
			
			${after$csmpFinish}
			 	
			 ${before$csmpUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmpUpdateTime" : (lz:join(relateObject,"$csmpUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmpUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmpUpdateTime || #request.defines['csmpUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmpUpdateTime":relateObject}" id="form-dl-csmpUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmpUpdateTimeYesNot=="not"?"checked-not":""}${data.csmpUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmpUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmpUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmpUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmpUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmpUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmpUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csmpUpdateTime}
			 	
			 ${before$csmpAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmpAddTime" : (lz:join(relateObject,"$csmpAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmpAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmpAddTime || #request.defines['csmpAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmpAddTime":relateObject}" id="form-dl-csmpAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmpAddTimeYesNot=="not"?"checked-not":""}${data.csmpAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmpAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmpAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmpAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmpAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmpAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmpAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csmpAddTime}
			 	
			 ${before$csmpStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmpStatus" : (lz:join(relateObject,"$csmpStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csmpStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmpStatus || #request.defines['csmpStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmpStatus":relateObject}" id="form-dl-csmpStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmpStatusYesNot=="not"?"checked-not":""}${data.csmpStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmpStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmpStatus==0?"selected":""}>策划中</option>
							<option value="1" ${data.csmpStatus==1?"selected":""}>执行中</option>
							<option value="2" ${data.csmpStatus==2?"selected":""}>执行完成</option>
							<option value="3" ${data.csmpStatus==3?"selected":""}>已取消</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csmpStatus}