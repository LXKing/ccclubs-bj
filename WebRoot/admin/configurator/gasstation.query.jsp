<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csgsId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgsId" : (lz:join(relateObject,"$csgsId")))}
			 ${lz:set("cname",lz:join(relateObject,".csgsId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgsId || #request.defines['csgsId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgsId":relateObject}" id="form-dl-csgsId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgsIdYesNot=="not"?"checked-not":""}${data.csgsIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgsIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csgsId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csgsId}
			 	
			 ${before$csgsHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgsHost" : (lz:join(relateObject,"$csgsHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csgsHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgsHost || #request.defines['csgsHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgsHost":relateObject}" id="form-dl-csgsHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgsHostYesNot=="not"?"checked-not":""}${data.csgsHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgsHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csgsHost}">
				 					${get:SrvHost(data.csgsHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csgsHost}
			 	
			 ${before$csgsName}
			 
			 ${lz:set("注释","*****************名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgsName" : (lz:join(relateObject,"$csgsName")))}
			 ${lz:set("cname",lz:join(relateObject,".csgsName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgsName || #request.defines['csgsName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgsName":relateObject}" id="form-dl-csgsName">
					<dt>名　　称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgsNameYesNot=="not"?"checked-not":""}${data.csgsNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgsNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csgsName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************名称字段的查询代码结束*****************")}
			
			
			${after$csgsName}
			 	
			 ${before$csgsArea}
			 
			 ${lz:set("注释","*****************所在区域字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgsArea" : (lz:join(relateObject,"$csgsArea")))}
			 ${lz:set("cname",lz:join(relateObject,".csgsArea"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgsArea || #request.defines['csgsArea']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgsArea":relateObject}" id="form-dl-csgsArea">
					<dt>所在区域：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgsAreaYesNot=="not"?"checked-not":""}${data.csgsAreaYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgsAreaYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/configurator/area_query.do?size=-1&csaHost={csgsHost}&csaHost={csgsHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csgsArea}">
				 					${get:CsArea(data.csgsArea).csaName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/area_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所在区域字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csgsArea']>0">
				${lz:set("注释","****当所在区域字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csgsArea")}
			  	<input type="hidden" name="csgsArea$on" id="CsArea$on" value="true"/>
			  	<jsp:include page="/admin/configurator/area.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csgsArea}
			 	
			 ${before$csgsStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgsStatus" : (lz:join(relateObject,"$csgsStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csgsStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgsStatus || #request.defines['csgsStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgsStatus":relateObject}" id="form-dl-csgsStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgsStatusYesNot=="not"?"checked-not":""}${data.csgsStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgsStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csgsStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csgsStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csgsStatus}