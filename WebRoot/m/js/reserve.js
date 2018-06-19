$(document).ready(function() {
	var data = Cache.get('location');
	if (data !== null && data !== undefined) {
		$('#o_from').val(data.data);
	}

	$('.navigate-right').bind('click', function() {
		$('#ccc-modal-box').html('');
		var value = $(this).data('value');
		$.get('ajax_order.html?carid=' + value, function(data) {
			ModalBox.show();
			$('#ccc-modal-box').html(data);
		});
	});
	
	var page = 0;
	var maxPage = $('#pagetotal').val();
	var latitude = $('#latitude').val();
	var longitude = $('#longitude').val();
	var startTimeIndex = $('#startTimeIndex').val();
	var finishTimeIndex = $('#finishTimeIndex').val();
	var host = $('#host').val();
	var carModel = $('#csoModel').val();
	
	distanceConvert(latitude,longitude);
	
	$(window).bind('scroll', function() {
		if ($(document).scrollTop() + $(window).height() >= $(document).height()) {
			if(page < maxPage-1){
				page++;
				window.isLoading=true;
				$('.more-loading').css("display", "block");
				$.get('/m/reserve.html',
						{isAjax:true,page:page,latitude:latitude,longitude:longitude,startTime:startTimeIndex,finishTime:finishTimeIndex,host:host,carModel:carModel},
						function(data){
					//先将临时存放区清空
					//$('#tempdata').html('');
					//将页面载入临时存放区
					//$('#tempdata').append(data);
					//用find方法找出车辆列表
					//var temp = $('#tempdata').find("#carlist");
					//由于find出来的是一个数组，所以取第一个元素即为要加载的数据
					//$('#carlist').append(temp[0].innerHTML);
					$('#carlist').append(data);
					$('.more-loading').css("display", "none");					
					distanceConvert(latitude,longitude);
					window.isLoading=false;
				});
			}else if(!window.isLoading){
				Toast.show("亲，没有更多车辆了哦！");
			}
		}
	});
});


function distanceConvert(latitude,longitude) {
	$.each($(".car-distance"), function(i, val) {
		val.innerHTML = getDistance(latitude,longitude,$(this).data('lat'),$(this).data('lng'));
	});
}

function toRad(d) {
	return d * Math.PI / 180;
}

function getDistance(lat1, lng1, lat2, lng2) {
	//lat为纬度, lng为经度
	var dis = 0;
	var radLat1 = toRad(lat1);
	var radLat2 = toRad(lat2);
	var deltaLat = radLat1 - radLat2;
	var deltaLng = toRad(lng1) - toRad(lng2);
	var dis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(deltaLng / 2), 2)));
	var distance = dis * 6378137;
	if (distance > 99*1000) {
		return ">99KM";
	} else {
	    if(distance<100){
	        return "0.1KM";
	    }else{
		    return (distance/1000).toFixed(1) + "KM";
		}
	}
}