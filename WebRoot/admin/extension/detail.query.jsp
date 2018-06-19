<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csvdId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvdId" : (lz:join(relateObject,"$csvdId")))}
			 ${lz:set("cname",lz:join(relateObject,".csvdId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvdId || #request.defines['csvdId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvdId":relateObject}" id="form-dl-csvdId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvdIdYesNot=="not"?"checked-not":""}${data.csvdIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvdIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csvdId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csvdId}
			 	
			 ${before$csvdHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvdHost" : (lz:join(relateObject,"$csvdHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csvdHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvdHost || #request.defines['csvdHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvdHost":relateObject}" id="form-dl-csvdHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvdHostYesNot=="not"?"checked-not":""}${data.csvdHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvdHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csvdHost}">
				 					${get:SrvHost(data.csvdHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csvdHost}
			 	
			 ${before$csvdChannel}
			 
			 ${lz:set("注释","*****************所属渠道字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvdChannel" : (lz:join(relateObject,"$csvdChannel")))}
			 ${lz:set("cname",lz:join(relateObject,".csvdChannel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvdChannel || #request.defines['csvdChannel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvdChannel":relateObject}" id="form-dl-csvdChannel">
					<dt>所属渠道：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvdChannelYesNot=="not"?"checked-not":""}${data.csvdChannelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvdChannelYesNot}"/>
			 			<input title="请输入访问渠道渠道名称进行查询" class="combox" action="${basePath}${proname}/extension/channel_query.do?value={param}&cscHost={csvdHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsChannel(data.csvdChannel).cscName}" value="${data.csvdChannel}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/extension/channel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属渠道字段的查询代码结束*****************")}
			
			
			${after$csvdChannel}
			 	
			 ${before$csvdPlan}
			 
			 ${lz:set("注释","*****************所属策划字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvdPlan" : (lz:join(relateObject,"$csvdPlan")))}
			 ${lz:set("cname",lz:join(relateObject,".csvdPlan"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvdPlan || #request.defines['csvdPlan']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvdPlan":relateObject}" id="form-dl-csvdPlan">
					<dt>所属策划：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvdPlanYesNot=="not"?"checked-not":""}${data.csvdPlanYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvdPlanYesNot}"/>
			 			<input title="请输入营销方案方案主题进行查询" class="combox" action="${basePath}${proname}/operate/marketplan_query.do?value={param}&csmpHost={csvdHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMarketPlan(data.csvdPlan).csmpName}" value="${data.csvdPlan}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/operate/marketplan_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属策划字段的查询代码结束*****************")}
			
			
			${after$csvdPlan}
			 	
			 ${before$csvdActivity}
			 
			 ${lz:set("注释","*****************所属活动字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvdActivity" : (lz:join(relateObject,"$csvdActivity")))}
			 ${lz:set("cname",lz:join(relateObject,".csvdActivity"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvdActivity || #request.defines['csvdActivity']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvdActivity":relateObject}" id="form-dl-csvdActivity">
					<dt>所属活动：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvdActivityYesNot=="not"?"checked-not":""}${data.csvdActivityYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvdActivityYesNot}"/>
			 			<input title="请输入营销活动活动主题进行查询" class="combox" action="${basePath}${proname}/operate/activitys/activity_query.do?value={param}&csaHost={csvdHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsActivity(data.csvdActivity).csaTitle}" value="${data.csvdActivity}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/operate/activitys/activity_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属活动字段的查询代码结束*****************")}
			
			
			${after$csvdActivity}
			 	
			 ${before$csvdAddTime}
			 
			 ${lz:set("注释","*****************访问时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvdAddTime" : (lz:join(relateObject,"$csvdAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csvdAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvdAddTime || #request.defines['csvdAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvdAddTime":relateObject}" id="form-dl-csvdAddTime">
					<dt>访问时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvdAddTimeYesNot=="not"?"checked-not":""}${data.csvdAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvdAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csvdAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csvdAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csvdAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csvdAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>访问时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csvdAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************访问时间字段的查询代码结束*****************")}
			
			
			${after$csvdAddTime}
			 	
			 ${before$csvdStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvdStatus" : (lz:join(relateObject,"$csvdStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csvdStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvdStatus || #request.defines['csvdStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvdStatus":relateObject}" id="form-dl-csvdStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvdStatusYesNot=="not"?"checked-not":""}${data.csvdStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvdStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csvdStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csvdStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csvdStatus}