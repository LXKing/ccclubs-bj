<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cssmId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssmId" : (lz:join(relateObject,"$cssmId")))}
			 ${lz:set("cname",lz:join(relateObject,".cssmId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssmId || #request.defines['cssmId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssmId":relateObject}" id="form-dl-cssmId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssmIdYesNot=="not"?"checked-not":""}${data.cssmIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssmIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cssmId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cssmId}
			 	
			 ${before$cssmHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssmHost" : (lz:join(relateObject,"$cssmHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cssmHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssmHost || #request.defines['cssmHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssmHost":relateObject}" id="form-dl-cssmHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssmHostYesNot=="not"?"checked-not":""}${data.cssmHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssmHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cssmHost}">
				 					${get:SrvHost(data.cssmHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cssmHost}
			 	
			 ${before$cssmType}
			 
			 ${lz:set("注释","*****************短信类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssmType" : (lz:join(relateObject,"$cssmType")))}
			 ${lz:set("cname",lz:join(relateObject,".cssmType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssmType || #request.defines['cssmType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssmType":relateObject}" id="form-dl-cssmType">
					<dt>短信类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssmTypeYesNot=="not"?"checked-not":""}${data.cssmTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssmTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.cssmType==1?"selected":""}>代码类短信</option>
							<option value="2" ${data.cssmType==2?"selected":""}>通知类短信</option>
							<option value="3" ${data.cssmType==3?"selected":""}>提示类短信</option>
							<option value="4" ${data.cssmType==4?"selected":""}>广告类短信</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************短信类型字段的查询代码结束*****************")}
			
			
			${after$cssmType}
			 	
			 ${before$cssmMobile}
			 
			 ${lz:set("注释","*****************接收号码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssmMobile" : (lz:join(relateObject,"$cssmMobile")))}
			 ${lz:set("cname",lz:join(relateObject,".cssmMobile"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssmMobile || #request.defines['cssmMobile']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssmMobile":relateObject}" id="form-dl-cssmMobile">
					<dt>接收号码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssmMobileYesNot=="not"?"checked-not":""}${data.cssmMobileYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssmMobileYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cssmMobile}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接收号码字段的查询代码结束*****************")}
			
			
			${after$cssmMobile}
			 	
			 ${before$cssmContent}
			 
			 ${lz:set("注释","*****************短信内容字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssmContent" : (lz:join(relateObject,"$cssmContent")))}
			 ${lz:set("cname",lz:join(relateObject,".cssmContent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssmContent || #request.defines['cssmContent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssmContent":relateObject}" id="form-dl-cssmContent">
					<dt>短信内容：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssmContentYesNot=="not"?"checked-not":""}${data.cssmContentYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssmContentYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="1024" name="${NAME}" id="${NAME}"  value="${data.cssmContent}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************短信内容字段的查询代码结束*****************")}
			
			
			${after$cssmContent}
			 	
			 ${before$cssmEditor}
			 
			 ${lz:set("注释","*****************操作人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssmEditor" : (lz:join(relateObject,"$cssmEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".cssmEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssmEditor || #request.defines['cssmEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssmEditor":relateObject}" id="form-dl-cssmEditor">
					<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssmEditorYesNot=="not"?"checked-not":""}${data.cssmEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssmEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cssmEditor}">
				 					${get:SrvUser(data.cssmEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作人字段的查询代码结束*****************")}
			
			
			${after$cssmEditor}
			 	
			 ${before$cssmChannel}
			 
			 ${lz:set("注释","*****************发送通道字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssmChannel" : (lz:join(relateObject,"$cssmChannel")))}
			 ${lz:set("cname",lz:join(relateObject,".cssmChannel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssmChannel || #request.defines['cssmChannel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssmChannel":relateObject}" id="form-dl-cssmChannel">
					<dt>发送通道：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssmChannelYesNot=="not"?"checked-not":""}${data.cssmChannelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssmChannelYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'SMS_CHANNEL') )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.spId}" ${data.cssmChannel==item.spId?"selected":""}>${item.spName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发送通道字段的查询代码结束*****************")}
			
			
			${after$cssmChannel}
			 	
			 ${before$cssmUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssmUpdateTime" : (lz:join(relateObject,"$cssmUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cssmUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssmUpdateTime || #request.defines['cssmUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssmUpdateTime":relateObject}" id="form-dl-cssmUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssmUpdateTimeYesNot=="not"?"checked-not":""}${data.cssmUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssmUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cssmUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cssmUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cssmUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cssmUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cssmUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cssmUpdateTime}
			 	
			 ${before$cssmAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssmAddTime" : (lz:join(relateObject,"$cssmAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cssmAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssmAddTime || #request.defines['cssmAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssmAddTime":relateObject}" id="form-dl-cssmAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssmAddTimeYesNot=="not"?"checked-not":""}${data.cssmAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssmAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cssmAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cssmAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cssmAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cssmAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cssmAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cssmAddTime}
			 	
			 ${before$cssmStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cssmStatus" : (lz:join(relateObject,"$cssmStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cssmStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cssmStatus || #request.defines['cssmStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cssmStatus":relateObject}" id="form-dl-cssmStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cssmStatusYesNot=="not"?"checked-not":""}${data.cssmStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cssmStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.cssmStatus==1?"selected":""}>待发送</option>
							<option value="2" ${data.cssmStatus==2?"selected":""}>已发送</option>
							<option value="3" ${data.cssmStatus==3?"selected":""}>发送失败</option>
							<option value="0" ${data.cssmStatus==0?"selected":""}>停止发送</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cssmStatus}