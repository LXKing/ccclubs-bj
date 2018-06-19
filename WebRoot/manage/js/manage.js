/**
 * 取消右击和复制
 */
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
 * 提示信息
 */
var Toast_input = (function() {
	var intervalCounter;
	// 不要超过12个字
	function show(message) {
		$("#toast_input").html(message);
		$("#toast_input").css('display', 'block');
		intervalCounter = setInterval("toast_input.hide()", 3000);
	}

	function hide() {
		$("#toast_input").css('display', 'none');
		clearInterval(intervalCounter);
	}

	return {
		show: show,
		hide: hide
	};
})();
/*
 * 传参触摸效果
 */
function mytouch(select,the_way,attribute,style,myfunction){
	$(select).on(the_way, function(){
		var i=$(this).html()
		$(this).css(attribute,style),
		myfunction(i);
	});
}
/*
 * 空值触摸效果
 */
function mytouchnull(select,the_way,attribute,style,myfunction){
	$(select).on(the_way, function(){
		var i=$(this).html()
		$(this).css(attribute,style),
		myfunction();
	});
}












