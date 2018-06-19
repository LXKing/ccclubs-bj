<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$salId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "salId" : (lz:join(relateObject,"$salId")))}
			 ${lz:set("cname",lz:join(relateObject,".salId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.salId || #request.defines['salId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"salId":relateObject}" id="form-dl-salId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.salIdYesNot=="not"?"checked-not":""}${data.salIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.salIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.salId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$salId}
			 	
			 ${before$salClient}
			 
			 ${lz:set("注释","*****************调用应用字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "salClient" : (lz:join(relateObject,"$salClient")))}
			 ${lz:set("cname",lz:join(relateObject,".salClient"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.salClient || #request.defines['salClient']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"salClient":relateObject}" id="form-dl-salClient">
					<dt>调用应用：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.salClientYesNot=="not"?"checked-not":""}${data.salClientYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.salClientYesNot}"/>
			 			<input title="请输入API客户端客户端ID进行查询" class="combox" action="${basePath}${proname}/configurator/apiclient_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:SrvApiClient(data.salClient).sacClient}" value="${data.salClient}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/apiclient_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************调用应用字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['salClient']>0">
				${lz:set("注释","****当调用应用字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","salClient")}
			  	<input type="hidden" name="salClient$on" id="SrvApiClient$on" value="true"/>
			  	<jsp:include page="/admin/configurator/apiclient.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$salClient}
			 	
			 ${before$salApi}
			 
			 ${lz:set("注释","*****************调用API字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "salApi" : (lz:join(relateObject,"$salApi")))}
			 ${lz:set("cname",lz:join(relateObject,".salApi"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.salApi || #request.defines['salApi']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"salApi":relateObject}" id="form-dl-salApi">
					<dt>调用API：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.salApiYesNot=="not"?"checked-not":""}${data.salApiYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.salApiYesNot}"/>
					<input title="请输入API接口API名称进行查询" class="combox" action="select * from srv_api where 1=1?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:SrvApi(data.salApi).saName}" value="${data.salApi}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************调用API字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['salApi']>0">
				${lz:set("注释","****当调用API字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","salApi")}
			  	<input type="hidden" name="salApi$on" id="SrvApi$on" value="true"/>
			  	<jsp:include page="/admin/configurator/api.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$salApi}
			 	
			 ${before$salParams}
			 
			 ${lz:set("注释","*****************调用参数字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "salParams" : (lz:join(relateObject,"$salParams")))}
			 ${lz:set("cname",lz:join(relateObject,".salParams"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.salParams || #request.defines['salParams']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"salParams":relateObject}" id="form-dl-salParams">
					<dt>调用参数：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.salParamsYesNot=="not"?"checked-not":""}${data.salParamsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.salParamsYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.salParams}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************调用参数字段的查询代码结束*****************")}
			
			
			${after$salParams}
			 	
			 ${before$salReturn}
			 
			 ${lz:set("注释","*****************返回数据字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "salReturn" : (lz:join(relateObject,"$salReturn")))}
			 ${lz:set("cname",lz:join(relateObject,".salReturn"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.salReturn || #request.defines['salReturn']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"salReturn":relateObject}" id="form-dl-salReturn">
					<dt>返回数据：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.salReturnYesNot=="not"?"checked-not":""}${data.salReturnYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.salReturnYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.salReturn}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************返回数据字段的查询代码结束*****************")}
			
			
			${after$salReturn}
			 	
			 ${before$salInvokeTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "salInvokeTime" : (lz:join(relateObject,"$salInvokeTime")))}
			 ${lz:set("cname",lz:join(relateObject,".salInvokeTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.salInvokeTime || #request.defines['salInvokeTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"salInvokeTime":relateObject}" id="form-dl-salInvokeTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.salInvokeTimeYesNot=="not"?"checked-not":""}${data.salInvokeTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.salInvokeTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.salInvokeTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.salInvokeTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.salInvokeTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.salInvokeTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.salInvokeTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$salInvokeTime}
			 	
			 ${before$salStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "salStatus" : (lz:join(relateObject,"$salStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".salStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.salStatus || #request.defines['salStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"salStatus":relateObject}" id="form-dl-salStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.salStatusYesNot=="not"?"checked-not":""}${data.salStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.salStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.salStatus==1?"selected":""}>默认</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$salStatus}