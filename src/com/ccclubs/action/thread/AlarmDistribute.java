package com.ccclubs.action.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.ccclubs.config.SYSTEM;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class AlarmDistribute extends Thread {

	static Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
	static Map<String,Boolean> cars = new HashMap();
	static Map<String,Long> carHosts = new HashMap();
	
	static Map<String,Boolean> moved = new HashMap();
	
	static{
		init();
	}
	
	public static void init(){	
		cars.clear();
		List<Map> list = dao.executeQuery("select csc_number,csc_host from cs_car");
		for(Map map:list){
			createTable(map.get("csc_number").toString());
			carHosts.put(map.get("csc_number").toString().toLowerCase(), (Long)map.get("csc_host"));
		}
	}
	public static void createTable(String number){
		Lazy3qDaoSupport historyDao = getHistoryDao("cs_alarm_"+number.toLowerCase());
		if(historyDao!=null)
			historyDao.execute("CREATE table if not exists cs_alarm_"+number.toLowerCase()+" LIKE cs_alarm");
		cars.put(number.toLowerCase(),true);
	}
	
	private static Lazy3qDaoSupport getHistoryDao(String tableName) {
		return $.getDao("ccclubs_system_history_ex");
	}
	
	
	@Override
	public void run() {
		while(true){
			int count = 0;
			try{
				count = distribute();
			}catch(Exception ex){
				ex.printStackTrace();				
			}
			try {
				Thread.sleep(count==0?5000l:0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	static String strKeys = "csa_id,csa_host,csa_number,csa_add_time,csa_info,csa_order";
	static String keys[]=strKeys.split(",");
	static String sqlTemplate = "INSERT INTO cs_alarm_{number}("+strKeys+") VALUES (?,?,?,?,?,?)"; 

	/**
	 * 分发数据
	 * @return 
	 */
	private int distribute() {
		List<Map> list = dao.executeQuery("select * from cs_alarm order by csa_id asc limit 100");
		int moving = 0;
		for(Map map:list){
			Object[] params=new Object[keys.length];
			for(int i=0;i<keys.length;i++){
				String key=keys[i];
				params[i] = map.get(key);
			}
			String sNumber  = (String) map.get("csa_number");
			Lazy3qDaoSupport historyDao = getHistoryDao("cs_alarm_"+sNumber.toLowerCase());
			if(historyDao==null){//正在迁移的放弃插入
				moving++;
				continue;
			}
			sNumber = sNumber.toLowerCase();
			params[1]=carHosts.get(sNumber);//设置host值 
			if(cars.get(sNumber)==null || !cars.get(sNumber)){
				if(dao.executeQuery("select * from cs_car where csc_number=?", sNumber).isEmpty()){
					dao.execute("delete from cs_alarm where csa_id=?", map.get("csa_id"));
					continue;
				}else{
					init();
				}
			}
			if(getHistoryDao("cs_alarm_"+sNumber.toLowerCase()).executeQuery("select * from cs_alarm_"+sNumber.toLowerCase()+" where csa_id=?", map.get("csa_id")).isEmpty())
				getHistoryDao("cs_alarm_"+sNumber.toLowerCase()).execute(sqlTemplate.replace("{number}",sNumber.toLowerCase()), params);
			dao.execute("delete from cs_alarm where csa_id=?", map.get("csa_id"));
		}
		return list.size()-moving;
	}	
	
}
