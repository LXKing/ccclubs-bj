/* 2013-08-10 */
/*
 * JavaScript Document for ccclubs.com
 * Copyright (c) 2013 Acathur
 * (http://acathur.com)
 * @email <acathur@qq.com>
 * @weibo <weibo.com/Acathur>
 */


$(document).ready(function() {
	
	/* CCClubs Maps */
	
	var cccMapCont = document.getElementById('ccc-fullscreen-map');
	var cccCenter = new google.maps.LatLng(30.2623905, 120.1028902);
	var cccMapOptions = {
		center: cccCenter,
		zoom: 12,
		minZoom: 10,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		streetViewControl: false,
		mapTypeControl: false,
		panControlOptions: {
			position: google.maps.ControlPosition.RIGHT_CENTER
		},
		zoomControlOptions: {
			style: google.maps.ZoomControlStyle.LARGE,
			position: google.maps.ControlPosition.RIGHT_CENTER
		}
	};
	var cccMap = new google.maps.Map(cccMapCont, cccMapOptions);
	
	$(window).resize(function() {
		cccMap.panTo(cccCenter);
	})
	
	var cccFilter = $('#ccc-widget-filter>ul');
	
	var markerImg = 'images/icons/marker-sprites@2x.png';
	var markerSize = new google.maps.Size(34, 40);
	var markerScaledSize = new google.maps.Size(74, 141);
	var markerShadow = new google.maps.MarkerImage(
		'images/icons/marker-sprites@2x.png',
		new google.maps.Size(39, 33), //size
		new google.maps.Point(35, 7), //origin
		new google.maps.Point(7, 33), //anchor
		new google.maps.Size(74, 141) //scaled size
	);
	
	$.getJSON('json/parking-lots.json', function(data) {
		$.each(data, function(index, i) {
			var cccMarker = new google.maps.Marker ({
				position: new google.maps.LatLng(i['Latitude'], i['Longitude']),
				icon: {
					url: markerImg,
					size: markerSize,
					scaledSize: markerScaledSize
				},
				shadow: markerShadow,
				title: i['Name'],
				map: cccMap
			});
			
			$('li.available>a', cccFilter).click(function() {
				$class = $(this).parent('li');
				$marker = cccMarker;
				setMarkerVisible();
			});
			
			google.maps.event.addListener(cccMarker, 'click', function() {
				showViewCard()
	
				$.get('ajax/viewcar.html',function(data){
					$('#ccc-widget-viewcar .cccScrollbar_container').html(data);
					$('#ccc-widget-viewcar').cccScrollbar('update');
					$('#ccc-widget-viewcar').cccScrollbar('scrollTo','left',{scrollInertia:400});
				});
				
			});
			
		});
	});
	
	// Temporary (Presets)
	$.getJSON('json/parking-lots-preset.json', function(data) {
		$.each(data, function(index, i) {
			var cccMarkerPre = new google.maps.Marker ({
				position: new google.maps.LatLng(i['Latitude'], i['Longitude']),
				icon: {
					url: markerImg,
					size: markerSize,
					scaledSize: markerScaledSize,
					origin: new google.maps.Point(0, 50)
				},
				shadow: markerShadow,
				title: i['NodeName'],
				map: cccMap,
				visible: false // hidden
			});
			
			$('li.presets>a', cccFilter).click(function() {
				$class = $(this).parent('li');
				$marker = cccMarkerPre;
				setMarkerVisible();
			});
			
		});
	});
	
	// Temporary (Gas Station)
	$.getJSON('json/GasStation.json', function(data) {
		$.each(data, function(index, i) {
			var cccMarkerGas = new google.maps.Marker ({
				position: new google.maps.LatLng(i['Latitude'], i['Longitude']),
				icon: {
					url: markerImg,
					size: markerSize,
					scaledSize: markerScaledSize,
					origin: new google.maps.Point(0, 100)
				},
				shadow: markerShadow,
				title: i['Name'],
				map: cccMap,
				visible: false // hidden
			});
			
			$('li.gas-station>a', cccFilter).click(function() {
				$class = $(this).parent('li');
				$marker = cccMarkerGas;
				setMarkerVisible();
			});
			
		});
	});
	
	// Show & Hide Marker fn
	function setMarkerVisible() {
		if ($marker.getVisible()==true) {
			$marker.setVisible(false);
			$class.removeClass('sel');
		} else if ($marker.getVisible()==false) {
			$marker.setVisible(true);
			$class.addClass('sel');
		} else {
			return false
		}
	}
	
	// Show & Close View Card fn
	function showViewCard() {
		var $vc =  $('#ccc-widget-viewcard');
		var $ib = $('#ccc-widget-viewinfo', $vc);
		if ($vc.height()!==150) {
			$vc.stop().animate({height:150},400,'easeOutCirc');
			$ib.stop().animate({marginRight:0},500,'easeOutCirc');
		} else {
			$vc.stop().animate({height:0},300).delay(200).animate({height:150},400,'easeOutCirc');
			$ib.stop().delay(300).animate({marginRight:'80%'},0).delay(200).animate({marginRight:0},500,'easeOutCirc');
		}
	}
	
	function closeViewCard() {
		var $vc =  $('#ccc-widget-viewcard');
		var $ib = $('#ccc-widget-viewinfo', $vc);
		$vc.stop().animate({height:0},300);
		$ib.stop().delay(300).animate({marginRight:'80%'},0);
	}
	
	$('#ccc-widget-viewcard-close').click(function() {
		closeViewCard()
	});
	
	
	/* CCClubs View Card Scroll */
	
	$('#ccc-widget-viewcar').cccScrollbar({
		horizontalScroll: true,
		scrollInertia: 500,
		mouseWheelPixels: 282,
		advanced:{
			autoExpandHorizontalScroll: true
		},
		callbacks:{
			onScroll:function() {
				snapScrollbar();
			}
		}
	});
	
	
	// Snap Scrollbar fn
	function snapScrollbar(){
		var snapTo = [];
		$('#ccc-widget-viewcar ul>li').each(function(){
			var $this = $(this);
			var thisX = $this.position().left;
			snapTo.push(thisX);
		});
		var posX = $('#ccc-widget-viewcar .cccScrollbar_container').position().left;
		var closestX = findClosest(Math.abs(posX),snapTo)-10;
		$('#ccc-widget-viewcar').cccScrollbar('scrollTo',closestX,{
			scrollInertia: 250,
			callbacks: false
		});
	}
	function findClosest(num,arr){
		var curr = arr[0];
		var diff = Math.abs(num-curr);
		for(var val=0; val<arr.length; val++){
			var newdiff = Math.abs(num-arr[val]);
			if(newdiff<diff){
				diff = newdiff;
				curr = arr[val];
			}
		}
		return curr;
	}
	
	
	// Keyboard Nav fn
	$('#ccc-widget-viewcar').hover(function() {
		$(document).data({'keyboard-input':'enabled'});
		$(this).addClass('keyboard-input');
	},function(){
		$(document).data({'keyboard-input':'disabled'});
		$(this).removeClass('keyboard-input');
	});
	
	$(document).keydown(function(e) {
		if($(this).data('keyboard-input')==='enabled') {
			var activeElem = $('.keyboard-input'),
				activeElemPos = Math.abs($('.keyboard-input .cccScrollbar_container').position().left),
				pixelsToScroll = 282;
			if (e.which===37) { //scroll left
				e.preventDefault();
				if (pixelsToScroll>activeElemPos) {
					activeElem.cccScrollbar('scrollTo','left');
				} else {
					activeElem.cccScrollbar('scrollTo',(activeElemPos-pixelsToScroll),{
						scrollInertia:500,
						scrollEasing:'easeOutCirc'
					});
				}
			} else if (e.which===39){ //scroll right
				e.preventDefault();
				activeElem.cccScrollbar('scrollTo',(activeElemPos+pixelsToScroll),{
					scrollInertia:500,
					scrollEasing:'easeOutCirc'
				});
			}
		}
	});
	
});