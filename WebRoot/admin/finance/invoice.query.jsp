<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csiId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiId" : (lz:join(relateObject,"$csiId")))}
			 ${lz:set("cname",lz:join(relateObject,".csiId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiId || #request.defines['csiId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiId":relateObject}" id="form-dl-csiId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiIdYesNot=="not"?"checked-not":""}${data.csiIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csiId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csiId}
			 	
			 ${before$csiHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiHost" : (lz:join(relateObject,"$csiHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csiHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiHost || #request.defines['csiHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiHost":relateObject}" id="form-dl-csiHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiHostYesNot=="not"?"checked-not":""}${data.csiHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csiHost}">
				 					${get:SrvHost(data.csiHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csiHost}
			 	
			 ${before$csiTitle}
			 
			 ${lz:set("注释","*****************抬头名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiTitle" : (lz:join(relateObject,"$csiTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".csiTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiTitle || #request.defines['csiTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiTitle":relateObject}" id="form-dl-csiTitle">
					<dt>抬头名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiTitleYesNot=="not"?"checked-not":""}${data.csiTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csiTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************抬头名称字段的查询代码结束*****************")}
			
			
			${after$csiTitle}
			 	
			 ${before$csiContent}
			 
			 ${lz:set("注释","*****************开票内容字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiContent" : (lz:join(relateObject,"$csiContent")))}
			 ${lz:set("cname",lz:join(relateObject,".csiContent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiContent || #request.defines['csiContent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiContent":relateObject}" id="form-dl-csiContent">
					<dt>开票内容：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiContentYesNot=="not"?"checked-not":""}${data.csiContentYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiContentYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", get:propertys("发票开票内容","invoice_content"))}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.spId}" ${data.csiContent==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************开票内容字段的查询代码结束*****************")}
			
			
			${after$csiContent}
			 	
			 ${before$csiMember}
			 
			 ${lz:set("注释","*****************所属会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiMember" : (lz:join(relateObject,"$csiMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csiMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiMember || #request.defines['csiMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiMember":relateObject}" id="form-dl-csiMember">
					<dt>所属会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiMemberYesNot=="not"?"checked-not":""}${data.csiMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csiHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csiMember).csmName}" value="${data.csiMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csiMember']>0">
				${lz:set("注释","****当所属会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csiMember")}
			  	<input type="hidden" name="csiMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csiMember}
			 	
			 ${before$csiSendType}
			 
			 ${lz:set("注释","*****************寄送方式字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiSendType" : (lz:join(relateObject,"$csiSendType")))}
			 ${lz:set("cname",lz:join(relateObject,".csiSendType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiSendType || #request.defines['csiSendType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiSendType":relateObject}" id="form-dl-csiSendType">
					<dt>寄送方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiSendTypeYesNot=="not"?"checked-not":""}${data.csiSendTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiSendTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csiSendType==0?"selected":""}>快递</option>
							<option value="1" ${data.csiSendType==1?"selected":""}>上门取件</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************寄送方式字段的查询代码结束*****************")}
			
			
			${after$csiSendType}
			 	
			 ${before$csiAddress}
			 
			 ${lz:set("注释","*****************寄送地址字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiAddress" : (lz:join(relateObject,"$csiAddress")))}
			 ${lz:set("cname",lz:join(relateObject,".csiAddress"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiAddress || #request.defines['csiAddress']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiAddress":relateObject}" id="form-dl-csiAddress">
					<dt>寄送地址：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiAddressYesNot=="not"?"checked-not":""}${data.csiAddressYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiAddressYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/user/address_query.do?size=-1&csaMember={csiMember}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csiAddress}">
				 					${get:CsAddress(data.csiAddress).csaAddress}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/address_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************寄送地址字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csiAddress']>0">
				${lz:set("注释","****当寄送地址字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csiAddress")}
			  	<input type="hidden" name="csiAddress$on" id="CsAddress$on" value="true"/>
			  	<jsp:include page="/admin/user/address.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csiAddress}
			 	
			 ${before$csiChecker}
			 
			 ${lz:set("注释","*****************审核人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiChecker" : (lz:join(relateObject,"$csiChecker")))}
			 ${lz:set("cname",lz:join(relateObject,".csiChecker"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiChecker || #request.defines['csiChecker']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiChecker":relateObject}" id="form-dl-csiChecker">
					<dt>审&nbsp;&nbsp;核&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiCheckerYesNot=="not"?"checked-not":""}${data.csiCheckerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiCheckerYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csiChecker}">
				 					${get:SrvUser(data.csiChecker).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************审核人字段的查询代码结束*****************")}
			
			
			${after$csiChecker}
			 	
			 ${before$csiEditor}
			 
			 ${lz:set("注释","*****************开票人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiEditor" : (lz:join(relateObject,"$csiEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".csiEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiEditor || #request.defines['csiEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiEditor":relateObject}" id="form-dl-csiEditor">
					<dt>开&nbsp;&nbsp;票&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiEditorYesNot=="not"?"checked-not":""}${data.csiEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csiEditor}">
				 					${get:SrvUser(data.csiEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************开票人字段的查询代码结束*****************")}
			
			
			${after$csiEditor}
			 	
			 ${before$csiAdder}
			 
			 ${lz:set("注释","*****************添加人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiAdder" : (lz:join(relateObject,"$csiAdder")))}
			 ${lz:set("cname",lz:join(relateObject,".csiAdder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiAdder || #request.defines['csiAdder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiAdder":relateObject}" id="form-dl-csiAdder">
					<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiAdderYesNot=="not"?"checked-not":""}${data.csiAdderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiAdderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csiAdder}">
				 					${get:SrvUser(data.csiAdder).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人字段的查询代码结束*****************")}
			
			
			${after$csiAdder}
			 	
			 ${before$csiCheckTime}
			 
			 ${lz:set("注释","*****************审核时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiCheckTime" : (lz:join(relateObject,"$csiCheckTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csiCheckTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiCheckTime || #request.defines['csiCheckTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiCheckTime":relateObject}" id="form-dl-csiCheckTime">
					<dt>审核时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiCheckTimeYesNot=="not"?"checked-not":""}${data.csiCheckTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiCheckTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csiCheckTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csiCheckTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csiCheckTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csiCheckTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>审核时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csiCheckTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************审核时间字段的查询代码结束*****************")}
			
			
			${after$csiCheckTime}
			 	
			 ${before$csiInvoiceTime}
			 
			 ${lz:set("注释","*****************开票时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiInvoiceTime" : (lz:join(relateObject,"$csiInvoiceTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csiInvoiceTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiInvoiceTime || #request.defines['csiInvoiceTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiInvoiceTime":relateObject}" id="form-dl-csiInvoiceTime">
					<dt>开票时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiInvoiceTimeYesNot=="not"?"checked-not":""}${data.csiInvoiceTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiInvoiceTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csiInvoiceTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csiInvoiceTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csiInvoiceTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csiInvoiceTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>开票时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csiInvoiceTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************开票时间字段的查询代码结束*****************")}
			
			
			${after$csiInvoiceTime}
			 	
			 ${before$csiUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiUpdateTime" : (lz:join(relateObject,"$csiUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csiUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiUpdateTime || #request.defines['csiUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiUpdateTime":relateObject}" id="form-dl-csiUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiUpdateTimeYesNot=="not"?"checked-not":""}${data.csiUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csiUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csiUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csiUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csiUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csiUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csiUpdateTime}
			 	
			 ${before$csiAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiAddTime" : (lz:join(relateObject,"$csiAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csiAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiAddTime || #request.defines['csiAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiAddTime":relateObject}" id="form-dl-csiAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiAddTimeYesNot=="not"?"checked-not":""}${data.csiAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csiAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csiAddTime}
			 	
			 ${before$csiStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiStatus" : (lz:join(relateObject,"$csiStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csiStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiStatus || #request.defines['csiStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiStatus":relateObject}" id="form-dl-csiStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiStatusYesNot=="not"?"checked-not":""}${data.csiStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csiStatus==0?"selected":""}>待审核</option>
							<option value="1" ${data.csiStatus==1?"selected":""}>已审核待开票</option>
							<option value="2" ${data.csiStatus==2?"selected":""}>已开票</option>
							<option value="3" ${data.csiStatus==3?"selected":""}>审核未通过</option>
							<option value="4" ${data.csiStatus==4?"selected":""}>取消</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csiStatus}