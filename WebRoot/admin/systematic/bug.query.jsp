<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$csbId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csbId" : (lz:join(relateObject,"$csbId")))}
			 ${lz:set("cname",lz:join(relateObject,".csbId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csbId || #request.defines['csbId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csbId":relateObject}" id="form-dl-csbId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csbIdYesNot=="not"?"checked-not":""}${data.csbIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csbIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.csbId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$csbId}
			 	
			 ${before$csbTitle}
			 
			 ${lz:set("注释","*****************BUG标题字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csbTitle" : (lz:join(relateObject,"$csbTitle")))}
			 ${lz:set("cname",lz:join(relateObject,".csbTitle"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csbTitle || #request.defines['csbTitle']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csbTitle":relateObject}" id="form-dl-csbTitle">
					<dt>BUG标题：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csbTitleYesNot=="not"?"checked-not":""}${data.csbTitleYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csbTitleYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csbTitle}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************BUG标题字段的查询代码结束*****************")}
			
			
			${after$csbTitle}
			 	
			 ${before$csbLevel}
			 
			 ${lz:set("注释","*****************BUG级别字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csbLevel" : (lz:join(relateObject,"$csbLevel")))}
			 ${lz:set("cname",lz:join(relateObject,".csbLevel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csbLevel || #request.defines['csbLevel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csbLevel":relateObject}" id="form-dl-csbLevel">
					<dt>BUG级别：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csbLevelYesNot=="not"?"checked-not":""}${data.csbLevelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csbLevelYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csbLevel==0?"selected":""}>未设置</option>
							<option value="1" ${data.csbLevel==1?"selected":""}>一级</option>
							<option value="2" ${data.csbLevel==2?"selected":""}>二级</option>
							<option value="3" ${data.csbLevel==3?"selected":""}>三级</option>
							<option value="4" ${data.csbLevel==4?"selected":""}>四级</option>
							<option value="5" ${data.csbLevel==5?"selected":""}>五级</option>
							<option value="6" ${data.csbLevel==6?"selected":""}>六级</option>
							<option value="7" ${data.csbLevel==7?"selected":""}>七级</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************BUG级别字段的查询代码结束*****************")}
			
			
			${after$csbLevel}
			 	
			 ${before$csbTester}
			 
			 ${lz:set("注释","*****************提交人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csbTester" : (lz:join(relateObject,"$csbTester")))}
			 ${lz:set("cname",lz:join(relateObject,".csbTester"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csbTester || #request.defines['csbTester']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csbTester":relateObject}" id="form-dl-csbTester">
					<dt>提&nbsp;&nbsp;交&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csbTesterYesNot=="not"?"checked-not":""}${data.csbTesterYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csbTesterYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csbTester}">
				 					${get:SrvUser(data.csbTester).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************提交人字段的查询代码结束*****************")}
			
			
			${after$csbTester}
			 	
			 ${before$csbTecher}
			 
			 ${lz:set("注释","*****************处理人字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csbTecher" : (lz:join(relateObject,"$csbTecher")))}
			 ${lz:set("cname",lz:join(relateObject,".csbTecher"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csbTecher || #request.defines['csbTecher']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csbTecher":relateObject}" id="form-dl-csbTecher">
					<dt>处&nbsp;&nbsp;理&nbsp;&nbsp;人：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csbTecherYesNot=="not"?"checked-not":""}${data.csbTecherYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csbTecherYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/permissions/user_tree.do" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.csbTecher}">
				 					${get:SrvUser(data.csbTecher).suRealName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************处理人字段的查询代码结束*****************")}
			
			
			${after$csbTecher}
			 	
			 ${before$csbExpectTime}
			 
			 ${lz:set("注释","*****************期望时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csbExpectTime" : (lz:join(relateObject,"$csbExpectTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csbExpectTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csbExpectTime || #request.defines['csbExpectTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csbExpectTime":relateObject}" id="form-dl-csbExpectTime">
					<dt>期望时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csbExpectTimeYesNot=="not"?"checked-not":""}${data.csbExpectTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csbExpectTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csbExpectTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csbExpectTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csbExpectTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csbExpectTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>期望时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csbExpectTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************期望时间字段的查询代码结束*****************")}
			
			
			${after$csbExpectTime}
			 	
			 ${before$csbUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csbUpdateTime" : (lz:join(relateObject,"$csbUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csbUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csbUpdateTime || #request.defines['csbUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csbUpdateTime":relateObject}" id="form-dl-csbUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csbUpdateTimeYesNot=="not"?"checked-not":""}${data.csbUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csbUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csbUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csbUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csbUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csbUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csbUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$csbUpdateTime}
			 	
			 ${before$csbAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csbAddTime" : (lz:join(relateObject,"$csbAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".csbAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csbAddTime || #request.defines['csbAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csbAddTime":relateObject}" id="form-dl-csbAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csbAddTimeYesNot=="not"?"checked-not":""}${data.csbAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csbAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.csbAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.csbAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.csbAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.csbAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.csbAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$csbAddTime}
			 	
			 ${before$csbStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "csbStatus" : (lz:join(relateObject,"$csbStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".csbStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.csbStatus || #request.defines['csbStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"csbStatus":relateObject}" id="form-dl-csbStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csbStatusYesNot=="not"?"checked-not":""}${data.csbStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csbStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.csbStatus==0?"selected":""}>已提交</option>
							<option value="1" ${data.csbStatus==1?"selected":""}>待处理</option>
							<option value="2" ${data.csbStatus==2?"selected":""}>已处理</option>
							<option value="3" ${data.csbStatus==3?"selected":""}>待商榷</option>
							<option value="4" ${data.csbStatus==4?"selected":""}>已否决</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$csbStatus}