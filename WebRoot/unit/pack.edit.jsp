<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty controller.title?"用户套餐编辑":controller.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
		
	<!-- 感谢kindeditor出品的在线HTML编辑控件 -->
	<script type="text/javascript" charset="utf-8" src="${basePath}admin/js/kindeditor/kindeditor.js"></script>
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>

</head>  
<body ${(controller.mutual=="page"||(controller.mutual!="dialog" && false))?"class='pagetype'":""}>
<!--LAZY3Q_JSP_BODY-->
<s:if test="csUserPack.csupId==null">
${lz:set("message","添加会员套餐后，将立即从会员保证金中扣除套餐所需价格")}
</s:if>
<!--LAZY3Q_JSP_BODY-->


<script>

$(function(){	
	showTips("${tips.value}");
});

window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";

$(function(){
		 
	${lz:set("isAddType",(lz:vacant(ids))&&(empty csUserPack.csupId))}
	${lz:set("haveEditable",false)}
	$("#packForm").form({
		"":function(){}
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupId')==null)?canEditable:(lz:editable(controller,'csupId')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUserPack.csupId":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupPack')==null)?canEditable:(lz:editable(controller,'csupPack')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUserPack.csupPack":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupMember')==null)?canEditable:(lz:editable(controller,'csupMember')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUserPack.csupMember":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupRemark')==null)?canEditable:(lz:editable(controller,'csupRemark')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUserPack.csupRemark":function(el){
			if(el.value.length>256)
				return "备注最大长度为256个字符";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupUntilTime')==null)?canEditable:(lz:editable(controller,'csupUntilTime')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUserPack.csupUntilTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "有效期至不能为空";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupUpdateTime')==null)?canEditable:(lz:editable(controller,'csupUpdateTime')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUserPack.csupUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupAddTime')==null)?canEditable:(lz:editable(controller,'csupAddTime')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUserPack.csupAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupState')==null)?canEditable:(lz:editable(controller,'csupState')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUserPack.csupState":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择续订状态";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupStatus')==null)?canEditable:(lz:editable(controller,'csupStatus')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUserPack.csupStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择套餐状态";
		}
		</s:if>	
	});		
});
</script>

<form class="form editform" id="packForm" name="packForm" action="${empty controller.action?"pack_save.do":controller.action}" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">	
	
		<s:if test="#request.haveEditable==true">
		<div class="prompt">
			温馨提示：请仔细填写用户套餐相关信息，<span class="extrude">"*" 为必填选项。</span>
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
		<input type="hidden" value="${ids}" name="ids" id="ids" />
		<input type="hidden" value="${lz:html(lz:json(controller))}" id="controller" name="controller" />
		
		${lz:set("isAddType",(lz:vacant(ids))&&(empty csUserPack.csupId))}		
		${lz:set("haveEditable",false)}
		${lz:set("havePrimary",false)}
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupId')==null)?canEditable:(lz:editable(controller,'csupId')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csupId')==null)?canVisible:(lz:visible(controller,'csupId')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && true)?"class='hidden'":""}  ref="csupId" >
			<dt>编号:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			${lz:set("havePrimary",true)}
			<dd>
				<input type="hidden" value="${csUserPack.csupId}" name="csUserPack.csupId" id="csupId" />
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUserPack.csupId}" id="csupId" />
			 		<span>
				${lz:zerofill(csUserPack.csupId,6)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupPack')==null)?canEditable:(lz:editable(controller,'csupPack')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csupPack')==null)?canVisible:(lz:visible(controller,'csupPack')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupPack" >
			<dt>所属系统套餐:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<select class="combox narrow" action="${basePath}admin/fee/pack_query.do?size=-1" id="csupPack" name="csUserPack.csupPack">
			 				<option selected value="${csUserPack.csupPack}">
			 					${get:CsPack(csUserPack.csupPack).cspName}
			 				</option>
			 			</select>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csupPack').val())==''){return;};window.href('${basePath}admin/fee/pack_details.do?id='+$('#csupPack').val(),{controller:{editable:false}})"></a>
		 	 
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUserPack.csupPack}" id="csupPack" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csUserPack.csupPack}')==''){return;};window.href('${basePath}admin/fee/pack_details.do?id=${csUserPack.csupPack}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsPack(csUserPack.csupPack).cspName}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupMember')==null)?canEditable:(lz:editable(controller,'csupMember')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csupMember')==null)?canVisible:(lz:visible(controller,'csupMember')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupMember" >
			<dt>所属会员:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}admin/user/member_inquire.do?value={param}" type="text" id="csupMember" name="csUserPack.csupMember" text="${get:CsMember(csUserPack.csupMember).csmName}" value="${csUserPack.csupMember}" />
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csupMember').val())==''){return;};window.href('${basePath}admin/user/member_details.do?id='+$('#csupMember').val(),{controller:{editable:false}})"></a>
		 	 
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUserPack.csupMember}" id="csupMember" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csUserPack.csupMember}')==''){return;};window.href('${basePath}admin/user/member_details.do?id=${csUserPack.csupMember}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsMember(csUserPack.csupMember).csmName}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupGifts')==null)?canEditable:(lz:editable(controller,'csupGifts')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csupGifts')==null)?canVisible:(lz:visible(controller,'csupGifts')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupGifts" style="width:98%;">
			<dt>套餐内容:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			  		<s:if test="csUserPack.csupId!=null">
			  		${lz:set("uuid",lz:uuid())}
					<iframe class="state-input" onload="$(this).autoHeight()" id="iframe_${uuid}" name="iframe_${uuid}" frameborder='0' src="${basePath}admin/user/gift.do?all=true&csgUserPack=${csUserPack.csupId}&controller={mutual:'dialog',queryable:false,fields:{csgUserPack:{visible:true,editable:false,defaultValue:${csUserPack.csupId}}}}&size=8" width='520' height='0'></iframe>
					</s:if>
					<s:else>
					<div class="state-input wide">
					完成或保存表单后即可编辑套餐内容
					</div>
					</s:else>
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<s:if test="csUserPack.csupId!=null">
		  		${lz:set("uuid",lz:uuid())}
				<iframe class="state-input" onload="$(this).autoHeight()" id="iframe_${uuid}" name="iframe_${uuid}" frameborder='0' src="${basePath}admin/user/gift.do?csgUserPack=${csUserPack.csupId}&all=true&controller={mutual:'dialog',canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" width='520' height='0'></iframe>
				</s:if>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupRemark')==null)?canEditable:(lz:editable(controller,'csupRemark')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csupRemark')==null)?canVisible:(lz:visible(controller,'csupRemark')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupRemark" style="width:98%;">
			<dt>备注:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<textarea class="input wide"  id="csupRemark" name="csUserPack.csupRemark" rows="5">${csUserPack.csupRemark}</textarea>
		 	 
		 	 
		 	 <em>请输入用户套餐的备注</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input wide">
			 		<span>
				 ${lz:html(csUserPack.csupRemark)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupUntilTime')==null)?canEditable:(lz:editable(controller,'csupUntilTime')))}
		
		${lz:set("canVisible",(false && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csupUntilTime')==null)?canVisible:(lz:visible(controller,'csupUntilTime')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupUntilTime" >
			<dt>有效期至:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUserPack.csupUntilTime" id="csupUntilTime"  value="<s:date name="csUserPack.csupUntilTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择用户套餐的有效期至</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUserPack.csupUntilTime}" id="csupUntilTime" />
			 		<span>
			 	 <s:date name="csUserPack.csupUntilTime" format="yyyy-MM-dd HH:mm:ss"/>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupUpdateTime')==null)?canEditable:(lz:editable(controller,'csupUpdateTime')))}
		
		${lz:set("canVisible",(false && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csupUpdateTime')==null)?canVisible:(lz:visible(controller,'csupUpdateTime')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupUpdateTime" >
			<dt>修改时间:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUserPack.csupUpdateTime" id="csupUpdateTime"  value="<s:date name="csUserPack.csupUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择用户套餐的修改时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUserPack.csupUpdateTime}" id="csupUpdateTime" />
			 		<span>
			 	 <s:date name="csUserPack.csupUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupAddTime')==null)?canEditable:(lz:editable(controller,'csupAddTime')))}
		
		${lz:set("canVisible",(false && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csupAddTime')==null)?canVisible:(lz:visible(controller,'csupAddTime')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupAddTime" >
			<dt>添加时间:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUserPack.csupAddTime" id="csupAddTime"  value="<s:date name="csUserPack.csupAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择用户套餐的添加时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUserPack.csupAddTime}" id="csupAddTime" />
			 		<span>
			 	 <s:date name="csUserPack.csupAddTime" format="yyyy-MM-dd HH:mm:ss"/>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupState')==null)?canEditable:(lz:editable(controller,'csupState')))}
		
		${lz:set("canVisible",(false && isAddType)||(false && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csupState')==null)?canVisible:(lz:visible(controller,'csupState')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupState" >
			<dt>续订状态:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<select class="narrow" id="csupState" name="csUserPack.csupState">
			 		<option value="">请选择</option>
					<option value="0" ${csUserPack.csupState==0?"selected":""}>不续订</option>
					<option value="1" ${csUserPack.csupState==1?"selected":""}>待续订</option>
					<option value="2" ${csUserPack.csupState==2?"selected":""}>已续订</option>
			 	</select>
		 	 
		 	 <b>*</b>
		 	 <em>请选择用户套餐的续订状态</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUserPack.csupState}" id="csupState" />
			 		<span>
				 ${csUserPack.csupState==0?"不续订":""}
				 ${csUserPack.csupState==1?"待续订":""}
				 ${csUserPack.csupState==2?"已续订":""}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csupStatus')==null)?canEditable:(lz:editable(controller,'csupStatus')))}
		
		${lz:set("canVisible",(false && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csupStatus')==null)?canVisible:(lz:visible(controller,'csupStatus')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupStatus" >
			<dt>套餐状态:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<select class="narrow" id="csupStatus" name="csUserPack.csupStatus">
			 		<option value="">请选择</option>
					<option value="1" ${csUserPack.csupStatus==1?"selected":""}>有效</option>
					<option value="0" ${csUserPack.csupStatus==0?"selected":""}>无效</option>
					<option value="2" ${csUserPack.csupStatus==2?"selected":""}>过期</option>
					<option value="3" ${csUserPack.csupStatus==3?"selected":""}>用完</option>
			 	</select>
		 	 
		 	 <b>*</b>
		 	 <em>请选择用户套餐的套餐状态</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUserPack.csupStatus}" id="csupStatus" />
			 		<span>
				 ${csUserPack.csupStatus==1?"有效":""}
				 ${csUserPack.csupStatus==0?"无效":""}
				 ${csUserPack.csupStatus==2?"过期":""}
				 ${csUserPack.csupStatus==3?"用完":""}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
	
		
		
		<div class="line"></div>
		<center>
			<input type="hidden" value="${csUserPackToken}" name="csUserPackToken" id="csUserPackToken" />
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csUserPack.csupId}" name="csUserPack.csupId" id="csupId" />
			</s:if>			
			<s:if test="#request.haveEditable==true">
				<s:if test="#request.controller.submits!=null">
					<s:iterator value="#request.controller.submits" id="submit" status="i">
			<button class="button" type="submit" name="submiter" value="${submit.value}">&nbsp;&nbsp;${submit.name}&nbsp;&nbsp;</button>
					</s:iterator>
				</s:if>
				<s:else>
			<button class="button" type="submit" name="submiter" value="完成">&nbsp;&nbsp;完成&nbsp;&nbsp;</button>
			<button class="button" type="submit" name="submiter" value="保存">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
				</s:else>
			<button class="button" type="reset">&nbsp;&nbsp;重置&nbsp;&nbsp;</button>
			</s:if>
			<s:else>
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;确定&nbsp;&nbsp;</button>
			</s:else>
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
		</center>
		
		
	</div>	
</div>
<div class="foot"></div>
	
</form>

<script>
	function cancel(){
		if(${controller.mutual=="dialog"})
			$.closeModalDialog();
		else if(${controller.mutual=="page"})
			window.location="${lz:xeh(entrypoint)}";
		else if(true)
			$.closeModalDialog();
		else
			window.location="${lz:xeh(entrypoint)}";
	}
	
	function href(url,params){
		if(${controller.mutual=="dialog"})
			top.$.showModalDialog({url:url},params,window);
		else if(${controller.mutual=="page"})
			$.submit(url,params,"_self");
		else if(true)
			top.$.showModalDialog({url:url},params,window);
		else
			$.submit(url,params,"_self");	
	}
</script>

</body>
</html>