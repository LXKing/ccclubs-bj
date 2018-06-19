<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/fee.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/fee.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitFee.csufId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csufId,csufHost,csufName,csufUnitInfo,csufModel,csufDayPrice,csufHourPrice,csufMileage",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csufId,csufHost,csufName,csufUnitInfo,csufModel,csufDayPrice,csufHourPrice,csufMileage",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csufId,csufHost,csufName,csufUnitInfo,csufModel,csufDayPrice,csufHourPrice,csufMileage",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csufId,csufHost,csufName,csufUnitInfo,csufModel,csufDayPrice,csufHourPrice,csufMileage",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"价格配置编辑":CTRL.title}</title>
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
	$("#feeForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csufId==true">
		${lz:set("haveEditable",true)}
		,"csUnitFee.csufId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csufHost==true">
		${lz:set("haveEditable",true)}
		,"csUnitFee.csufHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csufName==true">
		${lz:set("haveEditable",true)}
		,"csUnitFee.csufName":function(el){
			if(jQuery.trim(el.value)=="")
				return "名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csufUnitInfo==true">
		${lz:set("haveEditable",true)}
		,"csUnitFee.csufUnitInfo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csufModel==true">
		${lz:set("haveEditable",true)}
		,"csUnitFee.csufModel":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择车型";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("fee_query.do",{exists:true,csufModel:el.value});
				if(exists && exists.length>0){
					if(${csUnitFee.csufId==null} || exists[0].value!=$("#csufId").val())
						return "车型已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csufDayPrice==true">
		${lz:set("haveEditable",true)}
		,"csUnitFee.csufDayPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "天租价格输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csufHourPrice==true">
		${lz:set("haveEditable",true)}
		,"csUnitFee.csufHourPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "小时价格输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csufMileage==true">
		${lz:set("haveEditable",true)}
		,"csUnitFee.csufMileage":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "里程费输入格式错误";
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
	<form class="form editform" ref="CsUnitFee" id="feeForm" name="feeForm" action="${empty CTRL.action?"fee_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写价格配置相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csUnitFeeToken}" name="csUnitFeeToken" id="csUnitFeeToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitFee.csufId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csufId和after$csufId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csufId==true">
	${before$csufId}
	<dl class="csufId ${CTRL.e.csufId?"hidden":""}" major  ref="csufId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csufId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUnitFee$csufId!=null">${csUnitFee$csufId}</s:if><s:else>
			<input type="hidden" value="${csUnitFee.csufId}" name="csUnitFee.csufId" id="csufId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csufId">${csUnitFee.csufId}</textarea>
		 		<span>
		 	
			 ${csUnitFee.csufId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csufId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csufHost和after$csufHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csufHost==true">
	${before$csufHost}
	<dl class="csufHost " major  ref="csufHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csufHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitFee$csufHost!=null">${csUnitFee$csufHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csufHost" name="csUnitFee.csufHost">
		 				<option selected value="${csUnitFee.csufHost}">
		 					${get:SrvHost(csUnitFee.csufHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csufHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csufHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csufHost">${csUnitFee.csufHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csUnitFee.csufHost}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitFee.csufHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csufHost}
	</s:if>
	
	${lz:set("注释","*****************名称字段的输入框代码*****************")}
	${lz:set("注释","before$csufName和after$csufName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csufName==true">
	${before$csufName}
	<dl class="csufName " major  ref="csufName" >
		<dt>名　　称:</dt>
		<s:if test="#request.CTRL.e.csufName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitFee$csufName!=null">${csUnitFee$csufName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitFee.csufName" id="csufName"  value="${csUnitFee.csufName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入价格配置的名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csufName">${csUnitFee.csufName}</textarea>
		 		<span>
		 	
			 ${csUnitFee.csufName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csufName}
	</s:if>
	
	${lz:set("注释","*****************所属单位字段的输入框代码*****************")}
	${lz:set("注释","before$csufUnitInfo和after$csufUnitInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csufUnitInfo==true">
	${before$csufUnitInfo}
	<dl class="csufUnitInfo " major  ref="csufUnitInfo" >
		<dt>所属单位:</dt>
		<s:if test="#request.CTRL.e.csufUnitInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitFee$csufUnitInfo!=null">${csUnitFee$csufUnitInfo}</s:if><s:else>
		 			<input title="请输入企业信息名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csufHost}" type="text" id="csufUnitInfo" name="csUnitFee.csufUnitInfo" text="${get:CsUnitInfo(csUnitFee.csufUnitInfo).csuiName}" value="${csUnitFee.csufUnitInfo}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csufUnitInfo').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?key='+$('#csufUnitInfo').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属单位字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csufUnitInfo">${csUnitFee.csufUnitInfo}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${csUnitFee.csufUnitInfo}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitFee.csufUnitInfo$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csufUnitInfo}
	</s:if>
	
	${lz:set("注释","*****************车型字段的输入框代码*****************")}
	${lz:set("注释","before$csufModel和after$csufModel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csufModel==true">
	${before$csufModel}
	<dl class="csufModel " major  ref="csufModel" >
		<dt>车　　型:</dt>
		<s:if test="#request.CTRL.e.csufModel==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitFee$csufModel!=null">${csUnitFee$csufModel}</s:if><s:else>
		 	<select class="narrow" id="csufModel" name="csUnitFee.csufModel">
		 		<option value="">请选择</option>
 					${lz:set("items", lz:query("ccclubs_system","select * from cs_car_model where 1=1 ") )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.cscmId}" ${csUnitFee.csufModel==item.cscmId?"selected":""}>${item.cscmName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csufModel').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?key='+$('#csufModel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择价格配置的车型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csufModel">${csUnitFee.csufModel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${csUnitFee.csufModel}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitFee.csufModel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csufModel}
	</s:if>
	
	${lz:set("注释","*****************天租价格字段的输入框代码*****************")}
	${lz:set("注释","before$csufDayPrice和after$csufDayPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csufDayPrice==true">
	${before$csufDayPrice}
	<dl class="csufDayPrice " major  ref="csufDayPrice" >
		<dt>天租价格:</dt>
		<s:if test="#request.CTRL.e.csufDayPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitFee$csufDayPrice!=null">${csUnitFee$csufDayPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitFee.csufDayPrice" id="csufDayPrice"  value="${csUnitFee.csufDayPrice}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>设置为0表示不启用该价格</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****天租价格字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csufDayPrice">${csUnitFee.csufDayPrice}</textarea>
		 		<span>
		 	
			 ${csUnitFee.csufDayPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csufDayPrice}
	</s:if>
	
	${lz:set("注释","*****************小时价格字段的输入框代码*****************")}
	${lz:set("注释","before$csufHourPrice和after$csufHourPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csufHourPrice==true">
	${before$csufHourPrice}
	<dl class="csufHourPrice " major  ref="csufHourPrice" >
		<dt>小时价格:</dt>
		<s:if test="#request.CTRL.e.csufHourPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitFee$csufHourPrice!=null">${csUnitFee$csufHourPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitFee.csufHourPrice" id="csufHourPrice"  value="${csUnitFee.csufHourPrice}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>设置为0表示不启用该价格</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****小时价格字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csufHourPrice">${csUnitFee.csufHourPrice}</textarea>
		 		<span>
		 	
			 ${csUnitFee.csufHourPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csufHourPrice}
	</s:if>
	
	${lz:set("注释","*****************里程费字段的输入框代码*****************")}
	${lz:set("注释","before$csufMileage和after$csufMileage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csufMileage==true">
	${before$csufMileage}
	<dl class="csufMileage " major  ref="csufMileage" >
		<dt>里&nbsp;&nbsp;程&nbsp;&nbsp;费:</dt>
		<s:if test="#request.CTRL.e.csufMileage==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitFee$csufMileage!=null">${csUnitFee$csufMileage}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitFee.csufMileage" id="csufMileage"  value="${csUnitFee.csufMileage}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>设置为0表示不启用该价格</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****里程费字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csufMileage">${csUnitFee.csufMileage}</textarea>
		 		<span>
		 	
			 ${csUnitFee.csufMileage$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csufMileage}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUnitFee.csufId}" name="csUnitFee.csufId" id="csufId" />
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