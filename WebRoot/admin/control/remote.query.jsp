<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csrId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrId" : (lz:join(relateObject,"$csrId")))}
			 ${lz:set("cname",lz:join(relateObject,".csrId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrId || #request.defines['csrId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrId":relateObject}" id="form-dl-csrId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrIdYesNot=="not"?"checked-not":""}${data.csrIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csrId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csrId}
			 	
			 ${before$csrHost}
			 
			 ${lz:set("注释","*****************城市字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrHost" : (lz:join(relateObject,"$csrHost")))}
			 ${lz:set("cname",lz:join(relateObject,".csrHost"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrHost || #request.defines['csrHost']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrHost":relateObject}" id="form-dl-csrHost">
					<dt>城　　市：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrHostYesNot=="not"?"checked-not":""}${data.csrHostYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrHostYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/host_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrHost}">
				 					${get:SrvHost(data.csrHost).shName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************城市字段的查询代码结束*****************")}
			
			
			${after$csrHost}
			 	
			 ${before$csrNumber}
			 
			 ${lz:set("注释","*****************车牌号码字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrNumber" : (lz:join(relateObject,"$csrNumber")))}
			 ${lz:set("cname",lz:join(relateObject,".csrNumber"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrNumber || #request.defines['csrNumber']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrNumber":relateObject}" id="form-dl-csrNumber">
					<dt>车牌号码：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrNumberYesNot=="not"?"checked-not":""}${data.csrNumberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrNumberYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csrNumber}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************车牌号码字段的查询代码结束*****************")}
			
			
			${after$csrNumber}
			 	
			 ${before$csrCar}
			 
			 ${lz:set("注释","*****************控制车辆字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrCar" : (lz:join(relateObject,"$csrCar")))}
			 ${lz:set("cname",lz:join(relateObject,".csrCar"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrCar || #request.defines['csrCar']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrCar":relateObject}" id="form-dl-csrCar">
					<dt>控制车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrCarYesNot=="not"?"checked-not":""}${data.csrCarYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrCarYesNot}"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csrHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsCar(data.csrCar).cscNumber}" value="${data.csrCar}"/>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************控制车辆字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['csrCar']>0">
				${lz:set("注释","****当控制车辆字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","csrCar")}
			  	<input type="hidden" name="csrCar$on" id="CsCar$on" value="true"/>
			  	<jsp:include page="/admin/object/car.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$csrCar}
			 	
			 ${before$csrWay}
			 
			 ${lz:set("注释","*****************数据方式字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrWay" : (lz:join(relateObject,"$csrWay")))}
			 ${lz:set("cname",lz:join(relateObject,".csrWay"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrWay || #request.defines['csrWay']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrWay":relateObject}" id="form-dl-csrWay">
					<dt>数据方式：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrWayYesNot=="not"?"checked-not":""}${data.csrWayYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrWayYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csrWay==0?"selected":""}>网关</option>
							<option value="1" ${data.csrWay==1?"selected":""}>短信</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************数据方式字段的查询代码结束*****************")}
			
			
			${after$csrWay}
			 	
			 ${before$csrType}
			 
			 ${lz:set("注释","*****************控制类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrType" : (lz:join(relateObject,"$csrType")))}
			 ${lz:set("cname",lz:join(relateObject,".csrType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrType || #request.defines['csrType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrType":relateObject}" id="form-dl-csrType">
					<dt>控制类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrTypeYesNot=="not"?"checked-not":""}${data.csrTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csrType==0?"selected":""}>远程开门</option>
							<option value="1" ${data.csrType==1?"selected":""}>远程关门</option>
							<option value="2" ${data.csrType==2?"selected":""}>允许启动</option>
							<option value="3" ${data.csrType==3?"selected":""}>断油电</option>
							<option value="4" ${data.csrType==4?"selected":""}>初始化</option>
							<option value="5" ${data.csrType==5?"selected":""}>同步时钟</option>
							<option value="6" ${data.csrType==6?"selected":""}>远程取车</option>
							<option value="7" ${data.csrType==7?"selected":""}>远程还车</option>
							<option value="8" ${data.csrType==8?"selected":""}>远程升级</option>
							<option value="9" ${data.csrType==9?"selected":""}>远程重启</option>
							<option value="10" ${data.csrType==10?"selected":""}>GPS远程开</option>
							<option value="11" ${data.csrType==11?"selected":""}>GPS远程关</option>
							<option value="12" ${data.csrType==12?"selected":""}>读取RFID序列号</option>
							<option value="13" ${data.csrType==13?"selected":""}>设置GPS坐标</option>
							<option value="14" ${data.csrType==14?"selected":""}>查询手机号</option>
							<option value="15" ${data.csrType==15?"selected":""}>刷卡器开</option>
							<option value="16" ${data.csrType==16?"selected":""}>刷卡器关</option>
							<option value="17" ${data.csrType==17?"selected":""}>远程鸣笛</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************控制类型字段的查询代码结束*****************")}
			
			
			${after$csrType}
			 	
			 ${before$csrEditor}
			 
			 ${lz:set("注释","*****************操作人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrEditor" : (lz:join(relateObject,"$csrEditor")))}
			 ${lz:set("cname",lz:join(relateObject,".csrEditor"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrEditor || #request.defines['csrEditor']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrEditor":relateObject}" id="form-dl-csrEditor">
					<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrEditorYesNot=="not"?"checked-not":""}${data.csrEditorYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrEditorYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csrEditor}">
				 					${get:SrvUser(data.csrEditor).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作人字段的查询代码结束*****************")}
			
			
			${after$csrEditor}
			 	
			 ${before$csrUpdateTime}
			 
			 ${lz:set("注释","*****************操作成功时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrUpdateTime" : (lz:join(relateObject,"$csrUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csrUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrUpdateTime || #request.defines['csrUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrUpdateTime":relateObject}" id="form-dl-csrUpdateTime">
					<dt>操作成功时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrUpdateTimeYesNot=="not"?"checked-not":""}${data.csrUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csrUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csrUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csrUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csrUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>操作成功时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csrUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作成功时间字段的查询代码结束*****************")}
			
			
			${after$csrUpdateTime}
			 	
			 ${before$csrAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrAddTime" : (lz:join(relateObject,"$csrAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csrAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrAddTime || #request.defines['csrAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrAddTime":relateObject}" id="form-dl-csrAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrAddTimeYesNot=="not"?"checked-not":""}${data.csrAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csrAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csrAddTime}
			 	
			 ${before$csrState}
			 
			 ${lz:set("注释","*****************数据发送状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrState" : (lz:join(relateObject,"$csrState")))}
			 ${lz:set("cname",lz:join(relateObject,".csrState"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrState || #request.defines['csrState']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrState":relateObject}" id="form-dl-csrState">
					<dt>数据发送状态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrStateYesNot=="not"?"checked-not":""}${data.csrStateYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrStateYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csrState==0?"selected":""}>未发送</option>
							<option value="1" ${data.csrState==1?"selected":""}>已发送</option>
							<option value="2" ${data.csrState==2?"selected":""}>组装中</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************数据发送状态字段的查询代码结束*****************")}
			
			
			${after$csrState}
			 	
			 ${before$csrStatus}
			 
			 ${lz:set("注释","*****************操作结果字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csrStatus" : (lz:join(relateObject,"$csrStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csrStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csrStatus || #request.defines['csrStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csrStatus":relateObject}" id="form-dl-csrStatus">
					<dt>操作结果：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csrStatusYesNot=="not"?"checked-not":""}${data.csrStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csrStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csrStatus==0?"selected":""}>未知</option>
							<option value="1" ${data.csrStatus==1?"selected":""}>操作成功</option>
							<option value="2" ${data.csrStatus==2?"selected":""}>操作失败</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************操作结果字段的查询代码结束*****************")}
			
			
			${after$csrStatus}