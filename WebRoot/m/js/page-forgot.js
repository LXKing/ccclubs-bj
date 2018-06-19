/* 
 * qsxiaogang
 * 2015-05-28 16:36
 * V1.1
 */
$(document).ready(function() {

});

function getCheckCode() {
	var regMobile = /^(1[3|4|5|8][0-9])\d{8}$/;
	var phone = $.trim($("#mobile").val());
	if (!regMobile.test(phone)) {
		var msg = "手机号格式不正确";
		Toast.show(msg);
		return;
	}

	var url = "/m/home/account_exist.html?txtMobile=" + phone + "&t=" + new Date().getTime();
	$.get(url, function(data) {
		switch (data) {
			case "true":
				var msg = "该号码尚未注册";
				Toast.show(msg);
				break;
			case "false":
				var msg = "手机号格式不正确";
				Toast.show(msg);
				break;
			case "used":
				var url0 = "/m/home/account_sms.html?type=1&t=" + new Date().getTime();
				Toast.show("校验码已经发送到你的手机，请注意查收");
				$.get(url0, {
					mobile: phone
				}, function(data) {
					if (data == "success") {
						var btn = $('#j-resend-msg');
						btn.attr("onclick", "return false");
						btn.addClass('off');
						var time = 59;
						var timer = setInterval(function() {
							btn.text(time + " 秒后，重新获取");
							--time;
							if (time < 1) {
								clearInterval(timer);
								btn.text("重新获取校验码");
								btn.removeClass('off');
								btn.attr("onclick", "getCheckCode()");
							}
						}, 1000);
					}else if(data=="101"){
						Toast.show("手机号码格式错误");
					}else if(data=="102"){
						Toast.show("两次注册短信验证码发送时间间隔不能小于一分钟");
					}
				});
				break;
		}
	});
}