<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cscmId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmId" : (lz:join(relateObject,"$cscmId")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmId || #request.defines['cscmId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmId":relateObject}" id="form-dl-cscmId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmIdYesNot=="not"?"checked-not":""}${data.cscmIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cscmId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cscmId}
			 	
			 ${before$cscmHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmHost" : (lz:join(relateObject,"$cscmHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmHost || #request.defines['cscmHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmHost":relateObject}" id="form-dl-cscmHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmHostYesNot=="not"?"checked-not":""}${data.cscmHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cscmHost}">
				 					${get:SrvHost(data.cscmHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cscmHost}
			 	
			 ${before$cscmContent}
			 
			 ${lz:set("注释","*****************消息内容字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmContent" : (lz:join(relateObject,"$cscmContent")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmContent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmContent || #request.defines['cscmContent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmContent":relateObject}" id="form-dl-cscmContent">
					<dt>消息内容：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmContentYesNot=="not"?"checked-not":""}${data.cscmContentYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmContentYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.cscmContent}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************消息内容字段的查询代码结束*****************")}
			
			
			${after$cscmContent}
			 	
			 ${before$cscmType}
			 
			 ${lz:set("注释","*****************内容类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmType" : (lz:join(relateObject,"$cscmType")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmType || #request.defines['cscmType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmType":relateObject}" id="form-dl-cscmType">
					<dt>内容类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmTypeYesNot=="not"?"checked-not":""}${data.cscmTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="text" ${data.cscmType==text?"selected":""}>文本</option>
							<option value="image" ${data.cscmType==image?"selected":""}>图片</option>
							<option value="news" ${data.cscmType==news?"selected":""}>图文</option>
							<option value="html" ${data.cscmType==html?"selected":""}>特殊</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************内容类型字段的查询代码结束*****************")}
			
			
			${after$cscmType}
			 	
			 ${before$cscmFrom}
			 
			 ${lz:set("注释","*****************发送来源字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmFrom" : (lz:join(relateObject,"$cscmFrom")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmFrom"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmFrom || #request.defines['cscmFrom']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmFrom":relateObject}" id="form-dl-cscmFrom">
					<dt>发送来源：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmFromYesNot=="not"?"checked-not":""}${data.cscmFromYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmFromYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscmFrom}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发送来源字段的查询代码结束*****************")}
			
			
			${after$cscmFrom}
			 	
			 ${before$cscmTarget}
			 
			 ${lz:set("注释","*****************接收目标字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmTarget" : (lz:join(relateObject,"$cscmTarget")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmTarget"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmTarget || #request.defines['cscmTarget']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmTarget":relateObject}" id="form-dl-cscmTarget">
					<dt>接收目标：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmTargetYesNot=="not"?"checked-not":""}${data.cscmTargetYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmTargetYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cscmTarget}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接收目标字段的查询代码结束*****************")}
			
			
			${after$cscmTarget}
			 	
			 ${before$cscmUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmUpdateTime" : (lz:join(relateObject,"$cscmUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmUpdateTime || #request.defines['cscmUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmUpdateTime":relateObject}" id="form-dl-cscmUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmUpdateTimeYesNot=="not"?"checked-not":""}${data.cscmUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscmUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscmUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscmUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscmUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscmUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cscmUpdateTime}
			 	
			 ${before$cscmAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmAddTime" : (lz:join(relateObject,"$cscmAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmAddTime || #request.defines['cscmAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmAddTime":relateObject}" id="form-dl-cscmAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmAddTimeYesNot=="not"?"checked-not":""}${data.cscmAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cscmAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cscmAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cscmAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cscmAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cscmAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cscmAddTime}
			 	
			 ${before$cscmState}
			 
			 ${lz:set("注释","*****************接收状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmState" : (lz:join(relateObject,"$cscmState")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmState"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmState || #request.defines['cscmState']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmState":relateObject}" id="form-dl-cscmState">
					<dt>接收状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmStateYesNot=="not"?"checked-not":""}${data.cscmStateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmStateYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cscmState==0?"selected":""}>未读</option>
							<option value="1" ${data.cscmState==1?"selected":""}>已读</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************接收状态字段的查询代码结束*****************")}
			
			
			${after$cscmState}
			 	
			 ${before$cscmStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cscmStatus" : (lz:join(relateObject,"$cscmStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cscmStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cscmStatus || #request.defines['cscmStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cscmStatus":relateObject}" id="form-dl-cscmStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cscmStatusYesNot=="not"?"checked-not":""}${data.cscmStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cscmStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.cscmStatus==1?"selected":""}>默认</option>
							<option value="0" ${data.cscmStatus==0?"selected":""}>无效</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cscmStatus}