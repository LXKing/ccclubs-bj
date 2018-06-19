package com.ccclubs.action.admin;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.EventHelper;
import com.ccclubs.helper.TimeLineHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsState;
import com.ccclubs.model.SrvHost;
import com.ccclubs.vo.MapState;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper.LzMap;

public class CacheThread extends Thread {
	
	Long host = null;//当前线程扫描的地区
	
	static Map<Long,CacheThread> threadmapping = new HashMap();
	
	static{
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
	}
	
	
	public static CacheThread getCacheThread(Long host){
		CacheThread cacheThread = threadmapping.get(host);
		if(cacheThread==null && SrvHost.get(host)!=null)
			CreateHostThread(SrvHost.get(host));
		return threadmapping.get(host);
	}
	
	
	/**
	 * 数据缓存线程入口
	 * 分别为每个地域启动一个缓存线程
	 */
	@Override
	public synchronized void start() {
		
		TimeLineHelper.start();
		
		if(this.getHost()==null){
			$.trace("启动数据缓存线程，分发到地区");
			/*EventHelper.publish(
					EventHelper.MessageType.DATA, 
					0l, 0l, null, "system.started","",Thread.currentThread().getId());*/
			List<SrvHost> hosts = SrvHost.getSrvHostList($.Map(), -1);
			for(SrvHost srvHost:hosts){
				CreateHostThread(srvHost);
			}
		}else{
			$.trace(SrvHost.get(this.getHost()).getShName()+"地区数据缓存线程启动....");
			super.start();
		}
	}


	private static void CreateHostThread(SrvHost srvHost) {
		CacheThread cacheThread = new CacheThread();
		cacheThread.setHost(srvHost.getShId());
		cacheThread.start();
		threadmapping.put(srvHost.getShId(), cacheThread);
	}
	

	public void run(){
		while(true){
			
			try{
				//getOperations();						
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try{
				//getStatistics();						
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try{
				getCarstates();						
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(1000*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class DateSerializer implements JsonSerializer<java.util.Date> {

		@Override
		public JsonElement serialize(Date arg0, Type arg1,
				JsonSerializationContext arg2) {
			// TODO Auto-generated method stub
			if(arg0 == null){
				return null;
			}
			return new JsonPrimitive(String.valueOf(arg0.getTime()));
		}
	}
	
	
	class LocalCache<T>{
		
		private Date lastTime = new Date();
		private T data;
		
		public String getJsonData(){
			String json = null;
			if(this.data!=null){
				GsonBuilder gsonBuilder = new GsonBuilder();
				gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
				Gson gson = gsonBuilder.serializeNulls().create();
				json = gson.toJson(this.data);
			}
			return json;
		}
		
		public Date getLastTime() {
			return lastTime;
		}
		public void setLastTime(Date lastTime) {
			this.lastTime = lastTime;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
	}
	
	private volatile LocalCache<List<MapState>> statesData = new LocalCache<List<MapState>>();
	
	public List<MapState> getStateData(){
		return this.statesData.getData();
	}
	
	public String getSatateJsonData(){
		return this.statesData.getJsonData();
	}
		
	
	List<CsState> list = null;
	List<CsCar> cars = null;
	List<CsOrder> longorders = null;
	MapState ms = null;
	Map<String, Object> values = null;
	/**
	 * 获取车辆状态,一分钟取一次   
	 * @return
	 */
	public List<CsState> getCarstates() {
		
		list = SYSTEM.REDIS.getObject("CARSTATE", ""+host);
		if(list!=null)
			return list;
		
		cars = CsCar.getCsCarList($.add(CsCar.F.cscHost, host), -1);
		Map<String,CsCar> mapping = new HashMap();
		for(CsCar csCar:cars){
			mapping.put(csCar.getCscHost()+"-"+csCar.getCscNumber().toUpperCase(), csCar);
		}
		//根据当前时间把当前长租订单取出来，然后设置订单的长租标识
		longorders = CsOrder.getCsOrderList(
			$.add(CsOrder.F.csoLongOrder, true)
			.add(CsOrder.F.csoStartTimeEnd, new Date())
			.add(CsOrder.F.csoFinishTimeStart, new Date())
		, -1);
		Map<String,Boolean> longordermapping=new HashMap();
		for(CsOrder csOrder:longorders)
			longordermapping.put(csOrder.getCsoCarNumber().toUpperCase(), true);
		
		list = CsState.getCsStateList($.add(CsState.F.cssHost, host), 65535);
		for(CsState state:list){
			
			CsCar car = mapping.get(state.getCssHost()+"-"+state.getCssNumber().toUpperCase());
			if(car==null)continue;
			if(car.getCscStatus().shortValue()!=1)continue;
			long minute = (new Date().getTime()-state.getCssAddTime().getTime())/SYSTEM.MINUTE;
			if(minute>10){
				EventHelper.postEvent(host, 
					EventHelper.EventType.车辆掉线提醒,
					"车辆["+state.getCssNumber()+"]掉线超过"+minute+"分钟", 
					state.getCssId(),CsState.class
				);
			}
			state.setValues("longOrder", longordermapping.get(state.getCssNumber().toUpperCase()));
		}
		if(cars.size()>list.size()){
			Map<String,CsState> statusmap = new HashMap();
			for(CsState csState:list)
				statusmap.put(csState.getCssNumber(), csState);
			for(CsCar csCar:cars){
				CsState status = statusmap.get(csCar.getCscNumber());
				if(status==null)
					status = CsState.where().cssNumber(csCar.getCscNumber()).get();
				if(status==null){
					CsOutlets outlets = csCar.get$cscOutlets();
					status = new CsState()
					.cssHost(host)
					.cssAddTime(new Date())
					.cssCharging("0")
					.cssElectricMileage("0")
					.cssFuelMileage("0")
					.cssLatitude(outlets==null?null:outlets.getCsoLatitude().toString())
					.cssLongitude(outlets==null?null:outlets.getCsoLongitude().toString())
					.cssMileage("0")
					.cssMotor("0")
					.cssNumber(csCar.getCscNumber())
					.cssOil("0")
					.cssPower("0")
					.cssSpeed("0")
					.cssWarn("0").save();	
					list.add(status);
				}				
			}
		}
		
		SYSTEM.REDIS.setObject("CARSTATE", ""+host,list,$.parseInt(SYSTEM.MINUTE*2.23/1000));
		
		// =============================== 华丽丽分隔线 ==========================================
		List<MapState> mapdata = statesData.getData();
		if(mapdata == null){
			mapdata = new ArrayList<MapState>();
		}else{
			mapdata.clear();
		}
		
		for(CsState state : list){
			ms = new MapState();
			ms.setCssHost(state.getCssHost());
			ms.setCssAddTime(state.getCssAddTime());
			ms.setCssCharging(state.getCssCharging());
			ms.setCssEvBattery(state.getCssEvBattery());
			ms.setCssLatitude(state.getCssLatitude());
			ms.setCssLongitude(state.getCssLongitude());
			ms.setCssNumber(state.getCssNumber());
			ms.setCssOrder(state.getCssOrder());
			ms.setCssPower(state.getCssPower());
			ms.setValues(state.getValues());
			mapdata.add(ms);
		}
		statesData.setData(mapdata);
		statesData.setLastTime(new Date());
		Logger.getRootLogger().error(" ^^^^^^^^^^^^^^^^^^^^^^^^^^^^ state size :\t "+list.size());
		return list;
	}
	
	
	/**
	 * 获取统计数据,取最近一年的统计数据
	 * 下单数，注册数，登录数
	 * @return
	 */
	public Map getStatistics(){
		Map map = SYSTEM.REDIS.getObject("STATISTIC", ""+host);
		if(map!=null)
			return map;
		map = new HashMap();
		
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		List<Date> datenames = new ArrayList();
		Calendar calendar=Calendar.getInstance();	
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		Date start = calendar.getTime();
		map.put("end", calendar.getTime());		
		datenames.add(0,calendar.getTime());
		for(int i=0;i<11;i++){
			calendar.add(Calendar.MONTH, -1);			
			datenames.add(0,calendar.getTime());
			start = calendar.getTime();
		}
		map.put("datenames", datenames);
		map.put("start", start);
				
		Map<String,String> sqls=new HashMap();
		sqls.put("orders", "select count(1) as 'value',cso_add_time as 'date',date_format(cso_add_time,'%Y-%m') as 'month' from cs_order where cso_host="+host+" and cso_add_time>? group by month order by month desc");
		sqls.put("regists","select count(1) as 'value',csm_add_time as 'date',date_format(csm_add_time,'%Y-%m') as 'month' from cs_member where csm_host="+host+" and csm_add_time>? group by month order by month desc");

		for(Map.Entry<String, String> sql : sqls.entrySet()){
			List<Map> list = dao.executeQuery(sql.getValue(),start);
			Map<String,Map> kv=new HashMap();
			for(Map m:list)
				kv.put($.date((Date)m.get("date"), "yy年MM月"), m);
			list=new ArrayList();
			for(Date date:datenames){
				String sDate = $.date(date, "yy年MM月");
				list.add(kv.get(sDate)!=null?kv.get(sDate):($.add("value", 0).add("date", date)));
			}
			map.put(sql.getKey(), list);
		}		
		
		SYSTEM.REDIS.setObject("STATISTIC", ""+host,map,$.parseInt(SYSTEM.HOUR*15/1000));
		
		return map;
	}
	
	
	
	/**
	 * 获取运营数据：下单数，下单额，充值额，注册数，登录数
	 * 时间：今天，昨天，本周，本月
	 * @return
	 */
	public Map getOperations(){
		Map map = new HashMap();		
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");		
		
		Map<String,HashMap> times=new HashMap();
		Calendar calendar=Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		times.put("today",$.add("start", calendar.getTime()).add("end", new Date()));//今天
		calendar.add(Calendar.DATE, -1);
		times.put("yesterday",$.add("start", calendar.getTime()).add("end", times.get("today").get("start")));//昨天
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		times.put("week",$.add("start", calendar.getTime()).add("end", new Date()));//本周
		calendar=Calendar.getInstance();//因为设置为本周后，有可能跑到上个月，所以重新获取日历实例
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		times.put("month",$.add("start", calendar.getTime()).add("end", new Date()));//本月
				
		Map<String,String> sqls=new HashMap();
		sqls.put("orders", "select count(1) as 'value' from cs_order where cso_host="+host+" and cso_add_time>? and cso_add_time<? and cso_status!=7");
		sqls.put("recharge", "select sum(csar_amount)-sum(csar_refund) as 'value' from cs_alipay_record where csar_host="+host+" and csar_add_time>? and csar_add_time<? and csar_status=1");
		sqls.put("regists", "select count(1) as 'value' from cs_member where csm_status!=-1 and csm_host="+host+" and csm_add_time>? and csm_add_time<?");
		sqls.put("logins", "select count(1) as 'value' from cs_member where csm_status!=-1 and csm_host="+host+" and csm_last_time>? and csm_last_time<?");
		
		LzMap cacheSeconds = $.add("today", SYSTEM.HOUR*2/1000)
		.add("yesterday", SYSTEM.HOUR*7/1000)
		.add("week", SYSTEM.HOUR*11/1000)
		.add("month", SYSTEM.HOUR*17/1000);
		
		for(Map.Entry<String, String> sql : sqls.entrySet()){
			Map items=new HashMap();
			for(Map.Entry<String, HashMap> time : times.entrySet()){
				List<Map> list = (List<Map>) SYSTEM.REDIS.getObject("OPERATION", sql.getKey()+"."+time.getKey()+"."+host);
				if(list==null){
					list = dao.executeQuery(sql.getValue(), time.getValue().get("start"), time.getValue().get("end"));
					SYSTEM.REDIS.setObject("OPERATION", sql.getKey()+"."+time.getKey()+"."+host, list,$.parseInt(cacheSeconds.get(time.getKey())));
				}
				items.put(time.getKey(), list.get(0).get("value"));
			}
			map.put(sql.getKey(), items);
		}	
		
		
		//取订单金额
		{
			Map items=new HashMap();			
			for(Map.Entry<String, HashMap> time : times.entrySet()){	
				Object moneys = SYSTEM.REDIS.getObject("OPERATION", "moneys"+"."+time.getKey()+"."+host);
				if(moneys==null){
					moneys = $.or(getOrderMoney((Date)time.getValue().get("start"),(Date)time.getValue().get("end")).get("value"),0);
					SYSTEM.REDIS.setObject("OPERATION", "moneys"+"."+time.getKey()+"."+host, moneys,$.parseInt(cacheSeconds.get(time.getKey())));
				}
				items.put(time.getKey(),moneys);
			}
			map.put("order$", items);
		}
		
		
		//取订单时长
		{
			Map items=new HashMap();			
			for(Map.Entry<String, HashMap> time : times.entrySet()){
				Object longs = SYSTEM.REDIS.getObject("OPERATION", "longs"+"."+time.getKey()+"."+host);
				if(longs==null){
					longs = $.or(getOrderTime((Date)time.getValue().get("start"),(Date)time.getValue().get("end")).get("value"),0);
					SYSTEM.REDIS.setObject("OPERATION", "longs"+"."+time.getKey()+"."+host, longs,$.parseInt(cacheSeconds.get(time.getKey())));
				}
				items.put(time.getKey(),longs);
			}
			map.put("ordert", items);
		}
		
			
		return map;
	}
		
	
	
	
	
	/**
	 * 获取订单金额指标
	 * @param month
	 * @return
	 */
	private Map getOrderMoney(Date start,Date end) {
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");		
		long max = end.getTime()/1000;//最大时间转成时间戳
		long min = start.getTime()/1000;//最小时间转成时间戳		
		String factor="(LEAST(unix_timestamp(cso_finish_time),{max})-GREATEST(unix_timestamp(cso_start_time),{min}))/(unix_timestamp(cso_finish_time)-unix_timestamp(cso_start_time))";
		factor = factor.replace("{max}", ""+max).replace("{min}", ""+min);
		String sumMoney  = " sum("+moenyType(true)+" * {factor}) as 'value' ".replace("{factor}", ""+factor);//订单金额
		String strSql = "select {money} from cs_order " +
			"where cso_host="+host+" and cso_start_time<? and cso_finish_time>? and cso_income =1 and "+orderStatusSql(true)+"";
		strSql = strSql.replace("{money}", sumMoney);		
		List<Map> list = dao.executeQuery(strSql, end,start);
		return list.get(0);
	}
	/**
	 * 获取订单时长指标
	 * @param month
	 * @return
	 */
	private Map getOrderTime(Date start,Date end) {
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");		
		long max = end.getTime()/1000;//最大时间转成时间戳
		long min = start.getTime()/1000;//最小时间转成时间戳		
		String factor="(LEAST(unix_timestamp(cso_finish_time),{max})-GREATEST(unix_timestamp(cso_start_time),{min}))/(unix_timestamp(cso_finish_time)-unix_timestamp(cso_start_time))";
		factor = factor.replace("{max}", ""+max).replace("{min}", ""+min);		
		String sumTime = " sum((LEAST(unix_timestamp(cso_finish_time),{max})-GREATEST(unix_timestamp(cso_start_time),{min})))*1.0 as 'value' ".replace("{max}", ""+max).replace("{min}", ""+min);//订单时长
		String strSql = "select {time} from cs_order " +
			"where cso_host="+host+" and cso_start_time<? and cso_finish_time>? and cso_income = 1 and "+orderStatusSql(true)+"";
		strSql = strSql.replace("{time}", sumTime);		
		List<Map> list = dao.executeQuery(strSql, end,start);
		return list.get(0);
	}
	public String orderStatusSql(boolean all){
		return all==true?" (cso_status!=7 and cso_status!=3) ":"cso_status=4";
	}
	public String moenyType(boolean all){
		return all==true?"cso_pay_need":"cso_pay_real";
	}
	

	public Long getHost() {
		return host;
	}

	public void setHost(Long host) {
		this.host = host;
	}

}
