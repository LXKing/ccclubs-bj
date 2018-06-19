<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$saId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saId" : (lz:join(relateObject,"$saId")))}
			 ${lz:set("cname",lz:join(relateObject,".saId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saId || #request.defines['saId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saId":relateObject}" id="form-dl-saId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saIdYesNot=="not"?"checked-not":""}${data.saIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.saId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$saId}
			 	
			 ${before$saName}
			 
			 ${lz:set("注释","*****************API名称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saName" : (lz:join(relateObject,"$saName")))}
			 ${lz:set("cname",lz:join(relateObject,".saName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saName || #request.defines['saName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saName":relateObject}" id="form-dl-saName">
					<dt>API名称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saNameYesNot=="not"?"checked-not":""}${data.saNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.saName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************API名称字段的查询代码结束*****************")}
			
			
			${after$saName}
			 	
			 ${before$saNamespace}
			 
			 ${lz:set("注释","*****************名称空间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saNamespace" : (lz:join(relateObject,"$saNamespace")))}
			 ${lz:set("cname",lz:join(relateObject,".saNamespace"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saNamespace || #request.defines['saNamespace']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saNamespace":relateObject}" id="form-dl-saNamespace">
					<dt>名称空间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saNamespaceYesNot=="not"?"checked-not":""}${data.saNamespaceYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saNamespaceYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.saNamespace}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************名称空间字段的查询代码结束*****************")}
			
			
			${after$saNamespace}
			 	
			 ${before$saType}
			 
			 ${lz:set("注释","*****************接口分类字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saType" : (lz:join(relateObject,"$saType")))}
			 ${lz:set("cname",lz:join(relateObject,".saType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saType || #request.defines['saType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saType":relateObject}" id="form-dl-saType">
					<dt>接口分类：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saTypeYesNot=="not"?"checked-not":""}${data.saTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
			 					${lz:set("items", get:propertys("API接口分类","api_type"))}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.spId}" ${data.saType==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接口分类字段的查询代码结束*****************")}
			
			
			${after$saType}
			 	
			 ${before$saOpen}
			 
			 ${lz:set("注释","*****************是否开放字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saOpen" : (lz:join(relateObject,"$saOpen")))}
			 ${lz:set("cname",lz:join(relateObject,".saOpen"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saOpen || #request.defines['saOpen']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saOpen":relateObject}" id="form-dl-saOpen">
					<dt>是否开放：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saOpenYesNot=="not"?"checked-not":""}${data.saOpenYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saOpenYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.saOpen==1?"selected":""}>开放</option>
							<option value="0" ${data.saOpen==0?"selected":""}>非开放</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************是否开放字段的查询代码结束*****************")}
			
			
			${after$saOpen}
			 	
			 ${before$saDescript}
			 
			 ${lz:set("注释","*****************接口说明字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saDescript" : (lz:join(relateObject,"$saDescript")))}
			 ${lz:set("cname",lz:join(relateObject,".saDescript"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saDescript || #request.defines['saDescript']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saDescript":relateObject}" id="form-dl-saDescript">
					<dt>接口说明：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saDescriptYesNot=="not"?"checked-not":""}${data.saDescriptYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saDescriptYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.saDescript}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接口说明字段的查询代码结束*****************")}
			
			
			${after$saDescript}
			 	
			 ${before$saParams}
			 
			 ${lz:set("注释","*****************参数结构字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saParams" : (lz:join(relateObject,"$saParams")))}
			 ${lz:set("cname",lz:join(relateObject,".saParams"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saParams || #request.defines['saParams']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saParams":relateObject}" id="form-dl-saParams">
					<dt>参数结构：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saParamsYesNot=="not"?"checked-not":""}${data.saParamsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saParamsYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.saParams}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************参数结构字段的查询代码结束*****************")}
			
			
			${after$saParams}
			 	
			 ${before$saOauth}
			 
			 ${lz:set("注释","*****************需要用户授权字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saOauth" : (lz:join(relateObject,"$saOauth")))}
			 ${lz:set("cname",lz:join(relateObject,".saOauth"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saOauth || #request.defines['saOauth']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saOauth":relateObject}" id="form-dl-saOauth">
					<dt>需要用户授权：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saOauthYesNot=="not"?"checked-not":""}${data.saOauthYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saOauthYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.saOauth==0?"selected":""}>无需授权</option>
							<option value="1" ${data.saOauth==1?"selected":""}>需要授权</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************需要用户授权字段的查询代码结束*****************")}
			
			
			${after$saOauth}
			 	
			 ${before$saReturnStruct}
			 
			 ${lz:set("注释","*****************返回结构设计字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saReturnStruct" : (lz:join(relateObject,"$saReturnStruct")))}
			 ${lz:set("cname",lz:join(relateObject,".saReturnStruct"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saReturnStruct || #request.defines['saReturnStruct']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saReturnStruct":relateObject}" id="form-dl-saReturnStruct">
					<dt>返回结构设计：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saReturnStructYesNot=="not"?"checked-not":""}${data.saReturnStructYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saReturnStructYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.saReturnStruct}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************返回结构设计字段的查询代码结束*****************")}
			
			
			${after$saReturnStruct}
			 	
			 ${before$saEditor}
			 
			 ${lz:set("注释","*****************添加人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saEditor" : (lz:join(relateObject,"$saEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".saEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saEditor || #request.defines['saEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saEditor":relateObject}" id="form-dl-saEditor">
					<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saEditorYesNot=="not"?"checked-not":""}${data.saEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.saEditor}">
				 					${get:SrvUser(data.saEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人字段的查询代码结束*****************")}
			
			
			${after$saEditor}
			 	
			 ${before$saUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saUpdateTime" : (lz:join(relateObject,"$saUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".saUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saUpdateTime || #request.defines['saUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saUpdateTime":relateObject}" id="form-dl-saUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saUpdateTimeYesNot=="not"?"checked-not":""}${data.saUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.saUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.saUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.saUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.saUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.saUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$saUpdateTime}
			 	
			 ${before$saAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saAddTime" : (lz:join(relateObject,"$saAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".saAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saAddTime || #request.defines['saAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saAddTime":relateObject}" id="form-dl-saAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saAddTimeYesNot=="not"?"checked-not":""}${data.saAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.saAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.saAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.saAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.saAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.saAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$saAddTime}
			 	
			 ${before$saStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "saStatus" : (lz:join(relateObject,"$saStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".saStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.saStatus || #request.defines['saStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"saStatus":relateObject}" id="form-dl-saStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.saStatusYesNot=="not"?"checked-not":""}${data.saStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.saStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.saStatus==0?"selected":""}>测试中</option>
							<option value="1" ${data.saStatus==1?"selected":""}>正常</option>
							<option value="2" ${data.saStatus==2?"selected":""}>禁用</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$saStatus}