<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csodId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodId" : (lz:join(relateObject,"$csodId")))}
			 ${lz:set("cname",lz:join(relateObject,".csodId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodId || #request.defines['csodId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodId":relateObject}" id="form-dl-csodId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodIdYesNot=="not"?"checked-not":""}${data.csodIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csodId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csodId}
			 	
			 ${before$csodHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodHost" : (lz:join(relateObject,"$csodHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csodHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodHost || #request.defines['csodHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodHost":relateObject}" id="form-dl-csodHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodHostYesNot=="not"?"checked-not":""}${data.csodHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csodHost}">
				 					${get:SrvHost(data.csodHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csodHost}
			 	
			 ${before$csodOrder}
			 
			 ${lz:set("注释","*****************所属定单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodOrder" : (lz:join(relateObject,"$csodOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csodOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodOrder || #request.defines['csodOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodOrder":relateObject}" id="form-dl-csodOrder">
					<dt>所属定单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodOrderYesNot=="not"?"checked-not":""}${data.csodOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csodHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.csodOrder).csoId}" value="${data.csodOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属定单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csodOrder']>0">
				${lz:set("注释","****当所属定单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csodOrder")}
			  	<input type="hidden" name="csodOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csodOrder}
			 	
			 ${before$csodProduct}
			 
			 ${lz:set("注释","*****************所属产品字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodProduct" : (lz:join(relateObject,"$csodProduct")))}
			 ${lz:set("cname",lz:join(relateObject,".csodProduct"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodProduct || #request.defines['csodProduct']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodProduct":relateObject}" id="form-dl-csodProduct">
					<dt>所属产品：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodProductYesNot=="not"?"checked-not":""}${data.csodProductYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodProductYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/fee/product_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csodProduct}">
				 					${get:CsProduct(data.csodProduct).cspName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/product_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属产品字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csodProduct']>0">
				${lz:set("注释","****当所属产品字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csodProduct")}
			  	<input type="hidden" name="csodProduct$on" id="CsProduct$on" value="true"/>
			  	<jsp:include page="/admin/fee/product.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csodProduct}
			 	
			 ${before$csodGoods}
			 
			 ${lz:set("注释","*****************所属商品字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodGoods" : (lz:join(relateObject,"$csodGoods")))}
			 ${lz:set("cname",lz:join(relateObject,".csodGoods"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodGoods || #request.defines['csodGoods']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodGoods":relateObject}" id="form-dl-csodGoods">
					<dt>所属商品：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodGoodsYesNot=="not"?"checked-not":""}${data.csodGoodsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodGoodsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/fee/goods_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csodGoods}">
				 					${get:CsGoods(data.csodGoods).csgName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/goods_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属商品字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csodGoods']>0">
				${lz:set("注释","****当所属商品字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csodGoods")}
			  	<input type="hidden" name="csodGoods$on" id="CsGoods$on" value="true"/>
			  	<jsp:include page="/admin/fee/goods.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csodGoods}
			 	
			 ${before$csodPricer}
			 
			 ${lz:set("注释","*****************所属价格字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodPricer" : (lz:join(relateObject,"$csodPricer")))}
			 ${lz:set("cname",lz:join(relateObject,".csodPricer"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodPricer || #request.defines['csodPricer']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodPricer":relateObject}" id="form-dl-csodPricer">
					<dt>所属价格：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodPricerYesNot=="not"?"checked-not":""}${data.csodPricerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodPricerYesNot}"/>
			 			<input title="请输入价格编号进行查询" class="combox" action="${basePath}${proname}/fee/price_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsPrice(data.csodPricer).cspId}" value="${data.csodPricer}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/price_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属价格字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csodPricer']>0">
				${lz:set("注释","****当所属价格字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csodPricer")}
			  	<input type="hidden" name="csodPricer$on" id="CsPrice$on" value="true"/>
			  	<jsp:include page="/admin/fee/price.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csodPricer}
			 	
			 ${before$csodOutlets}
			 
			 ${lz:set("注释","*****************所属网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodOutlets" : (lz:join(relateObject,"$csodOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".csodOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodOutlets || #request.defines['csodOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodOutlets":relateObject}" id="form-dl-csodOutlets">
					<dt>所属网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodOutletsYesNot=="not"?"checked-not":""}${data.csodOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodOutletsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_tree.do?parent={param}&csoHost={csodHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csodOutlets}">
				 					${get:CsOutlets(data.csodOutlets).csoName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csodOutlets']>0">
				${lz:set("注释","****当所属网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csodOutlets")}
			  	<input type="hidden" name="csodOutlets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csodOutlets}
			 	
			 ${before$csodModel}
			 
			 ${lz:set("注释","*****************所属车型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodModel" : (lz:join(relateObject,"$csodModel")))}
			 ${lz:set("cname",lz:join(relateObject,".csodModel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodModel || #request.defines['csodModel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodModel":relateObject}" id="form-dl-csodModel">
					<dt>所属车型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodModelYesNot=="not"?"checked-not":""}${data.csodModelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodModelYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/carmodel_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csodModel}">
				 					${get:CsCarModel(data.csodModel).cscmName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属车型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csodModel']>0">
				${lz:set("注释","****当所属车型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csodModel")}
			  	<input type="hidden" name="csodModel$on" id="CsCarModel$on" value="true"/>
			  	<jsp:include page="/admin/object/carmodel.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csodModel}
			 	
			 ${before$csodCar}
			 
			 ${lz:set("注释","*****************所属车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodCar" : (lz:join(relateObject,"$csodCar")))}
			 ${lz:set("cname",lz:join(relateObject,".csodCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodCar || #request.defines['csodCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodCar":relateObject}" id="form-dl-csodCar">
					<dt>所属车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodCarYesNot=="not"?"checked-not":""}${data.csodCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodCarYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/car_query.do?size=-1&cscHost={csodHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csodCar}">
				 					${get:CsCar(data.csodCar).cscNumber}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csodCar']>0">
				${lz:set("注释","****当所属车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csodCar")}
			  	<input type="hidden" name="csodCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csodCar}
			 	
			 ${before$csodProvid}
			 
			 ${lz:set("注释","*****************所属商家字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodProvid" : (lz:join(relateObject,"$csodProvid")))}
			 ${lz:set("cname",lz:join(relateObject,".csodProvid"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodProvid || #request.defines['csodProvid']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodProvid":relateObject}" id="form-dl-csodProvid">
					<dt>所属商家：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodProvidYesNot=="not"?"checked-not":""}${data.csodProvidYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodProvidYesNot}"/>
			 			<input title="请输入供应商名称进行查询" class="combox" action="${basePath}${proname}/configurator/provid_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsProvid(data.csodProvid).cspName}" value="${data.csodProvid}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/provid_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属商家字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csodProvid']>0">
				${lz:set("注释","****当所属商家字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csodProvid")}
			  	<input type="hidden" name="csodProvid$on" id="CsProvid$on" value="true"/>
			  	<jsp:include page="/admin/configurator/provid.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csodProvid}
			 	
			 ${before$csodRule}
			 
			 ${lz:set("注释","*****************单位字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodRule" : (lz:join(relateObject,"$csodRule")))}
			 ${lz:set("cname",lz:join(relateObject,".csodRule"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodRule || #request.defines['csodRule']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodRule":relateObject}" id="form-dl-csodRule">
					<dt>单　　位：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodRuleYesNot=="not"?"checked-not":""}${data.csodRuleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodRuleYesNot}"/>
			 			<input title="请输入规则规则名称进行查询" class="combox" action="${basePath}${proname}/fee/rule_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsRule(data.csodRule).csrName}" value="${data.csodRule}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/rule_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************单位字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csodRule']>0">
				${lz:set("注释","****当单位字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csodRule")}
			  	<input type="hidden" name="csodRule$on" id="CsRule$on" value="true"/>
			  	<jsp:include page="/admin/fee/rule.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csodRule}
			 	
			 ${before$csodRemark}
			 
			 ${lz:set("注释","*****************备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodRemark" : (lz:join(relateObject,"$csodRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".csodRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodRemark || #request.defines['csodRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodRemark":relateObject}" id="form-dl-csodRemark">
					<dt>备　　注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodRemarkYesNot=="not"?"checked-not":""}${data.csodRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csodRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注字段的查询代码结束*****************")}
			
			
			${after$csodRemark}
			 	
			 ${before$csodAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csodAddTime" : (lz:join(relateObject,"$csodAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csodAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csodAddTime || #request.defines['csodAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csodAddTime":relateObject}" id="form-dl-csodAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csodAddTimeYesNot=="not"?"checked-not":""}${data.csodAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csodAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csodAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csodAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csodAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csodAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csodAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csodAddTime}