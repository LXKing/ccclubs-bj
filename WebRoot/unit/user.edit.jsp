<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty controller.title?"企业管理员编辑":controller.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
		
	<!-- 感谢kindeditor出品的在线HTML编辑控件 -->
	<script type="text/javascript" charset="utf-8" src="${basePath}admin/js/kindeditor/kindeditor.js"></script>
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>

	<script type="text/javascript">
		window.basePath="${basePath}";
		window.contextPath="${basePath}${proname}/";
		window.apiUrl="${basePath}${proname}/api.do";
	</script>

</head>  
<body ${(controller.mutual=="page"||(controller.mutual!="dialog" && false))?"class='pagetype'":""}>

<script>

$(function(){	
	showTips("${tips.value}");
});

$(function(){
		 
	${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitUser.csuuId))}
	${lz:set("haveEditable",false)}
	$("#userForm").form({
		"":function(){}
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuId')==null)?canEditable:(lz:editable(controller,'csuuId')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitUser.csuuId":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuUsername')==null)?canEditable:(lz:editable(controller,'csuuUsername')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitUser.csuuUsername":function(el){
			if(jQuery.trim(el.value)=="")
				return "登录帐号不能为空";
			if(el.value.length>32)
				return "登录帐号最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("user.query.do",{object:true,csuuUsername:el.value});
				if(exists && exists.length>0){
					if(${csUnitUser.csuuId==null} || exists[0].object.csuuId!=$("#csuuId").val())
						return "登录帐号已存在";						
				}
			}
		}
		</s:if>	
		
		
		${lz:set("canEditable",lz:editableEx(controller,'csuuPassword',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuPassword":function(el){
			if(jQuery.trim(el.value)=="")
				return "登录密码不能为空";
			if(el.value.length>32)
				return "登录密码最大长度为32个字符";
		}
		</s:if>	
		
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuLimit')==null)?canEditable:(lz:editable(controller,'csuuLimit')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitUser.csuuLimit":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuRealname')==null)?canEditable:(lz:editable(controller,'csuuRealname')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitUser.csuuRealname":function(el){
			if(el.value.length>32)
				return "真实姓名最大长度为32个字符";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuMobile')==null)?canEditable:(lz:editable(controller,'csuuMobile')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitUser.csuuMobile":function(el){
			if(el.value.length>32)
				return "联系电话最大长度为32个字符";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuAddTime')==null)?canEditable:(lz:editable(controller,'csuuAddTime')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitUser.csuuAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuLastIp')==null)?canEditable:(lz:editable(controller,'csuuLastIp')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitUser.csuuLastIp":function(el){
			if(el.value.length>32)
				return "最后登录IP最大长度为32个字符";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuLastTime')==null)?canEditable:(lz:editable(controller,'csuuLastTime')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitUser.csuuLastTime":function(el){
			if(el.value.length>32)
				return "最后登录时间最大长度为32个字符";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuStatus')==null)?canEditable:(lz:editable(controller,'csuuStatus')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitUser.csuuStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
	});		
});
</script>

<form class="form editform" id="userForm" name="userForm" action="${empty controller.action?"user.save.do":controller.action}" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">	
	
		<s:if test="#request.haveEditable==true">
		<div class="prompt">
			温馨提示：请仔细填写企业管理员相关信息，<span class="extrude">"*" 为必填选项。</span>
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
		<input type="hidden" value="${ids}" name="ids" id="ids" />
		<input type="hidden" value="${lz:html(lz:json(controller))}" id="controller" name="controller" />
		
		${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitUser.csuuId))}		
		${lz:set("haveEditable",false)}
		${lz:set("havePrimary",false)}
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuId')==null)?canEditable:(lz:editable(controller,'csuuId')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuuId')==null)?canVisible:(lz:visible(controller,'csuuId')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && true)?"class='hidden'":""}  ref="csuuId" >
			<dt>编号:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			${lz:set("havePrimary",true)}
			<dd>
				<input type="hidden" value="${csUnitUser.csuuId}" name="csUnitUser.csuuId" id="csuuId" />
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitUser.csuuId}" id="csuuId" />
			 		<span>
				${lz:zerofill(csUnitUser.csuuId,6)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuUsername')==null)?canEditable:(lz:editable(controller,'csuuUsername')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuuUsername')==null)?canVisible:(lz:visible(controller,'csuuUsername')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuuUsername" >
			<dt>登录帐号:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitUser.csuuUsername" id="csuuUsername"  value="${csUnitUser.csuuUsername}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入企业管理员的登录帐号</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitUser.csuuUsername}" id="csuuUsername" />
			 		<span>
				 ${lz:html(csUnitUser.csuuUsername)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
		
		
		${lz:set("canEditable",lz:editableEx(controller,'csuuPassword',(true && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuuPassword',(true && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuuPassword" >
			<dt>登录密码:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd input="text">
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitUser.csuuPassword" id="csuuPassword"  value="${csUnitUser.csuuPassword}"/>
		 	 
		 	 <b>*</b>
		 	 <em>默认密码为用户名</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitUser.csuuPassword}" id="csuuPassword" />
			 		<span>
			 	
				 ${csUnitUser.csuuPassword$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
				
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuLimit')==null)?canEditable:(lz:editable(controller,'csuuLimit')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuuLimit')==null)?canVisible:(lz:visible(controller,'csuuLimit')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuuLimit" style="width:98%;">
			<dt>权限:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
				<div class='wide input'>	
				 	&nbsp; 	
				 	<input type="hidden" value="#0#" name="csUnitUser.csuuLimit" id="csuuLimit"/>
					<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,0) ? "checked='checked'" : ""} value="#0#" name="csUnitUser.csuuLimit" id="csuuLimit"/>订单管理&nbsp;&nbsp;&nbsp;</label>
					<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,1) ? "checked='checked'" : ""} value="#1#" name="csUnitUser.csuuLimit" id="csuuLimit"/>车辆管理&nbsp;&nbsp;&nbsp;</label>
					<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,2) ? "checked='checked'" : ""} value="#2#" name="csUnitUser.csuuLimit" id="csuuLimit"/>车辆监控&nbsp;&nbsp;&nbsp;</label>
					<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,3) ? "checked='checked'" : ""} value="#3#" name="csUnitUser.csuuLimit" id="csuuLimit"/>权限管理&nbsp;&nbsp;&nbsp;</label>
					<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,4) ? "checked='checked'" : ""} value="#4#" name="csUnitUser.csuuLimit" id="csuuLimit"/>价格管理&nbsp;&nbsp;&nbsp;</label>
					<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,5) ? "checked='checked'" : ""} value="#5#" name="csUnitUser.csuuLimit" id="csuuLimit"/>部门分组&nbsp;&nbsp;&nbsp;</label>
					<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,6) ? "checked='checked'" : ""} value="#6#" name="csUnitUser.csuuLimit" id="csuuLimit"/>用车帐号&nbsp;&nbsp;&nbsp;</label>
					<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,7) ? "checked='checked'" : ""} value="#7#" name="csUnitUser.csuuLimit" id="csuuLimit"/>对账单&nbsp;&nbsp;&nbsp;</label>
				 </div>
		 	 <em>请选择企业管理员的权限</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input wide">
			 		<input type="hidden" value="${csUnitUser.csuuLimit}" id="csuuLimit" />
			 		<span>
						${csUnitUser.csuuLimit$}&nbsp;&nbsp;&nbsp;
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuRealname')==null)?canEditable:(lz:editable(controller,'csuuRealname')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuuRealname')==null)?canVisible:(lz:visible(controller,'csuuRealname')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuuRealname" >
			<dt>真实姓名:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitUser.csuuRealname" id="csuuRealname"  value="${csUnitUser.csuuRealname}"/>
		 	 
		 	 
		 	 <em>请输入企业管理员的真实姓名</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitUser.csuuRealname}" id="csuuRealname" />
			 		<span>
				 ${lz:html(csUnitUser.csuuRealname)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuMobile')==null)?canEditable:(lz:editable(controller,'csuuMobile')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuuMobile')==null)?canVisible:(lz:visible(controller,'csuuMobile')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuuMobile" >
			<dt>联系电话:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitUser.csuuMobile" id="csuuMobile"  value="${csUnitUser.csuuMobile}"/>
		 	 
		 	 
		 	 <em>请输入企业管理员的联系电话</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitUser.csuuMobile}" id="csuuMobile" />
			 		<span>
				 ${lz:html(csUnitUser.csuuMobile)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuAddTime')==null)?canEditable:(lz:editable(controller,'csuuAddTime')))}
		
		${lz:set("canVisible",(false && isAddType)||(false && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuuAddTime')==null)?canVisible:(lz:visible(controller,'csuuAddTime')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuuAddTime" >
			<dt>添加时间:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitUser.csuuAddTime" id="csuuAddTime"  value="<s:date name="csUnitUser.csuuAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业管理员的添加时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitUser.csuuAddTime}" id="csuuAddTime" />
			 		<span>
			 	 <s:date name="csUnitUser.csuuAddTime" format="yyyy-MM-dd HH:mm:ss"/>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuLastIp')==null)?canEditable:(lz:editable(controller,'csuuLastIp')))}
		
		${lz:set("canVisible",(false && isAddType)||(false && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuuLastIp')==null)?canVisible:(lz:visible(controller,'csuuLastIp')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuuLastIp" >
			<dt>最后登录IP:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitUser.csuuLastIp" id="csuuLastIp"  value="${csUnitUser.csuuLastIp}"/>
		 	 
		 	 
		 	 <em>请输入企业管理员的最后登录IP</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitUser.csuuLastIp}" id="csuuLastIp" />
			 		<span>
				 ${lz:html(csUnitUser.csuuLastIp)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuLastTime')==null)?canEditable:(lz:editable(controller,'csuuLastTime')))}
		
		${lz:set("canVisible",(false && isAddType)||(false && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuuLastTime')==null)?canVisible:(lz:visible(controller,'csuuLastTime')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuuLastTime" >
			<dt>最后登录时间:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitUser.csuuLastTime" id="csuuLastTime"  value="${csUnitUser.csuuLastTime}"/>
		 	 
		 	 
		 	 <em>请输入企业管理员的最后登录时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitUser.csuuLastTime}" id="csuuLastTime" />
			 		<span>
				 ${lz:html(csUnitUser.csuuLastTime)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuStatus')==null)?canEditable:(lz:editable(controller,'csuuStatus')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuuStatus')==null)?canVisible:(lz:visible(controller,'csuuStatus')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuuStatus" >
			<dt>状态:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<select class="narrow" id="csuuStatus" name="csUnitUser.csuuStatus">
			 		<option value="">请选择</option>
					<option value="1" ${csUnitUser.csuuStatus==1?"selected":""}>正常</option>
					<option value="0" ${csUnitUser.csuuStatus==0?"selected":""}>无效</option>
			 	</select>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业管理员的状态</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitUser.csuuStatus}" id="csuuStatus" />
			 		<span>
				 ${csUnitUser.csuuStatus==1?"正常":""}
				 ${csUnitUser.csuuStatus==0?"无效":""}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
	
		
		
		<div class="line"></div>
		<center>
			<input type="hidden" value="${csUnitUserToken}" name="csUnitUserToken" id="csUnitUserToken" />
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csUnitUser.csuuId}" name="csUnitUser.csuuId" id="csuuId" />
			</s:if>			
			<s:if test="#request.haveEditable==true">
				<s:if test="#request.controller.submits!=null">
					<s:iterator value="#request.controller.submits" id="submit" status="i">
			<button class="button" type="submit" name="submiter" value="${submit.value}">&nbsp;&nbsp;${submit.name}&nbsp;&nbsp;</button>
					</s:iterator>
				</s:if>
				<s:else>
			<button class="button" type="submit" name="submiter" value="完成">&nbsp;&nbsp;完成&nbsp;&nbsp;</button>
			<button class="button" type="submit" name="submiter" value="保存">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
				</s:else>
			<button class="button" type="reset">&nbsp;&nbsp;重置&nbsp;&nbsp;</button>
			</s:if>
			<s:else>
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;确定&nbsp;&nbsp;</button>
			</s:else>
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
		</center>
		
		
	</div>	
</div>
<div class="foot"></div>
	
</form>

<script>
	function cancel(){
		if(${controller.mutual=="dialog"})
			$.closeModalDialog();
		else if(${controller.mutual=="page"})
			window.location="${lz:xeh(entrypoint)}";
		else if(true)
			$.closeModalDialog();
		else
			window.location="${lz:xeh(entrypoint)}";
	}
	
	function href(url,params){
		if(${controller.mutual=="dialog"})
			top.$.showModalDialog({url:url},params,window);
		else if(${controller.mutual=="page"})
			$.submit(url,params,"_self");
		else if(true)
			top.$.showModalDialog({url:url},params,window);
		else
			$.submit(url,params,"_self");	
	}
</script>

</body>
</html>