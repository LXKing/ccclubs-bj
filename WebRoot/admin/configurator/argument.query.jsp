<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csaId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaId" : (lz:join(relateObject,"$csaId")))}
			 ${lz:set("cname",lz:join(relateObject,".csaId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaId || #request.defines['csaId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaId":relateObject}" id="form-dl-csaId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaIdYesNot=="not"?"checked-not":""}${data.csaIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csaId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csaId}
			 	
			 ${before$csaName}
			 
			 ${lz:set("注释","*****************参数名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaName" : (lz:join(relateObject,"$csaName")))}
			 ${lz:set("cname",lz:join(relateObject,".csaName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaName || #request.defines['csaName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaName":relateObject}" id="form-dl-csaName">
					<dt>参数名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaNameYesNot=="not"?"checked-not":""}${data.csaNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csaName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************参数名称字段的查询代码结束*****************")}
			
			
			${after$csaName}
			 	
			 ${before$csaFlag}
			 
			 ${lz:set("注释","*****************参数标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csaFlag" : (lz:join(relateObject,"$csaFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".csaFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csaFlag || #request.defines['csaFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csaFlag":relateObject}" id="form-dl-csaFlag">
					<dt>参数标识：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csaFlagYesNot=="not"?"checked-not":""}${data.csaFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csaFlagYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csaFlag}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************参数标识字段的查询代码结束*****************")}
			
			
			${after$csaFlag}