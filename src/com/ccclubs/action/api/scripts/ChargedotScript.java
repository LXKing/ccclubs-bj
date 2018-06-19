package com.ccclubs.action.api.scripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.ccclubs.action.api.model.Result;
import com.ccclubs.model.CsPowerPile;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsPowerPileService;
import com.lazy3q.web.helper.$;

public class ChargedotScript extends BaseScript{
	
	/**
	 * 所有电桩数据
	 * @return
	 */
	public static Result getAllList(){
		Result rs = new Result();
		try{
			
			ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");
			List<CsPowerPile> dataList = csPowerPileService.getCsPowerPileList($.add(CsPowerPile.C.csppHost, SrvHost.BeiJing), -1);
			
			if(!CollectionUtils.isEmpty(dataList)){
				List<Map> resdatalist = new ArrayList<Map>();
				for (CsPowerPile pwc : dataList) {
					Map datamap = new HashMap();
					datamap.put("id", pwc.getCsppId());
					datamap.put("host", pwc.getCsppHost());
					datamap.put("device_number", pwc.getCsppNo());
					datamap.put("outlets", pwc.getCsppOutlets());
					String location = pwc.getCsppLocation();
					if(location!=null){
						String loc_str[] = location.toString().split(",");
						datamap.put("loc_longitude", loc_str[1]);
						datamap.put("loc_latitude", loc_str[0]);
					}
					datamap.put("status", pwc.getCsppStatus());
					resdatalist.add(datamap);
				}
				rs.setData(resdatalist);
			}
			
			return rs.setResult(true);
		
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		} catch (Error e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		}
	}
	
}