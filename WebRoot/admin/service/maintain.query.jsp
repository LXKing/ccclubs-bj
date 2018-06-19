<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csmId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmId" : (lz:join(relateObject,"$csmId")))}
			 ${lz:set("cname",lz:join(relateObject,".csmId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmId || #request.defines['csmId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmId":relateObject}" id="form-dl-csmId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmIdYesNot=="not"?"checked-not":""}${data.csmIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csmId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csmId}
			 	
			 ${before$csmHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmHost" : (lz:join(relateObject,"$csmHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csmHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmHost || #request.defines['csmHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmHost":relateObject}" id="form-dl-csmHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmHostYesNot=="not"?"checked-not":""}${data.csmHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmHost}">
				 					${get:SrvHost(data.csmHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csmHost}
			 	
			 ${before$csmNumber}
			 
			 ${lz:set("注释","*****************车牌号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmNumber" : (lz:join(relateObject,"$csmNumber")))}
			 ${lz:set("cname",lz:join(relateObject,".csmNumber"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmNumber || #request.defines['csmNumber']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmNumber":relateObject}" id="form-dl-csmNumber">
					<dt>车&nbsp;&nbsp;牌&nbsp;&nbsp;号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmNumberYesNot=="not"?"checked-not":""}${data.csmNumberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmNumberYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csmHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.csmNumber).cscNumber}" value="${data.csmNumber}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车牌号字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmNumber']>0">
				${lz:set("注释","****当车牌号字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmNumber")}
			  	<input type="hidden" name="csmNumber$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmNumber}
			 	
			 ${before$csmModel}
			 
			 ${lz:set("注释","*****************车型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmModel" : (lz:join(relateObject,"$csmModel")))}
			 ${lz:set("cname",lz:join(relateObject,".csmModel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmModel || #request.defines['csmModel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmModel":relateObject}" id="form-dl-csmModel">
					<dt>车　　型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmModelYesNot=="not"?"checked-not":""}${data.csmModelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmModelYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/carmodel_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csmModel}">
				 					${get:CsCarModel(data.csmModel).cscmName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车型字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csmModel']>0">
				${lz:set("注释","****当车型字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csmModel")}
			  	<input type="hidden" name="csmModel$on" id="CsCarModel$on" value="true"/>
			  	<jsp:include page="/admin/object/carmodel.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csmModel}
			 	
			 ${before$csmType}
			 
			 ${lz:set("注释","*****************保期字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmType" : (lz:join(relateObject,"$csmType")))}
			 ${lz:set("cname",lz:join(relateObject,".csmType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmType || #request.defines['csmType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmType":relateObject}" id="form-dl-csmType">
					<dt>保　　期：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmTypeYesNot=="not"?"checked-not":""}${data.csmTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="1" ${data.csmType==1?"selected":""}>首保</option>
							<option value="2" ${data.csmType==2?"selected":""}>二保</option>
							<option value="3" ${data.csmType==3?"selected":""}>三保</option>
							<option value="4" ${data.csmType==4?"selected":""}>四保</option>
							<option value="5" ${data.csmType==5?"selected":""}>五保</option>
							<option value="6" ${data.csmType==6?"selected":""}>六保</option>
							<option value="7" ${data.csmType==7?"selected":""}>七保</option>
							<option value="8" ${data.csmType==8?"selected":""}>其它</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************保期字段的查询代码结束*****************")}
			
			
			${after$csmType}
			 	
			 ${before$csmExpectBy}
			 
			 ${lz:set("注释","*****************保养按字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmExpectBy" : (lz:join(relateObject,"$csmExpectBy")))}
			 ${lz:set("cname",lz:join(relateObject,".csmExpectBy"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmExpectBy || #request.defines['csmExpectBy']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmExpectBy":relateObject}" id="form-dl-csmExpectBy">
					<dt>保&nbsp;&nbsp;养&nbsp;&nbsp;按：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmExpectByYesNot=="not"?"checked-not":""}${data.csmExpectByYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmExpectByYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmExpectBy==0?"selected":""}>里程</option>
							<option value="1" ${data.csmExpectBy==1?"selected":""}>时间</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************保养按字段的查询代码结束*****************")}
			
			
			${after$csmExpectBy}
			 	
			 ${before$csmFeeType}
			 
			 ${lz:set("注释","*****************费别字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmFeeType" : (lz:join(relateObject,"$csmFeeType")))}
			 ${lz:set("cname",lz:join(relateObject,".csmFeeType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmFeeType || #request.defines['csmFeeType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmFeeType":relateObject}" id="form-dl-csmFeeType">
					<dt>费　　别：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmFeeTypeYesNot=="not"?"checked-not":""}${data.csmFeeTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmFeeTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmFeeType==0?"selected":""}>免费</option>
							<option value="1" ${data.csmFeeType==1?"selected":""}>自费</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************费别字段的查询代码结束*****************")}
			
			
			${after$csmFeeType}
			 	
			 ${before$csmStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csmStatus" : (lz:join(relateObject,"$csmStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csmStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csmStatus || #request.defines['csmStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csmStatus":relateObject}" id="form-dl-csmStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csmStatusYesNot=="not"?"checked-not":""}${data.csmStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csmStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csmStatus==0?"selected":""}>保养预备</option>
							<option value="1" ${data.csmStatus==1?"selected":""}>保养报警</option>
							<option value="2" ${data.csmStatus==2?"selected":""}>正在保养</option>
							<option value="3" ${data.csmStatus==3?"selected":""}>保养完成</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csmStatus}