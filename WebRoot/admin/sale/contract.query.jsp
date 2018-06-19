<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$tbscId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscId" : (lz:join(relateObject,"$tbscId")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscId || #request.defines['tbscId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscId":relateObject}" id="form-dl-tbscId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscIdYesNot=="not"?"checked-not":""}${data.tbscIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.tbscId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$tbscId}
			 	
			 ${before$tbscHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscHost" : (lz:join(relateObject,"$tbscHost")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscHost || #request.defines['tbscHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscHost":relateObject}" id="form-dl-tbscHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscHostYesNot=="not"?"checked-not":""}${data.tbscHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbscHost}">
				 					${get:SrvHost(data.tbscHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$tbscHost}
			 	
			 ${before$tbscName}
			 
			 ${lz:set("注释","*****************合同名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscName" : (lz:join(relateObject,"$tbscName")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscName || #request.defines['tbscName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscName":relateObject}" id="form-dl-tbscName">
					<dt>合同名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscNameYesNot=="not"?"checked-not":""}${data.tbscNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbscName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************合同名称字段的查询代码结束*****************")}
			
			
			${after$tbscName}
			 	
			 ${before$tbscNo}
			 
			 ${lz:set("注释","*****************合同编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscNo" : (lz:join(relateObject,"$tbscNo")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscNo || #request.defines['tbscNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscNo":relateObject}" id="form-dl-tbscNo">
					<dt>合同编号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscNoYesNot=="not"?"checked-not":""}${data.tbscNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbscNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************合同编号字段的查询代码结束*****************")}
			
			
			${after$tbscNo}
			 	
			 ${before$tbscBattle}
			 
			 ${lz:set("注释","*****************所属战役字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscBattle" : (lz:join(relateObject,"$tbscBattle")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscBattle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscBattle || #request.defines['tbscBattle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscBattle":relateObject}" id="form-dl-tbscBattle">
					<dt>所属战役：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscBattleYesNot=="not"?"checked-not":""}${data.tbscBattleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscBattleYesNot}"/>
			 			非关联对象，不可设置为query类型
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属战役字段的查询代码结束*****************")}
			
			
			${after$tbscBattle}
			 	
			 ${before$tbscEvent}
			 
			 ${lz:set("注释","*****************所属活动字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscEvent" : (lz:join(relateObject,"$tbscEvent")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscEvent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscEvent || #request.defines['tbscEvent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscEvent":relateObject}" id="form-dl-tbscEvent">
					<dt>所属活动：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscEventYesNot=="not"?"checked-not":""}${data.tbscEventYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscEventYesNot}"/>
			 			<input title="请输入销售活动活动主题进行查询" class="combox" action="${basePath}${proname}/sale/event_query.do?value={param}&csseHost={tbscHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsSaleEvent(data.tbscEvent).csseSubject}" value="${data.tbscEvent}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/event_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属活动字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbscEvent']>0">
				${lz:set("注释","****当所属活动字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbscEvent")}
			  	<input type="hidden" name="tbscEvent$on" id="CsSaleEvent$on" value="true"/>
			  	<jsp:include page="/admin/sale/event.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbscEvent}
			 	
			 ${before$tbscOpport}
			 
			 ${lz:set("注释","*****************所属机会字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscOpport" : (lz:join(relateObject,"$tbscOpport")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscOpport"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscOpport || #request.defines['tbscOpport']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscOpport":relateObject}" id="form-dl-tbscOpport">
					<dt>所属机会：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscOpportYesNot=="not"?"checked-not":""}${data.tbscOpportYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscOpportYesNot}"/>
			 			<input title="请输入销售机会机会标题进行查询" class="combox" action="${basePath}${proname}/sale/opport_query.do?value={param}&cssoHost={tbscHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsSaleOpport(data.tbscOpport).cssoTitle}" value="${data.tbscOpport}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/opport_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属机会字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbscOpport']>0">
				${lz:set("注释","****当所属机会字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbscOpport")}
			  	<input type="hidden" name="tbscOpport$on" id="CsSaleOpport$on" value="true"/>
			  	<jsp:include page="/admin/sale/opport.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbscOpport}
			 	
			 ${before$tbscQuote}
			 
			 ${lz:set("注释","*****************关联报价字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscQuote" : (lz:join(relateObject,"$tbscQuote")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscQuote"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscQuote || #request.defines['tbscQuote']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscQuote":relateObject}" id="form-dl-tbscQuote">
					<dt>关联报价：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscQuoteYesNot=="not"?"checked-not":""}${data.tbscQuoteYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscQuoteYesNot}"/>
			 			<input title="请输入跟单报价报价标题进行查询" class="combox" action="${basePath}${proname}/sale/salequote_query.do?value={param}&tbsqHost={tbscHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:TbSaleQuote(data.tbscQuote).tbsqTitle}" value="${data.tbscQuote}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/salequote_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联报价字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbscQuote']>0">
				${lz:set("注释","****当关联报价字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbscQuote")}
			  	<input type="hidden" name="tbscQuote$on" id="TbSaleQuote$on" value="true"/>
			  	<jsp:include page="/admin/sale/salequote.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbscQuote}
			 	
			 ${before$tbscGroup}
			 
			 ${lz:set("注释","*****************签约组织字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscGroup" : (lz:join(relateObject,"$tbscGroup")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscGroup"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscGroup || #request.defines['tbscGroup']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscGroup":relateObject}" id="form-dl-tbscGroup">
					<dt>签约组织：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscGroupYesNot=="not"?"checked-not":""}${data.tbscGroupYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscGroupYesNot}"/>
			 			<input title="请输入客户组织组织名称进行查询" class="combox" action="${basePath}${proname}/sale/customgroup_query.do?value={param}&tbcgHost={tbscHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:TbCustomGroup(data.tbscGroup).tbcgName}" value="${data.tbscGroup}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/customgroup_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************签约组织字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbscGroup']>0">
				${lz:set("注释","****当签约组织字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbscGroup")}
			  	<input type="hidden" name="tbscGroup$on" id="TbCustomGroup$on" value="true"/>
			  	<jsp:include page="/admin/sale/customgroup.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbscGroup}
			 	
			 ${before$tbscCustom}
			 
			 ${lz:set("注释","*****************签约客户字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscCustom" : (lz:join(relateObject,"$tbscCustom")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscCustom"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscCustom || #request.defines['tbscCustom']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscCustom":relateObject}" id="form-dl-tbscCustom">
					<dt>签约客户：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscCustomYesNot=="not"?"checked-not":""}${data.tbscCustomYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscCustomYesNot}"/>
			 			<input title="请输入客户资料客户名称进行查询" class="combox" action="${basePath}${proname}/sale/custom_query.do?value={param}&tbscHost={tbscHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:TbSaleCustom(data.tbscCustom).tbscName}" value="${data.tbscCustom}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/custom_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************签约客户字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbscCustom']>0">
				${lz:set("注释","****当签约客户字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbscCustom")}
			  	<input type="hidden" name="tbscCustom$on" id="TbSaleCustom$on" value="true"/>
			  	<jsp:include page="/admin/sale/custom.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbscCustom}
			 	
			 ${before$tbscSigner}
			 
			 ${lz:set("注释","*****************我方人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscSigner" : (lz:join(relateObject,"$tbscSigner")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscSigner"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscSigner || #request.defines['tbscSigner']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscSigner":relateObject}" id="form-dl-tbscSigner">
					<dt>我方人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscSignerYesNot=="not"?"checked-not":""}${data.tbscSignerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscSignerYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbscSigner}">
				 					${get:SrvUser(data.tbscSigner).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************我方人员字段的查询代码结束*****************")}
			
			
			${after$tbscSigner}
			 	
			 ${before$tbscSignDate}
			 
			 ${lz:set("注释","*****************签约日期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscSignDate" : (lz:join(relateObject,"$tbscSignDate")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscSignDate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscSignDate || #request.defines['tbscSignDate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscSignDate":relateObject}" id="form-dl-tbscSignDate">
					<dt>签约日期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscSignDateYesNot=="not"?"checked-not":""}${data.tbscSignDateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscSignDateYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbscSignDateStart,"yyyy-MM-dd")}" end="${lz:date(data.tbscSignDateEnd,"yyyy-MM-dd")}" exp="${data.tbscSignDateExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbscSignDateStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>签约日期：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbscSignDateEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************签约日期字段的查询代码结束*****************")}
			
			
			${after$tbscSignDate}
			 	
			 ${before$tbscBegin}
			 
			 ${lz:set("注释","*****************合同开始字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscBegin" : (lz:join(relateObject,"$tbscBegin")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscBegin"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscBegin || #request.defines['tbscBegin']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscBegin":relateObject}" id="form-dl-tbscBegin">
					<dt>合同开始：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscBeginYesNot=="not"?"checked-not":""}${data.tbscBeginYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscBeginYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbscBeginStart,"yyyy-MM-dd")}" end="${lz:date(data.tbscBeginEnd,"yyyy-MM-dd")}" exp="${data.tbscBeginExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbscBeginStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>合同开始：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbscBeginEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************合同开始字段的查询代码结束*****************")}
			
			
			${after$tbscBegin}
			 	
			 ${before$tbscEnd}
			 
			 ${lz:set("注释","*****************合同结束字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscEnd" : (lz:join(relateObject,"$tbscEnd")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscEnd"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscEnd || #request.defines['tbscEnd']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscEnd":relateObject}" id="form-dl-tbscEnd">
					<dt>合同结束：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscEndYesNot=="not"?"checked-not":""}${data.tbscEndYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscEndYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbscEndStart,"yyyy-MM-dd")}" end="${lz:date(data.tbscEndEnd,"yyyy-MM-dd")}" exp="${data.tbscEndExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbscEndStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>合同结束：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbscEndEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************合同结束字段的查询代码结束*****************")}
			
			
			${after$tbscEnd}
			 	
			 ${before$tbscOnwer}
			 
			 ${lz:set("注释","*****************所有人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscOnwer" : (lz:join(relateObject,"$tbscOnwer")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscOnwer"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscOnwer || #request.defines['tbscOnwer']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscOnwer":relateObject}" id="form-dl-tbscOnwer">
					<dt>所&nbsp;&nbsp;有&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscOnwerYesNot=="not"?"checked-not":""}${data.tbscOnwerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscOnwerYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbscOnwer}">
				 					${get:SrvUser(data.tbscOnwer).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所有人字段的查询代码结束*****************")}
			
			
			${after$tbscOnwer}
			 	
			 ${before$tbscAdder}
			 
			 ${lz:set("注释","*****************添加人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscAdder" : (lz:join(relateObject,"$tbscAdder")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscAdder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscAdder || #request.defines['tbscAdder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscAdder":relateObject}" id="form-dl-tbscAdder">
					<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscAdderYesNot=="not"?"checked-not":""}${data.tbscAdderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscAdderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbscAdder}">
				 					${get:SrvUser(data.tbscAdder).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人字段的查询代码结束*****************")}
			
			
			${after$tbscAdder}
			 	
			 ${before$tbscEditor}
			 
			 ${lz:set("注释","*****************修改人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscEditor" : (lz:join(relateObject,"$tbscEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscEditor || #request.defines['tbscEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscEditor":relateObject}" id="form-dl-tbscEditor">
					<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscEditorYesNot=="not"?"checked-not":""}${data.tbscEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.tbscEditor}">
				 					${get:SrvUser(data.tbscEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改人字段的查询代码结束*****************")}
			
			
			${after$tbscEditor}
			 	
			 ${before$tbscRemark}
			 
			 ${lz:set("注释","*****************备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscRemark" : (lz:join(relateObject,"$tbscRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscRemark || #request.defines['tbscRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscRemark":relateObject}" id="form-dl-tbscRemark">
					<dt>备　　注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscRemarkYesNot=="not"?"checked-not":""}${data.tbscRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.tbscRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注字段的查询代码结束*****************")}
			
			
			${after$tbscRemark}
			 	
			 ${before$tbscMemo}
			 
			 ${lz:set("注释","*****************备忘信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscMemo" : (lz:join(relateObject,"$tbscMemo")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscMemo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscMemo || #request.defines['tbscMemo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscMemo":relateObject}" id="form-dl-tbscMemo">
					<dt>备忘信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscMemoYesNot=="not"?"checked-not":""}${data.tbscMemoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscMemoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.tbscMemo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备忘信息字段的查询代码结束*****************")}
			
			
			${after$tbscMemo}
			 	
			 ${before$tbscData}
			 
			 ${lz:set("注释","*****************程序数据字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscData" : (lz:join(relateObject,"$tbscData")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscData"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscData || #request.defines['tbscData']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscData":relateObject}" id="form-dl-tbscData">
					<dt>程序数据：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscDataYesNot=="not"?"checked-not":""}${data.tbscDataYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscDataYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.tbscData}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************程序数据字段的查询代码结束*****************")}
			
			
			${after$tbscData}
			 	
			 ${before$tbscUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscUpdateTime" : (lz:join(relateObject,"$tbscUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscUpdateTime || #request.defines['tbscUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscUpdateTime":relateObject}" id="form-dl-tbscUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscUpdateTimeYesNot=="not"?"checked-not":""}${data.tbscUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbscUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbscUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbscUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbscUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbscUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$tbscUpdateTime}
			 	
			 ${before$tbscAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscAddTime" : (lz:join(relateObject,"$tbscAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscAddTime || #request.defines['tbscAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscAddTime":relateObject}" id="form-dl-tbscAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscAddTimeYesNot=="not"?"checked-not":""}${data.tbscAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.tbscAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.tbscAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.tbscAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.tbscAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.tbscAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$tbscAddTime}
			 	
			 ${before$tbscStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscStatus" : (lz:join(relateObject,"$tbscStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscStatus || #request.defines['tbscStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscStatus":relateObject}" id="form-dl-tbscStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscStatusYesNot=="not"?"checked-not":""}${data.tbscStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.tbscStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.tbscStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$tbscStatus}