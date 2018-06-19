<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$tbsbId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsbId" : (lz:join(relateObject,"$tbsbId")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsbId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsbId || #request.defines['tbsbId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsbId":relateObject}" id="form-dl-tbsbId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsbIdYesNot=="not"?"checked-not":""}${data.tbsbIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsbIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.tbsbId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$tbsbId}
			 	
			 ${before$tbsbHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsbHost" : (lz:join(relateObject,"$tbsbHost")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsbHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsbHost || #request.defines['tbsbHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsbHost":relateObject}" id="form-dl-tbsbHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsbHostYesNot=="not"?"checked-not":""}${data.tbsbHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsbHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbsbHost}">
				 					${get:SrvHost(data.tbsbHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$tbsbHost}
			 	
			 ${before$tbsbTitle}
			 
			 ${lz:set("注释","*****************战役标题字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsbTitle" : (lz:join(relateObject,"$tbsbTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsbTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsbTitle || #request.defines['tbsbTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsbTitle":relateObject}" id="form-dl-tbsbTitle">
					<dt>战役标题：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsbTitleYesNot=="not"?"checked-not":""}${data.tbsbTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsbTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbsbTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************战役标题字段的查询代码结束*****************")}
			
			
			${after$tbsbTitle}
			 	
			 ${before$tbsbHeader}
			 
			 ${lz:set("注释","*****************战役负责人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsbHeader" : (lz:join(relateObject,"$tbsbHeader")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsbHeader"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsbHeader || #request.defines['tbsbHeader']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsbHeader":relateObject}" id="form-dl-tbsbHeader">
					<dt>战役负责人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsbHeaderYesNot=="not"?"checked-not":""}${data.tbsbHeaderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsbHeaderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbsbHeader}">
				 					${get:SrvUser(data.tbsbHeader).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************战役负责人字段的查询代码结束*****************")}
			
			
			${after$tbsbHeader}
			 	
			 ${before$tbsbStart}
			 
			 ${lz:set("注释","*****************开始时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsbStart" : (lz:join(relateObject,"$tbsbStart")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsbStart"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsbStart || #request.defines['tbsbStart']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsbStart":relateObject}" id="form-dl-tbsbStart">
					<dt>开始时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsbStartYesNot=="not"?"checked-not":""}${data.tbsbStartYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsbStartYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbsbStartStart,"yyyy-MM-dd")}" end="${lz:date(data.tbsbStartEnd,"yyyy-MM-dd")}" exp="${data.tbsbStartExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbsbStartStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>开始时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbsbStartEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************开始时间字段的查询代码结束*****************")}
			
			
			${after$tbsbStart}
			 	
			 ${before$tbsbFinish}
			 
			 ${lz:set("注释","*****************结束时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsbFinish" : (lz:join(relateObject,"$tbsbFinish")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsbFinish"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsbFinish || #request.defines['tbsbFinish']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsbFinish":relateObject}" id="form-dl-tbsbFinish">
					<dt>结束时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsbFinishYesNot=="not"?"checked-not":""}${data.tbsbFinishYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsbFinishYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbsbFinishStart,"yyyy-MM-dd")}" end="${lz:date(data.tbsbFinishEnd,"yyyy-MM-dd")}" exp="${data.tbsbFinishExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbsbFinishStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>结束时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbsbFinishEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结束时间字段的查询代码结束*****************")}
			
			
			${after$tbsbFinish}
			 	
			 ${before$tbsbAdder}
			 
			 ${lz:set("注释","*****************添加人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsbAdder" : (lz:join(relateObject,"$tbsbAdder")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsbAdder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsbAdder || #request.defines['tbsbAdder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsbAdder":relateObject}" id="form-dl-tbsbAdder">
					<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsbAdderYesNot=="not"?"checked-not":""}${data.tbsbAdderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsbAdderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbsbAdder}">
				 					${get:SrvUser(data.tbsbAdder).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人字段的查询代码结束*****************")}
			
			
			${after$tbsbAdder}
			 	
			 ${before$tbsbEditor}
			 
			 ${lz:set("注释","*****************修改人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsbEditor" : (lz:join(relateObject,"$tbsbEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsbEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsbEditor || #request.defines['tbsbEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsbEditor":relateObject}" id="form-dl-tbsbEditor">
					<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsbEditorYesNot=="not"?"checked-not":""}${data.tbsbEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsbEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbsbEditor}">
				 					${get:SrvUser(data.tbsbEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改人字段的查询代码结束*****************")}
			
			
			${after$tbsbEditor}
			 	
			 ${before$tbsbRemark}
			 
			 ${lz:set("注释","*****************备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsbRemark" : (lz:join(relateObject,"$tbsbRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsbRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsbRemark || #request.defines['tbsbRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsbRemark":relateObject}" id="form-dl-tbsbRemark">
					<dt>备　　注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsbRemarkYesNot=="not"?"checked-not":""}${data.tbsbRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsbRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.tbsbRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注字段的查询代码结束*****************")}
			
			
			${after$tbsbRemark}
			 	
			 ${before$tbsbUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsbUpdateTime" : (lz:join(relateObject,"$tbsbUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsbUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsbUpdateTime || #request.defines['tbsbUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsbUpdateTime":relateObject}" id="form-dl-tbsbUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsbUpdateTimeYesNot=="not"?"checked-not":""}${data.tbsbUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsbUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbsbUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbsbUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbsbUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbsbUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbsbUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$tbsbUpdateTime}
			 	
			 ${before$tbsbAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsbAddTime" : (lz:join(relateObject,"$tbsbAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsbAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsbAddTime || #request.defines['tbsbAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsbAddTime":relateObject}" id="form-dl-tbsbAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsbAddTimeYesNot=="not"?"checked-not":""}${data.tbsbAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsbAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbsbAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbsbAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbsbAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbsbAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbsbAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$tbsbAddTime}
			 	
			 ${before$tbsbStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsbStatus" : (lz:join(relateObject,"$tbsbStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsbStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsbStatus || #request.defines['tbsbStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsbStatus":relateObject}" id="form-dl-tbsbStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsbStatusYesNot=="not"?"checked-not":""}${data.tbsbStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsbStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.tbsbStatus==0?"selected":""}>策划中</option>
							<option value="1" ${data.tbsbStatus==1?"selected":""}>执行中</option>
							<option value="2" ${data.tbsbStatus==2?"selected":""}>已完成</option>
							<option value="3" ${data.tbsbStatus==3?"selected":""}>已放弃</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$tbsbStatus}