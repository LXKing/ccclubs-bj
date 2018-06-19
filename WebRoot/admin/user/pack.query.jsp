<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csupId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csupId" : (lz:join(relateObject,"$csupId")))}
			 ${lz:set("cname",lz:join(relateObject,".csupId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csupId || #request.defines['csupId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csupId":relateObject}" id="form-dl-csupId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupIdYesNot=="not"?"checked-not":""}${data.csupIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csupId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csupId}
			 	
			 ${before$csupHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csupHost" : (lz:join(relateObject,"$csupHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csupHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csupHost || #request.defines['csupHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csupHost":relateObject}" id="form-dl-csupHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupHostYesNot=="not"?"checked-not":""}${data.csupHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csupHost}">
				 					${get:SrvHost(data.csupHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csupHost}
			 	
			 ${before$csupPack}
			 
			 ${lz:set("注释","*****************所属系统套餐字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csupPack" : (lz:join(relateObject,"$csupPack")))}
			 ${lz:set("cname",lz:join(relateObject,".csupPack"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csupPack || #request.defines['csupPack']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csupPack":relateObject}" id="form-dl-csupPack">
					<dt>所属系统套餐：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupPackYesNot=="not"?"checked-not":""}${data.csupPackYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupPackYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/fee/pack_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csupPack}">
				 					${get:CsPack(data.csupPack).cspName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/pack_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属系统套餐字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csupPack']>0">
				${lz:set("注释","****当所属系统套餐字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csupPack")}
			  	<input type="hidden" name="csupPack$on" id="CsPack$on" value="true"/>
			  	<jsp:include page="/admin/fee/pack.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csupPack}
			 	
			 ${before$csupMember}
			 
			 ${lz:set("注释","*****************所属会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csupMember" : (lz:join(relateObject,"$csupMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csupMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csupMember || #request.defines['csupMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csupMember":relateObject}" id="form-dl-csupMember">
					<dt>所属会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupMemberYesNot=="not"?"checked-not":""}${data.csupMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csupHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csupMember).csmName}" value="${data.csupMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csupMember']>0">
				${lz:set("注释","****当所属会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csupMember")}
			  	<input type="hidden" name="csupMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csupMember}
			 	
			 ${before$csupUntilTime}
			 
			 ${lz:set("注释","*****************有效期至字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csupUntilTime" : (lz:join(relateObject,"$csupUntilTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csupUntilTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csupUntilTime || #request.defines['csupUntilTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csupUntilTime":relateObject}" id="form-dl-csupUntilTime">
					<dt>有效期至：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupUntilTimeYesNot=="not"?"checked-not":""}${data.csupUntilTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupUntilTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csupUntilTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csupUntilTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csupUntilTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csupUntilTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>有效期至：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csupUntilTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************有效期至字段的查询代码结束*****************")}
			
			
			${after$csupUntilTime}
			 	
			 ${before$csupUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csupUpdateTime" : (lz:join(relateObject,"$csupUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csupUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csupUpdateTime || #request.defines['csupUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csupUpdateTime":relateObject}" id="form-dl-csupUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupUpdateTimeYesNot=="not"?"checked-not":""}${data.csupUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csupUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csupUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csupUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csupUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csupUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csupUpdateTime}
			 	
			 ${before$csupAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csupAddTime" : (lz:join(relateObject,"$csupAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csupAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csupAddTime || #request.defines['csupAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csupAddTime":relateObject}" id="form-dl-csupAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupAddTimeYesNot=="not"?"checked-not":""}${data.csupAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csupAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csupAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csupAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csupAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csupAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csupAddTime}
			 	
			 ${before$csupState}
			 
			 ${lz:set("注释","*****************续订状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csupState" : (lz:join(relateObject,"$csupState")))}
			 ${lz:set("cname",lz:join(relateObject,".csupState"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csupState || #request.defines['csupState']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csupState":relateObject}" id="form-dl-csupState">
					<dt>续订状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupStateYesNot=="not"?"checked-not":""}${data.csupStateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupStateYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csupState==0?"selected":""}>不续订</option>
							<option value="1" ${data.csupState==1?"selected":""}>待续订</option>
							<option value="2" ${data.csupState==2?"selected":""}>已续订</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************续订状态字段的查询代码结束*****************")}
			
			
			${after$csupState}
			 	
			 ${before$csupStatus}
			 
			 ${lz:set("注释","*****************套餐状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csupStatus" : (lz:join(relateObject,"$csupStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csupStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csupStatus || #request.defines['csupStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csupStatus":relateObject}" id="form-dl-csupStatus">
					<dt>套餐状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupStatusYesNot=="not"?"checked-not":""}${data.csupStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csupStatus==1?"selected":""}>有效</option>
							<option value="0" ${data.csupStatus==0?"selected":""}>无效</option>
							<option value="2" ${data.csupStatus==2?"selected":""}>过期</option>
							<option value="3" ${data.csupStatus==3?"selected":""}>用完</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************套餐状态字段的查询代码结束*****************")}
			
			
			${after$csupStatus}