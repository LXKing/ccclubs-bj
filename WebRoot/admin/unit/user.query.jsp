<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csuuId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuuId" : (lz:join(relateObject,"$csuuId")))}
			 ${lz:set("cname",lz:join(relateObject,".csuuId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuuId || #request.defines['csuuId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuuId":relateObject}" id="form-dl-csuuId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuuIdYesNot=="not"?"checked-not":""}${data.csuuIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuuIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csuuId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csuuId}
			 	
			 ${before$csuuHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuuHost" : (lz:join(relateObject,"$csuuHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csuuHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuuHost || #request.defines['csuuHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuuHost":relateObject}" id="form-dl-csuuHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuuHostYesNot=="not"?"checked-not":""}${data.csuuHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuuHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csuuHost}">
				 					${get:SrvHost(data.csuuHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csuuHost}
			 	
			 ${before$csuuUsername}
			 
			 ${lz:set("注释","*****************登录帐号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuuUsername" : (lz:join(relateObject,"$csuuUsername")))}
			 ${lz:set("cname",lz:join(relateObject,".csuuUsername"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuuUsername || #request.defines['csuuUsername']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuuUsername":relateObject}" id="form-dl-csuuUsername">
					<dt>登录帐号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuuUsernameYesNot=="not"?"checked-not":""}${data.csuuUsernameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuuUsernameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csuuUsername}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************登录帐号字段的查询代码结束*****************")}
			
			
			${after$csuuUsername}
			 	
			 ${before$csuuUnitInfo}
			 
			 ${lz:set("注释","*****************所属单位字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuuUnitInfo" : (lz:join(relateObject,"$csuuUnitInfo")))}
			 ${lz:set("cname",lz:join(relateObject,".csuuUnitInfo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuuUnitInfo || #request.defines['csuuUnitInfo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuuUnitInfo":relateObject}" id="form-dl-csuuUnitInfo">
					<dt>所属单位：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuuUnitInfoYesNot=="not"?"checked-not":""}${data.csuuUnitInfoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuuUnitInfoYesNot}"/>
			 			<input title="请输入企业信息名称进行查询" class="combox" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csuuHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsUnitInfo(data.csuuUnitInfo).csuiName}" value="${data.csuuUnitInfo}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属单位字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csuuUnitInfo']>0">
				${lz:set("注释","****当所属单位字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csuuUnitInfo")}
			  	<input type="hidden" name="csuuUnitInfo$on" id="CsUnitInfo$on" value="true"/>
			  	<jsp:include page="/admin/unit/info.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csuuUnitInfo}
			 	
			 ${before$csuuIsRoot}
			 
			 ${lz:set("注释","*****************系统管理员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuuIsRoot" : (lz:join(relateObject,"$csuuIsRoot")))}
			 ${lz:set("cname",lz:join(relateObject,".csuuIsRoot"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuuIsRoot || #request.defines['csuuIsRoot']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuuIsRoot":relateObject}" id="form-dl-csuuIsRoot">
					<dt>系统管理员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuuIsRootYesNot=="not"?"checked-not":""}${data.csuuIsRootYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuuIsRootYesNot}"/>
			 		<input type="radio" value="" name="${NAME}" id="${NAME}" ${data.csuuIsRoot==null?"checked":""}/>
					全部&nbsp;&nbsp;
			 	     <input type="radio" value="1" name="${NAME}" id="${NAME}" ${data.csuuIsRoot=='true'?"checked":""}/>
					是&nbsp;&nbsp;
					<input type="radio" value="0" name="${NAME}" id="${NAME}" ${data.csuuIsRoot=='false'?"checked":""}/>否
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************系统管理员字段的查询代码结束*****************")}
			
			
			${after$csuuIsRoot}
			 	
			 ${before$csuuRealname}
			 
			 ${lz:set("注释","*****************真实姓名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuuRealname" : (lz:join(relateObject,"$csuuRealname")))}
			 ${lz:set("cname",lz:join(relateObject,".csuuRealname"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuuRealname || #request.defines['csuuRealname']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuuRealname":relateObject}" id="form-dl-csuuRealname">
					<dt>真实姓名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuuRealnameYesNot=="not"?"checked-not":""}${data.csuuRealnameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuuRealnameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csuuRealname}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************真实姓名字段的查询代码结束*****************")}
			
			
			${after$csuuRealname}
			 	
			 ${before$csuuMobile}
			 
			 ${lz:set("注释","*****************联系电话字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuuMobile" : (lz:join(relateObject,"$csuuMobile")))}
			 ${lz:set("cname",lz:join(relateObject,".csuuMobile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuuMobile || #request.defines['csuuMobile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuuMobile":relateObject}" id="form-dl-csuuMobile">
					<dt>联系电话：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuuMobileYesNot=="not"?"checked-not":""}${data.csuuMobileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuuMobileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csuuMobile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************联系电话字段的查询代码结束*****************")}
			
			
			${after$csuuMobile}
			 	
			 ${before$csuuAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuuAddTime" : (lz:join(relateObject,"$csuuAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csuuAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuuAddTime || #request.defines['csuuAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuuAddTime":relateObject}" id="form-dl-csuuAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuuAddTimeYesNot=="not"?"checked-not":""}${data.csuuAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuuAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csuuAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csuuAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csuuAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csuuAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csuuAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csuuAddTime}
			 	
			 ${before$csuuStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csuuStatus" : (lz:join(relateObject,"$csuuStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csuuStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csuuStatus || #request.defines['csuuStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csuuStatus":relateObject}" id="form-dl-csuuStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csuuStatusYesNot=="not"?"checked-not":""}${data.csuuStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csuuStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csuuStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csuuStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csuuStatus}