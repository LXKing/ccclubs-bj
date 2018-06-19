<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>首页 - ${lz:config('project_name')}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_mi")}?v=2"></script>
	<link href="${basePath}admin/css/lazy3q.mi.css?v=2" rel="stylesheet" type="text/css" /> 
</head>

<style>
.icon-link center{padding:10px 0;}
.content .block{float:left;margin:15px 0;display:block;width:33%;}
.content .block img{width:50px;height:50px;}
#remoteControl{display:none;position:absolute; height:100%; width:100%; background:#efefef;}
#vehicleMaint{display:none;position:absolute; height:100%; width:100%; background:#efefef;}
#remotTips,#maintTips{color:#ff0000;}
</style>

<body>

	<div id="page" class="page">
		<s:if test="#session.ccclubs_login!=null">
			${lz:set("su", lz:query$("ccclubs_system","select u.su_status from srv_user u where u.su_id = ?",session.ccclubs_login.suId)[0].suStatus)}
			<s:if test="#request.su==null || #request.su!=1">
				<script>
					window.location.href = "${basePath}admin/login.do";
				</script>
			</s:if>
		</s:if>
		<div class="header">
			<span>车纷享系统</span>
			<a style="margin-right:10px;" class="right" href="${basePath}admin/index.do">刷&nbsp;&nbsp;新</a>
		</div>
		
		<div class="content">
				<s:iterator  value="#request.projects" id="project" status="i">
					${lz:set("ispermissions",lz:indexOf(project.spUrl,"permissions")==-1)}
					<s:if test="#request.ispermissions==true">
				
						${lz:set("canView",lz:indexOf(project.spUrl,"admin/control/remote.do")>-1)}
						<s:if test="#request.canView==true">
							<center class="block">
								<a onclick="remoteControl(true);" href="javascript:void(0);">
									<img src="${basePath}admin/images/icons/200882564215296778010.png"/></a>
								<h5>远程控制</h5>
							</center>
						</s:if>
						
						
						${lz:set("canView",lz:indexOf(project.spUrl,"admin/service/maintain.do")>-1)}
						<s:if test="#request.canView==true">
							<center class="block">
								<a onclick="vehicleMaint(true);" href="javascript:void(0);">
									<img src="${basePath}admin/images/icons/200882564229234778010.png"/></a>
								<h5>车辆保养</h5>
							</center>
						</s:if>
						
				
					</s:if>
				</s:iterator>
						
				
				<center class="block">
					<a  href="${basePath}admin/user/regist.do"><img src="${basePath}admin/images/icons/User.png"/></a>
					<h5>会员注册</h5>
				</center>
				
				<center class="block">
					<a  href="${basePath}admin/user/rebind.do"><img src="${basePath}admin/images/icons/20088256422923477804.png"/></a>
					<h5>绑定会员卡</h5>
				</center>
							
			
				<center class="block">
					<a   href="${basePath}admin/permissions/password.do"><img src="${basePath}admin/images/icons/Keychain2.png"/></a>
					<h5>修改密码</h5>
				</center>
				
				<center class="block">
					<a   href="${basePath}admin/login_logout.do"><img src="${basePath}admin/images/icons/2008825642782877805.png"/></a>
					<h5>退出系统</h5>
				</center>
				
		</div>
	</div>
	
	
	
	
	
	<div id="vehicleMaint">		
		<div class="header">
			<a style="margin-left:10px;"  onclick="vehicleMaint(false)" href="#" class="left">返回</a>
			<span>车辆保养</span>
		</div>
		<div style="padding:10px 20px;">
											
			<form>
								
			<label for="csmNumber">车牌号码</label>
			<input class="input" type="text" id="csmNumber" name="csmNumber" placeholder="请输入车牌号码" />

			<label for="csrNumber">当前公里数</label>
			<input class="input" type="text" id="csmCurrKm" name="csmCurrKm" placeholder="请输入当前公里数" />
			
			<br />
								
			<button id="maintSubmit" onclick="maint()" type="button">提交</button>
			
			</form>
			
		</div>
	</div>
	
	
	
	
	
	<div id="remoteControl">		
		<div class="header">
			<a style="margin-left:10px;"  onclick="remoteControl(false)" href="#" class="left">返回</a>
			<span>远程控制</span>
		</div>
		<div style="padding:10px 20px;">
											
			<form>
								
			<label for="csrNumber">车牌号码</label>
			<input class="input" type="text" id="csrNumber" name="csrNumber" placeholder="请输入车牌号码" />

			<label for="type">控制类型</label>
			<select id="csrType" name="csrType" class="select">
				<option value="0">开门</option>
				<option value="1">关门</option>
				<option value="2">允许启动</option>
				<option value="3">刷卡器开</option>
				<option value="4">刷卡器关</option>
				<option value="5">远程鸣笛</option>
			</select>
			
			<br />
								
			<button id="remoteSubmit" onclick="remote()" type="button">提交</button>
			
			</form>
			
		</div>
	</div>
	
	
	
	
	
	
	
	
	<script>
	<s:if test="#session.tips.notNull==true">
	//错误提示
	alert("${tips.value}");
	</s:if>	
	
	
	
	//////////////////////////车辆保养//////////////////////////////
	function vehicleMaint(bShow){
		if(bShow){
			$("#page").hide();
			$("#vehicleMaint").show();
		}else{
			$("#page").show();
			$("#vehicleMaint").hide();
		}
	}
	function maint(){
		if($.trim($("#csmNumber").val())==""){
			alert("请输入车牌号码");
			return;
		}
		if($.trim($("#csmCurrKm").val())==""){
			alert("请输入当前公里数");
			return;
		}
		$("#maintSubmit").attr("disabled","disabled");
		$.ajax({
			url:"${basePath}admin/service/maintain_maintain.do",
			data:{
				number:$("#csmNumber").val(),
				km:$("#csmCurrKm").val()
			},
			type:"post",
			dataType:"json",
			success:function(data){						
				if(data.success==true){
					alert(data.message);
					$("#page").show();
					$("#vehicleMaint").hide();
				}else{
					alert(data.message);
				}
				$("#maintSubmit").removeAttr("disabled");
			},
			error:function(){
				alert("提交车辆保养数据出错");
				$("#maintSubmit").removeAttr("disabled");
			}
		});
	}
	
	
	
	
	
	
	
	//////////////////////////远程控制//////////////////////////////
	function remoteControl(bShow){
		if(bShow){
			$("#page").hide();
			$("#remoteControl").show();
		}else{
			$("#page").show();
			$("#remoteControl").hide();
		}
	}	
	function remote(){
		if($.trim($("#csrNumber").val())==""){
			alert("请输入车牌号码");
			return;
		}
		$("#remoteSubmit").attr("disabled","disabled");
		$.ajax({
			url:"${basePath}admin/control/remote_remote.do",
			data:{
				number:$("#csrNumber").val(),
				type:$("#csrType").val()
			},
			type:"post",
			dataType:"json",
			success:function(data){						
				if(data.success==true){
					alert(data.message);
					$("#page").show();
					$("#remoteControl").hide();
				}else{
					alert(data.message);
				}
				$("#remoteSubmit").removeAttr("disabled");
			},
			error:function(){
				alert("远程操作出错");
				$("#remoteSubmit").removeAttr("disabled");
			}
		});
	}
	</script>
	
    
</body>
</html>