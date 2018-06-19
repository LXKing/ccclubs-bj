<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cscmId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmId" : (lz:join(relateObject,"$cscmId")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmId || #request.defines['cscmId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmId":relateObject}" id="form-dl-cscmId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmIdYesNot=="not"?"checked-not":""}${data.cscmIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cscmId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cscmId}
			 	
			 ${before$cscmName}
			 
			 ${lz:set("注释","*****************车型名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmName" : (lz:join(relateObject,"$cscmName")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmName || #request.defines['cscmName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmName":relateObject}" id="form-dl-cscmName">
					<dt>车型名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmNameYesNot=="not"?"checked-not":""}${data.cscmNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscmName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车型名称字段的查询代码结束*****************")}
			
			
			${after$cscmName}
			 	
			 ${before$cscmType}
			 
			 ${lz:set("注释","*****************动力类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmType" : (lz:join(relateObject,"$cscmType")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmType || #request.defines['cscmType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmType":relateObject}" id="form-dl-cscmType">
					<dt>动力类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmTypeYesNot=="not"?"checked-not":""}${data.cscmTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cscmType==0?"selected":""}>汽油车</option>
							<option value="1" ${data.cscmType==1?"selected":""}>电动车</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************动力类型字段的查询代码结束*****************")}
			
			
			${after$cscmType}
			 	
			 ${before$cscmFile}
			 
			 ${lz:set("注释","*****************档类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmFile" : (lz:join(relateObject,"$cscmFile")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmFile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmFile || #request.defines['cscmFile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmFile":relateObject}" id="form-dl-cscmFile">
					<dt>档&nbsp;&nbsp;类&nbsp;&nbsp;型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmFileYesNot=="not"?"checked-not":""}${data.cscmFileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmFileYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cscmFile==0?"selected":""}>自动档</option>
							<option value="1" ${data.cscmFile==1?"selected":""}>手动档</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************档类型字段的查询代码结束*****************")}
			
			
			${after$cscmFile}
			 	
			 ${before$cscmUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmUpdateTime" : (lz:join(relateObject,"$cscmUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmUpdateTime || #request.defines['cscmUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmUpdateTime":relateObject}" id="form-dl-cscmUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmUpdateTimeYesNot=="not"?"checked-not":""}${data.cscmUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscmUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscmUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscmUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscmUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscmUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cscmUpdateTime}
			 	
			 ${before$cscmAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmAddTime" : (lz:join(relateObject,"$cscmAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmAddTime || #request.defines['cscmAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmAddTime":relateObject}" id="form-dl-cscmAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmAddTimeYesNot=="not"?"checked-not":""}${data.cscmAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscmAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscmAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscmAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscmAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscmAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cscmAddTime}
			 	
			 ${before$cscmStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmStatus" : (lz:join(relateObject,"$cscmStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmStatus || #request.defines['cscmStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmStatus":relateObject}" id="form-dl-cscmStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmStatusYesNot=="not"?"checked-not":""}${data.cscmStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.cscmStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.cscmStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cscmStatus}