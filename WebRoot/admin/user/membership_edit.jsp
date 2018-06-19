<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/membership.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/membership.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csMemberShip.csmsId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csmsId,csmsHost,csmsPayer,csmsTargeter,csmsRemark,csmsStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmsId,csmsHost,csmsPayer,csmsTargeter,csmsRemark,csmsStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csmsId,csmsHost,csmsPayer,csmsTargeter,csmsRemark,csmsStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmsId,csmsHost,csmsPayer,csmsTargeter,csmsAddTime,csmsRemark,csmsStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"会员关系编辑":CTRL.title}</title>
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
	$("#membershipForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csmsId==true">
		${lz:set("haveEditable",true)}
		,"csMemberShip.csmsId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmsHost==true">
		${lz:set("haveEditable",true)}
		,"csMemberShip.csmsHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmsPayer==true">
		${lz:set("haveEditable",true)}
		,"csMemberShip.csmsPayer":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmsTargeter==true">
		${lz:set("haveEditable",true)}
		,"csMemberShip.csmsTargeter":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmsAddTime==true">
		${lz:set("haveEditable",true)}
		,"csMemberShip.csmsAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmsRemark==true">
		${lz:set("haveEditable",true)}
		,"csMemberShip.csmsRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmsStatus==true">
		${lz:set("haveEditable",true)}
		,"csMemberShip.csmsStatus":function(el){
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
	<form class="form editform" ref="CsMemberShip" id="membershipForm" name="membershipForm" action="${empty CTRL.action?"membership_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写会员关系相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csMemberShipToken}" name="csMemberShipToken" id="csMemberShipToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csMemberShip.csmsId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csmsId和after$csmsId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmsId==true">
	${before$csmsId}
	<dl class="csmsId ${CTRL.e.csmsId?"hidden":""}" major  ref="csmsId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csmsId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csMemberShip$csmsId!=null">${csMemberShip$csmsId}</s:if><s:else>
			<input type="hidden" value="${csMemberShip.csmsId}" name="csMemberShip.csmsId" id="csmsId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmsId">${csMemberShip.csmsId}</textarea>
		 		<span>
		 	
			 ${csMemberShip.csmsId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmsId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csmsHost和after$csmsHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmsHost==true">
	${before$csmsHost}
	<dl class="csmsHost " major  ref="csmsHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csmsHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMemberShip$csmsHost!=null">${csMemberShip$csmsHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csmsHost" name="csMemberShip.csmsHost">
		 				<option selected value="${csMemberShip.csmsHost}">
		 					${get:SrvHost(csMemberShip.csmsHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmsHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csmsHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmsHost">${csMemberShip.csmsHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csMemberShip.csmsHost}',{ctrl:{editable:false,visible:true}})">
			 ${csMemberShip.csmsHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmsHost}
	</s:if>
	
	${lz:set("注释","*****************付款帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csmsPayer和after$csmsPayer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmsPayer==true">
	${before$csmsPayer}
	<dl class="csmsPayer " major  ref="csmsPayer" >
		<dt>付款帐号:</dt>
		<s:if test="#request.CTRL.e.csmsPayer==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMemberShip$csmsPayer!=null">${csMemberShip$csmsPayer}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csmsHost}" type="text" id="csmsPayer" name="csMemberShip.csmsPayer" text="${get:CsMember(csMemberShip.csmsPayer).csmName}" value="${csMemberShip.csmsPayer}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmsPayer').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csmsPayer').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****付款帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmsPayer">${csMemberShip.csmsPayer}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csMemberShip.csmsPayer}',{ctrl:{editable:false,visible:true}})">
			 ${csMemberShip.csmsPayer$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmsPayer}
	</s:if>
	
	${lz:set("注释","*****************使用帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csmsTargeter和after$csmsTargeter变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmsTargeter==true">
	${before$csmsTargeter}
	<dl class="csmsTargeter " major  ref="csmsTargeter" >
		<dt>使用帐号:</dt>
		<s:if test="#request.CTRL.e.csmsTargeter==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMemberShip$csmsTargeter!=null">${csMemberShip$csmsTargeter}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csmsHost}" type="text" id="csmsTargeter" name="csMemberShip.csmsTargeter" text="${get:CsMember(csMemberShip.csmsTargeter).csmName}" value="${csMemberShip.csmsTargeter}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmsTargeter').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csmsTargeter').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmsTargeter">${csMemberShip.csmsTargeter}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csMemberShip.csmsTargeter}',{ctrl:{editable:false,visible:true}})">
			 ${csMemberShip.csmsTargeter$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmsTargeter}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmsAddTime和after$csmsAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmsAddTime==true">
	${before$csmsAddTime}
	<dl class="csmsAddTime " major  ref="csmsAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csmsAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMemberShip$csmsAddTime!=null">${csMemberShip$csmsAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMemberShip.csmsAddTime" id="csmsAddTime"  value="<s:date name="csMemberShip.csmsAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员关系的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmsAddTime">${csMemberShip.csmsAddTime}</textarea>
		 		<span>
		 	
			 ${csMemberShip.csmsAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmsAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csmsRemark和after$csmsRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmsRemark==true">
	${before$csmsRemark}
	<dl class="csmsRemark " minor  ref="csmsRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csmsRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csMemberShip$csmsRemark!=null">${csMemberShip$csmsRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csmsRemark" name="csMemberShip.csmsRemark" rows="5">${csMemberShip.csmsRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员关系的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csMemberShip.csmsRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmsRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmsStatus和after$csmsStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmsStatus==true">
	${before$csmsStatus}
	<dl class="csmsStatus " major  ref="csmsStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csmsStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMemberShip$csmsStatus!=null">${csMemberShip$csmsStatus}</s:if><s:else>
		 	<select class="narrow" id="csmsStatus" name="csMemberShip.csmsStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csMemberShip.csmsStatus==0?"selected":""}>正在审核</option>
				<option value="1" ${csMemberShip.csmsStatus==1?"selected":""}>审核通过</option>
				<option value="2" ${csMemberShip.csmsStatus==2?"selected":""}>审核未通过</option>
				<option value="3" ${csMemberShip.csmsStatus==3?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员关系的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmsStatus">${csMemberShip.csmsStatus}</textarea>
		 		<span>
		 	
			 ${csMemberShip.csmsStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmsStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csMemberShip.csmsId}" name="csMemberShip.csmsId" id="csmsId" />
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