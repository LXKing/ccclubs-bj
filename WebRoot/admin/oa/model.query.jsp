<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$smId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "smId" : (lz:join(relateObject,"$smId")))}
			 ${lz:set("cname",lz:join(relateObject,".smId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.smId || #request.defines['smId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"smId":relateObject}" id="form-dl-smId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.smIdYesNot=="not"?"checked-not":""}${data.smIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.smIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.smId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$smId}
			 	
			 ${before$smName}
			 
			 ${lz:set("注释","*****************对象名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "smName" : (lz:join(relateObject,"$smName")))}
			 ${lz:set("cname",lz:join(relateObject,".smName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.smName || #request.defines['smName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"smName":relateObject}" id="form-dl-smName">
					<dt>对象名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.smNameYesNot=="not"?"checked-not":""}${data.smNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.smNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.smName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************对象名称字段的查询代码结束*****************")}
			
			
			${after$smName}
			 	
			 ${before$smAction}
			 
			 ${lz:set("注释","*****************英文名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "smAction" : (lz:join(relateObject,"$smAction")))}
			 ${lz:set("cname",lz:join(relateObject,".smAction"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.smAction || #request.defines['smAction']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"smAction":relateObject}" id="form-dl-smAction">
					<dt>英文名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.smActionYesNot=="not"?"checked-not":""}${data.smActionYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.smActionYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.smAction}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************英文名称字段的查询代码结束*****************")}
			
			
			${after$smAction}
			 	
			 ${before$smNamespace}
			 
			 ${lz:set("注释","*****************相对路径字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "smNamespace" : (lz:join(relateObject,"$smNamespace")))}
			 ${lz:set("cname",lz:join(relateObject,".smNamespace"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.smNamespace || #request.defines['smNamespace']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"smNamespace":relateObject}" id="form-dl-smNamespace">
					<dt>相对路径：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.smNamespaceYesNot=="not"?"checked-not":""}${data.smNamespaceYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.smNamespaceYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.smNamespace}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************相对路径字段的查询代码结束*****************")}
			
			
			${after$smNamespace}
			 	
			 ${before$smDisplay}
			 
			 ${lz:set("注释","*****************结构类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "smDisplay" : (lz:join(relateObject,"$smDisplay")))}
			 ${lz:set("cname",lz:join(relateObject,".smDisplay"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.smDisplay || #request.defines['smDisplay']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"smDisplay":relateObject}" id="form-dl-smDisplay">
					<dt>结构类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.smDisplayYesNot=="not"?"checked-not":""}${data.smDisplayYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.smDisplayYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="table" ${data.smDisplay=='table'?"selected":""}>普通列表</option>
							<option value="tree" ${data.smDisplay=='tree'?"selected":""}>层级树形</option>
							<option value="form" ${data.smDisplay=='form'?"selected":""}>单条表单</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************结构类型字段的查询代码结束*****************")}
			
			
			${after$smDisplay}
			 	
			 ${before$smFunctions}
			 
			 ${lz:set("注释","*****************支持功能字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "smFunctions" : (lz:join(relateObject,"$smFunctions")))}
			 ${lz:set("cname",lz:join(relateObject,".smFunctions"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.smFunctions || #request.defines['smFunctions']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"smFunctions":relateObject}" id="form-dl-smFunctions">
					<dt>支持功能：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.smFunctionsYesNot=="not"?"checked-not":""}${data.smFunctionsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.smFunctionsYesNot}"/>
				 		<select id="${NAME}" name="${NAME}" >
			 				<option value="">全部</option>
							<option value="#1#" ${data.smFunctions=='#1#'?"selected":""}>查询</option>
							<option value="#2#" ${data.smFunctions=='#2#'?"selected":""}>添加</option>
							<option value="#3#" ${data.smFunctions=='#3#'?"selected":""}>修改</option>
							<option value="#4#" ${data.smFunctions=='#4#'?"selected":""}>删除</option>
							<option value="#5#" ${data.smFunctions=='#5#'?"selected":""}>导出</option>
							<option value="#6#" ${data.smFunctions=='#6#'?"selected":""}>报表</option>
							<option value="#7#" ${data.smFunctions=='#7#'?"selected":""}>工作流</option>
						</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************支持功能字段的查询代码结束*****************")}
			
			
			${after$smFunctions}
			 	
			 ${before$smEditor}
			 
			 ${lz:set("注释","*****************设计人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "smEditor" : (lz:join(relateObject,"$smEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".smEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.smEditor || #request.defines['smEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"smEditor":relateObject}" id="form-dl-smEditor">
					<dt>设&nbsp;&nbsp;计&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.smEditorYesNot=="not"?"checked-not":""}${data.smEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.smEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.smEditor}">
				 					${get:SrvUser(data.smEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************设计人字段的查询代码结束*****************")}
			
			
			${after$smEditor}
			 	
			 ${before$smUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "smUpdateTime" : (lz:join(relateObject,"$smUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".smUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.smUpdateTime || #request.defines['smUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"smUpdateTime":relateObject}" id="form-dl-smUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.smUpdateTimeYesNot=="not"?"checked-not":""}${data.smUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.smUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.smUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.smUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.smUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.smUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.smUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$smUpdateTime}
			 	
			 ${before$smAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "smAddTime" : (lz:join(relateObject,"$smAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".smAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.smAddTime || #request.defines['smAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"smAddTime":relateObject}" id="form-dl-smAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.smAddTimeYesNot=="not"?"checked-not":""}${data.smAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.smAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.smAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.smAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.smAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.smAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.smAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$smAddTime}
			 	
			 ${before$smStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "smStatus" : (lz:join(relateObject,"$smStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".smStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.smStatus || #request.defines['smStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"smStatus":relateObject}" id="form-dl-smStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.smStatusYesNot=="not"?"checked-not":""}${data.smStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.smStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.smStatus==0?"selected":""}>新增|测试</option>
							<option value="1" ${data.smStatus==1?"selected":""}>上线使用</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$smStatus}