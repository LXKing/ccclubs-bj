<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csehId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehId" : (lz:join(relateObject,"$csehId")))}
			 ${lz:set("cname",lz:join(relateObject,".csehId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehId || #request.defines['csehId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehId":relateObject}" id="form-dl-csehId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehIdYesNot=="not"?"checked-not":""}${data.csehIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csehId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csehId}
			 	
			 ${before$csehDid}
			 
			 ${lz:set("注释","*****************设备ID字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehDid" : (lz:join(relateObject,"$csehDid")))}
			 ${lz:set("cname",lz:join(relateObject,".csehDid"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehDid || #request.defines['csehDid']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehDid":relateObject}" id="form-dl-csehDid">
					<dt>设备ID：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehDidYesNot=="not"?"checked-not":""}${data.csehDidYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehDidYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csehDid}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************设备ID字段的查询代码结束*****************")}
			
			
			${after$csehDid}
			 	
			 ${before$csehPowerPile}
			 
			 ${lz:set("注释","*****************所属电桩字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehPowerPile" : (lz:join(relateObject,"$csehPowerPile")))}
			 ${lz:set("cname",lz:join(relateObject,".csehPowerPile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehPowerPile || #request.defines['csehPowerPile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehPowerPile":relateObject}" id="form-dl-csehPowerPile">
					<dt>所属电桩：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehPowerPileYesNot=="not"?"checked-not":""}${data.csehPowerPileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehPowerPileYesNot}"/>
			 			<input title="请输入充电桩标识码进行查询" class="combox" action="${basePath}${proname}/object/powerpile_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsPowerPile(data.csehPowerPile).csppNo}" value="${data.csehPowerPile}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/powerpile_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属电桩字段的查询代码结束*****************")}
			
			
			${after$csehPowerPile}
			 	
			 ${before$csehStatusCode}
			 
			 ${lz:set("注释","*****************状态编码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehStatusCode" : (lz:join(relateObject,"$csehStatusCode")))}
			 ${lz:set("cname",lz:join(relateObject,".csehStatusCode"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehStatusCode || #request.defines['csehStatusCode']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehStatusCode":relateObject}" id="form-dl-csehStatusCode">
					<dt>状态编码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehStatusCodeYesNot=="not"?"checked-not":""}${data.csehStatusCodeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehStatusCodeYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csehStatusCode}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态编码字段的查询代码结束*****************")}
			
			
			${after$csehStatusCode}
			 	
			 ${before$csehStatusText}
			 
			 ${lz:set("注释","*****************状态描述字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehStatusText" : (lz:join(relateObject,"$csehStatusText")))}
			 ${lz:set("cname",lz:join(relateObject,".csehStatusText"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehStatusText || #request.defines['csehStatusText']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehStatusText":relateObject}" id="form-dl-csehStatusText">
					<dt>状态描述：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehStatusTextYesNot=="not"?"checked-not":""}${data.csehStatusTextYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehStatusTextYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csehStatusText}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态描述字段的查询代码结束*****************")}
			
			
			${after$csehStatusText}
			 	
			 ${before$csehEleAmount}
			 
			 ${lz:set("注释","*****************充电量字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehEleAmount" : (lz:join(relateObject,"$csehEleAmount")))}
			 ${lz:set("cname",lz:join(relateObject,".csehEleAmount"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehEleAmount || #request.defines['csehEleAmount']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehEleAmount":relateObject}" id="form-dl-csehEleAmount">
					<dt>充&nbsp;&nbsp;电&nbsp;&nbsp;量：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehEleAmountYesNot=="not"?"checked-not":""}${data.csehEleAmountYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehEleAmountYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csehEleAmount}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************充电量字段的查询代码结束*****************")}
			
			
			${after$csehEleAmount}
			 	
			 ${before$csehTimeLong}
			 
			 ${lz:set("注释","*****************充电时长字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehTimeLong" : (lz:join(relateObject,"$csehTimeLong")))}
			 ${lz:set("cname",lz:join(relateObject,".csehTimeLong"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehTimeLong || #request.defines['csehTimeLong']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehTimeLong":relateObject}" id="form-dl-csehTimeLong">
					<dt>充电时长：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehTimeLongYesNot=="not"?"checked-not":""}${data.csehTimeLongYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehTimeLongYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csehTimeLong}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************充电时长字段的查询代码结束*****************")}
			
			
			${after$csehTimeLong}
			 	
			 ${before$csehErrorCode}
			 
			 ${lz:set("注释","*****************错误码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehErrorCode" : (lz:join(relateObject,"$csehErrorCode")))}
			 ${lz:set("cname",lz:join(relateObject,".csehErrorCode"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehErrorCode || #request.defines['csehErrorCode']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehErrorCode":relateObject}" id="form-dl-csehErrorCode">
					<dt>错&nbsp;&nbsp;误&nbsp;&nbsp;码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehErrorCodeYesNot=="not"?"checked-not":""}${data.csehErrorCodeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehErrorCodeYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="10" name="${NAME}" id="${NAME}"  value="${data.csehErrorCode}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************错误码字段的查询代码结束*****************")}
			
			
			${after$csehErrorCode}
			 	
			 ${before$csehCollectTime}
			 
			 ${lz:set("注释","*****************数据时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehCollectTime" : (lz:join(relateObject,"$csehCollectTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csehCollectTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehCollectTime || #request.defines['csehCollectTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehCollectTime":relateObject}" id="form-dl-csehCollectTime">
					<dt>数据时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehCollectTimeYesNot=="not"?"checked-not":""}${data.csehCollectTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehCollectTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csehCollectTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csehCollectTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csehCollectTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csehCollectTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>数据时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csehCollectTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************数据时间字段的查询代码结束*****************")}
			
			
			${after$csehCollectTime}
			 	
			 ${before$csehAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehAddTime" : (lz:join(relateObject,"$csehAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csehAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehAddTime || #request.defines['csehAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehAddTime":relateObject}" id="form-dl-csehAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehAddTimeYesNot=="not"?"checked-not":""}${data.csehAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csehAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csehAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csehAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csehAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csehAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csehAddTime}
			 	
			 ${before$csehData}
			 
			 ${lz:set("注释","*****************接口数据字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csehData" : (lz:join(relateObject,"$csehData")))}
			 ${lz:set("cname",lz:join(relateObject,".csehData"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csehData || #request.defines['csehData']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csehData":relateObject}" id="form-dl-csehData">
					<dt>接口数据：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csehDataYesNot=="not"?"checked-not":""}${data.csehDataYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csehDataYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.csehData}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接口数据字段的查询代码结束*****************")}
			
			
			${after$csehData}