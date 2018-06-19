<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csltId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csltId" : (lz:join(relateObject,"$csltId")))}
			 ${lz:set("cname",lz:join(relateObject,".csltId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csltId || #request.defines['csltId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csltId":relateObject}" id="form-dl-csltId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csltIdYesNot=="not"?"checked-not":""}${data.csltIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csltIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csltId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csltId}
			 	
			 ${before$csltHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csltHost" : (lz:join(relateObject,"$csltHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csltHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csltHost || #request.defines['csltHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csltHost":relateObject}" id="form-dl-csltHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csltHostYesNot=="not"?"checked-not":""}${data.csltHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csltHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csltHost}">
				 					${get:SrvHost(data.csltHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csltHost}
			 	
			 ${before$csltWeekDay}
			 
			 ${lz:set("注释","*****************星期几？字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csltWeekDay" : (lz:join(relateObject,"$csltWeekDay")))}
			 ${lz:set("cname",lz:join(relateObject,".csltWeekDay"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csltWeekDay || #request.defines['csltWeekDay']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csltWeekDay":relateObject}" id="form-dl-csltWeekDay">
					<dt>星期几？：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csltWeekDayYesNot=="not"?"checked-not":""}${data.csltWeekDayYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csltWeekDayYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csltWeekDay==1?"selected":""}>星期天</option>
							<option value="2" ${data.csltWeekDay==2?"selected":""}>星期一</option>
							<option value="3" ${data.csltWeekDay==3?"selected":""}>星期二</option>
							<option value="4" ${data.csltWeekDay==4?"selected":""}>星期三</option>
							<option value="5" ${data.csltWeekDay==5?"selected":""}>星期四</option>
							<option value="6" ${data.csltWeekDay==6?"selected":""}>星期五</option>
							<option value="7" ${data.csltWeekDay==7?"selected":""}>星期六</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************星期几？字段的查询代码结束*****************")}
			
			
			${after$csltWeekDay}
			 	
			 ${before$csltNum}
			 
			 ${lz:set("注释","*****************限行尾号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csltNum" : (lz:join(relateObject,"$csltNum")))}
			 ${lz:set("cname",lz:join(relateObject,".csltNum"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csltNum || #request.defines['csltNum']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csltNum":relateObject}" id="form-dl-csltNum">
					<dt>限行尾号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csltNumYesNot=="not"?"checked-not":""}${data.csltNumYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csltNumYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="16" name="${NAME}" id="${NAME}"  value="${data.csltNum}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************限行尾号字段的查询代码结束*****************")}
			
			
			${after$csltNum}