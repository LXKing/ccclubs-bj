<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csolId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csolId" : (lz:join(relateObject,"$csolId")))}
			 ${lz:set("cname",lz:join(relateObject,".csolId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csolId || #request.defines['csolId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csolId":relateObject}" id="form-dl-csolId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csolIdYesNot=="not"?"checked-not":""}${data.csolIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csolIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csolId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csolId}
			 	
			 ${before$csolTitle}
			 
			 ${lz:set("注释","*****************操作项目字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csolTitle" : (lz:join(relateObject,"$csolTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".csolTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csolTitle || #request.defines['csolTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csolTitle":relateObject}" id="form-dl-csolTitle">
					<dt>操作项目：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csolTitleYesNot=="not"?"checked-not":""}${data.csolTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csolTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="64" name="${NAME}" id="${NAME}"  value="${data.csolTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作项目字段的查询代码结束*****************")}
			
			
			${after$csolTitle}
			 	
			 ${before$csolOrder}
			 
			 ${lz:set("注释","*****************所属订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csolOrder" : (lz:join(relateObject,"$csolOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csolOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csolOrder || #request.defines['csolOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csolOrder":relateObject}" id="form-dl-csolOrder">
					<dt>所属订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csolOrderYesNot=="not"?"checked-not":""}${data.csolOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csolOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.csolOrder).csoId}" value="${data.csolOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csolOrder']>0">
				${lz:set("注释","****当所属订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csolOrder")}
			  	<input type="hidden" name="csolOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csolOrder}
			 	
			 ${before$csolEditor}
			 
			 ${lz:set("注释","*****************操作人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csolEditor" : (lz:join(relateObject,"$csolEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".csolEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csolEditor || #request.defines['csolEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csolEditor":relateObject}" id="form-dl-csolEditor">
					<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csolEditorYesNot=="not"?"checked-not":""}${data.csolEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csolEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csolEditor}">
				 					${get:SrvUser(data.csolEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作人字段的查询代码结束*****************")}
			
			
			${after$csolEditor}
			 	
			 ${before$csolMember}
			 
			 ${lz:set("注释","*****************操作会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csolMember" : (lz:join(relateObject,"$csolMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csolMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csolMember || #request.defines['csolMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csolMember":relateObject}" id="form-dl-csolMember">
					<dt>操作会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csolMemberYesNot=="not"?"checked-not":""}${data.csolMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csolMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csolMember).csmName}" value="${data.csolMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csolMember']>0">
				${lz:set("注释","****当操作会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csolMember")}
			  	<input type="hidden" name="csolMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csolMember}
			 	
			 ${before$csolSrc}
			 
			 ${lz:set("注释","*****************操作源信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csolSrc" : (lz:join(relateObject,"$csolSrc")))}
			 ${lz:set("cname",lz:join(relateObject,".csolSrc"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csolSrc || #request.defines['csolSrc']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csolSrc":relateObject}" id="form-dl-csolSrc">
					<dt>操作源信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csolSrcYesNot=="not"?"checked-not":""}${data.csolSrcYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csolSrcYesNot}"/>
			 		<input title="请输入动态查询" class="combox" generic="csolSrcGeneric" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:generic(data.csolSrc)}" value="${data.csolSrc}"/>
			 		${lz:set("models",get:models(""))}
				 	<select id="csolSrcGeneric" style="display:none;">
				 		<option value="">--选择模块--</option>
				 		<s:iterator value="#request.models" id="item" status="i">
				 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
				 		</s:iterator>
				 	</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作源信息字段的查询代码结束*****************")}
			
			
			${after$csolSrc}
			 	
			 ${before$csolRemark}
			 
			 ${lz:set("注释","*****************备注信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csolRemark" : (lz:join(relateObject,"$csolRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".csolRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csolRemark || #request.defines['csolRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csolRemark":relateObject}" id="form-dl-csolRemark">
					<dt>备注信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csolRemarkYesNot=="not"?"checked-not":""}${data.csolRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csolRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csolRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注信息字段的查询代码结束*****************")}
			
			
			${after$csolRemark}
			 	
			 ${before$csolAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csolAddTime" : (lz:join(relateObject,"$csolAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csolAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csolAddTime || #request.defines['csolAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csolAddTime":relateObject}" id="form-dl-csolAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csolAddTimeYesNot=="not"?"checked-not":""}${data.csolAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csolAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csolAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csolAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csolAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csolAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csolAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csolAddTime}