var MapType = {
	QQ : "qq",
	Google : "google",
	Baidu : "baidu"
}

/**
 * 
 * @param mapId
 *            //实例化一个地图对象需要在网页中创建一个空div元素，参数中需要传入div元素对象或div元素的id。
 * @param original_scale_level
 *            设置地图缩放级别
 * @param mapType
 * @param lat
 * @param lng
 * @param markers
 * @returns
 */
var EVnetMap = function(mapId, original_scale_level, mapType, lat, lng,
		mClusterer_flag) {
	this.container = mapId;
	this.current_vehindex = null;
	this.mapType = mapType || MapType.QQ; // 默认地图类型
	this.original_scale_level = original_scale_level; // 设置默认缩放级别
	this.reloadIntervalTime = 30000; // 设置取数据的间隔时间
	this.map = getMap(this.container, this.original_scale_level, mapType, lat,
			lng);
	this.isPlayOver = false;
	this.markers_obj = []; // maeker 对象
	this.marker = null;
	this.isFirstLoad = true;
	this.markers_info_obj = []; // maeker 信息 对象
	this.poly = null; // 轨迹
	this.infowindow = null; // 信息窗口
	this.timeClear = null;
	this.orderId = null;
	this.highSpeed = 90; // 高速行驶车速
	this.idleSpeed = 5; // 怠速行驶车速
	this.highSpeedMiles = 0; // 高速行驶里程数
	this.idleSpeedTime = 0; // 怠速行驶时长，单位为秒
	this.stopTime = 0; // 停车时长，单位为秒
	this.refuelCount = 0; // 加油次数
	this.refuel_obj = []; // 加油时间，原始油量，加油后的油量

	this.highSpeed_start = 0; // 上一点的经度
	this.highSpeed_end = 0; // 上一点的纬度
	this.highSpeed_flag = false; // 上一点的纬度

	this.idleSpeed_start = 0; // 上一点的经度
	this.idleSpeed_end = 0; // 上一点的纬度
	this.idleSpeed_flag = false; // 上一点的纬度

	this.stop_start = 0; // 上一点的经度
	this.stop_end = 0; // 上一点的纬度
	this.stop_flag = false; // 上一点的纬度

	this.refuel_start = 0; // 上一点的经度
	this.refuel_end = 0; // 上一点的纬度
	this.refuel_flag = false; // 上一点的纬度
	// this.isOnload = 0; //0：准备 ，1：正在加载，2：加载完毕
	this.mClusterer_flag = mClusterer_flag || true;// 是否开启点聚合
};

// 依据地图容器、缩放比例、地图的类型【soso、google、baidu等】、纬度和经度的地理坐标点获取地图类
function getMap(container, original_scale_level, mapType, lat, lng) {
	var mapObj;
	if (mapType == MapType.QQ) {
		mapObj = new qq.maps.Map(document.getElementById(container), {
			center : new qq.maps.LatLng(lat, lng), // 地图的中心地理坐标。
			zoom : original_scale_level,
			// 启用平移控件
			panControl : true,
			// 设置平移控件的位置
			panControlOptions : {
				// 设置平移控件的位置为相对右方中间位置对齐.
				position : qq.maps.ControlPosition.TOP_RIGHT

			},
			// 启用缩放控件
			zoomControl : true,
			// 设置缩放控件的位置和样式
			zoomControlOptions : {
				// 设置缩放控件的位置为相对左方中间位置对齐.
				position : qq.maps.ControlPosition.TOP_RIGHT,
				// 设置缩放控件样式为仅包含放大缩小两个按钮
				style : qq.maps.ZoomControlStyle.DEFAULT
			}
		});
	}
	return mapObj;
}

// 设置图片
var anchor = new qq.maps.Point(6, 6), size = new qq.maps.Size(24, 24), origin = new qq.maps.Point(
		0, 0), icon = new qq.maps.MarkerImage(
		'http://lbs.qq.com/javascript_v2/img/center.gif', size, origin, anchor);

// 创建标记
EVnetMap.prototype.createMarker = function(qMap, lat, lng, areaId, eval) {
	var position = new qq.maps.LatLng(lat, lng);
	marker = new qq.maps.Marker({
		position : position,
		map : qMap.map,
		icon : icon,
		title : "当前网点数：" + eval
	});

}
/**
 * ( 创建自定义覆盖物
 * 
 * @param qMap
 * @param lat
 * @param lng
 * @param areaId
 * @param eval
 */
EVnetMap.prototype.createOverlay = function(qMap, lat, lng, areaId, eval) {
	function CustomOverlay(position, index) {
		this.index = index;
		this.position = position;
	}
	CustomOverlay.prototype = new qq.maps.Overlay();
	// 定义construct,实现这个接口来初始化自定义的Dom元素
	CustomOverlay.prototype.construct = function() {
		var div = this.div = document.createElement("div");
		var divStyle = this.div.style;
		divStyle.position = "absolute";
		divStyle.backgroundColor = "#FFFFFF";
		divStyle.border = "2px dashed blue";
		divStyle.textAlign = "center";
		divStyle.cursor = "pointer";
		divStyle.opacity = 0.5;
		this.div.innerHTML = eval;
		// 将dom添加到覆盖物层
		var panes = this.getPanes();
		// 设置panes的层级，overlayMouseTarget可接收点击事件
		panes.overlayMouseTarget.appendChild(div);

		var self = this;
		this.div.onclick = function() {
			qMap.map.zoomTo(qMap.map.getZoom() + 1);
			overlay.setMap(null);
			$.ajax({
				url : "",
				data : {
					"areaId" : areaId
				},
				success : function() {

				}
			});

		}
	}
	// 实现draw接口来绘制和更新自定义的dom元素
	CustomOverlay.prototype.draw = function() {
		var overlayProjection = this.getProjection();
		// 返回覆盖物容器的相对像素坐标
		var pixel = overlayProjection.fromLatLngToDivPixel(this.position);
		var divStyle = this.div.style;
		divStyle.left = pixel.x - 12 + "px";
		divStyle.top = pixel.y - 12 + "px";

		if (qMap.map.getZoom() > 12) {
			divStyle.width = "100px";
			divStyle.height = "100px";
			divStyle.borderRadius = "50px";
			divStyle.lineHeight = "50px";
		}
		if (qMap.map.getZoom() < 13) {
			divStyle.width = "60px";
			divStyle.height = "60px";
			divStyle.borderRadius = "30px";
			divStyle.lineHeight = "30px";
		}
	}
	// 实现destroy接口来删除自定义的Dom元素，此方法会在setMap(null)后被调用
	CustomOverlay.prototype.destroy = function() {
		this.div.onclick = null;
		this.div.parentNode.removeChild(this.div);
		this.div = null
	}
	var latlng = new qq.maps.LatLng(lat, lng);
	var overlay = new CustomOverlay(latlng, 0);
	overlay.setMap(qMap.map);

	qq.maps.event.addListener(qMap.map, 'zoom_changed', function() {
		console.log(overlay.getProjection());
		if (qMap.map.getZoom() < 9) {
			overlay.setMap(null);
		} else {
			overlay.setMap(qMap.map);
		}
	});

}
