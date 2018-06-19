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
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csViolat.csvId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csvId,csvHost,csvOrder,csvMember,csvCar,csvHappenTime,csvNoticeTime,csvCutoffTime,csvType,csvBranch,csvPostType,csvAddress,csvAccepter,csvAdder,csvUpdateTime,csvAddTime,csvDealFlag,csvStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csvId,csvHost,csvOrder,csvMember,csvCar,csvMoney,csvScore,csvHappenTime,csvDecipt,csvNoticeTime,csvCutoffTime,csvType,csvPrice,csvBranch,csvPostType,csvAddress,csvRemark,csvAccepter,csvAdder,csvUpdateTime,csvAddTime,csvDealFlag,csvStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csvId,csvHost,csvOrder,csvMember,csvCar,csvHappenTime,csvNoticeTime,csvCutoffTime,csvType,csvBranch,csvDealFlag,csvStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csvId,csvHost,csvOrder,csvMember,csvCar,csvMoney,csvScore,csvHappenTime,csvDecipt,csvNoticeTime,csvCutoffTime,csvType,csvBranch,csvDealFlag,csvStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>车辆违章管理</title>
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


<!--LAZY3Q_JSP_BODY-->
<script>
$(function(){
	$(".table thead td[title='违章扣分']").attr("width",50);
	$(".table thead td[title='发生时间']").attr("width",120);
	$(".table thead td[title='违章描述']").attr("width",120);
	$(".table tbody td[ref='csvStatus']:contains('')").css("color","#FF00ff");
	$(".table tbody td[ref='csvStatus']:contains('未交款，未处理')").css("color","#ff0000");
	$(".table tbody td[ref='csvStatus']:contains('未交款，已处理')").css("color","#00ff00");
	$(".table tbody td[ref='csvStatus']:contains('已交款，已处理')").css("color","#0000ff");	
	$(".table tbody td[ref='csvNotice']:contains('未通知')").css("color","#FF0000");
});
</script>
<!--LAZY3Q_JSP_BODY-->


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
	<a href="violat_stats.do?${lz:querys("UTF-8","limitsid")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Statistics.png"/>
		数据统计
	</a>
		</s:if>
	</s:if>
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"车辆违章管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsViolat" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="violat.query.jsp"%>

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
			<button type="button" class="add" onclick="AddViolat();">
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
			<dl ref="com.ccclubs.model.CsViolat" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsViolat" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.csvDealFlag || #request.defines['csvDealFlag']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('deal')">受理标记</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csvStatus || #request.defines['csvStatus']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('status')">业务状态</a>
					</s:if>
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table
				</dd>
			</dl>
			</s:if>
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="violat_table" ref="CsViolat" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvId || #request.defines['csvId']!=null">
			 <td <s:if test="#request.defines['csvId']>0">colspan="${defines["csvId"]}" ${all?"width":"iwidth"}="${defines["csvId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csvId" flagKey  title="编号">
			 	<a class="${desc=="csv_id" ? "desc" : ""}${asc=="csv_id" ? "asc" : ""}" href="?${desc=="csv_id" ? "asc=csv_id" : ""}${(asc=="csv_id" || desc!="csv_id" )? "desc=csv_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csvId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvHost || #request.defines['csvHost']!=null">
			 <td <s:if test="#request.defines['csvHost']>0">colspan="${defines["csvHost"]}" ${all?"width":"iwidth"}="${defines["csvHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csvHost"   title="城市">
			 	<a class="${desc=="csv_host" ? "desc" : ""}${asc=="csv_host" ? "asc" : ""}" href="?${desc=="csv_host" ? "asc=csv_host" : ""}${(asc=="csv_host" || desc!="csv_host" )? "desc=csv_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csvHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvOrder || #request.defines['csvOrder']!=null">
			 <td <s:if test="#request.defines['csvOrder']>0">colspan="${defines["csvOrder"]}" ${all?"width":"iwidth"}="${defines["csvOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="csvOrder"   title="违章订单">
			 	<a class="${desc=="csv_order" ? "desc" : ""}${asc=="csv_order" ? "asc" : ""}" href="?${desc=="csv_order" ? "asc=csv_order" : ""}${(asc=="csv_order" || desc!="csv_order" )? "desc=csv_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	违章订单
			 	</a>
			 	${lz:set("checkeds[]","csvOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvMember || #request.defines['csvMember']!=null">
			 <td <s:if test="#request.defines['csvMember']>0">colspan="${defines["csvMember"]}" ${all?"width":"iwidth"}="${defines["csvMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csvMember"   title="违章会员">
			 	<a class="${desc=="csv_member" ? "desc" : ""}${asc=="csv_member" ? "asc" : ""}" href="?${desc=="csv_member" ? "asc=csv_member" : ""}${(asc=="csv_member" || desc!="csv_member" )? "desc=csv_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	违章会员
			 	</a>
			 	${lz:set("checkeds[]","csvMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvCar || #request.defines['csvCar']!=null">
			 <td <s:if test="#request.defines['csvCar']>0">colspan="${defines["csvCar"]}" ${all?"width":"iwidth"}="${defines["csvCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csvCar"   title="违章车辆">
			 	<a class="${desc=="csv_car" ? "desc" : ""}${asc=="csv_car" ? "asc" : ""}" href="?${desc=="csv_car" ? "asc=csv_car" : ""}${(asc=="csv_car" || desc!="csv_car" )? "desc=csv_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	违章车辆
			 	</a>
			 	${lz:set("checkeds[]","csvCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvMoney || #request.defines['csvMoney']!=null">
			 <td <s:if test="#request.defines['csvMoney']>0">colspan="${defines["csvMoney"]}" ${all?"width":"iwidth"}="${defines["csvMoney"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csvMoney"   title="违章罚款">
			 	<a class="${desc=="csv_money" ? "desc" : ""}${asc=="csv_money" ? "asc" : ""}" href="?${desc=="csv_money" ? "asc=csv_money" : ""}${(asc=="csv_money" || desc!="csv_money" )? "desc=csv_money" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	违章罚款
			 	</a>
			 	${lz:set("checkeds[]","csvMoney")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvScore || #request.defines['csvScore']!=null">
			 <td <s:if test="#request.defines['csvScore']>0">colspan="${defines["csvScore"]}" ${all?"width":"iwidth"}="${defines["csvScore"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csvScore"   title="违章扣分">
			 	<a class="${desc=="csv_score" ? "desc" : ""}${asc=="csv_score" ? "asc" : ""}" href="?${desc=="csv_score" ? "asc=csv_score" : ""}${(asc=="csv_score" || desc!="csv_score" )? "desc=csv_score" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	违章扣分
			 	</a>
			 	${lz:set("checkeds[]","csvScore")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvHappenTime || #request.defines['csvHappenTime']!=null">
			 <td <s:if test="#request.defines['csvHappenTime']>0">colspan="${defines["csvHappenTime"]}" ${all?"width":"iwidth"}="${defines["csvHappenTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="8" ref="csvHappenTime"   title="发生时间">
			 	<a class="${desc=="csv_happen_time" ? "desc" : ""}${asc=="csv_happen_time" ? "asc" : ""}" href="?${desc=="csv_happen_time" ? "asc=csv_happen_time" : ""}${(asc=="csv_happen_time" || desc!="csv_happen_time" )? "desc=csv_happen_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	发生时间
			 	</a>
			 	${lz:set("checkeds[]","csvHappenTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvDecipt || #request.defines['csvDecipt']!=null">
			 <td <s:if test="#request.defines['csvDecipt']>0">colspan="${defines["csvDecipt"]}" ${all?"width":"iwidth"}="${defines["csvDecipt"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="csvDecipt"   title="违章描述">
			 	<a class="${desc=="csv_decipt" ? "desc" : ""}${asc=="csv_decipt" ? "asc" : ""}" href="?${desc=="csv_decipt" ? "asc=csv_decipt" : ""}${(asc=="csv_decipt" || desc!="csv_decipt" )? "desc=csv_decipt" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	违章描述
			 	</a>
			 	${lz:set("checkeds[]","csvDecipt")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvNoticeTime || #request.defines['csvNoticeTime']!=null">
			 <td <s:if test="#request.defines['csvNoticeTime']>0">colspan="${defines["csvNoticeTime"]}" ${all?"width":"iwidth"}="${defines["csvNoticeTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="10" ref="csvNoticeTime"   title="通知时间">
			 	<a class="${desc=="csv_notice_time" ? "desc" : ""}${asc=="csv_notice_time" ? "asc" : ""}" href="?${desc=="csv_notice_time" ? "asc=csv_notice_time" : ""}${(asc=="csv_notice_time" || desc!="csv_notice_time" )? "desc=csv_notice_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	通知时间
			 	</a>
			 	${lz:set("checkeds[]","csvNoticeTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvCutoffTime || #request.defines['csvCutoffTime']!=null">
			 <td <s:if test="#request.defines['csvCutoffTime']>0">colspan="${defines["csvCutoffTime"]}" ${all?"width":"iwidth"}="${defines["csvCutoffTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="11" ref="csvCutoffTime"   title="截止时间">
			 	<a class="${desc=="csv_cutoff_time" ? "desc" : ""}${asc=="csv_cutoff_time" ? "asc" : ""}" href="?${desc=="csv_cutoff_time" ? "asc=csv_cutoff_time" : ""}${(asc=="csv_cutoff_time" || desc!="csv_cutoff_time" )? "desc=csv_cutoff_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	截止时间
			 	</a>
			 	${lz:set("checkeds[]","csvCutoffTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvType || #request.defines['csvType']!=null">
			 <td <s:if test="#request.defines['csvType']>0">colspan="${defines["csvType"]}" ${all?"width":"iwidth"}="${defines["csvType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="csvType"   title="处理方式">
			 	<a class="${desc=="csv_type" ? "desc" : ""}${asc=="csv_type" ? "asc" : ""}" href="?${desc=="csv_type" ? "asc=csv_type" : ""}${(asc=="csv_type" || desc!="csv_type" )? "desc=csv_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	处理方式
			 	</a>
			 	${lz:set("checkeds[]","csvType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvPrice || #request.defines['csvPrice']!=null">
			 <td <s:if test="#request.defines['csvPrice']>0">colspan="${defines["csvPrice"]}" ${all?"width":"iwidth"}="${defines["csvPrice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="csvPrice"   title="委托金额">
			 	<a class="${desc=="csv_price" ? "desc" : ""}${asc=="csv_price" ? "asc" : ""}" href="?${desc=="csv_price" ? "asc=csv_price" : ""}${(asc=="csv_price" || desc!="csv_price" )? "desc=csv_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	委托金额
			 	</a>
			 	${lz:set("checkeds[]","csvPrice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvBranch || #request.defines['csvBranch']!=null">
			 <td <s:if test="#request.defines['csvBranch']>0">colspan="${defines["csvBranch"]}" ${all?"width":"iwidth"}="${defines["csvBranch"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="csvBranch"   title="处理部门">
			 	<a class="${desc=="csv_branch" ? "desc" : ""}${asc=="csv_branch" ? "asc" : ""}" href="?${desc=="csv_branch" ? "asc=csv_branch" : ""}${(asc=="csv_branch" || desc!="csv_branch" )? "desc=csv_branch" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	处理部门
			 	</a>
			 	${lz:set("checkeds[]","csvBranch")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvPostType || #request.defines['csvPostType']!=null">
			 <td <s:if test="#request.defines['csvPostType']>0">colspan="${defines["csvPostType"]}" ${all?"width":"iwidth"}="${defines["csvPostType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="csvPostType"   title="寄送方式">
			 	<a class="${desc=="csv_post_type" ? "desc" : ""}${asc=="csv_post_type" ? "asc" : ""}" href="?${desc=="csv_post_type" ? "asc=csv_post_type" : ""}${(asc=="csv_post_type" || desc!="csv_post_type" )? "desc=csv_post_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	寄送方式
			 	</a>
			 	${lz:set("checkeds[]","csvPostType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvAddress || #request.defines['csvAddress']!=null">
			 <td <s:if test="#request.defines['csvAddress']>0">colspan="${defines["csvAddress"]}" ${all?"width":"iwidth"}="${defines["csvAddress"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="16" ref="csvAddress"   title="送寄地址">
			 	<a class="${desc=="csv_address" ? "desc" : ""}${asc=="csv_address" ? "asc" : ""}" href="?${desc=="csv_address" ? "asc=csv_address" : ""}${(asc=="csv_address" || desc!="csv_address" )? "desc=csv_address" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	送寄地址
			 	</a>
			 	${lz:set("checkeds[]","csvAddress")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvRemark || #request.defines['csvRemark']!=null">
			 <td <s:if test="#request.defines['csvRemark']>0">colspan="${defines["csvRemark"]}" ${all?"width":"iwidth"}="${defines["csvRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="17" ref="csvRemark"   title="备注">
			 	<a class="${desc=="csv_remark" ? "desc" : ""}${asc=="csv_remark" ? "asc" : ""}" href="?${desc=="csv_remark" ? "asc=csv_remark" : ""}${(asc=="csv_remark" || desc!="csv_remark" )? "desc=csv_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 	${lz:set("checkeds[]","csvRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvAccepter || #request.defines['csvAccepter']!=null">
			 <td <s:if test="#request.defines['csvAccepter']>0">colspan="${defines["csvAccepter"]}" ${all?"width":"iwidth"}="${defines["csvAccepter"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="18" ref="csvAccepter"   title="受理人员">
			 	<a class="${desc=="csv_accepter" ? "desc" : ""}${asc=="csv_accepter" ? "asc" : ""}" href="?${desc=="csv_accepter" ? "asc=csv_accepter" : ""}${(asc=="csv_accepter" || desc!="csv_accepter" )? "desc=csv_accepter" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	受理人员
			 	</a>
			 	${lz:set("checkeds[]","csvAccepter")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvAdder || #request.defines['csvAdder']!=null">
			 <td <s:if test="#request.defines['csvAdder']>0">colspan="${defines["csvAdder"]}" ${all?"width":"iwidth"}="${defines["csvAdder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="19" ref="csvAdder"   title="添加人员">
			 	<a class="${desc=="csv_adder" ? "desc" : ""}${asc=="csv_adder" ? "asc" : ""}" href="?${desc=="csv_adder" ? "asc=csv_adder" : ""}${(asc=="csv_adder" || desc!="csv_adder" )? "desc=csv_adder" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加人员
			 	</a>
			 	${lz:set("checkeds[]","csvAdder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvUpdateTime || #request.defines['csvUpdateTime']!=null">
			 <td <s:if test="#request.defines['csvUpdateTime']>0">colspan="${defines["csvUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csvUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="20" ref="csvUpdateTime"   title="处理时间">
			 	<a class="${desc=="csv_update_time" ? "desc" : ""}${asc=="csv_update_time" ? "asc" : ""}" href="?${desc=="csv_update_time" ? "asc=csv_update_time" : ""}${(asc=="csv_update_time" || desc!="csv_update_time" )? "desc=csv_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	处理时间
			 	</a>
			 	${lz:set("checkeds[]","csvUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvAddTime || #request.defines['csvAddTime']!=null">
			 <td <s:if test="#request.defines['csvAddTime']>0">colspan="${defines["csvAddTime"]}" ${all?"width":"iwidth"}="${defines["csvAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="21" ref="csvAddTime"   title="添加时间">
			 	<a class="${desc=="csv_add_time" ? "desc" : ""}${asc=="csv_add_time" ? "asc" : ""}" href="?${desc=="csv_add_time" ? "asc=csv_add_time" : ""}${(asc=="csv_add_time" || desc!="csv_add_time" )? "desc=csv_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csvAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvDealFlag || #request.defines['csvDealFlag']!=null">
			 <td <s:if test="#request.defines['csvDealFlag']>0">colspan="${defines["csvDealFlag"]}" ${all?"width":"iwidth"}="${defines["csvDealFlag"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="22" ref="csvDealFlag"   title="受理标记">
			 	<a class="${desc=="csv_deal_flag" ? "desc" : ""}${asc=="csv_deal_flag" ? "asc" : ""}" href="?${desc=="csv_deal_flag" ? "asc=csv_deal_flag" : ""}${(asc=="csv_deal_flag" || desc!="csv_deal_flag" )? "desc=csv_deal_flag" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	受理标记
			 	</a>
			 	${lz:set("checkeds[]","csvDealFlag")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csvStatus || #request.defines['csvStatus']!=null">
			 <td <s:if test="#request.defines['csvStatus']>0">colspan="${defines["csvStatus"]}" ${all?"width":"iwidth"}="${defines["csvStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="23" ref="csvStatus"   title="业务状态">
			 	<a class="${desc=="csv_status" ? "desc" : ""}${asc=="csv_status" ? "asc" : ""}" href="?${desc=="csv_status" ? "asc=csv_status" : ""}${(asc=="csv_status" || desc!="csv_status" )? "desc=csv_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	业务状态
			 	</a>
			 	${lz:set("checkeds[]","csvStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="24" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="25" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddViolat()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.csvId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csvId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvId || #request.defines['csvId']!=null">
				<s:if test="#request.defines['csvId']>0">
					${lz:set("注释","****csvId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvId",lz:indexOf(fieldName,"csvId")>-1)}
				  		<s:if test="#request.atCsvId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvId字段的字串格式化输出****")}
					<td ref="csvId" class="td  node">
						 
						 	${lz:or(item$csvId[i.count-1],lz:left(item.csvId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvHost || #request.defines['csvHost']!=null">
				<s:if test="#request.defines['csvHost']>0">
					${lz:set("注释","****csvHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvHost",lz:indexOf(fieldName,"csvHost")>-1)}
				  		<s:if test="#request.atCsvHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvHost字段的字串格式化输出****")}
					<td ref="csvHost" class="td " relate="${item.csvHost}">
						 
						 	<a <s:if test="#item.csvHost!=null && #item.csvHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csvHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csvHost[i.count-1],lz:left(item.csvHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvOrder || #request.defines['csvOrder']!=null">
				<s:if test="#request.defines['csvOrder']>0">
					${lz:set("注释","****csvOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvOrder",lz:indexOf(fieldName,"csvOrder")>-1)}
				  		<s:if test="#request.atCsvOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvOrder字段的字串格式化输出****")}
					<td ref="csvOrder" class="td " relate="${item.csvOrder}">
						 
						 	<a <s:if test="#item.csvOrder!=null && #item.csvOrder!=''"> onclick="window.href('${basePath}${proname}/service/order_details.do?key=${item.csvOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csvOrder[i.count-1],lz:left(item.csvOrder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvMember || #request.defines['csvMember']!=null">
				<s:if test="#request.defines['csvMember']>0">
					${lz:set("注释","****csvMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvMember",lz:indexOf(fieldName,"csvMember")>-1)}
				  		<s:if test="#request.atCsvMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvMember字段的字串格式化输出****")}
					<td ref="csvMember" class="td " relate="${item.csvMember}">
						 
						 	<a <s:if test="#item.csvMember!=null && #item.csvMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csvMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csvMember[i.count-1],lz:left(item.csvMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvCar || #request.defines['csvCar']!=null">
				<s:if test="#request.defines['csvCar']>0">
					${lz:set("注释","****csvCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvCar",lz:indexOf(fieldName,"csvCar")>-1)}
				  		<s:if test="#request.atCsvCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvCar字段的字串格式化输出****")}
					<td ref="csvCar" class="td " relate="${item.csvCar}">
						 
						 	<a <s:if test="#item.csvCar!=null && #item.csvCar!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.csvCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csvCar[i.count-1],lz:left(item.csvCar$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvMoney || #request.defines['csvMoney']!=null">
				<s:if test="#request.defines['csvMoney']>0">
					${lz:set("注释","****csvMoney关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvMoney))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvMoney",lz:indexOf(fieldName,"csvMoney")>-1)}
				  		<s:if test="#request.atCsvMoney==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvMoney字段的字串格式化输出****")}
					<td ref="csvMoney" class="td ">
						 
						 	${lz:or(item$csvMoney[i.count-1],lz:left(item.csvMoney$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvScore || #request.defines['csvScore']!=null">
				<s:if test="#request.defines['csvScore']>0">
					${lz:set("注释","****csvScore关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvScore))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvScore",lz:indexOf(fieldName,"csvScore")>-1)}
				  		<s:if test="#request.atCsvScore==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvScore字段的字串格式化输出****")}
					<td ref="csvScore" class="td ">
						 
						 	${lz:or(item$csvScore[i.count-1],lz:left(item.csvScore$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvHappenTime || #request.defines['csvHappenTime']!=null">
				<s:if test="#request.defines['csvHappenTime']>0">
					${lz:set("注释","****csvHappenTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvHappenTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvHappenTime",lz:indexOf(fieldName,"csvHappenTime")>-1)}
				  		<s:if test="#request.atCsvHappenTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvHappenTime字段的字串格式化输出****")}
					<td ref="csvHappenTime" class="td ">
						 
						 	${lz:or(item$csvHappenTime[i.count-1],lz:left(item.csvHappenTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvDecipt || #request.defines['csvDecipt']!=null">
				<s:if test="#request.defines['csvDecipt']>0">
					${lz:set("注释","****csvDecipt关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvDecipt))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvDecipt",lz:indexOf(fieldName,"csvDecipt")>-1)}
				  		<s:if test="#request.atCsvDecipt==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvDecipt字段的字串格式化输出****")}
					<td ref="csvDecipt" class="td ">
						 
						 	${lz:or(item$csvDecipt[i.count-1],lz:left(item.csvDecipt$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvNoticeTime || #request.defines['csvNoticeTime']!=null">
				<s:if test="#request.defines['csvNoticeTime']>0">
					${lz:set("注释","****csvNoticeTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvNoticeTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvNoticeTime",lz:indexOf(fieldName,"csvNoticeTime")>-1)}
				  		<s:if test="#request.atCsvNoticeTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvNoticeTime字段的字串格式化输出****")}
					<td ref="csvNoticeTime" class="td ">
						 
						 	${lz:or(item$csvNoticeTime[i.count-1],lz:left(item.csvNoticeTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvCutoffTime || #request.defines['csvCutoffTime']!=null">
				<s:if test="#request.defines['csvCutoffTime']>0">
					${lz:set("注释","****csvCutoffTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvCutoffTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvCutoffTime",lz:indexOf(fieldName,"csvCutoffTime")>-1)}
				  		<s:if test="#request.atCsvCutoffTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvCutoffTime字段的字串格式化输出****")}
					<td ref="csvCutoffTime" class="td ">
						 
						 	${lz:or(item$csvCutoffTime[i.count-1],lz:left(item.csvCutoffTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvType || #request.defines['csvType']!=null">
				<s:if test="#request.defines['csvType']>0">
					${lz:set("注释","****csvType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvType",lz:indexOf(fieldName,"csvType")>-1)}
				  		<s:if test="#request.atCsvType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvType字段的字串格式化输出****")}
					<td ref="csvType" class="td ">
						 
						 	${lz:or(item$csvType[i.count-1],lz:left(item.csvType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvPrice || #request.defines['csvPrice']!=null">
				<s:if test="#request.defines['csvPrice']>0">
					${lz:set("注释","****csvPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvPrice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvPrice",lz:indexOf(fieldName,"csvPrice")>-1)}
				  		<s:if test="#request.atCsvPrice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvPrice字段的字串格式化输出****")}
					<td ref="csvPrice" class="td ">
						 
						 	${lz:or(item$csvPrice[i.count-1],lz:left(item.csvPrice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvBranch || #request.defines['csvBranch']!=null">
				<s:if test="#request.defines['csvBranch']>0">
					${lz:set("注释","****csvBranch关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvBranch))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvBranch",lz:indexOf(fieldName,"csvBranch")>-1)}
				  		<s:if test="#request.atCsvBranch==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvBranch字段的字串格式化输出****")}
					<td ref="csvBranch" class="td ">
						 
						 	${lz:or(item$csvBranch[i.count-1],lz:left(item.csvBranch$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvPostType || #request.defines['csvPostType']!=null">
				<s:if test="#request.defines['csvPostType']>0">
					${lz:set("注释","****csvPostType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvPostType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvPostType",lz:indexOf(fieldName,"csvPostType")>-1)}
				  		<s:if test="#request.atCsvPostType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvPostType字段的字串格式化输出****")}
					<td ref="csvPostType" class="td ">
						 
						 	${lz:or(item$csvPostType[i.count-1],lz:left(item.csvPostType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvAddress || #request.defines['csvAddress']!=null">
				<s:if test="#request.defines['csvAddress']>0">
					${lz:set("注释","****csvAddress关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvAddress))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvAddress",lz:indexOf(fieldName,"csvAddress")>-1)}
				  		<s:if test="#request.atCsvAddress==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvAddress字段的字串格式化输出****")}
					<td ref="csvAddress" class="td ">
						 
						 	${lz:or(item$csvAddress[i.count-1],lz:left(item.csvAddress$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvRemark || #request.defines['csvRemark']!=null">
				<s:if test="#request.defines['csvRemark']>0">
					${lz:set("注释","****csvRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvRemark",lz:indexOf(fieldName,"csvRemark")>-1)}
				  		<s:if test="#request.atCsvRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvRemark字段的字串格式化输出****")}
					<td ref="csvRemark" class="td ">
						 
						 	${lz:or(item$csvRemark[i.count-1],lz:left(item.csvRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvAccepter || #request.defines['csvAccepter']!=null">
				<s:if test="#request.defines['csvAccepter']>0">
					${lz:set("注释","****csvAccepter关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvAccepter))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvAccepter",lz:indexOf(fieldName,"csvAccepter")>-1)}
				  		<s:if test="#request.atCsvAccepter==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvAccepter字段的字串格式化输出****")}
					<td ref="csvAccepter" class="td " relate="${item.csvAccepter}">
						 
						 	<a <s:if test="#item.csvAccepter!=null && #item.csvAccepter!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csvAccepter}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csvAccepter[i.count-1],lz:left(item.csvAccepter$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvAdder || #request.defines['csvAdder']!=null">
				<s:if test="#request.defines['csvAdder']>0">
					${lz:set("注释","****csvAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvAdder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvAdder",lz:indexOf(fieldName,"csvAdder")>-1)}
				  		<s:if test="#request.atCsvAdder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvAdder字段的字串格式化输出****")}
					<td ref="csvAdder" class="td " relate="${item.csvAdder}">
						 
						 	<a <s:if test="#item.csvAdder!=null && #item.csvAdder!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csvAdder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csvAdder[i.count-1],lz:left(item.csvAdder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvUpdateTime || #request.defines['csvUpdateTime']!=null">
				<s:if test="#request.defines['csvUpdateTime']>0">
					${lz:set("注释","****csvUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvUpdateTime",lz:indexOf(fieldName,"csvUpdateTime")>-1)}
				  		<s:if test="#request.atCsvUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvUpdateTime字段的字串格式化输出****")}
					<td ref="csvUpdateTime" class="td ">
						 
						 	${lz:or(item$csvUpdateTime[i.count-1],lz:left(item.csvUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvAddTime || #request.defines['csvAddTime']!=null">
				<s:if test="#request.defines['csvAddTime']>0">
					${lz:set("注释","****csvAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvAddTime",lz:indexOf(fieldName,"csvAddTime")>-1)}
				  		<s:if test="#request.atCsvAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvAddTime字段的字串格式化输出****")}
					<td ref="csvAddTime" class="td ">
						 
						 	${lz:or(item$csvAddTime[i.count-1],lz:left(item.csvAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvDealFlag || #request.defines['csvDealFlag']!=null">
				<s:if test="#request.defines['csvDealFlag']>0">
					${lz:set("注释","****csvDealFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvDealFlag))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvDealFlag",lz:indexOf(fieldName,"csvDealFlag")>-1)}
				  		<s:if test="#request.atCsvDealFlag==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvDealFlag字段的字串格式化输出****")}
					<td ref="csvDealFlag" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csvId}','deal')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csvDealFlag[i.count-1],lz:left(item.csvDealFlag$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csvStatus || #request.defines['csvStatus']!=null">
				<s:if test="#request.defines['csvStatus']>0">
					${lz:set("注释","****csvStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csvStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsvStatus",lz:indexOf(fieldName,"csvStatus")>-1)}
				  		<s:if test="#request.atCsvStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csvStatus字段的字串格式化输出****")}
					<td ref="csvStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csvId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csvStatus[i.count-1],lz:left(item.csvStatus$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('客服通知','${item.csvId}')">客服通知</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[1]==true">
					<a href="javascript:HandleSel('委托处理','${item.csvId}')">委托处理</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[2]==true">
					<a href="javascript:HandleSel('发送短信','${item.csvId}')">发送短信</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.csvId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectViolat('${item.csvId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditViolat('${item.csvId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelViolat('${item.csvId}','${item.csvId}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsViolat('${item.csvId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csvId || #request.defines['csvId']!=null">
				<s:if test="#request.defines['csvId']>0">
					${lz:set("注释","****csvId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvId",lz:indexOf(fieldName,"csvId")>-1)}				  		
				  		<s:if test="#request.atCsvId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvHost || #request.defines['csvHost']!=null">
				<s:if test="#request.defines['csvHost']>0">
					${lz:set("注释","****csvHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvHost",lz:indexOf(fieldName,"csvHost")>-1)}				  		
				  		<s:if test="#request.atCsvHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvOrder || #request.defines['csvOrder']!=null">
				<s:if test="#request.defines['csvOrder']>0">
					${lz:set("注释","****csvOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvOrder",lz:indexOf(fieldName,"csvOrder")>-1)}				  		
				  		<s:if test="#request.atCsvOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvMember || #request.defines['csvMember']!=null">
				<s:if test="#request.defines['csvMember']>0">
					${lz:set("注释","****csvMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvMember",lz:indexOf(fieldName,"csvMember")>-1)}				  		
				  		<s:if test="#request.atCsvMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvCar || #request.defines['csvCar']!=null">
				<s:if test="#request.defines['csvCar']>0">
					${lz:set("注释","****csvCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvCar",lz:indexOf(fieldName,"csvCar")>-1)}				  		
				  		<s:if test="#request.atCsvCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvMoney || #request.defines['csvMoney']!=null">
				<s:if test="#request.defines['csvMoney']>0">
					${lz:set("注释","****csvMoney关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvMoney",lz:indexOf(fieldName,"csvMoney")>-1)}				  		
				  		<s:if test="#request.atCsvMoney==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvMoney[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvScore || #request.defines['csvScore']!=null">
				<s:if test="#request.defines['csvScore']>0">
					${lz:set("注释","****csvScore关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvScore",lz:indexOf(fieldName,"csvScore")>-1)}				  		
				  		<s:if test="#request.atCsvScore==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvScore[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvHappenTime || #request.defines['csvHappenTime']!=null">
				<s:if test="#request.defines['csvHappenTime']>0">
					${lz:set("注释","****csvHappenTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvHappenTime",lz:indexOf(fieldName,"csvHappenTime")>-1)}				  		
				  		<s:if test="#request.atCsvHappenTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvHappenTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvDecipt || #request.defines['csvDecipt']!=null">
				<s:if test="#request.defines['csvDecipt']>0">
					${lz:set("注释","****csvDecipt关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvDecipt",lz:indexOf(fieldName,"csvDecipt")>-1)}				  		
				  		<s:if test="#request.atCsvDecipt==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvDecipt[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvNoticeTime || #request.defines['csvNoticeTime']!=null">
				<s:if test="#request.defines['csvNoticeTime']>0">
					${lz:set("注释","****csvNoticeTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvNoticeTime",lz:indexOf(fieldName,"csvNoticeTime")>-1)}				  		
				  		<s:if test="#request.atCsvNoticeTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvNoticeTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvCutoffTime || #request.defines['csvCutoffTime']!=null">
				<s:if test="#request.defines['csvCutoffTime']>0">
					${lz:set("注释","****csvCutoffTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvCutoffTime",lz:indexOf(fieldName,"csvCutoffTime")>-1)}				  		
				  		<s:if test="#request.atCsvCutoffTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvCutoffTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvType || #request.defines['csvType']!=null">
				<s:if test="#request.defines['csvType']>0">
					${lz:set("注释","****csvType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvType",lz:indexOf(fieldName,"csvType")>-1)}				  		
				  		<s:if test="#request.atCsvType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvPrice || #request.defines['csvPrice']!=null">
				<s:if test="#request.defines['csvPrice']>0">
					${lz:set("注释","****csvPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvPrice",lz:indexOf(fieldName,"csvPrice")>-1)}				  		
				  		<s:if test="#request.atCsvPrice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvPrice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvBranch || #request.defines['csvBranch']!=null">
				<s:if test="#request.defines['csvBranch']>0">
					${lz:set("注释","****csvBranch关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvBranch",lz:indexOf(fieldName,"csvBranch")>-1)}				  		
				  		<s:if test="#request.atCsvBranch==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvBranch[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvPostType || #request.defines['csvPostType']!=null">
				<s:if test="#request.defines['csvPostType']>0">
					${lz:set("注释","****csvPostType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvPostType",lz:indexOf(fieldName,"csvPostType")>-1)}				  		
				  		<s:if test="#request.atCsvPostType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvPostType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvAddress || #request.defines['csvAddress']!=null">
				<s:if test="#request.defines['csvAddress']>0">
					${lz:set("注释","****csvAddress关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvAddress",lz:indexOf(fieldName,"csvAddress")>-1)}				  		
				  		<s:if test="#request.atCsvAddress==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvAddress[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvRemark || #request.defines['csvRemark']!=null">
				<s:if test="#request.defines['csvRemark']>0">
					${lz:set("注释","****csvRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvRemark",lz:indexOf(fieldName,"csvRemark")>-1)}				  		
				  		<s:if test="#request.atCsvRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvAccepter || #request.defines['csvAccepter']!=null">
				<s:if test="#request.defines['csvAccepter']>0">
					${lz:set("注释","****csvAccepter关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvAccepter",lz:indexOf(fieldName,"csvAccepter")>-1)}				  		
				  		<s:if test="#request.atCsvAccepter==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvAccepter[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvAdder || #request.defines['csvAdder']!=null">
				<s:if test="#request.defines['csvAdder']>0">
					${lz:set("注释","****csvAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvAdder",lz:indexOf(fieldName,"csvAdder")>-1)}				  		
				  		<s:if test="#request.atCsvAdder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvAdder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvUpdateTime || #request.defines['csvUpdateTime']!=null">
				<s:if test="#request.defines['csvUpdateTime']>0">
					${lz:set("注释","****csvUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvUpdateTime",lz:indexOf(fieldName,"csvUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsvUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvAddTime || #request.defines['csvAddTime']!=null">
				<s:if test="#request.defines['csvAddTime']>0">
					${lz:set("注释","****csvAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvAddTime",lz:indexOf(fieldName,"csvAddTime")>-1)}				  		
				  		<s:if test="#request.atCsvAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvDealFlag || #request.defines['csvDealFlag']!=null">
				<s:if test="#request.defines['csvDealFlag']>0">
					${lz:set("注释","****csvDealFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvDealFlag",lz:indexOf(fieldName,"csvDealFlag")>-1)}				  		
				  		<s:if test="#request.atCsvDealFlag==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvDealFlag[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csvStatus || #request.defines['csvStatus']!=null">
				<s:if test="#request.defines['csvStatus']>0">
					${lz:set("注释","****csvStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsvStatus",lz:indexOf(fieldName,"csvStatus")>-1)}				  		
				  		<s:if test="#request.atCsvStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csvStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加车辆违章
	**/
	function AddViolat(parent){		
		var url = "${basePath}${namespace}violat_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加车辆违章'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择车辆违章
	**/
	function SelectViolat(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改车辆违章
	**/
	function EditViolat(id){
		var url = "${basePath}${namespace}violat_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改车辆违章'}":lz:json(ctrl)}};
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
		EditViolat(checker.val());
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
				var url = "${basePath}${namespace}violat_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新车辆违章",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}violat_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新车辆违章",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改车辆违章
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}violat_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新车辆违章",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["deal"]={};
	fieldNames["status"]={};
	fieldNames["deal"]["csvDealFlag"]=true;
	fieldNames["status"]["csvStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改车辆违章任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}violat_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新车辆违章",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}violat_edit.do";
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
			var url = "${basePath}${namespace}violat_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除车辆违章
	**/
	function DelViolat(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除车辆违章["+flag+"]吗？</span>",
				title:"删除车辆违章",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}violat_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选车辆违章吗？</span>",
				title:"批量删除车辆违章",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}violat_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示车辆违章详情
	**/
	function DetailsViolat(id)
	{
		var url = "${basePath}${namespace}violat_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"车辆违章详情",editable:false,visible:true}};
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
		DetailsViolat(checker.val());
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


<!-- LAZY3Q_JSP_BOTTOM -->
<script>
function getHandle(operate,id){
	if(operate=="发送短信")
		return sendSms;
}
function redate(string){
	return $.formatDate("MM月dd日HH点mm分",$.dateFormat(string,"yyyy-MM-dd HH:mm:ss"));
}
function sendSms(operate,id){
	var memberid=$("#"+id+" td[ref=csvMember]").attr("relate");
	var json = $.getObject("${basePath}admin/user/member_query.do",{object:true,csmId:memberid});
	var member=json.length>0?json[0].object:{csmMobile:"请输入号码"};
	var params = {
		entrypoint:"${entrypoint}",
		ctrl:{
			fields:{
				cssmMobile:{
					defaultValue:member.csmMobile
				}
			}
		}
	};
	href("${basePath}admin/systematic/smsmt_edit.do",params);
}
</script>
<!-- LAZY3Q_JSP_BOTTOM -->


</body>
</html>