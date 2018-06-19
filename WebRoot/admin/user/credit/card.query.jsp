<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csccId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csccId" : (lz:join(relateObject,"$csccId")))}
			 ${lz:set("cname",lz:join(relateObject,".csccId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csccId || #request.defines['csccId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csccId":relateObject}" id="form-dl-csccId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csccIdYesNot=="not"?"checked-not":""}${data.csccIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csccIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csccId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csccId}
			 	
			 ${before$csccNo}
			 
			 ${lz:set("注释","*****************信用卡号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csccNo" : (lz:join(relateObject,"$csccNo")))}
			 ${lz:set("cname",lz:join(relateObject,".csccNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csccNo || #request.defines['csccNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csccNo":relateObject}" id="form-dl-csccNo">
					<dt>信用卡号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csccNoYesNot=="not"?"checked-not":""}${data.csccNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csccNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csccNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************信用卡号字段的查询代码结束*****************")}
			
			
			${after$csccNo}
			 	
			 ${before$csccMember}
			 
			 ${lz:set("注释","*****************所属会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csccMember" : (lz:join(relateObject,"$csccMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csccMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csccMember || #request.defines['csccMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csccMember":relateObject}" id="form-dl-csccMember">
					<dt>所属会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csccMemberYesNot=="not"?"checked-not":""}${data.csccMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csccMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csccMember).csmName}" value="${data.csccMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csccMember']>0">
				${lz:set("注释","****当所属会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csccMember")}
			  	<input type="hidden" name="csccMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csccMember}
			 	
			 ${before$csccLimit}
			 
			 ${lz:set("注释","*****************信用额度字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csccLimit" : (lz:join(relateObject,"$csccLimit")))}
			 ${lz:set("cname",lz:join(relateObject,".csccLimit"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csccLimit || #request.defines['csccLimit']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csccLimit":relateObject}" id="form-dl-csccLimit">
					<dt>信用额度：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csccLimitYesNot=="not"?"checked-not":""}${data.csccLimitYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csccLimitYesNot}"/>
			 		    <input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csccLimitMin}"/>
			 			-
			 			<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csccLimitMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************信用额度字段的查询代码结束*****************")}
			
			
			${after$csccLimit}
			 	
			 ${before$csccScope}
			 
			 ${lz:set("注释","*****************信用分值字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csccScope" : (lz:join(relateObject,"$csccScope")))}
			 ${lz:set("cname",lz:join(relateObject,".csccScope"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csccScope || #request.defines['csccScope']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csccScope":relateObject}" id="form-dl-csccScope">
					<dt>信用分值：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csccScopeYesNot=="not"?"checked-not":""}${data.csccScopeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csccScopeYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csccScopeMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csccScopeMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************信用分值字段的查询代码结束*****************")}
			
			
			${after$csccScope}
			 	
			 ${before$csccType}
			 
			 ${lz:set("注释","*****************信用卡类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csccType" : (lz:join(relateObject,"$csccType")))}
			 ${lz:set("cname",lz:join(relateObject,".csccType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csccType || #request.defines['csccType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csccType":relateObject}" id="form-dl-csccType">
					<dt>信用卡类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csccTypeYesNot=="not"?"checked-not":""}${data.csccTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csccTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csccType==1?"selected":""}>车纷享信用卡</option>
							<option value="3" ${data.csccType==3?"selected":""}>支付宝信用卡</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************信用卡类型字段的查询代码结束*****************")}
			
			
			${after$csccType}
			 	
			 ${before$csccRemark}
			 
			 ${lz:set("注释","*****************备注信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csccRemark" : (lz:join(relateObject,"$csccRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".csccRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csccRemark || #request.defines['csccRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csccRemark":relateObject}" id="form-dl-csccRemark">
					<dt>备注信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csccRemarkYesNot=="not"?"checked-not":""}${data.csccRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csccRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="500" name="${NAME}" id="${NAME}"  value="${data.csccRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注信息字段的查询代码结束*****************")}
			
			
			${after$csccRemark}
			 	
			 ${before$csccData}
			 
			 ${lz:set("注释","*****************特殊数据字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csccData" : (lz:join(relateObject,"$csccData")))}
			 ${lz:set("cname",lz:join(relateObject,".csccData"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csccData || #request.defines['csccData']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csccData":relateObject}" id="form-dl-csccData">
					<dt>特殊数据：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csccDataYesNot=="not"?"checked-not":""}${data.csccDataYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csccDataYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.csccData}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************特殊数据字段的查询代码结束*****************")}
			
			
			${after$csccData}
			 	
			 ${before$csccMask}
			 
			 ${lz:set("注释","*****************掩码标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csccMask" : (lz:join(relateObject,"$csccMask")))}
			 ${lz:set("cname",lz:join(relateObject,".csccMask"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csccMask || #request.defines['csccMask']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csccMask":relateObject}" id="form-dl-csccMask">
					<dt>掩码标识：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csccMaskYesNot=="not"?"checked-not":""}${data.csccMaskYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csccMaskYesNot}"/>
						<!-- 数值型的checkbox，暂时以select方式查询，后面改成多值查询 -->				
						<select id="${NAME}" name="${NAME}" >
			 				<option value="">全部</option>
							<option value="1" ${lz:bitin(data.csccMask,1)?"selected":""}>已回流支付宝</option>
							<option value="2" ${lz:bitin(data.csccMask,2)?"selected":""}>支付宝风险名单用户</option>
							<option value="3" ${lz:bitin(data.csccMask,3)?"selected":""}>保留</option>
						</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************掩码标识字段的查询代码结束*****************")}
			
			
			${after$csccMask}
			 	
			 ${before$csccUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csccUpdateTime" : (lz:join(relateObject,"$csccUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csccUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csccUpdateTime || #request.defines['csccUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csccUpdateTime":relateObject}" id="form-dl-csccUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csccUpdateTimeYesNot=="not"?"checked-not":""}${data.csccUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csccUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csccUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csccUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csccUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csccUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csccUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csccUpdateTime}
			 	
			 ${before$csccAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csccAddTime" : (lz:join(relateObject,"$csccAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csccAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csccAddTime || #request.defines['csccAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csccAddTime":relateObject}" id="form-dl-csccAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csccAddTimeYesNot=="not"?"checked-not":""}${data.csccAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csccAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csccAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csccAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csccAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csccAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csccAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csccAddTime}
			 	
			 ${before$csccStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csccStatus" : (lz:join(relateObject,"$csccStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csccStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csccStatus || #request.defines['csccStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csccStatus":relateObject}" id="form-dl-csccStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csccStatusYesNot=="not"?"checked-not":""}${data.csccStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csccStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csccStatus==0?"selected":""}>待审核</option>
							<option value="1" ${data.csccStatus==1?"selected":""}>正常</option>
							<option value="2" ${data.csccStatus==2?"selected":""}>禁用</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csccStatus}