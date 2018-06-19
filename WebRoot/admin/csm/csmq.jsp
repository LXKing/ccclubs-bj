<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>在线客服系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<link rel="stylesheet" type="text/css" href="${basePath}admin/js/imqtt/chat.css">
	<link rel="stylesheet" type="text/css" href="${basePath}admin/js/imqtt/emojis/emoji.css">
	<script type="text/javascript" src="${basePath}admin/js/imqtt/IMqtt.E.js"></script>
	
</head>  



<body scroll="no">
<script>
/**  聊天对象结构信息定义
class ChatEntity {
	Number id;//ID号/帐号
	String name;//名称
	String header;//头像/图标
	String explain;//说明/签名信息
	String type;//类型  V:访客 S:客服 G:组
	String server;//消息服务器名称
	String platform;//所在平台 WEB WEIXIN ALIPAY SMS
	Date last;//最近时间
	String data;//关联数据信息
	Number state;//状态  0：不在线，1：在线，2：封号
	String classify;//分类名称
}
**/


$(function(){
	IMqttChat.create({
		send_message:null
		,get_message:null
		,get_members:function($sid,callback){
			return [{
				id:333,//ID号/帐号   如果服务ID为0时，表示随机待客服
				name:"游客",//昵称
				header:"faces/gg/1",//头像/图标
				explain:"未设置签名",//说明/签名信息
				type:"V",//类型  V:访客 S:客服 G:组
				server:"default",//默认连接游客消息服务器
				last:new Date(),//最近时间
				data:null,//关联数据信息
				state:1,//状态  0：不在线，1：在线，2：封号
				classify:null//分类名称
			}];
		}
		,get_informat:function($sid,callback){
			
			return "广告信息"+$sid;
		}
		,submit_feedback:null//留言反馈提交地址或函数
		,on_login:function(){//登录回调函数，您可在此函数中实现自己的登录机制，记得登录成功后IMqttChat.setInfo下下
			alert("登录未实现");
			
		}
		,init:function(){
			//创建对话(对话人信息,右边面板内容)  -- 其中右面板内容参数为html或图片
			var chatDialog = IMqttChat.chatWith("L0");
		}
	});
	IMqttChat.setUserInfo({
		id:12334,//ID号/帐号
		name:"小纷",//昵称
		header:"faces/kf/0",//头像/图标
		explain:"我说...算了,不说了😄",//说明/签名信息
		type:"V",//类型  V:访客 S:客服 G:组
		server:"default",//默认连接游客消息服务器
		platform:"EXTMAN",//所在平台 WEB WEIXIN ALIPAY SMS
		last:new Date(),//最近时间
		data:null,//关联数据信息
		state:1,//状态  0：不在线，1：在线，2：封号
		classify:null//分类名称
	});
	//设置背景颜色,文字颜色
	IMqttChat.setStyle("#18a0e0","#ffffff","#f2f2f2");
	
	
	
	
	
	
	
	//添加面板(面板名称,类型)  -- 其中类型： N:匿名用户 M:会员 P:联系人 S:客服  L:在线用户列表 G:群  D:讨论组 C:自定义面板  
	var gsImqttPanel = IMqttChat.addPanel("分类访客","L");	
	gsImqttPanel.addEntity({
		id:0,//ID号/帐号   如果服务ID为0时，表示随机待客服
		name:"全部访客组",//昵称
		header:"faces/cx/0",//头像/图标
		explain:"未设置签名",//说明/签名信息
		type:"L",//类型  V:访客 S:客服 G:组
		server:"default",//默认连接游客消息服务器
		platform:"WEB",//所在平台 WEB WEIXIN ALIPAY SMS
		last:new Date(),//最近时间
		data:null,//关联数据信息
		state:1,//状态  0：不在线，1：在线，2：封号
		classify:"杭州"//分类名称
	});
	gsImqttPanel.addEntity({
		id:1,//ID号/帐号   如果服务ID为0时，表示随机待客服
		name:"网站访客组",//昵称
		header:"faces/cx/1",//头像/图标
		explain:"未设置签名",//说明/签名信息
		type:"L",//类型  V:访客 S:客服 G:组
		server:"default",//默认连接游客消息服务器
		platform:"WEB",//所在平台 WEB WEIXIN ALIPAY SMS
		last:new Date(),//最近时间
		data:null,//关联数据信息
		state:1,//状态  0：不在线，1：在线，2：封号
		classify:"杭州"//分类名称
	});
	gsImqttPanel.addEntity({
		id:2,//ID号/帐号   如果服务ID为0时，表示随机待客服
		name:"微信访客组",//昵称
		header:"faces/cx/2",//头像/图标
		explain:"未设置签名",//说明/签名信息
		type:"L",//类型  V:访客 S:客服 G:组
		server:"default",//默认连接游客消息服务器
		platform:"WEB",//所在平台 WEB WEIXIN ALIPAY SMS
		last:new Date(),//最近时间
		data:null,//关联数据信息
		state:1,//状态  0：不在线，1：在线，2：封号
		classify:"杭州"//分类名称
	});
	gsImqttPanel.addEntity({
		id:3,//ID号/帐号   如果服务ID为0时，表示随机待客服
		name:"支付宝访客",//昵称
		header:"faces/cx/3",//头像/图标
		explain:"未设置签名",//说明/签名信息
		type:"L",//类型  V:访客 S:客服 G:组
		server:"default",//默认连接游客消息服务器
		platform:"WEB",//所在平台 WEB WEIXIN ALIPAY SMS
		last:new Date(),//最近时间
		data:null,//关联数据信息
		state:1,//状态  0：不在线，1：在线，2：封号
		classify:"杭州"//分类名称
	});
	gsImqttPanel.addEntity({
		id:4,//ID号/帐号   如果服务ID为0时，表示随机待客服
		name:"微网站访客",//昵称
		header:"faces/cx/4",//头像/图标
		explain:"未设置签名",//说明/签名信息
		type:"L",//类型  V:访客 S:客服 G:组
		server:"default",//默认连接游客消息服务器
		platform:"WEB",//所在平台 WEB WEIXIN ALIPAY SMS
		last:new Date(),//最近时间
		data:null,//关联数据信息
		state:1,//状态  0：不在线，1：在线，2：封号
		classify:"杭州"//分类名称
	});
	IMqttChat.focus("分类访客");
	
	
	
	//添加面板(面板名称,类型)  -- 其中类型： V:访客 S:客服 G:群组
	var gsImqttPanel = IMqttChat.addPanel("排队访客","V");	
	IMqttChat.focus("排队访客");
	
	
});
</script>


</body>
</html>
