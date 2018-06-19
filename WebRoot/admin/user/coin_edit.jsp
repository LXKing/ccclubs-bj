<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/coin.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/coin.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCoin.cscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscId,cscHost,cscMember,cscCount,cscValidity,records,cscRemark",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscMember,cscCount,cscValidity,records,cscRemark",
	</s:if>
	<s:elseif test="#request.CTRL.title=='扣除'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"amount,profile,order,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscMember,cscCount,cscRemain,amount,profile,order,nothing",
	</s:elseif>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscId,records,cscEnd,cscRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscMember,cscCount,cscRemain,cscValidity,cscFlag,records,cscEnd,cscEditor,cscRemark",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"红包编辑":CTRL.title}</title>
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
<script>
$(function(){
	$("#cscMember").attr("needparam","false");
});
</script>
<s:if test="#request.CTRL.title=='批量充红包'">
	<lz:set name="jsonString">
		{
			title:"批量充红包",
			action:"coin_batch.do",
			editable:false,
			visible:false,
			fields:{
				cscId:{visible:false}
				,cscCount:{editable:true,visible:true}
				,cscValidity:{editable:true,visible:true}
				,cscRemark:{editable:true,visible:true}
			}
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
	<lz:set name="fileHtml">
		<dl>
			<dt>2003格式excel:</dt>
			<dd>
			 	<input type="file" name="batch" id="batch" /></br>
		 	 	<b>*</b>
		 		<em>第一列为手机号,第二列为充值额度</em>
			</dd>
		</dl>
	</lz:set>
	<script>
	$(function(){
		$("${lz:js(fileHtml)}").insertBefore("dl[ref=cscCount]");
		$(".form").attr("enctype","multipart/form-data");
		$(".cscCount").remove();
		$(".validity_def").remove();
	});
	</script>
</s:if>
<!------------------------LAZY3Q_JSP_BODY------------------------>



<script>
$(function(){	
	showTips("${tips.value}");
	
	$("[name='csCoin.cscValidity']").change(function(){
	  	if($(this).val() == 0){
	  		if($("#cscEnd").length==0){
	  			var endTimeStr = "<dl class=\"cscEnd\" major  ref=\"cscEnd\" >"
		  		+"<dt>结束时间:</dt><dd input=\"date\">"
				+"<input onchange=\"void(0);\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\" type=\"text\" class=\"input narrow\" maxlength=\"19\" name=\"csCoin.cscEnd\" id=\"cscEnd\"  value=\"<s:date name='csCoin.cscEnd' format='yyyy-MM-dd'/>\"/>"
		 		+"<b>*</b>"
		 		+"<em>请选择红包的结束时间</em>"
				+"</dd>"
				+"</dl>";
				$(".cscValidity").after(endTimeStr);
	  		}
	  	}else{
	  		if($("#cscEnd").length>0){
	  			$(".cscEnd").remove();
	  		}
	  	}
	});
	
	
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#coinForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscId==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscHost==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscMember==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscMember":function(el){
		}
		</s:if>
		<s:if test="#request.CTRL.e.cscCoinSource==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscCoinSource":function(el){
		}
		</s:if>		
		<s:if test="#request.CTRL.e.cscCount==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscCount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "充值金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscRemain==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscRemain":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "可用金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscValidity==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscValidity":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscFlag==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "程序标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscEnd==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscEnd":function(el){
			if(jQuery.trim(el.value)=="")
				return "结束时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscSerial==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscSerial":function(el){
			if(el.value.length>50 && el.value.indexOf("[*]")==-1)
				return "序列号最大长度为50个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscEditor==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAddTime==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscRemark==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscStatus==true">
		${lz:set("haveEditable",true)}
		,"csCoin.cscStatus":function(el){
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
<s:elseif test="#request.CTRL.title=='扣除'">
	<form class="form editform" ref="CsCoin" id="coinForm" name="coinForm" action="${empty CTRL.action?"coin_debit.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写红包相关信息，<span class="extrude">"*" 为必填选项。</span>
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
				<input type="hidden" value="${csCoinToken}" name="csCoinToken" id="csCoinToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscId和after$cscId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscId}
	<dl class="cscId " major  ref="cscId" >
		<dt>编　　号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="label">
		<s:if test="#request.csCoin$cscId!=null">${csCoin$cscId}</s:if><s:else>
			<input type="hidden" value="${csCoin.cscId}" name="csCoin.cscId" id="cscId" />
			${csCoin.cscId$}
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscId">${csCoin.cscId}</textarea>
		 		<span>
		 	
			 ${csCoin.cscId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscId}
	
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscHost和after$cscHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscHost}
	<dl class="cscHost " major  ref="cscHost" >
		<dt>城　　市:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCoin$cscHost!=null">${csCoin$cscHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscHost" name="csCoin.cscHost">
		 				<option selected value="${csCoin.cscHost}">
		 					${get:SrvHost(csCoin.cscHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscHost">${csCoin.cscHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCoin.cscHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCoin.cscHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscHost}
	
	
	<%-- ${lz:set("注释","*****************红包来源字段的输入框代码*****************")}
	${lz:set("注释","before$cscCoinSource和after$cscCoinSource变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscCoinSource}
	<dl class="cscCoinSource " major  ref="cscCoinSource" >
		<dt>红包来源:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCoin$cscCoinSource!=null">${csCoin$cscCoinSource}</s:if><s:else>
 			<select class="combox narrow" action="${basePath}${proname}/user/coinsource_query.do?size=-1" id="cscCoinSource" name="csCoin.cscCoinSource">
 				<option selected value="${csCoin.cscCoinSource}">
 					${get:CsCoinSource(csCoin.cscCoinSource).cscsName}
 				</option>
 			</select>
 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscCoinSource).val())==''){return;};window.href('${basePath}${proname}/user/coinsource_details.do?key='+$('#cscCoinSource').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****红包来源字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscCoinSource">${csCoin.cscCoinSource}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/coinsource_details.do?key=${csCoin.cscCoinSource}',{ctrl:{editable:false,visible:true}})">
			 ${csCoin.cscCoinSource$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscCoinSource} --%>
	
	
	${lz:set("注释","*****************所属会员字段的输入框代码*****************")}
	${lz:set("注释","before$cscMember和after$cscMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscMember}
	<dl class="cscMember " major  ref="cscMember" >
		<dt>所属会员:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCoin$cscMember!=null">${csCoin$cscMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={cscHost}" type="text" id="cscMember" name="csCoin.cscMember" text="${get:CsMember(csCoin.cscMember).csmName}" value="${csCoin.cscMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cscMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscMember">${csCoin.cscMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csCoin.cscMember}',{ctrl:{editable:false,visible:true}})">
			 ${csCoin.cscMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscMember}
	
	
	${lz:set("注释","*****************充值金额字段的输入框代码*****************")}
	${lz:set("注释","before$cscCount和after$cscCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscCount}
	<dl class="cscCount " major  ref="cscCount" >
		<dt>充值金额:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCoin$cscCount!=null">${csCoin$cscCount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCoin.cscCount" id="cscCount"  value="${csCoin.cscCount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入红包的充值金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****充值金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscCount">${csCoin.cscCount}</textarea>
		 		<span>
		 	
			 ${csCoin.cscCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscCount}
	
	
	${lz:set("注释","*****************可用金额字段的输入框代码*****************")}
	${lz:set("注释","before$cscRemain和after$cscRemain变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscRemain}
	<dl class="cscRemain " major  ref="cscRemain" >
		<dt>可用金额:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCoin$cscRemain!=null">${csCoin$cscRemain}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCoin.cscRemain" id="cscRemain"  value="${csCoin.cscRemain}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入红包的可用金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****可用金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscRemain">${csCoin.cscRemain}</textarea>
		 		<span>
		 	
			 ${csCoin.cscRemain$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscRemain}
	
	
	${lz:set("注释","*****************扣除数额字段的输入框代码*****************")}
	${lz:set("注释","before$amount和after$amount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$amount}
	<dl class="amount " major  ref="amount" >
		<dt>扣除数额:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCoin$amount!=null">${csCoin$amount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="amount" id="amount"  value="${amount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入红包的扣除数额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****扣除数额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="amount">${amount}</textarea>
		 		<span>
		 	
			 ${amount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$amount}
	
	
	${lz:set("注释","*****************原因描述字段的输入框代码*****************")}
	${lz:set("注释","before$profile和after$profile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$profile}
	<dl class="profile " major  ref="profile" >
		<dt>原因描述:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCoin$profile!=null">${csCoin$profile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="" name="profile" id="profile"  value="${profile}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入红包的原因描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****原因描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="profile">${profile}</textarea>
		 		<span>
		 	
			 ${profile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$profile}
	
	
	${lz:set("注释","*****************关联订单字段的输入框代码*****************")}
	${lz:set("注释","before$order和after$order变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$order}
	<dl class="order " minor  ref="order" >
		<dt>关联订单:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCoin$order!=null">${csCoin$order}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={cscHost}" type="text" id="order" name="order" text="${get:CsOrder(order).csoId}" value="${order}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#order').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#order').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="order">${order}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${order}',{ctrl:{editable:false,visible:true}})">
			 ${order$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$order}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csCoin.cscId}" name="csCoin.cscId" id="cscId" />
					<button title="保存数据,关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="完成">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						完成&nbsp;&nbsp;</button>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:elseif>
<s:else>
	<form class="form editform" ref="CsCoin" id="coinForm" name="coinForm" action="${empty CTRL.action?"coin_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写红包相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCoinToken}" name="csCoinToken" id="csCoinToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCoin.cscId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscId和after$cscId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscId==true">
	${before$cscId}
	<dl class="cscId ${CTRL.e.cscId?"hidden":""}" major  ref="cscId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCoin$cscId!=null">${csCoin$cscId}</s:if><s:else>
			<input type="hidden" value="${csCoin.cscId}" name="csCoin.cscId" id="cscId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscId">${csCoin.cscId}</textarea>
		 		<span>
		 	
			 ${csCoin.cscId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscHost和after$cscHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscHost==true">
	${before$cscHost}
	<dl class="cscHost " major  ref="cscHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCoin$cscHost!=null">${csCoin$cscHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscHost" name="csCoin.cscHost">
		 				<option selected value="${csCoin.cscHost}">
		 					${get:SrvHost(csCoin.cscHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscHost">${csCoin.cscHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCoin.cscHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCoin.cscHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscHost}
	</s:if>
	
	
	<%-- ${lz:set("注释","*****************红包来源字段的输入框代码*****************")}
	${lz:set("注释","before$cscCoinSource和after$cscCoinSource变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscCoinSource==true">
	${before$cscCoinSource}
	<dl class="cscCoinSource " major  ref="cscCoinSource" >
		<dt>红包分类:</dt>
		<s:if test="#request.CTRL.e.cscCoinSource==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCoin$cscCoinSource!=null">${csCoin$cscCoinSource}</s:if><s:else>
 			<select class="combox narrow" action="${basePath}${proname}/user/coinsource_query.do?size=-1" id="cscCoinSource" name="csCoin.cscCoinSource">
 				<option selected value="${csCoin.cscCoinSource}">
 					${get:CsCoinSource(csCoin.cscCoinSource).cscsName}
 				</option>
 			</select>
 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscCoinSource').val())==''){return;};window.href('${basePath}${proname}/user/coinsource_details.do?key='+$('#cscCoinSource').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 <b>*</b>
	 	 <em>请设置红包分类</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscHost">${csCoin.cscHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/coinsource_details.do?key=${csCoin.cscCoinSource}',{ctrl:{editable:false,visible:true}})">
			 ${csCoin.cscCoinSource$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscCoinSource}
	</s:if> --%>
	
	
	${lz:set("注释","*****************所属会员字段的输入框代码*****************")}
	${lz:set("注释","before$cscMember和after$cscMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscMember==true">
	${before$cscMember}
	<dl class="cscMember " major  ref="cscMember" >
		<dt>所属会员:</dt>
		<s:if test="#request.CTRL.e.cscMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCoin$cscMember!=null">${csCoin$cscMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={cscHost}" type="text" id="cscMember" name="csCoin.cscMember" text="${get:CsMember(csCoin.cscMember).csmName}" value="${csCoin.cscMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cscMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscMember">${csCoin.cscMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csCoin.cscMember}',{ctrl:{editable:false,visible:true}})">
			 ${csCoin.cscMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscMember}
	</s:if>
	
	${lz:set("注释","*****************充值金额字段的输入框代码*****************")}
	${lz:set("注释","before$cscCount和after$cscCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscCount==true">
	${before$cscCount}
	<dl class="cscCount " major  ref="cscCount" >
		<dt>充值金额:</dt>
		<s:if test="#request.CTRL.e.cscCount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCoin$cscCount!=null">${csCoin$cscCount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCoin.cscCount" id="cscCount"  value="${csCoin.cscCount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入红包的充值金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****充值金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscCount">${csCoin.cscCount}</textarea>
		 		<span>
		 	
			 ${csCoin.cscCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscCount}
	</s:if>
	
	${lz:set("注释","*****************可用金额字段的输入框代码*****************")}
	${lz:set("注释","before$cscRemain和after$cscRemain变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscRemain==true">
	${before$cscRemain}
	<dl class="cscRemain " major  ref="cscRemain" >
		<dt>可用金额:</dt>
		<s:if test="#request.CTRL.e.cscRemain==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCoin$cscRemain!=null">${csCoin$cscRemain}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCoin.cscRemain" id="cscRemain"  value="${csCoin.cscRemain}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入红包的可用金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****可用金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscRemain">${csCoin.cscRemain}</textarea>
		 		<span>
		 	
			 ${csCoin.cscRemain$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscRemain}
	</s:if>
	
	${lz:set("注释","*****************有效期字段的输入框代码*****************")}
	${lz:set("注释","before$cscValidity和after$cscValidity变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscValidity==true">
	${before$cscValidity}
	<dl class="cscValidity " major  ref="cscValidity" style="width:98%;">
		<dt>原始有效期:</dt>
		<s:if test="#request.CTRL.e.cscValidity==true">
		${lz:set("haveEditable",true)}
		<dd input="radio">
		<s:if test="#request.csCoin$cscValidity!=null">${csCoin$cscValidity}</s:if><s:else>
		 	<div class='wide input'>
		 	&nbsp;
			<label>
				<input type="radio" value="1" name="csCoin.cscValidity" id="cscValidity" ${csCoin.cscValidity=='1'?"checked":""}/>1个月&nbsp;&nbsp;&nbsp;
			</label>
			<label>
				<input type="radio" value="2" name="csCoin.cscValidity" id="cscValidity" ${csCoin.cscValidity=='2'?"checked":""}/>2个月&nbsp;&nbsp;&nbsp;
			</label>
			<label>
				<input type="radio" value="3" name="csCoin.cscValidity" id="cscValidity" ${csCoin.cscValidity=='3'?"checked":""}/>3个月&nbsp;&nbsp;&nbsp;
			</label>
			<label>
				<input type="radio" value="4" name="csCoin.cscValidity" id="cscValidity" ${csCoin.cscValidity=='4'?"checked":""}/>4个月&nbsp;&nbsp;&nbsp;
			</label>
			<label>
				<input type="radio" value="5" name="csCoin.cscValidity" id="cscValidity" ${csCoin.cscValidity=='5'?"checked":""}/>5个月&nbsp;&nbsp;&nbsp;
			</label>
			<label>
				<input type="radio" value="6" name="csCoin.cscValidity" id="cscValidity" ${csCoin.cscValidity=='6'?"checked":""}/>半年&nbsp;&nbsp;&nbsp;
			</label>
			<label>
				<input type="radio" value="12" name="csCoin.cscValidity" id="cscValidity" ${csCoin.cscValidity=='12'?"checked":""}/>1年&nbsp;&nbsp;&nbsp;
			</label>
			<label class="validity_def"> 
 				<input type="radio" value="0" name="csCoin.cscValidity" id="cscValidity" ${csCoin.cscValidity=='0'?"checked":""}/>自定义&nbsp;&nbsp;&nbsp;
			</label> 
		 	</div>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择红包的原始有效期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****原始有效期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscValidity">${csCoin.cscValidity}</textarea>
		 		<span>
		 	
			 ${csCoin.cscValidity$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscValidity}
	</s:if>
	
	${lz:set("注释","*****************程序标识字段的输入框代码*****************")}
	${lz:set("注释","before$cscFlag和after$cscFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscFlag==true">
	${before$cscFlag}
	<dl class="cscFlag " minor  ref="cscFlag" >
		<dt>程序标识:</dt>
		<s:if test="#request.CTRL.e.cscFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCoin$cscFlag!=null">${csCoin$cscFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCoin.cscFlag" id="cscFlag"  value="${csCoin.cscFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入红包的程序标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscFlag">${csCoin.cscFlag}</textarea>
		 		<span>
		 	
			 ${csCoin.cscFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscFlag}
	</s:if>
	
	${lz:set("注释","*****************使用记录字段的输入框代码*****************")}
	${lz:set("注释","before$records和after$records变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.records==true">
	${before$records}
	<dl class="records " minor  ref="records" style="width:98%;">
		<dt>使用记录:</dt>
		<s:if test="#request.CTRL.e.records==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csCoin$records!=null">${csCoin$records}</s:if><s:else>
		  		<s:if test="csCoin.cscId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="records" name="records" frameborder='0' src="" url="${basePath}${proname}/user/userecord.do?inFrame=true&canQuery=false&all=true&csurCoin=${csCoin.cscId}&ctrl={queryable:false,fields:{csurCoin:{visible:true,editable:false,defaultValue:${csCoin.cscId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#records').attr('src',$('#records').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑使用记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csCoin.cscId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="records" name="records" frameborder='0' src="" url="${basePath}${proname}/user/userecord.do?inFrame=true&canQuery=false&all=true&csurCoin=${csCoin.cscId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#records').attr('src',$('#records').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$records}
	</s:if>
	
	${lz:set("注释","*****************结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscEnd和after$cscEnd变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscEnd==true">
	${before$cscEnd}
	<dl class="cscEnd " major  ref="cscEnd" >
		<dt>结束时间:</dt>
		<s:if test="#request.CTRL.e.cscEnd==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCoin$cscEnd!=null">${csCoin$cscEnd}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCoin.cscEnd" id="cscEnd"  value="<s:date name="csCoin.cscEnd" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择红包的结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscEnd">${csCoin.cscEnd}</textarea>
		 		<span>
		 	
			 ${csCoin.cscEnd$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscEnd}
	</s:if>
	
	${lz:set("注释","*****************序列号字段的输入框代码*****************")}
	${lz:set("注释","before$cscSerial和after$cscSerial变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscSerial==true">
	${before$cscSerial}
	<dl class="cscSerial " minor  ref="cscSerial" style="width:98%;">
		<dt>序&nbsp;&nbsp;列&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.cscSerial==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCoin$cscSerial!=null">${csCoin$cscSerial}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="50" name="csCoin.cscSerial" id="cscSerial"  value="${csCoin.cscSerial}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入红包的序列号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****序列号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscSerial">${csCoin.cscSerial}</textarea>
		 		<span>
		 	
			 ${csCoin.cscSerial$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscSerial}
	</s:if>
	
	${lz:set("注释","*****************充值人员字段的输入框代码*****************")}
	${lz:set("注释","before$cscEditor和after$cscEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscEditor==true">
	${before$cscEditor}
	<dl class="cscEditor " major  ref="cscEditor" >
		<dt>充值人员:</dt>
		<s:if test="#request.CTRL.e.cscEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCoin$cscEditor!=null">${csCoin$cscEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cscEditor" name="csCoin.cscEditor">
		 				<option selected value="${csCoin.cscEditor}">
		 					${get:SrvUser(csCoin.cscEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cscEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****充值人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscEditor">${csCoin.cscEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csCoin.cscEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csCoin.cscEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscUpdateTime和after$cscUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscUpdateTime==true">
	${before$cscUpdateTime}
	<dl class="cscUpdateTime " major  ref="cscUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cscUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCoin$cscUpdateTime!=null">${csCoin$cscUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCoin.cscUpdateTime" id="cscUpdateTime"  value="<s:date name="csCoin.cscUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择红包的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscUpdateTime">${csCoin.cscUpdateTime}</textarea>
		 		<span>
		 	
			 ${csCoin.cscUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscAddTime和after$cscAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscAddTime==true">
	${before$cscAddTime}
	<dl class="cscAddTime " major  ref="cscAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCoin$cscAddTime!=null">${csCoin$cscAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCoin.cscAddTime" id="cscAddTime"  value="<s:date name="csCoin.cscAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择红包的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAddTime">${csCoin.cscAddTime}</textarea>
		 		<span>
		 	
			 ${csCoin.cscAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$cscRemark和after$cscRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscRemark==true">
	${before$cscRemark}
	<dl class="cscRemark " minor  ref="cscRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.cscRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCoin$cscRemark!=null">${csCoin$cscRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cscRemark" name="csCoin.cscRemark" rows="5">${csCoin.cscRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入红包的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCoin.cscRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscStatus和after$cscStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscStatus==true">
	${before$cscStatus}
	<dl class="cscStatus " major  ref="cscStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCoin$cscStatus!=null">${csCoin$cscStatus}</s:if><s:else>
		 	<select class="narrow" id="cscStatus" name="csCoin.cscStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csCoin.cscStatus==1?"selected":""}>正常</option>
				<option value="2" ${csCoin.cscStatus==2?"selected":""}>已过期</option>
				<option value="3" ${csCoin.cscStatus==3?"selected":""}>用完</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择红包的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscStatus">${csCoin.cscStatus}</textarea>
		 		<span>
		 	
			 ${csCoin.cscStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCoin.cscId}" name="csCoin.cscId" id="cscId" />
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