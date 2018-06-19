<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/freehour.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/freehour.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csFreeHour.csfhId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csfhId,csfhHost,csfhMember,csfhCount,csfhValidity,records,csfhRemark",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csfhId,csfhHost,csfhMember,csfhCount,csfhValidity,records,csfhRemark",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csfhId,csfhHost,csfhMember,csfhRemain,records,csfhEnd,csfhRemark,csfhStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csfhId,csfhHost,csfhMember,csfhRemain,records,csfhEnd,csfhRemark,csfhStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"免费小时编辑":CTRL.title}</title>
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
<s:if test="#request.CTRL.title=='批量充小时'">
	<lz:set name="jsonString">
		{
			title:"批量充小时",
			action:"freehour_batch.do",
			editable:false,
			visible:false,
			fields:{
				csfhId:{visible:false}
				,csfhCount:{editable:true,visible:true}
				,csfhValidity:{editable:true,visible:true}
				,csfhRemark:{editable:true,visible:true}
			}
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
	<lz:set name="fileHtml">
		<dl>
			<dt>2003格式excel:</dt>
			<dd>
			 	<input type="file" name="batch" id="batch" /></br>
		 	 	<b>*</b>
		 		<em>第一列为会员ID,第二列为手机号</em>
			</dd>
		</dl>
	</lz:set>
	<script>
	$(function(){
		$("${lz:js(fileHtml)}").insertBefore("dl[ref=csfhCount]");
		$(".form").attr("enctype","multipart/form-data");
	});
	</script>
</s:if>
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
	$("#freehourForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csfhId==true">
		${lz:set("haveEditable",true)}
		,"csFreeHour.csfhId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csfhHost==true">
		${lz:set("haveEditable",true)}
		,"csFreeHour.csfhHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csfhMember==true">
		${lz:set("haveEditable",true)}
		,"csFreeHour.csfhMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csfhCount==true">
		${lz:set("haveEditable",true)}
		,"csFreeHour.csfhCount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "充值小时输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csfhRemain==true">
		${lz:set("haveEditable",true)}
		,"csFreeHour.csfhRemain":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "可用小时输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csfhValidity==true">
		${lz:set("haveEditable",true)}
		,"csFreeHour.csfhValidity":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csfhEnd==true">
		${lz:set("haveEditable",true)}
		,"csFreeHour.csfhEnd":function(el){
			if(jQuery.trim(el.value)=="")
				return "结束时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csfhEditor==true">
		${lz:set("haveEditable",true)}
		,"csFreeHour.csfhEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csfhUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csFreeHour.csfhUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csfhAddTime==true">
		${lz:set("haveEditable",true)}
		,"csFreeHour.csfhAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csfhRemark==true">
		${lz:set("haveEditable",true)}
		,"csFreeHour.csfhRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csfhStatus==true">
		${lz:set("haveEditable",true)}
		,"csFreeHour.csfhStatus":function(el){
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
	<form class="form editform" ref="CsFreeHour" id="freehourForm" name="freehourForm" action="${empty CTRL.action?"freehour_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写免费小时相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csFreeHourToken}" name="csFreeHourToken" id="csFreeHourToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csFreeHour.csfhId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csfhId和after$csfhId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csfhId==true">
	${before$csfhId}
	<dl class="csfhId ${CTRL.e.csfhId?"hidden":""}" major  ref="csfhId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csfhId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csFreeHour$csfhId!=null">${csFreeHour$csfhId}</s:if><s:else>
			<input type="hidden" value="${csFreeHour.csfhId}" name="csFreeHour.csfhId" id="csfhId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csfhId">${csFreeHour.csfhId}</textarea>
		 		<span>
		 	
			 ${csFreeHour.csfhId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csfhId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csfhHost和after$csfhHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csfhHost==true">
	${before$csfhHost}
	<dl class="csfhHost " major  ref="csfhHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csfhHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csFreeHour$csfhHost!=null">${csFreeHour$csfhHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csfhHost" name="csFreeHour.csfhHost">
		 				<option selected value="${csFreeHour.csfhHost}">
		 					${get:SrvHost(csFreeHour.csfhHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csfhHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csfhHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csfhHost">${csFreeHour.csfhHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csFreeHour.csfhHost}',{ctrl:{editable:false,visible:true}})">
			 ${csFreeHour.csfhHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csfhHost}
	</s:if>
	
	${lz:set("注释","*****************所属会员字段的输入框代码*****************")}
	${lz:set("注释","before$csfhMember和after$csfhMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csfhMember==true">
	${before$csfhMember}
	<dl class="csfhMember " major  ref="csfhMember" >
		<dt>所属会员:</dt>
		<s:if test="#request.CTRL.e.csfhMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csFreeHour$csfhMember!=null">${csFreeHour$csfhMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csfhHost}" type="text" id="csfhMember" name="csFreeHour.csfhMember" text="${get:CsMember(csFreeHour.csfhMember).csmName}" value="${csFreeHour.csfhMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csfhMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csfhMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csfhMember">${csFreeHour.csfhMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csFreeHour.csfhMember}',{ctrl:{editable:false,visible:true}})">
			 ${csFreeHour.csfhMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csfhMember}
	</s:if>
	
	${lz:set("注释","*****************充值小时字段的输入框代码*****************")}
	${lz:set("注释","before$csfhCount和after$csfhCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csfhCount==true">
	${before$csfhCount}
	<dl class="csfhCount " major  ref="csfhCount" >
		<dt>充值小时:</dt>
		<s:if test="#request.CTRL.e.csfhCount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csFreeHour$csfhCount!=null">${csFreeHour$csfhCount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csFreeHour.csfhCount" id="csfhCount"  value="${csFreeHour.csfhCount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入免费小时的充值小时</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****充值小时字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csfhCount">${csFreeHour.csfhCount}</textarea>
		 		<span>
		 	
			 ${csFreeHour.csfhCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csfhCount}
	</s:if>
	
	${lz:set("注释","*****************可用小时字段的输入框代码*****************")}
	${lz:set("注释","before$csfhRemain和after$csfhRemain变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csfhRemain==true">
	${before$csfhRemain}
	<dl class="csfhRemain " major  ref="csfhRemain" >
		<dt>可用小时:</dt>
		<s:if test="#request.CTRL.e.csfhRemain==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csFreeHour$csfhRemain!=null">${csFreeHour$csfhRemain}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csFreeHour.csfhRemain" id="csfhRemain"  value="${csFreeHour.csfhRemain}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入免费小时的可用小时</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****可用小时字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csfhRemain">${csFreeHour.csfhRemain}</textarea>
		 		<span>
		 	
			 ${csFreeHour.csfhRemain$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csfhRemain}
	</s:if>
	
	${lz:set("注释","*****************有效期字段的输入框代码*****************")}
	${lz:set("注释","before$csfhValidity和after$csfhValidity变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csfhValidity==true">
	${before$csfhValidity}
	<dl class="csfhValidity " major  ref="csfhValidity" style="width:98%;">
		<dt>有&nbsp;&nbsp;效&nbsp;&nbsp;期:</dt>
		<s:if test="#request.CTRL.e.csfhValidity==true">
		${lz:set("haveEditable",true)}
		<dd input="radio">
		<s:if test="#request.csFreeHour$csfhValidity!=null">${csFreeHour$csfhValidity}</s:if><s:else>
		 	<div class='wide input'>
		 	&nbsp;
			<label>
				<input type="radio" value="1" name="csFreeHour.csfhValidity" id="csfhValidity" ${csFreeHour.csfhValidity=='1'?"checked":""}/>1个月&nbsp;&nbsp;&nbsp;
			</label>
			<label>
				<input type="radio" value="2" name="csFreeHour.csfhValidity" id="csfhValidity" ${csFreeHour.csfhValidity=='2'?"checked":""}/>2个月&nbsp;&nbsp;&nbsp;
			</label>
			<label>
				<input type="radio" value="3" name="csFreeHour.csfhValidity" id="csfhValidity" ${csFreeHour.csfhValidity=='3'?"checked":""}/>3个月&nbsp;&nbsp;&nbsp;
			</label>
			<label>
				<input type="radio" value="6" name="csFreeHour.csfhValidity" id="csfhValidity" ${csFreeHour.csfhValidity=='6'?"checked":""}/>半年&nbsp;&nbsp;&nbsp;
			</label>
			<label>
				<input type="radio" value="12" name="csFreeHour.csfhValidity" id="csfhValidity" ${csFreeHour.csfhValidity=='12'?"checked":""}/>1年&nbsp;&nbsp;&nbsp;
			</label>
		 	</div>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择免费小时的有效期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****有效期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csfhValidity">${csFreeHour.csfhValidity}</textarea>
		 		<span>
		 	
			 ${csFreeHour.csfhValidity$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csfhValidity}
	</s:if>
	
	${lz:set("注释","*****************使用记录字段的输入框代码*****************")}
	${lz:set("注释","before$records和after$records变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.records==true">
	${before$records}
	<dl class="records " minor  ref="records" style="width:98%;">
		<dt>使用记录:</dt>
		<s:if test="#request.CTRL.e.records==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csFreeHour$records!=null">${csFreeHour$records}</s:if><s:else>
		  		<s:if test="csFreeHour.csfhId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="records" name="records" frameborder='0' src="" url="${basePath}${proname}/user/userecord.do?inFrame=true&canQuery=false&fields=csurOrder,csurCount,csurAddTime&csurFreeHour=${csFreeHour.csfhId}&ctrl={queryable:false,fields:{csurFreeHour:{visible:true,editable:false,defaultValue:${csFreeHour.csfhId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#records').attr('src',$('#records').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑使用记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csFreeHour.csfhId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="records" name="records" frameborder='0' src="" url="${basePath}${proname}/user/userecord.do?inFrame=true&canQuery=false&fields=csurOrder,csurCount,csurAddTime&csurFreeHour=${csFreeHour.csfhId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#records').attr('src',$('#records').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$records}
	</s:if>
	
	${lz:set("注释","*****************结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csfhEnd和after$csfhEnd变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csfhEnd==true">
	${before$csfhEnd}
	<dl class="csfhEnd " major  ref="csfhEnd" >
		<dt>结束时间:</dt>
		<s:if test="#request.CTRL.e.csfhEnd==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csFreeHour$csfhEnd!=null">${csFreeHour$csfhEnd}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csFreeHour.csfhEnd" id="csfhEnd"  value="<s:date name="csFreeHour.csfhEnd" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择免费小时的结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csfhEnd">${csFreeHour.csfhEnd}</textarea>
		 		<span>
		 	
			 ${csFreeHour.csfhEnd$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csfhEnd}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$csfhEditor和after$csfhEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csfhEditor==true">
	${before$csfhEditor}
	<dl class="csfhEditor " major  ref="csfhEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csfhEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csFreeHour$csfhEditor!=null">${csFreeHour$csfhEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csfhEditor" name="csFreeHour.csfhEditor">
		 				<option selected value="${csFreeHour.csfhEditor}">
		 					${get:SrvUser(csFreeHour.csfhEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csfhEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csfhEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csfhEditor">${csFreeHour.csfhEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csFreeHour.csfhEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csFreeHour.csfhEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csfhEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csfhUpdateTime和after$csfhUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csfhUpdateTime==true">
	${before$csfhUpdateTime}
	<dl class="csfhUpdateTime " major  ref="csfhUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csfhUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csFreeHour$csfhUpdateTime!=null">${csFreeHour$csfhUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csFreeHour.csfhUpdateTime" id="csfhUpdateTime"  value="<s:date name="csFreeHour.csfhUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择免费小时的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csfhUpdateTime">${csFreeHour.csfhUpdateTime}</textarea>
		 		<span>
		 	
			 ${csFreeHour.csfhUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csfhUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csfhAddTime和after$csfhAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csfhAddTime==true">
	${before$csfhAddTime}
	<dl class="csfhAddTime " major  ref="csfhAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csfhAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csFreeHour$csfhAddTime!=null">${csFreeHour$csfhAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csFreeHour.csfhAddTime" id="csfhAddTime"  value="<s:date name="csFreeHour.csfhAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择免费小时的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csfhAddTime">${csFreeHour.csfhAddTime}</textarea>
		 		<span>
		 	
			 ${csFreeHour.csfhAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csfhAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csfhRemark和after$csfhRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csfhRemark==true">
	${before$csfhRemark}
	<dl class="csfhRemark " minor  ref="csfhRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csfhRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csFreeHour$csfhRemark!=null">${csFreeHour$csfhRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csfhRemark" name="csFreeHour.csfhRemark" rows="5">${csFreeHour.csfhRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入免费小时的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csFreeHour.csfhRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csfhRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csfhStatus和after$csfhStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csfhStatus==true">
	${before$csfhStatus}
	<dl class="csfhStatus " major  ref="csfhStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csfhStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csFreeHour$csfhStatus!=null">${csFreeHour$csfhStatus}</s:if><s:else>
		 	<select class="narrow" id="csfhStatus" name="csFreeHour.csfhStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csFreeHour.csfhStatus==1?"selected":""}>正常</option>
				<option value="2" ${csFreeHour.csfhStatus==2?"selected":""}>已过期</option>
				<option value="3" ${csFreeHour.csfhStatus==3?"selected":""}>用完</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择免费小时的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csfhStatus">${csFreeHour.csfhStatus}</textarea>
		 		<span>
		 	
			 ${csFreeHour.csfhStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csfhStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csFreeHour.csfhId}" name="csFreeHour.csfhId" id="csfhId" />
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