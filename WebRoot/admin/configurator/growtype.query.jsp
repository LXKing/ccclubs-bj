<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csgtId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgtId" : (lz:join(relateObject,"$csgtId")))}
			 ${lz:set("cname",lz:join(relateObject,".csgtId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgtId || #request.defines['csgtId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgtId":relateObject}" id="form-dl-csgtId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgtIdYesNot=="not"?"checked-not":""}${data.csgtIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgtIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csgtId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csgtId}
			 	
			 ${before$csgtName}
			 
			 ${lz:set("注释","*****************类型名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgtName" : (lz:join(relateObject,"$csgtName")))}
			 ${lz:set("cname",lz:join(relateObject,".csgtName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgtName || #request.defines['csgtName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgtName":relateObject}" id="form-dl-csgtName">
					<dt>类型名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgtNameYesNot=="not"?"checked-not":""}${data.csgtNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgtNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csgtName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************类型名称字段的查询代码结束*****************")}
			
			
			${after$csgtName}
			 	
			 ${before$csgtIsArtif}
			 
			 ${lz:set("注释","*****************人工操作项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgtIsArtif" : (lz:join(relateObject,"$csgtIsArtif")))}
			 ${lz:set("cname",lz:join(relateObject,".csgtIsArtif"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgtIsArtif || #request.defines['csgtIsArtif']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgtIsArtif":relateObject}" id="form-dl-csgtIsArtif">
					<dt>人工操作项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgtIsArtifYesNot=="not"?"checked-not":""}${data.csgtIsArtifYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgtIsArtifYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csgtIsArtif=='true'?"selected":""}>是</option>
							<option value="false" ${data.csgtIsArtif=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************人工操作项字段的查询代码结束*****************")}
			
			
			${after$csgtIsArtif}
			 	
			 ${before$csgtIsOrder}
			 
			 ${lz:set("注释","*****************与订单相关字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgtIsOrder" : (lz:join(relateObject,"$csgtIsOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csgtIsOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgtIsOrder || #request.defines['csgtIsOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgtIsOrder":relateObject}" id="form-dl-csgtIsOrder">
					<dt>与订单相关：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgtIsOrderYesNot=="not"?"checked-not":""}${data.csgtIsOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgtIsOrderYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csgtIsOrder=='true'?"selected":""}>是</option>
							<option value="false" ${data.csgtIsOrder=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************与订单相关字段的查询代码结束*****************")}
			
			
			${after$csgtIsOrder}
			 	
			 ${before$csgtValue}
			 
			 ${lz:set("注释","*****************变化值字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgtValue" : (lz:join(relateObject,"$csgtValue")))}
			 ${lz:set("cname",lz:join(relateObject,".csgtValue"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgtValue || #request.defines['csgtValue']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgtValue":relateObject}" id="form-dl-csgtValue">
					<dt>变&nbsp;&nbsp;化&nbsp;&nbsp;值：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgtValueYesNot=="not"?"checked-not":""}${data.csgtValueYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgtValueYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csgtValueMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csgtValueMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************变化值字段的查询代码结束*****************")}
			
			
			${after$csgtValue}
			 	
			 ${before$csgtProfile}
			 
			 ${lz:set("注释","*****************类型说明字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgtProfile" : (lz:join(relateObject,"$csgtProfile")))}
			 ${lz:set("cname",lz:join(relateObject,".csgtProfile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgtProfile || #request.defines['csgtProfile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgtProfile":relateObject}" id="form-dl-csgtProfile">
					<dt>类型说明：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgtProfileYesNot=="not"?"checked-not":""}${data.csgtProfileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgtProfileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csgtProfile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************类型说明字段的查询代码结束*****************")}
			
			
			${after$csgtProfile}
			 	
			 ${before$csgtStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgtStatus" : (lz:join(relateObject,"$csgtStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csgtStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgtStatus || #request.defines['csgtStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgtStatus":relateObject}" id="form-dl-csgtStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgtStatusYesNot=="not"?"checked-not":""}${data.csgtStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgtStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csgtStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csgtStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csgtStatus}