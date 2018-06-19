/*!
 * EVnetmap js v2.0
 * E-mali 513387369@qq.com
 * Copyright 2011-2013, QSxiaogang
 * Date: 2013/10/08
 */

var MapType = {
    Gaode: "gaode",
    Google: "google",
    Baidu: "baidu"
}

var EVnetMap = function(mapId, original_scale_level, mapType, lat, lng, markers) {
    this.container = mapId;
    this.current_vehindex = null;
    this.mapType = mapType || MapType.Gaode; //默认地图类型
    this.original_scale_level = original_scale_level; //设置默认缩放级别
    this.reloadIntervalTime = 30000; //设置取数据的间隔时间
    this.map = getMap(this.container, this.original_scale_level, mapType, lat, lng);
    this.isPlayOver = false;
    this.markers_obj = []; //maeker 对象
    this.marker = null;
    this.isFirstLoad = true;
    this.markers_info_obj = []; //maeker 信息 对象
    this.poly = null; //轨迹
    this.infowindow = null; //信息窗口
    this.timeClear = null;
    this.orderId = null;
    this.highSpeed = 90; //高速行驶车速
    this.idleSpeed = 5; //怠速行驶车速
    this.highSpeedMiles = 0; //高速行驶里程数
    this.idleSpeedTime = 0; //怠速行驶时长，单位为秒
    this.stopTime = 0; //停车时长，单位为秒
    this.refuelCount = 0; //加油次数
    this.refuel_obj = []; //加油时间，原始油量，加油后的油量

    this.highSpeed_start = 0; //上一点的经度
    this.highSpeed_end = 0; //上一点的纬度
    this.highSpeed_flag = false; //上一点的纬度

    this.idleSpeed_start = 0; //上一点的经度
    this.idleSpeed_end = 0; //上一点的纬度
    this.idleSpeed_flag = false; //上一点的纬度

    this.stop_start = 0; //上一点的经度
    this.stop_end = 0; //上一点的纬度
    this.stop_flag = false; //上一点的纬度

    this.refuel_start = 0; //上一点的经度
    this.refuel_end = 0; //上一点的纬度
    this.refuel_flag = false; //上一点的纬度
    //this.isOnload = 0; //0：准备 ，1：正在加载，2：加载完毕
};

//依据地图容器、缩放比例、地图的类型【Gaode、google、baidu等】、纬度及经度获取地图类
function getMap(container, original_scale_level, mapType, lat, lng) {
    var mapObj;
    if (mapType == MapType.Gaode) {
        mapObj = new AMap.Map(document.getElementById(container), {
            center: new AMap.LngLat(lng,lat),
            zoom: original_scale_level,
            resizeEnable: true,
            scrollWheel: true
        });
        //比例尺控件
        mapObj.plugin(["AMap.Scale"],function(){
            var scale = new AMap.Scale();
            mapObj.addControl(scale);  
        });
        mapObj.plugin(["AMap.ToolBar"],function(){
            //加载工具条
            var tool = new AMap.ToolBar({position:"RT"});
            mapObj.addControl(tool);   
        });
    }
    if (mapType == MapType.Google) {
        mapObj = new google.maps.Map(document.getElementById(container), {
            zoom: original_scale_level,
            center: new google.maps.LatLng(lat, lng),
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            mapTypeControl: true,
            navigationControl: true,
            scrollwheel: true,
            scaleControl: true
        });
    }

    return mapObj;
}

//矫正GPS信息点，并显示车辆轨迹信息，并计算一些统计信息
EVnetMap.prototype.displayPloy = function(markers) {
    if (markers.length > 0) {
        this.playOrderTrack();
        for (var i = 0; i <= markers.length - 1; i++) {
            //2：时间，3：燃油量，4：车速，5：电压，6：油里程数
            this.appendMarkerToTrack(markers[i][0], markers[i][1], markers[i][2]);
            //依据车速，和电量，计算高速行驶里程数
            this.computHighSpeed(markers[i][4], markers[i][5], markers[i][6]);
            //依据车速，和电量，时刻，计算怠速时长
            this.computIdleSpeedTime(markers[i][4], markers[i][5], markers[i][2]);
            //依据车速，电量，时刻，计算停车时长
            this.computStopTime(markers[i][4], markers[i][5], markers[i][2]);
        };
    }
}

//矫正GPS信息点，并显示车辆轨迹信息，并计算一些统计信息
EVnetMap.prototype.analysis = function(markers,highSpeed,idleSpeed) {
    this.highSpeed = highSpeed || 90;
    this.idleSpeed = idleSpeed || 5;
    this.highSpeedMiles = 0; //高速行驶里程数
    this.idleSpeedTime = 0; //怠速行驶时长，单位为秒
    this.stopTime = 0; //停车时长，单位为秒

    this.highSpeed_start = 0; //上一点的经度
    this.highSpeed_end = 0; //上一点的纬度
    this.highSpeed_flag = false; //上一点的纬度

    this.idleSpeed_start = 0; //上一点的经度
    this.idleSpeed_end = 0; //上一点的纬度
    this.idleSpeed_flag = false; //上一点的纬度

    this.stop_start = 0; //上一点的经度
    this.stop_end = 0; //上一点的纬度
    this.stop_flag = false; //上一点的纬度
    
    if (markers.length > 0) {
        for (var i = 0; i <= markers.length - 1; i++) {
            //2：时间，3：燃油量，4：车速，5：电压，6：油里程数
            //依据车速，和电量，计算高速行驶里程数
            this.computHighSpeed(markers[i][4], markers[i][5], markers[i][6]);
            //依据车速，和电量，时刻，计算怠速时长
            this.computIdleSpeedTime(markers[i][4], markers[i][5], markers[i][2]);
            //依据车速，电量，时刻，计算停车时长
            this.computStopTime(markers[i][4], markers[i][5], markers[i][2]);
        };
    }
}

//显示历史轨迹
EVnetMap.prototype.playOrderTrack = function() {
    var polyOptions = {
        strokeColor: "#00FF00",
        strokeOpacity: 0.8,
        strokeWeight: 5
    };
    
    if (this.mapType == MapType.Gaode) {
        if (this.poly == null)
            this.poly = new AMap.Polyline();
    } else {
        if (this.poly == null)
            this.poly = new google.maps.Polyline();
    }
    this.poly.setOptions(polyOptions);
    this.poly.setMap(this.map);
};

//往轨迹数组里添加marker
EVnetMap.prototype.appendMarkerToTrack = function(weidu, jingdu, info) {
    var posn;
    var marker;

	var wgs84togcj02 = coordtransform.wgs84togcj02(jingdu, weidu);

    if (this.mapType == MapType.Gaode) {
        posn = new AMap.LngLat(wgs84togcj02[0],wgs84togcj02[1]);
        marker = new AMap.Marker({
            position: posn
        });
    } else {
        posn = new google.maps.LatLng(wgs84togcj02[1], wgs84togcj02[0]);
        marker = new google.maps.Marker({
            position: posn
        });
    }
    this.markers_obj.push(marker);
    this.markers_info_obj.push(info);
    if (this.isInChina(posn, this.mapType)) {
        //可根据车速确定线的着色
        var path = this.poly.getPath();
        path.push(posn);
        if (this.poly != null && this.mapType == this.Gaode)
            this.poly.setPath(path);
    }
};

//依据车速，和电量，计算高速行驶里程数
EVnetMap.prototype.computHighSpeed = function(speed, power, mileage) {
    if (this.highSpeed_flag) {
        if (parseFloat(power) >= 130 && parseFloat(speed) >= this.highSpeed) {
            this.highSpeed_end = mileage;
        } else {
            if(this.highSpeed_end - this.highSpeed_start > 0)
                this.highSpeedMiles = this.highSpeedMiles + this.highSpeed_end - this.highSpeed_start;
            this.highSpeed_flag = false;
            this.highSpeed_start = 0;
            this.highSpeed_end = 0;
        }
    } else {
        //开始计数
        if (parseFloat(power) >= 130 && parseFloat(speed) >= this.highSpeed) {
            this.highSpeed_start = mileage;
            this.highSpeed_end = mileage;
            this.highSpeed_flag = true;
        } else {
            return;
        }
    }
};

//依据车速，和电量，时刻，计算怠速时长
EVnetMap.prototype.computIdleSpeedTime = function(speed, power, addTime) {
    if (this.idleSpeed_flag) {
        if (parseFloat(power) >= 130 && parseFloat(speed) <= this.idleSpeed) {
            this.idleSpeed_end = addTime;
        } else {
            this.idleSpeedTime = this.idleSpeedTime + this.idleSpeed_end - this.idleSpeed_start;
            this.idleSpeed_flag = false;
            this.idleSpeed_start = 0;
            this.idleSpeed_end = 0;
        }
    } else {
        //开始计数
        if (parseFloat(power) >= 130 && parseFloat(speed) <= this.idleSpeed) {
            this.idleSpeed_start = addTime;
            this.idleSpeed_end = addTime;
            this.idleSpeed_flag = true;
        } else {
            return;
        }
    }
};

//依据车速，电量，时刻，计算停车时长
EVnetMap.prototype.computStopTime = function(speed, power, addTime) {
    if (this.stop_flag) {
        if (parseFloat(power) < 130) {
            this.stop_end = addTime;
        } else {
            this.stopTime = this.stopTime + this.stop_end - this.stop_start;
            this.stop_flag = false;
            this.stop_end = 0;
            this.stop_start = 0;
        }
    } else {
        //开始计数
        if (parseFloat(power) < 130) {
            this.stop_start = addTime;
            this.stop_end = addTime;
            this.stop_flag = true;
        } else {
            return;
        }
    }
};

//依据油量，和电量，计算加油时间，和加油量
EVnetMap.prototype.computRefuel = function(oil, power, addTime) {
    if (this.stop_flag) {
        if (parseFloat(power) < 130) {
            this.stop_end = addTime;
        } else {
            this.stopTime = this.stopTime + this.stop_end - this.stop_start;
            this.stop_flag = false;
            this.stop_end = 0;
            this.stop_start = 0;
        }
    } else {
        //开始计数
        if (parseFloat(power) < 130) {
            this.stop_start = addTime;
            this.stop_end = addTime;
            this.stop_flag = true;
        } else {
            return;
        }
    }
};

//校正精度
EVnetMap.prototype.regulateLgtd = function(longitude) {
    var regulateLongitude = 0;
    try {
        regulateLongitude = (parseFloat(longitude) + 0.0044).toFixed(6);
    } catch (e) {}
    return regulateLongitude.toString();
};
//校正纬度
EVnetMap.prototype.regulateLttd = function(latitude) {
    var regulateLatitude = 0;
    try {
        regulateLatitude = (parseFloat(latitude) - 0.0023).toFixed(6);
    } catch (e) {}
    return regulateLatitude.toString();
};
//是否在中国境内
EVnetMap.prototype.isInChina = function(posn, mapType) {
    var posn1;
    var posn2;
    var bounds;
    if (mapType == MapType.Gaode) {
        posn1 = new AMap.LngLat(75,20);
        posn2 = new AMap.LngLat(137,50);
        bounds = new AMap.Bounds(posn1, posn2);
    } else {
        posn1 = new google.maps.LatLng(28.25, 115.60);
        posn2 = new google.maps.LatLng(33.14, 122.65);
        bounds = new google.maps.LatLngBounds(posn1, posn2);
    }

    if (posn != null && posn != "") {
        if (bounds.contains(posn)) {
            return true;
        } else {
            return false;
        }
    } else {
        return false;
    }
};

/*------
interval ：D表示查询精确到天数的之差
interval ：H表示查询精确到小时之差
interval ：M表示查询精确到分钟之差
interval ：S表示查询精确到秒之差
interval ：T表示查询精确到毫秒之差
使用方法:
alert(dateDiff('D', '2007-4-1', '2007/04/19'))；
*/
function dateDiff(interval, date1, date2) {
    var objInterval = {
        'D': 1000 * 60 * 60 * 24,
        'H': 1000 * 60 * 60,
        'M': 1000 * 60,
        'S': 1000,
        'T': 1
    };
    interval = interval.toUpperCase();
    var dt1 = new Date(Date.parse(date1.replace(/-/g, '/')));
    var dt2 = new Date(Date.parse(date2.replace(/-/g, '/')));
    try {
        return Math.round((dt2.getTime() - dt1.getTime()) / eval('objInterval.' + interval));
    } catch (e) {
        return e.message;
    }
}

function convertTimeDur(timedur) {
    var min = Math.floor(timedur / 1000 / 60);
    if (min < 60)
        return "" + min + "分钟";
    if (min < 60 * 24) {
        if (min % 60 == 0) {
            return "" + Math.floor(min / 60) + "小时";
        } else {
            return "" + Math.floor(min / 60) + "小时" + min % 60 + "分钟";
        }
    }
    // 整天
    if (min % (60 * 24) == 0) {
        return "" + Math.floor(min / (60 * 24)) + "天";
    }
    // 有小时，没分钟
    if (min / 60 > 0 && min % 60 == 0) {
        return "" + Math.floor(min / (60 * 24)) + "天" + Math.floor(min % (60 * 24) / 60) + "小时";
    }
    // 有小时，有分钟
    if (min / 60 > 0 && min % 60 != 0) {
        return "" + Math.floor(min / (60 * 24)) + "天" + Math.floor(min % (60 * 24) / 60) + "小时" + min % 60 + "分钟";
    }
    // 没小时，有分钟
    return "" + Math.floor(min / (60 * 24)) + "天" + min % 60 + "分钟";
}