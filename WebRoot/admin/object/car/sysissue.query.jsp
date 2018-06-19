<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cscsiId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsiId" : (lz:join(relateObject,"$cscsiId")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsiId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsiId || #request.defines['cscsiId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsiId":relateObject}" id="form-dl-cscsiId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsiIdYesNot=="not"?"checked-not":""}${data.cscsiIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsiIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cscsiId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cscsiId}
			 	
			  ${before$cscsiHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsiHost" : (lz:join(relateObject,"$cscsiHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsiHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsiHost || #request.defines['cscsiHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsiHost":relateObject}" id="form-dl-cscsiHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsiHostYesNot=="not"?"checked-not":""}${data.cscsiHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsiHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscsiHost}">
				 					${get:SrvHost(data.cscsiHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cscsiHost}
			
			
			 ${before$cscsiCar}
			 
			 ${lz:set("注释","*****************所属车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsiCar" : (lz:join(relateObject,"$cscsiCar")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsiCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsiCar || #request.defines['cscsiCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsiCar":relateObject}" id="form-dl-cscsiCar">
					<dt>所属车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsiCarYesNot=="not"?"checked-not":""}${data.cscsiCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsiCarYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cscoHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.cscsiCar).cscNumber}" value="${data.cscsiCar}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cscsiCar']>0">
				${lz:set("注释","****当所属车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cscsiCar")}
			  	<input type="hidden" name="cscsiCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cscsiCar}
			 	
			 ${before$cscsiParts}
			 
			 ${lz:set("注释","*****************车部位不字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsiParts" : (lz:join(relateObject,"$cscsiParts")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsiParts"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsiParts || #request.defines['cscsiParts']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsiParts":relateObject}" id="form-dl-cscsiParts">
					<dt>部位：</dt>
					<dd>
					
		 	 			<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsiPartsYesNot=="not"?"checked-not":""}${data.cscsiPartsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsiPartsYesNot}"/>
			 			<input title="请输入名称进行查询" class="combox" action="${basePath}${proname}/object/car/carparts_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCarParts(data.cscsiParts).cscpName}" value="${data.cscsiParts}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car/carparts_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************名称字段的查询代码结束*****************")}
			
			
			${after$cscsiParts}
			 	
			 	
			 ${before$cscsiAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsiAddTime" : (lz:join(relateObject,"$cscsiAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsiAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsiAddTime || #request.defines['cscsiAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsiAddTime":relateObject}" id="form-dl-cscsiAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsiAddTimeYesNot=="not"?"checked-not":""}${data.cscsiAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsiAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscsiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscsiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscsiAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscsiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscsiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cscsiAddTime}
			
			${before$cscsiUpdateTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsiUpdateTime" : (lz:join(relateObject,"$cscsiUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsiUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsiUpdateTime || #request.defines['cscsiUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsiUpdateTime":relateObject}" id="form-dl-cscsiUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsiUpdateTimeYesNot=="not"?"checked-not":""}${data.cscsiUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsiUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscsiUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscsiUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscsiUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscsiUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscsiUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cscsiUpdateTime}
			
			${before$cscsiType}
			 
			 ${lz:set("注释","*****************类别字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscsiType" : (lz:join(relateObject,"$cscsiType")))}
			 ${lz:set("cname",lz:join(relateObject,".cscsiType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscsiType || #request.defines['cscsiType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscsiType":relateObject}" id="form-dl-cscsiType">
					<dt>类别：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscsiTypeYesNot=="not"?"checked-not":""}${data.cscsiTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscsiTypeYesNot}"/>
				 		<select id="${NAME}" name="${NAME}" >
				 			<option value="">全部</option>
							<option value="1" ${data.cscsiType==1?"selected":""}>刮痕</option>
							<option value="2" ${data.cscsiType==2?"selected":""}>凹痕</option>
							<option value="3" ${data.cscsiType==3?"selected":""}>损坏</option>
							<option value="4" ${data.cscsiType==4?"selected":""}>裂痕</option>
							<option value="5" ${data.cscsiType==5?"selected":""}>非正常工作</option>
							<option value="6" ${data.cscsiType==6?"selected":""}>缺失零件</option>
				 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************类别字段的查询代码结束*****************")}
			
			
			${after$cscsiType} 
			 
			 	
			 ${before$cscmiStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmiStatus" : (lz:join(relateObject,"$cscmiStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmiStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmiStatus || #request.defines['cscmiStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmiStatus":relateObject}" id="form-dl-cscmiStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmiStatusYesNot=="not"?"checked-not":""}${data.cscmiStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmiStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.cscmiStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.cscmiStatus==0?"selected":""}>禁用</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cscmiStatus}
			
			