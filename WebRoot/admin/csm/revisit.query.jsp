<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csrId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrId" : (lz:join(relateObject,"$csrId")))}
			 ${lz:set("cname",lz:join(relateObject,".csrId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrId || #request.defines['csrId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrId":relateObject}" id="form-dl-csrId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrIdYesNot=="not"?"checked-not":""}${data.csrIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csrId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csrId}
			 	
			 ${before$csrHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrHost" : (lz:join(relateObject,"$csrHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csrHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrHost || #request.defines['csrHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrHost":relateObject}" id="form-dl-csrHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrHostYesNot=="not"?"checked-not":""}${data.csrHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrHost}">
				 					${get:SrvHost(data.csrHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csrHost}
			 	
			 ${before$csrMember}
			 
			 ${lz:set("注释","*****************被访会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrMember" : (lz:join(relateObject,"$csrMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csrMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrMember || #request.defines['csrMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrMember":relateObject}" id="form-dl-csrMember">
					<dt>被访会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrMemberYesNot=="not"?"checked-not":""}${data.csrMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csrHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csrMember).csmName}" value="${data.csrMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************被访会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrMember']>0">
				${lz:set("注释","****当被访会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrMember")}
			  	<input type="hidden" name="csrMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrMember}
			 	
			 ${before$csrVisitor}
			 
			 ${lz:set("注释","*****************回访人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrVisitor" : (lz:join(relateObject,"$csrVisitor")))}
			 ${lz:set("cname",lz:join(relateObject,".csrVisitor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrVisitor || #request.defines['csrVisitor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrVisitor":relateObject}" id="form-dl-csrVisitor">
					<dt>回&nbsp;&nbsp;访&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrVisitorYesNot=="not"?"checked-not":""}${data.csrVisitorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrVisitorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrVisitor}">
				 					${get:SrvUser(data.csrVisitor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************回访人字段的查询代码结束*****************")}
			
			
			${after$csrVisitor}
			 	
			 ${before$csrType}
			 
			 ${lz:set("注释","*****************回访类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrType" : (lz:join(relateObject,"$csrType")))}
			 ${lz:set("cname",lz:join(relateObject,".csrType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrType || #request.defines['csrType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrType":relateObject}" id="form-dl-csrType">
					<dt>回访类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrTypeYesNot=="not"?"checked-not":""}${data.csrTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							    ${lz:set("items", lz:query("ccclubs_system","select * from cs_quest_template where csqt_type=1") )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.csqtId}" ${data.csrType==item.csqtId?"selected":""}>${item.csqtName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/csm/revisit/quest_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************回访类型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrType']>0">
				${lz:set("注释","****当回访类型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrType")}
			  	<input type="hidden" name="csrType$on" id="CsQuestTemplate$on" value="true"/>
			  	<jsp:include page="/admin/csm/revisit/quest.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrType}
			 	
			 ${before$csrRemark}
			 
			 ${lz:set("注释","*****************备注信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrRemark" : (lz:join(relateObject,"$csrRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".csrRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrRemark || #request.defines['csrRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrRemark":relateObject}" id="form-dl-csrRemark">
					<dt>备注信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrRemarkYesNot=="not"?"checked-not":""}${data.csrRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="512" name="${NAME}" id="${NAME}"  value="${data.csrRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注信息字段的查询代码结束*****************")}
			
			
			${after$csrRemark}
			 	
			 ${before$csrUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrUpdateTime" : (lz:join(relateObject,"$csrUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csrUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrUpdateTime || #request.defines['csrUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrUpdateTime":relateObject}" id="form-dl-csrUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrUpdateTimeYesNot=="not"?"checked-not":""}${data.csrUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csrUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csrUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csrUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csrUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csrUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csrUpdateTime}
			 	
			 ${before$csrAddTime}
			 
			 ${lz:set("注释","*****************回访时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrAddTime" : (lz:join(relateObject,"$csrAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csrAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrAddTime || #request.defines['csrAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrAddTime":relateObject}" id="form-dl-csrAddTime">
					<dt>回访时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrAddTimeYesNot=="not"?"checked-not":""}${data.csrAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csrAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>回访时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************回访时间字段的查询代码结束*****************")}
			
			
			${after$csrAddTime}
			 	
			 ${before$csrStatus}
			 
			 ${lz:set("注释","*****************回访状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrStatus" : (lz:join(relateObject,"$csrStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csrStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrStatus || #request.defines['csrStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrStatus":relateObject}" id="form-dl-csrStatus">
					<dt>回访状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrStatusYesNot=="not"?"checked-not":""}${data.csrStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csrStatus==1?"selected":""}>回访完成</option>
							<option value="0" ${data.csrStatus==0?"selected":""}>待处理</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************回访状态字段的查询代码结束*****************")}
			
			
			${after$csrStatus}