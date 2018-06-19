<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 	
			 ${before$cslrId}
			 
			 ${lz:set("注释","*****************编号字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslrId" : (lz:join(relateObject,"$cslrId")))}
			 ${lz:set("cname",lz:join(relateObject,".cslrId"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslrId || #request.defines['cslrId']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslrId":relateObject}" id="form-dl-cslrId">
					<dt>编　　号：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslrIdYesNot=="not"?"checked-not":""}${data.cslrIdYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslrIdYesNot}"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="${NAME}" id="${NAME}"  value="${data.cslrId}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************编号字段的查询代码结束*****************")}
			
			
			${after$cslrId}
			 	
			 ${before$cslrName}
			 
			 ${lz:set("注释","*****************中奖标题字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslrName" : (lz:join(relateObject,"$cslrName")))}
			 ${lz:set("cname",lz:join(relateObject,".cslrName"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslrName || #request.defines['cslrName']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslrName":relateObject}" id="form-dl-cslrName">
					<dt>中奖标题：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslrNameYesNot=="not"?"checked-not":""}${data.cslrNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslrNameYesNot}"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.cslrName}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************中奖标题字段的查询代码结束*****************")}
			
			
			${after$cslrName}
			 	
			 ${before$cslrMember}
			 
			 ${lz:set("注释","*****************中奖会员字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslrMember" : (lz:join(relateObject,"$cslrMember")))}
			 ${lz:set("cname",lz:join(relateObject,".cslrMember"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslrMember || #request.defines['cslrMember']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslrMember":relateObject}" id="form-dl-cslrMember">
					<dt>中奖会员：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslrMemberYesNot=="not"?"checked-not":""}${data.cslrMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslrMemberYesNot}"/>
			 			<input class="input" size="16" type="text" id="${NAME}" name="${NAME}" value="${data.cslrMember}" />
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************中奖会员字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cslrMember']>0">
				${lz:set("注释","****当中奖会员字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cslrMember")}
			  	<input type="hidden" name="cslrMember$on" id="CsMember$on" value="true"/>
			  	<jsp:include page="/admin/user/member.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cslrMember}
			 	
			 ${before$cslrLucky}
			 
			 ${lz:set("注释","*****************所属活动字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslrLucky" : (lz:join(relateObject,"$cslrLucky")))}
			 ${lz:set("cname",lz:join(relateObject,".cslrLucky"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslrLucky || #request.defines['cslrLucky']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslrLucky":relateObject}" id="form-dl-cslrLucky">
					<dt>所属活动：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslrLuckyYesNot=="not"?"checked-not":""}${data.cslrLuckyYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslrLuckyYesNot}"/>
			 			<input class="input" size="16" type="text" id="${NAME}" name="${NAME}" value="${data.cslrLucky}" />
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属活动字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cslrLucky']>0">
				${lz:set("注释","****当所属活动字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cslrLucky")}
			  	<input type="hidden" name="cslrLucky$on" id="CsLucky$on" value="true"/>
			  	<jsp:include page="/admin/operate/lucky/lucky.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cslrLucky}
			 	
			 ${before$cslrLuckyItem}
			 
			 ${lz:set("注释","*****************所属奖项字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslrLuckyItem" : (lz:join(relateObject,"$cslrLuckyItem")))}
			 ${lz:set("cname",lz:join(relateObject,".cslrLuckyItem"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslrLuckyItem || #request.defines['cslrLuckyItem']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslrLuckyItem":relateObject}" id="form-dl-cslrLuckyItem">
					<dt>所属奖项：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslrLuckyItemYesNot=="not"?"checked-not":""}${data.cslrLuckyItemYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslrLuckyItemYesNot}"/>
			 			<input class="input" size="16" type="text" id="${NAME}" name="${NAME}" value="${data.cslrLuckyItem}" />
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************所属奖项字段的查询代码结束*****************")}
			<s:if test="#request.relateObject==null && #request.defines['cslrLuckyItem']>0">
				${lz:set("注释","****当所属奖项字段有关联对象，并且用户勾选了显示其字段的话，把相关的查询字段也显示出来****")}
			  	${lz:set("relateObject","cslrLuckyItem")}
			  	<input type="hidden" name="cslrLuckyItem$on" id="CsLuckyItem$on" value="true"/>
			  	<jsp:include page="/admin/operate/lucky/luckyitem.query.jsp"/>
			  	${lz:set("relateObject",null)}
			</s:if>
			
			
			${after$cslrLuckyItem}
			 	
			 ${before$cslrItemType}
			 
			 ${lz:set("注释","*****************奖项类型字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslrItemType" : (lz:join(relateObject,"$cslrItemType")))}
			 ${lz:set("cname",lz:join(relateObject,".cslrItemType"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslrItemType || #request.defines['cslrItemType']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslrItemType":relateObject}" id="form-dl-cslrItemType">
					<dt>奖项类型：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslrItemTypeYesNot=="not"?"checked-not":""}${data.cslrItemTypeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslrItemTypeYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cslrItemType==0?"selected":""}>谢谢参与</option>
							<option value="1" ${data.cslrItemType==1?"selected":""}>积分</option>
							<option value="2" ${data.cslrItemType==2?"selected":""}>现金券</option>
							<option value="3" ${data.cslrItemType==3?"selected":""}>免费小时</option>
							<option value="4" ${data.cslrItemType==4?"selected":""}>实物</option>
							<option value="5" ${data.cslrItemType==5?"selected":""}>其它方式</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************奖项类型字段的查询代码结束*****************")}
			
			
			${after$cslrItemType}
			 	
			 ${before$cslrItemIndex}
			 
			 ${lz:set("注释","*****************奖项等级字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslrItemIndex" : (lz:join(relateObject,"$cslrItemIndex")))}
			 ${lz:set("cname",lz:join(relateObject,".cslrItemIndex"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslrItemIndex || #request.defines['cslrItemIndex']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslrItemIndex":relateObject}" id="form-dl-cslrItemIndex">
					<dt>奖项等级：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslrItemIndexYesNot=="not"?"checked-not":""}${data.cslrItemIndexYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslrItemIndexYesNot}"/>
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Min" id="${NAME}Min"  value="${data.cslrItemIndexMin}"/>
			 			-
			 			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input half"  maxlength="8" size="4" name="${NAME}Max" id="${NAME}Max"  value="${data.cslrItemIndexMax}"/>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************奖项等级字段的查询代码结束*****************")}
			
			
			${after$cslrItemIndex}
			 	
			 ${before$cslrFrom}
			 
			 ${lz:set("注释","*****************抽奖来源字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslrFrom" : (lz:join(relateObject,"$cslrFrom")))}
			 ${lz:set("cname",lz:join(relateObject,".cslrFrom"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslrFrom || #request.defines['cslrFrom']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslrFrom":relateObject}" id="form-dl-cslrFrom">
					<dt>抽奖来源：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslrFromYesNot=="not"?"checked-not":""}${data.cslrFromYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslrFromYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cslrFrom==0?"selected":""}>网站</option>
							<option value="1" ${data.cslrFrom==1?"selected":""}>微信</option>
							<option value="2" ${data.cslrFrom==2?"selected":""}>app</option>
							<option value="3" ${data.cslrFrom==3?"selected":""}>其它</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************抽奖来源字段的查询代码结束*****************")}
			
			
			${after$cslrFrom}
			 	
			 ${before$cslrUpdateTime}
			 
			 ${lz:set("注释","*****************修改时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslrUpdateTime" : (lz:join(relateObject,"$cslrUpdateTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cslrUpdateTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslrUpdateTime || #request.defines['cslrUpdateTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslrUpdateTime":relateObject}" id="form-dl-cslrUpdateTime">
					<dt>修改时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslrUpdateTimeYesNot=="not"?"checked-not":""}${data.cslrUpdateTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslrUpdateTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cslrUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cslrUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cslrUpdateTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cslrUpdateTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>修改时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cslrUpdateTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************修改时间字段的查询代码结束*****************")}
			
			
			${after$cslrUpdateTime}
			 	
			 ${before$cslrAddTime}
			 
			 ${lz:set("注释","*****************添加时间字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslrAddTime" : (lz:join(relateObject,"$cslrAddTime")))}
			 ${lz:set("cname",lz:join(relateObject,".cslrAddTime"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslrAddTime || #request.defines['cslrAddTime']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslrAddTime":relateObject}" id="form-dl-cslrAddTime">
					<dt>添加时间：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslrAddTimeYesNot=="not"?"checked-not":""}${data.cslrAddTimeYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslrAddTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="${NAME}" name="${NAME}Exp" id="${NAME}Exp" start="${lz:date(data.cslrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(data.cslrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${data.cslrAddTimeExp}">
			 		<!--
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}Start" id="${NAME}Start"  value="${lz:date(data.cslrAddTimeStart,"yyyy-MM-dd HH:mm:ss")}"/>
						起</dd></dl><dl class="query-item"><dt>添加时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="${NAME}End" id="${NAME}End"  value="${lz:date(data.cslrAddTimeEnd,"yyyy-MM-dd HH:mm:ss")}"/>
						止
					-->
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************添加时间字段的查询代码结束*****************")}
			
			
			${after$cslrAddTime}
			 	
			 ${before$cslrStatus}
			 
			 ${lz:set("注释","*****************状态字段的查询输入框代码*****************")}
			 ${lz:set("NAME",relateObject==null? "cslrStatus" : (lz:join(relateObject,"$cslrStatus")))}
			 ${lz:set("cname",lz:join(relateObject,".cslrStatus"))}
			 ${lz:set("data",relateObject==null?objects:objects[lz:ForMat(relateObject)])}
			 <s:if test="#request.defines==null && #request.CTRL.q.cslrStatus || #request.defines['cslrStatus']!=null || #request.children[#request.cname]!=null">
			 	${lz:set("haveQuery",true)}
				<dl group="${relateObject==null?"cslrStatus":relateObject}" id="form-dl-cslrStatus">
					<dt>状　　态：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${data.cslrStatusYesNot=="not"?"checked-not":""}${data.cslrStatusYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
						<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.cslrStatusYesNot}"/>
			 		<select id="${NAME}" name="${NAME}" >
			 			<option value="">全部</option>
							<option value="0" ${data.cslrStatus==0?"selected":""}>未领取</option>
							<option value="1" ${data.cslrStatus==1?"selected":""}>已发放</option>
							<option value="2" ${data.cslrStatus==2?"selected":""}>弃奖</option>
			 		</select>
		 	 		
					</dd>
				</dl>
			</s:if>
			${lz:set("注释","*****************状态字段的查询代码结束*****************")}
			
			
			${after$cslrStatus}