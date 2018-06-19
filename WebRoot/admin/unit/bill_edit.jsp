<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/bill.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/bill.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitBill.csubId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csubId,csubHost,csubAirport,details,specials,csubAirS,csubOrderS,csubSpecialS,csubFeeAir,csubFeeOrder,csubFeeSpecial,csubNeedPay,csubRealPay,csubSummary",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csubId,csubHost,csubAirport,details,specials,csubAirS,csubOrderS,csubSpecialS,csubFeeAir,csubFeeOrder,csubFeeSpecial,csubNeedPay,csubRealPay,csubSummary",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csubId,csubHost,csubAirport,details,specials,csubAirS,csubOrderS,csubSpecialS,csubFeeAir,csubFeeOrder,csubFeeSpecial,csubNeedPay,csubRealPay,csubSummary",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csubId,csubHost,csubMonth,csubUnit,csubAirport,details,specials,csubAirS,csubOrderS,csubSpecialS,csubFeeAir,csubFeeOrder,csubFeeSpecial,csubNeedPay,csubRealPay,csubIncome,csubPayout,csubSummary,csubStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"企业对账编辑":CTRL.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	<meta name="hashcode" content="${window.hashcode}">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		window.basePath="${basePath}";
		window.hashcode = "${window.hashcode}";
		window.contextPath="${basePath}${proname}/";
		window.apiUrl="${basePath}${proname}/api.do";
	</script>
		
	<!-- 感谢kindeditor出品的在线HTML编辑控件 -->
	<script type="text/javascript" charset="utf-8" src="${basePath}admin/js/kindeditor/kindeditor.js"></script>
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
	<!------------------------LAZY3Q_JSP_HEAD------------------------>
	<!------------------------LAZY3Q_JSP_HEAD------------------------>

</head>  
<body>

${lz:set("注释","****非法权限时的友情提醒****")}
<s:if test="#request.canView!=true && #request.canAdd!=true && #request.canEdit!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>${lz:config("nolimit.message")}</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>



<!------------------------LAZY3Q_JSP_BODY------------------------>
<!------------------------LAZY3Q_JSP_BODY------------------------>



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#billForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csubId==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubHost==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubMonth==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubMonth":function(el){
			if(jQuery.trim(el.value)=="")
				return "帐单月份不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubUnit==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubUnit":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubAirS==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubAirS":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubOrderS==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubOrderS":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubSpecialS==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubSpecialS":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubFeeAir==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubFeeAir":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "接送机费输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubFeeOrder==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubFeeOrder":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "订单费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubFeeSpecial==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubFeeSpecial":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "专车费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubNeedPay==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubNeedPay":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "应付金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubRealPay==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubRealPay":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "已付金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubEditor==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubAddTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubSummary==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubSummary":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "财务备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csubStatus==true">
		${lz:set("haveEditable",true)}
		,"csUnitBill.csubStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
	},function(){
		${lz:set("注释","****表单提交自定义判断，阻止提交返回提示字符串即可****")}
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		/******************************LAZY3Q_FORM_VALIDATE******************************/

	});		
});
</script>

<s:if test="false"></s:if>
<s:elseif test="false"></s:elseif>
<s:else>
	<form class="form editform" ref="CsUnitBill" id="billForm" name="billForm" action="${empty CTRL.action?"bill_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写企业对账相关信息，<span class="extrude">"*" 为必填选项。</span>			
					<input type="checkbox" ${empty edittype?"checked='checked'":""} id="all"/>显示全部可编辑项
				</div>
				</s:if>
				${lz:set("empty",lz:vacant(message))}
				<s:if test="#request.empty!=true">
				<br/>
				<center>
					<font color="red"><h4>${message}</h4></font>
				</center>
				<br/>
				</s:if>
				<input type="hidden" value="${editorpoint}" id="editorpoint" name="editorpoint" />	
				<input type="hidden" value="${entrypoint}" id="entrypoint" name="entrypoint" />
				<input type="hidden" value="${complete}" name="complete" id="complete" />
				<input type="hidden" value="${ids}" name="ids" id="ids" />		
				<input type="hidden" value="${edittype}" name="edittype" id="edittype" />
				<input type="hidden" value="${method}" name="method" id="method" />
				<input type="hidden" value="${lz:html(ctrl)}" id="ctrl" name="ctrl" />
				<input type="hidden" value="${csUnitBillToken}" name="csUnitBillToken" id="csUnitBillToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitBill.csubId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csubId和after$csubId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubId==true">
	${before$csubId}
	<dl class="csubId ${CTRL.e.csubId?"hidden":""}" major  ref="csubId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csubId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUnitBill$csubId!=null">${csUnitBill$csubId}</s:if><s:else>
			<input type="hidden" value="${(csUnitBill.csubId)}" name="csUnitBill.csubId" id="csubId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubId">${csUnitBill.csubId}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubId$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubId}
	</s:if>
	
	${lz:set("注释","*****************运营地区字段的输入框代码*****************")}
	${lz:set("注释","before$csubHost和after$csubHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubHost==true">
	${before$csubHost}
	<dl class="csubHost " major  ref="csubHost" >
		<dt>运营地区:</dt>
		<s:if test="#request.CTRL.e.csubHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitBill$csubHost!=null">${csUnitBill$csubHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csubHost" name="csUnitBill.csubHost">
		 				<option selected value="${(csUnitBill.csubHost)}">
		 					${get:SrvHost(csUnitBill.csubHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csubHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csubHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****运营地区字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubHost">${csUnitBill.csubHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${(csUnitBill.csubHost)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csUnitBill.csubHost$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubHost}
	</s:if>
	
	${lz:set("注释","*****************帐单月份字段的输入框代码*****************")}
	${lz:set("注释","before$csubMonth和after$csubMonth变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubMonth==true">
	${before$csubMonth}
	<dl class="csubMonth " major  ref="csubMonth" >
		<dt>帐单月份:</dt>
		<s:if test="#request.CTRL.e.csubMonth==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csUnitBill$csubMonth!=null">${csUnitBill$csubMonth}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM'})" type="text" class="input narrow" maxlength="19" name="csUnitBill.csubMonth" id="csubMonth"  value="<s:date name="csUnitBill.csubMonth" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>yyyy-MM</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****帐单月份字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubMonth">${csUnitBill.csubMonth}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubMonth$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubMonth}
	</s:if>
	
	${lz:set("注释","*****************所属单位字段的输入框代码*****************")}
	${lz:set("注释","before$csubUnit和after$csubUnit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubUnit==true">
	${before$csubUnit}
	<dl class="csubUnit " major  ref="csubUnit" >
		<dt>所属单位:</dt>
		<s:if test="#request.CTRL.e.csubUnit==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitBill$csubUnit!=null">${csUnitBill$csubUnit}</s:if><s:else>
		 			<input title="请输入企业信息名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csubHost}" type="text" id="csubUnit" name="csUnitBill.csubUnit" text="${get:CsUnitInfo(csUnitBill.csubUnit).csuiName}" value="${(csUnitBill.csubUnit)}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csubUnit').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?key='+$('#csubUnit').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属单位字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubUnit">${csUnitBill.csubUnit}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${(csUnitBill.csubUnit)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csUnitBill.csubUnit$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubUnit}
	</s:if>
	
	<!-- 
	${lz:set("注释","*****************机场服务字段的输入框代码*****************")}
	${lz:set("注释","before$csubAirport和after$csubAirport变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubAirport==true">
	${before$csubAirport}
	<dl class="csubAirport " major  ref="csubAirport" style="width:98%;">
		<dt>机场服务:</dt>
		<s:if test="#request.CTRL.e.csubAirport==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csUnitBill$csubAirport!=null">${csUnitBill$csubAirport}</s:if><s:else>
		  		<s:if test="csUnitBill.csubId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csubAirport" name="csubAirport" frameborder='0' src="" url="${basePath}${proname}/unit/airport.do?inFrame=true&canQuery=false&fields=csuaType,csuaFlight,csuaTime,csuaStatus&csuaStatement=${csUnitBill.csubId}&ctrl={queryable:false,fields:{csuaStatement:{visible:true,editable:false,defaultValue:${csUnitBill.csubId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csubAirport').attr('src',$('#csubAirport').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑机场服务
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****机场服务字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csUnitBill.csubId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csubAirport" name="csubAirport" frameborder='0' src="" url="${basePath}${proname}/unit/airport.do?inFrame=true&canQuery=false&fields=csuaType,csuaFlight,csuaTime,csuaStatus&csuaStatement=${csUnitBill.csubId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csubAirport').attr('src',$('#csubAirport').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csubAirport}
	</s:if>
	 -->
	
	${lz:set("注释","*****************订单明细字段的输入框代码*****************")}
	${lz:set("注释","before$details和after$details变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.details==true">
	${before$details}
	<dl class="details " minor  ref="details" style="width:98%;">
		<dt>订单明细:</dt>
		<s:if test="#request.CTRL.e.details==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csUnitBill$details!=null">${csUnitBill$details}</s:if><s:else>
		  		<s:if test="csUnitBill.csubId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/unit/order.do?inFrame=true&canQuery=false&fields=csuoId,csuoUnitMember,csuoUnitChild,csuoSettleRent,csuoSettleKilo,csuoSettleOther,csuoSettlement&csuoStatement=${csUnitBill.csubId}&ctrl={queryable:false,fields:{csuoStatement:{visible:true,editable:false,defaultValue:${csUnitBill.csubId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑订单明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csUnitBill.csubId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/unit/order.do?inFrame=true&canQuery=false&fields=csuoId,csuoUnitMember,csuoUnitChild,csuoSettleRent,csuoSettleKilo,csuoSettleOther,csuoSettlement&csuoStatement=${csUnitBill.csubId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$details}
	</s:if>
	
	${lz:set("注释","*****************专车订单字段的输入框代码*****************")}
	${lz:set("注释","before$specials和after$specials变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.specials==true">
	${before$specials}
	<dl class="specials " minor  ref="specials" style="width:98%;">
		<dt>专车订单:</dt>
		<s:if test="#request.CTRL.e.specials==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csUnitBill$specials!=null">${csUnitBill$specials}</s:if><s:else>
		  		<s:if test="csUnitBill.csubId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="specials" name="specials" frameborder='0' src="" url="${basePath}${proname}/unit/specialcar.do?inFrame=true&canQuery=false&fields=csscPerson,csscName,csscCarModel,csscCarNumber,csscThirdNo,csscThirdFee,csscThirdOtherFee,csscStatus&csscStatement=${csUnitBill.csubId}&ctrl={queryable:false,fields:{csscStatement:{visible:true,editable:false,defaultValue:${csUnitBill.csubId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#specials').attr('src',$('#specials').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑专车订单
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****专车订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csUnitBill.csubId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="specials" name="specials" frameborder='0' src="" url="${basePath}${proname}/unit/specialcar.do?inFrame=true&canQuery=false&fields=csscPerson,csscName,csscCarModel,csscCarNumber,csscThirdNo,csscThirdFee,csscThirdOtherFee,csscStatus&csscStatement=${csUnitBill.csubId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#specials').attr('src',$('#specials').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$specials}
	</s:if>
	
	${lz:set("注释","*****************订单明细字段的输入框代码*****************")}
	${lz:set("注释","before$record和after$record变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.record==true">
	${before$record}
	<dl class="record " minor  ref="record" style="width:98%;">
		<dt>收入明细:</dt>
		<s:if test="#request.CTRL.e.record==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csUnitBill$record!=null">${csUnitBill$record}</s:if><s:else>
		  		<s:if test="csUnitBill.csubId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="record" name="record" frameborder='0' src="" url="${basePath}${proname}/unit/record.do?inFrame=true&canQuery=false&fields=csrId,csrHost,csrRemark,csrMember,csrMoneyType,csrRecordSubject,csrRecordType,csrAmount,csrAddTime,csrOrder,csrObject,csrEditor&csuoStatement=${csUnitBill.csubId}&ctrl={queryable:false,fields:{csuoStatement:{visible:true,editable:false,defaultValue:${csUnitBill.csubId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#record').attr('src',$('#record').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑订单明细
				</div>
				</s:else>
	 	 </s:else>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csUnitBill.csubId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="record" name="record" frameborder='0' src="" url="${basePath}${proname}/finance/record.do?inFrame=true&canQuery=false&fields=csrId,csrRemark,csrMember,csrMoneyType,csrRecordSubject,csrAmount,csrAddTime,csrOrder,csrObject,csrEditor&record=true&recordIn=true&csrMemberList=${csUnitBill.$csubUnit.csuiMember}&csrHost=${csUnitBill.csubHost }&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#record').attr('src',$('#record').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$record}
	</s:if>
	
	${lz:set("注释","*****************订单明细字段的输入框代码*****************")}
	${lz:set("注释","before$recordOut和after$recordOut变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.recordOut==true">
	${before$recordOut}
	<dl class="recordOut " minor  ref="recordOut" style="width:98%;">
		<dt>支出明细:</dt>
		<s:if test="#request.CTRL.e.recordOut==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csUnitBill$recordOut!=null">${csUnitBill$recordOut}</s:if><s:else>
		  		<s:if test="csUnitBill.csubId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="recordOut" name="recordOut" frameborder='0' src="" url="${basePath}${proname}/finance/record.do?inFrame=true&canQuery=false&fields=csrId,csrRemark,csrMember,csrMoneyType,csrRecordSubject,csrAmount,csrAddTime,csrOrder,csrObject,csrEditor&csuoStatement=${csUnitBill.csubId}&ctrl={queryable:false,fields:{csuoStatement:{visible:true,editable:false,defaultValue:${csUnitBill.csubId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#recordOut').attr('src',$('#recordOut').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑订单明细
				</div>
				</s:else>
	 	 </s:else>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csUnitBill.csubId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="recordOut" name="recordOut" frameborder='0' src="" url="${basePath}${proname}/finance/record.do?inFrame=true&canQuery=false&fields=csrId,csrRemark,csrMember,csrMoneyType,csrRecordSubject,csrAmount,csrAddTime,csrOrder,csrObject,csrEditor&record=true&recordIn=false&csrMemberList=${csUnitBill.$csubUnit.csuiMember}&csrHost=${csUnitBill.csubHost }&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#recordOut').attr('src',$('#recordOut').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$recordOut}
	</s:if>
	
	${lz:set("注释","*****************接机次数字段的输入框代码*****************")}
	${lz:set("注释","before$csubAirS和after$csubAirS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubAirS==true">
	${before$csubAirS}
	<dl class="csubAirS " major  ref="csubAirS" >
		<dt>接机次数:</dt>
		<s:if test="#request.CTRL.e.csubAirS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csUnitBill$csubAirS!=null">${csUnitBill$csubAirS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitBill.csubAirS" id="csubAirS"  value="${(csUnitBill.csubAirS)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业对账的接机次数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接机次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubAirS">${csUnitBill.csubAirS}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubAirS$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubAirS}
	</s:if>
	
	${lz:set("注释","*****************订单个数字段的输入框代码*****************")}
	${lz:set("注释","before$csubOrderS和after$csubOrderS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubOrderS==true">
	${before$csubOrderS}
	<dl class="csubOrderS " major  ref="csubOrderS" >
		<dt>订单个数:</dt>
		<s:if test="#request.CTRL.e.csubOrderS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csUnitBill$csubOrderS!=null">${csUnitBill$csubOrderS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitBill.csubOrderS" id="csubOrderS"  value="${(csUnitBill.csubOrderS)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业对账的订单个数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单个数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubOrderS">${csUnitBill.csubOrderS}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubOrderS$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubOrderS}
	</s:if>
	
	${lz:set("注释","*****************专车个数字段的输入框代码*****************")}
	${lz:set("注释","before$csubSpecialS和after$csubSpecialS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubSpecialS==true">
	${before$csubSpecialS}
	<dl class="csubSpecialS " major  ref="csubSpecialS" >
		<dt>专车个数:</dt>
		<s:if test="#request.CTRL.e.csubSpecialS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csUnitBill$csubSpecialS!=null">${csUnitBill$csubSpecialS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitBill.csubSpecialS" id="csubSpecialS"  value="${(csUnitBill.csubSpecialS)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业对账的专车个数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****专车个数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubSpecialS">${csUnitBill.csubSpecialS}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubSpecialS$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubSpecialS}
	</s:if>
	
	${lz:set("注释","*****************接送机费字段的输入框代码*****************")}
	${lz:set("注释","before$csubFeeAir和after$csubFeeAir变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubFeeAir==true">
	${before$csubFeeAir}
	<dl class="csubFeeAir " major  ref="csubFeeAir" >
		<dt>接送机费:</dt>
		<s:if test="#request.CTRL.e.csubFeeAir==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitBill$csubFeeAir!=null">${csUnitBill$csubFeeAir}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitBill.csubFeeAir" id="csubFeeAir"  value="${(csUnitBill.csubFeeAir)}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入企业对账的接送机费</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接送机费字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubFeeAir">${csUnitBill.csubFeeAir}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubFeeAir$)}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubFeeAir}
	</s:if>
	
	${lz:set("注释","*****************订单费用字段的输入框代码*****************")}
	${lz:set("注释","before$csubFeeOrder和after$csubFeeOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubFeeOrder==true">
	${before$csubFeeOrder}
	<dl class="csubFeeOrder " major  ref="csubFeeOrder" >
		<dt>订单费用:</dt>
		<s:if test="#request.CTRL.e.csubFeeOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitBill$csubFeeOrder!=null">${csUnitBill$csubFeeOrder}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitBill.csubFeeOrder" id="csubFeeOrder"  value="${(csUnitBill.csubFeeOrder)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业对账的订单费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubFeeOrder">${csUnitBill.csubFeeOrder}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubFeeOrder$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubFeeOrder}
	</s:if>
	
	${lz:set("注释","*****************专车费用字段的输入框代码*****************")}
	${lz:set("注释","before$csubFeeSpecial和after$csubFeeSpecial变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubFeeSpecial==true">
	${before$csubFeeSpecial}
	<dl class="csubFeeSpecial " major  ref="csubFeeSpecial" >
		<dt>专车费用:</dt>
		<s:if test="#request.CTRL.e.csubFeeSpecial==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitBill$csubFeeSpecial!=null">${csUnitBill$csubFeeSpecial}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitBill.csubFeeSpecial" id="csubFeeSpecial"  value="${(csUnitBill.csubFeeSpecial)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业对账的专车费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****专车费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubFeeSpecial">${csUnitBill.csubFeeSpecial}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubFeeSpecial$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubFeeSpecial}
	</s:if>
	
	${lz:set("注释","*****************应付金额字段的输入框代码*****************")}
	${lz:set("注释","before$csubNeedPay和after$csubNeedPay变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubNeedPay==true">
	${before$csubNeedPay}
	<dl class="csubNeedPay " major  ref="csubNeedPay" >
		<dt>应付金额:</dt>
		<s:if test="#request.CTRL.e.csubNeedPay==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitBill$csubNeedPay!=null">${csUnitBill$csubNeedPay}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitBill.csubNeedPay" id="csubNeedPay"  value="${(csUnitBill.csubNeedPay)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>全部应付金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****应付金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubNeedPay">${csUnitBill.csubNeedPay}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubNeedPay$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubNeedPay}
	</s:if>
	
	${lz:set("注释","*****************已付金额字段的输入框代码*****************")}
	${lz:set("注释","before$csubRealPay和after$csubRealPay变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubRealPay==true">
	${before$csubRealPay}
	<dl class="csubRealPay " major  ref="csubRealPay" >
		<dt>已付金额:</dt>
		<s:if test="#request.CTRL.e.csubRealPay==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitBill$csubRealPay!=null">${csUnitBill$csubRealPay}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitBill.csubRealPay" id="csubRealPay"  value="${(csUnitBill.csubRealPay)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>实际已付金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****已付金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubRealPay">${csUnitBill.csubRealPay}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubRealPay$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubRealPay}
	</s:if>
	
	${lz:set("注释","*****************已付金额字段的输入框代码*****************")}
	${lz:set("注释","before$csubIncome和after$csubIncome变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubIncome==true">
	${before$csubIncome}
	<dl class="csubIncome " major  ref="csubIncome" >
		<dt>收入金额:</dt>
		<s:if test="#request.CTRL.e.csubIncome==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitBill$csubIncome!=null">${csUnitBill$csubIncome}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitBill.csubIncome" id="csubIncome"  value="${(csUnitBill.csubIncome)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>实际收入金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****已付金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<input class="" style="display:none;" id="csubIncome" value="${csUnitBill.csubIncome}" />
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubIncome$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubIncome}
	</s:if>
	
	${lz:set("注释","*****************已付金额字段的输入框代码*****************")}
	${lz:set("注释","before$csubPayout和after$csubPayout变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubPayout==true">
	${before$csubIncome}
	<dl class="csubPayout " major  ref="csubPayout" >
		<dt>支出金额:</dt>
		<s:if test="#request.CTRL.e.csubPayout==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitBill$csubPayout!=null">${csUnitBill$csubPayout}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitBill.csubPayout" id="csubPayout"  value="${(csUnitBill.csubPayout)}"/>
	 	 </s:else>
	 	 <em>实际支出金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****已付金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<input class="" style="display:none;" id="csubPayout" value="${csUnitBill.csubPayout}" />
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubPayout$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubPayout}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$csubEditor和after$csubEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubEditor==true">
	${before$csubEditor}
	<dl class="csubEditor " major  ref="csubEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csubEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitBill$csubEditor!=null">${csUnitBill$csubEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csubEditor" name="csUnitBill.csubEditor">
		 				<option selected value="${(csUnitBill.csubEditor)}">
		 					${get:SrvUser(csUnitBill.csubEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csubEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csubEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubEditor">${csUnitBill.csubEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${(csUnitBill.csubEditor)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csUnitBill.csubEditor$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csubUpdateTime和after$csubUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubUpdateTime==true">
	${before$csubUpdateTime}
	<dl class="csubUpdateTime " major  ref="csubUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csubUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitBill$csubUpdateTime!=null">${csUnitBill$csubUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitBill.csubUpdateTime" id="csubUpdateTime"  value="<s:date name="csUnitBill.csubUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业对账的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubUpdateTime">${csUnitBill.csubUpdateTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubUpdateTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csubAddTime和after$csubAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubAddTime==true">
	${before$csubAddTime}
	<dl class="csubAddTime " major  ref="csubAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csubAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitBill$csubAddTime!=null">${csUnitBill$csubAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitBill.csubAddTime" id="csubAddTime"  value="<s:date name="csUnitBill.csubAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业对账的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubAddTime">${csUnitBill.csubAddTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubAddTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubAddTime}
	</s:if>
	
	${lz:set("注释","*****************财务备注字段的输入框代码*****************")}
	${lz:set("注释","before$csubSummary和after$csubSummary变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubSummary==true">
	${before$csubSummary}
	<dl class="csubSummary " major  ref="csubSummary" style="width:98%;">
		<dt>财务备注:</dt>
		<s:if test="#request.CTRL.e.csubSummary==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUnitBill$csubSummary!=null">${csUnitBill$csubSummary}</s:if><s:else>
		 	<textarea class="input wide"  id="csubSummary" name="csUnitBill.csubSummary" rows="5">${(csUnitBill.csubSummary)}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业对账的财务备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****财务备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubSummary$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubSummary}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csubStatus和after$csubStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csubStatus==true">
	${before$csubStatus}
	<dl class="csubStatus " major  ref="csubStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csubStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitBill$csubStatus!=null">${csUnitBill$csubStatus}</s:if><s:else>
		 	<select class="narrow" id="csubStatus" name="csUnitBill.csubStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csUnitBill.csubStatus==0?"selected":""}>未审核</option>
				<option value="1" ${csUnitBill.csubStatus==1?"selected":""}>待结算</option>
				<option value="2" ${csUnitBill.csubStatus==2?"selected":""}>已结算</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业对账的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csubStatus">${csUnitBill.csubStatus}</textarea>
		 		<span>
		 	
			 ${lz:html(csUnitBill.csubStatus$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csubStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUnitBill.csubId}" name="csUnitBill.csubId" id="csubId" />
					</s:if>			
					<s:if test="#request.haveEditable==true">
						${lz:set("submitsCount",lz:size(CTRL.submits))}
						<s:if test="#request.submitsCount>0">
							<s:iterator value="#request.CTRL.submits" id="submit" status="i">
					<button class="button" type="submit" name="submiter" value="${submit.value}">&nbsp;&nbsp;${submit.name}&nbsp;&nbsp;</button>
							</s:iterator>
						</s:if>
						<s:else>
					<s:if test="#request.complete!=null && #request.complete!=''">
						${lz:set("注释","****如果调用时传入了complete完成句柄，那么只能提交到本页面，由action控制调用该句柄****")}
						<button title="保存数据,不关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="保存">
							&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
							保存&nbsp;&nbsp;</button>
					</s:if>
					<s:else>
						<button title="保存数据,关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="完成">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						完成&nbsp;&nbsp;</button>
						<button title="保存数据,不关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="保存">
							&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
							保存&nbsp;&nbsp;</button>
					</s:else>
						</s:else>
					<button class="button" type="reset" value="重置">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778045.png"/>
						重置&nbsp;&nbsp;</button>
					</s:if>
					<s:else>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						确定&nbsp;&nbsp;</button>
					</s:else>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:else>

<script>
	function cancel(){
		$.closeModalDialog();
	}
	function href(url,params){
		top.$.showModalDialog({url:url},params,window);
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>