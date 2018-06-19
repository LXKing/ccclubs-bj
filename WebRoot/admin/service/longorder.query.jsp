<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csloId}
			 
			 ${lz:set("注释","*****************订单编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloId" : (lz:join(relateObject,"$csloId")))}
			 ${lz:set("cname",lz:join(relateObject,".csloId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloId || #request.defines['csloId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloId":relateObject}" id="form-dl-csloId">
					<dt>订单编号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloIdYesNot=="not"?"checked-not":""}${data.csloIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csloId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单编号字段的查询代码结束*****************")}
			
			
			${after$csloId}
			 	
			 ${before$csloHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloHost" : (lz:join(relateObject,"$csloHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csloHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloHost || #request.defines['csloHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloHost":relateObject}" id="form-dl-csloHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloHostYesNot=="not"?"checked-not":""}${data.csloHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csloHost}">
				 					${get:SrvHost(data.csloHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csloHost}
			 	
			 ${before$csloPackNo}
			 
			 ${lz:set("注释","*****************合同号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloPackNo" : (lz:join(relateObject,"$csloPackNo")))}
			 ${lz:set("cname",lz:join(relateObject,".csloPackNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloPackNo || #request.defines['csloPackNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloPackNo":relateObject}" id="form-dl-csloPackNo">
					<dt>合&nbsp;&nbsp;同&nbsp;&nbsp;号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloPackNoYesNot=="not"?"checked-not":""}${data.csloPackNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloPackNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csloPackNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************合同号字段的查询代码结束*****************")}
			
			
			${after$csloPackNo}
			 	
			 ${before$csloMember}
			 
			 ${lz:set("注释","*****************会员帐号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloMember" : (lz:join(relateObject,"$csloMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csloMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloMember || #request.defines['csloMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloMember":relateObject}" id="form-dl-csloMember">
					<dt>会员帐号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloMemberYesNot=="not"?"checked-not":""}${data.csloMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csloHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csloMember).csmName}" value="${data.csloMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************会员帐号字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csloMember']>0">
				${lz:set("注释","****当会员帐号字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csloMember")}
			  	<input type="hidden" name="csloMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csloMember}
			 	
			 ${before$csloPackProfile}
			 
			 ${lz:set("注释","*****************合同备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloPackProfile" : (lz:join(relateObject,"$csloPackProfile")))}
			 ${lz:set("cname",lz:join(relateObject,".csloPackProfile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloPackProfile || #request.defines['csloPackProfile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloPackProfile":relateObject}" id="form-dl-csloPackProfile">
					<dt>合同备注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloPackProfileYesNot=="not"?"checked-not":""}${data.csloPackProfileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloPackProfileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csloPackProfile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************合同备注字段的查询代码结束*****************")}
			
			
			${after$csloPackProfile}
			 	
			 ${before$csloPayCycle}
			 
			 ${lz:set("注释","*****************付款周期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloPayCycle" : (lz:join(relateObject,"$csloPayCycle")))}
			 ${lz:set("cname",lz:join(relateObject,".csloPayCycle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloPayCycle || #request.defines['csloPayCycle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloPayCycle":relateObject}" id="form-dl-csloPayCycle">
					<dt>付款周期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloPayCycleYesNot=="not"?"checked-not":""}${data.csloPayCycleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloPayCycleYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csloPayCycle==0?"selected":""}>一次付清</option>
							<option value="1" ${data.csloPayCycle==1?"selected":""}>按月付</option>
							<option value="2" ${data.csloPayCycle==2?"selected":""}>按季付</option>
							<option value="3" ${data.csloPayCycle==3?"selected":""}>半年付</option>
							<option value="4" ${data.csloPayCycle==4?"selected":""}>按年付</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************付款周期字段的查询代码结束*****************")}
			
			
			${after$csloPayCycle}
			 	
			 ${before$csloPactTime}
			 
			 ${lz:set("注释","*****************合同签订时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloPactTime" : (lz:join(relateObject,"$csloPactTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csloPactTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloPactTime || #request.defines['csloPactTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloPactTime":relateObject}" id="form-dl-csloPactTime">
					<dt>合同签订时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloPactTimeYesNot=="not"?"checked-not":""}${data.csloPactTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloPactTimeYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csloPactTimeStart,"yyyy-MM-dd")}" end="${lz:date(data.csloPactTimeEnd,"yyyy-MM-dd")}" exp="${data.csloPactTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csloPactTimeStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>合同签订时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csloPactTimeEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************合同签订时间字段的查询代码结束*****************")}
			
			
			${after$csloPactTime}
			 	
			 ${before$csloStartTime}
			 
			 ${lz:set("注释","*****************合同开始时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloStartTime" : (lz:join(relateObject,"$csloStartTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csloStartTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloStartTime || #request.defines['csloStartTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloStartTime":relateObject}" id="form-dl-csloStartTime">
					<dt>合同开始时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloStartTimeYesNot=="not"?"checked-not":""}${data.csloStartTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloStartTimeYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csloStartTimeStart,"yyyy-MM-dd")}" end="${lz:date(data.csloStartTimeEnd,"yyyy-MM-dd")}" exp="${data.csloStartTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csloStartTimeStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>合同开始时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csloStartTimeEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************合同开始时间字段的查询代码结束*****************")}
			
			
			${after$csloStartTime}
			 	
			 ${before$csloFinishTime}
			 
			 ${lz:set("注释","*****************合同结束时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloFinishTime" : (lz:join(relateObject,"$csloFinishTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csloFinishTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloFinishTime || #request.defines['csloFinishTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloFinishTime":relateObject}" id="form-dl-csloFinishTime">
					<dt>合同结束时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloFinishTimeYesNot=="not"?"checked-not":""}${data.csloFinishTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloFinishTimeYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csloFinishTimeStart,"yyyy-MM-dd")}" end="${lz:date(data.csloFinishTimeEnd,"yyyy-MM-dd")}" exp="${data.csloFinishTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csloFinishTimeStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>合同结束时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csloFinishTimeEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************合同结束时间字段的查询代码结束*****************")}
			
			
			${after$csloFinishTime}
			 	
			 ${before$csloUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloUpdateTime" : (lz:join(relateObject,"$csloUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csloUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloUpdateTime || #request.defines['csloUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloUpdateTime":relateObject}" id="form-dl-csloUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloUpdateTimeYesNot=="not"?"checked-not":""}${data.csloUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csloUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csloUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csloUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csloUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csloUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csloUpdateTime}
			 	
			 ${before$csloAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloAddTime" : (lz:join(relateObject,"$csloAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csloAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloAddTime || #request.defines['csloAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloAddTime":relateObject}" id="form-dl-csloAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloAddTimeYesNot=="not"?"checked-not":""}${data.csloAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csloAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csloAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csloAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csloAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csloAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csloAddTime}
			 	
			 ${before$csloEditor}
			 
			 ${lz:set("注释","*****************操作人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloEditor" : (lz:join(relateObject,"$csloEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".csloEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloEditor || #request.defines['csloEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloEditor":relateObject}" id="form-dl-csloEditor">
					<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloEditorYesNot=="not"?"checked-not":""}${data.csloEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csloEditor}">
				 					${get:SrvUser(data.csloEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作人字段的查询代码结束*****************")}
			
			
			${after$csloEditor}
			 	
			 ${before$csloError}
			 
			 ${lz:set("注释","*****************报警信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloError" : (lz:join(relateObject,"$csloError")))}
			 ${lz:set("cname",lz:join(relateObject,".csloError"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloError || #request.defines['csloError']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloError":relateObject}" id="form-dl-csloError">
					<dt>报警信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloErrorYesNot=="not"?"checked-not":""}${data.csloErrorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloErrorYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csloError}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************报警信息字段的查询代码结束*****************")}
			
			
			${after$csloError}
			 	
			 ${before$csloStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csloStatus" : (lz:join(relateObject,"$csloStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csloStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csloStatus || #request.defines['csloStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csloStatus":relateObject}" id="form-dl-csloStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csloStatusYesNot=="not"?"checked-not":""}${data.csloStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csloStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csloStatus==0?"selected":""}>已预定</option>
							<option value="1" ${data.csloStatus==1?"selected":""}>使用中</option>
							<option value="2" ${data.csloStatus==2?"selected":""}>已还车</option>
							<option value="3" ${data.csloStatus==3?"selected":""}>已取消</option>
							<option value="4" ${data.csloStatus==4?"selected":""}>已完成</option>
							<option value="5" ${data.csloStatus==5?"selected":""}>待处理</option>
							<option value="6" ${data.csloStatus==6?"selected":""}>已退款</option>
							<option value="7" ${data.csloStatus==7?"selected":""}>已作废</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csloStatus}