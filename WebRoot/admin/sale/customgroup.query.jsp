<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$tbcgId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgId" : (lz:join(relateObject,"$tbcgId")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgId || #request.defines['tbcgId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgId":relateObject}" id="form-dl-tbcgId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgIdYesNot=="not"?"checked-not":""}${data.tbcgIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.tbcgId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$tbcgId}
			 	
			 ${before$tbcgHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgHost" : (lz:join(relateObject,"$tbcgHost")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgHost || #request.defines['tbcgHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgHost":relateObject}" id="form-dl-tbcgHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgHostYesNot=="not"?"checked-not":""}${data.tbcgHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbcgHost}">
				 					${get:SrvHost(data.tbcgHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$tbcgHost}
			 	
			 ${before$tbcgBattle}
			 
			 ${lz:set("注释","*****************归属战役字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgBattle" : (lz:join(relateObject,"$tbcgBattle")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgBattle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgBattle || #request.defines['tbcgBattle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgBattle":relateObject}" id="form-dl-tbcgBattle">
					<dt>归属战役：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgBattleYesNot=="not"?"checked-not":""}${data.tbcgBattleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgBattleYesNot}"/>
			 			<input title="请输入销售战役战役标题进行查询" class="combox" action="${basePath}${proname}/sale/battle_query.do?value={param}&tbsbHost={tbcgHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:TbSaleBattle(data.tbcgBattle).tbsbTitle}" value="${data.tbcgBattle}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/battle_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************归属战役字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbcgBattle']>0">
				${lz:set("注释","****当归属战役字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbcgBattle")}
			  	<input type="hidden" name="tbcgBattle$on" id="TbSaleBattle$on" value="true"/>
			  	<jsp:include page="/admin/sale/battle.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbcgBattle}
			 	
			 ${before$tbcgEvent}
			 
			 ${lz:set("注释","*****************归属活动字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgEvent" : (lz:join(relateObject,"$tbcgEvent")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgEvent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgEvent || #request.defines['tbcgEvent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgEvent":relateObject}" id="form-dl-tbcgEvent">
					<dt>归属活动：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgEventYesNot=="not"?"checked-not":""}${data.tbcgEventYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgEventYesNot}"/>
			 			<input title="请输入销售活动活动主题进行查询" class="combox" action="${basePath}${proname}/sale/event_query.do?value={param}&csseHost={tbcgHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsSaleEvent(data.tbcgEvent).csseSubject}" value="${data.tbcgEvent}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/event_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************归属活动字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbcgEvent']>0">
				${lz:set("注释","****当归属活动字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbcgEvent")}
			  	<input type="hidden" name="tbcgEvent$on" id="CsSaleEvent$on" value="true"/>
			  	<jsp:include page="/admin/sale/event.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbcgEvent}
			 	
			 ${before$tbcgName}
			 
			 ${lz:set("注释","*****************组织名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgName" : (lz:join(relateObject,"$tbcgName")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgName || #request.defines['tbcgName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgName":relateObject}" id="form-dl-tbcgName">
					<dt>组织名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgNameYesNot=="not"?"checked-not":""}${data.tbcgNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbcgName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************组织名称字段的查询代码结束*****************")}
			
			
			${after$tbcgName}
			 	
			 ${before$tbcgTrade}
			 
			 ${lz:set("注释","*****************行业字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgTrade" : (lz:join(relateObject,"$tbcgTrade")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgTrade"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgTrade || #request.defines['tbcgTrade']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgTrade":relateObject}" id="form-dl-tbcgTrade">
					<dt>行　　业：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgTradeYesNot=="not"?"checked-not":""}${data.tbcgTradeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgTradeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", get:propertys("客户组织行业","customgroup_trade"))}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.spId}" ${data.tbcgTrade==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************行业字段的查询代码结束*****************")}
			
			
			${after$tbcgTrade}
			 	
			 ${before$tbcgService}
			 
			 ${lz:set("注释","*****************主营业务字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgService" : (lz:join(relateObject,"$tbcgService")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgService"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgService || #request.defines['tbcgService']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgService":relateObject}" id="form-dl-tbcgService">
					<dt>主营业务：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgServiceYesNot=="not"?"checked-not":""}${data.tbcgServiceYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgServiceYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="64" name="${NAME}" id="${NAME}"  value="${data.tbcgService}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************主营业务字段的查询代码结束*****************")}
			
			
			${after$tbcgService}
			 	
			 ${before$tbcgScale}
			 
			 ${lz:set("注释","*****************人员规模字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgScale" : (lz:join(relateObject,"$tbcgScale")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgScale"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgScale || #request.defines['tbcgScale']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgScale":relateObject}" id="form-dl-tbcgScale">
					<dt>人员规模：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgScaleYesNot=="not"?"checked-not":""}${data.tbcgScaleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgScaleYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.tbcgScale==0?"selected":""}>20人以内</option>
							<option value="1" ${data.tbcgScale==1?"selected":""}>20到50人</option>
							<option value="2" ${data.tbcgScale==2?"selected":""}>50到100人</option>
							<option value="3" ${data.tbcgScale==3?"selected":""}>100到200人</option>
							<option value="4" ${data.tbcgScale==4?"selected":""}>200人以上</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************人员规模字段的查询代码结束*****************")}
			
			
			${after$tbcgScale}
			 	
			 ${before$tbcgAddress}
			 
			 ${lz:set("注释","*****************详细地址字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgAddress" : (lz:join(relateObject,"$tbcgAddress")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgAddress"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgAddress || #request.defines['tbcgAddress']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgAddress":relateObject}" id="form-dl-tbcgAddress">
					<dt>详细地址：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgAddressYesNot=="not"?"checked-not":""}${data.tbcgAddressYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgAddressYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.tbcgAddress}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************详细地址字段的查询代码结束*****************")}
			
			
			${after$tbcgAddress}
			 	
			 ${before$tbcgConcact}
			 
			 ${lz:set("注释","*****************联系人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgConcact" : (lz:join(relateObject,"$tbcgConcact")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgConcact"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgConcact || #request.defines['tbcgConcact']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgConcact":relateObject}" id="form-dl-tbcgConcact">
					<dt>联&nbsp;&nbsp;系&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgConcactYesNot=="not"?"checked-not":""}${data.tbcgConcactYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgConcactYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbcgConcact}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************联系人字段的查询代码结束*****************")}
			
			
			${after$tbcgConcact}
			 	
			 ${before$tbcgNumber}
			 
			 ${lz:set("注释","*****************联系电话字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgNumber" : (lz:join(relateObject,"$tbcgNumber")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgNumber"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgNumber || #request.defines['tbcgNumber']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgNumber":relateObject}" id="form-dl-tbcgNumber">
					<dt>联系电话：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgNumberYesNot=="not"?"checked-not":""}${data.tbcgNumberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgNumberYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbcgNumber}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************联系电话字段的查询代码结束*****************")}
			
			
			${after$tbcgNumber}
			 	
			 ${before$tbcgDetail}
			 
			 ${lz:set("注释","*****************备注信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgDetail" : (lz:join(relateObject,"$tbcgDetail")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgDetail"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgDetail || #request.defines['tbcgDetail']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgDetail":relateObject}" id="form-dl-tbcgDetail">
					<dt>备注信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgDetailYesNot=="not"?"checked-not":""}${data.tbcgDetailYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgDetailYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.tbcgDetail}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注信息字段的查询代码结束*****************")}
			
			
			${after$tbcgDetail}
			 	
			 ${before$tbcgOnwer}
			 
			 ${lz:set("注释","*****************所有人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgOnwer" : (lz:join(relateObject,"$tbcgOnwer")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgOnwer"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgOnwer || #request.defines['tbcgOnwer']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgOnwer":relateObject}" id="form-dl-tbcgOnwer">
					<dt>所&nbsp;&nbsp;有&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgOnwerYesNot=="not"?"checked-not":""}${data.tbcgOnwerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgOnwerYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbcgOnwer}">
				 					${get:SrvUser(data.tbcgOnwer).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所有人字段的查询代码结束*****************")}
			
			
			${after$tbcgOnwer}
			 	
			 ${before$tbcgAdder}
			 
			 ${lz:set("注释","*****************添加人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgAdder" : (lz:join(relateObject,"$tbcgAdder")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgAdder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgAdder || #request.defines['tbcgAdder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgAdder":relateObject}" id="form-dl-tbcgAdder">
					<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgAdderYesNot=="not"?"checked-not":""}${data.tbcgAdderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgAdderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbcgAdder}">
				 					${get:SrvUser(data.tbcgAdder).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人字段的查询代码结束*****************")}
			
			
			${after$tbcgAdder}
			 	
			 ${before$tbcgEditor}
			 
			 ${lz:set("注释","*****************修改人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgEditor" : (lz:join(relateObject,"$tbcgEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgEditor || #request.defines['tbcgEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgEditor":relateObject}" id="form-dl-tbcgEditor">
					<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgEditorYesNot=="not"?"checked-not":""}${data.tbcgEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbcgEditor}">
				 					${get:SrvUser(data.tbcgEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改人字段的查询代码结束*****************")}
			
			
			${after$tbcgEditor}
			 	
			 ${before$tbcgRemark}
			 
			 ${lz:set("注释","*****************备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgRemark" : (lz:join(relateObject,"$tbcgRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgRemark || #request.defines['tbcgRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgRemark":relateObject}" id="form-dl-tbcgRemark">
					<dt>备　　注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgRemarkYesNot=="not"?"checked-not":""}${data.tbcgRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.tbcgRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注字段的查询代码结束*****************")}
			
			
			${after$tbcgRemark}
			 	
			 ${before$tbcgMemo}
			 
			 ${lz:set("注释","*****************备忘信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgMemo" : (lz:join(relateObject,"$tbcgMemo")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgMemo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgMemo || #request.defines['tbcgMemo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgMemo":relateObject}" id="form-dl-tbcgMemo">
					<dt>备忘信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgMemoYesNot=="not"?"checked-not":""}${data.tbcgMemoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgMemoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.tbcgMemo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备忘信息字段的查询代码结束*****************")}
			
			
			${after$tbcgMemo}
			 	
			 ${before$tbcgData}
			 
			 ${lz:set("注释","*****************程序数据字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgData" : (lz:join(relateObject,"$tbcgData")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgData"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgData || #request.defines['tbcgData']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgData":relateObject}" id="form-dl-tbcgData">
					<dt>程序数据：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgDataYesNot=="not"?"checked-not":""}${data.tbcgDataYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgDataYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.tbcgData}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************程序数据字段的查询代码结束*****************")}
			
			
			${after$tbcgData}
			 	
			 ${before$tbcgUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgUpdateTime" : (lz:join(relateObject,"$tbcgUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgUpdateTime || #request.defines['tbcgUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgUpdateTime":relateObject}" id="form-dl-tbcgUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgUpdateTimeYesNot=="not"?"checked-not":""}${data.tbcgUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbcgUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbcgUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbcgUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbcgUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbcgUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$tbcgUpdateTime}
			 	
			 ${before$tbcgAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgAddTime" : (lz:join(relateObject,"$tbcgAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgAddTime || #request.defines['tbcgAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgAddTime":relateObject}" id="form-dl-tbcgAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgAddTimeYesNot=="not"?"checked-not":""}${data.tbcgAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbcgAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbcgAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbcgAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbcgAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbcgAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$tbcgAddTime}
			 	
			 ${before$tbcgStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbcgStatus" : (lz:join(relateObject,"$tbcgStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".tbcgStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbcgStatus || #request.defines['tbcgStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbcgStatus":relateObject}" id="form-dl-tbcgStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbcgStatusYesNot=="not"?"checked-not":""}${data.tbcgStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbcgStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.tbcgStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.tbcgStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$tbcgStatus}