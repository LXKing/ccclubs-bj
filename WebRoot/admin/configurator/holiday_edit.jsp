<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/holiday.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/holiday.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csHoliday.cshId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cshId,cshName,cshDate,cshType,cshIndex",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cshId,cshName,cshDate,cshType,cshIndex",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cshId,cshName,cshDate,cshType,cshIndex",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cshId,cshName,cshDate,cshType,cshIndex",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"节假日编辑":CTRL.title}</title>
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
	$("#holidayForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cshId==true">
		${lz:set("haveEditable",true)}
		,"csHoliday.cshId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshName==true">
		${lz:set("haveEditable",true)}
		,"csHoliday.cshName":function(el){
			if(jQuery.trim(el.value)=="")
				return "节假日名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "节假日名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshDate==true">
		${lz:set("haveEditable",true)}
		,"csHoliday.cshDate":function(el){
			if(jQuery.trim(el.value)=="")
				return "日期不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshType==true">
		${lz:set("haveEditable",true)}
		,"csHoliday.cshType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cshIndex==true">
		${lz:set("haveEditable",true)}
		,"csHoliday.cshIndex":function(el){
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
	<form class="form editform" ref="CsHoliday" id="holidayForm" name="holidayForm" action="${empty CTRL.action?"holiday_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写节假日相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csHolidayToken}" name="csHolidayToken" id="csHolidayToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csHoliday.cshId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cshId和after$cshId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshId==true">
	${before$cshId}
	<dl class="cshId ${CTRL.e.cshId?"hidden":""}" major  ref="cshId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cshId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csHoliday$cshId!=null">${csHoliday$cshId}</s:if><s:else>
			<input type="hidden" value="${csHoliday.cshId}" name="csHoliday.cshId" id="cshId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshId">${csHoliday.cshId}</textarea>
		 		<span>
		 	
			 ${csHoliday.cshId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshId}
	</s:if>
	
	${lz:set("注释","*****************节假日名称字段的输入框代码*****************")}
	${lz:set("注释","before$cshName和after$cshName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshName==true">
	${before$cshName}
	<dl class="cshName " major  ref="cshName" >
		<dt>节假日名称:</dt>
		<s:if test="#request.CTRL.e.cshName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csHoliday$cshName!=null">${csHoliday$cshName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csHoliday.cshName" id="cshName"  value="${csHoliday.cshName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>比如：国庆节、国庆节调班</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****节假日名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshName">${csHoliday.cshName}</textarea>
		 		<span>
		 	
			 ${csHoliday.cshName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshName}
	</s:if>
	
	${lz:set("注释","*****************日期字段的输入框代码*****************")}
	${lz:set("注释","before$cshDate和after$cshDate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshDate==true">
	${before$cshDate}
	<dl class="cshDate " major  ref="cshDate" >
		<dt>日　　期:</dt>
		<s:if test="#request.CTRL.e.cshDate==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csHoliday$cshDate!=null">${csHoliday$cshDate}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csHoliday.cshDate" id="cshDate"  value="<s:date name="csHoliday.cshDate" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择节假日的日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshDate">${csHoliday.cshDate}</textarea>
		 		<span>
		 	
			 ${csHoliday.cshDate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshDate}
	</s:if>
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$cshType和after$cshType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshType==true">
	${before$cshType}
	<dl class="cshType " major  ref="cshType" >
		<dt>类　　型:</dt>
		<s:if test="#request.CTRL.e.cshType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csHoliday$cshType!=null">${csHoliday$cshType}</s:if><s:else>
		 	<select class="narrow" id="cshType" name="csHoliday.cshType">
		 		<option value="">请选择</option>
				<option value="0" ${csHoliday.cshType==0?"selected":""}>放假</option>
				<option value="1" ${csHoliday.cshType==1?"selected":""}>调班</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>调班日期按工作日计费</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshType">${csHoliday.cshType}</textarea>
		 		<span>
		 	
			 ${csHoliday.cshType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshType}
	</s:if>
	
	${lz:set("注释","*****************星期几字段的输入框代码*****************")}
	${lz:set("注释","before$cshIndex和after$cshIndex变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cshIndex==true">
	${before$cshIndex}
	<dl class="cshIndex " major  ref="cshIndex" >
		<dt>星&nbsp;&nbsp;期&nbsp;&nbsp;几:</dt>
		<s:if test="#request.CTRL.e.cshIndex==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csHoliday$cshIndex!=null">${csHoliday$cshIndex}</s:if><s:else>
		 	<select class="narrow" id="cshIndex" name="csHoliday.cshIndex">
		 		<option value="">请选择</option>
				<option value="1" ${csHoliday.cshIndex==1?"selected":""}>星期天</option>
				<option value="2" ${csHoliday.cshIndex==2?"selected":""}>星期一</option>
				<option value="3" ${csHoliday.cshIndex==3?"selected":""}>星期二</option>
				<option value="4" ${csHoliday.cshIndex==4?"selected":""}>星期三</option>
				<option value="5" ${csHoliday.cshIndex==5?"selected":""}>星期四</option>
				<option value="6" ${csHoliday.cshIndex==6?"selected":""}>星期五</option>
				<option value="7" ${csHoliday.cshIndex==7?"selected":""}>星期六</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>类型为调班时有效，表示当天限行为星期几</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****星期几字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cshIndex">${csHoliday.cshIndex}</textarea>
		 		<span>
		 	
			 ${csHoliday.cshIndex$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cshIndex}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csHoliday.cshId}" name="csHoliday.cshId" id="cshId" />
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