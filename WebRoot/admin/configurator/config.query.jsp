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
			 	
			 ${before$cscLimitPower}
			 
			 ${lz:set("注释","*****************电量阀值字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscLimitPower" : (lz:join(relateObject,"$cscLimitPower")))}
			 ${lz:set("cname",lz:join(relateObject,".cscLimitPower"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscLimitPower || #request.defines['cscLimitPower']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscLimitPower":relateObject}" id="form-dl-cscLimitPower">
					<dt>电量阀值：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscLimitPowerYesNot=="not"?"checked-not":""}${data.cscLimitPowerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscLimitPowerYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.cscLimitPowerMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.cscLimitPowerMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************电量阀值字段的查询代码结束*****************")}
			
			
			${after$cscLimitPower}
			 	
			 ${before$cscFlags}
			 
			 ${lz:set("注释","*****************可选标记字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscFlags" : (lz:join(relateObject,"$cscFlags")))}
			 ${lz:set("cname",lz:join(relateObject,".cscFlags"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscFlags || #request.defines['cscFlags']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscFlags":relateObject}" id="form-dl-cscFlags">
					<dt>可选标记：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscFlagsYesNot=="not"?"checked-not":""}${data.cscFlagsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscFlagsYesNot}"/>
				 		<select id="${NAME}" name="${NAME}" >
			 				<option value="">全部</option>
							<option value="#0#" ${data.cscFlags=='#0#'?"selected":""}>默认</option>
							<option value="#1#" ${data.cscFlags=='#1#'?"selected":""}>叫车</option>
						</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************可选标记字段的查询代码结束*****************")}
			
			
			${after$cscFlags}
			 	
			 ${before$cscAddress}
			 
			 ${lz:set("注释","*****************联系地址字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscAddress" : (lz:join(relateObject,"$cscAddress")))}
			 ${lz:set("cname",lz:join(relateObject,".cscAddress"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscAddress || #request.defines['cscAddress']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscAddress":relateObject}" id="form-dl-cscAddress">
					<dt>联系地址：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscAddressYesNot=="not"?"checked-not":""}${data.cscAddressYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscAddressYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.cscAddress}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************联系地址字段的查询代码结束*****************")}
			
			
			${after$cscAddress}
			 	
			 ${before$cscSvrNumber}
			 
			 ${lz:set("注释","*****************客服电话字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscSvrNumber" : (lz:join(relateObject,"$cscSvrNumber")))}
			 ${lz:set("cname",lz:join(relateObject,".cscSvrNumber"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscSvrNumber || #request.defines['cscSvrNumber']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscSvrNumber":relateObject}" id="form-dl-cscSvrNumber">
					<dt>客服电话：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscSvrNumberYesNot=="not"?"checked-not":""}${data.cscSvrNumberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscSvrNumberYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscSvrNumber}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************客服电话字段的查询代码结束*****************")}
			
			
			${after$cscSvrNumber}
			 	
			 ${before$cscSrvQqGroup}
			 
			 ${lz:set("注释","*****************QQ群号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscSrvQqGroup" : (lz:join(relateObject,"$cscSrvQqGroup")))}
			 ${lz:set("cname",lz:join(relateObject,".cscSrvQqGroup"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscSrvQqGroup || #request.defines['cscSrvQqGroup']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscSrvQqGroup":relateObject}" id="form-dl-cscSrvQqGroup">
					<dt>QQ群号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscSrvQqGroupYesNot=="not"?"checked-not":""}${data.cscSrvQqGroupYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscSrvQqGroupYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscSrvQqGroup}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************QQ群号字段的查询代码结束*****************")}
			
			
			${after$cscSrvQqGroup}