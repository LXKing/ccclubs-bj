package com.ccclubs.action.api.scripts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.api.model.Result;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.service.admin.ICsOutletsService;
import com.lazy3q.web.helper.$;

public class OutletsScript extends BaseScript{
	
	/**
	 * 获取可异地还车还入的网点
	 * @return
	 */
	public static Result getParkList(Long hostId, Long outletsId){
		Result rs = new Result();
		try{
			ICsOutletsService	csOutletsService = $.GetSpringBean("csOutletsService");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(CsOutlets.F.csoHost, hostId);
			params.put(CsOutlets.F.csoStatus, "1");
			params.put(CsOutlets.F.csoCanOrder, "1");
			// 可用车位数大于0
			params.put("definex", "(" + CsOutlets.C.csoId + " = " + outletsId + " OR (" + CsOutlets.C.csoPositionS + " - " + CsOutlets.C.csoCarS + " > 0 AND " + CsOutlets.C.csoCanB + " = 1))");
			List<CsOutlets> outlets = csOutletsService.getCsOutletsList(params, -1);
			rs.setData(outlets);
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