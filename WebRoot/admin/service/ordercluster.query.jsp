<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csocId}
			 
			 ${lz:set("注释","*****************订单编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csocId" : (lz:join(relateObject,"$csocId")))}
			 ${lz:set("cname",lz:join(relateObject,".csocId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csocId || #request.defines['csocId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csocId":relateObject}" id="form-dl-csocId">
					<dt>订单编号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csoIdYesNot=="not"?"checked-not":""}${data.csoIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csoIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csocId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************订单编号字段的查询代码结束*****************")}
			
			
			${after$csocId}
			 	
			 ${before$csocHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csocHost" : (lz:join(relateObject,"$csocHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csocHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csocHost || #request.defines['csocHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csocHost":relateObject}" id="form-dl-csocHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csocHostYesNot=="not"?"checked-not":""}${data.csocHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csocHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csocHost}">
				 					${get:SrvHost(data.csocHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csocHost}
			 	
			 ${before$csocPayMember}
			 
			 ${lz:set("注释","*****************支付会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csocPayMember" : (lz:join(relateObject,"$csocPayMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csocPayMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csocPayMember || #request.defines['csocPayMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csocPayMember":relateObject}" id="form-dl-csocPayMember">
					<dt>支付会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csocPayMemberYesNot=="not"?"checked-not":""}${data.csocPayMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csocPayMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csocHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csocPayMember).csmName}" value="${data.csocPayMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************支付会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csocPayMember']>0">
				${lz:set("注释","****当支付会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csocPayMember")}
			  	<input type="hidden" name="csocPayMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csocPayMember}
			 	
			 ${before$csocMobile}
			 
			 ${lz:set("注释","*****************手机号码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csocMobile" : (lz:join(relateObject,"$csocMobile")))}
			 ${lz:set("cname",lz:join(relateObject,".csocMobile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csocMobile || #request.defines['csocMobile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csocMobile":relateObject}" id="form-dl-csocMobile">
					<dt>手机号码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csocMobileYesNot=="not"?"checked-not":""}${data.csocMobileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csocMobileYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csocMobile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************使用手机号码字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csocMobile']>0">
				${lz:set("注释","****当使用手机号码字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csocMobile")}
			  	<input type="hidden" name="csocMobile$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csocMobile}
			
			${before$csocStartTime}
			 
			 ${lz:set("注释","*****************添加订单开始时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csocStartTime" : (lz:join(relateObject,"$csocStartTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csocStartTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csocStartTime || #request.defines['csocStartTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csocStartTime":relateObject}" id="form-dl-csocStartTime">
					<dt>订单开始时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csocStartTimeYesNot=="not"?"checked-not":""}${data.csocStartTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csocStartTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csocStartTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(csocStartTimedTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csocStartTimeExp}">
			 		
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加订单开始时间字段的查询代码结束*****************")}
			
			
			${after$csocStartTime}
			
			${before$csocFinishTime}
			 
			 ${lz:set("注释","*****************添加订单结束时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csocFinishTime" : (lz:join(relateObject,"$csocFinishTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csocFinishTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csocFinishTime || #request.defines['csocFinishTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csocFinishTime":relateObject}" id="form-dl-csocFinishTime">
					<dt>订单结束时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csocFinishTimeYesNot=="not"?"checked-not":""}${data.csocFinishTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csocFinishTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csocFinishTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(csocFinishTimedTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csocFinishTimeExp}">
			 		
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加订单结束时间字段的查询代码结束*****************")}
			
			
			${after$csocFinishTime}  
			 
			 
			 	
			
			 ${before$csocAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csocAddTime" : (lz:join(relateObject,"$csocAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csocAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csocAddTime || #request.defines['csocAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csocAddTime":relateObject}" id="form-dl-csocAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csocAddTimeYesNot=="not"?"checked-not":""}${data.csocAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csocAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csocAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csocAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csocAddTimeExp}">
			 		
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csocAddTime}
			
			
			${before$csocUpdateTime}
			 
			 ${lz:set("注释","*****************添加修改字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csocUpdateTime" : (lz:join(relateObject,"$csocUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csocUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csocUpdateTime || #request.defines['csocUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csocUpdateTime":relateObject}" id="form-dl-csocUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csocUpdateTimeYesNot=="not"?"checked-not":""}${data.csocUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csocUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csocUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(csocUpdateTimedTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csocUpdateTimeExp}">
			 		
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csocUpdateTime}
			 	
			 
			 
			 	
			 ${before$csocStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csocStatus" : (lz:join(relateObject,"$csocStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csocStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csocStatus || #request.defines['csocStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csocStatus":relateObject}" id="form-dl-csocStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csocStatusYesNot=="not"?"checked-not":""}${data.csocStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csocStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csocStatus==0?"selected":""}>已预定</option>
							<option value="1" ${data.csocStatus==1?"selected":""}>使用中</option>
							<option value="3" ${data.csocStatus==3?"selected":""}>已取消</option>
							<option value="4" ${data.csocStatus==4?"selected":""}>已完成</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csocStatus}