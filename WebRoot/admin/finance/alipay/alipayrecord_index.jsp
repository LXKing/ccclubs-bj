<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/alipay/alipayrecord.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csAlipayRecord.csarId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csarId,csarHost,csarBankType,csarTradeNo,csarMember,csarRealName,csarAmount,csarBuyItem,csarRefund,csarPayMethod,csarBlank,csarAlipayNo,csarBuyerAccount,csarClientId,csarUpdateTime,csarAddTime,csarStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csarId,csarHost,csarBankType,csarTradeNo,csarMember,csarRealName,csarAmount,csarBuyItem,csarBuyCount,csarRefund,csarPayMethod,csarBlank,csarAlipayNo,csarBuyerAccount,csarClientId,csarThirdBack,csarUpdateTime,csarAddTime,csarStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csarId,csarHost,csarBankType,csarTradeNo,csarMember,csarRealName,csarAmount,csarBuyItem,csarRefund,csarPayMethod,csarBlank,csarAlipayNo,csarBuyerAccount,csarUpdateTime,csarAddTime,csarStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csarId,csarHost,csarBankType,csarTradeNo,csarMember,csarRealName,csarAmount,csarBuyItem,csarBuyCount,csarRefund,csarPayMethod,csarBlank,csarAlipayNo,csarBuyerAccount,csarUpdateTime,csarAddTime,csarStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>接口充值管理</title>
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


<!------------------------LAZY3Q_JSP_BODY------------------------>
<!------------------------LAZY3Q_JSP_BODY------------------------>


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
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"接口充值管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsAlipayRecord" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="alipayrecord.query.jsp"%>

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
			<dl ref="com.ccclubs.model.CsAlipayRecord" class="display dropdowan" style="margin-left:30px;">
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
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="alipayrecord_table" ref="CsAlipayRecord" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarId || #request.defines['csarId']!=null">
			 <td <s:if test="#request.defines['csarId']>0">colspan="${defines["csarId"]}" ${all?"width":"iwidth"}="${defines["csarId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csarId"   title="编号">
			 	<a class="${desc=="csar_id" ? "desc" : ""}${asc=="csar_id" ? "asc" : ""}" href="?${desc=="csar_id" ? "asc=csar_id" : ""}${(asc=="csar_id" || desc!="csar_id" )? "desc=csar_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csarId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarHost || #request.defines['csarHost']!=null">
			 <td <s:if test="#request.defines['csarHost']>0">colspan="${defines["csarHost"]}" ${all?"width":"iwidth"}="${defines["csarHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csarHost"   title="运营地区">
			 	<a class="${desc=="csar_host" ? "desc" : ""}${asc=="csar_host" ? "asc" : ""}" href="?${desc=="csar_host" ? "asc=csar_host" : ""}${(asc=="csar_host" || desc!="csar_host" )? "desc=csar_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	运营地区
			 	</a>
			 	${lz:set("checkeds[]","csarHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarBankType || #request.defines['csarBankType']!=null">
			 <td <s:if test="#request.defines['csarBankType']>0">colspan="${defines["csarBankType"]}" ${all?"width":"iwidth"}="${defines["csarBankType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="csarBankType"   title="接口类型">
			 	<a class="${desc=="csar_bank_type" ? "desc" : ""}${asc=="csar_bank_type" ? "asc" : ""}" href="?${desc=="csar_bank_type" ? "asc=csar_bank_type" : ""}${(asc=="csar_bank_type" || desc!="csar_bank_type" )? "desc=csar_bank_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	接口类型
			 	</a>
			 	${lz:set("checkeds[]","csarBankType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarTradeNo || #request.defines['csarTradeNo']!=null">
			 <td <s:if test="#request.defines['csarTradeNo']>0">colspan="${defines["csarTradeNo"]}" ${all?"width":"iwidth"}="${defines["csarTradeNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="4" ref="csarTradeNo" flagKey  title="交易号">
			 	<a class="${desc=="csar_trade_no" ? "desc" : ""}${asc=="csar_trade_no" ? "asc" : ""}" href="?${desc=="csar_trade_no" ? "asc=csar_trade_no" : ""}${(asc=="csar_trade_no" || desc!="csar_trade_no" )? "desc=csar_trade_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	交易号
			 	</a>
			 	${lz:set("checkeds[]","csarTradeNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarMember || #request.defines['csarMember']!=null">
			 <td <s:if test="#request.defines['csarMember']>0">colspan="${defines["csarMember"]}" ${all?"width":"iwidth"}="${defines["csarMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csarMember"   title="充值会员">
			 	<a class="${desc=="csar_member" ? "desc" : ""}${asc=="csar_member" ? "asc" : ""}" href="?${desc=="csar_member" ? "asc=csar_member" : ""}${(asc=="csar_member" || desc!="csar_member" )? "desc=csar_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	充值会员
			 	</a>
			 	${lz:set("checkeds[]","csarMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarRealName || #request.defines['csarRealName']!=null">
			 <td <s:if test="#request.defines['csarRealName']>0">colspan="${defines["csarRealName"]}" ${all?"width":"iwidth"}="${defines["csarRealName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="6" ref="csarRealName"   title="会员姓名">
			 	<a class="${desc=="csar_real_name" ? "desc" : ""}${asc=="csar_real_name" ? "asc" : ""}" href="?${desc=="csar_real_name" ? "asc=csar_real_name" : ""}${(asc=="csar_real_name" || desc!="csar_real_name" )? "desc=csar_real_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	会员姓名
			 	</a>
			 	${lz:set("checkeds[]","csarRealName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarAmount || #request.defines['csarAmount']!=null">
			 <td <s:if test="#request.defines['csarAmount']>0">colspan="${defines["csarAmount"]}" ${all?"width":"iwidth"}="${defines["csarAmount"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csarAmount"   title="充值金额">
			 	<a class="${desc=="csar_amount" ? "desc" : ""}${asc=="csar_amount" ? "asc" : ""}" href="?${desc=="csar_amount" ? "asc=csar_amount" : ""}${(asc=="csar_amount" || desc!="csar_amount" )? "desc=csar_amount" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	充值金额
			 	</a>
			 	${lz:set("checkeds[]","csarAmount")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarBuyItem || #request.defines['csarBuyItem']!=null">
			 <td <s:if test="#request.defines['csarBuyItem']>0">colspan="${defines["csarBuyItem"]}" ${all?"width":"iwidth"}="${defines["csarBuyItem"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csarBuyItem"   title="购买宝贝">
			 	<a class="${desc=="csar_buy_item" ? "desc" : ""}${asc=="csar_buy_item" ? "asc" : ""}" href="?${desc=="csar_buy_item" ? "asc=csar_buy_item" : ""}${(asc=="csar_buy_item" || desc!="csar_buy_item" )? "desc=csar_buy_item" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	购买宝贝
			 	</a>
			 	${lz:set("checkeds[]","csarBuyItem")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarBuyCount || #request.defines['csarBuyCount']!=null">
			 <td <s:if test="#request.defines['csarBuyCount']>0">colspan="${defines["csarBuyCount"]}" ${all?"width":"iwidth"}="${defines["csarBuyCount"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="csarBuyCount"   title="购买个数">
			 	<a class="${desc=="csar_buy_count" ? "desc" : ""}${asc=="csar_buy_count" ? "asc" : ""}" href="?${desc=="csar_buy_count" ? "asc=csar_buy_count" : ""}${(asc=="csar_buy_count" || desc!="csar_buy_count" )? "desc=csar_buy_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	购买个数
			 	</a>
			 	${lz:set("checkeds[]","csarBuyCount")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarRefund || #request.defines['csarRefund']!=null">
			 <td <s:if test="#request.defines['csarRefund']>0">colspan="${defines["csarRefund"]}" ${all?"width":"iwidth"}="${defines["csarRefund"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csarRefund"   title="已退金额">
			 	<a class="${desc=="csar_refund" ? "desc" : ""}${asc=="csar_refund" ? "asc" : ""}" href="?${desc=="csar_refund" ? "asc=csar_refund" : ""}${(asc=="csar_refund" || desc!="csar_refund" )? "desc=csar_refund" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	已退金额
			 	</a>
			 	${lz:set("checkeds[]","csarRefund")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarPayMethod || #request.defines['csarPayMethod']!=null">
			 <td <s:if test="#request.defines['csarPayMethod']>0">colspan="${defines["csarPayMethod"]}" ${all?"width":"iwidth"}="${defines["csarPayMethod"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="csarPayMethod"   title="支付接口">
			 	<a class="${desc=="csar_pay_method" ? "desc" : ""}${asc=="csar_pay_method" ? "asc" : ""}" href="?${desc=="csar_pay_method" ? "asc=csar_pay_method" : ""}${(asc=="csar_pay_method" || desc!="csar_pay_method" )? "desc=csar_pay_method" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	支付接口
			 	</a>
			 	${lz:set("checkeds[]","csarPayMethod")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarBlank || #request.defines['csarBlank']!=null">
			 <td <s:if test="#request.defines['csarBlank']>0">colspan="${defines["csarBlank"]}" ${all?"width":"iwidth"}="${defines["csarBlank"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="csarBlank"   title="银行名称">
			 	<a class="${desc=="csar_blank" ? "desc" : ""}${asc=="csar_blank" ? "asc" : ""}" href="?${desc=="csar_blank" ? "asc=csar_blank" : ""}${(asc=="csar_blank" || desc!="csar_blank" )? "desc=csar_blank" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	银行名称
			 	</a>
			 	${lz:set("checkeds[]","csarBlank")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarAlipayNo || #request.defines['csarAlipayNo']!=null">
			 <td <s:if test="#request.defines['csarAlipayNo']>0">colspan="${defines["csarAlipayNo"]}" ${all?"width":"iwidth"}="${defines["csarAlipayNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="csarAlipayNo"   title="支付宝交易号">
			 	<a class="${desc=="csar_alipay_no" ? "desc" : ""}${asc=="csar_alipay_no" ? "asc" : ""}" href="?${desc=="csar_alipay_no" ? "asc=csar_alipay_no" : ""}${(asc=="csar_alipay_no" || desc!="csar_alipay_no" )? "desc=csar_alipay_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	支付宝交易号
			 	</a>
			 	${lz:set("checkeds[]","csarAlipayNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarBuyerAccount || #request.defines['csarBuyerAccount']!=null">
			 <td <s:if test="#request.defines['csarBuyerAccount']>0">colspan="${defines["csarBuyerAccount"]}" ${all?"width":"iwidth"}="${defines["csarBuyerAccount"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="csarBuyerAccount"   title="支付宝帐号">
			 	<a class="${desc=="csar_buyer_account" ? "desc" : ""}${asc=="csar_buyer_account" ? "asc" : ""}" href="?${desc=="csar_buyer_account" ? "asc=csar_buyer_account" : ""}${(asc=="csar_buyer_account" || desc!="csar_buyer_account" )? "desc=csar_buyer_account" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	支付宝帐号
			 	</a>
			 	${lz:set("checkeds[]","csarBuyerAccount")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarClientId || #request.defines['csarClientId']!=null">
			 <td <s:if test="#request.defines['csarClientId']>0">colspan="${defines["csarClientId"]}" ${all?"width":"iwidth"}="${defines["csarClientId"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="csarClientId"   title="三方客户端ID">
			 	<a class="${desc=="csar_client_id" ? "desc" : ""}${asc=="csar_client_id" ? "asc" : ""}" href="?${desc=="csar_client_id" ? "asc=csar_client_id" : ""}${(asc=="csar_client_id" || desc!="csar_client_id" )? "desc=csar_client_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	三方客户端ID
			 	</a>
			 	${lz:set("checkeds[]","csarClientId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarThirdBack || #request.defines['csarThirdBack']!=null">
			 <td <s:if test="#request.defines['csarThirdBack']>0">colspan="${defines["csarThirdBack"]}" ${all?"width":"iwidth"}="${defines["csarThirdBack"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="16" ref="csarThirdBack"   title="三方回调">
			 	<a class="${desc=="csar_third_back" ? "desc" : ""}${asc=="csar_third_back" ? "asc" : ""}" href="?${desc=="csar_third_back" ? "asc=csar_third_back" : ""}${(asc=="csar_third_back" || desc!="csar_third_back" )? "desc=csar_third_back" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	三方回调
			 	</a>
			 	${lz:set("checkeds[]","csarThirdBack")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarUpdateTime || #request.defines['csarUpdateTime']!=null">
			 <td <s:if test="#request.defines['csarUpdateTime']>0">colspan="${defines["csarUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csarUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="17" ref="csarUpdateTime"   title="修改时间">
			 	<a class="${desc=="csar_update_time" ? "desc" : ""}${asc=="csar_update_time" ? "asc" : ""}" href="?${desc=="csar_update_time" ? "asc=csar_update_time" : ""}${(asc=="csar_update_time" || desc!="csar_update_time" )? "desc=csar_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csarUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarAddTime || #request.defines['csarAddTime']!=null">
			 <td <s:if test="#request.defines['csarAddTime']>0">colspan="${defines["csarAddTime"]}" ${all?"width":"iwidth"}="${defines["csarAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="18" ref="csarAddTime"   title="添加时间">
			 	<a class="${desc=="csar_add_time" ? "desc" : ""}${asc=="csar_add_time" ? "asc" : ""}" href="?${desc=="csar_add_time" ? "asc=csar_add_time" : ""}${(asc=="csar_add_time" || desc!="csar_add_time" )? "desc=csar_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csarAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarStatus || #request.defines['csarStatus']!=null">
			 <td <s:if test="#request.defines['csarStatus']>0">colspan="${defines["csarStatus"]}" ${all?"width":"iwidth"}="${defines["csarStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="19" ref="csarStatus"   title="状态">
			 	<a class="${desc=="csar_status" ? "desc" : ""}${asc=="csar_status" ? "asc" : ""}" href="?${desc=="csar_status" ? "asc=csar_status" : ""}${(asc=="csar_status" || desc!="csar_status" )? "desc=csar_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csarStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="20" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="21" ref="operation">
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
		  <tr id="${item.csarId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csarId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarId || #request.defines['csarId']!=null">
				<s:if test="#request.defines['csarId']>0">
					${lz:set("注释","****csarId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarId",lz:indexOf(fieldName,"csarId")>-1)}
				  		<s:if test="#request.atCsarId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarId字段的字串格式化输出****")}
					<td ref="csarId" class="td ">
						 
						 	${lz:or(item$csarId[i.count-1],(lz:left(item.csarId$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarHost || #request.defines['csarHost']!=null">
				<s:if test="#request.defines['csarHost']>0">
					${lz:set("注释","****csarHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarHost",lz:indexOf(fieldName,"csarHost")>-1)}
				  		<s:if test="#request.atCsarHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarHost字段的字串格式化输出****")}
					<td ref="csarHost" class="td " relate="${item.csarHost}">
						 
						 	<a <s:if test="#item.csarHost!=null && #item.csarHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csarHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csarHost[i.count-1],(lz:left(item.csarHost$,100)))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarBankType || #request.defines['csarBankType']!=null">
				<s:if test="#request.defines['csarBankType']>0">
					${lz:set("注释","****csarBankType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarBankType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarBankType",lz:indexOf(fieldName,"csarBankType")>-1)}
				  		<s:if test="#request.atCsarBankType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarBankType字段的字串格式化输出****")}
					<td ref="csarBankType" class="td ">
						 
						 	${lz:or(item$csarBankType[i.count-1],(lz:left(item.csarBankType$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarTradeNo || #request.defines['csarTradeNo']!=null">
				<s:if test="#request.defines['csarTradeNo']>0">
					${lz:set("注释","****csarTradeNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarTradeNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarTradeNo",lz:indexOf(fieldName,"csarTradeNo")>-1)}
				  		<s:if test="#request.atCsarTradeNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarTradeNo字段的字串格式化输出****")}
					<td ref="csarTradeNo" class="td  node">
						 
						 	${lz:or(item$csarTradeNo[i.count-1],(lz:left(item.csarTradeNo$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarMember || #request.defines['csarMember']!=null">
				<s:if test="#request.defines['csarMember']>0">
					${lz:set("注释","****csarMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarMember",lz:indexOf(fieldName,"csarMember")>-1)}
				  		<s:if test="#request.atCsarMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarMember字段的字串格式化输出****")}
					<td ref="csarMember" class="td " relate="${item.csarMember}">
						 
						 	<a <s:if test="#item.csarMember!=null && #item.csarMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csarMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csarMember[i.count-1],(lz:left(item.csarMember$,100)))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarRealName || #request.defines['csarRealName']!=null">
				<s:if test="#request.defines['csarRealName']>0">
					${lz:set("注释","****csarRealName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarRealName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarRealName",lz:indexOf(fieldName,"csarRealName")>-1)}
				  		<s:if test="#request.atCsarRealName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarRealName字段的字串格式化输出****")}
					<td ref="csarRealName" class="td ">
						 
						 	${lz:or(item$csarRealName[i.count-1],(lz:left(item.csarRealName$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarAmount || #request.defines['csarAmount']!=null">
				<s:if test="#request.defines['csarAmount']>0">
					${lz:set("注释","****csarAmount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarAmount))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarAmount",lz:indexOf(fieldName,"csarAmount")>-1)}
				  		<s:if test="#request.atCsarAmount==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarAmount字段的字串格式化输出****")}
					<td ref="csarAmount" class="td ">
						 
						 	${lz:or(item$csarAmount[i.count-1],(lz:left(item.csarAmount$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarBuyItem || #request.defines['csarBuyItem']!=null">
				<s:if test="#request.defines['csarBuyItem']>0">
					${lz:set("注释","****csarBuyItem关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarBuyItem))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarBuyItem",lz:indexOf(fieldName,"csarBuyItem")>-1)}
				  		<s:if test="#request.atCsarBuyItem==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarBuyItem字段的字串格式化输出****")}
					<td ref="csarBuyItem" class="td " relate="${item.csarBuyItem}">
						 
						 	<a <s:if test="#item.csarBuyItem!=null && #item.csarBuyItem!=''"> onclick="window.href('${basePath}${proname}/fee/item_details.do?key=${item.csarBuyItem}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csarBuyItem[i.count-1],(lz:left(item.csarBuyItem$,100)))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarBuyCount || #request.defines['csarBuyCount']!=null">
				<s:if test="#request.defines['csarBuyCount']>0">
					${lz:set("注释","****csarBuyCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarBuyCount))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarBuyCount",lz:indexOf(fieldName,"csarBuyCount")>-1)}
				  		<s:if test="#request.atCsarBuyCount==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarBuyCount字段的字串格式化输出****")}
					<td ref="csarBuyCount" class="td ">
						 
						 	${lz:or(item$csarBuyCount[i.count-1],(lz:left(item.csarBuyCount$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarRefund || #request.defines['csarRefund']!=null">
				<s:if test="#request.defines['csarRefund']>0">
					${lz:set("注释","****csarRefund关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarRefund))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarRefund",lz:indexOf(fieldName,"csarRefund")>-1)}
				  		<s:if test="#request.atCsarRefund==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarRefund字段的字串格式化输出****")}
					<td ref="csarRefund" class="td ">
						 
						 	${lz:or(item$csarRefund[i.count-1],(lz:left(item.csarRefund$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarPayMethod || #request.defines['csarPayMethod']!=null">
				<s:if test="#request.defines['csarPayMethod']>0">
					${lz:set("注释","****csarPayMethod关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarPayMethod))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarPayMethod",lz:indexOf(fieldName,"csarPayMethod")>-1)}
				  		<s:if test="#request.atCsarPayMethod==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarPayMethod字段的字串格式化输出****")}
					<td ref="csarPayMethod" class="td ">
						 
						 	${lz:or(item$csarPayMethod[i.count-1],(lz:left(item.csarPayMethod$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarBlank || #request.defines['csarBlank']!=null">
				<s:if test="#request.defines['csarBlank']>0">
					${lz:set("注释","****csarBlank关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarBlank))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarBlank",lz:indexOf(fieldName,"csarBlank")>-1)}
				  		<s:if test="#request.atCsarBlank==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarBlank字段的字串格式化输出****")}
					<td ref="csarBlank" class="td ">
						 
						 	${lz:or(item$csarBlank[i.count-1],(lz:left(item.csarBlank$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarAlipayNo || #request.defines['csarAlipayNo']!=null">
				<s:if test="#request.defines['csarAlipayNo']>0">
					${lz:set("注释","****csarAlipayNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarAlipayNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarAlipayNo",lz:indexOf(fieldName,"csarAlipayNo")>-1)}
				  		<s:if test="#request.atCsarAlipayNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarAlipayNo字段的字串格式化输出****")}
					<td ref="csarAlipayNo" class="td ">
						 
						 	${lz:or(item$csarAlipayNo[i.count-1],(lz:left(item.csarAlipayNo$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarBuyerAccount || #request.defines['csarBuyerAccount']!=null">
				<s:if test="#request.defines['csarBuyerAccount']>0">
					${lz:set("注释","****csarBuyerAccount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarBuyerAccount))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarBuyerAccount",lz:indexOf(fieldName,"csarBuyerAccount")>-1)}
				  		<s:if test="#request.atCsarBuyerAccount==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarBuyerAccount字段的字串格式化输出****")}
					<td ref="csarBuyerAccount" class="td ">
						 
						 	${lz:or(item$csarBuyerAccount[i.count-1],(lz:left(item.csarBuyerAccount$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarClientId || #request.defines['csarClientId']!=null">
				<s:if test="#request.defines['csarClientId']>0">
					${lz:set("注释","****csarClientId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarClientId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarClientId",lz:indexOf(fieldName,"csarClientId")>-1)}
				  		<s:if test="#request.atCsarClientId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarClientId字段的字串格式化输出****")}
					<td ref="csarClientId" class="td " relate="${item.csarClientId}">
						 
						 	<a <s:if test="#item.csarClientId!=null && #item.csarClientId!=''"> onclick="window.href('${basePath}${proname}/configurator/apiclient_details.do?key=${item.csarClientId}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csarClientId[i.count-1],(lz:left(item.csarClientId$,100)))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarThirdBack || #request.defines['csarThirdBack']!=null">
				<s:if test="#request.defines['csarThirdBack']>0">
					${lz:set("注释","****csarThirdBack关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarThirdBack))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarThirdBack",lz:indexOf(fieldName,"csarThirdBack")>-1)}
				  		<s:if test="#request.atCsarThirdBack==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarThirdBack字段的字串格式化输出****")}
					<td ref="csarThirdBack" class="td ">
						 
						 	${lz:or(item$csarThirdBack[i.count-1],(lz:left(item.csarThirdBack$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarUpdateTime || #request.defines['csarUpdateTime']!=null">
				<s:if test="#request.defines['csarUpdateTime']>0">
					${lz:set("注释","****csarUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarUpdateTime",lz:indexOf(fieldName,"csarUpdateTime")>-1)}
				  		<s:if test="#request.atCsarUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarUpdateTime字段的字串格式化输出****")}
					<td ref="csarUpdateTime" class="td ">
						 
						 	${lz:or(item$csarUpdateTime[i.count-1],(lz:left(item.csarUpdateTime$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarAddTime || #request.defines['csarAddTime']!=null">
				<s:if test="#request.defines['csarAddTime']>0">
					${lz:set("注释","****csarAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarAddTime",lz:indexOf(fieldName,"csarAddTime")>-1)}
				  		<s:if test="#request.atCsarAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarAddTime字段的字串格式化输出****")}
					<td ref="csarAddTime" class="td ">
						 
						 	${lz:or(item$csarAddTime[i.count-1],(lz:left(item.csarAddTime$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarStatus || #request.defines['csarStatus']!=null">
				<s:if test="#request.defines['csarStatus']>0">
					${lz:set("注释","****csarStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarStatus",lz:indexOf(fieldName,"csarStatus")>-1)}
				  		<s:if test="#request.atCsarStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarStatus字段的字串格式化输出****")}
					<td ref="csarStatus" class="td ">
						 
						 	${lz:or(item$csarStatus[i.count-1],(lz:left(item.csarStatus$,100)))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.csarId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectAlipayrecord('${item.csarId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelAlipayrecord('${item.csarId}','${item.csarTradeNo}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsAlipayrecord('${item.csarId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csarId || #request.defines['csarId']!=null">
				<s:if test="#request.defines['csarId']>0">
					${lz:set("注释","****csarId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarId",lz:indexOf(fieldName,"csarId")>-1)}				  		
				  		<s:if test="#request.atCsarId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarHost || #request.defines['csarHost']!=null">
				<s:if test="#request.defines['csarHost']>0">
					${lz:set("注释","****csarHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarHost",lz:indexOf(fieldName,"csarHost")>-1)}				  		
				  		<s:if test="#request.atCsarHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarBankType || #request.defines['csarBankType']!=null">
				<s:if test="#request.defines['csarBankType']>0">
					${lz:set("注释","****csarBankType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarBankType",lz:indexOf(fieldName,"csarBankType")>-1)}				  		
				  		<s:if test="#request.atCsarBankType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarBankType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarTradeNo || #request.defines['csarTradeNo']!=null">
				<s:if test="#request.defines['csarTradeNo']>0">
					${lz:set("注释","****csarTradeNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarTradeNo",lz:indexOf(fieldName,"csarTradeNo")>-1)}				  		
				  		<s:if test="#request.atCsarTradeNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarTradeNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarMember || #request.defines['csarMember']!=null">
				<s:if test="#request.defines['csarMember']>0">
					${lz:set("注释","****csarMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarMember",lz:indexOf(fieldName,"csarMember")>-1)}				  		
				  		<s:if test="#request.atCsarMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarRealName || #request.defines['csarRealName']!=null">
				<s:if test="#request.defines['csarRealName']>0">
					${lz:set("注释","****csarRealName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarRealName",lz:indexOf(fieldName,"csarRealName")>-1)}				  		
				  		<s:if test="#request.atCsarRealName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarRealName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarAmount || #request.defines['csarAmount']!=null">
				<s:if test="#request.defines['csarAmount']>0">
					${lz:set("注释","****csarAmount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarAmount",lz:indexOf(fieldName,"csarAmount")>-1)}				  		
				  		<s:if test="#request.atCsarAmount==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarAmount[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarBuyItem || #request.defines['csarBuyItem']!=null">
				<s:if test="#request.defines['csarBuyItem']>0">
					${lz:set("注释","****csarBuyItem关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarBuyItem",lz:indexOf(fieldName,"csarBuyItem")>-1)}				  		
				  		<s:if test="#request.atCsarBuyItem==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarBuyItem[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarBuyCount || #request.defines['csarBuyCount']!=null">
				<s:if test="#request.defines['csarBuyCount']>0">
					${lz:set("注释","****csarBuyCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarBuyCount",lz:indexOf(fieldName,"csarBuyCount")>-1)}				  		
				  		<s:if test="#request.atCsarBuyCount==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarBuyCount[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarRefund || #request.defines['csarRefund']!=null">
				<s:if test="#request.defines['csarRefund']>0">
					${lz:set("注释","****csarRefund关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarRefund",lz:indexOf(fieldName,"csarRefund")>-1)}				  		
				  		<s:if test="#request.atCsarRefund==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarRefund[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarPayMethod || #request.defines['csarPayMethod']!=null">
				<s:if test="#request.defines['csarPayMethod']>0">
					${lz:set("注释","****csarPayMethod关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarPayMethod",lz:indexOf(fieldName,"csarPayMethod")>-1)}				  		
				  		<s:if test="#request.atCsarPayMethod==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarPayMethod[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarBlank || #request.defines['csarBlank']!=null">
				<s:if test="#request.defines['csarBlank']>0">
					${lz:set("注释","****csarBlank关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarBlank",lz:indexOf(fieldName,"csarBlank")>-1)}				  		
				  		<s:if test="#request.atCsarBlank==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarBlank[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarAlipayNo || #request.defines['csarAlipayNo']!=null">
				<s:if test="#request.defines['csarAlipayNo']>0">
					${lz:set("注释","****csarAlipayNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarAlipayNo",lz:indexOf(fieldName,"csarAlipayNo")>-1)}				  		
				  		<s:if test="#request.atCsarAlipayNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarAlipayNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarBuyerAccount || #request.defines['csarBuyerAccount']!=null">
				<s:if test="#request.defines['csarBuyerAccount']>0">
					${lz:set("注释","****csarBuyerAccount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarBuyerAccount",lz:indexOf(fieldName,"csarBuyerAccount")>-1)}				  		
				  		<s:if test="#request.atCsarBuyerAccount==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarBuyerAccount[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarClientId || #request.defines['csarClientId']!=null">
				<s:if test="#request.defines['csarClientId']>0">
					${lz:set("注释","****csarClientId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarClientId",lz:indexOf(fieldName,"csarClientId")>-1)}				  		
				  		<s:if test="#request.atCsarClientId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarClientId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarThirdBack || #request.defines['csarThirdBack']!=null">
				<s:if test="#request.defines['csarThirdBack']>0">
					${lz:set("注释","****csarThirdBack关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarThirdBack",lz:indexOf(fieldName,"csarThirdBack")>-1)}				  		
				  		<s:if test="#request.atCsarThirdBack==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarThirdBack[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarUpdateTime || #request.defines['csarUpdateTime']!=null">
				<s:if test="#request.defines['csarUpdateTime']>0">
					${lz:set("注释","****csarUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarUpdateTime",lz:indexOf(fieldName,"csarUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsarUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarAddTime || #request.defines['csarAddTime']!=null">
				<s:if test="#request.defines['csarAddTime']>0">
					${lz:set("注释","****csarAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarAddTime",lz:indexOf(fieldName,"csarAddTime")>-1)}				  		
				  		<s:if test="#request.atCsarAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarStatus || #request.defines['csarStatus']!=null">
				<s:if test="#request.defines['csarStatus']>0">
					${lz:set("注释","****csarStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarStatus",lz:indexOf(fieldName,"csarStatus")>-1)}				  		
				  		<s:if test="#request.atCsarStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 选择接口充值
	**/
	function SelectAlipayrecord(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改接口充值任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}alipayrecord_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新接口充值",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}alipayrecord_edit.do";
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
			var url = "${basePath}${namespace}alipayrecord_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除接口充值
	**/
	function DelAlipayrecord(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除接口充值["+flag+"]吗？</span>",
				title:"删除接口充值",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}alipayrecord_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选接口充值吗？</span>",
				title:"批量删除接口充值",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}alipayrecord_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示接口充值详情
	**/
	function DetailsAlipayrecord(id)
	{
		var url = "${basePath}${namespace}alipayrecord_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"接口充值详情",editable:false,visible:true}};
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
		DetailsAlipayrecord(checker.val());
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