var Host = function () {
	this.shId = null;
};
$(document).ready(function () {
	var stack = jQuery( '#stack ul' ).stackmenu();
	jQuery( '#stack .trigger' ).click( function(){
		stack.stackmenu( 'toggle' );
	});
	initHost();
});

// initHost,set the city
function initHost() {
	$.getJSON("/weixin/ajax_getHost.html?t=" + new Date().getTime(), function (data) {
		if (data != null) {
			// set display span
			Host.shId = data.shId;
			$("#index-city").html(data.shName);
		}
	});
}

// set the host
function setHost(value) {
	if (Host.shId != value) {
		var ajax = {url:"/weixin/ajax_setHost.html?t=" + new Date().getTime(), type:"POST", data:{host:value}, async:true, success:function (data) {
			switch (data) {
			  case "true":
				location.reload();
				break;
			  case "false":
				alert("systen occour error.");
				break;
			}
		}};
		$.ajax(ajax);
	} else {
		return;
	}
}

