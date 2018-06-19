<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cscId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscId" : (lz:join(relateObject,"$cscId")))}
			 ${lz:set("cname",lz:join(relateObject,".cscId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscId || #request.defines['cscId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscId":relateObject}" id="form-dl-cscId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscIdYesNot=="not"?"checked-not":""}${data.cscIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cscId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cscId}
			 	
			 ${before$cscHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscHost" : (lz:join(relateObject,"$cscHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cscHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscHost || #request.defines['cscHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscHost":relateObject}" id="form-dl-cscHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscHostYesNot=="not"?"checked-not":""}${data.cscHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscHost}">
				 					${get:SrvHost(data.cscHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cscHost}
			 	
			 ${before$cscNumber}
			 
			 ${lz:set("注释","*****************车牌号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscNumber" : (lz:join(relateObject,"$cscNumber")))}
			 ${lz:set("cname",lz:join(relateObject,".cscNumber"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscNumber || #request.defines['cscNumber']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscNumber":relateObject}" id="form-dl-cscNumber">
					<dt>车&nbsp;&nbsp;牌&nbsp;&nbsp;号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscNumberYesNot=="not"?"checked-not":""}${data.cscNumberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscNumberYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscNumber}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车牌号字段的查询代码结束*****************")}
			
			
			${after$cscNumber}
			 	
			 ${before$cscCarNo}
			 
			 ${lz:set("注释","*****************车牌名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscCarNo" : (lz:join(relateObject,"$cscCarNo")))}
			 ${lz:set("cname",lz:join(relateObject,".cscCarNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscCarNo || #request.defines['cscCarNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscCarNo":relateObject}" id="form-dl-cscCarNo">
					<dt>车牌名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscCarNoYesNot=="not"?"checked-not":""}${data.cscCarNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscCarNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscCarNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车牌名称字段的查询代码结束*****************")}
			
			
			${after$cscCarNo}
			 	
			 ${before$cscModel}
			 
			 ${lz:set("注释","*****************车型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscModel" : (lz:join(relateObject,"$cscModel")))}
			 ${lz:set("cname",lz:join(relateObject,".cscModel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscModel || #request.defines['cscModel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscModel":relateObject}" id="form-dl-cscModel">
					<dt>车　　型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscModelYesNot=="not"?"checked-not":""}${data.cscModelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscModelYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/carmodel_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscModel}">
				 					${get:CsCarModel(data.cscModel).cscmName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscModel']>0">
				${lz:set("注释","****当车型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscModel")}
			  	<input type="hidden" name="cscModel$on" id="CsCarModel$on" value="true"/>
			  	<jsp:include page="/admin/object/carmodel.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscModel}
			 	
			 ${before$cscOutlets}
			 
			 ${lz:set("注释","*****************网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscOutlets" : (lz:join(relateObject,"$cscOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".cscOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscOutlets || #request.defines['cscOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscOutlets":relateObject}" id="form-dl-cscOutlets">
					<dt>网　　点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscOutletsYesNot=="not"?"checked-not":""}${data.cscOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscOutletsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={cscHost}&csoHost={cscHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscOutlets}">
				 					${get:CsOutlets(data.cscOutlets).csoName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscOutlets']>0">
				${lz:set("注释","****当网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscOutlets")}
			  	<input type="hidden" name="cscOutlets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscOutlets}
			 	
			 ${before$cscProvid}
			 
			 ${lz:set("注释","*****************供应商字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscProvid" : (lz:join(relateObject,"$cscProvid")))}
			 ${lz:set("cname",lz:join(relateObject,".cscProvid"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscProvid || #request.defines['cscProvid']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscProvid":relateObject}" id="form-dl-cscProvid">
					<dt>供&nbsp;&nbsp;应&nbsp;&nbsp;商：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscProvidYesNot=="not"?"checked-not":""}${data.cscProvidYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscProvidYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", lz:query("ccclubs_system","select * from cs_provid where 1=1 ") )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.cspId}" ${data.cscProvid==item.cspId?"selected":""}>${item.cspName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/provid_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************供应商字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscProvid']>0">
				${lz:set("注释","****当供应商字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscProvid")}
			  	<input type="hidden" name="cscProvid$on" id="CsProvid$on" value="true"/>
			  	<jsp:include page="/admin/configurator/provid.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscProvid}
			 	
			 ${before$cscRebate}
			 
			 ${lz:set("注释","*****************折扣字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscRebate" : (lz:join(relateObject,"$cscRebate")))}
			 ${lz:set("cname",lz:join(relateObject,".cscRebate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscRebate || #request.defines['cscRebate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscRebate":relateObject}" id="form-dl-cscRebate">
					<dt>折　　扣：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscRebateYesNot=="not"?"checked-not":""}${data.cscRebateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscRebateYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.cscRebateMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.cscRebateMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************折扣字段的查询代码结束*****************")}
			
			
			${after$cscRebate}
			 	
			 ${before$cscIsAd}
			 
			 ${lz:set("注释","*****************广告车？字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscIsAd" : (lz:join(relateObject,"$cscIsAd")))}
			 ${lz:set("cname",lz:join(relateObject,".cscIsAd"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscIsAd || #request.defines['cscIsAd']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscIsAd":relateObject}" id="form-dl-cscIsAd">
					<dt>广告车？：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscIsAdYesNot=="not"?"checked-not":""}${data.cscIsAdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscIsAdYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.cscIsAd=='true'?"selected":""}>是</option>
							<option value="false" ${data.cscIsAd=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************广告车？字段的查询代码结束*****************")}
			
			
			${after$cscIsAd}
			 	
			 ${before$cscMobile}
			 
			 ${lz:set("注释","*****************手机号码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscMobile" : (lz:join(relateObject,"$cscMobile")))}
			 ${lz:set("cname",lz:join(relateObject,".cscMobile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscMobile || #request.defines['cscMobile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscMobile":relateObject}" id="form-dl-cscMobile">
					<dt>手机号码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscMobileYesNot=="not"?"checked-not":""}${data.cscMobileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscMobileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscMobile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************手机号码字段的查询代码结束*****************")}
			
			
			${after$cscMobile}
			 	
			 ${before$cscOilCard}
			 
			 ${lz:set("注释","*****************加油卡号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscOilCard" : (lz:join(relateObject,"$cscOilCard")))}
			 ${lz:set("cname",lz:join(relateObject,".cscOilCard"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscOilCard || #request.defines['cscOilCard']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscOilCard":relateObject}" id="form-dl-cscOilCard">
					<dt>加油卡号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscOilCardYesNot=="not"?"checked-not":""}${data.cscOilCardYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscOilCardYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscOilCard}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************加油卡号字段的查询代码结束*****************")}
			
			
			${after$cscOilCard}
			 	
			 ${before$cscVin}
			 
			 ${lz:set("注释","*****************车架号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscVin" : (lz:join(relateObject,"$cscVin")))}
			 ${lz:set("cname",lz:join(relateObject,".cscVin"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscVin || #request.defines['cscVin']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscVin":relateObject}" id="form-dl-cscVin">
					<dt>车&nbsp;&nbsp;架&nbsp;&nbsp;号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscVinYesNot=="not"?"checked-not":""}${data.cscVinYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscVinYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscVin}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车架号字段的查询代码结束*****************")}
			
			
			${after$cscVin}
			 	
			 ${before$cscEngineNo}
			 
			 ${lz:set("注释","*****************发动机号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscEngineNo" : (lz:join(relateObject,"$cscEngineNo")))}
			 ${lz:set("cname",lz:join(relateObject,".cscEngineNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscEngineNo || #request.defines['cscEngineNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscEngineNo":relateObject}" id="form-dl-cscEngineNo">
					<dt>发动机号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscEngineNoYesNot=="not"?"checked-not":""}${data.cscEngineNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscEngineNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscEngineNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发动机号字段的查询代码结束*****************")}
			
			
			${after$cscEngineNo}
			 	
			 ${before$cscBuyDate}
			 
			 ${lz:set("注释","*****************购车日字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscBuyDate" : (lz:join(relateObject,"$cscBuyDate")))}
			 ${lz:set("cname",lz:join(relateObject,".cscBuyDate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscBuyDate || #request.defines['cscBuyDate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscBuyDate":relateObject}" id="form-dl-cscBuyDate">
					<dt>购&nbsp;&nbsp;车&nbsp;&nbsp;日：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscBuyDateYesNot=="not"?"checked-not":""}${data.cscBuyDateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscBuyDateYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscBuyDateStart,"yyyy-MM-dd")}" end="${lz:date(data.cscBuyDateEnd,"yyyy-MM-dd")}" exp="${data.cscBuyDateExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscBuyDateStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>购车日：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscBuyDateEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************购车日字段的查询代码结束*****************")}
			
			
			${after$cscBuyDate}
			 	
			 ${before$cscBargainNo}
			 
			 ${lz:set("注释","*****************合同号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscBargainNo" : (lz:join(relateObject,"$cscBargainNo")))}
			 ${lz:set("cname",lz:join(relateObject,".cscBargainNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscBargainNo || #request.defines['cscBargainNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscBargainNo":relateObject}" id="form-dl-cscBargainNo">
					<dt>合&nbsp;&nbsp;同&nbsp;&nbsp;号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscBargainNoYesNot=="not"?"checked-not":""}${data.cscBargainNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscBargainNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscBargainNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************合同号字段的查询代码结束*****************")}
			
			
			${after$cscBargainNo}
			 	
			 ${before$cscCertific}
			 
			 ${lz:set("注释","*****************合格证号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscCertific" : (lz:join(relateObject,"$cscCertific")))}
			 ${lz:set("cname",lz:join(relateObject,".cscCertific"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscCertific || #request.defines['cscCertific']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscCertific":relateObject}" id="form-dl-cscCertific">
					<dt>合格证号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscCertificYesNot=="not"?"checked-not":""}${data.cscCertificYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscCertificYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscCertific}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************合格证号字段的查询代码结束*****************")}
			
			
			${after$cscCertific}
			 	
			 ${before$cscFactoryNo}
			 
			 ${lz:set("注释","*****************厂牌型号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscFactoryNo" : (lz:join(relateObject,"$cscFactoryNo")))}
			 ${lz:set("cname",lz:join(relateObject,".cscFactoryNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscFactoryNo || #request.defines['cscFactoryNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscFactoryNo":relateObject}" id="form-dl-cscFactoryNo">
					<dt>厂牌型号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscFactoryNoYesNot=="not"?"checked-not":""}${data.cscFactoryNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscFactoryNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscFactoryNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************厂牌型号字段的查询代码结束*****************")}
			
			
			${after$cscFactoryNo}
			 	
			 ${before$cscColor}
			 
			 ${lz:set("注释","*****************颜色字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscColor" : (lz:join(relateObject,"$cscColor")))}
			 ${lz:set("cname",lz:join(relateObject,".cscColor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscColor || #request.defines['cscColor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscColor":relateObject}" id="form-dl-cscColor">
					<dt>颜　　色：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscColorYesNot=="not"?"checked-not":""}${data.cscColorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscColorYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cscColor==0?"selected":""}>黑色</option>
							<option value="1" ${data.cscColor==1?"selected":""}>白色</option>
							<option value="2" ${data.cscColor==2?"selected":""}>红色</option>
							<option value="3" ${data.cscColor==3?"selected":""}>绿色</option>
							<option value="4" ${data.cscColor==4?"selected":""}>黄色</option>
							<option value="5" ${data.cscColor==5?"selected":""}>橙色</option>
							<option value="6" ${data.cscColor==6?"selected":""}>银色</option>
							<option value="7" ${data.cscColor==7?"selected":""}>蓝色</option>
							<option value="8" ${data.cscColor==8?"selected":""}>深蓝</option>
							<option value="9" ${data.cscColor==9?"selected":""}>深灰</option>
							<option value="10" ${data.cscColor==10?"selected":""}>深红</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************颜色字段的查询代码结束*****************")}
			
			
			${after$cscColor}
			 	
			 ${before$cscAnnualPre}
			 
			 ${lz:set("注释","*****************上次年检日期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscAnnualPre" : (lz:join(relateObject,"$cscAnnualPre")))}
			 ${lz:set("cname",lz:join(relateObject,".cscAnnualPre"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscAnnualPre || #request.defines['cscAnnualPre']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscAnnualPre":relateObject}" id="form-dl-cscAnnualPre">
					<dt>上次年检日期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscAnnualPreYesNot=="not"?"checked-not":""}${data.cscAnnualPreYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscAnnualPreYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscAnnualPreStart,"yyyy-MM-dd")}" end="${lz:date(data.cscAnnualPreEnd,"yyyy-MM-dd")}" exp="${data.cscAnnualPreExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscAnnualPreStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>上次年检日期：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscAnnualPreEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************上次年检日期字段的查询代码结束*****************")}
			
			
			${after$cscAnnualPre}
			 	
			 ${before$cscAnnual}
			 
			 ${lz:set("注释","*****************实际年检日期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscAnnual" : (lz:join(relateObject,"$cscAnnual")))}
			 ${lz:set("cname",lz:join(relateObject,".cscAnnual"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscAnnual || #request.defines['cscAnnual']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscAnnual":relateObject}" id="form-dl-cscAnnual">
					<dt>实际年检日期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscAnnualYesNot=="not"?"checked-not":""}${data.cscAnnualYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscAnnualYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscAnnualStart,"yyyy-MM-dd")}" end="${lz:date(data.cscAnnualEnd,"yyyy-MM-dd")}" exp="${data.cscAnnualExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscAnnualStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>实际年检日期：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscAnnualEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************实际年检日期字段的查询代码结束*****************")}
			
			
			${after$cscAnnual}
			 	
			 ${before$cscAnnualNext}
			 
			 ${lz:set("注释","*****************预计下次年检字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscAnnualNext" : (lz:join(relateObject,"$cscAnnualNext")))}
			 ${lz:set("cname",lz:join(relateObject,".cscAnnualNext"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscAnnualNext || #request.defines['cscAnnualNext']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscAnnualNext":relateObject}" id="form-dl-cscAnnualNext">
					<dt>预计下次年检：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscAnnualNextYesNot=="not"?"checked-not":""}${data.cscAnnualNextYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscAnnualNextYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscAnnualNextStart,"yyyy-MM-dd")}" end="${lz:date(data.cscAnnualNextEnd,"yyyy-MM-dd")}" exp="${data.cscAnnualNextExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscAnnualNextStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>预计下次年检：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscAnnualNextEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************预计下次年检字段的查询代码结束*****************")}
			
			
			${after$cscAnnualNext}
			 	
			 ${before$cscTiUnit}
			 
			 ${lz:set("注释","*****************交强险到期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscTiUnit" : (lz:join(relateObject,"$cscTiUnit")))}
			 ${lz:set("cname",lz:join(relateObject,".cscTiUnit"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscTiUnit || #request.defines['cscTiUnit']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscTiUnit":relateObject}" id="form-dl-cscTiUnit">
					<dt>交强险到期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscTiUnitYesNot=="not"?"checked-not":""}${data.cscTiUnitYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscTiUnitYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscTiUnitStart,"yyyy-MM-dd")}" end="${lz:date(data.cscTiUnitEnd,"yyyy-MM-dd")}" exp="${data.cscTiUnitExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscTiUnitStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>交强险到期：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscTiUnitEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************交强险到期字段的查询代码结束*****************")}
			
			
			${after$cscTiUnit}
			 	
			 ${before$cscTiNo}
			 
			 ${lz:set("注释","*****************交强险保单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscTiNo" : (lz:join(relateObject,"$cscTiNo")))}
			 ${lz:set("cname",lz:join(relateObject,".cscTiNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscTiNo || #request.defines['cscTiNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscTiNo":relateObject}" id="form-dl-cscTiNo">
					<dt>交强险保单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscTiNoYesNot=="not"?"checked-not":""}${data.cscTiNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscTiNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscTiNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************交强险保单字段的查询代码结束*****************")}
			
			
			${after$cscTiNo}
			 	
			 ${before$cscBiUnit}
			 
			 ${lz:set("注释","*****************商业险到期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscBiUnit" : (lz:join(relateObject,"$cscBiUnit")))}
			 ${lz:set("cname",lz:join(relateObject,".cscBiUnit"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscBiUnit || #request.defines['cscBiUnit']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscBiUnit":relateObject}" id="form-dl-cscBiUnit">
					<dt>商业险到期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscBiUnitYesNot=="not"?"checked-not":""}${data.cscBiUnitYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscBiUnitYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscBiUnitStart,"yyyy-MM-dd")}" end="${lz:date(data.cscBiUnitEnd,"yyyy-MM-dd")}" exp="${data.cscBiUnitExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscBiUnitStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>商业险到期：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscBiUnitEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************商业险到期字段的查询代码结束*****************")}
			
			
			${after$cscBiUnit}
			 	
			 ${before$cscBiType}
			 
			 ${lz:set("注释","*****************商业险类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscBiType" : (lz:join(relateObject,"$cscBiType")))}
			 ${lz:set("cname",lz:join(relateObject,".cscBiType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscBiType || #request.defines['cscBiType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscBiType":relateObject}" id="form-dl-cscBiType">
					<dt>商业险类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscBiTypeYesNot=="not"?"checked-not":""}${data.cscBiTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscBiTypeYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.cscBiType}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************商业险类型字段的查询代码结束*****************")}
			
			
			${after$cscBiType}
			 	
			 ${before$cscEditor}
			 
			 ${lz:set("注释","*****************修改人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscEditor" : (lz:join(relateObject,"$cscEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".cscEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscEditor || #request.defines['cscEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscEditor":relateObject}" id="form-dl-cscEditor">
					<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscEditorYesNot=="not"?"checked-not":""}${data.cscEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscEditor}">
				 					${get:SrvUser(data.cscEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改人字段的查询代码结束*****************")}
			
			
			${after$cscEditor}
			 	
			 ${before$cscUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscUpdateTime" : (lz:join(relateObject,"$cscUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscUpdateTime || #request.defines['cscUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscUpdateTime":relateObject}" id="form-dl-cscUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscUpdateTimeYesNot=="not"?"checked-not":""}${data.cscUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cscUpdateTime}
			 	
			 ${before$cscAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscAddTime" : (lz:join(relateObject,"$cscAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscAddTime || #request.defines['cscAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscAddTime":relateObject}" id="form-dl-cscAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscAddTimeYesNot=="not"?"checked-not":""}${data.cscAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cscAddTime}
			 	
			 ${before$cscRemark}
			 
			 ${lz:set("注释","*****************备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscRemark" : (lz:join(relateObject,"$cscRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".cscRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscRemark || #request.defines['cscRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscRemark":relateObject}" id="form-dl-cscRemark">
					<dt>备　　注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscRemarkYesNot=="not"?"checked-not":""}${data.cscRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.cscRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注字段的查询代码结束*****************")}
			
			
			${after$cscRemark}
			 	
			 ${before$cscMemo}
			 
			 ${lz:set("注释","*****************备忘信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscMemo" : (lz:join(relateObject,"$cscMemo")))}
			 ${lz:set("cname",lz:join(relateObject,".cscMemo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscMemo || #request.defines['cscMemo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscMemo":relateObject}" id="form-dl-cscMemo">
					<dt>备忘信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscMemoYesNot=="not"?"checked-not":""}${data.cscMemoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscMemoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.cscMemo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备忘信息字段的查询代码结束*****************")}
			
			
			${after$cscMemo}
			 	
			 ${before$cscOnPsd}
			 
			 ${lz:set("注释","*****************上线密码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscOnPsd" : (lz:join(relateObject,"$cscOnPsd")))}
			 ${lz:set("cname",lz:join(relateObject,".cscOnPsd"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscOnPsd || #request.defines['cscOnPsd']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscOnPsd":relateObject}" id="form-dl-cscOnPsd">
					<dt>上线密码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscOnPsdYesNot=="not"?"checked-not":""}${data.cscOnPsdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscOnPsdYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscOnPsd}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************上线密码字段的查询代码结束*****************")}
			
			
			${after$cscOnPsd}
			 	
			 ${before$cscSetup}
			 
			 ${lz:set("注释","*****************车机安装字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscSetup" : (lz:join(relateObject,"$cscSetup")))}
			 ${lz:set("cname",lz:join(relateObject,".cscSetup"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscSetup || #request.defines['cscSetup']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscSetup":relateObject}" id="form-dl-cscSetup">
					<dt>车机安装：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscSetupYesNot=="not"?"checked-not":""}${data.cscSetupYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscSetupYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cscSetup==0?"selected":""}>未安装</option>
							<option value="1" ${data.cscSetup==1?"selected":""}>正在安装</option>
							<option value="2" ${data.cscSetup==2?"selected":""}>已安装</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车机安装字段的查询代码结束*****************")}
			
			
			${after$cscSetup}
			 	
			 ${before$cscDeviceVesion}
			 
			 ${lz:set("注释","*****************下位机版本字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscDeviceVesion" : (lz:join(relateObject,"$cscDeviceVesion")))}
			 ${lz:set("cname",lz:join(relateObject,".cscDeviceVesion"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscDeviceVesion || #request.defines['cscDeviceVesion']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscDeviceVesion":relateObject}" id="form-dl-cscDeviceVesion">
					<dt>下位机版本：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscDeviceVesionYesNot=="not"?"checked-not":""}${data.cscDeviceVesionYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscDeviceVesionYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cscDeviceVesion==0?"selected":""}>1.0版</option>
							<option value="1" ${data.cscDeviceVesion==1?"selected":""}>2.0版</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************下位机版本字段的查询代码结束*****************")}
			
			
			${after$cscDeviceVesion}
			 	
			 ${before$cscMqttFlag}
			 
			 ${lz:set("注释","*****************服务器标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscMqttFlag" : (lz:join(relateObject,"$cscMqttFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".cscMqttFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscMqttFlag || #request.defines['cscMqttFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscMqttFlag":relateObject}" id="form-dl-cscMqttFlag">
					<dt>服务器标识：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscMqttFlagYesNot=="not"?"checked-not":""}${data.cscMqttFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscMqttFlagYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscMqttFlag}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************服务器标识字段的查询代码结束*****************")}
			
			
			${after$cscMqttFlag}
			 	
			 ${before$cscMask}
			 
			 ${lz:set("注释","*****************程序掩码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscMask" : (lz:join(relateObject,"$cscMask")))}
			 ${lz:set("cname",lz:join(relateObject,".cscMask"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscMask || #request.defines['cscMask']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscMask":relateObject}" id="form-dl-cscMask">
					<dt>程序掩码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscMaskYesNot=="not"?"checked-not":""}${data.cscMaskYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscMaskYesNot}"/>
						<!-- 数值型的checkbox，暂时以select方式查询，后面改成多值查询 -->				
						<select id="${NAME}" name="${NAME}" >
			 				<option value="">全部</option>
							<option value="1" ${lz:bitin(data.cscMask,1)?"selected":""}>假定条件</option>
						</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************程序掩码字段的查询代码结束*****************")}
			
			
			${after$cscMask}
			 	
			 ${before$cscStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscStatus" : (lz:join(relateObject,"$cscStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cscStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscStatus || #request.defines['cscStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscStatus":relateObject}" id="form-dl-cscStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscStatusYesNot=="not"?"checked-not":""}${data.cscStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.cscStatus==1?"selected":""}>上线</option>
							<option value="2" ${data.cscStatus==2?"selected":""}>下线</option>
							<option value="3" ${data.cscStatus==3?"selected":""}>企业可租</option>
							<option value="0" ${data.cscStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cscStatus}