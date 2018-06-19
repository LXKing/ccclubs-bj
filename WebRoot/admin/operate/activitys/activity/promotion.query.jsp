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
			 	
			 ${before$cspHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspHost" : (lz:join(relateObject,"$cspHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cspHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspHost || #request.defines['cspHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspHost":relateObject}" id="form-dl-cspHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspHostYesNot=="not"?"checked-not":""}${data.cspHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cspHost}">
				 					${get:SrvHost(data.cspHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cspHost}
			 	
			 ${before$cspName}
			 
			 ${lz:set("注释","*****************促销名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspName" : (lz:join(relateObject,"$cspName")))}
			 ${lz:set("cname",lz:join(relateObject,".cspName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspName || #request.defines['cspName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspName":relateObject}" id="form-dl-cspName">
					<dt>促销名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspNameYesNot=="not"?"checked-not":""}${data.cspNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cspName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************促销名称字段的查询代码结束*****************")}
			
			
			${after$cspName}
			 	
			 ${before$cspActivity}
			 
			 ${lz:set("注释","*****************所属活动字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspActivity" : (lz:join(relateObject,"$cspActivity")))}
			 ${lz:set("cname",lz:join(relateObject,".cspActivity"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspActivity || #request.defines['cspActivity']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspActivity":relateObject}" id="form-dl-cspActivity">
					<dt>所属活动：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspActivityYesNot=="not"?"checked-not":""}${data.cspActivityYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspActivityYesNot}"/>
			 			<input title="请输入营销活动活动主题进行查询" class="combox" action="${basePath}${proname}/operate/activitys/activity_query.do?value={param}&csaHost={cspHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsActivity(data.cspActivity).csaTitle}" value="${data.cspActivity}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/operate/activitys/activity_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属活动字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cspActivity']>0">
				${lz:set("注释","****当所属活动字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cspActivity")}
			  	<input type="hidden" name="cspActivity$on" id="CsActivity$on" value="true"/>
			  	<jsp:include page="/admin/operate/activitys/activity.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cspActivity}
			 	
			 ${before$cspType}
			 
			 ${lz:set("注释","*****************促销类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspType" : (lz:join(relateObject,"$cspType")))}
			 ${lz:set("cname",lz:join(relateObject,".cspType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspType || #request.defines['cspType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspType":relateObject}" id="form-dl-cspType">
					<dt>促销类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspTypeYesNot=="not"?"checked-not":""}${data.cspTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cspType==0?"selected":""}>充值类</option>
							<option value="1" ${data.cspType==1?"selected":""}>购买类</option>
							<option value="2" ${data.cspType==2?"selected":""}>消费类</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************促销类型字段的查询代码结束*****************")}
			
			
			${after$cspType}
			 	
			 ${before$cspPackage}
			 
			 ${lz:set("注释","*****************关联套餐字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspPackage" : (lz:join(relateObject,"$cspPackage")))}
			 ${lz:set("cname",lz:join(relateObject,".cspPackage"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspPackage || #request.defines['cspPackage']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspPackage":relateObject}" id="form-dl-cspPackage">
					<dt>关联套餐：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspPackageYesNot=="not"?"checked-not":""}${data.cspPackageYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspPackageYesNot}"/>
			 			<input title="请输入系统套餐套餐名称进行查询" class="combox" action="${basePath}${proname}/fee/pack_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsPack(data.cspPackage).cspName}" value="${data.cspPackage}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/pack_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联套餐字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cspPackage']>0">
				${lz:set("注释","****当关联套餐字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cspPackage")}
			  	<input type="hidden" name="cspPackage$on" id="CsPack$on" value="true"/>
			  	<jsp:include page="/admin/fee/pack.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cspPackage}
			 	
			 ${before$cspItems}
			 
			 ${lz:set("注释","*****************关联宝贝字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspItems" : (lz:join(relateObject,"$cspItems")))}
			 ${lz:set("cname",lz:join(relateObject,".cspItems"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspItems || #request.defines['cspItems']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspItems":relateObject}" id="form-dl-cspItems">
					<dt>关联宝贝：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspItemsYesNot=="not"?"checked-not":""}${data.cspItemsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspItemsYesNot}"/>
					<input title="请输入商品信息商品名称进行查询" class="combox" action="select * from cs_item where 1=1?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsItem(data.cspItems).csiTitle}" value="${data.cspItems}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联宝贝字段的查询代码结束*****************")}
			
			
			${after$cspItems}
			 	
			 ${before$cspPresents}
			 
			 ${lz:set("注释","*****************关联赠品字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspPresents" : (lz:join(relateObject,"$cspPresents")))}
			 ${lz:set("cname",lz:join(relateObject,".cspPresents"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspPresents || #request.defines['cspPresents']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspPresents":relateObject}" id="form-dl-cspPresents">
					<dt>关联赠品：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspPresentsYesNot=="not"?"checked-not":""}${data.cspPresentsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspPresentsYesNot}"/>
					<input title="请输入礼品赠品礼品名称进行查询" class="combox" action="select * from cs_present where 1=1?value={param}&cspHost={cspHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsPresent(data.cspPresents).cspName}" value="${data.cspPresents}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联赠品字段的查询代码结束*****************")}
			
			
			${after$cspPresents}
			 	
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
							<option value="0" ${data.cspStatus==0?"selected":""}>配置中</option>
							<option value="1" ${data.cspStatus==1?"selected":""}>运营中</option>
							<option value="2" ${data.cspStatus==2?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cspStatus}