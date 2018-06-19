<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$stId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "stId" : (lz:join(relateObject,"$stId")))}
			 ${lz:set("cname",lz:join(relateObject,".stId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.stId || #request.defines['stId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"stId":relateObject}" id="form-dl-stId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.stIdYesNot=="not"?"checked-not":""}${data.stIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.stIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.stId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$stId}
			 	
			 ${before$stEntry}
			 
			 ${lz:set("注释","*****************调用入口字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "stEntry" : (lz:join(relateObject,"$stEntry")))}
			 ${lz:set("cname",lz:join(relateObject,".stEntry"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.stEntry || #request.defines['stEntry']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"stEntry":relateObject}" id="form-dl-stEntry">
					<dt>调用入口：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.stEntryYesNot=="not"?"checked-not":""}${data.stEntryYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.stEntryYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.stEntry}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************调用入口字段的查询代码结束*****************")}
			
			
			${after$stEntry}
			 	
			 ${before$stDetails}
			 
			 ${lz:set("注释","*****************调用详情字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "stDetails" : (lz:join(relateObject,"$stDetails")))}
			 ${lz:set("cname",lz:join(relateObject,".stDetails"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.stDetails || #request.defines['stDetails']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"stDetails":relateObject}" id="form-dl-stDetails">
					<dt>调用详情：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.stDetailsYesNot=="not"?"checked-not":""}${data.stDetailsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.stDetailsYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.stDetails}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************调用详情字段的查询代码结束*****************")}
			
			
			${after$stDetails}
			 	
			 ${before$stModels}
			 
			 ${lz:set("注释","*****************修改模块字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "stModels" : (lz:join(relateObject,"$stModels")))}
			 ${lz:set("cname",lz:join(relateObject,".stModels"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.stModels || #request.defines['stModels']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"stModels":relateObject}" id="form-dl-stModels">
					<dt>修改模块：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.stModelsYesNot=="not"?"checked-not":""}${data.stModelsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.stModelsYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="512" name="${NAME}" id="${NAME}"  value="${data.stModels}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改模块字段的查询代码结束*****************")}
			
			
			${after$stModels}