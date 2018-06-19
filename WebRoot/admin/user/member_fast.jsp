<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>快速会员审核</title>
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
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		window.basePath="${basePath}";
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

<style>
<!--
.pointerror{position:absolute;z-index:8;height:0px;width:0px; overflow:visible;color:#ffffff;left:44px;top:44px;white-space: nowrap;}
.pointerror *{color:#ffffff;}
.mask{position:absolute;z-index:1;background:#000000;top:0px;left:0px;height:100%;width:100%;filter:alpha(opacity=40);-moz-opacity:0.40;opacity:0.40;}
.fore{position:absolute;z-index:3;top:0px;left:0px;width:100%;height:100%;}
.fore *{color:#ffffff;font-size:12px;}

.preview{position:absolute;z-index:1;top:0px;left:0px;height:100%;width:100%;overflow:hidden;background:url(${basePath}admin/images/background.jpg) #999999;}
.previewtable{table-layout:fixed;}
.previewtable td{overflow:hidden;}
.preview .pic{position:absolute;z-index:1;top:0px;left:0px;height:100%;width:100%;overflow-y:scroll;}
.preview .pic img{max-width:100%;max-height:100%;margin:0px auto;}
.preview .pag{position:absolute;z-index:1;top:50%;left:0px;height:0px;width:100%;overflow:visible;}
.preview .pag .btn{position:absolute;width:88px;height:88px;top:-44px;text-align:center; cursor:pointer;}
.preview .pag .prev{left:20px;}
.preview .pag .next{right:20px;}
.preview .pag .popup{display:none;left:50%;top:0px;position:absolute;width:0px;height:0px;overflow:visible;}
.preview .pag .popup .panel{width:360px; height:88px;line-height:25px;position:absolute;top:-44px;left:-180px;}
.preview .pag .popup .panel .fore table td{}
.preview .pag .cancel .panel{height:120px;top:-100px;}
.preview .pag .cancel .panel .smsInput{background:transparent;border:1px solid #999999;width:100%;height:28px;font-size:12px;}
.info{position:absolute;z-index:3;top:0px;left:0px;height:32px;width:100%;color:#ffffff;font-size:14px;}

.okcheck td{color:#ffffff;font-size:18px;text-align:center;}
.uncheck td{color:#ffffff;font-size:12px;}

.b{display:none;}

.s2 .b{display:block;}
.s2 .a{display:none;}

-->
</style>

<body>

<script>

<lz:set name="imgHtml">
<img onerror="this.src='${basePath}admin/images/image.500.png'" class="idcard a"/>
<img onerror="this.src='${basePath}admin/images/image.500.png'" class="driver b"/>
</lz:set>

function next(params){
	$.loading(true);
	window.member = null;
	$('.preview').removeClass('s2');
	$(".popup").hide();
	$('.btn').show();
	$(".preview .pic .imgs").html("${lz:js(imgHtml)}");
	$.getObject("${basePath}admin/user/member_fastCheck.do",params||{},function(data){
		$.loading(false);
		if(!data || !data.success){
			Alert("系统可能出错了，请尝试再试一次");
			return false;
		}
		if(!data.member){
			Alert("可能没有待审核会员了，请到会员管理中确认是否真的没有待审核会员了");
			return false;
		}
		if(data.submit=="ok"){
			$.toast("会员审核成功，您可以继续审核下一个.");
		}
		window.member = data.member;
		$(".property").html("");
		$(".pointerror span").html("");
		for(var o in data.member){
			$("."+o).html(data.member[o]);
		}
		$(".preview").removeClass("s2").addClass("s1");
		$(".idcard").attr("src",data.member.idcard);
		$(".driver").attr("src",data.member.driver);
		var errors=[];
		if($.empty(data.member.mobile))
			errors[errors.length]="手机号码未填写";
		if($.empty(data.member.address))
			errors[errors.length]="寄送地址未填写";
		if($.empty(data.member.number))
			errors[errors.length]="驾症号码未填写";
		if($.empty(data.member.idcard))
			errors[errors.length]="身份证照片未上传";
		if($.empty(data.member.driver))
			errors[errors.length]="驾证照片未上传";
		if(errors.length>0){
			$(".pointerror span").html("审核信息提醒："+errors.join(","));
		}
	});
}

function submitCheck(){
	next({
		id:window.member.id,
		csmVDrive:1,
		csmVReal:1
	});
}

function submitUnCheck(){
	next({
		id:window.member.id,
		csmVDrive:3,
		csmVReal:3,
		message:$("#smsInput").val()
	});
}

function onSize(){
	$(".preview .previewtable").height($(window).height());
}

$(function(){
	next();
	
	window.mousewheel=new Date().getTime();
	window.wheelcount = 0;
	window.wheels=false;
	$(document).on("mousewheel",function(event){
		if((new Date().getTime()-window.mousewheel)<50){
			if(event.originalEvent.wheelDeltaY>0)
				window.wheelcount++;
			else
				window.wheelcount--;
		}else{
			window.wheelcount=0;
			window.wheels=false;
		}
		window.mousewheel = new Date().getTime();
		if(window.wheels){
			return false;
		}else if(window.wheelcount>1){
			$(".mup:visible").click();
			window.wheelcount=0;
			window.wheels = true;
		}else if(window.wheelcount<-1){
			$(".mdown:visible").click();
			window.wheelcount=0;
			window.wheels = true;
		}else{
			return false;
		};
		return false;		
	});
	
	$("#byInput").click(function(){
		if($(this).is(":checked")){
			$("#smsInput").focus().select();
		}
	});
	
	$(window).resize(onSize);
	$(document).resize(onSize);
	onSize();
});

</script>


<div class="preview">
	<div class="pic">
		<table class="previewtable" width="100%" border="1" cellspacing="0" cellpadding="0">
		  <tr>
		    <td class="imgs" align="center" valign="middle">
				<img onerror="this.src='${basePath}admin/images/image.500.png'" class="idcard a"/>
				<img onerror="this.src='${basePath}admin/images/image.500.png'" class="driver b"/>
			</td>
		  </tr>
		</table>
	</div>
	<div class="pag">
		<div class="btn prev">
			<div onclick="$('.btn').hide();$('.cancel').show();" class="fore a mup">
				<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td title="试试在屏幕任意位置鼠标向上轮滑，跟点这个按钮一样的效果哦" align="center" valign="middle">&lt;&lt;&lt;<br/>审核不通过</td>
				  </tr>
				</table>
			</div>
			<div onclick="$('.preview').removeClass('s2');" class="fore b mup">
				<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td title="试试在屏幕任意位置鼠标向上轮滑，跟点这个按钮一样的效果哦" align="center" valign="middle">&lt;&lt;&lt;<br/>身份证照片</td>
				  </tr>
				</table>
			</div>
			<div class="mask"></div>
		</div>
		<div class="btn next">
			<div onclick="$('.preview').addClass('s2');" class="fore a mdown">
				<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td title="试试在屏幕任意位置鼠标向下轮滑，跟点这个按钮一样的效果哦" align="center" valign="middle">&gt;&gt;&gt;<br/>驾证照片</td>
				  </tr>
				</table>
			</div>
			<div onclick="$('.btn').hide();$('.confirm').show();" class="fore b mdown">
				<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td title="试试在屏幕任意位置鼠标向下轮滑，跟点这个按钮一样的效果哦" align="center" valign="middle">&gt;&gt;&gt;<br/>审核通过</td>
				  </tr>
				</table>
			</div>
			<div class="mask"></div>
		</div>
		
		
		<div class="confirm popup">
			<div class="panel">
				<div class="fore">
					<table class="okcheck" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					    <td>
					    	<a class="mup" onclick="$('.btn').show();$('.confirm').hide();" title="试试在屏幕任意位置鼠标向上轮滑，跟点这个按钮一样的效果哦" href="javascript:void(0);">取消</a>
					    	&nbsp;&nbsp;&nbsp;&nbsp;
							确定审核通过吗？
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="mdown" onclick="submitCheck()" title="试试在屏幕任意位置鼠标向下轮滑，跟点这个按钮一样的效果哦" href="javascript:void(0);">确定</a>
						</td>
					  </tr>
					</table>
				</div>
				<div class="mask"></div>
			</div>
		</div>
		
		
		
		<div class="cancel popup">
			<div class="panel">
				<div class="fore">
					<table class="uncheck" border="0" cellspacing="3" cellpadding="0" style="margin:10px;">
					  <tr>
					  	<td>
					  		您好，你在车纷享注册的会员帐号信息审核未通过，原因：
					  	</td>
					  </tr>
					  <tr>
					    <td>
							<input align="absmiddle" type="text" class="smsInput" id="smsInput" name="smsInput" placeholder="输入自定义短信内容"/>
						</td>
					  </tr>
					  <tr>
					  	<td align="center">
							<a class="mup" onclick="submitUnCheck()" title="试试在屏幕任意位置鼠标向上轮滑，跟点这个按钮一样的效果哦" href="javascript:void(0);">提交</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="mdown" onclick="$('.btn').show();$('.cancel').hide();" title="试试在屏幕任意位置鼠标向下轮滑，跟点这个按钮一样的效果哦" href="javascript:void(0);">返回</a>
					  	</td>
					  </tr>
					</table>
				</div>
				<div class="mask"></div>
			</div>
		</div>
	</div>
</div>


<div class="pointerror">
	<span></span>
</div>





<div class="info">
	<div class="mask"></div>
	<div class="fore">
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
		  <tr>
		    <td width="50" align="right">姓名</td>
		    <td class="property name" width="72" >&nbsp;</td>
		    <td width="50" align="right">手机</td>
		    <td class="property mobile" width="100">&nbsp;</td>
		    <td width="80" align="right">证件号码</td>
		    <td class="property number" width="150">&nbsp;</td>
		    <td width="50" align="right">地址</td>
		    <td class="property address" width="*">&nbsp;</td>
		  </tr>
		</table>
	</div>
</div>







</body>
</html>