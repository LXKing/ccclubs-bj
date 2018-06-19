<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>用车帐号管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		window.basePath="${basePath}";
		window.contextPath="${basePath}${proname}/";
		window.apiUrl="${basePath}${proname}/api.do";
		window.handles={};
	</script>
		
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
	<!------------------------LAZY3Q_JSP_HEAD------------------------>
	<!------------------------LAZY3Q_JSP_HEAD------------------------>	
	
</head>  
<body>

<table border="0" cellspacing="0" cellpadding="0"><tr><td>

<div class="title">
	<a href="javascript:void(0);" onclick="parent.current.close();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Badge-multiply.png"/>
		关闭页面
	</a>
	<a href="javascript:void(0);" class="linker imize ${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize"))?"minimize":"maximize"}">
		<img align="absmiddle" class="up" src="${basePath}admin/images/icons/Arrow up.png"/>
		<img align="absmiddle" class="down" src="${basePath}admin/images/icons/Arrow down.png"/>
		展开收起
	</a>
	<a href="javascript:void(0);" class="linker setting">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256421529677801.png"/>
		页面设置
	</a>
	<a href="?${lz:querys("UTF-8","xxxxx")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/2008825642250778028.png"/>
		重新加载
	</a>
	
	<span class="caption">${empty title?"用车帐号管理":title}</span>
</div>

<!-- 
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsUnitPerson" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		
			 
			<dl group="${relateObject==null?"csupName":relateObject}" id="form-dl-csupName">
				<dt>名称别名：</dt>
				<dd>
					<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupNameYesNot=="not"?"checked-not":""}${data.csupNameYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
					<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupNameYesNot}"/>
		 			<input type="text" class="input" size="16"  maxlength="32" name="${NAME}" id="${NAME}"  value="${data.csupName}"/>
				</dd>
			</dl>
			<dl group="${relateObject==null?"csupGroup":relateObject}" id="form-dl-csupGroup">
				<dt>所属分组：</dt>
				<dd>
				
					<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupGroupYesNot=="not"?"checked-not":""}${data.csupGroupYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
					<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupGroupYesNot}"/>
		 			<select class="combox" action="${basePath}${proname}/unit/group.query.do" size="16" type="text" id="${NAME}" name="${NAME}" value="">
		 				<option value="${data.csupGroup}">${get:CsUnitGroup(data.csupGroup).csugName}</option>
		 			</select>
				</dd>
			</dl>
			<dl group="${relateObject==null?"csupMember":relateObject}" id="form-dl-csupMember">
				<dt>帐号信息：</dt>
				<dd>
				
					<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupMemberYesNot=="not"?"checked-not":""}${data.csupMemberYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
					<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupMemberYesNot}"/>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csupHost}" size="16" type="text" id="${NAME}" name="${NAME}" text="${get:CsMember(data.csupMember).csmName}" value="${data.csupMember}"/>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#${NAME}').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?id='+$('#${NAME}').val(),{ctrl:{editable:false,editable:false,visible:true}})"></a>
	 	 		
				</dd>
			</dl>
			<dl group="${relateObject==null?"csupMemo":relateObject}" id="form-dl-csupMemo">
				<dt>备忘信息：</dt>
				<dd>
				
					<a href="javascript:void(0);" title="不包含" class="checkbox ${data.csupMemoYesNot=="not"?"checked-not":""}${data.csupMemoYesNot=="yes"?"checked-yes":""}" for="${NAME}YesNot"></a>
					<input class="YesNot" type="hidden" name="${NAME}YesNot" id="${NAME}YesNot" value="${data.csupMemoYesNot}"/>
		 			<input type="text" class="input" size="16"  maxlength="65535" name="${NAME}" id="${NAME}"  value="${data.csupMemo}"/>
	 	 		
				</dd>
			</dl>
			<div class="line"></div>
			<div class="option">			
				<table border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td>
						<input type="hidden" name="all" value="${all}" />
						<input type="hidden" name="fields" value="${fields}" />
						<button class="button" type="submit">
							&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Zoom.png"/>
							查询&nbsp;&nbsp;</button>		
						<button class="button" onclick="clearForm()" type="button">
							&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778045.png"/>
							重置&nbsp;&nbsp;</button>
					</td>
				  </tr>
				</table>
				<div class="clear"></div>
			</div>
	</form>
</div>
 -->
	
</td></tr><tr><td>
	<s:if test="#request.CTRL.canQuery==true">
  <%@include file="/admin/module/page.jsp"%>
  	</s:if>
  			
	<div class="content">
	    <div class="toolbar">
	    
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="person_table" ref="CsUnitPerson" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	<td width="40" align="center">
			 	选择
			 </td>
			 <td width="120">所属分组</td>
		  	 <td width="120">会员编号</td>
		  	 <td>真实姓名</td>
  			 <td>手机号码</td>
  			 <td>会员卡</td>
  			 <td width="100">实名认证</td>
  			 <td width="100">驾驶认证</td>
  			 <td width="130" class="operation">
  				<a href="javascript:create();">
  				<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-plus.png"/>
  				创建帐号</a>
  			 </td>
		  </tr>
		 </thead>	
		 <tbody>
	 	 <s:iterator value="#request.page.result" id="item" status="i">
	 	 	<get:CsUnitPerson name="person">{
	 	 		csupMember:${item.csmId}
	 	 	}</get:CsUnitPerson>
	 	 	<tr id="${item.csmId}">
	 	 		<td align="center">
					<input class="check" type="checkbox" value="${item.csmId}"/>
				</td>
				<td style="text-indent:0px;">
					<center>${person==null?"&lt;未绑定组&gt;":(person.csupGroup$)}</center>
				</td> 
	 	 		<td style="text-indent:0px;">
	 	 			<center>${item.csmId}</center>
	 	 		</td>
	  			<td>${item.csmName}</td>
	  			<td>${item.csmMobile}</td>
	  			<td>${empty item.csmEvcard?("&lt;未绑定会员卡&gt;"):(item.csmEvcard)}</td>
	  			<td style="text-indent:0px;"><center>${item.csmVReal$}</center></td>
  			 	<td style="text-indent:0px;"><center>${item.csmVDrive$}</center></td>
	  			<td style="text-indent:0px;">
	  				<center>
						<a href="javascript:edit(${item.csmId});">修改</a>
						<a href="javascript:DelPerson(${item.csmId},'${item.csmName}');">删除</a>
						<!-- <a href="#">详情</a> -->
					</center>
				</td>
	  		</tr>
	 	 </s:iterator>
		 </tbody>			 
	  </table>
	  
	  ${lz:set("注释","*****************数据列表结束*****************")}
	  
  </div>
  <%@include file="/admin/module/page.jsp"%>
  
 </td></tr></table>

<script>

	showTips("${tips.value}");

	function href(url,params){
		top.$.showModalDialog({url:url},params,window);
	} 
	
	/**
	* 创建用车帐号
	**/
	function create(){		
		var url = "${basePath}${namespace}person.edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}"};
		href(url,params);
	}

	/**
	* 修改用车帐号
	**/
	function edit(member){
		var url = "${basePath}${namespace}person.edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",member:member};
		href(url,params);
	}
	
	/*
	* 自定义操作
	*/
	function HandleSel(operate,id){
		if(!id){
			var checker=$(".check:checked");
			if(checker.length!=1){
				$.tips("请选择一个需要操作的记录.");
				return;
			}
			id = checker.val();
		}
		
		if(window.getHandle && window.getHandle(operate,id)!=null)//本窗口的操作函数获取句柄
			window.getHandle(operate)(operate,id);
		else if(window.handles && window.handles[operate])//本窗口的操作函数句柄
			window.handles[operate](operate,id);
		else if(typeof(caller)!="undefined" && caller.handles && caller.handles[operate])//调用窗口的操作函数句柄
			caller.handles[operate](operate,id);
		else if(parent && parent.handles && parent.handles[operate])//父窗口的操作函数句柄
			parent.handles[operate](operate,id);
		else{
			var url = "${basePath}${namespace}person_edit.do";
			var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:operate}};
			href(url,params);
		}
	}
	/*
	* 自定义菜单
	*/
	function HandleMenu(operate){
		if(window.getHandle && window.getHandle(operate)!=null)//本窗口的操作函数获取句柄
			window.getHandle(operate)(operate);
		else if(window.handles && window.handles[operate])//本窗口的操作函数句柄
			window.handles[operate](operate);
		else if(typeof(caller)!="undefined" && caller.handles && caller.handles[operate])//调用窗口的操作函数句柄
			caller.handles[operate](operate);
		else if(parent && parent.handles && parent.handles[operate])//父窗口的操作函数句柄
			parent.handles[operate](operate);
		else{
			var url = "${basePath}${namespace}person_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}

	/**
	* 删除用车帐号
	**/
	function DelPerson(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除用车帐号["+flag+"]吗？（删除后将保留个人下单功能）</span>",
				title:"删除用车帐号",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}person_del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);
				}
		 });	
	}
	/**
	* 显示用车帐号详情
	**/
	function DetailsPerson(id)
	{
		var url = "${basePath}${namespace}person_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"用车帐号详情",editable:false,visible:true}};
		href(url,params);
	}
</script>


</body>
</html>