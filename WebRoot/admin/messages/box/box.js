$(function(){
	$("#likeHost").combox();
	$("#likeHost").change(function(){
		window.location.href="index.do?host="+$(this).val();
	});
	$('#boxDialog').show();
	$(".tabs").each(function(){
		var pThis = $(this);
		var focusClass = $(this).attr("focus");
		$(this).find("[ref]").click(function(){
			pThis.find("[ref]").each(function(){
				$($(this).attr("ref")).hide();
				$(this).removeClass(focusClass);				
			});
			$($(this).attr("ref")).show();
			$(this).addClass(focusClass);
		});
	});
	$("#boxDialog .box-header").drag($("#boxDialog"));
	$("#search").focus(function(){
		if($(this).val()=="搜索：会员、订单、车辆")
			$(this).val("");
		if($.trim($("#results .list").html())!="")
			$(".results").show();
	});
	$("#search").blur(function(){
		if($(this).val()=="")
			$(this).val("搜索：会员、订单、车辆");
	});
	$("#search").keyup(function(){
		if(valid($(this).val()))
			search($(this).val(),serial+1);
		else
			serial = 0;
	});
	$("#results .close").click(function(){
		$(".results").hide();
		return false;
	});
	$("#maxi").click(function(){
		$('#boxDialog').removeClass('mini');
		return false;
	});
	$("#mini").click(function(){
		$('#boxDialog').addClass('mini');
		return false;
	});
	$(".box-header .tit a").mousedown(function(){
		$("#maxi").click();
		$(".tabs button[ref='"+$(this).attr("ref")+"']").click();
		return false;
	});
	window.$on("TODO",onTodo);
	window.$on("NOTICE",onNotice);
	window.$on("MESSAGE",onMessage);
	setInterval(onFlush,300);
	reloadTodo(true);
	reloadNotice(true);
});

function onFlush(){
	$(".flush").toggleClass("visibility");
}

function onTodo(object){
	$("#todo-"+object.id).addClass("flush");
	var a = $("<a style='color:#ffffff;' href='javascript:void(0);'></a>").html("待办事项到期警报："+object.content).click(function(){
		$.open('/admin/systematic/todo_edit.do?id='+object.id,reloadTodo);
	});
	$(".box-tabs button[ref='#todos']").say(a,"left");
	Notifiy("待办事项到期警报",object.content);
}
function onNotice(object){
	if($("#notice-"+object.id+"").size()==0){
		var item = $("<div class='item' id='notice-"+object.id+"'></div>").prependTo("#notices .list");
		var tit = $("<a class='tit' href='javascript:void(0)' ref='"+object.id+"'></a>").html(object.content).appendTo(item);
		tit.click(function(){
			$.open('/admin/systematic/notice_edit.do?id='+object.id,reloadNotice);
		});
	}
	$("#notice-"+object.id).addClass("flush");	
	var noticeDialog = null;
	var div = $("<div style='padding:20px;'><a href='javascript:void(0);'></a></div>");
	div.find("a").html("[系统通知]："+object.content).click(function(){
		$.open('/admin/systematic/notice_edit.do?id='+object.id,reloadNotice);
		noticeDialog.close();
	});
	noticeDialog = $.showMsgDialog({
		title:"系统通知",
		content:div,
		flag:"notice-dialog"
	});
	Notifiy("系统通知",object.content);
}
function onMessage(object){	
	$(".box-tabs button[ref='#messages']").say("[提示消息]："+object.content,"left");
	Notifiy("提示消息",object.content);
	var item = $("<div class='item'></div>").prependTo("#messages .list");
	var tit = $("<span class='tit'></span>").html(object.content).appendTo(item);
	$("#messages .list .item:gt(20)").remove();
	var count = $("#messages .list .item").size();
	$("#messageCount").html(count=="0"?"":count);
	if(object.flag=="会员上传资料图片"){
		$.showMsgDialog({flag:"CHECK-MEMBER-"+object.id,title:"会员证件照片认证",url:"/admin/message.member.do?id="+object.id});
	}
}

function heed(){
	$.open('message.heed.do',function(){});
}

function reloadTodo(submited){
	$("#todos .list").empty().load("/admin/message.todo.do?mm="+Math.random(),function(){
		var count = $("#todos .list input").val();
		$("#todoCount").html(count=="0"?"":count);
	});
}
function reloadNotice(submited){
	$("#notices .list").empty().load("/admin/message.notice.do?mm="+Math.random(),function(){
		var count = $("#notices .list input").val();
		$("#noticeCount").html(count=="0"?"":count);
	});
}

function Notifiy(title,content){
	window.Notifications = window.Notifications || window.webkitNotifications;
	var perLel = window.Notifications.checkPermission();
	if(perLel == 0){
		console.log('已授权...');
		Notifications.createNotification(title,content).show();
	}else if(perLel ==1){
		console.log('待授权...');
        window.Notifications.requestPermission(Notifiy);
	}else{
		console.log('已禁止...');
	} 
}



var serial = 0;
var lastKeyword = null;
function search(keyword,s){
	keyword = $.trim(keyword);
	serial = s;
	$(".results").show();
	if($.trim($("#results .list").html())=="")
		$("#results .list").html("<center>正在搜索...</center>");
	setTimeout(function(){
		if(s!=serial){
			return;
		}
		if(keyword == lastKeyword)
			return;	
		lastKeyword = keyword;
		$("#results .list").load("/admin/search.do",{
			keyword:keyword
		},function(){
			$(".results").show();
		});
	},1000);
}
function valid(keyword){
	if(/[\u4e00-\u9fa5]{2,}/g.test(keyword))
		return true;
	if(/[0-9]{,7}/g.test(keyword))
		return false;
	if(/[a-zA-Z0-9]{5,}/g.test(keyword))
		return true;
	return false;
}
function href(url,params){
	top.$.showModalDialog({url:url},params,window);
}
function HandleSel(operate,id,url){
	if(window.getHandle && window.getHandle(operate,id)!=null)
		window.getHandle(operate)(operate,id);
	else if(window.handles && window.handles[operate])
		window.handles[operate](operate,id);
	else if(typeof(caller)!="undefined" && caller.handles && caller.handles[operate])
		caller.handles[operate](operate,id);
	else if(parent && parent.handles && parent.handles[operate])
		parent.handles[operate](operate,id);
	else{
		var params = {id:id,ctrl:{title:operate}};
		href(url,params);
	}
}
var controls={"开门":0,"关门":1,"可启动":2,"断油电":3,"初始化":4,"同步":5};
function getHandle(operate,id){
	if(operate=="充值"||operate=="扣款")
		return recharge;
	if(operate=="发短信")
		return sendSms;
	if(operate=="充小时")
		return rehour;
	if(operate=="重置")
		return resetpwd;
	if(operate=="预订")
		return reserve;
	if(typeof(controls[operate])!="undefined")
		return remote;
}
//远程控制
function remote(operate,id){
	var controls={"开门":0,"关门":1,"可启动":2,"断油电":3,"初始化":4,"同步":5};
	var url = "/admin/control/remote_edit.do";
	var params = {
		
		"csRemote.csrCar":id,
		"csRemote.csrType":controls[operate]
	};
	href(url,params);
}
function recharge(operate,id){
	var url = "/admin/user/member_recharger.do";
	var params = {way:operate,id:id};
	href(url,params);
}
function rechargeSel(operate){
	var checker=$(".check:checked");
	if(checker.length==0){
		$.tips("您未选中任何记录，请至少选择一项.");
		return;
	}
	var ids = "";
	checker.each(function(i){
		if(ids!="")
			ids+=",";
		ids+=$(this).val();
	});
	var url = "/admin/user/member_recharger.do";
	var params = {way:operate,ids:ids};
	href(url,params);
}
function sendSms(operate,id){
	var list=$.getObject("/admin/user/member_query.do?object=true",{csmId:id});
	var member=null;
	if(list && list.length)
		member=list[0].object;
	if(!member || $.trim(member.csmMobile)==""){
		$.tips("该会员没有手机号码，不能发送短信");
		return;
	}
	var url = "/admin/systematic/smsmt_edit.do";
	var params = {
		
		ctrl:{
			fields:{
				cssmMobile:{
					defaultValue:member.csmMobile,
					editable:false
				}			
			}
		}
	};
	href(url,params);
}
function rehour(operate,id){
	var url = "/admin/user/freehour_edit.do";
	var params = {
		
		ctrl:{
			fields:{
				csfhMember:{
					defaultValue:id,
					editable:false
				}			
			}
		}
	};
	href(url,params);
}
function resetpwd(operate,id){
	$.getInput({
		title:"请输入6到16位重置密码",
		callback:function(value){
			if($.trim(value)=="")
				return $.tips("未输入密码");
			if(value.length<6||value.length>16)
				return $.tips("密码长度应为6到16位");
			$.getObject("/admin/user/member_reset.do",{id:id,password:value},function(json){
				$.tips(json.message);
			});
		}
	});
}
function reserve(operate,id){
	var url = "/admin/service/order_edit.do";
	var params = {
		
		ctrl:{
			title:'添加订单',
			fields:{
				csoPayMember:{
					defaultValue:id
				},
				csoUseMember:{
					defaultValue:id
				}			
			}
		}
	};	
	href(url,params);
}
