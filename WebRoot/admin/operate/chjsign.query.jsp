<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cscsId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsId" : (lz:join(relateObject,"$cscsId")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsId || #request.defines['cscsId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsId":relateObject}" id="form-dl-cscsId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsIdYesNot=="not"?"checked-not":""}${data.cscsIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cscsId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cscsId}
			 	
			 ${before$cscsHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsHost" : (lz:join(relateObject,"$cscsHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsHost || #request.defines['cscsHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsHost":relateObject}" id="form-dl-cscsHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsHostYesNot=="not"?"checked-not":""}${data.cscsHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscsHost}">
				 					${get:SrvHost(data.cscsHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cscsHost}
			 	
			 ${before$cscsName}
			 
			 ${lz:set("注释","*****************姓名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsName" : (lz:join(relateObject,"$cscsName")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsName || #request.defines['cscsName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsName":relateObject}" id="form-dl-cscsName">
					<dt>姓　　名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsNameYesNot=="not"?"checked-not":""}${data.cscsNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscsName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************姓名字段的查询代码结束*****************")}
			
			
			${after$cscsName}
			 	
			 ${before$cscsMobile}
			 
			 ${lz:set("注释","*****************手机号码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsMobile" : (lz:join(relateObject,"$cscsMobile")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsMobile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsMobile || #request.defines['cscsMobile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsMobile":relateObject}" id="form-dl-cscsMobile">
					<dt>手机号码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsMobileYesNot=="not"?"checked-not":""}${data.cscsMobileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsMobileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscsMobile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************手机号码字段的查询代码结束*****************")}
			
			
			${after$cscsMobile}
			 	
			 ${before$cscsMember}
			 
			 ${lz:set("注释","*****************关联会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsMember" : (lz:join(relateObject,"$cscsMember")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsMember || #request.defines['cscsMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsMember":relateObject}" id="form-dl-cscsMember">
					<dt>关联会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsMemberYesNot=="not"?"checked-not":""}${data.cscsMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={cscsHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.cscsMember).csmName}" value="${data.cscsMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscsMember']>0">
				${lz:set("注释","****当关联会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscsMember")}
			  	<input type="hidden" name="cscsMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscsMember}
			 	
			 ${before$cscsTakeTime}
			 
			 ${lz:set("注释","*****************取车时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsTakeTime" : (lz:join(relateObject,"$cscsTakeTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsTakeTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsTakeTime || #request.defines['cscsTakeTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsTakeTime":relateObject}" id="form-dl-cscsTakeTime">
					<dt>取车时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsTakeTimeYesNot=="not"?"checked-not":""}${data.cscsTakeTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsTakeTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscsTakeTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscsTakeTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscsTakeTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscsTakeTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>取车时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscsTakeTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************取车时间字段的查询代码结束*****************")}
			
			
			${after$cscsTakeTime}
			 	
			 ${before$cscsRetTime}
			 
			 ${lz:set("注释","*****************还车时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsRetTime" : (lz:join(relateObject,"$cscsRetTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsRetTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsRetTime || #request.defines['cscsRetTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsRetTime":relateObject}" id="form-dl-cscsRetTime">
					<dt>还车时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsRetTimeYesNot=="not"?"checked-not":""}${data.cscsRetTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsRetTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscsRetTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscsRetTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscsRetTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscsRetTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>还车时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscsRetTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************还车时间字段的查询代码结束*****************")}
			
			
			${after$cscsRetTime}
			 	
			 ${before$cscsModel}
			 
			 ${lz:set("注释","*****************预定车型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsModel" : (lz:join(relateObject,"$cscsModel")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsModel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsModel || #request.defines['cscsModel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsModel":relateObject}" id="form-dl-cscsModel">
					<dt>预定车型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsModelYesNot=="not"?"checked-not":""}${data.cscsModelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsModelYesNot}"/>
			 			<input title="请输入车型车型名称进行查询" class="combox" action="${basePath}${proname}/object/carmodel_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCarModel(data.cscsModel).cscmName}" value="${data.cscsModel}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************预定车型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscsModel']>0">
				${lz:set("注释","****当预定车型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscsModel")}
			  	<input type="hidden" name="cscsModel$on" id="CsCarModel$on" value="true"/>
			  	<jsp:include page="/admin/object/carmodel.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscsModel}
			 	
			 ${before$cscsOutlets}
			 
			 ${lz:set("注释","*****************预定网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsOutlets" : (lz:join(relateObject,"$cscsOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsOutlets || #request.defines['cscsOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsOutlets":relateObject}" id="form-dl-cscsOutlets">
					<dt>预定网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsOutletsYesNot=="not"?"checked-not":""}${data.cscsOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsOutletsYesNot}"/>
			 			<input title="请输入网点名称进行查询" class="combox" action="${basePath}${proname}/object/outlets_query.do?value={param}&csoHost={cscsHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOutlets(data.cscsOutlets).csoName}" value="${data.cscsOutlets}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************预定网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscsOutlets']>0">
				${lz:set("注释","****当预定网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscsOutlets")}
			  	<input type="hidden" name="cscsOutlets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscsOutlets}
			 	
			 ${before$cscsLocked}
			 
			 ${lz:set("注释","*****************预定套餐字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsLocked" : (lz:join(relateObject,"$cscsLocked")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsLocked"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsLocked || #request.defines['cscsLocked']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsLocked":relateObject}" id="form-dl-cscsLocked">
					<dt>预定套餐：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsLockedYesNot=="not"?"checked-not":""}${data.cscsLockedYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsLockedYesNot}"/>
			 			<input title="请输入系统套餐套餐名称进行查询" class="combox" action="${basePath}${proname}/fee/pack_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsPack(data.cscsLocked).cspName}" value="${data.cscsLocked}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/pack_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************预定套餐字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscsLocked']>0">
				${lz:set("注释","****当预定套餐字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscsLocked")}
			  	<input type="hidden" name="cscsLocked$on" id="CsPack$on" value="true"/>
			  	<jsp:include page="/admin/fee/pack.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscsLocked}
			 	
			 ${before$cscsLockTime}
			 
			 ${lz:set("注释","*****************预定时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsLockTime" : (lz:join(relateObject,"$cscsLockTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsLockTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsLockTime || #request.defines['cscsLockTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsLockTime":relateObject}" id="form-dl-cscsLockTime">
					<dt>预定时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsLockTimeYesNot=="not"?"checked-not":""}${data.cscsLockTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsLockTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscsLockTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscsLockTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscsLockTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscsLockTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>预定时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscsLockTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************预定时间字段的查询代码结束*****************")}
			
			
			${after$cscsLockTime}
			 	
			 ${before$cscsYear}
			 
			 ${lz:set("注释","*****************年份字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsYear" : (lz:join(relateObject,"$cscsYear")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsYear"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsYear || #request.defines['cscsYear']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsYear":relateObject}" id="form-dl-cscsYear">
					<dt>年　　份：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsYearYesNot=="not"?"checked-not":""}${data.cscsYearYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsYearYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="2014" ${data.cscsYear==2014?"selected":""}>2014年</option>
							<option value="2015" ${data.cscsYear==2015?"selected":""}>2015年</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************年份字段的查询代码结束*****************")}
			
			
			${after$cscsYear}
			 	
			 ${before$cscsVisitChannel}
			 
			 ${lz:set("注释","*****************渠道来源字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsVisitChannel" : (lz:join(relateObject,"$cscsVisitChannel")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsVisitChannel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsVisitChannel || #request.defines['cscsVisitChannel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsVisitChannel":relateObject}" id="form-dl-cscsVisitChannel">
					<dt>渠道来源：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsVisitChannelYesNot=="not"?"checked-not":""}${data.cscsVisitChannelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsVisitChannelYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/extension/channel_query.do?size=-1&cscHost={cscsHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscsVisitChannel}">
				 					${get:CsChannel(data.cscsVisitChannel).cscName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/extension/channel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************渠道来源字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscsVisitChannel']>0">
				${lz:set("注释","****当渠道来源字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscsVisitChannel")}
			  	<input type="hidden" name="cscsVisitChannel$on" id="CsChannel$on" value="true"/>
			  	<jsp:include page="/admin/extension/channel.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscsVisitChannel}
			 	
			 ${before$cscsUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsUpdateTime" : (lz:join(relateObject,"$cscsUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsUpdateTime || #request.defines['cscsUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsUpdateTime":relateObject}" id="form-dl-cscsUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsUpdateTimeYesNot=="not"?"checked-not":""}${data.cscsUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscsUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscsUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscsUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscsUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscsUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cscsUpdateTime}
			 	
			 ${before$cscsAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsAddTime" : (lz:join(relateObject,"$cscsAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsAddTime || #request.defines['cscsAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsAddTime":relateObject}" id="form-dl-cscsAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsAddTimeYesNot=="not"?"checked-not":""}${data.cscsAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscsAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscsAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscsAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscsAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscsAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cscsAddTime}
			 	
			 ${before$cscsStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsStatus" : (lz:join(relateObject,"$cscsStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsStatus || #request.defines['cscsStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsStatus":relateObject}" id="form-dl-cscsStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsStatusYesNot=="not"?"checked-not":""}${data.cscsStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cscsStatus==0?"selected":""}>已报名</option>
							<option value="1" ${data.cscsStatus==1?"selected":""}>已预定</option>
							<option value="2" ${data.cscsStatus==2?"selected":""}>已购买</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cscsStatus}