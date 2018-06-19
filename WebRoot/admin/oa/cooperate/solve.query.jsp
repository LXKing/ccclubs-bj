<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$tbsId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsId" : (lz:join(relateObject,"$tbsId")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsId || #request.defines['tbsId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsId":relateObject}" id="form-dl-tbsId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsIdYesNot=="not"?"checked-not":""}${data.tbsIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.tbsId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$tbsId}
			 	
			 ${before$tbsHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsHost" : (lz:join(relateObject,"$tbsHost")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsHost || #request.defines['tbsHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsHost":relateObject}" id="form-dl-tbsHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsHostYesNot=="not"?"checked-not":""}${data.tbsHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbsHost}">
				 					${get:SrvHost(data.tbsHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$tbsHost}
			 	
			 ${before$tbsDesc}
			 
			 ${lz:set("注释","*****************处理描述字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsDesc" : (lz:join(relateObject,"$tbsDesc")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsDesc"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsDesc || #request.defines['tbsDesc']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsDesc":relateObject}" id="form-dl-tbsDesc">
					<dt>处理描述：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsDescYesNot=="not"?"checked-not":""}${data.tbsDescYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsDescYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.tbsDesc}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************处理描述字段的查询代码结束*****************")}
			
			
			${after$tbsDesc}
			 	
			 ${before$tbsProplem}
			 
			 ${lz:set("注释","*****************所属问题字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsProplem" : (lz:join(relateObject,"$tbsProplem")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsProplem"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsProplem || #request.defines['tbsProplem']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsProplem":relateObject}" id="form-dl-tbsProplem">
					<dt>所属问题：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsProplemYesNot=="not"?"checked-not":""}${data.tbsProplemYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsProplemYesNot}"/>
			 			<input title="请输入问题故障报修问题描述进行查询" class="combox" action="${basePath}${proname}/oa/cooperate/problem_query.do?value={param}&tbpHost={tbsHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:TbProblem(data.tbsProplem).tbpProfile}" value="${data.tbsProplem}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/oa/cooperate/problem_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属问题字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbsProplem']>0">
				${lz:set("注释","****当所属问题字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbsProplem")}
			  	<input type="hidden" name="tbsProplem$on" id="TbProblem$on" value="true"/>
			  	<jsp:include page="/admin/oa/cooperate/problem.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbsProplem}
			 	
			 ${before$tbsUser}
			 
			 ${lz:set("注释","*****************处理人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsUser" : (lz:join(relateObject,"$tbsUser")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsUser"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsUser || #request.defines['tbsUser']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsUser":relateObject}" id="form-dl-tbsUser">
					<dt>处&nbsp;&nbsp;理&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsUserYesNot=="not"?"checked-not":""}${data.tbsUserYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsUserYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbsUser}">
				 					${get:SrvUser(data.tbsUser).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************处理人字段的查询代码结束*****************")}
			
			
			${after$tbsUser}
			 	
			 ${before$tbsRedirect}
			 
			 ${lz:set("注释","*****************流转到字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsRedirect" : (lz:join(relateObject,"$tbsRedirect")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsRedirect"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsRedirect || #request.defines['tbsRedirect']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsRedirect":relateObject}" id="form-dl-tbsRedirect">
					<dt>流&nbsp;&nbsp;转&nbsp;&nbsp;到：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsRedirectYesNot=="not"?"checked-not":""}${data.tbsRedirectYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsRedirectYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.tbsRedirectMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.tbsRedirectMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************流转到字段的查询代码结束*****************")}
			
			
			${after$tbsRedirect}
			 	
			 ${before$tbsStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbsStatus" : (lz:join(relateObject,"$tbsStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".tbsStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbsStatus || #request.defines['tbsStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbsStatus":relateObject}" id="form-dl-tbsStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbsStatusYesNot=="not"?"checked-not":""}${data.tbsStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbsStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.tbsStatus==0?"selected":""}>未受理</option>
							<option value="1" ${data.tbsStatus==1?"selected":""}>在处理</option>
							<option value="2" ${data.tbsStatus==2?"selected":""}>已流转</option>
							<option value="3" ${data.tbsStatus==3?"selected":""}>已结案</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$tbsStatus}