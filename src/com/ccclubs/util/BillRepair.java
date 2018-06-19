package com.ccclubs.util;

import java.util.List;
import java.util.Map;

import com.ccclubs.model.CsRecord;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class BillRepair {
	
	
	public void main(String...strings){
		
		
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		
		
		List<Map> list = dao.executeQuery("select csr_object from bill_record GROUP BY csr_object");
		for(Map map:list){
			String csr_object = (String) map.get("csr_object");
			long id = Long.parseLong(csr_object.split("@")[1]);
			
			
			
			
			
			
		}
		
		
		
		
		
	}
	

}
