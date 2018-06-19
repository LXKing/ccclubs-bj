package com.ccclubs.action.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.ccclubs.config.SYSTEM;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class HistoryDistribute extends Thread {

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
		Lazy3qDaoSupport historyDao = getHistoryDao("cs_history_state_"+number.toLowerCase());
		if(historyDao!=null)
			historyDao.execute("CREATE table if not exists cs_history_state_"+number.toLowerCase()+" LIKE cs_history_state");
		cars.put(number.toLowerCase(),true);
	}
	
	private static Lazy3qDaoSupport getHistoryDao(String tableName) {
		return $.getDao("ccclubs_system_history_ex");
		/*
		if(moved.get(tableName)!=null){
			return $.getDao("ccclubs_system_history_ex");
		}
		List<Map> moves = dao.executeQuery("select * from tb_data_move where table_name=?", tableName);
		Map move = moves.isEmpty()?null:moves.get(0);
		if(move==null){
			return $.getDao("ccclubs_system_history");
		}else if(move.get("status").toString().equals("1")){
			moved.put(tableName, true);
			return $.getDao("ccclubs_system_history_ex");
		}else{//正在迁移
			return $.getDao("ccclubs_system_history_ex");
		}
		*/
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
			try{
				sycnHost();
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
	
	
	//最后一次同步
	static int lastSycnHost = -1;
	/**
	 * 同步当前车辆状态的城市地域
	 */
	private void sycnHost() {
		//一个小时检查一次
		if(new Date().getHours()==lastSycnHost)
			return;
		lastSycnHost = new Date().getHours();		
		$.trace("#########################同步车辆当前状态、车辆警报的城市##############################");
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		dao.executeUpdate("update cs_state a left join cs_car b on a.css_number = b.csc_number and (b.csc_mask&1)!=1 set a.css_host = b.csc_host,a.css_car=b.csc_id");
		dao.executeUpdate("delete from cs_state where css_host is null");		
	}
	

	static String strKeys = "cshs_id,cshs_host,cshs_number,cshs_add_time,cshs_rented,cshs_warn,cshs_rfid,cshs_rfid_dte,cshs_obd_mile,cshs_mileage,cshs_speed,cshs_motor,cshs_oil,cshs_power,cshs_ev_battery,cshs_charging,cshs_fuel_mileage,cshs_electric_mileage,cshs_endurance,cshs_temperature,cshs_csq,cshs_power_consumption,cshs_longitude,cshs_latitude,cshs_order,cshs_current_time";
	static String keys[]=strKeys.split(",");
	static String sqlTemplate = "INSERT INTO cs_history_state_{number}("+strKeys+") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 

	/**
	 * 分发数据
	 * @return 
	 */
	private int distribute() {
		List<Map> list = dao.executeQuery("select * from cs_history_state order by cshs_id asc limit 100");
		int moving = 0;
		for(Map map:list){
			Object[] params=new Object[keys.length];
			for(int i=0;i<keys.length;i++){
				String key=keys[i];
				params[i] = map.get(key);
			}
			String sNumber  = (String) map.get("cshs_number");
			Lazy3qDaoSupport historyDao = getHistoryDao("cs_history_state_"+sNumber.toLowerCase());
			if(historyDao==null){//正在迁移的放弃插入
				moving++;
				continue;
			}
			sNumber = sNumber.toLowerCase();
			params[1]=carHosts.get(sNumber);//设置host值 
			if(cars.get(sNumber)==null || !cars.get(sNumber))
				init();
			if(getHistoryDao("cs_history_state_"+sNumber.toLowerCase()).executeQuery("select * from cs_history_state_"+sNumber.toLowerCase()+" where cshs_id=?", map.get("cshs_id")).isEmpty())
				getHistoryDao("cs_history_state_"+sNumber.toLowerCase()).execute(sqlTemplate.replace("{number}",sNumber.toLowerCase()), params);
			dao.execute("delete from cs_history_state where cshs_id=?", map.get("cshs_id"));
		}
		return list.size()-moving;
	}	
	
	public static void main(String...strings){
		new HistoryDistribute().start();
		new AlarmDistribute().start();
	}
}
