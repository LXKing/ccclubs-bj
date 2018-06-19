<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csgId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgId" : (lz:join(relateObject,"$csgId")))}
			 ${lz:set("cname",lz:join(relateObject,".csgId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgId || #request.defines['csgId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgId":relateObject}" id="form-dl-csgId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgIdYesNot=="not"?"checked-not":""}${data.csgIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csgId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csgId}
			 	
			 ${before$csgHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgHost" : (lz:join(relateObject,"$csgHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csgHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgHost || #request.defines['csgHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgHost":relateObject}" id="form-dl-csgHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgHostYesNot=="not"?"checked-not":""}${data.csgHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csgHost}">
				 					${get:SrvHost(data.csgHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csgHost}
			 	
			 ${before$csgMember}
			 
			 ${lz:set("注释","*****************所属会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgMember" : (lz:join(relateObject,"$csgMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csgMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgMember || #request.defines['csgMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgMember":relateObject}" id="form-dl-csgMember">
					<dt>所属会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgMemberYesNot=="not"?"checked-not":""}${data.csgMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csgHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csgMember).csmName}" value="${data.csgMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csgMember']>0">
				${lz:set("注释","****当所属会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csgMember")}
			  	<input type="hidden" name="csgMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csgMember}
			 	
			 ${before$csgSysPack}
			 
			 ${lz:set("注释","*****************系统套餐字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgSysPack" : (lz:join(relateObject,"$csgSysPack")))}
			 ${lz:set("cname",lz:join(relateObject,".csgSysPack"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgSysPack || #request.defines['csgSysPack']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgSysPack":relateObject}" id="form-dl-csgSysPack">
					<dt>系统套餐：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgSysPackYesNot=="not"?"checked-not":""}${data.csgSysPackYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgSysPackYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/fee/pack_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csgSysPack}">
				 					${get:CsPack(data.csgSysPack).cspName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/pack_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************系统套餐字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csgSysPack']>0">
				${lz:set("注释","****当系统套餐字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csgSysPack")}
			  	<input type="hidden" name="csgSysPack$on" id="CsPack$on" value="true"/>
			  	<jsp:include page="/admin/fee/pack.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csgSysPack}
			 	
			 ${before$csgUserPack}
			 
			 ${lz:set("注释","*****************会员套餐字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgUserPack" : (lz:join(relateObject,"$csgUserPack")))}
			 ${lz:set("cname",lz:join(relateObject,".csgUserPack"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgUserPack || #request.defines['csgUserPack']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgUserPack":relateObject}" id="form-dl-csgUserPack">
					<dt>会员套餐：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgUserPackYesNot=="not"?"checked-not":""}${data.csgUserPackYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgUserPackYesNot}"/>
			 			<input title="请输入用户套餐编号进行查询" class="combox" action="${basePath}${proname}/user/pack_query.do?value={param}&csupHost={csgHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsUserPack(data.csgUserPack).csupId}" value="${data.csgUserPack}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/pack_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************会员套餐字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csgUserPack']>0">
				${lz:set("注释","****当会员套餐字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csgUserPack")}
			  	<input type="hidden" name="csgUserPack$on" id="CsUserPack$on" value="true"/>
			  	<jsp:include page="/admin/user/pack.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csgUserPack}
			 	
			 ${before$csgGoods}
			 
			 ${lz:set("注释","*****************所属商品字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgGoods" : (lz:join(relateObject,"$csgGoods")))}
			 ${lz:set("cname",lz:join(relateObject,".csgGoods"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgGoods || #request.defines['csgGoods']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgGoods":relateObject}" id="form-dl-csgGoods">
					<dt>所属商品：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgGoodsYesNot=="not"?"checked-not":""}${data.csgGoodsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgGoodsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/fee/goods_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csgGoods}">
				 					${get:CsGoods(data.csgGoods).csgName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/goods_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属商品字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csgGoods']>0">
				${lz:set("注释","****当所属商品字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csgGoods")}
			  	<input type="hidden" name="csgGoods$on" id="CsGoods$on" value="true"/>
			  	<jsp:include page="/admin/fee/goods.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csgGoods}
			 	
			 ${before$csgOutlets}
			 
			 ${lz:set("注释","*****************所属网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgOutlets" : (lz:join(relateObject,"$csgOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".csgOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgOutlets || #request.defines['csgOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgOutlets":relateObject}" id="form-dl-csgOutlets">
					<dt>所属网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgOutletsYesNot=="not"?"checked-not":""}${data.csgOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgOutletsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_query.do?size=-1&csoHost={csgHost}" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csgOutlets}">
				 					${get:CsOutlets(data.csgOutlets).csoName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csgOutlets']>0">
				${lz:set("注释","****当所属网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csgOutlets")}
			  	<input type="hidden" name="csgOutlets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csgOutlets}
			 	
			 ${before$csgModel}
			 
			 ${lz:set("注释","*****************所属车型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgModel" : (lz:join(relateObject,"$csgModel")))}
			 ${lz:set("cname",lz:join(relateObject,".csgModel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgModel || #request.defines['csgModel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgModel":relateObject}" id="form-dl-csgModel">
					<dt>所属车型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgModelYesNot=="not"?"checked-not":""}${data.csgModelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgModelYesNot}"/>
		 			<select id="${NAME}" name="${NAME}" >
		 				<option value="">全部</option>
	 					${lz:set("items", lz:query("ccclubs_system","select * from cs_car_model where 1=1 ") )}
						<s:iterator value="#request.items" id="item" status="i">
						<option value="#${item.cscm_id}#" ${lz:idin(data.csgModel,item.cscm_id)?"selected":""}>${item.cscm_name}</option>
						</s:iterator>
						${lz:set("items",null)}
					</select>
					<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?key='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属车型字段的查询代码结束*****************")}
			
			
			${after$csgModel}
			 	
			 ${before$csgType}
			 
			 ${lz:set("注释","*****************优惠类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgType" : (lz:join(relateObject,"$csgType")))}
			 ${lz:set("cname",lz:join(relateObject,".csgType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgType || #request.defines['csgType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgType":relateObject}" id="form-dl-csgType">
					<dt>优惠类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgTypeYesNot=="not"?"checked-not":""}${data.csgTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csgType==0?"selected":""}>数量</option>
							<option value="1" ${data.csgType==1?"selected":""}>价格</option>
							<option value="2" ${data.csgType==2?"selected":""}>折扣</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************优惠类型字段的查询代码结束*****************")}
			
			
			${after$csgType}
			 	
			 ${before$csgScript}
			 
			 ${lz:set("注释","*****************函数脚本字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgScript" : (lz:join(relateObject,"$csgScript")))}
			 ${lz:set("cname",lz:join(relateObject,".csgScript"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgScript || #request.defines['csgScript']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgScript":relateObject}" id="form-dl-csgScript">
					<dt>函数脚本：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgScriptYesNot=="not"?"checked-not":""}${data.csgScriptYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgScriptYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csgScript}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************函数脚本字段的查询代码结束*****************")}
			
			
			${after$csgScript}
			 	
			 ${before$csgUntilTime}
			 
			 ${lz:set("注释","*****************有效期至字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgUntilTime" : (lz:join(relateObject,"$csgUntilTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csgUntilTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgUntilTime || #request.defines['csgUntilTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgUntilTime":relateObject}" id="form-dl-csgUntilTime">
					<dt>有效期至：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgUntilTimeYesNot=="not"?"checked-not":""}${data.csgUntilTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgUntilTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csgUntilTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csgUntilTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csgUntilTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csgUntilTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>有效期至：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csgUntilTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************有效期至字段的查询代码结束*****************")}
			
			
			${after$csgUntilTime}
			 	
			 ${before$csgUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgUpdateTime" : (lz:join(relateObject,"$csgUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csgUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgUpdateTime || #request.defines['csgUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgUpdateTime":relateObject}" id="form-dl-csgUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgUpdateTimeYesNot=="not"?"checked-not":""}${data.csgUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csgUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csgUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csgUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csgUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csgUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csgUpdateTime}
			 	
			 ${before$csgAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgAddTime" : (lz:join(relateObject,"$csgAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csgAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgAddTime || #request.defines['csgAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgAddTime":relateObject}" id="form-dl-csgAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgAddTimeYesNot=="not"?"checked-not":""}${data.csgAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csgAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csgAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csgAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csgAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csgAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csgAddTime}
			 	
			 ${before$csgStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csgStatus" : (lz:join(relateObject,"$csgStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csgStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csgStatus || #request.defines['csgStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csgStatus":relateObject}" id="form-dl-csgStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csgStatusYesNot=="not"?"checked-not":""}${data.csgStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csgStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csgStatus==1?"selected":""}>有效</option>
							<option value="0" ${data.csgStatus==0?"selected":""}>无效</option>
							<option value="2" ${data.csgStatus==2?"selected":""}>过期</option>
							<option value="3" ${data.csgStatus==3?"selected":""}>用完</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csgStatus}