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
			 
			 ${lz:set("注释","*****************运营地区字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrHost" : (lz:join(relateObject,"$csrHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csrHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrHost || #request.defines['csrHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrHost":relateObject}" id="form-dl-csrHost">
					<dt>运营地区：</dt>
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
			${lz:set("注释","*****************运营地区字段的查询代码结束*****************")}
			
			
			${after$csrHost}
			 	
			 ${before$csrMember}
			 
			 ${lz:set("注释","*****************退款会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrMember" : (lz:join(relateObject,"$csrMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csrMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrMember || #request.defines['csrMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrMember":relateObject}" id="form-dl-csrMember">
					<dt>退款会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrMemberYesNot=="not"?"checked-not":""}${data.csrMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csrHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csrMember).csmName}" value="${data.csrMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************退款会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrMember']>0">
				${lz:set("注释","****当退款会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrMember")}
			  	<input type="hidden" name="csrMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrMember}
			 	
			 ${before$csrReturnType}
			 
			 ${lz:set("注释","*****************退款方式字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrReturnType" : (lz:join(relateObject,"$csrReturnType")))}
			 ${lz:set("cname",lz:join(relateObject,".csrReturnType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrReturnType || #request.defines['csrReturnType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrReturnType":relateObject}" id="form-dl-csrReturnType">
					<dt>退款方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrReturnTypeYesNot=="not"?"checked-not":""}${data.csrReturnTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrReturnTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csrReturnType==0?"selected":""}>接口退款</option>
							<option value="1" ${data.csrReturnType==1?"selected":""}>支付宝转帐</option>
							<option value="2" ${data.csrReturnType==2?"selected":""}>银行卡转帐</option>
							<option value="3" ${data.csrReturnType==3?"selected":""}>邮局汇款</option>
							<option value="4" ${data.csrReturnType==4?"selected":""}>上门取现</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************退款方式字段的查询代码结束*****************")}
			
			
			${after$csrReturnType}
			 	
			 ${before$csrAccountNo}
			 
			 ${lz:set("注释","*****************收款帐号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrAccountNo" : (lz:join(relateObject,"$csrAccountNo")))}
			 ${lz:set("cname",lz:join(relateObject,".csrAccountNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrAccountNo || #request.defines['csrAccountNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrAccountNo":relateObject}" id="form-dl-csrAccountNo">
					<dt>收款帐号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrAccountNoYesNot=="not"?"checked-not":""}${data.csrAccountNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrAccountNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="64" name="${NAME}" id="${NAME}"  value="${data.csrAccountNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************收款帐号字段的查询代码结束*****************")}
			
			
			${after$csrAccountNo}
			 	
			 ${before$csrAccountName}
			 
			 ${lz:set("注释","*****************收款姓名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrAccountName" : (lz:join(relateObject,"$csrAccountName")))}
			 ${lz:set("cname",lz:join(relateObject,".csrAccountName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrAccountName || #request.defines['csrAccountName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrAccountName":relateObject}" id="form-dl-csrAccountName">
					<dt>收款姓名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrAccountNameYesNot=="not"?"checked-not":""}${data.csrAccountNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrAccountNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csrAccountName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************收款姓名字段的查询代码结束*****************")}
			
			
			${after$csrAccountName}
			 	
			 ${before$csrChecker}
			 
			 ${lz:set("注释","*****************审核人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrChecker" : (lz:join(relateObject,"$csrChecker")))}
			 ${lz:set("cname",lz:join(relateObject,".csrChecker"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrChecker || #request.defines['csrChecker']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrChecker":relateObject}" id="form-dl-csrChecker">
					<dt>审核人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrCheckerYesNot=="not"?"checked-not":""}${data.csrCheckerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrCheckerYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrChecker}">
				 					${get:SrvUser(data.csrChecker).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************审核人员字段的查询代码结束*****************")}
			
			
			${after$csrChecker}
			 	
			 ${before$csrBacker}
			 
			 ${lz:set("注释","*****************退款人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrBacker" : (lz:join(relateObject,"$csrBacker")))}
			 ${lz:set("cname",lz:join(relateObject,".csrBacker"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrBacker || #request.defines['csrBacker']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrBacker":relateObject}" id="form-dl-csrBacker">
					<dt>退款人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrBackerYesNot=="not"?"checked-not":""}${data.csrBackerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrBackerYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrBacker}">
				 					${get:SrvUser(data.csrBacker).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************退款人员字段的查询代码结束*****************")}
			
			
			${after$csrBacker}
			 	
			 ${before$csrAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrAddTime" : (lz:join(relateObject,"$csrAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csrAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrAddTime || #request.defines['csrAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrAddTime":relateObject}" id="form-dl-csrAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrAddTimeYesNot=="not"?"checked-not":""}${data.csrAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csrAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csrAddTime}
			 	
			 ${before$csrStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrStatus" : (lz:join(relateObject,"$csrStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csrStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrStatus || #request.defines['csrStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrStatus":relateObject}" id="form-dl-csrStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrStatusYesNot=="not"?"checked-not":""}${data.csrStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csrStatus==0?"selected":""}>等待审核</option>
							<option value="1" ${data.csrStatus==1?"selected":""}>等待退款</option>
							<option value="2" ${data.csrStatus==2?"selected":""}>退款完成</option>
							<option value="3" ${data.csrStatus==3?"selected":""}>退款失败</option>
							<option value="4" ${data.csrStatus==4?"selected":""}>退款取消</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csrStatus}