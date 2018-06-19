//(function(doc, win) {
//	var docEl = doc.documentElement,
//		resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
//		recalc = function() {
//			var clientWidth = docEl.clientWidth;
//			if (!clientWidth) return;
//			docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
//		};
//
//	if (!doc.addEventListener) return;
//	win.addEventListener(resizeEvt, recalc, false);
//	doc.addEventListener('DOMContentLoaded', recalc, false);
//})(document, window);
var URLHelper = {
	DriverInfo: '/driverapp/official/getDriverInfo.ashx',
	ModifyPass: '/driverapp/official/modifyPass.ashx',
	DriverCars: '/driverapp/official/getDriverCars.ashx',
	DriverOrders: '/driverapp/official/getAllDriverOrders.ashx'
};

function showPageBody() {
	$('.pageBody').show();
}

function ajax(url, data, callback) {
	$.ajax({
		type: 'POST',
		async: true,
		data: data,
		dataType: 'json',
		url: url + '?t=' + new Date().getTime(),
		beforeSend: function(xhr, settings) {
			showLoadingToast();
			return true;
		},
		success: function(data) {
			if (!data.success) {
				if (data.code === 100 || data.code === '100')
					showWarnToast('登录失效!');
				else
					showErrorToast(data.text);
				return;
			}
			callback(data.data);
		},
		error: function(xhr, errorType, error) {
			closeLoadingToast();
		},
		complete: function(xhr, status) {
			closeLoadingToast();
		}
	})
}

function isEmpty(obj) {
	if (obj == null) return true;
	return obj.length === 0;
};

/*
@@获取url中的参数
*/
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return r[2];
	return "";
};

function showLoadingToast() {
	var loadingToast = $('#loadingToast');
	if (loadingToast.css('display') != 'none') {
		return;
	}
	loadingToast.show();
}

function closeLoadingToast() {
	var loadingToast = $('#loadingToast');
	if (loadingToast.css('display') === 'none') {
		return;
	}
	loadingToast.hide();
}

function showSuccessToast(content) {
	var $toast = $('#toast');
	var $toastIcon = $('#toast .weui_icon_toast');
	if (!isEmpty(content))
		$('#toast .weui_toast_content').html(content);

	if ($toast.css('display') != 'none') {
		return;
	}
	$toastIcon.removeClass();
	$toastIcon.addClass('weui_icon_toast toast_success');
	$toast.show();
	setTimeout(function() {
		$toast.hide();
	}, 3000);
}

function showWarnToast(content) {
	var $toast = $('#toast');
	var $toastIcon = $('#toast .weui_icon_toast');
	if (!isEmpty(content))
		$('#toast .weui_toast_content').html(content);

	if ($toast.css('display') != 'none') {
		return;
	}
	$toastIcon.removeClass();
	$toastIcon.addClass('weui_icon_toast toast_warn');
	$toast.show();
	setTimeout(function() {
		$toast.hide();
	}, 3000);
}

function showErrorToast(content) {
	var $toast = $('#toast');
	var $toastIcon = $('#toast .weui_icon_toast');
	if (!isEmpty(content))
		$('#toast .weui_toast_content').html(content);

	if ($toast.css('display') != 'none') {
		return;
	}
	$toastIcon.removeClass();
	$toastIcon.addClass('weui_icon_toast toast_error');
	$toast.show();
	setTimeout(function() {
		$toast.hide();
	}, 3000);
}
/*
@@格式化为json对象
*/
function txtToJson(txt) {
	if (txt) {
		var j = {};
		try {
			j = JSON.parse(txt)
		} catch (e) {
			try {
				j = eval("(" + txt + ")")
			} catch (ee) {}
		}
		return j
	}
};

Date.prototype.toStringL = Date.prototype.toStringL || function(format) {
	var m_monthDefault = this.getMonth() + 1;
	var m_date = this.getDate();
	var m_hour = this.getHours();
	var m_min = this.getMinutes();
	var short_fomart = m_monthDefault + '月' + m_date + '日' + ' ' + (m_hour < 10 ? ('0' + m_hour) : m_hour) + ':' + (m_min < 10 ? ('0' + m_min) : m_min);
	var full_fomart = '' + this.getFullYear() + '-' + (m_monthDefault < 10 ? ('0' + m_monthDefault) : m_monthDefault) + '-' + (m_date < 10 ? ('0' + m_date) : m_date) + ' ' + (m_hour < 10 ? ('0' + m_hour) : m_hour) + ':' + (m_min < 10 ? ('0' + m_min) : m_min);

	if (format === 'F')
		return full_fomart
	return short_fomart;
}