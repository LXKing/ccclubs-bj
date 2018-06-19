package com.ccclubs.util;

import java.util.List;
import java.util.Map;

import com.lazy3q.lang.api;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class OpenIdsMove {
	
	static Boolean running = false;
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static @api void execute() throws Exception {
		if(running==true)return;
		running = true;
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		List<Map> list = dao.executeQuery("select csm_id,csm_open_ids from cs_member where length(csm_open_ids)>0");
		for(Map map:list){
			String csm_open_ids = map.get("csm_open_ids").toString();
			Long id = (Long) map.get("csm_id");
			String[] array = csm_open_ids.split(";");
			String weixin = "";
			String alipay = "";
			for(String flag:array){
				if(flag.startsWith("wx:"))
					weixin = flag.replace("wx:", "");
				if(flag.startsWith("alipay:"))
					alipay = flag.replace("alipay:", "");
			}
			dao.executeUpdate("update cs_member set csm_weixin_flag=?,csm_alipay_flag=? where csm_id=?", weixin,alipay,id);			
		}
		
	}
	

}
