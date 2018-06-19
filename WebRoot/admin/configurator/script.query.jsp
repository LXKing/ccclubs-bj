<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$ssId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "ssId" : (lz:join(relateObject,"$ssId")))}
			 ${lz:set("cname",lz:join(relateObject,".ssId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.ssId || #request.defines['ssId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"ssId":relateObject}" id="form-dl-ssId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.ssIdYesNot=="not"?"checked-not":""}${data.ssIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.ssIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.ssId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$ssId}
			 	
			 ${before$ssName}
			 
			 ${lz:set("注释","*****************脚本名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "ssName" : (lz:join(relateObject,"$ssName")))}
			 ${lz:set("cname",lz:join(relateObject,".ssName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.ssName || #request.defines['ssName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"ssName":relateObject}" id="form-dl-ssName">
					<dt>脚本名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.ssNameYesNot=="not"?"checked-not":""}${data.ssNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.ssNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.ssName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************脚本名称字段的查询代码结束*****************")}
			
			
			${after$ssName}
			 	
			 ${before$ssModelClass}
			 
			 ${lz:set("注释","*****************类名全称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "ssModelClass" : (lz:join(relateObject,"$ssModelClass")))}
			 ${lz:set("cname",lz:join(relateObject,".ssModelClass"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.ssModelClass || #request.defines['ssModelClass']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"ssModelClass":relateObject}" id="form-dl-ssModelClass">
					<dt>类名全称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.ssModelClassYesNot=="not"?"checked-not":""}${data.ssModelClassYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.ssModelClassYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.ssModelClass}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************类名全称字段的查询代码结束*****************")}
			
			
			${after$ssModelClass}
			 	
			 ${before$ssEvent}
			 
			 ${lz:set("注释","*****************事件名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "ssEvent" : (lz:join(relateObject,"$ssEvent")))}
			 ${lz:set("cname",lz:join(relateObject,".ssEvent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.ssEvent || #request.defines['ssEvent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"ssEvent":relateObject}" id="form-dl-ssEvent">
					<dt>事件名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.ssEventYesNot=="not"?"checked-not":""}${data.ssEventYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.ssEventYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.ssEvent}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************事件名称字段的查询代码结束*****************")}
			
			
			${after$ssEvent}
			 	
			 ${before$ssUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "ssUpdateTime" : (lz:join(relateObject,"$ssUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".ssUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.ssUpdateTime || #request.defines['ssUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"ssUpdateTime":relateObject}" id="form-dl-ssUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.ssUpdateTimeYesNot=="not"?"checked-not":""}${data.ssUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.ssUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.ssUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.ssUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.ssUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.ssUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.ssUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$ssUpdateTime}
			 	
			 ${before$ssAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "ssAddTime" : (lz:join(relateObject,"$ssAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".ssAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.ssAddTime || #request.defines['ssAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"ssAddTime":relateObject}" id="form-dl-ssAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.ssAddTimeYesNot=="not"?"checked-not":""}${data.ssAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.ssAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.ssAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.ssAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.ssAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.ssAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.ssAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$ssAddTime}
			 	
			 ${before$ssStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "ssStatus" : (lz:join(relateObject,"$ssStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".ssStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.ssStatus || #request.defines['ssStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"ssStatus":relateObject}" id="form-dl-ssStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.ssStatusYesNot=="not"?"checked-not":""}${data.ssStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.ssStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.ssStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.ssStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$ssStatus}