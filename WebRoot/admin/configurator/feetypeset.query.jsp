<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csftsId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csftsId" : (lz:join(relateObject,"$csftsId")))}
			 ${lz:set("cname",lz:join(relateObject,".csftsId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csftsId || #request.defines['csftsId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csftsId":relateObject}" id="form-dl-csftsId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csftsIdYesNot=="not"?"checked-not":""}${data.csftsIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csftsIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csftsId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csftsId}
			 	
			 ${before$csftsHost}
			 
			 ${lz:set("注释","*****************运营城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csftsHost" : (lz:join(relateObject,"$csftsHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csftsHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csftsHost || #request.defines['csftsHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csftsHost":relateObject}" id="form-dl-csftsHost">
					<dt>运营城市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csftsHostYesNot=="not"?"checked-not":""}${data.csftsHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csftsHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csftsHost}">
				 					${get:SrvHost(data.csftsHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************运营城市字段的查询代码结束*****************")}
			
			
			${after$csftsHost}
			 	
			 ${before$csftsModel}
			 
			 ${lz:set("注释","*****************适用车型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csftsModel" : (lz:join(relateObject,"$csftsModel")))}
			 ${lz:set("cname",lz:join(relateObject,".csftsModel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csftsModel || #request.defines['csftsModel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csftsModel":relateObject}" id="form-dl-csftsModel">
					<dt>适用车型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csftsModelYesNot=="not"?"checked-not":""}${data.csftsModelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csftsModelYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/carmodel_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csftsModel}">
				 					${get:CsCarModel(data.csftsModel).cscmName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************适用车型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csftsModel']>0">
				${lz:set("注释","****当适用车型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csftsModel")}
			  	<input type="hidden" name="csftsModel$on" id="CsCarModel$on" value="true"/>
			  	<jsp:include page="/admin/object/carmodel.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csftsModel}
			 	
			 ${before$csftsFeeType}
			 
			 ${lz:set("注释","*****************适用计费类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csftsFeeType" : (lz:join(relateObject,"$csftsFeeType")))}
			 ${lz:set("cname",lz:join(relateObject,".csftsFeeType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csftsFeeType || #request.defines['csftsFeeType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csftsFeeType":relateObject}" id="form-dl-csftsFeeType">
					<dt>适用计费类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csftsFeeTypeYesNot=="not"?"checked-not":""}${data.csftsFeeTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csftsFeeTypeYesNot}"/>
		 			<select id="${NAME}" name="${NAME}" >
		 				<option value="">全部</option>
					    ${lz:set("items", lz:query("ccclubs_system","select * from cs_user_type where 1=1") )}
						<s:iterator value="#request.items" id="item" status="i">
						<option value="#${item.csut_id}#" ${lz:idin(data.csftsFeeType,item.csut_id)?"selected":""}>${item.csut_name}</option>
						</s:iterator>
						${lz:set("items",null)}
					</select>
					<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?key='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************适用计费类型字段的查询代码结束*****************")}
			
			
			${after$csftsFeeType}
			 	
			 ${before$csftsDefault}
			 
			 ${lz:set("注释","*****************默认计费类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csftsDefault" : (lz:join(relateObject,"$csftsDefault")))}
			 ${lz:set("cname",lz:join(relateObject,".csftsDefault"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csftsDefault || #request.defines['csftsDefault']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csftsDefault":relateObject}" id="form-dl-csftsDefault">
					<dt>默认计费类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csftsDefaultYesNot=="not"?"checked-not":""}${data.csftsDefaultYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csftsDefaultYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/user/type_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csftsDefault}">
				 					${get:CsUserType(data.csftsDefault).csutName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************默认计费类型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csftsDefault']>0">
				${lz:set("注释","****当默认计费类型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csftsDefault")}
			  	<input type="hidden" name="csftsDefault$on" id="CsUserType$on" value="true"/>
			  	<jsp:include page="/admin/user/type.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csftsDefault}
			 	
			 ${before$csftsOutlets}
			 
			 ${lz:set("注释","*****************默认网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csftsOutlets" : (lz:join(relateObject,"$csftsOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".csftsOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csftsOutlets || #request.defines['csftsOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csftsOutlets":relateObject}" id="form-dl-csftsOutlets">
					<dt>默认网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csftsOutletsYesNot=="not"?"checked-not":""}${data.csftsOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csftsOutletsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={csftsHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csftsOutlets}">
				 					${get:CsOutlets(data.csftsOutlets).csoName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************默认网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csftsOutlets']>0">
				${lz:set("注释","****当默认网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csftsOutlets")}
			  	<input type="hidden" name="csftsOutlets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csftsOutlets}
			 	
			 ${before$csftsStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csftsStatus" : (lz:join(relateObject,"$csftsStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csftsStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csftsStatus || #request.defines['csftsStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csftsStatus":relateObject}" id="form-dl-csftsStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csftsStatusYesNot=="not"?"checked-not":""}${data.csftsStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csftsStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csftsStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csftsStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csftsStatus}