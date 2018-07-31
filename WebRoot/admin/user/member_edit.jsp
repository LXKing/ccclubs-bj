<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/member.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/member.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csMember.csmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csmId,csmHost,csmUsername,csmGroup,csmRebate,csmNotRevenue,csmOrders,csmAlipays,csmPacks,csmFreehours,csmCoins,csmRecords,csmBills,csmIntegrals,csmGrows,csmUseRecord,csmRefounds,csmViolats,csmTroubles,csmInvoices,csmAddresses,csmMemos,csmWeixinFlag,csmAlipayFlag,csmHeader,csmEmail,csmMobile,csmEvcard,csmExpress,csmTemp,csmInfo,csmOutlets,csmRemark,csmVMobile,csmVEmail,csmVReal,csmVDrive,csmVWork,csmVOffline",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmId,csmHost,csmUsername,csmGroup,csmRebate,csmNotRevenue,csmOrders,csmAlipays,csmPacks,csmFreehours,csmCoins,csmRecords,csmBills,csmIntegrals,csmGrows,csmUseRecord,csmRefounds,csmViolats,csmTroubles,csmInvoices,csmAddresses,csmMemos,csmWeixinFlag,csmAlipayFlag,csmHeader,csmEmail,csmMobile,csmEvcard,csmExpress,csmTemp,csmInfo,csmOutlets,csmRemark,csmVMobile,csmVEmail,csmVReal,csmVDrive,csmVWork,csmVOffline",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csmId,csmGroup,csmRebate,csmNotRevenue,csmOrders,csmAlipays,csmPacks,csmFreehours,csmCoins,csmRecords,csmBills,csmIntegrals,csmGrows,csmUseRecord,csmRefounds,csmViolats,csmTroubles,csmInvoices,csmAddresses,csmMemos,csmWeixinFlag,csmAlipayFlag,csmHeader,csmEmail,csmEvcard,csmExpress,csmTemp,csmName,csmInfo,csmOutlets,csmIsVip,csmVipStart,csmVipEnd,csmMarket,csmTag,csmMark,csmVisitFlag,csmRemark,csmVMobile,csmVEmail,csmVReal,csmVDrive,csmVWork,csmVOffline,csmLockReason,csmVOfflineCode,csmStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmId,csmHost,csmUsername,csmGroup,csmRebate,csmNotRevenue,csmOrders,csmAlipays,csmPacks,csmFreehours,csmCoins,csmRecords,csmBills,csmIntegrals,csmGrows,csmUseRecord,csmRefounds,csmViolats,csmTroubles,csmInvoices,csmAddresses,csmMemos,csmWeixinFlag,csmAlipayFlag,csmHeader,csmEmail,csmMobile,csmEvcard,csmExpress,csmTemp,csmName,csmInfo,csmOutlets,csmIsVip,csmVipStart,csmVipEnd,csmMarket,csmTag,csmMark,csmVisitFlag,csmRemark,csmVMobile,csmVEmail,csmVReal,csmVDrive,csmVWork,csmVOffline,csmLockReason,csmVOfflineCode,csmStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"会员帐号编辑":CTRL.title}</title>
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
<lz:set name="logHtml">
	<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="orders" style="width:98%;">
		<dt>操作日志:</dt>
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="logs" name="logs" frameborder='0' src="" url="${basePath}admin/systematic/log.do?canQuery=false&fields=cslName,cslUser,cslModel,cslType,cslDetail,cslAddTime&cslRelate=${csMember.csmId}&cslClass=CsMember&ctrl={mutual:'dialog',canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#logs').attr('src',$('#logs').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>操作日志</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>
		</dd>
	</dl>
</lz:set>
<script>
window.$on("readyStart",function(){
	$("${lz:js(logHtml)}").insertAfter("dl[ref='csmMemos']");
});
</script>
<s:if test="#request.CTRL.title=='修改会员帐号'">
	<lz:set name="jsonString">
		{
			title:"修改会员帐号",
			editable:true,
			visible:true,
			fields:{
				csmId:{editable:false,visible:true}
				,csmVReal:{editable:false,visible:false}
				,csmVDrive:{editable:false,visible:false}
				,csmVWork:{editable:false,visible:false}
				,csmVOffline:{editable:false,visible:false}
				,unitInfo:{editable:false,visible:false}
				,unitGroup:{editable:false,visible:false}
				,payMember:{editable:false,visible:false}
				,csmVOfflineCode:{editable:false,visible:false}
				,vstatus:{editable:false,visible:true}
			}
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
</s:if>
<s:if test="#request.CTRL.title=='改号'">
	<lz:set name="jsonString">
		{
			title:"改号",
			editable:false,
			visible:false,
			fields:{
				csmId:{editable:false,visible:true}
				,csmHost:{editable:true,visible:true}
				,csmName:{editable:true,visible:true}
				,csmMobile:{editable:true,visible:true}
			}
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
</s:if>
<s:if test="#request.CTRL.title=='审核'">
	<s:if test="csMember.csmVReal==1 && csMember.csmVDrive==1 && csMember.csmVWork==1 && csMember.csmVOffline==1 ">
		${lz:set("message","温馨提示，该会员不在审核状态，不需审核")}
	</s:if>
	<lz:set name="jsonString">
		{
			title:"审核",
			action:"member_verify.do",
			editable:false,
			visible:false,
			fields:{
				csmEvcard:{editable:true,visible:true}
				,csmMobile:{editable:false,visible:true}
				,csmInfo:{editable:false,visible:true}
				,csmVReal:{editable:true,visible:true}
				,csmVDrive:{editable:true,visible:true}
				,csmVWork:{editable:true,visible:true}
				,csmVOffline:{editable:true,visible:true}
				,csmRemark:{editable:true,visible:true}
				,payMember:{editable:true,visible:true}
				,unitInfo:{editable:true,visible:true}
				,unitGroup:{editable:true,visible:true}
				,csmVOfflineCode:{editable:true,visible:true}
			},
			submits:[{name:"提交审核",value:"提交审核"}]
		}
	</lz:set>
	<lz:set name="insertHtml">
		<dl>
			<dt>身份证号:</dt>
			<dd>
				<div class="state-input narrow">&nbsp;${csMember.$csmInfo.csmiCertifyNum$}</div>
			</dd>
		</dl>
		<dl style="width:98%;">
			<dt>身份证人像面:</dt>
			<dd input="image">
				<div class="state-input wide">
					${lz:set("certifyImageLen",(lz:size(csMember.$csmInfo.csmiCertifyImage)))}
					<s:if test="#request.certifyImageLen1>0">
						<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=300}else{this.height=300}" src="${csMember.$csmInfo.csmiCertifyImage}"/>
					</s:if>
					<s:else><center><font color="red"><h4>未上传驾证照片，建议审核不通过</h4></font></center></s:else>
				</div>
			</dd>
		</dl>
		<dl style="width:98%;">
			<dt>身份证国徽面:</dt>
			<dd input="image">
				<div class="state-input wide">
					${lz:set("onCertifyImageLen",(lz:size(csMember.$csmInfo.csmiOnCertifyImage)))}
					<s:if test="#request.onCertifyImageLen>0">
						<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=300}else{this.height=300}" src="${csMember.$csmInfo.csmiOnCertifyImage}"/>
					</s:if>
					<s:else><center><font color="red"><h4>未上传驾证照片，建议审核不通过</h4></font></center></s:else>
				</div>
			</dd>
		</dl>
		<dl>
			<dt>驾驶证件号:</dt>
			<dd>
				<div class="state-input narrow">&nbsp;${csMember.$csmInfo.csmiDriverNum$}</div>
			</dd>
		</dl>
		<dl style="width:98%;">
			<dt>驾证图片:</dt>
			<dd input="image">
				<div class="state-input wide">
					${lz:set("driverImageLen",(lz:size(csMember.$csmInfo.csmiDriverImage)))}
					<s:if test="#request.driverImageLen>0">
						<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=300}else{this.height=300}" src="${csMember.$csmInfo.csmiDriverImage}"/>
					</s:if>
					<s:else><center><font color="red"><h4>未上传驾证照片，建议审核不通过</h4></font></center></s:else>
				</div>
			</dd>
		</dl>
		<dl style="width:98%;">
			<dt>工作证图片:</dt>
			<dd input="image">
				<div class="state-input wide">
					${lz:set("employmentImageLen",(lz:size(csMember.$csmInfo.csmiProofOfEmployment)))}
					<s:if test="#request.employmentImageLen>0">
						<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=300}else{this.height=300}" src="${csMember.$csmInfo.csmiProofOfEmployment}"/>
					</s:if>
					<s:else><center><font color="red"><h4>未上传驾证照片，建议审核不通过</h4></font></center></s:else>
				</div>
			</dd>
		</dl>
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
	<script>
	$(function(){
		
		$("${lz:js(insertHtml)}").insertAfter("dl[ref='csmInfo']");
		/*$("#memberForm").submit(function(){
			if($("#csmVReal").val()=="1" && $("#csmVDrive").val()=="1" && $("#csmEvcard").val()==""){
				Alert("认证通过的会员需要绑定会员卡");
				return false;
			}
		});*/
		$("<a href='javascript:void(0);'>发送短信</a>").appendTo(".buttons").click(function(){
			var list=$.getObject("${basePath}admin/user/member_query.do?object=true",{csmId:${csMember.csmId}});
			var member=null;
			if(list && list.length)
				member=list[0].object;
			if(!member || $.trim(member.csmMobile)==""){
				$.tips("该会员没有手机号码，不能发送短信");
				return;
			}
			var url = "${basePath}admin/systematic/smsmt_edit.do";
			var params = {
				entrypoint:"${entrypoint}",
				ctrl:{
					fields:{
						cssmMobile:{
							defaultValue:member.csmMobile,
							editable:true
						}			
					}
				}
			};
			top.$.open(url,params);
		});
		
		if($("#unitInfo").length>0){
			$("#unitInfo").change(function(){
				$.ajax({
					url:"${basePath}${namespace}info_getInfoPayMember.do".replace("user","unit"),
					data:{
						"csuiId":$("#unitInfo").val()
					},
					datetype:"json",
					success:function(data){
						var data = $.parseJSON(data);
						var options = "<option value=\"\">请选择</option>";
						for(var i=0;i<data.length;i++){
							options+="<option value=\""+data[i].csmId+"\" > "+data[i].csmName+"</option>";
						}
						$("#payMember").html(options);
						$("#payMember").find("option[value=\""+${request.payMember}+"\"]").attr("selected",true);
					}	
			    });
			});
		}
		
	});
	
	
	
	</script>
</s:if>
<!------------------------LAZY3Q_JSP_BODY------------------------>



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";

function csmStatus_select(){
	var csmStatusVal = $("#csmStatus").val();
	if(csmStatusVal != 0){
		$("#csmLockReason_div").css("display","none");
	}else{
		$("#csmLockReason_div").css("display","");
	}
}
function csmVOffline_select(){
	var csmVOfflineVal = $("#csmVOffline").val();
	if(csmVOfflineVal != 1){
		$("#csmVOfflineCode_div").css("display","none");
	}else{
		$("#csmVOfflineCode_div").css("display","");
	}
}
$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#memberForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csmId==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmHost==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmUsername==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmUsername":function(el){
			if(jQuery.trim(el.value)=="")
				return "用户名不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "用户名最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("member_query.do",{exists:true,csmUsername:el.value});
				if(exists && exists.length>0){
					if(${csMember.csmId==null} || exists[0].value!=$("#csmId").val())
						return "用户名已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmPassword==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmPassword":function(el){
			if(jQuery.trim(el.value)=="")
				return "帐号密码不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "帐号密码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmGroup==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmGroup":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmMoney==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmMoney":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "余额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmCoupon==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmCoupon":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "现金券输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmIntegral==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmIntegral":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "积分输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmGrow==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmGrow":function(el){
			if(jQuery.trim(el.value)=="")
				return "成长值不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmGrade==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmGrade":function(el){
			if(jQuery.trim(el.value)=="")
				return "会员等级不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmRebate==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmRebate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "折扣输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmNotRevenue==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmNotRevenue":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmWeixinFlag==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmWeixinFlag":function(el){
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "微信帐号标识最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmAlipayFlag==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmAlipayFlag":function(el){
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "支付宝帐号标识最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmHeader==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmHeader":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmEmail==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmEmail":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "邮箱最大长度为128个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("member_query.do",{exists:true,csmEmail:el.value});
				if(exists && exists.length>0){
					if(${csMember.csmId==null} || exists[0].value!=$("#csmId").val())
						return "邮箱已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmMobile==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmMobile":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "手机最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("member_query.do",{exists:true,csmMobile:el.value});
				if(exists && exists.length>0){
					if(${csMember.csmId==null} || exists[0].value!=$("#csmId").val())
						return "手机已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmEvcard==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmEvcard":function(el){
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("member_query.do",{exists:true,csmEvcard:el.value});
				if(exists && exists.length>0){
					if(${csMember.csmId==null} || exists[0].value!=$("#csmId").val())
						return "EV卡已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmExpress==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmExpress":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "快递单号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmTemp==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmTemp":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "临时标记最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmName==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmName":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "真实姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmInfo==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmInfo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmOutlets==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmOutlets":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmIsVip==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmIsVip":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmVipStart==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmVipStart":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmVipEnd==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmVipEnd":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmUpdateTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmAddTime==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "注册时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmLastTime==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmLastTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmLastIp==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmLastIp":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "最后登录IP最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmLoginS==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmLoginS":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmFirstUse==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmFirstUse":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmLastUse==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmLastUse":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmAllRecharge==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmAllRecharge":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "累计充值输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmAllOrderS==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmAllOrderS":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmAllUseTime==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmAllUseTime":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "累计用车小时输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmAllViolatS==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmAllViolatS":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmUnViolatS==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmUnViolatS":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmAllTroubleS==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmAllTroubleS":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmFirstRecharge==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmFirstRecharge":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmRefundTime==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmRefundTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmRegistIp==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmRegistIp":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "注册IP最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmAdder==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmTracker==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmTracker":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmRefer==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmRefer":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmReferType==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmReferType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmFrom==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmFrom":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmSrc==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmSrc":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmChannel==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmChannel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmMarketPlan==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmMarketPlan":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmActivity==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmActivity":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmSaler==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmSaler":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmMarket==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmMarket":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmTag==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmTag":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmMark==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmMark":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmVisitFlag==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmVisitFlag":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmMask==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmMask":function(el){
				var bitValue = 0;
				$("input[ref='csmMask']").each(function(){
					if($(this).is(":checked"))
						bitValue|=$(this).val();
				});
				$("#csmMask").val(bitValue);
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmRemark==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmVMobile==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmVMobile":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmVEmail==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmVEmail":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmVReal==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmVReal":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmVDrive==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmVDrive":function(el){
		}
		</s:if>
		<s:if test="#request.CTRL.e.csmVWork==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmVWork":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmVOffline==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmVOffline":function(el){
		}
		</s:if>
		<s:if test="#request.CTRL.e.csmStatus==true">
		${lz:set("haveEditable",true)}
		,"csMember.csmStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择可用状态";
		}
		</s:if>
		
		<s:if test="#request.CTRL.v.payMember==true && (csMember.csmVReal!=1 || csMember.csmVDrive!=1) && #request.payMembers != null">
		${lz:set("haveEditable",true)}
		,"unitGroup":function(el){
			if(jQuery.trim(el.value)=="" || jQuery.trim(el.value)==-1)
				return "请选择部门";
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
	<form class="form editform" ref="CsMember" id="memberForm" name="memberForm" action="${empty CTRL.action?"member_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写会员帐号相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csMemberToken}" name="csMemberToken" id="csMemberToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csMember.csmId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csmId和after$csmId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmId==true">
	${before$csmId}
	<dl class="csmId ${CTRL.e.csmId?"hidden":""}" major  ref="csmId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csmId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csMember$csmId!=null">${csMember$csmId}</s:if><s:else>
			<input type="hidden" value="${csMember.csmId}" name="csMember.csmId" id="csmId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmId">${csMember.csmId}</textarea>
		 		<span>
		 	
			 ${csMember.csmId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csmHost和after$csmHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmHost==true">
	${before$csmHost}
	<dl class="csmHost " major  ref="csmHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csmHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMember$csmHost!=null">${csMember$csmHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csmHost" name="csMember.csmHost">
		 				<option selected value="${csMember.csmHost}">
		 					${get:SrvHost(csMember.csmHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csmHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmHost">${csMember.csmHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csMember.csmHost}',{ctrl:{editable:false,visible:true}})">
			 ${csMember.csmHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmHost}
	</s:if>
	
	${lz:set("注释","*****************用户名字段的输入框代码*****************")}
	${lz:set("注释","before$csmUsername和after$csmUsername变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmUsername==true">
	${before$csmUsername}
	<dl class="csmUsername " major  ref="csmUsername" >
		<dt>用&nbsp;&nbsp;户&nbsp;&nbsp;名:</dt>
		<s:if test="#request.CTRL.e.csmUsername==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmUsername!=null">${csMember$csmUsername}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmUsername" id="csmUsername"  value="${csMember.csmUsername}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>如果由第三方平台注册，不同类型加前缀</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****用户名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmUsername">${csMember.csmUsername}</textarea>
		 		<span>
		 	
			 ${csMember.csmUsername$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmUsername}
	</s:if>
	
	${lz:set("注释","*****************帐号密码字段的输入框代码*****************")}
	${lz:set("注释","before$csmPassword和after$csmPassword变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmPassword==true">
	${before$csmPassword}
	<dl class="csmPassword " major  ref="csmPassword" >
		<dt>帐号密码:</dt>
		<s:if test="#request.CTRL.e.csmPassword==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmPassword!=null">${csMember$csmPassword}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmPassword" id="csmPassword"  value="${csMember.csmPassword}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员帐号的帐号密码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****帐号密码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmPassword">${csMember.csmPassword}</textarea>
		 		<span>
		 	
			 ${csMember.csmPassword$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmPassword}
	</s:if>
	
	${lz:set("注释","*****************所属组织字段的输入框代码*****************")}
	${lz:set("注释","before$csmGroup和after$csmGroup变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmGroup==true">
	${before$csmGroup}
	<dl class="csmGroup " minor  ref="csmGroup" >
		<dt>所属组织:</dt>
		<s:if test="#request.CTRL.e.csmGroup==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMember$csmGroup!=null">${csMember$csmGroup}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/user/membergroup_query.do?size=-1&csmgHost={csmHost}" id="csmGroup" name="csMember.csmGroup">
		 				<option selected value="${csMember.csmGroup}">
		 					${get:CsMemberGroup(csMember.csmGroup).csmgName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmGroup').val())==''){return;};window.href('${basePath}${proname}/user/membergroup_details.do?key='+$('#csmGroup').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属组织字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmGroup">${csMember.csmGroup}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/membergroup_details.do?key=${csMember.csmGroup}',{ctrl:{editable:false,visible:true}})">
			 ${csMember.csmGroup$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmGroup}
	</s:if>
	
	${lz:set("注释","*****************余额字段的输入框代码*****************")}
	${lz:set("注释","before$csmMoney和after$csmMoney变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmMoney==true">
	${before$csmMoney}
	<dl class="csmMoney " major  ref="csmMoney" >
		<dt>余　　额:</dt>
		<s:if test="#request.CTRL.e.csmMoney==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csMember$csmMoney!=null">${csMember$csmMoney}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmMoney" id="csmMoney"  value="${csMember.csmMoney}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员帐号的余额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****余额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmMoney">${csMember.csmMoney}</textarea>
		 		<span>
		 	
			 ${csMember.csmMoney$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmMoney}
	</s:if>
	
	${lz:set("注释","*****************现金券字段的输入框代码*****************")}
	${lz:set("注释","before$csmCoupon和after$csmCoupon变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmCoupon==true">
	${before$csmCoupon}
	<dl class="csmCoupon " major  ref="csmCoupon" >
		<dt>现&nbsp;&nbsp;金&nbsp;&nbsp;券:</dt>
		<s:if test="#request.CTRL.e.csmCoupon==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csMember$csmCoupon!=null">${csMember$csmCoupon}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmCoupon" id="csmCoupon"  value="${csMember.csmCoupon}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员帐号的现金券</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****现金券字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmCoupon">${csMember.csmCoupon}</textarea>
		 		<span>
		 	
			 ${csMember.csmCoupon$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmCoupon}
	</s:if>
	
	${lz:set("注释","*****************积分字段的输入框代码*****************")}
	${lz:set("注释","before$csmIntegral和after$csmIntegral变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmIntegral==true">
	${before$csmIntegral}
	<dl class="csmIntegral " major  ref="csmIntegral" >
		<dt>积　　分:</dt>
		<s:if test="#request.CTRL.e.csmIntegral==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csMember$csmIntegral!=null">${csMember$csmIntegral}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmIntegral" id="csmIntegral"  value="${csMember.csmIntegral}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员帐号的积分</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****积分字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmIntegral">${csMember.csmIntegral}</textarea>
		 		<span>
		 	
			 ${csMember.csmIntegral$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmIntegral}
	</s:if>
	
	${lz:set("注释","*****************成长值字段的输入框代码*****************")}
	${lz:set("注释","before$csmGrow和after$csmGrow变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmGrow==true">
	${before$csmGrow}
	<dl class="csmGrow " major  ref="csmGrow" >
		<dt>成&nbsp;&nbsp;长&nbsp;&nbsp;值:</dt>
		<s:if test="#request.CTRL.e.csmGrow==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csMember$csmGrow!=null">${csMember$csmGrow}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmGrow" id="csmGrow"  value="${csMember.csmGrow}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员帐号的成长值</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****成长值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmGrow">${csMember.csmGrow}</textarea>
		 		<span>
		 	
			 ${csMember.csmGrow$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmGrow}
	</s:if>
	
	${lz:set("注释","*****************会员等级字段的输入框代码*****************")}
	${lz:set("注释","before$csmGrade和after$csmGrade变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmGrade==true">
	${before$csmGrade}
	<dl class="csmGrade " major  ref="csmGrade" >
		<dt>会员等级:</dt>
		<s:if test="#request.CTRL.e.csmGrade==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csMember$csmGrade!=null">${csMember$csmGrade}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmGrade" id="csmGrade"  value="${csMember.csmGrade}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员帐号的会员等级</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员等级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmGrade">${csMember.csmGrade}</textarea>
		 		<span>
		 	
			 ${csMember.csmGrade$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmGrade}
	</s:if>
	
	${lz:set("注释","*****************折扣字段的输入框代码*****************")}
	${lz:set("注释","before$csmRebate和after$csmRebate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmRebate==true">
	${before$csmRebate}
	<dl class="csmRebate " major  ref="csmRebate" >
		<dt>折　　扣:</dt>
		<s:if test="#request.CTRL.e.csmRebate==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csMember$csmRebate!=null">${csMember$csmRebate}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmRebate" id="csmRebate"  value="${csMember.csmRebate}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>0到1,比如0.75表示七五折</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****折扣字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmRebate">${csMember.csmRebate}</textarea>
		 		<span>
		 	
			 ${csMember.csmRebate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmRebate}
	</s:if>
	
	${lz:set("注释","*****************计算营收字段的输入框代码*****************")}
	${lz:set("注释","before$csmNotRevenue和after$csmNotRevenue变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmNotRevenue==true">
	${before$csmNotRevenue}
	<dl class="csmNotRevenue " minor  ref="csmNotRevenue" >
		<dt>计算营收:</dt>
		<s:if test="#request.CTRL.e.csmNotRevenue==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMember$csmNotRevenue!=null">${csMember$csmNotRevenue}</s:if><s:else>
		 	<select class="narrow" id="csmNotRevenue" name="csMember.csmNotRevenue">
		 		<option value="">请选择</option>
				<option value="0" ${csMember.csmNotRevenue==0?"selected":""}>计入营收</option>
				<option value="1" ${csMember.csmNotRevenue==1?"selected":""}>不计营收</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>表示该帐号支付的订单是否为营收项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****计算营收字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmNotRevenue">${csMember.csmNotRevenue}</textarea>
		 		<span>
		 	
			 ${csMember.csmNotRevenue$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmNotRevenue}
	</s:if>
	
	${lz:set("注释","*****************订单记录字段的输入框代码*****************")}
	${lz:set("注释","before$csmOrders和after$csmOrders变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmOrders==true">
	${before$csmOrders}
	<dl class="csmOrders " minor  ref="csmOrders" style="width:98%;">
		<dt>订单记录:</dt>
		<s:if test="#request.CTRL.e.csmOrders==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmOrders!=null">${csMember$csmOrders}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmOrders" name="csmOrders" frameborder='0' src="" url="${basePath}${proname}/service/order.do?inFrame=true&canQuery=false&fields=csoId,csoOutlets,csoCar,csoPayReal,csoStatus&csoPayMember=${csMember.csmId}&ctrl={queryable:false,fields:{csoPayMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmOrders').attr('src',$('#csmOrders').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑订单记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmOrders" name="csmOrders" frameborder='0' src="" url="${basePath}${proname}/service/order.do?inFrame=true&canQuery=false&fields=csoId,csoOutlets,csoCar,csoPayReal,csoStatus&csoPayMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmOrders').attr('src',$('#csmOrders').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmOrders}
	</s:if>
	
	${lz:set("注释","*****************充值记录字段的输入框代码*****************")}
	${lz:set("注释","before$csmAlipays和after$csmAlipays变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmAlipays==true">
	${before$csmAlipays}
	<dl class="csmAlipays " minor  ref="csmAlipays" style="width:98%;">
		<dt>充值记录:</dt>
		<s:if test="#request.CTRL.e.csmAlipays==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmAlipays!=null">${csMember$csmAlipays}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmAlipays" name="csmAlipays" frameborder='0' src="" url="${basePath}${proname}/finance/alipay/alipayrecord.do?inFrame=true&canQuery=false&fields=csarAmount,csarPayMethod,csarAddTime,csarStatus&csarMember=${csMember.csmId}&ctrl={queryable:false,fields:{csarMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmAlipays').attr('src',$('#csmAlipays').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑充值记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****充值记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmAlipays" name="csmAlipays" frameborder='0' src="" url="${basePath}${proname}/finance/alipay/alipayrecord.do?inFrame=true&canQuery=false&fields=csarAmount,csarPayMethod,csarAddTime,csarStatus&csarMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmAlipays').attr('src',$('#csmAlipays').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmAlipays}
	</s:if>
	
	${lz:set("注释","*****************使用套餐字段的输入框代码*****************")}
	${lz:set("注释","before$csmPacks和after$csmPacks变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmPacks==true">
	${before$csmPacks}
	<dl class="csmPacks " minor  ref="csmPacks" style="width:98%;">
		<dt>使用套餐:</dt>
		<s:if test="#request.CTRL.e.csmPacks==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmPacks!=null">${csMember$csmPacks}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmPacks" name="csmPacks" frameborder='0' src="" url="${basePath}${proname}/user/pack.do?inFrame=true&canQuery=false&fields=csupPack,csupUntilTime,csupStatus&csupMember=${csMember.csmId}&ctrl={queryable:false,fields:{csupMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmPacks').attr('src',$('#csmPacks').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑使用套餐
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用套餐字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmPacks" name="csmPacks" frameborder='0' src="" url="${basePath}${proname}/user/pack.do?inFrame=true&canQuery=false&fields=csupPack,csupUntilTime,csupStatus&csupMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmPacks').attr('src',$('#csmPacks').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmPacks}
	</s:if>
	
	${lz:set("注释","*****************免费小时字段的输入框代码*****************")}
	${lz:set("注释","before$csmFreehours和after$csmFreehours变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmFreehours==true">
	${before$csmFreehours}
	<dl class="csmFreehours " minor  ref="csmFreehours" style="width:98%;">
		<dt>免费小时:</dt>
		<s:if test="#request.CTRL.e.csmFreehours==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmFreehours!=null">${csMember$csmFreehours}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmFreehours" name="csmFreehours" frameborder='0' src="" url="${basePath}${proname}/user/freehour.do?inFrame=true&canQuery=false&fields=csfhCount,csfhRemain,csfhEnd,csfhStatus&csfhMember=${csMember.csmId}&ctrl={queryable:false,fields:{csfhMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmFreehours').attr('src',$('#csmFreehours').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑免费小时
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****免费小时字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmFreehours" name="csmFreehours" frameborder='0' src="" url="${basePath}${proname}/user/freehour.do?inFrame=true&canQuery=false&fields=csfhCount,csfhRemain,csfhEnd,csfhStatus&csfhMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmFreehours').attr('src',$('#csmFreehours').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmFreehours}
	</s:if>
	
	${lz:set("注释","*****************红包字段的输入框代码*****************")}
	${lz:set("注释","before$csmCoins和after$csmCoins变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmCoins==true">
	${before$csmCoins}
	<dl class="csmCoins " minor  ref="csmCoins" style="width:98%;">
		<dt>红&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;包:</dt>
		<s:if test="#request.CTRL.e.csmCoins==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmCoins!=null">${csMember$csmCoins}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmCoins" name="csmCoins" frameborder='0' src="" url="${basePath}${proname}/user/coin.do?inFrame=true&canQuery=false&fields=cscId,cscCount,cscRemain,cscEnd,cscStatus&cscMember=${csMember.csmId}&ctrl={queryable:false,fields:{cscMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmCoins').attr('src',$('#csmCoins').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑红包
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****红包字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmCoins" name="csmCoins" frameborder='0' src="" url="${basePath}${proname}/user/coin.do?inFrame=true&canQuery=false&fields=cscId,cscCount,cscRemain,cscEnd,cscStatus&cscMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmCoins').attr('src',$('#csmCoins').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmCoins}
	</s:if>
	
	${lz:set("注释","*****************消费记录字段的输入框代码*****************")}
	${lz:set("注释","before$csmRecords和after$csmRecords变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmRecords==true">
	${before$csmRecords}
	<dl class="csmRecords " minor  ref="csmRecords" style="width:98%;">
		<dt>消费记录:</dt>
		<s:if test="#request.CTRL.e.csmRecords==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmRecords!=null">${csMember$csmRecords}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmRecords" name="csmRecords" frameborder='0' src="" url="${basePath}${proname}/finance/record.do?inFrame=true&canQuery=false&fields=csrRemark,csrMoneyType,csrRecordSubject,csrAmount,csrAddTime,csrOrder,csrObject&csrMember=${csMember.csmId}&ctrl={queryable:false,fields:{csrMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmRecords').attr('src',$('#csmRecords').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑消费记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消费记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmRecords" name="csmRecords" frameborder='0' src="" url="${basePath}${proname}/finance/record.do?inFrame=true&canQuery=false&fields=csrRemark,csrMoneyType,csrRecordSubject,csrAmount,csrAddTime,csrOrder,csrObject&csrMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmRecords').attr('src',$('#csmRecords').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmRecords}
	</s:if>
	
	${lz:set("注释","*****************信用账单字段的输入框代码*****************")}
	${lz:set("注释","before$csmBills和after$csmBills变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmBills==true">
	${before$csmBills}
	<dl class="csmBills " minor  ref="csmBills" style="width:98%;">
		<dt>信用账单:</dt>
		<s:if test="#request.CTRL.e.csmBills==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmBills!=null">${csMember$csmBills}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmBills" name="csmBills" frameborder='0' src="" url="${basePath}${proname}/finance/credit/bill.do?inFrame=true&canQuery=false&fields=cscbId,cscbTitle,cscbOrder,cscbValue,cscbPayment,cscbAddTime,cscbPaySerial,cscbStatus&cscbMember=${csMember.csmId}&ctrl={queryable:false,fields:{cscbMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmBills').attr('src',$('#csmBills').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑信用账单
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****信用账单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmBills" name="csmBills" frameborder='0' src="" url="${basePath}${proname}/finance/credit/bill.do?inFrame=true&canQuery=false&fields=cscbId,cscbTitle,cscbOrder,cscbValue,cscbPayment,cscbAddTime,cscbPaySerial,cscbStatus&cscbMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmBills').attr('src',$('#csmBills').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmBills}
	</s:if>
	
	${lz:set("注释","*****************积分记录字段的输入框代码*****************")}
	${lz:set("注释","before$csmIntegrals和after$csmIntegrals变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmIntegrals==true">
	${before$csmIntegrals}
	<dl class="csmIntegrals " minor  ref="csmIntegrals" style="width:98%;">
		<dt>积分记录:</dt>
		<s:if test="#request.CTRL.e.csmIntegrals==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmIntegrals!=null">${csMember$csmIntegrals}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmIntegrals" name="csmIntegrals" frameborder='0' src="" url="${basePath}${proname}/user/integralrecord.do?inFrame=true&canQuery=false&fields=csrMoneyType,csrAmount,csrType,csrOrder,csrAddTime&csrMember=${csMember.csmId}&ctrl={queryable:false,fields:{csrMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmIntegrals').attr('src',$('#csmIntegrals').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑积分记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****积分记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmIntegrals" name="csmIntegrals" frameborder='0' src="" url="${basePath}${proname}/user/integralrecord.do?inFrame=true&canQuery=false&fields=csrMoneyType,csrAmount,csrType,csrOrder,csrAddTime&csrMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmIntegrals').attr('src',$('#csmIntegrals').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmIntegrals}
	</s:if>
	
	${lz:set("注释","*****************成长记录字段的输入框代码*****************")}
	${lz:set("注释","before$csmGrows和after$csmGrows变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmGrows==true">
	${before$csmGrows}
	<dl class="csmGrows " minor  ref="csmGrows" style="width:98%;">
		<dt>成长记录:</dt>
		<s:if test="#request.CTRL.e.csmGrows==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmGrows!=null">${csMember$csmGrows}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmGrows" name="csmGrows" frameborder='0' src="" url="${basePath}${proname}/user/growrecord.do?inFrame=true&canQuery=false&fields=csgrDesc,csgrType,csgrAmount,csgrAddTime,csgrStatus&csgrMember=${csMember.csmId}&ctrl={queryable:false,fields:{csgrMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmGrows').attr('src',$('#csmGrows').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑成长记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****成长记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmGrows" name="csmGrows" frameborder='0' src="" url="${basePath}${proname}/user/growrecord.do?inFrame=true&canQuery=false&fields=csgrDesc,csgrType,csgrAmount,csgrAddTime,csgrStatus&csgrMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmGrows').attr('src',$('#csmGrows').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmGrows}
	</s:if>
	
	${lz:set("注释","*****************优惠记录字段的输入框代码*****************")}
	${lz:set("注释","before$csmUseRecord和after$csmUseRecord变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmUseRecord==true">
	${before$csmUseRecord}
	<dl class="csmUseRecord " minor  ref="csmUseRecord" style="width:98%;">
		<dt>红包使用记录:</dt>
		<s:if test="#request.CTRL.e.csmUseRecord==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmUseRecord!=null">${csMember$csmUseRecord}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmUseRecord" name="csmUseRecord" frameborder='0' src="" url="${basePath}${proname}/user/userecord.do?inFrame=true&canQuery=false&fields=csurCoin,csurOrder,csurCount,csurAddTime&csurMember=${csMember.csmId}&ctrl={queryable:false,fields:{csurMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmUseRecord').attr('src',$('#csmUseRecord').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑红包使用记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优惠记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmUseRecord" name="csmUseRecord" frameborder='0' src="" url="${basePath}${proname}/user/userecord.do?inFrame=true&canQuery=false&fields=csurCoin,csurOrder,csurCount,csurAddTime&csurMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmUseRecord').attr('src',$('#csmUseRecord').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmUseRecord}
	</s:if>
	
	${lz:set("注释","*****************退款记录字段的输入框代码*****************")}
	${lz:set("注释","before$csmRefounds和after$csmRefounds变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmRefounds==true">
	${before$csmRefounds}
	<dl class="csmRefounds " minor  ref="csmRefounds" style="width:98%;">
		<dt>退款记录:</dt>
		<s:if test="#request.CTRL.e.csmRefounds==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmRefounds!=null">${csMember$csmRefounds}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmRefounds" name="csmRefounds" frameborder='0' src="" url="${basePath}${proname}/finance/refund.do?inFrame=true&canQuery=false&fields=csrAmount,csrReturnType,csrAddTime,csrStatus&csrMember=${csMember.csmId}&ctrl={queryable:false,fields:{csrMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmRefounds').attr('src',$('#csmRefounds').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑退款记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****退款记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmRefounds" name="csmRefounds" frameborder='0' src="" url="${basePath}${proname}/finance/refund.do?inFrame=true&canQuery=false&fields=csrAmount,csrReturnType,csrAddTime,csrStatus&csrMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmRefounds').attr('src',$('#csmRefounds').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmRefounds}
	</s:if>
	
	${lz:set("注释","*****************违章记录字段的输入框代码*****************")}
	${lz:set("注释","before$csmViolats和after$csmViolats变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmViolats==true">
	${before$csmViolats}
	<dl class="csmViolats " minor  ref="csmViolats" style="width:98%;">
		<dt>违章记录:</dt>
		<s:if test="#request.CTRL.e.csmViolats==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmViolats!=null">${csMember$csmViolats}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmViolats" name="csmViolats" frameborder='0' src="" url="${basePath}${proname}/service/violat.do?inFrame=true&canQuery=false&fields=csvOrder,csvCar,csvHappenTime,csvDecipt,csvStatus&csvMember=${csMember.csmId}&ctrl={queryable:false,fields:{csvMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmViolats').attr('src',$('#csmViolats').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑违章记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****违章记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmViolats" name="csmViolats" frameborder='0' src="" url="${basePath}${proname}/service/violat.do?inFrame=true&canQuery=false&fields=csvOrder,csvCar,csvHappenTime,csvDecipt,csvStatus&csvMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmViolats').attr('src',$('#csmViolats').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmViolats}
	</s:if>
	
	${lz:set("注释","*****************事故记录字段的输入框代码*****************")}
	${lz:set("注释","before$csmTroubles和after$csmTroubles变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmTroubles==true">
	${before$csmTroubles}
	<dl class="csmTroubles " minor  ref="csmTroubles" style="width:98%;">
		<dt>事故记录:</dt>
		<s:if test="#request.CTRL.e.csmTroubles==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmTroubles!=null">${csMember$csmTroubles}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmTroubles" name="csmTroubles" frameborder='0' src="" url="${basePath}${proname}/service/trouble.do?inFrame=true&canQuery=false&fields=cstOrder,cstCar,cstType,cstHappenTime,cstStatus&cstMember=${csMember.csmId}&ctrl={queryable:false,fields:{cstMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmTroubles').attr('src',$('#csmTroubles').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑事故记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事故记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmTroubles" name="csmTroubles" frameborder='0' src="" url="${basePath}${proname}/service/trouble.do?inFrame=true&canQuery=false&fields=cstOrder,cstCar,cstType,cstHappenTime,cstStatus&cstMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmTroubles').attr('src',$('#csmTroubles').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmTroubles}
	</s:if>
	
	${lz:set("注释","*****************发票记录字段的输入框代码*****************")}
	${lz:set("注释","before$csmInvoices和after$csmInvoices变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmInvoices==true">
	${before$csmInvoices}
	<dl class="csmInvoices " minor  ref="csmInvoices" style="width:98%;">
		<dt>发票记录:</dt>
		<s:if test="#request.CTRL.e.csmInvoices==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmInvoices!=null">${csMember$csmInvoices}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmInvoices" name="csmInvoices" frameborder='0' src="" url="${basePath}${proname}/finance/invoice.do?inFrame=true&canQuery=false&fields=csiTitle,csiContent,csiType,csiMoney,csiInvoiceTime,csiStatus&csiMember=${csMember.csmId}&ctrl={queryable:false,fields:{csiMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmInvoices').attr('src',$('#csmInvoices').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑发票记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发票记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmInvoices" name="csmInvoices" frameborder='0' src="" url="${basePath}${proname}/finance/invoice.do?inFrame=true&canQuery=false&fields=csiTitle,csiContent,csiType,csiMoney,csiInvoiceTime,csiStatus&csiMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmInvoices').attr('src',$('#csmInvoices').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmInvoices}
	</s:if>
	
	${lz:set("注释","*****************地址管理字段的输入框代码*****************")}
	${lz:set("注释","before$csmAddresses和after$csmAddresses变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmAddresses==true">
	${before$csmAddresses}
	<dl class="csmAddresses " minor  ref="csmAddresses" style="width:98%;">
		<dt>地址管理:</dt>
		<s:if test="#request.CTRL.e.csmAddresses==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmAddresses!=null">${csMember$csmAddresses}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmAddresses" name="csmAddresses" frameborder='0' src="" url="${basePath}${proname}/user/address.do?inFrame=true&canQuery=false&fields=csaAddress,csaName,csaContact,csaStatus&csaMember=${csMember.csmId}&ctrl={queryable:false,fields:{csaMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmAddresses').attr('src',$('#csmAddresses').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑地址管理
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****地址管理字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmAddresses" name="csmAddresses" frameborder='0' src="" url="${basePath}${proname}/user/address.do?inFrame=true&canQuery=false&fields=csaAddress,csaName,csaContact,csaStatus&csaMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmAddresses').attr('src',$('#csmAddresses').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmAddresses}
	</s:if>
	
	${lz:set("注释","*****************备忘备注字段的输入框代码*****************")}
	${lz:set("注释","before$csmMemos和after$csmMemos变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmMemos==true">
	${before$csmMemos}
	<dl class="csmMemos " minor  ref="csmMemos" style="width:98%;">
		<dt>备忘备注:</dt>
		<s:if test="#request.CTRL.e.csmMemos==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMember$csmMemos!=null">${csMember$csmMemos}</s:if><s:else>
		  		<s:if test="csMember.csmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmMemos" name="csmMemos" frameborder='0' src="" url="${basePath}${proname}/user/membermemo.do?inFrame=true&canQuery=false&fields=csmmId,csmmContent,csmmAddTime&csmmMember=${csMember.csmId}&ctrl={queryable:false,fields:{csmmMember:{visible:true,editable:false,defaultValue:${csMember.csmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmMemos').attr('src',$('#csmMemos').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑备忘备注
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMember.csmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csmMemos" name="csmMemos" frameborder='0' src="" url="${basePath}${proname}/user/membermemo.do?inFrame=true&canQuery=false&fields=csmmId,csmmContent,csmmAddTime&csmmMember=${csMember.csmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csmMemos').attr('src',$('#csmMemos').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csmMemos}
	</s:if>
	
	${lz:set("注释","*****************微信帐号标识字段的输入框代码*****************")}
	${lz:set("注释","before$csmWeixinFlag和after$csmWeixinFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmWeixinFlag==true">
	${before$csmWeixinFlag}
	<dl class="csmWeixinFlag " minor  ref="csmWeixinFlag" style="width:98%;">
		<dt>微信帐号标识:</dt>
		<s:if test="#request.CTRL.e.csmWeixinFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmWeixinFlag!=null">${csMember$csmWeixinFlag}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csMember.csmWeixinFlag" id="csmWeixinFlag"  value="${csMember.csmWeixinFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的微信帐号标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****微信帐号标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmWeixinFlag">${csMember.csmWeixinFlag}</textarea>
		 		<span>
		 	
			 ${csMember.csmWeixinFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmWeixinFlag}
	</s:if>
	
	${lz:set("注释","*****************支付宝帐号标识字段的输入框代码*****************")}
	${lz:set("注释","before$csmAlipayFlag和after$csmAlipayFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmAlipayFlag==true">
	${before$csmAlipayFlag}
	<dl class="csmAlipayFlag " minor  ref="csmAlipayFlag" style="width:98%;">
		<dt>支付宝帐号标识:</dt>
		<s:if test="#request.CTRL.e.csmAlipayFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmAlipayFlag!=null">${csMember$csmAlipayFlag}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csMember.csmAlipayFlag" id="csmAlipayFlag"  value="${csMember.csmAlipayFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的支付宝帐号标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付宝帐号标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmAlipayFlag">${csMember.csmAlipayFlag}</textarea>
		 		<span>
		 	
			 ${csMember.csmAlipayFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmAlipayFlag}
	</s:if>
	
	${lz:set("注释","*****************头像字段的输入框代码*****************")}
	${lz:set("注释","before$csmHeader和after$csmHeader变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmHeader==true">
	${before$csmHeader}
	<dl class="csmHeader " minor  ref="csmHeader" style="width:98%;">
		<dt>头　　像:</dt>
		<s:if test="#request.CTRL.e.csmHeader==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csMember$csmHeader!=null">${csMember$csmHeader}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csMember.csmHeader" id="csmHeader"  value="${csMember.csmHeader}"/>
			<button type="button" onclick="$.upload({type:'img',width:200,height:200,callback:function(url){if(url)$('#csmHeader').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#csmHeader').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#csmHeader').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传会员帐号的头像</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****头像字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmHeader">${csMember.csmHeader}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csMember.csmHeader))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csMember.csmHeader}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmHeader}
	</s:if>
	
	${lz:set("注释","*****************邮箱字段的输入框代码*****************")}
	${lz:set("注释","before$csmEmail和after$csmEmail变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmEmail==true">
	${before$csmEmail}
	<dl class="csmEmail " minor  ref="csmEmail" style="width:98%;">
		<dt>邮　　箱:</dt>
		<s:if test="#request.CTRL.e.csmEmail==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmEmail!=null">${csMember$csmEmail}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csMember.csmEmail" id="csmEmail"  value="${csMember.csmEmail}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的邮箱</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****邮箱字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmEmail">${csMember.csmEmail}</textarea>
		 		<span>
		 	
			 ${csMember.csmEmail$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmEmail}
	</s:if>
	
	${lz:set("注释","*****************手机字段的输入框代码*****************")}
	${lz:set("注释","before$csmMobile和after$csmMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmMobile==true">
	${before$csmMobile}
	<dl class="csmMobile " major  ref="csmMobile" >
		<dt>手　　机:</dt>
		<s:if test="#request.CTRL.e.csmMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmMobile!=null">${csMember$csmMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmMobile" id="csmMobile"  value="${csMember.csmMobile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的手机</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****手机字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmMobile">${csMember.csmMobile}</textarea>
		 		<span>
		 	
			 ${csMember.csmMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmMobile}
	</s:if>
	
	${lz:set("注释","*****************EV卡字段的输入框代码*****************")}
	${lz:set("注释","before$csmEvcard和after$csmEvcard变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmEvcard==true">
	${before$csmEvcard}
	<dl class="csmEvcard " major  ref="csmEvcard" >
		<dt>E&nbsp;&nbsp;V&nbsp;&nbsp;卡:</dt>
		<s:if test="#request.CTRL.e.csmEvcard==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMember$csmEvcard!=null">${csMember$csmEvcard}</s:if><s:else>
		 			<input title="请输入会员卡卡号进行查询" class="combox narrow" action="${basePath}${proname}/object/evcard_query.do?value={param}&csecHost={csmHost}" type="text" id="csmEvcard" name="csMember.csmEvcard" text="${get:CsEvCard(csMember.csmEvcard).csecNumber}" value="${csMember.csmEvcard}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmEvcard').val())==''){return;};window.href('${basePath}${proname}/object/evcard_details.do?key='+$('#csmEvcard').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****EV卡字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmEvcard">${csMember.csmEvcard}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/evcard_details.do?key=${csMember.csmEvcard}',{ctrl:{editable:false,visible:true}})">
			 ${csMember.csmEvcard$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmEvcard}
	</s:if>
	
	${lz:set("注释","*****************快递单号字段的输入框代码*****************")}
	${lz:set("注释","before$csmExpress和after$csmExpress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmExpress==true">
	${before$csmExpress}
	<dl class="csmExpress " minor  ref="csmExpress" >
		<dt>快递单号:</dt>
		<s:if test="#request.CTRL.e.csmExpress==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmExpress!=null">${csMember$csmExpress}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmExpress" id="csmExpress"  value="${csMember.csmExpress}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的快递单号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****快递单号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmExpress">${csMember.csmExpress}</textarea>
		 		<span>
		 	
			 ${csMember.csmExpress$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmExpress}
	</s:if>
	
	${lz:set("注释","*****************邀请码字段的输入框代码*****************")}
	${lz:set("注释","before$csmTemp和after$csmTemp变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmTemp==true">
	${before$csmTemp}
	<dl class="csmTemp " minor  ref="csmTemp" >
		<dt>邀请码:</dt>
		<s:if test="#request.CTRL.e.csmTemp==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmTemp!=null">${csMember$csmTemp}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmTemp" id="csmTemp"  value="${csMember.csmTemp}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的邀请码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****邀请码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmTemp">${csMember.csmTemp}</textarea>
		 		<span>
		 	
			 ${csMember.csmTemp$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmTemp}
	</s:if>
	
	${lz:set("注释","*****************真实姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csmName和after$csmName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmName==true">
	${before$csmName}
	<dl class="csmName " major  ref="csmName" >
		<dt>真实姓名:</dt>
		<s:if test="#request.CTRL.e.csmName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmName!=null">${csMember$csmName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmName" id="csmName"  value="${csMember.csmName}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的真实姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****真实姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmName">${csMember.csmName}</textarea>
		 		<span>
		 	
			 ${csMember.csmName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmName}
	</s:if>
	
	${lz:set("注释","*****************详细信息字段的输入框代码*****************")}
	${lz:set("注释","before$csmInfo和after$csmInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmInfo==true">
	${before$csmInfo}
	<dl class="csmInfo " major  ref="csmInfo" >
		<dt>详细信息:</dt>
		<s:if test="#request.CTRL.e.csmInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMember$csmInfo!=null">${csMember$csmInfo}</s:if><s:else>
		 			<input title="请输入会员信息真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/memberinfo_query.do?value={param}&csmiHost={csmHost}" type="text" id="csmInfo" name="csMember.csmInfo" text="${get:CsMemberInfo(csMember.csmInfo).csmiName}" value="${csMember.csmInfo}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmInfo').val())==''){return;};window.href('${basePath}${proname}/user/memberinfo_details.do?key='+$('#csmInfo').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>查看人员详细信息，包括单位信息、身份信息、驾驶证信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****详细信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmInfo">${csMember.csmInfo}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/memberinfo_details.do?key=${csMember.csmInfo}',{ctrl:{editable:false,visible:true}})">
			 ${csMember.csmInfo$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmInfo}
	</s:if>
	
	${lz:set("注释","*****************默认网点字段的输入框代码*****************")}
	${lz:set("注释","before$csmOutlets和after$csmOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmOutlets==true">
	${before$csmOutlets}
	<dl class="csmOutlets " minor  ref="csmOutlets" >
		<dt>默认网点:</dt>
		<s:if test="#request.CTRL.e.csmOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMember$csmOutlets!=null">${csMember$csmOutlets}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&csoHost={csmHost}" id="csmOutlets" name="csMember.csmOutlets">
		 				<option selected value="${csMember.csmOutlets}">
		 					${get:CsOutlets(csMember.csmOutlets).csoName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmOutlets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#csmOutlets').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****默认网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmOutlets">${csMember.csmOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csMember.csmOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csMember.csmOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmOutlets}
	</s:if>
	
	${lz:set("注释","*****************VIP状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmIsVip和after$csmIsVip变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmIsVip==true">
	${before$csmIsVip}
	<dl class="csmIsVip " minor  ref="csmIsVip" >
		<dt>VIP状态:</dt>
		<s:if test="#request.CTRL.e.csmIsVip==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMember$csmIsVip!=null">${csMember$csmIsVip}</s:if><s:else>
		 	<select class="narrow" id="csmIsVip" name="csMember.csmIsVip">
		 		<option value="">请选择</option>
				<option value="0" ${csMember.csmIsVip==0?"selected":""}>不是</option>
				<option value="1" ${csMember.csmIsVip==1?"selected":""}>是</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的VIP状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****VIP状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmIsVip">${csMember.csmIsVip}</textarea>
		 		<span>
		 	
			 ${csMember.csmIsVip$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmIsVip}
	</s:if>
	
	${lz:set("注释","*****************VIP开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmVipStart和after$csmVipStart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmVipStart==true">
	${before$csmVipStart}
	<dl class="csmVipStart " minor  ref="csmVipStart" >
		<dt>VIP开始时间:</dt>
		<s:if test="#request.CTRL.e.csmVipStart==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMember$csmVipStart!=null">${csMember$csmVipStart}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMember.csmVipStart" id="csmVipStart"  value="<s:date name="csMember.csmVipStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的VIP开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****VIP开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmVipStart">${csMember.csmVipStart}</textarea>
		 		<span>
		 	
			 ${csMember.csmVipStart$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmVipStart}
	</s:if>
	
	${lz:set("注释","*****************VIP结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmVipEnd和after$csmVipEnd变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmVipEnd==true">
	${before$csmVipEnd}
	<dl class="csmVipEnd " minor  ref="csmVipEnd" >
		<dt>VIP结束时间:</dt>
		<s:if test="#request.CTRL.e.csmVipEnd==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMember$csmVipEnd!=null">${csMember$csmVipEnd}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMember.csmVipEnd" id="csmVipEnd"  value="<s:date name="csMember.csmVipEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的VIP结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****VIP结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmVipEnd">${csMember.csmVipEnd}</textarea>
		 		<span>
		 	
			 ${csMember.csmVipEnd$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmVipEnd}
	</s:if>
	
	${lz:set("注释","*****************更新时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmUpdateTime和after$csmUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmUpdateTime==true">
	${before$csmUpdateTime}
	<dl class="csmUpdateTime " minor  ref="csmUpdateTime" >
		<dt>更新时间:</dt>
		<s:if test="#request.CTRL.e.csmUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMember$csmUpdateTime!=null">${csMember$csmUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMember.csmUpdateTime" id="csmUpdateTime"  value="<s:date name="csMember.csmUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的更新时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****更新时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmUpdateTime">${csMember.csmUpdateTime}</textarea>
		 		<span>
		 	
			 ${csMember.csmUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************注册时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmAddTime和after$csmAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmAddTime==true">
	${before$csmAddTime}
	<dl class="csmAddTime " major  ref="csmAddTime" >
		<dt>注册时间:</dt>
		<s:if test="#request.CTRL.e.csmAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMember$csmAddTime!=null">${csMember$csmAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMember.csmAddTime" id="csmAddTime"  value="<s:date name="csMember.csmAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员帐号的注册时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****注册时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmAddTime">${csMember.csmAddTime}</textarea>
		 		<span>
		 	
			 ${csMember.csmAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmAddTime}
	</s:if>
	
	${lz:set("注释","*****************最后登录字段的输入框代码*****************")}
	${lz:set("注释","before$csmLastTime和after$csmLastTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmLastTime==true">
	${before$csmLastTime}
	<dl class="csmLastTime " minor  ref="csmLastTime" >
		<dt>最后登录:</dt>
		<s:if test="#request.CTRL.e.csmLastTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMember$csmLastTime!=null">${csMember$csmLastTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMember.csmLastTime" id="csmLastTime"  value="<s:date name="csMember.csmLastTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的最后登录</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****最后登录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmLastTime">${csMember.csmLastTime}</textarea>
		 		<span>
		 	
			 ${csMember.csmLastTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmLastTime}
	</s:if>
	
	${lz:set("注释","*****************最后登录IP字段的输入框代码*****************")}
	${lz:set("注释","before$csmLastIp和after$csmLastIp变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmLastIp==true">
	${before$csmLastIp}
	<dl class="csmLastIp " minor  ref="csmLastIp" >
		<dt>最后登录IP:</dt>
		<s:if test="#request.CTRL.e.csmLastIp==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmLastIp!=null">${csMember$csmLastIp}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmLastIp" id="csmLastIp"  value="${csMember.csmLastIp}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的最后登录IP</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****最后登录IP字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmLastIp">${csMember.csmLastIp}</textarea>
		 		<span>
		 	
			 ${csMember.csmLastIp$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmLastIp}
	</s:if>
	
	${lz:set("注释","*****************登录次数字段的输入框代码*****************")}
	${lz:set("注释","before$csmLoginS和after$csmLoginS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmLoginS==true">
	${before$csmLoginS}
	<dl class="csmLoginS " minor  ref="csmLoginS" >
		<dt>登录次数:</dt>
		<s:if test="#request.CTRL.e.csmLoginS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csMember$csmLoginS!=null">${csMember$csmLoginS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmLoginS" id="csmLoginS"  value="${csMember.csmLoginS}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的登录次数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****登录次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmLoginS">${csMember.csmLoginS}</textarea>
		 		<span>
		 	
			 ${csMember.csmLoginS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmLoginS}
	</s:if>
	
	${lz:set("注释","*****************首次用车字段的输入框代码*****************")}
	${lz:set("注释","before$csmFirstUse和after$csmFirstUse变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmFirstUse==true">
	${before$csmFirstUse}
	<dl class="csmFirstUse " minor  ref="csmFirstUse" >
		<dt>首次用车:</dt>
		<s:if test="#request.CTRL.e.csmFirstUse==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMember$csmFirstUse!=null">${csMember$csmFirstUse}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMember.csmFirstUse" id="csmFirstUse"  value="<s:date name="csMember.csmFirstUse" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的首次用车</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****首次用车字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmFirstUse">${csMember.csmFirstUse}</textarea>
		 		<span>
		 	
			 ${csMember.csmFirstUse$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmFirstUse}
	</s:if>
	
	${lz:set("注释","*****************最后使用字段的输入框代码*****************")}
	${lz:set("注释","before$csmLastUse和after$csmLastUse变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmLastUse==true">
	${before$csmLastUse}
	<dl class="csmLastUse " minor  ref="csmLastUse" >
		<dt>最后使用:</dt>
		<s:if test="#request.CTRL.e.csmLastUse==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMember$csmLastUse!=null">${csMember$csmLastUse}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMember.csmLastUse" id="csmLastUse"  value="<s:date name="csMember.csmLastUse" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的最后使用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****最后使用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmLastUse">${csMember.csmLastUse}</textarea>
		 		<span>
		 	
			 ${csMember.csmLastUse$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmLastUse}
	</s:if>
	
	${lz:set("注释","*****************累计充值字段的输入框代码*****************")}
	${lz:set("注释","before$csmAllRecharge和after$csmAllRecharge变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmAllRecharge==true">
	${before$csmAllRecharge}
	<dl class="csmAllRecharge " minor  ref="csmAllRecharge" >
		<dt>累计充值:</dt>
		<s:if test="#request.CTRL.e.csmAllRecharge==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csMember$csmAllRecharge!=null">${csMember$csmAllRecharge}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmAllRecharge" id="csmAllRecharge"  value="${csMember.csmAllRecharge}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入会员帐号的累计充值</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****累计充值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmAllRecharge">${csMember.csmAllRecharge}</textarea>
		 		<span>
		 	
			 ${csMember.csmAllRecharge$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmAllRecharge}
	</s:if>
	
	${lz:set("注释","*****************累计订单字段的输入框代码*****************")}
	${lz:set("注释","before$csmAllOrderS和after$csmAllOrderS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmAllOrderS==true">
	${before$csmAllOrderS}
	<dl class="csmAllOrderS " minor  ref="csmAllOrderS" >
		<dt>累计订单:</dt>
		<s:if test="#request.CTRL.e.csmAllOrderS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csMember$csmAllOrderS!=null">${csMember$csmAllOrderS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmAllOrderS" id="csmAllOrderS"  value="${csMember.csmAllOrderS}"/>
	 	 </s:else>
	 	 个
	 	 
	 	 <em>请输入会员帐号的累计订单</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****累计订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmAllOrderS">${csMember.csmAllOrderS}</textarea>
		 		<span>
		 	
			 ${csMember.csmAllOrderS$}
	 	 个 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmAllOrderS}
	</s:if>
	
	${lz:set("注释","*****************累计用车小时字段的输入框代码*****************")}
	${lz:set("注释","before$csmAllUseTime和after$csmAllUseTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmAllUseTime==true">
	${before$csmAllUseTime}
	<dl class="csmAllUseTime " minor  ref="csmAllUseTime" >
		<dt>累计用车小时:</dt>
		<s:if test="#request.CTRL.e.csmAllUseTime==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csMember$csmAllUseTime!=null">${csMember$csmAllUseTime}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmAllUseTime" id="csmAllUseTime"  value="${csMember.csmAllUseTime}"/>
	 	 </s:else>
	 	 小时
	 	 
	 	 <em>请输入会员帐号的累计用车小时</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****累计用车小时字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmAllUseTime">${csMember.csmAllUseTime}</textarea>
		 		<span>
		 	
			 ${csMember.csmAllUseTime$}
	 	 小时 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmAllUseTime}
	</s:if>
	
	<s:if test="#request.pay_coin != null">
	<dl class="pay_coupon " minor  ref="pay_coupon" >
		<dt>现金券消费:</dt>
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="pay_coupon">${pay_coupon }</textarea>
		 		<span>${pay_coupon }
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
	</dl>
	</s:if>
	
	<s:if test="#request.pay_coin != null">
	<dl class="pay_coin " minor  ref="pay_coin" >
		<dt>红包累计消费:</dt>
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="pay_coin">${pay_coin }</textarea>
		 		<span>${pay_coin }
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
	</dl>
	</s:if>
	
	
	
	${lz:set("注释","*****************违章次数字段的输入框代码*****************")}
	${lz:set("注释","before$csmAllViolatS和after$csmAllViolatS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmAllViolatS==true">
	${before$csmAllViolatS}
	<dl class="csmAllViolatS " minor  ref="csmAllViolatS" >
		<dt>违章次数:</dt>
		<s:if test="#request.CTRL.e.csmAllViolatS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csMember$csmAllViolatS!=null">${csMember$csmAllViolatS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmAllViolatS" id="csmAllViolatS"  value="${csMember.csmAllViolatS}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的违章次数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****违章次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmAllViolatS">${csMember.csmAllViolatS}</textarea>
		 		<span>
		 	
			 ${csMember.csmAllViolatS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmAllViolatS}
	</s:if>
	
	${lz:set("注释","*****************未处理违章字段的输入框代码*****************")}
	${lz:set("注释","before$csmUnViolatS和after$csmUnViolatS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmUnViolatS==true">
	${before$csmUnViolatS}
	<dl class="csmUnViolatS " minor  ref="csmUnViolatS" >
		<dt>未处理违章:</dt>
		<s:if test="#request.CTRL.e.csmUnViolatS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csMember$csmUnViolatS!=null">${csMember$csmUnViolatS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmUnViolatS" id="csmUnViolatS"  value="${csMember.csmUnViolatS}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的未处理违章</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****未处理违章字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmUnViolatS">${csMember.csmUnViolatS}</textarea>
		 		<span>
		 	
			 ${csMember.csmUnViolatS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmUnViolatS}
	</s:if>
	
	${lz:set("注释","*****************事故次数字段的输入框代码*****************")}
	${lz:set("注释","before$csmAllTroubleS和after$csmAllTroubleS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmAllTroubleS==true">
	${before$csmAllTroubleS}
	<dl class="csmAllTroubleS " minor  ref="csmAllTroubleS" >
		<dt>事故次数:</dt>
		<s:if test="#request.CTRL.e.csmAllTroubleS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csMember$csmAllTroubleS!=null">${csMember$csmAllTroubleS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMember.csmAllTroubleS" id="csmAllTroubleS"  value="${csMember.csmAllTroubleS}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的事故次数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事故次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmAllTroubleS">${csMember.csmAllTroubleS}</textarea>
		 		<span>
		 	
			 ${csMember.csmAllTroubleS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmAllTroubleS}
	</s:if>
	
	${lz:set("注释","*****************首次充值字段的输入框代码*****************")}
	${lz:set("注释","before$csmFirstRecharge和after$csmFirstRecharge变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmFirstRecharge==true">
	${before$csmFirstRecharge}
	<dl class="csmFirstRecharge " minor  ref="csmFirstRecharge" >
		<dt>首次充值:</dt>
		<s:if test="#request.CTRL.e.csmFirstRecharge==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMember$csmFirstRecharge!=null">${csMember$csmFirstRecharge}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMember.csmFirstRecharge" id="csmFirstRecharge"  value="<s:date name="csMember.csmFirstRecharge" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的首次充值</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****首次充值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmFirstRecharge">${csMember.csmFirstRecharge}</textarea>
		 		<span>
		 	
			 ${csMember.csmFirstRecharge$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmFirstRecharge}
	</s:if>
	
	${lz:set("注释","*****************最后退款字段的输入框代码*****************")}
	${lz:set("注释","before$csmRefundTime和after$csmRefundTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmRefundTime==true">
	${before$csmRefundTime}
	<dl class="csmRefundTime " minor  ref="csmRefundTime" >
		<dt>最后退款:</dt>
		<s:if test="#request.CTRL.e.csmRefundTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMember$csmRefundTime!=null">${csMember$csmRefundTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMember.csmRefundTime" id="csmRefundTime"  value="<s:date name="csMember.csmRefundTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的最后退款</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****最后退款字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmRefundTime">${csMember.csmRefundTime}</textarea>
		 		<span>
		 	
			 ${csMember.csmRefundTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmRefundTime}
	</s:if>
	
	${lz:set("注释","*****************注册IP字段的输入框代码*****************")}
	${lz:set("注释","before$csmRegistIp和after$csmRegistIp变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmRegistIp==true">
	${before$csmRegistIp}
	<dl class="csmRegistIp " minor  ref="csmRegistIp" >
		<dt>注册IP:</dt>
		<s:if test="#request.CTRL.e.csmRegistIp==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmRegistIp!=null">${csMember$csmRegistIp}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmRegistIp" id="csmRegistIp"  value="${csMember.csmRegistIp}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的注册IP</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****注册IP字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmRegistIp">${csMember.csmRegistIp}</textarea>
		 		<span>
		 	
			 ${csMember.csmRegistIp$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmRegistIp}
	</s:if>
	
	${lz:set("注释","*****************添加人员字段的输入框代码*****************")}
	${lz:set("注释","before$csmAdder和after$csmAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmAdder==true">
	${before$csmAdder}
	<dl class="csmAdder " minor  ref="csmAdder" >
		<dt>添加人员:</dt>
		<s:if test="#request.CTRL.e.csmAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMember$csmAdder!=null">${csMember$csmAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csmAdder" name="csMember.csmAdder">
		 				<option selected value="${csMember.csmAdder}">
		 					${get:SrvUser(csMember.csmAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csmAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmAdder">${csMember.csmAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csMember.csmAdder}',{ctrl:{editable:false,visible:true}})">
			 ${csMember.csmAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmAdder}
	</s:if>
	
	${lz:set("注释","*****************跟踪人员字段的输入框代码*****************")}
	${lz:set("注释","before$csmTracker和after$csmTracker变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmTracker==true">
	${before$csmTracker}
	<dl class="csmTracker " minor  ref="csmTracker" >
		<dt>跟踪人员:</dt>
		<s:if test="#request.CTRL.e.csmTracker==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMember$csmTracker!=null">${csMember$csmTracker}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csmTracker" name="csMember.csmTracker">
		 				<option selected value="${csMember.csmTracker}">
		 					${get:SrvUser(csMember.csmTracker).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmTracker').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csmTracker').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****跟踪人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmTracker">${csMember.csmTracker}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csMember.csmTracker}',{ctrl:{editable:false,visible:true}})">
			 ${csMember.csmTracker$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmTracker}
	</s:if>
	
	${lz:set("注释","*****************推荐会员字段的输入框代码*****************")}
	${lz:set("注释","before$csmRefer和after$csmRefer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmRefer==true">
	${before$csmRefer}
	<dl class="csmRefer " minor  ref="csmRefer" >
		<dt>推荐会员:</dt>
		<s:if test="#request.CTRL.e.csmRefer==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMember$csmRefer!=null">${csMember$csmRefer}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csmHost}" type="text" id="csmRefer" name="csMember.csmRefer" text="${get:CsMember(csMember.csmRefer).csmName}" value="${csMember.csmRefer}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmRefer').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csmRefer').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****推荐会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmRefer">${csMember.csmRefer}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csMember.csmRefer}',{ctrl:{editable:false,visible:true}})">
			 ${csMember.csmRefer$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmRefer}
	</s:if>
	
	${lz:set("注释","*****************推荐方式字段的输入框代码*****************")}
	${lz:set("注释","before$csmReferType和after$csmReferType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmReferType==true">
	${before$csmReferType}
	<dl class="csmReferType " minor  ref="csmReferType" >
		<dt>推荐方式:</dt>
		<s:if test="#request.CTRL.e.csmReferType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMember$csmReferType!=null">${csMember$csmReferType}</s:if><s:else>
		 	<select class="narrow" id="csmReferType" name="csMember.csmReferType">
		 		<option value="">请选择</option>
				<option value="0" ${csMember.csmReferType==0?"selected":""}>无</option>
				<option value="1" ${csMember.csmReferType==1?"selected":""}>地址转发</option>
				<option value="2" ${csMember.csmReferType==2?"selected":""}>微博分享</option>
				<option value="3" ${csMember.csmReferType==3?"selected":""}>广告媒体</option>
				<option value="4" ${csMember.csmReferType==4?"selected":""}>网络搜索</option>
				<option value="5" ${csMember.csmReferType==5?"selected":""}>朋友推荐</option>
				<option value="6" ${csMember.csmReferType==6?"selected":""}>活动推广</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的推荐方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****推荐方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmReferType">${csMember.csmReferType}</textarea>
		 		<span>
		 	
			 ${csMember.csmReferType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmReferType}
	</s:if>
	
	${lz:set("注释","*****************注册平台字段的输入框代码*****************")}
	${lz:set("注释","before$csmFrom和after$csmFrom变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmFrom==true">
	${before$csmFrom}
	<dl class="csmFrom " major  ref="csmFrom" >
		<dt>注册平台:</dt>
		<s:if test="#request.CTRL.e.csmFrom==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMember$csmFrom!=null">${csMember$csmFrom}</s:if><s:else>
		 	<select class="narrow" id="csmFrom" name="csMember.csmFrom">
		 		<option value="">请选择</option>
				<option value="0" ${csMember.csmFrom==0?"selected":""}>ADMIN后台</option>
				<option value="2" ${csMember.csmFrom==2?"selected":""}>OFFICE后台</option>
				<option value="3" ${csMember.csmFrom==3?"selected":""}>IOS</option>
				<option value="4" ${csMember.csmFrom==4?"selected":""}>Android</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的注册平台</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****注册平台字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmFrom">${csMember.csmFrom}</textarea>
		 		<span>
		 	
			 ${csMember.csmFrom$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmFrom}
	</s:if>
	
	${lz:set("注释","*****************注册源信息字段的输入框代码*****************")}
	${lz:set("注释","before$csmSrc和after$csmSrc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmSrc==true">
	${before$csmSrc}
	<dl class="csmSrc " minor  ref="csmSrc" >
		<dt>注册源信息:</dt>
		<s:if test="#request.CTRL.e.csmSrc==true">
		${lz:set("haveEditable",true)}
		<dd input="generic">
		<s:if test="#request.csMember$csmSrc!=null">${csMember$csmSrc}</s:if><s:else>
		 	<input title="请输入动态查询" class="combox narrow" generic="csmSrcGeneric" size="16" type="text" id="csmSrc" name="csMember.csmSrc" text="${csMember.csmSrc$}" value="${csMember.csmSrc}"/>
		 	${lz:set("models",get:models(""))}
		 	<select id="csmSrcGeneric" style="display:none;">
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
		${lz:set("注释","****注册源信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmSrc">${csMember.csmSrc}</textarea>
		 		<span>
		 	
			 ${csMember.csmSrc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmSrc}
	</s:if>
	
	${lz:set("注释","*****************来源渠道字段的输入框代码*****************")}
	${lz:set("注释","before$csmChannel和after$csmChannel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmChannel==true">
	${before$csmChannel}
	<dl class="csmChannel " minor  ref="csmChannel" >
		<dt>来源渠道:</dt>
		<s:if test="#request.CTRL.e.csmChannel==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMember$csmChannel!=null">${csMember$csmChannel}</s:if><s:else>
		 			<input title="请输入访问渠道渠道名称进行查询" class="combox narrow" action="${basePath}${proname}/extension/channel_query.do?value={param}&cscHost={csmHost}" type="text" id="csmChannel" name="csMember.csmChannel" text="${get:CsChannel(csMember.csmChannel).cscName}" value="${csMember.csmChannel}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmChannel').val())==''){return;};window.href('${basePath}${proname}/extension/channel_details.do?key='+$('#csmChannel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****来源渠道字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmChannel">${csMember.csmChannel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/extension/channel_details.do?key=${csMember.csmChannel}',{ctrl:{editable:false,visible:true}})">
			 ${csMember.csmChannel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmChannel}
	</s:if>
	
	${lz:set("注释","*****************营销方案字段的输入框代码*****************")}
	${lz:set("注释","before$csmMarketPlan和after$csmMarketPlan变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmMarketPlan==true">
	${before$csmMarketPlan}
	<dl class="csmMarketPlan " minor  ref="csmMarketPlan" >
		<dt>营销方案:</dt>
		<s:if test="#request.CTRL.e.csmMarketPlan==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMember$csmMarketPlan!=null">${csMember$csmMarketPlan}</s:if><s:else>
		 			<input title="请输入营销方案方案主题进行查询" class="combox narrow" action="${basePath}${proname}/operate/marketplan_query.do?value={param}&csmpHost={csmHost}" type="text" id="csmMarketPlan" name="csMember.csmMarketPlan" text="${get:CsMarketPlan(csMember.csmMarketPlan).csmpName}" value="${csMember.csmMarketPlan}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmMarketPlan').val())==''){return;};window.href('${basePath}${proname}/operate/marketplan_details.do?key='+$('#csmMarketPlan').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****营销方案字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmMarketPlan">${csMember.csmMarketPlan}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/marketplan_details.do?key=${csMember.csmMarketPlan}',{ctrl:{editable:false,visible:true}})">
			 ${csMember.csmMarketPlan$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmMarketPlan}
	</s:if>
	
	${lz:set("注释","*****************来源活动字段的输入框代码*****************")}
	${lz:set("注释","before$csmActivity和after$csmActivity变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmActivity==true">
	${before$csmActivity}
	<dl class="csmActivity " minor  ref="csmActivity" >
		<dt>来源活动:</dt>
		<s:if test="#request.CTRL.e.csmActivity==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMember$csmActivity!=null">${csMember$csmActivity}</s:if><s:else>
		 			<input title="请输入营销活动活动主题进行查询" class="combox narrow" action="${basePath}${proname}/operate/activitys/activity_query.do?value={param}&csaHost={csmHost}" type="text" id="csmActivity" name="csMember.csmActivity" text="${get:CsActivity(csMember.csmActivity).csaTitle}" value="${csMember.csmActivity}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmActivity').val())==''){return;};window.href('${basePath}${proname}/operate/activitys/activity_details.do?key='+$('#csmActivity').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****来源活动字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmActivity">${csMember.csmActivity}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/activitys/activity_details.do?key=${csMember.csmActivity}',{ctrl:{editable:false,visible:true}})">
			 ${csMember.csmActivity$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmActivity}
	</s:if>
	
	${lz:set("注释","*****************销售人员字段的输入框代码*****************")}
	${lz:set("注释","before$csmSaler和after$csmSaler变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmSaler==true">
	${before$csmSaler}
	<dl class="csmSaler " minor  ref="csmSaler" >
		<dt>销售人员:</dt>
		<s:if test="#request.CTRL.e.csmSaler==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMember$csmSaler!=null">${csMember$csmSaler}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csmSaler" name="csMember.csmSaler">
		 				<option selected value="${csMember.csmSaler}">
		 					${get:SrvUser(csMember.csmSaler).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmSaler').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csmSaler').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****销售人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmSaler">${csMember.csmSaler}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csMember.csmSaler}',{ctrl:{editable:false,visible:true}})">
			 ${csMember.csmSaler$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmSaler}
	</s:if>
	
	${lz:set("注释","*****************营销标记字段的输入框代码*****************")}
	${lz:set("注释","before$csmMarket和after$csmMarket变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmMarket==true">
	${before$csmMarket}
	<dl class="csmMarket " minor  ref="csmMarket" style="width:98%;">
		<dt>营销标记:</dt>
		<s:if test="#request.CTRL.e.csmMarket==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csMember$csmMarket!=null">${csMember$csmMarket}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csMember.csmMarket" id="csmMarket"/>
				    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'MEMBER_MARKET') )}
					<s:iterator value="#request.items" id="item" status="i">
			<label><input type="checkbox" ${lz:idin(csMember.csmMarket,item.sp_id)?"checked='checked'":""} value="#${item.sp_id}#" name="csMember.csmMarket" id="csmMarket"/>
					${item.sp_name}&nbsp;&nbsp;&nbsp;</label>
					</s:iterator>
					${lz:set("items",null)}
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的营销标记</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****营销标记字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmMarket">${csMember.csmMarket}</textarea>
		 		<span>
		 	
			 ${csMember.csmMarket$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmMarket}
	</s:if>
	
	${lz:set("注释","*****************会员标签字段的输入框代码*****************")}
	${lz:set("注释","before$csmTag和after$csmTag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmTag==true">
	${before$csmTag}
	<dl class="csmTag " minor  ref="csmTag" style="width:98%;">
		<dt>会员标签:</dt>
		<s:if test="#request.CTRL.e.csmTag==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csMember$csmTag!=null">${csMember$csmTag}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csMember.csmTag" id="csmTag"/>
				    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'MEMBER_TAG') )}
					<s:iterator value="#request.items" id="item" status="i">
			<label><input type="checkbox" ${lz:idin(csMember.csmTag,item.sp_id)?"checked='checked'":""} value="#${item.sp_id}#" name="csMember.csmTag" id="csmTag"/>
					${item.sp_name}&nbsp;&nbsp;&nbsp;</label>
					</s:iterator>
					${lz:set("items",null)}
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>(会员特性)标签用来对用户分类，在系统属性表中配置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员标签字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmTag">${csMember.csmTag}</textarea>
		 		<span>
		 	
			 ${csMember.csmTag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmTag}
	</s:if>
	
	${lz:set("注释","*****************备忘标记字段的输入框代码*****************")}
	${lz:set("注释","before$csmMark和after$csmMark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmMark==true">
	${before$csmMark}
	<dl class="csmMark " minor  ref="csmMark" style="width:98%;">
		<dt>备忘标记:</dt>
		<s:if test="#request.CTRL.e.csmMark==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csMember$csmMark!=null">${csMember$csmMark}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csMember.csmMark" id="csmMark"/>
				    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'MEMBER_MARK') )}
					<s:iterator value="#request.items" id="item" status="i">
			<label><input type="checkbox" ${lz:idin(csMember.csmMark,item.sp_id)?"checked='checked'":""} value="#${item.sp_id}#" name="csMember.csmMark" id="csmMark"/>
					${item.sp_name}&nbsp;&nbsp;&nbsp;</label>
					</s:iterator>
					${lz:set("items",null)}
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>(管理标记)管理人员对该会员做的备忘标记，在系统属性表中配置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘标记字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmMark">${csMember.csmMark}</textarea>
		 		<span>
		 	
			 ${csMember.csmMark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmMark}
	</s:if>
	
	${lz:set("注释","*****************回访标记字段的输入框代码*****************")}
	${lz:set("注释","before$csmVisitFlag和after$csmVisitFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmVisitFlag==true">
	${before$csmVisitFlag}
	<dl class="csmVisitFlag " minor  ref="csmVisitFlag" style="width:98%;">
		<dt>回访标记:</dt>
		<s:if test="#request.CTRL.e.csmVisitFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csMember$csmVisitFlag!=null">${csMember$csmVisitFlag}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csMember.csmVisitFlag" id="csmVisitFlag"/>
				    ${lz:set("items", lz:query("ccclubs_system","select * from cs_quest_template where 1=1") )}
					<s:iterator value="#request.items" id="item" status="i">
			<label><input type="checkbox" ${lz:idin(csMember.csmVisitFlag,item.csqt_id)?"checked='checked'":""} value="#${item.csqt_id}#" name="csMember.csmVisitFlag" id="csmVisitFlag"/>
					${item.csqt_name}&nbsp;&nbsp;&nbsp;</label>
					</s:iterator>
					${lz:set("items",null)}
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>(回访标记)对应回访模板,勾上了表示该模板已调查或已回访</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****回访标记字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmVisitFlag">${csMember.csmVisitFlag}</textarea>
		 		<span>
		 	
			 ${csMember.csmVisitFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmVisitFlag}
	</s:if>
	
	${lz:set("注释","*****************程序掩码字段的输入框代码*****************")}
	${lz:set("注释","before$csmMask和after$csmMask变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmMask==true">
	${before$csmMask}
	<dl class="csmMask " minor  ref="csmMask" style="width:98%;">
		<dt>程序掩码:</dt>
		<s:if test="#request.CTRL.e.csmMask==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csMember$csmMask!=null">${csMember$csmMask}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csMember.csmMask" id="csmMask"/>
			<label><input type="checkbox" ${lz:bitin(csMember.csmMask,1) ? "checked='checked'" : ""} value="1" ref="csmMask"/>已回流支付宝&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:bitin(csMember.csmMask,2) ? "checked='checked'" : ""} value="2" ref="csmMask"/>保留2&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:bitin(csMember.csmMask,4) ? "checked='checked'" : ""} value="4" ref="csmMask"/>保留4&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:bitin(csMember.csmMask,8) ? "checked='checked'" : ""} value="8" ref="csmMask"/>保留8&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>(程序标记)技术人员使用的标记，按位运算</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序掩码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmMask">${csMember.csmMask}</textarea>
		 		<span>
		 	
			 ${csMember.csmMask$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmMask}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csmRemark和after$csmRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmRemark==true">
	${before$csmRemark}
	<dl class="csmRemark " major  ref="csmRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csmRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csMember$csmRemark!=null">${csMember$csmRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csmRemark" name="csMember.csmRemark" rows="5">${csMember.csmRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员帐号的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csMember.csmRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmRemark}
	</s:if>
	
	${lz:set("注释","*****************手机认证字段的输入框代码*****************")}
	${lz:set("注释","before$csmVMobile和after$csmVMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmVMobile==true">
	${before$csmVMobile}
	<dl class="csmVMobile " minor  ref="csmVMobile" >
		<dt>手机认证:</dt>
		<s:if test="#request.CTRL.e.csmVMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMember$csmVMobile!=null">${csMember$csmVMobile}</s:if><s:else>
		 	<select class="narrow" id="csmVMobile" name="csMember.csmVMobile">
		 		<option value="">请选择</option>
				<option value="0" ${csMember.csmVMobile==0?"selected":""}>未认证</option>
				<option value="1" ${csMember.csmVMobile==1?"selected":""}>已认证</option>
				<option value="2" ${csMember.csmVMobile==2?"selected":""}>等待认证</option>
				<option value="3" ${csMember.csmVMobile==3?"selected":""}>认证失败</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的手机认证</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****手机认证字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmVMobile">${csMember.csmVMobile}</textarea>
		 		<span>
		 	
			 ${csMember.csmVMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmVMobile}
	</s:if>
	
	${lz:set("注释","*****************邮箱认证字段的输入框代码*****************")}
	${lz:set("注释","before$csmVEmail和after$csmVEmail变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmVEmail==true">
	${before$csmVEmail}
	<dl class="csmVEmail " minor  ref="csmVEmail" >
		<dt>邮箱认证:</dt>
		<s:if test="#request.CTRL.e.csmVEmail==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMember$csmVEmail!=null">${csMember$csmVEmail}</s:if><s:else>
		 	<select class="narrow" id="csmVEmail" name="csMember.csmVEmail">
		 		<option value="">请选择</option>
				<option value="0" ${csMember.csmVEmail==0?"selected":""}>未认证</option>
				<option value="1" ${csMember.csmVEmail==1?"selected":""}>已认证</option>
				<option value="2" ${csMember.csmVEmail==2?"selected":""}>等待认证</option>
				<option value="3" ${csMember.csmVEmail==3?"selected":""}>认证失败</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员帐号的邮箱认证</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****邮箱认证字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmVEmail">${csMember.csmVEmail}</textarea>
		 		<span>
		 	
			 ${csMember.csmVEmail$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmVEmail}
	</s:if>
	
	${lz:set("注释","*****************实名认证字段的输入框代码*****************")}
	${lz:set("注释","before$csmVReal和after$csmVReal变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmVReal==true">
	${before$csmVReal}
	<dl class="csmVReal " major  ref="csmVReal" >
		<dt>身份认证:</dt>
		<s:if test="#request.CTRL.e.csmVReal==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMember$csmVReal!=null">${csMember$csmVReal}{csMember.csmVReal}</s:if><s:else>
		 	<select class="narrow" id="csmVReal" name="csMember.csmVReal">
		 		<option value="">请选择</option>
				<option value="0" ${csMember.csmVReal==0?"selected":""}>未认证</option>
				<option value="1" ${csMember.csmVReal==1?"selected":""}>已认证</option>
				<option value="2" ${csMember.csmVReal==2?"selected":""}>等待认证</option>
				<option value="3" ${csMember.csmVReal==3?"selected":""}>认证失败</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请确认人员的单位和身份信息无误</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实名认证字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmVReal">${csMember.csmVReal}</textarea>
		 		<span>
		 	
			 ${csMember.csmVReal$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmVReal}
	</s:if>
	
	${lz:set("注释","*****************驾驶认证字段的输入框代码*****************")}
	${lz:set("注释","before$csmVDrive和after$csmVDrive变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmVDrive==true">
	${before$csmVDrive}
	<dl class="csmVDrive " major  ref="csmVDrive" >
		<dt>驾驶认证:</dt>
		<s:if test="#request.CTRL.e.csmVDrive==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMember$csmVDrive!=null">${csMember$csmVDrive}</s:if><s:else>
		 	<select class="narrow" id="csmVDrive" name="csMember.csmVDrive">
		 		<option value="">请选择</option>
				<option value="0" ${csMember.csmVDrive==0?"selected":""}>未认证</option>
				<option value="1" ${csMember.csmVDrive==1?"selected":""}>已认证</option>
				<option value="2" ${csMember.csmVDrive==2?"selected":""}>等待认证</option>
				<option value="3" ${csMember.csmVDrive==3?"selected":""}>认证失败</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请确认人员的驾驶证信息无误</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****驾驶认证字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmVDrive">${csMember.csmVDrive}</textarea>
		 		<span>
		 	
			 ${csMember.csmVDrive$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmVDrive}
	</s:if>
	
	${lz:set("注释","*****************工作认证字段的输入框代码*****************")}
	${lz:set("注释","before$csmVWork和after$csmVWork变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmVWork==true">
	${before$csmVWork}
	<dl class="csmVWork " major  ref="csmVWork" >
		<dt>工作认证:</dt>
		<s:if test="#request.CTRL.e.csmVWork==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMember$csmVWork!=null">${csMember$csmVWork}</s:if><s:else>
		 	<select class="narrow" id="csmVWork" name="csMember.csmVWork">
		 		<option value="">请选择</option>
				<option value="0" ${csMember.csmVWork==0?"selected":""}>未认证</option>
				<option value="1" ${csMember.csmVWork==1?"selected":""}>已认证</option>
				<option value="2" ${csMember.csmVWork==2?"selected":""}>等待认证</option>
				<option value="3" ${csMember.csmVWork==3?"selected":""}>认证失败</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请确认人员的单位和身份信息无误</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****工作认证字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmVWork">${csMember.csmVWork}</textarea>
		 		<span>
		 	
			 ${csMember.csmVWork$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmVWork}
	</s:if>
	
	${lz:set("注释","*****************线下认证字段的输入框代码*****************")}
	${lz:set("注释","before$csmVOffline和after$csmVOffline变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmVOffline==true">
	${before$csmVOffline}
	<dl class="csmVOffline " major  ref="csmVOffline" >
		<dt>线下认证:</dt>
		<s:if test="#request.CTRL.e.csmVOffline==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMember$csmVOffline!=null">${csMember$csmVOffline}</s:if><s:else>
		 	<select class="narrow" id="csmVOffline" name="csMember.csmVOffline" onchange="csmVOffline_select()">
		 		<option value="">请选择</option>
				<option value="0" ${csMember.csmVOffline==0?"selected":""}>未认证</option>
				<option value="1" ${csMember.csmVOffline==1?"selected":""}>已认证</option>
				<option value="2" ${csMember.csmVOffline==2?"selected":""}>等待认证</option>
				<option value="3" ${csMember.csmVOffline==3?"selected":""}>认证失败</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请确认人员的单位和身份信息无误</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****线下认证段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmVOffline">${csMember.csmVOffline}</textarea>
		 		<span>
		 	
			 ${csMember.csmVOffline$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmVOffline}
	</s:if>
	
	${lz:set("注释","*****************可用状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmStatus和after$csmStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmStatus==true">
	${before$csmStatus}
	<dl class="csmStatus " major  ref="csmStatus" >
		<dt>可用状态:</dt>
		<s:if test="#request.CTRL.e.csmStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMember$csmStatus!=null">${csMember$csmStatus}</s:if><s:else>
		 	<select class="narrow" id="csmStatus" name="csMember.csmStatus" onchange="csmStatus_select()">
		 		<option value="">请选择</option>
				<option value="1" ${csMember.csmStatus==1?"selected":""}>正常</option>
				<option value="0" ${csMember.csmStatus==0?"selected":""}>禁用</option>
				<option value="-1" ${csMember.csmStatus==2?"selected":""}>黑名单</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员帐号的可用状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****可用状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmStatus">${csMember.csmStatus}</textarea>
		 		<span>
		 	
			 ${csMember.csmStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmStatus}
	</s:if>
	
	${lz:set("注释","*****************实名认证总状态字段的输入框代码*****************")}
	${lz:set("注释","before$vstatus和after$vstatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.vStatus==true">
	${before$vstatus}
	<dl class="vstatus " major  ref="vstatus" >
		<dt>认证总状态:</dt>
		<s:if test="#request.CTRL.e.vstatus==true">
		${lz:set("haveEditable",false)}
		<dd input="select">
		<s:if test="#csMember$vstatus!=null">${csMember.vstatus}</s:if><s:else>
		 	<select class="narrow" id="vstatus" name="csMember.vstatus">
		 		<option value="0" ${csMember.vstatus==0?"selected":""}>未认证</option>
				<option value="1" ${csMember.vstatus==1?"selected":""}>已认证</option>
				<option value="2" ${csMember.vstatus==2?"selected":""}>等待认证</option>
				<option value="3" ${csMember.vstatus==3?"selected":""}>认证失败</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员帐号的可用状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实名认证总状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="vstatus">${csMember.vstatus}</textarea>
		 		<span>
		 	
			 ${csMember.vstatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$vstatus}
	</s:if>
	
	${lz:set("注释","*****************所属单位字段的输入框代码*****************")}
	${lz:set("注释","before$infoList和after$infoList变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.unitInfo==true && (csMember.csmVReal!=1 || csMember.csmVDrive!=1) && #request.unitPerson != null">
	${before$unitInfo}
	<dl class="unitInfo " major  ref="unitInfo" >
		<dt>所属单位:</dt>			
		<s:if test="#request.CTRL.e.unitInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.unitPerson!=null">
		 	<input title="请输入企业信息名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csupHost}" type="text" id="unitInfo" name="unitInfo" text="${get:CsUnitInfo(request.unitPerson.csupInfo).csuiName}" value="${request.unitPerson.csupInfo}" />
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#unitInfo').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?key='+$('#unitInfo').val(),{ctrl:{editable:false}})"></a>
	 	</s:if>
	 	 <b>*</b>
	 	 <em>请选择所属单位</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属单位字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="payMember-input narrow">
		 		<textarea class="" style="display:none;" id="unitInfo">${#request.unitPerson.$csupInfo.csuiName}</textarea>
		 		<span>
		 	
			 ${#request.unitPerson.$csupInfo.csuiName}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$unitInfo}
	</s:if>
	
	
	${lz:set("注释","*****************所属分组字段的输入框代码*****************")}
	${lz:set("注释","before$unitGroup和after$unitGroup变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.unitGroup==true && (csMember.csmVReal!=1 || csMember.csmVDrive!=1) && #request.unitPerson != null">
	${before$unitGroup}
	<dl class="unitGroup " major  ref="unitGroup" >
		<dt>所属部门:</dt>			
		<s:if test="#request.CTRL.e.unitGroup==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.unitPerson!=null">
		 	<input title="请输入分组信息组名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/group_query.do?value={param}&csugInfo={unitInfo}" type="text" id="unitGroup" name="unitGroup" text="${get:CsUnitGroup(request.unitPerson.csupGroup).csugName}" value="${request.unitPerson.csupGroup}" />
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#unitGroup').val())==''){return;};window.href('${basePath}${proname}/unit/group_details.do?key='+$('#unitGroup').val(),{ctrl:{editable:false}})"></a>
	 	</s:if>
	 	 
	 	 <b>*</b>
	 	 <em>请确认会员的所属部门</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属部门字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="payMember-input narrow">
		 		<textarea class="" style="display:none;" id="unitGroup">${#request.unitPerson.$csupGroup.csugName }</textarea>
		 		<span>
		 	
			 ${#request.unitPerson.$csupGroup.csugName}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$unitGroup}
	</s:if>
	
	${lz:set("注释","*****************支付会员字段的输入框代码*****************")}
	${lz:set("注释","before$payMember和after$payMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.payMember==true && (csMember.csmVReal!=1 || csMember.csmVDrive!=1) && #request.payMembers != null">
	${before$payMember}
	<dl class="payMember " major  ref="payMember" >
		<dt>支付账户:</dt>			
		<s:if test="#request.CTRL.e.payMember==true">
		${lz:set("haveEditable",true)}
		<dd input="select">${#request.payMembers }
		<s:if test="#request.payMembers!=null">
		 	<select class="narrow" id="payMember" name="payMember">
		 		<option value="">请选择</option>
				<s:iterator value="#request.payMembers" id="item" status="csmsPayer">
			 		<option value="${item.csmId}" ${#request.payMember==#item.csmId ? "selected":"" }>${item.csmName}</option>
				</s:iterator>
		 	</select>
	 	</s:if>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员支付账户</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付账户字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="payMember-input narrow">
		 		<textarea class="" style="display:none;" id="payMember">${payMembers}</textarea>
		 		<span>
		 	
			 ${payMembers}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$payMember}
	</s:if>
	
	
	
	${lz:set("注释","*****************禁用原因字段的输入框代码*****************")}
	${lz:set("注释","before$csmLockReason和after$csmLockReason变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	<s:if test="#request.CTRL.v.csmLockReason==true">
	${before$csmLockReason}
	<dl class="csmLockReason " major  ref="csmLockReason" id="csmLockReason_div" style="display:${#request.csMember$csmStatus == 0 ? "''": "none"}">
		<dt>禁用原因:</dt>
		<s:if test="#request.CTRL.e.csmLockReason==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmLockReason!=null">${csMember$csmLockReason}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmLockReason" id="csmLockReason"  value="${csMember.csmLockReason}"/>
	 	 </s:else>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****禁用原因字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmLockReason">${csMember.csmLockReason}</textarea>
		 		<span>
		 	
			 ${csMember.csmLockReason$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmLockReason}
	</s:if>

	
		
	${lz:set("注释","*****************机器编码字段的输入框代码*****************")}
	${lz:set("注释","before$csmVOfflineCode和after$csmVOfflineCode变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmVOfflineCode==true">
	${before$csmLockReason}
	<dl class="csmVOfflineCode " major  ref="csmVOfflineCode"  id="csmVOfflineCode_div" style="display:${#request.csMember$csmVOffline == 1 ? "''": "none"}" >
		<dt>机器编码:</dt>
		<s:if test="#request.CTRL.e.csmVOfflineCode==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMember$csmVOfflineCode!=null">${csMember$csmVOfflineCode}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmVOfflineCode" id="csmVOfflineCode"  value="${csMember.csmVOfflineCode}"/>
	 	 </s:else>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****机器编码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmVOfflineCode">${csMember.csmVOfflineCode}</textarea>
		 		<span>
		 	
			 ${csMember.csmVOfflineCode$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmVOfflineCode}
	</s:if>
	
	
	
	
	
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csMember.csmId}" name="csMember.csmId" id="csmId" />
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