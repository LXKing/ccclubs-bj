<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csliId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csliId" : (lz:join(relateObject,"$csliId")))}
			 ${lz:set("cname",lz:join(relateObject,".csliId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csliId || #request.defines['csliId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csliId":relateObject}" id="form-dl-csliId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csliIdYesNot=="not"?"checked-not":""}${data.csliIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csliIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csliId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csliId}
			 	
			 ${before$csliName}
			 
			 ${lz:set("注释","*****************奖品名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csliName" : (lz:join(relateObject,"$csliName")))}
			 ${lz:set("cname",lz:join(relateObject,".csliName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csliName || #request.defines['csliName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csliName":relateObject}" id="form-dl-csliName">
					<dt>奖品名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csliNameYesNot=="not"?"checked-not":""}${data.csliNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csliNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csliName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************奖品名称字段的查询代码结束*****************")}
			
			
			${after$csliName}
			 	
			 ${before$csliLuckyId}
			 
			 ${lz:set("注释","*****************所属活动字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csliLuckyId" : (lz:join(relateObject,"$csliLuckyId")))}
			 ${lz:set("cname",lz:join(relateObject,".csliLuckyId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csliLuckyId || #request.defines['csliLuckyId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csliLuckyId":relateObject}" id="form-dl-csliLuckyId">
					<dt>所属活动：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csliLuckyIdYesNot=="not"?"checked-not":""}${data.csliLuckyIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csliLuckyIdYesNot}"/>
			 			<input title="请输入抽奖活动活动名称进行查询" class="combox" action="${basePath}${proname}/operate/lucky/lucky_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsLucky(data.csliLuckyId).cslName}" value="${data.csliLuckyId}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/operate/lucky/lucky_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属活动字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csliLuckyId']>0">
				${lz:set("注释","****当所属活动字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csliLuckyId")}
			  	<input type="hidden" name="csliLuckyId$on" id="CsLucky$on" value="true"/>
			  	<jsp:include page="/admin/operate/lucky/lucky.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csliLuckyId}
			 	
			 ${before$csliType}
			 
			 ${lz:set("注释","*****************奖项类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csliType" : (lz:join(relateObject,"$csliType")))}
			 ${lz:set("cname",lz:join(relateObject,".csliType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csliType || #request.defines['csliType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csliType":relateObject}" id="form-dl-csliType">
					<dt>奖项类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csliTypeYesNot=="not"?"checked-not":""}${data.csliTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csliTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csliType==0?"selected":""}>谢谢参与</option>
							<option value="1" ${data.csliType==1?"selected":""}>积分</option>
							<option value="2" ${data.csliType==2?"selected":""}>现金券</option>
							<option value="3" ${data.csliType==3?"selected":""}>免费小时</option>
							<option value="4" ${data.csliType==4?"selected":""}>实物</option>
							<option value="5" ${data.csliType==5?"selected":""}>其它方式</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************奖项类型字段的查询代码结束*****************")}
			
			
			${after$csliType}
			 	
			 ${before$csliIndex}
			 
			 ${lz:set("注释","*****************奖项等级字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csliIndex" : (lz:join(relateObject,"$csliIndex")))}
			 ${lz:set("cname",lz:join(relateObject,".csliIndex"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csliIndex || #request.defines['csliIndex']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csliIndex":relateObject}" id="form-dl-csliIndex">
					<dt>奖项等级：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csliIndexYesNot=="not"?"checked-not":""}${data.csliIndexYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csliIndexYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.csliIndexMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.csliIndexMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************奖项等级字段的查询代码结束*****************")}
			
			
			${after$csliIndex}
			 	
			 ${before$csliUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csliUpdateTime" : (lz:join(relateObject,"$csliUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csliUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csliUpdateTime || #request.defines['csliUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csliUpdateTime":relateObject}" id="form-dl-csliUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csliUpdateTimeYesNot=="not"?"checked-not":""}${data.csliUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csliUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csliUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csliUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csliUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csliUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csliUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csliUpdateTime}
			 	
			 ${before$csliAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csliAddTime" : (lz:join(relateObject,"$csliAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csliAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csliAddTime || #request.defines['csliAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csliAddTime":relateObject}" id="form-dl-csliAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csliAddTimeYesNot=="not"?"checked-not":""}${data.csliAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csliAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csliAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csliAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csliAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csliAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csliAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csliAddTime}
			 	
			 ${before$csliStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csliStatus" : (lz:join(relateObject,"$csliStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csliStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csliStatus || #request.defines['csliStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csliStatus":relateObject}" id="form-dl-csliStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csliStatusYesNot=="not"?"checked-not":""}${data.csliStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csliStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csliStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.csliStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csliStatus}