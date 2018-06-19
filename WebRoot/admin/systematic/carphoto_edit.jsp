<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/carphoto.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/carphoto.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCarPhoto.cscpId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscpId,cscpHost,cscpCar,cscpDate,cscpImages,cscpRemark",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscpId,cscpHost,cscpCar,cscpDate,cscpImages,cscpRemark",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscpId,cscpHost,cscpCar,cscpDate,cscpImages,cscpRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscpId,cscpHost,cscpCar,cscpDate,cscpImages,cscpRemark",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车辆照片编辑":CTRL.title}</title>
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
	$("#carphotoForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscpId==true">
		${lz:set("haveEditable",true)}
		,"csCarPhoto.cscpId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscpHost==true">
		${lz:set("haveEditable",true)}
		,"csCarPhoto.cscpHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscpCar==true">
		${lz:set("haveEditable",true)}
		,"csCarPhoto.cscpCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscpDate==true">
		${lz:set("haveEditable",true)}
		,"csCarPhoto.cscpDate":function(el){
			if(jQuery.trim(el.value)=="")
				return "照片日期不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscpImages==true">
		${lz:set("haveEditable",true)}
		,"csCarPhoto.cscpImages":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscpEditor==true">
		${lz:set("haveEditable",true)}
		,"csCarPhoto.cscpEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscpRemark==true">
		${lz:set("haveEditable",true)}
		,"csCarPhoto.cscpRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscpAddTime==true">
		${lz:set("haveEditable",true)}
		,"csCarPhoto.cscpAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
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
	<form class="form editform" ref="CsCarPhoto" id="carphotoForm" name="carphotoForm" action="${empty CTRL.action?"carphoto_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车辆照片相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCarPhotoToken}" name="csCarPhotoToken" id="csCarPhotoToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCarPhoto.cscpId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscpId和after$cscpId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscpId==true">
	${before$cscpId}
	<dl class="cscpId ${CTRL.e.cscpId?"hidden":""}" major  ref="cscpId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscpId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCarPhoto$cscpId!=null">${csCarPhoto$cscpId}</s:if><s:else>
			<input type="hidden" value="${csCarPhoto.cscpId}" name="csCarPhoto.cscpId" id="cscpId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscpId">${csCarPhoto.cscpId}</textarea>
		 		<span>
		 	
			 ${csCarPhoto.cscpId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscpId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscpHost和after$cscpHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscpHost==true">
	${before$cscpHost}
	<dl class="cscpHost " major  ref="cscpHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscpHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCarPhoto$cscpHost!=null">${csCarPhoto$cscpHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscpHost" name="csCarPhoto.cscpHost">
		 				<option selected value="${csCarPhoto.cscpHost}">
		 					${get:SrvHost(csCarPhoto.cscpHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscpHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscpHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscpHost">${csCarPhoto.cscpHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCarPhoto.cscpHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCarPhoto.cscpHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscpHost}
	</s:if>
	
	${lz:set("注释","*****************所属车辆字段的输入框代码*****************")}
	${lz:set("注释","before$cscpCar和after$cscpCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscpCar==true">
	${before$cscpCar}
	<dl class="cscpCar " major  ref="cscpCar" >
		<dt>所属车辆:</dt>
		<s:if test="#request.CTRL.e.cscpCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCarPhoto$cscpCar!=null">${csCarPhoto$cscpCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cscpHost}" type="text" id="cscpCar" name="csCarPhoto.cscpCar" text="${get:CsCar(csCarPhoto.cscpCar).cscNumber}" value="${csCarPhoto.cscpCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscpCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#cscpCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscpCar">${csCarPhoto.cscpCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csCarPhoto.cscpCar}',{ctrl:{editable:false,visible:true}})">
			 ${csCarPhoto.cscpCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscpCar}
	</s:if>
	
	${lz:set("注释","*****************照片日期字段的输入框代码*****************")}
	${lz:set("注释","before$cscpDate和after$cscpDate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscpDate==true">
	${before$cscpDate}
	<dl class="cscpDate " major  ref="cscpDate" >
		<dt>照片日期:</dt>
		<s:if test="#request.CTRL.e.cscpDate==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCarPhoto$cscpDate!=null">${csCarPhoto$cscpDate}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCarPhoto.cscpDate" id="cscpDate"  value="<s:date name="csCarPhoto.cscpDate" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆照片的照片日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****照片日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscpDate">${csCarPhoto.cscpDate}</textarea>
		 		<span>
		 	
			 ${csCarPhoto.cscpDate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscpDate}
	</s:if>
	
	${lz:set("注释","*****************照片列表字段的输入框代码*****************")}
	${lz:set("注释","before$cscpImages和after$cscpImages变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscpImages==true">
	${before$cscpImages}
	<dl class="cscpImages " major  ref="cscpImages" style="width:98%;">
		<dt>照片列表:</dt>
		<s:if test="#request.CTRL.e.cscpImages==true">
		${lz:set("haveEditable",true)}
		<dd input="imagelist">
		<s:if test="#request.csCarPhoto$cscpImages!=null">${csCarPhoto$cscpImages}</s:if><s:else>
		    <div id="cscpImages" class="wide input imagelist">
			<s:generator val="csCarPhoto.cscpImages" separator=",">
				<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<div><a href="${item}" target="_blank"><img src="${item}"/><input type="hidden" name="csCarPhoto.cscpImages" value="${item}"/></a><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
				</s:iterator>
			</s:generator>
			</div>
			<button type="button" onclick="$.upload({type:'img',count:10,callback:function(url){if(url)$('#cscpImages').append('<div><a href=&quot;'+url+'&quot; target=&quot;_blank&quot;><img src=&quot;'+url+'&quot;/><input type=&quot;hidden&quot; name=&quot;csCarPhoto.cscpImages&quot; value=&quot;'+url+'&quot;/></a><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}})" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请上传车辆照片的照片列表</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****照片列表字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscpImages">${csCarPhoto.cscpImages}</textarea>
		 		<span>
		 	 <s:generator val="csCarPhoto.cscpImages" separator=",">
		 	 	<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="<s:property/>"/>
					</s:if>
				</s:iterator>
			 </s:generator>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscpImages}
	</s:if>
	
	${lz:set("注释","*****************上传人员字段的输入框代码*****************")}
	${lz:set("注释","before$cscpEditor和after$cscpEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscpEditor==true">
	${before$cscpEditor}
	<dl class="cscpEditor " major  ref="cscpEditor" >
		<dt>上传人员:</dt>
		<s:if test="#request.CTRL.e.cscpEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCarPhoto$cscpEditor!=null">${csCarPhoto$cscpEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cscpEditor" name="csCarPhoto.cscpEditor">
		 				<option selected value="${csCarPhoto.cscpEditor}">
		 					${get:SrvUser(csCarPhoto.cscpEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscpEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cscpEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上传人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscpEditor">${csCarPhoto.cscpEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csCarPhoto.cscpEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csCarPhoto.cscpEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscpEditor}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$cscpRemark和after$cscpRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscpRemark==true">
	${before$cscpRemark}
	<dl class="cscpRemark " minor  ref="cscpRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.cscpRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCarPhoto$cscpRemark!=null">${csCarPhoto$cscpRemark}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csCarPhoto.cscpRemark" id="cscpRemark"  value="${csCarPhoto.cscpRemark}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆照片的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscpRemark">${csCarPhoto.cscpRemark}</textarea>
		 		<span>
		 	
			 ${csCarPhoto.cscpRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscpRemark}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscpAddTime和after$cscpAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscpAddTime==true">
	${before$cscpAddTime}
	<dl class="cscpAddTime " major  ref="cscpAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscpAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCarPhoto$cscpAddTime!=null">${csCarPhoto$cscpAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCarPhoto.cscpAddTime" id="cscpAddTime"  value="<s:date name="csCarPhoto.cscpAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆照片的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscpAddTime">${csCarPhoto.cscpAddTime}</textarea>
		 		<span>
		 	
			 ${csCarPhoto.cscpAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscpAddTime}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCarPhoto.cscpId}" name="csCarPhoto.cscpId" id="cscpId" />
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


<!-- LAZY3Q_JSP_BOTTOM -->
<script>
window["lzFlashUrl"]="http://photo.ccclubs.com/";
window["uploadUrl"]="http://photo.ccclubs.com/upload.do";
</script>
<!-- LAZY3Q_JSP_BOTTOM -->


</body>
</html>