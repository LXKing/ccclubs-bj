package com.ccclubs.action.admin;

import java.util.HashMap;
import java.util.Map;

import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCashRecord;
import com.ccclubs.model.CsOperRecord;
import com.ccclubs.model.SrvHost;
import com.lazy3q.web.helper.$;

public class AdminHelper {
	
	public static Map getOperCashInfo(Long host){
		Map map = new HashMap();
		SrvHost srvHost = null;
		map.put("host", srvHost = ((host==0 || host==null)?(SystemHelper.getAdminHost()):(SrvHost.get(host))));
		//冻结额
		Double freezed = CsCashRecord.where().cscrHost(srvHost.getShId()).add("definex", "(cscr_status=1 or cscr_status=2)").eval("sum(cscr_ammount)");
		//已提取
		Double extracted = CsCashRecord.where().cscrHost(srvHost.getShId()).cscrStatus((short)3).eval("sum(cscr_ammount)");
		//总收入
		Double comein = CsOperRecord.where().csorHost(srvHost.getShId()).eval("sum(csor_in)");
		//总支出
		Double comeout = CsOperRecord.where().csorHost(srvHost.getShId()).eval("sum(csor_out)");
		//可提取额
		Double withdraw = $.or(comein,0d)-$.or(comeout,0d)-$.or(extracted,0d)-$.or(freezed,0d);
		map.put("comein", comein);
		map.put("comeout", comeout);
		map.put("extracted", extracted);
		map.put("freezed", freezed);
		map.put("account", comein-comeout);
		map.put("withdraw", withdraw);
		return map;
	}

}
