<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/credit/record.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/credit/record.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCreditRecord.cscrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscrId,cscrTitle,cscrMember,cscrValue,cscrStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscrId,cscrTitle,cscrMember,cscrValue,cscrStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscrId,cscrTitle,cscrMember,cscrValue,cscrStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscrId,cscrTitle,cscrMember,cscrValue,cscrStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"信用记录编辑":CTRL.title}</title>
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
	$("#recordForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscrId==true">
		${lz:set("haveEditable",true)}
		,"csCreditRecord.cscrId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrTitle==true">
		${lz:set("haveEditable",true)}
		,"csCreditRecord.cscrTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "信用描述不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "信用描述最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrMember==true">
		${lz:set("haveEditable",true)}
		,"csCreditRecord.cscrMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrValue==true">
		${lz:set("haveEditable",true)}
		,"csCreditRecord.cscrValue":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "信用值输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csCreditRecord.cscrUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrAddTime==true">
		${lz:set("haveEditable",true)}
		,"csCreditRecord.cscrAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrStatus==true">
		${lz:set("haveEditable",true)}
		,"csCreditRecord.cscrStatus":function(el){
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
	<form class="form editform" ref="CsCreditRecord" id="recordForm" name="recordForm" action="${empty CTRL.action?"record_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写信用记录相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCreditRecordToken}" name="csCreditRecordToken" id="csCreditRecordToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCreditRecord.cscrId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscrId和after$cscrId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrId==true">
	${before$cscrId}
	<dl class="cscrId ${CTRL.e.cscrId?"hidden":""}" major  ref="cscrId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscrId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCreditRecord$cscrId!=null">${csCreditRecord$cscrId}</s:if><s:else>
			<input type="hidden" value="${csCreditRecord.cscrId}" name="csCreditRecord.cscrId" id="cscrId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrId">${csCreditRecord.cscrId}</textarea>
		 		<span>
		 	
			 ${csCreditRecord.cscrId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrId}
	</s:if>
	
	${lz:set("注释","*****************信用描述字段的输入框代码*****************")}
	${lz:set("注释","before$cscrTitle和after$cscrTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrTitle==true">
	${before$cscrTitle}
	<dl class="cscrTitle " major  ref="cscrTitle" style="width:98%;">
		<dt>信用描述:</dt>
		<s:if test="#request.CTRL.e.cscrTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCreditRecord$cscrTitle!=null">${csCreditRecord$cscrTitle}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csCreditRecord.cscrTitle" id="cscrTitle"  value="${csCreditRecord.cscrTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入信用记录的信用描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****信用描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscrTitle">${csCreditRecord.cscrTitle}</textarea>
		 		<span>
		 	
			 ${csCreditRecord.cscrTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrTitle}
	</s:if>
	
	${lz:set("注释","*****************所属会员字段的输入框代码*****************")}
	${lz:set("注释","before$cscrMember和after$cscrMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrMember==true">
	${before$cscrMember}
	<dl class="cscrMember " major  ref="cscrMember" >
		<dt>所属会员:</dt>
		<s:if test="#request.CTRL.e.cscrMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCreditRecord$cscrMember!=null">${csCreditRecord$cscrMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}" type="text" id="cscrMember" name="csCreditRecord.cscrMember" text="${get:CsMember(csCreditRecord.cscrMember).csmName}" value="${csCreditRecord.cscrMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscrMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cscrMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrMember">${csCreditRecord.cscrMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csCreditRecord.cscrMember}',{ctrl:{editable:false,visible:true}})">
			 ${csCreditRecord.cscrMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrMember}
	</s:if>
	
	${lz:set("注释","*****************信用值字段的输入框代码*****************")}
	${lz:set("注释","before$cscrValue和after$cscrValue变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrValue==true">
	${before$cscrValue}
	<dl class="cscrValue " major  ref="cscrValue" >
		<dt>信&nbsp;&nbsp;用&nbsp;&nbsp;值:</dt>
		<s:if test="#request.CTRL.e.cscrValue==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCreditRecord$cscrValue!=null">${csCreditRecord$cscrValue}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCreditRecord.cscrValue" id="cscrValue"  value="${csCreditRecord.cscrValue}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入信用记录的信用值</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****信用值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrValue">${csCreditRecord.cscrValue}</textarea>
		 		<span>
		 	
			 ${csCreditRecord.cscrValue$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrValue}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscrUpdateTime和after$cscrUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrUpdateTime==true">
	${before$cscrUpdateTime}
	<dl class="cscrUpdateTime " major  ref="cscrUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cscrUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCreditRecord$cscrUpdateTime!=null">${csCreditRecord$cscrUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCreditRecord.cscrUpdateTime" id="cscrUpdateTime"  value="<s:date name="csCreditRecord.cscrUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择信用记录的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrUpdateTime">${csCreditRecord.cscrUpdateTime}</textarea>
		 		<span>
		 	
			 ${csCreditRecord.cscrUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscrAddTime和after$cscrAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrAddTime==true">
	${before$cscrAddTime}
	<dl class="cscrAddTime " major  ref="cscrAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscrAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCreditRecord$cscrAddTime!=null">${csCreditRecord$cscrAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCreditRecord.cscrAddTime" id="cscrAddTime"  value="<s:date name="csCreditRecord.cscrAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择信用记录的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrAddTime">${csCreditRecord.cscrAddTime}</textarea>
		 		<span>
		 	
			 ${csCreditRecord.cscrAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscrStatus和after$cscrStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrStatus==true">
	${before$cscrStatus}
	<dl class="cscrStatus " major  ref="cscrStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscrStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCreditRecord$cscrStatus!=null">${csCreditRecord$cscrStatus}</s:if><s:else>
		 	<select class="narrow" id="cscrStatus" name="csCreditRecord.cscrStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csCreditRecord.cscrStatus==1?"selected":""}>正常</option>
				<option value="0" ${csCreditRecord.cscrStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择信用记录的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrStatus">${csCreditRecord.cscrStatus}</textarea>
		 		<span>
		 	
			 ${csCreditRecord.cscrStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCreditRecord.cscrId}" name="csCreditRecord.cscrId" id="cscrId" />
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