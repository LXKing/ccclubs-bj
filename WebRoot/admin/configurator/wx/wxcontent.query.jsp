<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cswcId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswcId" : (lz:join(relateObject,"$cswcId")))}
			 ${lz:set("cname",lz:join(relateObject,".cswcId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswcId || #request.defines['cswcId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswcId":relateObject}" id="form-dl-cswcId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswcIdYesNot=="not"?"checked-not":""}${data.cswcIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswcIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cswcId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cswcId}
			 	
			 ${before$cswcTitle}
			 
			 ${lz:set("注释","*****************标题字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswcTitle" : (lz:join(relateObject,"$cswcTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".cswcTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswcTitle || #request.defines['cswcTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswcTitle":relateObject}" id="form-dl-cswcTitle">
					<dt>标　　题：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswcTitleYesNot=="not"?"checked-not":""}${data.cswcTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswcTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cswcTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************标题字段的查询代码结束*****************")}
			
			
			${after$cswcTitle}
			 	
			 ${before$cswcContnet}
			 
			 ${lz:set("注释","*****************内容字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswcContnet" : (lz:join(relateObject,"$cswcContnet")))}
			 ${lz:set("cname",lz:join(relateObject,".cswcContnet"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswcContnet || #request.defines['cswcContnet']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswcContnet":relateObject}" id="form-dl-cswcContnet">
					<dt>内　　容：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswcContnetYesNot=="not"?"checked-not":""}${data.cswcContnetYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswcContnetYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="1024" name="${NAME}" id="${NAME}"  value="${data.cswcContnet}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************内容字段的查询代码结束*****************")}
			
			
			${after$cswcContnet}
			 	
			 ${before$cswcOrder}
			 
			 ${lz:set("注释","*****************所属指令字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswcOrder" : (lz:join(relateObject,"$cswcOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".cswcOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswcOrder || #request.defines['cswcOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswcOrder":relateObject}" id="form-dl-cswcOrder">
					<dt>所属指令：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswcOrderYesNot=="not"?"checked-not":""}${data.cswcOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswcOrderYesNot}"/>
			 			<input title="请输入微信指令指令名称进行查询" class="combox" action="${basePath}${proname}/configurator/wx/wxorder_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsWxOrder(data.cswcOrder).cswoName}" value="${data.cswcOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/wx/wxorder_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属指令字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cswcOrder']>0">
				${lz:set("注释","****当所属指令字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cswcOrder")}
			  	<input type="hidden" name="cswcOrder$on" id="CsWxOrder$on" value="true"/>
			  	<jsp:include page="/admin/configurator/wx/wxorder.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cswcOrder}
			 	
			 ${before$cswcAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswcAddTime" : (lz:join(relateObject,"$cswcAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cswcAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswcAddTime || #request.defines['cswcAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswcAddTime":relateObject}" id="form-dl-cswcAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswcAddTimeYesNot=="not"?"checked-not":""}${data.cswcAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswcAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cswcAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cswcAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cswcAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cswcAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cswcAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cswcAddTime}