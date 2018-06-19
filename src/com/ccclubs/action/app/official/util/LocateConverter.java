package com.ccclubs.action.app.official.util;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.helper.$;

/**
 * 
 * 坐标转换器
 * 
 * @author joel
 * 
 * @see  <a href="http://www.gpsspg.com/api/convert/latlng/">http://www.gpsspg.com/api/convert/latlng/</a>
 *
 */
public class LocateConverter {
	
	final static String API = "http://apis.map.qq.com/ws/coord/v1/translate";
	
	final static String key = "0e2941f1708f3eb3d19c029c3802521b";
	
	/**
	 * 
	 * @param locations 地理位置
	 * @param type  类型
					    1 GPS坐标
						2 sogou经纬度
						3 baidu经纬度
						4 mapbar经纬度
						5 [默认]腾讯、google、高德坐标
						6 sogou墨卡托
	 * @param key  开发key
	 * @return
	 */
	public static String execute(String locations, int type, String key){
		if(key == null)key = LocateConverter.key;
		String respstr = null;
		try {
			respstr = $.http(API+"?locations="+locations+"&type="+type+"&key="+key);
			Map<String, Object> resp = ApiBase.fromJson(respstr);
			if(resp!=null){
				Object code = resp.get("status");
				
				if(Double.valueOf(code.toString()).intValue()==0){
					List result = (List)resp.get("locations");
					if(result!=null && result.size()>0){
						Map map = (Map)result.get(0);
						String lat = Double.valueOf(map.get("lat").toString()).toString();
						String lng = Double.valueOf(map.get("lng").toString()).toString();
						return lat+","+lng;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locations;
	}
	
	 /** 
     * 计算地球上任意两点(经纬度)距离 
     *  
     * @param long1 
     *            第一点经度 
     * @param lat1 
     *            第一点纬度 
     * @param long2 
     *            第二点经度 
     * @param lat2 
     *            第二点纬度 
     * @return 返回距离 单位：米 
     */  
    public static double getDistance(double long1, double lat1, double long2, double lat2) {  
        double a, b, R;  
        R = 6378137; // 地球半径  
        lat1 = lat1 * Math.PI / 180.0;  
        lat2 = lat2 * Math.PI / 180.0;  
        a = lat1 - lat2;  
        b = (long1 - long2) * Math.PI / 180.0;  
        double d;  
        double sa2, sb2;  
        sa2 = Math.sin(a / 2.0);  
        sb2 = Math.sin(b / 2.0);  
        d = 2  * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));  
        return d;  
    }
	
	public static void main(String[] args) {
		String str = execute("40.042422,116.319822", 5, key);
		System.out.println(str);
	}
}
