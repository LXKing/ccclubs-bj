<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cstId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstId" : (lz:join(relateObject,"$cstId")))}
			 ${lz:set("cname",lz:join(relateObject,".cstId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstId || #request.defines['cstId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstId":relateObject}" id="form-dl-cstId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstIdYesNot=="not"?"checked-not":""}${data.cstIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cstId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cstId}
			 	
			 ${before$cstName}
			 
			 ${lz:set("注释","*****************任务名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstName" : (lz:join(relateObject,"$cstName")))}
			 ${lz:set("cname",lz:join(relateObject,".cstName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstName || #request.defines['cstName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstName":relateObject}" id="form-dl-cstName">
					<dt>任务名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstNameYesNot=="not"?"checked-not":""}${data.cstNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cstName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************任务名称字段的查询代码结束*****************")}
			
			
			${after$cstName}
			 	
			 ${before$cstUserIds}
			 
			 ${lz:set("注释","*****************处理人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstUserIds" : (lz:join(relateObject,"$cstUserIds")))}
			 ${lz:set("cname",lz:join(relateObject,".cstUserIds"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstUserIds || #request.defines['cstUserIds']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstUserIds":relateObject}" id="form-dl-cstUserIds">
					<dt>处理人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstUserIdsYesNot=="not"?"checked-not":""}${data.cstUserIdsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstUserIdsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cstUserIds}">
				 					${get:SrvUser(data.cstUserIds).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************处理人员字段的查询代码结束*****************")}
			
			
			${after$cstUserIds}
			 	
			 ${before$cstEditTime}
			 
			 ${lz:set("注释","*****************编辑时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstEditTime" : (lz:join(relateObject,"$cstEditTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cstEditTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstEditTime || #request.defines['cstEditTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstEditTime":relateObject}" id="form-dl-cstEditTime">
					<dt>编辑时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstEditTimeYesNot=="not"?"checked-not":""}${data.cstEditTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstEditTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cstEditTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cstEditTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cstEditTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cstEditTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>编辑时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cstEditTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编辑时间字段的查询代码结束*****************")}
			
			
			${after$cstEditTime}
			 	
			 ${before$cstStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cstStatus" : (lz:join(relateObject,"$cstStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cstStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cstStatus || #request.defines['cstStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cstStatus":relateObject}" id="form-dl-cstStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cstStatusYesNot=="not"?"checked-not":""}${data.cstStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cstStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.cstStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.cstStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cstStatus}