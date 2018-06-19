<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/membermemo.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/membermemo.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csMemberMemo.csmmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csmmId,csmmContent,csmmMember,csmmStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmmId,csmmContent,csmmMember,csmmStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csmmId,csmmContent,csmmMember,csmmStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmmId,csmmContent,csmmMember,csmmStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"会员备忘编辑":CTRL.title}</title>
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
	$("#membermemoForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csmmId==true">
		${lz:set("haveEditable",true)}
		,"csMemberMemo.csmmId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmmContent==true">
		${lz:set("haveEditable",true)}
		,"csMemberMemo.csmmContent":function(el){
			if(jQuery.trim(el.value)=="")
				return "备忘内容不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备忘内容最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmmMember==true">
		${lz:set("haveEditable",true)}
		,"csMemberMemo.csmmMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmmEditor==true">
		${lz:set("haveEditable",true)}
		,"csMemberMemo.csmmEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmmUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csMemberMemo.csmmUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmmAddTime==true">
		${lz:set("haveEditable",true)}
		,"csMemberMemo.csmmAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmmStatus==true">
		${lz:set("haveEditable",true)}
		,"csMemberMemo.csmmStatus":function(el){
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
	<form class="form editform" ref="CsMemberMemo" id="membermemoForm" name="membermemoForm" action="${empty CTRL.action?"membermemo_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写会员备忘相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csMemberMemoToken}" name="csMemberMemoToken" id="csMemberMemoToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csMemberMemo.csmmId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csmmId和after$csmmId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmmId==true">
	${before$csmmId}
	<dl class="csmmId ${CTRL.e.csmmId?"hidden":""}" major  ref="csmmId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csmmId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csMemberMemo$csmmId!=null">${csMemberMemo$csmmId}</s:if><s:else>
			<input type="hidden" value="${csMemberMemo.csmmId}" name="csMemberMemo.csmmId" id="csmmId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmmId">${csMemberMemo.csmmId}</textarea>
		 		<span>
		 	
			 ${csMemberMemo.csmmId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmmId}
	</s:if>
	
	${lz:set("注释","*****************备忘内容字段的输入框代码*****************")}
	${lz:set("注释","before$csmmContent和after$csmmContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmmContent==true">
	${before$csmmContent}
	<dl class="csmmContent " major  ref="csmmContent" style="width:98%;">
		<dt>备忘内容:</dt>
		<s:if test="#request.CTRL.e.csmmContent==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csMemberMemo$csmmContent!=null">${csMemberMemo$csmmContent}</s:if><s:else>
		 	<textarea class="input wide"  id="csmmContent" name="csMemberMemo.csmmContent" rows="5">${csMemberMemo.csmmContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员备忘的备忘内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csMemberMemo.csmmContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmmContent}
	</s:if>
	
	${lz:set("注释","*****************所属会员字段的输入框代码*****************")}
	${lz:set("注释","before$csmmMember和after$csmmMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmmMember==true">
	${before$csmmMember}
	<dl class="csmmMember " major  ref="csmmMember" >
		<dt>所属会员:</dt>
		<s:if test="#request.CTRL.e.csmmMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMemberMemo$csmmMember!=null">${csMemberMemo$csmmMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}" type="text" id="csmmMember" name="csMemberMemo.csmmMember" text="${get:CsMember(csMemberMemo.csmmMember).csmName}" value="${csMemberMemo.csmmMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmmMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csmmMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmmMember">${csMemberMemo.csmmMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csMemberMemo.csmmMember}',{ctrl:{editable:false,visible:true}})">
			 ${csMemberMemo.csmmMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmmMember}
	</s:if>
	
	${lz:set("注释","*****************操作人字段的输入框代码*****************")}
	${lz:set("注释","before$csmmEditor和after$csmmEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmmEditor==true">
	${before$csmmEditor}
	<dl class="csmmEditor " major  ref="csmmEditor" >
		<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csmmEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMemberMemo$csmmEditor!=null">${csMemberMemo$csmmEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csmmEditor" name="csMemberMemo.csmmEditor">
		 				<option selected value="${csMemberMemo.csmmEditor}">
		 					${get:SrvUser(csMemberMemo.csmmEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmmEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csmmEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmmEditor">${csMemberMemo.csmmEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csMemberMemo.csmmEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csMemberMemo.csmmEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmmEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmmUpdateTime和after$csmmUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmmUpdateTime==true">
	${before$csmmUpdateTime}
	<dl class="csmmUpdateTime " major  ref="csmmUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csmmUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMemberMemo$csmmUpdateTime!=null">${csMemberMemo$csmmUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMemberMemo.csmmUpdateTime" id="csmmUpdateTime"  value="<s:date name="csMemberMemo.csmmUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员备忘的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmmUpdateTime">${csMemberMemo.csmmUpdateTime}</textarea>
		 		<span>
		 	
			 ${csMemberMemo.csmmUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmmUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmmAddTime和after$csmmAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmmAddTime==true">
	${before$csmmAddTime}
	<dl class="csmmAddTime " major  ref="csmmAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csmmAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMemberMemo$csmmAddTime!=null">${csMemberMemo$csmmAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMemberMemo.csmmAddTime" id="csmmAddTime"  value="<s:date name="csMemberMemo.csmmAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员备忘的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmmAddTime">${csMemberMemo.csmmAddTime}</textarea>
		 		<span>
		 	
			 ${csMemberMemo.csmmAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmmAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmmStatus和after$csmmStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmmStatus==true">
	${before$csmmStatus}
	<dl class="csmmStatus " major  ref="csmmStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csmmStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMemberMemo$csmmStatus!=null">${csMemberMemo$csmmStatus}</s:if><s:else>
		 	<select class="narrow" id="csmmStatus" name="csMemberMemo.csmmStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csMemberMemo.csmmStatus==1?"selected":""}>正常</option>
				<option value="0" ${csMemberMemo.csmmStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员备忘的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmmStatus">${csMemberMemo.csmmStatus}</textarea>
		 		<span>
		 	
			 ${csMemberMemo.csmmStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmmStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csMemberMemo.csmmId}" name="csMemberMemo.csmmId" id="csmmId" />
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