<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cswmId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmId" : (lz:join(relateObject,"$cswmId")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmId || #request.defines['cswmId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmId":relateObject}" id="form-dl-cswmId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmIdYesNot=="not"?"checked-not":""}${data.cswmIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cswmId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cswmId}
			 	
			 ${before$cswmName}
			 
			 ${lz:set("注释","*****************菜单名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmName" : (lz:join(relateObject,"$cswmName")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmName || #request.defines['cswmName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmName":relateObject}" id="form-dl-cswmName">
					<dt>菜单名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmNameYesNot=="not"?"checked-not":""}${data.cswmNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cswmName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************菜单名称字段的查询代码结束*****************")}
			
			
			${after$cswmName}
			 	
			 ${before$cswmSupport}
			 
			 ${lz:set("注释","*****************支持终端类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmSupport" : (lz:join(relateObject,"$cswmSupport")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmSupport"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmSupport || #request.defines['cswmSupport']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmSupport":relateObject}" id="form-dl-cswmSupport">
					<dt>支持终端类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmSupportYesNot=="not"?"checked-not":""}${data.cswmSupportYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmSupportYesNot}"/>
				 		<select id="${NAME}" name="${NAME}" >
			 				<option value="">全部</option>
							<option value="#0#" ${data.cswmSupport=='#0#'?"selected":""}>微信</option>
							<option value="#1#" ${data.cswmSupport=='#1#'?"selected":""}>支付宝</option>
						</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************支持终端类型字段的查询代码结束*****************")}
			
			
			${after$cswmSupport}
			 	
			 ${before$cswmLevel}
			 
			 ${lz:set("注释","*****************优先级字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmLevel" : (lz:join(relateObject,"$cswmLevel")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmLevel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmLevel || #request.defines['cswmLevel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmLevel":relateObject}" id="form-dl-cswmLevel">
					<dt>优&nbsp;&nbsp;先&nbsp;&nbsp;级：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmLevelYesNot=="not"?"checked-not":""}${data.cswmLevelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmLevelYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cswmLevel==0?"selected":""}>默认</option>
							<option value="1" ${data.cswmLevel==1?"selected":""}>1</option>
							<option value="2" ${data.cswmLevel==2?"selected":""}>2</option>
							<option value="3" ${data.cswmLevel==3?"selected":""}>3</option>
							<option value="4" ${data.cswmLevel==4?"selected":""}>4</option>
							<option value="5" ${data.cswmLevel==5?"selected":""}>5</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************优先级字段的查询代码结束*****************")}
			
			
			${after$cswmLevel}
			 	
			 ${before$cswmTime}
			 
			 ${lz:set("注释","*****************更改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmTime" : (lz:join(relateObject,"$cswmTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmTime || #request.defines['cswmTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmTime":relateObject}" id="form-dl-cswmTime">
					<dt>更改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmTimeYesNot=="not"?"checked-not":""}${data.cswmTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cswmTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cswmTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cswmTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cswmTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>更改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cswmTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************更改时间字段的查询代码结束*****************")}
			
			
			${after$cswmTime}
			 	
			 ${before$cswmStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmStatus" : (lz:join(relateObject,"$cswmStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmStatus || #request.defines['cswmStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmStatus":relateObject}" id="form-dl-cswmStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmStatusYesNot=="not"?"checked-not":""}${data.cswmStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.cswmStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.cswmStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cswmStatus}