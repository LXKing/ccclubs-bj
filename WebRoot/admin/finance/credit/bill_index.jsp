<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/credit/bill.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCreditBill.cscbId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"cscbId,cscbHost,cscbTitle,cscbMember,cscbCreditCard,cscbOrder,cscbViolat,cscbTrouble,cscbRepayTime,cscbUpdateTime,cscbAddTime,cscbEditor,cscbProcess,cscbState,cscbStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"cscbId,cscbHost,cscbTitle,cscbMember,cscbCreditCard,cscbOrder,cscbViolat,cscbTrouble,cscbValue,cscbPayment,cscbRepayTime,cscbUpdateTime,cscbAddTime,cscbEditor,cscbRemark,cscbProcess,cscbPaySerial,cscbState,cscbStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"cscbId,cscbHost,cscbTitle,cscbMember,cscbCreditCard,cscbOrder,cscbRepayTime,cscbAddTime,cscbProcess,cscbStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"cscbId,cscbHost,cscbTitle,cscbMember,cscbCreditCard,cscbOrder,cscbValue,cscbPayment,cscbRepayTime,cscbAddTime,cscbRemark,cscbProcess,cscbStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>信用账单管理</title>
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
<s:if test="#request.canExp[3]==true && #request.CTRL.canExp[3]==true">
<script>
//批量设置已付
$(function(){
	var button = $("<button onclick=\"batch()\" type=\"button\" class=\"edit\">批量处理已付</button>");
	button.appendTo(".toolbar");
})
function batch(){
	var url = "${basePath}${namespace}bill_edit.do";
	var params = {entrypoint:"${entrypoint}",ctrl:{title:"批量处理已付"}};
	href(url,params);
}
</script>
</s:if>
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
	<span class="caption">${empty title?"信用账单管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsCreditBill" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="bill.query.jsp"%>

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
			<s:if test="#request.CTRL.canAdd==true">
			<button type="button" class="add" onclick="AddBill();">
				添加
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
			<dl ref="com.ccclubs.model.CsCreditBill" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsCreditBill" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.cscbRemark || #request.defines['cscbRemark']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('remark')">备注信息</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.cscbProcess || #request.defines['cscbProcess']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('process')">跟踪信息</a>
					</s:if>
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table
				</dd>
			</dl>
			</s:if>
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="bill_table" ref="CsCreditBill" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbId || #request.defines['cscbId']!=null">
			 <td <s:if test="#request.defines['cscbId']>0">colspan="${defines["cscbId"]}" ${all?"width":"iwidth"}="${defines["cscbId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="cscbId" flagKey  title="编号">
			 	<a class="${desc=="cscb_id" ? "desc" : ""}${asc=="cscb_id" ? "asc" : ""}" href="?${desc=="cscb_id" ? "asc=cscb_id" : ""}${(asc=="cscb_id" || desc!="cscb_id" )? "desc=cscb_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","cscbId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbHost || #request.defines['cscbHost']!=null">
			 <td <s:if test="#request.defines['cscbHost']>0">colspan="${defines["cscbHost"]}" ${all?"width":"iwidth"}="${defines["cscbHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="cscbHost"   title="城市">
			 	<a class="${desc=="cscb_host" ? "desc" : ""}${asc=="cscb_host" ? "asc" : ""}" href="?${desc=="cscb_host" ? "asc=cscb_host" : ""}${(asc=="cscb_host" || desc!="cscb_host" )? "desc=cscb_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","cscbHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbTitle || #request.defines['cscbTitle']!=null">
			 <td <s:if test="#request.defines['cscbTitle']>0">colspan="${defines["cscbTitle"]}" ${all?"width":"iwidth"}="${defines["cscbTitle"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="cscbTitle"   title="账单描述">
			 	<a class="${desc=="cscb_title" ? "desc" : ""}${asc=="cscb_title" ? "asc" : ""}" href="?${desc=="cscb_title" ? "asc=cscb_title" : ""}${(asc=="cscb_title" || desc!="cscb_title" )? "desc=cscb_title" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	账单描述
			 	</a>
			 	${lz:set("checkeds[]","cscbTitle")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbMember || #request.defines['cscbMember']!=null">
			 <td <s:if test="#request.defines['cscbMember']>0">colspan="${defines["cscbMember"]}" ${all?"width":"iwidth"}="${defines["cscbMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="cscbMember"   title="所属会员">
			 	<a class="${desc=="cscb_member" ? "desc" : ""}${asc=="cscb_member" ? "asc" : ""}" href="?${desc=="cscb_member" ? "asc=cscb_member" : ""}${(asc=="cscb_member" || desc!="cscb_member" )? "desc=cscb_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属会员
			 	</a>
			 	${lz:set("checkeds[]","cscbMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbCreditCard || #request.defines['cscbCreditCard']!=null">
			 <td <s:if test="#request.defines['cscbCreditCard']>0">colspan="${defines["cscbCreditCard"]}" ${all?"width":"iwidth"}="${defines["cscbCreditCard"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="cscbCreditCard"   title="所属信用卡">
			 	<a class="${desc=="cscb_credit_card" ? "desc" : ""}${asc=="cscb_credit_card" ? "asc" : ""}" href="?${desc=="cscb_credit_card" ? "asc=cscb_credit_card" : ""}${(asc=="cscb_credit_card" || desc!="cscb_credit_card" )? "desc=cscb_credit_card" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属信用卡
			 	</a>
			 	${lz:set("checkeds[]","cscbCreditCard")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbOrder || #request.defines['cscbOrder']!=null">
			 <td <s:if test="#request.defines['cscbOrder']>0">colspan="${defines["cscbOrder"]}" ${all?"width":"iwidth"}="${defines["cscbOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="cscbOrder"   title="关联订单">
			 	<a class="${desc=="cscb_order" ? "desc" : ""}${asc=="cscb_order" ? "asc" : ""}" href="?${desc=="cscb_order" ? "asc=cscb_order" : ""}${(asc=="cscb_order" || desc!="cscb_order" )? "desc=cscb_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	关联订单
			 	</a>
			 	${lz:set("checkeds[]","cscbOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbViolat || #request.defines['cscbViolat']!=null">
			 <td <s:if test="#request.defines['cscbViolat']>0">colspan="${defines["cscbViolat"]}" ${all?"width":"iwidth"}="${defines["cscbViolat"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="cscbViolat"   title="关联违章">
			 	<a class="${desc=="cscb_violat" ? "desc" : ""}${asc=="cscb_violat" ? "asc" : ""}" href="?${desc=="cscb_violat" ? "asc=cscb_violat" : ""}${(asc=="cscb_violat" || desc!="cscb_violat" )? "desc=cscb_violat" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	关联违章
			 	</a>
			 	${lz:set("checkeds[]","cscbViolat")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbTrouble || #request.defines['cscbTrouble']!=null">
			 <td <s:if test="#request.defines['cscbTrouble']>0">colspan="${defines["cscbTrouble"]}" ${all?"width":"iwidth"}="${defines["cscbTrouble"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="cscbTrouble"   title="关联事故">
			 	<a class="${desc=="cscb_trouble" ? "desc" : ""}${asc=="cscb_trouble" ? "asc" : ""}" href="?${desc=="cscb_trouble" ? "asc=cscb_trouble" : ""}${(asc=="cscb_trouble" || desc!="cscb_trouble" )? "desc=cscb_trouble" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	关联事故
			 	</a>
			 	${lz:set("checkeds[]","cscbTrouble")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbValue || #request.defines['cscbValue']!=null">
			 <td <s:if test="#request.defines['cscbValue']>0">colspan="${defines["cscbValue"]}" ${all?"width":"iwidth"}="${defines["cscbValue"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="cscbValue"   title="总欠账额">
			 	<a class="${desc=="cscb_value" ? "desc" : ""}${asc=="cscb_value" ? "asc" : ""}" href="?${desc=="cscb_value" ? "asc=cscb_value" : ""}${(asc=="cscb_value" || desc!="cscb_value" )? "desc=cscb_value" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	总欠账额
			 	</a>
			 	${lz:set("checkeds[]","cscbValue")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbPayment || #request.defines['cscbPayment']!=null">
			 <td <s:if test="#request.defines['cscbPayment']>0">colspan="${defines["cscbPayment"]}" ${all?"width":"iwidth"}="${defines["cscbPayment"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="cscbPayment"   title="已付额">
			 	<a class="${desc=="cscb_payment" ? "desc" : ""}${asc=="cscb_payment" ? "asc" : ""}" href="?${desc=="cscb_payment" ? "asc=cscb_payment" : ""}${(asc=="cscb_payment" || desc!="cscb_payment" )? "desc=cscb_payment" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	已付额
			 	</a>
			 	${lz:set("checkeds[]","cscbPayment")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbRepayTime || #request.defines['cscbRepayTime']!=null">
			 <td <s:if test="#request.defines['cscbRepayTime']>0">colspan="${defines["cscbRepayTime"]}" ${all?"width":"iwidth"}="${defines["cscbRepayTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="11" ref="cscbRepayTime"   title="还款时间">
			 	<a class="${desc=="cscb_repay_time" ? "desc" : ""}${asc=="cscb_repay_time" ? "asc" : ""}" href="?${desc=="cscb_repay_time" ? "asc=cscb_repay_time" : ""}${(asc=="cscb_repay_time" || desc!="cscb_repay_time" )? "desc=cscb_repay_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	还款时间
			 	</a>
			 	${lz:set("checkeds[]","cscbRepayTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbUpdateTime || #request.defines['cscbUpdateTime']!=null">
			 <td <s:if test="#request.defines['cscbUpdateTime']>0">colspan="${defines["cscbUpdateTime"]}" ${all?"width":"iwidth"}="${defines["cscbUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="12" ref="cscbUpdateTime"   title="修改时间">
			 	<a class="${desc=="cscb_update_time" ? "desc" : ""}${asc=="cscb_update_time" ? "asc" : ""}" href="?${desc=="cscb_update_time" ? "asc=cscb_update_time" : ""}${(asc=="cscb_update_time" || desc!="cscb_update_time" )? "desc=cscb_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","cscbUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbAddTime || #request.defines['cscbAddTime']!=null">
			 <td <s:if test="#request.defines['cscbAddTime']>0">colspan="${defines["cscbAddTime"]}" ${all?"width":"iwidth"}="${defines["cscbAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="13" ref="cscbAddTime"   title="添加时间">
			 	<a class="${desc=="cscb_add_time" ? "desc" : ""}${asc=="cscb_add_time" ? "asc" : ""}" href="?${desc=="cscb_add_time" ? "asc=cscb_add_time" : ""}${(asc=="cscb_add_time" || desc!="cscb_add_time" )? "desc=cscb_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","cscbAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbEditor || #request.defines['cscbEditor']!=null">
			 <td <s:if test="#request.defines['cscbEditor']>0">colspan="${defines["cscbEditor"]}" ${all?"width":"iwidth"}="${defines["cscbEditor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="cscbEditor"   title="操作人员">
			 	<a class="${desc=="cscb_editor" ? "desc" : ""}${asc=="cscb_editor" ? "asc" : ""}" href="?${desc=="cscb_editor" ? "asc=cscb_editor" : ""}${(asc=="cscb_editor" || desc!="cscb_editor" )? "desc=cscb_editor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	操作人员
			 	</a>
			 	${lz:set("checkeds[]","cscbEditor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbRemark || #request.defines['cscbRemark']!=null">
			 <td <s:if test="#request.defines['cscbRemark']>0">colspan="${defines["cscbRemark"]}" ${all?"width":"iwidth"}="${defines["cscbRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="15" ref="cscbRemark"   title="备注信息">
			 	<a class="${desc=="cscb_remark" ? "desc" : ""}${asc=="cscb_remark" ? "asc" : ""}" href="?${desc=="cscb_remark" ? "asc=cscb_remark" : ""}${(asc=="cscb_remark" || desc!="cscb_remark" )? "desc=cscb_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注信息
			 	</a>
			 	${lz:set("checkeds[]","cscbRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbProcess || #request.defines['cscbProcess']!=null">
			 <td <s:if test="#request.defines['cscbProcess']>0">colspan="${defines["cscbProcess"]}" ${all?"width":"iwidth"}="${defines["cscbProcess"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="16" ref="cscbProcess"   title="跟踪信息">
			 	<a class="${desc=="cscb_process" ? "desc" : ""}${asc=="cscb_process" ? "asc" : ""}" href="?${desc=="cscb_process" ? "asc=cscb_process" : ""}${(asc=="cscb_process" || desc!="cscb_process" )? "desc=cscb_process" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	跟踪信息
			 	</a>
			 	${lz:set("checkeds[]","cscbProcess")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbPaySerial || #request.defines['cscbPaySerial']!=null">
			 <td <s:if test="#request.defines['cscbPaySerial']>0">colspan="${defines["cscbPaySerial"]}" ${all?"width":"iwidth"}="${defines["cscbPaySerial"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="17" ref="cscbPaySerial"   title="还款交易号">
			 	<a class="${desc=="cscb_pay_serial" ? "desc" : ""}${asc=="cscb_pay_serial" ? "asc" : ""}" href="?${desc=="cscb_pay_serial" ? "asc=cscb_pay_serial" : ""}${(asc=="cscb_pay_serial" || desc!="cscb_pay_serial" )? "desc=cscb_pay_serial" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	还款交易号
			 	</a>
			 	${lz:set("checkeds[]","cscbPaySerial")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbState || #request.defines['cscbState']!=null">
			 <td <s:if test="#request.defines['cscbState']>0">colspan="${defines["cscbState"]}" ${all?"width":"iwidth"}="${defines["cscbState"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="18" ref="cscbState"   title="同步状态">
			 	<a class="${desc=="cscb_state" ? "desc" : ""}${asc=="cscb_state" ? "asc" : ""}" href="?${desc=="cscb_state" ? "asc=cscb_state" : ""}${(asc=="cscb_state" || desc!="cscb_state" )? "desc=cscb_state" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	同步状态
			 	</a>
			 	${lz:set("checkeds[]","cscbState")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscbStatus || #request.defines['cscbStatus']!=null">
			 <td <s:if test="#request.defines['cscbStatus']>0">colspan="${defines["cscbStatus"]}" ${all?"width":"iwidth"}="${defines["cscbStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="19" ref="cscbStatus"   title="状态">
			 	<a class="${desc=="cscb_status" ? "desc" : ""}${asc=="cscb_status" ? "asc" : ""}" href="?${desc=="cscb_status" ? "asc=cscb_status" : ""}${(asc=="cscb_status" || desc!="cscb_status" )? "desc=cscb_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","cscbStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="20" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="21" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddBill()" style="text-decoration:underline">添加</a>
				</s:if>
				<s:else>&nbsp;</s:else>
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
		  <tr id="${item.cscbId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.cscbId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbId || #request.defines['cscbId']!=null">
				<s:if test="#request.defines['cscbId']>0">
					${lz:set("注释","****cscbId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbId",lz:indexOf(fieldName,"cscbId")>-1)}
				  		<s:if test="#request.atCscbId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbId字段的字串格式化输出****")}
					<td ref="cscbId" class="td  node">
						 
						 	${lz:or(item$cscbId[i.count-1],lz:left(item.cscbId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbHost || #request.defines['cscbHost']!=null">
				<s:if test="#request.defines['cscbHost']>0">
					${lz:set("注释","****cscbHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbHost",lz:indexOf(fieldName,"cscbHost")>-1)}
				  		<s:if test="#request.atCscbHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbHost字段的字串格式化输出****")}
					<td ref="cscbHost" class="td " relate="${item.cscbHost}">
						 
						 	<a <s:if test="#item.cscbHost!=null && #item.cscbHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.cscbHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscbHost[i.count-1],lz:left(item.cscbHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbTitle || #request.defines['cscbTitle']!=null">
				<s:if test="#request.defines['cscbTitle']>0">
					${lz:set("注释","****cscbTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbTitle))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbTitle",lz:indexOf(fieldName,"cscbTitle")>-1)}
				  		<s:if test="#request.atCscbTitle==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbTitle字段的字串格式化输出****")}
					<td ref="cscbTitle" class="td ">
						 
						 	${lz:or(item$cscbTitle[i.count-1],lz:left(item.cscbTitle$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbMember || #request.defines['cscbMember']!=null">
				<s:if test="#request.defines['cscbMember']>0">
					${lz:set("注释","****cscbMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbMember",lz:indexOf(fieldName,"cscbMember")>-1)}
				  		<s:if test="#request.atCscbMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbMember字段的字串格式化输出****")}
					<td ref="cscbMember" class="td " relate="${item.cscbMember}">
						 
						 	<a <s:if test="#item.cscbMember!=null && #item.cscbMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.cscbMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscbMember[i.count-1],lz:left(item.cscbMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbCreditCard || #request.defines['cscbCreditCard']!=null">
				<s:if test="#request.defines['cscbCreditCard']>0">
					${lz:set("注释","****cscbCreditCard关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbCreditCard))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbCreditCard",lz:indexOf(fieldName,"cscbCreditCard")>-1)}
				  		<s:if test="#request.atCscbCreditCard==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbCreditCard字段的字串格式化输出****")}
					<td ref="cscbCreditCard" class="td " relate="${item.cscbCreditCard}">
						 
						 	<a <s:if test="#item.cscbCreditCard!=null && #item.cscbCreditCard!=''"> onclick="window.href('${basePath}${proname}/user/credit/card_details.do?key=${item.cscbCreditCard}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscbCreditCard[i.count-1],lz:left(item.cscbCreditCard$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbOrder || #request.defines['cscbOrder']!=null">
				<s:if test="#request.defines['cscbOrder']>0">
					${lz:set("注释","****cscbOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbOrder",lz:indexOf(fieldName,"cscbOrder")>-1)}
				  		<s:if test="#request.atCscbOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbOrder字段的字串格式化输出****")}
					<td ref="cscbOrder" class="td " relate="${item.cscbOrder}">
						 
						 	<a <s:if test="#item.cscbOrder!=null && #item.cscbOrder!=''"> onclick="window.href('${basePath}${proname}/service/order_details.do?key=${item.cscbOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscbOrder[i.count-1],lz:left(item.cscbOrder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbViolat || #request.defines['cscbViolat']!=null">
				<s:if test="#request.defines['cscbViolat']>0">
					${lz:set("注释","****cscbViolat关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbViolat))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbViolat",lz:indexOf(fieldName,"cscbViolat")>-1)}
				  		<s:if test="#request.atCscbViolat==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbViolat字段的字串格式化输出****")}
					<td ref="cscbViolat" class="td " relate="${item.cscbViolat}">
						 
						 	<a <s:if test="#item.cscbViolat!=null && #item.cscbViolat!=''"> onclick="window.href('${basePath}${proname}/service/violat_details.do?key=${item.cscbViolat}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscbViolat[i.count-1],lz:left(item.cscbViolat$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbTrouble || #request.defines['cscbTrouble']!=null">
				<s:if test="#request.defines['cscbTrouble']>0">
					${lz:set("注释","****cscbTrouble关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbTrouble))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbTrouble",lz:indexOf(fieldName,"cscbTrouble")>-1)}
				  		<s:if test="#request.atCscbTrouble==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbTrouble字段的字串格式化输出****")}
					<td ref="cscbTrouble" class="td " relate="${item.cscbTrouble}">
						 
						 	<a <s:if test="#item.cscbTrouble!=null && #item.cscbTrouble!=''"> onclick="window.href('${basePath}${proname}/service/trouble_details.do?key=${item.cscbTrouble}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscbTrouble[i.count-1],lz:left(item.cscbTrouble$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbValue || #request.defines['cscbValue']!=null">
				<s:if test="#request.defines['cscbValue']>0">
					${lz:set("注释","****cscbValue关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbValue))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbValue",lz:indexOf(fieldName,"cscbValue")>-1)}
				  		<s:if test="#request.atCscbValue==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbValue字段的字串格式化输出****")}
					<td ref="cscbValue" class="td ">
						 
						 	${lz:or(item$cscbValue[i.count-1],lz:left(item.cscbValue$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbPayment || #request.defines['cscbPayment']!=null">
				<s:if test="#request.defines['cscbPayment']>0">
					${lz:set("注释","****cscbPayment关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbPayment))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbPayment",lz:indexOf(fieldName,"cscbPayment")>-1)}
				  		<s:if test="#request.atCscbPayment==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbPayment字段的字串格式化输出****")}
					<td ref="cscbPayment" class="td ">
						 
						 	${lz:or(item$cscbPayment[i.count-1],lz:left(item.cscbPayment$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbRepayTime || #request.defines['cscbRepayTime']!=null">
				<s:if test="#request.defines['cscbRepayTime']>0">
					${lz:set("注释","****cscbRepayTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbRepayTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbRepayTime",lz:indexOf(fieldName,"cscbRepayTime")>-1)}
				  		<s:if test="#request.atCscbRepayTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbRepayTime字段的字串格式化输出****")}
					<td ref="cscbRepayTime" class="td ">
						 
						 	${lz:or(item$cscbRepayTime[i.count-1],lz:left(item.cscbRepayTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbUpdateTime || #request.defines['cscbUpdateTime']!=null">
				<s:if test="#request.defines['cscbUpdateTime']>0">
					${lz:set("注释","****cscbUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbUpdateTime",lz:indexOf(fieldName,"cscbUpdateTime")>-1)}
				  		<s:if test="#request.atCscbUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbUpdateTime字段的字串格式化输出****")}
					<td ref="cscbUpdateTime" class="td ">
						 
						 	${lz:or(item$cscbUpdateTime[i.count-1],lz:left(item.cscbUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbAddTime || #request.defines['cscbAddTime']!=null">
				<s:if test="#request.defines['cscbAddTime']>0">
					${lz:set("注释","****cscbAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbAddTime",lz:indexOf(fieldName,"cscbAddTime")>-1)}
				  		<s:if test="#request.atCscbAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbAddTime字段的字串格式化输出****")}
					<td ref="cscbAddTime" class="td ">
						 
						 	${lz:or(item$cscbAddTime[i.count-1],lz:left(item.cscbAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbEditor || #request.defines['cscbEditor']!=null">
				<s:if test="#request.defines['cscbEditor']>0">
					${lz:set("注释","****cscbEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbEditor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbEditor",lz:indexOf(fieldName,"cscbEditor")>-1)}
				  		<s:if test="#request.atCscbEditor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbEditor字段的字串格式化输出****")}
					<td ref="cscbEditor" class="td " relate="${item.cscbEditor}">
						 
						 	<a <s:if test="#item.cscbEditor!=null && #item.cscbEditor!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.cscbEditor}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscbEditor[i.count-1],lz:left(item.cscbEditor$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbRemark || #request.defines['cscbRemark']!=null">
				<s:if test="#request.defines['cscbRemark']>0">
					${lz:set("注释","****cscbRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbRemark",lz:indexOf(fieldName,"cscbRemark")>-1)}
				  		<s:if test="#request.atCscbRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbRemark字段的字串格式化输出****")}
					<td ref="cscbRemark" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscbId}','remark')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cscbRemark[i.count-1],lz:left(item.cscbRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbProcess || #request.defines['cscbProcess']!=null">
				<s:if test="#request.defines['cscbProcess']>0">
					${lz:set("注释","****cscbProcess关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbProcess))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbProcess",lz:indexOf(fieldName,"cscbProcess")>-1)}
				  		<s:if test="#request.atCscbProcess==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbProcess字段的字串格式化输出****")}
					<td ref="cscbProcess" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscbId}','process')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cscbProcess[i.count-1],lz:left(item.cscbProcess$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbPaySerial || #request.defines['cscbPaySerial']!=null">
				<s:if test="#request.defines['cscbPaySerial']>0">
					${lz:set("注释","****cscbPaySerial关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbPaySerial))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbPaySerial",lz:indexOf(fieldName,"cscbPaySerial")>-1)}
				  		<s:if test="#request.atCscbPaySerial==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbPaySerial字段的字串格式化输出****")}
					<td ref="cscbPaySerial" class="td ">
						 
						 	${lz:or(item$cscbPaySerial[i.count-1],lz:left(item.cscbPaySerial$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbState || #request.defines['cscbState']!=null">
				<s:if test="#request.defines['cscbState']>0">
					${lz:set("注释","****cscbState关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbState))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbState",lz:indexOf(fieldName,"cscbState")>-1)}
				  		<s:if test="#request.atCscbState==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbState字段的字串格式化输出****")}
					<td ref="cscbState" class="td ">
						 
						 	${lz:or(item$cscbState[i.count-1],lz:left(item.cscbState$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscbStatus || #request.defines['cscbStatus']!=null">
				<s:if test="#request.defines['cscbStatus']>0">
					${lz:set("注释","****cscbStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscbStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscbStatus",lz:indexOf(fieldName,"cscbStatus")>-1)}
				  		<s:if test="#request.atCscbStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscbStatus字段的字串格式化输出****")}
					<td ref="cscbStatus" class="td ">
						 
						 	${lz:or(item$cscbStatus[i.count-1],lz:left(item.cscbStatus$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('帐户代付','${item.cscbId}')">帐户代付</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[1]==true">
					<a href="javascript:HandleSel('撤销帐单','${item.cscbId}')">撤销帐单</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[2]==true">
					<a href="javascript:HandleSel('坏帐处理','${item.cscbId}')">坏帐处理</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[3]==true">
					<a href="javascript:HandleSel('已付处理','${item.cscbId}')">已付处理</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.cscbId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectBill('${item.cscbId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditBill('${item.cscbId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelBill('${item.cscbId}','${item.cscbId}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsBill('${item.cscbId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscbId || #request.defines['cscbId']!=null">
				<s:if test="#request.defines['cscbId']>0">
					${lz:set("注释","****cscbId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbId",lz:indexOf(fieldName,"cscbId")>-1)}				  		
				  		<s:if test="#request.atCscbId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbHost || #request.defines['cscbHost']!=null">
				<s:if test="#request.defines['cscbHost']>0">
					${lz:set("注释","****cscbHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbHost",lz:indexOf(fieldName,"cscbHost")>-1)}				  		
				  		<s:if test="#request.atCscbHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbTitle || #request.defines['cscbTitle']!=null">
				<s:if test="#request.defines['cscbTitle']>0">
					${lz:set("注释","****cscbTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbTitle",lz:indexOf(fieldName,"cscbTitle")>-1)}				  		
				  		<s:if test="#request.atCscbTitle==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbTitle[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbMember || #request.defines['cscbMember']!=null">
				<s:if test="#request.defines['cscbMember']>0">
					${lz:set("注释","****cscbMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbMember",lz:indexOf(fieldName,"cscbMember")>-1)}				  		
				  		<s:if test="#request.atCscbMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbCreditCard || #request.defines['cscbCreditCard']!=null">
				<s:if test="#request.defines['cscbCreditCard']>0">
					${lz:set("注释","****cscbCreditCard关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbCreditCard",lz:indexOf(fieldName,"cscbCreditCard")>-1)}				  		
				  		<s:if test="#request.atCscbCreditCard==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbCreditCard[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbOrder || #request.defines['cscbOrder']!=null">
				<s:if test="#request.defines['cscbOrder']>0">
					${lz:set("注释","****cscbOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbOrder",lz:indexOf(fieldName,"cscbOrder")>-1)}				  		
				  		<s:if test="#request.atCscbOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbViolat || #request.defines['cscbViolat']!=null">
				<s:if test="#request.defines['cscbViolat']>0">
					${lz:set("注释","****cscbViolat关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbViolat",lz:indexOf(fieldName,"cscbViolat")>-1)}				  		
				  		<s:if test="#request.atCscbViolat==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbViolat[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbTrouble || #request.defines['cscbTrouble']!=null">
				<s:if test="#request.defines['cscbTrouble']>0">
					${lz:set("注释","****cscbTrouble关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbTrouble",lz:indexOf(fieldName,"cscbTrouble")>-1)}				  		
				  		<s:if test="#request.atCscbTrouble==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbTrouble[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbValue || #request.defines['cscbValue']!=null">
				<s:if test="#request.defines['cscbValue']>0">
					${lz:set("注释","****cscbValue关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbValue",lz:indexOf(fieldName,"cscbValue")>-1)}				  		
				  		<s:if test="#request.atCscbValue==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbValue[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbPayment || #request.defines['cscbPayment']!=null">
				<s:if test="#request.defines['cscbPayment']>0">
					${lz:set("注释","****cscbPayment关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbPayment",lz:indexOf(fieldName,"cscbPayment")>-1)}				  		
				  		<s:if test="#request.atCscbPayment==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbPayment[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbRepayTime || #request.defines['cscbRepayTime']!=null">
				<s:if test="#request.defines['cscbRepayTime']>0">
					${lz:set("注释","****cscbRepayTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbRepayTime",lz:indexOf(fieldName,"cscbRepayTime")>-1)}				  		
				  		<s:if test="#request.atCscbRepayTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbRepayTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbUpdateTime || #request.defines['cscbUpdateTime']!=null">
				<s:if test="#request.defines['cscbUpdateTime']>0">
					${lz:set("注释","****cscbUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbUpdateTime",lz:indexOf(fieldName,"cscbUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCscbUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbAddTime || #request.defines['cscbAddTime']!=null">
				<s:if test="#request.defines['cscbAddTime']>0">
					${lz:set("注释","****cscbAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbAddTime",lz:indexOf(fieldName,"cscbAddTime")>-1)}				  		
				  		<s:if test="#request.atCscbAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbEditor || #request.defines['cscbEditor']!=null">
				<s:if test="#request.defines['cscbEditor']>0">
					${lz:set("注释","****cscbEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbEditor",lz:indexOf(fieldName,"cscbEditor")>-1)}				  		
				  		<s:if test="#request.atCscbEditor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbEditor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbRemark || #request.defines['cscbRemark']!=null">
				<s:if test="#request.defines['cscbRemark']>0">
					${lz:set("注释","****cscbRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbRemark",lz:indexOf(fieldName,"cscbRemark")>-1)}				  		
				  		<s:if test="#request.atCscbRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbProcess || #request.defines['cscbProcess']!=null">
				<s:if test="#request.defines['cscbProcess']>0">
					${lz:set("注释","****cscbProcess关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbProcess",lz:indexOf(fieldName,"cscbProcess")>-1)}				  		
				  		<s:if test="#request.atCscbProcess==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbProcess[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbPaySerial || #request.defines['cscbPaySerial']!=null">
				<s:if test="#request.defines['cscbPaySerial']>0">
					${lz:set("注释","****cscbPaySerial关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbPaySerial",lz:indexOf(fieldName,"cscbPaySerial")>-1)}				  		
				  		<s:if test="#request.atCscbPaySerial==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbPaySerial[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbState || #request.defines['cscbState']!=null">
				<s:if test="#request.defines['cscbState']>0">
					${lz:set("注释","****cscbState关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbState",lz:indexOf(fieldName,"cscbState")>-1)}				  		
				  		<s:if test="#request.atCscbState==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbState[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscbStatus || #request.defines['cscbStatus']!=null">
				<s:if test="#request.defines['cscbStatus']>0">
					${lz:set("注释","****cscbStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscbStatus",lz:indexOf(fieldName,"cscbStatus")>-1)}				  		
				  		<s:if test="#request.atCscbStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscbStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
<s:if test="#request.CTRL.canAdd==true">
	/**
	* 添加信用账单
	**/
	function AddBill(parent){		
		var url = "${basePath}${namespace}bill_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加信用账单'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择信用账单
	**/
	function SelectBill(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改信用账单
	**/
	function EditBill(id){
		var url = "${basePath}${namespace}bill_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改信用账单'}":lz:json(ctrl)}};
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
		EditBill(checker.val());
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
				var url = "${basePath}${namespace}bill_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新信用账单",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}bill_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新信用账单",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改信用账单
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}bill_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新信用账单",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["remark"]={};
	fieldNames["process"]={};
	fieldNames["remark"]["cscbRemark"]=true;
	fieldNames["process"]["cscbProcess"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改信用账单任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}bill_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新信用账单",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}bill_edit.do";
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
			var url = "${basePath}${namespace}bill_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除信用账单
	**/
	function DelBill(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除信用账单["+flag+"]吗？</span>",
				title:"删除信用账单",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}bill_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选信用账单吗？</span>",
				title:"批量删除信用账单",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}bill_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示信用账单详情
	**/
	function DetailsBill(id)
	{
		var url = "${basePath}${namespace}bill_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"信用账单详情",editable:false,visible:true}};
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
		DetailsBill(checker.val());
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