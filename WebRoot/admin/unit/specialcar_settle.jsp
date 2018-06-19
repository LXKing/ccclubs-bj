<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/specialcar.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
${lz:set("canEdit",true)}
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/specialcar.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csSmsMt.cssmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csscKilo,finishTime,cssmRemark",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"price,cost,csscKilo,finishTime,cssmRemark",
	</s:if>
	<s:else>	
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csscKilo,finishTime,cssmRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"price,cost,finishTime,csscKilo,cssmRemark"
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"短信发送编辑":CTRL.title}</title>
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



<!--LAZY3Q_JSP_BODY-->
<script>
$(function(){
	var dl=$("<dl><dt></dt><dd></dd></dl>").insertAfter("dl[ref=cssmMobile]");
	var button = $("<button class='button' type='button'>选择模板短信</button>").appendTo(dl.find("dd"));
	button.click(function(){
		href("${basePath}admin/configurator/template_select.do",{});
	});
});
</script>
<!--LAZY3Q_JSP_BODY-->



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#smsmtForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cssmId==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cssmId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csscKilo==true">
		${lz:set("haveEditable",true)}
		,"csscKilo":function(el){
			if(el<=0)
			return "里程数大于0";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cost==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cost":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择短信类型";
		}
		</s:if>	
		
		<s:if test="#request.CTRL.e.finishTime==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.finishTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "短信内容不能为空";
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "短信内容最大长度为1024个字符";
		}
		</s:if>	
		
		
		
		
		<s:if test="#request.CTRL.e.cssmRemark==true">
		${lz:set("haveEditable",true)}
		,"cssmRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		
	},function(){
		${lz:set("注释","****表单提交自定义判断，阻止提交返回提示字符串即可****")}
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		
		if($("#csscKilo").val()==""){
			showTips("请输入里程数");
			return "";
		}else if(Math.min(Number($("#csscKilo").val()),0) <0){
			showTips("里程数大于0");
			return "里程数大于0";
		}else{
			$("#cost").val($("#csscKilo").val()*$("#price").val());
			$("#costText").text($("#csscKilo").val()*$("#price").val());
		}
			
	});		
});
</script>

<s:if test="false"></s:if>
<s:elseif test="false"></s:elseif>
<s:else>
	<form class="form editform" ref="CsSmsMt" id="smsmtForm" name="smsmtForm" action="${empty CTRL.action?"specialcar_doSettle.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写短信发送相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csSmsMtToken}" name="csSmsMtToken" id="csSmsMtToken" />
				<input type="hidden"  name="csscId" id="csscId" value="${csSpecialCar.csscId}"/>
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csSmsMt.cssmId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************里程单价字段的输入框代码*****************")}
	${lz:set("注释","before$price和after$price变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.price==true">
	${before$price}
	<dl class="price ${CTRL.e.price?"hidden":""}" major  ref="price" >
		<dt>里程单价:</dt>
		<s:if test="#request.CTRL.e.price==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.price$price!=null">${csSmsMt$price}</s:if><s:else>
			<input type="hidden" value="${price}" name="price" id="price" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="price">${price}</textarea>
		 		<span>
		 	
			 ${price}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$price}
	</s:if>
	
	${lz:set("注释","*****************实际里程数字段的输入框代码*****************")}
	${lz:set("注释","before$csscKilo和after$csscKilo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csscKilo==true">
	${before$csscKilo}
	<dl class="csscKilo " major  ref="csscKilo" >
		<dt>实际里程数：</dt>
		<s:if test="#request.CTRL.e.csscKilo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSmsMt$csscKilo!=null">${csSmsMt$csscKilo}</s:if><s:else>
			<input type="number" class="input narrow" value="${csSpecialCar.csscKilo}" name="csscKilo" id="csscKilo" onblur="count()"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入里程数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际里程数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csscKilo">${csscKilo}</textarea>
		 		<span>
		 	
			 ${csSpecialCar.csscKilo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csscKilo}
	</s:if>
	
	${lz:set("注释","*****************费用字段的输入框代码*****************")}
	${lz:set("注释","before$cost和after$cost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cost==true">
	${before$cost}
	<dl class="cost " major  ref="cost" >
		<dt>里程费用:</dt>
		<s:if test="#request.CTRL.e.cost==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSmsMt$cost!=null">${csSmsMt$cost}</s:if><s:else>
		 	<input type="hidden" value="${cost}" name="cost" id="cost" />
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>里程费用=里程单价*里程数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****短信类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cost">${csSmsMt.cost}</textarea>
		 		<span id="costText">
		 	
			 ${csSmsMt.cost$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cost}
	</s:if>
	

	
	${lz:set("注释","*****************订单结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$finishTime和after$finishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.finishTime==true">
	${before$finishTime}
	<dl class="finishTime " major  ref="finishTime" >
		<dt>订单结束时间:</dt>
		<s:if test="#request.CTRL.e.finishTime==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSmsMt$finishTime!=null">${csSmsMt$finishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="finishTime" id="finishTime"  value="<s:date name="csSpecialCar.finishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>订单结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csSpecialCar.finishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$finishTime}
	</s:if>
	
	
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$cssmRemark和after$cssmRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmRemark==true">
	${before$cssmRemark}
	<dl class="cssmRemark " minor  ref="cssmRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.cssmRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSmsMt$cssmRemark!=null">${csSmsMt$cssmRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cssmRemark" name="cssmRemark" rows="5">${cssmRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入订单的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${cssmRemark}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmRemark}
	</s:if>
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csSmsMt.cssmId}" name="csSmsMt.cssmId" id="cssmId" />
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
	function count(){
		$("#cost").val($("#csscKilo").val()*$("#price").val());
		$("#costText").text($("#csscKilo").val()*$("#price").val());
	}
	function check(c)
	{
	    var r= /^[1-9]?[0-9]*\.[0-9]*$/;
	    return r.test(c);
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>