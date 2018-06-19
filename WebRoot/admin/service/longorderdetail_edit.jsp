<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/longorderdetail.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/service/longorderdetail.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csLongOrderDetail.cslodId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cslodLongOrder,cslodCar,cslodPrice,cslodSetKilo,cslodRetKilo,cslodSetTime,cslodRemark",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cslodLongOrder,cslodCar,cslodPrice,cslodSetKilo,cslodRetKilo,cslodSetTime,cslodRemark",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cslodId,cslodLongOrder,cslodPrice,cslodSetKilo,cslodRetKilo,cslodRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cslodId,cslodLongOrder,cslodCar,cslodPrice,cslodSetKilo,cslodRetKilo,cslodSetTime,cslodRemark,cslodStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"长单明细编辑":CTRL.title}</title>
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
	$("#longorderdetailForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cslodId==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodHost==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodLongOrder==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodLongOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodCar==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodPrice==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "每月租金输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodSetKilo==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodSetKilo":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodRetKilo==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodRetKilo":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodSetTime==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodSetTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodRetTime==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodRetTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodAddTime==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodEditor==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodRemark==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslodStatus==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderDetail.cslodStatus":function(el){
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
	<form class="form editform" ref="CsLongOrderDetail" id="longorderdetailForm" name="longorderdetailForm" action="${empty CTRL.action?"longorderdetail_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写长单明细相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csLongOrderDetailToken}" name="csLongOrderDetailToken" id="csLongOrderDetailToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csLongOrderDetail.cslodId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cslodId和after$cslodId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodId==true">
	${before$cslodId}
	<dl class="cslodId ${CTRL.e.cslodId?"hidden":""}" major  ref="cslodId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cslodId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csLongOrderDetail$cslodId!=null">${csLongOrderDetail$cslodId}</s:if><s:else>
			<input type="hidden" value="${csLongOrderDetail.cslodId}" name="csLongOrderDetail.cslodId" id="cslodId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodId">${csLongOrderDetail.cslodId}</textarea>
		 		<span>
		 	
			 ${csLongOrderDetail.cslodId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cslodHost和after$cslodHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodHost==true">
	${before$cslodHost}
	<dl class="cslodHost " major  ref="cslodHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cslodHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csLongOrderDetail$cslodHost!=null">${csLongOrderDetail$cslodHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cslodHost" name="csLongOrderDetail.cslodHost">
		 				<option selected value="${csLongOrderDetail.cslodHost}">
		 					${get:SrvHost(csLongOrderDetail.cslodHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslodHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cslodHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodHost">${csLongOrderDetail.cslodHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csLongOrderDetail.cslodHost}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrderDetail.cslodHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodHost}
	</s:if>
	
	${lz:set("注释","*****************所属订单字段的输入框代码*****************")}
	${lz:set("注释","before$cslodLongOrder和after$cslodLongOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodLongOrder==true">
	${before$cslodLongOrder}
	<dl class="cslodLongOrder " major  ref="cslodLongOrder" >
		<dt>所属订单:</dt>
		<s:if test="#request.CTRL.e.cslodLongOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csLongOrderDetail$cslodLongOrder!=null">${csLongOrderDetail$cslodLongOrder}</s:if><s:else>
		 			<input title="请输入长租订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/longorder_query.do?value={param}&csloHost={cslodHost}" type="text" id="cslodLongOrder" name="csLongOrderDetail.cslodLongOrder" text="${get:CsLongOrder(csLongOrderDetail.cslodLongOrder).csloId}" value="${csLongOrderDetail.cslodLongOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslodLongOrder').val())==''){return;};window.href('${basePath}${proname}/service/longorder_details.do?key='+$('#cslodLongOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodLongOrder">${csLongOrderDetail.cslodLongOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/longorder_details.do?key=${csLongOrderDetail.cslodLongOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrderDetail.cslodLongOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodLongOrder}
	</s:if>
	
	${lz:set("注释","*****************预订车辆字段的输入框代码*****************")}
	${lz:set("注释","before$cslodCar和after$cslodCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodCar==true">
	${before$cslodCar}
	<dl class="cslodCar " major  ref="cslodCar" >
		<dt>预订车辆:</dt>
		<s:if test="#request.CTRL.e.cslodCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csLongOrderDetail$cslodCar!=null">${csLongOrderDetail$cslodCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cslodHost}" type="text" id="cslodCar" name="csLongOrderDetail.cslodCar" text="${get:CsCar(csLongOrderDetail.cslodCar).cscNumber}" value="${csLongOrderDetail.cslodCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslodCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#cslodCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预订车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodCar">${csLongOrderDetail.cslodCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csLongOrderDetail.cslodCar}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrderDetail.cslodCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodCar}
	</s:if>
	
	${lz:set("注释","*****************每月租金字段的输入框代码*****************")}
	${lz:set("注释","before$cslodPrice和after$cslodPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodPrice==true">
	${before$cslodPrice}
	<dl class="cslodPrice " major  ref="cslodPrice" >
		<dt>每月租金:</dt>
		<s:if test="#request.CTRL.e.cslodPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrderDetail$cslodPrice!=null">${csLongOrderDetail$cslodPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrderDetail.cslodPrice" id="cslodPrice"  value="${csLongOrderDetail.cslodPrice}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长单明细的每月租金</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****每月租金字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodPrice">${csLongOrderDetail.cslodPrice}</textarea>
		 		<span>
		 	
			 ${csLongOrderDetail.cslodPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodPrice}
	</s:if>
	
	${lz:set("注释","*****************交付时里程字段的输入框代码*****************")}
	${lz:set("注释","before$cslodSetKilo和after$cslodSetKilo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodSetKilo==true">
	${before$cslodSetKilo}
	<dl class="cslodSetKilo " major  ref="cslodSetKilo" >
		<dt>交付时里程:</dt>
		<s:if test="#request.CTRL.e.cslodSetKilo==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLongOrderDetail$cslodSetKilo!=null">${csLongOrderDetail$cslodSetKilo}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrderDetail.cslodSetKilo" id="cslodSetKilo"  value="${csLongOrderDetail.cslodSetKilo}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入长单明细的交付时里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****交付时里程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodSetKilo">${csLongOrderDetail.cslodSetKilo}</textarea>
		 		<span>
		 	
			 ${csLongOrderDetail.cslodSetKilo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodSetKilo}
	</s:if>
	
	${lz:set("注释","*****************还车时里程字段的输入框代码*****************")}
	${lz:set("注释","before$cslodRetKilo和after$cslodRetKilo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodRetKilo==true">
	${before$cslodRetKilo}
	<dl class="cslodRetKilo " major  ref="cslodRetKilo" >
		<dt>还车时里程:</dt>
		<s:if test="#request.CTRL.e.cslodRetKilo==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLongOrderDetail$cslodRetKilo!=null">${csLongOrderDetail$cslodRetKilo}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrderDetail.cslodRetKilo" id="cslodRetKilo"  value="${csLongOrderDetail.cslodRetKilo}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入长单明细的还车时里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****还车时里程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodRetKilo">${csLongOrderDetail.cslodRetKilo}</textarea>
		 		<span>
		 	
			 ${csLongOrderDetail.cslodRetKilo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodRetKilo}
	</s:if>
	
	${lz:set("注释","*****************车辆交付时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslodSetTime和after$cslodSetTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodSetTime==true">
	${before$cslodSetTime}
	<dl class="cslodSetTime " major  ref="cslodSetTime" >
		<dt>车辆交付时间:</dt>
		<s:if test="#request.CTRL.e.cslodSetTime==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrderDetail$cslodSetTime!=null">${csLongOrderDetail$cslodSetTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrderDetail.cslodSetTime" id="cslodSetTime"  value="<s:date name="csLongOrderDetail.cslodSetTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择长单明细的车辆交付时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆交付时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodSetTime">${csLongOrderDetail.cslodSetTime}</textarea>
		 		<span>
		 	
			 ${csLongOrderDetail.cslodSetTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodSetTime}
	</s:if>
	
	${lz:set("注释","*****************车辆归还时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslodRetTime和after$cslodRetTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodRetTime==true">
	${before$cslodRetTime}
	<dl class="cslodRetTime " major  ref="cslodRetTime" >
		<dt>车辆归还时间:</dt>
		<s:if test="#request.CTRL.e.cslodRetTime==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrderDetail$cslodRetTime!=null">${csLongOrderDetail$cslodRetTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrderDetail.cslodRetTime" id="cslodRetTime"  value="<s:date name="csLongOrderDetail.cslodRetTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择长单明细的车辆归还时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆归还时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodRetTime">${csLongOrderDetail.cslodRetTime}</textarea>
		 		<span>
		 	
			 ${csLongOrderDetail.cslodRetTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodRetTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslodUpdateTime和after$cslodUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodUpdateTime==true">
	${before$cslodUpdateTime}
	<dl class="cslodUpdateTime " major  ref="cslodUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cslodUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLongOrderDetail$cslodUpdateTime!=null">${csLongOrderDetail$cslodUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLongOrderDetail.cslodUpdateTime" id="cslodUpdateTime"  value="<s:date name="csLongOrderDetail.cslodUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长单明细的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodUpdateTime">${csLongOrderDetail.cslodUpdateTime}</textarea>
		 		<span>
		 	
			 ${csLongOrderDetail.cslodUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslodAddTime和after$cslodAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodAddTime==true">
	${before$cslodAddTime}
	<dl class="cslodAddTime " major  ref="cslodAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cslodAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLongOrderDetail$cslodAddTime!=null">${csLongOrderDetail$cslodAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLongOrderDetail.cslodAddTime" id="cslodAddTime"  value="<s:date name="csLongOrderDetail.cslodAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长单明细的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodAddTime">${csLongOrderDetail.cslodAddTime}</textarea>
		 		<span>
		 	
			 ${csLongOrderDetail.cslodAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodAddTime}
	</s:if>
	
	${lz:set("注释","*****************操作人字段的输入框代码*****************")}
	${lz:set("注释","before$cslodEditor和after$cslodEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodEditor==true">
	${before$cslodEditor}
	<dl class="cslodEditor " major  ref="cslodEditor" >
		<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cslodEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csLongOrderDetail$cslodEditor!=null">${csLongOrderDetail$cslodEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cslodEditor" name="csLongOrderDetail.cslodEditor">
		 				<option selected value="${csLongOrderDetail.cslodEditor}">
		 					${get:SrvUser(csLongOrderDetail.cslodEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslodEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cslodEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodEditor">${csLongOrderDetail.cslodEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csLongOrderDetail.cslodEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrderDetail.cslodEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodEditor}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$cslodRemark和after$cslodRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodRemark==true">
	${before$cslodRemark}
	<dl class="cslodRemark " minor  ref="cslodRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.cslodRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csLongOrderDetail$cslodRemark!=null">${csLongOrderDetail$cslodRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cslodRemark" name="csLongOrderDetail.cslodRemark" rows="5">${csLongOrderDetail.cslodRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入长单明细的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csLongOrderDetail.cslodRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cslodStatus和after$cslodStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslodStatus==true">
	${before$cslodStatus}
	<dl class="cslodStatus " major  ref="cslodStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cslodStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrderDetail$cslodStatus!=null">${csLongOrderDetail$cslodStatus}</s:if><s:else>
		 	<select class="narrow" id="cslodStatus" name="csLongOrderDetail.cslodStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csLongOrderDetail.cslodStatus==0?"selected":""}>待交付</option>
				<option value="1" ${csLongOrderDetail.cslodStatus==1?"selected":""}>使用中</option>
				<option value="2" ${csLongOrderDetail.cslodStatus==2?"selected":""}>已结束</option>
				<option value="3" ${csLongOrderDetail.cslodStatus==3?"selected":""}>已作废</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长单明细的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslodStatus">${csLongOrderDetail.cslodStatus}</textarea>
		 		<span>
		 	
			 ${csLongOrderDetail.cslodStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslodStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csLongOrderDetail.cslodId}" name="csLongOrderDetail.cslodId" id="cslodId" />
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