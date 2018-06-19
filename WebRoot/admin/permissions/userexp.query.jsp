<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$sueId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sueId" : (lz:join(relateObject,"$sueId")))}
			 ${lz:set("cname",lz:join(relateObject,".sueId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sueId || #request.defines['sueId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sueId":relateObject}" id="form-dl-sueId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sueIdYesNot=="not"?"checked-not":""}${data.sueIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sueIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.sueId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$sueId}
			 	
			 ${before$sueUser}
			 
			 ${lz:set("注释","*****************所属用户字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sueUser" : (lz:join(relateObject,"$sueUser")))}
			 ${lz:set("cname",lz:join(relateObject,".sueUser"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sueUser || #request.defines['sueUser']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sueUser":relateObject}" id="form-dl-sueUser">
					<dt>所属用户：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sueUserYesNot=="not"?"checked-not":""}${data.sueUserYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sueUserYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.sueUser}">
				 					${get:SrvUser(data.sueUser).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属用户字段的查询代码结束*****************")}
			
			
			${after$sueUser}
			 	
			 ${before$sueWxOpenid}
			 
			 ${lz:set("注释","*****************微信标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sueWxOpenid" : (lz:join(relateObject,"$sueWxOpenid")))}
			 ${lz:set("cname",lz:join(relateObject,".sueWxOpenid"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sueWxOpenid || #request.defines['sueWxOpenid']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sueWxOpenid":relateObject}" id="form-dl-sueWxOpenid">
					<dt>微信标识：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sueWxOpenidYesNot=="not"?"checked-not":""}${data.sueWxOpenidYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sueWxOpenidYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.sueWxOpenid}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************微信标识字段的查询代码结束*****************")}
			
			
			${after$sueWxOpenid}
			 	
			 ${before$sueIsQrOp}
			 
			 ${lz:set("注释","*****************外勤作业字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sueIsQrOp" : (lz:join(relateObject,"$sueIsQrOp")))}
			 ${lz:set("cname",lz:join(relateObject,".sueIsQrOp"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sueIsQrOp || #request.defines['sueIsQrOp']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sueIsQrOp":relateObject}" id="form-dl-sueIsQrOp">
					<dt>外勤作业：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sueIsQrOpYesNot=="not"?"checked-not":""}${data.sueIsQrOpYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sueIsQrOpYesNot}"/>
			 		<input type="radio" value="" name="${NAME}" id="${NAME}" ${data.sueIsQrOp==null?"checked":""}/>
					全部&nbsp;&nbsp;
			 	     <input type="radio" value="1" name="${NAME}" id="${NAME}" ${data.sueIsQrOp=='true'?"checked":""}/>
					是&nbsp;&nbsp;
					<input type="radio" value="0" name="${NAME}" id="${NAME}" ${data.sueIsQrOp=='false'?"checked":""}/>否
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************外勤作业字段的查询代码结束*****************")}
			
			
			${after$sueIsQrOp}
			 	
			 ${before$sueIsServicer}
			 
			 ${lz:set("注释","*****************是否客服字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sueIsServicer" : (lz:join(relateObject,"$sueIsServicer")))}
			 ${lz:set("cname",lz:join(relateObject,".sueIsServicer"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sueIsServicer || #request.defines['sueIsServicer']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sueIsServicer":relateObject}" id="form-dl-sueIsServicer">
					<dt>是否客服：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sueIsServicerYesNot=="not"?"checked-not":""}${data.sueIsServicerYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sueIsServicerYesNot}"/>
			 		<input type="radio" value="" name="${NAME}" id="${NAME}" ${data.sueIsServicer==null?"checked":""}/>
					全部&nbsp;&nbsp;
			 	     <input type="radio" value="1" name="${NAME}" id="${NAME}" ${data.sueIsServicer=='true'?"checked":""}/>
					是&nbsp;&nbsp;
					<input type="radio" value="0" name="${NAME}" id="${NAME}" ${data.sueIsServicer=='false'?"checked":""}/>否
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************是否客服字段的查询代码结束*****************")}
			
			
			${after$sueIsServicer}
			 	
			 ${before$sueChatNick}
			 
			 ${lz:set("注释","*****************客服昵称字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sueChatNick" : (lz:join(relateObject,"$sueChatNick")))}
			 ${lz:set("cname",lz:join(relateObject,".sueChatNick"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sueChatNick || #request.defines['sueChatNick']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sueChatNick":relateObject}" id="form-dl-sueChatNick">
					<dt>客服昵称：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sueChatNickYesNot=="not"?"checked-not":""}${data.sueChatNickYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sueChatNickYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.sueChatNick}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************客服昵称字段的查询代码结束*****************")}
			
			
			${after$sueChatNick}
			 	
			 ${before$sueChatSign}
			 
			 ${lz:set("注释","*****************客服签名字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "sueChatSign" : (lz:join(relateObject,"$sueChatSign")))}
			 ${lz:set("cname",lz:join(relateObject,".sueChatSign"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.sueChatSign || #request.defines['sueChatSign']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"sueChatSign":relateObject}" id="form-dl-sueChatSign">
					<dt>客服签名：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.sueChatSignYesNot=="not"?"checked-not":""}${data.sueChatSignYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.sueChatSignYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.sueChatSign}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************客服签名字段的查询代码结束*****************")}
			
			
			${after$sueChatSign}