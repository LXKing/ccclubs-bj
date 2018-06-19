<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csrsId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrsId" : (lz:join(relateObject,"$csrsId")))}
			 ${lz:set("cname",lz:join(relateObject,".csrsId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrsId || #request.defines['csrsId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrsId":relateObject}" id="form-dl-csrsId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrsIdYesNot=="not"?"checked-not":""}${data.csrsIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrsIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csrsId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csrsId}
			 	
			 ${before$csrsName}
			 
			 ${lz:set("注释","*****************科目名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrsName" : (lz:join(relateObject,"$csrsName")))}
			 ${lz:set("cname",lz:join(relateObject,".csrsName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrsName || #request.defines['csrsName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrsName":relateObject}" id="form-dl-csrsName">
					<dt>科目名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrsNameYesNot=="not"?"checked-not":""}${data.csrsNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrsNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csrsName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************科目名称字段的查询代码结束*****************")}
			
			
			${after$csrsName}
			 	
			 ${before$csrsIsIncome}
			 
			 ${lz:set("注释","*****************系统收入项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrsIsIncome" : (lz:join(relateObject,"$csrsIsIncome")))}
			 ${lz:set("cname",lz:join(relateObject,".csrsIsIncome"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrsIsIncome || #request.defines['csrsIsIncome']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrsIsIncome":relateObject}" id="form-dl-csrsIsIncome">
					<dt>系统收入项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrsIsIncomeYesNot=="not"?"checked-not":""}${data.csrsIsIncomeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrsIsIncomeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csrsIsIncome=='true'?"selected":""}>是</option>
							<option value="false" ${data.csrsIsIncome=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************系统收入项字段的查询代码结束*****************")}
			
			
			${after$csrsIsIncome}
			 	
			 ${before$csrsIsOperIn}
			 
			 ${lz:set("注释","*****************商家收入项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrsIsOperIn" : (lz:join(relateObject,"$csrsIsOperIn")))}
			 ${lz:set("cname",lz:join(relateObject,".csrsIsOperIn"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrsIsOperIn || #request.defines['csrsIsOperIn']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrsIsOperIn":relateObject}" id="form-dl-csrsIsOperIn">
					<dt>商家收入项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrsIsOperInYesNot=="not"?"checked-not":""}${data.csrsIsOperInYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrsIsOperInYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csrsIsOperIn=='true'?"selected":""}>是</option>
							<option value="false" ${data.csrsIsOperIn=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************商家收入项字段的查询代码结束*****************")}
			
			
			${after$csrsIsOperIn}
			 	
			 ${before$csrsIsOperOut}
			 
			 ${lz:set("注释","*****************商家支出项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrsIsOperOut" : (lz:join(relateObject,"$csrsIsOperOut")))}
			 ${lz:set("cname",lz:join(relateObject,".csrsIsOperOut"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrsIsOperOut || #request.defines['csrsIsOperOut']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrsIsOperOut":relateObject}" id="form-dl-csrsIsOperOut">
					<dt>商家支出项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrsIsOperOutYesNot=="not"?"checked-not":""}${data.csrsIsOperOutYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrsIsOperOutYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csrsIsOperOut=='true'?"selected":""}>是</option>
							<option value="false" ${data.csrsIsOperOut=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************商家支出项字段的查询代码结束*****************")}
			
			
			${after$csrsIsOperOut}
			 	
			 ${before$csrsIsUserIn}
			 
			 ${lz:set("注释","*****************会员收入项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrsIsUserIn" : (lz:join(relateObject,"$csrsIsUserIn")))}
			 ${lz:set("cname",lz:join(relateObject,".csrsIsUserIn"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrsIsUserIn || #request.defines['csrsIsUserIn']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrsIsUserIn":relateObject}" id="form-dl-csrsIsUserIn">
					<dt>会员收入项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrsIsUserInYesNot=="not"?"checked-not":""}${data.csrsIsUserInYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrsIsUserInYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csrsIsUserIn=='true'?"selected":""}>是</option>
							<option value="false" ${data.csrsIsUserIn=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************会员收入项字段的查询代码结束*****************")}
			
			
			${after$csrsIsUserIn}
			 	
			 ${before$csrsIsUserOut}
			 
			 ${lz:set("注释","*****************会员支出项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrsIsUserOut" : (lz:join(relateObject,"$csrsIsUserOut")))}
			 ${lz:set("cname",lz:join(relateObject,".csrsIsUserOut"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrsIsUserOut || #request.defines['csrsIsUserOut']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrsIsUserOut":relateObject}" id="form-dl-csrsIsUserOut">
					<dt>会员支出项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrsIsUserOutYesNot=="not"?"checked-not":""}${data.csrsIsUserOutYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrsIsUserOutYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csrsIsUserOut=='true'?"selected":""}>是</option>
							<option value="false" ${data.csrsIsUserOut=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************会员支出项字段的查询代码结束*****************")}
			
			
			${after$csrsIsUserOut}
			 	
			 ${before$csrsIsArtif}
			 
			 ${lz:set("注释","*****************人工操作项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrsIsArtif" : (lz:join(relateObject,"$csrsIsArtif")))}
			 ${lz:set("cname",lz:join(relateObject,".csrsIsArtif"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrsIsArtif || #request.defines['csrsIsArtif']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrsIsArtif":relateObject}" id="form-dl-csrsIsArtif">
					<dt>人工操作项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrsIsArtifYesNot=="not"?"checked-not":""}${data.csrsIsArtifYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrsIsArtifYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csrsIsArtif=='true'?"selected":""}>是</option>
							<option value="false" ${data.csrsIsArtif=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************人工操作项字段的查询代码结束*****************")}
			
			
			${after$csrsIsArtif}
			 	
			 ${before$csrsIsOrder}
			 
			 ${lz:set("注释","*****************订单相关项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrsIsOrder" : (lz:join(relateObject,"$csrsIsOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csrsIsOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrsIsOrder || #request.defines['csrsIsOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrsIsOrder":relateObject}" id="form-dl-csrsIsOrder">
					<dt>订单相关项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrsIsOrderYesNot=="not"?"checked-not":""}${data.csrsIsOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrsIsOrderYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csrsIsOrder=='true'?"selected":""}>是</option>
							<option value="false" ${data.csrsIsOrder=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单相关项字段的查询代码结束*****************")}
			
			
			${after$csrsIsOrder}
			 	
			 ${before$csrsIsInvoice}
			 
			 ${lz:set("注释","*****************是否开票项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrsIsInvoice" : (lz:join(relateObject,"$csrsIsInvoice")))}
			 ${lz:set("cname",lz:join(relateObject,".csrsIsInvoice"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrsIsInvoice || #request.defines['csrsIsInvoice']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrsIsInvoice":relateObject}" id="form-dl-csrsIsInvoice">
					<dt>是否开票项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrsIsInvoiceYesNot=="not"?"checked-not":""}${data.csrsIsInvoiceYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrsIsInvoiceYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="true" ${data.csrsIsInvoice=='true'?"selected":""}>是</option>
							<option value="false" ${data.csrsIsInvoice=='false'?"selected":""}>否</option>		 		
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************是否开票项字段的查询代码结束*****************")}
			
			
			${after$csrsIsInvoice}