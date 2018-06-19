<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/cashrecord.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/finance/cashrecord.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCashRecord.cscrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscrId,cscrHost,cscrTradeNo,cscrAmmount,cscrTitle,io,cscrImage",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscrId,cscrHost,cscrTradeNo,cscrAmmount,cscrTitle,io,cscrImage",
	</s:if>
	<s:elseif test="#request.CTRL.title=='提款'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscrHost,start,finish,io,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscrHost,cscrAmmount,start,finish,io,nothing",
	</s:elseif>
	<s:elseif test="#request.CTRL.title=='审核'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"io,cscrRemark,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscrHost,cscrTitle,cscrAmmount,cscrAdder,cscrAddTime,cscrStatus,io,cscrRemark,nothing",
	</s:elseif>
	<s:elseif test="#request.CTRL.title=='完成'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"io,cscrVoucher,cscrImage,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscrHost,cscrTitle,cscrAmmount,cscrAdder,io,cscrRemark,cscrAddTime,cscrStatus,cscrVoucher,cscrImage,nothing",
	</s:elseif>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscrId,cscrHost,cscrTradeNo,cscrAmmount,cscrTitle,io,cscrImage",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscrId,cscrHost,cscrTradeNo,cscrAmmount,cscrTitle,io,cscrVoucher,cscrImage,cscrAdder,cscrChecker",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"提现记录编辑":CTRL.title}</title>
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
<s:if test="csCashRecord.cscrId!=null">
<lz:set name="before$io">
	<dl style="width:98%;">
		<dt style="height:32px;">&nbsp;</dt>
		<dd>
			<a class="button" type="button" target="_blank" onclick="$(this).replaceWith('正在下载文件，请稍等...')"
				href="operecord.do?csorCashNo=${csCashRecord.cscrId}&export=true&size=65535&keys=csorTitle&keys=csorMoneyType&keys=csorRecordType&keys=csorIn&keys=csorOut&keys=csorOrder&keys=csorDateTime"
				style="display:block;text-align:center;line-height:32px;width:620px;height:32px;margin:0px;padding:0px;">点击下载详单</a>
		</dd>
	</dl>
</lz:set>
</s:if>


<s:if test="#request.CTRL.title=='提款'">
	<script>
	$(function(){
		$("#start").attr("onclick","").click(function(){
			//开始日期为今天之前的
			WdatePicker((function(){
				var d1=1000*60*60*24*1;//1天
				return {
					el:"start",
					maxDate:$.formatDate("yyyy-MM-dd",new Date(new Date().getTime()-d1)),
					dateFmt:'yyyy-MM-dd'
				};
			})());
		});
		$("#finish").attr("onclick","").click(function(){
			WdatePicker((function(){
				var d1=1000*60*60*24*1;//1天
				return {
					el:"finish",
					minDate:$("#start").val(),
					maxDate:$.formatDate("yyyy-MM-dd",new Date(new Date().getTime()-d1)),
					dateFmt:'yyyy-MM-dd'
				};
			})());
		});
		$("#start,#finish,#cscrHost").change(function(){
			if($("#start").val()==""||$("#finish").val()==""||$("#cscrHost").val()=="")
				return;
			var json = $.getObject("cashrecord_info.do",{
				"start":$("#start").val(),
				"finish":$("#finish").val(),
				"host":$("#cscrHost").val()
			});
			if(!json){
				Alert("系统繁忙，请稍候再试");
				$("#cscrAmmount").next("span").html("&nbsp;");
				$(".form dl[ref=io] dd").html("&nbsp;");
				return;
			}
			if(!json.success){
				Alert(json.message);
				$("#cscrAmmount").next("span").html("&nbsp;");
				$(".form dl[ref=io] dd").html("&nbsp;");
				return;
			}
			$("#cscrAmmount").next("span").html(json.value).css("font-size","18px").css("color","red").css("font-weight","bold");
			
			//显示提取详情列表
			var html="<iframe class='state-input' onload=\"var d=$(this.contentWindow.document);var b=d.find('body');$(this).attr('height',d.height());if(d.height()>b.height())$(this).attr('height',d.height()+(d.height()-b.height()));\" id='iframe_${uuid}' name='iframe_${uuid}' frameborder='0' src='' width='620' height='0'></iframe>";
			$(".form dl[ref=io] dd").html(html);
			var url = "operecord.do";
			var params = {
				"csorDateTimeStart":$("#start").val(),
				"csorDateTimeEnd":$("#finish").val(),
				"csorHost":$("#cscrHost").val()
			};
			params.all=false;
			params.canQuery=false;
			params.ctrl={
				mutual:'dialog',
				queryable:false,
				editable:false,
				canAdd:false,
				canEdit:false,
				canDel:false,
				canView:false,
				fields:{
					csorTitle:{listable:false},
					csorMoneyType:{listable:false},
					csorRecordType:{listable:false},
					csorIn:{listable:false},
					csorOut:{listable:false}		
				}
			};	
			$.submit(url,params,$(".form iframe").attr("name"));
		});
		$("#cashrecordForm").submit(function(){
			
		});
	});
	</script>
</s:if>
<s:if test="#request.CTRL.title=='审核' && csCashRecord.cscrStatus!=1">
	${lz:set("message","只有待审核的提现才能进行审核操作")}
	<lz:set name="jsonString">
		{
			title:"不能审核",
			editable:false,
			visible:false
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
</s:if>


<s:if test="#request.CTRL.title=='完成' && csCashRecord.cscrStatus!=2">
	${lz:set("message","只有已审核的提现才能进行完成操作")}
	<lz:set name="jsonString">
		{
			title:"不能完成",
			editable:false,
			visible:false
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
</s:if>
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
	$("#cashrecordForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscrId==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrHost==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrTradeNo==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrTradeNo":function(el){
			if(jQuery.trim(el.value)=="")
				return "流水号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "流水号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrAmmount==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrAmmount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "提取额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrTitle==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "摘要不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "摘要最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrVoucher==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrVoucher":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "付款信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrImage==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrImage":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrAdder==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrChecker==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrChecker":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrRemark==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrAddTime==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrStatus==true">
		${lz:set("haveEditable",true)}
		,"csCashRecord.cscrStatus":function(el){
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
<s:elseif test="#request.CTRL.title=='提款'">
	<form class="form editform" ref="CsCashRecord" id="cashrecordForm" name="cashrecordForm" action="${empty CTRL.action?"cashrecord_withdraw.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写提现记录相关信息，<span class="extrude">"*" 为必填选项。</span>
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
				<input type="hidden" value="${csCashRecordToken}" name="csCashRecordToken" id="csCashRecordToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscrHost和after$cscrHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrHost}
	<dl class="cscrHost " major  ref="cscrHost" >
		<dt>城　　市:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCashRecord$cscrHost!=null">${csCashRecord$cscrHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscrHost" name="csCashRecord.cscrHost">
		 				<option selected value="${csCashRecord.cscrHost}">
		 					${get:SrvHost(csCashRecord.cscrHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscrHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscrHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrHost">${csCashRecord.cscrHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCashRecord.cscrHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCashRecord.cscrHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrHost}
	
	
	${lz:set("注释","*****************提取额字段的输入框代码*****************")}
	${lz:set("注释","before$cscrAmmount和after$cscrAmmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrAmmount}
	<dl class="cscrAmmount " major  ref="cscrAmmount" >
		<dt>提&nbsp;&nbsp;取&nbsp;&nbsp;额:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCashRecord$cscrAmmount!=null">${csCashRecord$cscrAmmount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCashRecord.cscrAmmount" id="cscrAmmount"  value="${csCashRecord.cscrAmmount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入提现记录的提取额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****提取额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrAmmount">${csCashRecord.cscrAmmount}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrAmmount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrAmmount}
	
	
	${lz:set("注释","*****************开始日期字段的输入框代码*****************")}
	${lz:set("注释","before$start和after$start变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$start}
	<dl class="start " major  ref="start" >
		<dt>开始日期:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCashRecord$start!=null">${csCashRecord$start}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="start" id="start"  value="<s:date name="start" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择提现记录的开始日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开始日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="start">${start}</textarea>
		 		<span>
		 	
			 ${start$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$start}
	
	
	${lz:set("注释","*****************结束日期字段的输入框代码*****************")}
	${lz:set("注释","before$finish和after$finish变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$finish}
	<dl class="finish " major  ref="finish" >
		<dt>结束日期:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCashRecord$finish!=null">${csCashRecord$finish}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="finish" id="finish"  value="<s:date name="finish" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择提现记录的结束日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="finish">${finish}</textarea>
		 		<span>
		 	
			 ${finish$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$finish}
	
	
	${lz:set("注释","*****************详单明细字段的输入框代码*****************")}
	${lz:set("注释","before$io和after$io变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$io}
	<dl class="io " minor  ref="io" style="width:98%;">
		<dt>详单明细:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csCashRecord$io!=null">${csCashRecord$io}</s:if><s:else>
		  		<s:if test="csCashRecord.cscrId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="io" name="io" frameborder='0' src="" url="${basePath}${proname}/finance/operecord.do?inFrame=true&canQuery=false&fields=csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorOrder,csorStatus&csorCashNo=${csCashRecord.cscrId}&ctrl={queryable:false,fields:{csorCashNo:{visible:true,editable:false,defaultValue:${csCashRecord.cscrId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#io').attr('src',$('#io').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑详单明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****详单明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csCashRecord.cscrId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="io" name="io" frameborder='0' src="" url="${basePath}${proname}/finance/operecord.do?inFrame=true&canQuery=false&fields=csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorOrder,csorStatus&csorCashNo=${csCashRecord.cscrId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#io').attr('src',$('#io').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$io}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csCashRecord.cscrId}" name="csCashRecord.cscrId" id="cscrId" />
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
<s:elseif test="#request.CTRL.title=='审核'">
	<form class="form editform" ref="CsCashRecord" id="cashrecordForm" name="cashrecordForm" action="${empty CTRL.action?"cashrecord_verify.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写提现记录相关信息，<span class="extrude">"*" 为必填选项。</span>
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
				<input type="hidden" value="${csCashRecordToken}" name="csCashRecordToken" id="csCashRecordToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscrHost和after$cscrHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrHost}
	<dl class="cscrHost " major  ref="cscrHost" >
		<dt>城　　市:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCashRecord$cscrHost!=null">${csCashRecord$cscrHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscrHost" name="csCashRecord.cscrHost">
		 				<option selected value="${csCashRecord.cscrHost}">
		 					${get:SrvHost(csCashRecord.cscrHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscrHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscrHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrHost">${csCashRecord.cscrHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCashRecord.cscrHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCashRecord.cscrHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrHost}
	
	
	${lz:set("注释","*****************摘要字段的输入框代码*****************")}
	${lz:set("注释","before$cscrTitle和after$cscrTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrTitle}
	<dl class="cscrTitle " major  ref="cscrTitle" style="width:98%;">
		<dt>摘　　要:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCashRecord$cscrTitle!=null">${csCashRecord$cscrTitle}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csCashRecord.cscrTitle" id="cscrTitle"  value="${csCashRecord.cscrTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入提现记录的摘要</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****摘要字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscrTitle">${csCashRecord.cscrTitle}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrTitle}
	
	
	${lz:set("注释","*****************提取额字段的输入框代码*****************")}
	${lz:set("注释","before$cscrAmmount和after$cscrAmmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrAmmount}
	<dl class="cscrAmmount " major  ref="cscrAmmount" >
		<dt>提&nbsp;&nbsp;取&nbsp;&nbsp;额:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCashRecord$cscrAmmount!=null">${csCashRecord$cscrAmmount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCashRecord.cscrAmmount" id="cscrAmmount"  value="${csCashRecord.cscrAmmount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入提现记录的提取额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****提取额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrAmmount">${csCashRecord.cscrAmmount}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrAmmount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrAmmount}
	
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$cscrAdder和after$cscrAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrAdder}
	<dl class="cscrAdder " major  ref="cscrAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCashRecord$cscrAdder!=null">${csCashRecord$cscrAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cscrAdder" name="csCashRecord.cscrAdder">
		 				<option selected value="${csCashRecord.cscrAdder}">
		 					${get:SrvUser(csCashRecord.cscrAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscrAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cscrAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrAdder">${csCashRecord.cscrAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csCashRecord.cscrAdder}',{ctrl:{editable:false,visible:true}})">
			 ${csCashRecord.cscrAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrAdder}
	
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscrAddTime和after$cscrAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrAddTime}
	<dl class="cscrAddTime " major  ref="cscrAddTime" >
		<dt>添加时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCashRecord$cscrAddTime!=null">${csCashRecord$cscrAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCashRecord.cscrAddTime" id="cscrAddTime"  value="<s:date name="csCashRecord.cscrAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择提现记录的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrAddTime">${csCashRecord.cscrAddTime}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrAddTime}
	
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscrStatus和after$cscrStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrStatus}
	<dl class="cscrStatus " major  ref="cscrStatus" >
		<dt>状　　态:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCashRecord$cscrStatus!=null">${csCashRecord$cscrStatus}</s:if><s:else>
		 	<select class="narrow" id="cscrStatus" name="csCashRecord.cscrStatus">
		 		<option value="">请选择</option>
		 		 <option value="">无法自动构造</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择提现记录的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrStatus">${csCashRecord.cscrStatus}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrStatus}
	
	
	${lz:set("注释","*****************详单明细字段的输入框代码*****************")}
	${lz:set("注释","before$io和after$io变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$io}
	<dl class="io " minor  ref="io" style="width:98%;">
		<dt>详单明细:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csCashRecord$io!=null">${csCashRecord$io}</s:if><s:else>
		  		<s:if test="csCashRecord.cscrId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="io" name="io" frameborder='0' src="" url="${basePath}${proname}/finance/operecord.do?inFrame=true&canQuery=false&fields=csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorOrder,csorStatus&csorCashNo=${csCashRecord.cscrId}&ctrl={queryable:false,fields:{csorCashNo:{visible:true,editable:false,defaultValue:${csCashRecord.cscrId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#io').attr('src',$('#io').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑详单明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****详单明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csCashRecord.cscrId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="io" name="io" frameborder='0' src="" url="${basePath}${proname}/finance/operecord.do?inFrame=true&canQuery=false&fields=csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorOrder,csorStatus&csorCashNo=${csCashRecord.cscrId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#io').attr('src',$('#io').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$io}
	
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$cscrRemark和after$cscrRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrRemark}
	<dl class="cscrRemark " major  ref="cscrRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCashRecord$cscrRemark!=null">${csCashRecord$cscrRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cscrRemark" name="csCashRecord.cscrRemark" rows="5">${csCashRecord.cscrRemark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入提现记录的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCashRecord.cscrRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrRemark}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csCashRecord.cscrId}" name="csCashRecord.cscrId" id="cscrId" />
						<button class="button" type="submit" name="submiter" value="审核通过">
						&nbsp;&nbsp;审核通过&nbsp;&nbsp;</button>
						<button class="button" type="submit" name="submiter" value="审核不通过">
						&nbsp;&nbsp;审核不通过&nbsp;&nbsp;</button>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:elseif>
<s:elseif test="#request.CTRL.title=='完成'">
	<form class="form editform" ref="CsCashRecord" id="cashrecordForm" name="cashrecordForm" action="${empty CTRL.action?"cashrecord_pay.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写提现记录相关信息，<span class="extrude">"*" 为必填选项。</span>
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
				<input type="hidden" value="${csCashRecordToken}" name="csCashRecordToken" id="csCashRecordToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscrHost和after$cscrHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrHost}
	<dl class="cscrHost " major  ref="cscrHost" >
		<dt>城　　市:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCashRecord$cscrHost!=null">${csCashRecord$cscrHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscrHost" name="csCashRecord.cscrHost">
		 				<option selected value="${csCashRecord.cscrHost}">
		 					${get:SrvHost(csCashRecord.cscrHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscrHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscrHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrHost">${csCashRecord.cscrHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCashRecord.cscrHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCashRecord.cscrHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrHost}
	
	
	${lz:set("注释","*****************摘要字段的输入框代码*****************")}
	${lz:set("注释","before$cscrTitle和after$cscrTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrTitle}
	<dl class="cscrTitle " major  ref="cscrTitle" style="width:98%;">
		<dt>摘　　要:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCashRecord$cscrTitle!=null">${csCashRecord$cscrTitle}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csCashRecord.cscrTitle" id="cscrTitle"  value="${csCashRecord.cscrTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入提现记录的摘要</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****摘要字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscrTitle">${csCashRecord.cscrTitle}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrTitle}
	
	
	${lz:set("注释","*****************提取额字段的输入框代码*****************")}
	${lz:set("注释","before$cscrAmmount和after$cscrAmmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrAmmount}
	<dl class="cscrAmmount " major  ref="cscrAmmount" >
		<dt>提&nbsp;&nbsp;取&nbsp;&nbsp;额:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCashRecord$cscrAmmount!=null">${csCashRecord$cscrAmmount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCashRecord.cscrAmmount" id="cscrAmmount"  value="${csCashRecord.cscrAmmount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入提现记录的提取额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****提取额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrAmmount">${csCashRecord.cscrAmmount}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrAmmount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrAmmount}
	
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$cscrAdder和after$cscrAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrAdder}
	<dl class="cscrAdder " major  ref="cscrAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCashRecord$cscrAdder!=null">${csCashRecord$cscrAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cscrAdder" name="csCashRecord.cscrAdder">
		 				<option selected value="${csCashRecord.cscrAdder}">
		 					${get:SrvUser(csCashRecord.cscrAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscrAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cscrAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrAdder">${csCashRecord.cscrAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csCashRecord.cscrAdder}',{ctrl:{editable:false,visible:true}})">
			 ${csCashRecord.cscrAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrAdder}
	
	
	${lz:set("注释","*****************详单明细字段的输入框代码*****************")}
	${lz:set("注释","before$io和after$io变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$io}
	<dl class="io " minor  ref="io" style="width:98%;">
		<dt>详单明细:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csCashRecord$io!=null">${csCashRecord$io}</s:if><s:else>
		  		<s:if test="csCashRecord.cscrId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="io" name="io" frameborder='0' src="" url="${basePath}${proname}/finance/operecord.do?inFrame=true&canQuery=false&fields=csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorOrder,csorStatus&csorCashNo=${csCashRecord.cscrId}&ctrl={queryable:false,fields:{csorCashNo:{visible:true,editable:false,defaultValue:${csCashRecord.cscrId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#io').attr('src',$('#io').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑详单明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****详单明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csCashRecord.cscrId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="io" name="io" frameborder='0' src="" url="${basePath}${proname}/finance/operecord.do?inFrame=true&canQuery=false&fields=csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorOrder,csorStatus&csorCashNo=${csCashRecord.cscrId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#io').attr('src',$('#io').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$io}
	
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$cscrRemark和after$cscrRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrRemark}
	<dl class="cscrRemark " major  ref="cscrRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCashRecord$cscrRemark!=null">${csCashRecord$cscrRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cscrRemark" name="csCashRecord.cscrRemark" rows="5">${csCashRecord.cscrRemark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入提现记录的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCashRecord.cscrRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrRemark}
	
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscrAddTime和after$cscrAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrAddTime}
	<dl class="cscrAddTime " major  ref="cscrAddTime" >
		<dt>添加时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCashRecord$cscrAddTime!=null">${csCashRecord$cscrAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCashRecord.cscrAddTime" id="cscrAddTime"  value="<s:date name="csCashRecord.cscrAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择提现记录的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrAddTime">${csCashRecord.cscrAddTime}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrAddTime}
	
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscrStatus和after$cscrStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrStatus}
	<dl class="cscrStatus " major  ref="cscrStatus" >
		<dt>状　　态:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCashRecord$cscrStatus!=null">${csCashRecord$cscrStatus}</s:if><s:else>
		 	<select class="narrow" id="cscrStatus" name="csCashRecord.cscrStatus">
		 		<option value="">请选择</option>
		 		 <option value="">无法自动构造</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择提现记录的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrStatus">${csCashRecord.cscrStatus}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrStatus}
	
	
	${lz:set("注释","*****************付款信息字段的输入框代码*****************")}
	${lz:set("注释","before$cscrVoucher和after$cscrVoucher变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrVoucher}
	<dl class="cscrVoucher " major  ref="cscrVoucher" style="width:98%;">
		<dt>付款信息:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCashRecord$cscrVoucher!=null">${csCashRecord$cscrVoucher}</s:if><s:else>
		 	<textarea class="input wide"  id="cscrVoucher" name="csCashRecord.cscrVoucher" rows="5">${csCashRecord.cscrVoucher}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>描述财务打款的信息，方便查账</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****付款信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCashRecord.cscrVoucher$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrVoucher}
	
	
	${lz:set("注释","*****************付款截图字段的输入框代码*****************")}
	${lz:set("注释","before$cscrImage和after$cscrImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscrImage}
	<dl class="cscrImage " major  ref="cscrImage" style="width:98%;">
		<dt>付款截图:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csCashRecord$cscrImage!=null">${csCashRecord$cscrImage}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csCashRecord.cscrImage" id="cscrImage"  value="${csCashRecord.cscrImage}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#cscrImage').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#cscrImage').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#cscrImage').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请上传提现记录的付款截图</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****付款截图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscrImage">${csCashRecord.cscrImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csCashRecord.cscrImage))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csCashRecord.cscrImage}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrImage}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csCashRecord.cscrId}" name="csCashRecord.cscrId" id="cscrId" />
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
	<form class="form editform" ref="CsCashRecord" id="cashrecordForm" name="cashrecordForm" action="${empty CTRL.action?"cashrecord_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写提现记录相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCashRecordToken}" name="csCashRecordToken" id="csCashRecordToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCashRecord.cscrId))}		
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
		<s:if test="#request.csCashRecord$cscrId!=null">${csCashRecord$cscrId}</s:if><s:else>
			<input type="hidden" value="${csCashRecord.cscrId}" name="csCashRecord.cscrId" id="cscrId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrId">${csCashRecord.cscrId}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscrHost和after$cscrHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrHost==true">
	${before$cscrHost}
	<dl class="cscrHost " major  ref="cscrHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscrHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCashRecord$cscrHost!=null">${csCashRecord$cscrHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscrHost" name="csCashRecord.cscrHost">
		 				<option selected value="${csCashRecord.cscrHost}">
		 					${get:SrvHost(csCashRecord.cscrHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscrHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscrHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrHost">${csCashRecord.cscrHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCashRecord.cscrHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCashRecord.cscrHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrHost}
	</s:if>
	
	${lz:set("注释","*****************流水号字段的输入框代码*****************")}
	${lz:set("注释","before$cscrTradeNo和after$cscrTradeNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrTradeNo==true">
	${before$cscrTradeNo}
	<dl class="cscrTradeNo " major  ref="cscrTradeNo" >
		<dt>流&nbsp;&nbsp;水&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.cscrTradeNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCashRecord$cscrTradeNo!=null">${csCashRecord$cscrTradeNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCashRecord.cscrTradeNo" id="cscrTradeNo"  value="${csCashRecord.cscrTradeNo}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>运营商+城市+年月日+年月日</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****流水号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrTradeNo">${csCashRecord.cscrTradeNo}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrTradeNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrTradeNo}
	</s:if>
	
	${lz:set("注释","*****************提取额字段的输入框代码*****************")}
	${lz:set("注释","before$cscrAmmount和after$cscrAmmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrAmmount==true">
	${before$cscrAmmount}
	<dl class="cscrAmmount " major  ref="cscrAmmount" >
		<dt>提&nbsp;&nbsp;取&nbsp;&nbsp;额:</dt>
		<s:if test="#request.CTRL.e.cscrAmmount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCashRecord$cscrAmmount!=null">${csCashRecord$cscrAmmount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCashRecord.cscrAmmount" id="cscrAmmount"  value="${csCashRecord.cscrAmmount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入提现记录的提取额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****提取额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrAmmount">${csCashRecord.cscrAmmount}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrAmmount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrAmmount}
	</s:if>
	
	${lz:set("注释","*****************摘要字段的输入框代码*****************")}
	${lz:set("注释","before$cscrTitle和after$cscrTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrTitle==true">
	${before$cscrTitle}
	<dl class="cscrTitle " major  ref="cscrTitle" style="width:98%;">
		<dt>摘　　要:</dt>
		<s:if test="#request.CTRL.e.cscrTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCashRecord$cscrTitle!=null">${csCashRecord$cscrTitle}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csCashRecord.cscrTitle" id="cscrTitle"  value="${csCashRecord.cscrTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入提现记录的摘要</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****摘要字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscrTitle">${csCashRecord.cscrTitle}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrTitle}
	</s:if>
	
	${lz:set("注释","*****************详单明细字段的输入框代码*****************")}
	${lz:set("注释","before$io和after$io变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.io==true">
	${before$io}
	<dl class="io " minor  ref="io" style="width:98%;">
		<dt>详单明细:</dt>
		<s:if test="#request.CTRL.e.io==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csCashRecord$io!=null">${csCashRecord$io}</s:if><s:else>
		  		<s:if test="csCashRecord.cscrId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="io" name="io" frameborder='0' src="" url="${basePath}${proname}/finance/operecord.do?inFrame=true&canQuery=false&fields=csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorOrder,csorStatus&csorCashNo=${csCashRecord.cscrId}&ctrl={queryable:false,fields:{csorCashNo:{visible:true,editable:false,defaultValue:${csCashRecord.cscrId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#io').attr('src',$('#io').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑详单明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****详单明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csCashRecord.cscrId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="io" name="io" frameborder='0' src="" url="${basePath}${proname}/finance/operecord.do?inFrame=true&canQuery=false&fields=csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorOrder,csorStatus&csorCashNo=${csCashRecord.cscrId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#io').attr('src',$('#io').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$io}
	</s:if>
	
	${lz:set("注释","*****************付款信息字段的输入框代码*****************")}
	${lz:set("注释","before$cscrVoucher和after$cscrVoucher变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrVoucher==true">
	${before$cscrVoucher}
	<dl class="cscrVoucher " major  ref="cscrVoucher" style="width:98%;">
		<dt>付款信息:</dt>
		<s:if test="#request.CTRL.e.cscrVoucher==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCashRecord$cscrVoucher!=null">${csCashRecord$cscrVoucher}</s:if><s:else>
		 	<textarea class="input wide"  id="cscrVoucher" name="csCashRecord.cscrVoucher" rows="5">${csCashRecord.cscrVoucher}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>描述财务打款的信息，方便查账</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****付款信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCashRecord.cscrVoucher$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrVoucher}
	</s:if>
	
	${lz:set("注释","*****************付款截图字段的输入框代码*****************")}
	${lz:set("注释","before$cscrImage和after$cscrImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrImage==true">
	${before$cscrImage}
	<dl class="cscrImage " minor  ref="cscrImage" style="width:98%;">
		<dt>付款截图:</dt>
		<s:if test="#request.CTRL.e.cscrImage==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csCashRecord$cscrImage!=null">${csCashRecord$cscrImage}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csCashRecord.cscrImage" id="cscrImage"  value="${csCashRecord.cscrImage}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#cscrImage').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#cscrImage').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#cscrImage').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传提现记录的付款截图</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****付款截图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscrImage">${csCashRecord.cscrImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csCashRecord.cscrImage))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csCashRecord.cscrImage}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrImage}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$cscrAdder和after$cscrAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrAdder==true">
	${before$cscrAdder}
	<dl class="cscrAdder " major  ref="cscrAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cscrAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCashRecord$cscrAdder!=null">${csCashRecord$cscrAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cscrAdder" name="csCashRecord.cscrAdder">
		 				<option selected value="${csCashRecord.cscrAdder}">
		 					${get:SrvUser(csCashRecord.cscrAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscrAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cscrAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrAdder">${csCashRecord.cscrAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csCashRecord.cscrAdder}',{ctrl:{editable:false,visible:true}})">
			 ${csCashRecord.cscrAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrAdder}
	</s:if>
	
	${lz:set("注释","*****************审核人字段的输入框代码*****************")}
	${lz:set("注释","before$cscrChecker和after$cscrChecker变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrChecker==true">
	${before$cscrChecker}
	<dl class="cscrChecker " minor  ref="cscrChecker" >
		<dt>审&nbsp;&nbsp;核&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cscrChecker==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCashRecord$cscrChecker!=null">${csCashRecord$cscrChecker}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cscrChecker" name="csCashRecord.cscrChecker">
		 				<option selected value="${csCashRecord.cscrChecker}">
		 					${get:SrvUser(csCashRecord.cscrChecker).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscrChecker').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cscrChecker').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****审核人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrChecker">${csCashRecord.cscrChecker}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csCashRecord.cscrChecker}',{ctrl:{editable:false,visible:true}})">
			 ${csCashRecord.cscrChecker$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrChecker}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$cscrRemark和after$cscrRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrRemark==true">
	${before$cscrRemark}
	<dl class="cscrRemark " major  ref="cscrRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.cscrRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCashRecord$cscrRemark!=null">${csCashRecord$cscrRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cscrRemark" name="csCashRecord.cscrRemark" rows="5">${csCashRecord.cscrRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入提现记录的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCashRecord.cscrRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrRemark}
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
		<s:if test="#request.csCashRecord$cscrUpdateTime!=null">${csCashRecord$cscrUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCashRecord.cscrUpdateTime" id="cscrUpdateTime"  value="<s:date name="csCashRecord.cscrUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择提现记录的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrUpdateTime">${csCashRecord.cscrUpdateTime}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrUpdateTime$}
	 	  
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
		<s:if test="#request.csCashRecord$cscrAddTime!=null">${csCashRecord$cscrAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCashRecord.cscrAddTime" id="cscrAddTime"  value="<s:date name="csCashRecord.cscrAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择提现记录的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrAddTime">${csCashRecord.cscrAddTime}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrAddTime$}
	 	  
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
		<s:if test="#request.csCashRecord$cscrStatus!=null">${csCashRecord$cscrStatus}</s:if><s:else>
		 	<select class="narrow" id="cscrStatus" name="csCashRecord.cscrStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csCashRecord.cscrStatus==1?"selected":""}>待审核</option>
				<option value="2" ${csCashRecord.cscrStatus==2?"selected":""}>已审核</option>
				<option value="3" ${csCashRecord.cscrStatus==3?"selected":""}>已完成</option>
				<option value="0" ${csCashRecord.cscrStatus==0?"selected":""}>未通过</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择提现记录的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrStatus">${csCashRecord.cscrStatus}</textarea>
		 		<span>
		 	
			 ${csCashRecord.cscrStatus$}
	 	  
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
					<input type="hidden" value="${csCashRecord.cscrId}" name="csCashRecord.cscrId" id="cscrId" />
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
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>