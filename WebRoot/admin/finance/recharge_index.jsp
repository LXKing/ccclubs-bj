<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/recharge.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csArtifRecord.csarId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csarId,csarHost,csarTitle,csarMember,csarMoneyType,csarRecordSubject,csarOrder,csarRelate,csarReceived,csarVoucher,csarAdder,csarEditor,csarConfirmer,csarUpdateTime,csarAddTime,csarConfirmTime,csarRemark,csarStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csarId,csarHost,csarTitle,csarMember,csarCount,csarMoneyType,csarRecordSubject,csarOrder,csarRelate,csarReceived,csarVoucher,csarAdder,csarEditor,csarConfirmer,csarUpdateTime,csarAddTime,csarConfirmTime,csarRemark,csarStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csarId,csarHost,csarTitle,csarMember,csarMoneyType,csarRecordSubject,csarOrder,csarRelate,csarStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csarId,csarHost,csarTitle,csarMember,csarCount,csarMoneyType,csarRecordSubject,csarOrder,csarRelate,csarStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>充值扣款管理</title>
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
<s:if test="#request.CTRL.canExp[3]==true">
<script>
window.handles["充现金券"]=function(){
	top.$.open("${basePath}admin/finance/recharge_edit.do",{
		"csArtifRecord.csarMoneyType":1,
		"csArtifRecord.csarRecordSubject":CsRecordSubject.Get({csrsName:"现金券充值"}).csrsId
	});
}
window.handles["赠送现金券"]=function(){
	top.$.open("${basePath}admin/finance/recharge_edit.do",{
		"csArtifRecord.csarMoneyType":1,
		"csArtifRecord.csarRecordSubject":CsRecordSubject.Get({csrsName:"赠送现金券"}).csrsId
	});
}
window.handles["现金券转出"]=function(){
	top.$.open("${basePath}admin/finance/recharge_edit.do",{
		"csArtifRecord.csarMoneyType":1,
		"csArtifRecord.csarRecordSubject":CsRecordSubject.Get({csrsName:"现金券转出"}).csrsId
	});
}
window.handles["现金券转入"]=function(){
	top.$.open("${basePath}admin/finance/recharge_edit.do",{
		"csArtifRecord.csarMoneyType":1,
		"csArtifRecord.csarRecordSubject":CsRecordSubject.Get({csrsName:"现金券转入"}).csrsId
	});
}
window.handles["POS机充值"]=function(){
	top.$.open("${basePath}admin/finance/recharge_edit.do",{
		"csArtifRecord.csarMoneyType":0,
		"csArtifRecord.csarRecordSubject":CsRecordSubject.Get({csrsName:"POS机充值"}).csrsId
	});
}
window.handles["批量审核"]=function(){
	var checker=$(".check:checked");
	if(checker.length==0)
	{
		$.tips("您未选中任何记录，请至少选择一项.");
		return;
	}
	var ids = "";
	checker.each(function(i){
		if(ids!="")
			ids+=",";
		ids+=$(this).val();
	});
	var url = "${basePath}${namespace}recharge_batchedit.do";
	var params = {entrypoint:"${entrypoint}",ids:ids,method:"any",ctrl:{title:"审核",visible:false,editable:false,fields:{}}};
	params.ctrl["fields"]["csarStatus"]={visible:true,editable:true};
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
	<span class="caption">${empty title?"充值扣款管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsArtifRecord" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="recharge.query.jsp"%>

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
			<button type="button" class="add" onclick="AddRecharge();">
				添加
			</button>							
			</s:if>
		  </s:if>
		  <s:if test="#request.alias==null">	
			<s:if test="#request.CTRL.canExp[3]==true">
			<button onclick="HandleMenu('充现金券')" type="button" class="edit">
			
				充现金券
			</button>
			</s:if>
			<s:if test="#request.CTRL.canExp[4]==true">
			<button onclick="HandleMenu('赠送现金券')" type="button" class="edit">
			
				赠送现金券
			</button>
			</s:if>
			<s:if test="#request.CTRL.canExp[5]==true">
			<button onclick="HandleMenu('现金券转出')" type="button" class="edit">
			
				现金券转出
			</button>
			</s:if>
			<s:if test="#request.CTRL.canExp[6]==true">
			<button onclick="HandleMenu('现金券转入')" type="button" class="edit">
			
				现金券转入
			</button>
			</s:if>
			<s:if test="#request.CTRL.canExp[7]==true">
			<button onclick="HandleMenu('POS机充值')" type="button" class="edit">
			
				POS机充值
			</button>
			</s:if>
			<s:if test="#request.CTRL.canExp[8]==true">
			<button onclick="HandleMenu('批量审核')" type="button" class="edit">
			
				批量审核
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
			<dl ref="com.ccclubs.model.CsArtifRecord" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsArtifRecord" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table
				</dd>
			</dl>
			</s:if>
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="recharge_table" ref="CsArtifRecord" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
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
			 <td <s:if test="#request.defines['csarHost']>0">colspan="${defines["csarHost"]}" ${all?"width":"iwidth"}="${defines["csarHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csarHost" flagKey  title="城市">
			 	<a class="${desc=="csar_host" ? "desc" : ""}${asc=="csar_host" ? "asc" : ""}" href="?${desc=="csar_host" ? "asc=csar_host" : ""}${(asc=="csar_host" || desc!="csar_host" )? "desc=csar_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csarHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarTitle || #request.defines['csarTitle']!=null">
			 <td <s:if test="#request.defines['csarTitle']>0">colspan="${defines["csarTitle"]}" ${all?"width":"iwidth"}="${defines["csarTitle"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="csarTitle"   title="摘要">
			 	<a class="${desc=="csar_title" ? "desc" : ""}${asc=="csar_title" ? "asc" : ""}" href="?${desc=="csar_title" ? "asc=csar_title" : ""}${(asc=="csar_title" || desc!="csar_title" )? "desc=csar_title" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	摘要
			 	</a>
			 	${lz:set("checkeds[]","csarTitle")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarMember || #request.defines['csarMember']!=null">
			 <td <s:if test="#request.defines['csarMember']>0">colspan="${defines["csarMember"]}" ${all?"width":"iwidth"}="${defines["csarMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csarMember"   title="会员">
			 	<a class="${desc=="csar_member" ? "desc" : ""}${asc=="csar_member" ? "asc" : ""}" href="?${desc=="csar_member" ? "asc=csar_member" : ""}${(asc=="csar_member" || desc!="csar_member" )? "desc=csar_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	会员
			 	</a>
			 	${lz:set("checkeds[]","csarMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarCount || #request.defines['csarCount']!=null">
			 <td <s:if test="#request.defines['csarCount']>0">colspan="${defines["csarCount"]}" ${all?"width":"iwidth"}="${defines["csarCount"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csarCount"   title="金额">
			 	<a class="${desc=="csar_count" ? "desc" : ""}${asc=="csar_count" ? "asc" : ""}" href="?${desc=="csar_count" ? "asc=csar_count" : ""}${(asc=="csar_count" || desc!="csar_count" )? "desc=csar_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	金额
			 	</a>
			 	${lz:set("checkeds[]","csarCount")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarMoneyType || #request.defines['csarMoneyType']!=null">
			 <td <s:if test="#request.defines['csarMoneyType']>0">colspan="${defines["csarMoneyType"]}" ${all?"width":"iwidth"}="${defines["csarMoneyType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csarMoneyType"   title="金额类型">
			 	<a class="${desc=="csar_money_type" ? "desc" : ""}${asc=="csar_money_type" ? "asc" : ""}" href="?${desc=="csar_money_type" ? "asc=csar_money_type" : ""}${(asc=="csar_money_type" || desc!="csar_money_type" )? "desc=csar_money_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	金额类型
			 	</a>
			 	${lz:set("checkeds[]","csarMoneyType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarRecordSubject || #request.defines['csarRecordSubject']!=null">
			 <td <s:if test="#request.defines['csarRecordSubject']>0">colspan="${defines["csarRecordSubject"]}" ${all?"width":"iwidth"}="${defines["csarRecordSubject"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csarRecordSubject"   title="类型科目">
			 	<a class="${desc=="csar_record_subject" ? "desc" : ""}${asc=="csar_record_subject" ? "asc" : ""}" href="?${desc=="csar_record_subject" ? "asc=csar_record_subject" : ""}${(asc=="csar_record_subject" || desc!="csar_record_subject" )? "desc=csar_record_subject" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	类型科目
			 	</a>
			 	${lz:set("checkeds[]","csarRecordSubject")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarOrder || #request.defines['csarOrder']!=null">
			 <td <s:if test="#request.defines['csarOrder']>0">colspan="${defines["csarOrder"]}" ${all?"width":"iwidth"}="${defines["csarOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csarOrder"   title="关联订单">
			 	<a class="${desc=="csar_order" ? "desc" : ""}${asc=="csar_order" ? "asc" : ""}" href="?${desc=="csar_order" ? "asc=csar_order" : ""}${(asc=="csar_order" || desc!="csar_order" )? "desc=csar_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	关联订单
			 	</a>
			 	${lz:set("checkeds[]","csarOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarRelate || #request.defines['csarRelate']!=null">
			 <td <s:if test="#request.defines['csarRelate']>0">colspan="${defines["csarRelate"]}" ${all?"width":"iwidth"}="${defines["csarRelate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="csarRelate"   title="其它关联">
			 	<a class="${desc=="csar_relate" ? "desc" : ""}${asc=="csar_relate" ? "asc" : ""}" href="?${desc=="csar_relate" ? "asc=csar_relate" : ""}${(asc=="csar_relate" || desc!="csar_relate" )? "desc=csar_relate" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	其它关联
			 	</a>
			 	${lz:set("checkeds[]","csarRelate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarReceived || #request.defines['csarReceived']!=null">
			 <td <s:if test="#request.defines['csarReceived']>0">colspan="${defines["csarReceived"]}" ${all?"width":"iwidth"}="${defines["csarReceived"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csarReceived"   title="现金已收讫">
			 	<a class="${desc=="csar_received" ? "desc" : ""}${asc=="csar_received" ? "asc" : ""}" href="?${desc=="csar_received" ? "asc=csar_received" : ""}${(asc=="csar_received" || desc!="csar_received" )? "desc=csar_received" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	现金已收讫
			 	</a>
			 	${lz:set("checkeds[]","csarReceived")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarVoucher || #request.defines['csarVoucher']!=null">
			 <td <s:if test="#request.defines['csarVoucher']>0">colspan="${defines["csarVoucher"]}" ${all?"width":"iwidth"}="${defines["csarVoucher"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="csarVoucher"   title="现金入帐凭据">
			 	<a class="${desc=="csar_voucher" ? "desc" : ""}${asc=="csar_voucher" ? "asc" : ""}" href="?${desc=="csar_voucher" ? "asc=csar_voucher" : ""}${(asc=="csar_voucher" || desc!="csar_voucher" )? "desc=csar_voucher" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	现金入帐凭据
			 	</a>
			 	${lz:set("checkeds[]","csarVoucher")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarAdder || #request.defines['csarAdder']!=null">
			 <td <s:if test="#request.defines['csarAdder']>0">colspan="${defines["csarAdder"]}" ${all?"width":"iwidth"}="${defines["csarAdder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="csarAdder"   title="添加人">
			 	<a class="${desc=="csar_adder" ? "desc" : ""}${asc=="csar_adder" ? "asc" : ""}" href="?${desc=="csar_adder" ? "asc=csar_adder" : ""}${(asc=="csar_adder" || desc!="csar_adder" )? "desc=csar_adder" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加人
			 	</a>
			 	${lz:set("checkeds[]","csarAdder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarEditor || #request.defines['csarEditor']!=null">
			 <td <s:if test="#request.defines['csarEditor']>0">colspan="${defines["csarEditor"]}" ${all?"width":"iwidth"}="${defines["csarEditor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="csarEditor"   title="修改人">
			 	<a class="${desc=="csar_editor" ? "desc" : ""}${asc=="csar_editor" ? "asc" : ""}" href="?${desc=="csar_editor" ? "asc=csar_editor" : ""}${(asc=="csar_editor" || desc!="csar_editor" )? "desc=csar_editor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改人
			 	</a>
			 	${lz:set("checkeds[]","csarEditor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarConfirmer || #request.defines['csarConfirmer']!=null">
			 <td <s:if test="#request.defines['csarConfirmer']>0">colspan="${defines["csarConfirmer"]}" ${all?"width":"iwidth"}="${defines["csarConfirmer"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="csarConfirmer"   title="财务审核人">
			 	<a class="${desc=="csar_confirmer" ? "desc" : ""}${asc=="csar_confirmer" ? "asc" : ""}" href="?${desc=="csar_confirmer" ? "asc=csar_confirmer" : ""}${(asc=="csar_confirmer" || desc!="csar_confirmer" )? "desc=csar_confirmer" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	财务审核人
			 	</a>
			 	${lz:set("checkeds[]","csarConfirmer")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarUpdateTime || #request.defines['csarUpdateTime']!=null">
			 <td <s:if test="#request.defines['csarUpdateTime']>0">colspan="${defines["csarUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csarUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="15" ref="csarUpdateTime"   title="修改时间">
			 	<a class="${desc=="csar_update_time" ? "desc" : ""}${asc=="csar_update_time" ? "asc" : ""}" href="?${desc=="csar_update_time" ? "asc=csar_update_time" : ""}${(asc=="csar_update_time" || desc!="csar_update_time" )? "desc=csar_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csarUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarAddTime || #request.defines['csarAddTime']!=null">
			 <td <s:if test="#request.defines['csarAddTime']>0">colspan="${defines["csarAddTime"]}" ${all?"width":"iwidth"}="${defines["csarAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="16" ref="csarAddTime"   title="添加时间">
			 	<a class="${desc=="csar_add_time" ? "desc" : ""}${asc=="csar_add_time" ? "asc" : ""}" href="?${desc=="csar_add_time" ? "asc=csar_add_time" : ""}${(asc=="csar_add_time" || desc!="csar_add_time" )? "desc=csar_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csarAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarConfirmTime || #request.defines['csarConfirmTime']!=null">
			 <td <s:if test="#request.defines['csarConfirmTime']>0">colspan="${defines["csarConfirmTime"]}" ${all?"width":"iwidth"}="${defines["csarConfirmTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="17" ref="csarConfirmTime"   title="审核时间">
			 	<a class="${desc=="csar_confirm_time" ? "desc" : ""}${asc=="csar_confirm_time" ? "asc" : ""}" href="?${desc=="csar_confirm_time" ? "asc=csar_confirm_time" : ""}${(asc=="csar_confirm_time" || desc!="csar_confirm_time" )? "desc=csar_confirm_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	审核时间
			 	</a>
			 	${lz:set("checkeds[]","csarConfirmTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csarRemark || #request.defines['csarRemark']!=null">
			 <td <s:if test="#request.defines['csarRemark']>0">colspan="${defines["csarRemark"]}" ${all?"width":"iwidth"}="${defines["csarRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="18" ref="csarRemark"   title="备注">
			 	<a class="${desc=="csar_remark" ? "desc" : ""}${asc=="csar_remark" ? "asc" : ""}" href="?${desc=="csar_remark" ? "asc=csar_remark" : ""}${(asc=="csar_remark" || desc!="csar_remark" )? "desc=csar_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 	${lz:set("checkeds[]","csarRemark")}
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
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddRecharge()" style="text-decoration:underline">添加</a>
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
						 
						 	${lz:or(item$csarId[i.count-1],lz:left(item.csarId$,100))}
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
					<td ref="csarHost" class="td  node" relate="${item.csarHost}">
						 
						 	<a <s:if test="#item.csarHost!=null && #item.csarHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csarHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csarHost[i.count-1],lz:left(item.csarHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarTitle || #request.defines['csarTitle']!=null">
				<s:if test="#request.defines['csarTitle']>0">
					${lz:set("注释","****csarTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarTitle))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarTitle",lz:indexOf(fieldName,"csarTitle")>-1)}
				  		<s:if test="#request.atCsarTitle==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarTitle字段的字串格式化输出****")}
					<td ref="csarTitle" class="td ">
						 
						 	${lz:or(item$csarTitle[i.count-1],lz:left(item.csarTitle$,100))}
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
						 	${lz:or(item$csarMember[i.count-1],lz:left(item.csarMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarCount || #request.defines['csarCount']!=null">
				<s:if test="#request.defines['csarCount']>0">
					${lz:set("注释","****csarCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarCount))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarCount",lz:indexOf(fieldName,"csarCount")>-1)}
				  		<s:if test="#request.atCsarCount==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarCount字段的字串格式化输出****")}
					<td ref="csarCount" class="td ">
						 
						 	${lz:or(item$csarCount[i.count-1],lz:left(item.csarCount$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarMoneyType || #request.defines['csarMoneyType']!=null">
				<s:if test="#request.defines['csarMoneyType']>0">
					${lz:set("注释","****csarMoneyType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarMoneyType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarMoneyType",lz:indexOf(fieldName,"csarMoneyType")>-1)}
				  		<s:if test="#request.atCsarMoneyType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarMoneyType字段的字串格式化输出****")}
					<td ref="csarMoneyType" class="td ">
						 
						 	${lz:or(item$csarMoneyType[i.count-1],lz:left(item.csarMoneyType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarRecordSubject || #request.defines['csarRecordSubject']!=null">
				<s:if test="#request.defines['csarRecordSubject']>0">
					${lz:set("注释","****csarRecordSubject关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarRecordSubject))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarRecordSubject",lz:indexOf(fieldName,"csarRecordSubject")>-1)}
				  		<s:if test="#request.atCsarRecordSubject==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarRecordSubject字段的字串格式化输出****")}
					<td ref="csarRecordSubject" class="td " relate="${item.csarRecordSubject}">
						 
						 	<a <s:if test="#item.csarRecordSubject!=null && #item.csarRecordSubject!=''"> onclick="window.href('${basePath}${proname}/configurator/recordsubject_details.do?key=${item.csarRecordSubject}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csarRecordSubject[i.count-1],lz:left(item.csarRecordSubject$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarOrder || #request.defines['csarOrder']!=null">
				<s:if test="#request.defines['csarOrder']>0">
					${lz:set("注释","****csarOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarOrder",lz:indexOf(fieldName,"csarOrder")>-1)}
				  		<s:if test="#request.atCsarOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarOrder字段的字串格式化输出****")}
					<td ref="csarOrder" class="td " relate="${item.csarOrder}">
						 
						 	<a <s:if test="#item.csarOrder!=null && #item.csarOrder!=''"> onclick="window.href('${basePath}${proname}/service/order_details.do?key=${item.csarOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csarOrder[i.count-1],lz:left(item.csarOrder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarRelate || #request.defines['csarRelate']!=null">
				<s:if test="#request.defines['csarRelate']>0">
					${lz:set("注释","****csarRelate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarRelate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarRelate",lz:indexOf(fieldName,"csarRelate")>-1)}
				  		<s:if test="#request.atCsarRelate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarRelate字段的字串格式化输出****")}
					<td ref="csarRelate" class="td ">
						 
						 	${lz:or(item$csarRelate[i.count-1],lz:left(item.csarRelate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarReceived || #request.defines['csarReceived']!=null">
				<s:if test="#request.defines['csarReceived']>0">
					${lz:set("注释","****csarReceived关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarReceived))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarReceived",lz:indexOf(fieldName,"csarReceived")>-1)}
				  		<s:if test="#request.atCsarReceived==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarReceived字段的字串格式化输出****")}
					<td ref="csarReceived" class="td ">
						 
						 	${lz:or(item$csarReceived[i.count-1],lz:left(item.csarReceived$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarVoucher || #request.defines['csarVoucher']!=null">
				<s:if test="#request.defines['csarVoucher']>0">
					${lz:set("注释","****csarVoucher关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarVoucher))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarVoucher",lz:indexOf(fieldName,"csarVoucher")>-1)}
				  		<s:if test="#request.atCsarVoucher==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarVoucher字段的字串格式化输出****")}
					<td ref="csarVoucher" class="td ">
						 
						 	${lz:or(item$csarVoucher[i.count-1],lz:left(item.csarVoucher$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarAdder || #request.defines['csarAdder']!=null">
				<s:if test="#request.defines['csarAdder']>0">
					${lz:set("注释","****csarAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarAdder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarAdder",lz:indexOf(fieldName,"csarAdder")>-1)}
				  		<s:if test="#request.atCsarAdder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarAdder字段的字串格式化输出****")}
					<td ref="csarAdder" class="td " relate="${item.csarAdder}">
						 
						 	<a <s:if test="#item.csarAdder!=null && #item.csarAdder!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csarAdder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csarAdder[i.count-1],lz:left(item.csarAdder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarEditor || #request.defines['csarEditor']!=null">
				<s:if test="#request.defines['csarEditor']>0">
					${lz:set("注释","****csarEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarEditor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarEditor",lz:indexOf(fieldName,"csarEditor")>-1)}
				  		<s:if test="#request.atCsarEditor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarEditor字段的字串格式化输出****")}
					<td ref="csarEditor" class="td " relate="${item.csarEditor}">
						 
						 	<a <s:if test="#item.csarEditor!=null && #item.csarEditor!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csarEditor}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csarEditor[i.count-1],lz:left(item.csarEditor$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarConfirmer || #request.defines['csarConfirmer']!=null">
				<s:if test="#request.defines['csarConfirmer']>0">
					${lz:set("注释","****csarConfirmer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarConfirmer))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarConfirmer",lz:indexOf(fieldName,"csarConfirmer")>-1)}
				  		<s:if test="#request.atCsarConfirmer==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarConfirmer字段的字串格式化输出****")}
					<td ref="csarConfirmer" class="td ">
						 
						 	${lz:or(item$csarConfirmer[i.count-1],lz:left(item.csarConfirmer$,100))}
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
						 
						 	${lz:or(item$csarUpdateTime[i.count-1],lz:left(item.csarUpdateTime$,100))}
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
						 
						 	${lz:or(item$csarAddTime[i.count-1],lz:left(item.csarAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarConfirmTime || #request.defines['csarConfirmTime']!=null">
				<s:if test="#request.defines['csarConfirmTime']>0">
					${lz:set("注释","****csarConfirmTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarConfirmTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarConfirmTime",lz:indexOf(fieldName,"csarConfirmTime")>-1)}
				  		<s:if test="#request.atCsarConfirmTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarConfirmTime字段的字串格式化输出****")}
					<td ref="csarConfirmTime" class="td ">
						 
						 	${lz:or(item$csarConfirmTime[i.count-1],lz:left(item.csarConfirmTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csarRemark || #request.defines['csarRemark']!=null">
				<s:if test="#request.defines['csarRemark']>0">
					${lz:set("注释","****csarRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csarRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsarRemark",lz:indexOf(fieldName,"csarRemark")>-1)}
				  		<s:if test="#request.atCsarRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csarRemark字段的字串格式化输出****")}
					<td ref="csarRemark" class="td ">
						 
						 	${lz:or(item$csarRemark[i.count-1],lz:left(item.csarRemark$,100))}
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
						 
						 	${lz:or(item$csarStatus[i.count-1],lz:left(item.csarStatus$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('审核','${item.csarId}')">审核</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[1]==true">
					<a href="javascript:HandleSel('收讫','${item.csarId}')">收讫</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[2]==true">
					<a href="javascript:HandleSel('入账','${item.csarId}')">入账</a>
					</s:if>
				</s:if>
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
				<a href="javascript:SelectRecharge('${item.csarId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditRecharge('${item.csarId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelRecharge('${item.csarId}','${item.csarHost}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsRecharge('${item.csarId}')">详情</a></s:if>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarTitle || #request.defines['csarTitle']!=null">
				<s:if test="#request.defines['csarTitle']>0">
					${lz:set("注释","****csarTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarTitle",lz:indexOf(fieldName,"csarTitle")>-1)}				  		
				  		<s:if test="#request.atCsarTitle==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarTitle[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarCount || #request.defines['csarCount']!=null">
				<s:if test="#request.defines['csarCount']>0">
					${lz:set("注释","****csarCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarCount",lz:indexOf(fieldName,"csarCount")>-1)}				  		
				  		<s:if test="#request.atCsarCount==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarCount[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarMoneyType || #request.defines['csarMoneyType']!=null">
				<s:if test="#request.defines['csarMoneyType']>0">
					${lz:set("注释","****csarMoneyType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarMoneyType",lz:indexOf(fieldName,"csarMoneyType")>-1)}				  		
				  		<s:if test="#request.atCsarMoneyType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarMoneyType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarRecordSubject || #request.defines['csarRecordSubject']!=null">
				<s:if test="#request.defines['csarRecordSubject']>0">
					${lz:set("注释","****csarRecordSubject关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarRecordSubject",lz:indexOf(fieldName,"csarRecordSubject")>-1)}				  		
				  		<s:if test="#request.atCsarRecordSubject==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarRecordSubject[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarOrder || #request.defines['csarOrder']!=null">
				<s:if test="#request.defines['csarOrder']>0">
					${lz:set("注释","****csarOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarOrder",lz:indexOf(fieldName,"csarOrder")>-1)}				  		
				  		<s:if test="#request.atCsarOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarRelate || #request.defines['csarRelate']!=null">
				<s:if test="#request.defines['csarRelate']>0">
					${lz:set("注释","****csarRelate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarRelate",lz:indexOf(fieldName,"csarRelate")>-1)}				  		
				  		<s:if test="#request.atCsarRelate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarRelate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarReceived || #request.defines['csarReceived']!=null">
				<s:if test="#request.defines['csarReceived']>0">
					${lz:set("注释","****csarReceived关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarReceived",lz:indexOf(fieldName,"csarReceived")>-1)}				  		
				  		<s:if test="#request.atCsarReceived==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarReceived[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarVoucher || #request.defines['csarVoucher']!=null">
				<s:if test="#request.defines['csarVoucher']>0">
					${lz:set("注释","****csarVoucher关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarVoucher",lz:indexOf(fieldName,"csarVoucher")>-1)}				  		
				  		<s:if test="#request.atCsarVoucher==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarVoucher[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarAdder || #request.defines['csarAdder']!=null">
				<s:if test="#request.defines['csarAdder']>0">
					${lz:set("注释","****csarAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarAdder",lz:indexOf(fieldName,"csarAdder")>-1)}				  		
				  		<s:if test="#request.atCsarAdder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarAdder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarEditor || #request.defines['csarEditor']!=null">
				<s:if test="#request.defines['csarEditor']>0">
					${lz:set("注释","****csarEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarEditor",lz:indexOf(fieldName,"csarEditor")>-1)}				  		
				  		<s:if test="#request.atCsarEditor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarEditor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarConfirmer || #request.defines['csarConfirmer']!=null">
				<s:if test="#request.defines['csarConfirmer']>0">
					${lz:set("注释","****csarConfirmer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarConfirmer",lz:indexOf(fieldName,"csarConfirmer")>-1)}				  		
				  		<s:if test="#request.atCsarConfirmer==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarConfirmer[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarConfirmTime || #request.defines['csarConfirmTime']!=null">
				<s:if test="#request.defines['csarConfirmTime']>0">
					${lz:set("注释","****csarConfirmTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarConfirmTime",lz:indexOf(fieldName,"csarConfirmTime")>-1)}				  		
				  		<s:if test="#request.atCsarConfirmTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarConfirmTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csarRemark || #request.defines['csarRemark']!=null">
				<s:if test="#request.defines['csarRemark']>0">
					${lz:set("注释","****csarRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsarRemark",lz:indexOf(fieldName,"csarRemark")>-1)}				  		
				  		<s:if test="#request.atCsarRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csarRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
<s:if test="#request.CTRL.canAdd==true">
	/**
	* 添加充值扣款
	**/
	function AddRecharge(parent){		
		var url = "${basePath}${namespace}recharge_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加充值扣款'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择充值扣款
	**/
	function SelectRecharge(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改充值扣款
	**/
	function EditRecharge(id){
		var url = "${basePath}${namespace}recharge_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改充值扣款'}":lz:json(ctrl)}};
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
		EditRecharge(checker.val());
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
				var url = "${basePath}${namespace}recharge_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新充值扣款",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}recharge_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新充值扣款",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改充值扣款
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}recharge_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新充值扣款",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改充值扣款任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}recharge_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新充值扣款",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}recharge_edit.do";
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
			var url = "${basePath}${namespace}recharge_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除充值扣款
	**/
	function DelRecharge(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除充值扣款["+flag+"]吗？</span>",
				title:"删除充值扣款",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}recharge_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选充值扣款吗？</span>",
				title:"批量删除充值扣款",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}recharge_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示充值扣款详情
	**/
	function DetailsRecharge(id)
	{
		var url = "${basePath}${namespace}recharge_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"充值扣款详情",editable:false,visible:true}};
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
		DetailsRecharge(checker.val());
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