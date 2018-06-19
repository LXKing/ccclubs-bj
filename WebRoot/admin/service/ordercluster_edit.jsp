<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/order.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/service/ordercluster.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrderCluster.csocId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csocHost,csocSubOrders,csocPayMember,csocMobile,csocPrice,csocMarginNeed,csocTotalDuration,csocPayNeed,csocPayReal,csocPayRent,csocPayInsure,csocPayTimeout,csocPayDetails,csocStartTime,csocFinishTime,csocStatus,csocAddTime,csocUpdateTime",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csocId,csocHost,csocSubOrders,csocPayMember,csocMobile,csocPrice,csocMarginNeed,csocTotalDuration,csocPayNeed,csocPayReal,csocPayRent,csocPayInsure,csocPayTimeout,csocPayDetails,csocStartTime,csocFinishTime,csocStatus,csocAddTime,csocUpdateTime",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"订单簇编辑":CTRL.title}</title>
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



<!--LAZY3Q_JSP_BODY-->




<!--LAZY3Q_JSP_BODY-->



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#orderForm").form({
		"":function(){}
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
	<form class="form editform" ref="CsOrderCluster" id="orderclusterForm" name="orderclusterForm" action="${empty CTRL.action?"order_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写订单簇相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csOrderToken}" name="csOrderClusterToken" id="csOrderClusterToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrderCluster.csocId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************订单编号字段的输入框代码*****************")}
	${lz:set("注释","before$csocId和after$csocId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocId==true">
	${before$csocId}
	<dl class="csocId ${CTRL.e.csocId?"hidden":""}" major  ref="csocId" >
		<dt>订单编号:</dt>
		<s:if test="#request.CTRL.e.csocId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csOrderCluster$csocId!=null">${csOrderCluster$csocId}</s:if><s:else>
			<input type="hidden" value="${csOrderCluster.csocId}" name="csOrderCluster.csocId" id="csocId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocId">${csOrderCluster.csocId}</textarea>
		 		<span>
		 	
			 ${csOrderCluster.csocId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csoHost和after$csoHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocHost==true">
	${before$csocHost}
	<dl class="csocHost " major  ref="csocHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csocHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrderCluster$csocHost!=null">${csOrderCluster$csocHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csocHost" name="csOrderCluster.csocHost">
		 				<option selected value="${csOrderCluster.csocHost}">
		 					${get:SrvHost(csOrderCluster.csocHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csocHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csocHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocHost">${csOrderCluster.csocHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csOrderCluster.csocHost}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderCluster.csocHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocHost}
	</s:if>
	
	
	${lz:set("注释","*****************子订单列表字段的输入框代码*****************")}
	${lz:set("注释","before$csocSubOrders和after$csocSubOrders变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocSubOrders==true">
	${before$csocSubOrders}
	<dl class="csocSubOrders " minor  ref="csocSubOrders" style="width:98%;">
		<dt>子订单列表:</dt>
		<s:if test="#request.CTRL.e.csocSubOrders==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csOrderCluster$csocSubOrders!=null">${csOrderCluster$csocSubOrders}</s:if><s:else>
		  		<s:if test="csOrderCluster.cscmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csocSubOrders" name="csocSubOrders" frameborder='0' src="" url="${basePath}${proname}/object/car.do?inFrame=true&canQuery=false&fields=csoId,csoPayTimeout,csoDuration,csoUseIndex,csoState,csoStatus&cscModel=${csOrderCluster.cscmId}&ctrl={queryable:false,fields:{cscModel:{visible:true,editable:false,defaultValue:${csOrderCluster.cscmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csocSubOrders').attr('src',$('#csocSubOrders').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑车辆列表
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆列表字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csOrderCluster.csocSubOrders!=null && csOrderCluster.csocSubOrders != '' ">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csocSubOrders" name="csocSubOrders" frameborder='0' src="" url="${basePath}${proname}/service/order.do?inFrame=true&canQuery=false&fields=csoId,csoPayTimeout,csoDuration,csoUseIndex,csoState,csoStatus&csoIds=${csOrderCluster.csocSubOrders}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csocSubOrders').attr('src',$('#csocSubOrders').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csocSubOrders}
	</s:if>
	
	
	
	
	${lz:set("注释","*****************支付会员字段的输入框代码*****************")}
	${lz:set("注释","before$csocPayMember和after$csocPayMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocPayMember==true">
	${before$csocPayMember}
	<dl class="csocPayMember " major  ref="csocPayMember" >
		<dt>支付会员:</dt>
		<s:if test="#request.CTRL.e.csocPayMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrderCluster$csocPayMember!=null">${csOrderCluster$csocPayMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csocHost}" type="text" id="csocPayMember" name="csOrderCluster.csocPayMember" text="${get:CsMember(csOrderCluster.csocPayMember).csmName}" value="${csOrderCluster.csocPayMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csocPayMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csocPayMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocPayMember">${csOrderCluster.csocPayMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csOrderCluster.csocPayMember}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderCluster.csocPayMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocPayMember}
	</s:if>
	
	${lz:set("注释","*****************使用会员字段的输入框代码*****************")}
	${lz:set("注释","before$csocMobile和after$csocMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocMobile==true">
	${before$csocMobile}
	<dl class="csocMobile " major  ref="csocMobile" >
		<dt>支付明细:</dt>
		<s:if test="#request.CTRL.e.csocMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrderCluster$csocMobile!=null">${csOrderCluster$csocMobile}</s:if><s:else>
		 			<input title="请输入手机号进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csocHost}" type="text" id="csocMobile" name="csOrderCluster.csocMobile" text="${get:CsMember(csOrderCluster.csocMobile).csmName}" value="${csOrderCluster.csocMobile}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csocMobile').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csocMobile').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用手机号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocMobile">${csOrderCluster.csocMobile}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csOrderCluster.csocMobile}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderCluster.csocMobile$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocMobile}
	</s:if>
	

	
	${lz:set("注释","*****************订单总价类型字段的输入框代码*****************")}
	${lz:set("注释","before$csocPrice和after$csocPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocPrice==true">
	${before$csocPrice}
	<dl class="csocPrice " major  ref="csocPrice" >
		<dt>订单总价:</dt>
		<s:if test="#request.CTRL.e.csocPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrderCluster$csocPrice!=null">${csOrderCluster$csocPrice}</s:if><s:else>
		 	<input class="combox narrow"  type="text" id="csocPrice" name="csOrderCluster.csocPrice"  value="${csOrderCluster.csocPrice}" />
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单总价</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****计费类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocPrice">${csOrderCluster.csocPrice}</textarea>
		 		<span>
			 ${csOrderCluster.csocPrice$}</a>
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocPrice}
	</s:if>
	
	${lz:set("注释","*****************网点区域字段的输入框代码*****************")}
	${lz:set("注释","before$csocMarginNeed和after$csocMarginNeed变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocMarginNeed==true">
	${before$csocMarginNeed}
	<dl class="csocMarginNeed " major  ref="csocMarginNeed" >
		<dt>押金/保证金:</dt>
		<s:if test="#request.CTRL.e.csocMarginNeed==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrderCluster$csocMarginNeed!=null">${csOrderCluster$csocMarginNeed}</s:if><s:else>
	 	 	<input class="combox narrow"  type="text" id="csocMarginNeed" name="csOrderCluster.csocMarginNeed"  value="${csOrderCluster.csocMarginNeed}" />
	 	 	
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入押金/保证金</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****网点区域字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocMarginNeed">${csOrderCluster.csocMarginNeed}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/area_details.do?key=${csOrderCluster.csocMarginNeed}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderCluster.csocMarginNeed$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocMarginNeed}
	</s:if>
	
	${lz:set("注释","*****************订单总时长字段的输入框代码*****************")}
	${lz:set("注释","before$csocTotalDuration和after$csocTotalDuration变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocTotalDuration==true">
	${before$csocTotalDuration}
	<dl class="csocTotalDuration " major  ref="csocTotalDuration" >
		<dt>订单总时长:</dt>
		<s:if test="#request.CTRL.e.csocTotalDuration==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrderCluster$csocTotalDuration!=null">${csOrderCluster$csocTotalDuration}</s:if><s:else>
	 	 	<input class="combox narrow"  type="text" id="csocTotalDuration" name="csOrderCluster.csocTotalDuration"  value="${csOrderCluster.csocTotalDuration}" />
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单总时长字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocTotalDuration">${csOrderCluster.csocTotalDuration}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csOrderCluster.csocTotalDuration}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderCluster.csocTotalDuration$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocTotalDuration}
	</s:if>
	
	${lz:set("注释","*****************应付金额字段的输入框代码*****************")}
	${lz:set("注释","before$csocPayNeed和after$csocPayNeed变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocPayNeed==true">
	${before$csocPayNeed}
	<dl class="csocPayNeed " major  ref="csocPayNeed" >
		<dt>应付金额:</dt>
		<s:if test="#request.CTRL.e.csocPayNeed==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrderCluster$csocPayNeed!=null">${csOrderCluster$csocPayNeed}</s:if><s:else>
	 	 </s:else>
	 	 	<input class="combox narrow"  type="text" id="csocPayNeed" name="csOrderCluster.csocPayNeed"  value="${csOrderCluster.csocPayNeed}" />
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****应付金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocTotalDurationRet">${csOrderCluster.csocPayNeed}</textarea>
		 		<span>
		 	
			 ${csOrderCluster.csocPayNeed$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocPayNeed}
	</s:if>
	
	${lz:set("注释","*****************实付金额字段的输入框代码*****************")}
	${lz:set("注释","before$csocPayReal和after$csocPayReal变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocPayReal==true">
	${before$csocPayReal}
	<dl class="csocPayReal " major  ref="csocPayReal" >
		<dt>预订车型:</dt>
		<s:if test="#request.CTRL.e.csocPayReal==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrderCluster$csocPayReal!=null">${csOrderCluster$csocPayReal}</s:if><s:else>
	 		<input class="combox narrow"  type="text" id="csocPayReal" name="csOrderCluster.csocPayReal"  value="${csOrderCluster.csocPayReal}" />
	 	</s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实付金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocPayReal">${csOrderCluster.csocPayReal}</textarea>
		 		<span>
		 	
			 ${csOrderCluster.csocPayReal$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocPayReal}
	</s:if>
	
	${lz:set("注释","*****************预订车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csocPayRent和after$csocPayRent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocPayRent==true">
	${before$csocPayRent}
	<dl class="csocPayRent " major  ref="csocPayRent" >
		<dt>租金费用:</dt>
		<s:if test="#request.CTRL.e.csocPayRent==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrderCluster$csocPayRent!=null">${csOrderCluster$csocPayRent}</s:if><s:else>
		 			<input class="combox narrow" type="text" id="csocPayRent" name="csOrderCluster.csocPayRent"  value="${csOrderCluster.csocPayRent}" />
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****租金费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocPayRent">${csOrderCluster.csocPayRent}</textarea>
		 		<span>
		 	
			 ${csOrderCluster.csocPayRent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocTotalDuration}
	</s:if>
	
	${lz:set("注释","*****************免责费用字段的输入框代码*****************")}
	${lz:set("注释","before$csocPayInsure和after$csocPayInsure变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocPayInsure==true">
	${before$csocPayInsure}
	<dl class="csocPayInsure " major  ref="csocPayInsure" >
		<dt>免责费用:</dt>
		<s:if test="#request.CTRL.e.csocPayInsure==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrderCluster$csocPayInsure!=null">${csOrderCluster$csocPayInsure}</s:if><s:else>
		 			<input  class="combox narrow"  type="text" id="csocPayInsure" name="csOrderCluster.csocPayInsure"  value="${csOrderCluster.csocPayInsure}" />
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****免责费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocPayInsure">${csOrderCluster.csocPayInsure}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/provid_details.do?key=${csOrderCluster.csocPayInsure}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderCluster.csocPayInsure$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocPayInsure}
	</s:if>
	
	${lz:set("注释","*****************超时费用字段的输入框代码*****************")}
	${lz:set("注释","before$csocPayTimeout和after$csocPayTimeout变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocPayTimeout==true">
	${before$csocPayTimeout}
	<dl class="csocPayTimeout " major  ref="csocPayTimeout" >
		<dt>超时费用:</dt>
		<s:if test="#request.CTRL.e.csocPayTimeout==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOrderCluster$csocPayTimeout!=null">${csOrderCluster$csocPayTimeout}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOrderCluster.csocPayTimeout" id="csocPayTimeout"  value="${csOrderCluster.csocPayTimeout}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统订单的超时费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****超时费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocPayTimeout">${csOrderCluster.csocPayTimeout}</textarea>
		 		<span>
		 	
			 ${csOrderCluster.csocPayTimeout$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocTotalDuration}
	</s:if>
	
	${lz:set("注释","*****************支付明细字段的输入框代码*****************")}
	${lz:set("注释","before$csocPayDetails和after$csocPayDetails变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocPayDetails==true">
	${before$csocPayDetails}
	<dl class="csocPayDetails " minor  ref="csocPayDetails" >
		<dt>支付明细:</dt>
		<s:if test="#request.CTRL.e.csocPayDetails==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOrderCluster$csocPayDetails!=null">${csOrderCluster$csocPayDetails}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOrderCluster.csocPayDetails" id="csocPayDetails"  value="${csOrderCluster.csocPayDetails}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统订单的支付明细</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocPayDetails">${csOrderCluster.csocPayDetails}</textarea>
		 		<span>
		 	
			 ${csOrderCluster.csocPayDetails$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocPayDetails}
	</s:if>

	
	${lz:set("注释","*****************订单开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csocStartTime和after$csocStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocStartTime==true">
	${before$csocStartTime}
	<dl class="csocStartTime " major  ref="csocStartTime" >
		<dt>订单开始时间:</dt>
		<s:if test="#request.CTRL.e.csocStartTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrderCluster$csocStartTime!=null">${csOrderCluster$csocStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrderCluster.csocStartTime" id="csocStartTime"  value="<s:date name="csOrderCluster.csocStartTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的订单开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocStartTime">${csOrderCluster.csocStartTime}</textarea>
		 		<span>
		 	
			 ${csOrderCluster.csocStartTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocStartTime}
	</s:if>
	
	${lz:set("注释","*****************订单结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csocFinishTime和after$csocFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocFinishTime==true">
	${before$csocFinishTime}
	<dl class="csocFinishTime " major  ref="csocFinishTime" >
		<dt>订单结束时间:</dt>
		<s:if test="#request.CTRL.e.csocFinishTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrderCluster$csocFinishTime!=null">${csOrderCluster$csocFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrderCluster.csocFinishTime" id="csocFinishTime"  value="<s:date name="csOrderCluster.csocFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的订单结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocFinishTime">${csOrderCluster.csocFinishTime}</textarea>
		 		<span>
		 	
			 ${csOrderCluster.csocFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocFinishTime}
	</s:if>
	

	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	

	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csocUpdateTime和after$csocUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocUpdateTime==true">
	${before$csocUpdateTime}
	<dl class="csocUpdateTime " major  ref="csocUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csocUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrderCluster$csocUpdateTime!=null">${csOrderCluster$csocUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrderCluster.csocUpdateTime" id="csocUpdateTime"  value="<s:date name="csOrderCluster.csocUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocUpdateTime">${csOrderCluster.csocUpdateTime}</textarea>
		 		<span>
		 	
			 ${csOrderCluster.csocUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csocAddTime和after$csocAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocAddTime==true">
	${before$csocAddTime}
	<dl class="csocAddTime " major  ref="csocAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csocAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrderCluster$csocAddTime!=null">${csOrderCluster$csocAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrderCluster.csocAddTime" id="csocAddTime"  value="<s:date name="csOrderCluster.csocAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocAddTime">${csOrderCluster.csocAddTime}</textarea>
		 		<span>
		 	
			 ${csOrderCluster.csocAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocAddTime}
	</s:if>
	
	
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csocStatus和after$csocStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csocStatus==true">
	${before$csocStatus}
	<dl class="csocStatus " major  ref="csocStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csocStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrderCluster$csocStatus!=null">${csOrderCluster$csocStatus}</s:if><s:else>
		 	<select class="narrow" id="csocStatus" name="csOrderCluster.csocStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csOrderCluster.csocStatus==0?"selected":""}>已预定</option>
				<option value="1" ${csOrderCluster.csocStatus==1?"selected":""}>使用中</option>
				<option value="3" ${csOrderCluster.csocStatus==3?"selected":""}>已取消</option>
				<option value="4" ${csOrderCluster.csocStatus==4?"selected":""}>已完成</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csocStatus">${csOrderCluster.csocStatus}</textarea>
		 		<span>
		 	
			 ${csOrderCluster.csocStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csocStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csOrderCluster.csocId}" name="csOrderCluster.csocId" id="csocId" />
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