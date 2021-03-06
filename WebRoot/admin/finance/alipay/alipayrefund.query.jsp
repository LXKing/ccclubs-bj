<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csarId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarId" : (lz:join(relateObject,"$csarId")))}
			 ${lz:set("cname",lz:join(relateObject,".csarId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarId || #request.defines['csarId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarId":relateObject}" id="form-dl-csarId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarIdYesNot=="not"?"checked-not":""}${data.csarIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csarId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csarId}
			 	
			 ${before$csarHost}
			 
			 ${lz:set("注释","*****************运营地区字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarHost" : (lz:join(relateObject,"$csarHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csarHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarHost || #request.defines['csarHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarHost":relateObject}" id="form-dl-csarHost">
					<dt>运营地区：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarHostYesNot=="not"?"checked-not":""}${data.csarHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csarHost}">
				 					${get:SrvHost(data.csarHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************运营地区字段的查询代码结束*****************")}
			
			
			${after$csarHost}
			 	
			 ${before$csarBankType}
			 
			 ${lz:set("注释","*****************接口类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarBankType" : (lz:join(relateObject,"$csarBankType")))}
			 ${lz:set("cname",lz:join(relateObject,".csarBankType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarBankType || #request.defines['csarBankType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarBankType":relateObject}" id="form-dl-csarBankType">
					<dt>接口类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarBankTypeYesNot=="not"?"checked-not":""}${data.csarBankTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarBankTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csarBankType==0?"selected":""}>支付宝</option>
							<option value="10" ${data.csarBankType==10?"selected":""}>招商银行</option>
							<option value="21" ${data.csarBankType==21?"selected":""}>支付宝转帐</option>
							<option value="22" ${data.csarBankType==22?"selected":""}>银行卡转帐</option>
							<option value="23" ${data.csarBankType==23?"selected":""}>邮局汇款</option>
							<option value="24" ${data.csarBankType==24?"selected":""}>现金退款</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接口类型字段的查询代码结束*****************")}
			
			
			${after$csarBankType}
			 	
			 ${before$csarTradeNo}
			 
			 ${lz:set("注释","*****************退款批次号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarTradeNo" : (lz:join(relateObject,"$csarTradeNo")))}
			 ${lz:set("cname",lz:join(relateObject,".csarTradeNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarTradeNo || #request.defines['csarTradeNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarTradeNo":relateObject}" id="form-dl-csarTradeNo">
					<dt>退款批次号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarTradeNoYesNot=="not"?"checked-not":""}${data.csarTradeNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarTradeNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csarTradeNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************退款批次号字段的查询代码结束*****************")}
			
			
			${after$csarTradeNo}
			 	
			 ${before$csarMember}
			 
			 ${lz:set("注释","*****************退款会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarMember" : (lz:join(relateObject,"$csarMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csarMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarMember || #request.defines['csarMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarMember":relateObject}" id="form-dl-csarMember">
					<dt>退款会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarMemberYesNot=="not"?"checked-not":""}${data.csarMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csarHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csarMember).csmName}" value="${data.csarMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************退款会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csarMember']>0">
				${lz:set("注释","****当退款会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csarMember")}
			  	<input type="hidden" name="csarMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csarMember}
			 	
			 ${before$csarRealName}
			 
			 ${lz:set("注释","*****************会员姓名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarRealName" : (lz:join(relateObject,"$csarRealName")))}
			 ${lz:set("cname",lz:join(relateObject,".csarRealName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarRealName || #request.defines['csarRealName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarRealName":relateObject}" id="form-dl-csarRealName">
					<dt>会员姓名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarRealNameYesNot=="not"?"checked-not":""}${data.csarRealNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarRealNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csarRealName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************会员姓名字段的查询代码结束*****************")}
			
			
			${after$csarRealName}
			 	
			 ${before$csarRefund}
			 
			 ${lz:set("注释","*****************关联退款申请字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarRefund" : (lz:join(relateObject,"$csarRefund")))}
			 ${lz:set("cname",lz:join(relateObject,".csarRefund"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarRefund || #request.defines['csarRefund']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarRefund":relateObject}" id="form-dl-csarRefund">
					<dt>关联退款申请：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarRefundYesNot=="not"?"checked-not":""}${data.csarRefundYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarRefundYesNot}"/>
			 			<input title="请输入会员退款编号进行查询" class="combox" action="${basePath}${proname}/finance/refund_query.do?value={param}&csrHost={csarHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsRefund(data.csarRefund).csrId}" value="${data.csarRefund}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/finance/refund_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联退款申请字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csarRefund']>0">
				${lz:set("注释","****当关联退款申请字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csarRefund")}
			  	<input type="hidden" name="csarRefund$on" id="CsRefund$on" value="true"/>
			  	<jsp:include page="/admin/finance/refund.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csarRefund}
			 	
			 ${before$csarRecord}
			 
			 ${lz:set("注释","*****************原接口充值字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarRecord" : (lz:join(relateObject,"$csarRecord")))}
			 ${lz:set("cname",lz:join(relateObject,".csarRecord"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarRecord || #request.defines['csarRecord']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarRecord":relateObject}" id="form-dl-csarRecord">
					<dt>原接口充值：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarRecordYesNot=="not"?"checked-not":""}${data.csarRecordYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarRecordYesNot}"/>
			 			<input title="请输入接口充值交易号进行查询" class="combox" action="${basePath}${proname}/finance/alipay/alipayrecord_query.do?value={param}&csarHost={csarHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsAlipayRecord(data.csarRecord).csarTradeNo}" value="${data.csarRecord}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/finance/alipay/alipayrecord_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************原接口充值字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csarRecord']>0">
				${lz:set("注释","****当原接口充值字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csarRecord")}
			  	<input type="hidden" name="csarRecord$on" id="CsAlipayRecord$on" value="true"/>
			  	<jsp:include page="/admin/finance/alipay/alipayrecord.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csarRecord}
			 	
			 ${before$csarAlipayNo}
			 
			 ${lz:set("注释","*****************备注信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarAlipayNo" : (lz:join(relateObject,"$csarAlipayNo")))}
			 ${lz:set("cname",lz:join(relateObject,".csarAlipayNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarAlipayNo || #request.defines['csarAlipayNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarAlipayNo":relateObject}" id="form-dl-csarAlipayNo">
					<dt>备注信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarAlipayNoYesNot=="not"?"checked-not":""}${data.csarAlipayNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarAlipayNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csarAlipayNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注信息字段的查询代码结束*****************")}
			
			
			${after$csarAlipayNo}
			 	
			 ${before$csarUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarUpdateTime" : (lz:join(relateObject,"$csarUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csarUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarUpdateTime || #request.defines['csarUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarUpdateTime":relateObject}" id="form-dl-csarUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarUpdateTimeYesNot=="not"?"checked-not":""}${data.csarUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csarUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csarUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csarUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csarUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csarUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csarUpdateTime}
			 	
			 ${before$csarAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarAddTime" : (lz:join(relateObject,"$csarAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csarAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarAddTime || #request.defines['csarAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarAddTime":relateObject}" id="form-dl-csarAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarAddTimeYesNot=="not"?"checked-not":""}${data.csarAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csarAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csarAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csarAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csarAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csarAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csarAddTime}
			 	
			 ${before$csarStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csarStatus" : (lz:join(relateObject,"$csarStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csarStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csarStatus || #request.defines['csarStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csarStatus":relateObject}" id="form-dl-csarStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csarStatusYesNot=="not"?"checked-not":""}${data.csarStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csarStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csarStatus==0?"selected":""}>等待</option>
							<option value="1" ${data.csarStatus==1?"selected":""}>成功</option>
							<option value="2" ${data.csarStatus==2?"selected":""}>失败</option>
							<option value="3" ${data.csarStatus==3?"selected":""}>取消</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csarStatus}