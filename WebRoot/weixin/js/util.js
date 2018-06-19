
var TipsBox = (function() {
	
	function show(text) {
		var width = 14 * text.length;
		$("#tip-box").html(text).css("width", (width + 24) + "px").css("display", "block");
	}
	
	function hide() {
		$("#tip-box").css("display", "none");
	}
	
	return {
		show : show,
		hide : hide
	};
})();

var ModalBox = (function() {
	
	function show() {
		$(".modal").addClass('active');
	}
	
	function hide() {
		$(".modal").removeClass('active');
	}
	
	return {
		show : show,
		hide : hide
	};
})();

var Loading = (function() {
	
	function show() {
		$("#loading-wrapper").css('display', 'block');
		$("#loading-icon").css('display', 'block');
	}
	
	function dismiss() {
		setTimeout(function() {
			$("#loading-wrapper").css('display', 'none');
			$("#loading-icon").css('display', 'none');	
		}, 1000);
	}
	
	return {
		show : show,
		dismiss : dismiss
	};
	
})();


var AlertWindow = (function() {
	
	function showMsg(title, content, showMask, callback) {
		var search = window.location.search;
		var t = search.indexOf("src=alipaycitysvc");
		if(t>0)
		{
			AlipayJSBridge.call('alert', {

				message : content,
				button : '确定'
			}, function() {
					history.back();
			});
		}
		else
		{
		    var $doc = $('body'),
		        $dialog,
		        $mask;
		    if (showMask) {
		        $mask = $('<div class="mask"></div>');
		        $doc.append($mask);
		    }
		    $dialog = $('<div class="dialog-alert"><div class="dialog-alert-header"><a href="" class="dialog-alert-close">&times;</a><p>'+title+'</p></div><div class="dialog-alert-content"><p>'+content+'</p><button class="dialog-btn-ok">确定</button></div></div>');
		    $doc.append($dialog);
	
		    $dialog.css({
		        height: $dialog.height(),
		        top: 0,
		        left: 0,
		        right: 0,
		        bottom: 0
		    });
	
		    $dialog.delegate('a, button', 'click', function() {
		        showMask && $mask.remove();
		        $dialog.remove();
		        $dialog.off();
		        callback && callback();
		        return false;
		    });
			}
	}
	
	return {
		showMsg : showMsg
	};
})();

var CookieManager = (function() {
	
	function getCookie(name) {
		var cookies = document.cookie.split(";");
		for (var i = 0; i < cookies.length; i++) {
			var cookie = cookies[i];
			if (cookie.charAt(0) == ' ')
				cookie = cookie.substr(1);
			var pair = cookie.split("=");
			if (pair==null || pair.length!=2)
				continue;
			if (pair[0] == name)
				return pair[1];
		}
		return null;
	}
	
	function setCookie(name, value, expireDays) {
		var expiredIn = new Date();
		if (expireDays != null)
			expiredIn.setDate(expiredIn.getDate() + expireDays);
		document.cookie = name + "=" + value + (expireDays==null ? "" : ";expires=" + expiredIn.toGMTString());
	}
	
	function ignoreSelectStopTips() {
		setCookie("selectStopTipsIgnored", "true", 365);
	}
	
	function isSelectStopTipsIgnored() {
		var value = getCookie("selectStopTipsIgnored");
		if (value == "true")
			return true;
		else
			return false;
	}
	
	return {
		isSelectStopTipsIgnored : isSelectStopTipsIgnored,
		ignoreSelectStopTips : ignoreSelectStopTips
	};
})();
	
