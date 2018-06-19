<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csiId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiId" : (lz:join(relateObject,"$csiId")))}
			 ${lz:set("cname",lz:join(relateObject,".csiId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiId || #request.defines['csiId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiId":relateObject}" id="form-dl-csiId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiIdYesNot=="not"?"checked-not":""}${data.csiIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csiId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csiId}
			 	
			 ${before$csiTitle}
			 
			 ${lz:set("注释","*****************商品名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiTitle" : (lz:join(relateObject,"$csiTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".csiTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiTitle || #request.defines['csiTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiTitle":relateObject}" id="form-dl-csiTitle">
					<dt>商品名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiTitleYesNot=="not"?"checked-not":""}${data.csiTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csiTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************商品名称字段的查询代码结束*****************")}
			
			
			${after$csiTitle}
			 	
			 ${before$csiType}
			 
			 ${lz:set("注释","*****************商品类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiType" : (lz:join(relateObject,"$csiType")))}
			 ${lz:set("cname",lz:join(relateObject,".csiType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiType || #request.defines['csiType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiType":relateObject}" id="form-dl-csiType">
					<dt>商品类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiTypeYesNot=="not"?"checked-not":""}${data.csiTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csiType==0?"selected":""}>实物</option>
							<option value="1" ${data.csiType==1?"selected":""}>现金券</option>
							<option value="2" ${data.csiType==2?"selected":""}>免费小时</option>
							<option value="3" ${data.csiType==3?"selected":""}>红包</option>
							<option value="4" ${data.csiType==4?"selected":""}>套餐</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************商品类型字段的查询代码结束*****************")}
			
			
			${after$csiType}
			 	
			 ${before$csiSort}
			 
			 ${lz:set("注释","*****************商品分类字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiSort" : (lz:join(relateObject,"$csiSort")))}
			 ${lz:set("cname",lz:join(relateObject,".csiSort"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiSort || #request.defines['csiSort']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiSort":relateObject}" id="form-dl-csiSort">
					<dt>商品分类：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiSortYesNot=="not"?"checked-not":""}${data.csiSortYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiSortYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'ITEM_TYPE') )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.spId}" ${data.csiSort==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************商品分类字段的查询代码结束*****************")}
			
			
			${after$csiSort}
			 	
			 ${before$csiFlag}
			 
			 ${lz:set("注释","*****************标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiFlag" : (lz:join(relateObject,"$csiFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".csiFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiFlag || #request.defines['csiFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiFlag":relateObject}" id="form-dl-csiFlag">
					<dt>标　　识：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiFlagYesNot=="not"?"checked-not":""}${data.csiFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiFlagYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csiFlag}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************标识字段的查询代码结束*****************")}
			
			
			${after$csiFlag}
			 	
			 ${before$csiRemark}
			 
			 ${lz:set("注释","*****************备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiRemark" : (lz:join(relateObject,"$csiRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".csiRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiRemark || #request.defines['csiRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiRemark":relateObject}" id="form-dl-csiRemark">
					<dt>备　　注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiRemarkYesNot=="not"?"checked-not":""}${data.csiRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.csiRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注字段的查询代码结束*****************")}
			
			
			${after$csiRemark}
			 	
			 ${before$csiUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiUpdateTime" : (lz:join(relateObject,"$csiUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csiUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiUpdateTime || #request.defines['csiUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiUpdateTime":relateObject}" id="form-dl-csiUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiUpdateTimeYesNot=="not"?"checked-not":""}${data.csiUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csiUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csiUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csiUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csiUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csiUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csiUpdateTime}
			 	
			 ${before$csiAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiAddTime" : (lz:join(relateObject,"$csiAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csiAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiAddTime || #request.defines['csiAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiAddTime":relateObject}" id="form-dl-csiAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiAddTimeYesNot=="not"?"checked-not":""}${data.csiAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csiAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csiAddTime}
			 	
			 ${before$csiStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csiStatus" : (lz:join(relateObject,"$csiStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csiStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csiStatus || #request.defines['csiStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csiStatus":relateObject}" id="form-dl-csiStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csiStatusYesNot=="not"?"checked-not":""}${data.csiStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csiStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csiStatus==1?"selected":""}>正常</option>
							<option value="2" ${data.csiStatus==2?"selected":""}>下架</option>
							<option value="0" ${data.csiStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csiStatus}