<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cspId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspId" : (lz:join(relateObject,"$cspId")))}
			 ${lz:set("cname",lz:join(relateObject,".cspId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspId || #request.defines['cspId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspId":relateObject}" id="form-dl-cspId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspIdYesNot=="not"?"checked-not":""}${data.cspIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cspId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cspId}
			 	
			 ${before$cspName}
			 
			 ${lz:set("注释","*****************套餐名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspName" : (lz:join(relateObject,"$cspName")))}
			 ${lz:set("cname",lz:join(relateObject,".cspName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspName || #request.defines['cspName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspName":relateObject}" id="form-dl-cspName">
					<dt>套餐名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspNameYesNot=="not"?"checked-not":""}${data.cspNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cspName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************套餐名称字段的查询代码结束*****************")}
			
			
			${after$cspName}
			 	
			 ${before$cspFlag}
			 
			 ${lz:set("注释","*****************套餐标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspFlag" : (lz:join(relateObject,"$cspFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".cspFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspFlag || #request.defines['cspFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspFlag":relateObject}" id="form-dl-cspFlag">
					<dt>套餐标识：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspFlagYesNot=="not"?"checked-not":""}${data.cspFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspFlagYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cspFlag}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************套餐标识字段的查询代码结束*****************")}
			
			
			${after$cspFlag}
			 	
			 ${before$cspValidity}
			 
			 ${lz:set("注释","*****************有效期类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspValidity" : (lz:join(relateObject,"$cspValidity")))}
			 ${lz:set("cname",lz:join(relateObject,".cspValidity"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspValidity || #request.defines['cspValidity']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspValidity":relateObject}" id="form-dl-cspValidity">
					<dt>有效期类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspValidityYesNot=="not"?"checked-not":""}${data.cspValidityYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspValidityYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cspValidity==0?"selected":""}>不限</option>
							<option value="1" ${data.cspValidity==1?"selected":""}>一月</option>
							<option value="2" ${data.cspValidity==2?"selected":""}>一季</option>
							<option value="3" ${data.cspValidity==3?"selected":""}>半年</option>
							<option value="4" ${data.cspValidity==4?"selected":""}>一年</option>
							<option value="5" ${data.cspValidity==5?"selected":""}>天数</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************有效期类型字段的查询代码结束*****************")}
			
			
			${after$cspValidity}
			 	
			 ${before$cspUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspUpdateTime" : (lz:join(relateObject,"$cspUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cspUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspUpdateTime || #request.defines['cspUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspUpdateTime":relateObject}" id="form-dl-cspUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspUpdateTimeYesNot=="not"?"checked-not":""}${data.cspUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cspUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cspUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cspUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cspUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cspUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cspUpdateTime}
			 	
			 ${before$cspAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspAddTime" : (lz:join(relateObject,"$cspAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cspAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspAddTime || #request.defines['cspAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspAddTime":relateObject}" id="form-dl-cspAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspAddTimeYesNot=="not"?"checked-not":""}${data.cspAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cspAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cspAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cspAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cspAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cspAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cspAddTime}
			 	
			 ${before$cspStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspStatus" : (lz:join(relateObject,"$cspStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cspStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspStatus || #request.defines['cspStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspStatus":relateObject}" id="form-dl-cspStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspStatusYesNot=="not"?"checked-not":""}${data.cspStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.cspStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.cspStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cspStatus}