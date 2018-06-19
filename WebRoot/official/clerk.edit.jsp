<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty controller.title?"人员信息编辑":controller.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="zhuangtian">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
		
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
//window["lzFlashUrl"]="http://www.ccclubs.com/include/";
//window["uploadUrl"]="http://www.ccclubs.com/upload.html?app=memberinfo_edit";
//window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";

window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";

$(function(){
		 
	${lz:set("isAddType",(lz:vacant(id))&&(empty csUnitPerson.csupId))}
	${lz:set("haveEditable",false)}
	$("#clerkForm").form({
		"":function(){}



		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitPerson.csupId":function(el){
		}
		</s:if>
 		
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitPerson.csupName":function(el){
			if(jQuery.trim(el.value)=="")
				return "姓名不能为空";
		}
		</s:if>	
		

		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitPerson.csupMobile":function(el){
			if(jQuery.trim(el.value)=="")
				return "手机号码不能为空";
		}
		</s:if>	
		
		
	
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitPerson.csupGroup":function(el){
			if(jQuery.trim(el.value)=="")
				return "部门不能为空";
		}
		</s:if>	
		
		
			 		
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitPerson.csupStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
	});		
});
</script>
<script type="text/javascript">
    var websocket = null;
    function connect(){
        var msg = document.getElementById("msg");
        try{
            var readyState = new Array("正在连接", "已建立连接", "正在关闭连接", "已关闭连接");
            var host = "ws://localhost:90";
            websocket = new WebSocket(host);
            websocket.onopen = function(){
                msg.innerHTML = "温馨提示：请仔细填写相关信息，<span class='extrude'>* 识别仪状态：" +readyState[websocket.readyState] + "。</span>";
				//websocket.send("1234");//在打开后发送一个信息，这个信息不返回任何消息
				websocket.send("1000002");//打开自动触发
            }
            websocket.onmessage  = function(event){
		       var str = event.data;
			   var strsub = str.substr(1);
			   if(strsub != ""){
				   var info = strsub.split("\*");
				   switch (parseInt(info[0])) {
						case 3:
							showTips("请身份证正面放在识别仪上面");
							break;
						case 2:
							$("#csmiCertifyType").val(1);
							var name = info[2].split(":");
							$("#csmName").val(name[1]);
							var csmiSex = info[3].split(":");
							$("#csmiSex").val(csmiSex[1]=="男"?1:0);
							var csmiCertifyNum = info[7].split(":");
							$("#csmiCertifyNum").val(csmiCertifyNum[1]);
							break;
						case 5:
							var csmiDriverNum = info[2].split(":");
							$("#csmiDriverNum").val(csmiDriverNum[1]);
							break;
					}
			   }
					
            }
            websocket.onclose　= function(){
                msg.innerHTML = "识别仪状态： " + readyState[websocket.readyState];
            }
            msg = document.getElementById("msg");
            msg.innerHTML = "识别仪状态： " + readyState[websocket.readyState] ;
        }catch(exception){
            msg.innerHTML = "有错误发生";
        }
    }
    function send(){
        var msg = document.getElementById("msg");
        var text = document.getElementById("text").value;
        if(text == ""){
            msg.innerHTML = "请输入一些文字";
            return;
        }
        try{
            websocket.send(text);
            msg.innerHTML = "发送数据:  " + text;
        }catch(exception){
            msg.innerHTML = "发送数据出错";
        }
        document.getElementById("text").value = "";
    }
 
    function disconnect(){
        websocket.close();
    }
	function AutoPhotoAndRecog(){
		if(websocket!=null)
	 	{
	    	websocket.send("1111");
	 	}

	}
	$(function(){
		connect();
		setInterval("AutoPhotoAndRecog()", 100);
	});
</script>

<form class="form editform" id="clerkForm" name="clerkForm" action="clerk.save.do" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">	
	
		<div class="prompt" id="msg">
					
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
		<input type="hidden" value="${id}" name="id" id="id" />
		<input type="hidden" value="${lz:html(lz:json(controller))}" id="controller" name="controller" />
		
		
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && true)?"class='hidden'":""}  ref="csupId" >
			<dt>编号:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			${lz:set("havePrimary",true)}
			<dd>
				<input type="hidden" value="${csUnitPerson.csupId}" name="csUnitPerson.csupId" id="csupId" />

		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitPerson.csupId}" id="csupId" />
			 		<span>
				${lz:zerofill(csUnitPerson.csupId,6)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				

		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupName" >
			<dt>公务员编号:</dt>	
		 	${lz:set("haveEditable",true)}					 
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitPerson.csupName" id="csupName"  value="${csUnitPerson.csupName}"/>
		 	 <b>*</b>
		 	<s:if test="#request.canVisible==false">
		 	 <em>请输入公务员编号</em>
		 	 </s:if>
			</dd>
		</dl>


		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csmName" >
			<dt>姓名:</dt>	
		 	${lz:set("haveEditable",true)}					 
			<dd>
			 	<input type="text" class="input narrow" maxlength="32" name="csMember.csmName" id="csmName" value="${csMember.csmName}"/>
		 	 <b>*</b>
		 	<s:if test="#request.canVisible==false">
		 	 <em>请输入姓名</em>
		 	 </s:if>
			</dd>
		</dl>
		
		

		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupGroup" >
			<dt>部门:</dt>
			<s:if test="#request.canEditable==true">	
		 	${lz:set("haveEditable",true)}				 
			<dd>
			 	<select class="narrow" id="csupGroup" name="csUnitPerson.csupGroup">
			 		<option value="">请选择</option>			 					 					 		
			 		<s:iterator value="#request.csUnitGroups" id="item" status="csUnitGroup">
			 		<option value="${item.csugId}" ${csUnitPerson.csupGroup==item.csugId?"selected":""}>${item.csugName}</option>
					</s:iterator>					
			 	</select>
		 	 <b>*</b>	
		 	 <s:if test="#request.canVisible==false">	 	
		 	 <em>请选择所属部门信息</em>
		 	 </s:if>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<select class="narrow" id="csupGroup" name="csUnitPerson.csupGroup">			 					 					 		
			 		<s:iterator value="#request.csUnitGroups" id="item" status="csUnitGroup">
			 		<option value="${item.csugId}" ${csUnitPerson.csupGroup==item.csugId?"selected":""}>${item.csugName}</option>
					</s:iterator>
					
			 	</select>
			</dd>
			</s:else>
		</dl>
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csmsPayer" >
			<dt>支付账号:</dt>
			<s:if test="#request.canEditable==true">	
		 	${lz:set("haveEditable",true)}				 
			<dd>
			 	<select class="narrow" id="csmsPayer" name="csMemberShip.csmsPayer">
			 					 					 					 		
			 		<s:iterator value="#request.payMembers" id="item" status="csmsPayer">
			 		<option value="${item.csmId}" ${csMemberShip.csmsPayer==item.csmId?"selected":""}>${item.csmName}</option>
					</s:iterator>
			 	</select>
		 	 <b>*</b>
		 	<s:if test="#request.canVisible==false">
		 	 <em>请选择支付账号</em>
		 	 </s:if>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<select class="narrow" id="csupGroup" name="csMemberShip.csmsPayer">			 					 					 		
			 		<s:iterator value="#request.payMembers" id="item" status="csmsPayer">
			 		<option value="${item.csmId}" ${csMemberShip.csmsPayer==item.csmId?"selected":""}>${item.csmName}</option>
					</s:iterator>
					
			 	</select>
			</dd>
			</s:else>
		</dl>
		
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMember.csmMobile" >
			<dt>手机号码:</dt>	
		 	${lz:set("haveEditable",true)}					 
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csMember.csmMobile" id="csmMobile"  value="${csMember.csmMobile}"/>
		 	 <b>*</b>
		 	<s:if test="#request.canVisible==false">
		 	 <em>请输入手机号码</em>
		 	 </s:if>
			</dd>			
		</dl>
		
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMemberInfo.csmiSex" >
			<dt>性别:</dt>	
		 	${lz:set("haveEditable",true)}					 
			<dd>
			 	<select class="narrow" id="csmiSex" name="csMemberInfo.csmiSex">
			 		<option value="">请选择</option>
					<option value="1" ${csMemberInfo.csmiSex==1?"selected":""}>男</option>
					<option value="0" ${csMemberInfo.csmiSex==0?"selected":""}>女</option>
			 	</select>
		 	 <b>*</b>
		 	 <em>请选择性别</em>
			</dd>			
		</dl>
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMemberInfo.csmiCertifyType" >
			<dt>证件类型:</dt>
		 	${lz:set("haveEditable",true)}					 
			<dd>
			 	<select class="narrow" id="csmiCertifyType" name="csMemberInfo.csmiCertifyType">
			 		<option value="">请选择</option>
					<option value="1" ${csUnitPerson.$csupMember.$csmInfo.csmiCertifyType$==1?"selected":""}>身份证</option>
					<option value="5" ${csUnitPerson.$csupMember.$csmInfo.csmiCertifyType$==5?"selected":""}>港澳台通行证</option>
					<option value="7" ${csUnitPerson.$csupMember.$csmInfo.csmiCertifyType$==7?"selected":""}>公务员证</option>
			 	</select>	
		 	<s:if test="#request.canVisible==false">
		 	 <em>请选择证件类型</em>
		 	 </s:if>
			</dd>			
		</dl>
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMemberInfo.csmiCertifyNum" >
			<dt>证件号码:</dt>	
		 	${lz:set("haveEditable",true)}					 
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csMemberInfo.csmiCertifyNum" id="csmiCertifyNum"  value="${csMemberInfo.csmiCertifyNum}"/>		 	
		 	<s:if test="#request.canVisible==false">
		 	 <em>请输入证件号码</em>
		 	 </s:if>
			</dd>			
		</dl>
		<dl></dl>
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMemberInfo.csmiCertifyImage" style="width:100%">
			<dt>证件图片:</dt>	
		 	${lz:set("haveEditable",true)}					 
			<dd><s:property value="url"/>
			 	<!-- <input type="text" class="input narrow"  maxlength="128" size="32" name="csUnitPerson.$csupMember.$csmInfo.csmiCertifyImage" id="csmiCertifyImage"  value="${csUnitPerson.$csupMember.$csmInfo.csmiCertifyImage}"/> -->		 	
		 		<input type="text" class="input narrow"  maxlength="128" size="32" name="csMemberInfo.csmiCertifyImage" id="csmiCertifyImage"  value="${csMemberInfo.csmiCertifyImage}"/>
		 		<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csmiCertifyImage').val(url)}})" class="button">设置图片</button>
				<button type="button" onclick="$('#csmiCertifyImage').val('')" class="button">删除图片</button>
				<button type="button" onclick="$.thumb({url:$('#csmiCertifyImage').val()})" class="button">查看图片</button>
				
		 	<s:if test="#request.canVisible==false">
		 	 <em>请选择证件图片</em>
		 	 </s:if>
			</dd>			
		</dl>
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMemberInfo.csmiDriverNum" >
			<dt>驾照号码:</dt>	
		 	${lz:set("haveEditable",true)}					 
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csMemberInfo.csmiDriverNum" id="csmiDriverNum"  value="${csMemberInfo.csmiDriverNum}"/>		 	
		 	<s:if test="#request.canVisible==false">
		 	 <em>请输入驾照号码</em>
		 	 </s:if>
			</dd>			
		</dl>
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csMemberInfo.csmiDriverNum" style="width:100%">
			<dt>驾照图片:</dt>	
		 	${lz:set("haveEditable",true)}					 
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csMemberInfo.csmiDriverImage" id="csmiDriverImage"  value="${csMemberInfo.csmiDriverImage}"/>
			 	<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csmiDriverImage').val(url)}})" class="button">设置图片</button>
				<button type="button" onclick="$('#csmiDriverImage').val('')" class="button">删除图片</button>
				<button type="button" onclick="$.thumb({url:$('#csmiDriverImage').val()})" class="button">查看图片</button>		 	
		 	<s:if test="#request.canVisible==false">
		 	 <em>请上传驾照图片</em>
		 	 </s:if>
			</dd>			
		</dl>	
			
		
		
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupFlag" >
			<dt>叫车权限:</dt>
			${lz:set("haveEditable",true)}
			<dd>
			 	<%-- 
			 	<select class="narrow" id="csupFlag" name="csUnitPerson.csupFlag">
			 		<option value="">请选择</option>
					<option value="1" ${csUnitPerson.csupFlag==1?"selected":""}>正常</option>
					<option value="0" ${csUnitPerson.csupFlag==0?"selected":""}>无效</option>
			 	</select> 
			 	--%>
		 	 
		 	  <input type="checkbox" name="csUnitPerson.csupFlag" id="csupFlag1" class="cnarrow" value="1"  ${csUnitPerson.csupFlag==1?"checked":""}>叫车
		 	  <input type="checkbox" name="csUnitPerson.csupFlag" id="csupFlag2" class="cnarrow" value="2"  ${csUnitPerson.csupFlag==2?"checked":""}>约租车
		 	  
		 	  <script>
		 	  	<s:generator separator="," val="csUnitPerson.csupFlag" id="flags">
					<s:iterator value="#flags" id="flag">
						<s:if test="#flag==1">$("#csupFlag1").attr("checked", true);</s:if>
						<s:if test="#flag==2">$("#csupFlag2").attr("checked", true);</s:if>
						<s:if test="#flag==3">$("#csupFlag3").attr("checked", true);</s:if>
					</s:iterator>
			  	</s:generator>
		 	  </script>
		 	 <s:if test="#request.canVisible==false">
		 	 <em>请选择叫车权限</em>
		 	 </s:if>
			</dd>
		</dl>
		
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupFlag" >
			<dt>公务出行审批权限:</dt>
			${lz:set("haveEditable",true)}
			<dd>
			 	<%-- 
			 	<select class="narrow" id="csupFlag" name="csUnitPerson.csupFlag">
			 		<option value="">请选择</option>
					<option value="1" ${csUnitPerson.csupFlag==1?"selected":""}>正常</option>
					<option value="0" ${csUnitPerson.csupFlag==0?"selected":""}>无效</option>
			 	</select> 
			 	--%>
		 	  <input type="radio" name="csUnitPerson.csupFlag" id="csupFlag4" class="cnarrow" value="4"  ${csUnitPerson.csupFlag==4?"checked":""} checked="checked">需要审批
		 	  <input type="radio" name="csUnitPerson.csupFlag" id="csupFlag3" class="cnarrow" value="3"  ${csUnitPerson.csupFlag==3?"checked":""}>无需审批
		 	  
		 	  <script>
		 	  	<s:generator separator="," val="csUnitPerson.csupFlag" id="flags">
					<s:iterator value="#flags" id="flag">
						<s:if test="#flag==3">$("#csupFlag3").attr("checked", true);</s:if>
						<s:if test="#flag==4">$("#csupFlag4").attr("checked", true);</s:if>
					</s:iterator>
			  	</s:generator>
		 	  </script>
		 	 <s:if test="#request.canVisible==false">
		 	 <em>公务出行审批权限</em>
		 	 </s:if>
			</dd>
		</dl>
	
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csupStatus" >
			<dt>状态:</dt>
			${lz:set("haveEditable",true)}
			<dd>
			 	<select class="narrow" id="csupStatus" name="csUnitPerson.csupStatus">
			 		<option value="">请选择</option>
					<option value="1" ${csUnitPerson.csupStatus==1?"selected":""}>正常</option>
					<option value="0" ${csUnitPerson.csupStatus==0?"selected":""}>无效</option>
			 	</select>
		 	 
		 	 <b>*</b>
		 	 <s:if test="#request.canVisible==true">
		 	 <em>请选择人员状态</em>
		 	 </s:if>
			</dd>
		</dl>
		
		<div class="line"></div>
		<center>
			<input type="hidden" value="${csUnitUserToken}" name="csUnitUserToken" id="csUnitUserToken" />
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csUnitPerson.csupId}" name="csUnitPerson.csupId" id="csupId" />
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
		disconnect();	
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