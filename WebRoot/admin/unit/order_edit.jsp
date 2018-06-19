<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/order.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/order.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitOrder.csuoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csuoId,csuoHost,csuoUnitInfo,csuoUnitMember,csuoUnitChild,csuoCar,csuoType,csuoPerson,csuoUsername,csuoAlias,csuoMobile,csuoProfile,csuoIdcard,csuoStartTime,csuoFinishTime,csuoRetTime,csuoFreeHour,csuoFreeKm,csuoDayPrice,csuoHourPrice,csuoKmPrice,csuoPayNeed,csuoPayReal,csuoRemark,csuoMileage,csuoSettlement,csuoSettleState,csuoSettleTime,csuoStatement",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuoId,csuoHost,csuoUnitInfo,csuoUnitMember,csuoUnitChild,csuoCar,csuoType,csuoPerson,csuoUsername,csuoAlias,csuoMobile,csuoProfile,csuoIdcard,csuoStartTime,csuoFinishTime,csuoRetTime,csuoFreeHour,csuoFreeKm,csuoDayPrice,csuoHourPrice,csuoKmPrice,csuoPayNeed,csuoPayReal,csuoRemark,csuoMileage,csuoSettlement,csuoSettleState,csuoSettleTime,csuoStatement",
	</s:if>
	<s:elseif test="#request.CTRL.title=='续订'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csuoFinishTime,csuoRemark,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuoOrder,csuoUnitMember,csuoUnitChild,csuoCar,csuoType,csuoUsername,csuoMobile,csuoProfile,csuoStartTime,csuoFinishTime,csuoRemark,nothing",
	</s:elseif>
	<s:elseif test="#request.CTRL.title=='取消'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csuoRemark,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuoOrder,csuoUnitMember,csuoUnitChild,csuoCar,csuoUsername,csuoMobile,csuoStartTime,csuoFinishTime,csuoRemark,nothing",
	</s:elseif>
	<s:elseif test="#request.CTRL.title=='审核'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csuoRemark,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuoOrder,csuoUnitInfo,csuoUnitMember,csuoUnitChild,csuoCar,csuoNumber,csuoType,csuoUsername,csuoMobile,csuoProfile,csuoStartTime,csuoFinishTime,csuoRemark,nothing",
	</s:elseif>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csuoId,csuoHost,csuoOrder,csuoSpecial,csuoUnitInfo,csuoUnitGroup,csuoUnitMember,csuoUnitChild,csuoCar,csuoNumber,csuoType,csuoPerson,csuoUsername,csuoAlias,csuoMobile,csuoProfile,csuoIdcard,csuoStartTime,csuoFinishTime,csuoRetTime,csuoFreeHour,csuoFreeKm,csuoDayPrice,csuoHourPrice,csuoKmPrice,csuoPayNeed,csuoPayReal,csuoRemark,csuoMileage,csuoSettlement,csuoSettleState,csuoSettleTime,csuoStatement",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuoId,csuoHost,csuoOrder,csuoSpecial,csuoUnitInfo,csuoUnitGroup,csuoUnitMember,csuoUnitChild,csuoCar,csuoNumber,csuoType,csuoPerson,csuoUsername,csuoAlias,csuoMobile,csuoProfile,csuoIdcard,csuoStartTime,csuoFinishTime,csuoRetTime,csuoFreeHour,csuoFreeKm,csuoDayPrice,csuoHourPrice,csuoKmPrice,csuoPayNeed,csuoPayReal,csuoRemark,csuoMileage,csuoSettlement,csuoSettleState,csuoSettleTime,csuoStatement",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"企业订单编辑":CTRL.title}</title>
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
<s:if test="#request.CTRL.title=='续订'">
	<s:if test="csUnitOrder.$csuoOrder.csoStatus<2">
	<script>
	$(function(){
		$("#csuoFinishTime").attr("onclick","").click(function(){
			WdatePicker(getFinishDateParams());
		});
	});
	function getFinishDateParams(){
		var h1 = 1000*60*60;//1小时
		var m10 = 1000*60*10;//1小时
		var d30=1000*60*60*24*30;//30天
		var orderDate = $.dateFormat("<s:date name="csUnitOrder.csuoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>","yyyy-MM-dd HH:mm:ss");
		var minDate = new Date(orderDate.getTime()+m10);//最小10分钟
		var startDate = new Date(orderDate.getTime()+h1);//默认1小时
		var maxDate = new Date(minDate.getTime()+d30);
		return {
			el:"csuoFinishTime",
			minDate:$.formatDate("yyyy-MM-dd HH:mm:ss",minDate),
			maxDate:$.formatDate("yyyy-MM-dd HH:mm:ss",maxDate),
			alwaysUseStartDate:true,
			startDate:$.formatDate("yyyy-MM-dd HH:mm:00",startDate),
			dateFmt:'yyyy-MM-dd HH:mm:00',
			disabledDates:['....-..-.. ..\:.[1-9]\:..']
		};
	}
	</script>
	</s:if>
	<s:else>
		${lz:set("message","只有已预订或使用中的[系统]订单才能续订")}
		<lz:set name="jsonString">
			{
				title:"不能续订",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
</s:if>
<s:elseif test="#request.CTRL.title=='取消'">
	<s:if test="csUnitOrder.$csuoOrder.csoStatus==0">
	
	
	
	</s:if>
	<s:else>
		${lz:set("message","只有已预订的[系统]订单才能续订")}
		<lz:set name="jsonString">
			{
				title:"不能取消",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
</s:elseif>
<s:elseif test="#request.CTRL.title=='审核'">
	<s:if test="csUnitOrder.csuoState==1">
		
	
	</s:if>
	<s:else>
		${lz:set("message","只有等待审核的订单才能审核")}
		<lz:set name="jsonString">
			{
				title:"不能审核",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
</s:elseif>

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
	$("#orderForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csuoId==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoHost==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoOrder==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoSpecial==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoSpecial":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoUnitInfo==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoUnitInfo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoUnitGroup==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoUnitGroup":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoUnitMember==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoUnitMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoUnitChild==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoUnitChild":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoCar==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoNumber==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoNumber":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车牌号码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoType==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择订单类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoPerson==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoPerson":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoUsername==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoUsername":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "使用人姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoAlias==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoAlias":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "别名或标记最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoMobile==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoMobile":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "使用人手机最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoProfile==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoProfile":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "订单摘要最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoIdcard==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoIdcard":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "身份证号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoStartTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoStartTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "订单开始时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoFinishTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoFinishTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "订单结束时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoRetTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoRetTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoFreeHour==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoFreeHour":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoFreeKm==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoFreeKm":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "免费里程(代理)输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoDayPrice==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoDayPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "天租价格(代理)输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoHourPrice==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoHourPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "小时价格(代理)输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoKmPrice==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoKmPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "里程价格(代理)输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoPayNeed==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoPayNeed":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "应付金额(代理)输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoPayReal==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoPayReal":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "实付金额(代理)输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoRemark==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "订单备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoAddTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoAddTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoUpdateTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoMileage==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoMileage":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "里程数输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoSettlement==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoSettlement":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "结算费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoSettleState==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoSettleState":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoSettleTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoSettleTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoStatement==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoStatement":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoFrom==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoFrom":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "订单来源最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoCheckTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoCheckTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoChecker==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoChecker":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuoState==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoState":function(el){
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
<s:elseif test="#request.CTRL.title=='续订'">
	<form class="form editform" ref="CsUnitOrder" id="orderForm" name="orderForm" action="${empty CTRL.action?"order_reorder.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写企业订单相关信息，<span class="extrude">"*" 为必填选项。</span>
				</div>
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
				<input type="hidden" value="${csUnitOrderToken}" name="csUnitOrderToken" id="csUnitOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************系统订单字段的输入框代码*****************")}
	${lz:set("注释","before$csuoOrder和after$csuoOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoOrder}
	<dl class="csuoOrder " major  ref="csuoOrder" >
		<dt>系统订单:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoOrder!=null">${csUnitOrder$csuoOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csuoHost}" type="text" id="csuoOrder" name="csUnitOrder.csuoOrder" text="${get:CsOrder(csUnitOrder.csuoOrder).csoId}" value="${csUnitOrder.csuoOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csuoOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****系统订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoOrder">${csUnitOrder.csuoOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csUnitOrder.csuoOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoOrder}
	
	
	${lz:set("注释","*****************支付帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUnitMember和after$csuoUnitMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoUnitMember}
	<dl class="csuoUnitMember " major  ref="csuoUnitMember" >
		<dt>支付帐号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitOrder$csuoUnitMember!=null">${csUnitOrder$csuoUnitMember}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/user/member_query.do?size=-1&csmHost={csuoHost}" id="csuoUnitMember" name="csUnitOrder.csuoUnitMember">
		 				<option selected value="${csUnitOrder.csuoUnitMember}">
		 					${get:CsMember(csUnitOrder.csuoUnitMember).csmName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoUnitMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csuoUnitMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUnitMember">${csUnitOrder.csuoUnitMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csUnitOrder.csuoUnitMember}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoUnitMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUnitMember}
	
	
	${lz:set("注释","*****************使用帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUnitChild和after$csuoUnitChild变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoUnitChild}
	<dl class="csuoUnitChild " major  ref="csuoUnitChild" >
		<dt>使用帐号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoUnitChild!=null">${csUnitOrder$csuoUnitChild}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_room.do?value={param}&csmHost={csuoHost}" type="text" id="csuoUnitChild" name="csUnitOrder.csuoUnitChild" text="${get:CsMember(csUnitOrder.csuoUnitChild).csmName}" value="${csUnitOrder.csuoUnitChild}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoUnitChild').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csuoUnitChild').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUnitChild">${csUnitOrder.csuoUnitChild}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csUnitOrder.csuoUnitChild}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoUnitChild$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUnitChild}
	
	
	${lz:set("注释","*****************预订车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csuoCar和after$csuoCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoCar}
	<dl class="csuoCar " minor  ref="csuoCar" >
		<dt>预订车辆:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoCar!=null">${csUnitOrder$csuoCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csuoHost}" type="text" id="csuoCar" name="csUnitOrder.csuoCar" text="${get:CsCar(csUnitOrder.csuoCar).cscNumber}" value="${csUnitOrder.csuoCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#csuoCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预订车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoCar">${csUnitOrder.csuoCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csUnitOrder.csuoCar}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoCar}
	
	
	${lz:set("注释","*****************订单类型字段的输入框代码*****************")}
	${lz:set("注释","before$csuoType和after$csuoType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoType}
	<dl class="csuoType " major  ref="csuoType" >
		<dt>订单类型:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitOrder$csuoType!=null">${csUnitOrder$csuoType}</s:if><s:else>
		 	<select class="narrow" id="csuoType" name="csUnitOrder.csuoType">
		 		<option value="">请选择</option>
		 		 <option value="">无法自动构造</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业订单的订单类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoType">${csUnitOrder.csuoType}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoType}
	
	
	${lz:set("注释","*****************使用人姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUsername和after$csuoUsername变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoUsername}
	<dl class="csuoUsername " major  ref="csuoUsername" >
		<dt>使用人姓名:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoUsername!=null">${csUnitOrder$csuoUsername}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoUsername" id="csuoUsername"  value="${csUnitOrder.csuoUsername}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的使用人姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用人姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUsername">${csUnitOrder.csuoUsername}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoUsername$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUsername}
	
	
	${lz:set("注释","*****************使用人手机字段的输入框代码*****************")}
	${lz:set("注释","before$csuoMobile和after$csuoMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoMobile}
	<dl class="csuoMobile " minor  ref="csuoMobile" >
		<dt>使用人手机:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoMobile!=null">${csUnitOrder$csuoMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoMobile" id="csuoMobile"  value="${csUnitOrder.csuoMobile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的使用人手机</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用人手机字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoMobile">${csUnitOrder.csuoMobile}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoMobile}
	
	
	${lz:set("注释","*****************订单摘要字段的输入框代码*****************")}
	${lz:set("注释","before$csuoProfile和after$csuoProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoProfile}
	<dl class="csuoProfile " minor  ref="csuoProfile" >
		<dt>订单摘要:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoProfile!=null">${csUnitOrder$csuoProfile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoProfile" id="csuoProfile"  value="${csUnitOrder.csuoProfile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的订单摘要</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单摘要字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoProfile">${csUnitOrder.csuoProfile}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoProfile}
	
	
	${lz:set("注释","*****************订单开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoStartTime和after$csuoStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoStartTime}
	<dl class="csuoStartTime " major  ref="csuoStartTime" >
		<dt>订单开始时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoStartTime!=null">${csUnitOrder$csuoStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoStartTime" id="csuoStartTime"  value="<s:date name="csUnitOrder.csuoStartTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业订单的订单开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoStartTime">${csUnitOrder.csuoStartTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoStartTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoStartTime}
	
	
	${lz:set("注释","*****************订单结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoFinishTime和after$csuoFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoFinishTime}
	<dl class="csuoFinishTime " major  ref="csuoFinishTime" >
		<dt>订单结束时间:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoFinishTime!=null">${csUnitOrder$csuoFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoFinishTime" id="csuoFinishTime"  value="<s:date name="csUnitOrder.csuoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业订单的订单结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoFinishTime">${csUnitOrder.csuoFinishTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoFinishTime}
	
	
	${lz:set("注释","*****************订单备注字段的输入框代码*****************")}
	${lz:set("注释","before$csuoRemark和after$csuoRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoRemark}
	<dl class="csuoRemark " major  ref="csuoRemark" style="width:98%;">
		<dt>订单备注:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUnitOrder$csuoRemark!=null">${csUnitOrder$csuoRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csuoRemark" name="csUnitOrder.csuoRemark" rows="5">${csUnitOrder.csuoRemark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入企业订单的订单备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUnitOrder.csuoRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoRemark}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csUnitOrder.csuoId}" name="csUnitOrder.csuoId" id="csuoId" />
					<button title="保存数据,关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="完成">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						完成&nbsp;&nbsp;</button>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:elseif>
<s:elseif test="#request.CTRL.title=='取消'">
	<form class="form editform" ref="CsUnitOrder" id="orderForm" name="orderForm" action="${empty CTRL.action?"order_cancel.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写企业订单相关信息，<span class="extrude">"*" 为必填选项。</span>
				</div>
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
				<input type="hidden" value="${csUnitOrderToken}" name="csUnitOrderToken" id="csUnitOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************系统订单字段的输入框代码*****************")}
	${lz:set("注释","before$csuoOrder和after$csuoOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoOrder}
	<dl class="csuoOrder " major  ref="csuoOrder" >
		<dt>系统订单:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoOrder!=null">${csUnitOrder$csuoOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csuoHost}" type="text" id="csuoOrder" name="csUnitOrder.csuoOrder" text="${get:CsOrder(csUnitOrder.csuoOrder).csoId}" value="${csUnitOrder.csuoOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csuoOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****系统订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoOrder">${csUnitOrder.csuoOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csUnitOrder.csuoOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoOrder}
	
	
	${lz:set("注释","*****************支付帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUnitMember和after$csuoUnitMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoUnitMember}
	<dl class="csuoUnitMember " major  ref="csuoUnitMember" >
		<dt>支付帐号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitOrder$csuoUnitMember!=null">${csUnitOrder$csuoUnitMember}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/user/member_query.do?size=-1&csmHost={csuoHost}" id="csuoUnitMember" name="csUnitOrder.csuoUnitMember">
		 				<option selected value="${csUnitOrder.csuoUnitMember}">
		 					${get:CsMember(csUnitOrder.csuoUnitMember).csmName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoUnitMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csuoUnitMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUnitMember">${csUnitOrder.csuoUnitMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csUnitOrder.csuoUnitMember}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoUnitMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUnitMember}
	
	
	${lz:set("注释","*****************使用帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUnitChild和after$csuoUnitChild变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoUnitChild}
	<dl class="csuoUnitChild " major  ref="csuoUnitChild" >
		<dt>使用帐号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoUnitChild!=null">${csUnitOrder$csuoUnitChild}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_room.do?value={param}&csmHost={csuoHost}" type="text" id="csuoUnitChild" name="csUnitOrder.csuoUnitChild" text="${get:CsMember(csUnitOrder.csuoUnitChild).csmName}" value="${csUnitOrder.csuoUnitChild}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoUnitChild').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csuoUnitChild').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUnitChild">${csUnitOrder.csuoUnitChild}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csUnitOrder.csuoUnitChild}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoUnitChild$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUnitChild}
	
	
	${lz:set("注释","*****************预订车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csuoCar和after$csuoCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoCar}
	<dl class="csuoCar " minor  ref="csuoCar" >
		<dt>预订车辆:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoCar!=null">${csUnitOrder$csuoCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csuoHost}" type="text" id="csuoCar" name="csUnitOrder.csuoCar" text="${get:CsCar(csUnitOrder.csuoCar).cscNumber}" value="${csUnitOrder.csuoCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#csuoCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预订车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoCar">${csUnitOrder.csuoCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csUnitOrder.csuoCar}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoCar}
	
	
	${lz:set("注释","*****************使用人姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUsername和after$csuoUsername变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoUsername}
	<dl class="csuoUsername " major  ref="csuoUsername" >
		<dt>使用人姓名:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoUsername!=null">${csUnitOrder$csuoUsername}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoUsername" id="csuoUsername"  value="${csUnitOrder.csuoUsername}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的使用人姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用人姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUsername">${csUnitOrder.csuoUsername}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoUsername$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUsername}
	
	
	${lz:set("注释","*****************使用人手机字段的输入框代码*****************")}
	${lz:set("注释","before$csuoMobile和after$csuoMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoMobile}
	<dl class="csuoMobile " minor  ref="csuoMobile" >
		<dt>使用人手机:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoMobile!=null">${csUnitOrder$csuoMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoMobile" id="csuoMobile"  value="${csUnitOrder.csuoMobile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的使用人手机</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用人手机字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoMobile">${csUnitOrder.csuoMobile}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoMobile}
	
	
	${lz:set("注释","*****************订单开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoStartTime和after$csuoStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoStartTime}
	<dl class="csuoStartTime " major  ref="csuoStartTime" >
		<dt>订单开始时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoStartTime!=null">${csUnitOrder$csuoStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoStartTime" id="csuoStartTime"  value="<s:date name="csUnitOrder.csuoStartTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业订单的订单开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoStartTime">${csUnitOrder.csuoStartTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoStartTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoStartTime}
	
	
	${lz:set("注释","*****************订单结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoFinishTime和after$csuoFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoFinishTime}
	<dl class="csuoFinishTime " major  ref="csuoFinishTime" >
		<dt>订单结束时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoFinishTime!=null">${csUnitOrder$csuoFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoFinishTime" id="csuoFinishTime"  value="<s:date name="csUnitOrder.csuoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业订单的订单结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoFinishTime">${csUnitOrder.csuoFinishTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoFinishTime}
	
	
	${lz:set("注释","*****************订单备注字段的输入框代码*****************")}
	${lz:set("注释","before$csuoRemark和after$csuoRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoRemark}
	<dl class="csuoRemark " major  ref="csuoRemark" style="width:98%;">
		<dt>订单备注:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUnitOrder$csuoRemark!=null">${csUnitOrder$csuoRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csuoRemark" name="csUnitOrder.csuoRemark" rows="5">${csUnitOrder.csuoRemark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入企业订单的订单备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUnitOrder.csuoRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoRemark}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csUnitOrder.csuoId}" name="csUnitOrder.csuoId" id="csuoId" />
					<button title="保存数据,关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="完成">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						完成&nbsp;&nbsp;</button>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:elseif>
<s:elseif test="#request.CTRL.title=='审核'">
	<form class="form editform" ref="CsUnitOrder" id="orderForm" name="orderForm" action="${empty CTRL.action?"order_verify.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写企业订单相关信息，<span class="extrude">"*" 为必填选项。</span>
				</div>
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
				<input type="hidden" value="${csUnitOrderToken}" name="csUnitOrderToken" id="csUnitOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************系统订单字段的输入框代码*****************")}
	${lz:set("注释","before$csuoOrder和after$csuoOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoOrder}
	<dl class="csuoOrder " major  ref="csuoOrder" >
		<dt>系统订单:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoOrder!=null">${csUnitOrder$csuoOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csuoHost}" type="text" id="csuoOrder" name="csUnitOrder.csuoOrder" text="${get:CsOrder(csUnitOrder.csuoOrder).csoId}" value="${csUnitOrder.csuoOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csuoOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****系统订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoOrder">${csUnitOrder.csuoOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csUnitOrder.csuoOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoOrder}
	
	
	${lz:set("注释","*****************所属单位字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUnitInfo和after$csuoUnitInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoUnitInfo}
	<dl class="csuoUnitInfo " major  ref="csuoUnitInfo" >
		<dt>所属单位:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoUnitInfo!=null">${csUnitOrder$csuoUnitInfo}</s:if><s:else>
		 			<input title="请输入企业信息名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csuoHost}" type="text" id="csuoUnitInfo" name="csUnitOrder.csuoUnitInfo" text="${get:CsUnitInfo(csUnitOrder.csuoUnitInfo).csuiName}" value="${csUnitOrder.csuoUnitInfo}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoUnitInfo').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?key='+$('#csuoUnitInfo').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属单位字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUnitInfo">${csUnitOrder.csuoUnitInfo}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${csUnitOrder.csuoUnitInfo}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoUnitInfo$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUnitInfo}
	
	
	${lz:set("注释","*****************支付帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUnitMember和after$csuoUnitMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoUnitMember}
	<dl class="csuoUnitMember " major  ref="csuoUnitMember" >
		<dt>支付帐号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitOrder$csuoUnitMember!=null">${csUnitOrder$csuoUnitMember}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/user/member_query.do?size=-1&csmHost={csuoHost}" id="csuoUnitMember" name="csUnitOrder.csuoUnitMember">
		 				<option selected value="${csUnitOrder.csuoUnitMember}">
		 					${get:CsMember(csUnitOrder.csuoUnitMember).csmName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoUnitMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csuoUnitMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUnitMember">${csUnitOrder.csuoUnitMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csUnitOrder.csuoUnitMember}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoUnitMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUnitMember}
	
	
	${lz:set("注释","*****************使用帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUnitChild和after$csuoUnitChild变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoUnitChild}
	<dl class="csuoUnitChild " major  ref="csuoUnitChild" >
		<dt>使用帐号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoUnitChild!=null">${csUnitOrder$csuoUnitChild}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_room.do?value={param}&csmHost={csuoHost}" type="text" id="csuoUnitChild" name="csUnitOrder.csuoUnitChild" text="${get:CsMember(csUnitOrder.csuoUnitChild).csmName}" value="${csUnitOrder.csuoUnitChild}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoUnitChild').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csuoUnitChild').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUnitChild">${csUnitOrder.csuoUnitChild}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csUnitOrder.csuoUnitChild}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoUnitChild$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUnitChild}
	
	
	${lz:set("注释","*****************预订车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csuoCar和after$csuoCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoCar}
	<dl class="csuoCar " minor  ref="csuoCar" >
		<dt>预订车辆:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoCar!=null">${csUnitOrder$csuoCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csuoHost}" type="text" id="csuoCar" name="csUnitOrder.csuoCar" text="${get:CsCar(csUnitOrder.csuoCar).cscNumber}" value="${csUnitOrder.csuoCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#csuoCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预订车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoCar">${csUnitOrder.csuoCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csUnitOrder.csuoCar}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoCar}
	
	
	${lz:set("注释","*****************车牌号码字段的输入框代码*****************")}
	${lz:set("注释","before$csuoNumber和after$csuoNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoNumber}
	<dl class="csuoNumber " major  ref="csuoNumber" >
		<dt>车牌号码:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoNumber!=null">${csUnitOrder$csuoNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoNumber" id="csuoNumber"  value="${csUnitOrder.csuoNumber}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的车牌号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoNumber">${csUnitOrder.csuoNumber}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoNumber}
	
	
	${lz:set("注释","*****************订单类型字段的输入框代码*****************")}
	${lz:set("注释","before$csuoType和after$csuoType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoType}
	<dl class="csuoType " major  ref="csuoType" >
		<dt>订单类型:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitOrder$csuoType!=null">${csUnitOrder$csuoType}</s:if><s:else>
		 	<select class="narrow" id="csuoType" name="csUnitOrder.csuoType">
		 		<option value="">请选择</option>
		 		 <option value="">无法自动构造</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业订单的订单类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoType">${csUnitOrder.csuoType}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoType}
	
	
	${lz:set("注释","*****************使用人姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUsername和after$csuoUsername变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoUsername}
	<dl class="csuoUsername " major  ref="csuoUsername" >
		<dt>使用人姓名:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoUsername!=null">${csUnitOrder$csuoUsername}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoUsername" id="csuoUsername"  value="${csUnitOrder.csuoUsername}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的使用人姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用人姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUsername">${csUnitOrder.csuoUsername}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoUsername$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUsername}
	
	
	${lz:set("注释","*****************使用人手机字段的输入框代码*****************")}
	${lz:set("注释","before$csuoMobile和after$csuoMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoMobile}
	<dl class="csuoMobile " minor  ref="csuoMobile" >
		<dt>使用人手机:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoMobile!=null">${csUnitOrder$csuoMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoMobile" id="csuoMobile"  value="${csUnitOrder.csuoMobile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的使用人手机</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用人手机字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoMobile">${csUnitOrder.csuoMobile}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoMobile}
	
	
	${lz:set("注释","*****************订单摘要字段的输入框代码*****************")}
	${lz:set("注释","before$csuoProfile和after$csuoProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoProfile}
	<dl class="csuoProfile " minor  ref="csuoProfile" >
		<dt>订单摘要:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoProfile!=null">${csUnitOrder$csuoProfile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoProfile" id="csuoProfile"  value="${csUnitOrder.csuoProfile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的订单摘要</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单摘要字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoProfile">${csUnitOrder.csuoProfile}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoProfile}
	
	
	${lz:set("注释","*****************订单开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoStartTime和after$csuoStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoStartTime}
	<dl class="csuoStartTime " major  ref="csuoStartTime" >
		<dt>订单开始时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoStartTime!=null">${csUnitOrder$csuoStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoStartTime" id="csuoStartTime"  value="<s:date name="csUnitOrder.csuoStartTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业订单的订单开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoStartTime">${csUnitOrder.csuoStartTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoStartTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoStartTime}
	
	
	${lz:set("注释","*****************订单结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoFinishTime和after$csuoFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoFinishTime}
	<dl class="csuoFinishTime " major  ref="csuoFinishTime" >
		<dt>订单结束时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoFinishTime!=null">${csUnitOrder$csuoFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoFinishTime" id="csuoFinishTime"  value="<s:date name="csUnitOrder.csuoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业订单的订单结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoFinishTime">${csUnitOrder.csuoFinishTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoFinishTime}
	
	
	${lz:set("注释","*****************订单备注字段的输入框代码*****************")}
	${lz:set("注释","before$csuoRemark和after$csuoRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csuoRemark}
	<dl class="csuoRemark " major  ref="csuoRemark" style="width:98%;">
		<dt>订单备注:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUnitOrder$csuoRemark!=null">${csUnitOrder$csuoRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csuoRemark" name="csUnitOrder.csuoRemark" rows="5">${csUnitOrder.csuoRemark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入企业订单的订单备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUnitOrder.csuoRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoRemark}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csUnitOrder.csuoId}" name="csUnitOrder.csuoId" id="csuoId" />
						<button class="button" type="submit" name="submiter" value="审核通过">
						&nbsp;&nbsp;审核通过&nbsp;&nbsp;</button>
						<button class="button" type="submit" name="submiter" value="审核不通过">
						&nbsp;&nbsp;审核不通过&nbsp;&nbsp;</button>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:elseif>
<s:else>
	<form class="form editform" ref="CsUnitOrder" id="orderForm" name="orderForm" action="${empty CTRL.action?"order_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写企业订单相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csUnitOrderToken}" name="csUnitOrderToken" id="csUnitOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitOrder.csuoId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csuoId和after$csuoId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoId==true">
	${before$csuoId}
	<dl class="csuoId ${CTRL.e.csuoId?"hidden":""}" major  ref="csuoId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csuoId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUnitOrder$csuoId!=null">${csUnitOrder$csuoId}</s:if><s:else>
			<input type="hidden" value="${csUnitOrder.csuoId}" name="csUnitOrder.csuoId" id="csuoId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoId">${csUnitOrder.csuoId}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csuoHost和after$csuoHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoHost==true">
	${before$csuoHost}
	<dl class="csuoHost " major  ref="csuoHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csuoHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitOrder$csuoHost!=null">${csUnitOrder$csuoHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csuoHost" name="csUnitOrder.csuoHost">
		 				<option selected value="${csUnitOrder.csuoHost}">
		 					${get:SrvHost(csUnitOrder.csuoHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csuoHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoHost">${csUnitOrder.csuoHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csUnitOrder.csuoHost}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoHost}
	</s:if>
	
	${lz:set("注释","*****************系统订单字段的输入框代码*****************")}
	${lz:set("注释","before$csuoOrder和after$csuoOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoOrder==true">
	${before$csuoOrder}
	<dl class="csuoOrder " major  ref="csuoOrder" >
		<dt>系统订单:</dt>
		<s:if test="#request.CTRL.e.csuoOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoOrder!=null">${csUnitOrder$csuoOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csuoHost}" type="text" id="csuoOrder" name="csUnitOrder.csuoOrder" text="${get:CsOrder(csUnitOrder.csuoOrder).csoId}" value="${csUnitOrder.csuoOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csuoOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****系统订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoOrder">${csUnitOrder.csuoOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csUnitOrder.csuoOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoOrder}
	</s:if>
	
	${lz:set("注释","*****************专车订单字段的输入框代码*****************")}
	${lz:set("注释","before$csuoSpecial和after$csuoSpecial变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoSpecial==true">
	${before$csuoSpecial}
	<dl class="csuoSpecial " major  ref="csuoSpecial" >
		<dt>专车订单:</dt>
		<s:if test="#request.CTRL.e.csuoSpecial==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoSpecial!=null">${csUnitOrder$csuoSpecial}</s:if><s:else>
		 			<input title="请输入专车订单编号进行查询" class="combox narrow" action="${basePath}${proname}/unit/specialcar_query.do?value={param}&csscHost={csuoHost}" type="text" id="csuoSpecial" name="csUnitOrder.csuoSpecial" text="${get:CsSpecialCar(csUnitOrder.csuoSpecial).csscId}" value="${csUnitOrder.csuoSpecial}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoSpecial').val())==''){return;};window.href('${basePath}${proname}/unit/specialcar_details.do?key='+$('#csuoSpecial').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****专车订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoSpecial">${csUnitOrder.csuoSpecial}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/specialcar_details.do?key=${csUnitOrder.csuoSpecial}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoSpecial$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoSpecial}
	</s:if>
	
	${lz:set("注释","*****************所属单位字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUnitInfo和after$csuoUnitInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoUnitInfo==true">
	${before$csuoUnitInfo}
	<dl class="csuoUnitInfo " major  ref="csuoUnitInfo" >
		<dt>所属单位:</dt>
		<s:if test="#request.CTRL.e.csuoUnitInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoUnitInfo!=null">${csUnitOrder$csuoUnitInfo}</s:if><s:else>
		 			<input title="请输入企业信息名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csuoHost}" type="text" id="csuoUnitInfo" name="csUnitOrder.csuoUnitInfo" text="${get:CsUnitInfo(csUnitOrder.csuoUnitInfo).csuiName}" value="${csUnitOrder.csuoUnitInfo}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoUnitInfo').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?key='+$('#csuoUnitInfo').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属单位字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUnitInfo">${csUnitOrder.csuoUnitInfo}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${csUnitOrder.csuoUnitInfo}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoUnitInfo$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUnitInfo}
	</s:if>
	
	${lz:set("注释","*****************所属分组字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUnitGroup和after$csuoUnitGroup变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoUnitGroup==true">
	${before$csuoUnitGroup}
	<dl class="csuoUnitGroup " major  ref="csuoUnitGroup" >
		<dt>所属分组:</dt>
		<s:if test="#request.CTRL.e.csuoUnitGroup==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoUnitGroup!=null">${csUnitOrder$csuoUnitGroup}</s:if><s:else>
		 			<input title="请输入分组信息组名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/group_query.do?value={param}&csugHost={csuoHost}" type="text" id="csuoUnitGroup" name="csUnitOrder.csuoUnitGroup" text="${get:CsUnitGroup(csUnitOrder.csuoUnitGroup).csugName}" value="${csUnitOrder.csuoUnitGroup}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoUnitGroup').val())==''){return;};window.href('${basePath}${proname}/unit/group_details.do?key='+$('#csuoUnitGroup').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属分组字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUnitGroup">${csUnitOrder.csuoUnitGroup}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/group_details.do?key=${csUnitOrder.csuoUnitGroup}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoUnitGroup$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUnitGroup}
	</s:if>
	
	${lz:set("注释","*****************支付帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUnitMember和after$csuoUnitMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoUnitMember==true">
	${before$csuoUnitMember}
	<dl class="csuoUnitMember " major  ref="csuoUnitMember" >
		<dt>支付帐号:</dt>
		<s:if test="#request.CTRL.e.csuoUnitMember==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitOrder$csuoUnitMember!=null">${csUnitOrder$csuoUnitMember}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/user/member_query.do?size=-1&csmHost={csuoHost}" id="csuoUnitMember" name="csUnitOrder.csuoUnitMember">
		 				<option selected value="${csUnitOrder.csuoUnitMember}">
		 					${get:CsMember(csUnitOrder.csuoUnitMember).csmName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoUnitMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csuoUnitMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUnitMember">${csUnitOrder.csuoUnitMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csUnitOrder.csuoUnitMember}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoUnitMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUnitMember}
	</s:if>
	
	${lz:set("注释","*****************使用帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUnitChild和after$csuoUnitChild变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoUnitChild==true">
	${before$csuoUnitChild}
	<dl class="csuoUnitChild " major  ref="csuoUnitChild" >
		<dt>使用帐号:</dt>
		<s:if test="#request.CTRL.e.csuoUnitChild==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoUnitChild!=null">${csUnitOrder$csuoUnitChild}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_room.do?value={param}&unit={csuoUnitInfo}&csmHost={csuoHost}" type="text" id="csuoUnitChild" name="csUnitOrder.csuoUnitChild" text="${get:CsMember(csUnitOrder.csuoUnitChild).csmName}" value="${csUnitOrder.csuoUnitChild}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoUnitChild').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csuoUnitChild').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUnitChild">${csUnitOrder.csuoUnitChild}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csUnitOrder.csuoUnitChild}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoUnitChild$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUnitChild}
	</s:if>
	
	${lz:set("注释","*****************预订车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csuoCar和after$csuoCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoCar==true">
	${before$csuoCar}
	<dl class="csuoCar " minor  ref="csuoCar" >
		<dt>预订车辆:</dt>
		<s:if test="#request.CTRL.e.csuoCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoCar!=null">${csUnitOrder$csuoCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csuoHost}" type="text" id="csuoCar" name="csUnitOrder.csuoCar" text="${get:CsCar(csUnitOrder.csuoCar).cscNumber}" value="${csUnitOrder.csuoCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#csuoCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预订车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoCar">${csUnitOrder.csuoCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csUnitOrder.csuoCar}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoCar}
	</s:if>
	
	${lz:set("注释","*****************车牌号码字段的输入框代码*****************")}
	${lz:set("注释","before$csuoNumber和after$csuoNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoNumber==true">
	${before$csuoNumber}
	<dl class="csuoNumber " major  ref="csuoNumber" >
		<dt>车牌号码:</dt>
		<s:if test="#request.CTRL.e.csuoNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoNumber!=null">${csUnitOrder$csuoNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoNumber" id="csuoNumber"  value="${csUnitOrder.csuoNumber}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的车牌号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoNumber">${csUnitOrder.csuoNumber}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoNumber}
	</s:if>
	
	${lz:set("注释","*****************订单类型字段的输入框代码*****************")}
	${lz:set("注释","before$csuoType和after$csuoType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoType==true">
	${before$csuoType}
	<dl class="csuoType " major  ref="csuoType" >
		<dt>订单类型:</dt>
		<s:if test="#request.CTRL.e.csuoType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitOrder$csuoType!=null">${csUnitOrder$csuoType}</s:if><s:else>
		 	<select class="narrow" id="csuoType" name="csUnitOrder.csuoType">
		 		<option value="">请选择</option>
				<option value="0" ${csUnitOrder.csuoType==0?"selected":""}>代理订单</option>
				<option value="1" ${csUnitOrder.csuoType==1?"selected":""}>公务用车</option>
				<option value="2" ${csUnitOrder.csuoType==2?"selected":""}>紧急公务</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业订单的订单类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoType">${csUnitOrder.csuoType}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoType}
	</s:if>
	
	${lz:set("注释","*****************用车人员字段的输入框代码*****************")}
	${lz:set("注释","before$csuoPerson和after$csuoPerson变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoPerson==true">
	${before$csuoPerson}
	<dl class="csuoPerson " minor  ref="csuoPerson" >
		<dt>用车人员:</dt>
		<s:if test="#request.CTRL.e.csuoPerson==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoPerson!=null">${csUnitOrder$csuoPerson}</s:if><s:else>
		 			<input title="请输入用车人员姓名进行查询" class="combox narrow" action="${basePath}${proname}/unit/person_query.do?value={param}&csupHost={csuoHost}" type="text" id="csuoPerson" name="csUnitOrder.csuoPerson" text="${get:CsUnitPerson(csUnitOrder.csuoPerson).csupName}" value="${csUnitOrder.csuoPerson}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoPerson').val())==''){return;};window.href('${basePath}${proname}/unit/person_details.do?key='+$('#csuoPerson').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****用车人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoPerson">${csUnitOrder.csuoPerson}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/person_details.do?key=${csUnitOrder.csuoPerson}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoPerson$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoPerson}
	</s:if>
	
	${lz:set("注释","*****************使用人姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUsername和after$csuoUsername变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoUsername==true">
	${before$csuoUsername}
	<dl class="csuoUsername " major  ref="csuoUsername" >
		<dt>使用人姓名:</dt>
		<s:if test="#request.CTRL.e.csuoUsername==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoUsername!=null">${csUnitOrder$csuoUsername}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoUsername" id="csuoUsername"  value="${csUnitOrder.csuoUsername}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的使用人姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用人姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUsername">${csUnitOrder.csuoUsername}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoUsername$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUsername}
	</s:if>
	
	${lz:set("注释","*****************别名或标记字段的输入框代码*****************")}
	${lz:set("注释","before$csuoAlias和after$csuoAlias变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoAlias==true">
	${before$csuoAlias}
	<dl class="csuoAlias " minor  ref="csuoAlias" >
		<dt>别名或标记:</dt>
		<s:if test="#request.CTRL.e.csuoAlias==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoAlias!=null">${csUnitOrder$csuoAlias}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoAlias" id="csuoAlias"  value="${csUnitOrder.csuoAlias}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的别名或标记</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****别名或标记字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoAlias">${csUnitOrder.csuoAlias}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoAlias$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoAlias}
	</s:if>
	
	${lz:set("注释","*****************使用人手机字段的输入框代码*****************")}
	${lz:set("注释","before$csuoMobile和after$csuoMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoMobile==true">
	${before$csuoMobile}
	<dl class="csuoMobile " minor  ref="csuoMobile" >
		<dt>使用人手机:</dt>
		<s:if test="#request.CTRL.e.csuoMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoMobile!=null">${csUnitOrder$csuoMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoMobile" id="csuoMobile"  value="${csUnitOrder.csuoMobile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的使用人手机</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用人手机字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoMobile">${csUnitOrder.csuoMobile}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoMobile}
	</s:if>
	
	${lz:set("注释","*****************订单摘要字段的输入框代码*****************")}
	${lz:set("注释","before$csuoProfile和after$csuoProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoProfile==true">
	${before$csuoProfile}
	<dl class="csuoProfile " minor  ref="csuoProfile" >
		<dt>订单摘要:</dt>
		<s:if test="#request.CTRL.e.csuoProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoProfile!=null">${csUnitOrder$csuoProfile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoProfile" id="csuoProfile"  value="${csUnitOrder.csuoProfile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的订单摘要</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单摘要字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoProfile">${csUnitOrder.csuoProfile}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoProfile}
	</s:if>
	
	${lz:set("注释","*****************身份证号字段的输入框代码*****************")}
	${lz:set("注释","before$csuoIdcard和after$csuoIdcard变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoIdcard==true">
	${before$csuoIdcard}
	<dl class="csuoIdcard " minor  ref="csuoIdcard" >
		<dt>身份证号:</dt>
		<s:if test="#request.CTRL.e.csuoIdcard==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoIdcard!=null">${csUnitOrder$csuoIdcard}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoIdcard" id="csuoIdcard"  value="${csUnitOrder.csuoIdcard}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的身份证号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****身份证号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoIdcard">${csUnitOrder.csuoIdcard}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoIdcard$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoIdcard}
	</s:if>
	
	${lz:set("注释","*****************订单开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoStartTime和after$csuoStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoStartTime==true">
	${before$csuoStartTime}
	<dl class="csuoStartTime " major  ref="csuoStartTime" >
		<dt>订单开始时间:</dt>
		<s:if test="#request.CTRL.e.csuoStartTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoStartTime!=null">${csUnitOrder$csuoStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoStartTime" id="csuoStartTime"  value="<s:date name="csUnitOrder.csuoStartTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业订单的订单开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoStartTime">${csUnitOrder.csuoStartTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoStartTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoStartTime}
	</s:if>
	
	${lz:set("注释","*****************订单结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoFinishTime和after$csuoFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoFinishTime==true">
	${before$csuoFinishTime}
	<dl class="csuoFinishTime " major  ref="csuoFinishTime" >
		<dt>订单结束时间:</dt>
		<s:if test="#request.CTRL.e.csuoFinishTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoFinishTime!=null">${csUnitOrder$csuoFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoFinishTime" id="csuoFinishTime"  value="<s:date name="csUnitOrder.csuoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业订单的订单结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoFinishTime">${csUnitOrder.csuoFinishTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoFinishTime}
	</s:if>
	
	${lz:set("注释","*****************实际还车时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoRetTime和after$csuoRetTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoRetTime==true">
	${before$csuoRetTime}
	<dl class="csuoRetTime " minor  ref="csuoRetTime" >
		<dt>实际还车时间:</dt>
		<s:if test="#request.CTRL.e.csuoRetTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoRetTime!=null">${csUnitOrder$csuoRetTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoRetTime" id="csuoRetTime"  value="<s:date name="csUnitOrder.csuoRetTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择企业订单的实际还车时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际还车时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoRetTime">${csUnitOrder.csuoRetTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoRetTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoRetTime}
	</s:if>
	
	${lz:set("注释","*****************免费小时(代理)字段的输入框代码*****************")}
	${lz:set("注释","before$csuoFreeHour和after$csuoFreeHour变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoFreeHour==true">
	${before$csuoFreeHour}
	<dl class="csuoFreeHour " minor  ref="csuoFreeHour" >
		<dt>免费小时(代理):</dt>
		<s:if test="#request.CTRL.e.csuoFreeHour==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csUnitOrder$csuoFreeHour!=null">${csUnitOrder$csuoFreeHour}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoFreeHour" id="csuoFreeHour"  value="${csUnitOrder.csuoFreeHour}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的免费小时(代理)</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****免费小时(代理)字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoFreeHour">${csUnitOrder.csuoFreeHour}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoFreeHour$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoFreeHour}
	</s:if>
	
	${lz:set("注释","*****************免费里程(代理)字段的输入框代码*****************")}
	${lz:set("注释","before$csuoFreeKm和after$csuoFreeKm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoFreeKm==true">
	${before$csuoFreeKm}
	<dl class="csuoFreeKm " minor  ref="csuoFreeKm" >
		<dt>免费里程(代理):</dt>
		<s:if test="#request.CTRL.e.csuoFreeKm==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitOrder$csuoFreeKm!=null">${csUnitOrder$csuoFreeKm}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoFreeKm" id="csuoFreeKm"  value="${csUnitOrder.csuoFreeKm}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的免费里程(代理)</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****免费里程(代理)字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoFreeKm">${csUnitOrder.csuoFreeKm}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoFreeKm$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoFreeKm}
	</s:if>
	
	${lz:set("注释","*****************天租价格(代理)字段的输入框代码*****************")}
	${lz:set("注释","before$csuoDayPrice和after$csuoDayPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoDayPrice==true">
	${before$csuoDayPrice}
	<dl class="csuoDayPrice " minor  ref="csuoDayPrice" >
		<dt>天租价格(代理):</dt>
		<s:if test="#request.CTRL.e.csuoDayPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitOrder$csuoDayPrice!=null">${csUnitOrder$csuoDayPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoDayPrice" id="csuoDayPrice"  value="${csUnitOrder.csuoDayPrice}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入企业订单的天租价格(代理)</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****天租价格(代理)字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoDayPrice">${csUnitOrder.csuoDayPrice}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoDayPrice$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoDayPrice}
	</s:if>
	
	${lz:set("注释","*****************小时价格(代理)字段的输入框代码*****************")}
	${lz:set("注释","before$csuoHourPrice和after$csuoHourPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoHourPrice==true">
	${before$csuoHourPrice}
	<dl class="csuoHourPrice " minor  ref="csuoHourPrice" >
		<dt>小时价格(代理):</dt>
		<s:if test="#request.CTRL.e.csuoHourPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitOrder$csuoHourPrice!=null">${csUnitOrder$csuoHourPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoHourPrice" id="csuoHourPrice"  value="${csUnitOrder.csuoHourPrice}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入企业订单的小时价格(代理)</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****小时价格(代理)字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoHourPrice">${csUnitOrder.csuoHourPrice}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoHourPrice$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoHourPrice}
	</s:if>
	
	${lz:set("注释","*****************里程价格(代理)字段的输入框代码*****************")}
	${lz:set("注释","before$csuoKmPrice和after$csuoKmPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoKmPrice==true">
	${before$csuoKmPrice}
	<dl class="csuoKmPrice " minor  ref="csuoKmPrice" >
		<dt>里程价格(代理):</dt>
		<s:if test="#request.CTRL.e.csuoKmPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitOrder$csuoKmPrice!=null">${csUnitOrder$csuoKmPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoKmPrice" id="csuoKmPrice"  value="${csUnitOrder.csuoKmPrice}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的里程价格(代理)</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****里程价格(代理)字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoKmPrice">${csUnitOrder.csuoKmPrice}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoKmPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoKmPrice}
	</s:if>
	
	${lz:set("注释","*****************应付金额(代理)字段的输入框代码*****************")}
	${lz:set("注释","before$csuoPayNeed和after$csuoPayNeed变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoPayNeed==true">
	${before$csuoPayNeed}
	<dl class="csuoPayNeed " minor  ref="csuoPayNeed" >
		<dt>应付金额(代理):</dt>
		<s:if test="#request.CTRL.e.csuoPayNeed==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitOrder$csuoPayNeed!=null">${csUnitOrder$csuoPayNeed}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoPayNeed" id="csuoPayNeed"  value="${csUnitOrder.csuoPayNeed}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入企业订单的应付金额(代理)</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****应付金额(代理)字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoPayNeed">${csUnitOrder.csuoPayNeed}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoPayNeed$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoPayNeed}
	</s:if>
	
	${lz:set("注释","*****************实付金额(代理)字段的输入框代码*****************")}
	${lz:set("注释","before$csuoPayReal和after$csuoPayReal变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoPayReal==true">
	${before$csuoPayReal}
	<dl class="csuoPayReal " minor  ref="csuoPayReal" >
		<dt>实付金额(代理):</dt>
		<s:if test="#request.CTRL.e.csuoPayReal==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitOrder$csuoPayReal!=null">${csUnitOrder$csuoPayReal}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoPayReal" id="csuoPayReal"  value="${csUnitOrder.csuoPayReal}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入企业订单的实付金额(代理)</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实付金额(代理)字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoPayReal">${csUnitOrder.csuoPayReal}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoPayReal$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoPayReal}
	</s:if>
	
	${lz:set("注释","*****************订单备注字段的输入框代码*****************")}
	${lz:set("注释","before$csuoRemark和after$csuoRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoRemark==true">
	${before$csuoRemark}
	<dl class="csuoRemark " minor  ref="csuoRemark" style="width:98%;">
		<dt>订单备注:</dt>
		<s:if test="#request.CTRL.e.csuoRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUnitOrder$csuoRemark!=null">${csUnitOrder$csuoRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csuoRemark" name="csUnitOrder.csuoRemark" rows="5">${csUnitOrder.csuoRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的订单备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUnitOrder.csuoRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoRemark}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoAddTime和after$csuoAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoAddTime==true">
	${before$csuoAddTime}
	<dl class="csuoAddTime " minor  ref="csuoAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csuoAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoAddTime!=null">${csUnitOrder$csuoAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoAddTime" id="csuoAddTime"  value="<s:date name="csUnitOrder.csuoAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择企业订单的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoAddTime">${csUnitOrder.csuoAddTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoAddTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoUpdateTime和after$csuoUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoUpdateTime==true">
	${before$csuoUpdateTime}
	<dl class="csuoUpdateTime " minor  ref="csuoUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csuoUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoUpdateTime!=null">${csUnitOrder$csuoUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoUpdateTime" id="csuoUpdateTime"  value="<s:date name="csUnitOrder.csuoUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择企业订单的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoUpdateTime">${csUnitOrder.csuoUpdateTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************里程数字段的输入框代码*****************")}
	${lz:set("注释","before$csuoMileage和after$csuoMileage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoMileage==true">
	${before$csuoMileage}
	<dl class="csuoMileage " minor  ref="csuoMileage" >
		<dt>里&nbsp;&nbsp;程&nbsp;&nbsp;数:</dt>
		<s:if test="#request.CTRL.e.csuoMileage==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitOrder$csuoMileage!=null">${csUnitOrder$csuoMileage}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoMileage" id="csuoMileage"  value="${csUnitOrder.csuoMileage}"/>
	 	 </s:else>
	 	 公里
	 	 
	 	 <em>请输入企业订单的里程数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****里程数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoMileage">${csUnitOrder.csuoMileage}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoMileage$}
	 	 公里 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoMileage}
	</s:if>
	
	${lz:set("注释","*****************结算费用字段的输入框代码*****************")}
	${lz:set("注释","before$csuoSettlement和after$csuoSettlement变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoSettlement==true">
	${before$csuoSettlement}
	<dl class="csuoSettlement " minor  ref="csuoSettlement" >
		<dt>结算费用:</dt>
		<s:if test="#request.CTRL.e.csuoSettlement==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csUnitOrder$csuoSettlement!=null">${csUnitOrder$csuoSettlement}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoSettlement" id="csuoSettlement"  value="${csUnitOrder.csuoSettlement}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的结算费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结算费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoSettlement">${csUnitOrder.csuoSettlement}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoSettlement$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoSettlement}
	</s:if>
	
	${lz:set("注释","*****************结算状态字段的输入框代码*****************")}
	${lz:set("注释","before$csuoSettleState和after$csuoSettleState变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoSettleState==true">
	${before$csuoSettleState}
	<dl class="csuoSettleState " minor  ref="csuoSettleState" >
		<dt>结算状态:</dt>
		<s:if test="#request.CTRL.e.csuoSettleState==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitOrder$csuoSettleState!=null">${csUnitOrder$csuoSettleState}</s:if><s:else>
		 	<select class="narrow" id="csuoSettleState" name="csUnitOrder.csuoSettleState">
		 		<option value="">请选择</option>
				<option value="0" ${csUnitOrder.csuoSettleState==0?"selected":""}>未结算</option>
				<option value="1" ${csUnitOrder.csuoSettleState==1?"selected":""}>已结算</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择企业订单的结算状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结算状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoSettleState">${csUnitOrder.csuoSettleState}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoSettleState$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoSettleState}
	</s:if>
	
	${lz:set("注释","*****************结算时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoSettleTime和after$csuoSettleTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoSettleTime==true">
	${before$csuoSettleTime}
	<dl class="csuoSettleTime " minor  ref="csuoSettleTime" >
		<dt>结算时间:</dt>
		<s:if test="#request.CTRL.e.csuoSettleTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoSettleTime!=null">${csUnitOrder$csuoSettleTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoSettleTime" id="csuoSettleTime"  value="<s:date name="csUnitOrder.csuoSettleTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择企业订单的结算时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结算时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoSettleTime">${csUnitOrder.csuoSettleTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoSettleTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoSettleTime}
	</s:if>
	
	${lz:set("注释","*****************结账单字段的输入框代码*****************")}
	${lz:set("注释","before$csuoStatement和after$csuoStatement变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoStatement==true">
	${before$csuoStatement}
	<dl class="csuoStatement " minor  ref="csuoStatement" >
		<dt>结&nbsp;&nbsp;账&nbsp;&nbsp;单:</dt>
		<s:if test="#request.CTRL.e.csuoStatement==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitOrder$csuoStatement!=null">${csUnitOrder$csuoStatement}</s:if><s:else>
		 			<input title="请输入企业对账帐单月份进行查询" class="combox narrow" action="${basePath}${proname}/unit/bill_query.do?value={param}&csubHost={csuoHost}" type="text" id="csuoStatement" name="csUnitOrder.csuoStatement" text="${get:CsUnitBill(csUnitOrder.csuoStatement).csubMonth}" value="${csUnitOrder.csuoStatement}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoStatement').val())==''){return;};window.href('${basePath}${proname}/unit/bill_details.do?key='+$('#csuoStatement').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结账单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoStatement">${csUnitOrder.csuoStatement}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/bill_details.do?key=${csUnitOrder.csuoStatement}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitOrder.csuoStatement$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoStatement}
	</s:if>
	
	${lz:set("注释","*****************订单来源字段的输入框代码*****************")}
	${lz:set("注释","before$csuoFrom和after$csuoFrom变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoFrom==true">
	${before$csuoFrom}
	<dl class="csuoFrom " minor  ref="csuoFrom" >
		<dt>订单来源:</dt>
		<s:if test="#request.CTRL.e.csuoFrom==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitOrder$csuoFrom!=null">${csUnitOrder$csuoFrom}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoFrom" id="csuoFrom"  value="${csUnitOrder.csuoFrom}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业订单的订单来源</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单来源字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoFrom">${csUnitOrder.csuoFrom}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoFrom$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoFrom}
	</s:if>
	
	${lz:set("注释","*****************审核时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuoCheckTime和after$csuoCheckTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoCheckTime==true">
	${before$csuoCheckTime}
	<dl class="csuoCheckTime " minor  ref="csuoCheckTime" >
		<dt>审核时间:</dt>
		<s:if test="#request.CTRL.e.csuoCheckTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitOrder$csuoCheckTime!=null">${csUnitOrder$csuoCheckTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoCheckTime" id="csuoCheckTime"  value="<s:date name="csUnitOrder.csuoCheckTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择企业订单的审核时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****审核时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoCheckTime">${csUnitOrder.csuoCheckTime}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoCheckTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoCheckTime}
	</s:if>
	
	${lz:set("注释","*****************审核人字段的输入框代码*****************")}
	${lz:set("注释","before$csuoChecker和after$csuoChecker变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoChecker==true">
	${before$csuoChecker}
	<dl class="csuoChecker " minor  ref="csuoChecker" >
		<dt>审&nbsp;&nbsp;核&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csuoChecker==true">
		${lz:set("haveEditable",true)}
		<dd input="generic">
		<s:if test="#request.csUnitOrder$csuoChecker!=null">${csUnitOrder$csuoChecker}</s:if><s:else>
		 	<input title="请输入动态查询" class="combox narrow" generic="csuoCheckerGeneric" size="16" type="text" id="csuoChecker" name="csUnitOrder.csuoChecker" text="${csUnitOrder.csuoChecker$}" value="${csUnitOrder.csuoChecker}"/>
		 	${lz:set("models",get:models("SrvUser,CsUnitUser,CsUnitPerson"))}
		 	<select id="csuoCheckerGeneric" style="display:none;">
		 		<option value="">--选择模块--</option>
		 		<s:iterator value="#request.models" id="item" status="i">
		 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
		 		</s:iterator>
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****审核人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoChecker">${csUnitOrder.csuoChecker}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoChecker$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoChecker}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csuoState和after$csuoState变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuoState==true">
	${before$csuoState}
	<dl class="csuoState " major  ref="csuoState" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csuoState==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitOrder$csuoState!=null">${csUnitOrder$csuoState}</s:if><s:else>
		 	<select class="narrow" id="csuoState" name="csUnitOrder.csuoState">
		 		<option value="">请选择</option>
				<option value="1" ${csUnitOrder.csuoState==1?"selected":""}>等待审核</option>
				<option value="2" ${csUnitOrder.csuoState==2?"selected":""}>审核通过</option>
				<option value="3" ${csUnitOrder.csuoState==3?"selected":""}>审核未过</option>
				<option value="4" ${csUnitOrder.csuoState==4?"selected":""}>订单结束</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择企业订单的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuoState">${csUnitOrder.csuoState}</textarea>
		 		<span>
		 	
			 ${csUnitOrder.csuoState$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuoState}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUnitOrder.csuoId}" name="csUnitOrder.csuoId" id="csuoId" />
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