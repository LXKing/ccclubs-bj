<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>用车人员编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		window.basePath="${basePath}";
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

<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#personForm").form({
		"":function(){}
	},function(){
	});		
});
</script>


	<form class="form editform" ref="CsUnitPerson" id="personForm" name="personForm" action="${empty CTRL.action?"person_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写用车人员相关信息，<span class="extrude">"*" 为必填选项。</span>			
					<input type="checkbox" ${empty edittype?"checked='checked'":""} id="all"/>显示全部可编辑项
				</div>
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
				<input type="hidden" value="${csUnitPersonToken}" name="csUnitPersonToken" id="csUnitPersonToken" />
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitPerson.csupId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
				
				<input type="hidden" value="${csMember.csmId}" name="csUnitPerson.csupMember" id="csupMember" />
	
				<dl class="" major  ref="csupGroup" >
					<dt>所属分组:</dt>
					<dd input="query">
						<select class="combox narrow" action="${basePath}${proname}/unit/group.query.do" size="16" type="text" id="csupGroup" name="csUnitPerson.csupGroup" value="">
			 				<option selected value="${csUnitPerson.csupGroup}">${get:CsUnitGroup(csUnitPerson.csupGroup).csugName}</option>
			 			</select>
					 	<b>*</b>
					 	<em></em>
					</dd>
				</dl>
				
				
				<dl class="" major  ref="csupMember" >
					<dt>付款帐号:</dt>
					<dd input="query">
						<get:CsMember name="pays" size="65535">{definex:"csm_id in (${unit_login.$csuuUnitInfo.csuiMember})"}</get:CsMember>
			 			<s:if test="#request.csMember.csmId!=null">
			 				<get:CsMemberShip name="ship">{csmsTargeter:${csMember.csmId},definex:"csms_payer in (${unit_login.$csuuUnitInfo.csuiMember})"}</get:CsMemberShip>
			 			</s:if>
			 			<select class="narrow" id="csmsPayer" name="csmsPayer">
			 				<option value="">--请选择--</option>
			 				<s:iterator value="#request.pays" id="pay" status="i">
			 				<option ${ship.csmsPayer==pay.csmId?"selected":""} value="${pay.csmId}">${pay.csmName}</option>
			 				</s:iterator>
			 			</select>
				 	 	<b>*</b>
				 	 	<em></em>
					</dd>
				</dl>
				
	
				<dl class="" major  ref="csupName" >
					<dt>真实姓名:</dt>
					<dd input="text">
						<s:if test="#request.csMember!=null">
							<div class="state-input narrow">
								<span>${csMember.csmName}</span>
							</div>
						</s:if>
						<s:else>
						 	<input type="text" class="input narrow"  maxlength="32" name="csmName" id="csmName"  value="${csMember.csmName}"/>
					 	 	<b>*</b>
					 	 	<em>请输入用车人员的姓名</em>
				 	 	</s:else>
					</dd>
				</dl>
				
				<dl class="" major>
					<dt>手机号码:</dt>
					<dd input="text">
						<s:if test="#request.csMember!=null">
							<div class="state-input narrow">
								<span>${csMember.csmMobile}</span>
							</div>
						</s:if>
						<s:else>
						 	<input type="text" class="input narrow"  maxlength="32" name="csmMobile" id="csmMobile"  value="${csMember.csmMobile}"/>
					 	 	<b>*</b>
					 	 	<em>请输入用车人员的手机号码</em>
					 	</s:else>
					</dd>
				</dl>
				
				
				
				<dl class=""  style="width:98%;">
					<dt>证件图片:</dt>
					<dd input="text">
					    <input type="text" class="input" maxlength="128" size="32" name="csmiCertifyImage" id="csmiCertifyImage"  value="${csMember.$csmInfo.csmiCertifyImage}"/>
						<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csmiCertifyImage').val(url)}})" class="button">设置图片</button>
						<button type="button" onclick="$('#csmiCertifyImage').val('')" class="button">删除图片</button>
						<button type="button" onclick="$.thumb({url:$('#csmiCertifyImage').val()})" class="button">查看图片</button>
				 	 	<b>*</b>
				 	 	<em>请输入用车人员的证件号码</em>
					</dd>
				</dl>
				
				
				<dl class="" major>
					<dt>证件类型:</dt>
					<dd input="text">
					 	<select class="narrow" id="csmiCertifyType" name="csmiCertifyType">
					 		<option value="">请选择</option>
							<option value="1" ${csMember.$csmInfo.csmiCertifyType==1?"selected":""}>身份证</option>
							<option value="2" ${csMember.$csmInfo.csmiCertifyType==2?"selected":""}>护照</option>
							<option value="3" ${csMember.$csmInfo.csmiCertifyType==3?"selected":""}>军官证</option>
							<option value="4" ${csMember.$csmInfo.csmiCertifyType==4?"selected":""}>台胞证</option>
							<option value="5" ${csMember.$csmInfo.csmiCertifyType==5?"selected":""}>港澳通行证</option>
							<option value="6" ${csMember.$csmInfo.csmiCertifyType==6?"selected":""}>营业执照</option>
							<option value="7" ${csMember.$csmInfo.csmiCertifyType==7?"selected":""}>公务员证</option>
							<option value="9" ${csMember.$csmInfo.csmiCertifyType==9?"selected":""}>其它</option>
					 	</select>
				 	 	<b>*</b>
				 	 	<em>请选择用车人员的证件类型</em>
					</dd>
				</dl>
				
				
				<dl class="" major>
					<dt>证件号码:</dt>
					<dd input="text">
					 	<input type="text" class="input narrow"  maxlength="32" name="csmiCertifyNum" id="csmiCertifyNum"  value="${csMember.$csmInfo.csmiCertifyNum}"/>
				 	 	<b>*</b>
				 	 	<em>请输入用车人员的证件号码</em>
					</dd>
				</dl>
				
				
				
				<dl class=""  style="width:98%;">
					<dt>驾证图片:</dt>
					<dd input="text">
					    <input type="text" class="input" maxlength="128" size="32" name="csmiDriverImage" id="csmiDriverImage"  value="${csMember.$csmInfo.csmiDriverImage}"/>
						<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csmiDriverImage').val(url)}})" class="button">设置图片</button>
						<button type="button" onclick="$('#csmiDriverImage').val('')" class="button">删除图片</button>
						<button type="button" onclick="$.thumb({url:$('#csmiDriverImage').val()})" class="button">查看图片</button>
				 	 	<b>*</b>
				 	 	<em>请输入用车人员的证件号码</em>
					</dd>
				</dl>
				
				
				<dl class="" major>
					<dt>驾证号码:</dt>
					<dd input="text">
					 	<input type="text" class="input narrow"  maxlength="32" name="csmiDriverNum" id="csmiDriverNum"  value="${csMember.$csmInfo.csmiDriverNum}"/>
				 	 	<b>*</b>
				 	 	<em>请输入用车人员的驾证号码</em>
					</dd>
				</dl>
				
				
				
				
				
				<dl class="" major  ref="csupStatus" >
					<dt>状　　态:</dt>
					<dd input="select">
					 	<select class="narrow" id="csupStatus" name="csUnitPerson.csupStatus">
					 		<option value="">请选择</option>
							<option value="1" ${csUnitPerson.csupStatus==1?"selected":""}>正常</option>
							<option value="0" ${csUnitPerson.csupStatus==0?"selected":""}>无效</option>
					 	</select>
				 	 	<b>*</b>
				 	 	<em>请选择用车人员的状态</em>
					</dd>
				</dl>
				
				
				<div class="line"></div>
				
				
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUnitPerson.csupId}" name="csUnitPerson.csupId" id="csupId" />
					</s:if>	
					<button title="保存数据,关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="完成">
					&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
					完成&nbsp;&nbsp;</button>
					<button title="保存数据,不关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="保存">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
						保存&nbsp;&nbsp;</button>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>

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