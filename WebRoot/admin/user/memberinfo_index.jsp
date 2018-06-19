<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/memberinfo.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csMemberInfo.csmiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csmiId,csmiHost,csmiName,csmiMemberId,csmiSex,csmiUpdateTime,csmiAddTime,csmiStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csmiId,csmiHost,csmiName,csmiMemberId,csmiCertifyType,csmiCertifyNum,csmiCertifyImage,csmiDriverNum,csmiDriverImage,csmiSex,csmiUpdateTime,csmiAddTime,csmiStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csmiId,csmiHost,csmiName,csmiMemberId,csmiSex,csmiUpdateTime,csmiAddTime,csmiStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csmiId,csmiHost,csmiName,csmiMemberId,csmiCertifyType,csmiSex,csmiUpdateTime,csmiAddTime,csmiStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>会员信息管理</title>
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
	<span class="caption">${empty title?"会员信息管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsMemberInfo" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="memberinfo.query.jsp"%>

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
			<button type="button" class="add" onclick="AddMemberinfo();">
				添加
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
			<dl ref="com.ccclubs.model.CsMemberInfo" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsMemberInfo" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.csmiStatus || #request.defines['csmiStatus']!=null">
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
		<table id="memberinfo_table" ref="CsMemberInfo" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiId || #request.defines['csmiId']!=null">
			 <td <s:if test="#request.defines['csmiId']>0">colspan="${defines["csmiId"]}" ${all?"width":"iwidth"}="${defines["csmiId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csmiId"   title="编号">
			 	<a class="${desc=="csmi_id" ? "desc" : ""}${asc=="csmi_id" ? "asc" : ""}" href="?${desc=="csmi_id" ? "asc=csmi_id" : ""}${(asc=="csmi_id" || desc!="csmi_id" )? "desc=csmi_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csmiId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiHost || #request.defines['csmiHost']!=null">
			 <td <s:if test="#request.defines['csmiHost']>0">colspan="${defines["csmiHost"]}" ${all?"width":"iwidth"}="${defines["csmiHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csmiHost"   title="城市">
			 	<a class="${desc=="csmi_host" ? "desc" : ""}${asc=="csmi_host" ? "asc" : ""}" href="?${desc=="csmi_host" ? "asc=csmi_host" : ""}${(asc=="csmi_host" || desc!="csmi_host" )? "desc=csmi_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csmiHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiName || #request.defines['csmiName']!=null">
			 <td <s:if test="#request.defines['csmiName']>0">colspan="${defines["csmiName"]}" ${all?"width":"iwidth"}="${defines["csmiName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="csmiName" flagKey  title="真实姓名">
			 	<a class="${desc=="csmi_name" ? "desc" : ""}${asc=="csmi_name" ? "asc" : ""}" href="?${desc=="csmi_name" ? "asc=csmi_name" : ""}${(asc=="csmi_name" || desc!="csmi_name" )? "desc=csmi_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	真实姓名
			 	</a>
			 	${lz:set("checkeds[]","csmiName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiMemberId || #request.defines['csmiMemberId']!=null">
			 <td <s:if test="#request.defines['csmiMemberId']>0">colspan="${defines["csmiMemberId"]}" ${all?"width":"iwidth"}="${defines["csmiMemberId"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csmiMemberId"   title="会员帐号">
			 	<a class="${desc=="csmi_member_id" ? "desc" : ""}${asc=="csmi_member_id" ? "asc" : ""}" href="?${desc=="csmi_member_id" ? "asc=csmi_member_id" : ""}${(asc=="csmi_member_id" || desc!="csmi_member_id" )? "desc=csmi_member_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	会员帐号
			 	</a>
			 	${lz:set("checkeds[]","csmiMemberId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiCertifyType || #request.defines['csmiCertifyType']!=null">
			 <td <s:if test="#request.defines['csmiCertifyType']>0">colspan="${defines["csmiCertifyType"]}" ${all?"width":"iwidth"}="${defines["csmiCertifyType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csmiCertifyType"   title="证件类型">
			 	<a class="${desc=="csmi_certify_type" ? "desc" : ""}${asc=="csmi_certify_type" ? "asc" : ""}" href="?${desc=="csmi_certify_type" ? "asc=csmi_certify_type" : ""}${(asc=="csmi_certify_type" || desc!="csmi_certify_type" )? "desc=csmi_certify_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	证件类型
			 	</a>
			 	${lz:set("checkeds[]","csmiCertifyType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiCertifyNum || #request.defines['csmiCertifyNum']!=null">
			 <td <s:if test="#request.defines['csmiCertifyNum']>0">colspan="${defines["csmiCertifyNum"]}" ${all?"width":"iwidth"}="${defines["csmiCertifyNum"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="6" ref="csmiCertifyNum"   title="证件号码">
			 	<a class="${desc=="csmi_certify_num" ? "desc" : ""}${asc=="csmi_certify_num" ? "asc" : ""}" href="?${desc=="csmi_certify_num" ? "asc=csmi_certify_num" : ""}${(asc=="csmi_certify_num" || desc!="csmi_certify_num" )? "desc=csmi_certify_num" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	证件号码
			 	</a>
			 	${lz:set("checkeds[]","csmiCertifyNum")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiCertifyImage || #request.defines['csmiCertifyImage']!=null">
			 <td <s:if test="#request.defines['csmiCertifyImage']>0">colspan="${defines["csmiCertifyImage"]}" ${all?"width":"iwidth"}="${defines["csmiCertifyImage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="csmiCertifyImage"   title="证件图片">
			 	<a class="${desc=="csmi_certify_image" ? "desc" : ""}${asc=="csmi_certify_image" ? "asc" : ""}" href="?${desc=="csmi_certify_image" ? "asc=csmi_certify_image" : ""}${(asc=="csmi_certify_image" || desc!="csmi_certify_image" )? "desc=csmi_certify_image" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	证件图片
			 	</a>
			 	${lz:set("checkeds[]","csmiCertifyImage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiDriverNum || #request.defines['csmiDriverNum']!=null">
			 <td <s:if test="#request.defines['csmiDriverNum']>0">colspan="${defines["csmiDriverNum"]}" ${all?"width":"iwidth"}="${defines["csmiDriverNum"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="8" ref="csmiDriverNum"   title="驾证号码">
			 	<a class="${desc=="csmi_driver_num" ? "desc" : ""}${asc=="csmi_driver_num" ? "asc" : ""}" href="?${desc=="csmi_driver_num" ? "asc=csmi_driver_num" : ""}${(asc=="csmi_driver_num" || desc!="csmi_driver_num" )? "desc=csmi_driver_num" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	驾证号码
			 	</a>
			 	${lz:set("checkeds[]","csmiDriverNum")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiDriverImage || #request.defines['csmiDriverImage']!=null">
			 <td <s:if test="#request.defines['csmiDriverImage']>0">colspan="${defines["csmiDriverImage"]}" ${all?"width":"iwidth"}="${defines["csmiDriverImage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="csmiDriverImage"   title="驾证图片">
			 	<a class="${desc=="csmi_driver_image" ? "desc" : ""}${asc=="csmi_driver_image" ? "asc" : ""}" href="?${desc=="csmi_driver_image" ? "asc=csmi_driver_image" : ""}${(asc=="csmi_driver_image" || desc!="csmi_driver_image" )? "desc=csmi_driver_image" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	驾证图片
			 	</a>
			 	${lz:set("checkeds[]","csmiDriverImage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiSex || #request.defines['csmiSex']!=null">
			 <td <s:if test="#request.defines['csmiSex']>0">colspan="${defines["csmiSex"]}" ${all?"width":"iwidth"}="${defines["csmiSex"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csmiSex"   title="性别">
			 	<a class="${desc=="csmi_sex" ? "desc" : ""}${asc=="csmi_sex" ? "asc" : ""}" href="?${desc=="csmi_sex" ? "asc=csmi_sex" : ""}${(asc=="csmi_sex" || desc!="csmi_sex" )? "desc=csmi_sex" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	性别
			 	</a>
			 	${lz:set("checkeds[]","csmiSex")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiBirthday || #request.defines['csmiBirthday']!=null">
			 <td <s:if test="#request.defines['csmiBirthday']>0">colspan="${defines["csmiBirthday"]}" ${all?"width":"iwidth"}="${defines["csmiBirthday"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="11" ref="csmiBirthday"   title="生日">
			 	<a class="${desc=="csmi_birthday" ? "desc" : ""}${asc=="csmi_birthday" ? "asc" : ""}" href="?${desc=="csmi_birthday" ? "asc=csmi_birthday" : ""}${(asc=="csmi_birthday" || desc!="csmi_birthday" )? "desc=csmi_birthday" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	生日
			 	</a>
			 	${lz:set("checkeds[]","csmiBirthday")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiQq || #request.defines['csmiQq']!=null">
			 <td <s:if test="#request.defines['csmiQq']>0">colspan="${defines["csmiQq"]}" ${all?"width":"iwidth"}="${defines["csmiQq"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="csmiQq"   title="QQ">
			 	<a class="${desc=="csmi_qq" ? "desc" : ""}${asc=="csmi_qq" ? "asc" : ""}" href="?${desc=="csmi_qq" ? "asc=csmi_qq" : ""}${(asc=="csmi_qq" || desc!="csmi_qq" )? "desc=csmi_qq" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	QQ
			 	</a>
			 	${lz:set("checkeds[]","csmiQq")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiNation || #request.defines['csmiNation']!=null">
			 <td <s:if test="#request.defines['csmiNation']>0">colspan="${defines["csmiNation"]}" ${all?"width":"iwidth"}="${defines["csmiNation"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="csmiNation"   title="民族">
			 	<a class="${desc=="csmi_nation" ? "desc" : ""}${asc=="csmi_nation" ? "asc" : ""}" href="?${desc=="csmi_nation" ? "asc=csmi_nation" : ""}${(asc=="csmi_nation" || desc!="csmi_nation" )? "desc=csmi_nation" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	民族
			 	</a>
			 	${lz:set("checkeds[]","csmiNation")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiAddress || #request.defines['csmiAddress']!=null">
			 <td <s:if test="#request.defines['csmiAddress']>0">colspan="${defines["csmiAddress"]}" ${all?"width":"iwidth"}="${defines["csmiAddress"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="csmiAddress"   title="邮寄地址">
			 	<a class="${desc=="csmi_address" ? "desc" : ""}${asc=="csmi_address" ? "asc" : ""}" href="?${desc=="csmi_address" ? "asc=csmi_address" : ""}${(asc=="csmi_address" || desc!="csmi_address" )? "desc=csmi_address" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	邮寄地址
			 	</a>
			 	${lz:set("checkeds[]","csmiAddress")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiCompany || #request.defines['csmiCompany']!=null">
			 <td <s:if test="#request.defines['csmiCompany']>0">colspan="${defines["csmiCompany"]}" ${all?"width":"iwidth"}="${defines["csmiCompany"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="15" ref="csmiCompany"   title="就职公司">
			 	<a class="${desc=="csmi_company" ? "desc" : ""}${asc=="csmi_company" ? "asc" : ""}" href="?${desc=="csmi_company" ? "asc=csmi_company" : ""}${(asc=="csmi_company" || desc!="csmi_company" )? "desc=csmi_company" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	就职公司
			 	</a>
			 	${lz:set("checkeds[]","csmiCompany")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiPlace || #request.defines['csmiPlace']!=null">
			 <td <s:if test="#request.defines['csmiPlace']>0">colspan="${defines["csmiPlace"]}" ${all?"width":"iwidth"}="${defines["csmiPlace"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="16" ref="csmiPlace"   title="所在地">
			 	<a class="${desc=="csmi_place" ? "desc" : ""}${asc=="csmi_place" ? "asc" : ""}" href="?${desc=="csmi_place" ? "asc=csmi_place" : ""}${(asc=="csmi_place" || desc!="csmi_place" )? "desc=csmi_place" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所在地
			 	</a>
			 	${lz:set("checkeds[]","csmiPlace")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiPerson || #request.defines['csmiPerson']!=null">
			 <td <s:if test="#request.defines['csmiPerson']>0">colspan="${defines["csmiPerson"]}" ${all?"width":"iwidth"}="${defines["csmiPerson"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="17" ref="csmiPerson"   title="紧急联系人">
			 	<a class="${desc=="csmi_person" ? "desc" : ""}${asc=="csmi_person" ? "asc" : ""}" href="?${desc=="csmi_person" ? "asc=csmi_person" : ""}${(asc=="csmi_person" || desc!="csmi_person" )? "desc=csmi_person" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	紧急联系人
			 	</a>
			 	${lz:set("checkeds[]","csmiPerson")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiContact || #request.defines['csmiContact']!=null">
			 <td <s:if test="#request.defines['csmiContact']>0">colspan="${defines["csmiContact"]}" ${all?"width":"iwidth"}="${defines["csmiContact"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="18" ref="csmiContact"   title="紧急联系号码">
			 	<a class="${desc=="csmi_contact" ? "desc" : ""}${asc=="csmi_contact" ? "asc" : ""}" href="?${desc=="csmi_contact" ? "asc=csmi_contact" : ""}${(asc=="csmi_contact" || desc!="csmi_contact" )? "desc=csmi_contact" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	紧急联系号码
			 	</a>
			 	${lz:set("checkeds[]","csmiContact")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiRelation || #request.defines['csmiRelation']!=null">
			 <td <s:if test="#request.defines['csmiRelation']>0">colspan="${defines["csmiRelation"]}" ${all?"width":"iwidth"}="${defines["csmiRelation"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="19" ref="csmiRelation"   title="联系人关系">
			 	<a class="${desc=="csmi_relation" ? "desc" : ""}${asc=="csmi_relation" ? "asc" : ""}" href="?${desc=="csmi_relation" ? "asc=csmi_relation" : ""}${(asc=="csmi_relation" || desc!="csmi_relation" )? "desc=csmi_relation" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系人关系
			 	</a>
			 	${lz:set("checkeds[]","csmiRelation")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiUpdateTime || #request.defines['csmiUpdateTime']!=null">
			 <td <s:if test="#request.defines['csmiUpdateTime']>0">colspan="${defines["csmiUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csmiUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="20" ref="csmiUpdateTime"   title="修改时间">
			 	<a class="${desc=="csmi_update_time" ? "desc" : ""}${asc=="csmi_update_time" ? "asc" : ""}" href="?${desc=="csmi_update_time" ? "asc=csmi_update_time" : ""}${(asc=="csmi_update_time" || desc!="csmi_update_time" )? "desc=csmi_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csmiUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiAddTime || #request.defines['csmiAddTime']!=null">
			 <td <s:if test="#request.defines['csmiAddTime']>0">colspan="${defines["csmiAddTime"]}" ${all?"width":"iwidth"}="${defines["csmiAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="21" ref="csmiAddTime"   title="添加时间">
			 	<a class="${desc=="csmi_add_time" ? "desc" : ""}${asc=="csmi_add_time" ? "asc" : ""}" href="?${desc=="csmi_add_time" ? "asc=csmi_add_time" : ""}${(asc=="csmi_add_time" || desc!="csmi_add_time" )? "desc=csmi_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csmiAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csmiStatus || #request.defines['csmiStatus']!=null">
			 <td <s:if test="#request.defines['csmiStatus']>0">colspan="${defines["csmiStatus"]}" ${all?"width":"iwidth"}="${defines["csmiStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="22" ref="csmiStatus"   title="状态">
			 	<a class="${desc=="csmi_status" ? "desc" : ""}${asc=="csmi_status" ? "asc" : ""}" href="?${desc=="csmi_status" ? "asc=csmi_status" : ""}${(asc=="csmi_status" || desc!="csmi_status" )? "desc=csmi_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csmiStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="23" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="24" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddMemberinfo()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.csmiId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csmiId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiId || #request.defines['csmiId']!=null">
				<s:if test="#request.defines['csmiId']>0">
					${lz:set("注释","****csmiId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiId",lz:indexOf(fieldName,"csmiId")>-1)}
				  		<s:if test="#request.atCsmiId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiId字段的字串格式化输出****")}
					<td ref="csmiId" class="td ">
						 
						 	${lz:or(item$csmiId[i.count-1],lz:left(item.csmiId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiHost || #request.defines['csmiHost']!=null">
				<s:if test="#request.defines['csmiHost']>0">
					${lz:set("注释","****csmiHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiHost",lz:indexOf(fieldName,"csmiHost")>-1)}
				  		<s:if test="#request.atCsmiHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiHost字段的字串格式化输出****")}
					<td ref="csmiHost" class="td " relate="${item.csmiHost}">
						 
						 	<a <s:if test="#item.csmiHost!=null && #item.csmiHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csmiHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmiHost[i.count-1],lz:left(item.csmiHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiName || #request.defines['csmiName']!=null">
				<s:if test="#request.defines['csmiName']>0">
					${lz:set("注释","****csmiName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiName",lz:indexOf(fieldName,"csmiName")>-1)}
				  		<s:if test="#request.atCsmiName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiName字段的字串格式化输出****")}
					<td ref="csmiName" class="td  node">
						 
						 	${lz:or(item$csmiName[i.count-1],lz:left(item.csmiName$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiMemberId || #request.defines['csmiMemberId']!=null">
				<s:if test="#request.defines['csmiMemberId']>0">
					${lz:set("注释","****csmiMemberId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiMemberId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiMemberId",lz:indexOf(fieldName,"csmiMemberId")>-1)}
				  		<s:if test="#request.atCsmiMemberId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiMemberId字段的字串格式化输出****")}
					<td ref="csmiMemberId" class="td " relate="${item.csmiMemberId}">
						 
						 	<a <s:if test="#item.csmiMemberId!=null && #item.csmiMemberId!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csmiMemberId}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csmiMemberId[i.count-1],lz:left(item.csmiMemberId$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiCertifyType || #request.defines['csmiCertifyType']!=null">
				<s:if test="#request.defines['csmiCertifyType']>0">
					${lz:set("注释","****csmiCertifyType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiCertifyType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiCertifyType",lz:indexOf(fieldName,"csmiCertifyType")>-1)}
				  		<s:if test="#request.atCsmiCertifyType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiCertifyType字段的字串格式化输出****")}
					<td ref="csmiCertifyType" class="td ">
						 
						 	${lz:or(item$csmiCertifyType[i.count-1],lz:left(item.csmiCertifyType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiCertifyNum || #request.defines['csmiCertifyNum']!=null">
				<s:if test="#request.defines['csmiCertifyNum']>0">
					${lz:set("注释","****csmiCertifyNum关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiCertifyNum))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiCertifyNum",lz:indexOf(fieldName,"csmiCertifyNum")>-1)}
				  		<s:if test="#request.atCsmiCertifyNum==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiCertifyNum字段的字串格式化输出****")}
					<td ref="csmiCertifyNum" class="td ">
						 
						 	${lz:or(item$csmiCertifyNum[i.count-1],lz:left(item.csmiCertifyNum$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiCertifyImage || #request.defines['csmiCertifyImage']!=null">
				<s:if test="#request.defines['csmiCertifyImage']>0">
					${lz:set("注释","****csmiCertifyImage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiCertifyImage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiCertifyImage",lz:indexOf(fieldName,"csmiCertifyImage")>-1)}
				  		<s:if test="#request.atCsmiCertifyImage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiCertifyImage字段的字串格式化输出****")}
					<td ref="csmiCertifyImage" class="td ">
						 
						 	${lz:or(item$csmiCertifyImage[i.count-1],lz:left(item.csmiCertifyImage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiDriverNum || #request.defines['csmiDriverNum']!=null">
				<s:if test="#request.defines['csmiDriverNum']>0">
					${lz:set("注释","****csmiDriverNum关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiDriverNum))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiDriverNum",lz:indexOf(fieldName,"csmiDriverNum")>-1)}
				  		<s:if test="#request.atCsmiDriverNum==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiDriverNum字段的字串格式化输出****")}
					<td ref="csmiDriverNum" class="td ">
						 
						 	${lz:or(item$csmiDriverNum[i.count-1],lz:left(item.csmiDriverNum$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiDriverImage || #request.defines['csmiDriverImage']!=null">
				<s:if test="#request.defines['csmiDriverImage']>0">
					${lz:set("注释","****csmiDriverImage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiDriverImage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiDriverImage",lz:indexOf(fieldName,"csmiDriverImage")>-1)}
				  		<s:if test="#request.atCsmiDriverImage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiDriverImage字段的字串格式化输出****")}
					<td ref="csmiDriverImage" class="td ">
						 
						 	${lz:or(item$csmiDriverImage[i.count-1],lz:left(item.csmiDriverImage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiSex || #request.defines['csmiSex']!=null">
				<s:if test="#request.defines['csmiSex']>0">
					${lz:set("注释","****csmiSex关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiSex))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiSex",lz:indexOf(fieldName,"csmiSex")>-1)}
				  		<s:if test="#request.atCsmiSex==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiSex字段的字串格式化输出****")}
					<td ref="csmiSex" class="td ">
						 
						 	${lz:or(item$csmiSex[i.count-1],lz:left(item.csmiSex$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiBirthday || #request.defines['csmiBirthday']!=null">
				<s:if test="#request.defines['csmiBirthday']>0">
					${lz:set("注释","****csmiBirthday关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiBirthday))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiBirthday",lz:indexOf(fieldName,"csmiBirthday")>-1)}
				  		<s:if test="#request.atCsmiBirthday==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiBirthday字段的字串格式化输出****")}
					<td ref="csmiBirthday" class="td ">
						 
						 	${lz:or(item$csmiBirthday[i.count-1],lz:left(item.csmiBirthday$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiQq || #request.defines['csmiQq']!=null">
				<s:if test="#request.defines['csmiQq']>0">
					${lz:set("注释","****csmiQq关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiQq))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiQq",lz:indexOf(fieldName,"csmiQq")>-1)}
				  		<s:if test="#request.atCsmiQq==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiQq字段的字串格式化输出****")}
					<td ref="csmiQq" class="td ">
						 
						 	${lz:or(item$csmiQq[i.count-1],lz:left(item.csmiQq$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiNation || #request.defines['csmiNation']!=null">
				<s:if test="#request.defines['csmiNation']>0">
					${lz:set("注释","****csmiNation关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiNation))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiNation",lz:indexOf(fieldName,"csmiNation")>-1)}
				  		<s:if test="#request.atCsmiNation==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiNation字段的字串格式化输出****")}
					<td ref="csmiNation" class="td ">
						 
						 	${lz:or(item$csmiNation[i.count-1],lz:left(item.csmiNation$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiAddress || #request.defines['csmiAddress']!=null">
				<s:if test="#request.defines['csmiAddress']>0">
					${lz:set("注释","****csmiAddress关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiAddress))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiAddress",lz:indexOf(fieldName,"csmiAddress")>-1)}
				  		<s:if test="#request.atCsmiAddress==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiAddress字段的字串格式化输出****")}
					<td ref="csmiAddress" class="td ">
						 
						 	${lz:or(item$csmiAddress[i.count-1],lz:left(item.csmiAddress$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiCompany || #request.defines['csmiCompany']!=null">
				<s:if test="#request.defines['csmiCompany']>0">
					${lz:set("注释","****csmiCompany关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiCompany))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiCompany",lz:indexOf(fieldName,"csmiCompany")>-1)}
				  		<s:if test="#request.atCsmiCompany==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiCompany字段的字串格式化输出****")}
					<td ref="csmiCompany" class="td ">
						 
						 	${lz:or(item$csmiCompany[i.count-1],lz:left(item.csmiCompany$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiPlace || #request.defines['csmiPlace']!=null">
				<s:if test="#request.defines['csmiPlace']>0">
					${lz:set("注释","****csmiPlace关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiPlace))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiPlace",lz:indexOf(fieldName,"csmiPlace")>-1)}
				  		<s:if test="#request.atCsmiPlace==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiPlace字段的字串格式化输出****")}
					<td ref="csmiPlace" class="td ">
						 
						 	${lz:or(item$csmiPlace[i.count-1],lz:left(item.csmiPlace$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiPerson || #request.defines['csmiPerson']!=null">
				<s:if test="#request.defines['csmiPerson']>0">
					${lz:set("注释","****csmiPerson关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiPerson))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiPerson",lz:indexOf(fieldName,"csmiPerson")>-1)}
				  		<s:if test="#request.atCsmiPerson==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiPerson字段的字串格式化输出****")}
					<td ref="csmiPerson" class="td ">
						 
						 	${lz:or(item$csmiPerson[i.count-1],lz:left(item.csmiPerson$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiContact || #request.defines['csmiContact']!=null">
				<s:if test="#request.defines['csmiContact']>0">
					${lz:set("注释","****csmiContact关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiContact))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiContact",lz:indexOf(fieldName,"csmiContact")>-1)}
				  		<s:if test="#request.atCsmiContact==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiContact字段的字串格式化输出****")}
					<td ref="csmiContact" class="td ">
						 
						 	${lz:or(item$csmiContact[i.count-1],lz:left(item.csmiContact$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiRelation || #request.defines['csmiRelation']!=null">
				<s:if test="#request.defines['csmiRelation']>0">
					${lz:set("注释","****csmiRelation关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiRelation))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiRelation",lz:indexOf(fieldName,"csmiRelation")>-1)}
				  		<s:if test="#request.atCsmiRelation==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiRelation字段的字串格式化输出****")}
					<td ref="csmiRelation" class="td ">
						 
						 	${lz:or(item$csmiRelation[i.count-1],lz:left(item.csmiRelation$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiUpdateTime || #request.defines['csmiUpdateTime']!=null">
				<s:if test="#request.defines['csmiUpdateTime']>0">
					${lz:set("注释","****csmiUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiUpdateTime",lz:indexOf(fieldName,"csmiUpdateTime")>-1)}
				  		<s:if test="#request.atCsmiUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiUpdateTime字段的字串格式化输出****")}
					<td ref="csmiUpdateTime" class="td ">
						 
						 	${lz:or(item$csmiUpdateTime[i.count-1],lz:left(item.csmiUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiAddTime || #request.defines['csmiAddTime']!=null">
				<s:if test="#request.defines['csmiAddTime']>0">
					${lz:set("注释","****csmiAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiAddTime",lz:indexOf(fieldName,"csmiAddTime")>-1)}
				  		<s:if test="#request.atCsmiAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiAddTime字段的字串格式化输出****")}
					<td ref="csmiAddTime" class="td ">
						 
						 	${lz:or(item$csmiAddTime[i.count-1],lz:left(item.csmiAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csmiStatus || #request.defines['csmiStatus']!=null">
				<s:if test="#request.defines['csmiStatus']>0">
					${lz:set("注释","****csmiStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csmiStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsmiStatus",lz:indexOf(fieldName,"csmiStatus")>-1)}
				  		<s:if test="#request.atCsmiStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csmiStatus字段的字串格式化输出****")}
					<td ref="csmiStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csmiId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csmiStatus[i.count-1],lz:left(item.csmiStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.csmiId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectMemberinfo('${item.csmiId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditMemberinfo('${item.csmiId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelMemberinfo('${item.csmiId}','${item.csmiName}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsMemberinfo('${item.csmiId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csmiId || #request.defines['csmiId']!=null">
				<s:if test="#request.defines['csmiId']>0">
					${lz:set("注释","****csmiId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiId",lz:indexOf(fieldName,"csmiId")>-1)}				  		
				  		<s:if test="#request.atCsmiId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiHost || #request.defines['csmiHost']!=null">
				<s:if test="#request.defines['csmiHost']>0">
					${lz:set("注释","****csmiHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiHost",lz:indexOf(fieldName,"csmiHost")>-1)}				  		
				  		<s:if test="#request.atCsmiHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiName || #request.defines['csmiName']!=null">
				<s:if test="#request.defines['csmiName']>0">
					${lz:set("注释","****csmiName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiName",lz:indexOf(fieldName,"csmiName")>-1)}				  		
				  		<s:if test="#request.atCsmiName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiMemberId || #request.defines['csmiMemberId']!=null">
				<s:if test="#request.defines['csmiMemberId']>0">
					${lz:set("注释","****csmiMemberId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiMemberId",lz:indexOf(fieldName,"csmiMemberId")>-1)}				  		
				  		<s:if test="#request.atCsmiMemberId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiMemberId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiCertifyType || #request.defines['csmiCertifyType']!=null">
				<s:if test="#request.defines['csmiCertifyType']>0">
					${lz:set("注释","****csmiCertifyType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiCertifyType",lz:indexOf(fieldName,"csmiCertifyType")>-1)}				  		
				  		<s:if test="#request.atCsmiCertifyType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiCertifyType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiCertifyNum || #request.defines['csmiCertifyNum']!=null">
				<s:if test="#request.defines['csmiCertifyNum']>0">
					${lz:set("注释","****csmiCertifyNum关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiCertifyNum",lz:indexOf(fieldName,"csmiCertifyNum")>-1)}				  		
				  		<s:if test="#request.atCsmiCertifyNum==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiCertifyNum[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiCertifyImage || #request.defines['csmiCertifyImage']!=null">
				<s:if test="#request.defines['csmiCertifyImage']>0">
					${lz:set("注释","****csmiCertifyImage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiCertifyImage",lz:indexOf(fieldName,"csmiCertifyImage")>-1)}				  		
				  		<s:if test="#request.atCsmiCertifyImage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiCertifyImage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiDriverNum || #request.defines['csmiDriverNum']!=null">
				<s:if test="#request.defines['csmiDriverNum']>0">
					${lz:set("注释","****csmiDriverNum关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiDriverNum",lz:indexOf(fieldName,"csmiDriverNum")>-1)}				  		
				  		<s:if test="#request.atCsmiDriverNum==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiDriverNum[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiDriverImage || #request.defines['csmiDriverImage']!=null">
				<s:if test="#request.defines['csmiDriverImage']>0">
					${lz:set("注释","****csmiDriverImage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiDriverImage",lz:indexOf(fieldName,"csmiDriverImage")>-1)}				  		
				  		<s:if test="#request.atCsmiDriverImage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiDriverImage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiSex || #request.defines['csmiSex']!=null">
				<s:if test="#request.defines['csmiSex']>0">
					${lz:set("注释","****csmiSex关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiSex",lz:indexOf(fieldName,"csmiSex")>-1)}				  		
				  		<s:if test="#request.atCsmiSex==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiSex[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiBirthday || #request.defines['csmiBirthday']!=null">
				<s:if test="#request.defines['csmiBirthday']>0">
					${lz:set("注释","****csmiBirthday关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiBirthday",lz:indexOf(fieldName,"csmiBirthday")>-1)}				  		
				  		<s:if test="#request.atCsmiBirthday==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiBirthday[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiQq || #request.defines['csmiQq']!=null">
				<s:if test="#request.defines['csmiQq']>0">
					${lz:set("注释","****csmiQq关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiQq",lz:indexOf(fieldName,"csmiQq")>-1)}				  		
				  		<s:if test="#request.atCsmiQq==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiQq[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiNation || #request.defines['csmiNation']!=null">
				<s:if test="#request.defines['csmiNation']>0">
					${lz:set("注释","****csmiNation关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiNation",lz:indexOf(fieldName,"csmiNation")>-1)}				  		
				  		<s:if test="#request.atCsmiNation==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiNation[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiAddress || #request.defines['csmiAddress']!=null">
				<s:if test="#request.defines['csmiAddress']>0">
					${lz:set("注释","****csmiAddress关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiAddress",lz:indexOf(fieldName,"csmiAddress")>-1)}				  		
				  		<s:if test="#request.atCsmiAddress==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiAddress[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiCompany || #request.defines['csmiCompany']!=null">
				<s:if test="#request.defines['csmiCompany']>0">
					${lz:set("注释","****csmiCompany关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiCompany",lz:indexOf(fieldName,"csmiCompany")>-1)}				  		
				  		<s:if test="#request.atCsmiCompany==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiCompany[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiPlace || #request.defines['csmiPlace']!=null">
				<s:if test="#request.defines['csmiPlace']>0">
					${lz:set("注释","****csmiPlace关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiPlace",lz:indexOf(fieldName,"csmiPlace")>-1)}				  		
				  		<s:if test="#request.atCsmiPlace==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiPlace[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiPerson || #request.defines['csmiPerson']!=null">
				<s:if test="#request.defines['csmiPerson']>0">
					${lz:set("注释","****csmiPerson关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiPerson",lz:indexOf(fieldName,"csmiPerson")>-1)}				  		
				  		<s:if test="#request.atCsmiPerson==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiPerson[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiContact || #request.defines['csmiContact']!=null">
				<s:if test="#request.defines['csmiContact']>0">
					${lz:set("注释","****csmiContact关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiContact",lz:indexOf(fieldName,"csmiContact")>-1)}				  		
				  		<s:if test="#request.atCsmiContact==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiContact[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiRelation || #request.defines['csmiRelation']!=null">
				<s:if test="#request.defines['csmiRelation']>0">
					${lz:set("注释","****csmiRelation关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiRelation",lz:indexOf(fieldName,"csmiRelation")>-1)}				  		
				  		<s:if test="#request.atCsmiRelation==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiRelation[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiUpdateTime || #request.defines['csmiUpdateTime']!=null">
				<s:if test="#request.defines['csmiUpdateTime']>0">
					${lz:set("注释","****csmiUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiUpdateTime",lz:indexOf(fieldName,"csmiUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsmiUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiAddTime || #request.defines['csmiAddTime']!=null">
				<s:if test="#request.defines['csmiAddTime']>0">
					${lz:set("注释","****csmiAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiAddTime",lz:indexOf(fieldName,"csmiAddTime")>-1)}				  		
				  		<s:if test="#request.atCsmiAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csmiStatus || #request.defines['csmiStatus']!=null">
				<s:if test="#request.defines['csmiStatus']>0">
					${lz:set("注释","****csmiStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsmiStatus",lz:indexOf(fieldName,"csmiStatus")>-1)}				  		
				  		<s:if test="#request.atCsmiStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csmiStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加会员信息
	**/
	function AddMemberinfo(parent){		
		var url = "${basePath}${namespace}memberinfo_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加会员信息'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择会员信息
	**/
	function SelectMemberinfo(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改会员信息
	**/
	function EditMemberinfo(id){
		var url = "${basePath}${namespace}memberinfo_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改会员信息'}":lz:json(ctrl)}};
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
		EditMemberinfo(checker.val());
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
				var url = "${basePath}${namespace}memberinfo_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新会员信息",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}memberinfo_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新会员信息",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改会员信息
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}memberinfo_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新会员信息",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["csmiStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改会员信息任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}memberinfo_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新会员信息",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}memberinfo_edit.do";
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
			var url = "${basePath}${namespace}memberinfo_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除会员信息
	**/
	function DelMemberinfo(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除会员信息["+flag+"]吗？</span>",
				title:"删除会员信息",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}memberinfo_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选会员信息吗？</span>",
				title:"批量删除会员信息",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}memberinfo_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示会员信息详情
	**/
	function DetailsMemberinfo(id)
	{
		var url = "${basePath}${namespace}memberinfo_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"会员信息详情",editable:false,visible:true}};
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
		DetailsMemberinfo(checker.val());
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