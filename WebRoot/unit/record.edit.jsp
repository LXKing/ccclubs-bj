<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty controller.title?"免费优惠记录编辑":controller.title}</title>
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

<script>

$(function(){	
	showTips("${tips.value}");
});

window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";

$(function(){
		 
	${lz:set("isAddType",(lz:empty(ids))&&(empty csUseRecord.csurId))}
	${lz:set("haveEditable",false)}
	$("#userecordForm").form({
		"":function(){}
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurId')==null)?canEditable:(lz:editable(controller,'csurId')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUseRecord.csurId":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurMember')==null)?canEditable:(lz:editable(controller,'csurMember')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUseRecord.csurMember":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurFreeHour')==null)?canEditable:(lz:editable(controller,'csurFreeHour')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUseRecord.csurFreeHour":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurGift')==null)?canEditable:(lz:editable(controller,'csurGift')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUseRecord.csurGift":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurOrder')==null)?canEditable:(lz:editable(controller,'csurOrder')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUseRecord.csurOrder":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurCount')==null)?canEditable:(lz:editable(controller,'csurCount')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUseRecord.csurCount":function(el){
				var pattern = /^(([1-9]\d*)|0)(\.\d+)?$/;
				if(el.value!="" && !pattern.test(el.value))
					return "使用数量输入格式错误";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurAddTime')==null)?canEditable:(lz:editable(controller,'csurAddTime')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUseRecord.csurAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
	});		
});
</script>

<form class="form editform" id="userecordForm" name="userecordForm" action="${empty controller.action?"userecord_save.do":controller.action}" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">	
	
		<s:if test="#request.haveEditable==true">
		<div class="prompt">
			温馨提示：请仔细填写免费优惠记录相关信息，<span class="extrude">"*" 为必填选项。</span>
		</div>
		</s:if>
		
		
		${lz:set("empty",lz:empty(message))}
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
		
		${lz:set("isAddType",(lz:empty(ids))&&(empty csUseRecord.csurId))}		
		${lz:set("haveEditable",false)}
		${lz:set("havePrimary",false)}
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurId')==null)?canEditable:(lz:editable(controller,'csurId')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csurId')==null)?canVisible:(lz:visible(controller,'csurId')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && true)?"class='hidden'":""}  ref="csurId" >
			<dt>编号:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			${lz:set("havePrimary",true)}
			<dd>
				<input type="hidden" value="${csUseRecord.csurId}" name="csUseRecord.csurId" id="csurId" />
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUseRecord.csurId}" id="csurId" />
			 		<span>
				${lz:zerofill(csUseRecord.csurId,6)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurMember')==null)?canEditable:(lz:editable(controller,'csurMember')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csurMember')==null)?canVisible:(lz:visible(controller,'csurMember')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csurMember" >
			<dt>所属会员:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}admin/user/member_inquire.do?value={param}" type="text" id="csurMember" name="csUseRecord.csurMember" text="${get:CsMember(csUseRecord.csurMember).csmName}" value="${csUseRecord.csurMember}" />
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csurMember').val())==''){return;};window.href('${basePath}admin/user/member_details.do?id='+$('#csurMember').val(),{controller:{editable:false}})"></a>
		 	 
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUseRecord.csurMember}" id="csurMember" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csUseRecord.csurMember}')==''){return;};window.href('${basePath}admin/user/member_details.do?id=${csUseRecord.csurMember}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsMember(csUseRecord.csurMember).csmName}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurFreeHour')==null)?canEditable:(lz:editable(controller,'csurFreeHour')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csurFreeHour')==null)?canVisible:(lz:visible(controller,'csurFreeHour')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csurFreeHour" >
			<dt>所属免费小时:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<input title="请输入免费小时编号进行查询" class="combox narrow" action="${basePath}admin/user/freehour_query.do?value={param}" type="text" id="csurFreeHour" name="csUseRecord.csurFreeHour" text="${get:CsFreeHour(csUseRecord.csurFreeHour).csfhId}" value="${csUseRecord.csurFreeHour}" />
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csurFreeHour').val())==''){return;};window.href('${basePath}admin/user/freehour_details.do?id='+$('#csurFreeHour').val(),{controller:{editable:false}})"></a>
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUseRecord.csurFreeHour}" id="csurFreeHour" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csUseRecord.csurFreeHour}')==''){return;};window.href('${basePath}admin/user/freehour_details.do?id=${csUseRecord.csurFreeHour}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsFreeHour(csUseRecord.csurFreeHour).csfhId}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurGift')==null)?canEditable:(lz:editable(controller,'csurGift')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csurGift')==null)?canVisible:(lz:visible(controller,'csurGift')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csurGift" >
			<dt>所属优惠项:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<input title="请输入优惠内容编号进行查询" class="combox narrow" action="${basePath}admin/user/gift_query.do?value={param}" type="text" id="csurGift" name="csUseRecord.csurGift" text="${get:CsGift(csUseRecord.csurGift).csgId}" value="${csUseRecord.csurGift}" />
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csurGift').val())==''){return;};window.href('${basePath}admin/user/gift_details.do?id='+$('#csurGift').val(),{controller:{editable:false}})"></a>
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUseRecord.csurGift}" id="csurGift" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csUseRecord.csurGift}')==''){return;};window.href('${basePath}admin/user/gift_details.do?id=${csUseRecord.csurGift}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsGift(csUseRecord.csurGift).csgId}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurOrder')==null)?canEditable:(lz:editable(controller,'csurOrder')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csurOrder')==null)?canVisible:(lz:visible(controller,'csurOrder')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csurOrder" >
			<dt>所属订单:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<input title="请输入订单订单编号进行查询" class="combox narrow" action="${basePath}admin/service/order_query.do?value={param}" type="text" id="csurOrder" name="csUseRecord.csurOrder" text="${get:CsOrder(csUseRecord.csurOrder).csoId}" value="${csUseRecord.csurOrder}" />
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csurOrder').val())==''){return;};window.href('${basePath}admin/service/order_details.do?id='+$('#csurOrder').val(),{controller:{editable:false}})"></a>
		 	 
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUseRecord.csurOrder}" id="csurOrder" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csUseRecord.csurOrder}')==''){return;};window.href('${basePath}admin/service/order_details.do?id=${csUseRecord.csurOrder}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsOrder(csUseRecord.csurOrder).csoId}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurCount')==null)?canEditable:(lz:editable(controller,'csurCount')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csurCount')==null)?canVisible:(lz:visible(controller,'csurCount')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csurCount" >
			<dt>使用数量:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="onkeyup()" onkeyup="var reg=/^(([1-9]\d{0,9})|0)(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUseRecord.csurCount" id="csurCount"  value="${csUseRecord.csurCount}"/>
		 	 
		 	 
		 	 <em>请输入免费优惠记录的使用数量</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUseRecord.csurCount}" id="csurCount" />
			 		<span>
			 				${csUseRecord.csurCount}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csurAddTime')==null)?canEditable:(lz:editable(controller,'csurAddTime')))}
		
		${lz:set("canVisible",(false && isAddType)||(false && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csurAddTime')==null)?canVisible:(lz:visible(controller,'csurAddTime')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csurAddTime" >
			<dt>添加时间:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUseRecord.csurAddTime" id="csurAddTime"  value="<s:date name="csUseRecord.csurAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择免费优惠记录的添加时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUseRecord.csurAddTime}" id="csurAddTime" />
			 		<span>
			 	 <s:date name="csUseRecord.csurAddTime" format="yyyy-MM-dd HH:mm:ss"/>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
	
		
		
		<div class="line"></div>
		<center>
			<input type="hidden" value="${csUseRecordToken}" name="csUseRecordToken" id="csUseRecordToken" />
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csUseRecord.csurId}" name="csUseRecord.csurId" id="csurId" />
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