<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csgrId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgrId" : (lz:join(relateObject,"$csgrId")))}
			 ${lz:set("cname",lz:join(relateObject,".csgrId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgrId || #request.defines['csgrId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgrId":relateObject}" id="form-dl-csgrId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgrIdYesNot=="not"?"checked-not":""}${data.csgrIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgrIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csgrId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csgrId}
			 	
			 ${before$csgrHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgrHost" : (lz:join(relateObject,"$csgrHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csgrHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgrHost || #request.defines['csgrHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgrHost":relateObject}" id="form-dl-csgrHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgrHostYesNot=="not"?"checked-not":""}${data.csgrHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgrHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csgrHost}">
				 					${get:SrvHost(data.csgrHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csgrHost}
			 	
			 ${before$csgrMember}
			 
			 ${lz:set("注释","*****************会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgrMember" : (lz:join(relateObject,"$csgrMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csgrMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgrMember || #request.defines['csgrMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgrMember":relateObject}" id="form-dl-csgrMember">
					<dt>会　　员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgrMemberYesNot=="not"?"checked-not":""}${data.csgrMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgrMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csgrHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csgrMember).csmName}" value="${data.csgrMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csgrMember']>0">
				${lz:set("注释","****当会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csgrMember")}
			  	<input type="hidden" name="csgrMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csgrMember}
			 	
			 ${before$csgrDesc}
			 
			 ${lz:set("注释","*****************描述字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgrDesc" : (lz:join(relateObject,"$csgrDesc")))}
			 ${lz:set("cname",lz:join(relateObject,".csgrDesc"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgrDesc || #request.defines['csgrDesc']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgrDesc":relateObject}" id="form-dl-csgrDesc">
					<dt>描　　述：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgrDescYesNot=="not"?"checked-not":""}${data.csgrDescYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgrDescYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csgrDesc}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************描述字段的查询代码结束*****************")}
			
			
			${after$csgrDesc}
			 	
			 ${before$csgrType}
			 
			 ${lz:set("注释","*****************类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgrType" : (lz:join(relateObject,"$csgrType")))}
			 ${lz:set("cname",lz:join(relateObject,".csgrType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgrType || #request.defines['csgrType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgrType":relateObject}" id="form-dl-csgrType">
					<dt>类　　型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgrTypeYesNot=="not"?"checked-not":""}${data.csgrTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgrTypeYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csgrType}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************类型字段的查询代码结束*****************")}
			
			
			${after$csgrType}
			 	
			 ${before$csgrAmount}
			 
			 ${lz:set("注释","*****************变化值字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgrAmount" : (lz:join(relateObject,"$csgrAmount")))}
			 ${lz:set("cname",lz:join(relateObject,".csgrAmount"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgrAmount || #request.defines['csgrAmount']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgrAmount":relateObject}" id="form-dl-csgrAmount">
					<dt>变&nbsp;&nbsp;化&nbsp;&nbsp;值：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgrAmountYesNot=="not"?"checked-not":""}${data.csgrAmountYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgrAmountYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csgrAmountMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csgrAmountMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************变化值字段的查询代码结束*****************")}
			
			
			${after$csgrAmount}
			 	
			 ${before$csgrRelate}
			 
			 ${lz:set("注释","*****************关联数据字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgrRelate" : (lz:join(relateObject,"$csgrRelate")))}
			 ${lz:set("cname",lz:join(relateObject,".csgrRelate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgrRelate || #request.defines['csgrRelate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgrRelate":relateObject}" id="form-dl-csgrRelate">
					<dt>关联数据：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgrRelateYesNot=="not"?"checked-not":""}${data.csgrRelateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgrRelateYesNot}"/>
			 		<input title="请输入动态查询" class="combox" generic="csgrRelateGeneric" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:generic(data.csgrRelate)}" value="${data.csgrRelate}"/>
			 		${lz:set("models",get:models("CsTrouble,CsViolat,CsOrder"))}
				 	<select id="csgrRelateGeneric" style="display:none;">
				 		<option value="">--选择模块--</option>
				 		<s:iterator value="#request.models" id="item" status="i">
				 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
				 		</s:iterator>
				 	</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联数据字段的查询代码结束*****************")}
			
			
			${after$csgrRelate}
			 	
			 ${before$csgrUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgrUpdateTime" : (lz:join(relateObject,"$csgrUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csgrUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgrUpdateTime || #request.defines['csgrUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgrUpdateTime":relateObject}" id="form-dl-csgrUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgrUpdateTimeYesNot=="not"?"checked-not":""}${data.csgrUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgrUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csgrUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csgrUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csgrUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csgrUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csgrUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csgrUpdateTime}
			 	
			 ${before$csgrAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgrAddTime" : (lz:join(relateObject,"$csgrAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csgrAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgrAddTime || #request.defines['csgrAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgrAddTime":relateObject}" id="form-dl-csgrAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgrAddTimeYesNot=="not"?"checked-not":""}${data.csgrAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgrAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csgrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csgrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csgrAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csgrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csgrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csgrAddTime}
			 	
			 ${before$csgrStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgrStatus" : (lz:join(relateObject,"$csgrStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csgrStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgrStatus || #request.defines['csgrStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgrStatus":relateObject}" id="form-dl-csgrStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgrStatusYesNot=="not"?"checked-not":""}${data.csgrStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgrStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csgrStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csgrStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csgrStatus}