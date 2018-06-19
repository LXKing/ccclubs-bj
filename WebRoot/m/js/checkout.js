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
		// todo animate
	};
	pThis.find(".checkout-left").click(function () {
		execute(pThis.data("index") - 1);
		return false;
	});
	pThis.find(".checkout-right").click(function () {
		execute(pThis.data("index") + 1);
		return false;
	});
});
$(document).ready(function () {
	$("#ccc-start-time").date({title:"取车时间", curdate:true}, function (datestr, datevalue) {
		setStartTimeUI(datestr, datevalue);
		OrderInfoOperate.setStartTime(datevalue);
	});
	$("#ccc-finish-time").date({title:"还车时间", curdate:true}, function (datestr, datevalue) {
		setFinishTimeUI(datestr, datevalue);
		OrderInfoOperate.setFinishTime(datevalue);
	});

	init_orderinfo();
	
	$(document).on("click", ".ccc-switcher #freeDepositLable", function (event) {
		if ($(event.target).parent().hasClass("on")) {
			$(this).parent().removeClass("on");
			$("#freeDepositHide").val(0);
			if ($(".ccc-switcher #freeHoursLable").parent().hasClass("on")) {
				$("#freeDepositHide").val(1);
			}
		} else {
			$(this).parent().addClass("on");
			if ($(".ccc-switcher #freeDepositLable").parent().hasClass("on")) {
				$("#freeDepositHide").val(1);
			}
		}
	});
	
	$(document).on('click','.panel-split',function() {
	    var content = $(this).siblings('.panel-content');
	    if (content.hasClass('hide')) {
			content.removeClass('hide');
		} else {
			content.addClass('hide');
		}
    });
    
	$("#j-insure .tag-blue").click(function() {
    	if($(this).hasClass('on')){
    	    $(this).removeClass('on');
    	    $('#insureType').attr("value","0");
    	}else{
    	    $(this).siblings('.tag-blue').removeClass('on');
    	    $(this).addClass('on');
    	    if(this.id=='insureHour'){
    	    	$('#insureType').attr("value", $('#insureHour_hidden').val());
    	    }else if(this.id=='insureMile'){
    	    	$('#insureType').attr("value", $('#insureMile_hidden').val());
    	    }
    	}
    });
  
	document.querySelector('#checkout-reserve-btn').onclick = function() {
		var free_val = $('#fhAmount').val();
		if(free_val===''||free_val===null){
			free_val = 0;
		}
		if(!isNaN(free_val)&&free_val>=0){
			if($('#freehour').val()<parseFloat(free_val)){
	        	Toast.show('您的免费小时数不够，请获得免费小时后再使用');
	            return;
	        }else{
	        	//异地还车网点
            	var yidiwd_value = $("#yidi_wd").val();
            	var yidiwd_text = $("#yidi_wd option").eq($("#ID").attr("selectedIndex")).text();
            	if (typeof(yidiwd_value) == "undefined") {
					yidiwd_value = -1;
				}
            	var insureType =  $('#insureType').val();
            	var start_time = OrderInfoOperate.getStartTime() + ":00";
            	var finish_time = OrderInfoOperate.getFinishTime() + ":00";
            	var credit = false;
            	if ($("#freeDepositHide").val()==1) {
            		credit =　true;
         		}
            	$.ajax({
            		type: 'get',
            		url: 'checkout_confirm.html',
            		data:{
            			freeHours:　free_val,
            			csoStart: start_time,
            			csoFinish: finish_time,
            			csoAvoid: $('#insureType').val(),
            			csoCar: $('#carid').val(),
            			credit: credit,
            			yidiwd_value: yidiwd_value,
            			yidiwd_text: encodeURIComponent(encodeURIComponent(yidiwd_text))
            		},
            		dataType: 'json',
            		success: function(data){
            			if(data.state){
            				location.href = 'confirm.html';
            			}else{
            				Toast.show(data.message);
                            return;
            			}
            		},
            		error: function(xhr, type){
            			Toast.show('通讯失败，请检查网络');
                        return;
            		}
            	});
	        }
		}else{
			Toast.show('免费小时输入格式不正确。请重新输入');
            return;
		}
	};
});
var init_orderinfo = function () {
	if (OrderInfoOperate.getStartTime() === null || OrderInfoOperate.getFinishTime() === null) {
		//设置取还，车时间
		init_ordertime();
	} else {
		// 判断缓存时间，如果时间间隔超过20分钟，重新设置取还车时间
		var startTime = Cache.get('start_time');
		var finishTime = Cache.get('finish_time');
		var start = new Date(Date.parse(startTime.data.replace(/-/g, "/")));
		var end = new Date(Date.parse(finishTime.data.replace(/-/g, "/")));
		if (dateDiffD("M", new Date(startTime.cacheTime), new Date()) > 20) {
			init_ordertime();
		} else {
			setStartTimeUI(start.toStringL(), start.toStringL('F'));
			OrderInfoOperate.setStartTime(startTime.data);
			setFinishTimeUI(end.toStringL(), end.toStringL('F'));
			OrderInfoOperate.setFinishTime(finishTime.data);
		}
	}
};