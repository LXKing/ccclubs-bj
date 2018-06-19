<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>会员分类</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<link href="${basePath}admin/css/stats.css?mm=${lz:uuid()}" rel="stylesheet" type="text/css" /> 
	
	<style>
	#toggle-postion{width:5px;height:1px;position:fixed;top:50%;left:0px;}
	</style>
	
</head>  
<body scroll="no">
<div id="toggle-postion"></div>
<script>
$(function(){
	showTips("${tips.value}");
	$(".item").click(function(){
		$("#classifyForm").attr("action",$(this).attr("ref"));
		$("#classifyForm").submit();
		$(".item").removeClass("focus");
		$(this).addClass("focus");
	});
	$("#classify_member_frame").load(function(){
		var window = $(this).get(0).contentWindow;
		
	});
	$("#classifyForm .item:eq(0)").click();
	try{
		top.$("#indexframe").get(0).contentWindow.toggle(false);
		$("#toggle-postion").say("点这儿可以切换系统导航菜单的显示隐藏哦");
	}catch(e){}
});
window.handles={
	"回访会员":function(opt,id){
		var url = "${basePath}admin/crm/revisit_edit.do";
		var cw = $("#classify_member_frame").get(0).contentWindow;
		var params = {
			entrypoint:cw.entrypoint,
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
		cw.href(url,params);
	},
	"标注会员":function(opt,id){
		var url = "${basePath}admin/user/member_edit.do";
		var cw = $("#classify_member_frame").get(0).contentWindow;
		var params = {
			entrypoint:cw.entrypoint,
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
		cw.href(url,params);
	},
	"备忘标记":function(opt,id){
		var url = "${basePath}admin/user/member_edit.do";
		var cw = $("#classify_member_frame").get(0).contentWindow;
		var params = {
			entrypoint:cw.entrypoint,
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
		cw.href(url,params);
	}
}
</script>



<div id="navigator" >
	
	<form name="classifyForm" id="classifyForm" action="" method="post" target="classify_member_frame">
	${lz:set("isDyna",lz:indexOf(alias,'dyna')>-1)}
	<s:if test="#request.isDyna==true">
	
		<button type="button" class="item focus ${type=="money"?"focus":""}" ref="${basePath}admin/user/member.dyna.do?hideQuery=true&title=新注册会员&showKey=csmAddTime&desc=csm_add_time">		
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256427828778017.png"/>
			新注册会员
		</button>
		
		<button type="button" class="item ${type=="coupon"?"focus":""}" ref="${basePath}admin/user/member.dyna.do?hideQuery=true&title=新充值会员&showKey=csmFirstRecharge&desc=csm_first_recharge">
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477806.png"/>
			新充值会员
		</button>
		
		<button type="button" class="item ${type=="package"?"focus":""}" ref="${basePath}admin/user/member.dyna.do?hideQuery=true&title=第一次用车会员&showKey=csmFirstUse&desc=csm_first_use">
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477809.png"/>
			第一次用车会员	
		</button>
		
		<button type="button" class="item ${type=="package"?"focus":""}" ref="${basePath}admin/user/member.dyna.do?hideQuery=true&title=最近用车会员&showKey=csmLastUse,csmAllOrderS,csmAllUseTime&desc=csm_last_use">
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477804.png"/>
			最近用车会员	
		</button>
		
		<button type="button" class="item ${type=="package"?"focus":""}" ref="${basePath}admin/user/member.dyna.do?hideQuery=true&title=新退款会员&showKey=csmRefundTime&desc=csm_refund_time">
			<img align="absmiddle" src="${basePath}admin/images/icons/Wallet.png"/>
			新退款会员
		</button>
		
		<button type="button" class="item ${type=="package"?"focus":""}" ref="${basePath}admin/user/member.dyna.do?hideQuery=true&title=订单最多会员&showKey=csmAllOrderS&desc=csm_all_order_s">
			<img align="absmiddle" src="${basePath}admin/images/icons/200882564229234778010.png"/>
			订单最多会员
		</button>
		
		<button type="button" class="item ${type=="package"?"focus":""}" ref="${basePath}admin/user/member.dyna.do?hideQuery=true&title=用车时间最长&showKey=csmAllUseTime&desc=csm_all_use_time">
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477803.png"/>
			用车时间最长
		</button>
		
		<button type="button" class="item ${type=="package"?"focus":""}" ref="${basePath}admin/user/member.dyna.do?hideQuery=true&title=充值最多会员&showKey=csmAllRecharge&desc=csm_all_recharge">
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477806.png"/>
			充值最多会员
		</button>
		
		<button type="button" class="item ${type=="package"?"focus":""}" ref="${basePath}admin/user/member.dyna.do?hideQuery=true&title=待审核会员&handles=审核&showKey=csmVReal,csmVDrive,csmUpdateTime&csmVReal=2&csmVDrive=2&desc=csm_update_time">
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256427828778012.png"/>
			待审核会员
		</button>
		
	</s:if>
	${lz:set("isTag",lz:indexOf(alias,'tag')>-1)}
	<s:if test="#request.isTag==true">
	
		<s:iterator value="#request.propertys" id="property" status="i">
	
		<button type="button" class="item ${type=="package"?"focus":""}" ref="${basePath}admin/user/member.dyna.do?hideQuery=true&title=${property.spName}&csmTag=${property.spId}">
			<img align="absmiddle" src="${basePath}admin/images/icons/Tag.png"/>
			${property.spName}
		</button>
		
		</s:iterator>
	
	</s:if>		
		
	</form>
	
</div>


<table id="separator-panel" width="8" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr height="*">
		<td width="8" class="separator" align="center" valign="middle">&nbsp;</td>
	</tr>
</table>

<div style="margin-left:210px;height:100%;overflow:hidden;">
	<iframe src="" id="classify_member_frame" name="classify_member_frame" frameborder="0" scrolling='yes' height="100%" width="100%"></iframe>
</div>

</body>
</html>
