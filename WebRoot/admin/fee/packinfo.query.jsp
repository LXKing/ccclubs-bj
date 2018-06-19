<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cspiId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspiId" : (lz:join(relateObject,"$cspiId")))}
			 ${lz:set("cname",lz:join(relateObject,".cspiId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspiId || #request.defines['cspiId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspiId":relateObject}" id="form-dl-cspiId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspiIdYesNot=="not"?"checked-not":""}${data.cspiIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspiIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cspiId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cspiId}
			 	
			 ${before$cspiPack}
			 
			 ${lz:set("注释","*****************所属套餐字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspiPack" : (lz:join(relateObject,"$cspiPack")))}
			 ${lz:set("cname",lz:join(relateObject,".cspiPack"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspiPack || #request.defines['cspiPack']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspiPack":relateObject}" id="form-dl-cspiPack">
					<dt>所属套餐：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspiPackYesNot=="not"?"checked-not":""}${data.cspiPackYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspiPackYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/fee/pack_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cspiPack}">
				 					${get:CsPack(data.cspiPack).cspName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/pack_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属套餐字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cspiPack']>0">
				${lz:set("注释","****当所属套餐字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cspiPack")}
			  	<input type="hidden" name="cspiPack$on" id="CsPack$on" value="true"/>
			  	<jsp:include page="/admin/fee/pack.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cspiPack}
			 	
			 ${before$cspiGoods}
			 
			 ${lz:set("注释","*****************所属商品字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspiGoods" : (lz:join(relateObject,"$cspiGoods")))}
			 ${lz:set("cname",lz:join(relateObject,".cspiGoods"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspiGoods || #request.defines['cspiGoods']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspiGoods":relateObject}" id="form-dl-cspiGoods">
					<dt>所属商品：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspiGoodsYesNot=="not"?"checked-not":""}${data.cspiGoodsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspiGoodsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/fee/goods_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cspiGoods}">
				 					${get:CsGoods(data.cspiGoods).csgName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/fee/goods_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属商品字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cspiGoods']>0">
				${lz:set("注释","****当所属商品字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cspiGoods")}
			  	<input type="hidden" name="cspiGoods$on" id="CsGoods$on" value="true"/>
			  	<jsp:include page="/admin/fee/goods.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cspiGoods}
			 	
			 ${before$cspiOutlets}
			 
			 ${lz:set("注释","*****************所属网点字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspiOutlets" : (lz:join(relateObject,"$cspiOutlets")))}
			 ${lz:set("cname",lz:join(relateObject,".cspiOutlets"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspiOutlets || #request.defines['cspiOutlets']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspiOutlets":relateObject}" id="form-dl-cspiOutlets">
					<dt>所属网点：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspiOutletsYesNot=="not"?"checked-not":""}${data.cspiOutletsYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspiOutletsYesNot}"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_query.do?size=-1" id="${NAME}" name="${NAME}">
				 				<option selected value="${data.cspiOutlets}">
				 					${get:CsOutlets(data.cspiOutlets).csoName}
				 				</option>
				 		</select>
				 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属网点字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cspiOutlets']>0">
				${lz:set("注释","****当所属网点字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cspiOutlets")}
			  	<input type="hidden" name="cspiOutlets$on" id="CsOutlets$on" value="true"/>
			  	<jsp:include page="/admin/object/outlets.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cspiOutlets}
			 	
			 ${before$cspiModel}
			 
			 ${lz:set("注释","*****************所属车型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspiModel" : (lz:join(relateObject,"$cspiModel")))}
			 ${lz:set("cname",lz:join(relateObject,".cspiModel"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspiModel || #request.defines['cspiModel']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspiModel":relateObject}" id="form-dl-cspiModel">
					<dt>所属车型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspiModelYesNot=="not"?"checked-not":""}${data.cspiModelYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspiModelYesNot}"/>
		 			<select id="${NAME}" name="${NAME}" >
		 				<option value="">全部</option>
	 					${lz:set("items", lz:query("ccclubs_system","select * from cs_car_model where 1=1 ") )}
						<s:iterator value="#request.items" id="item" status="i">
						<option value="#${item.cscm_id}#" ${lz:idin(data.cspiModel,item.cscm_id)?"selected":""}>${item.cscm_name}</option>
						</s:iterator>
						${lz:set("items",null)}
					</select>
					<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?key='+$('#${NAME}').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属车型字段的查询代码结束*****************")}
			
			
			${after$cspiModel}
			 	
			 ${before$cspiType}
			 
			 ${lz:set("注释","*****************类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspiType" : (lz:join(relateObject,"$cspiType")))}
			 ${lz:set("cname",lz:join(relateObject,".cspiType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspiType || #request.defines['cspiType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspiType":relateObject}" id="form-dl-cspiType">
					<dt>类　　型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspiTypeYesNot=="not"?"checked-not":""}${data.cspiTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspiTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cspiType==0?"selected":""}>数量</option>
							<option value="1" ${data.cspiType==1?"selected":""}>价格</option>
							<option value="2" ${data.cspiType==2?"selected":""}>折扣</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************类型字段的查询代码结束*****************")}
			
			
			${after$cspiType}
			 	
			 ${before$cspiScript}
			 
			 ${lz:set("注释","*****************函数脚本字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspiScript" : (lz:join(relateObject,"$cspiScript")))}
			 ${lz:set("cname",lz:join(relateObject,".cspiScript"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspiScript || #request.defines['cspiScript']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspiScript":relateObject}" id="form-dl-cspiScript">
					<dt>函数脚本：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspiScriptYesNot=="not"?"checked-not":""}${data.cspiScriptYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspiScriptYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="128" name="${NAME}" id="${NAME}"  value="${data.cspiScript}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************函数脚本字段的查询代码结束*****************")}
			
			
			${after$cspiScript}
			 	
			 ${before$cspiAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cspiAddTime" : (lz:join(relateObject,"$cspiAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cspiAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cspiAddTime || #request.defines['cspiAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cspiAddTime":relateObject}" id="form-dl-cspiAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cspiAddTimeYesNot=="not"?"checked-not":""}${data.cspiAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cspiAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cspiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cspiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cspiAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cspiAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cspiAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cspiAddTime}