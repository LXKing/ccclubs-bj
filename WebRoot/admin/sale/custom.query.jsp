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
			 
			 ${lz:set("注释","*****************客户名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscName" : (lz:join(relateObject,"$tbscName")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscName || #request.defines['tbscName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscName":relateObject}" id="form-dl-tbscName">
					<dt>客户名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscNameYesNot=="not"?"checked-not":""}${data.tbscNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbscName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************客户名称字段的查询代码结束*****************")}
			
			
			${after$tbscName}
			 	
			 ${before$tbscBattle}
			 
			 ${lz:set("注释","*****************归属战役字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscBattle" : (lz:join(relateObject,"$tbscBattle")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscBattle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscBattle || #request.defines['tbscBattle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscBattle":relateObject}" id="form-dl-tbscBattle">
					<dt>归属战役：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscBattleYesNot=="not"?"checked-not":""}${data.tbscBattleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscBattleYesNot}"/>
			 			<input title="请输入销售战役战役标题进行查询" class="combox" action="${basePath}${proname}/sale/battle_query.do?value={param}&tbsbHost={tbscHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:TbSaleBattle(data.tbscBattle).tbsbTitle}" value="${data.tbscBattle}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/battle_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************归属战役字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbscBattle']>0">
				${lz:set("注释","****当归属战役字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbscBattle")}
			  	<input type="hidden" name="tbscBattle$on" id="TbSaleBattle$on" value="true"/>
			  	<jsp:include page="/admin/sale/battle.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbscBattle}
			 	
			 ${before$tbscEvent}
			 
			 ${lz:set("注释","*****************归属活动字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscEvent" : (lz:join(relateObject,"$tbscEvent")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscEvent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscEvent || #request.defines['tbscEvent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscEvent":relateObject}" id="form-dl-tbscEvent">
					<dt>归属活动：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscEventYesNot=="not"?"checked-not":""}${data.tbscEventYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscEventYesNot}"/>
			 			<input title="请输入销售活动活动主题进行查询" class="combox" action="${basePath}${proname}/sale/event_query.do?value={param}&csseHost={tbscHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsSaleEvent(data.tbscEvent).csseSubject}" value="${data.tbscEvent}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/event_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************归属活动字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbscEvent']>0">
				${lz:set("注释","****当归属活动字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbscEvent")}
			  	<input type="hidden" name="tbscEvent$on" id="CsSaleEvent$on" value="true"/>
			  	<jsp:include page="/admin/sale/event.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbscEvent}
			 	
			 ${before$tbscGroup}
			 
			 ${lz:set("注释","*****************所属组织字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscGroup" : (lz:join(relateObject,"$tbscGroup")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscGroup"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscGroup || #request.defines['tbscGroup']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscGroup":relateObject}" id="form-dl-tbscGroup">
					<dt>所属组织：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscGroupYesNot=="not"?"checked-not":""}${data.tbscGroupYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscGroupYesNot}"/>
			 			<input title="请输入客户组织组织名称进行查询" class="combox" action="${basePath}${proname}/sale/customgroup_query.do?value={param}&tbcgHost={tbscHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:TbCustomGroup(data.tbscGroup).tbcgName}" value="${data.tbscGroup}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/sale/customgroup_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属组织字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['tbscGroup']>0">
				${lz:set("注释","****当所属组织字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","tbscGroup")}
			  	<input type="hidden" name="tbscGroup$on" id="TbCustomGroup$on" value="true"/>
			  	<jsp:include page="/admin/sale/customgroup.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$tbscGroup}
			 	
			 ${before$tbscMobile}
			 
			 ${lz:set("注释","*****************联系手机字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscMobile" : (lz:join(relateObject,"$tbscMobile")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscMobile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscMobile || #request.defines['tbscMobile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscMobile":relateObject}" id="form-dl-tbscMobile">
					<dt>联系手机：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscMobileYesNot=="not"?"checked-not":""}${data.tbscMobileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscMobileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbscMobile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************联系手机字段的查询代码结束*****************")}
			
			
			${after$tbscMobile}
			 	
			 ${before$tbscNumber}
			 
			 ${lz:set("注释","*****************联系电话字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscNumber" : (lz:join(relateObject,"$tbscNumber")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscNumber"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscNumber || #request.defines['tbscNumber']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscNumber":relateObject}" id="form-dl-tbscNumber">
					<dt>联系电话：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscNumberYesNot=="not"?"checked-not":""}${data.tbscNumberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscNumberYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbscNumber}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************联系电话字段的查询代码结束*****************")}
			
			
			${after$tbscNumber}
			 	
			 ${before$tbscEmail}
			 
			 ${lz:set("注释","*****************联系邮箱字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscEmail" : (lz:join(relateObject,"$tbscEmail")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscEmail"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscEmail || #request.defines['tbscEmail']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscEmail":relateObject}" id="form-dl-tbscEmail">
					<dt>联系邮箱：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscEmailYesNot=="not"?"checked-not":""}${data.tbscEmailYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscEmailYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbscEmail}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************联系邮箱字段的查询代码结束*****************")}
			
			
			${after$tbscEmail}
			 	
			 ${before$tbscQq}
			 
			 ${lz:set("注释","*****************联系QQ字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscQq" : (lz:join(relateObject,"$tbscQq")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscQq"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscQq || #request.defines['tbscQq']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscQq":relateObject}" id="form-dl-tbscQq">
					<dt>联系QQ：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscQqYesNot=="not"?"checked-not":""}${data.tbscQqYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscQqYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.tbscQq}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************联系QQ字段的查询代码结束*****************")}
			
			
			${after$tbscQq}
			 	
			 ${before$tbscProfile}
			 
			 ${lz:set("注释","*****************业务方向字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscProfile" : (lz:join(relateObject,"$tbscProfile")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscProfile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscProfile || #request.defines['tbscProfile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscProfile":relateObject}" id="form-dl-tbscProfile">
					<dt>业务方向：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscProfileYesNot=="not"?"checked-not":""}${data.tbscProfileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscProfileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="64" name="${NAME}" id="${NAME}"  value="${data.tbscProfile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************业务方向字段的查询代码结束*****************")}
			
			
			${after$tbscProfile}
			 	
			 ${before$tbscLevel}
			 
			 ${lz:set("注释","*****************会员级别字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscLevel" : (lz:join(relateObject,"$tbscLevel")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscLevel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscLevel || #request.defines['tbscLevel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscLevel":relateObject}" id="form-dl-tbscLevel">
					<dt>会员级别：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscLevelYesNot=="not"?"checked-not":""}${data.tbscLevelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscLevelYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.tbscLevel==0?"selected":""}>普通客户</option>
							<option value="1" ${data.tbscLevel==1?"selected":""}>重要客户</option>
							<option value="2" ${data.tbscLevel==2?"selected":""}>集团大客户</option>
							<option value="3" ${data.tbscLevel==3?"selected":""}>政府大客户</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************会员级别字段的查询代码结束*****************")}
			
			
			${after$tbscLevel}
			 	
			 ${before$tbscFormal}
			 
			 ${lz:set("注释","*****************意向阶段字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscFormal" : (lz:join(relateObject,"$tbscFormal")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscFormal"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscFormal || #request.defines['tbscFormal']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscFormal":relateObject}" id="form-dl-tbscFormal">
					<dt>意向阶段：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscFormalYesNot=="not"?"checked-not":""}${data.tbscFormalYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscFormalYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.tbscFormal==0?"selected":""}>暂无意向</option>
							<option value="1" ${data.tbscFormal==1?"selected":""}>意向一般</option>
							<option value="2" ${data.tbscFormal==2?"selected":""}>意向强烈</option>
							<option value="3" ${data.tbscFormal==3?"selected":""}>售前跟踪</option>
							<option value="4" ${data.tbscFormal==4?"selected":""}>合同执行</option>
							<option value="5" ${data.tbscFormal==5?"selected":""}>售后服务</option>
							<option value="6" ${data.tbscFormal==6?"selected":""}>合同期满</option>
							<option value="7" ${data.tbscFormal==7?"selected":""}>失败</option>
							<option value="8" ${data.tbscFormal==8?"selected":""}>搁置</option>
							<option value="9" ${data.tbscFormal==9?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************意向阶段字段的查询代码结束*****************")}
			
			
			${after$tbscFormal}
			 	
			 ${before$tbscServer}
			 
			 ${lz:set("注释","*****************服务阶段字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "tbscServer" : (lz:join(relateObject,"$tbscServer")))}
			 ${lz:set("cname",lz:join(relateObject,".tbscServer"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.tbscServer || #request.defines['tbscServer']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"tbscServer":relateObject}" id="form-dl-tbscServer">
					<dt>服务阶段：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.tbscServerYesNot=="not"?"checked-not":""}${data.tbscServerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.tbscServerYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
					 		 <option value="">无法自动构造</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************服务阶段字段的查询代码结束*****************")}
			
			
			${after$tbscServer}
			 	
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