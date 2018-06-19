<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/record.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csRecord.csrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csrId,csrHost,csrRemark,csrMember,csrMoneyType,csrRecordSubject,csrRecordType,csrAmount,csrAddTime,csrOrder,csrObject,csrEditor",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csrId,csrHost,csrRemark,csrMember,csrMoneyType,csrRecordSubject,csrRecordType,csrAmount,csrRemain,csrAddTime,csrOrder,csrRelate,csrObject,csrEditor",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csrId,csrHost,csrRemark,csrMember,csrMoneyType,csrRecordSubject,csrRecordType,csrAmount,csrAddTime",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csrId,csrHost,csrRemark,csrMember,csrMoneyType,csrRecordSubject,csrRecordType,csrAmount,csrAddTime",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>消费记录统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	<meta name="hashcode" content="${window.hashcode}">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	<!-- 统计处理js -->
	<script type="text/javascript" src="${basePath}admin/js/lazy3q.stats.js"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	<script type="text/javascript">
		window.basePath="${basePath}";
		window.hashcode = "${window.hashcode}";
		window.contextPath="${basePath}admin/";
		window.apiUrl="${basePath}admin/api.do";
	</script>
		
	<!-- 感谢My97DatePicker时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	<!-- 感谢FusionCharts报表图控件 -->
	<script type="text/javascript" src="${basePath}admin/js/FusionCharts/JSClass/FusionCharts.js"></script>
	
	
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

<div class="shortcuts state-content" style="display:${(window.shortcuts!="hide" && (shortcuts!=null || haveQuerys))?"block":"none"};">
	<div class="shadow"></div>
	<div class="holder">
		<a class="item ${(T+Q+0)==0?"focus":""}" href="${location}">		
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256421631277806.png"/>
			默认数据统计
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


<table class="${(window.shortcuts!="hide" && (shortcuts!=null || haveQuerys))?"margin-by-shortcut":""}" border="0" cellspacing="0" cellpadding="0"><tr><td>

<div class="title">
	
	<a href="javascript:void(0);" onclick="parent.current.close();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Badge-multiply.png"/>
		关闭页面
	</a>
	<s:if test="#request.hideQuery!=true">
	<a href="javascript:void(0);" class="linker imize ${window.imize=="minimize"?"minimize":"maximize"}">
		<img align="absmiddle" class="up" src="${basePath}admin/images/icons/Arrow up.png"/>
		<img align="absmiddle" class="down" src="${basePath}admin/images/icons/Arrow down.png"/>
		展开收起
	</a>
	</s:if>
	<a href="javascript:void(0);" class="linker setting">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256421529677801.png"/>
		页面设置
	</a>
	<a href="?${lz:querys("UTF-8","xxxxx")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/2008825642250778028.png"/>
		重新加载
	</a>
	<a class="linker export" href="record_stats.do?export=true">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256427828778018.png"/>
		数据导出
	</a>
	<a href="record.do?${lz:querys("UTF-8","")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256421631277806.png"/>
		数据管理
	</a>
		
		
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">消费记录统计</span>
	<dl ref="com.ccclubs.model.CsRecord" class="display dropdowan" style="margin-left:30px;">
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
				<a all="" fields="${fields}" href="${basePath}${lz:replace(servletPath,"^/","")}?${lz:queryss("UTF-8","all","fields")}">自定义显示</a>
			</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
			</tbody></table>
		</dd>
	</dl>
</div>
<s:if test="#request.canQuery==true && #request.CTRL.canQuery==true">
<div class="plate" style="${(hideQuery || (lz:or(window.imize,"maximize"))=="minimize") ? "display:none;":""}">
	<form ref="CsRecord" class="query statsForm" id="queryForm" action="record_stats.do" method="post">
		
		${lz:set("haveQuery",false)}
	
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="record.query.jsp"%>
	
		<div class="line"></div>
		
		<div class="group condition">
			<strong>统计分组：</strong>				
			<ul>
				<li>
					<input ${csrMemberGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csrMember"/>消费会员
				 	&nbsp;&nbsp;
				</li>
				<li>
					<input ${csrAddTimeGroup==true?"checked='checked'":""} class="groupBy" type="checkbox" name="groupBy" value="csrAddTime"/>
				 	<select name="csrAddTimeTimeType">
				 		<option value="%Y-%m,yyyy年MM月">时间</option>
				 		
				 		<option ${csrAddTimeTimeType=="%Y,yyyy年"?"selected":""} value="%Y,yyyy年">
					 	每年(时间)</option>
					 	<option ${csrAddTimeTimeType=="%Y-%m,yyyy年MM月"?"selected":""} value="%Y-%m,yyyy年MM月">
					 	每月(时间)</option>
					 	<option ${csrAddTimeTimeType=="%Y%U,yyyy年第ww周"?"selected":""} value="%Y%U,yyyy年第ww周">
					 	每周(时间)</option>
					 	<option ${csrAddTimeTimeType=="%Y-%m-%d,yyyy年MM月dd日"?"selected":""} value="%Y-%m-%d,yyyy年MM月dd日">
					 	每日(时间)</option>
					 	<option ${csrAddTimeTimeType=="%Y-%m-%d %H,yyyy年MM月dd日 HH点"?"selected":""} value="%Y-%m-%d %H,yyyy年MM月dd日 HH点">
					 	每时(时间)</option>
					 	
					 	<option ${csrAddTimeTimeType=="%m,MM月"?"selected":""} value="%m,MM月">
					 	月份(时间)</option>
					 	<option ${csrAddTimeTimeType=="%U,第ww周"?"selected":""} value="%U,第ww周">
					 	周份(时间)</option>
					 	<option ${csrAddTimeTimeType=="%w,EEEE"?"selected":""} value="%w,EEEE">
					 	星期(时间)</option>
					 	<option ${csrAddTimeTimeType=="%d,dd日"?"selected":""} value="%d,dd日">
					 	天份(时间)</option>
					 	<option ${csrAddTimeTimeType=="%H,HH点"?"selected":""} value="%H,HH点">
					 	时份(时间)</option>
				 		
				 	</select>
				 	&nbsp;&nbsp;
				</li>
				<div class="clear"></div>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="line"></div>
		
					
		<div class="sum condition">
			<strong>统计类型：</strong>
			<ul>
				<li>
					<input ${countSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="count"/>
					数量&nbsp;&nbsp;
				</li>
				<li>
				 	<input ${csrAmountSum==true?"checked='checked'":""} class="sumBy" type="checkbox" name="sumBy" value="csrAmount"/>
					数额&nbsp;&nbsp;
				</li>
		
<!------------------------LAZY3Q_SUM_DEFINE------------------------>
<!------------------------LAZY3Q_SUM_DEFINE------------------------>

				<div class="clear"></div>
			</ul>
			<div class="clear"></div>
		</div>
		
		
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
})
</script>
</s:if>
	
</td></tr><tr><td>
	  			
	<div class="content">
	
		<s:if test="#request.groupCount==0 && #request.sumCount==0">
		<div style="padding:10px;line-height:32px;font-size:14px;">
			<img width="25" height="25" align="absmiddle" src="${basePath}admin/images/icons/20088256421529677807.png" />
			&nbsp;&nbsp;<font size="3">[统计说明]：</font><br/>
			1：先根据查询条件筛选出您想要的数据，然后选择下面不同的分组类型和统计类型，查询出N种不同类型的统计数据<br/>
			2：统计分组指的是根据不同的类型把查出的数据分成不的组分别进行统计,统计类型同时最多只能选择三项（当选择三项分组时，系统将使用这三项进行交叉分组统计）<br/>
			3：统计类型指的是要统计哪些内容,当统计分组和统计类型都只选择一项时，数据可以通过表格、饼图、柱状图、曲线图...等多种方式展示数据<br/>
			4：关于时间分组：<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			每年,每月,每周,每日,每时表示每个时间单元不重复，比如两年的每日会分成365*2=730个统计单位<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			月份,周份,星期,天份,时份表未每个时间单元重复分组，比如你查询再久的时间范围，按月份永远只分成12组，按星期的话永远只分成7组.....
		</div>
		</s:if>
		<s:else>
		<div class='viewtypes'>
			
		</div>
		<div id="stats" class="stats"></div>
		<script>
		var stats = {
			groupCount:${groupCount+0},
			sumCount:${sumCount+0},
			groupBy:${lz:or(lz:json(groupBy),"[]")},
			sumBy:[<s:iterator value="#request.sumBy" id="item" status="i">${i.count>1?",":""}"${lz:split(item,":")[0]}"</s:iterator>],
			haveTimeType:${haveTimeType==true},
			timeTypes:${lz:or(lz:json(timeTypes),"[]")},
			viewType:"${lz:or(viewType,"table")}"			
		};
		stats.structs={
		 	  "count":"总数"
			  ,"csrMember":"消费会员"
			  ,"csrAmount":"数额"
			  ,"csrAddTime":"时间"
			  <s:iterator value="#request.sumBy" id="sumName" status="i">
			  ${lz:set("array",lz:split(sumName,":"))}${lz:set("arrayCount",lz:size(array))}
			  <s:if test="#request.arrayCount==3">,"${array[0]}":"${array[2]}"</s:if>
			  </s:iterator>
		}
		stats.datas=[ 
		 	  <s:iterator value="#request.list" id="item" status="i">${i.count>1?",":""}{"count":"${item.count}"
			  <lz:set name="itemValue">
					 	 ${get:CsMember(item.csrMember).csmName}			 	 
		  	 </lz:set>
		  	 ,"csrMember":"${lz:js(lz:trim(itemValue))}"
			  <lz:set name="itemValue">
					 	 ${lz:date(item.csrAddTime,lz:split(csrAddTimeTimeType,",")[1])}		 	 
		  	 </lz:set>
		  	 ,"csrAddTime":"${lz:js(lz:trim(itemValue))}"
		  	 ,"csrAmount":"${lz:digit(item.csrAmount,2)}"
			  <s:iterator value="#request.sumBy" id="sumName" status="i">
			  ${lz:set("array",lz:split(sumName,":"))}${lz:set("arrayCount",lz:size(array))}
			  <s:if test="#request.arrayCount==3">,"${array[0]}":"${item[array[0]]}"</s:if>
			  </s:iterator>
			 }</s:iterator>
		 ]
		 </script>
		 </s:else>
		 
	</div>
  
  
 </td></tr></table>

<script>
	
	showTips("${tips.value}");

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

</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>