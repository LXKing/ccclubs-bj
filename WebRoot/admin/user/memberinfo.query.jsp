<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csmiId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiId" : (lz:join(relateObject,"$csmiId")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiId || #request.defines['csmiId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiId":relateObject}" id="form-dl-csmiId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiIdYesNot=="not"?"checked-not":""}${data.csmiIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csmiId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csmiId}
			 	
			 ${before$csmiHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiHost" : (lz:join(relateObject,"$csmiHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiHost || #request.defines['csmiHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiHost":relateObject}" id="form-dl-csmiHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiHostYesNot=="not"?"checked-not":""}${data.csmiHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmiHost}">
				 					${get:SrvHost(data.csmiHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csmiHost}
			 	
			 ${before$csmiName}
			 
			 ${lz:set("注释","*****************真实姓名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiName" : (lz:join(relateObject,"$csmiName")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiName || #request.defines['csmiName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiName":relateObject}" id="form-dl-csmiName">
					<dt>真实姓名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiNameYesNot=="not"?"checked-not":""}${data.csmiNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csmiName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************真实姓名字段的查询代码结束*****************")}
			
			
			${after$csmiName}
			 	
			 ${before$csmiMemberId}
			 
			 ${lz:set("注释","*****************会员帐号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiMemberId" : (lz:join(relateObject,"$csmiMemberId")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiMemberId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiMemberId || #request.defines['csmiMemberId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiMemberId":relateObject}" id="form-dl-csmiMemberId">
					<dt>会员帐号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiMemberIdYesNot=="not"?"checked-not":""}${data.csmiMemberIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiMemberIdYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csmiHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csmiMemberId).csmName}" value="${data.csmiMemberId}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************会员帐号字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmiMemberId']>0">
				${lz:set("注释","****当会员帐号字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmiMemberId")}
			  	<input type="hidden" name="csmiMemberId$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmiMemberId}
			 	
			 ${before$csmiSex}
			 
			 ${lz:set("注释","*****************性别字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiSex" : (lz:join(relateObject,"$csmiSex")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiSex"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiSex || #request.defines['csmiSex']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiSex":relateObject}" id="form-dl-csmiSex">
					<dt>性　　别：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiSexYesNot=="not"?"checked-not":""}${data.csmiSexYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiSexYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmiSex==0?"selected":""}>女</option>
							<option value="1" ${data.csmiSex==1?"selected":""}>男</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************性别字段的查询代码结束*****************")}
			
			
			${after$csmiSex}
			 	
			 ${before$csmiBirthday}
			 
			 ${lz:set("注释","*****************生日字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiBirthday" : (lz:join(relateObject,"$csmiBirthday")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiBirthday"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiBirthday || #request.defines['csmiBirthday']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiBirthday":relateObject}" id="form-dl-csmiBirthday">
					<dt>生　　日：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiBirthdayYesNot=="not"?"checked-not":""}${data.csmiBirthdayYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiBirthdayYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmiBirthdayStart,"yyyy-MM-dd")}" end="${lz:date(data.csmiBirthdayEnd,"yyyy-MM-dd")}" exp="${data.csmiBirthdayExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmiBirthdayStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>生日：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmiBirthdayEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************生日字段的查询代码结束*****************")}
			
			
			${after$csmiBirthday}
			 	
			 ${before$csmiQq}
			 
			 ${lz:set("注释","*****************QQ字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiQq" : (lz:join(relateObject,"$csmiQq")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiQq"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiQq || #request.defines['csmiQq']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiQq":relateObject}" id="form-dl-csmiQq">
					<dt>Q　　Q：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiQqYesNot=="not"?"checked-not":""}${data.csmiQqYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiQqYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csmiQq}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************QQ字段的查询代码结束*****************")}
			
			
			${after$csmiQq}
			 	
			 ${before$csmiNation}
			 
			 ${lz:set("注释","*****************民族字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiNation" : (lz:join(relateObject,"$csmiNation")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiNation"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiNation || #request.defines['csmiNation']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiNation":relateObject}" id="form-dl-csmiNation">
					<dt>民　　族：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiNationYesNot=="not"?"checked-not":""}${data.csmiNationYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiNationYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csmiNation}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************民族字段的查询代码结束*****************")}
			
			
			${after$csmiNation}
			 	
			 ${before$csmiAddress}
			 
			 ${lz:set("注释","*****************邮寄地址字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiAddress" : (lz:join(relateObject,"$csmiAddress")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiAddress"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiAddress || #request.defines['csmiAddress']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiAddress":relateObject}" id="form-dl-csmiAddress">
					<dt>邮寄地址：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiAddressYesNot=="not"?"checked-not":""}${data.csmiAddressYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiAddressYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csmiAddress}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************邮寄地址字段的查询代码结束*****************")}
			
			
			${after$csmiAddress}
			 	
			 ${before$csmiCompany}
			 
			 ${lz:set("注释","*****************就职公司字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiCompany" : (lz:join(relateObject,"$csmiCompany")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiCompany"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiCompany || #request.defines['csmiCompany']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiCompany":relateObject}" id="form-dl-csmiCompany">
					<dt>就职公司：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiCompanyYesNot=="not"?"checked-not":""}${data.csmiCompanyYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiCompanyYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csmiCompany}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************就职公司字段的查询代码结束*****************")}
			
			
			${after$csmiCompany}
			 	
			 ${before$csmiPlace}
			 
			 ${lz:set("注释","*****************所在地字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiPlace" : (lz:join(relateObject,"$csmiPlace")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiPlace"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiPlace || #request.defines['csmiPlace']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiPlace":relateObject}" id="form-dl-csmiPlace">
					<dt>所&nbsp;&nbsp;在&nbsp;&nbsp;地：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiPlaceYesNot=="not"?"checked-not":""}${data.csmiPlaceYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiPlaceYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csmiPlaceMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csmiPlaceMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所在地字段的查询代码结束*****************")}
			
			
			${after$csmiPlace}
			 	
			 ${before$csmiUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiUpdateTime" : (lz:join(relateObject,"$csmiUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiUpdateTime || #request.defines['csmiUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiUpdateTime":relateObject}" id="form-dl-csmiUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiUpdateTimeYesNot=="not"?"checked-not":""}${data.csmiUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmiUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmiUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmiUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmiUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmiUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csmiUpdateTime}
			 	
			 ${before$csmiAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiAddTime" : (lz:join(relateObject,"$csmiAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiAddTime || #request.defines['csmiAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiAddTime":relateObject}" id="form-dl-csmiAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiAddTimeYesNot=="not"?"checked-not":""}${data.csmiAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csmiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csmiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csmiAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csmiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csmiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csmiAddTime}
			
		 ${before$csmiCheckPhoto}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiCheckPhoto" : (lz:join(relateObject,"$csmiCheckPhoto")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiCheckPhoto"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiCheckPhoto || #request.defines['csmiCheckPhoto']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiCheckPhoto":relateObject}" id="form-dl-csmiCheckPhoto">
					<dt>机审照片：</dt>
					<dd>
					<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiCheckPhotoYesNot=="not"?"checked-not":""}${data.csmiCheckPhotoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiCheckPhotoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csmiCheckPhoto}"/>
		 	 		
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csmiCheckPhoto}
			 	
			 ${before$csmiStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmiStatus" : (lz:join(relateObject,"$csmiStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csmiStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmiStatus || #request.defines['csmiStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmiStatus":relateObject}" id="form-dl-csmiStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmiStatusYesNot=="not"?"checked-not":""}${data.csmiStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmiStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csmiStatus==1?"selected":""}>默认</option>
							<option value="2" ${data.csmiStatus==2?"selected":""}>保留</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csmiStatus}