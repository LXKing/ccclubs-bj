/* 
 * qsxiaogang
 * 2015-05-28 14:16
 * V1.1
 */
$(document).ready(function() {
	$('#iAgree').iCheck({
		checkboxClass: 'icheckbox_square-blue',
		increaseArea: '20%' // optional
	});
	$('#iAgree').on('ifChecked', function(event){
	    var btn = $('#save-btn');
	    btn.removeClass('disabled');
		btn.attr("onclick", "submit()");
	});
	
	$('#iAgree').on('ifUnchecked', function(event){
	    var btn = $('#save-btn');
	    btn.addClass('disabled');
		btn.attr("onclick", "return false");
	});
	
	document.querySelector('#ccc-city').onclick = function() {
		$('#ccc-modal-box').html('');
		$.get('ajax_city.html?t=' + new Date().getTime(), function(data) {
			ModalBox.show();
			$('#ccc-modal-box').html(data);
			var city_value = Cache.get('host_value').data;
			getSelectCity(city_value);
		});
	};
	
	//初始化城市
	if (OrderInfoOperate.getCityValue() === null || OrderInfoOperate.getCityName() === null) {
		// ajax 
		//		$.post(fetchUrl("sys/getCityInfo.htm"), {
		//          'lng': _longitude,
		//          'lat': _latitude
		//      }, function(e) {
		//          
		//      }, 'json'); TODO:加入获取城市
		OrderInfoOperate.setCity('杭州', 1);
		cityname = '杭州';
		$("#host").attr("value","1");
	} else {
		$('#ccc-city .badge').html(OrderInfoOperate.getCityName());
		$("#host").attr("value",OrderInfoOperate.getCityValue());
	}
	
});

var getSelectCity = function(city_value){
	var city_list = $('.table-modal-view li');
	city_list.each(function(){
		var model_id = $(this).attr("id");
		var tmp_id = "table-city-view-cell-" + city_value;
		if(model_id!=tmp_id){
			$(this).find("a").removeClass("active");
		}
	});
}

function getCheckCode() {
	var regMobile = /^(1[3|4|5|8][0-9])\d{8}$/;
	var phone = $.trim($("#mobile").val());
	if (!regMobile.test(phone)) {
		var msg = "手机号格式不正确";
		Toast.show(msg);
		return;
	}

	var url0 = "/m/ajax_sms.html?type=0&t=" + new Date().getTime();
	Toast.show("校验码已经发送到你的手机，请注意查收");
	$.get(url0, {
		mobile: phone,
		host:$("#host").val()
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
		}else if(data=="103"){
			Toast.show("手机号码已经被注册");
		}
	});
}

function submitRegister() {
	var regMobile = /^(1[0-9]|15[0|3|6|7|8|9]|18[8|9])|(17[0-9]{9})\d{8}$/;
	var phone = $.trim($("#mobile").val());
	var regCity = OrderInfoOperate.getCityValue();
    if(!regMobile.test(phone)){
		var msg = "您输入的手机号码不正确";
		Toast.show(msg);
		return false;
	}
	var code = $.trim($("#checkcode").val());
	if(code===""){
		var msg = "您尚未输入校验码";
		Toast.show(msg);
		return false;
	}
	
	var regPwd = /^[a-zA-Z0-9!@#$%*()_+]{6,20}$/;
	if($.trim($("#txtPwd").val())==""){
		var msg = "密码不能为空";
		Toast.show(msg);
		return false;
	}
    if(!regPwd.test($.trim($("#txtPwd").val()))){
        var msg = "请输入6到20位有效密码";
		Toast.show(msg);
		return false;
	}
    if(!regPwd.test($.trim($("#txtRePwd").val()))){
		var msg = "密码由6-20位英文字母、数字或符号组成";
		Toast.show(msg);
		return false;
	}
	if($.trim($("#txtPwd").val())!=$.trim($("#txtRePwd").val())){
		var msg = "您两次输入的密码不一致";
		Toast.show(msg);
		return false;
	}
	var ischecked = document.getElementById("iAgree").checked;
		if(!ischecked){
			alert("请先同意车纷享用户协议");
			return;
	}
	
	// 需要禁用按钮 
	btn.attr("onclick", "return false");
	btn.addClass('disabled');
    btn.text("正在注册。。。");
	
}