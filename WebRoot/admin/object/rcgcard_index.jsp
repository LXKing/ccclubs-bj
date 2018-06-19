<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/rcgcard.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csRechargeCard.csrcId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csrcId,csrcHost,csrcNumber,csrcType,csrcDenomination,csrcMethod,csrcValidity,csrcValidityUtil,csrcUtil,csrcMember,csrcUseTime,csrcAddTime,csrcStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csrcId,csrcHost,csrcNumber,csrcPassword,csrcType,csrcDenomination,csrcMethod,csrcValidity,csrcValidityUtil,csrcUtil,csrcMember,csrcUseTime,csrcAddTime,csrcStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csrcId,csrcHost,csrcNumber,csrcType,csrcDenomination,csrcMethod,csrcValidity,csrcValidityUtil,csrcUtil,csrcMember,csrcUseTime,csrcStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csrcId,csrcHost,csrcNumber,csrcPassword,csrcType,csrcDenomination,csrcMethod,csrcValidity,csrcValidityUtil,csrcUtil,csrcMember,csrcUseTime,csrcStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>充值卡管理</title>
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
<script>
<s:if test="#request.canExp[0]==true && #request.CTRL.canExp[0]==true">
$(function(){
	var button = $("<button onclick=\"storage('批量入库')\" type=\"button\" class=\"edit\">批量入库</button>");
	button.appendTo(".toolbar");
});
//批量入库
function storage(){
	<lz:set name="storageForm">
		<form id="storageForm" name="storageForm" onsubmit="return false;" class="form" enctype="multipart/form-data" method="post" action="#">	
			<table border="0" cellspacing="2" cellpadding="2">
			  <tr>
			    <td width="97" align="right">卡号前缀：</td>
			    <td width="229">
			    	<input type="text" class="input narrow" size="4" maxlength="5" name="prefix" id="prefix" value="CFX">
					<br/>尽量为大写字母比如：CFX
				</td>
			    <td width="97" align="right">充值类型：</td>
			    <td width="229">
					<select class="narrow" id="type" name="type">
						<option value="0">现金券</option>
						<option value="1">免费小时</option>
						<option value="2">红包</option>
				 	</select>
				 	<br/>请选择
				</td>
			  </tr>
			  <tr>
			    <td align="right">最小卡号：</td>
			    <td>
			    	<input type="text" class="input narrow" size="4" maxlength="32" name="min" id="min" value="">
			    	<br/>必须大于当前系统最大卡号[<font color='red'>{maxid}</font>]
				</td>
			    <td align="right">最大卡号：</td>
			    <td>
					<input type="text" class="input narrow" size="4" maxlength="32" name="max" id="max" value="">
					<br/>最大卡号必须大于最小卡号
				</td>
			  </tr>
			  <tr>
			    <td align="right">充值面额:</td>
			    <td>
					<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow" maxlength="8" name="denomination" id="denomination" value="100">
					<br/>请输入充值卡的充值面额
				</td>
			    <td align="right" valign="top">充值卡分类:</td>
			    <td>
					<select class="narrow" id="method" name="method">
				 		<option value="">请选择</option>
		 					${lz:set("items", get:propertys("充值卡分类","rcgcard_method"))}
							<s:iterator value="#request.items" id="item" status="i">
							<option value="${item.spId}" ${method==item.spId?"selected":""}>${item.spName}</option>
							</s:iterator>
							${lz:set("items",null)}
				 	</select>
				 	<br/>不存在分类请联系后台程序员
				</td>
			  </tr>
			  <tr>
			    <td align="right" valign="top">内容有效期:</td>
			    <td>
					<select class="narrow" id="validity" style="" name="validity">
				 		<option value="">请选择</option>
		 				<option value="1">1个月</option>
						<option value="2">2个月</option>
						<option value="3">3个月</option>
						<option value="6">半年</option>
						<option value="12">1年</option>
						<option value="0">自定义</option>
				 	</select>
				 	<br/>指的是充值的内容的有效期
				</td>
			    <td align="right" valign="top">兑换有效期至:</td>
			    <td>
					<input type="text" onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="input narrow" size="4" maxlength="32" name="util" id="util" value="">
					<br/>不输入表示长期有效
				</td>
			  </tr>
			  <tr>
			    <td align="right" valign="top">&nbsp;</td>
			    <td id="vutiltd" style="display:none;">
				 	<input type="text" onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="input narrow" size="4" maxlength="32" name="vutil" id="vutil" value="">
				 	<br/>指的是充值的内容的有效期时间
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			  </tr>
			</table>			
		</form>
	</lz:set>
	var storageDialog = top.$("${lz:js(storageForm)}".replace("{maxid}",$.api('com.ccclubs.action.admin.object.RcgcardAction','maxid')())).dialog({
		title:"批量入库",
		modal:true,
		onOk:function(){			
			var params = storageDialog.find("#storageForm").params();
			if(""==$.trim(params.min)){
				alert("请输入最小卡号");
				return false;
			}
			if(""==$.trim(params.max)){
				alert("请输入最大卡号");
				return false;
			}
			if(""==$.trim(params.min)){
				alert("请输入最小卡号");
				return false;
			}
			if(""==$.trim(params.min)){
				alert("请输入最小卡号");
				return false;
			}
			if(""==$.trim(params.min)){
				alert("请输入最小卡号");
				return false;
			}
			if(""==$.trim(params.method)){
				alert("请选择充值卡分类");
				return false;
			}
			if(""==$.trim(params.validity)){
				alert("请选择充值内容有效期");
				return false;
			}
			if("0"==$.trim(params.validity) && ""==$.trim(params.vutil)){
				alert("请输入充值内容有效期至时间");
				return false;
			}
			var result = $.getObject("rcgcard_storage.do",params);
			alert(result.message);
			if(result && result.success==true)
				return true;
			return false;
		}
	});
	storageDialog.find("#validity").change(function(){
		storageDialog.find("#vutiltd")[$(this).val()=="0"?"show":"hide"]();
	});	
}
</s:if>
if(!window.handles)window.handles={};
window.handles["出库"]=function(operate,id){
	var url = "${basePath}${namespace}rcgcard_edit.do";
	var params = {entrypoint:"${entrypoint}",id:id,ctrl:{editable:false,title:operate,fields:{csrcStatus:{editable:true}}}};
	href(url,params);
}
</script>
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
	<span class="caption">${empty title?"充值卡管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsRechargeCard" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="rcgcard.query.jsp"%>

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
			<button type="button" class="add" onclick="AddRcgcard();">
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
			<dl ref="com.ccclubs.model.CsRechargeCard" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsRechargeCard" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.csrcStatus || #request.defines['csrcStatus']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('status')">状态</a>
					</s:if>
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table
				</dd>
			</dl>
			</s:if>
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="rcgcard_table" ref="CsRechargeCard" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcId || #request.defines['csrcId']!=null">
			 <td <s:if test="#request.defines['csrcId']>0">colspan="${defines["csrcId"]}" ${all?"width":"iwidth"}="${defines["csrcId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csrcId"   title="编号">
			 	<a class="${desc=="csrc_id" ? "desc" : ""}${asc=="csrc_id" ? "asc" : ""}" href="?${desc=="csrc_id" ? "asc=csrc_id" : ""}${(asc=="csrc_id" || desc!="csrc_id" )? "desc=csrc_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csrcId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcHost || #request.defines['csrcHost']!=null">
			 <td <s:if test="#request.defines['csrcHost']>0">colspan="${defines["csrcHost"]}" ${all?"width":"iwidth"}="${defines["csrcHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csrcHost"   title="城市">
			 	<a class="${desc=="csrc_host" ? "desc" : ""}${asc=="csrc_host" ? "asc" : ""}" href="?${desc=="csrc_host" ? "asc=csrc_host" : ""}${(asc=="csrc_host" || desc!="csrc_host" )? "desc=csrc_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csrcHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcNumber || #request.defines['csrcNumber']!=null">
			 <td <s:if test="#request.defines['csrcNumber']>0">colspan="${defines["csrcNumber"]}" ${all?"width":"iwidth"}="${defines["csrcNumber"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="csrcNumber" flagKey  title="卡号">
			 	<a class="${desc=="csrc_number" ? "desc" : ""}${asc=="csrc_number" ? "asc" : ""}" href="?${desc=="csrc_number" ? "asc=csrc_number" : ""}${(asc=="csrc_number" || desc!="csrc_number" )? "desc=csrc_number" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	卡号
			 	</a>
			 	${lz:set("checkeds[]","csrcNumber")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcPassword || #request.defines['csrcPassword']!=null">
			 <td <s:if test="#request.defines['csrcPassword']>0">colspan="${defines["csrcPassword"]}" ${all?"width":"iwidth"}="${defines["csrcPassword"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="4" ref="csrcPassword"   title="密码">
			 	<a class="${desc=="csrc_password" ? "desc" : ""}${asc=="csrc_password" ? "asc" : ""}" href="?${desc=="csrc_password" ? "asc=csrc_password" : ""}${(asc=="csrc_password" || desc!="csrc_password" )? "desc=csrc_password" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	密码
			 	</a>
			 	${lz:set("checkeds[]","csrcPassword")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcType || #request.defines['csrcType']!=null">
			 <td <s:if test="#request.defines['csrcType']>0">colspan="${defines["csrcType"]}" ${all?"width":"iwidth"}="${defines["csrcType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csrcType"   title="类型">
			 	<a class="${desc=="csrc_type" ? "desc" : ""}${asc=="csrc_type" ? "asc" : ""}" href="?${desc=="csrc_type" ? "asc=csrc_type" : ""}${(asc=="csrc_type" || desc!="csrc_type" )? "desc=csrc_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	类型
			 	</a>
			 	${lz:set("checkeds[]","csrcType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcDenomination || #request.defines['csrcDenomination']!=null">
			 <td <s:if test="#request.defines['csrcDenomination']>0">colspan="${defines["csrcDenomination"]}" ${all?"width":"iwidth"}="${defines["csrcDenomination"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csrcDenomination"   title="面额">
			 	<a class="${desc=="csrc_denomination" ? "desc" : ""}${asc=="csrc_denomination" ? "asc" : ""}" href="?${desc=="csrc_denomination" ? "asc=csrc_denomination" : ""}${(asc=="csrc_denomination" || desc!="csrc_denomination" )? "desc=csrc_denomination" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	面额
			 	</a>
			 	${lz:set("checkeds[]","csrcDenomination")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcMethod || #request.defines['csrcMethod']!=null">
			 <td <s:if test="#request.defines['csrcMethod']>0">colspan="${defines["csrcMethod"]}" ${all?"width":"iwidth"}="${defines["csrcMethod"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csrcMethod"   title="分类">
			 	<a class="${desc=="csrc_method" ? "desc" : ""}${asc=="csrc_method" ? "asc" : ""}" href="?${desc=="csrc_method" ? "asc=csrc_method" : ""}${(asc=="csrc_method" || desc!="csrc_method" )? "desc=csrc_method" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	分类
			 	</a>
			 	${lz:set("checkeds[]","csrcMethod")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcValidity || #request.defines['csrcValidity']!=null">
			 <td <s:if test="#request.defines['csrcValidity']>0">colspan="${defines["csrcValidity"]}" ${all?"width":"iwidth"}="${defines["csrcValidity"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csrcValidity"   title="内容有效期">
			 	<a class="${desc=="csrc_validity" ? "desc" : ""}${asc=="csrc_validity" ? "asc" : ""}" href="?${desc=="csrc_validity" ? "asc=csrc_validity" : ""}${(asc=="csrc_validity" || desc!="csrc_validity" )? "desc=csrc_validity" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	内容有效期
			 	</a>
			 	${lz:set("checkeds[]","csrcValidity")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcValidityUtil || #request.defines['csrcValidityUtil']!=null">
			 <td <s:if test="#request.defines['csrcValidityUtil']>0">colspan="${defines["csrcValidityUtil"]}" ${all?"width":"iwidth"}="${defines["csrcValidityUtil"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="9" ref="csrcValidityUtil"   title="内容有效至">
			 	<a class="${desc=="csrc_validity_util" ? "desc" : ""}${asc=="csrc_validity_util" ? "asc" : ""}" href="?${desc=="csrc_validity_util" ? "asc=csrc_validity_util" : ""}${(asc=="csrc_validity_util" || desc!="csrc_validity_util" )? "desc=csrc_validity_util" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	内容有效至
			 	</a>
			 	${lz:set("checkeds[]","csrcValidityUtil")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcUtil || #request.defines['csrcUtil']!=null">
			 <td <s:if test="#request.defines['csrcUtil']>0">colspan="${defines["csrcUtil"]}" ${all?"width":"iwidth"}="${defines["csrcUtil"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="10" ref="csrcUtil"   title="充值有效至">
			 	<a class="${desc=="csrc_util" ? "desc" : ""}${asc=="csrc_util" ? "asc" : ""}" href="?${desc=="csrc_util" ? "asc=csrc_util" : ""}${(asc=="csrc_util" || desc!="csrc_util" )? "desc=csrc_util" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	充值有效至
			 	</a>
			 	${lz:set("checkeds[]","csrcUtil")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcMember || #request.defines['csrcMember']!=null">
			 <td <s:if test="#request.defines['csrcMember']>0">colspan="${defines["csrcMember"]}" ${all?"width":"iwidth"}="${defines["csrcMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="csrcMember"   title="使用会员">
			 	<a class="${desc=="csrc_member" ? "desc" : ""}${asc=="csrc_member" ? "asc" : ""}" href="?${desc=="csrc_member" ? "asc=csrc_member" : ""}${(asc=="csrc_member" || desc!="csrc_member" )? "desc=csrc_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用会员
			 	</a>
			 	${lz:set("checkeds[]","csrcMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcUseTime || #request.defines['csrcUseTime']!=null">
			 <td <s:if test="#request.defines['csrcUseTime']>0">colspan="${defines["csrcUseTime"]}" ${all?"width":"iwidth"}="${defines["csrcUseTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="12" ref="csrcUseTime"   title="使用时间">
			 	<a class="${desc=="csrc_use_time" ? "desc" : ""}${asc=="csrc_use_time" ? "asc" : ""}" href="?${desc=="csrc_use_time" ? "asc=csrc_use_time" : ""}${(asc=="csrc_use_time" || desc!="csrc_use_time" )? "desc=csrc_use_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用时间
			 	</a>
			 	${lz:set("checkeds[]","csrcUseTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcAddTime || #request.defines['csrcAddTime']!=null">
			 <td <s:if test="#request.defines['csrcAddTime']>0">colspan="${defines["csrcAddTime"]}" ${all?"width":"iwidth"}="${defines["csrcAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="13" ref="csrcAddTime"   title="添加时间">
			 	<a class="${desc=="csrc_add_time" ? "desc" : ""}${asc=="csrc_add_time" ? "asc" : ""}" href="?${desc=="csrc_add_time" ? "asc=csrc_add_time" : ""}${(asc=="csrc_add_time" || desc!="csrc_add_time" )? "desc=csrc_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csrcAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csrcStatus || #request.defines['csrcStatus']!=null">
			 <td <s:if test="#request.defines['csrcStatus']>0">colspan="${defines["csrcStatus"]}" ${all?"width":"iwidth"}="${defines["csrcStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="csrcStatus"   title="状态">
			 	<a class="${desc=="csrc_status" ? "desc" : ""}${asc=="csrc_status" ? "asc" : ""}" href="?${desc=="csrc_status" ? "asc=csrc_status" : ""}${(asc=="csrc_status" || desc!="csrc_status" )? "desc=csrc_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csrcStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="15" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="16" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddRcgcard()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.csrcId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csrcId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcId || #request.defines['csrcId']!=null">
				<s:if test="#request.defines['csrcId']>0">
					${lz:set("注释","****csrcId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcId",lz:indexOf(fieldName,"csrcId")>-1)}
				  		<s:if test="#request.atCsrcId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcId字段的字串格式化输出****")}
					<td ref="csrcId" class="td ">
						 
						 	${lz:or(item$csrcId[i.count-1],lz:left(item.csrcId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcHost || #request.defines['csrcHost']!=null">
				<s:if test="#request.defines['csrcHost']>0">
					${lz:set("注释","****csrcHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcHost",lz:indexOf(fieldName,"csrcHost")>-1)}
				  		<s:if test="#request.atCsrcHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcHost字段的字串格式化输出****")}
					<td ref="csrcHost" class="td " relate="${item.csrcHost}">
						 
						 	<a <s:if test="#item.csrcHost!=null && #item.csrcHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csrcHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csrcHost[i.count-1],lz:left(item.csrcHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcNumber || #request.defines['csrcNumber']!=null">
				<s:if test="#request.defines['csrcNumber']>0">
					${lz:set("注释","****csrcNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcNumber))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcNumber",lz:indexOf(fieldName,"csrcNumber")>-1)}
				  		<s:if test="#request.atCsrcNumber==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcNumber字段的字串格式化输出****")}
					<td ref="csrcNumber" class="td  node">
						 
						 	${lz:or(item$csrcNumber[i.count-1],lz:left(item.csrcNumber$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcPassword || #request.defines['csrcPassword']!=null">
				<s:if test="#request.defines['csrcPassword']>0">
					${lz:set("注释","****csrcPassword关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcPassword))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcPassword",lz:indexOf(fieldName,"csrcPassword")>-1)}
				  		<s:if test="#request.atCsrcPassword==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcPassword字段的字串格式化输出****")}
					<td ref="csrcPassword" class="td ">
						 
						 	${lz:or(item$csrcPassword[i.count-1],lz:left(item.csrcPassword$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcType || #request.defines['csrcType']!=null">
				<s:if test="#request.defines['csrcType']>0">
					${lz:set("注释","****csrcType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcType",lz:indexOf(fieldName,"csrcType")>-1)}
				  		<s:if test="#request.atCsrcType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcType字段的字串格式化输出****")}
					<td ref="csrcType" class="td ">
						 
						 	${lz:or(item$csrcType[i.count-1],lz:left(item.csrcType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcDenomination || #request.defines['csrcDenomination']!=null">
				<s:if test="#request.defines['csrcDenomination']>0">
					${lz:set("注释","****csrcDenomination关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcDenomination))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcDenomination",lz:indexOf(fieldName,"csrcDenomination")>-1)}
				  		<s:if test="#request.atCsrcDenomination==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcDenomination字段的字串格式化输出****")}
					<td ref="csrcDenomination" class="td ">
						 
						 	${lz:or(item$csrcDenomination[i.count-1],lz:left(item.csrcDenomination$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcMethod || #request.defines['csrcMethod']!=null">
				<s:if test="#request.defines['csrcMethod']>0">
					${lz:set("注释","****csrcMethod关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcMethod))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcMethod",lz:indexOf(fieldName,"csrcMethod")>-1)}
				  		<s:if test="#request.atCsrcMethod==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcMethod字段的字串格式化输出****")}
					<td ref="csrcMethod" class="td " relate="${item.csrcMethod}">
						 
						 	<a <s:if test="#item.csrcMethod!=null && #item.csrcMethod!=''"> onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${item.csrcMethod}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csrcMethod[i.count-1],lz:left(item.csrcMethod$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcValidity || #request.defines['csrcValidity']!=null">
				<s:if test="#request.defines['csrcValidity']>0">
					${lz:set("注释","****csrcValidity关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcValidity))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcValidity",lz:indexOf(fieldName,"csrcValidity")>-1)}
				  		<s:if test="#request.atCsrcValidity==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcValidity字段的字串格式化输出****")}
					<td ref="csrcValidity" class="td ">
						 
						 	${lz:or(item$csrcValidity[i.count-1],lz:left(item.csrcValidity$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcValidityUtil || #request.defines['csrcValidityUtil']!=null">
				<s:if test="#request.defines['csrcValidityUtil']>0">
					${lz:set("注释","****csrcValidityUtil关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcValidityUtil))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcValidityUtil",lz:indexOf(fieldName,"csrcValidityUtil")>-1)}
				  		<s:if test="#request.atCsrcValidityUtil==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcValidityUtil字段的字串格式化输出****")}
					<td ref="csrcValidityUtil" class="td ">
						 
						 	${lz:or(item$csrcValidityUtil[i.count-1],lz:left(item.csrcValidityUtil$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcUtil || #request.defines['csrcUtil']!=null">
				<s:if test="#request.defines['csrcUtil']>0">
					${lz:set("注释","****csrcUtil关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcUtil))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcUtil",lz:indexOf(fieldName,"csrcUtil")>-1)}
				  		<s:if test="#request.atCsrcUtil==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcUtil字段的字串格式化输出****")}
					<td ref="csrcUtil" class="td ">
						 
						 	${lz:or(item$csrcUtil[i.count-1],lz:left(item.csrcUtil$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcMember || #request.defines['csrcMember']!=null">
				<s:if test="#request.defines['csrcMember']>0">
					${lz:set("注释","****csrcMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcMember",lz:indexOf(fieldName,"csrcMember")>-1)}
				  		<s:if test="#request.atCsrcMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcMember字段的字串格式化输出****")}
					<td ref="csrcMember" class="td " relate="${item.csrcMember}">
						 
						 	<a <s:if test="#item.csrcMember!=null && #item.csrcMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csrcMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csrcMember[i.count-1],lz:left(item.csrcMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcUseTime || #request.defines['csrcUseTime']!=null">
				<s:if test="#request.defines['csrcUseTime']>0">
					${lz:set("注释","****csrcUseTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcUseTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcUseTime",lz:indexOf(fieldName,"csrcUseTime")>-1)}
				  		<s:if test="#request.atCsrcUseTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcUseTime字段的字串格式化输出****")}
					<td ref="csrcUseTime" class="td ">
						 
						 	${lz:or(item$csrcUseTime[i.count-1],lz:left(item.csrcUseTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcAddTime || #request.defines['csrcAddTime']!=null">
				<s:if test="#request.defines['csrcAddTime']>0">
					${lz:set("注释","****csrcAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcAddTime",lz:indexOf(fieldName,"csrcAddTime")>-1)}
				  		<s:if test="#request.atCsrcAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcAddTime字段的字串格式化输出****")}
					<td ref="csrcAddTime" class="td ">
						 
						 	${lz:or(item$csrcAddTime[i.count-1],lz:left(item.csrcAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csrcStatus || #request.defines['csrcStatus']!=null">
				<s:if test="#request.defines['csrcStatus']>0">
					${lz:set("注释","****csrcStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csrcStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsrcStatus",lz:indexOf(fieldName,"csrcStatus")>-1)}
				  		<s:if test="#request.atCsrcStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csrcStatus字段的字串格式化输出****")}
					<td ref="csrcStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csrcId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csrcStatus[i.count-1],lz:left(item.csrcStatus$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('出库','${item.csrcId}')">出库</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.csrcId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectRcgcard('${item.csrcId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditRcgcard('${item.csrcId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelRcgcard('${item.csrcId}','${item.csrcNumber}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsRcgcard('${item.csrcId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csrcId || #request.defines['csrcId']!=null">
				<s:if test="#request.defines['csrcId']>0">
					${lz:set("注释","****csrcId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcId",lz:indexOf(fieldName,"csrcId")>-1)}				  		
				  		<s:if test="#request.atCsrcId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcHost || #request.defines['csrcHost']!=null">
				<s:if test="#request.defines['csrcHost']>0">
					${lz:set("注释","****csrcHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcHost",lz:indexOf(fieldName,"csrcHost")>-1)}				  		
				  		<s:if test="#request.atCsrcHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcNumber || #request.defines['csrcNumber']!=null">
				<s:if test="#request.defines['csrcNumber']>0">
					${lz:set("注释","****csrcNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcNumber",lz:indexOf(fieldName,"csrcNumber")>-1)}				  		
				  		<s:if test="#request.atCsrcNumber==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcNumber[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcPassword || #request.defines['csrcPassword']!=null">
				<s:if test="#request.defines['csrcPassword']>0">
					${lz:set("注释","****csrcPassword关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcPassword",lz:indexOf(fieldName,"csrcPassword")>-1)}				  		
				  		<s:if test="#request.atCsrcPassword==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcPassword[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcType || #request.defines['csrcType']!=null">
				<s:if test="#request.defines['csrcType']>0">
					${lz:set("注释","****csrcType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcType",lz:indexOf(fieldName,"csrcType")>-1)}				  		
				  		<s:if test="#request.atCsrcType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcDenomination || #request.defines['csrcDenomination']!=null">
				<s:if test="#request.defines['csrcDenomination']>0">
					${lz:set("注释","****csrcDenomination关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcDenomination",lz:indexOf(fieldName,"csrcDenomination")>-1)}				  		
				  		<s:if test="#request.atCsrcDenomination==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcDenomination[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcMethod || #request.defines['csrcMethod']!=null">
				<s:if test="#request.defines['csrcMethod']>0">
					${lz:set("注释","****csrcMethod关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcMethod",lz:indexOf(fieldName,"csrcMethod")>-1)}				  		
				  		<s:if test="#request.atCsrcMethod==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcMethod[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcValidity || #request.defines['csrcValidity']!=null">
				<s:if test="#request.defines['csrcValidity']>0">
					${lz:set("注释","****csrcValidity关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcValidity",lz:indexOf(fieldName,"csrcValidity")>-1)}				  		
				  		<s:if test="#request.atCsrcValidity==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcValidity[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcValidityUtil || #request.defines['csrcValidityUtil']!=null">
				<s:if test="#request.defines['csrcValidityUtil']>0">
					${lz:set("注释","****csrcValidityUtil关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcValidityUtil",lz:indexOf(fieldName,"csrcValidityUtil")>-1)}				  		
				  		<s:if test="#request.atCsrcValidityUtil==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcValidityUtil[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcUtil || #request.defines['csrcUtil']!=null">
				<s:if test="#request.defines['csrcUtil']>0">
					${lz:set("注释","****csrcUtil关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcUtil",lz:indexOf(fieldName,"csrcUtil")>-1)}				  		
				  		<s:if test="#request.atCsrcUtil==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcUtil[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcMember || #request.defines['csrcMember']!=null">
				<s:if test="#request.defines['csrcMember']>0">
					${lz:set("注释","****csrcMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcMember",lz:indexOf(fieldName,"csrcMember")>-1)}				  		
				  		<s:if test="#request.atCsrcMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcUseTime || #request.defines['csrcUseTime']!=null">
				<s:if test="#request.defines['csrcUseTime']>0">
					${lz:set("注释","****csrcUseTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcUseTime",lz:indexOf(fieldName,"csrcUseTime")>-1)}				  		
				  		<s:if test="#request.atCsrcUseTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcUseTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcAddTime || #request.defines['csrcAddTime']!=null">
				<s:if test="#request.defines['csrcAddTime']>0">
					${lz:set("注释","****csrcAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcAddTime",lz:indexOf(fieldName,"csrcAddTime")>-1)}				  		
				  		<s:if test="#request.atCsrcAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csrcStatus || #request.defines['csrcStatus']!=null">
				<s:if test="#request.defines['csrcStatus']>0">
					${lz:set("注释","****csrcStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsrcStatus",lz:indexOf(fieldName,"csrcStatus")>-1)}				  		
				  		<s:if test="#request.atCsrcStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csrcStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加充值卡
	**/
	function AddRcgcard(parent){		
		var url = "${basePath}${namespace}rcgcard_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加充值卡'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择充值卡
	**/
	function SelectRcgcard(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改充值卡
	**/
	function EditRcgcard(id){
		var url = "${basePath}${namespace}rcgcard_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改充值卡'}":lz:json(ctrl)}};
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
		EditRcgcard(checker.val());
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
				var url = "${basePath}${namespace}rcgcard_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新充值卡",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}rcgcard_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新充值卡",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改充值卡
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}rcgcard_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新充值卡",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["csrcStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改充值卡任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}rcgcard_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新充值卡",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}rcgcard_edit.do";
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
			var url = "${basePath}${namespace}rcgcard_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除充值卡
	**/
	function DelRcgcard(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除充值卡["+flag+"]吗？</span>",
				title:"删除充值卡",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}rcgcard_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选充值卡吗？</span>",
				title:"批量删除充值卡",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}rcgcard_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示充值卡详情
	**/
	function DetailsRcgcard(id)
	{
		var url = "${basePath}${namespace}rcgcard_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"充值卡详情",editable:false,visible:true}};
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
		DetailsRcgcard(checker.val());
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