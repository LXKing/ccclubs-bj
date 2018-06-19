<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$shId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shId" : (lz:join(relateObject,"$shId")))}
			 ${lz:set("cname",lz:join(relateObject,".shId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shId || #request.defines['shId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shId":relateObject}" id="form-dl-shId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shIdYesNot=="not"?"checked-not":""}${data.shIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.shId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$shId}
			 	
			 ${before$shParent}
			 
			 ${lz:set("注释","*****************上级目录字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shParent" : (lz:join(relateObject,"$shParent")))}
			 ${lz:set("cname",lz:join(relateObject,".shParent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shParent || #request.defines['shParent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shParent":relateObject}" id="form-dl-shParent">
					<dt>上级目录：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shParentYesNot=="not"?"checked-not":""}${data.shParentYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shParentYesNot}"/>
					<input title="请输入系统帮助主题名称进行查询" class="combox" action="${basePath}${proname}/systematic/help_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:SrvHelp(data.shParent).shName}" value="${data.shParent}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************上级目录字段的查询代码结束*****************")}
			
			
			${after$shParent}
			 	
			 ${before$shProfile}
			 
			 ${lz:set("注释","*****************帮助简述字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shProfile" : (lz:join(relateObject,"$shProfile")))}
			 ${lz:set("cname",lz:join(relateObject,".shProfile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shProfile || #request.defines['shProfile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shProfile":relateObject}" id="form-dl-shProfile">
					<dt>帮助简述：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shProfileYesNot=="not"?"checked-not":""}${data.shProfileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shProfileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.shProfile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************帮助简述字段的查询代码结束*****************")}
			
			
			${after$shProfile}
			 	
			 ${before$shEditor}
			 
			 ${lz:set("注释","*****************修改人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shEditor" : (lz:join(relateObject,"$shEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".shEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shEditor || #request.defines['shEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shEditor":relateObject}" id="form-dl-shEditor">
					<dt>修改人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shEditorYesNot=="not"?"checked-not":""}${data.shEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.shEditor}">
				 					${get:SrvUser(data.shEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改人员字段的查询代码结束*****************")}
			
			
			${after$shEditor}
			 	
			 ${before$shAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shAddTime" : (lz:join(relateObject,"$shAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".shAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shAddTime || #request.defines['shAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shAddTime":relateObject}" id="form-dl-shAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shAddTimeYesNot=="not"?"checked-not":""}${data.shAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.shAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.shAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.shAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.shAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.shAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$shAddTime}
			 	
			 ${before$shUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shUpdateTime" : (lz:join(relateObject,"$shUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".shUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shUpdateTime || #request.defines['shUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shUpdateTime":relateObject}" id="form-dl-shUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shUpdateTimeYesNot=="not"?"checked-not":""}${data.shUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.shUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.shUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.shUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.shUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.shUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$shUpdateTime}
			 	
			 ${before$shLevel}
			 
			 ${lz:set("注释","*****************优先级字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "shLevel" : (lz:join(relateObject,"$shLevel")))}
			 ${lz:set("cname",lz:join(relateObject,".shLevel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.shLevel || #request.defines['shLevel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"shLevel":relateObject}" id="form-dl-shLevel">
					<dt>优&nbsp;&nbsp;先&nbsp;&nbsp;级：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.shLevelYesNot=="not"?"checked-not":""}${data.shLevelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.shLevelYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.shLevelMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.shLevelMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************优先级字段的查询代码结束*****************")}
			
			
			${after$shLevel}