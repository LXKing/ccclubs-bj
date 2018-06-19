<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csctId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csctId" : (lz:join(relateObject,"$csctId")))}
			 ${lz:set("cname",lz:join(relateObject,".csctId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csctId || #request.defines['csctId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csctId":relateObject}" id="form-dl-csctId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csctIdYesNot=="not"?"checked-not":""}${data.csctIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csctIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csctId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csctId}
			 	
			 ${before$csctHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csctHost" : (lz:join(relateObject,"$csctHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csctHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csctHost || #request.defines['csctHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csctHost":relateObject}" id="form-dl-csctHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csctHostYesNot=="not"?"checked-not":""}${data.csctHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csctHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csctHost}">
				 					${get:SrvHost(data.csctHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csctHost}
			 	
			 ${before$csctName}
			 
			 ${lz:set("注释","*****************名称姓名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csctName" : (lz:join(relateObject,"$csctName")))}
			 ${lz:set("cname",lz:join(relateObject,".csctName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csctName || #request.defines['csctName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csctName":relateObject}" id="form-dl-csctName">
					<dt>名称姓名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csctNameYesNot=="not"?"checked-not":""}${data.csctNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csctNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csctName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************名称姓名字段的查询代码结束*****************")}
			
			
			${after$csctName}
			 	
			 ${before$csctMember}
			 
			 ${lz:set("注释","*****************关联会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csctMember" : (lz:join(relateObject,"$csctMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csctMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csctMember || #request.defines['csctMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csctMember":relateObject}" id="form-dl-csctMember">
					<dt>关联会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csctMemberYesNot=="not"?"checked-not":""}${data.csctMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csctMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csctHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csctMember).csmName}" value="${data.csctMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csctMember']>0">
				${lz:set("注释","****当关联会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csctMember")}
			  	<input type="hidden" name="csctMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csctMember}
			 	
			 ${before$csctFlag}
			 
			 ${lz:set("注释","*****************会话标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csctFlag" : (lz:join(relateObject,"$csctFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".csctFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csctFlag || #request.defines['csctFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csctFlag":relateObject}" id="form-dl-csctFlag">
					<dt>会话标识：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csctFlagYesNot=="not"?"checked-not":""}${data.csctFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csctFlagYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csctFlag}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************会话标识字段的查询代码结束*****************")}
			
			
			${after$csctFlag}
			 	
			 ${before$csctPlatform}
			 
			 ${lz:set("注释","*****************来源平台字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csctPlatform" : (lz:join(relateObject,"$csctPlatform")))}
			 ${lz:set("cname",lz:join(relateObject,".csctPlatform"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csctPlatform || #request.defines['csctPlatform']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csctPlatform":relateObject}" id="form-dl-csctPlatform">
					<dt>来源平台：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csctPlatformYesNot=="not"?"checked-not":""}${data.csctPlatformYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csctPlatformYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csctPlatform==0?"selected":""}>后台</option>
							<option value="1" ${data.csctPlatform==1?"selected":""}>网站</option>
							<option value="2" ${data.csctPlatform==2?"selected":""}>微站</option>
							<option value="3" ${data.csctPlatform==3?"selected":""}>微信</option>
							<option value="4" ${data.csctPlatform==4?"selected":""}>支付宝</option>
							<option value="5" ${data.csctPlatform==5?"selected":""}>短信</option>
							<option value="6" ${data.csctPlatform==6?"selected":""}>QQ</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************来源平台字段的查询代码结束*****************")}
			
			
			${after$csctPlatform}
			 	
			 ${before$csctServicer}
			 
			 ${lz:set("注释","*****************当前客服字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csctServicer" : (lz:join(relateObject,"$csctServicer")))}
			 ${lz:set("cname",lz:join(relateObject,".csctServicer"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csctServicer || #request.defines['csctServicer']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csctServicer":relateObject}" id="form-dl-csctServicer">
					<dt>当前客服：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csctServicerYesNot=="not"?"checked-not":""}${data.csctServicerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csctServicerYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csctServicer}">
				 					${get:SrvUser(data.csctServicer).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************当前客服字段的查询代码结束*****************")}
			
			
			${after$csctServicer}
			 	
			 ${before$csctAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csctAddTime" : (lz:join(relateObject,"$csctAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csctAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csctAddTime || #request.defines['csctAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csctAddTime":relateObject}" id="form-dl-csctAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csctAddTimeYesNot=="not"?"checked-not":""}${data.csctAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csctAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csctAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csctAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csctAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csctAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csctAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csctAddTime}
			 	
			 ${before$csctState}
			 
			 ${lz:set("注释","*****************客服状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csctState" : (lz:join(relateObject,"$csctState")))}
			 ${lz:set("cname",lz:join(relateObject,".csctState"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csctState || #request.defines['csctState']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csctState":relateObject}" id="form-dl-csctState">
					<dt>客服状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csctStateYesNot=="not"?"checked-not":""}${data.csctStateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csctStateYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csctState==0?"selected":""}>排队中</option>
							<option value="1" ${data.csctState==1?"selected":""}>沟通中</option>
							<option value="2" ${data.csctState==2?"selected":""}>已结束</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************客服状态字段的查询代码结束*****************")}
			
			
			${after$csctState}
			 	
			 ${before$csctStatus}
			 
			 ${lz:set("注释","*****************当前状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csctStatus" : (lz:join(relateObject,"$csctStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csctStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csctStatus || #request.defines['csctStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csctStatus":relateObject}" id="form-dl-csctStatus">
					<dt>当前状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csctStatusYesNot=="not"?"checked-not":""}${data.csctStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csctStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csctStatus==1?"selected":""}>在线</option>
							<option value="0" ${data.csctStatus==0?"selected":""}>不在线</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************当前状态字段的查询代码结束*****************")}
			
			
			${after$csctStatus}