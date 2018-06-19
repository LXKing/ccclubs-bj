<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csulId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csulId" : (lz:join(relateObject,"$csulId")))}
			 ${lz:set("cname",lz:join(relateObject,".csulId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csulId || #request.defines['csulId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csulId":relateObject}" id="form-dl-csulId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csulIdYesNot=="not"?"checked-not":""}${data.csulIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csulIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csulId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csulId}
			 	
			 ${before$csulTitle}
			 
			 ${lz:set("注释","*****************升级名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csulTitle" : (lz:join(relateObject,"$csulTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".csulTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csulTitle || #request.defines['csulTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csulTitle":relateObject}" id="form-dl-csulTitle">
					<dt>升级名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csulTitleYesNot=="not"?"checked-not":""}${data.csulTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csulTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csulTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************升级名称字段的查询代码结束*****************")}
			
			
			${after$csulTitle}
			 	
			 ${before$csulType}
			 
			 ${lz:set("注释","*****************升级类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csulType" : (lz:join(relateObject,"$csulType")))}
			 ${lz:set("cname",lz:join(relateObject,".csulType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csulType || #request.defines['csulType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csulType":relateObject}" id="form-dl-csulType">
					<dt>升级类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csulTypeYesNot=="not"?"checked-not":""}${data.csulTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csulTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csulType==0?"selected":""}>系统</option>
							<option value="1" ${data.csulType==1?"selected":""}>后台</option>
							<option value="2" ${data.csulType==2?"selected":""}>网站</option>
							<option value="3" ${data.csulType==3?"selected":""}>微信</option>
							<option value="4" ${data.csulType==4?"selected":""}>支付宝</option>
							<option value="5" ${data.csulType==5?"selected":""}>APP</option>
							<option value="6" ${data.csulType==6?"selected":""}>API</option>
							<option value="7" ${data.csulType==7?"selected":""}>车机</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************升级类型字段的查询代码结束*****************")}
			
			
			${after$csulType}
			 	
			 ${before$csulDescribe}
			 
			 ${lz:set("注释","*****************升级描述字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csulDescribe" : (lz:join(relateObject,"$csulDescribe")))}
			 ${lz:set("cname",lz:join(relateObject,".csulDescribe"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csulDescribe || #request.defines['csulDescribe']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csulDescribe":relateObject}" id="form-dl-csulDescribe">
					<dt>升级描述：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csulDescribeYesNot=="not"?"checked-not":""}${data.csulDescribeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csulDescribeYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="1024" name="${NAME}" id="${NAME}"  value="${data.csulDescribe}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************升级描述字段的查询代码结束*****************")}
			
			
			${after$csulDescribe}
			 	
			 ${before$csulOnTime}
			 
			 ${lz:set("注释","*****************上线日期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csulOnTime" : (lz:join(relateObject,"$csulOnTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csulOnTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csulOnTime || #request.defines['csulOnTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csulOnTime":relateObject}" id="form-dl-csulOnTime">
					<dt>上线日期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csulOnTimeYesNot=="not"?"checked-not":""}${data.csulOnTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csulOnTimeYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csulOnTimeStart,"yyyy-MM-dd")}" end="${lz:date(data.csulOnTimeEnd,"yyyy-MM-dd")}" exp="${data.csulOnTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csulOnTimeStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>上线日期：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csulOnTimeEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************上线日期字段的查询代码结束*****************")}
			
			
			${after$csulOnTime}
			 	
			 ${before$csulAdder}
			 
			 ${lz:set("注释","*****************添加人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csulAdder" : (lz:join(relateObject,"$csulAdder")))}
			 ${lz:set("cname",lz:join(relateObject,".csulAdder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csulAdder || #request.defines['csulAdder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csulAdder":relateObject}" id="form-dl-csulAdder">
					<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csulAdderYesNot=="not"?"checked-not":""}${data.csulAdderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csulAdderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csulAdder}">
				 					${get:SrvUser(data.csulAdder).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人字段的查询代码结束*****************")}
			
			
			${after$csulAdder}
			 	
			 ${before$csulUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csulUpdateTime" : (lz:join(relateObject,"$csulUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csulUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csulUpdateTime || #request.defines['csulUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csulUpdateTime":relateObject}" id="form-dl-csulUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csulUpdateTimeYesNot=="not"?"checked-not":""}${data.csulUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csulUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csulUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csulUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csulUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csulUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csulUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csulUpdateTime}
			 	
			 ${before$csulAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csulAddTime" : (lz:join(relateObject,"$csulAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csulAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csulAddTime || #request.defines['csulAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csulAddTime":relateObject}" id="form-dl-csulAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csulAddTimeYesNot=="not"?"checked-not":""}${data.csulAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csulAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csulAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csulAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csulAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csulAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csulAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csulAddTime}
			 	
			 ${before$csulStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csulStatus" : (lz:join(relateObject,"$csulStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csulStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csulStatus || #request.defines['csulStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csulStatus":relateObject}" id="form-dl-csulStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csulStatusYesNot=="not"?"checked-not":""}${data.csulStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csulStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csulStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csulStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csulStatus}