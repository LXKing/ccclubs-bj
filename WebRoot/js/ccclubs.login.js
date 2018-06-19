/* 2013-08-10 */
/*
 * JavaScript Document for ccclubs.com
 * v1.0.6
 
 * Copyright (c) 2013 QS_XiaoGang
 * @email <moneycomehere@vip.qq.com> 
 * @weibo <weibo.com/513387369>
 
 * Copyright (c) 2013 Acathur
 * (http://acathur.com)
 * @email <acathur@qq.com> 
 * @weibo <weibo.com/Acathur>
 */
$(document).ready(function() {
	// bind events
	$('#btn_login').bind('click', function() {
		var strUsername = $.trim($('#username').val());
		var strPwd = $.trim($('#password').val());
		var syscode = $.trim($('#syscode').val());
		var remMe = false;
		if ($('#remMe').length > 0) {
			remMe = $('#remMe').get(0).checked;
		}

		if (strUsername == null || strUsername.length == 0) {
			var msg = "你还没有输入帐号！";
			$('#ccc-login-tips').css("display", "block");
			$('#ccc-login-tips span').html(msg);
			return;
		}
		if (strPwd == null || strPwd.length == 0) {
			var msg = "你还没有输入密码！";
			$('#ccc-login-tips').css("display", "block");
			$('#ccc-login-tips span').html(msg);
			return;
		}
		initMyRSA();
		var ajax = {
			url: "/login_ajaxlogin.html",
			type: "POST",
			data: {
				username: strUsername,
				password: myRSA(strPwd),
				syscode:syscode,
				remMe: remMe
			},
			async: true,
			success: function(data) {
				switch (data) {
					case "100":
						var msg = "成功登录，正在跳转...";
						$('#ccc-login-tips').addClass('succ').css("display", "block");
						$('#ccc-login-tips span').html(msg);
						var from = getQueryString("from");
						if(from == null)
						{
							location.href = '/';
						}else{
							location.href = from;
						}
						
						break;
					case "101":
						var msg = "验证码错误！";
						$('#ccc-login-tips').css("display", "block");
						$('#ccc-login-tips span').html(msg);
						break;
					case "102":
						var msg = "你还没有输入帐号！";
						$('#ccc-login-tips').css("display", "block");
						$('#ccc-login-tips span').html(msg);
						break;
					case "103":
						var msg = "你还没有输入密码！";
						$('#err_tips').html(msg);
						break;
					case "105":
						var msg = "你输入的帐号不正确，请重新输入。";
						$('#ccc-login-tips').css("display", "block");
						$('#ccc-login-tips span').html(msg);
						break;
					case "106":
						var msg = "你输入的密码不正确，<a href=\"/forgot.html\">忘记密码？</a>";
						$('#ccc-login-tips').css("display", "block");
						$('#ccc-login-tips span').html(msg);
						break;
					case "107":
						var msg = "该帐户已经失效，请联系车纷享。";
						$('#ccc-login-tips').css("display", "block");
						$('#ccc-login-tips span').html(msg);
						break;
					case "9999":
						var msg = "系统错误,请稍候再试。";
						$('#ccc-login-tips').css("display", "block");
						$('#ccc-login-tips span').html(msg);
						break;
					default:
						var msg = "未知的返回。";
						$('#ccc-login-tips').css("display", "block");
						$('#ccc-login-tips span').html(msg);
						return;
				}
			}
		}
		$.ajax(ajax);
	});

	$('#password').keydown(function(event) {
        if (event.which == 13 || event.keyCode == 13) {
            $('#btn_login').trigger('click');
        }
    });
});

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}