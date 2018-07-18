<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cumId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cumId" : (lz:join(relateObject,"$cumId")))}
			 ${lz:set("cname",lz:join(relateObject,".cumId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cumId || #request.defines['cumId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cumId":relateObject}" id="form-dl-cumId">
					<dt>编　　号：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cumIdYesNot=="not"?"checked-not":""}${data.cumIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cumIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cumId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cumId}
			
			
			
			
			 	
			 ${before$cumHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cumHost" : (lz:join(relateObject,"$cumHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cumHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cumHost || #request.defines['cumHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cumHost":relateObject}" id="form-dl-cumHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cumHostYesNot=="not"?"checked-not":""}${data.cumHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cumHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cumHost}">
				 					${get:SrvHost(data.cumHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cumHost}
			
			
			 ${before$cumArea}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cumArea" : (lz:join(relateObject,"$cumArea")))}
			 ${lz:set("cname",lz:join(relateObject,".cumArea"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cumArea || #request.defines['cumArea']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cumArea":relateObject}" id="form-dl-cumArea">
					<dt>区域：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cumAreaYesNot=="not"?"checked-not":""}${data.cumAreaYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cumAreaYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/configurator/area_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cumArea}">
				 					${get:CsArea(data.cumArea).csaName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/area_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			${after$cumArea}
			
			 	
			 ${before$cumUser}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cumUser" : (lz:join(relateObject,"$cumUser")))}
			 ${lz:set("cname",lz:join(relateObject,".cumUser"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cumUser || #request.defines['cumUser']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cumUser":relateObject}" id="form-dl-cumUser">
					<dt>对接人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cumUserYesNot=="not"?"checked-not":""}${data.cumUserYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cumUserYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cumUser}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cumUser} 	
			 	
			 	
			 	
	 ${before$cumMobile}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cumMobile" : (lz:join(relateObject,"$cumMobile")))}
			 ${lz:set("cname",lz:join(relateObject,".cumMobile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cumMobile || #request.defines['cumMobile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cumMobile":relateObject}" id="form-dl-cumMobile">
					<dt>联系方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cumMobileYesNot=="not"?"checked-not":""}${data.cumMobileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cumMobileYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cumMobile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cumMobile}
			 	
			 	
		
		
		
			 	 	
		 ${before$cumUpdateTime}
			 
			 ${lz:set("注释","*****************截止时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cumUpdateTime" : (lz:join(relateObject,"$cumUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cumUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cumUpdateTime || #request.defines['cumUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cumUpdateTime":relateObject}" id="form-dl-cumUpdateTime">
					<dt>更新时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cumUpdateTimeYesNot=="not"?"checked-not":""}${data.cumUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cumUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cumUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cumUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cumUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csvCutoffTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>截止时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csvCutoffTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************截止时间字段的查询代码结束*****************")}
			
			
	${after$cumUpdateTime}
			 	
			 	
			 	
			
			 	
	 ${before$cumAddTime}
			 
			 ${lz:set("注释","*****************截止时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cumAddTime" : (lz:join(relateObject,"$cumAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cumAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cumAddTime || #request.defines['cumAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cumAddTime":relateObject}" id="form-dl-cumAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cumAddTimeYesNot=="not"?"checked-not":""}${data.cumAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cumAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cumAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cumAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cumAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csvCutoffTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>截止时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csvCutoffTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************截止时间字段的查询代码结束*****************")}
			
			
			${after$cumAddTime}
			 	



 	
			 ${before$cumStatus}
			 
			 ${lz:set("注释","*****************业务状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cumStatus" : (lz:join(relateObject,"$cumStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cumStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cumStatus || #request.defines['cumStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cumStatus":relateObject}" id="form-dl-cumStatus">
					<dt>业务状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cumStatusYesNot=="not"?"checked-not":""}${data.cumStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cumStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							
							<option value="1" ${data.cumStatus==1?"selected":""}>正常</option>
							<option value="-1" ${data.cumStatus==-1?"selected":""}>无效</option>
							
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************业务状态字段的查询代码结束*****************")}
			
			
			${after$cumStatus}
 

			
 
			 	