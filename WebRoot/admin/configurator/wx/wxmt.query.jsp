<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cswmId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmId" : (lz:join(relateObject,"$cswmId")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmId || #request.defines['cswmId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmId":relateObject}" id="form-dl-cswmId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmIdYesNot=="not"?"checked-not":""}${data.cswmIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cswmId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cswmId}
			 	
			 ${before$cswmHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmHost" : (lz:join(relateObject,"$cswmHost")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmHost || #request.defines['cswmHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmHost":relateObject}" id="form-dl-cswmHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmHostYesNot=="not"?"checked-not":""}${data.cswmHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cswmHost}">
				 					${get:SrvHost(data.cswmHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$cswmHost}
			 	
			 ${before$cswmMember}
			 
			 ${lz:set("注释","*****************关联会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmMember" : (lz:join(relateObject,"$cswmMember")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmMember || #request.defines['cswmMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmMember":relateObject}" id="form-dl-cswmMember">
					<dt>关联会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmMemberYesNot=="not"?"checked-not":""}${data.cswmMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={cswmHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.cswmMember).csmName}" value="${data.cswmMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cswmMember']>0">
				${lz:set("注释","****当关联会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cswmMember")}
			  	<input type="hidden" name="cswmMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cswmMember}
			 	
			 ${before$cswmMsgFlag}
			 
			 ${lz:set("注释","*****************分类标识字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmMsgFlag" : (lz:join(relateObject,"$cswmMsgFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmMsgFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmMsgFlag || #request.defines['cswmMsgFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmMsgFlag":relateObject}" id="form-dl-cswmMsgFlag">
					<dt>分类标识：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmMsgFlagYesNot=="not"?"checked-not":""}${data.cswmMsgFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmMsgFlagYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cswmMsgFlag}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************分类标识字段的查询代码结束*****************")}
			
			
			${after$cswmMsgFlag}
			 	
			 ${before$cswmRelate}
			 
			 ${lz:set("注释","*****************关联对象字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmRelate" : (lz:join(relateObject,"$cswmRelate")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmRelate"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmRelate || #request.defines['cswmRelate']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmRelate":relateObject}" id="form-dl-cswmRelate">
					<dt>关联对象：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmRelateYesNot=="not"?"checked-not":""}${data.cswmRelateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmRelateYesNot}"/>
			 		<input title="请输入动态查询" class="combox" generic="cswmRelateGeneric" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:generic(data.cswmRelate)}" value="${data.cswmRelate}"/>
			 		${lz:set("models",get:models("CsTrouble,CsViolat,CsOrder"))}
				 	<select id="cswmRelateGeneric" style="display:none;">
				 		<option value="">--选择模块--</option>
				 		<s:iterator value="#request.models" id="item" status="i">
				 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
				 		</s:iterator>
				 	</select>
			 		<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联对象字段的查询代码结束*****************")}
			
			
			${after$cswmRelate}
			 	
			 ${before$cswmContent}
			 
			 ${lz:set("注释","*****************消息内容字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmContent" : (lz:join(relateObject,"$cswmContent")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmContent"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmContent || #request.defines['cswmContent']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmContent":relateObject}" id="form-dl-cswmContent">
					<dt>消息内容：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmContentYesNot=="not"?"checked-not":""}${data.cswmContentYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmContentYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.cswmContent}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************消息内容字段的查询代码结束*****************")}
			
			
			${after$cswmContent}
			 	
			 ${before$cswmMo}
			 
			 ${lz:set("注释","*****************关联上行字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmMo" : (lz:join(relateObject,"$cswmMo")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmMo"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmMo || #request.defines['cswmMo']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmMo":relateObject}" id="form-dl-cswmMo">
					<dt>关联上行：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmMoYesNot=="not"?"checked-not":""}${data.cswmMoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmMoYesNot}"/>
			 			<input title="请输入微信上行编号进行查询" class="combox" action="${basePath}${proname}/configurator/wx/wxmo_query.do?value={param}&cswmHost={cswmHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsWxMo(data.cswmMo).cswmId}" value="${data.cswmMo}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/configurator/wx/wxmo_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************关联上行字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cswmMo']>0">
				${lz:set("注释","****当关联上行字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cswmMo")}
			  	<input type="hidden" name="cswmMo$on" id="CsWxMo$on" value="true"/>
			  	<jsp:include page="/admin/configurator/wx/wxmo.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cswmMo}
			 	
			 ${before$cswmSendTime}
			 
			 ${lz:set("注释","*****************发送时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmSendTime" : (lz:join(relateObject,"$cswmSendTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmSendTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmSendTime || #request.defines['cswmSendTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmSendTime":relateObject}" id="form-dl-cswmSendTime">
					<dt>发送时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmSendTimeYesNot=="not"?"checked-not":""}${data.cswmSendTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmSendTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cswmSendTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cswmSendTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cswmSendTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cswmSendTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>发送时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cswmSendTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发送时间字段的查询代码结束*****************")}
			
			
			${after$cswmSendTime}
			 	
			 ${before$cswmMask}
			 
			 ${lz:set("注释","*****************发送状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmMask" : (lz:join(relateObject,"$cswmMask")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmMask"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmMask || #request.defines['cswmMask']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmMask":relateObject}" id="form-dl-cswmMask">
					<dt>发送状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmMaskYesNot=="not"?"checked-not":""}${data.cswmMaskYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmMaskYesNot}"/>
						<!-- 数值型的checkbox，暂时以select方式查询，后面改成多值查询 -->				
						<select id="${NAME}" name="${NAME}" >
			 				<option value="">全部</option>
							<option value="1" ${lz:bitin(data.cswmMask,1)?"selected":""}>微信已发</option>
							<option value="2" ${lz:bitin(data.cswmMask,2)?"selected":""}>支付宝已发</option>
						</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发送状态字段的查询代码结束*****************")}
			
			
			${after$cswmMask}
			 	
			 ${before$cswmSuccess}
			 
			 ${lz:set("注释","*****************成功状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmSuccess" : (lz:join(relateObject,"$cswmSuccess")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmSuccess"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmSuccess || #request.defines['cswmSuccess']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmSuccess":relateObject}" id="form-dl-cswmSuccess">
					<dt>成功状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmSuccessYesNot=="not"?"checked-not":""}${data.cswmSuccessYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmSuccessYesNot}"/>
						<!-- 数值型的checkbox，暂时以select方式查询，后面改成多值查询 -->				
						<select id="${NAME}" name="${NAME}" >
			 				<option value="">全部</option>
							<option value="1" ${lz:bitin(data.cswmSuccess,1)?"selected":""}>微信成功</option>
							<option value="2" ${lz:bitin(data.cswmSuccess,2)?"selected":""}>支付宝成功</option>
						</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************成功状态字段的查询代码结束*****************")}
			
			
			${after$cswmSuccess}
			 	
			 ${before$cswmAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmAddTime" : (lz:join(relateObject,"$cswmAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmAddTime || #request.defines['cswmAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmAddTime":relateObject}" id="form-dl-cswmAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmAddTimeYesNot=="not"?"checked-not":""}${data.cswmAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cswmAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cswmAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cswmAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cswmAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cswmAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cswmAddTime}
			 	
			 ${before$cswmStatus}
			 
			 ${lz:set("注释","*****************数据状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cswmStatus" : (lz:join(relateObject,"$cswmStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cswmStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cswmStatus || #request.defines['cswmStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cswmStatus":relateObject}" id="form-dl-cswmStatus">
					<dt>数据状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cswmStatusYesNot=="not"?"checked-not":""}${data.cswmStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cswmStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cswmStatus==0?"selected":""}>默认</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************数据状态字段的查询代码结束*****************")}
			
			
			${after$cswmStatus}