<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csvId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvId" : (lz:join(relateObject,"$csvId")))}
			 ${lz:set("cname",lz:join(relateObject,".csvId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvId || #request.defines['csvId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvId":relateObject}" id="form-dl-csvId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvIdYesNot=="not"?"checked-not":""}${data.csvIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csvId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csvId}
			 	
			 ${before$csvHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvHost" : (lz:join(relateObject,"$csvHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csvHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvHost || #request.defines['csvHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvHost":relateObject}" id="form-dl-csvHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvHostYesNot=="not"?"checked-not":""}${data.csvHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csvHost}">
				 					${get:SrvHost(data.csvHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csvHost}
			 	
			 ${before$csvOrder}
			 
			 ${lz:set("注释","*****************违章订单字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvOrder" : (lz:join(relateObject,"$csvOrder")))}
			 ${lz:set("cname",lz:join(relateObject,".csvOrder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvOrder || #request.defines['csvOrder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvOrder":relateObject}" id="form-dl-csvOrder">
					<dt>违章订单：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvOrderYesNot=="not"?"checked-not":""}${data.csvOrderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvOrderYesNot}"/>
			 			<input title="请输入系统订单订单编号进行查询" class="combox" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csvHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsOrder(data.csvOrder).csoId}" value="${data.csvOrder}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************违章订单字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csvOrder']>0">
				${lz:set("注释","****当违章订单字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csvOrder")}
			  	<input type="hidden" name="csvOrder$on" id="CsOrder$on" value="true"/>
			  	<jsp:include page="/admin/service/order.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csvOrder}
			 	
			 ${before$csvMember}
			 
			 ${lz:set("注释","*****************违章会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvMember" : (lz:join(relateObject,"$csvMember")))}
			 ${lz:set("cname",lz:join(relateObject,".csvMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvMember || #request.defines['csvMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvMember":relateObject}" id="form-dl-csvMember">
					<dt>违章会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvMemberYesNot=="not"?"checked-not":""}${data.csvMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvMemberYesNot}"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csvHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csvMember).csmName}" value="${data.csvMember}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************违章会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csvMember']>0">
				${lz:set("注释","****当违章会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csvMember")}
			  	<input type="hidden" name="csvMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csvMember}
			 	
			 ${before$csvCar}
			 
			 ${lz:set("注释","*****************违章车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvCar" : (lz:join(relateObject,"$csvCar")))}
			 ${lz:set("cname",lz:join(relateObject,".csvCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvCar || #request.defines['csvCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvCar":relateObject}" id="form-dl-csvCar">
					<dt>违章车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvCarYesNot=="not"?"checked-not":""}${data.csvCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvCarYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csvHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.csvCar).cscNumber}" value="${data.csvCar}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************违章车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csvCar']>0">
				${lz:set("注释","****当违章车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csvCar")}
			  	<input type="hidden" name="csvCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csvCar}
			 	
			 ${before$csvHappenTime}
			 
			 ${lz:set("注释","*****************发生时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvHappenTime" : (lz:join(relateObject,"$csvHappenTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csvHappenTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvHappenTime || #request.defines['csvHappenTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvHappenTime":relateObject}" id="form-dl-csvHappenTime">
					<dt>发生时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvHappenTimeYesNot=="not"?"checked-not":""}${data.csvHappenTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvHappenTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csvHappenTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csvHappenTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csvHappenTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csvHappenTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>发生时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csvHappenTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************发生时间字段的查询代码结束*****************")}
			
			
			${after$csvHappenTime}
			 	
			 ${before$csvNoticeTime}
			 
			 ${lz:set("注释","*****************通知时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvNoticeTime" : (lz:join(relateObject,"$csvNoticeTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csvNoticeTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvNoticeTime || #request.defines['csvNoticeTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvNoticeTime":relateObject}" id="form-dl-csvNoticeTime">
					<dt>通知时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvNoticeTimeYesNot=="not"?"checked-not":""}${data.csvNoticeTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvNoticeTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csvNoticeTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csvNoticeTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csvNoticeTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csvNoticeTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>通知时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csvNoticeTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************通知时间字段的查询代码结束*****************")}
			
			
			${after$csvNoticeTime}
			 	
			 ${before$csvCutoffTime}
			 
			 ${lz:set("注释","*****************截止时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvCutoffTime" : (lz:join(relateObject,"$csvCutoffTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csvCutoffTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvCutoffTime || #request.defines['csvCutoffTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvCutoffTime":relateObject}" id="form-dl-csvCutoffTime">
					<dt>截止时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvCutoffTimeYesNot=="not"?"checked-not":""}${data.csvCutoffTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvCutoffTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csvCutoffTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csvCutoffTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csvCutoffTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csvCutoffTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>截止时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csvCutoffTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************截止时间字段的查询代码结束*****************")}
			
			
			${after$csvCutoffTime}
			 	
			 ${before$csvType}
			 
			 ${lz:set("注释","*****************处理方式字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvType" : (lz:join(relateObject,"$csvType")))}
			 ${lz:set("cname",lz:join(relateObject,".csvType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvType || #request.defines['csvType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvType":relateObject}" id="form-dl-csvType">
					<dt>处理方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvTypeYesNot=="not"?"checked-not":""}${data.csvTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csvType==0?"selected":""}>自己处理</option>
							<option value="1" ${data.csvType==1?"selected":""}>协商委托</option>
							<option value="2" ${data.csvType==2?"selected":""}>强制委托</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************处理方式字段的查询代码结束*****************")}
			
			
			${after$csvType}
			 	
			 ${before$csvBranch}
			 
			 ${lz:set("注释","*****************处理部门字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvBranch" : (lz:join(relateObject,"$csvBranch")))}
			 ${lz:set("cname",lz:join(relateObject,".csvBranch"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvBranch || #request.defines['csvBranch']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvBranch":relateObject}" id="form-dl-csvBranch">
					<dt>处理部门：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvBranchYesNot=="not"?"checked-not":""}${data.csvBranchYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvBranchYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csvBranch==0?"selected":""}>交警</option>
							<option value="1" ${data.csvBranch==1?"selected":""}>城管</option>
							<option value="2" ${data.csvBranch==2?"selected":""}>铁路</option>
							<option value="3" ${data.csvBranch==3?"selected":""}>高速</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************处理部门字段的查询代码结束*****************")}
			
			
			${after$csvBranch}
			 	
			 ${before$csvPostType}
			 
			 ${lz:set("注释","*****************寄送方式字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvPostType" : (lz:join(relateObject,"$csvPostType")))}
			 ${lz:set("cname",lz:join(relateObject,".csvPostType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvPostType || #request.defines['csvPostType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvPostType":relateObject}" id="form-dl-csvPostType">
					<dt>寄送方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvPostTypeYesNot=="not"?"checked-not":""}${data.csvPostTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvPostTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csvPostType==0?"selected":""}>邮寄</option>
							<option value="1" ${data.csvPostType==1?"selected":""}>人工</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************寄送方式字段的查询代码结束*****************")}
			
			
			${after$csvPostType}
			 	
			 ${before$csvAddress}
			 
			 ${lz:set("注释","*****************送寄地址字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvAddress" : (lz:join(relateObject,"$csvAddress")))}
			 ${lz:set("cname",lz:join(relateObject,".csvAddress"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvAddress || #request.defines['csvAddress']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvAddress":relateObject}" id="form-dl-csvAddress">
					<dt>送寄地址：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvAddressYesNot=="not"?"checked-not":""}${data.csvAddressYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvAddressYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.csvAddress}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************送寄地址字段的查询代码结束*****************")}
			
			
			${after$csvAddress}
			 	
			 ${before$csvAccepter}
			 
			 ${lz:set("注释","*****************受理人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvAccepter" : (lz:join(relateObject,"$csvAccepter")))}
			 ${lz:set("cname",lz:join(relateObject,".csvAccepter"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvAccepter || #request.defines['csvAccepter']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvAccepter":relateObject}" id="form-dl-csvAccepter">
					<dt>受理人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvAccepterYesNot=="not"?"checked-not":""}${data.csvAccepterYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvAccepterYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csvAccepter}">
				 					${get:SrvUser(data.csvAccepter).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************受理人员字段的查询代码结束*****************")}
			
			
			${after$csvAccepter}
			 	
			 ${before$csvAdder}
			 
			 ${lz:set("注释","*****************添加人员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvAdder" : (lz:join(relateObject,"$csvAdder")))}
			 ${lz:set("cname",lz:join(relateObject,".csvAdder"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvAdder || #request.defines['csvAdder']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvAdder":relateObject}" id="form-dl-csvAdder">
					<dt>添加人员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvAdderYesNot=="not"?"checked-not":""}${data.csvAdderYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvAdderYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csvAdder}">
				 					${get:SrvUser(data.csvAdder).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加人员字段的查询代码结束*****************")}
			
			
			${after$csvAdder}
			 	
			 ${before$csvUpdateTime}
			 
			 ${lz:set("注释","*****************处理时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvUpdateTime" : (lz:join(relateObject,"$csvUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csvUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvUpdateTime || #request.defines['csvUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvUpdateTime":relateObject}" id="form-dl-csvUpdateTime">
					<dt>处理时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvUpdateTimeYesNot=="not"?"checked-not":""}${data.csvUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csvUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csvUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csvUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csvUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>处理时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csvUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************处理时间字段的查询代码结束*****************")}
			
			
			${after$csvUpdateTime}
			 	
			 ${before$csvAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvAddTime" : (lz:join(relateObject,"$csvAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csvAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvAddTime || #request.defines['csvAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvAddTime":relateObject}" id="form-dl-csvAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvAddTimeYesNot=="not"?"checked-not":""}${data.csvAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csvAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csvAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csvAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csvAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csvAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csvAddTime}
			 	
			 ${before$csvDealFlag}
			 
			 ${lz:set("注释","*****************受理标记字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvDealFlag" : (lz:join(relateObject,"$csvDealFlag")))}
			 ${lz:set("cname",lz:join(relateObject,".csvDealFlag"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvDealFlag || #request.defines['csvDealFlag']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvDealFlag":relateObject}" id="form-dl-csvDealFlag">
					<dt>受理标记：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvDealFlagYesNot=="not"?"checked-not":""}${data.csvDealFlagYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvDealFlagYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csvDealFlag==0?"selected":""}>正常处理</option>
							<option value="1" ${data.csvDealFlag==1?"selected":""}>会员不配合</option>
							<option value="2" ${data.csvDealFlag==2?"selected":""}>交警不配合</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************受理标记字段的查询代码结束*****************")}
			
			
			${after$csvDealFlag}
			 	
			 ${before$csvStatus}
			 
			 ${lz:set("注释","*****************业务状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csvStatus" : (lz:join(relateObject,"$csvStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csvStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csvStatus || #request.defines['csvStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csvStatus":relateObject}" id="form-dl-csvStatus">
					<dt>业务状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csvStatusYesNot=="not"?"checked-not":""}${data.csvStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csvStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csvStatus==0?"selected":""}>未交款，未处理</option>
							<option value="1" ${data.csvStatus==1?"selected":""}>未交款，已处理</option>
							<option value="2" ${data.csvStatus==2?"selected":""}>已交款，已处理</option>
							<option value="3" ${data.csvStatus==3?"selected":""}>等待查询</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************业务状态字段的查询代码结束*****************")}
			
			
			${after$csvStatus}