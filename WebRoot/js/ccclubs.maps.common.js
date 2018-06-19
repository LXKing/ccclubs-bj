/* 2013-08-10 */
/*
 * JavaScript Document for ccclubs.com
 * v1.0.6
 
 * Copyright (c) 2013 QS_XiaoGang
 * @email <moneycomehere@vip.qq.com> 
 * @weibo <weibo.com/513387369>
 
 * Copyright (c) 2013 Acathur
 * (http://acathur.com)
 * @email <acathur@qq.com> 
 * @weibo <weibo.com/Acathur>
 */

var CCClubsMap = function() {
	this.map = null;
	/* marker manager */
	this.mapCluster = null;
	/* markers arrays */
	this.mapDB = null;
	this.circle = null;
	this.center = null;

	this.zoom = null;
	this.mapCont = null;

	//this.parkingVisible = false;
	//this.gasVisible = false;
	this.currentMarker = null;
	//this.selectedMarker = null;
	this.infoWindow = null;
	this.radius = 1500;
	//
	this.markerImg = 'images/icons/marker-sprites@2x.png';
	this.markerSize = new google.maps.Size(34, 40);
	this.markerSizeX1 = new google.maps.Size(51, 60);
	this.markerScaledSize = new google.maps.Size(74, 141);
	this.markerScaledSizeX1 = new google.maps.Size(110, 212);
	this.markerShadow = new google.maps.MarkerImage(
		'images/icons/marker-sprites@2x.png',
		new google.maps.Size(39, 33), //size
		new google.maps.Point(35, 7), //origin
		new google.maps.Point(7, 33), //anchor
		new google.maps.Size(74, 141) //scaled size
	);
};

/* Initialize map
 * before Initialize() function you must set
 * Zoom , Center , MapCont
 */
CCClubsMap.prototype.initialize = function() {
	// load map
	var myOptions = {
		center: this.center,
		zoom: this.zoom,
		minZoom: 9,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		streetViewControl: false,
		mapTypeControl: false,
		scaleControl: true,
		panControlOptions: {
			position: google.maps.ControlPosition.RIGHT_CENTER
		},
		zoomControlOptions: {
			style: google.maps.ZoomControlStyle.LARGE,
			position: google.maps.ControlPosition.RIGHT_CENTER
		}
	};

	this.map = new google.maps.Map(this.mapCont, myOptions);
	// create cluster instance
	var clusterValueOffset = [41, 30];
	/*  */
	var mcOptions = {
		gridSize: 60,
		maxZoom: 14,
		clusterClass: 'ico-cluster',
		styles: [{
			url: "images/icons/marker-cluster1.png",
			width: 47,
			height: 60,
			anchor: clusterValueOffset,
			textColor: '#ffffff',
			textSize: 12
		}, {
			url: "images/icons/marker-cluster2.png",
			width: 57,
			height: 60,
			anchor: clusterValueOffset,
			textColor: '#ffffff',
			textSize: 12
		}, {
			url: "images/icons/marker-cluster3.png",
			width: 65,
			height: 60,
			anchor: clusterValueOffset,
			textColor: '#ffffff',
			textSize: 12
		}],
		calculator: function(markers, numStyles) {
			var index = 0;
			var title = "";
			var count = markers.length;

			if (count >= 0 && count < 10) {
				index = 1;
			} else if (count >= 10 && count < 100) {
				index = 2;
			} else if (count >= 100 && count < 1000) {
				index = 3;
			} else {
				index = 4;
			}

			index = Math.min(index, numStyles);
			return {
				text: count.toString(),
				index: index,
				title: title
			};
		}
	};
	this.mapCluster = new MarkerClusterer(this.map, [], mcOptions);

	this.circle = new google.maps.Circle({
		fillColor: '#C8E0FA',
		fillOpacity: 0.5,
		strokeColor: '#007ab6',
		strokeOpacity: 0.8,
		strokeWeight: 1,
		map: this.map
	});
	// load data
	this.updateMap();
	// check location capabilities and enable/disable geo button
	this.checkLocationCapabilities();
	var parent = this;
	google.maps.event.addDomListener(window, 'resize', function(e) {
		parent.map.panTo(parent.center);
	});
	// bind events
	$('#ccc-search-btn').bind('click', $.proxy(this.searchAddress, this));
	$('#ccc-searchbox').keydown(function(event) {
        if (event.which == 13 || event.keyCode == 13) {
            $('#ccc-search-btn').trigger('click');
        }
    });
}

/*
 * load map data ,such as
 * parkingLots, parkingLotsPreset, gasStation
 */
CCClubsMap.prototype.updateMap = function() {
	var parent = this;
	if (parent.mapDB != undefined) {
		$.each(parent.mapDB, function(category, categoryData) {
			$.each(categoryData.markers, function(key, marker) {
				marker.setMap(null);
			});
		});
	}
	parent.mapDB = {
		'parkingLots': {
			'markers': new Array()
		},
		'parkingLotsPreset': {
			'markers': new Array()
		},
		'gasStation': {
			'markers': new Array()
		}
	};

	var cccFilter = $('#ccc-widget-filter>ul');

	$.when(
		$.getJSON('/ajax_outlets.html?csoCanOrder=1', function(data) {
			$.each(data, function(index, i) {
				var cccMarker = new google.maps.Marker({
					position: new google.maps.LatLng(i['csoLatitude'], i['csoLongitude']),
					icon: {
						url: parent.markerImg,
						size: parent.markerSize,
						scaledSize: parent.markerScaledSize
					},
					shadow: parent.markerShadow,
					title: i['csoName'],
					map: parent.map
				});
				parent.mapDB['parkingLots']['markers'].push(i);
				parent.mapCluster.addMarker(cccMarker);
				/* bind click event */
				google.maps.event.addListener(cccMarker, 'click', function() {
					parent.showViewcar(i, cccMarker);
				});
			});
		}),
		// Temporary (Presets)
		$.getJSON('/ajax_outlets.html?csoCanOrder=0', function(data) {
			$.each(data, function(index, i) {
				var cccMarkerPre = new google.maps.Marker({
					position: new google.maps.LatLng(i['csoLatitude'], i['csoLongitude']),
					icon: {
						url: parent.markerImg,
						size: parent.markerSize,
						scaledSize: parent.markerScaledSize,
						origin: new google.maps.Point(0, 50)
					},
					shadow: parent.markerShadow,
					title: i['csoName'],
					map: parent.map,
					visible: false // hidden
				});
				parent.mapDB['parkingLotsPreset']['markers'].push(cccMarkerPre);
				$('li.presets>a', cccFilter).click(function() {
					$class = $(this).parent('li');
					$marker = cccMarkerPre;
					parent.closeInfoWindow();
					setMarkerVisible();
				});
				google.maps.event.addListener(cccMarkerPre, 'click', function() {
					parent.openInfoWindow(cccMarkerPre, parent.getPresetParkingLotsInfo(i));
				});
			});

		}),
		// Temporary (Gas Station)
		$.getJSON('json/GasStation.json', function(data) {
			$.each(data, function(index, i) {
				var cccMarkerGas = new google.maps.Marker({
					position: new google.maps.LatLng(i['Latitude'], i['Longitude']),
					icon: {
						url: parent.markerImg,
						size: parent.markerSize,
						scaledSize: parent.markerScaledSize,
						origin: new google.maps.Point(0, 100)
					},
					shadow: parent.markerShadow,
					title: i['Name'],
					map: parent.map,
					visible: false // hidden
				});
				parent.mapDB['gasStation']['markers'].push(cccMarkerGas);
				$('li.gas-station>a', cccFilter).click(function() {
					$class = $(this).parent('li');
					$marker = cccMarkerGas;
					parent.closeInfoWindow();
					setMarkerVisible();
				});
				google.maps.event.addListener(cccMarkerGas, 'click', function() {
					parent.openInfoWindow(cccMarkerGas, parent.getGasStationInfo(i));
				});
			});
		})
	).done(function() {
		//set the count of parkingLots
		$('#ccc-widget-filter li.available').find('.value').text(parent.mapDB['parkingLots']['markers'].length);

		//set the count of parkingLotsPreset
		$('#ccc-widget-filter li.presets').find('.value').text(parent.mapDB['parkingLotsPreset']['markers'].length);

		//set the count of gasStation
		$('#ccc-widget-filter li.gas-station').find('.value').text(parent.mapDB['gasStation']['markers'].length);
	});

	function setMarkerVisible() {
		if ($marker.getVisible() == true) {
			$marker.setVisible(false);
			$class.removeClass('sel');
		} else if ($marker.getVisible() == false) {
			$marker.setVisible(true);
			$class.addClass('sel');
		} else {
			return false
		}
	}
}
/*
 * check location capabilities and enable/disable geo button
 */
CCClubsMap.prototype.checkLocationCapabilities = function() {
	if (navigator.geolocation) {
		//TODO
		//$('.map-search .btn-location').addClass('active');
	}
}
/*
 * showMyLocation
 */
CCClubsMap.prototype.showMyLocation = function() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(getPositionSuccess, getPositionError, {
			enableHighAccuracy: true,
			maximumAge: 0
		});
	}

	function getPositionSuccess(position) {
		// correction the location
		var latlng = new google.maps.LatLng((parseFloat(position.coords.latitude) - 0.0023).toFixed(6), (parseFloat(position.coords.longitude) + 0.0046).toFixed(6));
		//TODO
		this.updateCurrentLocation(latlng, ' I am here ', 14);
	}

	function getPositionError(err) {
		//alert(err.code + "|" + err.message);
	}
}

/*
 * update current location marker
 */
CCClubsMap.prototype.updateCurrentLocation = function(latlng, title, zoom) {
	// marker already on map, just update it
	if (this.currentMarker) {
		this.currentMarker.setPosition(latlng);
		this.currentMarker.setTitle(title);
	} else { // create new marker
		this.currentMarker = new google.maps.Marker({
			icon: {
				url: 'images/icons/map-current-marker@2x.png',
				size: new google.maps.Size(18, 18),
				scaledSize: new google.maps.Size(18, 18),
				anchor: new google.maps.Point(9, 9)
			},
			position: latlng,
			title: title,
			map: this.map
		});
	}
	this.setBaseMarkersIcon();
	this.circle.setRadius(this.radius);
	this.circle.setCenter(latlng);
	this.center = latlng;
	this.map.setCenter(this.center);
	this.map.panTo(this.center);
	this.map.setZoom(zoom);
	this.parkingLotsCalculate(this.center);
}

/*
 * search address on the map
 */
CCClubsMap.prototype.searchAddress = function() {
	var keyword = $('#ccc-searchbox').val();
	var parent = this;
	var geocoder = new soso.maps.Geocoder({
		complete: function(result) {
			//map.setCenter(result.detail.location);
			var lat = result.detail.location.getLat();
			var lng = result.detail.location.getLng();
			var latlng = new google.maps.LatLng(lat, lng);
			parent.updateCurrentLocation(latlng, keyword, 14);
		}
	});

	if (keyword != '' && keyword != undefined) {
		geocoder.getLocation(keyword);
	} else {
		showTips('亲，要先填个地址，再搜索哦。','error',5);
	}
}

/*
 * get distance between two positions
 */
CCClubsMap.prototype.getDistance = function(position1, position2) {

	var EARTH_RADIUS = 6378137.0,
		PI = Math.PI;
	var lat1 = position1.lat(),
		lng1 = position1.lng();
	var lat2 = position2.lat(),
		lng2 = position2.lng();
	var f = getRad((lat1 + lat2) / 2);
	var g = getRad((lat1 - lat2) / 2);
	var l = getRad((lng1 - lng2) / 2);

	var sg = Math.sin(g);
	var sl = Math.sin(l);
	var sf = Math.sin(f);

	var s, c, w, r, d, h1, h2;
	var a = EARTH_RADIUS;
	var fl = 1 / 298.257;

	sg = sg * sg;
	sl = sl * sl;
	sf = sf * sf;

	s = sg * (1 - sl) + (1 - sf) * sl;
	c = (1 - sg) * (1 - sl) + sf * sl;

	w = Math.atan(Math.sqrt(s / c));
	r = Math.sqrt(s * c) / w;
	d = 2 * w * a;
	h1 = (3 * r - 1) / 2 / c;
	h2 = (3 * r + 1) / 2 / s;

	return Math.round(d * (1 + fl * (h1 * sf * (1 - sg) - h2 * (1 - sf) * sg)));

	function getRad(d) {
		return d * PI / 180.0;
	}
}

/*
 * calculate parkingLots in radius(1500m)
 */
CCClubsMap.prototype.parkingLotsCalculate = function(position1) {
	var fitParkingLots = new Array();
	var ulContainer = document.createElement("ul");
	var parent = this;
	if (parent.mapDB['parkingLots']['markers'].length > 0) {
		$.when(
			$.each(parent.mapDB['parkingLots']['markers'], function(i, data) {
				var position2 = new google.maps.LatLng(data.csoLatitude, data.csoLongitude);
				var poisDistance = parent.getDistance(position1, position2);
				if (poisDistance < parent.radius) {
					data.Distance = poisDistance;
					fitParkingLots.push(data);
				}
			})
		).done(function() {
			if (fitParkingLots.length > 0) {
				//alert('附近共有' + fitParkingLots.length + '个预设点可供选择');
				fitParkingLots = fitParkingLots.sort(function(a, b) {
					if(a.Distance < b.Distance) return -1;  
                    if(a.Distance > b.Distance) return 1;  
                    return 0;
				});
				$.each(fitParkingLots,
					function(index, value) {
					var liContainer = document.createElement("li");
					liContainer.className = "ccc-widget-viewinfo";
					var htmlData = "";
					//htmlData += "<li class=\"ccc-widget-viewinfo\">";
					htmlData += "<em class=\"before\"></em>";
					htmlData += "<em class=\"after\"></em>";
					htmlData += "<div class=\"ccc-viewinfo-cont\">";
					htmlData = htmlData + "<span class=\"name\">" + value.csoName + "</span>";
					htmlData = htmlData + "<span class=\"address\">" + value.csoAddress + "</span>";
					htmlData = htmlData + "<span class=\"notes\">" + value.csoSpace + "</span>";
					htmlData = htmlData + "<button class=\"reserve\" id=\"btn_" + value.csoId + "\"></button>";
					htmlData += "</div>";
					liContainer.innerHTML = htmlData;
					ulContainer.appendChild(liContainer);
					var btn = "#btn_" + value.csoId;
					$(document).on("click", btn, function() {
						parent.clickViewcar(value.csoId);
					});
					}
				);

				var viewcar = $('#viewcar'),
					widget_viewinfo = $('#ccc-widget-viewinfo'),
					viewinfo = $('#viewinfo');
				// set div visible
				viewcar.css("display", "none");
				viewinfo.css("display", "block");

				$('#viewinfo .cccScrollbar_container').html(ulContainer.outerHTML);
				$('#viewinfo').cccScrollbar('update');
				$('#viewinfo').cccScrollbar('scrollTo', 'left', {
					scrollInertia: 400
				});
			} else if (fitParkingLots.length == 0) {
				htmlData = '抱歉，附近暂无可供选择的网点';
				var viewcar = $('#viewcar'),
					widget_viewinfo = $('#ccc-widget-viewinfo'),
					viewinfo = $('#viewinfo');
				// set div visible
				viewcar.css("display", "none");
				viewinfo.css("display", "block");
				$('#viewinfo .cccScrollbar_container').html("<div id='ccc-tooltip'>" + htmlData + "</div>");
				$('#viewinfo').cccScrollbar('update');
				$('#viewinfo').cccScrollbar('scrollTo', 'left', {
					scrollInertia: 400
				});
			}
			showViewCard();
		});
	}
}

CCClubsMap.prototype.setBaseMarkersIcon = function() {
	this.setMarkersIcon(this.mapCluster.getMarkers(), {
		url: this.markerImg,
		size: this.markerSize,
		scaledSize: this.markerScaledSize
	});
}
/*
 *
 */
CCClubsMap.prototype.showViewcar = function(parkingLots, cccMarker) {
	this.setBaseMarkersIcon();
	// reset all marker icon
	// this.selectedMarker = cccMarker;
	cccMarker.setIcon({
		url: this.markerImg,
		size: this.markerSizeX1,
		scaledSize: this.markerScaledSizeX1
	});
	closeViewCard();
	setTimeout(function(){
		var viewcar = $('#viewcar'),
			widget_viewinfo = $('#ccc-widget-viewinfo'),
			viewinfo = $('#viewinfo');
		// set div visible
		viewcar.css("display", "block");
		viewinfo.css("display", "none");
		var htmlData = "<em class=\"before\"></em>";
		htmlData += "<em class=\"after\"></em>";
		htmlData += "<div class=\"ccc-viewinfo-cont\">";
		htmlData = htmlData + "<span class=\"name\">" + parkingLots['csoName'] + "</span>";
		htmlData = htmlData + "<span class=\"address\">" + parkingLots['csoAddress'] + "</span>";
		htmlData = htmlData + "<span class=\"notes\">" + parkingLots['csoSpace'] + "</span>";
		htmlData += "</div>";
		widget_viewinfo.html(htmlData);
		$.get('/parkinglots_getVehicles.html?cscOutlets=' + parkingLots['csoId'], function(data) {
			$('#ccc-widget-viewcar .cccScrollbar_container').html(data);
			$('#ccc-widget-viewcar').cccScrollbar('update');
			$('#ccc-widget-viewcar').cccScrollbar('scrollTo', 'left', {
				scrollInertia: 400
			});
		}).done(showViewCard);
	},300)
}

/*
 * viewCard list click event
 */
CCClubsMap.prototype.clickViewcar = function(a) {
	var parkingLots;
	if (this.mapDB['parkingLots']['markers'].length > 0) {
		for (i = 0; i < this.mapDB['parkingLots']['markers'].length; i++) {
			if (a === this.mapDB['parkingLots']['markers'][i].csoId) {
				parkingLots = this.mapDB['parkingLots']['markers'][i];
				break;
			}
		}
		if (parkingLots != null) {
			var cccMarker = new google.maps.Marker({
				title: parkingLots.csoName
			});
			this.setBaseMarkersIcon();
			var index = this.mapCluster.indexOfMarker(cccMarker);
			if (index > -1) {
				this.showViewcar(parkingLots, this.mapCluster.getMarkers()[index]);
			}
		}
	}
}
/*
 * batch set markers[] icon
 */
CCClubsMap.prototype.setMarkersIcon = function(markers, icon) {
	var mCount = markers.length;
	if (mCount > 0) {
		for (i = 0; i < markers.length; i++) {
			markers[i].setIcon(icon);
		}
	}
}
/*
 * open InfoWindow
 */
CCClubsMap.prototype.openInfoWindow = function(marker, info) {
	if (this.infoWindow == null) {
		this.infoWindow = new google.maps.InfoWindow({
			maxWidth: 250
		});
	}
	this.map.panTo(marker.getPosition());
	this.infoWindow.setContent([
		info,
	].join(''));
	this.infoWindow.open(this.map, marker);
}
/*
 * close InfoWindow
 */
CCClubsMap.prototype.closeInfoWindow = function() {
	if (this.infoWindow != null) {
		this.infoWindow.close();
	}
}
/*
 * get preset parkinglots info
 */
CCClubsMap.prototype.getPresetParkingLotsInfo = function(presetParkingLots) {
	var info = "";
	info += "<div class=\"ccc-viewinfo-cont\">";
	info += "<span class=\"name\">" + presetParkingLots.csoName + "</span>";
	info += "<span class=\"address\">" + presetParkingLots.csoAddress + "</span>";
	info += "</div>";
	return info;
}

/*
 * get gasStation info
 */
CCClubsMap.prototype.getGasStationInfo = function(gasStation) {
	var info = "";
	info += "<div class=\"ccc-viewinfo-cont\">";
	info += "<span class=\"name\">" + gasStation.Name + "</span>";
	info += "<span class=\"address\">" + gasStation.Address + "</span>";
	info += "</div>";
	return info;
}
// Show & Close View Card fn

function showViewCard() {
	var $vc = $('#ccc-widget-viewcard');
	var $ib = $('#ccc-widget-viewinfo', $vc);
	if ($vc.height() !== 150) {
		$vc.stop().animate({
			height: 150
		}, 400, 'easeOutCirc');
		$ib.stop().animate({
			marginRight: 0
		}, 500, 'easeOutCirc');
	} else {
		$vc.stop().animate({
			height: 0
		}, 300).delay(200).animate({
			height: 150
		}, 400, 'easeOutCirc');
		$ib.stop().delay(300).animate({
			marginRight: '80%'
		}, 0).delay(200).animate({
			marginRight: 0
		}, 500, 'easeOutCirc');
	}
}

function closeViewCard() {
	var $vc = $('#ccc-widget-viewcard');
	var $ib = $('#ccc-widget-viewinfo', $vc);
	$vc.stop().animate({
		height: 0
	}, 300);
	$ib.stop().delay(300).animate({
		marginRight: '80%'
	}, 0);
}

$('#ccc-widget-viewcard-close').click(function() {
	closeViewCard()
});
//TODO


$(document).ready(function() {

	/* CCClubs Maps */
	var cccMap = new CCClubsMap();
	cccMap.zoom = 12;
	cccMap.mapCont = document.getElementById('ccc-fullscreen-map');
	cccMap.center = new google.maps.LatLng(30.2623905, 120.1028902);
	cccMap.initialize();
	/////////////////////////////////////////////////////////////////////////////
	var input = document.getElementById('ccc-searchbox');
	var searchBox = new google.maps.places.SearchBox(input);
	
	google.maps.event.addListener(searchBox, 'places_changed', function() {
		var places = searchBox.getPlaces();
		if (places.length > 0) {
			cccMap.updateCurrentLocation(places[0].geometry.location, places[0].name , 14);
		} else {
			return false;
		}
	});



	/* CCClubs View Card Scroll */

	$('#ccc-widget-viewcar').cccScrollbar({
		horizontalScroll: true,
		scrollInertia: 500,
		mouseWheelPixels: 282,
		advanced: {
			autoExpandHorizontalScroll: true
		},
		callbacks: {
			onScroll: function() {
				snapScrollbar();
			}
		}
	});

	$('#viewinfo').cccScrollbar({
		horizontalScroll: true,
		scrollInertia: 500,
		mouseWheelPixels: 255,
		advanced: {
			autoExpandHorizontalScroll: true
		}
	});


	// Snap Scrollbar fn

	function snapScrollbar() {
		var snapTo = [];
		$('#ccc-widget-viewcar ul>li').each(function() {
			var $this = $(this);
			var thisX = $this.position().left;
			snapTo.push(thisX);
		});
		var posX = $('#ccc-widget-viewcar .cccScrollbar_container').position().left;
		var closestX = findClosest(Math.abs(posX), snapTo) - 10;
		$('#ccc-widget-viewcar').cccScrollbar('scrollTo', closestX, {
			scrollInertia: 250,
			callbacks: false
		});
	}

	function findClosest(num, arr) {
		var curr = arr[0];
		var diff = Math.abs(num - curr);
		for (var val = 0; val < arr.length; val++) {
			var newdiff = Math.abs(num - arr[val]);
			if (newdiff < diff) {
				diff = newdiff;
				curr = arr[val];
			}
		}
		return curr;
	}


	// Keyboard Nav fn
	$('#ccc-widget-viewcar').hover(function() {
		$(document).data({
			'keyboard-input': 'enabled'
		});
		$(this).addClass('keyboard-input');
	}, function() {
		$(document).data({
			'keyboard-input': 'disabled'
		});
		$(this).removeClass('keyboard-input');
	});

	$(document).keydown(function(e) {
		if ($(this).data('keyboard-input') === 'enabled') {
			var activeElem = $('.keyboard-input'),
				activeElemPos = Math.abs($('.keyboard-input .cccScrollbar_container').position().left),
				pixelsToScroll = 282;
			if (e.which === 37) { //scroll left
				e.preventDefault();
				if (pixelsToScroll > activeElemPos) {
					activeElem.cccScrollbar('scrollTo', 'left');
				} else {
					activeElem.cccScrollbar('scrollTo', (activeElemPos - pixelsToScroll), {
						scrollInertia: 500,
						scrollEasing: 'easeOutCirc'
					});
				}
			} else if (e.which === 39) { //scroll right
				e.preventDefault();
				activeElem.cccScrollbar('scrollTo', (activeElemPos + pixelsToScroll), {
					scrollInertia: 500,
					scrollEasing: 'easeOutCirc'
				});
			}
		}
	});

});