package com.ccclubs.action.weixin.scripts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lazy3q.web.helper.$;
import com.ccclubs.action.weixin.IWXScript;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsWxContent;

/**
 * 空气质量查询
 * 
 * @author qs小刚
 * 
 */
public class AQIWXScript implements IWXScript {
	// 最后获取时间，每10分钟获取一次
	static Date lastTime = null;
	// 获取的内容
	static String aqiJsonString = "";

	public String getContent(String openId, String content) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CsWxContent> getContents(String openId, String content) {
		List<CsWxContent> contentList = new ArrayList<CsWxContent>();
		// 每20分钟重新获取一次
		if (lastTime == null || ((new Date().getTime() - lastTime.getTime()) >= 20 * SYSTEM.MINUTE)) {
			try {
				// 通过 token=qUppHMacq7FvSErdboXx 获取城市空气质量数据
				aqiJsonString = $.http("http://www.pm25.in/api/querys/aqi_ranking.json?token=qUppHMacq7FvSErdboXx");
				lastTime = new Date();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if ($.empty(aqiJsonString)) {
			CsWxContent csWxContent = new CsWxContent();
			csWxContent.setCswcTitle("杭州空气质量报告");
			csWxContent.setCswcContnet("\n暂时还没获取到杭州空气质量报告\n");
			csWxContent.setCswcLink("http://www.ccclubs.com/pm25.html");
			contentList.add(csWxContent);
		} else {
			String aqiString = "{\"aqiccclubs\":" + aqiJsonString + "}";
			Map mapAQI = $.eval(aqiString);
			List<Map<String, Object>> list = (List<Map<String, Object>>) mapAQI.get("aqiccclubs");
			for (Map<String, Object> map : list) {
				if (map.get("area").equals("杭州")) {
					CsWxContent csWxContent = new CsWxContent();
					csWxContent.setCswcTitle("杭州空气质量报告");
					csWxContent.setCswcContnet(getDescription(map.get("quality").toString(), map.get("aqi").toString(), map.get("pm2_5").toString(), map.get("pm10").toString(), map.get("time_point")
							.toString()));
					csWxContent.setCswcLink("http://www.ccclubs.com/pm25.html");
					contentList.add(csWxContent);
				}
			}
		}
		return contentList;
	}

	public ReturnType getReturnType() {
		return ReturnType.NEWS;
	}

	/**
	 * 通过空气质量，获取描述
	 * 
	 * @param aqiDes
	 * @param aqi
	 * @param pm25
	 * @param pm10
	 * @return
	 */
	public String getDescription(String aqiDes, String aqi, String pm25, String pm10, String time) {
		String description = "\n空气质量指数：{aqi} ({aqiDes})\n\nPM2.5：{pm25}\nPM10 ：{pm10}\n\n{des}\n\n关注空气质量，关注车纷享～\n";
		description = description + "和我们一起改善杭州空气质量！\n\n车纷享百科：\nPM2.5 表示入肺颗粒\nPM10  表示可吸入颗粒\n更新时间：{time}，数据来源：pm25.in";
		try {
			if (aqiDes.equals("优")) {
				return description.replace("{pm25}", pm25).replace("{pm10}", pm10).replace("{aqi}", aqi).replace("{aqiDes}", aqiDes).replace("{des}", "杭州今天的空气质量非常好！大口呼吸新鲜空气吧～").replace("{time}",
						time.substring(11, 16));
			}
			if (aqiDes.equals("良")) {
				return description.replace("{pm25}", pm25).replace("{pm10}", pm10).replace("{aqi}", aqi).replace("{aqiDes}", aqiDes).replace("{des}", "杭州今天的空气质量还不错，如果想户外活动基本没问题的。").replace("{time}",
						time.substring(11, 16));
			}
			if (aqiDes.equals("轻度污染")) {
				return description.replace("{pm25}", pm25).replace("{pm10}", pm10).replace("{aqi}", aqi).replace("{aqiDes}", aqiDes).replace("{des}", "杭州今天的空气受到了轻度污染，老人和小孩要减少户外活动哦～").replace(
						"{time}", time.substring(11, 16));
			}
			if (aqiDes.equals("中度污染")) {
				return description.replace("{pm25}", pm25).replace("{pm10}", pm10).replace("{aqi}", aqi).replace("{aqiDes}", aqiDes).replace("{des}", "杭州今天的空气受到了中度污染，建议不要长时间呆在户外。").replace("{time}",
						time.substring(11, 16));
			}
			if (aqiDes.equals("重度污染")) {
				return description.replace("{pm25}", pm25).replace("{pm10}", pm10).replace("{aqi}", aqi).replace("{aqiDes}", aqiDes).replace("{des}", "杭州今天的空气受到了重度污染！建议没啥事还是少出门儿比较好！").replace(
						"{time}", time.substring(11, 16));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return description.replace("{pm25}", pm25).replace("{pm10}", pm10).replace("{aqi}", aqi).replace("{aqiDes}", aqiDes).replace("{des}", "杭州今天的空气质量还不错，如果想户外活动基本没问题的。").replace("{time}", time);
	}

}
