<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 
			 
			<dl group="${relateObject==null?"memberConsuMe.csMember.csmId":relateObject}" id="form-dl-memberConsuMe.csMember.csmId">
				<dt>使用会员：</dt>
				<dd>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}" size="16" type="text" id="csmId" name="csmId" text="${get:CsMember(data.csmId).csmName}" value="${csMember.csmName}"/>
				</dd>
			</dl>
			 	
			 
			<%--<dl group="${relateObject==null?"csoFeeType":relateObject}" id="form-dl-csoFeeType">
				<dt>计费类型：</dt>
				<dd>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
	 					${lz:set("items", lz:query("ccclubs_system","select * from cs_user_type where 1=1 ") )}
						<s:iterator value="#request.items" id="item" status="i">
						<option value="${item.csutId}" ${data.csoFeeType==item.csutId?"selected":""}>${item.csutName}</option>
						</s:iterator>
						${lz:set("items",null)}
			 		</select>
				</dd>
			</dl>
			 	--%>
			 <dl group="${relateObject==null?"unitInfo.csuiId":relateObject}" id="form-dl-unitInfo">
				<dt>所属单位：</dt>
				<dd>
		 			<input title="请输入企业信息名称进行查询" class="combox" action="${basePath}${proname}/unit/info_query.do?value={param}" size="16" type="text" id="csuiId" name="csuiId" text="${get:CsUnitInfo(data.csuoUnitInfo).csuiName}" value="${unitInfo.csuiName}"/>
				</dd>
			</dl>
			
			
			 	
			<dl group="${relateObject==null?"unitInfo.csugId":relateObject}" id="form-dl-csuoUnitGroup">
				<dt>所属分组：</dt>
				<dd>
		 			<input title="请输入分组信息组名称进行查询" class="combox" action="${basePath}${proname}/unit/group_query.do?value={param}" size="16" type="text" id="csugId" name="csugId" text="${get:CsUnitGroup(data.csuoUnitGroup).csugName}" value="${unitGroup.csugName}"/>
				</dd>
			</dl>
			
			
			<dl group="${relateObject==null?"addTime":relateObject}" id="form-dl-addTime">
				<dt>添加时间：</dt>
				<dd>
		 			<input type="datetime" class="input" ref="addTime" name="addTime" id="addTime" start="${lz:date(addTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(addTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${addTime}">
				</dd>
			</dl>
			 	
		
			 	
			 
			
			
			 	
			 
			
			
			