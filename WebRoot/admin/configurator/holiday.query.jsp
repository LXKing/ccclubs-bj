<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cshId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cshId" : (lz:join(relateObject,"$cshId")))}
			 ${lz:set("cname",lz:join(relateObject,".cshId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cshId || #request.defines['cshId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cshId":relateObject}" id="form-dl-cshId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cshIdYesNot=="not"?"checked-not":""}${data.cshIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cshIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cshId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cshId}
			 	
			 ${before$cshName}
			 
			 ${lz:set("注释","*****************节假日名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cshName" : (lz:join(relateObject,"$cshName")))}
			 ${lz:set("cname",lz:join(relateObject,".cshName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cshName || #request.defines['cshName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cshName":relateObject}" id="form-dl-cshName">
					<dt>节假日名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cshNameYesNot=="not"?"checked-not":""}${data.cshNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cshNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cshName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************节假日名称字段的查询代码结束*****************")}
			
			
			${after$cshName}
			 	
			 ${before$cshDate}
			 
			 ${lz:set("注释","*****************日期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cshDate" : (lz:join(relateObject,"$cshDate")))}
			 ${lz:set("cname",lz:join(relateObject,".cshDate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cshDate || #request.defines['cshDate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cshDate":relateObject}" id="form-dl-cshDate">
					<dt>日　　期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cshDateYesNot=="not"?"checked-not":""}${data.cshDateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cshDateYesNot}"/>
			 			<input type="date" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cshDateStart,"yyyy-MM-dd")}" end="${lz:date(data.cshDateEnd,"yyyy-MM-dd")}" exp="${data.cshDateExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cshDateStart,"yyyy-MM-dd")}"/>
						起</dd></dl><dl class="query-item"><dt>日期：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cshDateEnd,"yyyy-MM-dd")}"/>
						止
					-->	 
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************日期字段的查询代码结束*****************")}
			
			
			${after$cshDate}
			 	
			 ${before$cshType}
			 
			 ${lz:set("注释","*****************类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cshType" : (lz:join(relateObject,"$cshType")))}
			 ${lz:set("cname",lz:join(relateObject,".cshType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cshType || #request.defines['cshType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cshType":relateObject}" id="form-dl-cshType">
					<dt>类　　型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cshTypeYesNot=="not"?"checked-not":""}${data.cshTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cshTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cshType==0?"selected":""}>放假</option>
							<option value="1" ${data.cshType==1?"selected":""}>调班</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************类型字段的查询代码结束*****************")}
			
			
			${after$cshType}
			 	
			 ${before$cshIndex}
			 
			 ${lz:set("注释","*****************星期几字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cshIndex" : (lz:join(relateObject,"$cshIndex")))}
			 ${lz:set("cname",lz:join(relateObject,".cshIndex"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cshIndex || #request.defines['cshIndex']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cshIndex":relateObject}" id="form-dl-cshIndex">
					<dt>星&nbsp;&nbsp;期&nbsp;&nbsp;几：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cshIndexYesNot=="not"?"checked-not":""}${data.cshIndexYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cshIndexYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.cshIndex==1?"selected":""}>星期天</option>
							<option value="2" ${data.cshIndex==2?"selected":""}>星期一</option>
							<option value="3" ${data.cshIndex==3?"selected":""}>星期二</option>
							<option value="4" ${data.cshIndex==4?"selected":""}>星期三</option>
							<option value="5" ${data.cshIndex==5?"selected":""}>星期四</option>
							<option value="6" ${data.cshIndex==6?"selected":""}>星期五</option>
							<option value="7" ${data.cshIndex==7?"selected":""}>星期六</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************星期几字段的查询代码结束*****************")}
			
			
			${after$cshIndex}