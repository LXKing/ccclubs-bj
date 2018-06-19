$(document).ready(function () {
	var pThis = $(".panel");
	var count = pThis.find(".panel-holder").size();
	pThis.data("index", 0);
	var execute = function (index) {
		if (index < 0) {
			index = count - 1;
		} else {
			if (index > count - 1) {
				index = 0;
			}
		}
		pThis.find(".panel-holder").addClass("hide");
		$(pThis.find(".panel-holder").get(index)).removeClass("hide");
		pThis.data("index", index);
	};
	pThis.find(".checkout-left").click(function () {
		execute(pThis.data("index") - 1);
		return false;
	});
	pThis.find(".checkout-right").click(function () {
		execute(pThis.data("index") + 1);
		return false;
	});
	
	var _date = new Date(Date.parse($('#newFinish').val().replace(/-/g, "/")));
	var hour = _date.getHours();
	var min = _date.getMinutes();
	setFinishTimeUI(_date.toStringL(), _date.toStringL('F'));
	OrderInfoOperate.setFinishTime(_date.toStringL('F'));
	Cache.save('finish_time',_date.toStringL('F'));
	$("#ccc-finish-time").date({title:"还车时间", curdate:true}, function (datestr, datevalue) {
		setFinishTimeUI(datestr, datevalue);
		OrderInfoOperate.setFinishTime(datevalue);
		$('#re20m').removeClass('active');
		$('#re40m').removeClass('active');
		$('#re1h').removeClass('active');
		$('#re3h').removeClass('active');
	});
	
	$(".panel-split").click(function() {
	    var content = $(this).siblings('.panel-content');
	    if (content.hasClass('hide')) {
			content.removeClass('hide');
		} else {
			content.addClass('hide');
		}
    });
	
});

var setFinishTimeUI = function(str, value) {
	$('#ccc-finish-time').html(str);
	$('#ccc-finish-time').data('value', value);
}

var reordersubmit = function(orderid){
	var btn = $('#reorderbtn');
	btn.attr("disabled", true);
	var finishTime = Cache.get('finish_time').data + ":00";
	$.ajax({
		type: 'get',
		url: 'order_reordersubmit.html',
		data:{
			orderid:orderid,
			newFinishDate: finishTime
		},
		dataType: 'json',
		success: function(data){
			btn.removeAttr("disabled");
			if(data.state){
				location.href = "order_detail.html?orderid="+$('#orderid').val();
			}else{
				Toast.show(data.message);
                return;
			}
		},
		error: function(xhr, type){
			btn.removeAttr("disabled");
			Toast.show('通讯失败，请检查网络');
            return;
		}
	});
};