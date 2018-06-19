<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cscrId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscrId" : (lz:join(relateObject,"$cscrId")))}
			 ${lz:set("cname",lz:join(relateObject,".cscrId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscrId || #request.defines['cscrId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscrId":relateObject}" id="form-dl-cscrId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscrIdYesNot=="not"?"checked-not":""}${data.cscrIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscrIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cscrId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cscrId}
			 	
			 ${before$cscrHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscrHost" : (lz:join(relateObject,"$cscrHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cscrHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscrHost || #request.defines['cscrHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscrHost":relateObject}" id="form-dl-cscrHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscrHostYesNot=="not"?"checked-not":""}${data.cscrHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscrHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscrHost}">
				 					${get:SrvHost(data.cscrHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			${before$cscrOutlets}
			 
			 ${lz:set("注释","*****************指定网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscrOutlets" : (lz:join(relateObject,"$cscrOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".cscrOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscrOutlets || #request.defines['cscrOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscrOutlets":relateObject}" id="form-dl-cscrOutlets">
					<dt>网　　点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscrOutletsYesNot=="not"?"checked-not":""}${data.cscrOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscrOutletsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={csoHost}&csoHost={csuiHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${cscrOutlets}">
				 					${get:CsOutlets(cscrOutlets).csoName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************指定网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscrOutlets']>0">
				${lz:set("注释","****当指定网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscrOutlets")}
			  	<input type="hidden" name="cscrOutlets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscrOutlets}
			 	
			 ${before$cscrCarmodel}
			 
			 ${lz:set("注释","*****************外部车型的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscrCarmodel" : (lz:join(relateObject,"$cscrCarmodel")))}
			 ${lz:set("cname",lz:join(relateObject,".cscrCarmodel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscrCarmodel || #request.defines['cscrCarmodel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscrCarmodel":relateObject}" id="form-dl-cscrCarmodel">
					<dt>外部车型：</dt>
					<dd>
			 			<%--<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${cscrCarmodel}"/>
		 	 		
					--%>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscrOutletsYesNot=="not"?"checked-not":""}${data.cscrOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscrCarmodelYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/unit/specialmodel_query.do?parent={param}&host={csoHost}&csoHost={csuiHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${cscrCarmodel}">
				 					${get:CsSpecialModel(cscrCarmodel).cssmName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/unit/specialmodel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************外部车型的查询代码结束*****************")}
			
			
			${after$cscrCarmodel}
			 	
			 ${before$cscrCarNo}
			 
			 ${lz:set("注释","*****************车牌名称的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscrCarNo" : (lz:join(relateObject,"$cscrCarNo")))}
			 ${lz:set("cname",lz:join(relateObject,".cscrCarNo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscrCarNo || #request.defines['cscrCarNo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscrCarNo":relateObject}" id="form-dl-cscrCarNo">
					<dt>车牌名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscrCarNoYesNot=="not"?"checked-not":""}${data.cscrCarNoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscrCarNoYesNot}"/>
						<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${cscrCarNo}"/>
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车牌名称字段的查询代码结束*****************")}
			
			${after$cscrCarNo}
			 	
			 ${before$cscrColor}
			 
			 ${lz:set("注释","*****************车辆颜色字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscrColor" : (lz:join(relateObject,"$cscrColor")))}
			 ${lz:set("cname",lz:join(relateObject,".cscrColor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscrColor || #request.defines['cscrColor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscrColor":relateObject}" id="form-dl-cscrColor">
					<dt>车辆颜色：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscrColorYesNot=="not"?"checked-not":""}${data.cscrColorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscrColorYesNot}"/>
		 	 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${cscrColor}"/>
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车辆颜色查询代码结束*****************")}
			
			
			
			${after$cscrColor}
			
			${before$cscrLocation}
			 
			 ${lz:set("注释","*****************位置字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscrLocation" : (lz:join(relateObject,"$cscrLocation")))}
			 ${lz:set("cname",lz:join(relateObject,".cscrLocation"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscrLocation || #request.defines['cscrLocation']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscrLocation":relateObject}" id="form-dl-cscrColor">
					<dt>车辆位置：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscrLocationYesNot=="not"?"checked-not":""}${data.cscrLocationYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscrLocationYesNot}"/>
		 	 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${cscrLocation}"/>
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车辆颜色查询代码结束*****************")}
			
			
			
			${after$cscrLocation}
			 	
			 ${before$cscrSource}
			 
			 ${lz:set("注释","*****************数据来源的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscrSource" : (lz:join(relateObject,"$cscrSource")))}
			 ${lz:set("cname",lz:join(relateObject,".cscrSource"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscrSource || #request.defines['cscrSource']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscrSource":relateObject}" id="form-dl-cscrSource">
					<dt>数据来源：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscrSourceYesNot=="not"?"checked-not":""}${data.cscrSourceYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscrSourceYesNot}"/>
		 	 			<select id="${NAME}" name="${NAME}" >
			 				<option value="">全部</option>
							<option value="1" ${data.cscrSource==1?"selected":""}>公务出行</option>
							<option value="0" ${data.cscrSource==2?"selected":""}>外部采集</option>
			 			</select>
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************数据来源字段的查询代码结束*****************")}
			
			
			${after$cscrSource}
			 	
			 ${before$cscrStatus}
			 
			 ${lz:set("注释","*****************数据状态查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscrStatus" : (lz:join(relateObject,"$cscrStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cscrStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscrStatus || #request.defines['cscrStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscrStatus":relateObject}" id="form-dl-cscrStatus">
					<dt>数据状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscrStatusYesNot=="not"?"checked-not":""}${data.cscrStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscrStatusYesNot}"/>
						<select id="${NAME}" name="${NAME}" >
			 				<option value="">全部</option>
							<option value="1" ${data.cscrStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.cscrStatus==0?"selected":""}>禁用</option>
			 			</select>
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************数据状态查询代码结束*****************")}
			
			
			${after$cscrStatus}
			 	
			
			 ${before$cscrUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscrUpdateTime" : (lz:join(relateObject,"$cscrUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscrUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscrUpdateTime || #request.defines['cscrUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscrUpdateTime":relateObject}" id="form-dl-cscrUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscrUpdateTimeYesNot=="not"?"checked-not":""}${data.cscrUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscrUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscrUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscrUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscrUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscrUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscrUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cscrUpdateTime}
			 	
			 ${before$cscrAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscrAddTime" : (lz:join(relateObject,"$cscrAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscrAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscrAddTime || #request.defines['cscrAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscrAddTime":relateObject}" id="form-dl-cscrAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscrAddTimeYesNot=="not"?"checked-not":""}${data.cscrAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscrAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscrAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cscrAddTime}