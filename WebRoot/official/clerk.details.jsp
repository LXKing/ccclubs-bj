<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty controller.title?"人员信息编辑":controller.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="zhuangtian">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
		
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
window["lzFlashUrl"]="http://www.ccclubs.com/include/";
window["uploadUrl"]="http://www.ccclubs.com/upload.html?app=memberinfo_edit";
//window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";
$(function(){
		 
	${lz:set("isAddType",(lz:vacant(id))&&(empty csUnitPerson.csupId))}
	${lz:set("haveEditable",false)}
	$("#clerkForm").form({
		"":function(){}



		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitPerson.csupId":function(el){
		}
		</s:if>
 		
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitPerson.csupName":function(el){
			if(jQuery.trim(el.value)=="")
				return "姓名不能为空";
		}
		</s:if>	
		

		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitPerson.csupMobile":function(el){
			if(jQuery.trim(el.value)=="")
				return "手机号码不能为空";
		}
		</s:if>	
		
		
	
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitPerson.csupGroup":function(el){
			if(jQuery.trim(el.value)=="")
				return "部门不能为空";
		}
		</s:if>	
		
		
			 		
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitPerson.csupStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
	});		
});
</script>

<form class="form editform" id="clerkForm" name="clerkForm" action="clerk.save.do" method="post">

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
				
		
		<dl ${(canEditable && true)?"class='hidden'":""}  ref="csupId" >
			<dt>编号:</dt>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitPerson.csupId}" id="csupId" />
			 		<span>
				${lz:zerofill(csUnitPerson.csupId,6)}		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
		</dl>
				

		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupName" >
			<dt>公务员编号:</dt>		 
			<dd>
			<div class="state-input narrow">
				<input type="hidden" value="${csUnitPerson.csupName}" id="csupName" />
			 		<span>
				${csUnitPerson.csupName}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 </div>
			</dd>
		</dl>


		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csmName" >
			<dt>姓名:</dt>				 
			<dd>
			<div class="state-input narrow">
				<input type="hidden" value="${csMember.csmName}" id="csmName" />
			 		<span>
				${csMember.csmName}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 </div>
			</dd>
		</dl>
		
		

		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupGroup" >
			<dt>部门:</dt>
			<dd>
			<div class="state-input narrow">
				<input type="hidden" value="${csUnitPerson.csupGroup}" id="csupGroup" />
			 		<span>
				${csUnitPerson.csupGroup$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 </div>
			 	<%-- <select class="narrow" id="csupGroup" name="csUnitPerson.csupGroup">			 					 					 		
			 		<s:iterator value="#request.csUnitGroups" id="item" status="csUnitGroup">
			 		<option value="${item.csugId}" ${csUnitPerson.csupGroup==item.csugId?"selected":""}>${item.csugName}</option>
					</s:iterator>					
			 	</select> --%>
			</dd>
		</dl>
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csmsPayer" >
			<dt>支付账号:</dt>
			<dd>
			 	<select  class="narrow" id="csupGroup" name="csMemberShip.csmsPayer">			 					 					 		
			 		<s:iterator value="#request.payMembers" id="item" status="csmsPayer">
			 		<option value="${item.csmId}" ${csMemberShip.csmsPayer==item.csmId?"selected":""}>${item.csmName}</option>
					</s:iterator>					
			 	</select>
			</dd>
		</dl>
		
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMember.csmMobile" >
			<dt>手机号码:</dt>			 
			<dd>
			<div class="state-input narrow">
				<input type="hidden" value="${csMember.csmMobile}" id="csmMobile" />
			 		<span>
				${csMember.csmMobile}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 </div>
			</dd>			
		</dl>
		
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMemberInfo.csmiSex" >
			<dt>性别:</dt>			 
			<dd>
			<div class="state-input narrow">
				<input type="hidden" value="${csMemberInfo.csmiSex}" id="csmiSex" />
			 		<span>
				${csMemberInfo.csmiSex$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 </div>
			 	<%-- <select class="narrow" id="csupStatus" name="csMemberInfo.csmiSex">
			 		<option value="">请选择</option>
					<option value="1" ${csMemberInfo.csmiSex==1?"selected":""}>男</option>
					<option value="0" ${csMemberInfo.csmiSex==0?"selected":""}>女</option>
			 	</select> --%>
			</dd>			
		</dl>
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMemberInfo.csmiCertifyType" >
			<dt>证件类型:</dt>			 
			<dd>
			<div class="state-input narrow">
				<input type="hidden" value="${csMemberInfo.csmiCertifyType}" id="csmiCertifyType" />
			 		<span>
				${csMemberInfo.csmiCertifyType$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 </div>
			 	<%-- <select class="narrow" id="csmiCertifyType" name="csMemberInfo.csmiCertifyType">
			 		<option value="">请选择</option>
					<option value="1" ${csUnitPerson.$csupMember.$csmInfo.csmiCertifyType$==1?"selected":""}>身份证</option>
					<option value="5" ${csUnitPerson.$csupMember.$csmInfo.csmiCertifyType$==5?"selected":""}>港澳台通行证</option>
					<option value="7" ${csUnitPerson.$csupMember.$csmInfo.csmiCertifyType$==7?"selected":""}>公务员证</option>
			 	</select>	 --%>
			</dd>			
		</dl>
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMemberInfo.csmiCertifyNum" >
			<dt>证件号码:</dt>					 
			<dd>
			<div class="state-input narrow">
				<input type="hidden" value="${csMemberInfo.csmiCertifyNum}" id="csmiCertifyNum" />
			 		<span>
				${csMemberInfo.csmiCertifyNum}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 </div>		 	
			</dd>			
		</dl>
		
		<dl></dl>
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMemberInfo.csmiCertifyImage" style="width:100%">
			<dt>证件图片:</dt>					 
			<dd><s:property value="url"/>
			 	<!-- <input type="text" class="input narrow"  maxlength="128" size="32" name="csUnitPerson.$csupMember.$csmInfo.csmiCertifyImage" id="csmiCertifyImage"  value="${csUnitPerson.$csupMember.$csmInfo.csmiCertifyImage}"/> -->		 	
		 		<input type="text" class="input narrow" readonly="readonly"  maxlength="128" size="32" name="csMemberInfo.csmiCertifyImage" id="csmiCertifyImage"  value="${csMemberInfo.csmiCertifyImage}"/>
		 		<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csmiCertifyImage').val(url)}})" class="button">设置图片</button>
				<button type="button" onclick="$('#csmiCertifyImage').val('')" class="button">删除图片</button>
				<button type="button" onclick="$.thumb({url:$('#csmiCertifyImage').val()})" class="button">查看图片</button>
			</dd>			
		</dl>
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMemberInfo.csmiDriverNum" >
			<dt>驾照号码:</dt>		 
			<dd>
			<div class="state-input narrow">
				<input type="hidden" value="${csMemberInfo.csmiDriverNum}" id="csmiDriverNum" />
			 		<span>
				${csMemberInfo.csmiDriverNum}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 </div>			 			 	
			</dd>			
		</dl>
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMemberInfo.csmiDriverNum" style="width:100%">
			<dt>驾照图片:</dt>			 			 
			<dd>
			 	<input type="text" class="input narrow" readonly="readonly"  maxlength="32" name="csMemberInfo.csmiDriverImage" id="csmiDriverImage"  value="${csMemberInfo.csmiDriverImage}"/>
			 	<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csmiDriverImage').val(url)}})" class="button">设置图片</button>
				<button type="button" onclick="$('#csmiDriverImage').val('')" class="button">删除图片</button>
				<button type="button" onclick="$.thumb({url:$('#csmiDriverImage').val()})" class="button">查看图片</button>	 	
			</dd>			
		</dl>
		
		
		<dl>
			<dt>添加时间:</dt>	
			<dd>
			<div class="state-input narrow">
				<input type="hidden" value="${csUnitPerson.csupAddTime}" id="csupAddTime" />
			 		<span>
				${csUnitPerson.csupAddTime$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 </div>		
			</dd>
		</dl>	
		
		
		<dl>
			<dt>更新时间:</dt>	
			<dd>
			<div class="state-input narrow">
				<input type="hidden" value="${csUnitPerson.csupUpdateTime}" id="csupUpdateTime" />
			 		<span>
				${csUnitPerson.csupUpdateTime$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 </div>		
			</dd>
		</dl>
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupFlag" >
			<dt>叫车权限:</dt>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitPerson.csupFlag}" id="csupFlag" />
			 		<span>
				<s:generator separator="," val="csUnitPerson.csupFlag" id="flags">
					<s:iterator value="#flags" id="flag">
						<s:if test="#flag==1">[叫车]</s:if>
						<s:if test="#flag==2">[约租车]</s:if>
					</s:iterator>
				</s:generator>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
		</dl>
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupFlag" >
			<dt>公务出行审批权限:</dt>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitPerson.csupFlag}" id="csupFlag" />
			 		<span>
				<s:generator separator="," val="csUnitPerson.csupFlag" id="flags">
					<s:iterator value="#flags" id="flag">
						<s:if test="#flag==3">[无需审批]</s:if>
						<s:if test="#flag==4">[需要审批]</s:if>
					</s:iterator>
				</s:generator>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
		</dl>
			
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupStatus" >
			<dt>状态:</dt>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitPerson.csupStatus}" id="csupStatus" />
			 		<span>
				 ${csUnitPerson.csupStatus==1?"正常":""}
				 ${csUnitPerson.csupStatus==0?"无效":""}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
		</dl>
		
	
		
		
		<div class="line"></div>
		<center>
			<input type="hidden" value="${csUnitUserToken}" name="csUnitUserToken" id="csUnitUserToken" />
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csUnitPerson.csupId}" name="csUnitPerson.csupId" id="csupId" />
			</s:if>			
			<s:if test="#request.haveEditable==true">
				<s:if test="#request.controller.submits!=null">
					<s:iterator value="#request.controller.submits" id="submit" status="i">
			<button class="button" type="submit" name="submiter" value="${submit.value}">&nbsp;&nbsp;${submit.name}&nbsp;&nbsp;</button>
					</s:iterator>
				</s:if>
			</s:if>
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;确定&nbsp;&nbsp;</button>

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