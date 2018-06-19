<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
<lz:JSEncode>
$(function(){
	var $config = {};
	$config["host"]="${lz:config("$.mqtt.ip")}";
	$config["port"]=Number("${lz:or(lz:config("$.mqtt.port"),1883)}");
	$config["username"]="${lz:config("$.mqtt.username")}";
	$config["password"]="${lz:config("$.mqtt.password")}";
	$config["flashPath"]="${basePath}admin/flash/";
	$config["clientid"]="user_${ccclubs_login_id}";
	$config["topic"]="notice";
	$config["onConnect"]=function($message){
		//消息格式：event/{type}/{host}/{from}/{to}
		window["console"].log("event server connected");		
		window["mqtt"].subscribe("event/+/+/+/${ccclubs_login_id}",0);//订阅消息
		var $hosts = "${ccclubs_login.suHost}".split(",");
		for(var o in $hosts)
			window["mqtt"].subscribe("event/+/"+$.trim($hosts[o])+"/+/null",0);//所在地全员消息
		window["mqtt"].subscribe("event/+/0/+/null",0);//全网全员消息
		
		window["mqtt"].subscribe("event/DATA/+/+/+",0);//全网全员消息
	};
	$config["onError"]=function($message){
		window["console"].log("错误 - "+JSON.stringify($message));
	};
	$config["onReceive"]=function($topicName,$topicContent){
		if(${lz:config("debug")=="true"})
			window["console"].log("消息",$topicName,$topicContent);	
		var $typeName = $topicName.split("/")[1];
		var $array = eval("("+$topicContent+")");
		var $object={};
		$object.flag=$array[0];
		$object.content=$array[1];
		$object.id=$array[2];
		window["$"+"fire"]($typeName,$object);
		if($object && $object.flag)
			window["$"+"fire"]($typeName+"."+$object.flag,$object);		
	};
	//创建MQTT
	window["mqtt"] = new window["IMqtt"]($config);
	$(window).unload(function(){
		window["mqtt"].close();
		window["console"].log("窗口关闭");
		return false;
	});
	$("<div></div>").appendTo("body").load("${basePath}/admin/message.do?mm="+Math.random());	
});
<s:if test="#session.ccclubs_login.suId==0">
window["restart"]=function(){
	if(confirm("确定要重启服务吗？(擦！自己跟自己说好别扭)"))
		window["mqtt"].publish("event/DATA/0/0/null","[\"system.restart\",\"\",0]",0);
}
</s:if>
//系统已经重新启动
window["$"+"on"]("DATA.system.started",function($data){
	var $params = {};
	$params["username"]="${ccclubs_login_id==0?(lz:config("super_username")):(ccclubs_login.suUsername)}";
	$params["password"]="${ccclubs_login_id==0?(lz:config("super_password")):(ccclubs_login.suPassword)}";
	$.getObject("${basePath}admin/login_ajax.do",$params,function($data){
		if($data && $data.success==true){
			$("#restartLoadding").remove();
		}else{
			window.location.href="login.do";
		}
	},function(){
		window.location.href="login.do";
	});
});
<lz:set name="restartLoadding">
<div id="restartLoadding" style="position:fixed;z-index:999999; top:0px; left:0px; width:100%; height:100%;">
	<table style="position:fixed;z-index:999999; top:0px; left:0px; width:100%; height:100%;" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td align="center" valign="middle"><span style="font-size:18px;color:#ffffff;">系统正在升级，请稍等片刻.....</span><br/><br/><img align="absmiddle" src="${basePath}admin/images/loading.gif"></td>
	  </tr>
	</table>
	<div class="overlay" style="z-index:999998;filter:alpha(opacity=60);opacity: 0.6;"></div>
</div>
</lz:set>
//系统准备重新启动
window["$"+"on"]("DATA.system.restart",function($data){
	if($("#restartLoadding").size()==0)
		$("${lz:js(restartLoadding)}").appendTo("body");
});
</lz:JSEncode>
</script>
