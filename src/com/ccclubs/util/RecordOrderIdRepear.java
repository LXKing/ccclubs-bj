package com.ccclubs.util;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

/**
 * 消费记录订单号分析
 * @author uiu
 *
 */
public class RecordOrderIdRepear {

	public static void main(String...s){
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		List<Map> list = dao.executeQuery("select csr_id,csr_remark from cs_record left JOIN cs_record_type on csr_record_type=csrt_id where csr_order is null and (csrt_type_name='人工充值' or csrt_type_name='人工扣款') ");
		for(Map map:list){
			Long id = (Long) map.get("csr_id");	
			String strRemark = (String) map.get("csr_remark");
			Long orderid = findOrderByRemark(strRemark);
			if(orderid!=null){
				System.out.println("订单号:"+orderid);
				dao.executeUpdate("update cs_record set csr_order=? where csr_id=?",orderid,id);
			}
		}
		System.out.println("完了...");
		try {
			Thread.sleep(10000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private static Long findOrderByRemark(String strRemark) {
		Pattern tPattern = Pattern.compile("100[\\d]{5}");
		java.util.regex.Matcher tMatcher = tPattern.matcher(strRemark);
		if (tMatcher.find()) {
			return Long.parseLong(tMatcher.group());
		}
		return null;
	}
	
	
}
