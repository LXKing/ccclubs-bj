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
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csMember.csmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csmId,csmHost,csmUsername,csmGroup,csmMoney,csmNotRevenue,csmWeixinFlag,csmAlipayFlag,csmEmail,csmMobile,csmEvcard,csmExpress,csmTemp,csmName,csmInfo,csmOutlets,csmUpdateTime,csmAddTime,csmLastTime,csmFirstUse,csmLastUse,csmAllRecharge,csmAllOrderS,csmAllUseTime,csmAllViolatS,csmUnViolatS,csmAllTroubleS,csmFirstRecharge,csmRefundTime,csmAdder,csmTracker,csmRefer,csmReferType,csmFrom,csmSrc,csmChannel,csmMarketPlan,csmActivity,csmSaler,csmMarket,csmTag,csmMark,csmVisitFlag,csmMask,csmRemark,csmVMobile,csmVEmail,csmVReal,csmVDrive,csmVWork,csmVOffline,csmStatus,vstatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csmId,csmHost,csmUsername,csmGroup,csmMoney,csmCoupon,csmIntegral,csmGrow,csmGrade,csmRebate,csmNotRevenue,csmWeixinFlag,csmAlipayFlag,csmEmail,csmMobile,csmEvcard,csmExpress,csmTemp,csmName,csmInfo,csmOutlets,csmIsVip,csmVipStart,csmVipEnd,csmUpdateTime,csmAddTime,csmLastTime,csmLastIp,csmLoginS,csmFirstUse,csmLastUse,csmAllRecharge,csmAllOrderS,csmAllUseTime,csmAllViolatS,csmUnViolatS,csmAllTroubleS,csmFirstRecharge,csmRefundTime,csmRegistIp,csmAdder,csmTracker,csmRefer,csmReferType,csmFrom,csmSrc,csmChannel,csmMarketPlan,csmActivity,csmSaler,csmMarket,csmTag,csmMark,csmVisitFlag,csmMask,csmRemark,csmVMobile,csmVEmail,csmVReal,csmVDrive,csmVWork,csmVOffline,csmStatus,vstatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csmId,csmHost,csmMobile,csmEvcard,csmName,csmInfo,csmFrom,csmRemark,csmVReal,csmVDrive,csmVWork,csmVOffline,csmStatus,vstatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csmId,csmHost,csmMobile,csmEvcard,csmName,csmInfo,csmFrom,csmRemark,csmVReal,csmVDrive,csmVWork,csmVOffline,csmStatus,vstatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>会员帐号管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	<meta name="hashcode" content="${window.hashcode}">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		window.basePath="${basePath}";
		window.contextPath="${basePath}${proname}/";
		window.hashcode = "${window.hashcode}";
		window.apiUrl="${basePath}${proname}/api.do";
		window.handles={};
	</script>
		
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
	<!------------------------LAZY3Q_JSP_HEAD------------------------>
	<!------------------------LAZY3Q_JSP_HEAD------------------------>	
	
</head>  
<body>

${lz:set("注释","****非法权限时的友情提醒****")}
<s:if test="#request.canView!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>${lz:config("nolimit.message")}</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>


<!-- LAZY3Q_JSP_BODY -->
<s:iterator value="#request.page.result" id="item" status="i">
${lz:set("item$csmEvcard[]",(empty item.csmEvcard ? "<span style='color:#666666;'>[未绑定EV卡]</span>" : item.csmEvcard$))}
${lz:set("item$csmName[]",(empty item.csmName ? "<span style='color:#666666;'>[未完善]</span>" : item.csmName$))}
${lz:set("item$csmInfo[]",(empty item.csmInfo ? "<span style='color:#666666;'>[未完善]</span>" : item.csmInfo$))}
</s:iterator>
<script>
$(function(){
	$(".table thead td[title='EV卡']").attr("width",100);
	$(".table thead td[title='手机']").attr("width",100);
	$(".table thead td[title='实名认证']").attr("width",80);
	$(".table thead td[title='驾驶认证']").attr("width",80);
	window.handles={
		"充积分":recharge,"扣积分":recharge,
		"发短信":sendSms,"充小时":rehour,
		"重置":resetpwd,"预订":reserve,
		"备注":memo,"寄卡":print,
		"回访会员":function(opt,id){
			var url = "${basePath}admin/crm/revisit_edit.do";
			var params = {
				entrypoint:"${entrypoint}",
				ctrl:{
					fields:{
						csrMember:{
							defaultValue:id,
							editable:false
						},
						csrStatus:{
							defaultValue:1
						}	
					}
				}
			};
			href(url,params);
		},
		"标注会员":function(opt,id){
			var url = "${basePath}admin/user/member_edit.do";
			var params = {
				entrypoint:"${entrypoint}",
				id:id,
				ctrl:{
					title:"标注会员",
					visible:false,
					editable:false,fields:{
						csmId:{visible:true,editable:true},
						csmTag:{visible:true,editable:true}
					}
				}
			};
			href(url,params);
		},
		"备忘标记":function(opt,id){
			var url = "${basePath}admin/user/member_edit.do";
			var params = {
				entrypoint:"${entrypoint}",
				id:id,
				ctrl:{
					title:"标注会员",
					visible:false,
					editable:false,fields:{
						csmId:{visible:true,editable:true},
						csmMark:{visible:true,editable:true}
					}
				}
			};
			href(url,params);
		},
		"加入专管客户":function(opt,id){
			if(confirm("确定要添加该会员到您的专管客户吗?")){
				var url = "${basePath}admin/user/member_tracke.do";
				var params = {
					entrypoint:"${entrypoint}",
					id:id
				};
				$.submit(url,params);
			}
		}
	}
});

function print(operate,id){
	var url = "${basePath}admin/module/express-print.jsp?id="+id;
	window.open(url)
	//top.$.open({title:"打印快递单",url:url,width:$(top.window).width(),height:$(top.window).height()},{id:id});
}

function memo(operate,id){
	var url = "${basePath}admin/user/membermemo_edit.do";
	var params = {entrypoint:"${entrypoint}","csMemberMemo.csmmMember":id};
	href(url,params);
}
function recharge(operate,id){
	var url = "${basePath}admin/user/member_recharger.do";
	var params = {way:operate,entrypoint:"${entrypoint}",id:id};
	href(url,params);
}
function rechargeSel(operate){
	var checker=$(".check:checked");
	if(checker.length==0){
		$.tips("您未选中任何记录，请至少选择一项.");
		return;
	}
	var ids = "";
	checker.each(function(i){
		if(ids!="")
			ids+=",";
		ids+=$(this).val();
	});
	var url = "${basePath}admin/user/member_recharger.do";
	var params = {way:operate,entrypoint:"${entrypoint}",ids:ids};
	href(url,params);
}
function sendSms(operate,id){
	var mobiles = "";
	if(!id){
		var checker=$(".check:checked");
		if(checker.length==0){
			$.tips("您未选中任何记录，请至少选择一项.");
			return;
		}
		if($("td[ref='csmMobile']").size()==0){
			$.tips("当前未显示手机号码列，无法批量发送短信");
			return;
		}
		checker.each(function(i){
			if(mobiles!="")
				mobiles+=" ";
			mobiles+=$.trim($(this).parents("tr:eq(0)").find("td[ref='csmMobile']").text());
		});
		mobiles="[*]"+mobiles;
	}else{
		var list=$.getObject("${basePath}admin/user/member_query.do?object=true",{csmId:id});
		var member=null;
		if(list && list.length)
			member=list[0].object;
		if(!member || $.trim(member.csmMobile)==""){
			$.tips("该会员没有手机号码，不能发送短信");
			return;
		}
		mobiles = member.csmMobile;
	}
	var url = "${basePath}admin/systematic/smsmt_edit.do";
	var params = {
		entrypoint:"${entrypoint}",
		"csSmsMt.cssmMobile":mobiles
	};
	href(url,params);
}
function rehour(operate,id){
	var url = "${basePath}admin/user/freehour_edit.do";
	var params = {
		entrypoint:"${entrypoint}",
		ctrl:{
			fields:{
				csfhMember:{
					defaultValue:id,
					editable:false
				}			
			}
		}
	};
	href(url,params);
}
function resetpwd(operate,id){
	$.getInput({
		title:"请输入6到16位重置密码",
		callback:function(value){
			if($.trim(value)=="")
				return $.tips("未输入密码");
			if(value.length<6||value.length>16)
				return $.tips("密码长度应为6到16位");
			$.getObject("${basePath}admin/user/member_reset.do",{id:id,password:value},function(json){
				$.tips(json.message);
			});
		}
	});
}
function reserve(operate,id){
	var url = "${basePath}admin/service/order_edit.do";
	var params = {
		entrypoint:"${entrypoint}",
		ctrl:{
			title:'添加订单',
			fields:{
				csoPayMember:{
					defaultValue:id
				},
				csoUseMember:{
					defaultValue:id
				}			
			}
		}
	};	
	href(url,params);
}

<s:if test="#request.alias=='public'">
${lz:set("handles","加入专管客户,回访会员,标注会员,备忘标记")}
</s:if>


<s:if test="#request.alias=='dyna'">
$(function(){
	$("<a href=\"javascript:AddMyCustom()\" style=\"text-decoration:underline\">添加</a>").appendTo($(".table thead .operation").empty());
});
function AddMyCustom(){
	top.$.open({url:"${basePath}admin/user/member.select.do",width:$(window).width()},{
		csmTrackerYesNot:"yes",
		handles:"回访会员,标注会员,备忘标记"
	},function(id){
		if(id){
			{
				var url = "${basePath}admin/crm/revisit_edit.do";
				var params = {
					entrypoint:"${entrypoint}",
					ctrl:{
						fields:{
							csrMember:{
								defaultValue:id,
								editable:false
							},
							csrStatus:{
								defaultValue:1
							}	
						}
					}
				};
				top.$.open(url,params);
			}
			{
				var url = "${basePath}admin/user/member_tracke.do";
				var params = {
					entrypoint:"${entrypoint}",
					id:id
				};
				$.submit(url,params);
			}
		}
	});
}
</s:if>


<s:if test="#request.alias==null">

	<s:if test="#request.CTRL.canExp[1]==true">
	//批量发短信
	$(function(){
		var button = $("<a onclick=\"sendSms()\" href=\"javascript:void(0)\" class=\"btn\">发短信</a>");
		button.appendTo(".toolbar");
	})
	</s:if>
	<s:if test="#request.CTRL.canExp[2]==true">
	//批量充积分
	$(function(){
		//var button = $("<a onclick=\"rechargeSel('充积分')\" href=\"javascript:void(0)\" class=\"btn\">充积分</a>");
		//button.appendTo(".toolbar");
	})
	</s:if>
	<s:if test="#request.CTRL.canExp[3]==true">
	//批量扣积分
	$(function(){
		//var button = $("<a onclick=\"rechargeSel('扣积分')\" href=\"javascript:void(0)\" class=\"btn\">扣积分</a>");
		//button.appendTo(".toolbar");
	})
	</s:if>
		
	<s:if test="#request.CTRL.canExp[2]==true && #request.CTRL.canExp[3]==true">
	//Excel导入批量充积分扣积分
	$(function(){
		//var button = $('<a href=\"javascript:void(0)\" class="btn" onclick="">批量充积分扣积分</a>');
		//button.appendTo(".toolbar");
		//button.click(batch);
	})
	//Excel导入批量充积分扣积分
	function batch(){
		<lz:set name="batchForm">
			<form id="batchForm" name="batchForm" enctype="multipart/form-data" method="post" action="member_batch.do">
			  <input type="file" name="batch" id="batch" /></br>
			    请上传批量充积分的excel表格,文件格式如下(第一行记录为表头)：
			  </br>
				会员ID|订单ID|积分充值数|积分充值描述
			  <input type="hidden" value="${editorpoint}" id="editorpoint" name="editorpoint" />	
			  <input type="hidden" value="${entrypoint}" id="entrypoint" name="entrypoint" />
			  <input type="hidden" value="${lz:html(ctrl)}" id="ctrl" name="ctrl" />
			</form>
		</lz:set>
		$("${lz:js(batchForm)}").dialog({
			title:"批量充积分",
			modal:true,
			onOk:function(){
				$("#batchForm").submit();
			}
		});
	}
	</s:if>
	
	
	<s:if test="#request.CTRL.canExp[6]==true">
	//快速审核
	$(function(){
		//var button = $("<a onclick=\"fastCheck()\" href=\"javascript:void(0)\" class=\"btn\">快速审核</a>");
		//button.appendTo(".toolbar");
	});	
	function fastCheck(){
		top.$.open({url:"${basePath}admin/user/member_fastCheck.do?jsp=true",width:$(window).width(),height:$(window).height()});
	}
	
	</s:if>
	

</s:if>

</script>
<s:if test="#request.CTRL.canAdd==true">
<script>
$(function(){
	//var button = $('<a href=\"javascript:void(0)\" class="btn" onclick="">注册会员</a>');
	//button.appendTo(".toolbar");
	//button.click(regist);
	var btn = $('<a href=\"javascript:void(0)\" class="btn" onclick="">绑定会员卡</a>');
	btn.appendTo(".toolbar");
	btn.click(rebind);
})
/**
* 注册会员
**/
function regist(){		
	var url = "${basePath}admin/user/regist.do";
	var params = {entrypoint:"${entrypoint}",ctrl:${ctrl==null?"{title:'注册会员'}":ctrl}};
	href(url,params);
}
/**
* 绑定会员卡
**/
function rebind(){		
	var url = "${basePath}admin/user/rebind.do";
	var params = {entrypoint:"${entrypoint}",ctrl:${ctrl==null?"{title:'绑定会员卡'}":ctrl}};
	href(url,params);
}
</script>
</s:if>

<s:if test="#request.alias==null||#request.alias=='public'">
<lz:set name="shortcuts">
<a class="item ${T==1?"focus":""}" href="?T=1&hideQuery=true&title=最新注册会员&fields=csmId,csmHost,csmMobile,csmName,csmTag,csmMark,csmVisitFlag,csmRemark,csmAddTime&desc=csm_add_time">		
	<img align="absmiddle" src="${basePath}admin/images/icons/20088256427828778017.png"/>
	最新注册会员
</a>

<a class="item ${T==2?"focus":""}" href="?T=2&hideQuery=true&title=最新充值会员&fields=csmId,csmHost,csmMobile,csmName,csmTag,csmMark,csmVisitFlag,csmRemark,csmFirstRecharge&desc=csm_first_recharge">
	<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477806.png"/>
	最新充值会员
</a>

<a class="item ${T==3?"focus":""}" href="?T=3&hideQuery=true&title=首次用车会员&fields=csmId,csmHost,csmMobile,csmName,csmTag,csmMark,csmVisitFlag,csmRemark,csmFirstUse&desc=csm_first_use">
	<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477809.png"/>
	首次用车会员	
</a>

<a class="item ${T==4?"focus":""}" href="?T=4&hideQuery=true&title=最近用车会员&fields=csmId,csmHost,csmMobile,csmName,csmTag,csmMark,csmVisitFlag,csmRemark,csmLastUse,csmAllOrderS,csmAllUseTime&desc=csm_last_use">
	<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477804.png"/>
	最近用车会员	
</a>

<a class="item ${T==5?"focus":""}" href="?T=5&hideQuery=true&title=最新退款会员&fields=csmId,csmHost,csmMobile,csmName,csmTag,csmMark,csmVisitFlag,csmRemark,csmRefundTime&desc=csm_refund_time">
	<img align="absmiddle" src="${basePath}admin/images/icons/Wallet.png"/>
	最新退款会员
</a>

<a class="item ${T==6?"focus":""}" href="?T=6&hideQuery=true&title=订单最多会员&fields=csmId,csmHost,csmMobile,csmName,csmTag,csmMark,csmVisitFlag,csmRemark,csmAllOrderS&desc=csm_all_order_s">
	<img align="absmiddle" src="${basePath}admin/images/icons/200882564229234778010.png"/>
	订单最多会员
</a>

<a class="item ${T==7?"focus":""}" href="?T=7&hideQuery=true&title=用车时间最长&fields=csmId,csmHost,csmMobile,csmName,csmTag,csmMark,csmVisitFlag,csmRemark,csmAllUseTime&desc=csm_all_use_time">
	<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477803.png"/>
	用车时间最长
</a>

<a class="item ${T==8?"focus":""}" href="?T=8&hideQuery=true&title=充值最多会员&fields=csmId,csmHost,csmMobile,csmName,csmTag,csmMark,csmVisitFlag,csmRemark,csmAllRecharge&desc=csm_all_recharge">
	<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477806.png"/>
	充值最多会员
</a>

<a class="item ${T==9?"focus":""}" href="?T=9&hideQuery=true&title=等待审核会员&fields=csmId,csmHost,csmMobile,csmFrom,csmInfo,csmExpress,csmTag,csmMark,csmVisitFlag,csmRemark,csmVReal,csmVDrive,csmUpdateTime&csmVReal=2&csmVDrive=2&desc=csm_update_time">
	<img align="absmiddle" src="${basePath}admin/images/icons/20088256427828778012.png"/>
	等待审核会员
</a>

</lz:set>
</s:if>
<s:if test="#request.T==9">
	<lz:set name="memberinfo">
		<table class="memberinfo state-content" style="border:1px solid #ededed;box-shadow:0px 0px 8px #333333;background:#ffffff;" width="400" border="0" cellpadding="5" cellspacing="0">
		  <tr>
			<td width="140" class='BB' style='color:red;font-size:14px;'></td>
			<td width="241" class='CC' style='color:red;font-size:14px;'></td>
		  </tr>
		  <tr>
			<td colspan="2" class='DD' style='color:red;font-size:14px;'></td>
		  </tr>
		  <tr>
			<td colspan="2" class='AA'></td>
		  </tr>
		</table>
	</lz:set>
	<script>
	$(function(){
		$(".table tbody td[ref='csmInfo']").mouseenter(function(){
			$(".memberinfo").remove();
			var info = $.api("com.ccclubs.model.CsMemberInfo","get")($(this).attr("relate"));
			if(!info)return;
			var panel = $("${lz:js(memberinfo)}").appendTo("body");
			panel.find(".AA").html($.trim(info.csmiDriverImage)==""?"驾证图片未上传":("<a href='"+info.csmiDriverImage+"' target='_blank'>已上传驾证照片（点击查看）</a>"));
			panel.find(".BB").html($.trim(info.csmiName)==""?"资料未完善":info.csmiName);
			panel.find(".CC").html($.trim(info.csmiDriverNum)==""?"驾证号码未填写":info.csmiDriverNum);
			panel.find(".DD").html($.trim(info.csmiAddress)==""?"地址未填写":info.csmiAddress);
			panel.css("position","absolute");
			panel.css("left",($(this).offset().left+$(this).width())+"px");
			panel.css("top",$(this).offset().top+"px");
		});
	});
	</script>
</s:if>
<!-- LAZY3Q_JSP_BODY -->


${lz:set("querys",get:querys(servletPath))}
${lz:set("haveQuerys",lz:size(querys)>0)}

<div class="shortcuts state-content" style="display:${inFrame==null && (window.shortcuts!="hide" && (shortcuts!=null || haveQuerys))?"block":"none"};">
	<div class="shadow"></div>
	<div class="holder">
		<a class="item ${(T+Q+0)==0?"focus":""}" href="${location}">		
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256421631277806.png"/>
			默认数据列表
		</a>
		
		<s:iterator value="#request.querys" id="item" status="i">
		<a class="item ${Q==item.sqId?"focus":""}" onclick="$.submit('${item.sqPath}?Q=${item.sqId}',eval('('+$(this).find('textarea').val()+')'))" href="javascript:void(0);">
			<span ref="${item.sqId}">X</span>
			<img align="absmiddle" src="${item.sqIcon}"/>${item.sqName}
			<textarea style="display:none;">${item.sqParams}</textarea>
		</a>
		</s:iterator>
		
		
		${shortcuts}
	</div>
</div>

<table class="${inFrame==null && (window.shortcuts!="hide" && (shortcuts!=null || haveQuerys))?"margin-by-shortcut":""}" border="0" cellspacing="0" cellpadding="0"><tr><td>

${before$title}
	<s:if test="#request.CTRL.canQuery==true">
<div class="title">
	<a href="javascript:void(0);" onclick="parent.current.close();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Badge-multiply.png"/>
		关闭页面
	</a>
	<a href="javascript:void(0);" class="linker imize ${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize"))?"minimize":"maximize"}">
		<img align="absmiddle" class="up" src="${basePath}admin/images/icons/Arrow up.png"/>
		<img align="absmiddle" class="down" src="${basePath}admin/images/icons/Arrow down.png"/>
		展开收起
	</a>
	<a href="javascript:void(0);" class="linker setting">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256421529677801.png"/>
		页面设置
	</a>
	<a href="?${lz:querys("UTF-8","xxxxx")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/2008825642250778028.png"/>
		重新加载
	</a>
	<s:if test="#request.CTRL.canExport==true">
	<a href="javascript:void(0);" onclick="exportXls();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256427828778018.png"/>
		数据导出
	</a>
	</s:if>
	<s:if test="#request.alias==null">
		<s:if test="#request.CTRL.canStats==true">
	<a href="member_stats.do?${lz:querys("UTF-8","limitsid")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Statistics.png"/>
		数据统计
	</a>
		</s:if>
	</s:if>
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"会员帐号管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsMember" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="member.query.jsp"%>

		<div class="line"></div>
		<div class="option">			
			<table border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td>
					<input type="hidden" name="all" value="${all}" />
					<input type="hidden" name="fields" value="${fields}" />
					<button class="button" type="submit">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Zoom.png"/>
						查询&nbsp;&nbsp;</button>		
					<button class="button" onclick="clearForm()" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778045.png"/>
						重置&nbsp;&nbsp;</button>
					<button class="button" onclick="saveForm()" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
						保存&nbsp;&nbsp;</button>
					<s:if test="#request.alias=='join' || #request.alias=='select'">
					<button class="button onok" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						确定&nbsp;&nbsp;</button>
					<button class="button oncancel" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						放弃&nbsp;&nbsp;</button>
					</s:if>
				</td>
			  </tr>
			</table>
			<div class="clear"></div>
		</div>
	</form>
</div>
</s:if>

<s:if test="#request.haveQuery==false">
<script>
$(function(){
	$(".query").parent().prevAll().remove();
	$(".query").parent().remove();
	$(".pager:eq(0)").remove();
})
</script>
</s:if>
    
${after$form}
	
</td></tr><tr><td>
	<s:if test="#request.CTRL.canQuery==true">
  <%@include file="/admin/module/page.jsp"%>
  	</s:if>
  			
	<div class="content">
	    <div class="toolbar">
	    
	      ${before$toolbar}
	    	
	      <s:if test="#request.alias==null">
			<s:if test="#request.CTRL.canDel==true">
			<button type="button" class="del" onclick="DelSel();">
				删除
			</button>
			</s:if>
		  </s:if>
		  <s:if test="#request.alias==null">	
		  </s:if>
	    	
	      ${lz:set("items",lz:split(handles,","))}
		  <s:iterator value="#request.items" id="handle" status="i">
			${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
			<s:if test="#request.isMenu==true">
			<button type="button" class="edit" onclick="HandleMenu('${handle}');">
				${handle}
			</button>
			</s:if>
		  </s:iterator>
	    
		  
			<button type="button" class="all" onclick="$('.check').attr('checked',true)">
				全选
			</button>
			<button type="button" class="invert" onclick="$('.check').click();">
				反选
			</button>
			<dl ref="com.ccclubs.model.CsMember" class="display dropdowan" style="margin-left:30px;">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/display.png"/>
					${((all=="false" || all==null) && defines==null ) ? "主要内容":""}
					${(all=="true" && defines==null) ? "全部内容":""}
					${(defines!=null) ? "自定义显示":""}
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
						<a all="false" href="${basePath}${lz:replace(servletPath,"^/","")}?all=false&${lz:queryss("UTF-8","all","fields")}">主要内容</a>
						<a all="true" href="${basePath}${lz:replace(servletPath,"^/","")}?all=true&${lz:queryss("UTF-8","all","fields")}">全部内容</a>
						<a all="" href="${basePath}${lz:replace(servletPath,"^/","")}?${lz:queryss("UTF-8","all","fields")}">自定义显示</a>
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table>
				</dd>
			</dl>
			<s:if test="#request.CTRL.canEdit==true">
			<dl ref="com.ccclubs.model.CsMember" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.csmGroup || #request.defines['csmGroup']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('group')">所属组织</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csmNotRevenue || #request.defines['csmNotRevenue']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('not_revenue')">计算营收</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csmExpress || #request.defines['csmExpress']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('express')">快递单号</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csmTemp || #request.defines['csmTemp']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('temp')">邀请码</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csmIsVip || #request.defines['csmIsVip']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('e')">VIP状态</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csmTag || #request.defines['csmTag']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('tag')">会员标签</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csmMark || #request.defines['csmMark']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('mark')">备忘标记</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csmVisitFlag || #request.defines['csmVisitFlag']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('visited')">回访标记</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csmStatus || #request.defines['csmStatus']!=null">
						<a href="javascript:void(0);">可用状态</a>  <!-- onclick="UpdateSel('status')" -->
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.vstatus || #request.defines['vstatus']!=null">
						<a href="javascript:void(0);">认证总状态</a>
					</s:if>
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table
				</dd>
			</dl>
			</s:if>
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="member_table" ref="CsMember" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmId || #request.defines['csmId']!=null">
			 <td <s:if test="#request.defines['csmId']>0">colspan="${defines["csmId"]}" ${all?"width":"iwidth"}="${defines["csmId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csmId"   title="编号">
			 	<a class="${desc=="csm_id" ? "desc" : ""}${asc=="csm_id" ? "asc" : ""}" href="?${desc=="csm_id" ? "asc=csm_id" : ""}${(asc=="csm_id" || desc!="csm_id" )? "desc=csm_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csmId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmHost || #request.defines['csmHost']!=null">
			 <td <s:if test="#request.defines['csmHost']>0">colspan="${defines["csmHost"]}" ${all?"width":"iwidth"}="${defines["csmHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csmHost"   title="城市">
			 	<a class="${desc=="csm_host" ? "desc" : ""}${asc=="csm_host" ? "asc" : ""}" href="?${desc=="csm_host" ? "asc=csm_host" : ""}${(asc=="csm_host" || desc!="csm_host" )? "desc=csm_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csmHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmUsername || #request.defines['csmUsername']!=null">
			 <td <s:if test="#request.defines['csmUsername']>0">colspan="${defines["csmUsername"]}" ${all?"width":"iwidth"}="${defines["csmUsername"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="csmUsername"   title="用户名">
			 	<a class="${desc=="csm_username" ? "desc" : ""}${asc=="csm_username" ? "asc" : ""}" href="?${desc=="csm_username" ? "asc=csm_username" : ""}${(asc=="csm_username" || desc!="csm_username" )? "desc=csm_username" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	用户名
			 	</a>
			 	${lz:set("checkeds[]","csmUsername")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmGroup || #request.defines['csmGroup']!=null">
			 <td <s:if test="#request.defines['csmGroup']>0">colspan="${defines["csmGroup"]}" ${all?"width":"iwidth"}="${defines["csmGroup"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csmGroup"   title="所属组织">
			 	<a class="${desc=="csm_group" ? "desc" : ""}${asc=="csm_group" ? "asc" : ""}" href="?${desc=="csm_group" ? "asc=csm_group" : ""}${(asc=="csm_group" || desc!="csm_group" )? "desc=csm_group" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属组织
			 	</a>
			 	${lz:set("checkeds[]","csmGroup")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmMoney || #request.defines['csmMoney']!=null">
			 <td <s:if test="#request.defines['csmMoney']>0">colspan="${defines["csmMoney"]}" ${all?"width":"iwidth"}="${defines["csmMoney"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csmMoney"   title="余额">
			 	<a class="${desc=="csm_money" ? "desc" : ""}${asc=="csm_money" ? "asc" : ""}" href="?${desc=="csm_money" ? "asc=csm_money" : ""}${(asc=="csm_money" || desc!="csm_money" )? "desc=csm_money" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	余额
			 	</a>
			 	${lz:set("checkeds[]","csmMoney")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmCoupon || #request.defines['csmCoupon']!=null">
			 <td <s:if test="#request.defines['csmCoupon']>0">colspan="${defines["csmCoupon"]}" ${all?"width":"iwidth"}="${defines["csmCoupon"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csmCoupon"   title="现金券">
			 	<a class="${desc=="csm_coupon" ? "desc" : ""}${asc=="csm_coupon" ? "asc" : ""}" href="?${desc=="csm_coupon" ? "asc=csm_coupon" : ""}${(asc=="csm_coupon" || desc!="csm_coupon" )? "desc=csm_coupon" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	现金券
			 	</a>
			 	${lz:set("checkeds[]","csmCoupon")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmIntegral || #request.defines['csmIntegral']!=null">
			 <td <s:if test="#request.defines['csmIntegral']>0">colspan="${defines["csmIntegral"]}" ${all?"width":"iwidth"}="${defines["csmIntegral"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csmIntegral"   title="积分">
			 	<a class="${desc=="csm_integral" ? "desc" : ""}${asc=="csm_integral" ? "asc" : ""}" href="?${desc=="csm_integral" ? "asc=csm_integral" : ""}${(asc=="csm_integral" || desc!="csm_integral" )? "desc=csm_integral" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	积分
			 	</a>
			 	${lz:set("checkeds[]","csmIntegral")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmGrow || #request.defines['csmGrow']!=null">
			 <td <s:if test="#request.defines['csmGrow']>0">colspan="${defines["csmGrow"]}" ${all?"width":"iwidth"}="${defines["csmGrow"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csmGrow"   title="成长值">
			 	<a class="${desc=="csm_grow" ? "desc" : ""}${asc=="csm_grow" ? "asc" : ""}" href="?${desc=="csm_grow" ? "asc=csm_grow" : ""}${(asc=="csm_grow" || desc!="csm_grow" )? "desc=csm_grow" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	成长值
			 	</a>
			 	${lz:set("checkeds[]","csmGrow")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmGrade || #request.defines['csmGrade']!=null">
			 <td <s:if test="#request.defines['csmGrade']>0">colspan="${defines["csmGrade"]}" ${all?"width":"iwidth"}="${defines["csmGrade"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="csmGrade"   title="会员等级">
			 	<a class="${desc=="csm_grade" ? "desc" : ""}${asc=="csm_grade" ? "asc" : ""}" href="?${desc=="csm_grade" ? "asc=csm_grade" : ""}${(asc=="csm_grade" || desc!="csm_grade" )? "desc=csm_grade" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	会员等级
			 	</a>
			 	${lz:set("checkeds[]","csmGrade")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmRebate || #request.defines['csmRebate']!=null">
			 <td <s:if test="#request.defines['csmRebate']>0">colspan="${defines["csmRebate"]}" ${all?"width":"iwidth"}="${defines["csmRebate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csmRebate"   title="折扣">
			 	<a class="${desc=="csm_rebate" ? "desc" : ""}${asc=="csm_rebate" ? "asc" : ""}" href="?${desc=="csm_rebate" ? "asc=csm_rebate" : ""}${(asc=="csm_rebate" || desc!="csm_rebate" )? "desc=csm_rebate" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	折扣
			 	</a>
			 	${lz:set("checkeds[]","csmRebate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmNotRevenue || #request.defines['csmNotRevenue']!=null">
			 <td <s:if test="#request.defines['csmNotRevenue']>0">colspan="${defines["csmNotRevenue"]}" ${all?"width":"iwidth"}="${defines["csmNotRevenue"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="csmNotRevenue"   title="计算营收">
			 	<a class="${desc=="csm_not_revenue" ? "desc" : ""}${asc=="csm_not_revenue" ? "asc" : ""}" href="?${desc=="csm_not_revenue" ? "asc=csm_not_revenue" : ""}${(asc=="csm_not_revenue" || desc!="csm_not_revenue" )? "desc=csm_not_revenue" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	计算营收
			 	</a>
			 	${lz:set("checkeds[]","csmNotRevenue")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmWeixinFlag || #request.defines['csmWeixinFlag']!=null">
			 <td <s:if test="#request.defines['csmWeixinFlag']>0">colspan="${defines["csmWeixinFlag"]}" ${all?"width":"iwidth"}="${defines["csmWeixinFlag"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="csmWeixinFlag"   title="微信帐号标识">
			 	<a class="${desc=="csm_weixin_flag" ? "desc" : ""}${asc=="csm_weixin_flag" ? "asc" : ""}" href="?${desc=="csm_weixin_flag" ? "asc=csm_weixin_flag" : ""}${(asc=="csm_weixin_flag" || desc!="csm_weixin_flag" )? "desc=csm_weixin_flag" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	微信帐号标识
			 	</a>
			 	${lz:set("checkeds[]","csmWeixinFlag")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmAlipayFlag || #request.defines['csmAlipayFlag']!=null">
			 <td <s:if test="#request.defines['csmAlipayFlag']>0">colspan="${defines["csmAlipayFlag"]}" ${all?"width":"iwidth"}="${defines["csmAlipayFlag"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="csmAlipayFlag"   title="支付宝帐号标识">
			 	<a class="${desc=="csm_alipay_flag" ? "desc" : ""}${asc=="csm_alipay_flag" ? "asc" : ""}" href="?${desc=="csm_alipay_flag" ? "asc=csm_alipay_flag" : ""}${(asc=="csm_alipay_flag" || desc!="csm_alipay_flag" )? "desc=csm_alipay_flag" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	支付宝帐号标识
			 	</a>
			 	${lz:set("checkeds[]","csmAlipayFlag")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmEmail || #request.defines['csmEmail']!=null">
			 <td <s:if test="#request.defines['csmEmail']>0">colspan="${defines["csmEmail"]}" ${all?"width":"iwidth"}="${defines["csmEmail"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="csmEmail"   title="邮箱">
			 	<a class="${desc=="csm_email" ? "desc" : ""}${asc=="csm_email" ? "asc" : ""}" href="?${desc=="csm_email" ? "asc=csm_email" : ""}${(asc=="csm_email" || desc!="csm_email" )? "desc=csm_email" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	邮箱
			 	</a>
			 	${lz:set("checkeds[]","csmEmail")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmMobile || #request.defines['csmMobile']!=null">
			 <td <s:if test="#request.defines['csmMobile']>0">colspan="${defines["csmMobile"]}" ${all?"width":"iwidth"}="${defines["csmMobile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="15" ref="csmMobile"   title="手机">
			 	<a class="${desc=="csm_mobile" ? "desc" : ""}${asc=="csm_mobile" ? "asc" : ""}" href="?${desc=="csm_mobile" ? "asc=csm_mobile" : ""}${(asc=="csm_mobile" || desc!="csm_mobile" )? "desc=csm_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	手机
			 	</a>
			 	${lz:set("checkeds[]","csmMobile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmEvcard || #request.defines['csmEvcard']!=null">
			 <td <s:if test="#request.defines['csmEvcard']>0">colspan="${defines["csmEvcard"]}" ${all?"width":"iwidth"}="${defines["csmEvcard"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="16" ref="csmEvcard"   title="EV卡">
			 	<a class="${desc=="csm_evcard" ? "desc" : ""}${asc=="csm_evcard" ? "asc" : ""}" href="?${desc=="csm_evcard" ? "asc=csm_evcard" : ""}${(asc=="csm_evcard" || desc!="csm_evcard" )? "desc=csm_evcard" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	EV卡
			 	</a>
			 	${lz:set("checkeds[]","csmEvcard")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmExpress || #request.defines['csmExpress']!=null">
			 <td <s:if test="#request.defines['csmExpress']>0">colspan="${defines["csmExpress"]}" ${all?"width":"iwidth"}="${defines["csmExpress"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="17" ref="csmExpress"   title="快递单号">
			 	<a class="${desc=="csm_express" ? "desc" : ""}${asc=="csm_express" ? "asc" : ""}" href="?${desc=="csm_express" ? "asc=csm_express" : ""}${(asc=="csm_express" || desc!="csm_express" )? "desc=csm_express" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	快递单号
			 	</a>
			 	${lz:set("checkeds[]","csmExpress")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmTemp || #request.defines['csmTemp']!=null">
			 <td <s:if test="#request.defines['csmTemp']>0">colspan="${defines["csmTemp"]}" ${all?"width":"iwidth"}="${defines["csmTemp"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="18" ref="csmTemp"   title="邀请码">
			 	<a class="${desc=="csm_temp" ? "desc" : ""}${asc=="csm_temp" ? "asc" : ""}" href="?${desc=="csm_temp" ? "asc=csm_temp" : ""}${(asc=="csm_temp" || desc!="csm_temp" )? "desc=csm_temp" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	邀请码
			 	</a>
			 	${lz:set("checkeds[]","csmTemp")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmName || #request.defines['csmName']!=null">
			 <td <s:if test="#request.defines['csmName']>0">colspan="${defines["csmName"]}" ${all?"width":"iwidth"}="${defines["csmName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="19" ref="csmName" flagKey  title="真实姓名">
			 	<a class="${desc=="csm_name" ? "desc" : ""}${asc=="csm_name" ? "asc" : ""}" href="?${desc=="csm_name" ? "asc=csm_name" : ""}${(asc=="csm_name" || desc!="csm_name" )? "desc=csm_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	真实姓名
			 	</a>
			 	${lz:set("checkeds[]","csmName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmInfo || #request.defines['csmInfo']!=null">
			 <td <s:if test="#request.defines['csmInfo']>0">colspan="${defines["csmInfo"]}" ${all?"width":"iwidth"}="${defines["csmInfo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="20" ref="csmInfo"   title="详细信息">
			 	<a class="${desc=="csm_info" ? "desc" : ""}${asc=="csm_info" ? "asc" : ""}" href="?${desc=="csm_info" ? "asc=csm_info" : ""}${(asc=="csm_info" || desc!="csm_info" )? "desc=csm_info" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	详细信息
			 	</a>
			 	${lz:set("checkeds[]","csmInfo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmOutlets || #request.defines['csmOutlets']!=null">
			 <td <s:if test="#request.defines['csmOutlets']>0">colspan="${defines["csmOutlets"]}" ${all?"width":"iwidth"}="${defines["csmOutlets"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="21" ref="csmOutlets"   title="默认网点">
			 	<a class="${desc=="csm_outlets" ? "desc" : ""}${asc=="csm_outlets" ? "asc" : ""}" href="?${desc=="csm_outlets" ? "asc=csm_outlets" : ""}${(asc=="csm_outlets" || desc!="csm_outlets" )? "desc=csm_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	默认网点
			 	</a>
			 	${lz:set("checkeds[]","csmOutlets")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmIsVip || #request.defines['csmIsVip']!=null">
			 <td <s:if test="#request.defines['csmIsVip']>0">colspan="${defines["csmIsVip"]}" ${all?"width":"iwidth"}="${defines["csmIsVip"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="22" ref="csmIsVip"   title="VIP状态">
			 	<a class="${desc=="csm_is_vip" ? "desc" : ""}${asc=="csm_is_vip" ? "asc" : ""}" href="?${desc=="csm_is_vip" ? "asc=csm_is_vip" : ""}${(asc=="csm_is_vip" || desc!="csm_is_vip" )? "desc=csm_is_vip" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	VIP状态
			 	</a>
			 	${lz:set("checkeds[]","csmIsVip")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmVipStart || #request.defines['csmVipStart']!=null">
			 <td <s:if test="#request.defines['csmVipStart']>0">colspan="${defines["csmVipStart"]}" ${all?"width":"iwidth"}="${defines["csmVipStart"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="23" ref="csmVipStart"   title="VIP开始时间">
			 	<a class="${desc=="csm_vip_start" ? "desc" : ""}${asc=="csm_vip_start" ? "asc" : ""}" href="?${desc=="csm_vip_start" ? "asc=csm_vip_start" : ""}${(asc=="csm_vip_start" || desc!="csm_vip_start" )? "desc=csm_vip_start" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	VIP开始时间
			 	</a>
			 	${lz:set("checkeds[]","csmVipStart")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmVipEnd || #request.defines['csmVipEnd']!=null">
			 <td <s:if test="#request.defines['csmVipEnd']>0">colspan="${defines["csmVipEnd"]}" ${all?"width":"iwidth"}="${defines["csmVipEnd"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="24" ref="csmVipEnd"   title="VIP结束时间">
			 	<a class="${desc=="csm_vip_end" ? "desc" : ""}${asc=="csm_vip_end" ? "asc" : ""}" href="?${desc=="csm_vip_end" ? "asc=csm_vip_end" : ""}${(asc=="csm_vip_end" || desc!="csm_vip_end" )? "desc=csm_vip_end" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	VIP结束时间
			 	</a>
			 	${lz:set("checkeds[]","csmVipEnd")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmUpdateTime || #request.defines['csmUpdateTime']!=null">
			 <td <s:if test="#request.defines['csmUpdateTime']>0">colspan="${defines["csmUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csmUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="25" ref="csmUpdateTime"   title="更新时间">
			 	<a class="${desc=="csm_update_time" ? "desc" : ""}${asc=="csm_update_time" ? "asc" : ""}" href="?${desc=="csm_update_time" ? "asc=csm_update_time" : ""}${(asc=="csm_update_time" || desc!="csm_update_time" )? "desc=csm_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	更新时间
			 	</a>
			 	${lz:set("checkeds[]","csmUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmAddTime || #request.defines['csmAddTime']!=null">
			 <td <s:if test="#request.defines['csmAddTime']>0">colspan="${defines["csmAddTime"]}" ${all?"width":"iwidth"}="${defines["csmAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="26" ref="csmAddTime"   title="注册时间">
			 	<a class="${desc=="csm_add_time" ? "desc" : ""}${asc=="csm_add_time" ? "asc" : ""}" href="?${desc=="csm_add_time" ? "asc=csm_add_time" : ""}${(asc=="csm_add_time" || desc!="csm_add_time" )? "desc=csm_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	注册时间
			 	</a>
			 	${lz:set("checkeds[]","csmAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmLastTime || #request.defines['csmLastTime']!=null">
			 <td <s:if test="#request.defines['csmLastTime']>0">colspan="${defines["csmLastTime"]}" ${all?"width":"iwidth"}="${defines["csmLastTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="27" ref="csmLastTime"   title="最后登录">
			 	<a class="${desc=="csm_last_time" ? "desc" : ""}${asc=="csm_last_time" ? "asc" : ""}" href="?${desc=="csm_last_time" ? "asc=csm_last_time" : ""}${(asc=="csm_last_time" || desc!="csm_last_time" )? "desc=csm_last_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	最后登录
			 	</a>
			 	${lz:set("checkeds[]","csmLastTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmLastIp || #request.defines['csmLastIp']!=null">
			 <td <s:if test="#request.defines['csmLastIp']>0">colspan="${defines["csmLastIp"]}" ${all?"width":"iwidth"}="${defines["csmLastIp"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="28" ref="csmLastIp"   title="最后登录IP">
			 	<a class="${desc=="csm_last_ip" ? "desc" : ""}${asc=="csm_last_ip" ? "asc" : ""}" href="?${desc=="csm_last_ip" ? "asc=csm_last_ip" : ""}${(asc=="csm_last_ip" || desc!="csm_last_ip" )? "desc=csm_last_ip" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	最后登录IP
			 	</a>
			 	${lz:set("checkeds[]","csmLastIp")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmLoginS || #request.defines['csmLoginS']!=null">
			 <td <s:if test="#request.defines['csmLoginS']>0">colspan="${defines["csmLoginS"]}" ${all?"width":"iwidth"}="${defines["csmLoginS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="29" ref="csmLoginS"   title="登录次数">
			 	<a class="${desc=="csm_login_s" ? "desc" : ""}${asc=="csm_login_s" ? "asc" : ""}" href="?${desc=="csm_login_s" ? "asc=csm_login_s" : ""}${(asc=="csm_login_s" || desc!="csm_login_s" )? "desc=csm_login_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	登录次数
			 	</a>
			 	${lz:set("checkeds[]","csmLoginS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmFirstUse || #request.defines['csmFirstUse']!=null">
			 <td <s:if test="#request.defines['csmFirstUse']>0">colspan="${defines["csmFirstUse"]}" ${all?"width":"iwidth"}="${defines["csmFirstUse"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="30" ref="csmFirstUse"   title="首次用车">
			 	<a class="${desc=="csm_first_use" ? "desc" : ""}${asc=="csm_first_use" ? "asc" : ""}" href="?${desc=="csm_first_use" ? "asc=csm_first_use" : ""}${(asc=="csm_first_use" || desc!="csm_first_use" )? "desc=csm_first_use" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	首次用车
			 	</a>
			 	${lz:set("checkeds[]","csmFirstUse")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmLastUse || #request.defines['csmLastUse']!=null">
			 <td <s:if test="#request.defines['csmLastUse']>0">colspan="${defines["csmLastUse"]}" ${all?"width":"iwidth"}="${defines["csmLastUse"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="31" ref="csmLastUse"   title="最后使用">
			 	<a class="${desc=="csm_last_use" ? "desc" : ""}${asc=="csm_last_use" ? "asc" : ""}" href="?${desc=="csm_last_use" ? "asc=csm_last_use" : ""}${(asc=="csm_last_use" || desc!="csm_last_use" )? "desc=csm_last_use" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	最后使用
			 	</a>
			 	${lz:set("checkeds[]","csmLastUse")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmAllRecharge || #request.defines['csmAllRecharge']!=null">
			 <td <s:if test="#request.defines['csmAllRecharge']>0">colspan="${defines["csmAllRecharge"]}" ${all?"width":"iwidth"}="${defines["csmAllRecharge"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="32" ref="csmAllRecharge"   title="累计充值(元)">
			 	<a class="${desc=="csm_all_recharge" ? "desc" : ""}${asc=="csm_all_recharge" ? "asc" : ""}" href="?${desc=="csm_all_recharge" ? "asc=csm_all_recharge" : ""}${(asc=="csm_all_recharge" || desc!="csm_all_recharge" )? "desc=csm_all_recharge" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	累计充值(元)
			 	</a>
			 	${lz:set("checkeds[]","csmAllRecharge")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmAllOrderS || #request.defines['csmAllOrderS']!=null">
			 <td <s:if test="#request.defines['csmAllOrderS']>0">colspan="${defines["csmAllOrderS"]}" ${all?"width":"iwidth"}="${defines["csmAllOrderS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="33" ref="csmAllOrderS"   title="累计订单(个)">
			 	<a class="${desc=="csm_all_order_s" ? "desc" : ""}${asc=="csm_all_order_s" ? "asc" : ""}" href="?${desc=="csm_all_order_s" ? "asc=csm_all_order_s" : ""}${(asc=="csm_all_order_s" || desc!="csm_all_order_s" )? "desc=csm_all_order_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	累计订单(个)
			 	</a>
			 	${lz:set("checkeds[]","csmAllOrderS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmAllUseTime || #request.defines['csmAllUseTime']!=null">
			 <td <s:if test="#request.defines['csmAllUseTime']>0">colspan="${defines["csmAllUseTime"]}" ${all?"width":"iwidth"}="${defines["csmAllUseTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="34" ref="csmAllUseTime"   title="累计用车小时(小时)">
			 	<a class="${desc=="csm_all_use_time" ? "desc" : ""}${asc=="csm_all_use_time" ? "asc" : ""}" href="?${desc=="csm_all_use_time" ? "asc=csm_all_use_time" : ""}${(asc=="csm_all_use_time" || desc!="csm_all_use_time" )? "desc=csm_all_use_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	累计用车小时(小时)
			 	</a>
			 	${lz:set("checkeds[]","csmAllUseTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmAllViolatS || #request.defines['csmAllViolatS']!=null">
			 <td <s:if test="#request.defines['csmAllViolatS']>0">colspan="${defines["csmAllViolatS"]}" ${all?"width":"iwidth"}="${defines["csmAllViolatS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="35" ref="csmAllViolatS"   title="违章次数">
			 	<a class="${desc=="csm_all_violat_s" ? "desc" : ""}${asc=="csm_all_violat_s" ? "asc" : ""}" href="?${desc=="csm_all_violat_s" ? "asc=csm_all_violat_s" : ""}${(asc=="csm_all_violat_s" || desc!="csm_all_violat_s" )? "desc=csm_all_violat_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	违章次数
			 	</a>
			 	${lz:set("checkeds[]","csmAllViolatS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmUnViolatS || #request.defines['csmUnViolatS']!=null">
			 <td <s:if test="#request.defines['csmUnViolatS']>0">colspan="${defines["csmUnViolatS"]}" ${all?"width":"iwidth"}="${defines["csmUnViolatS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="36" ref="csmUnViolatS"   title="未处理违章">
			 	<a class="${desc=="csm_un_violat_s" ? "desc" : ""}${asc=="csm_un_violat_s" ? "asc" : ""}" href="?${desc=="csm_un_violat_s" ? "asc=csm_un_violat_s" : ""}${(asc=="csm_un_violat_s" || desc!="csm_un_violat_s" )? "desc=csm_un_violat_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	未处理违章
			 	</a>
			 	${lz:set("checkeds[]","csmUnViolatS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmAllTroubleS || #request.defines['csmAllTroubleS']!=null">
			 <td <s:if test="#request.defines['csmAllTroubleS']>0">colspan="${defines["csmAllTroubleS"]}" ${all?"width":"iwidth"}="${defines["csmAllTroubleS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="37" ref="csmAllTroubleS"   title="事故次数">
			 	<a class="${desc=="csm_all_trouble_s" ? "desc" : ""}${asc=="csm_all_trouble_s" ? "asc" : ""}" href="?${desc=="csm_all_trouble_s" ? "asc=csm_all_trouble_s" : ""}${(asc=="csm_all_trouble_s" || desc!="csm_all_trouble_s" )? "desc=csm_all_trouble_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	事故次数
			 	</a>
			 	${lz:set("checkeds[]","csmAllTroubleS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmFirstRecharge || #request.defines['csmFirstRecharge']!=null">
			 <td <s:if test="#request.defines['csmFirstRecharge']>0">colspan="${defines["csmFirstRecharge"]}" ${all?"width":"iwidth"}="${defines["csmFirstRecharge"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="38" ref="csmFirstRecharge"   title="首次充值">
			 	<a class="${desc=="csm_first_recharge" ? "desc" : ""}${asc=="csm_first_recharge" ? "asc" : ""}" href="?${desc=="csm_first_recharge" ? "asc=csm_first_recharge" : ""}${(asc=="csm_first_recharge" || desc!="csm_first_recharge" )? "desc=csm_first_recharge" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	首次充值
			 	</a>
			 	${lz:set("checkeds[]","csmFirstRecharge")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmRefundTime || #request.defines['csmRefundTime']!=null">
			 <td <s:if test="#request.defines['csmRefundTime']>0">colspan="${defines["csmRefundTime"]}" ${all?"width":"iwidth"}="${defines["csmRefundTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="39" ref="csmRefundTime"   title="最后退款">
			 	<a class="${desc=="csm_refund_time" ? "desc" : ""}${asc=="csm_refund_time" ? "asc" : ""}" href="?${desc=="csm_refund_time" ? "asc=csm_refund_time" : ""}${(asc=="csm_refund_time" || desc!="csm_refund_time" )? "desc=csm_refund_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	最后退款
			 	</a>
			 	${lz:set("checkeds[]","csmRefundTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmRegistIp || #request.defines['csmRegistIp']!=null">
			 <td <s:if test="#request.defines['csmRegistIp']>0">colspan="${defines["csmRegistIp"]}" ${all?"width":"iwidth"}="${defines["csmRegistIp"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="40" ref="csmRegistIp"   title="注册IP">
			 	<a class="${desc=="csm_regist_ip" ? "desc" : ""}${asc=="csm_regist_ip" ? "asc" : ""}" href="?${desc=="csm_regist_ip" ? "asc=csm_regist_ip" : ""}${(asc=="csm_regist_ip" || desc!="csm_regist_ip" )? "desc=csm_regist_ip" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	注册IP
			 	</a>
			 	${lz:set("checkeds[]","csmRegistIp")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmAdder || #request.defines['csmAdder']!=null">
			 <td <s:if test="#request.defines['csmAdder']>0">colspan="${defines["csmAdder"]}" ${all?"width":"iwidth"}="${defines["csmAdder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="41" ref="csmAdder"   title="添加人员">
			 	<a class="${desc=="csm_adder" ? "desc" : ""}${asc=="csm_adder" ? "asc" : ""}" href="?${desc=="csm_adder" ? "asc=csm_adder" : ""}${(asc=="csm_adder" || desc!="csm_adder" )? "desc=csm_adder" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加人员
			 	</a>
			 	${lz:set("checkeds[]","csmAdder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmTracker || #request.defines['csmTracker']!=null">
			 <td <s:if test="#request.defines['csmTracker']>0">colspan="${defines["csmTracker"]}" ${all?"width":"iwidth"}="${defines["csmTracker"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="42" ref="csmTracker"   title="跟踪人员">
			 	<a class="${desc=="csm_tracker" ? "desc" : ""}${asc=="csm_tracker" ? "asc" : ""}" href="?${desc=="csm_tracker" ? "asc=csm_tracker" : ""}${(asc=="csm_tracker" || desc!="csm_tracker" )? "desc=csm_tracker" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	跟踪人员
			 	</a>
			 	${lz:set("checkeds[]","csmTracker")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmRefer || #request.defines['csmRefer']!=null">
			 <td <s:if test="#request.defines['csmRefer']>0">colspan="${defines["csmRefer"]}" ${all?"width":"iwidth"}="${defines["csmRefer"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="43" ref="csmRefer"   title="推荐会员">
			 	<a class="${desc=="csm_refer" ? "desc" : ""}${asc=="csm_refer" ? "asc" : ""}" href="?${desc=="csm_refer" ? "asc=csm_refer" : ""}${(asc=="csm_refer" || desc!="csm_refer" )? "desc=csm_refer" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	推荐会员
			 	</a>
			 	${lz:set("checkeds[]","csmRefer")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmReferType || #request.defines['csmReferType']!=null">
			 <td <s:if test="#request.defines['csmReferType']>0">colspan="${defines["csmReferType"]}" ${all?"width":"iwidth"}="${defines["csmReferType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="44" ref="csmReferType"   title="推荐方式">
			 	<a class="${desc=="csm_refer_type" ? "desc" : ""}${asc=="csm_refer_type" ? "asc" : ""}" href="?${desc=="csm_refer_type" ? "asc=csm_refer_type" : ""}${(asc=="csm_refer_type" || desc!="csm_refer_type" )? "desc=csm_refer_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	推荐方式
			 	</a>
			 	${lz:set("checkeds[]","csmReferType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmFrom || #request.defines['csmFrom']!=null">
			 <td <s:if test="#request.defines['csmFrom']>0">colspan="${defines["csmFrom"]}" ${all?"width":"iwidth"}="${defines["csmFrom"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="45" ref="csmFrom"   title="注册平台">
			 	<a class="${desc=="csm_from" ? "desc" : ""}${asc=="csm_from" ? "asc" : ""}" href="?${desc=="csm_from" ? "asc=csm_from" : ""}${(asc=="csm_from" || desc!="csm_from" )? "desc=csm_from" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	注册平台
			 	</a>
			 	${lz:set("checkeds[]","csmFrom")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmSrc || #request.defines['csmSrc']!=null">
			 <td <s:if test="#request.defines['csmSrc']>0">colspan="${defines["csmSrc"]}" ${all?"width":"iwidth"}="${defines["csmSrc"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="46" ref="csmSrc"   title="注册源信息">
			 	<a class="${desc=="csm_src" ? "desc" : ""}${asc=="csm_src" ? "asc" : ""}" href="?${desc=="csm_src" ? "asc=csm_src" : ""}${(asc=="csm_src" || desc!="csm_src" )? "desc=csm_src" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	注册源信息
			 	</a>
			 	${lz:set("checkeds[]","csmSrc")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmChannel || #request.defines['csmChannel']!=null">
			 <td <s:if test="#request.defines['csmChannel']>0">colspan="${defines["csmChannel"]}" ${all?"width":"iwidth"}="${defines["csmChannel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="47" ref="csmChannel"   title="来源渠道">
			 	<a class="${desc=="csm_channel" ? "desc" : ""}${asc=="csm_channel" ? "asc" : ""}" href="?${desc=="csm_channel" ? "asc=csm_channel" : ""}${(asc=="csm_channel" || desc!="csm_channel" )? "desc=csm_channel" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	来源渠道
			 	</a>
			 	${lz:set("checkeds[]","csmChannel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmMarketPlan || #request.defines['csmMarketPlan']!=null">
			 <td <s:if test="#request.defines['csmMarketPlan']>0">colspan="${defines["csmMarketPlan"]}" ${all?"width":"iwidth"}="${defines["csmMarketPlan"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="48" ref="csmMarketPlan"   title="营销方案">
			 	<a class="${desc=="csm_market_plan" ? "desc" : ""}${asc=="csm_market_plan" ? "asc" : ""}" href="?${desc=="csm_market_plan" ? "asc=csm_market_plan" : ""}${(asc=="csm_market_plan" || desc!="csm_market_plan" )? "desc=csm_market_plan" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	营销方案
			 	</a>
			 	${lz:set("checkeds[]","csmMarketPlan")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmActivity || #request.defines['csmActivity']!=null">
			 <td <s:if test="#request.defines['csmActivity']>0">colspan="${defines["csmActivity"]}" ${all?"width":"iwidth"}="${defines["csmActivity"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="49" ref="csmActivity"   title="来源活动">
			 	<a class="${desc=="csm_activity" ? "desc" : ""}${asc=="csm_activity" ? "asc" : ""}" href="?${desc=="csm_activity" ? "asc=csm_activity" : ""}${(asc=="csm_activity" || desc!="csm_activity" )? "desc=csm_activity" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	来源活动
			 	</a>
			 	${lz:set("checkeds[]","csmActivity")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmSaler || #request.defines['csmSaler']!=null">
			 <td <s:if test="#request.defines['csmSaler']>0">colspan="${defines["csmSaler"]}" ${all?"width":"iwidth"}="${defines["csmSaler"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="50" ref="csmSaler"   title="销售人员">
			 	<a class="${desc=="csm_saler" ? "desc" : ""}${asc=="csm_saler" ? "asc" : ""}" href="?${desc=="csm_saler" ? "asc=csm_saler" : ""}${(asc=="csm_saler" || desc!="csm_saler" )? "desc=csm_saler" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	销售人员
			 	</a>
			 	${lz:set("checkeds[]","csmSaler")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmMarket || #request.defines['csmMarket']!=null">
			 <td <s:if test="#request.defines['csmMarket']>0">colspan="${defines["csmMarket"]}" ${all?"width":"iwidth"}="${defines["csmMarket"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="51" ref="csmMarket"   title="营销标记">
			 	<a class="${desc=="csm_market" ? "desc" : ""}${asc=="csm_market" ? "asc" : ""}" href="?${desc=="csm_market" ? "asc=csm_market" : ""}${(asc=="csm_market" || desc!="csm_market" )? "desc=csm_market" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	营销标记
			 	</a>
			 	${lz:set("checkeds[]","csmMarket")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmTag || #request.defines['csmTag']!=null">
			 <td <s:if test="#request.defines['csmTag']>0">colspan="${defines["csmTag"]}" ${all?"width":"iwidth"}="${defines["csmTag"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="52" ref="csmTag"   title="会员标签">
			 	<a class="${desc=="csm_tag" ? "desc" : ""}${asc=="csm_tag" ? "asc" : ""}" href="?${desc=="csm_tag" ? "asc=csm_tag" : ""}${(asc=="csm_tag" || desc!="csm_tag" )? "desc=csm_tag" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	会员标签
			 	</a>
			 	${lz:set("checkeds[]","csmTag")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmMark || #request.defines['csmMark']!=null">
			 <td <s:if test="#request.defines['csmMark']>0">colspan="${defines["csmMark"]}" ${all?"width":"iwidth"}="${defines["csmMark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="53" ref="csmMark"   title="备忘标记">
			 	<a class="${desc=="csm_mark" ? "desc" : ""}${asc=="csm_mark" ? "asc" : ""}" href="?${desc=="csm_mark" ? "asc=csm_mark" : ""}${(asc=="csm_mark" || desc!="csm_mark" )? "desc=csm_mark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备忘标记
			 	</a>
			 	${lz:set("checkeds[]","csmMark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmVisitFlag || #request.defines['csmVisitFlag']!=null">
			 <td <s:if test="#request.defines['csmVisitFlag']>0">colspan="${defines["csmVisitFlag"]}" ${all?"width":"iwidth"}="${defines["csmVisitFlag"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="54" ref="csmVisitFlag"   title="回访标记">
			 	<a class="${desc=="csm_visit_flag" ? "desc" : ""}${asc=="csm_visit_flag" ? "asc" : ""}" href="?${desc=="csm_visit_flag" ? "asc=csm_visit_flag" : ""}${(asc=="csm_visit_flag" || desc!="csm_visit_flag" )? "desc=csm_visit_flag" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	回访标记
			 	</a>
			 	${lz:set("checkeds[]","csmVisitFlag")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmMask || #request.defines['csmMask']!=null">
			 <td <s:if test="#request.defines['csmMask']>0">colspan="${defines["csmMask"]}" ${all?"width":"iwidth"}="${defines["csmMask"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="55" ref="csmMask"   title="程序掩码">
			 	<a class="${desc=="csm_mask" ? "desc" : ""}${asc=="csm_mask" ? "asc" : ""}" href="?${desc=="csm_mask" ? "asc=csm_mask" : ""}${(asc=="csm_mask" || desc!="csm_mask" )? "desc=csm_mask" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	程序掩码
			 	</a>
			 	${lz:set("checkeds[]","csmMask")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmRemark || #request.defines['csmRemark']!=null">
			 <td <s:if test="#request.defines['csmRemark']>0">colspan="${defines["csmRemark"]}" ${all?"width":"iwidth"}="${defines["csmRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="56" ref="csmRemark"   title="备注">
			 	<a class="${desc=="csm_remark" ? "desc" : ""}${asc=="csm_remark" ? "asc" : ""}" href="?${desc=="csm_remark" ? "asc=csm_remark" : ""}${(asc=="csm_remark" || desc!="csm_remark" )? "desc=csm_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 	${lz:set("checkeds[]","csmRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmVMobile || #request.defines['csmVMobile']!=null">
			 <td <s:if test="#request.defines['csmVMobile']>0">colspan="${defines["csmVMobile"]}" ${all?"width":"iwidth"}="${defines["csmVMobile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="57" ref="csmVMobile"   title="手机认证">
			 	<a class="${desc=="csm_v_mobile" ? "desc" : ""}${asc=="csm_v_mobile" ? "asc" : ""}" href="?${desc=="csm_v_mobile" ? "asc=csm_v_mobile" : ""}${(asc=="csm_v_mobile" || desc!="csm_v_mobile" )? "desc=csm_v_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	手机认证
			 	</a>
			 	${lz:set("checkeds[]","csmVMobile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmVEmail || #request.defines['csmVEmail']!=null">
			 <td <s:if test="#request.defines['csmVEmail']>0">colspan="${defines["csmVEmail"]}" ${all?"width":"iwidth"}="${defines["csmVEmail"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="58" ref="csmVEmail"   title="邮箱认证">
			 	<a class="${desc=="csm_v_email" ? "desc" : ""}${asc=="csm_v_email" ? "asc" : ""}" href="?${desc=="csm_v_email" ? "asc=csm_v_email" : ""}${(asc=="csm_v_email" || desc!="csm_v_email" )? "desc=csm_v_email" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	邮箱认证
			 	</a>
			 	${lz:set("checkeds[]","csmVEmail")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmVReal || #request.defines['csmVReal']!=null">
			 <td <s:if test="#request.defines['csmVReal']>0">colspan="${defines["csmVReal"]}" ${all?"width":"iwidth"}="${defines["csmVReal"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="59" ref="csmVReal"   title="实名认证">
			 	<a class="${desc=="csm_v_real" ? "desc" : ""}${asc=="csm_v_real" ? "asc" : ""}" href="?${desc=="csm_v_real" ? "asc=csm_v_real" : ""}${(asc=="csm_v_real" || desc!="csm_v_real" )? "desc=csm_v_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	身份认证
			 	</a>
			 	${lz:set("checkeds[]","csmVReal")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmVDrive || #request.defines['csmVDrive']!=null">
			 <td <s:if test="#request.defines['csmVDrive']>0">colspan="${defines["csmVDrive"]}" ${all?"width":"iwidth"}="${defines["csmVDrive"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="60" ref="csmVDrive"   title="驾驶认证">
			 	<a class="${desc=="csm_v_drive" ? "desc" : ""}${asc=="csm_v_drive" ? "asc" : ""}" href="?${desc=="csm_v_drive" ? "asc=csm_v_drive" : ""}${(asc=="csm_v_drive" || desc!="csm_v_drive" )? "desc=csm_v_drive" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	驾驶认证
			 	</a>
			 	${lz:set("checkeds[]","csmVDrive")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csmVWork || #request.defines['csmVWork']!=null">
			 <td <s:if test="#request.defines['csmVWork']>0">colspan="${defines["csmVWork"]}" ${all?"width":"iwidth"}="${defines["csmVWork"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="60" ref="csmVWork"   title="工作认证">
			 	<a class="${desc=="csm_v_work" ? "desc" : ""}${asc=="csm_v_work" ? "asc" : ""}" href="?${desc=="csm_v_work" ? "asc=csm_v_work" : ""}${(asc=="csm_v_work" || desc!="csm_v_work" )? "desc=csm_v_work" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	工作认证
			 	</a>
			 	${lz:set("checkeds[]","csmVWork")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csmVOffline || #request.defines['csmVOffline']!=null">
			 <td <s:if test="#request.defines['csmVOffline']>0">colspan="${defines["csmVOffline"]}" ${all?"width":"iwidth"}="${defines["csmVOffline"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="60" ref="csmVOffline"   title="线下认证">
			 	<a class="${desc=="csm_v_offline" ? "desc" : ""}${asc=="csm_v_offline" ? "asc" : ""}" href="?${desc=="csm_v_offline" ? "asc=csm_v_offline" : ""}${(asc=="csm_v_offline" || desc!="csm_v_offline" )? "desc=csm_v_offline" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	线下认证
			 	</a>
			 	${lz:set("checkeds[]","csmVOffline")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmStatus || #request.defines['csmStatus']!=null">
			 <td <s:if test="#request.defines['csmStatus']>0">colspan="${defines["csmStatus"]}" ${all?"width":"iwidth"}="${defines["csmStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="61" ref="csmStatus"   title="可用状态">
			 	<a class="${desc=="csm_status" ? "desc" : ""}${asc=="csm_status" ? "asc" : ""}" href="?${desc=="csm_status" ? "asc=csm_status" : ""}${(asc=="csm_status" || desc!="csm_status" )? "desc=csm_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	可用状态
			 	</a>
			 	${lz:set("checkeds[]","csmStatus")}
			 </td>
			 </s:if>
			 <s:if test="#request.defines==null && #request.CTRL.l.vstatus || #request.defines['vstatus']!=null">
			 <td <s:if test="#request.defines['vstatus']>0">colspan="${defines["vstatus"]}" ${all?"width":"iwidth"}="${defines["vstatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="61" ref="vstatus"   title="认证总状态">
			 	<a class="${desc=="vstatus" ? "desc" : ""}${asc=="vstatus" ? "asc" : ""}" href="?${desc=="vstatus" ? "asc=vstatus" : ""}${(asc=="vstatus" || desc!="vstatus" )? "desc=vstatus" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	认证总状态
			 	</a>
			 	${lz:set("checkeds[]","vstatus")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csmLockReason || #request.defines['csmLockReason']!=null">
			 <td <s:if test="#request.defines['csmLockReason']>0">colspan="${defines["csmLockReason"]}" ${all?"width":"iwidth"}="${defines["csmLockReason"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="61" ref="csmLockReason"   title="禁用原因">
			 	<a class="${desc=="csmLockReason" ? "desc" : ""}${asc=="csmLockReason" ? "asc" : ""}" href="?${desc=="csmLockReason" ? "asc=csmLockReason" : ""}${(asc=="csmLockReason" || desc!="csmLockReason" )? "desc=csmLockReason" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	禁用原因
			 	</a>
			 	${lz:set("checkeds[]","csmLockReason")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="62" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="63" ref="operation">
					&nbsp;
			</td>
		  </tr>
		  <s:if test="#request.childes!=null">
		  ${lz:set("注释","****级联字段:当某字段关联另外一个表时，并且用户勾选了要显示另外的字段****")}
		  <tr>
		  	<s:iterator value="#request.childes" id="fieldName" status="i">
		 	<td tdid="${fieldName}" ref="${fieldName}">
		 		${lz:set("field",lz:field(modelClass,fieldName))}
		 		<!-- <a class="${desc==field.notes.column.value ? "desc" : ""}${asc==field.notes.column.value ? "asc" : ""}" href="?${desc==field.notes.column.value ? (lz:join("asc=",field.notes.column.value)) : ""}${(asc==field.notes.column.value || desc!=field.notes.column.value )? (lz:join("desc=",field.notes.column.value)) : ""}&${lz:queryss("UTF-8","desc","asc")}"> -->
		 		${field.notes.caption.value}
		 		<!-- </a> -->
		 	</td>
		 	</s:iterator>
		 	${lz:set("checkeds[]",fieldName)}
		  </tr>
		  </s:if>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csmId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csmId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmId || #request.defines['csmId']!=null">
				<s:if test="#request.defines['csmId']>0">
					${lz:set("注释","****csmId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmId",lz:indexOf(fieldName,"csmId")>-1)}
				  		<s:if test="#request.atCsmId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmId字段的字串格式化输出****")}
					<td ref="csmId" class="td ">
						 
						 	${lz:or(item$csmId[i.count-1],lz:left(item.csmId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmHost || #request.defines['csmHost']!=null">
				<s:if test="#request.defines['csmHost']>0">
					${lz:set("注释","****csmHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmHost",lz:indexOf(fieldName,"csmHost")>-1)}
				  		<s:if test="#request.atCsmHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmHost字段的字串格式化输出****")}
					<td ref="csmHost" class="td " relate="${item.csmHost}">
						 
						 	<a <s:if test="#item.csmHost!=null && #item.csmHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csmHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmHost[i.count-1],lz:left(item.csmHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmUsername || #request.defines['csmUsername']!=null">
				<s:if test="#request.defines['csmUsername']>0">
					${lz:set("注释","****csmUsername关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmUsername))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmUsername",lz:indexOf(fieldName,"csmUsername")>-1)}
				  		<s:if test="#request.atCsmUsername==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmUsername字段的字串格式化输出****")}
					<td ref="csmUsername" class="td ">
						 
						 	${lz:or(item$csmUsername[i.count-1],lz:left(item.csmUsername$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmGroup || #request.defines['csmGroup']!=null">
				<s:if test="#request.defines['csmGroup']>0">
					${lz:set("注释","****csmGroup关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmGroup))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmGroup",lz:indexOf(fieldName,"csmGroup")>-1)}
				  		<s:if test="#request.atCsmGroup==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmGroup字段的字串格式化输出****")}
					<td ref="csmGroup" class="td " relate="${item.csmGroup}">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csmId}','group')"></a>
							 </s:if>
					     </s:if>
						 
						 	<a <s:if test="#item.csmGroup!=null && #item.csmGroup!=''"> onclick="window.href('${basePath}${proname}/user/membergroup_details.do?key=${item.csmGroup}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmGroup[i.count-1],lz:left(item.csmGroup$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmMoney || #request.defines['csmMoney']!=null">
				<s:if test="#request.defines['csmMoney']>0">
					${lz:set("注释","****csmMoney关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmMoney))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmMoney",lz:indexOf(fieldName,"csmMoney")>-1)}
				  		<s:if test="#request.atCsmMoney==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmMoney字段的字串格式化输出****")}
					<td ref="csmMoney" class="td ">
						 
						 	${lz:or(item$csmMoney[i.count-1],lz:left(item.csmMoney$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmCoupon || #request.defines['csmCoupon']!=null">
				<s:if test="#request.defines['csmCoupon']>0">
					${lz:set("注释","****csmCoupon关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmCoupon))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmCoupon",lz:indexOf(fieldName,"csmCoupon")>-1)}
				  		<s:if test="#request.atCsmCoupon==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmCoupon字段的字串格式化输出****")}
					<td ref="csmCoupon" class="td ">
						 
						 	${lz:or(item$csmCoupon[i.count-1],lz:left(item.csmCoupon$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmIntegral || #request.defines['csmIntegral']!=null">
				<s:if test="#request.defines['csmIntegral']>0">
					${lz:set("注释","****csmIntegral关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmIntegral))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmIntegral",lz:indexOf(fieldName,"csmIntegral")>-1)}
				  		<s:if test="#request.atCsmIntegral==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmIntegral字段的字串格式化输出****")}
					<td ref="csmIntegral" class="td ">
						 
						 	${lz:or(item$csmIntegral[i.count-1],lz:left(item.csmIntegral$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmGrow || #request.defines['csmGrow']!=null">
				<s:if test="#request.defines['csmGrow']>0">
					${lz:set("注释","****csmGrow关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmGrow))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmGrow",lz:indexOf(fieldName,"csmGrow")>-1)}
				  		<s:if test="#request.atCsmGrow==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmGrow字段的字串格式化输出****")}
					<td ref="csmGrow" class="td ">
						 
						 	${lz:or(item$csmGrow[i.count-1],lz:left(item.csmGrow$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmGrade || #request.defines['csmGrade']!=null">
				<s:if test="#request.defines['csmGrade']>0">
					${lz:set("注释","****csmGrade关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmGrade))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmGrade",lz:indexOf(fieldName,"csmGrade")>-1)}
				  		<s:if test="#request.atCsmGrade==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmGrade字段的字串格式化输出****")}
					<td ref="csmGrade" class="td ">
						 
						 	${lz:or(item$csmGrade[i.count-1],lz:left(item.csmGrade$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmRebate || #request.defines['csmRebate']!=null">
				<s:if test="#request.defines['csmRebate']>0">
					${lz:set("注释","****csmRebate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmRebate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmRebate",lz:indexOf(fieldName,"csmRebate")>-1)}
				  		<s:if test="#request.atCsmRebate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmRebate字段的字串格式化输出****")}
					<td ref="csmRebate" class="td ">
						 
						 	${lz:or(item$csmRebate[i.count-1],lz:left(item.csmRebate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmNotRevenue || #request.defines['csmNotRevenue']!=null">
				<s:if test="#request.defines['csmNotRevenue']>0">
					${lz:set("注释","****csmNotRevenue关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmNotRevenue))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmNotRevenue",lz:indexOf(fieldName,"csmNotRevenue")>-1)}
				  		<s:if test="#request.atCsmNotRevenue==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmNotRevenue字段的字串格式化输出****")}
					<td ref="csmNotRevenue" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csmId}','not_revenue')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csmNotRevenue[i.count-1],lz:left(item.csmNotRevenue$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmWeixinFlag || #request.defines['csmWeixinFlag']!=null">
				<s:if test="#request.defines['csmWeixinFlag']>0">
					${lz:set("注释","****csmWeixinFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmWeixinFlag))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmWeixinFlag",lz:indexOf(fieldName,"csmWeixinFlag")>-1)}
				  		<s:if test="#request.atCsmWeixinFlag==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmWeixinFlag字段的字串格式化输出****")}
					<td ref="csmWeixinFlag" class="td ">
						 
						 	${lz:or(item$csmWeixinFlag[i.count-1],lz:left(item.csmWeixinFlag$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmAlipayFlag || #request.defines['csmAlipayFlag']!=null">
				<s:if test="#request.defines['csmAlipayFlag']>0">
					${lz:set("注释","****csmAlipayFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmAlipayFlag))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmAlipayFlag",lz:indexOf(fieldName,"csmAlipayFlag")>-1)}
				  		<s:if test="#request.atCsmAlipayFlag==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmAlipayFlag字段的字串格式化输出****")}
					<td ref="csmAlipayFlag" class="td ">
						 
						 	${lz:or(item$csmAlipayFlag[i.count-1],lz:left(item.csmAlipayFlag$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmEmail || #request.defines['csmEmail']!=null">
				<s:if test="#request.defines['csmEmail']>0">
					${lz:set("注释","****csmEmail关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmEmail))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmEmail",lz:indexOf(fieldName,"csmEmail")>-1)}
				  		<s:if test="#request.atCsmEmail==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmEmail字段的字串格式化输出****")}
					<td ref="csmEmail" class="td ">
						 
						 	${lz:or(item$csmEmail[i.count-1],lz:left(item.csmEmail$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmMobile || #request.defines['csmMobile']!=null">
				<s:if test="#request.defines['csmMobile']>0">
					${lz:set("注释","****csmMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmMobile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmMobile",lz:indexOf(fieldName,"csmMobile")>-1)}
				  		<s:if test="#request.atCsmMobile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmMobile字段的字串格式化输出****")}
					<td ref="csmMobile" class="td ">
						 
						 	${lz:or(item$csmMobile[i.count-1],lz:left(item.csmMobile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmEvcard || #request.defines['csmEvcard']!=null">
				<s:if test="#request.defines['csmEvcard']>0">
					${lz:set("注释","****csmEvcard关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmEvcard))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmEvcard",lz:indexOf(fieldName,"csmEvcard")>-1)}
				  		<s:if test="#request.atCsmEvcard==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmEvcard字段的字串格式化输出****")}
					<td ref="csmEvcard" class="td " relate="${item.csmEvcard}">
						 
						 	<a <s:if test="#item.csmEvcard!=null && #item.csmEvcard!=''"> onclick="window.href('${basePath}${proname}/object/evcard_details.do?key=${item.csmEvcard}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmEvcard[i.count-1],lz:left(item.csmEvcard$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmExpress || #request.defines['csmExpress']!=null">
				<s:if test="#request.defines['csmExpress']>0">
					${lz:set("注释","****csmExpress关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmExpress))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmExpress",lz:indexOf(fieldName,"csmExpress")>-1)}
				  		<s:if test="#request.atCsmExpress==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmExpress字段的字串格式化输出****")}
					<td ref="csmExpress" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csmId}','express')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csmExpress[i.count-1],lz:left(item.csmExpress$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmTemp || #request.defines['csmTemp']!=null">
				<s:if test="#request.defines['csmTemp']>0">
					${lz:set("注释","****csmTemp关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmTemp))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmTemp",lz:indexOf(fieldName,"csmTemp")>-1)}
				  		<s:if test="#request.atCsmTemp==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmTemp字段的字串格式化输出****")}
					<td ref="csmTemp" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csmId}','temp')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csmTemp[i.count-1],lz:left(item.csmTemp$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmName || #request.defines['csmName']!=null">
				<s:if test="#request.defines['csmName']>0">
					${lz:set("注释","****csmName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmName",lz:indexOf(fieldName,"csmName")>-1)}
				  		<s:if test="#request.atCsmName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmName字段的字串格式化输出****")}
					<td ref="csmName" class="td  node">
						 
						 	${lz:or(item$csmName[i.count-1],lz:left(item.csmName$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmInfo || #request.defines['csmInfo']!=null">
				<s:if test="#request.defines['csmInfo']>0">
					${lz:set("注释","****csmInfo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmInfo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmInfo",lz:indexOf(fieldName,"csmInfo")>-1)}
				  		<s:if test="#request.atCsmInfo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmInfo字段的字串格式化输出****")}
					<td ref="csmInfo" class="td " relate="${item.csmInfo}">
						 
						 	<a <s:if test="#item.csmInfo!=null && #item.csmInfo!=''"> onclick="window.href('${basePath}${proname}/user/memberinfo_details.do?key=${item.csmInfo}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmInfo[i.count-1],lz:left(item.csmInfo$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmOutlets || #request.defines['csmOutlets']!=null">
				<s:if test="#request.defines['csmOutlets']>0">
					${lz:set("注释","****csmOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmOutlets))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmOutlets",lz:indexOf(fieldName,"csmOutlets")>-1)}
				  		<s:if test="#request.atCsmOutlets==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmOutlets字段的字串格式化输出****")}
					<td ref="csmOutlets" class="td " relate="${item.csmOutlets}">
						 
						 	<a <s:if test="#item.csmOutlets!=null && #item.csmOutlets!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.csmOutlets}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmOutlets[i.count-1],lz:left(item.csmOutlets$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmIsVip || #request.defines['csmIsVip']!=null">
				<s:if test="#request.defines['csmIsVip']>0">
					${lz:set("注释","****csmIsVip关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmIsVip))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmIsVip",lz:indexOf(fieldName,"csmIsVip")>-1)}
				  		<s:if test="#request.atCsmIsVip==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmIsVip字段的字串格式化输出****")}
					<td ref="csmIsVip" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csmId}','e')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csmIsVip[i.count-1],lz:left(item.csmIsVip$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmVipStart || #request.defines['csmVipStart']!=null">
				<s:if test="#request.defines['csmVipStart']>0">
					${lz:set("注释","****csmVipStart关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmVipStart))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmVipStart",lz:indexOf(fieldName,"csmVipStart")>-1)}
				  		<s:if test="#request.atCsmVipStart==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmVipStart字段的字串格式化输出****")}
					<td ref="csmVipStart" class="td ">
						 
						 	${lz:or(item$csmVipStart[i.count-1],lz:left(item.csmVipStart$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmVipEnd || #request.defines['csmVipEnd']!=null">
				<s:if test="#request.defines['csmVipEnd']>0">
					${lz:set("注释","****csmVipEnd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmVipEnd))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmVipEnd",lz:indexOf(fieldName,"csmVipEnd")>-1)}
				  		<s:if test="#request.atCsmVipEnd==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmVipEnd字段的字串格式化输出****")}
					<td ref="csmVipEnd" class="td ">
						 
						 	${lz:or(item$csmVipEnd[i.count-1],lz:left(item.csmVipEnd$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmUpdateTime || #request.defines['csmUpdateTime']!=null">
				<s:if test="#request.defines['csmUpdateTime']>0">
					${lz:set("注释","****csmUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmUpdateTime",lz:indexOf(fieldName,"csmUpdateTime")>-1)}
				  		<s:if test="#request.atCsmUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmUpdateTime字段的字串格式化输出****")}
					<td ref="csmUpdateTime" class="td ">
						 
						 	${lz:or(item$csmUpdateTime[i.count-1],lz:left(item.csmUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmAddTime || #request.defines['csmAddTime']!=null">
				<s:if test="#request.defines['csmAddTime']>0">
					${lz:set("注释","****csmAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmAddTime",lz:indexOf(fieldName,"csmAddTime")>-1)}
				  		<s:if test="#request.atCsmAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmAddTime字段的字串格式化输出****")}
					<td ref="csmAddTime" class="td ">
						 
						 	${lz:or(item$csmAddTime[i.count-1],lz:left(item.csmAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmLastTime || #request.defines['csmLastTime']!=null">
				<s:if test="#request.defines['csmLastTime']>0">
					${lz:set("注释","****csmLastTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmLastTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmLastTime",lz:indexOf(fieldName,"csmLastTime")>-1)}
				  		<s:if test="#request.atCsmLastTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmLastTime字段的字串格式化输出****")}
					<td ref="csmLastTime" class="td ">
						 
						 	${lz:or(item$csmLastTime[i.count-1],lz:left(item.csmLastTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmLastIp || #request.defines['csmLastIp']!=null">
				<s:if test="#request.defines['csmLastIp']>0">
					${lz:set("注释","****csmLastIp关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmLastIp))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmLastIp",lz:indexOf(fieldName,"csmLastIp")>-1)}
				  		<s:if test="#request.atCsmLastIp==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmLastIp字段的字串格式化输出****")}
					<td ref="csmLastIp" class="td ">
						 
						 	${lz:or(item$csmLastIp[i.count-1],lz:left(item.csmLastIp$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmLoginS || #request.defines['csmLoginS']!=null">
				<s:if test="#request.defines['csmLoginS']>0">
					${lz:set("注释","****csmLoginS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmLoginS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmLoginS",lz:indexOf(fieldName,"csmLoginS")>-1)}
				  		<s:if test="#request.atCsmLoginS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmLoginS字段的字串格式化输出****")}
					<td ref="csmLoginS" class="td ">
						 
						 	${lz:or(item$csmLoginS[i.count-1],lz:left(item.csmLoginS$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmFirstUse || #request.defines['csmFirstUse']!=null">
				<s:if test="#request.defines['csmFirstUse']>0">
					${lz:set("注释","****csmFirstUse关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmFirstUse))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmFirstUse",lz:indexOf(fieldName,"csmFirstUse")>-1)}
				  		<s:if test="#request.atCsmFirstUse==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmFirstUse字段的字串格式化输出****")}
					<td ref="csmFirstUse" class="td ">
						 
						 	${lz:or(item$csmFirstUse[i.count-1],lz:left(item.csmFirstUse$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmLastUse || #request.defines['csmLastUse']!=null">
				<s:if test="#request.defines['csmLastUse']>0">
					${lz:set("注释","****csmLastUse关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmLastUse))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmLastUse",lz:indexOf(fieldName,"csmLastUse")>-1)}
				  		<s:if test="#request.atCsmLastUse==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmLastUse字段的字串格式化输出****")}
					<td ref="csmLastUse" class="td ">
						 
						 	${lz:or(item$csmLastUse[i.count-1],lz:left(item.csmLastUse$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmAllRecharge || #request.defines['csmAllRecharge']!=null">
				<s:if test="#request.defines['csmAllRecharge']>0">
					${lz:set("注释","****csmAllRecharge关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmAllRecharge))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmAllRecharge",lz:indexOf(fieldName,"csmAllRecharge")>-1)}
				  		<s:if test="#request.atCsmAllRecharge==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmAllRecharge字段的字串格式化输出****")}
					<td ref="csmAllRecharge" class="td ">
						 
						 	${lz:or(item$csmAllRecharge[i.count-1],lz:left(item.csmAllRecharge$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmAllOrderS || #request.defines['csmAllOrderS']!=null">
				<s:if test="#request.defines['csmAllOrderS']>0">
					${lz:set("注释","****csmAllOrderS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmAllOrderS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmAllOrderS",lz:indexOf(fieldName,"csmAllOrderS")>-1)}
				  		<s:if test="#request.atCsmAllOrderS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmAllOrderS字段的字串格式化输出****")}
					<td ref="csmAllOrderS" class="td ">
						 
						 	${lz:or(item$csmAllOrderS[i.count-1],lz:left(item.csmAllOrderS$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmAllUseTime || #request.defines['csmAllUseTime']!=null">
				<s:if test="#request.defines['csmAllUseTime']>0">
					${lz:set("注释","****csmAllUseTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmAllUseTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmAllUseTime",lz:indexOf(fieldName,"csmAllUseTime")>-1)}
				  		<s:if test="#request.atCsmAllUseTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmAllUseTime字段的字串格式化输出****")}
					<td ref="csmAllUseTime" class="td ">
						 
						 	${lz:or(item$csmAllUseTime[i.count-1],lz:left(item.csmAllUseTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmAllViolatS || #request.defines['csmAllViolatS']!=null">
				<s:if test="#request.defines['csmAllViolatS']>0">
					${lz:set("注释","****csmAllViolatS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmAllViolatS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmAllViolatS",lz:indexOf(fieldName,"csmAllViolatS")>-1)}
				  		<s:if test="#request.atCsmAllViolatS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmAllViolatS字段的字串格式化输出****")}
					<td ref="csmAllViolatS" class="td ">
						 
						 	${lz:or(item$csmAllViolatS[i.count-1],lz:left(item.csmAllViolatS$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmUnViolatS || #request.defines['csmUnViolatS']!=null">
				<s:if test="#request.defines['csmUnViolatS']>0">
					${lz:set("注释","****csmUnViolatS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmUnViolatS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmUnViolatS",lz:indexOf(fieldName,"csmUnViolatS")>-1)}
				  		<s:if test="#request.atCsmUnViolatS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmUnViolatS字段的字串格式化输出****")}
					<td ref="csmUnViolatS" class="td ">
						 
						 	${lz:or(item$csmUnViolatS[i.count-1],lz:left(item.csmUnViolatS$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmAllTroubleS || #request.defines['csmAllTroubleS']!=null">
				<s:if test="#request.defines['csmAllTroubleS']>0">
					${lz:set("注释","****csmAllTroubleS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmAllTroubleS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmAllTroubleS",lz:indexOf(fieldName,"csmAllTroubleS")>-1)}
				  		<s:if test="#request.atCsmAllTroubleS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmAllTroubleS字段的字串格式化输出****")}
					<td ref="csmAllTroubleS" class="td ">
						 
						 	${lz:or(item$csmAllTroubleS[i.count-1],lz:left(item.csmAllTroubleS$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmFirstRecharge || #request.defines['csmFirstRecharge']!=null">
				<s:if test="#request.defines['csmFirstRecharge']>0">
					${lz:set("注释","****csmFirstRecharge关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmFirstRecharge))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmFirstRecharge",lz:indexOf(fieldName,"csmFirstRecharge")>-1)}
				  		<s:if test="#request.atCsmFirstRecharge==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmFirstRecharge字段的字串格式化输出****")}
					<td ref="csmFirstRecharge" class="td ">
						 
						 	${lz:or(item$csmFirstRecharge[i.count-1],lz:left(item.csmFirstRecharge$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmRefundTime || #request.defines['csmRefundTime']!=null">
				<s:if test="#request.defines['csmRefundTime']>0">
					${lz:set("注释","****csmRefundTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmRefundTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmRefundTime",lz:indexOf(fieldName,"csmRefundTime")>-1)}
				  		<s:if test="#request.atCsmRefundTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmRefundTime字段的字串格式化输出****")}
					<td ref="csmRefundTime" class="td ">
						 
						 	${lz:or(item$csmRefundTime[i.count-1],lz:left(item.csmRefundTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmRegistIp || #request.defines['csmRegistIp']!=null">
				<s:if test="#request.defines['csmRegistIp']>0">
					${lz:set("注释","****csmRegistIp关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmRegistIp))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmRegistIp",lz:indexOf(fieldName,"csmRegistIp")>-1)}
				  		<s:if test="#request.atCsmRegistIp==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmRegistIp字段的字串格式化输出****")}
					<td ref="csmRegistIp" class="td ">
						 
						 	${lz:or(item$csmRegistIp[i.count-1],lz:left(item.csmRegistIp$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmAdder || #request.defines['csmAdder']!=null">
				<s:if test="#request.defines['csmAdder']>0">
					${lz:set("注释","****csmAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmAdder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmAdder",lz:indexOf(fieldName,"csmAdder")>-1)}
				  		<s:if test="#request.atCsmAdder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmAdder字段的字串格式化输出****")}
					<td ref="csmAdder" class="td " relate="${item.csmAdder}">
						 
						 	<a <s:if test="#item.csmAdder!=null && #item.csmAdder!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csmAdder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmAdder[i.count-1],lz:left(item.csmAdder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmTracker || #request.defines['csmTracker']!=null">
				<s:if test="#request.defines['csmTracker']>0">
					${lz:set("注释","****csmTracker关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmTracker))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmTracker",lz:indexOf(fieldName,"csmTracker")>-1)}
				  		<s:if test="#request.atCsmTracker==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmTracker字段的字串格式化输出****")}
					<td ref="csmTracker" class="td " relate="${item.csmTracker}">
						 
						 	<a <s:if test="#item.csmTracker!=null && #item.csmTracker!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csmTracker}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmTracker[i.count-1],lz:left(item.csmTracker$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmRefer || #request.defines['csmRefer']!=null">
				<s:if test="#request.defines['csmRefer']>0">
					${lz:set("注释","****csmRefer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmRefer))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmRefer",lz:indexOf(fieldName,"csmRefer")>-1)}
				  		<s:if test="#request.atCsmRefer==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmRefer字段的字串格式化输出****")}
					<td ref="csmRefer" class="td " relate="${item.csmRefer}">
						 
						 	<a <s:if test="#item.csmRefer!=null && #item.csmRefer!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csmRefer}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmRefer[i.count-1],lz:left(item.csmRefer$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmReferType || #request.defines['csmReferType']!=null">
				<s:if test="#request.defines['csmReferType']>0">
					${lz:set("注释","****csmReferType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmReferType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmReferType",lz:indexOf(fieldName,"csmReferType")>-1)}
				  		<s:if test="#request.atCsmReferType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmReferType字段的字串格式化输出****")}
					<td ref="csmReferType" class="td ">
						 
						 	${lz:or(item$csmReferType[i.count-1],lz:left(item.csmReferType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmFrom || #request.defines['csmFrom']!=null">
				<s:if test="#request.defines['csmFrom']>0">
					${lz:set("注释","****csmFrom关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmFrom))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmFrom",lz:indexOf(fieldName,"csmFrom")>-1)}
				  		<s:if test="#request.atCsmFrom==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmFrom字段的字串格式化输出****")}
					<td ref="csmFrom" class="td ">
						 
						 	${lz:or(item$csmFrom[i.count-1],lz:left(item.csmFrom$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmSrc || #request.defines['csmSrc']!=null">
				<s:if test="#request.defines['csmSrc']>0">
					${lz:set("注释","****csmSrc关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmSrc))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmSrc",lz:indexOf(fieldName,"csmSrc")>-1)}
				  		<s:if test="#request.atCsmSrc==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmSrc字段的字串格式化输出****")}
					<td ref="csmSrc" class="td ">
						 
						 	${lz:or(item$csmSrc[i.count-1],lz:left(item.csmSrc$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmChannel || #request.defines['csmChannel']!=null">
				<s:if test="#request.defines['csmChannel']>0">
					${lz:set("注释","****csmChannel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmChannel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmChannel",lz:indexOf(fieldName,"csmChannel")>-1)}
				  		<s:if test="#request.atCsmChannel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmChannel字段的字串格式化输出****")}
					<td ref="csmChannel" class="td " relate="${item.csmChannel}">
						 
						 	<a <s:if test="#item.csmChannel!=null && #item.csmChannel!=''"> onclick="window.href('${basePath}${proname}/extension/channel_details.do?key=${item.csmChannel}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmChannel[i.count-1],lz:left(item.csmChannel$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmMarketPlan || #request.defines['csmMarketPlan']!=null">
				<s:if test="#request.defines['csmMarketPlan']>0">
					${lz:set("注释","****csmMarketPlan关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmMarketPlan))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmMarketPlan",lz:indexOf(fieldName,"csmMarketPlan")>-1)}
				  		<s:if test="#request.atCsmMarketPlan==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmMarketPlan字段的字串格式化输出****")}
					<td ref="csmMarketPlan" class="td " relate="${item.csmMarketPlan}">
						 
						 	<a <s:if test="#item.csmMarketPlan!=null && #item.csmMarketPlan!=''"> onclick="window.href('${basePath}${proname}/operate/marketplan_details.do?key=${item.csmMarketPlan}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmMarketPlan[i.count-1],lz:left(item.csmMarketPlan$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmActivity || #request.defines['csmActivity']!=null">
				<s:if test="#request.defines['csmActivity']>0">
					${lz:set("注释","****csmActivity关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmActivity))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmActivity",lz:indexOf(fieldName,"csmActivity")>-1)}
				  		<s:if test="#request.atCsmActivity==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmActivity字段的字串格式化输出****")}
					<td ref="csmActivity" class="td " relate="${item.csmActivity}">
						 
						 	<a <s:if test="#item.csmActivity!=null && #item.csmActivity!=''"> onclick="window.href('${basePath}${proname}/operate/activitys/activity_details.do?key=${item.csmActivity}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmActivity[i.count-1],lz:left(item.csmActivity$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmSaler || #request.defines['csmSaler']!=null">
				<s:if test="#request.defines['csmSaler']>0">
					${lz:set("注释","****csmSaler关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmSaler))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmSaler",lz:indexOf(fieldName,"csmSaler")>-1)}
				  		<s:if test="#request.atCsmSaler==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmSaler字段的字串格式化输出****")}
					<td ref="csmSaler" class="td " relate="${item.csmSaler}">
						 
						 	<a <s:if test="#item.csmSaler!=null && #item.csmSaler!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csmSaler}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmSaler[i.count-1],lz:left(item.csmSaler$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmMarket || #request.defines['csmMarket']!=null">
				<s:if test="#request.defines['csmMarket']>0">
					${lz:set("注释","****csmMarket关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmMarket))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmMarket",lz:indexOf(fieldName,"csmMarket")>-1)}
				  		<s:if test="#request.atCsmMarket==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmMarket字段的字串格式化输出****")}
					<td ref="csmMarket" class="td " relate="${item.csmMarket}">
						 
						 	${lz:or(item$csmMarket[i.count-1],lz:left(item.csmMarket$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmTag || #request.defines['csmTag']!=null">
				<s:if test="#request.defines['csmTag']>0">
					${lz:set("注释","****csmTag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmTag))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmTag",lz:indexOf(fieldName,"csmTag")>-1)}
				  		<s:if test="#request.atCsmTag==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmTag字段的字串格式化输出****")}
					<td ref="csmTag" class="td " relate="${item.csmTag}">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csmId}','tag')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csmTag[i.count-1],lz:left(item.csmTag$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmMark || #request.defines['csmMark']!=null">
				<s:if test="#request.defines['csmMark']>0">
					${lz:set("注释","****csmMark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmMark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmMark",lz:indexOf(fieldName,"csmMark")>-1)}
				  		<s:if test="#request.atCsmMark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmMark字段的字串格式化输出****")}
					<td ref="csmMark" class="td " relate="${item.csmMark}">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csmId}','mark')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csmMark[i.count-1],lz:left(item.csmMark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmVisitFlag || #request.defines['csmVisitFlag']!=null">
				<s:if test="#request.defines['csmVisitFlag']>0">
					${lz:set("注释","****csmVisitFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmVisitFlag))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmVisitFlag",lz:indexOf(fieldName,"csmVisitFlag")>-1)}
				  		<s:if test="#request.atCsmVisitFlag==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmVisitFlag字段的字串格式化输出****")}
					<td ref="csmVisitFlag" class="td " relate="${item.csmVisitFlag}">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csmId}','visited')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csmVisitFlag[i.count-1],lz:left(item.csmVisitFlag$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmMask || #request.defines['csmMask']!=null">
				<s:if test="#request.defines['csmMask']>0">
					${lz:set("注释","****csmMask关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmMask))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmMask",lz:indexOf(fieldName,"csmMask")>-1)}
				  		<s:if test="#request.atCsmMask==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmMask字段的字串格式化输出****")}
					<td ref="csmMask" class="td ">
						 
						 	${lz:or(item$csmMask[i.count-1],lz:left(item.csmMask$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmRemark || #request.defines['csmRemark']!=null">
				<s:if test="#request.defines['csmRemark']>0">
					${lz:set("注释","****csmRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmRemark",lz:indexOf(fieldName,"csmRemark")>-1)}
				  		<s:if test="#request.atCsmRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmRemark字段的字串格式化输出****")}
					<td ref="csmRemark" class="td ">
						 
						 	${lz:or(item$csmRemark[i.count-1],lz:left(item.csmRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmVMobile || #request.defines['csmVMobile']!=null">
				<s:if test="#request.defines['csmVMobile']>0">
					${lz:set("注释","****csmVMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmVMobile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmVMobile",lz:indexOf(fieldName,"csmVMobile")>-1)}
				  		<s:if test="#request.atCsmVMobile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmVMobile字段的字串格式化输出****")}
					<td ref="csmVMobile" class="td ">
						 
						 	${lz:or(item$csmVMobile[i.count-1],lz:left(item.csmVMobile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmVEmail || #request.defines['csmVEmail']!=null">
				<s:if test="#request.defines['csmVEmail']>0">
					${lz:set("注释","****csmVEmail关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmVEmail))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmVEmail",lz:indexOf(fieldName,"csmVEmail")>-1)}
				  		<s:if test="#request.atCsmVEmail==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmVEmail字段的字串格式化输出****")}
					<td ref="csmVEmail" class="td ">
						 
						 	${lz:or(item$csmVEmail[i.count-1],lz:left(item.csmVEmail$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmVReal || #request.defines['csmVReal']!=null">
				<s:if test="#request.defines['csmVReal']>0">
					${lz:set("注释","****csmVReal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmVReal))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmVReal",lz:indexOf(fieldName,"csmVReal")>-1)}
				  		<s:if test="#request.atCsmVReal==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmVReal字段的字串格式化输出****")}
					<td ref="csmVReal" class="td ">
						 
						 	${lz:or(item$csmVReal[i.count-1],lz:left(item.csmVReal$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmVDrive || #request.defines['csmVDrive']!=null">
				<s:if test="#request.defines['csmVDrive']>0">
					${lz:set("注释","****csmVDrive关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmVDrive))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmVDrive",lz:indexOf(fieldName,"csmVDrive")>-1)}
				  		<s:if test="#request.atCsmVDrive==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmVDrive字段的字串格式化输出****")}
					<td ref="csmVDrive" class="td ">
						 
						 	${lz:or(item$csmVDrive[i.count-1],lz:left(item.csmVDrive$,100))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csmVWork || #request.defines['csmVWork']!=null">
				<s:if test="#request.defines['csmVWork']>0">
					${lz:set("注释","****csmVWork关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmVWork))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmVWork",lz:indexOf(fieldName,"csmVWork")>-1)}
				  		<s:if test="#request.atCsmVWork==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmVWork字段的字串格式化输出****")}
					<td ref="csmVWork" class="td ">
						 
						 	${lz:or(item$csmVWork[i.count-1],lz:left(item.csmVWork$,100))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csmVOffline || #request.defines['csmVOffline']!=null">
				<s:if test="#request.defines['csmVOffline']>0">
					${lz:set("注释","****csmVWork关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmVOffline))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmVOffline",lz:indexOf(fieldName,"csmVOffline")>-1)}
				  		<s:if test="#request.atCsmVOffline==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmVOffline字段的字串格式化输出****")}
					<td ref="csmVOffline" class="td ">
						 
						 	${lz:or(item$csmVOffline[i.count-1],lz:left(item.csmVOffline$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmStatus || #request.defines['csmStatus']!=null">
				<s:if test="#request.defines['csmStatus']>0">
					${lz:set("注释","****csmStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmStatus",lz:indexOf(fieldName,"csmStatus")>-1)}
				  		<s:if test="#request.atCsmStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmStatus字段的字串格式化输出****")}
					<td ref="csmStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csmId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csmStatus[i.count-1],lz:left(item.csmStatus$,100))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.vstatus || #request.defines['vstatus']!=null">
				<s:if test="#request.defines['vstatus']>0">
					${lz:set("注释","****csmStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.vstatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atVstatus",lz:indexOf(fieldName,"vstatus")>-1)}
				  		<s:if test="#request.atVstatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmStatus字段的字串格式化输出****")}
					<td ref="vstatus" class="td ">
						 <s:if test="#request.alias==null">
							 
					     </s:if>
						 
						 	${lz:or(item$vstatus[i.count-1],lz:left(item.vstatus$,100))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csmLockReason || #request.defines['csmLockReason']!=null">
				<s:if test="#request.defines['csmLockReason']>0">
					${lz:set("注释","****csmLockReason关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmLockReason))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmLockReason",lz:indexOf(fieldName,"csmLockReason")>-1)}
				  		<s:if test="#request.atCsmLockReason==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmLockReason字段的字串格式化输出****")}
					<td ref="csmLockReason" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<!--<a class="modify" href="javascript:Update('${item.csmId}','csmLockReason')"></a>-->
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csmLockReason[i.count-1],lz:left(item.csmLockReason$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('预订','${item.csmId}')">预订</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[1]==true">
					<a href="javascript:HandleSel('发短信','${item.csmId}')">发短信</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[2]==true">
					<a href="javascript:HandleSel('充积分','${item.csmId}')">充积分</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[3]==true">
					<a href="javascript:HandleSel('扣积分','${item.csmId}')">扣积分</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[4]==true">
					<a href="javascript:HandleSel('充小时','${item.csmId}')">充小时</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[5]==true">
					<a href="javascript:HandleSel('重置','${item.csmId}')">重置</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[6]==true">
					<a href="javascript:HandleSel('审核','${item.csmId}')">审核</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[7]==true">
					<a href="javascript:HandleSel('备注','${item.csmId}')">备注</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[8]==true">
					<a href="javascript:HandleSel('寄卡','${item.csmId}')">寄卡</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[9]==true">
					<a href="javascript:HandleSel('改号','${item.csmId}')">改号</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.csmId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectMember('${item.csmId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditMember('${item.csmId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelMember('${item.csmId}','${item.csmName}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsMember('${item.csmId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmId || #request.defines['csmId']!=null">
				<s:if test="#request.defines['csmId']>0">
					${lz:set("注释","****csmId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmId",lz:indexOf(fieldName,"csmId")>-1)}				  		
				  		<s:if test="#request.atCsmId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmHost || #request.defines['csmHost']!=null">
				<s:if test="#request.defines['csmHost']>0">
					${lz:set("注释","****csmHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmHost",lz:indexOf(fieldName,"csmHost")>-1)}				  		
				  		<s:if test="#request.atCsmHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmUsername || #request.defines['csmUsername']!=null">
				<s:if test="#request.defines['csmUsername']>0">
					${lz:set("注释","****csmUsername关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmUsername",lz:indexOf(fieldName,"csmUsername")>-1)}				  		
				  		<s:if test="#request.atCsmUsername==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmUsername[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmGroup || #request.defines['csmGroup']!=null">
				<s:if test="#request.defines['csmGroup']>0">
					${lz:set("注释","****csmGroup关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmGroup",lz:indexOf(fieldName,"csmGroup")>-1)}				  		
				  		<s:if test="#request.atCsmGroup==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmGroup[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmMoney || #request.defines['csmMoney']!=null">
				<s:if test="#request.defines['csmMoney']>0">
					${lz:set("注释","****csmMoney关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmMoney",lz:indexOf(fieldName,"csmMoney")>-1)}				  		
				  		<s:if test="#request.atCsmMoney==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmMoney[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmCoupon || #request.defines['csmCoupon']!=null">
				<s:if test="#request.defines['csmCoupon']>0">
					${lz:set("注释","****csmCoupon关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmCoupon",lz:indexOf(fieldName,"csmCoupon")>-1)}				  		
				  		<s:if test="#request.atCsmCoupon==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmCoupon[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmIntegral || #request.defines['csmIntegral']!=null">
				<s:if test="#request.defines['csmIntegral']>0">
					${lz:set("注释","****csmIntegral关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmIntegral",lz:indexOf(fieldName,"csmIntegral")>-1)}				  		
				  		<s:if test="#request.atCsmIntegral==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmIntegral[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmGrow || #request.defines['csmGrow']!=null">
				<s:if test="#request.defines['csmGrow']>0">
					${lz:set("注释","****csmGrow关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmGrow",lz:indexOf(fieldName,"csmGrow")>-1)}				  		
				  		<s:if test="#request.atCsmGrow==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmGrow[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmGrade || #request.defines['csmGrade']!=null">
				<s:if test="#request.defines['csmGrade']>0">
					${lz:set("注释","****csmGrade关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmGrade",lz:indexOf(fieldName,"csmGrade")>-1)}				  		
				  		<s:if test="#request.atCsmGrade==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmGrade[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmRebate || #request.defines['csmRebate']!=null">
				<s:if test="#request.defines['csmRebate']>0">
					${lz:set("注释","****csmRebate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmRebate",lz:indexOf(fieldName,"csmRebate")>-1)}				  		
				  		<s:if test="#request.atCsmRebate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmRebate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmNotRevenue || #request.defines['csmNotRevenue']!=null">
				<s:if test="#request.defines['csmNotRevenue']>0">
					${lz:set("注释","****csmNotRevenue关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmNotRevenue",lz:indexOf(fieldName,"csmNotRevenue")>-1)}				  		
				  		<s:if test="#request.atCsmNotRevenue==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmNotRevenue[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmWeixinFlag || #request.defines['csmWeixinFlag']!=null">
				<s:if test="#request.defines['csmWeixinFlag']>0">
					${lz:set("注释","****csmWeixinFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmWeixinFlag",lz:indexOf(fieldName,"csmWeixinFlag")>-1)}				  		
				  		<s:if test="#request.atCsmWeixinFlag==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmWeixinFlag[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmAlipayFlag || #request.defines['csmAlipayFlag']!=null">
				<s:if test="#request.defines['csmAlipayFlag']>0">
					${lz:set("注释","****csmAlipayFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmAlipayFlag",lz:indexOf(fieldName,"csmAlipayFlag")>-1)}				  		
				  		<s:if test="#request.atCsmAlipayFlag==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmAlipayFlag[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmEmail || #request.defines['csmEmail']!=null">
				<s:if test="#request.defines['csmEmail']>0">
					${lz:set("注释","****csmEmail关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmEmail",lz:indexOf(fieldName,"csmEmail")>-1)}				  		
				  		<s:if test="#request.atCsmEmail==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmEmail[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmMobile || #request.defines['csmMobile']!=null">
				<s:if test="#request.defines['csmMobile']>0">
					${lz:set("注释","****csmMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmMobile",lz:indexOf(fieldName,"csmMobile")>-1)}				  		
				  		<s:if test="#request.atCsmMobile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmMobile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmEvcard || #request.defines['csmEvcard']!=null">
				<s:if test="#request.defines['csmEvcard']>0">
					${lz:set("注释","****csmEvcard关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmEvcard",lz:indexOf(fieldName,"csmEvcard")>-1)}				  		
				  		<s:if test="#request.atCsmEvcard==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmEvcard[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmExpress || #request.defines['csmExpress']!=null">
				<s:if test="#request.defines['csmExpress']>0">
					${lz:set("注释","****csmExpress关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmExpress",lz:indexOf(fieldName,"csmExpress")>-1)}				  		
				  		<s:if test="#request.atCsmExpress==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmExpress[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmTemp || #request.defines['csmTemp']!=null">
				<s:if test="#request.defines['csmTemp']>0">
					${lz:set("注释","****csmTemp关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmTemp",lz:indexOf(fieldName,"csmTemp")>-1)}				  		
				  		<s:if test="#request.atCsmTemp==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmTemp[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmName || #request.defines['csmName']!=null">
				<s:if test="#request.defines['csmName']>0">
					${lz:set("注释","****csmName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmName",lz:indexOf(fieldName,"csmName")>-1)}				  		
				  		<s:if test="#request.atCsmName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmInfo || #request.defines['csmInfo']!=null">
				<s:if test="#request.defines['csmInfo']>0">
					${lz:set("注释","****csmInfo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmInfo",lz:indexOf(fieldName,"csmInfo")>-1)}				  		
				  		<s:if test="#request.atCsmInfo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmInfo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmOutlets || #request.defines['csmOutlets']!=null">
				<s:if test="#request.defines['csmOutlets']>0">
					${lz:set("注释","****csmOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmOutlets",lz:indexOf(fieldName,"csmOutlets")>-1)}				  		
				  		<s:if test="#request.atCsmOutlets==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmOutlets[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmIsVip || #request.defines['csmIsVip']!=null">
				<s:if test="#request.defines['csmIsVip']>0">
					${lz:set("注释","****csmIsVip关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmIsVip",lz:indexOf(fieldName,"csmIsVip")>-1)}				  		
				  		<s:if test="#request.atCsmIsVip==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmIsVip[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmVipStart || #request.defines['csmVipStart']!=null">
				<s:if test="#request.defines['csmVipStart']>0">
					${lz:set("注释","****csmVipStart关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmVipStart",lz:indexOf(fieldName,"csmVipStart")>-1)}				  		
				  		<s:if test="#request.atCsmVipStart==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmVipStart[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmVipEnd || #request.defines['csmVipEnd']!=null">
				<s:if test="#request.defines['csmVipEnd']>0">
					${lz:set("注释","****csmVipEnd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmVipEnd",lz:indexOf(fieldName,"csmVipEnd")>-1)}				  		
				  		<s:if test="#request.atCsmVipEnd==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmVipEnd[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmUpdateTime || #request.defines['csmUpdateTime']!=null">
				<s:if test="#request.defines['csmUpdateTime']>0">
					${lz:set("注释","****csmUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmUpdateTime",lz:indexOf(fieldName,"csmUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsmUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmAddTime || #request.defines['csmAddTime']!=null">
				<s:if test="#request.defines['csmAddTime']>0">
					${lz:set("注释","****csmAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmAddTime",lz:indexOf(fieldName,"csmAddTime")>-1)}				  		
				  		<s:if test="#request.atCsmAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmLastTime || #request.defines['csmLastTime']!=null">
				<s:if test="#request.defines['csmLastTime']>0">
					${lz:set("注释","****csmLastTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmLastTime",lz:indexOf(fieldName,"csmLastTime")>-1)}				  		
				  		<s:if test="#request.atCsmLastTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmLastTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmLastIp || #request.defines['csmLastIp']!=null">
				<s:if test="#request.defines['csmLastIp']>0">
					${lz:set("注释","****csmLastIp关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmLastIp",lz:indexOf(fieldName,"csmLastIp")>-1)}				  		
				  		<s:if test="#request.atCsmLastIp==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmLastIp[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmLoginS || #request.defines['csmLoginS']!=null">
				<s:if test="#request.defines['csmLoginS']>0">
					${lz:set("注释","****csmLoginS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmLoginS",lz:indexOf(fieldName,"csmLoginS")>-1)}				  		
				  		<s:if test="#request.atCsmLoginS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmLoginS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmFirstUse || #request.defines['csmFirstUse']!=null">
				<s:if test="#request.defines['csmFirstUse']>0">
					${lz:set("注释","****csmFirstUse关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmFirstUse",lz:indexOf(fieldName,"csmFirstUse")>-1)}				  		
				  		<s:if test="#request.atCsmFirstUse==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmFirstUse[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmLastUse || #request.defines['csmLastUse']!=null">
				<s:if test="#request.defines['csmLastUse']>0">
					${lz:set("注释","****csmLastUse关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmLastUse",lz:indexOf(fieldName,"csmLastUse")>-1)}				  		
				  		<s:if test="#request.atCsmLastUse==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmLastUse[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmAllRecharge || #request.defines['csmAllRecharge']!=null">
				<s:if test="#request.defines['csmAllRecharge']>0">
					${lz:set("注释","****csmAllRecharge关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmAllRecharge",lz:indexOf(fieldName,"csmAllRecharge")>-1)}				  		
				  		<s:if test="#request.atCsmAllRecharge==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmAllRecharge[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmAllOrderS || #request.defines['csmAllOrderS']!=null">
				<s:if test="#request.defines['csmAllOrderS']>0">
					${lz:set("注释","****csmAllOrderS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmAllOrderS",lz:indexOf(fieldName,"csmAllOrderS")>-1)}				  		
				  		<s:if test="#request.atCsmAllOrderS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmAllOrderS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmAllUseTime || #request.defines['csmAllUseTime']!=null">
				<s:if test="#request.defines['csmAllUseTime']>0">
					${lz:set("注释","****csmAllUseTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmAllUseTime",lz:indexOf(fieldName,"csmAllUseTime")>-1)}				  		
				  		<s:if test="#request.atCsmAllUseTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmAllUseTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmAllViolatS || #request.defines['csmAllViolatS']!=null">
				<s:if test="#request.defines['csmAllViolatS']>0">
					${lz:set("注释","****csmAllViolatS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmAllViolatS",lz:indexOf(fieldName,"csmAllViolatS")>-1)}				  		
				  		<s:if test="#request.atCsmAllViolatS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmAllViolatS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmUnViolatS || #request.defines['csmUnViolatS']!=null">
				<s:if test="#request.defines['csmUnViolatS']>0">
					${lz:set("注释","****csmUnViolatS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmUnViolatS",lz:indexOf(fieldName,"csmUnViolatS")>-1)}				  		
				  		<s:if test="#request.atCsmUnViolatS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmUnViolatS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmAllTroubleS || #request.defines['csmAllTroubleS']!=null">
				<s:if test="#request.defines['csmAllTroubleS']>0">
					${lz:set("注释","****csmAllTroubleS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmAllTroubleS",lz:indexOf(fieldName,"csmAllTroubleS")>-1)}				  		
				  		<s:if test="#request.atCsmAllTroubleS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmAllTroubleS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmFirstRecharge || #request.defines['csmFirstRecharge']!=null">
				<s:if test="#request.defines['csmFirstRecharge']>0">
					${lz:set("注释","****csmFirstRecharge关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmFirstRecharge",lz:indexOf(fieldName,"csmFirstRecharge")>-1)}				  		
				  		<s:if test="#request.atCsmFirstRecharge==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmFirstRecharge[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmRefundTime || #request.defines['csmRefundTime']!=null">
				<s:if test="#request.defines['csmRefundTime']>0">
					${lz:set("注释","****csmRefundTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmRefundTime",lz:indexOf(fieldName,"csmRefundTime")>-1)}				  		
				  		<s:if test="#request.atCsmRefundTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmRefundTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmRegistIp || #request.defines['csmRegistIp']!=null">
				<s:if test="#request.defines['csmRegistIp']>0">
					${lz:set("注释","****csmRegistIp关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmRegistIp",lz:indexOf(fieldName,"csmRegistIp")>-1)}				  		
				  		<s:if test="#request.atCsmRegistIp==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmRegistIp[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmAdder || #request.defines['csmAdder']!=null">
				<s:if test="#request.defines['csmAdder']>0">
					${lz:set("注释","****csmAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmAdder",lz:indexOf(fieldName,"csmAdder")>-1)}				  		
				  		<s:if test="#request.atCsmAdder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmAdder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmTracker || #request.defines['csmTracker']!=null">
				<s:if test="#request.defines['csmTracker']>0">
					${lz:set("注释","****csmTracker关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmTracker",lz:indexOf(fieldName,"csmTracker")>-1)}				  		
				  		<s:if test="#request.atCsmTracker==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmTracker[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmRefer || #request.defines['csmRefer']!=null">
				<s:if test="#request.defines['csmRefer']>0">
					${lz:set("注释","****csmRefer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmRefer",lz:indexOf(fieldName,"csmRefer")>-1)}				  		
				  		<s:if test="#request.atCsmRefer==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmRefer[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmReferType || #request.defines['csmReferType']!=null">
				<s:if test="#request.defines['csmReferType']>0">
					${lz:set("注释","****csmReferType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmReferType",lz:indexOf(fieldName,"csmReferType")>-1)}				  		
				  		<s:if test="#request.atCsmReferType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmReferType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmFrom || #request.defines['csmFrom']!=null">
				<s:if test="#request.defines['csmFrom']>0">
					${lz:set("注释","****csmFrom关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmFrom",lz:indexOf(fieldName,"csmFrom")>-1)}				  		
				  		<s:if test="#request.atCsmFrom==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmFrom[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmSrc || #request.defines['csmSrc']!=null">
				<s:if test="#request.defines['csmSrc']>0">
					${lz:set("注释","****csmSrc关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmSrc",lz:indexOf(fieldName,"csmSrc")>-1)}				  		
				  		<s:if test="#request.atCsmSrc==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmSrc[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmChannel || #request.defines['csmChannel']!=null">
				<s:if test="#request.defines['csmChannel']>0">
					${lz:set("注释","****csmChannel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmChannel",lz:indexOf(fieldName,"csmChannel")>-1)}				  		
				  		<s:if test="#request.atCsmChannel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmChannel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmMarketPlan || #request.defines['csmMarketPlan']!=null">
				<s:if test="#request.defines['csmMarketPlan']>0">
					${lz:set("注释","****csmMarketPlan关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmMarketPlan",lz:indexOf(fieldName,"csmMarketPlan")>-1)}				  		
				  		<s:if test="#request.atCsmMarketPlan==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmMarketPlan[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmActivity || #request.defines['csmActivity']!=null">
				<s:if test="#request.defines['csmActivity']>0">
					${lz:set("注释","****csmActivity关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmActivity",lz:indexOf(fieldName,"csmActivity")>-1)}				  		
				  		<s:if test="#request.atCsmActivity==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmActivity[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmSaler || #request.defines['csmSaler']!=null">
				<s:if test="#request.defines['csmSaler']>0">
					${lz:set("注释","****csmSaler关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmSaler",lz:indexOf(fieldName,"csmSaler")>-1)}				  		
				  		<s:if test="#request.atCsmSaler==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmSaler[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmMarket || #request.defines['csmMarket']!=null">
				<s:if test="#request.defines['csmMarket']>0">
					${lz:set("注释","****csmMarket关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmMarket",lz:indexOf(fieldName,"csmMarket")>-1)}				  		
				  		<s:if test="#request.atCsmMarket==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmMarket[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmTag || #request.defines['csmTag']!=null">
				<s:if test="#request.defines['csmTag']>0">
					${lz:set("注释","****csmTag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmTag",lz:indexOf(fieldName,"csmTag")>-1)}				  		
				  		<s:if test="#request.atCsmTag==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmTag[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmMark || #request.defines['csmMark']!=null">
				<s:if test="#request.defines['csmMark']>0">
					${lz:set("注释","****csmMark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmMark",lz:indexOf(fieldName,"csmMark")>-1)}				  		
				  		<s:if test="#request.atCsmMark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmMark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmVisitFlag || #request.defines['csmVisitFlag']!=null">
				<s:if test="#request.defines['csmVisitFlag']>0">
					${lz:set("注释","****csmVisitFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmVisitFlag",lz:indexOf(fieldName,"csmVisitFlag")>-1)}				  		
				  		<s:if test="#request.atCsmVisitFlag==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmVisitFlag[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmMask || #request.defines['csmMask']!=null">
				<s:if test="#request.defines['csmMask']>0">
					${lz:set("注释","****csmMask关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmMask",lz:indexOf(fieldName,"csmMask")>-1)}				  		
				  		<s:if test="#request.atCsmMask==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmMask[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmRemark || #request.defines['csmRemark']!=null">
				<s:if test="#request.defines['csmRemark']>0">
					${lz:set("注释","****csmRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmRemark",lz:indexOf(fieldName,"csmRemark")>-1)}				  		
				  		<s:if test="#request.atCsmRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmVMobile || #request.defines['csmVMobile']!=null">
				<s:if test="#request.defines['csmVMobile']>0">
					${lz:set("注释","****csmVMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmVMobile",lz:indexOf(fieldName,"csmVMobile")>-1)}				  		
				  		<s:if test="#request.atCsmVMobile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmVMobile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmVEmail || #request.defines['csmVEmail']!=null">
				<s:if test="#request.defines['csmVEmail']>0">
					${lz:set("注释","****csmVEmail关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmVEmail",lz:indexOf(fieldName,"csmVEmail")>-1)}				  		
				  		<s:if test="#request.atCsmVEmail==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmVEmail[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmVReal || #request.defines['csmVReal']!=null">
				<s:if test="#request.defines['csmVReal']>0">
					${lz:set("注释","****csmVReal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmVReal",lz:indexOf(fieldName,"csmVReal")>-1)}				  		
				  		<s:if test="#request.atCsmVReal==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmVReal[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmVDrive || #request.defines['csmVDrive']!=null">
				<s:if test="#request.defines['csmVDrive']>0">
					${lz:set("注释","****csmVDrive关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmVDrive",lz:indexOf(fieldName,"csmVDrive")>-1)}				  		
				  		<s:if test="#request.atCsmVDrive==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmVDrive[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmStatus || #request.defines['csmStatus']!=null">
				<s:if test="#request.defines['csmStatus']>0">
					${lz:set("注释","****csmStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmStatus",lz:indexOf(fieldName,"csmStatus")>-1)}				  		
				  		<s:if test="#request.atCsmStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.vstatus || #request.defines['vstatus']!=null">
				<s:if test="#request.defines['vstatus']>0">
					${lz:set("注释","****csmStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atVstatus",lz:indexOf(fieldName,"vstatus")>-1)}				  		
				  		<s:if test="#request.atVstatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">vstatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csmLockReason || #request.defines['csmLockReason']!=null">
				<s:if test="#request.defines['csmLockReason']>0">
					${lz:set("注释","****csmLockReason关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmLockReason",lz:indexOf(fieldName,"csmLockReason")>-1)}				  		
				  		<s:if test="#request.atCsmLockReason==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmLockReason[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			 
			 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
		  	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
		 </tr>
			 </s:iterator>
	 	 </s:bean>
		 </s:if>
		  
		  
		 </s:iterator>
		 </tbody>			 
	  </table>
	  
	  ${lz:set("注释","*****************数据列表结束*****************")}
	  
  </div>
  <%@include file="/admin/module/page.jsp"%>
  
 </td></tr></table>

<script>

	<s:if test="#request.alias=='join'">
	window.objects = ${objects==null ? null : (lz:json(objects))};
	</s:if>	
	window.entrypoint="${entrypoint}";
	
	showTips("${tips.value}");
	
	<s:if test="#request.onload!=null">
	$(function(){
		try{
			if("${lz:js(onload)}".indexOf("(")>-1){
				eval("${lz:js(onload)}");
			}else{
				window["${onload}"]();
			}
		}catch(e){}
	});
	</s:if>
	

	function href(url,params){
		top.$.showModalDialog({url:url},params,window);
	} 
	
	function saveForm(){
		var params = $("#queryForm").params();
		var paramCount = 0;
		for(var o in params){
			if($.trim(params[o])=="")
				delete params[o];
			else if($("#queryForm *[name='"+o+"']").attr("type")=="hidden"){
			
			}else
				paramCount++;
		}
		if(paramCount==0){
			top.Alert("没有任何查询条件，不能保存");
			return;
		}
		var dialog = top.$.open("${basePath}admin/configurator/query_edit.do",{
			"srvQuery.sqPath":"${servletPath}",
			"srvQuery.sqParams":JSON.stringify(params)
		},function(object){
			if(typeof(object)!="object"){
				try{
					dialog.find("iframe").get(0).contentWindow.$("#sqPath").parents("dl").hide();
					dialog.find("iframe").get(0).contentWindow.$("#sqParams").parents("dl").hide();
				}catch(e){}
			}else if(object && object.sqId){
				top.Alert("快速查询链接保存成功");
				$.cache("shortcuts","show");
				$.submit("${location}?Q="+object.sqId,params);
			}
		});		
	}
	$(function(){
		$(".shortcuts .item span").click(function(){
			var id = $(this).attr("ref");
			var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除该快速查询链接吗？</span>",
				title:"删除快速查询链接",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}admin/configurator/query_del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);					
				}
			});
			return false;	
		});
	});
<s:if test="#request.alias=='select'">
	/**
	* 选择会员帐号
	**/
	function SelectMember(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改会员帐号
	**/
	function EditMember(id){
		var url = "${basePath}${namespace}member_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改会员帐号'}":lz:json(ctrl)}};
		href(url,params);
	}
	/*
	* 修改所选
	*/
	function EditSel(){
		var checker=$(".check:checked");
		if(checker.length!=1)
		{
			$.tips("请选择一个需要修改的记录.");
			return;
		}
		EditMember(checker.val());
	}
	
	/**
	* 批量修改
	**/
	function UpdateSel(flag)
	{
		var checker=$(".check:checked");
		if(checker.length==0){
			<s:if test="#session.ccclubs_login.suId==0">
			$.alert("确定要对当前查询的${objects.count}条记录进行批量修改吗?",function(){
				var url = "${basePath}${namespace}member_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新会员帐号",visible:false,editable:false,fields:{}}};
				for(var o in fieldNames[flag])
					params.ctrl["fields"][o]={visible:true,editable:true};
				href(url,params);
			});
			</s:if>
			<s:else>
			top.$.alert("您未选择要修改的记录");
			</s:else>
		}else{
			var ids = "";
			checker.each(function(i){
				if(ids!="")
					ids+=",";
				ids+=$(this).val();
			});
			var url = "${basePath}${namespace}member_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新会员帐号",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改会员帐号
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}member_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新会员帐号",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		//console.log(params);
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["group"]={};
	fieldNames["not_revenue"]={};
	fieldNames["express"]={};
	fieldNames["temp"]={};
	fieldNames["e"]={};
	fieldNames["tag"]={};
	fieldNames["mark"]={};
	fieldNames["visited"]={};
	fieldNames["status"]={};
	//fieldNames["csmLockReason"]={};
	fieldNames["group"]["csmGroup"]=true;
	fieldNames["not_revenue"]["csmNotRevenue"]=true;
	fieldNames["express"]["csmExpress"]=true;
	fieldNames["temp"]["csmTemp"]=true;
	fieldNames["e"]["csmIsVip"]=true;
	fieldNames["tag"]["csmTag"]=true;
	fieldNames["mark"]["csmMark"]=true;
	fieldNames["visited"]["csmVisitFlag"]=true;
	fieldNames["status"]["csmStatus"]=true;
	//fieldNames["csmLockReason"]["csmLockReason"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改会员帐号任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}member_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新会员帐号",visible:false,editable:false,fields:{}}};
			params.ctrl["fields"][$(this).attr("ref")]={visible:true,editable:true};
			href(url,params);
		});
	});
	</s:if>

	/*
	* 自定义操作
	*/
	function HandleSel(operate,id){
		if(!id){
			var checker=$(".check:checked");
			if(checker.length!=1){
				$.tips("请选择一个需要操作的记录.");
				return;
			}
			id = checker.val();
		}
		
		if(window.getHandle && window.getHandle(operate,id)!=null)//本窗口的操作函数获取句柄
			window.getHandle(operate)(operate,id);
		else if(window.handles && window.handles[operate])//本窗口的操作函数句柄
			window.handles[operate](operate,id);
		else if(typeof(caller)!="undefined" && caller.handles && caller.handles[operate])//调用窗口的操作函数句柄
			caller.handles[operate](operate,id);
		else if(parent && parent.handles && parent.handles[operate])//父窗口的操作函数句柄
			parent.handles[operate](operate,id);
		else{
			var url = "${basePath}${namespace}member_edit.do";
			var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:operate}};
			href(url,params);
		}
	}
	/*
	* 自定义菜单
	*/
	function HandleMenu(operate){
		if(window.getHandle && window.getHandle(operate)!=null)//本窗口的操作函数获取句柄
			window.getHandle(operate)(operate);
		else if(window.handles && window.handles[operate])//本窗口的操作函数句柄
			window.handles[operate](operate);
		else if(typeof(caller)!="undefined" && caller.handles && caller.handles[operate])//调用窗口的操作函数句柄
			caller.handles[operate](operate);
		else if(parent && parent.handles && parent.handles[operate])//父窗口的操作函数句柄
			parent.handles[operate](operate);
		else{
			var url = "${basePath}${namespace}member_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除会员帐号
	**/
	function DelMember(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除会员帐号["+flag+"]吗？</span>",
				title:"删除会员帐号",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}member_del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);
				}
		 });	
	}
	/*
	* 删除所选记录详情
	*/
	function DelSel(){
		var checker=$(".check:checked");
		if(checker.length==0)
		{
			$.tips("您未选中任何记录，请至少选择一项.");
			return;
		}
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除所选会员帐号吗？</span>",
				title:"批量删除会员帐号",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}member_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示会员帐号详情
	**/
	function DetailsMember(id)
	{
		var url = "${basePath}${namespace}member_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"会员帐号详情",editable:false,visible:true}};
		href(url,params);
	}
	/*
	* 显示所选记录详情
	*/
	function DetailsSel(){
		var checker=$(".check:checked");
		if(checker.length!=1)
		{
			$.tips("请选择一个需要显示详情的记录.");
			return;
		}
		DetailsMember(checker.val());
	}
	/*
	* 数据导出
	*/
	function exportXls(){
		if(${page.count>=0 ? page.count : 0}==0){
			showTips("当前没有可导出数据");
			return;
		}
		top.$.showModalDialog({
			url:"${basePath}admin/export.do"
		},{
			className:"${page.result[0].class.name}",
			count:${page.count+0},
			exporturl:"${basePath}${servletPath}?export=true&page={page}&${lz:querys("UTF-8","page")}",
			checkeds:"${checkeds}"
		},window);	
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>