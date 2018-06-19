<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csqaId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csqaId" : (lz:join(relateObject,"$csqaId")))}
			 ${lz:set("cname",lz:join(relateObject,".csqaId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csqaId || #request.defines['csqaId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csqaId":relateObject}" id="form-dl-csqaId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csqaIdYesNot=="not"?"checked-not":""}${data.csqaIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csqaIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csqaId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csqaId}
			 	
			 ${before$csqaTitle}
			 
			 ${lz:set("注释","*****************标题内容字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csqaTitle" : (lz:join(relateObject,"$csqaTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".csqaTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csqaTitle || #request.defines['csqaTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csqaTitle":relateObject}" id="form-dl-csqaTitle">
					<dt>标题内容：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csqaTitleYesNot=="not"?"checked-not":""}${data.csqaTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csqaTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csqaTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************标题内容字段的查询代码结束*****************")}
			
			
			${after$csqaTitle}
			 	
			 ${before$csqaParent}
			 
			 ${lz:set("注释","*****************所属问题字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csqaParent" : (lz:join(relateObject,"$csqaParent")))}
			 ${lz:set("cname",lz:join(relateObject,".csqaParent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csqaParent || #request.defines['csqaParent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csqaParent":relateObject}" id="form-dl-csqaParent">
					<dt>所属问题：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csqaParentYesNot=="not"?"checked-not":""}${data.csqaParentYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csqaParentYesNot}"/>
					<input title="请输入问题及选项标题内容进行查询" class="combox" action="${basePath}${proname}/csm/revisit/answer_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsQuestAnswer(data.csqaParent).csqaTitle}" value="${data.csqaParent}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属问题字段的查询代码结束*****************")}
			
			
			${after$csqaParent}