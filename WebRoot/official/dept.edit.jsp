<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty controller.title?"企业部门编辑":controller.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="zhuangtian">
	
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

</head>  
<body ${(controller.mutual=="page"||(controller.mutual!="dialog" && false))?"class='pagetype'":""}>

<script>

$(function(){	
	showTips("${tips.value}");
});

$(function(){
		 
	${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitGroup.csugId))}
	${lz:set("haveEditable",false)}
	$("#deptForm").form({
		"":function(){}
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csugId')==null)?canEditable:(lz:editable(controller,'csugId')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitGroup.csugId":function(el){
		}
		</s:if>
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csugName')==null)?canEditable:(lz:editable(controller,'csugName')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitGroup.csugName":function(el){
			if(jQuery.trim(el.value)=="")
				return "部门名称不能为空";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuuStatus')==null)?canEditable:(lz:editable(controller,'csuuStatus')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitGroup.csugStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
	});		
});
</script>

<form class="form editform" id="deptForm" name="deptForm" action="dept.save.do" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">	
	
		
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
		<input type="hidden" value="${id}" name="id" id="id" />
		<input type="hidden" value="${lz:html(lz:json(controller))}" id="controller" name="controller" />
		
		${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitGroup.csugId))}		
		${lz:set("haveEditable",false)}
		${lz:set("havePrimary",false)}
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csugId')==null)?canEditable:(lz:editable(controller,'csugId')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csugId')==null)?canVisible:(lz:visible(controller,'csugId')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && true)?"class='hidden'":""}  ref="csugId" >
			<dt>编号:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			${lz:set("havePrimary",true)}
			<dd>
				<input type="hidden" value="${csUnitUser.csuuId}" name="csUnitGroup.csugId" id="csugId" />
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitGroup.csugId}" id="csugId" />
			 		<span>
				${lz:zerofill(csUnitGroup.csugId,6)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>

		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csugName" >
			<dt>部门名称:</dt>	
		 	${lz:set("haveEditable",true)}					 
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitGroup.csugName" id="csugName"  value="${csUnitGroup.csugName}"/>
		 	 <b>*</b>
		 	<s:if test="#request.canEditable==true">
		 	 <em>请输入企业部门名称</em>
		 	 </s:if>
			</dd>
		</dl>
		</s:if>

		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csugPerson" >
			<dt>部门负责人:</dt>	
		 	${lz:set("haveEditable",true)}					 
			<dd>
		 	 <select class="narrow" id="csugPerson" name="csUnitGroup.csugPerson">
		 	 		<option value="">请选择</option>
			 		<s:iterator value="#request.csUnitPersons" id="item" status="csUnitPerson">
			 		<option value="${item.csupId}" ${csUnitGroup.csugPerson==item.csupId?"selected":""}>${item.$csupMember.csmName}-${item.$csupMember.csmMobile}</option>
					</s:iterator>
			 </select>
		 	<s:if test="#request.canEditable==true">
		 	 <em>请输入企业部门负责人</em>
		 	 </s:if>
			</dd>
		</dl>
		</s:if>
				
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csugStatus" >
			<dt>状态:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<select class="narrow" id="csugStatus" name="csUnitGroup.csugStatus">
			 		<option value="">请选择</option>
					<option value="1" ${csUnitGroup.csugStatus==1?"selected":""}>正常</option>
					<option value="0" ${csUnitGroup.csugStatus==0?"selected":""}>无效</option>
			 	</select>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业部门的状态</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitGroup.csugStatus}" id="csugStatus" />
			 		<span>
				 ${csUnitGroup.csugStatus==1?"正常":""}
				 ${csUnitGroup.csugStatus==0?"无效":""}
		 	  
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
			<input type="hidden" value="${csUnitGroup.csugId}" name="csUnitGroup.csugId" id="csugId" />
			</s:if>			
			<s:if test="#request.haveEditable==true">
				<s:if test="#request.controller.submits!=null">
					<s:iterator value="#request.controller.submits" id="submit" status="i">
			<button class="button" type="submit" name="submiter" value="${submit.value}">&nbsp;&nbsp;${submit.name}&nbsp;&nbsp;</button>
					</s:iterator>
				</s:if>
				<s:else>
			<button class="button" type="submit" name="submiter" value="完成">&nbsp;&nbsp;完成&nbsp;&nbsp;</button>
			<!-- <button class="button" type="submit" name="submiter" value="保存">&nbsp;&nbsp;保存&nbsp;&nbsp;</button> -->
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
/* 		if(${controller.mutual=="dialog"})
			top.$.showModalDialog({url:url},params,window);
		else if(${controller.mutual=="page"})
			$.submit(url,params,"_self");
		else if(true)
			top.$.showModalDialog({url:url},params,window);
		else */
			$.submit(url,params,"_self");	
	}
</script>

</body>
</html>