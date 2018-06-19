<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/extension/channel.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/extension/channel.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csChannel.cscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscId,cscHost,cscName,cscType,cscVIndex,indexes,logs,cscDesc,cscStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscName,cscType,cscVIndex,indexes,logs,cscDesc,cscStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscId,cscHost,cscName,cscType,cscVIndex,indexes,logs,cscDesc,cscStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscName,cscType,cscVIndex,indexes,logs,cscDesc,cscStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"访问渠道编辑":CTRL.title}</title>
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
<!------------------------LAZY3Q_JSP_BODY------------------------>



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#channelForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscId==true">
		${lz:set("haveEditable",true)}
		,"csChannel.cscId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscHost==true">
		${lz:set("haveEditable",true)}
		,"csChannel.cscHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscName==true">
		${lz:set("haveEditable",true)}
		,"csChannel.cscName":function(el){
			if(jQuery.trim(el.value)=="")
				return "渠道名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "渠道名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscType==true">
		${lz:set("haveEditable",true)}
		,"csChannel.cscType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择渠道类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscVIndex==true">
		${lz:set("haveEditable",true)}
		,"csChannel.cscVIndex":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscDesc==true">
		${lz:set("haveEditable",true)}
		,"csChannel.cscDesc":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "描述说明最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csChannel.cscUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAddTime==true">
		${lz:set("haveEditable",true)}
		,"csChannel.cscAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscStatus==true">
		${lz:set("haveEditable",true)}
		,"csChannel.cscStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
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
	<form class="form editform" ref="CsChannel" id="channelForm" name="channelForm" action="${empty CTRL.action?"channel_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写访问渠道相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csChannelToken}" name="csChannelToken" id="csChannelToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csChannel.cscId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscId和after$cscId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscId==true">
	${before$cscId}
	<dl class="cscId ${CTRL.e.cscId?"hidden":""}" major  ref="cscId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csChannel$cscId!=null">${csChannel$cscId}</s:if><s:else>
			<input type="hidden" value="${csChannel.cscId}" name="csChannel.cscId" id="cscId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscId">${csChannel.cscId}</textarea>
		 		<span>
		 	
			 ${csChannel.cscId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscHost和after$cscHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscHost==true">
	${before$cscHost}
	<dl class="cscHost " major  ref="cscHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csChannel$cscHost!=null">${csChannel$cscHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscHost" name="csChannel.cscHost">
		 				<option selected value="${csChannel.cscHost}">
		 					${get:SrvHost(csChannel.cscHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscHost">${csChannel.cscHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csChannel.cscHost}',{ctrl:{editable:false,visible:true}})">
			 ${csChannel.cscHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscHost}
	</s:if>
	
	${lz:set("注释","*****************渠道名称字段的输入框代码*****************")}
	${lz:set("注释","before$cscName和after$cscName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscName==true">
	${before$cscName}
	<dl class="cscName " major  ref="cscName" >
		<dt>渠道名称:</dt>
		<s:if test="#request.CTRL.e.cscName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChannel$cscName!=null">${csChannel$cscName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csChannel.cscName" id="cscName"  value="${csChannel.cscName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入访问渠道的渠道名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****渠道名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscName">${csChannel.cscName}</textarea>
		 		<span>
		 	
			 ${csChannel.cscName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscName}
	</s:if>
	
	${lz:set("注释","*****************渠道类型字段的输入框代码*****************")}
	${lz:set("注释","before$cscType和after$cscType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscType==true">
	${before$cscType}
	<dl class="cscType " major  ref="cscType" >
		<dt>渠道类型:</dt>
		<s:if test="#request.CTRL.e.cscType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChannel$cscType!=null">${csChannel$cscType}</s:if><s:else>
		 	<select class="narrow" id="cscType" name="csChannel.cscType">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("渠道类型","channel_type"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csChannel.cscType==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscType').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#cscType').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("channel_type").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#cscType');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择访问渠道的渠道类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****渠道类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscType">${csChannel.cscType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csChannel.cscType}',{ctrl:{editable:false,visible:true}})">
			 ${csChannel.cscType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscType}
	</s:if>
	
	${lz:set("注释","*****************开启V指标字段的输入框代码*****************")}
	${lz:set("注释","before$cscVIndex和after$cscVIndex变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscVIndex==true">
	${before$cscVIndex}
	<dl class="cscVIndex " major  ref="cscVIndex" >
		<dt>开启V指标:</dt>
		<s:if test="#request.CTRL.e.cscVIndex==true">
		${lz:set("haveEditable",true)}
		<dd input="radio">
		<s:if test="#request.csChannel$cscVIndex!=null">${csChannel$cscVIndex}</s:if><s:else>
		 	<div class='narrow input'>
		 	&nbsp;
	 	    <label><input type="radio" value="true" name="csChannel.cscVIndex" id="cscVIndex" ${csChannel.cscVIndex==true?"checked":""}/>是&nbsp;&nbsp;&nbsp;</label>
			<label><input type="radio" value="false" name="csChannel.cscVIndex" id="cscVIndex" ${csChannel==null || csChannel.cscVIndex==null || csChannel.cscVIndex==false?"checked":""}/>否</label>
		 	</div>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>是否开启UV,PV,IP指标</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开启V指标字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscVIndex">${csChannel.cscVIndex}</textarea>
		 		<span>
		 	
			 ${csChannel.cscVIndex$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscVIndex}
	</s:if>
	
	${lz:set("注释","*****************指标字段的输入框代码*****************")}
	${lz:set("注释","before$indexes和after$indexes变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.indexes==true">
	${before$indexes}
	<dl class="indexes " minor  ref="indexes" style="width:98%;">
		<dt>指　　标:</dt>
		<s:if test="#request.CTRL.e.indexes==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csChannel$indexes!=null">${csChannel$indexes}</s:if><s:else>
		  		<s:if test="csChannel.cscId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="indexes" name="indexes" frameborder='0' src="" url="${basePath}${proname}/extension/channelindex.do?inFrame=true&canQuery=false&all=true&csciPlan=${csChannel.cscId}&ctrl={queryable:false,fields:{csciPlan:{visible:true,editable:false,defaultValue:${csChannel.cscId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#indexes').attr('src',$('#indexes').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑指标
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****指标字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csChannel.cscId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="indexes" name="indexes" frameborder='0' src="" url="${basePath}${proname}/extension/channelindex.do?inFrame=true&canQuery=false&all=true&csciPlan=${csChannel.cscId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#indexes').attr('src',$('#indexes').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$indexes}
	</s:if>
	
	${lz:set("注释","*****************跟踪维护字段的输入框代码*****************")}
	${lz:set("注释","before$logs和after$logs变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.logs==true">
	${before$logs}
	<dl class="logs " minor  ref="logs" style="width:98%;">
		<dt>跟踪维护:</dt>
		<s:if test="#request.CTRL.e.logs==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csChannel$logs!=null">${csChannel$logs}</s:if><s:else>
		  		<s:if test="csChannel.cscId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="logs" name="logs" frameborder='0' src="" url="${basePath}${proname}/extension/channellog.do?inFrame=true&canQuery=false&all=true&csclPlan=${csChannel.cscId}&ctrl={queryable:false,fields:{csclPlan:{visible:true,editable:false,defaultValue:${csChannel.cscId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#logs').attr('src',$('#logs').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑跟踪维护
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****跟踪维护字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csChannel.cscId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="logs" name="logs" frameborder='0' src="" url="${basePath}${proname}/extension/channellog.do?inFrame=true&canQuery=false&all=true&csclPlan=${csChannel.cscId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#logs').attr('src',$('#logs').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$logs}
	</s:if>
	
	${lz:set("注释","*****************描述说明字段的输入框代码*****************")}
	${lz:set("注释","before$cscDesc和after$cscDesc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscDesc==true">
	${before$cscDesc}
	<dl class="cscDesc " major  ref="cscDesc" style="width:98%;">
		<dt>描述说明:</dt>
		<s:if test="#request.CTRL.e.cscDesc==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csChannel$cscDesc!=null">${csChannel$cscDesc}</s:if><s:else>
		 	<textarea class="input wide"  id="cscDesc" name="csChannel.cscDesc" rows="5">${csChannel.cscDesc}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入访问渠道的描述说明</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****描述说明字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csChannel.cscDesc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscDesc}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscUpdateTime和after$cscUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscUpdateTime==true">
	${before$cscUpdateTime}
	<dl class="cscUpdateTime " major  ref="cscUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cscUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChannel$cscUpdateTime!=null">${csChannel$cscUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChannel.cscUpdateTime" id="cscUpdateTime"  value="<s:date name="csChannel.cscUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择访问渠道的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscUpdateTime">${csChannel.cscUpdateTime}</textarea>
		 		<span>
		 	
			 ${csChannel.cscUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscAddTime和after$cscAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscAddTime==true">
	${before$cscAddTime}
	<dl class="cscAddTime " major  ref="cscAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChannel$cscAddTime!=null">${csChannel$cscAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChannel.cscAddTime" id="cscAddTime"  value="<s:date name="csChannel.cscAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择访问渠道的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAddTime">${csChannel.cscAddTime}</textarea>
		 		<span>
		 	
			 ${csChannel.cscAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscStatus和after$cscStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscStatus==true">
	${before$cscStatus}
	<dl class="cscStatus " major  ref="cscStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChannel$cscStatus!=null">${csChannel$cscStatus}</s:if><s:else>
		 	<select class="narrow" id="cscStatus" name="csChannel.cscStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csChannel.cscStatus==1?"selected":""}>正常</option>
				<option value="0" ${csChannel.cscStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择访问渠道的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscStatus">${csChannel.cscStatus}</textarea>
		 		<span>
		 	
			 ${csChannel.cscStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csChannel.cscId}" name="csChannel.cscId" id="cscId" />
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