<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/lucky/lucky.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/lucky/lucky.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csLucky.cslId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cslId,cslName,cslFlag,cslType,cslItems,cslStart,cslFinish,cslPrice,cslStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cslId,cslName,cslFlag,cslType,cslItems,cslStart,cslFinish,cslPrice,cslStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cslId,cslName,cslFlag,cslType,cslItems,cslStart,cslFinish,cslPrice,cslStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cslId,cslName,cslFlag,cslType,cslItems,cslStart,cslFinish,cslPrice,cslStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"抽奖活动编辑":CTRL.title}</title>
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
	$("#luckyForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cslId==true">
		${lz:set("haveEditable",true)}
		,"csLucky.cslId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslName==true">
		${lz:set("haveEditable",true)}
		,"csLucky.cslName":function(el){
			if(jQuery.trim(el.value)=="")
				return "活动名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "活动名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslFlag==true">
		${lz:set("haveEditable",true)}
		,"csLucky.cslFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "活动标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslType==true">
		${lz:set("haveEditable",true)}
		,"csLucky.cslType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择抽奖类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslStart==true">
		${lz:set("haveEditable",true)}
		,"csLucky.cslStart":function(el){
			if(jQuery.trim(el.value)=="")
				return "开始时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslFinish==true">
		${lz:set("haveEditable",true)}
		,"csLucky.cslFinish":function(el){
			if(jQuery.trim(el.value)=="")
				return "结束时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslPrice==true">
		${lz:set("haveEditable",true)}
		,"csLucky.cslPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "抽奖价格输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslEditor==true">
		${lz:set("haveEditable",true)}
		,"csLucky.cslEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csLucky.cslUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslAddTime==true">
		${lz:set("haveEditable",true)}
		,"csLucky.cslAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslStatus==true">
		${lz:set("haveEditable",true)}
		,"csLucky.cslStatus":function(el){
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
	<form class="form editform" ref="CsLucky" id="luckyForm" name="luckyForm" action="${empty CTRL.action?"lucky_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写抽奖活动相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csLuckyToken}" name="csLuckyToken" id="csLuckyToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csLucky.cslId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cslId和after$cslId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslId==true">
	${before$cslId}
	<dl class="cslId ${CTRL.e.cslId?"hidden":""}" major  ref="cslId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cslId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csLucky$cslId!=null">${csLucky$cslId}</s:if><s:else>
			<input type="hidden" value="${csLucky.cslId}" name="csLucky.cslId" id="cslId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslId">${csLucky.cslId}</textarea>
		 		<span>
		 	
			 ${csLucky.cslId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslId}
	</s:if>
	
	${lz:set("注释","*****************活动名称字段的输入框代码*****************")}
	${lz:set("注释","before$cslName和after$cslName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslName==true">
	${before$cslName}
	<dl class="cslName " major  ref="cslName" >
		<dt>活动名称:</dt>
		<s:if test="#request.CTRL.e.cslName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLucky$cslName!=null">${csLucky$cslName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csLucky.cslName" id="cslName"  value="${csLucky.cslName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入抽奖活动的活动名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****活动名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslName">${csLucky.cslName}</textarea>
		 		<span>
		 	
			 ${csLucky.cslName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslName}
	</s:if>
	
	${lz:set("注释","*****************活动标识字段的输入框代码*****************")}
	${lz:set("注释","before$cslFlag和after$cslFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslFlag==true">
	${before$cslFlag}
	<dl class="cslFlag " minor  ref="cslFlag" >
		<dt>活动标识:</dt>
		<s:if test="#request.CTRL.e.cslFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLucky$cslFlag!=null">${csLucky$cslFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csLucky.cslFlag" id="cslFlag"  value="${csLucky.cslFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>尽量不要改</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****活动标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslFlag">${csLucky.cslFlag}</textarea>
		 		<span>
		 	
			 ${csLucky.cslFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslFlag}
	</s:if>
	
	${lz:set("注释","*****************抽奖类型字段的输入框代码*****************")}
	${lz:set("注释","before$cslType和after$cslType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslType==true">
	${before$cslType}
	<dl class="cslType " major  ref="cslType" >
		<dt>抽奖类型:</dt>
		<s:if test="#request.CTRL.e.cslType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLucky$cslType!=null">${csLucky$cslType}</s:if><s:else>
		 	<select class="narrow" id="cslType" name="csLucky.cslType">
		 		<option value="">请选择</option>
				<option value="0" ${csLucky.cslType==0?"selected":""}>大转盘</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>如大转盘、老虎机、刮刮奖</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****抽奖类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslType">${csLucky.cslType}</textarea>
		 		<span>
		 	
			 ${csLucky.cslType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslType}
	</s:if>
	
	${lz:set("注释","*****************奖项字段的输入框代码*****************")}
	${lz:set("注释","before$cslItems和after$cslItems变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslItems==true">
	${before$cslItems}
	<dl class="cslItems " minor  ref="cslItems" style="width:98%;">
		<dt>奖　　项:</dt>
		<s:if test="#request.CTRL.e.cslItems==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLucky$cslItems!=null">${csLucky$cslItems}</s:if><s:else>
		  		<s:if test="csLucky.cslId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="cslItems" name="cslItems" frameborder='0' src="" url="${basePath}${proname}/operate/lucky/luckyitem.do?inFrame=true&canQuery=false&all=true&csliLuckyId=${csLucky.cslId}&ctrl={queryable:false,fields:{csliLuckyId:{visible:true,editable:false,defaultValue:${csLucky.cslId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#cslItems').attr('src',$('#cslItems').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑奖项
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****奖项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLucky.cslId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="cslItems" name="cslItems" frameborder='0' src="" url="${basePath}${proname}/operate/lucky/luckyitem.do?inFrame=true&canQuery=false&all=true&csliLuckyId=${csLucky.cslId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#cslItems').attr('src',$('#cslItems').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$cslItems}
	</s:if>
	
	${lz:set("注释","*****************开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslStart和after$cslStart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslStart==true">
	${before$cslStart}
	<dl class="cslStart " major  ref="cslStart" >
		<dt>开始时间:</dt>
		<s:if test="#request.CTRL.e.cslStart==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLucky$cslStart!=null">${csLucky$cslStart}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLucky.cslStart" id="cslStart"  value="<s:date name="csLucky.cslStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择抽奖活动的开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslStart">${csLucky.cslStart}</textarea>
		 		<span>
		 	
			 ${csLucky.cslStart$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslStart}
	</s:if>
	
	${lz:set("注释","*****************结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslFinish和after$cslFinish变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslFinish==true">
	${before$cslFinish}
	<dl class="cslFinish " major  ref="cslFinish" >
		<dt>结束时间:</dt>
		<s:if test="#request.CTRL.e.cslFinish==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLucky$cslFinish!=null">${csLucky$cslFinish}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLucky.cslFinish" id="cslFinish"  value="<s:date name="csLucky.cslFinish" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择抽奖活动的结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslFinish">${csLucky.cslFinish}</textarea>
		 		<span>
		 	
			 ${csLucky.cslFinish$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslFinish}
	</s:if>
	
	${lz:set("注释","*****************抽奖价格字段的输入框代码*****************")}
	${lz:set("注释","before$cslPrice和after$cslPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslPrice==true">
	${before$cslPrice}
	<dl class="cslPrice " major  ref="cslPrice" >
		<dt>抽奖价格:</dt>
		<s:if test="#request.CTRL.e.cslPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLucky$cslPrice!=null">${csLucky$cslPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLucky.cslPrice" id="cslPrice"  value="${csLucky.cslPrice}"/>
	 	 </s:else>
	 	 积分
	 	 <b>*</b>
	 	 <em>请输入抽奖活动的抽奖价格</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****抽奖价格字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslPrice">${csLucky.cslPrice}</textarea>
		 		<span>
		 	
			 ${csLucky.cslPrice$}
	 	 积分 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslPrice}
	</s:if>
	
	${lz:set("注释","*****************编辑人字段的输入框代码*****************")}
	${lz:set("注释","before$cslEditor和after$cslEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslEditor==true">
	${before$cslEditor}
	<dl class="cslEditor " major  ref="cslEditor" >
		<dt>编&nbsp;&nbsp;辑&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cslEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csLucky$cslEditor!=null">${csLucky$cslEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cslEditor" name="csLucky.cslEditor">
		 				<option selected value="${csLucky.cslEditor}">
		 					${get:SrvUser(csLucky.cslEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cslEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编辑人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslEditor">${csLucky.cslEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csLucky.cslEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csLucky.cslEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslUpdateTime和after$cslUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslUpdateTime==true">
	${before$cslUpdateTime}
	<dl class="cslUpdateTime " major  ref="cslUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cslUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLucky$cslUpdateTime!=null">${csLucky$cslUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLucky.cslUpdateTime" id="cslUpdateTime"  value="<s:date name="csLucky.cslUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择抽奖活动的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslUpdateTime">${csLucky.cslUpdateTime}</textarea>
		 		<span>
		 	
			 ${csLucky.cslUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslAddTime和after$cslAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslAddTime==true">
	${before$cslAddTime}
	<dl class="cslAddTime " major  ref="cslAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cslAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLucky$cslAddTime!=null">${csLucky$cslAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLucky.cslAddTime" id="cslAddTime"  value="<s:date name="csLucky.cslAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择抽奖活动的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslAddTime">${csLucky.cslAddTime}</textarea>
		 		<span>
		 	
			 ${csLucky.cslAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cslStatus和after$cslStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslStatus==true">
	${before$cslStatus}
	<dl class="cslStatus " major  ref="cslStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cslStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLucky$cslStatus!=null">${csLucky$cslStatus}</s:if><s:else>
		 	<select class="narrow" id="cslStatus" name="csLucky.cslStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csLucky.cslStatus==1?"selected":""}>正常</option>
				<option value="0" ${csLucky.cslStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择抽奖活动的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslStatus">${csLucky.cslStatus}</textarea>
		 		<span>
		 	
			 ${csLucky.cslStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csLucky.cslId}" name="csLucky.cslId" id="cslId" />
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