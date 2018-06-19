<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/violat.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/service/violat.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csViolat.csvId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csvId,csvHost,csvOrder,csvMember,csvCar,csvMoney,csvScore,csvHappenTime,csvDecipt,csvNoticeTime,csvCutoffTime,csvBranch,csvRemark,csvStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csvId,csvHost,csvOrder,csvMember,csvCar,csvMoney,csvScore,csvHappenTime,csvDecipt,csvNoticeTime,csvCutoffTime,csvBranch,csvRemark,csvStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csvId,csvHost,csvOrder,csvMember,csvCar,csvMoney,csvScore,csvHappenTime,csvDecipt,csvNoticeTime,csvCutoffTime,csvPrice,csvBranch,csvPostType,csvAddress,csvRemark,csvDealFlag,csvStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csvId,csvHost,csvOrder,csvMember,csvCar,csvMoney,csvScore,csvHappenTime,csvDecipt,csvNoticeTime,csvCutoffTime,csvPrice,csvBranch,csvPostType,csvAddress,csvRemark,csvDealFlag,csvStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车辆违章编辑":CTRL.title}</title>
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
<s:if test="#request.CTRL.title=='客服通知'">
	<lz:set name="jsonString">
		{
			title:"客服通知",
			action:"violat_notice.do",
			editable:false,
			visible:true,
			fields:{
				csvId:{visible:false}
				,csvNoticeTime:{editable:true,visible:true}
				,csvCutoffTime:{editable:true,visible:true}
				,csvRemark:{editable:true,visible:true}
				,csvType:{visible:false}
				,csvAddTime:{visible:false}
				,csvStatus:{visible:false}
			}
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
</s:if>
<s:if test="#request.CTRL.title=='委托处理'">
	<s:if test="csViolat.csvStatus!=0">
		${lz:set("message","只有[未交款，未处理]的违章才能委托处理")}
		<lz:set name="jsonString">
			{
				title:"委托处理",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:elseif test="csViolat.csvType!=0">
		${lz:set("message","该违章正在委托处理中，请跟进")}
		<lz:set name="jsonString">
			{
				title:"委托处理",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:elseif>
	<s:else>
		${lz:set("message","委托处理后系统将自动扣除会员帐户余额")}
		<lz:set name="jsonString">
			{
				title:"委托处理",
				action:"violat_trust.do",
				editable:false,
				visible:true,
				fields:{
					csvId:{visible:false}
					,csvPrice:{editable:true,visible:true}
					,csvRemark:{editable:true,visible:true}
					,csvUpdateTime:{visible:false}
					,csvType:{editable:true,visible:true}
					,csvAddTime:{visible:false}
					,csvStatus:{visible:false}
				},
				submits:[{name:"委托处理",value:"委托处理"}]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
</s:if>
<s:if test="#request.CTRL.title=='添加车辆违章'">
	<script>
		$(function(){
			$("#csvOrder").change(function(){
				$.getObject("${basePath}admin/service/order_info.do",{id:$(this).val()},function(obj){
					$("#csvMember").val(obj.member.id);
					$("#csvMember").prev().html(obj.member.name);					
					$("#csvCar").val(obj.car.id);
					$("#csvCar").prev().html(obj.car.name);
				});
			});
			$("#csvMember,#csvCar,#csvHappenTime").change(function(){
				$.getObject("${basePath}admin/service/violat_order.do",{
						member:$("#csvMember").val(),
						car:$("#csvCar").val(),
						time:$("#csvHappenTime").val()
					},function(obj){
						$("#csvOrder").val(obj.order.id);
						$("#csvOrder").prev().html(obj.order.name);
						$("#csvMember").val(obj.member.id);
						$("#csvMember").prev().html(obj.member.name);					
						$("#csvCar").val(obj.car.id);
						$("#csvCar").prev().html(obj.car.name);
					});
			});					
		});
	</script>
</s:if>
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
	$("#violatForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csvId==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvHost==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvOrder==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvMember==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvCar==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvMoney==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvMoney":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "违章罚款输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvScore==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvScore":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvHappenTime==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvHappenTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "发生时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvDecipt==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvDecipt":function(el){
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "违章描述最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvNoticeTime==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvNoticeTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvCutoffTime==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvCutoffTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvType==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择处理方式";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvPrice==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "委托金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvBranch==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvBranch":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvPostType==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvPostType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvAddress==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvAddress":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "送寄地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvRemark==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvRemark":function(el){
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvAccepter==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvAccepter":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvAdder==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvUpdateTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvAddTime==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvDealFlag==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvDealFlag":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csv22==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csv22":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvStatus==true">
		${lz:set("haveEditable",true)}
		,"csViolat.csvStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择业务状态";
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
	<form class="form editform" ref="CsViolat" id="violatForm" name="violatForm" action="${empty CTRL.action?"violat_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车辆违章相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csViolatToken}" name="csViolatToken" id="csViolatToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csViolat.csvId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csvId和after$csvId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvId==true">
	${before$csvId}
	<dl class="csvId ${CTRL.e.csvId?"hidden":""}" major  ref="csvId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csvId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csViolat$csvId!=null">${csViolat$csvId}</s:if><s:else>
			<input type="hidden" value="${csViolat.csvId}" name="csViolat.csvId" id="csvId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvId">${csViolat.csvId}</textarea>
		 		<span>
		 	
			 ${csViolat.csvId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csvHost和after$csvHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvHost==true">
	${before$csvHost}
	<dl class="csvHost " major  ref="csvHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csvHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csViolat$csvHost!=null">${csViolat$csvHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csvHost" name="csViolat.csvHost">
		 				<option selected value="${csViolat.csvHost}">
		 					${get:SrvHost(csViolat.csvHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csvHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csvHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvHost">${csViolat.csvHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csViolat.csvHost}',{ctrl:{editable:false,visible:true}})">
			 ${csViolat.csvHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvHost}
	</s:if>
	
	${lz:set("注释","*****************违章订单字段的输入框代码*****************")}
	${lz:set("注释","before$csvOrder和after$csvOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvOrder==true">
	${before$csvOrder}
	<dl class="csvOrder " major  ref="csvOrder" >
		<dt>违章订单:</dt>
		<s:if test="#request.CTRL.e.csvOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csViolat$csvOrder!=null">${csViolat$csvOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csvHost}" type="text" id="csvOrder" name="csViolat.csvOrder" text="${get:CsOrder(csViolat.csvOrder).csoId}" value="${csViolat.csvOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csvOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csvOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****违章订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvOrder">${csViolat.csvOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csViolat.csvOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csViolat.csvOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvOrder}
	</s:if>
	
	${lz:set("注释","*****************违章会员字段的输入框代码*****************")}
	${lz:set("注释","before$csvMember和after$csvMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvMember==true">
	${before$csvMember}
	<dl class="csvMember " major  ref="csvMember" >
		<dt>违章会员:</dt>
		<s:if test="#request.CTRL.e.csvMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csViolat$csvMember!=null">${csViolat$csvMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csvHost}" type="text" id="csvMember" name="csViolat.csvMember" text="${get:CsMember(csViolat.csvMember).csmName}" value="${csViolat.csvMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csvMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csvMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****违章会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvMember">${csViolat.csvMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csViolat.csvMember}',{ctrl:{editable:false,visible:true}})">
			 ${csViolat.csvMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvMember}
	</s:if>
	
	${lz:set("注释","*****************违章车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csvCar和after$csvCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvCar==true">
	${before$csvCar}
	<dl class="csvCar " major  ref="csvCar" >
		<dt>违章车辆:</dt>
		<s:if test="#request.CTRL.e.csvCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csViolat$csvCar!=null">${csViolat$csvCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csvHost}" type="text" id="csvCar" name="csViolat.csvCar" text="${get:CsCar(csViolat.csvCar).cscNumber}" value="${csViolat.csvCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csvCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#csvCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****违章车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvCar">${csViolat.csvCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csViolat.csvCar}',{ctrl:{editable:false,visible:true}})">
			 ${csViolat.csvCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvCar}
	</s:if>
	
	${lz:set("注释","*****************违章罚款字段的输入框代码*****************")}
	${lz:set("注释","before$csvMoney和after$csvMoney变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvMoney==true">
	${before$csvMoney}
	<dl class="csvMoney " major  ref="csvMoney" >
		<dt>违章罚款:</dt>
		<s:if test="#request.CTRL.e.csvMoney==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csViolat$csvMoney!=null">${csViolat$csvMoney}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csViolat.csvMoney" id="csvMoney"  value="${csViolat.csvMoney}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆违章的违章罚款</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****违章罚款字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvMoney">${csViolat.csvMoney}</textarea>
		 		<span>
		 	
			 ${csViolat.csvMoney$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvMoney}
	</s:if>
	
	${lz:set("注释","*****************违章扣分字段的输入框代码*****************")}
	${lz:set("注释","before$csvScore和after$csvScore变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvScore==true">
	${before$csvScore}
	<dl class="csvScore " major  ref="csvScore" >
		<dt>违章扣分:</dt>
		<s:if test="#request.CTRL.e.csvScore==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csViolat$csvScore!=null">${csViolat$csvScore}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csViolat.csvScore" id="csvScore"  value="${csViolat.csvScore}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆违章的违章扣分</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****违章扣分字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvScore">${csViolat.csvScore}</textarea>
		 		<span>
		 	
			 ${csViolat.csvScore$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvScore}
	</s:if>
	
	${lz:set("注释","*****************发生时间字段的输入框代码*****************")}
	${lz:set("注释","before$csvHappenTime和after$csvHappenTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvHappenTime==true">
	${before$csvHappenTime}
	<dl class="csvHappenTime " major  ref="csvHappenTime" >
		<dt>发生时间:</dt>
		<s:if test="#request.CTRL.e.csvHappenTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csViolat$csvHappenTime!=null">${csViolat$csvHappenTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csViolat.csvHappenTime" id="csvHappenTime"  value="<s:date name="csViolat.csvHappenTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆违章的发生时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发生时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvHappenTime">${csViolat.csvHappenTime}</textarea>
		 		<span>
		 	
			 ${csViolat.csvHappenTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvHappenTime}
	</s:if>
	
	${lz:set("注释","*****************违章描述字段的输入框代码*****************")}
	${lz:set("注释","before$csvDecipt和after$csvDecipt变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvDecipt==true">
	${before$csvDecipt}
	<dl class="csvDecipt " major  ref="csvDecipt" style="width:98%;">
		<dt>违章描述:</dt>
		<s:if test="#request.CTRL.e.csvDecipt==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csViolat$csvDecipt!=null">${csViolat$csvDecipt}</s:if><s:else>
		 	<textarea class="input wide"  id="csvDecipt" name="csViolat.csvDecipt" rows="5">${csViolat.csvDecipt}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆违章的违章描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****违章描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csViolat.csvDecipt$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvDecipt}
	</s:if>
	
	${lz:set("注释","*****************通知时间字段的输入框代码*****************")}
	${lz:set("注释","before$csvNoticeTime和after$csvNoticeTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvNoticeTime==true">
	${before$csvNoticeTime}
	<dl class="csvNoticeTime " major  ref="csvNoticeTime" >
		<dt>通知时间:</dt>
		<s:if test="#request.CTRL.e.csvNoticeTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csViolat$csvNoticeTime!=null">${csViolat$csvNoticeTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csViolat.csvNoticeTime" id="csvNoticeTime"  value="<s:date name="csViolat.csvNoticeTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆违章的通知时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****通知时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvNoticeTime">${csViolat.csvNoticeTime}</textarea>
		 		<span>
		 	
			 ${csViolat.csvNoticeTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvNoticeTime}
	</s:if>
	
	${lz:set("注释","*****************截止时间字段的输入框代码*****************")}
	${lz:set("注释","before$csvCutoffTime和after$csvCutoffTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvCutoffTime==true">
	${before$csvCutoffTime}
	<dl class="csvCutoffTime " major  ref="csvCutoffTime" >
		<dt>截止时间:</dt>
		<s:if test="#request.CTRL.e.csvCutoffTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csViolat$csvCutoffTime!=null">${csViolat$csvCutoffTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csViolat.csvCutoffTime" id="csvCutoffTime"  value="<s:date name="csViolat.csvCutoffTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆违章的截止时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****截止时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvCutoffTime">${csViolat.csvCutoffTime}</textarea>
		 		<span>
		 	
			 ${csViolat.csvCutoffTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvCutoffTime}
	</s:if>
	
	${lz:set("注释","*****************处理方式字段的输入框代码*****************")}
	${lz:set("注释","before$csvType和after$csvType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvType==true">
	${before$csvType}
	<dl class="csvType " major  ref="csvType" >
		<dt>处理方式:</dt>
		<s:if test="#request.CTRL.e.csvType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csViolat$csvType!=null">${csViolat$csvType}</s:if><s:else>
		 	<select class="narrow" id="csvType" name="csViolat.csvType">
		 		<option value="">请选择</option>
				<option value="0" ${csViolat.csvType==0?"selected":""}>自己处理</option>
				<option value="1" ${csViolat.csvType==1?"selected":""}>协商委托</option>
				<option value="2" ${csViolat.csvType==2?"selected":""}>强制委托</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆违章的处理方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvType">${csViolat.csvType}</textarea>
		 		<span>
		 	
			 ${csViolat.csvType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvType}
	</s:if>
	
	${lz:set("注释","*****************委托金额字段的输入框代码*****************")}
	${lz:set("注释","before$csvPrice和after$csvPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvPrice==true">
	${before$csvPrice}
	<dl class="csvPrice " major  ref="csvPrice" >
		<dt>委托金额:</dt>
		<s:if test="#request.CTRL.e.csvPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csViolat$csvPrice!=null">${csViolat$csvPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csViolat.csvPrice" id="csvPrice"  value="${csViolat.csvPrice}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车辆违章的委托金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****委托金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvPrice">${csViolat.csvPrice}</textarea>
		 		<span>
		 	
			 ${csViolat.csvPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvPrice}
	</s:if>
	
	${lz:set("注释","*****************处理部门字段的输入框代码*****************")}
	${lz:set("注释","before$csvBranch和after$csvBranch变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvBranch==true">
	${before$csvBranch}
	<dl class="csvBranch " major  ref="csvBranch" >
		<dt>处理部门:</dt>
		<s:if test="#request.CTRL.e.csvBranch==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csViolat$csvBranch!=null">${csViolat$csvBranch}</s:if><s:else>
		 	<select class="narrow" id="csvBranch" name="csViolat.csvBranch">
		 		<option value="">请选择</option>
				<option value="0" ${csViolat.csvBranch==0?"selected":""}>交警</option>
				<option value="1" ${csViolat.csvBranch==1?"selected":""}>城管</option>
				<option value="2" ${csViolat.csvBranch==2?"selected":""}>铁路</option>
				<option value="3" ${csViolat.csvBranch==3?"selected":""}>高速</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆违章的处理部门</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理部门字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvBranch">${csViolat.csvBranch}</textarea>
		 		<span>
		 	
			 ${csViolat.csvBranch$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvBranch}
	</s:if>
	
	${lz:set("注释","*****************寄送方式字段的输入框代码*****************")}
	${lz:set("注释","before$csvPostType和after$csvPostType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvPostType==true">
	${before$csvPostType}
	<dl class="csvPostType " minor  ref="csvPostType" >
		<dt>寄送方式:</dt>
		<s:if test="#request.CTRL.e.csvPostType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csViolat$csvPostType!=null">${csViolat$csvPostType}</s:if><s:else>
		 	<select class="narrow" id="csvPostType" name="csViolat.csvPostType">
		 		<option value="">请选择</option>
				<option value="0" ${csViolat.csvPostType==0?"selected":""}>邮寄</option>
				<option value="1" ${csViolat.csvPostType==1?"selected":""}>人工</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆违章的寄送方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****寄送方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvPostType">${csViolat.csvPostType}</textarea>
		 		<span>
		 	
			 ${csViolat.csvPostType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvPostType}
	</s:if>
	
	${lz:set("注释","*****************送寄地址字段的输入框代码*****************")}
	${lz:set("注释","before$csvAddress和after$csvAddress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvAddress==true">
	${before$csvAddress}
	<dl class="csvAddress " minor  ref="csvAddress" style="width:98%;">
		<dt>送寄地址:</dt>
		<s:if test="#request.CTRL.e.csvAddress==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csViolat$csvAddress!=null">${csViolat$csvAddress}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csViolat.csvAddress" id="csvAddress"  value="${csViolat.csvAddress}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆违章的送寄地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****送寄地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csvAddress">${csViolat.csvAddress}</textarea>
		 		<span>
		 	
			 ${csViolat.csvAddress$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvAddress}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csvRemark和after$csvRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvRemark==true">
	${before$csvRemark}
	<dl class="csvRemark " minor  ref="csvRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csvRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csViolat$csvRemark!=null">${csViolat$csvRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csvRemark" name="csViolat.csvRemark" rows="5">${csViolat.csvRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆违章的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csViolat.csvRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvRemark}
	</s:if>
	
	${lz:set("注释","*****************受理人员字段的输入框代码*****************")}
	${lz:set("注释","before$csvAccepter和after$csvAccepter变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvAccepter==true">
	${before$csvAccepter}
	<dl class="csvAccepter " minor  ref="csvAccepter" >
		<dt>受理人员:</dt>
		<s:if test="#request.CTRL.e.csvAccepter==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csViolat$csvAccepter!=null">${csViolat$csvAccepter}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csvAccepter" name="csViolat.csvAccepter">
		 				<option selected value="${csViolat.csvAccepter}">
		 					${get:SrvUser(csViolat.csvAccepter).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csvAccepter').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csvAccepter').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****受理人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvAccepter">${csViolat.csvAccepter}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csViolat.csvAccepter}',{ctrl:{editable:false,visible:true}})">
			 ${csViolat.csvAccepter$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvAccepter}
	</s:if>
	
	${lz:set("注释","*****************添加人员字段的输入框代码*****************")}
	${lz:set("注释","before$csvAdder和after$csvAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvAdder==true">
	${before$csvAdder}
	<dl class="csvAdder " minor  ref="csvAdder" >
		<dt>添加人员:</dt>
		<s:if test="#request.CTRL.e.csvAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csViolat$csvAdder!=null">${csViolat$csvAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csvAdder" name="csViolat.csvAdder">
		 				<option selected value="${csViolat.csvAdder}">
		 					${get:SrvUser(csViolat.csvAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csvAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csvAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvAdder">${csViolat.csvAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csViolat.csvAdder}',{ctrl:{editable:false,visible:true}})">
			 ${csViolat.csvAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvAdder}
	</s:if>
	
	${lz:set("注释","*****************处理时间字段的输入框代码*****************")}
	${lz:set("注释","before$csvUpdateTime和after$csvUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvUpdateTime==true">
	${before$csvUpdateTime}
	<dl class="csvUpdateTime " minor  ref="csvUpdateTime" >
		<dt>处理时间:</dt>
		<s:if test="#request.CTRL.e.csvUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csViolat$csvUpdateTime!=null">${csViolat$csvUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csViolat.csvUpdateTime" id="csvUpdateTime"  value="<s:date name="csViolat.csvUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆违章的处理时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvUpdateTime">${csViolat.csvUpdateTime}</textarea>
		 		<span>
		 	
			 ${csViolat.csvUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csvAddTime和after$csvAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvAddTime==true">
	${before$csvAddTime}
	<dl class="csvAddTime " major  ref="csvAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csvAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csViolat$csvAddTime!=null">${csViolat$csvAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csViolat.csvAddTime" id="csvAddTime"  value="<s:date name="csViolat.csvAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆违章的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvAddTime">${csViolat.csvAddTime}</textarea>
		 		<span>
		 	
			 ${csViolat.csvAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvAddTime}
	</s:if>
	
	${lz:set("注释","*****************受理标记字段的输入框代码*****************")}
	${lz:set("注释","before$csvDealFlag和after$csvDealFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvDealFlag==true">
	${before$csvDealFlag}
	<dl class="csvDealFlag " major  ref="csvDealFlag" >
		<dt>受理标记:</dt>
		<s:if test="#request.CTRL.e.csvDealFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csViolat$csvDealFlag!=null">${csViolat$csvDealFlag}</s:if><s:else>
		 	<select class="narrow" id="csvDealFlag" name="csViolat.csvDealFlag">
		 		<option value="">请选择</option>
				<option value="0" ${csViolat.csvDealFlag==0?"selected":""}>正常处理</option>
				<option value="1" ${csViolat.csvDealFlag==1?"selected":""}>会员不配合</option>
				<option value="2" ${csViolat.csvDealFlag==2?"selected":""}>交警不配合</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆违章的受理标记</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****受理标记字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvDealFlag">${csViolat.csvDealFlag}</textarea>
		 		<span>
		 	
			 ${csViolat.csvDealFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvDealFlag}
	</s:if>
	
	${lz:set("注释","*****************查询次数字段的输入框代码*****************")}
	${lz:set("注释","before$csv22和after$csv22变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csv22==true">
	${before$csv22}
	<dl class="csv22 " minor  ref="csv22" >
		<dt>查询次数:</dt>
		<s:if test="#request.CTRL.e.csv22==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csViolat$csv22!=null">${csViolat$csv22}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csViolat.csv22" id="csv22"  value="${csViolat.csv22}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆违章的查询次数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****查询次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csv22">${csViolat.csv22}</textarea>
		 		<span>
		 	
			 ${csViolat.csv22$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csv22}
	</s:if>
	
	${lz:set("注释","*****************业务状态字段的输入框代码*****************")}
	${lz:set("注释","before$csvStatus和after$csvStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvStatus==true">
	${before$csvStatus}
	<dl class="csvStatus " major  ref="csvStatus" >
		<dt>业务状态:</dt>
		<s:if test="#request.CTRL.e.csvStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csViolat$csvStatus!=null">${csViolat$csvStatus}</s:if><s:else>
		 	<select class="narrow" id="csvStatus" name="csViolat.csvStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csViolat.csvStatus==0?"selected":""}>未交款，未处理</option>
				<option value="1" ${csViolat.csvStatus==1?"selected":""}>未交款，已处理</option>
				<option value="2" ${csViolat.csvStatus==2?"selected":""}>已交款，已处理</option>
				<option value="3" ${csViolat.csvStatus==3?"selected":""}>等待查询</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆违章的业务状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****业务状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvStatus">${csViolat.csvStatus}</textarea>
		 		<span>
		 	
			 ${csViolat.csvStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csViolat.csvId}" name="csViolat.csvId" id="csvId" />
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