/* 2013-08-10 */
/*
 * JavaScript Document for ccclubs.com my
 * Copyright (c) 2013 Acathur
 * (http://acathur.com)
 * @email <acathur@qq.com>
 * @weibo <weibo.com/Acathur>
 */
 
 
$(document).ready(function() {
	narrowFix();
	myTipsFix();
	infoBoxToggle();
	
	$(window).resize(narrowFix);
	$(window).scroll(myTipsFix);
});
	
	
	// info box toggle
	function infoBoxToggle() {
		var myUser = $('#ccc-my-header .ccc-my-user');
		var myInfoBox = $('#myInfoBox');
		var myTrigger = 'ccc-my-user-trigger';
		$(document).click(function(event) {
			if ($(event.target).hasClass(myTrigger)||$(event.target).parent().hasClass(myTrigger)) {
				myInfoBox.fadeToggle(0);
				return false;
			} else {
				myInfoBox.hide();
			}
		});
		myInfoBox.click(function(event) {
			event.stopPropagation();
		});
	}
	
	
	// My Tips fix
	function myTipsFix() {
		if ($(window).scrollTop() > 100) {
			$('#ccc-my-global-tips').addClass('ccc-tips-fix');
		} else {
			$('#ccc-my-global-tips').removeClass('ccc-tips-fix');
		}
	}
	
	// for narrow screen
	function narrowFix() {
		var winW = $(window).width()
		if (winW < 1200) {
			$('#ccc-my-page').addClass('ccc-narrow');
		} else {
			$('#ccc-my-page').removeClass();
		}
	}

	// lowercase to Upcase
	function lowercase2Upcase() {
		$.each($(".ccc-month"), function(i, val) {
			switch ($.trim(val.innerHTML)) {
				case "1":
					val.innerHTML = "一月";
					break;
				case "2":
					val.innerHTML = "二月";
					break;
				case "3":
					val.innerHTML = "三月";
					break;
				case "4":
					val.innerHTML = "四月";
					break;
				case "5":
					val.innerHTML = "五月";
					break;
				case "6":
					val.innerHTML = "六月";
					break;
				case "7":
					val.innerHTML = "七月";
					break;
				case "8":
					val.innerHTML = "八月";
					break;
				case "9":
					val.innerHTML = "九月";
					break;
				case "10":
					val.innerHTML = "十月";
					break;
				case "11":
					val.innerHTML = "十一月";
					break;
				case "12":
					val.innerHTML = "十二月";
					break;
			}
		});
	}
