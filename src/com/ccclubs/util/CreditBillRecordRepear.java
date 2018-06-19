package com.ccclubs.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

/**
 * 信用卡帐单消费记录修复
 * @author uiu
 *
 */
public class CreditBillRecordRepear {

	
	public static void main(String...s){
		
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		
		List<Map> list = dao.executeQuery("select * from cs_credit_bill where cscb_status=1");
		for(Map map:list){
			Long order = (Long) map.get("cscb_order");
			Long member = (Long) map.get("cscb_member");
			Object time = map.get("cscb_update_time");
			Double cscb_value = (Double) map.get("cscb_value");
			
			List<Map> records = dao.executeQuery("select * from cs_record where csr_record_subject=35 and csr_object='CsCreditBill@'", order);
			if(records.isEmpty()){//没有消费记录
				//查询最近一条消费记录的余额
				List<Map> lasts = dao.executeQuery("select * from cs_record where csr_member=? and csr_add_time<? and csr_record_type in (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,32,34,35,38,39,40,41,43,45,49) order by csr_add_time desc limit 1", member ,time);
				Double lastRemain = 0d;
				if(!lasts.isEmpty())
					lastRemain = (Double) lasts.get(0).get("csr_remain");
				
				String insertSql = "insert into cs_record(csr_host,csr_member,csr_group,csr_record_type,csr_amount,csr_remain,csr_remark,csr_add_time,csr_order,csr_relate,csr_editor) values(?,?,?,?,?,?,?,?,?,?,?)";
				//插入一条充值记录
				dao.execute(insertSql,map.get("cscb_host"),member,null,49,cscb_value,cscb_value+lastRemain,"",time,order,null,0l);
				//插入一条消费记录
				dao.execute(insertSql,map.get("cscb_host"),member,null,32,-cscb_value,lastRemain,"",time,order,null,0l);
				
			}else{
				for(Map record:records){
					Long id = (Long) record.get("csr_id");
					Double amount = (Double) record.get("csr_amount");
					if(amount>0){
						dao.executeUpdate("update cs_record set csr_record_type=49 where csr_id=?", id);
					}else{
						dao.executeUpdate("update cs_record set csr_record_type=32 where csr_id=?", id);
					}
				}
			}			
		}
		
		System.out.println("完了...");
		
		try {
			Thread.sleep(10000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
