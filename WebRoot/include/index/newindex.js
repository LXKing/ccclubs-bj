$(document).ready(function() {
	fullscreen();
	headerFix();
	navToggle();
	
	$(window).resize(fullscreen);
	$(window).scroll(headerFix);
	
	$('#google_play').click(function(){
		showConfirm();
		return false;
	});
});

if (navigator.userAgent.indexOf('MSIE 6.0')>0) {
	 location.href= '/goodbye-ie6.html';
}

// sub nav
function navToggle() {
	var nav = $('#ccc-header');
	var navMy = $('.ccc-nav-my',nav);
	var subNav = $('.ccc-sub-nav',nav);
	
	$(document).mouseover(function(event) {
		if ($(event.target).hasClass('ccc-nav-my')) {
			subNav.show();
		} else {
			subNav.hide();
		}
	});
	subNav.mouseover(function(event) {
		event.stopPropagation();
	});
}

//fullscreen
function fullscreen() {
	var menuH = $('.ccc-home-logo').height() + 524,
		winW = $(window).width(),
		winH = Math.max(($(window).height()),menuH);
	
	$('#ccc-fullscreen').css({'width':winW,'height':winH});
	if (winH/winW > 0.5625) {
		$('.ccc-background img').css({'width':'auto','height':'100%'});
	} else {
		$('.ccc-background img').css({'width':'100%','height':'auto'});
	}
}


// header fix
function headerFix() {
	if ($(window).scrollTop() > 0) {
		$('#ccc-header').addClass('ccc-header-fix');
	} else {
		$('#ccc-header').removeClass('ccc-header-fix');
	}
}

//float layer
function showLayer() {
	$('#ccc-layer').fadeIn(500);
	$('body').css('overflow','hidden');
	$('#ccc-global-cont').css('-webkit-filter','blur(2px)');
	$('#ccc-fullscreen').css('-webkit-filter','blur(2px)');
	$('#ccc-my-page').css('-webkit-filter','blur(2px)');
}

function closeLayer() {
	$('#ccc-layer').fadeOut(400);
	$('#ccc-overpage').stop().animate({marginTop:'-50%',opacity:0},300).delay(300).queue(function() {
		$(this).removeAttr('style');
	});
	$('body').css('overflow','auto');
	$('#ccc-global-cont').css('-webkit-filter','none');
	$('#ccc-fullscreen').css('-webkit-filter','none');
	$('#ccc-my-page').css('-webkit-filter','none');
}


// global tips
function showTips(text,status,timeout,callback) {
	var gTips = $('#ccc-global-tips');
	gTips.removeClass().addClass(status);
	gTips.children('span').html('').html(text);
	gTips.show().stop().css('margin-left',-(gTips.width()+40)/2).animate({top:'50%',opacity:1},300);
	if (timeout!==undefined && timeout!=='') {
		setTimeout(function(){
			closeTips();
			if (callback!==undefined) {
				setTimeout(callback,300);
			}
		},timeout*1000);
	};
}

function closeTips() {
	var gTips = $('#ccc-global-tips');
	gTips.stop().animate({top:'-50%',opacity:0},300).queue(function() {
		$(this).removeAttr('style');
	});
}


// global confirm
function showConfirm(title,text,trueLabel,returnLabel,callback) {
	var gConfirm = $('#ccc-global-confirm');
	showLayer();
	gConfirm.addClass('ccc-show');
	gConfirm.find('h3').text(title);
	gConfirm.find('#ccc-confirm-tip').html(text);
	gConfirm.find('#ccc-true').text(trueLabel);
	gConfirm.find('#ccc-return').text(returnLabel);
	gConfirm.find('.ccc-cfm-close').one('click',function(){
		closeConfirm();
		if (callback!==undefined && $(this).hasClass('ccc-cfm-true')) {
			callback();
		}
	});
	$(document).one('click','#ccc-overclose',closeConfirm);
}

function closeConfirm() {
	$('#ccc-global-confirm .ccc-cfm-close').off('click');
	closeLayer();
	$('#ccc-global-confirm').removeClass('ccc-show');
}

// time trans
function dateDiff(interval, date1, date2) {
	var objInterval = { 'D': 1000 * 60 * 60 * 24, 'H': 1000 * 60 * 60, 'M': 1000 * 60, 'S': 1000, 'T': 1 };
	interval = interval.toUpperCase();
	var dt1 = new Date(Date.parse(date1.replace(/-/g, '/')));
	var dt2 = new Date(Date.parse(date2.replace(/-/g, '/')));
	try {
		return Math.round((dt2.getTime() - dt1.getTime()) / eval('objInterval.' + interval));
	}
	catch (e) {
		return e.message;
	}
}


$(document).on('click','#ccc-overclose',closeLayer);
$(document).on('click','#ccc-global-tips.error',closeTips);

if (window.console) {
	console.log('Copyright © '+ (new Date()).getFullYear() +' CCClubs. All Rights Reserved');
}

function carover(obj){
	$(obj).removeClass("ccc-car-img");
	$(obj).addClass("ccc-img-border");
}
function caroverout(obj){
	$(obj).removeClass("ccc-img-border");
	$(obj).addClass("ccc-car-img");
}

function friendsover(obj, imgNum){
	$(obj).attr('src','images/index/'+imgNum+'-green.png');
}
function friendsoverout(obj, imgNum){
	$(obj).attr('src','images/index/'+imgNum+'.jpg');
}

function changecity(){
	$('#ccc-city').hide();
	$('#ccc-city-change').show();
}
