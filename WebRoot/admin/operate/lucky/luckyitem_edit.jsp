<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/lucky/luckyitem.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/lucky/luckyitem.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csLuckyItem.csliId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csliId,csliName,csliLuckyId,csliType,csliIndex,csliAmount,csliCount,csliRatio,csliStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csliId,csliName,csliLuckyId,csliType,csliIndex,csliAmount,csliCount,csliRatio,csliStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csliId,csliName,csliLuckyId,csliType,csliIndex,csliAmount,csliCount,csliRatio,csliStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csliId,csliName,csliLuckyId,csliType,csliIndex,csliAmount,csliCount,csliRatio,csliStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"抽奖奖项编辑":CTRL.title}</title>
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
	$("#luckyitemForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csliId==true">
		${lz:set("haveEditable",true)}
		,"csLuckyItem.csliId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csliName==true">
		${lz:set("haveEditable",true)}
		,"csLuckyItem.csliName":function(el){
			if(jQuery.trim(el.value)=="")
				return "奖品名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "奖品名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csliLuckyId==true">
		${lz:set("haveEditable",true)}
		,"csLuckyItem.csliLuckyId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csliType==true">
		${lz:set("haveEditable",true)}
		,"csLuckyItem.csliType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择奖项类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csliIndex==true">
		${lz:set("haveEditable",true)}
		,"csLuckyItem.csliIndex":function(el){
			if(jQuery.trim(el.value)=="")
				return "奖项等级不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csliAmount==true">
		${lz:set("haveEditable",true)}
		,"csLuckyItem.csliAmount":function(el){
			if(jQuery.trim(el.value)=="")
				return "奖额不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csliCount==true">
		${lz:set("haveEditable",true)}
		,"csLuckyItem.csliCount":function(el){
			if(jQuery.trim(el.value)=="")
				return "投放数量不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csliRemain==true">
		${lz:set("haveEditable",true)}
		,"csLuckyItem.csliRemain":function(el){
			if(jQuery.trim(el.value)=="")
				return "剩余数量不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csliRatio==true">
		${lz:set("haveEditable",true)}
		,"csLuckyItem.csliRatio":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "中奖概率输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csliUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csLuckyItem.csliUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csliAddTime==true">
		${lz:set("haveEditable",true)}
		,"csLuckyItem.csliAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csliStatus==true">
		${lz:set("haveEditable",true)}
		,"csLuckyItem.csliStatus":function(el){
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
	<form class="form editform" ref="CsLuckyItem" id="luckyitemForm" name="luckyitemForm" action="${empty CTRL.action?"luckyitem_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写抽奖奖项相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csLuckyItemToken}" name="csLuckyItemToken" id="csLuckyItemToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csLuckyItem.csliId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csliId和after$csliId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csliId==true">
	${before$csliId}
	<dl class="csliId ${CTRL.e.csliId?"hidden":""}" major  ref="csliId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csliId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csLuckyItem$csliId!=null">${csLuckyItem$csliId}</s:if><s:else>
			<input type="hidden" value="${csLuckyItem.csliId}" name="csLuckyItem.csliId" id="csliId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csliId">${csLuckyItem.csliId}</textarea>
		 		<span>
		 	
			 ${csLuckyItem.csliId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csliId}
	</s:if>
	
	${lz:set("注释","*****************奖品名称字段的输入框代码*****************")}
	${lz:set("注释","before$csliName和after$csliName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csliName==true">
	${before$csliName}
	<dl class="csliName " major  ref="csliName" >
		<dt>奖品名称:</dt>
		<s:if test="#request.CTRL.e.csliName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLuckyItem$csliName!=null">${csLuckyItem$csliName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csLuckyItem.csliName" id="csliName"  value="${csLuckyItem.csliName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入抽奖奖项的奖品名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****奖品名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csliName">${csLuckyItem.csliName}</textarea>
		 		<span>
		 	
			 ${csLuckyItem.csliName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csliName}
	</s:if>
	
	${lz:set("注释","*****************所属活动字段的输入框代码*****************")}
	${lz:set("注释","before$csliLuckyId和after$csliLuckyId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csliLuckyId==true">
	${before$csliLuckyId}
	<dl class="csliLuckyId " major  ref="csliLuckyId" >
		<dt>所属活动:</dt>
		<s:if test="#request.CTRL.e.csliLuckyId==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csLuckyItem$csliLuckyId!=null">${csLuckyItem$csliLuckyId}</s:if><s:else>
		 			<input title="请输入抽奖活动活动名称进行查询" class="combox narrow" action="${basePath}${proname}/operate/lucky/lucky_query.do?value={param}" type="text" id="csliLuckyId" name="csLuckyItem.csliLuckyId" text="${get:CsLucky(csLuckyItem.csliLuckyId).cslName}" value="${csLuckyItem.csliLuckyId}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csliLuckyId').val())==''){return;};window.href('${basePath}${proname}/operate/lucky/lucky_details.do?key='+$('#csliLuckyId').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属活动字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csliLuckyId">${csLuckyItem.csliLuckyId}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/lucky/lucky_details.do?key=${csLuckyItem.csliLuckyId}',{ctrl:{editable:false,visible:true}})">
			 ${csLuckyItem.csliLuckyId$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csliLuckyId}
	</s:if>
	
	${lz:set("注释","*****************奖项类型字段的输入框代码*****************")}
	${lz:set("注释","before$csliType和after$csliType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csliType==true">
	${before$csliType}
	<dl class="csliType " major  ref="csliType" >
		<dt>奖项类型:</dt>
		<s:if test="#request.CTRL.e.csliType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLuckyItem$csliType!=null">${csLuckyItem$csliType}</s:if><s:else>
		 	<select class="narrow" id="csliType" name="csLuckyItem.csliType">
		 		<option value="">请选择</option>
				<option value="0" ${csLuckyItem.csliType==0?"selected":""}>谢谢参与</option>
				<option value="1" ${csLuckyItem.csliType==1?"selected":""}>积分</option>
				<option value="2" ${csLuckyItem.csliType==2?"selected":""}>现金券</option>
				<option value="3" ${csLuckyItem.csliType==3?"selected":""}>免费小时</option>
				<option value="4" ${csLuckyItem.csliType==4?"selected":""}>实物</option>
				<option value="5" ${csLuckyItem.csliType==5?"selected":""}>其它方式</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择抽奖奖项的奖项类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****奖项类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csliType">${csLuckyItem.csliType}</textarea>
		 		<span>
		 	
			 ${csLuckyItem.csliType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csliType}
	</s:if>
	
	${lz:set("注释","*****************奖项等级字段的输入框代码*****************")}
	${lz:set("注释","before$csliIndex和after$csliIndex变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csliIndex==true">
	${before$csliIndex}
	<dl class="csliIndex " major  ref="csliIndex" >
		<dt>奖项等级:</dt>
		<s:if test="#request.CTRL.e.csliIndex==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLuckyItem$csliIndex!=null">${csLuckyItem$csliIndex}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLuckyItem.csliIndex" id="csliIndex"  value="${csLuckyItem.csliIndex}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>技术参数，用来判断几等奖</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****奖项等级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csliIndex">${csLuckyItem.csliIndex}</textarea>
		 		<span>
		 	
			 ${csLuckyItem.csliIndex$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csliIndex}
	</s:if>
	
	${lz:set("注释","*****************奖额字段的输入框代码*****************")}
	${lz:set("注释","before$csliAmount和after$csliAmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csliAmount==true">
	${before$csliAmount}
	<dl class="csliAmount " major  ref="csliAmount" >
		<dt>奖　　额:</dt>
		<s:if test="#request.CTRL.e.csliAmount==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLuckyItem$csliAmount!=null">${csLuckyItem$csliAmount}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLuckyItem.csliAmount" id="csliAmount"  value="${csLuckyItem.csliAmount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>积分、免费小时、现金券为对应的数量，其它为1</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****奖额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csliAmount">${csLuckyItem.csliAmount}</textarea>
		 		<span>
		 	
			 ${csLuckyItem.csliAmount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csliAmount}
	</s:if>
	
	${lz:set("注释","*****************投放数量字段的输入框代码*****************")}
	${lz:set("注释","before$csliCount和after$csliCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csliCount==true">
	${before$csliCount}
	<dl class="csliCount " major  ref="csliCount" >
		<dt>投放数量:</dt>
		<s:if test="#request.CTRL.e.csliCount==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLuckyItem$csliCount!=null">${csLuckyItem$csliCount}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLuckyItem.csliCount" id="csliCount"  value="${csLuckyItem.csliCount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入抽奖奖项的投放数量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****投放数量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csliCount">${csLuckyItem.csliCount}</textarea>
		 		<span>
		 	
			 ${csLuckyItem.csliCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csliCount}
	</s:if>
	
	${lz:set("注释","*****************剩余数量字段的输入框代码*****************")}
	${lz:set("注释","before$csliRemain和after$csliRemain变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csliRemain==true">
	${before$csliRemain}
	<dl class="csliRemain " major  ref="csliRemain" >
		<dt>剩余数量:</dt>
		<s:if test="#request.CTRL.e.csliRemain==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLuckyItem$csliRemain!=null">${csLuckyItem$csliRemain}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLuckyItem.csliRemain" id="csliRemain"  value="${csLuckyItem.csliRemain}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入抽奖奖项的剩余数量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****剩余数量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csliRemain">${csLuckyItem.csliRemain}</textarea>
		 		<span>
		 	
			 ${csLuckyItem.csliRemain$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csliRemain}
	</s:if>
	
	${lz:set("注释","*****************中奖概率字段的输入框代码*****************")}
	${lz:set("注释","before$csliRatio和after$csliRatio变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csliRatio==true">
	${before$csliRatio}
	<dl class="csliRatio " major  ref="csliRatio" >
		<dt>中奖概率:</dt>
		<s:if test="#request.CTRL.e.csliRatio==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLuckyItem$csliRatio!=null">${csLuckyItem$csliRatio}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLuckyItem.csliRatio" id="csliRatio"  value="${csLuckyItem.csliRatio}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>0到1之间的小数，默认应为1</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****中奖概率字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csliRatio">${csLuckyItem.csliRatio}</textarea>
		 		<span>
		 	
			 ${csLuckyItem.csliRatio$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csliRatio}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csliUpdateTime和after$csliUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csliUpdateTime==true">
	${before$csliUpdateTime}
	<dl class="csliUpdateTime " major  ref="csliUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csliUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLuckyItem$csliUpdateTime!=null">${csLuckyItem$csliUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLuckyItem.csliUpdateTime" id="csliUpdateTime"  value="<s:date name="csLuckyItem.csliUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择抽奖奖项的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csliUpdateTime">${csLuckyItem.csliUpdateTime}</textarea>
		 		<span>
		 	
			 ${csLuckyItem.csliUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csliUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csliAddTime和after$csliAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csliAddTime==true">
	${before$csliAddTime}
	<dl class="csliAddTime " major  ref="csliAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csliAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLuckyItem$csliAddTime!=null">${csLuckyItem$csliAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLuckyItem.csliAddTime" id="csliAddTime"  value="<s:date name="csLuckyItem.csliAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择抽奖奖项的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csliAddTime">${csLuckyItem.csliAddTime}</textarea>
		 		<span>
		 	
			 ${csLuckyItem.csliAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csliAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csliStatus和after$csliStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csliStatus==true">
	${before$csliStatus}
	<dl class="csliStatus " major  ref="csliStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csliStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLuckyItem$csliStatus!=null">${csLuckyItem$csliStatus}</s:if><s:else>
		 	<select class="narrow" id="csliStatus" name="csLuckyItem.csliStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csLuckyItem.csliStatus==1?"selected":""}>正常</option>
				<option value="0" ${csLuckyItem.csliStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择抽奖奖项的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csliStatus">${csLuckyItem.csliStatus}</textarea>
		 		<span>
		 	
			 ${csLuckyItem.csliStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csliStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csLuckyItem.csliId}" name="csLuckyItem.csliId" id="csliId" />
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