<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cssoiId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoiId" : (lz:join(relateObject,"$cssoiId")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoiId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoiId || #request.defines['cssoiId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoiId":relateObject}" id="form-dl-cssoiId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoiIdYesNot=="not"?"checked-not":""}${data.cssoiIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoiIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cssoiId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cssoiId}
			 	
			 ${before$cssoiOrder}
			 
			 ${lz:set("注释","*****************所属订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoiOrder" : (lz:join(relateObject,"$cssoiOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoiOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoiOrder || #request.defines['cssoiOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoiOrder":relateObject}" id="form-dl-cssoiOrder">
					<dt>所属订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoiOrderYesNot=="not"?"checked-not":""}${data.cssoiOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoiOrderYesNot}"/>
			 			<input title="请输入商城订单编号进行查询" class="combox" action="${basePath}${proname}/shop/order_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsShopOrder(data.cssoiOrder).cssoId}" value="${data.cssoiOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/shop/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cssoiOrder']>0">
				${lz:set("注释","****当所属订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cssoiOrder")}
			  	<input type="hidden" name="cssoiOrder$on" id="CsShopOrder$on" value="true"/>
			  	<jsp:include page="/admin/shop/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cssoiOrder}
			 	
			 ${before$cssoiItem}
			 
			 ${lz:set("注释","*****************商品信息字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoiItem" : (lz:join(relateObject,"$cssoiItem")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoiItem"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoiItem || #request.defines['cssoiItem']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoiItem":relateObject}" id="form-dl-cssoiItem">
					<dt>商品信息：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoiItemYesNot=="not"?"checked-not":""}${data.cssoiItemYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoiItemYesNot}"/>
			 			<input title="请输入商品信息商品名称进行查询" class="combox" action="${basePath}${proname}/fee/item_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsItem(data.cssoiItem).csiTitle}" value="${data.cssoiItem}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/item_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************商品信息字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cssoiItem']>0">
				${lz:set("注释","****当商品信息字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cssoiItem")}
			  	<input type="hidden" name="cssoiItem$on" id="CsItem$on" value="true"/>
			  	<jsp:include page="/admin/fee/item.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cssoiItem}
			 	
			 ${before$cssoiMember}
			 
			 ${lz:set("注释","*****************会员帐号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoiMember" : (lz:join(relateObject,"$cssoiMember")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoiMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoiMember || #request.defines['cssoiMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoiMember":relateObject}" id="form-dl-cssoiMember">
					<dt>会员帐号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoiMemberYesNot=="not"?"checked-not":""}${data.cssoiMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoiMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.cssoiMember).csmName}" value="${data.cssoiMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************会员帐号字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cssoiMember']>0">
				${lz:set("注释","****当会员帐号字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cssoiMember")}
			  	<input type="hidden" name="cssoiMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cssoiMember}
			 	
			 ${before$cssoiTitle}
			 
			 ${lz:set("注释","*****************商品名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoiTitle" : (lz:join(relateObject,"$cssoiTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoiTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoiTitle || #request.defines['cssoiTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoiTitle":relateObject}" id="form-dl-cssoiTitle">
					<dt>商品名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoiTitleYesNot=="not"?"checked-not":""}${data.cssoiTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoiTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cssoiTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************商品名称字段的查询代码结束*****************")}
			
			
			${after$cssoiTitle}
			 	
			 ${before$cssoiEditor}
			 
			 ${lz:set("注释","*****************修改人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoiEditor" : (lz:join(relateObject,"$cssoiEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoiEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoiEditor || #request.defines['cssoiEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoiEditor":relateObject}" id="form-dl-cssoiEditor">
					<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoiEditorYesNot=="not"?"checked-not":""}${data.cssoiEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoiEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cssoiEditor}">
				 					${get:SrvUser(data.cssoiEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改人字段的查询代码结束*****************")}
			
			
			${after$cssoiEditor}
			 	
			 ${before$cssoiRemark}
			 
			 ${lz:set("注释","*****************备注字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoiRemark" : (lz:join(relateObject,"$cssoiRemark")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoiRemark"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoiRemark || #request.defines['cssoiRemark']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoiRemark":relateObject}" id="form-dl-cssoiRemark">
					<dt>备　　注：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoiRemarkYesNot=="not"?"checked-not":""}${data.cssoiRemarkYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoiRemarkYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="${NAME}" id="${NAME}"  value="${data.cssoiRemark}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************备注字段的查询代码结束*****************")}
			
			
			${after$cssoiRemark}
			 	
			 ${before$cssoiUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoiUpdateTime" : (lz:join(relateObject,"$cssoiUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoiUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoiUpdateTime || #request.defines['cssoiUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoiUpdateTime":relateObject}" id="form-dl-cssoiUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoiUpdateTimeYesNot=="not"?"checked-not":""}${data.cssoiUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoiUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cssoiUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cssoiUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cssoiUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cssoiUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cssoiUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cssoiUpdateTime}
			 	
			 ${before$cssoiAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoiAddTime" : (lz:join(relateObject,"$cssoiAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoiAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoiAddTime || #request.defines['cssoiAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoiAddTime":relateObject}" id="form-dl-cssoiAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoiAddTimeYesNot=="not"?"checked-not":""}${data.cssoiAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoiAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cssoiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cssoiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cssoiAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cssoiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cssoiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cssoiAddTime}
			 	
			 ${before$cssoiStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssoiStatus" : (lz:join(relateObject,"$cssoiStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cssoiStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssoiStatus || #request.defines['cssoiStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssoiStatus":relateObject}" id="form-dl-cssoiStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssoiStatusYesNot=="not"?"checked-not":""}${data.cssoiStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssoiStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.cssoiStatus==1?"selected":""}>正常</option>
							<option value="0" ${data.cssoiStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cssoiStatus}