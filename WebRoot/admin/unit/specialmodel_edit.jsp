<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/specialmodel.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/specialmodel.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csSpecialModel.cssmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cssmId,cssmName,cssmThird,cssmThirdCode,cssmStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssmId,cssmName,cssmThird,cssmThirdCode,cssmStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cssmId,cssmName,cssmThird,cssmThirdCode,cssmStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssmId,cssmName,cssmThird,cssmThirdCode,cssmStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"专车车型编辑":CTRL.title}</title>
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
	$("#specialmodelForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cssmId==true">
		${lz:set("haveEditable",true)}
		,"csSpecialModel.cssmId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmName==true">
		${lz:set("haveEditable",true)}
		,"csSpecialModel.cssmName":function(el){
			if(jQuery.trim(el.value)=="")
				return "车型名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车型名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmThird==true">
		${lz:set("haveEditable",true)}
		,"csSpecialModel.cssmThird":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择三方名称";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmThirdCode==true">
		${lz:set("haveEditable",true)}
		,"csSpecialModel.cssmThirdCode":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "三方代码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csSpecialModel.cssmUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmAddTime==true">
		${lz:set("haveEditable",true)}
		,"csSpecialModel.cssmAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmStatus==true">
		${lz:set("haveEditable",true)}
		,"csSpecialModel.cssmStatus":function(el){
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
	<form class="form editform" ref="CsSpecialModel" id="specialmodelForm" name="specialmodelForm" action="${empty CTRL.action?"specialmodel_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写专车车型相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csSpecialModelToken}" name="csSpecialModelToken" id="csSpecialModelToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csSpecialModel.cssmId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cssmId和after$cssmId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmId==true">
	${before$cssmId}
	<dl class="cssmId ${CTRL.e.cssmId?"hidden":""}" major  ref="cssmId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cssmId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csSpecialModel$cssmId!=null">${csSpecialModel$cssmId}</s:if><s:else>
			<input type="hidden" value="${csSpecialModel.cssmId}" name="csSpecialModel.cssmId" id="cssmId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmId">${csSpecialModel.cssmId}</textarea>
		 		<span>
		 	
			 ${csSpecialModel.cssmId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmId}
	</s:if>
	
	${lz:set("注释","*****************车型名称字段的输入框代码*****************")}
	${lz:set("注释","before$cssmName和after$cssmName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmName==true">
	${before$cssmName}
	<dl class="cssmName " major  ref="cssmName" >
		<dt>车型名称:</dt>
		<s:if test="#request.CTRL.e.cssmName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSpecialModel$cssmName!=null">${csSpecialModel$cssmName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSpecialModel.cssmName" id="cssmName"  value="${csSpecialModel.cssmName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入专车车型的车型名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车型名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmName">${csSpecialModel.cssmName}</textarea>
		 		<span>
		 	
			 ${csSpecialModel.cssmName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmName}
	</s:if>
	
	${lz:set("注释","*****************三方名称字段的输入框代码*****************")}
	${lz:set("注释","before$cssmThird和after$cssmThird变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmThird==true">
	${before$cssmThird}
	<dl class="cssmThird " major  ref="cssmThird" >
		<dt>三方名称:</dt>
		<s:if test="#request.CTRL.e.cssmThird==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSpecialModel$cssmThird!=null">${csSpecialModel$cssmThird}</s:if><s:else>
		 	<select class="narrow" id="cssmThird" name="csSpecialModel.cssmThird">
		 		<option value="">请选择</option>
				<option value="1" ${csSpecialModel.cssmThird==1?"selected":""}>滴滴专车</option>
				<option value="2" ${csSpecialModel.cssmThird==2?"selected":""}>易道专车</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择专车车型的三方名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****三方名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmThird">${csSpecialModel.cssmThird}</textarea>
		 		<span>
		 	
			 ${csSpecialModel.cssmThird$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmThird}
	</s:if>
	
	${lz:set("注释","*****************三方代码字段的输入框代码*****************")}
	${lz:set("注释","before$cssmThirdCode和after$cssmThirdCode变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmThirdCode==true">
	${before$cssmThirdCode}
	<dl class="cssmThirdCode " minor  ref="cssmThirdCode" >
		<dt>三方代码:</dt>
		<s:if test="#request.CTRL.e.cssmThirdCode==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSpecialModel$cssmThirdCode!=null">${csSpecialModel$cssmThirdCode}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSpecialModel.cssmThirdCode" id="cssmThirdCode"  value="${csSpecialModel.cssmThirdCode}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入专车车型的三方代码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****三方代码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmThirdCode">${csSpecialModel.cssmThirdCode}</textarea>
		 		<span>
		 	
			 ${csSpecialModel.cssmThirdCode$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmThirdCode}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssmUpdateTime和after$cssmUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmUpdateTime==true">
	${before$cssmUpdateTime}
	<dl class="cssmUpdateTime " major  ref="cssmUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cssmUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSpecialModel$cssmUpdateTime!=null">${csSpecialModel$cssmUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSpecialModel.cssmUpdateTime" id="cssmUpdateTime"  value="<s:date name="csSpecialModel.cssmUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择专车车型的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmUpdateTime">${csSpecialModel.cssmUpdateTime}</textarea>
		 		<span>
		 	
			 ${csSpecialModel.cssmUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssmAddTime和after$cssmAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmAddTime==true">
	${before$cssmAddTime}
	<dl class="cssmAddTime " major  ref="cssmAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cssmAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSpecialModel$cssmAddTime!=null">${csSpecialModel$cssmAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSpecialModel.cssmAddTime" id="cssmAddTime"  value="<s:date name="csSpecialModel.cssmAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择专车车型的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmAddTime">${csSpecialModel.cssmAddTime}</textarea>
		 		<span>
		 	
			 ${csSpecialModel.cssmAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cssmStatus和after$cssmStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmStatus==true">
	${before$cssmStatus}
	<dl class="cssmStatus " major  ref="cssmStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cssmStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSpecialModel$cssmStatus!=null">${csSpecialModel$cssmStatus}</s:if><s:else>
		 	<select class="narrow" id="cssmStatus" name="csSpecialModel.cssmStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csSpecialModel.cssmStatus==1?"selected":""}>正常</option>
				<option value="0" ${csSpecialModel.cssmStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择专车车型的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmStatus">${csSpecialModel.cssmStatus}</textarea>
		 		<span>
		 	
			 ${csSpecialModel.cssmStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csSpecialModel.cssmId}" name="csSpecialModel.cssmId" id="cssmId" />
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