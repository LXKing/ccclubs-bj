<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csppId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csppId" : (lz:join(relateObject,"$csppId")))}
			 ${lz:set("cname",lz:join(relateObject,".csppId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csppId || #request.defines['csppId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csppId":relateObject}" id="form-dl-csppId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csppIdYesNot=="not"?"checked-not":""}${data.csppIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csppIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csppId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csppId}
			 	
			 ${before$csppHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csppHost" : (lz:join(relateObject,"$csppHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csppHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csppHost || #request.defines['csppHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csppHost":relateObject}" id="form-dl-csppHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csppHostYesNot=="not"?"checked-not":""}${data.csppHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csppHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csppHost}">
				 					${get:SrvHost(data.csppHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csppHost}
			 	
			 ${before$csppNo}
			 
			 ${lz:set("注释","*****************标识码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csppNo" : (lz:join(relateObject,"$csppNo")))}
			 ${lz:set("cname",lz:join(relateObject,".csppNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csppNo || #request.defines['csppNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csppNo":relateObject}" id="form-dl-csppNo">
					<dt>标&nbsp;&nbsp;识&nbsp;&nbsp;码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csppNoYesNot=="not"?"checked-not":""}${data.csppNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csppNoYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csppNo}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************标识码字段的查询代码结束*****************")}
			
			
			${after$csppNo}
			 	
			 ${before$csppOutlets}
			 
			 ${lz:set("注释","*****************所在网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csppOutlets" : (lz:join(relateObject,"$csppOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".csppOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csppOutlets || #request.defines['csppOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csppOutlets":relateObject}" id="form-dl-csppOutlets">
					<dt>所在网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csppOutletsYesNot=="not"?"checked-not":""}${data.csppOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csppOutletsYesNot}"/>
				  		<select class="combox" id="${NAME}" name="${NAME}" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&csoHost={csppHost}">
							<option value="${data.csppOutlets}">
									${get:CsOutlets(data.csppOutlets).csoName}
							</option>
						</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所在网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csppOutlets']>0">
				${lz:set("注释","****当所在网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csppOutlets")}
			  	<input type="hidden" name="csppOutlets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csppOutlets}
			 	
			 ${before$csppPerson}
			 
			 ${lz:set("注释","*****************负责人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csppPerson" : (lz:join(relateObject,"$csppPerson")))}
			 ${lz:set("cname",lz:join(relateObject,".csppPerson"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csppPerson || #request.defines['csppPerson']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csppPerson":relateObject}" id="form-dl-csppPerson">
					<dt>负&nbsp;&nbsp;责&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csppPersonYesNot=="not"?"checked-not":""}${data.csppPersonYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csppPersonYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csppPerson}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************负责人字段的查询代码结束*****************")}
			
			
			${after$csppPerson}
			 	
			 ${before$csppTel}
			 
			 ${lz:set("注释","*****************联系电话字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csppTel" : (lz:join(relateObject,"$csppTel")))}
			 ${lz:set("cname",lz:join(relateObject,".csppTel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csppTel || #request.defines['csppTel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csppTel":relateObject}" id="form-dl-csppTel">
					<dt>联系电话：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csppTelYesNot=="not"?"checked-not":""}${data.csppTelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csppTelYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csppTel}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************联系电话字段的查询代码结束*****************")}
			
			
			${after$csppTel}
			 	
			 ${before$csppUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csppUpdateTime" : (lz:join(relateObject,"$csppUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csppUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csppUpdateTime || #request.defines['csppUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csppUpdateTime":relateObject}" id="form-dl-csppUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csppUpdateTimeYesNot=="not"?"checked-not":""}${data.csppUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csppUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csppUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csppUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csppUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csppUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csppUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csppUpdateTime}
			 	
			 ${before$csppAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csppAddTime" : (lz:join(relateObject,"$csppAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csppAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csppAddTime || #request.defines['csppAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csppAddTime":relateObject}" id="form-dl-csppAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csppAddTimeYesNot=="not"?"checked-not":""}${data.csppAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csppAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csppAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csppAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csppAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csppAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csppAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csppAddTime}
			 	
			 ${before$csppStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csppStatus" : (lz:join(relateObject,"$csppStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csppStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csppStatus || #request.defines['csppStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csppStatus":relateObject}" id="form-dl-csppStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csppStatusYesNot=="not"?"checked-not":""}${data.csppStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csppStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csppStatus==1?"selected":""}>正常</option>
							<option value="2" ${data.csppStatus==2?"selected":""}>预设</option>
							<option value="3" ${data.csppStatus==3?"selected":""}>已坏</option>
							<option value="0" ${data.csppStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csppStatus}