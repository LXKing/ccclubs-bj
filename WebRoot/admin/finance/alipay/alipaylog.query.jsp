<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csalId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csalId" : (lz:join(relateObject,"$csalId")))}
			 ${lz:set("cname",lz:join(relateObject,".csalId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csalId || #request.defines['csalId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csalId":relateObject}" id="form-dl-csalId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csalIdYesNot=="not"?"checked-not":""}${data.csalIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csalIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csalId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csalId}
			 	
			 ${before$csalBankType}
			 
			 ${lz:set("注释","*****************接口类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csalBankType" : (lz:join(relateObject,"$csalBankType")))}
			 ${lz:set("cname",lz:join(relateObject,".csalBankType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csalBankType || #request.defines['csalBankType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csalBankType":relateObject}" id="form-dl-csalBankType">
					<dt>接口类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csalBankTypeYesNot=="not"?"checked-not":""}${data.csalBankTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csalBankTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csalBankType==0?"selected":""}>支付宝</option>
							<option value="10" ${data.csalBankType==10?"selected":""}>招商银行</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接口类型字段的查询代码结束*****************")}
			
			
			${after$csalBankType}
			 	
			 ${before$csalRecord}
			 
			 ${lz:set("注释","*****************关联充值字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csalRecord" : (lz:join(relateObject,"$csalRecord")))}
			 ${lz:set("cname",lz:join(relateObject,".csalRecord"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csalRecord || #request.defines['csalRecord']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csalRecord":relateObject}" id="form-dl-csalRecord">
					<dt>关联充值：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csalRecordYesNot=="not"?"checked-not":""}${data.csalRecordYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csalRecordYesNot}"/>
			 			<input title="请输入接口充值交易号进行查询" class="combox" action="${basePath}${proname}/finance/alipay/alipayrecord_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsAlipayRecord(data.csalRecord).csarTradeNo}" value="${data.csalRecord}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/finance/alipay/alipayrecord_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联充值字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csalRecord']>0">
				${lz:set("注释","****当关联充值字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csalRecord")}
			  	<input type="hidden" name="csalRecord$on" id="CsAlipayRecord$on" value="true"/>
			  	<jsp:include page="/admin/finance/alipay/alipayrecord.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csalRecord}
			 	
			 ${before$csalRefund}
			 
			 ${lz:set("注释","*****************关联退款字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csalRefund" : (lz:join(relateObject,"$csalRefund")))}
			 ${lz:set("cname",lz:join(relateObject,".csalRefund"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csalRefund || #request.defines['csalRefund']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csalRefund":relateObject}" id="form-dl-csalRefund">
					<dt>关联退款：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csalRefundYesNot=="not"?"checked-not":""}${data.csalRefundYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csalRefundYesNot}"/>
			 			<input title="请输入接口退款退款批次号进行查询" class="combox" action="${basePath}${proname}/finance/alipay/alipayrefund_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsAlipayRefund(data.csalRefund).csarTradeNo}" value="${data.csalRefund}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/finance/alipay/alipayrefund_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联退款字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csalRefund']>0">
				${lz:set("注释","****当关联退款字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csalRefund")}
			  	<input type="hidden" name="csalRefund$on" id="CsAlipayRefund$on" value="true"/>
			  	<jsp:include page="/admin/finance/alipay/alipayrefund.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csalRefund}
			 	
			 ${before$csalOther}
			 
			 ${lz:set("注释","*****************关联其它字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csalOther" : (lz:join(relateObject,"$csalOther")))}
			 ${lz:set("cname",lz:join(relateObject,".csalOther"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csalOther || #request.defines['csalOther']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csalOther":relateObject}" id="form-dl-csalOther">
					<dt>关联其它：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csalOtherYesNot=="not"?"checked-not":""}${data.csalOtherYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csalOtherYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csalOtherMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csalOtherMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联其它字段的查询代码结束*****************")}
			
			
			${after$csalOther}
			 	
			 ${before$csalModelClass}
			 
			 ${lz:set("注释","*****************模型类名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csalModelClass" : (lz:join(relateObject,"$csalModelClass")))}
			 ${lz:set("cname",lz:join(relateObject,".csalModelClass"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csalModelClass || #request.defines['csalModelClass']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csalModelClass":relateObject}" id="form-dl-csalModelClass">
					<dt>模型类名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csalModelClassYesNot=="not"?"checked-not":""}${data.csalModelClassYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csalModelClassYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csalModelClass}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************模型类名字段的查询代码结束*****************")}
			
			
			${after$csalModelClass}
			 	
			 ${before$csalAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csalAddTime" : (lz:join(relateObject,"$csalAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csalAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csalAddTime || #request.defines['csalAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csalAddTime":relateObject}" id="form-dl-csalAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csalAddTimeYesNot=="not"?"checked-not":""}${data.csalAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csalAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csalAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csalAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csalAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csalAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csalAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csalAddTime}
			 	
			 ${before$csalStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csalStatus" : (lz:join(relateObject,"$csalStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csalStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csalStatus || #request.defines['csalStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csalStatus":relateObject}" id="form-dl-csalStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csalStatusYesNot=="not"?"checked-not":""}${data.csalStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csalStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csalStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csalStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csalStatus}