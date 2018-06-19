
document.body.style.webkitUserSelect='none'; 
var Utils = (function() {
	function getWidth() {
		return window.innerWidth;
	}

	function getHeight() {
		return window.innerHeight;
	}

	function isWeixin(){  
		var ua = navigator.userAgent.toLowerCase();  
		if(ua.match(/MicroMessenger/i)=="micromessenger") {  
		        return true;  
		} else {  
		        return false;  
		}  
    }  
	return {
		getWidth: getWidth,
		getHeight: getHeight,
		isWeixin:isWeixin
	};
})();
/**
 * 模态窗口
 */
var ModalBox = (function() {

	function show() {
		$("#ccc-layer").css('display', 'block');
		$('body').css('overflow', 'hidden');
		$('body').css('overflow-y', 'hidden');
	}

	function hide() {
		$("#ccc-layer").css('display', 'none');
		$('body').css('overflow', 'auto');
		$('body').css('overflow-y', 'auto');
	}

	return {
		show: show,
		hide: hide
	};
})();

/**
 * 提示信息
 */
var Toast = (function() {
	var intervalCounter;
	// 不要超过12个字
	function show(message) {
		$("#toast").html(message);
		$("#toast").css('display', 'block');
		intervalCounter = setInterval("Toast.hide()", 3000);
	}

	function hide() {
		$("#toast").css('display', 'none');
		clearInterval(intervalCounter);
	}

	return {
		show: show,
		hide: hide
	};
})();

/**
 * 数据缓存
 * todo  对数据进行加密
 */
var Cache = (function() {
	var GET_KEY_PREFIX = '_C_';

	/**
	 * 缓存从服务端获取的数据
	 * @param key
	 * @param value
	 */
	var save = function(key, value) {
			var data = {
				data: value,
				cacheTime: new Date()
			}
			window.localStorage.setItem(GET_KEY_PREFIX + key, JSON.stringify(data));
		}
		/**
		 * 获取本地已缓存的数据
		 */
	var get = function(key) {
		var storage = window.localStorage;
		if(storage.hasOwnProperty(GET_KEY_PREFIX + key)){
			return JSON.parse(window.localStorage.getItem(GET_KEY_PREFIX + key));
		}else{
			return null;
		}
	}

	/**
	 * 清空本地所有缓存
	 */
	var clear = function() {
		var storage = window.localStorage;
		for (var key in storage) {
			if (key.indexOf(GET_KEY_PREFIX) == 0) {
				storage.removeItem(GET_KEY_PREFIX + key);
			}
		}
	}

	/**
	 * 清空指定key缓存
	 */
	var remove = function(key) {
		var storage = window.localStorage;
		storage.removeItem(GET_KEY_PREFIX + key);
	}

	return {
		get: get,
		save: save,
		remove: remove,
		clear: clear
	}

})();

// set global font-size ,root font-size 
//(function(doc, win) {
//	var docEl = doc.documentElement,
//		resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
//		recalc = function() {
//			var clientWidth = docEl.clientWidth;
//			if (!clientWidth) return;
//			if(clientWidth>=640) clientWidth = 640;
//			docEl.style.fontSize = 20 * (clientWidth / 320) + 'px';
//		};
//	if (!doc.addEventListener) return;
//	win.addEventListener(resizeEvt, recalc, false);
//	doc.addEventListener('DOMContentLoaded', recalc, false);
//})(document, window);

//$(document).on("click", "#ccc-overpage", ModalBox.hide);
$(document).on('click', '.modal-close', ModalBox.hide);

/**  
 * * 判断是否null  
 * * @param data  */
function isNull(data){
	return (data == "" || data == undefined || data == null) ? "无" : data;
}
/*
*光标始终在最后
*
*/
function changecheck(){
	setCaretToPos(document.getElementById("o_from"), 118);
}
function setSelectionRange(input, selectionStart, selectionEnd) {
  if (input.setSelectionRange) {
    input.focus();
    input.setSelectionRange(selectionStart, selectionEnd);
  }
  else if (input.createTextRange) {
    var range = input.createTextRange();
    range.collapse(true);
    range.moveEnd('character', selectionEnd);
    range.moveStart('character', selectionStart);
    range.select();
  }
}

function setCaretToPos (input, pos) {
  setSelectionRange(input, pos, pos);
}
$("#ccc-overpage").click(function(){
	ModalBox.hide();
	if (Ncallback === undefined) {
		return false;
	} else {
		Ncallback(false);
	}
});
