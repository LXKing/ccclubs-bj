<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cswoId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswoId" : (lz:join(relateObject,"$cswoId")))}
			 ${lz:set("cname",lz:join(relateObject,".cswoId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswoId || #request.defines['cswoId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswoId":relateObject}" id="form-dl-cswoId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswoIdYesNot=="not"?"checked-not":""}${data.cswoIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswoIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cswoId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cswoId}
			 	
			 ${before$cswoName}
			 
			 ${lz:set("注释","*****************指令名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswoName" : (lz:join(relateObject,"$cswoName")))}
			 ${lz:set("cname",lz:join(relateObject,".cswoName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswoName || #request.defines['cswoName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswoName":relateObject}" id="form-dl-cswoName">
					<dt>指令名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswoNameYesNot=="not"?"checked-not":""}${data.cswoNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswoNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.cswoName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************指令名称字段的查询代码结束*****************")}
			
			
			${after$cswoName}
			 	
			 ${before$cswoType}
			 
			 ${lz:set("注释","*****************匹配类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswoType" : (lz:join(relateObject,"$cswoType")))}
			 ${lz:set("cname",lz:join(relateObject,".cswoType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswoType || #request.defines['cswoType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswoType":relateObject}" id="form-dl-cswoType">
					<dt>匹配类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswoTypeYesNot=="not"?"checked-not":""}${data.cswoTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswoTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cswoType==0?"selected":""}>精确匹配</option>
							<option value="1" ${data.cswoType==1?"selected":""}>模糊匹配</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************匹配类型字段的查询代码结束*****************")}
			
			
			${after$cswoType}
			 	
			 ${before$cswoRtype}
			 
			 ${lz:set("注释","*****************回复方式字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswoRtype" : (lz:join(relateObject,"$cswoRtype")))}
			 ${lz:set("cname",lz:join(relateObject,".cswoRtype"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswoRtype || #request.defines['cswoRtype']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswoRtype":relateObject}" id="form-dl-cswoRtype">
					<dt>回复方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswoRtypeYesNot=="not"?"checked-not":""}${data.cswoRtypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswoRtypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cswoRtype==0?"selected":""}>文字内容</option>
							<option value="1" ${data.cswoRtype==1?"selected":""}>图文内容</option>
							<option value="2" ${data.cswoRtype==2?"selected":""}>调用过程</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************回复方式字段的查询代码结束*****************")}
			
			
			${after$cswoRtype}
			 	
			 ${before$cswoClass}
			 
			 ${lz:set("注释","*****************调用过程字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswoClass" : (lz:join(relateObject,"$cswoClass")))}
			 ${lz:set("cname",lz:join(relateObject,".cswoClass"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswoClass || #request.defines['cswoClass']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswoClass":relateObject}" id="form-dl-cswoClass">
					<dt>调用过程：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswoClassYesNot=="not"?"checked-not":""}${data.cswoClassYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswoClassYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'WX_ORDER_CLASS') )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.spId}" ${data.cswoClass==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************调用过程字段的查询代码结束*****************")}
			
			
			${after$cswoClass}
			 	
			 ${before$cswoTime}
			 
			 ${lz:set("注释","*****************更改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswoTime" : (lz:join(relateObject,"$cswoTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cswoTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswoTime || #request.defines['cswoTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswoTime":relateObject}" id="form-dl-cswoTime">
					<dt>更改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswoTimeYesNot=="not"?"checked-not":""}${data.cswoTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswoTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cswoTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cswoTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cswoTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cswoTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>更改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cswoTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************更改时间字段的查询代码结束*****************")}
			
			
			${after$cswoTime}
			 	
			 ${before$cswoStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswoStatus" : (lz:join(relateObject,"$cswoStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cswoStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswoStatus || #request.defines['cswoStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswoStatus":relateObject}" id="form-dl-cswoStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswoStatusYesNot=="not"?"checked-not":""}${data.cswoStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswoStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.cswoStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.cswoStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cswoStatus}