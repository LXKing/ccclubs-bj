package com.ccclubs.action.api.scripts;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.action.app.official.util.ApiBase;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.SystemHelper.Prices;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsState;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

/**
 * 车辆脚本
 * 
 * @author 
 *
 */
public class CarScript extends BaseScript{
	
	/**
	 * 加载车辆状态数据
	 */
	public static void loadCarFeatures(Page<CsCar> page){
		List<CsCar> result = page.getResult();
		if(result == null)return;
		
		Map<String, Prices> pricesMap = new HashMap<String, Prices>();
		
		List<CsCar> deletedList = new ArrayList<CsCar>();
		for(int i = 0; i<result.size(); i++){
			CsCar car = result.get(i);
			String key = car.getCscHost()+"-"+car.getCscModel()+"-"+car.getCscOutlets();
			Prices price = pricesMap.get(key);
			if(price == null){
				price = SystemHelper.getPrices(car.getCscHost(), car.getCscModel(), car.getCscOutlets());
				pricesMap.put(key, price);
			}
			if(price!=null){
				Object dayPrice = price.get("租金.工作日一天|租金.每天");
				Object HourPrice = price.get("租金.工作日白天小时|租金.白天小时|租金.每小时");
				Object oilMileageFee = price.get("油里程费.每公里");
				Object elecMileageFee = price.get("电里程费.每公里");
				Object feePerMin = price.get("租金.每分钟|租金.分钟租金");
				//
				car.setValues("dayPrice", dayPrice == null ? 0 : Double.valueOf(dayPrice.toString()));
				car.setValues("hourPrice", HourPrice == null ? 0 : Double.valueOf(HourPrice.toString()));
				car.setValues("elecMileageFee", elecMileageFee);
				car.setValues("oilMileageFee", oilMileageFee);
				car.setValues("elecMileageFee", elecMileageFee);
				car.setValues("feePerMin", feePerMin);
			}
			
			CsState cs = CsState.getCsState($.add(CsState.F.cssNumber, car.getCscNumber()));
			if(cs!=null){
				car.setValues("endurance", cs.getCssEndurance());
				car.setValues("speed", cs.getCssSpeed());
				car.setValues("power", cs.getCssPower());
				car.setValues("ischarging", cs.getCssCharging());
				car.setValues("evBattery", cs.getCssEvBattery());
				car.setValues("oil", cs.getCssOil());
				
				//超出十五分钟未有实时状态更新的，则视为下线车辆
				if(new Date().getTime() - cs.getCssAddTime().getTime() > 1000*60*60){
					deletedList.add(car);
				}
				
				//car.setValues("longitude", cs.getCssLongitude());
				//car.setValues("latitude", cs.getCssLatitude());
				
				car.setCscLongitude(Double.valueOf($.or(cs.getCssLongitude(), "0")));
				car.setCscLatitude(Double.valueOf($.or(cs.getCssLatitude(), "0")));
			}
		}
		if(ApiBase.useState){
			result.removeAll(deletedList);
		}
	}
	
}
