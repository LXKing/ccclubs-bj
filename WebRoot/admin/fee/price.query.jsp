<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cspId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspId" : (lz:join(relateObject,"$cspId")))}
			 ${lz:set("cname",lz:join(relateObject,".cspId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspId || #request.defines['cspId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspId":relateObject}" id="form-dl-cspId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspIdYesNot=="not"?"checked-not":""}${data.cspIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cspId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cspId}
			 	
			 ${before$cspUserType}
			 
			 ${lz:set("注释","*****************计费类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspUserType" : (lz:join(relateObject,"$cspUserType")))}
			 ${lz:set("cname",lz:join(relateObject,".cspUserType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspUserType || #request.defines['cspUserType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspUserType":relateObject}" id="form-dl-cspUserType">
					<dt>计费类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspUserTypeYesNot=="not"?"checked-not":""}${data.cspUserTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspUserTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items",get:hfts(lz:or(csoHost,get:host())))}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.csutId}" ${data.cspUserType==item.csutId?"selected":""}>${item.csutName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************计费类型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cspUserType']>0">
				${lz:set("注释","****当计费类型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cspUserType")}
			  	<input type="hidden" name="cspUserType$on" id="CsUserType$on" value="true"/>
			  	<jsp:include page="/admin/user/type.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cspUserType}
			 	
			 ${before$cspGoods}
			 
			 ${lz:set("注释","*****************商品字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspGoods" : (lz:join(relateObject,"$cspGoods")))}
			 ${lz:set("cname",lz:join(relateObject,".cspGoods"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspGoods || #request.defines['cspGoods']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspGoods":relateObject}" id="form-dl-cspGoods">
					<dt>商　　品：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspGoodsYesNot=="not"?"checked-not":""}${data.cspGoodsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspGoodsYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", lz:query("ccclubs_system","select * from cs_goods where 1=1 ") )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.csgId}" ${data.cspGoods==item.csgId?"selected":""}>${item.csgName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/goods_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************商品字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cspGoods']>0">
				${lz:set("注释","****当商品字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cspGoods")}
			  	<input type="hidden" name="cspGoods$on" id="CsGoods$on" value="true"/>
			  	<jsp:include page="/admin/fee/goods.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cspGoods}
			 	
			 ${before$cspOutets}
			 
			 ${lz:set("注释","*****************网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspOutets" : (lz:join(relateObject,"$cspOutets")))}
			 ${lz:set("cname",lz:join(relateObject,".cspOutets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspOutets || #request.defines['cspOutets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspOutets":relateObject}" id="form-dl-cspOutets">
					<dt>网　　点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspOutetsYesNot=="not"?"checked-not":""}${data.cspOutetsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspOutetsYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							    ${lz:set("items", lz:query$("ccclubs_system","select * from cs_outlets where cso_host=?" ,lz:or(csoHost,get:host())) )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.csoId}" ${data.cspOutets==item.csoId?"selected":""}>${item.csoName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cspOutets']>0">
				${lz:set("注释","****当网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cspOutets")}
			  	<input type="hidden" name="cspOutets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cspOutets}
			 	
			 ${before$cspModel}
			 
			 ${lz:set("注释","*****************车型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspModel" : (lz:join(relateObject,"$cspModel")))}
			 ${lz:set("cname",lz:join(relateObject,".cspModel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspModel || #request.defines['cspModel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspModel":relateObject}" id="form-dl-cspModel">
					<dt>车　　型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspModelYesNot=="not"?"checked-not":""}${data.cspModelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspModelYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items",get:hms(lz:or(csoHost,get:host())))}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.cscmId}" ${data.cspModel==item.cscmId?"selected":""}>${item.cscmName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cspModel']>0">
				${lz:set("注释","****当车型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cspModel")}
			  	<input type="hidden" name="cspModel$on" id="CsCarModel$on" value="true"/>
			  	<jsp:include page="/admin/object/carmodel.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cspModel}