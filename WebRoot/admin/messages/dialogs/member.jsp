<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>会员审核</title> 
</head>

	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

<body>

<style> 
#title{font-size:14px;font-weight:bold;} 
#content span{cursor:pointer;text-decoration:underline;} 
#images a{border:2px;display:block;width:140px;height:80px;overflow:hidden;} 
#images img{width:140px;height:80px;border:0px;} 
#message-button span{cursor:pointer;text-decoration:underline;}
</style>
<table style="table-layout:fixed;" width="100%" border="0" cellspacing="0" cellpadding="3">
  <tr>
    <td width="72" rowspan="3" align="center" valign="top" id="icon">
    	<br/>
		<img src="${basePath}admin/images/icons/20088256427828778017.png" />		
	</td>
    <td id="title">通知：有会员${timeDesc}修改了资料</td>
  </tr>
  <tr>
    <td id="content" title="${lz:date(csMember.$csmInfo.csmiUpdateTime,"yyyy-MM-dd HH:mm:ss")}">
		会员
		<span onclick="window.href('${basePath}admin/user/member_details.do?id=${csMember.$csmInfo.csmiMemberId}',{ctrl:{editable:false}})">
			${csMember.csmMobile}</span>
		<span onclick="window.href('${basePath}admin/user/memberinfo_details.do?id=${csMember.$csmInfo.csmiId}',{ctrl:{editable:false}})">
			(${csMember.$csmInfo.csmiName})</span>
		修改了个人资料，其中证件照片有改动，请审核		
	</td>
  </tr>
  <tr>
    <td id="images" align="center">   
    
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td align="center" width="50%">
				<a class="state-input" href="${csMember.$csmInfo.csmiDriverImage}" target="_blank">
					<img src="${csMember.$csmInfo.csmiDriverImage}" alt="${csMember.$csmInfo.csmiDriverNum}"/></a>
			</td>
		    <td align="center">
				<input ${csMember.csmVDrive==1?"checked='checked'":""} name="csmVDrive" id="csmVDrive" type="checkbox" />
				认证通过
			</td>
		  </tr>
		</table> 	
				
	</td>
  </tr>
  <tr>
    <td colspan="2"  style="height:2px;">
    	<div class="line"></div>
    </td>
  </tr>
  <tr>
  	<td>&nbsp;
		
	</td>
    <td align="center" valign="middle"  id="message-button"> 
    	<span onclick="sendSms()">发送短信</span>
    	&nbsp;&nbsp;
    	<span onclick="bindEvc()">绑定EV卡</span>
    	&nbsp;&nbsp;
		<button onclick="submitCheck()" class="button" type="button">
			&nbsp;<img width="22" align="absmiddle" src="${basePath}admin/images/icons/20088256421529677802.png"/>
			&nbsp;提交审核&nbsp;&nbsp;</button>
	</td>
  </tr>
</table>

<iframe id="tips_iframe" name="tips_iframe" src="${basePath}admin/message.tips.do" width="0" height="0" style="display:none;"></iframe>

<script>

$(function(){
	parent.$("#message-dialog .caption .title").html("${csMember.$csmInfo.csmiName}${timeDesc}修改了资料");
});


function href(url,params){
	top.$.showModalDialog({url:url},params,tips_iframe);
}
function submitCheck(){
	$.getObject("${basePath}admin/user/member_check.do",{
		id:${csMember.csmId},
		csmVReal:$("#csmVReal").is(":checked")?1:3,
		csmVDrive:$("#csmVDrive").is(":checked")?1:3
	},function(data){
		if(!data.success && data.message)
			window.top.tips(data.message);
		if(data.success==true)
			$.closeMsgDialog();
	});
}
function bindEvc(){
	var url = "${basePath}admin/user/member_edit.do";
	var params = {entrypoint:"${entrypoint}",id:${csMember.csmId},ctrl:{title:"绑定EV卡",visible:false,editable:false,fields:{}}};
	params.ctrl["fields"]["csmEvcard"]={visible:true,editable:true};
	href(url,params);
}
function sendSms(){
	var list=$.getObject("${basePath}admin/user/member_query.do?object=true",{csmId:${csMember.csmId}});
	var member=null;
	if(list && list.length)
		member=list[0].object;
	if(!member || $.trim(member.csmMobile)==""){
		$.tips("该会员没有手机号码，不能发送短信");
		return;
	}
	var url = "${basePath}admin/systematic/smsmt_edit.do";
	var params = {
		entrypoint:"${entrypoint}",
		ctrl:{
			fields:{
				cssmMobile:{
					defaultValue:member.csmMobile,
					editable:true
				}			
			}
		}
	};
	href(url,params);
}
</script>

</body>
</html>
