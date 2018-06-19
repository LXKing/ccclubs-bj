<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/lucky/luckyresult.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/lucky/luckyresult.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csLuckyResult.cslrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cslrId,cslrName,cslrMember,cslrLucky,cslrLuckyItem,cslrItemType,cslrItemIndex,cslrAmount,cslrFrom,cslrIssueTime,cslrRemark,cslrState,cslrStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cslrId,cslrName,cslrMember,cslrLucky,cslrLuckyItem,cslrItemType,cslrItemIndex,cslrAmount,cslrFrom,cslrIssueTime,cslrRemark,cslrState,cslrStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cslrId,cslrName,cslrMember,cslrLucky,cslrLuckyItem,cslrItemType,cslrItemIndex,cslrAmount,cslrFrom,cslrIssueTime,cslrRemark,cslrState,cslrStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cslrId,cslrName,cslrMember,cslrLucky,cslrLuckyItem,cslrItemType,cslrItemIndex,cslrAmount,cslrFrom,cslrIssueTime,cslrRemark,cslrState,cslrStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"中奖结果编辑":CTRL.title}</title>
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
	$("#luckyresultForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cslrId==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrName==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrName":function(el){
			if(jQuery.trim(el.value)=="")
				return "中奖标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "中奖标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrMember==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrMember":function(el){
			if(jQuery.trim(el.value)=="")
				return "中奖会员不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^([0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的编号";				
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrLucky==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrLucky":function(el){
			if(jQuery.trim(el.value)=="")
				return "所属活动不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^([0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的编号";				
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrLuckyItem==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrLuckyItem":function(el){
			if(jQuery.trim(el.value)=="")
				return "所属奖项不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^([0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的编号";				
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrItemType==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrItemType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择奖项类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrItemIndex==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrItemIndex":function(el){
			if(jQuery.trim(el.value)=="")
				return "奖项等级不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrAmount==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrAmount":function(el){
			if(jQuery.trim(el.value)=="")
				return "中奖数额不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrFrom==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrFrom":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择抽奖来源";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrIssueTime==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrIssueTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrAddTime==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrRemark==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrRemark":function(el){
			if(jQuery.trim(el.value)=="")
				return "备注不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrState==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrState":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择是否已收到";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslrStatus==true">
		${lz:set("haveEditable",true)}
		,"csLuckyResult.cslrStatus":function(el){
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
	<form class="form editform" ref="CsLuckyResult" id="luckyresultForm" name="luckyresultForm" action="${empty CTRL.action?"luckyresult_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写中奖结果相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csLuckyResultToken}" name="csLuckyResultToken" id="csLuckyResultToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csLuckyResult.cslrId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cslrId和after$cslrId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrId==true">
	${before$cslrId}
	<dl class="cslrId ${CTRL.e.cslrId?"hidden":""}" major  ref="cslrId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cslrId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csLuckyResult$cslrId!=null">${csLuckyResult$cslrId}</s:if><s:else>
			<input type="hidden" value="${csLuckyResult.cslrId}" name="csLuckyResult.cslrId" id="cslrId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrId">${csLuckyResult.cslrId}</textarea>
		 		<span>
		 	
			 ${csLuckyResult.cslrId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrId}
	</s:if>
	
	${lz:set("注释","*****************中奖标题字段的输入框代码*****************")}
	${lz:set("注释","before$cslrName和after$cslrName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrName==true">
	${before$cslrName}
	<dl class="cslrName " major  ref="cslrName" >
		<dt>中奖标题:</dt>
		<s:if test="#request.CTRL.e.cslrName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLuckyResult$cslrName!=null">${csLuckyResult$cslrName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csLuckyResult.cslrName" id="cslrName"  value="${csLuckyResult.cslrName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入中奖结果的中奖标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****中奖标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrName">${csLuckyResult.cslrName}</textarea>
		 		<span>
		 	
			 ${csLuckyResult.cslrName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrName}
	</s:if>
	
	${lz:set("注释","*****************中奖会员字段的输入框代码*****************")}
	${lz:set("注释","before$cslrMember和after$cslrMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrMember==true">
	${before$cslrMember}
	<dl class="cslrMember " major  ref="cslrMember" >
		<dt>中奖会员:</dt>
		<s:if test="#request.CTRL.e.cslrMember==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLuckyResult$cslrMember!=null">${csLuckyResult$cslrMember}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLuckyResult.cslrMember" id="cslrMember"  value="${csLuckyResult.cslrMember}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入中奖结果的中奖会员</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****中奖会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrMember">${csLuckyResult.cslrMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csLuckyResult.cslrMember}',{ctrl:{editable:false,visible:true}})">
			 ${csLuckyResult.cslrMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrMember}
	</s:if>
	
	${lz:set("注释","*****************所属活动字段的输入框代码*****************")}
	${lz:set("注释","before$cslrLucky和after$cslrLucky变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrLucky==true">
	${before$cslrLucky}
	<dl class="cslrLucky " major  ref="cslrLucky" >
		<dt>所属活动:</dt>
		<s:if test="#request.CTRL.e.cslrLucky==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLuckyResult$cslrLucky!=null">${csLuckyResult$cslrLucky}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLuckyResult.cslrLucky" id="cslrLucky"  value="${csLuckyResult.cslrLucky}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入中奖结果的所属活动</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属活动字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrLucky">${csLuckyResult.cslrLucky}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/lucky/lucky_details.do?key=${csLuckyResult.cslrLucky}',{ctrl:{editable:false,visible:true}})">
			 ${csLuckyResult.cslrLucky$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrLucky}
	</s:if>
	
	${lz:set("注释","*****************所属奖项字段的输入框代码*****************")}
	${lz:set("注释","before$cslrLuckyItem和after$cslrLuckyItem变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrLuckyItem==true">
	${before$cslrLuckyItem}
	<dl class="cslrLuckyItem " major  ref="cslrLuckyItem" >
		<dt>所属奖项:</dt>
		<s:if test="#request.CTRL.e.cslrLuckyItem==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLuckyResult$cslrLuckyItem!=null">${csLuckyResult$cslrLuckyItem}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLuckyResult.cslrLuckyItem" id="cslrLuckyItem"  value="${csLuckyResult.cslrLuckyItem}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入中奖结果的所属奖项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属奖项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrLuckyItem">${csLuckyResult.cslrLuckyItem}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/lucky/luckyitem_details.do?key=${csLuckyResult.cslrLuckyItem}',{ctrl:{editable:false,visible:true}})">
			 ${csLuckyResult.cslrLuckyItem$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrLuckyItem}
	</s:if>
	
	${lz:set("注释","*****************奖项类型字段的输入框代码*****************")}
	${lz:set("注释","before$cslrItemType和after$cslrItemType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrItemType==true">
	${before$cslrItemType}
	<dl class="cslrItemType " major  ref="cslrItemType" >
		<dt>奖项类型:</dt>
		<s:if test="#request.CTRL.e.cslrItemType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLuckyResult$cslrItemType!=null">${csLuckyResult$cslrItemType}</s:if><s:else>
		 	<select class="narrow" id="cslrItemType" name="csLuckyResult.cslrItemType">
		 		<option value="">请选择</option>
				<option value="0" ${csLuckyResult.cslrItemType==0?"selected":""}>谢谢参与</option>
				<option value="1" ${csLuckyResult.cslrItemType==1?"selected":""}>积分</option>
				<option value="2" ${csLuckyResult.cslrItemType==2?"selected":""}>现金券</option>
				<option value="3" ${csLuckyResult.cslrItemType==3?"selected":""}>免费小时</option>
				<option value="4" ${csLuckyResult.cslrItemType==4?"selected":""}>实物</option>
				<option value="5" ${csLuckyResult.cslrItemType==5?"selected":""}>其它方式</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>如果是免费小时和现金券则自动发放</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****奖项类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrItemType">${csLuckyResult.cslrItemType}</textarea>
		 		<span>
		 	
			 ${csLuckyResult.cslrItemType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrItemType}
	</s:if>
	
	${lz:set("注释","*****************奖项等级字段的输入框代码*****************")}
	${lz:set("注释","before$cslrItemIndex和after$cslrItemIndex变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrItemIndex==true">
	${before$cslrItemIndex}
	<dl class="cslrItemIndex " major  ref="cslrItemIndex" >
		<dt>奖项等级:</dt>
		<s:if test="#request.CTRL.e.cslrItemIndex==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLuckyResult$cslrItemIndex!=null">${csLuckyResult$cslrItemIndex}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLuckyResult.cslrItemIndex" id="cslrItemIndex"  value="${csLuckyResult.cslrItemIndex}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>技术参数，用来判断几等奖</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****奖项等级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrItemIndex">${csLuckyResult.cslrItemIndex}</textarea>
		 		<span>
		 	
			 ${csLuckyResult.cslrItemIndex$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrItemIndex}
	</s:if>
	
	${lz:set("注释","*****************中奖数额字段的输入框代码*****************")}
	${lz:set("注释","before$cslrAmount和after$cslrAmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrAmount==true">
	${before$cslrAmount}
	<dl class="cslrAmount " major  ref="cslrAmount" >
		<dt>中奖数额:</dt>
		<s:if test="#request.CTRL.e.cslrAmount==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLuckyResult$cslrAmount!=null">${csLuckyResult$cslrAmount}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLuckyResult.cslrAmount" id="cslrAmount"  value="${csLuckyResult.cslrAmount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>积分、免费小时、现金券为对应的数量，其它为1</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****中奖数额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrAmount">${csLuckyResult.cslrAmount}</textarea>
		 		<span>
		 	
			 ${csLuckyResult.cslrAmount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrAmount}
	</s:if>
	
	${lz:set("注释","*****************抽奖来源字段的输入框代码*****************")}
	${lz:set("注释","before$cslrFrom和after$cslrFrom变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrFrom==true">
	${before$cslrFrom}
	<dl class="cslrFrom " major  ref="cslrFrom" >
		<dt>抽奖来源:</dt>
		<s:if test="#request.CTRL.e.cslrFrom==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLuckyResult$cslrFrom!=null">${csLuckyResult$cslrFrom}</s:if><s:else>
		 	<select class="narrow" id="cslrFrom" name="csLuckyResult.cslrFrom">
		 		<option value="">请选择</option>
				<option value="0" ${csLuckyResult.cslrFrom==0?"selected":""}>网站</option>
				<option value="1" ${csLuckyResult.cslrFrom==1?"selected":""}>微信</option>
				<option value="2" ${csLuckyResult.cslrFrom==2?"selected":""}>app</option>
				<option value="3" ${csLuckyResult.cslrFrom==3?"selected":""}>其它</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择中奖结果的抽奖来源</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****抽奖来源字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrFrom">${csLuckyResult.cslrFrom}</textarea>
		 		<span>
		 	
			 ${csLuckyResult.cslrFrom$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrFrom}
	</s:if>
	
	${lz:set("注释","*****************奖品发放时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslrIssueTime和after$cslrIssueTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrIssueTime==true">
	${before$cslrIssueTime}
	<dl class="cslrIssueTime " minor  ref="cslrIssueTime" >
		<dt>奖品发放时间:</dt>
		<s:if test="#request.CTRL.e.cslrIssueTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLuckyResult$cslrIssueTime!=null">${csLuckyResult$cslrIssueTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLuckyResult.cslrIssueTime" id="cslrIssueTime"  value="<s:date name="csLuckyResult.cslrIssueTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择中奖结果的奖品发放时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****奖品发放时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrIssueTime">${csLuckyResult.cslrIssueTime}</textarea>
		 		<span>
		 	
			 ${csLuckyResult.cslrIssueTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrIssueTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslrUpdateTime和after$cslrUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrUpdateTime==true">
	${before$cslrUpdateTime}
	<dl class="cslrUpdateTime " major  ref="cslrUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cslrUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLuckyResult$cslrUpdateTime!=null">${csLuckyResult$cslrUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLuckyResult.cslrUpdateTime" id="cslrUpdateTime"  value="<s:date name="csLuckyResult.cslrUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择中奖结果的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrUpdateTime">${csLuckyResult.cslrUpdateTime}</textarea>
		 		<span>
		 	
			 ${csLuckyResult.cslrUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslrAddTime和after$cslrAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrAddTime==true">
	${before$cslrAddTime}
	<dl class="cslrAddTime " major  ref="cslrAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cslrAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLuckyResult$cslrAddTime!=null">${csLuckyResult$cslrAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLuckyResult.cslrAddTime" id="cslrAddTime"  value="<s:date name="csLuckyResult.cslrAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择中奖结果的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrAddTime">${csLuckyResult.cslrAddTime}</textarea>
		 		<span>
		 	
			 ${csLuckyResult.cslrAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$cslrRemark和after$cslrRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrRemark==true">
	${before$cslrRemark}
	<dl class="cslrRemark " major  ref="cslrRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.cslrRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLuckyResult$cslrRemark!=null">${csLuckyResult$cslrRemark}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csLuckyResult.cslrRemark" id="cslrRemark"  value="${csLuckyResult.cslrRemark}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入中奖结果的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cslrRemark">${csLuckyResult.cslrRemark}</textarea>
		 		<span>
		 	
			 ${csLuckyResult.cslrRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrRemark}
	</s:if>
	
	${lz:set("注释","*****************是否已收到字段的输入框代码*****************")}
	${lz:set("注释","before$cslrState和after$cslrState变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrState==true">
	${before$cslrState}
	<dl class="cslrState " major  ref="cslrState" >
		<dt>是否已收到:</dt>
		<s:if test="#request.CTRL.e.cslrState==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLuckyResult$cslrState!=null">${csLuckyResult$cslrState}</s:if><s:else>
		 	<select class="narrow" id="cslrState" name="csLuckyResult.cslrState">
		 		<option value="">请选择</option>
				<option value="0" ${csLuckyResult.cslrState==0?"selected":""}>未收到</option>
				<option value="1" ${csLuckyResult.cslrState==1?"selected":""}>已收到</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择中奖结果的是否已收到</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****是否已收到字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrState">${csLuckyResult.cslrState}</textarea>
		 		<span>
		 	
			 ${csLuckyResult.cslrState$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrState}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cslrStatus和after$cslrStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslrStatus==true">
	${before$cslrStatus}
	<dl class="cslrStatus " major  ref="cslrStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cslrStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLuckyResult$cslrStatus!=null">${csLuckyResult$cslrStatus}</s:if><s:else>
		 	<select class="narrow" id="cslrStatus" name="csLuckyResult.cslrStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csLuckyResult.cslrStatus==0?"selected":""}>未领取</option>
				<option value="1" ${csLuckyResult.cslrStatus==1?"selected":""}>已发放</option>
				<option value="2" ${csLuckyResult.cslrStatus==2?"selected":""}>弃奖</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择中奖结果的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslrStatus">${csLuckyResult.cslrStatus}</textarea>
		 		<span>
		 	
			 ${csLuckyResult.cslrStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslrStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csLuckyResult.cslrId}" name="csLuckyResult.cslrId" id="cslrId" />
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