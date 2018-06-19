<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$sqId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sqId" : (lz:join(relateObject,"$sqId")))}
			 ${lz:set("cname",lz:join(relateObject,".sqId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sqId || #request.defines['sqId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sqId":relateObject}" id="form-dl-sqId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sqIdYesNot=="not"?"checked-not":""}${data.sqIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sqIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.sqId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$sqId}
			 	
			 ${before$sqName}
			 
			 ${lz:set("注释","*****************查询名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sqName" : (lz:join(relateObject,"$sqName")))}
			 ${lz:set("cname",lz:join(relateObject,".sqName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sqName || #request.defines['sqName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sqName":relateObject}" id="form-dl-sqName">
					<dt>查询名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sqNameYesNot=="not"?"checked-not":""}${data.sqNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sqNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="10" name="${NAME}" id="${NAME}"  value="${data.sqName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************查询名称字段的查询代码结束*****************")}
			
			
			${after$sqName}
			 	
			 ${before$sqIcon}
			 
			 ${lz:set("注释","*****************图标字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sqIcon" : (lz:join(relateObject,"$sqIcon")))}
			 ${lz:set("cname",lz:join(relateObject,".sqIcon"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sqIcon || #request.defines['sqIcon']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sqIcon":relateObject}" id="form-dl-sqIcon">
					<dt>图　　标：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sqIconYesNot=="not"?"checked-not":""}${data.sqIconYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sqIconYesNot}"/>
		 				无法自动构造
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************图标字段的查询代码结束*****************")}
			
			
			${after$sqIcon}
			 	
			 ${before$sqPath}
			 
			 ${lz:set("注释","*****************项目路径字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sqPath" : (lz:join(relateObject,"$sqPath")))}
			 ${lz:set("cname",lz:join(relateObject,".sqPath"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sqPath || #request.defines['sqPath']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sqPath":relateObject}" id="form-dl-sqPath">
					<dt>项目路径：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sqPathYesNot=="not"?"checked-not":""}${data.sqPathYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sqPathYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.sqPath}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************项目路径字段的查询代码结束*****************")}
			
			
			${after$sqPath}
			 	
			 ${before$sqEditor}
			 
			 ${lz:set("注释","*****************添加人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sqEditor" : (lz:join(relateObject,"$sqEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".sqEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sqEditor || #request.defines['sqEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sqEditor":relateObject}" id="form-dl-sqEditor">
					<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sqEditorYesNot=="not"?"checked-not":""}${data.sqEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sqEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.sqEditor}">
				 					${get:SrvUser(data.sqEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人字段的查询代码结束*****************")}
			
			
			${after$sqEditor}
			 	
			 ${before$sqUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sqUpdateTime" : (lz:join(relateObject,"$sqUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".sqUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sqUpdateTime || #request.defines['sqUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sqUpdateTime":relateObject}" id="form-dl-sqUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sqUpdateTimeYesNot=="not"?"checked-not":""}${data.sqUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sqUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.sqUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.sqUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.sqUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.sqUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.sqUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$sqUpdateTime}
			 	
			 ${before$sqAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sqAddTime" : (lz:join(relateObject,"$sqAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".sqAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sqAddTime || #request.defines['sqAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sqAddTime":relateObject}" id="form-dl-sqAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sqAddTimeYesNot=="not"?"checked-not":""}${data.sqAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sqAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.sqAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.sqAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.sqAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.sqAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.sqAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$sqAddTime}
			 	
			 ${before$sqStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sqStatus" : (lz:join(relateObject,"$sqStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".sqStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sqStatus || #request.defines['sqStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sqStatus":relateObject}" id="form-dl-sqStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sqStatusYesNot=="not"?"checked-not":""}${data.sqStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sqStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.sqStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.sqStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$sqStatus}