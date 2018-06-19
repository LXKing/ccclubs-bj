
/* 2013-08-10 */
/*
 * JavaScript Document for ccclubs.com
 * Copyright (c) 2013 Acathur
 * (http://acathur.com)
 * @email <acathur@qq.com>
 * @weibo <weibo.com/Acathur>
 */
var Host = function () {
	this.shId = null;
	/* marker manager */
	this.shName = null;
};

/* baidu */
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fa55d5e3694c9e658c560fdf941cd2edd' type='text/javascript'%3E%3C/script%3E"));


$(document).ready(function () {
	fullscreen();
	headerFix();
	navToggle();
	initHost();
	$(window).resize(fullscreen);
	$(window).scroll(headerFix);
});
if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
	location.href = "/goodbye-ie6.html";
}

	// fullscreen
function fullscreen() {
	var menuH = $(".ccc-home-logo").height() + 524, winW = $(window).width(), winH = Math.max(($(window).height()), menuH);
	$("#ccc-fullscreen").css({"width":winW, "height":winH});
	if (winH / winW > 0.5625) {
		$(".ccc-background img").css({"width":"auto", "height":"100%"});
	} else {
		$(".ccc-background img").css({"width":"100%", "height":"auto"});
	}
}
	
	
	// header fix
function headerFix() {
	if ($(window).scrollTop() > 0) {
		$("#ccc-header").addClass("ccc-header-fix");
		$("#ccc-elec").hide();
	} else {
		$("#ccc-header").removeClass("ccc-header-fix");
		$("#ccc-elec").show();
	}
}
	
	
	// sub nav
function navToggle() {
	var nav = $("#ccc-city");
	var navMy = $(".ccc-nav-my", nav);
	var subNav = $(".ccc-sub-nav", nav);
	$(document).mouseover(function (event) {
		if ($(event.target).hasClass("ccc-nav-my")) {
			subNav.show();
		} else {
			subNav.hide();
		}
	});
	subNav.mouseover(function (event) {
		event.stopPropagation();
	});
}
	
	// initHost,set the city
function initHost() {
	$.getJSON("/ajax_getHosts.html?t=" + new Date().getTime(), function (data) {
		if (data != null) {
			
			var html = "";
			for(var i=0; i<data.hostList.length; i++){
				var host = data.hostList[i];
				html += "<li><a href=\"javascript:;\" data=\""+host.shId+"\" onclick=\"setHost("+host.shId+");\">"+host.shName+"</a></li>";
			}
			$("#host-ol").html(html);
			
			$(".ccc-sub-nav").css({
				"height":data.hostList.length*40
			});
			$(".ccc-sub-nav-bg").css({
				"height":data.hostList.length*40
			});
			
			Host.shId = data.current.shId;
			Host.shName = data.current.shName;
			
			//如果是廊坊，定位到北京
			if(data.current.shId == 4){
				Host.shId = 2;
				Host.shName = "北京";
			}
			
			$("#ccc-city .nav_city_current").html(Host.shName);
		}
	});
}

// set the host
function setHost(value) {
	if (Host.shId != value) {
		var ajax = {url:"/ajax_setHost.html?t=" + new Date().getTime(), type:"POST", data:{host:value}, async:true, success:function (data) {
			switch (data) {
			  case "true":
				location.reload();
				break;
			  case "false":
				alert("systen occour error.");
				break;
				return;
			}
		}};
		$.ajax(ajax);
	} else {
		return;
	}
}	
	
	
	// float layer
function showLayer() {
	$("#ccc-layer").fadeIn(500);
	$("body").css("overflow", "hidden");
	//$("#ccc-global-cont").css("-webkit-filter", "blur(2px)");
	$("#ccc-fullscreen").css("-webkit-filter", "blur(2px)");
	$("#ccc-my-page").css("-webkit-filter", "blur(2px)");
}
function closeLayer() {
	$("#ccc-layer").fadeOut(400);
	$("#ccc-overpage").stop().animate({marginTop:"-50%", opacity:0}, 300).delay(300).queue(function () {
		$(this).removeAttr("style");
	});
	$("body").css("overflow", "auto");
	$("#ccc-global-cont").css("-webkit-filter", "none");
	$("#ccc-fullscreen").css("-webkit-filter", "none");
	$("#ccc-my-page").css("-webkit-filter", "none");
}
	
	
	// global tips
function showTips(text, status, timeout, callback) {
	var gTips = $("#ccc-global-tips");
	gTips.removeClass().addClass(status);
	gTips.children("span").html("").html(text);
	gTips.show().stop().css("margin-left", -(gTips.width() + 40) / 2).animate({top:"50%", opacity:1}, 300);
	if (timeout !== undefined && timeout !== "") {
		setTimeout(function () {
			closeTips();
			if (callback !== undefined) {
				setTimeout(callback, 300);
			}
		}, timeout * 1000);
	}
}
function closeTips() {
	var gTips = $("#ccc-global-tips");
	gTips.stop().animate({top:"-50%", opacity:0}, 300).queue(function () {
		$(this).removeAttr("style");
	});
}
	
	
	// global confirm
function showConfirm(title, text, trueLabel, returnLabel, callback, cancelhandler) {
	var gConfirm = $("#ccc-global-confirm");
	showLayer();
	gConfirm.addClass("ccc-show");
	gConfirm.find("h3").text(title);
	if((typeof text=='string') && text.constructor==String){
		gConfirm.find("#ccc-confirm-tip").html(text);
	}else{
		gConfirm.find("#ccc-confirm-tip").append(text);
	}
	gConfirm.find("#ccc-true").text(trueLabel);
	gConfirm.find("#ccc-return").text(returnLabel);
	gConfirm.find(".ccc-cfm-close").one("click", function () {
		closeConfirm();
		if (callback !== undefined && $(this).hasClass("ccc-cfm-true")) {
			callback();
		}else{
			if (cancelhandler !== undefined){
				cancelhandler();
			}
		}
	});
	$(document).one("click", "#ccc-overclose", closeConfirm);
	this.confirm_win = gConfirm;
}

function closeConfirm() {
	$("#feedback_textarea").val(''); 
	$("#ccc-global-confirm .ccc-cfm-close").off("click");
	closeLayer();
	$("#ccc-global-confirm").removeClass("ccc-show");
}
	
	// time trans
function dateDiff(interval, date1, date2) {
	var objInterval = {"D":1000 * 60 * 60 * 24, "H":1000 * 60 * 60, "M":1000 * 60, "S":1000, "T":1};
	interval = interval.toUpperCase();
	var dt1 = new Date(Date.parse(date1.replace(/-/g, "/")));
	var dt2 = new Date(Date.parse(date2.replace(/-/g, "/")));
	try {
		return Math.round((dt2.getTime() - dt1.getTime()) / eval("objInterval." + interval));
	}
	catch (e) {
		return e.message;
	}
}
$(document).on("click", "#ccc-overclose", closeLayer);
$(document).on("click", "#ccc-global-tips.error", closeTips);
if (window.console) {
	console.log("Copyright \xa9 " + (new Date()).getFullYear() + " CCClubs. All Rights Reserved");
}

