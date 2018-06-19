package com.ccclubs.action.thread;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccclubs.config.SYSTEM;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

/**
 * 运营统计数据分析
 * @author uiu
 *
 */
public class OperateThread extends Thread{

	
	@Override
	public void run() {
		while(true){			
			try{
				scanNewCharge();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			try {
				Thread.sleep(SYSTEM.HOUR);//一个小时处理一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String...strings){
		//new OperateThread().start();
	}	
	
	/**
	 * 检查新增会员充值
	 * 2014-01-20
	 */
	public static void scanNewCharge(){
		/*
		String sDate = SYSTEM.getArgument("新增会员充值最后分析时间", "NEW_RECHARGE_TIME","2014-01-01");
		String sToday = $.date(new Date(), "yyyy-MM-dd");
		
		if($.equals(sToday, sDate))
			return;
				
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		List<Map> recharges = dao.executeQuery("select csr_member,csr_add_time from cs_record where csr_money_type=0 and csr_type like '%充值%' and csr_add_time>'"+sDate+" 00:00:00' order by csr_add_time asc");
		for(Map recharge:recharges){
			Long member = (Long) recharge.get("csr_member");
			Date date = (Date) recharge.get("csr_add_time");
			dao.executeUpdate("update cs_member set csm_first_recharge=? where csm_first_recharge is null and csm_id=?",date,member);	
		}		
		dao.executeUpdate("update cs_argument set csa_value=? where csa_flag=?", sToday,"NEW_RECHARGE_TIME");
		*/
	}
	
	
	
	
}
